package aplicacion.modelo.dao;

import org.apache.ibatis.session.SqlSession;

import aplicacion.modelo.dao.mappers.UsuariosMapper;
import aplicacion.modelo.pojo.Usuario;

public class UsuarioDAO {

	/****
	 * Obtiene un usuario a partir de su correo y su contraseña.
	 * 
	 * @param correo   Correo del usuario.
	 * @param password Contraseña del usuario.
	 * @return Usuario con ese correo y contraseña.
	 */
	public static Usuario loginUsuario(String correo, String password) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		Usuario usuario = null;
		try {
			UsuariosMapper usuariosMapper = sqlSession.getMapper(UsuariosMapper.class);
			if (correo != null && password != null) {
				usuario = usuariosMapper.loginUsuario(correo, password);
			}
			return usuario;
		} finally {
			sqlSession.close();
		}
	}

	/****
	 * Obtiene un usuario a partir de un correo y si hay uno devuelve True, si no
	 * False.
	 * 
	 * @param correo Correo del usuario.
	 * @return Boolean.
	 */
	public static boolean existeUsuario(String correo) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		Usuario usuario = null;
		try {
			if (correo != null) {
				UsuariosMapper usuariosMapper = sqlSession.getMapper(UsuariosMapper.class);
				usuario = usuariosMapper.existeUsuario(correo);
			}
		} finally {
			sqlSession.close();
		}
		return usuario != null;
	}

	/****
	 * Introduce un usuario en la BBDD.
	 * 
	 * @param nombre   Nombre del usuario.
	 * @param correo   Correo del usuario.
	 * @param password Contraseña del usuario.
	 */
	public static void registrarUsuario(String nombre, String correo, String password) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			if (nombre != null & correo != null & password != null) {
				UsuariosMapper usuariosMapper = sqlSession.getMapper(UsuariosMapper.class);
				usuariosMapper.registrarUsuario(nombre, correo, password);
				sqlSession.commit();
			}
		} finally {
			sqlSession.close();
		}
	}

	/****
	 * Modifica los datos de un usuario a partir de su correo original.
	 * 
	 * @param correoOriginal Correo original del usuario.
	 * @param nombreNuevo    Nuevo nombre del usuario.
	 * @param correoNuevo    Nuevo correo del usuario.
	 * @param passwordNuevo  Nueva contraseña del usuario.
	 */
	public static void editarUsuario(String correoOriginal, String nombreNuevo, String correoNuevo,
			String passwordNuevo) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			if (correoOriginal != null & nombreNuevo != null & correoNuevo != null & passwordNuevo != null) {
				UsuariosMapper usuariosMapper = sqlSession.getMapper(UsuariosMapper.class);
				usuariosMapper.editarUsuario(correoOriginal, nombreNuevo, correoNuevo, passwordNuevo);
				sqlSession.commit();
			}
		} finally {
			sqlSession.close();
		}

	}

	/****
	 * Elimina un usuario de la BBDD a partir de su ID.
	 * 
	 * @param id ID de usuario.
	 */
	public static void eliminarUsuario(Integer id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			if (id != null) {
				UsuariosMapper usuariosMapper = sqlSession.getMapper(UsuariosMapper.class);
				usuariosMapper.eliminarUsuario(id);
				sqlSession.commit();
			}
		} finally {
			sqlSession.close();
		}
	}

	/****
	 * Obtiene un usuario a partir del ID lista.
	 * 
	 * @param idLista ID de su lista.
	 * @return Usuario de esa lista.
	 */
	public static Usuario getUsuarioPorIdLista(Integer idLista) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuariosMapper usuariosMapper = sqlSession.getMapper(UsuariosMapper.class);
			return usuariosMapper.getUsuarioPorIdLista(idLista);
		} finally {
			sqlSession.close();
		}
	}

	/****
	 * Modifica el nombre y el correo de un usuario a partir de su correo original.
	 * 
	 * @param correoOriginal Correo original del usuario.
	 * @param nombreNuevo    Nuevo nombre del usuario.
	 * @param correoNuevo    Nuevo correo del usuario.
	 */
	public static void editarNombreYCorreoDeUsuario(String correoOriginal, String nombreNuevo, String correoNuevo) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			if (correoOriginal != null & nombreNuevo != null & correoNuevo != null) {
				UsuariosMapper usuariosMapper = sqlSession.getMapper(UsuariosMapper.class);
				usuariosMapper.editarNombreYCorreoDeUsuario(correoOriginal, nombreNuevo, correoNuevo);
				sqlSession.commit();
			}
		} finally {
			sqlSession.close();
		}
	}
}
