package aplicacion.modelo.dao.mappers;

import org.apache.ibatis.annotations.Param;

import aplicacion.modelo.pojo.Usuario;

public interface UsuariosMapper {
	public Usuario loginUsuario(@Param("correo") String correo, @Param("password") String password);
}
