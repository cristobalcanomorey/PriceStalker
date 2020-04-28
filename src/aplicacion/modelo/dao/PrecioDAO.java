package aplicacion.modelo.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import aplicacion.modelo.dao.mappers.PreciosMapper;
import aplicacion.modelo.pojo.Precio;

public class PrecioDAO {

	public static ArrayList<Precio> obtenerPreciosDeProductoPorIdContenido(int contenidoId) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			PreciosMapper preciosMapper = sqlSession.getMapper(PreciosMapper.class);
			return preciosMapper.obtenerPreciosDeProductoPorIdContenido(contenidoId);
		} finally {
			sqlSession.close();
		}

	}

}
