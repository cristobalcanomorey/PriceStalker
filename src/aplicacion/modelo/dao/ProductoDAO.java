package aplicacion.modelo.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import aplicacion.modelo.dao.mappers.ProductosMapper;
import aplicacion.modelo.pojo.Producto;

public class ProductoDAO {

	public static ArrayList<Producto> getProductosPorUserId(Integer id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			ProductosMapper productosMapper = sqlSession.getMapper(ProductosMapper.class);
			return productosMapper.getProductosPorUserId(id);
		} finally {
			sqlSession.close();
		}
	}

	public static Producto getProductoPorIdContenido(String idContenido) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			ProductosMapper productosMapper = sqlSession.getMapper(ProductosMapper.class);
			return productosMapper.getProductoPorIdContenido(idContenido);
		} finally {
			sqlSession.close();
		}
	}

	public static Integer existeProducto(String enlace) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			ProductosMapper productosMapper = sqlSession.getMapper(ProductosMapper.class);
			return productosMapper.existeProducto(enlace);
		} finally {
			sqlSession.close();
		}
	}

	public static void insertProducto(String nombre, String enlace, String imgLink) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			if (nombre != null & enlace != null & imgLink != null) {
				ProductosMapper usuariosMapper = sqlSession.getMapper(ProductosMapper.class);
				usuariosMapper.insertProducto(nombre, enlace, imgLink);
				sqlSession.commit();
			}
		} finally {
			sqlSession.close();
		}
	}

}
