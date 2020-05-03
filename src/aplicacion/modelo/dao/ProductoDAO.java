package aplicacion.modelo.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import aplicacion.modelo.dao.mappers.ProductosMapper;
import aplicacion.modelo.pojo.CaracteristicasDeProducto;
import aplicacion.modelo.pojo.Producto;
import aplicacion.modelo.pojo.ProductoSinPrecio;

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

	public static ArrayList<ProductoSinPrecio> productosSinPrecioPorUserId(Integer idUsuario) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			ProductosMapper productosMapper = sqlSession.getMapper(ProductosMapper.class);
			return productosMapper.productosSinPrecioPorUserId(idUsuario);
		} finally {
			sqlSession.close();
		}
	}

	public static String getNombrePorIdContenido(int contenidoId) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			ProductosMapper productosMapper = sqlSession.getMapper(ProductosMapper.class);
			return productosMapper.getNombrePorIdContenido(contenidoId);
		} finally {
			sqlSession.close();
		}
	}

	public static ProductoSinPrecio getProductoAEliminar(int contenidoId) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			ProductosMapper productosMapper = sqlSession.getMapper(ProductosMapper.class);
			return productosMapper.getProductoAEliminar(contenidoId);
		} finally {
			sqlSession.close();
		}
	}

	public static void eliminarProductoDeLaLista(int contenidoId, Integer idUsuario) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			if (contenidoId > 0) {
				ProductosMapper usuariosMapper = sqlSession.getMapper(ProductosMapper.class);
				usuariosMapper.eliminarProductoDeLaLista(contenidoId, idUsuario);
				sqlSession.commit();
			}
		} finally {
			sqlSession.close();
		}
	}

	public static int getNumeroDeProductosEnAlgunaLista() {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			ProductosMapper productosMapper = sqlSession.getMapper(ProductosMapper.class);
			return productosMapper.getNumeroDeProductosEnAlgunaLista();
		} finally {
			sqlSession.close();
		}
	}

	public static CaracteristicasDeProducto getCaracteristicasDelProductoNumero(int i) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			ProductosMapper productosMapper = sqlSession.getMapper(ProductosMapper.class);
			return productosMapper.getCaracteristicasDelProductoNumero(i);
		} finally {
			sqlSession.close();
		}
	}

	public static void updateCaracteristicasDeProducto(CaracteristicasDeProducto producto) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			ProductosMapper usuariosMapper = sqlSession.getMapper(ProductosMapper.class);
			usuariosMapper.updateCaracteristicasDeProducto(producto.getId(), producto.getNombre(),
					producto.getImgLink());
			sqlSession.commit();

		} finally {
			sqlSession.close();
		}
	}

}
