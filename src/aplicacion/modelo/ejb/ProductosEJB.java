package aplicacion.modelo.ejb;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import aplicacion.modelo.dao.ProductoDAO;
import aplicacion.modelo.pojo.Producto;
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

}
