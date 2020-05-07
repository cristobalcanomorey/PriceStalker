package aplicacion.modelo.ejb;

import java.io.IOException;

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

	public ProductoScraped scrapeLink(String link, int ms) {
		LogSingleton log = LogSingleton.getInstance();
		Document doc;
		try {
			doc = Jsoup.connect(link).timeout(ms).get();
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

	private ProductoScraped obtenProductoDeAmazon(Document doc) {
		LogSingleton log = LogSingleton.getInstance();
		String titulo = null;
		String precioString = null;
		try {
			titulo = doc.select("#productTitle").text();
			precioString = doc.select("#priceblock_ourprice").text();
			return formarProductoScrapedDeAmazon(titulo, precioString);
		} catch (NullPointerException e) {
			log.getLoggerScraperEJB().error("Se ha producido un error en ScraperEJB: " + e);
		}
		try {
			titulo = doc.select("#title").text();
			precioString = doc.select("#olp-upd-new-freeshipping > a:nth-child(1) > span:nth-child(1)").text();
			if (precioString.equals("")) {
				precioString = doc.select("#outOfStock > div:nth-child(1) > div:nth-child(1) > span:nth-child(1)")
						.text();
			}
			return formarProductoScrapedDeAmazon(titulo, precioString);
		} catch (NullPointerException e) {
			log.getLoggerScraperEJB().error("Se ha producido un error en ScraperEJB: " + e);
		}
		try {
			titulo = doc.select("#title").text();
			precioString = doc.select(".offer-price").first().text();
			return formarProductoScrapedDeAmazon(titulo, precioString);
		} catch (NullPointerException e) {
			log.getLoggerScraperEJB().error("Se ha producido un error en ScraperEJB: " + e);
		}
		return null;
	}

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

	private ProductoScraped obtenProductoDeElCorteIngles(Document doc) {
		LogSingleton log = LogSingleton.getInstance();
		try {
			String titulo = doc.select(".brand > a:nth-child(1)").text();
			String precio = doc.select("span.current").text();
			String imgLink = doc.select("#product-image-placer").attr("src");
			String precios[] = precio.split("€");
			if (precios.length > 1) {
				precio = precios[0];
			}
			if (!precio.equals("")) {
				precio = quitarSimboloEuro(precio);
				return new ProductoScraped(titulo, Double.parseDouble(precio), imgLink);
			}
		} catch (NullPointerException e) {
			log.getLoggerScraperEJB().error("Se ha producido un error en ScraperEJB: " + e);
		}
		return null;
	}

}
