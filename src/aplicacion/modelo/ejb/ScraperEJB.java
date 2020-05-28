package aplicacion.modelo.ejb;

import java.io.IOException;
import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import aplicacion.modelo.LogSingleton;
import aplicacion.modelo.pojo.ProductoScraped;

@Stateless
@LocalBean
public class ScraperEJB {

	/****
	 * Hace scraping de un enlace y devuelve sus datos o null si no lo encuentra.
	 * 
	 * @param link Enlace del producto.
	 * @return Los datos obtenidos de ese producto de ese producto.
	 */
	public ProductoScraped scrapeLink(String link) {
		LogSingleton log = LogSingleton.getInstance();
		Document doc;
		try {
			doc = Jsoup.connect(link).get();
		} catch (IOException e) {
			log.getLoggerScraperEJB().error("Se ha producido un error en ScraperEJB: " + e);
			return null;
		} catch (IllegalArgumentException e) {
			log.getLoggerScraperEJB().error("Se ha producido un error en ScraperEJB: " + e);
			return null;
		}
		ProductoScraped result = obtenProductoDeAmazon(doc);
		if (result == null) {
			result = obtenProductoDeEbay(doc);
		}
		if (result == null) {
			result = obtenProductoDeElCorteIngles(doc);
		}
		return result;
	}

	/****
	 * Quita el simbolo del Euro, quita los puntos de los miles y reemplaza la coma
	 * de los decimales por un punto. Devuelve null si precio es null.
	 * 
	 * @param precio Precio string a tratar.
	 * @return Precio sin simbolo de Euro, con los puntos eliminados y con punto
	 *         para los decimales en vez de una coma.
	 */
	private String quitarSimboloEuro(String precio) {
		if (precio != null) {
			String precios[] = precio.split("-");
			if (precios.length == 2) {
				precio = precios[1];
			}
			precio = precio.replace(".", "");
			return precio.substring(0, precio.length() - 1).replace(',', '.');
		} else {
			return null;
		}
	}

	/****
	 * Forma un objeto ProductoScraped a partir del título y precio de un producto.
	 * Específico para los productos de Amazon.
	 * 
	 * @param titulo       Título de un producto.
	 * @param precioString Precio de un producto.
	 * @return ProductoScraped.
	 */
	private ProductoScraped formarProductoScrapedDeAmazon(String titulo, String precioString) {
		LogSingleton log = LogSingleton.getInstance();
		if (!titulo.equals("") & !precioString.equals("")) {
			precioString = quitarSimboloEuro(precioString);
		} else if (!titulo.equals("")) {
			precioString = "-1";
		}
		Double precio = Double.parseDouble("-1");
		try {
			precio = Double.parseDouble(precioString);
		} catch (Exception e) {
			log.getLoggerScraperEJB().error("Se ha producido un error en ScraperEJB: " + e);
		}
		if (!titulo.equals("") & !precioString.equals("")) {
			return new ProductoScraped(titulo, precio, "imgs/ProductoDeAmazon.png");
		} else {
			return null;
		}
	}

	/****
	 * A partir del documento obtenido de un enlace, prueba a extraer los datos de
	 * cuatro layouts diferentes de la página de Amazon y los guarda en un array.
	 * Finalmente busca en cada array si ha encontrado un título y un precio para
	 * devolver un producto, si el título falta devuelve null. Si el precio falta
	 * pero el título no, devuelve un producto no disponible. Si los dos faltan
	 * devuelve null
	 * 
	 * @param doc Documento HTML.
	 * @return ProductoScraped.
	 */
	private ProductoScraped obtenProductoDeAmazon(Document doc) {
		LogSingleton log = LogSingleton.getInstance();
		ArrayList<String> titulos = new ArrayList<String>();
		ArrayList<String> precios = new ArrayList<String>();
		try {
			titulos.add(doc.select("#productTitle").text());
			precios.add(doc.select("#priceblock_ourprice").text());
		} catch (NullPointerException e) {
			log.getLoggerScraperEJB().error("Se ha producido un error en ScraperEJB: " + e);
		}
		try {
			titulos.add(doc.select("#title").text());
			precios.add(doc.select("#olp-upd-new-freeshipping > a:nth-child(1) > span:nth-child(1)").text());
			precios.add(doc.select("#outOfStock > div:nth-child(1) > div:nth-child(1) > span:nth-child(1)").text());
		} catch (NullPointerException e) {
			log.getLoggerScraperEJB().error("Se ha producido un error en ScraperEJB: " + e);
		}
		try {
			titulos.add(doc.select("#title").text());
			precios.add(doc.select(".offer-price").text());
		} catch (NullPointerException e) {
			log.getLoggerScraperEJB().error("Se ha producido un error en ScraperEJB: " + e);
		}
		try {
			titulos.add(doc.select("#productTitle").text());
			precios.add(doc.select("#price_inside_buybox").text());
		} catch (NullPointerException e) {
			log.getLoggerScraperEJB().error("Se ha producido un error en ScraperEJB: " + e);
		}
		if (!titulos.isEmpty()) {
			for (String titulo : titulos) {
				if (!titulo.equals("")) {
					for (String precio : precios) {
						if (!precio.equals("")) {
							return formarProductoScrapedDeAmazon(titulo, precio);
						}
					}
					return formarProductoScrapedDeAmazon(titulo, "");
				}
			}
		}
		return null;
	}

	/****
	 * A partir del documento obtenido de un enlace, prueba a extraer los datos de
	 * un layout de la página de Ebay.
	 * 
	 * @param doc Documento HTML
	 * @return ProductoScraped.
	 */
	private ProductoScraped obtenProductoDeEbay(Document doc) {
		LogSingleton log = LogSingleton.getInstance();
		try {
			Element span = doc.select("#itemTitle span").first();
			String nombre = span.nextSibling().toString();
			String precio = doc.select("#prcIsum").attr("content");
			String imgLink = doc.select("#icImg").attr("src");
			return new ProductoScraped(nombre, Double.parseDouble(precio), imgLink);
		} catch (NullPointerException e) {
			log.getLoggerScraperEJB().error("Se ha producido un error en ScraperEJB: " + e);
		}
		return null;
	}

	/****
	 * A partir del documento obtenido de un enlace, prueba a extraer los datos de
	 * un layout de la página de El Corte Ingles. Si está la opcion de añadir a la
	 * cesta devuelve un producto. Si no está devuelve un producto no disponible. Si
	 * algo falla devuelve null.
	 * 
	 * @param doc Documento HTML.
	 * @return ProductoScraped.
	 */
	private ProductoScraped obtenProductoDeElCorteIngles(Document doc) {
		LogSingleton log = LogSingleton.getInstance();
		try {
			String titulo = doc.select(".brand > a:nth-child(1)").text();
			String precio = doc.select("span.current").text();
			String imgLink = doc.select("#product-image-placer").attr("src");
			String precios[] = precio.split("€");
			String disponibilidad = "";
			Element opcion = doc.select("a.event:nth-child(6)").first();
			if (opcion == null) {
				opcion = doc.select("a.event:nth-child(7)").first();
			}
			disponibilidad = opcion.text();
			if (precios.length > 1) {
				precio = precios[0];
			}
			if (!precio.equals("")) {
				if (disponibilidad.equals("Añadir a la cesta")) {
					precio = quitarSimboloEuro(precio);
					return new ProductoScraped(titulo, Double.parseDouble(precio), imgLink);
				} else {
					return new ProductoScraped(titulo, Double.parseDouble("-1"), imgLink);
				}
			}

		} catch (NullPointerException e) {
			log.getLoggerScraperEJB().error("Se ha producido un error en ScraperEJB: " + e);
		}
		return null;
	}

}
