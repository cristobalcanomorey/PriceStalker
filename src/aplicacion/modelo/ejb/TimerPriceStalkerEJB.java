package aplicacion.modelo.ejb;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.Timer;

import aplicacion.modelo.pojo.CaracteristicasDeProducto;
import aplicacion.modelo.pojo.Contenido;
import aplicacion.modelo.pojo.ProductoScraped;
import aplicacion.modelo.pojo.Usuario;

@Stateless
public class TimerPriceStalkerEJB {

	public TimerPriceStalkerEJB() {

	}

	@EJB
	ProductosEJB productosEJB;

	@EJB
	ListasEJB listasEJB;

	@EJB
	UsuariosEJB usuariosEJB;

	@EJB
	ScraperEJB scraperEJB;

	@EJB
	MailEJB mailEJB;

	private int numeroDeProductos = 0;

	@SuppressWarnings("unused")
	@Schedule(second = "0", minute = "23", hour = "14", dayOfWeek = "*", dayOfMonth = "*", month = "*", year = "*", info = "MyTimer")
	private void scheduledTimeout(final Timer t) {

		numeroDeProductos = productosEJB.getNumeroDeProductosEnAlgunaLista();
		if (numeroDeProductos > 3600) {
			productosEJB.eliminarDefectuosos();
			numeroDeProductos = productosEJB.getNumeroDeProductosEnAlgunaLista();
		}
		if (numeroDeProductos > 0 && numeroDeProductos <= 3600) {
			int ms = (3600 / numeroDeProductos) * 1000;
			ArrayList<CaracteristicasDeProducto> productos = productosEJB.getCaracteristicasDeProductos();

			if (productos != null) {
				for (CaracteristicasDeProducto caracteristicasDeProducto : productos) {
					ArrayList<Contenido> contenidos = listasEJB
							.getContenidosPorIdProducto(caracteristicasDeProducto.getId());

					if (ms > 30000) {
						ms = 30000;
					}

					ProductoScraped productoScraped = scraperEJB.scrapeLink(caracteristicasDeProducto.getLink(), ms);

					if (productoScraped != null) {
						caracteristicasDeProducto.setNombre(productoScraped.getNombre());
						caracteristicasDeProducto.setImgLink(productoScraped.getImgLink());

						productosEJB.updateCaracteristicasDeProducto(caracteristicasDeProducto);
						productosEJB.insertPrecio(caracteristicasDeProducto.getId(), productoScraped);
						if (!productoScraped.getPrecio().equals(Double.valueOf("-1"))) {
							for (Contenido contenido : contenidos) {
								if (productoScraped.getPrecio().compareTo(contenido.getPrecioObjetivo()) <= 0) {
									Usuario usuario = usuariosEJB.getUsuarioPorIdLista(contenido.getIdLista());
									mailEJB.enviarCorreoBajadaDePrecio(usuario, contenido, productoScraped,
											caracteristicasDeProducto.getLink());
								}
							}
						}
					} else {
						productosEJB.marcarComoDefectuoso(caracteristicasDeProducto);
					}
				}
			}
		}
	}
}