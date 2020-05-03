package aplicacion.modelo.dao;

import org.apache.ibatis.session.SqlSession;

import aplicacion.modelo.dao.mappers.UsuariosMapper;
import aplicacion.modelo.pojo.Usuario;

public class UsuarioDAO {

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

	public static Usuario getUsuarioPorIdLista(Integer idLista) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuariosMapper usuariosMapper = sqlSession.getMapper(UsuariosMapper.class);
			return usuariosMapper.getUsuarioPorIdLista(idLista);
		} finally {
			sqlSession.close();
		}
	}
}
