package aplicacion.modelo.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import aplicacion.modelo.dao.UsuarioDAO;
import aplicacion.modelo.pojo.Usuario;

@Stateless
@LocalBean
public class UsuariosEJB {

	/****
	 * Obtiene un usuario a partir de su correo y su contraseña.
	 * 
	 * @param correo   Correo del usuario.
	 * @param password Contraseña del usuario.
	 * @return Usuario con ese correo y contraseña.
	 */
	public Usuario loginUsuario(String correo, String password) {
		return UsuarioDAO.loginUsuario(correo, password);
	}

	/****
	 * Obtiene un usuario a partir de un correo y si hay uno devuelve True, si no
	 * False.
	 * 
	 * @param correo Correo del usuario.
	 * @return Boolean.
	 */
	public boolean existeUsuario(String correo) {
		return UsuarioDAO.existeUsuario(correo);
	}

	/****
	 * Introduce un usuario en la BBDD y lo recoge de la BBDD.
	 * 
	 * @param nombre   Nombre del usuario.
	 * @param correo   Correo del usuario.
	 * @param password Contraseña del usuario.
	 * @return Usuario registrado.
	 */
	public Usuario registrarUsuario(String nombre, String correo, String password) {
		UsuarioDAO.registrarUsuario(nombre, correo, password);
		return loginUsuario(correo, password);
	}

	/****
	 * Modifica los datos de un usuario a partir de su correo original. Si
	 * passwordNuevo es null cambia solo el nombre y correo.
	 * 
	 * @param correoOriginal Correo original del usuario.
	 * @param nombreNuevo    Nuevo nombre del usuario.
	 * @param correoNuevo    Nuevo correo del usuario.
	 * @param passwordNuevo  Nueva contraseña del usuario.
	 */
	public void editarUsuario(String correoOriginal, String nombreNuevo, String correoNuevo, String passwordNuevo) {
		if (passwordNuevo != null) {
			UsuarioDAO.editarUsuario(correoOriginal, nombreNuevo, correoNuevo, passwordNuevo);
		} else {
			UsuarioDAO.editarNombreYCorreoDeUsuario(correoOriginal, nombreNuevo, correoNuevo);
		}

	}

	/****
	 * Elimina al usuario de la BBDD.
	 * 
	 * @param usuario Usuario.
	 */
	public void eliminarUsuario(Usuario usuario) {
		UsuarioDAO.eliminarUsuario(usuario.getId());
	}

	/****
	 * Obtiene un usuario a partir del ID lista.
	 * 
	 * @param idLista ID de su lista.
	 * @return Usuario de esa lista.
	 */
	public Usuario getUsuarioPorIdLista(Integer idLista) {
		return UsuarioDAO.getUsuarioPorIdLista(idLista);
	}

}
