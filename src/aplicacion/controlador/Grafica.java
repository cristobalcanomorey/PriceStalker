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
import aplicacion.modelo.ejb.ProductosEJB;
import aplicacion.modelo.ejb.SesionesEJB;
import aplicacion.modelo.pojo.Usuario;

@WebServlet("/Grafica")
public class Grafica extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	SesionesEJB sesionesEJB;

	@EJB
	ProductosEJB productosEJB;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		LogSingleton log = LogSingleton.getInstance();
		Usuario usuario = sesionesEJB.usuarioLogeado(session);
		String idContenido = request.getParameter("id");
		if (usuario != null && idContenido != null) {
			int contenidoId = -1;
			try {
				contenidoId = Integer.parseInt(idContenido);
			} catch (Exception e) {
				log.getLoggerGrafica().error("Se ha producido un error en GET Grafica: ", e);
			}
			if (contenidoId > 0) {
				int idProducto = productosEJB.obtenerPreciosDeProductoPorIdContenido(idContenido);
			} else {
				response.sendRedirect("Principal");
			}
		} else {
			response.sendRedirect("Principal");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
