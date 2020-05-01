package aplicacion.modelo.dao.mappers;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import aplicacion.modelo.pojo.Producto;

public interface ProductosMapper {

	public ArrayList<Producto> getProductosPorUserId(@Param("id") Integer id);

	public Producto getProductoPorIdContenido(@Param("idContenido") String idContenido);

	public Integer existeProducto(@Param("enlace") String enlace);

	public void insertProducto(@Param("nombre") String nombre, @Param("enlace") String enlace,
			@Param("imgLink") String imgLink);

}
