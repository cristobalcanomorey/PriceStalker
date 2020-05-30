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
	@Schedule(second = "0", minute = "0", hour = "17", dayOfWeek = "*", dayOfMonth = "*", month = "*", year = "*", info = "MyTimer")
	/****
	 * Hace scraping de todos los productos una vez al día y manda un correo a los
	 * usuarios que tengan como precio objetivo un precio inferior al precio actual
	 * del producto.
	 * 
	 * @param t Timer.
	 */
	private void scheduledTimeout(final Timer t) {
		numeroDeProductos = productosEJB.getNumeroDeProductosEnAlgunaLista();
		/**
		 * Si hay muchos productos elimina los defectuosos.
		 */
		if (numeroDeProductos > 26) {
			productosEJB.eliminarDefectuosos();
			numeroDeProductos = productosEJB.getNumeroDeProductosEnAlgunaLista();
		}
		/**
		 * Si hay algún producto hace scraping y guarda sus nuevos datos en la BBDD.
		 */
		if (numeroDeProductos > 0) {
			ArrayList<CaracteristicasDeProducto> productos = productosEJB.getCaracteristicasDeProductos();

			if (productos != null) {
				/**
				 * Por cada producto hace scraping de su enlace.
				 */
				for (CaracteristicasDeProducto caracteristicasDeProducto : productos) {
					ArrayList<Contenido> contenidos = listasEJB
							.getContenidosPorIdProducto(caracteristicasDeProducto.getId());

					ProductoScraped productoScraped = scraperEJB.scrapeLink(caracteristicasDeProducto.getLink());

					/**
					 * Si no se han obtenido los datos necesarios del enlace se marca como
					 * defectuoso.
					 */
					if (productoScraped != null) {
						caracteristicasDeProducto.setNombre(productoScraped.getNombre());
						caracteristicasDeProducto.setImgLink(productoScraped.getImgLink());

						productosEJB.updateCaracteristicasDeProducto(caracteristicasDeProducto,
								productoScraped.getImageBytes());
						productosEJB.insertPrecio(caracteristicasDeProducto.getId(), productoScraped);
						/**
						 * Si el producto está disponible y comprueba si su valor actual es inferior del
						 * valor objetivo de algún usuario.
						 */
						if (!productoScraped.getPrecio().equals(Double.valueOf("-1"))) {
							for (Contenido contenido : contenidos) {
								/**
								 * Si el precio actual del producto es inferior al precio objetivo manda un
								 * correo al usuario.
								 */
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