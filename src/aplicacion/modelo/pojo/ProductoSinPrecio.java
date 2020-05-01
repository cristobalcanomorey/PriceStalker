package aplicacion.modelo.pojo;

public class ProductoSinPrecio {

	private Integer id;
	private String nombre;
	private String link;
	private Double precioObjetivo;
	private String imgLink;

	public ProductoSinPrecio() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Double getPrecioObjetivo() {
		return precioObjetivo;
	}

	public void setPrecioObjetivo(Double precioObjetivo) {
		this.precioObjetivo = precioObjetivo;
	}

	public String getImgLink() {
		return imgLink;
	}

	public void setImgLink(String imgLink) {
		this.imgLink = imgLink;
	}

}
