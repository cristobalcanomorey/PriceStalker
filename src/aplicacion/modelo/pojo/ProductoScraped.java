package aplicacion.modelo.pojo;

public class ProductoScraped {

	private String nombre;
	private Double precio;
	private String imgLink;

	public ProductoScraped(String nombre, Double precio, String imgLink) {
		this.nombre = nombre;
		this.precio = precio;
		this.imgLink = imgLink;
	}

	public String getNombre() {
		return nombre;
	}

	public Double getPrecio() {
		return precio;
	}

	public String getImgLink() {
		return imgLink;
	}

}
