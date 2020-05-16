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
import aplicacion.modelo.ejb.ListasEJB;
import aplicacion.modelo.ejb.ProductosEJB;
import aplicacion.modelo.ejb.SesionesEJB;
import aplicacion.modelo.pojo.Usuario;

@WebServlet("/AddProducto")
public class AddProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	SesionesEJB sesionesEJB;

	@EJB
	ListasEJB listasEJB;

	@EJB
	ProductosEJB productosEJB;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		LogSingleton log = LogSingleton.getInstance();
		Usuario usuario = sesionesEJB.usuarioLogeado(session);
		if (usuario != null) {
			RequestDispatcher rs = getServletContext().getRequestDispatcher("/PaginaAddProducto.jsp");
			request.setAttribute("nombreLista", listasEJB.getNombreLista(usuario.getId()));
			try {
				rs.forward(request, response);
			} catch (ServletException | IOException e) {
				log.getLoggerAddProducto().error("Se ha producido un error en GET AddProducto: ", e);
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
		Usuario usuario = sesionesEJB.usuarioLogeado(session);
		String enlace = request.getParameter("enlace");
		String nombreLista = request.getParameter("nombreLista");
		String precioObjetivo = request.getParameter("precioObjetivo");
		if (usuario != null & enlace != null & precioObjetivo != null) {
			if (nombreLista != null) {
				listasEJB.insertLista(nombreLista, usuario.getId());
			}
			Integer idProducto = productosEJB.existeProducto(enlace);
			if (idProducto == null) {
				productosEJB.insertProducto("Producto sin nombre", enlace, "imgs/productoSinImg.png");
				idProducto = productosEJB.existeProducto(enlace);
			}
			Integer idLista = listasEJB.getIdLista(usuario.getId());
			try {
				listasEJB.addContenido(precioObjetivo, idLista, idProducto);
				response.sendRedirect("Lista");
			} catch (NumberFormatException e) {
				log.getLoggerAddProducto().error("Se ha producido un error en POST AddProducto: ", e);
				response.sendRedirect("Principal");
			}

		} else {
			response.sendRedirect("Principal");
		}
	}

}
