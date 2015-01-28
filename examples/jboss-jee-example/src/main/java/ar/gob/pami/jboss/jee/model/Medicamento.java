package ar.gob.pami.jboss.jee.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PAMI_MEDICAMENTO")
public class Medicamento extends Model {

	private String nombre;
	private String laboratorio;
	private Double precioVenta;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorio(String laboratorio) {
		this.laboratorio = laboratorio;
	}

	public Double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(Double precioVenta) {
		this.precioVenta = precioVenta;
	}

}
