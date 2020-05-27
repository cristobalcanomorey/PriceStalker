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
	/****
	 * Método GET que obtiene el usuario de la sesión y si está logueado muestra la
	 * página de administrar usuario. Si no, redirige a Principal.
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
	/****
	 * Método POST que obtiene el usuario de la sesión, el nombre, correo,
	 * contraseña actual, la nueva contraseña y la nueva contraseña repetida de los
	 * parametros para modificar los datos del usuario logueado con estos.
	 * 
	 * Si el usuario ha sido modificado cierra la sesión con el usuario antiguo y
	 * redirige a Login.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		LogSingleton log = LogSingleton.getInstance();
		/**
		 * Obtiene el usuario de la sesión.
		 */
		Usuario usuarioLogueado = sesionesEJB.usuarioLogeado(session);
		String error = null;
		/**
		 * Si el usuario está logueado modifica el usuario y redirige a Login o muestra
		 * un mensaje de error. Si no, redirige a Principal.
		 */
		if (usuarioLogueado != null) {
			/**
			 * Obtiene los datos necesarios para modificar el usuario.
			 */
			String nombre = request.getParameter("nombre");
			String correo = request.getParameter("correo");
			String password = request.getParameter("password");
			String password2 = request.getParameter("password2");
			String confirmaPassword2 = request.getParameter("confirmaPassword2");
			/**
			 * Si obtiene todos los datos intenta modificar el usuario, si no muestra un
			 * error.
			 */
			if (nombre != null & correo != null & password != null & password2 != null & confirmaPassword2 != null) {
				/*
				 * Obtenemos el usuario que tenga el correo y password original, si no lo
				 * encuentra muestra un mensaje de error.
				 */
				Usuario usuarioAModificar = usuariosEJB.loginUsuario(usuarioLogueado.getCorreo(), password);
				boolean correoRepetido = false;
				/**
				 * Si el correo es diferente comprueba si el nuevo correo ya existe en la BBDD.
				 */
				if (!usuarioLogueado.getCorreo().equals(correo)) {
					correoRepetido = usuariosEJB.existeUsuario(correo);
				}
				/**
				 * Si el correo no está en la BBDD muestra un error. Si no, intenta modificar el
				 * usuario.
				 */
				if (!correoRepetido) {
					/**
					 * Si el usuario obtenido a partir de los datos de sesión está vacío muestra un
					 * mensaje de error. Si no, intenta modificar el usuario.
					 */
					if (usuarioAModificar != null) {
						/**
						 * Si las dos nuevas contraseñas no están en blanco intenta cambiar el usuario.
						 * Si ninguna de las contraseñas están en blanco cambia el nombre y correo del
						 * usuario, termina la sesión y redirige a Login. Si alguna de las nuevas
						 * contraseñas no está vacía pero la otra sí muestra un mensaje de error.
						 */
						if (!password2.equals("") && !confirmaPassword2.equals("")) {
							/**
							 * Si las nuevas contraseñas coinciden edita el usuario, cierra la sesión y
							 * redirige a Login.
							 */
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
			/**
			 * Si ha ocurrido un error muestra la página AdminUser con el error
			 */
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
