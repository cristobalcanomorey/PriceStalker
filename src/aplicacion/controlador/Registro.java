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

@WebServlet("/Registro")
public class Registro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final String FALTAN_DATOS = "Debes rellenar todos los campos obligatorios.";
	private final String PASSWORDS_NO_COINCIDEN = "Las contraseñas no coinciden.";
	private final String USUARIO_YA_EXISTE = "Ya tenemos un usuario con ese correo electrónico, prueba a usar otro.";

	@EJB
	SesionesEJB sesionesEJB;

	@EJB
	UsuariosEJB usuariosEJB;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		LogSingleton log = LogSingleton.getInstance();
		Usuario usuario = sesionesEJB.usuarioLogeado(session);
		if (usuario == null) {
			RequestDispatcher rs = getServletContext().getRequestDispatcher("/PaginaRegistro.jsp");
			try {
				rs.forward(request, response);
			} catch (ServletException | IOException e) {
				log.getLoggerRegistro().error("Se ha producido un error en GET Registro: ", e);
			}
		} else {
			try {
				response.sendRedirect("Principal");
			} catch (IOException e) {
				log.getLoggerRegistro().error("Se ha producido un error en GET Registro: ", e);
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		LogSingleton log = LogSingleton.getInstance();
		Usuario usuario = sesionesEJB.usuarioLogeado(session);
		String error = null;
		if (usuario == null) {
			String nombre = request.getParameter("nombre");
			String correo = request.getParameter("correo");
			String password = request.getParameter("password");
			String confirmaPassword = request.getParameter("confirmaPassword");
			if (nombre != null & correo != null & password != null & confirmaPassword != null) {
				if (password.equals(confirmaPassword)) {
					if (!usuariosEJB.existeUsuario(correo)) {
						usuario = usuariosEJB.registrarUsuario(nombre, correo, password);
						sesionesEJB.loginUsuario(session, usuario);
						try {
							response.sendRedirect("Principal");
						} catch (IOException e) {
							log.getLoggerRegistro().error("Se ha producido un error en GET Registro: ", e);
						}
					} else {
						error = USUARIO_YA_EXISTE;
					}
				} else {
					error = PASSWORDS_NO_COINCIDEN;
				}
			} else {
				error = FALTAN_DATOS;
			}
		}
		if (error != null) {
			RequestDispatcher rs = getServletContext().getRequestDispatcher("/PaginaRegistro.jsp");

			request.setAttribute("error", error);

			try {
				rs.forward(request, response);
			} catch (ServletException | IOException e) {
				log.getLoggerRegistro().error("Se ha producido un error en GET Registro: ", e);
			}
		}
	}

}
