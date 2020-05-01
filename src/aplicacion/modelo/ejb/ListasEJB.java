package aplicacion.modelo.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import aplicacion.modelo.dao.ListaDAO;

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
}
