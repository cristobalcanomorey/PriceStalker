package aplicacion.modelo.dao.mappers;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import aplicacion.modelo.pojo.Precio;

public interface PreciosMapper {

	ArrayList<Precio> obtenerPreciosDeProductoPorIdContenido(@Param("contenidoId") int contenidoId);

}
