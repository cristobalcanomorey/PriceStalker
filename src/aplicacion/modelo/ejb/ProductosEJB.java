package aplicacion.modelo.ejb;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import aplicacion.modelo.LogSingleton;
import aplicacion.modelo.dao.ProductoDAO;
import aplicacion.modelo.pojo.Producto;
import aplicacion.modelo.pojo.ProductoSinPrecio;
import aplicacion.modelo.pojo.Usuario;

@Stateless
@LocalBean
public class ProductosEJB {

	public ArrayList<Producto> productosPorUserId(Usuario usuario) {
		return ProductoDAO.getProductosPorUserId(usuario.getId());
	}

	public Producto obtenerProductoPorIdContenido(String idContenido) {
		return ProductoDAO.getProductoPorIdContenido(idContenido);
	}

	public Integer existeProducto(String enlace) {
		return ProductoDAO.existeProducto(enlace);
	}

	public void insertProducto(String nombre, String enlace, String imgLink) {
		ProductoDAO.insertProducto(nombre, enlace, imgLink);
	}

	public ArrayList<ProductoSinPrecio> productosSinPrecioPorUserId(Usuario usuario) {
		return ProductoDAO.productosSinPrecioPorUserId(usuario.getId());
	}

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

	public ProductoSinPrecio getProductoAEliminar(String idContenido) {
		LogSingleton log = LogSingleton.getInstance();
		int contenidoId = -1;
		try {
			contenidoId = Integer.parseInt(idContenido);
		} catch (Exception e) {
			log.getLoggerProductosEJB().error("Se ha producido un error en ProductosEJB: ", e);
			return null;
		}
		return ProductoDAO.getProductoAEliminar(contenidoId);
	}

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

}
