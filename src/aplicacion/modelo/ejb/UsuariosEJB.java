package aplicacion.modelo.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import aplicacion.modelo.dao.UsuarioDAO;
import aplicacion.modelo.pojo.Usuario;

@Stateless
@LocalBean
public class UsuariosEJB {

	public Usuario loginUsuario(String correo, String password) {
		return UsuarioDAO.loginUsuario(correo, password);
	}

	public boolean existeUsuario(String correo) {
		return UsuarioDAO.existeUsuario(correo);
	}

	public Usuario registrarUsuario(String nombre, String correo, String password) {
		UsuarioDAO.registrarUsuario(nombre, correo, password);
		return loginUsuario(correo, password);
	}

	public void editarUsuario(String correoOriginal, String nombreNuevo, String correoNuevo, String passwordNuevo) {
		if (passwordNuevo != null) {
			UsuarioDAO.editarUsuario(correoOriginal, nombreNuevo, correoNuevo, passwordNuevo);
		} else {
			UsuarioDAO.editarNombreYCorreoDeUsuario(correoOriginal, nombreNuevo, correoNuevo);
		}

	}

	public void eliminarUsuario(Usuario usuario) {
		UsuarioDAO.eliminarUsuario(usuario.getId());
	}

	public Usuario getUsuarioPorIdLista(Integer idLista) {
		return UsuarioDAO.getUsuarioPorIdLista(idLista);
	}

}
