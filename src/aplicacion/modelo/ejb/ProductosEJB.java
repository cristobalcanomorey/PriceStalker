package aplicacion.modelo.ejb;

import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import aplicacion.modelo.LogSingleton;
import aplicacion.modelo.dao.PrecioDAO;
import aplicacion.modelo.dao.ProductoDAO;
import aplicacion.modelo.pojo.CaracteristicasDeProducto;
import aplicacion.modelo.pojo.Producto;
import aplicacion.modelo.pojo.ProductoScraped;
import aplicacion.modelo.pojo.ProductoSinPrecio;
import aplicacion.modelo.pojo.Usuario;

@Stateless
@LocalBean
public class ProductosEJB {

	/****
	 * Obtiene los productos de un usuario.
	 * 
	 * @param usuario Usuario.
	 * @return Su lista de productos.
	 */
	public ArrayList<Producto> productosPorUserId(Usuario usuario) {
		return ProductoDAO.getProductosPorUserId(usuario.getId());
	}

	/****
	 * Obtiene un producto y su coste actual por el ID contenido.
	 * 
	 * @param idContenido ID del contenido.
	 * @return Producto con el coste actual.
	 */
	public Producto obtenerProductoPorIdContenido(String idContenido) {
		return ProductoDAO.getProductoPorIdContenido(idContenido);
	}

	/****
	 * Obtiene el id de un producto si el enlace ya existe o null si no existe.
	 * 
	 * @param enlace Enlace del producto.
	 * @return ID del producto.
	 */
	public Integer existeProducto(String enlace) {
		return ProductoDAO.existeProducto(enlace);
	}

	/****
	 * Añade un producto en la tabla BBDD.
	 * 
	 * @param nombre  Nombre del producto.
	 * @param enlace  Enlace de compra del producto.
	 * @param imgLink Enlace de la imágen del producto.
	 */
	public void insertProducto(String nombre, String enlace, String imgLink) {
		ProductoDAO.insertProducto(nombre, enlace, imgLink);
	}

	/****
	 * Obtiene una lista de los productos sin precio de un usuario.
	 * 
	 * @param usuario Usuario.
	 * @return Lista de productos que todabía no tienen precios de una lista.
	 */
	public ArrayList<ProductoSinPrecio> productosSinPrecioPorUserId(Usuario usuario) {
		return ProductoDAO.productosSinPrecioPorUserId(usuario.getId());
	}

	/****
	 * Obtiene el nombre de un producto a partir del ID del contenido y devuelve
	 * null si idContenido no es un número.
	 * 
	 * @param idContenido ID del contenido.
	 * @return Nombre del producto.
	 */
	public String getNombrePorIdContenido(String idContenido) {
		LogSingleton log = LogSingleton.getInstance();
		int contenidoId = -1;
		try {
			contenidoId = Integer.parseInt(idContenido);
		} catch (Exception e) {
			log.getLoggerProductosEJB().error("Se ha producido un error en ProductosEJB: ", e);
			return null;
		}
		return ProductoDAO.getNombrePorIdContenido(contenidoId);
	}

	/****
	 * Obtiene un producto por el ID del usuario y el ID contenido. Y devuelve null
	 * si el contenidoId no es un número.
	 * 
	 * @param idContenido ID del contenido.
	 * @param idUsuario   ID del usuario
	 * @return Producto.
	 */
	public ProductoSinPrecio getProductoAEliminar(String idContenido, Integer idUsuario) {
		LogSingleton log = LogSingleton.getInstance();
		int contenidoId = -1;
		try {
			contenidoId = Integer.parseInt(idContenido);
		} catch (Exception e) {
			log.getLoggerProductosEJB().error("Se ha producido un error en ProductosEJB: ", e);
			return null;
		}
		return ProductoDAO.getProductoAEliminar(contenidoId, idUsuario);
	}

	/****
	 * Elimina un contenido de la lista por su ID y el ID del usuario. Devuelve null
	 * si el contenidoId no es un número.
	 * 
	 * @param idContenido ID del contenido.
	 * @param idUsuario   ID del usuario.
	 */
	public void eliminarProductoDeLaLista(String idContenido, Integer idUsuario) {
		LogSingleton log = LogSingleton.getInstance();
		int contenidoId = -1;
		try {
			contenidoId = Integer.parseInt(idContenido);
		} catch (Exception e) {
			log.getLoggerProductosEJB().error("Se ha producido un error en ProductosEJB: ", e);
		}
		ProductoDAO.eliminarProductoDeLaLista(contenidoId, idUsuario);
	}

	/***
	 * Obtiene el número de productos que están al menos en una lista.
	 * 
	 * @return Número de productos.
	 */
	public int getNumeroDeProductosEnAlgunaLista() {
		return ProductoDAO.getNumeroDeProductosEnAlgunaLista();
	}

	/****
	 * Modifica las características de un producto. Si la imágen se ha obtenido de
	 * Amazon se guarda en disco.
	 * 
	 * @param producto Producto con las nuevas características.
	 */
	public void updateCaracteristicasDeProducto(CaracteristicasDeProducto producto, byte[] imageBytes) {
		if (imageBytes != null) {
			Integer id = ProductoDAO.getIdDelProductoPorEnlace(producto.getLink());
			if (id != null) {
				guardarImgEnDisco(imageBytes,
						"/home/ubuntu/PriceStalker/PriceStalker/WebContent/imgs/ProductoDeAmazon" + id + ".jpg");
				producto.setImgLink("imgs/ProductoDeAmazon" + id + ".jpg");
			}
		}

		ProductoDAO.updateCaracteristicasDeProducto(producto);
	}

	/****
	 * Guarda la imágen en el disco duro.
	 * 
	 * @param imageBytes Imágen en forma de bytes.
	 * @param ruta       Ruta absoluta de la imágen.
	 */
	public void guardarImgEnDisco(byte[] imageBytes, String ruta) {
		LogSingleton log = LogSingleton.getInstance();
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(ruta);
			fos.write(imageBytes);
			fos.close();
		} catch (Exception e) {
			log.getLoggerProductosEJB().error("Se ha producido un error en ProductosEJB: " + e);
		}

	}

	/****
	 * Añade un precio a un producto.
	 * 
	 * @param id              ID del producto.
	 * @param productoScraped Producto con el precio a añadir.
	 */
	public void insertPrecio(Integer id, ProductoScraped productoScraped) {
		PrecioDAO.insertPrecio(id, productoScraped.getPrecio());
	}

	/****
	 * Obtiene las características de los productos.
	 * 
	 * @return Características de los productos.
	 */
	public ArrayList<CaracteristicasDeProducto> getCaracteristicasDeProductos() {
		return ProductoDAO.getCaracteristicasDeProductos();
	}

	/****
	 * Modifica los datos de un producto para marcarlo como defectuoso.
	 * 
	 * @param caracteristicasDeProducto Características originales del producto.
	 */
	public void marcarComoDefectuoso(CaracteristicasDeProducto caracteristicasDeProducto) {
		ProductoDAO.marcarComoDefectuoso(caracteristicasDeProducto);
	}

	/****
	 * Elimina todos los productos marcados como defectuosos.
	 */
	public void eliminarDefectuosos() {
		ProductoDAO.eliminarDefectuosos();
	}

}
