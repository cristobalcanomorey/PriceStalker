package aplicacion.controlador;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import aplicacion.modelo.LogSingleton;
import aplicacion.modelo.ejb.SesionesEJB;
import aplicacion.modelo.ejb.UsuariosEJB;
import aplicacion.modelo.pojo.Usuario;

@WebServlet("/AdminUser")
public class AdminUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final String FALTAN_DATOS = "Debes rellenar todos los campos obligatorios";
	private final String NO_REPITE_PASSWORD = "Debes introducir la nueva contraseña dos veces";
	private final String PASSWORD_INCORRECTO = "La contraseña original no es correcta";
	private final String PASSWORDS_NUEVOS_NO_COINCIDEN = "Las contraseñas nuevas no coinciden";
	private final String EXISTE_USUARIO = "Ya tenemos otro usuario registrado con ese correo";

	@EJB
	SesionesEJB sesionesEJB;

	@EJB
	UsuariosEJB usuariosEJB;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		LogSingleton log = LogSingleton.getInstance();
		Usuario usuario = sesionesEJB.usuarioLogeado(session);
		if (usuario != null) {
			RequestDispatcher rs = getServletContext().getRequestDispatcher("/PaginaAdminUser.jsp");
			request.setAttribute("usuario", usuario);
			try {
				rs.forward(request, response);
			} catch (ServletException | IOException e) {
				log.getLoggerAdminUser().error("Se ha producido un error en GET AdminUser: ", e);
			}
		} else {
			response.sendRedirect("Principal");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		LogSingleton log = LogSingleton.getInstance();
		Usuario usuarioLogueado = sesionesEJB.usuarioLogeado(session);
		String error = null;
		if (usuarioLogueado != null) {
			String nombre = request.getParameter("nombre");
			String correo = request.getParameter("correo");
			String password = request.getParameter("password");
			String password2 = request.getParameter("password2");
			String confirmaPassword2 = request.getParameter("confirmaPassword2");
			if (nombre != null & correo != null & password != null & password2 != null & confirmaPassword2 != null) {
				/*
				 * Obtenemos el usuario que tenga el correo y password original, si no lo
				 * encuentra muestra un mensaje de error.
				 */
				Usuario usuarioAModificar = usuariosEJB.loginUsuario(usuarioLogueado.getCorreo(), password);
				boolean correoRepetido = false;
				if (!usuarioLogueado.getCorreo().equals(correo)) {
					correoRepetido = usuariosEJB.existeUsuario(correo);
				}
				if (!correoRepetido) {
					if (usuarioAModificar != null) {
						if (!password2.equals("") && !confirmaPassword2.equals("")) {
							if (password2.equals(confirmaPassword2)) {
								usuariosEJB.editarUsuario(usuarioAModificar.getCorreo(), nombre, correo, password2);
								sesionesEJB.logoutUsuario(session);
								RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login");
								rd.forward(request, response);
							} else {
								error = PASSWORDS_NUEVOS_NO_COINCIDEN;
							}
						} else if (password2.equals("") && confirmaPassword2.equals("")) {
							usuariosEJB.editarUsuario(usuarioAModificar.getCorreo(), nombre, correo, null);
							sesionesEJB.logoutUsuario(session);
							RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login");
							rd.forward(request, response);
						} else {
							error = NO_REPITE_PASSWORD;
						}
					} else {
						error = PASSWORD_INCORRECTO;
					}
				} else {
					error = EXISTE_USUARIO;
				}
			} else {
				error = FALTAN_DATOS;
			}
			if (error != null) {
				RequestDispatcher rs = getServletContext().getRequestDispatcher("/PaginaAdminUser.jsp");

				request.setAttribute("usuario", usuarioLogueado);
				request.setAttribute("error", error);

				try {
					rs.forward(request, response);
				} catch (ServletException | IOException e) {
					log.getLoggerAdminUser().error("Se ha producido un error en POST AdminUser: ", e);
				}
			}
		} else {
			response.sendRedirect("Principal");
		}

	}

}
