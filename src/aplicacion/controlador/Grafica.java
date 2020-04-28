package aplicacion.controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import aplicacion.modelo.LogSingleton;
import aplicacion.modelo.ejb.GraficasEJB;
import aplicacion.modelo.ejb.ProductosEJB;
import aplicacion.modelo.ejb.SesionesEJB;
import aplicacion.modelo.pojo.Precio;
import aplicacion.modelo.pojo.Producto;
import aplicacion.modelo.pojo.Usuario;

@WebServlet("/Grafica")
public class Grafica extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	SesionesEJB sesionesEJB;

	@EJB
	GraficasEJB graficasEJB;

	@EJB
	ProductosEJB productosEJB;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		LogSingleton log = LogSingleton.getInstance();
		Usuario usuario = sesionesEJB.usuarioLogeado(session);
		String idContenido = request.getParameter("producto");
		if (usuario != null && idContenido != null) {
			ArrayList<Precio> precios = graficasEJB.obtenerPreciosDeProductoPorIdContenido(idContenido);
			Producto producto = productosEJB.obtenerProductoPorIdContenido(idContenido);
			if (precios != null & producto != null) {
				String labels = graficasEJB.fechasEnPreciosAJson(precios);
				String data = graficasEJB.costesEnPreciosAJson(precios);
				RequestDispatcher rs = getServletContext().getRequestDispatcher("/PaginaGrafica.jsp");
				request.setAttribute("usuario", usuario);
				request.setAttribute("labels", labels);
				request.setAttribute("data", data);
				request.setAttribute("producto", producto);
				try {
					rs.forward(request, response);
				} catch (ServletException | IOException e) {
					log.getLoggerGrafica().error("Se ha producido un error en GET Grafica: ", e);
				}
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
