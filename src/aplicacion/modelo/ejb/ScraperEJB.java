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
			doc = Jsoup.connect(link)
					.userAgent("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:76.0) Gecko/20100101 Firefox/76.0").get();
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
	 * Forma un objeto ProductoScraped a partir del título, precio de un producto y
	 * su imágen en forma de bytes o por su enlace. Específico para los productos de
	 * Amazon.
	 * 
	 * @param titulo       Título del producto.
	 * @param precioString Precio del producto.
	 * @param imageBytes   Bytes de la imágen.
	 * @param enlaceImg    Enlace de la imágen del producto sin codificar.
	 * @return ProductoScraped.
	 */
	private ProductoScraped formarProductoScrapedDeAmazon(String titulo, String precioString, byte[] imageBytes,
			String enlaceImg) {
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
			if (imageBytes != null) {
				return new ProductoScraped(titulo, precio, imageBytes);
			} else if (enlaceImg != null) {
				return new ProductoScraped(titulo, precio, enlaceImg);
			}
		}
		return null;
	}

	/****
	 * Obtiene la imágen de Amazon, la descodifica y la guarda en un array de bytes.
	 * 
	 * @param doc Documento HTML.
	 * @return Imágen de Amazon en forma de array de bytes.
	 */
	public byte[] obtenImgDeAmazon(Document doc) {
		LogSingleton log = LogSingleton.getInstance();
		// Obtener un enlace a la foto del producto de amazon
		try {
			Element e = doc.select("#landingImage").first();
			if (e != null) {
				String s = e.attr("src");
				if (s != null) {
					// Tenemos foto en formato base64. La decodificamos y la guardamos a disco

					// si la imágen no está codificada lanza una excepción
					String encodedImg = s.split(",")[1];
					byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(encodedImg);

					return imageBytes;
				}
			}
		} catch (Exception e) {
			log.getLoggerScraperEJB().error("Se ha producido un error en ScraperEJB: " + e);
		}
		return null;
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
		/**
		 * Obtiene la imágen de Amazon
		 */
		byte[] imageBytes = obtenImgDeAmazon(doc);
		if (!titulos.isEmpty()) {
			for (String titulo : titulos) {
				if (!titulo.equals("")) {
					for (String precio : precios) {
						if (!precio.equals("")) {
							/**
							 * Si no ha conseguido la imágen comprueba si la imágen no está codificada.
							 */
							if (imageBytes == null) {
								try {
									Element e = doc.select("#landingImage").first();
									String s = e.attr("src");
									/**
									 * Si la imágen no está codificada guarda el enlace en el producto.
									 */
									if (s.startsWith("https://")) {
										return formarProductoScrapedDeAmazon(titulo, precio, null, s);
									}
								} catch (Exception e) {
									log.getLoggerScraperEJB().error("Se ha producido un error en ScraperEJB: " + e);
								}
							} else {
								return formarProductoScrapedDeAmazon(titulo, precio, imageBytes, null);
							}
						}
					}
					/**
					 * Si el precio se deja en blanco se marca el producto como no disponible.
					 */
					return formarProductoScrapedDeAmazon(titulo, "", imageBytes, null);
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
