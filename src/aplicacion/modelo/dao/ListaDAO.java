package aplicacion.modelo.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import aplicacion.modelo.dao.mappers.ListasMapper;
import aplicacion.modelo.pojo.Contenido;

public class ListaDAO {

	/****
	 * Añade una lista a la BBDD.
	 * 
	 * @param nombreLista Nombre de la lista.
	 * @param id          ID de su usuario.
	 */
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

	/****
	 * Obtiene el ID de la lista de un usuario
	 * 
	 * @param idUsuario ID del usuario.
	 * @return ID de su lista.
	 */
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

	/****
	 * Añade contenido a una lista.
	 * 
	 * @param costeObjetivo Coste objetivo.
	 * @param idLista       ID de la lista.
	 * @param idProducto    ID del producto.
	 */
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

	/****
	 * Obtiene el nombre de una lista por el ID de su usuario.
	 * 
	 * @param id ID de su usuario.
	 * @return Nombre de su lista.
	 */
	public static String getNombreLista(Integer id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			ListasMapper listasMapper = sqlSession.getMapper(ListasMapper.class);
			return listasMapper.getNombreLista(id);
		} finally {
			sqlSession.close();
		}
	}

	/****
	 * Obtiene el contenido de las listas que tengan ese producto.
	 * 
	 * @param id ID del producto.
	 * @return Contenido de las listas que tengan ese producto.
	 */
	public static ArrayList<Contenido> getContenidosPorIdProducto(Integer id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			ListasMapper listasMapper = sqlSession.getMapper(ListasMapper.class);
			return listasMapper.getContenidosPorIdProducto(id);
		} finally {
			sqlSession.close();
		}
	}

	/****
	 * Comprueba si el ID contenido pertenece a uno en la lista del usuario. Si
	 * devuelve Null es que no es de su lista.
	 * 
	 * @param idContenido ID del contenido.
	 * @param idUsuario   ID del usuario.
	 * @return
	 */
	public static Integer getIdContenidoPorIdContenidoEIdUsuario(String idContenido, Integer idUsuario) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			ListasMapper listasMapper = sqlSession.getMapper(ListasMapper.class);
			return listasMapper.getIdContenidoPorIdContenidoEIdUsuario(idContenido, idUsuario);
		} finally {
			sqlSession.close();
		}
	}

}
