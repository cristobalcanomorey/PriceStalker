package aplicacion.modelo.dao.mappers;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import aplicacion.modelo.pojo.Precio;

public interface PreciosMapper {

	public ArrayList<Precio> obtenerPreciosDeProductoPorIdContenido(@Param("contenidoId") int contenidoId);

	public void insertPrecio(@Param("id") Integer id, @Param("precio") Double precio);

}
