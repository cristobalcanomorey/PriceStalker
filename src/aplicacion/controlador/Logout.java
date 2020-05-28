package aplicacion.controlador;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import aplicacion.modelo.LogSingleton;
import aplicacion.modelo.ejb.SesionesEJB;

@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	SesionesEJB sesionesEJB;

	@Override
	/****
	 * Método GET que termina la sesión de un usuario.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		LogSingleton log = LogSingleton.getInstance();
		HttpSession session = request.getSession(false);
		sesionesEJB.logoutUsuario(session);
		try {
			response.sendRedirect("Principal");
		} catch (IOException e) {
			log.getLoggerLogout().error("Se ha producido un error en GET Logout: ", e);
		}
	}

}
