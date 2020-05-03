package aplicacion.modelo.ejb;

import java.time.LocalDateTime;
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

	private int i = 0;
	private int numeroDeProductos = 0;

	@SuppressWarnings("unused")
	@Schedule(second = "0", minute = "*/5", hour = "12", dayOfWeek = "*", dayOfMonth = "*", month = "*", year = "*", info = "MyTimer")
	private void scheduledTimeout(final Timer t) {

		numeroDeProductos = productosEJB.getNumeroDeProductosEnAlgunaLista();

		if (i < numeroDeProductos) {
			CaracteristicasDeProducto producto = productosEJB.getCaracteristicasDelProductoNumero(i);

			if (producto != null) {
				ArrayList<Contenido> contenidos = listasEJB.getContenidosPorIdProducto(producto.getId());
				ProductoScraped productoScraped = scraperEJB.scrapeLink(producto.getLink());

				producto.setNombre(productoScraped.getNombre());
				producto.setImgLink(productoScraped.getImgLink());

				productosEJB.updateCaracteristicasDeProducto(producto);
				productosEJB.insertPrecio(producto.getId(), productoScraped);

				for (Contenido contenido : contenidos) {
					if (contenido.getPrecioObjetivo().compareTo(productoScraped.getPrecio()) <= 0) {
						Usuario usuario = usuariosEJB.getUsuarioPorIdLista(contenido.getIdLista());
						mailEJB.enviarCorreo(usuario, contenido, productoScraped);
					}
				}

			}
		}

		if (LocalDateTime.now().getMinute() == 55) {
			i = 0;
		} else {
			i++;
		}

	}
}