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
	/****
	 * Método GET que obtiene el usuario de la sesión y si está logueado muestra la
	 * página de añadir producto. Si no, redirige a Principal.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		LogSingleton log = LogSingleton.getInstance();
		Usuario usuario = sesionesEJB.usuarioLogeado(session);
		/**
		 * Si no está logueado redirige a Principal.
		 */
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

	/****
	 * Método POST que obtiene el usuario de la sesión, enlace del producto, el
	 * nombre de la lista y el precio objetivo de los parametros. Si el usuario está
	 * logueado y los parametros del producto no están vacíos añade un producto a la
	 * lista. También, si el parametro del nombre de la lista no está vacío crea una
	 * lista para el usuario.
	 * 
	 * Si consigue añadir un producto o crear una lista con un producto redirige a
	 * la Lista, si algo falla redirige a Principal.
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		LogSingleton log = LogSingleton.getInstance();
		/**
		 * Obtiene los datos necesarios para añadir un producto o crear una lista.
		 */
		Usuario usuario = sesionesEJB.usuarioLogeado(session);
		String enlace = request.getParameter("enlace");
		String nombreLista = request.getParameter("nombreLista");
		String precioObjetivo = request.getParameter("precioObjetivo");
		/**
		 * Comprueba si ha conseguido los datos necesarios, si no redirige a Principal.
		 */
		if (usuario != null & enlace != null & precioObjetivo != null) {
			/**
			 * Si obtiene el nombre de lista crea una lista para el usuario.
			 */
			if (nombreLista != null) {
				listasEJB.insertLista(nombreLista, usuario.getId());
			}
			Integer idProducto = productosEJB.existeProducto(enlace); // Comprueba si el producto ya existe en la BBDD
			/**
			 * Si no existe lo añade en la tabla producto
			 */
			if (idProducto == null) {
				productosEJB.insertProducto("Producto sin nombre", enlace, "imgs/productoSinImg.png");
				idProducto = productosEJB.existeProducto(enlace);
			}
			Integer idLista = listasEJB.getIdLista(usuario.getId());
			try {
				listasEJB.addContenido(precioObjetivo, idLista, idProducto); // Añade el contenido a la lista
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
