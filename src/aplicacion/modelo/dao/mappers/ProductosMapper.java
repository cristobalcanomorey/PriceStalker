package aplicacion.modelo.dao.mappers;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import aplicacion.modelo.pojo.Producto;

public interface ProductosMapper {

	public ArrayList<Producto> getProductosPorUserId(@Param("id") Integer id);

}
