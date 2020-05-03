package aplicacion.modelo.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import aplicacion.modelo.dao.mappers.ListasMapper;
import aplicacion.modelo.pojo.Contenido;

public class ListaDAO {

	public static void insertLista(String nombreLista, Integer id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			if (nombreLista != null & id != null) {
				ListasMapper listasMapper = sqlSession.getMapper(ListasMapper.class);
				listasMapper.insertLista(nombreLista, id);
				sqlSession.commit();
			}
		} finally {
			sqlSession.close();
		}
	}

	public static Integer getIdLista(Integer idUsuario) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			if (idUsuario != null) {
				ListasMapper listasMapper = sqlSession.getMapper(ListasMapper.class);
				return listasMapper.getIdLista(idUsuario);
			}
		} finally {
			sqlSession.close();
		}
		return null;
	}

	public static void addContenido(Double costeObjetivo, Integer idLista, Integer idProducto) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			if (costeObjetivo != null & idLista != null & idProducto != null) {
				ListasMapper listasMapper = sqlSession.getMapper(ListasMapper.class);
				listasMapper.addContenido(costeObjetivo, idLista, idProducto);
				sqlSession.commit();
			}
		} finally {
			sqlSession.close();
		}
	}

	public static String getNombreLista(Integer id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			ListasMapper productosMapper = sqlSession.getMapper(ListasMapper.class);
			return productosMapper.getNombreLista(id);
		} finally {
			sqlSession.close();
		}
	}

	public static ArrayList<Contenido> getContenidosPorIdProducto(Integer id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			ListasMapper productosMapper = sqlSession.getMapper(ListasMapper.class);
			return productosMapper.getContenidosPorIdProducto(id);
		} finally {
			sqlSession.close();
		}
	}

}
