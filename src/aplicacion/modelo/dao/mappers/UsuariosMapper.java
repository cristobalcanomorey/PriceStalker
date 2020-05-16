package aplicacion.modelo.dao.mappers;

import org.apache.ibatis.annotations.Param;

import aplicacion.modelo.pojo.Usuario;

public interface UsuariosMapper {
	public Usuario loginUsuario(@Param("correo") String correo, @Param("password") String password);

	public Usuario existeUsuario(@Param("correo") String correo);

	public void registrarUsuario(@Param("nombre") String nombre, @Param("correo") String correo,
			@Param("password") String password);

	public void editarUsuario(@Param("correoOriginal") String correoOriginal, @Param("nombreNuevo") String nombreNuevo,
			@Param("correoNuevo") String correoNuevo, @Param("passwordNuevo") String passwordNuevo);

	public void eliminarUsuario(@Param("id") Integer id);

	public Usuario getUsuarioPorIdLista(@Param("idLista") Integer idLista);

	public void editarNombreYCorreoDeUsuario(@Param("correoOriginal") String correoOriginal,
			@Param("nombreNuevo") String nombreNuevo, @Param("correoNuevo") String correoNuevo);
}
