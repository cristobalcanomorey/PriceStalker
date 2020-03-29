package aplicacion.modelo.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import aplicacion.modelo.dao.UsuarioDAO;
import aplicacion.modelo.pojo.Usuario;

@Stateless
@LocalBean
public class UsuariosEJB {

	public Usuario loginUsuario(String correo, String paswd) {
		return UsuarioDAO.loginUsuario(correo, paswd);
	}
}
