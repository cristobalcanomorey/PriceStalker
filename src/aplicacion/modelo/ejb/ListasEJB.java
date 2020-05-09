package aplicacion.modelo.ejb;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import aplicacion.modelo.dao.ListaDAO;
import aplicacion.modelo.pojo.Contenido;

@Stateless
@LocalBean
public class ListasEJB {

	public String getNombreLista(Integer id) {
		return ListaDAO.getNombreLista(id);
	}

	public void insertLista(String nombreLista, Integer id) {
		ListaDAO.insertLista(nombreLista, id);
	}

	public Integer getIdLista(Integer idUsuario) {
		return ListaDAO.getIdLista(idUsuario);
	}

	public void addContenido(String precioObjetivo, Integer idLista, Integer idProducto) throws NumberFormatException {
		Double costeObjetivo = Double.parseDouble(precioObjetivo);
		ListaDAO.addContenido(costeObjetivo, idLista, idProducto);
	}

	public ArrayList<Contenido> getContenidosPorIdProducto(Integer id) {
		return ListaDAO.getContenidosPorIdProducto(id);
	}

	public boolean productoEstaEnSuLista(String idContenido, Integer idUsuario) {
		Integer id = ListaDAO.getIdContenidoPorIdContenidoEIdUsuario(idContenido, idUsuario);
		return id != null;
	}
}
