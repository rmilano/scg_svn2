package mx.gob.economia.scg.model;

import java.io.Serializable;
import mx.gob.economia.scg.util.Util;

public class Area implements Serializable
{

    // Constantes
    public static final Integer INTERNA = 0;
    public static final Integer EXTERNA = 1;
    // Propiedades
    private Integer id_area;
    private Integer id_area_padre;
    private Area area_padre;
    private String area;
    private String clave;
    private Integer tipo;
    private Integer activo;
    private Integer unidad_administrativa;
    private Integer listado_area;
    private Integer envio_correo;

    public Area()
    {
    	this.tipo = INTERNA;
        this.id_area = -1;
        this.id_area_padre = -1;
        this.area = "";
        this.unidad_administrativa = 0;
        this.listado_area = 0;
        this.envio_correo = 0;
    }

    /**
     * @return the id_area
     */
    public Integer getId_area()
    {
        return id_area;
    }

    /**
     * @param id_area
     *            the id_area to set
     */
    public void setId_area(Integer id_area)
    {
        this.id_area = id_area;
    }

    /**
     * @return the id_area_padre
     */
    public Integer getId_area_padre()
    {
        return id_area_padre;
    }

    /**
     * @param id_area_padre
     *            the id_area_padre to set
     */
    public void setId_area_padre(Integer id_area_padre)
    {
        this.id_area_padre = id_area_padre;
    }

    /**
     * @return the area_padre
     */
    public Area getArea_padre()
    {
        return area_padre;
    }

    /**
     * @param area_padre
     *            the area_padre to set
     */
    public void setArea_padre(Area area_padre)
    {
        this.area_padre = area_padre;
    }

    /**
     * @return the area
     */
    public String getArea()
    {
        return Util.formatearCadena(area);
    }

    /**
     * @param area
     *            the area to set
     */
    public void setArea(String area)
    {
        this.area = area;
    }

    /**
     * @return the clave
     */
    public String getClave()
    {
        return Util.formatearCadena(clave);
    }

    /**
     * @param clave
     *            the clave to set
     */
    public void setClave(String clave)
    {
        this.clave = clave;
    }

    /**
     * @return the tipo
     */
    public Integer getTipo()
    {
        return tipo;
    }

    /**
     * @param tipo
     *            the tipo to set
     */
    public void setTipo(Integer tipo)
    {
        this.tipo = tipo;
    }

    /**
     * @return the activo
     */
    public Integer getActivo()
    {
        return activo;
    }

    /**
     * @param activo
     *            the activo to set
     */
    public void setActivo(Integer activo)
    {
        this.activo = activo;
    }
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Area other = (Area) obj;
		if (id_area != other.id_area)
			return false;
		return true;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_area;
		return result;
	}

    /**
     * @return the unidad_administrativa
     */
    public Integer getUnidad_administrativa()
    {
        return unidad_administrativa;
    }

    /**
     * @param unidad_administrativa the unidad_administrativa to set
     */
    public void setUnidad_administrativa(Integer unidad_administrativa)
    {
        this.unidad_administrativa = unidad_administrativa;
    }

    /**
     * @return the listado_area
     */
    public Integer getListado_area()
    {
        return listado_area;
    }

    /**
     * @param listado_area the listado_area to set
     */
    public void setListado_area(Integer listado_area)
    {
        this.listado_area = listado_area;
    }

    public Integer getEnvio_correo() {
        return envio_correo;
    }

    public void setEnvio_correo(Integer envio_correo) {
        this.envio_correo = envio_correo;
    }
}
