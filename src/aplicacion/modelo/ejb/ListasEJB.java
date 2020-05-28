package aplicacion.modelo.ejb;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import aplicacion.modelo.dao.ListaDAO;
import aplicacion.modelo.pojo.Contenido;

@Stateless
@LocalBean
public class ListasEJB {

	/****
	 * Obtiene el nombre de una lista.
	 * 
	 * @param id ID del usuario.
	 * @return String del nombre de su lista.
	 */
	public String getNombreLista(Integer id) {
		return ListaDAO.getNombreLista(id);
	}

	/****
	 * Añade una lista a la BBDD.
	 * 
	 * @param nombreLista Nombre de la lista.
	 * @param id          ID de su usuario.
	 */
	public void insertLista(String nombreLista, Integer id) {
		ListaDAO.insertLista(nombreLista, id);
	}

	/****
	 * Obtiene el ID de la lista de un usuario.
	 * 
	 * @param idUsuario ID de un usuario.
	 * @return ID de su lista.
	 */
	public Integer getIdLista(Integer idUsuario) {
		return ListaDAO.getIdLista(idUsuario);
	}

	/****
	 * Añade contenido a una lista.
	 * 
	 * @param precioObjetivo Coste objetivo.
	 * @param idLista        ID de la lista.
	 * @param idProducto     ID del producto.
	 * @throws NumberFormatException Si precioObjetivo no es un número lanza una
	 *                               excepción.
	 */
	public void addContenido(String precioObjetivo, Integer idLista, Integer idProducto) throws NumberFormatException {
		Double costeObjetivo = Double.parseDouble(precioObjetivo);
		ListaDAO.addContenido(costeObjetivo, idLista, idProducto);
	}

	/****
	 * Obtiene el contenido de las listas que tengan ese producto.
	 * 
	 * @param id ID del producto.
	 * @return Contenido de las listas que tengan ese producto.
	 */
	public ArrayList<Contenido> getContenidosPorIdProducto(Integer id) {
		return ListaDAO.getContenidosPorIdProducto(id);
	}

	/****
	 * Comprueba si el ID contenido pertenece a uno en la lista del usuario. Si
	 * devuelve False es que no es de su lista.
	 * 
	 * @param idContenido ID del contenido.
	 * @param idUsuario   ID del usuario.
	 * @return Boolean.
	 */
	public boolean productoEstaEnSuLista(String idContenido, Integer idUsuario) {
		Integer id = ListaDAO.getIdContenidoPorIdContenidoEIdUsuario(idContenido, idUsuario);
		return id != null;
	}
}
