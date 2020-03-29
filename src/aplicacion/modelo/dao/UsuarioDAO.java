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
}
