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

}
