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

	public ProductoScraped scrapeLink(String link) throws IllegalArgumentException, IOException {
		LogSingleton log = LogSingleton.getInstance();
		Document doc = Jsoup.connect(link).get();
		try {
			return obtenProductoDeEbay(doc);
		} catch (NullPointerException e) {
			log.getLoggerScraperEJB().error("Se ha producido un error en ScraperEJB: " + e);
		}
		return null;
	}

	public ProductoScraped obtenProductoDeEbay(Document doc) throws NullPointerException {
		Element span = doc.select("#itemTitle span").first();
		String nombre = span.nextSibling().toString();
		String precio = doc.select("#prcIsum").attr("content");
		String imgLink = doc.select("#icImg").attr("src");
		return new ProductoScraped(nombre, Double.parseDouble(precio), imgLink);
	}

}
