package mx.gob.economia.scg.model;

import java.io.Serializable;

public class Instruccion implements Serializable
{
	// Propiedades
	private Integer id_instruccion;
	private Integer id_area;
	private Area area;
	private Integer id_empleado;	
	private String instruccion;
	private Integer activo;

	public Instruccion() {
		this.id_instruccion = -1;
		this.id_area = -1;
		this.area = new Area();
		this.id_empleado = -1;		
		this.instruccion = "";
		this.activo = 1;
	}

	/**
	 * @return the id_instruccion
	 */
	public Integer getId_instruccion() {
		return id_instruccion;
	}

	/**
	 * @param id_instruccion
	 *            the id_instruccion to set
	 */
	public void setId_instruccion(Integer id_instruccion) {
		this.id_instruccion = id_instruccion;
	}

	/**
	 * @return the id_area
	 */
	public Integer getId_area() {
		return id_area;
	}

	/**
	 * @param id_area
	 *            the id_area to set
	 */
	public void setId_area(Integer id_area) {
		this.id_area = id_area;
	}

	/**
	 * @return the area
	 */
	public Area getArea() {
		return area;
	}

	/**
	 * @param area
	 *            the area to set
	 */
	public void setArea(Area area) {
		this.area = area;
	}

	/**
	 * @return the id_empleado
	 */
	public Integer getId_empleado() {
		return id_empleado;
	}

	/**
	 * @param id_empleado
	 *            the id_empleado to set
	 */
	public void setId_empleado(Integer id_empleado) {
		this.id_empleado = id_empleado;
	}

	/**
	 * @return the instruccion
	 */
	public String getInstruccion() {
            this.instruccion = this.instruccion == null? "": this.instruccion.toUpperCase();
	    return instruccion;
	}

	/**
	 * @param instruccion
	 *            the instruccion to set
	 */
	public void setInstruccion(String instruccion) {
		this.instruccion = instruccion;
	}

	/**
	 * @return the activo
	 */
	public Integer getActivo() {
		return activo;
	}

	/**
	 * @param activo
	 *            the activo to set
	 */
	public void setActivo(Integer activo) {
		this.activo = activo;
	}

	/**
     * 
     */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Instruccion other = (Instruccion) obj;
		if (id_instruccion != other.id_instruccion)
			return false;
		return true;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_instruccion;
		return result;
	}

}
