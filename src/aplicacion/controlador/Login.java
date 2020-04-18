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

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

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

		if (usuario != null) {
			try {
				response.sendRedirect("Principal");
			} catch (IOException e) {
				log.getLoggerLogin().error("Se ha producido un error en GET Login: " + e);
			}
		} else {
			RequestDispatcher rs = getServletContext().getRequestDispatcher("/PaginaLogin.jsp");

			request.setAttribute("error", request.getParameter("error"));
			try {
				rs.forward(request, response);
			} catch (ServletException | IOException e) {
				log.getLoggerLogin().error("Se ha producido un error en GET Login: " + e);
			}
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LogSingleton log = LogSingleton.getInstance();
		String correo = request.getParameter("correo");
		/*
		 * Importante no modificar el nombre del parametro "password2" para que
		 * AdminUser funcione correctamente.
		 */
		String password = request.getParameter("password2");
		Usuario usuario = usuariosEJB.loginUsuario(correo, password);
		if (usuario == null) {
			try {
				response.sendRedirect("Login?error=1");
			} catch (IOException e) {
				log.getLoggerLogin().error("Se ha producido un error en POST Login: ", e);
			}
		} else {
			HttpSession session = request.getSession(true);
			sesionesEJB.loginUsuario(session, usuario);

			try {
				response.sendRedirect("Principal");
			} catch (IOException e) {
				log.getLoggerLogin().error("Se ha producido un error en POST Login: ", e);
			}
		}
	}

}
