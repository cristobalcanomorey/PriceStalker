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

}
