package aplicacion.modelo.dao.mappers;

import org.apache.ibatis.annotations.Param;

public interface ListasMapper {
	public String getNombreLista(@Param("id") Integer id);

	public void insertLista(@Param("nombreLista") String nombreLista, @Param("id") Integer id);

	public Integer getIdLista(@Param("idUsuario") Integer idUsuario);

	public void addContenido(@Param("costeObjetivo") Double costeObjetivo, @Param("idLista") Integer idLista,
			@Param("idProducto") Integer idProducto);
}
