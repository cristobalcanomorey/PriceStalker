package aplicacion.modelo.pojo;

import java.util.Date;

public class Precio {

	private Integer id;
	private Double coste;
	private Date fecha;
	private Integer idProducto;

	public Precio() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getCoste() {
		return coste;
	}

	public void setCoste(Double coste) {
		this.coste = coste;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

}
