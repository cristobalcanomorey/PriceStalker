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

@WebServlet("/DelUser")
public class DelUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final String FALTAN_DATOS = "Debes rellenar todos los campos obligatorios.";
	private final String PASSWORD_INCORRECTO = "La contraseña no es correcta.";

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
			RequestDispatcher rs = getServletContext().getRequestDispatcher("/PaginaDelUser.jsp");
			try {
				rs.forward(request, response);
			} catch (ServletException | IOException e) {
				log.getLoggerDelUser().error("Se ha producido un error en GET DelUser: ", e);
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
			String password = request.getParameter("password");
			if (password != null) {
				Usuario usuario = usuariosEJB.loginUsuario(usuarioLogueado.getCorreo(), password);
				if (usuario != null) {
					usuariosEJB.eliminarUsuario(usuario);
					sesionesEJB.logoutUsuario(session);
					response.sendRedirect("Principal");
				} else {
					error = PASSWORD_INCORRECTO;
				}
			} else {
				error = FALTAN_DATOS;
			}
			if (error != null) {
				RequestDispatcher rs = getServletContext().getRequestDispatcher("/PaginaDelUser.jsp");
				request.setAttribute("error", error);
				try {
					rs.forward(request, response);
				} catch (ServletException | IOException e) {
					log.getLoggerDelUser().error("Se ha producido un error en POST DelUser: ", e);
				}
			}
		} else {
			response.sendRedirect("Principal");
		}
	}

}
