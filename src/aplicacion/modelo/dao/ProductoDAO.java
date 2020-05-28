package aplicacion.modelo.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import aplicacion.modelo.dao.mappers.ProductosMapper;
import aplicacion.modelo.pojo.CaracteristicasDeProducto;
import aplicacion.modelo.pojo.Producto;
import aplicacion.modelo.pojo.ProductoSinPrecio;

public class ProductoDAO {

	/****
	 * Obtiene los productos de un usuario.
	 * 
	 * @param id ID del usuario.
	 * @return Lista de productos.
	 */
	public static ArrayList<Producto> getProductosPorUserId(Integer id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			ProductosMapper productosMapper = sqlSession.getMapper(ProductosMapper.class);
			return productosMapper.getProductosPorUserId(id);
		} finally {
			sqlSession.close();
		}
	}

	/****
	 * Obtiene un producto y su coste actual por el ID contenido.
	 * 
	 * @param idContenido ID del contenido.
	 * @return Producto con el coste actual.
	 */
	public static Producto getProductoPorIdContenido(String idContenido) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			ProductosMapper productosMapper = sqlSession.getMapper(ProductosMapper.class);
			return productosMapper.getProductoPorIdContenido(idContenido);
		} finally {
			sqlSession.close();
		}
	}

	/****
	 * Obtiene el id de un producto si el enlace ya existe o null si no existe.
	 * 
	 * @param enlace Enlace del producto.
	 * @return ID del producto.
	 */
	public static Integer existeProducto(String enlace) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			ProductosMapper productosMapper = sqlSession.getMapper(ProductosMapper.class);
			return productosMapper.existeProducto(enlace);
		} finally {
			sqlSession.close();
		}
	}

	/****
	 * Añade un producto en la tabla BBDD.
	 * 
	 * @param nombre  Nombre del producto.
	 * @param enlace  Enlace de compra del producto.
	 * @param imgLink Enlace de la imágen del producto.
	 */
	public static void insertProducto(String nombre, String enlace, String imgLink) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			if (nombre != null & enlace != null & imgLink != null) {
				ProductosMapper productosMapper = sqlSession.getMapper(ProductosMapper.class);
				productosMapper.insertProducto(nombre, enlace, imgLink);
				sqlSession.commit();
			}
		} finally {
			sqlSession.close();
		}
	}

	/****
	 * Obtiene una lista de los productos sin precio de un usuario.
	 * 
	 * @param idUsuario ID del usuario.
	 * @return Lista de productos que todabía no tienen precios de una lista.
	 */
	public static ArrayList<ProductoSinPrecio> productosSinPrecioPorUserId(Integer idUsuario) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			ProductosMapper productosMapper = sqlSession.getMapper(ProductosMapper.class);
			return productosMapper.productosSinPrecioPorUserId(idUsuario);
		} finally {
			sqlSession.close();
		}
	}

	/****
	 * Obtiene el nombre de un producto a partir del ID del contenido.
	 * 
	 * @param contenidoId ID del contenido.
	 * @return Nombre del producto.
	 */
	public static String getNombrePorIdContenido(int contenidoId) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			ProductosMapper productosMapper = sqlSession.getMapper(ProductosMapper.class);
			return productosMapper.getNombrePorIdContenido(contenidoId);
		} finally {
			sqlSession.close();
		}
	}

	/****
	 * Obtiene un producto por el ID del usuario y el ID contenido.
	 * 
	 * @param contenidoId ID del contenido.
	 * @param idUsuario   ID del usuario.
	 * @return Producto.
	 */
	public static ProductoSinPrecio getProductoAEliminar(int contenidoId, Integer idUsuario) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			ProductosMapper productosMapper = sqlSession.getMapper(ProductosMapper.class);
			return productosMapper.getProductoAEliminar(contenidoId, idUsuario);
		} finally {
			sqlSession.close();
		}
	}

	/****
	 * Elimina un contenido de la lista por su ID y el ID del usuario.
	 * 
	 * @param contenidoId ID del contenido.
	 * @param idUsuario   ID del usuario.
	 */
	public static void eliminarProductoDeLaLista(int contenidoId, Integer idUsuario) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			if (contenidoId > 0) {
				ProductosMapper productosMapper = sqlSession.getMapper(ProductosMapper.class);
				productosMapper.eliminarProductoDeLaLista(contenidoId, idUsuario);
				sqlSession.commit();
			}
		} finally {
			sqlSession.close();
		}
	}

	/***
	 * Obtiene el número de productos que están al menos en una lista.
	 * 
	 * @return Número de productos.
	 */
	public static int getNumeroDeProductosEnAlgunaLista() {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			ProductosMapper productosMapper = sqlSession.getMapper(ProductosMapper.class);
			return productosMapper.getNumeroDeProductosEnAlgunaLista();
		} finally {
			sqlSession.close();
		}
	}

	/****
	 * Modifica las características de un producto.
	 * 
	 * @param producto Producto con las nuevas características.
	 */
	public static void updateCaracteristicasDeProducto(CaracteristicasDeProducto producto) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			ProductosMapper productosMapper = sqlSession.getMapper(ProductosMapper.class);
			productosMapper.updateCaracteristicasDeProducto(producto.getId(), producto.getNombre(),
					producto.getImgLink());
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	/****
	 * Obtiene las características de los productos.
	 * 
	 * @return Características de los productos.
	 */
	public static ArrayList<CaracteristicasDeProducto> getCaracteristicasDeProductos() {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			ProductosMapper productosMapper = sqlSession.getMapper(ProductosMapper.class);
			return productosMapper.getCaracteristicasDeProductos();
		} finally {
			sqlSession.close();
		}
	}

	/****
	 * Modifica los datos de un producto para marcarlo como defectuoso.
	 * 
	 * @param caracteristicasDeProducto Características originales del producto.
	 */
	public static void marcarComoDefectuoso(CaracteristicasDeProducto caracteristicasDeProducto) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			ProductosMapper productosMapper = sqlSession.getMapper(ProductosMapper.class);
			productosMapper.marcarComoDefectuoso(caracteristicasDeProducto.getId(),
					"El enlace de este producto no funciona.", "ProductoNotFound", "imgs/ImgNotFound.png");
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	/****
	 * Elimina todos los productos marcados como defectuosos.
	 */
	public static void eliminarDefectuosos() {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {

			ProductosMapper productosMapper = sqlSession.getMapper(ProductosMapper.class);
			productosMapper.eliminarDefectuosos();
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

}
