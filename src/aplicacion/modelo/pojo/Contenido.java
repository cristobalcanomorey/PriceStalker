package aplicacion.modelo.pojo;

public class Contenido {

	private Integer id;
	private Double precioObjetivo;
	private Integer idLista;
	private Integer idProducto;

	public Contenido() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getPrecioObjetivo() {
		return precioObjetivo;
	}

	public void setPrecioObjetivo(Double precioObjetivo) {
		this.precioObjetivo = precioObjetivo;
	}

	public Integer getIdLista() {
		return idLista;
	}

	public void setIdLista(Integer idLista) {
		this.idLista = idLista;
	}

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

}
