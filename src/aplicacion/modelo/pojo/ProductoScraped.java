package aplicacion.modelo.pojo;

public class ProductoScraped {

	private String nombre;
	private Double precio;
	private String imgLink;
	private byte[] imageBytes = null;

	public ProductoScraped(String nombre, Double precio, String imgLink) {
		this.nombre = nombre;
		this.precio = precio;
		this.imgLink = imgLink;
	}

	public ProductoScraped(String nombre, Double precio, byte[] imageBytes) {
		this.nombre = nombre;
		this.precio = precio;
		this.imageBytes = imageBytes;
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

	public void setImgLink(String imgLink) {
		this.imgLink = imgLink;
	}

	public byte[] getImageBytes() {
		return imageBytes;
	}

}
