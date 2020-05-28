package aplicacion.modelo.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import aplicacion.modelo.dao.mappers.PreciosMapper;
import aplicacion.modelo.pojo.Precio;

public class PrecioDAO {

	/****
	 * Obtiene los precios de un producto por el ID del contenido.
	 * 
	 * @param contenidoId ID del contenido.
	 * @return Precios del producto.
	 */
	public static ArrayList<Precio> obtenerPreciosDeProductoPorIdContenido(int contenidoId) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			PreciosMapper preciosMapper = sqlSession.getMapper(PreciosMapper.class);
			return preciosMapper.obtenerPreciosDeProductoPorIdContenido(contenidoId);
		} finally {
			sqlSession.close();
		}

	}

	/****
	 * Añade un precio a un producto.
	 * 
	 * @param id     ID del producto.
	 * @param precio Precio del producto.
	 */
	public static void insertPrecio(Integer id, Double precio) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			PreciosMapper preciosMapper = sqlSession.getMapper(PreciosMapper.class);
			preciosMapper.insertPrecio(id, precio);
			sqlSession.commit();

		} finally {
			sqlSession.close();
		}
	}

}
