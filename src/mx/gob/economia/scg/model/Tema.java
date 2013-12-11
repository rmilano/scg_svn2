package mx.gob.economia.scg.model;

import java.io.Serializable;
import mx.gob.economia.scg.util.Util;

public class Tema implements Serializable
{
    // Propiedades

    private Integer id_tema;
    private Integer id_tema_padre;
    private Integer id_area;
    private Area area;
    private String tema;
    private Integer evento;
    private Integer activo;
    private Integer fila;
    private Empleado revisor;

    public Tema()
    {
        this.id_tema = -1;
        this.id_tema_padre = -1;
        this.id_area = -1;
        this.area = new Area();
        this.tema = "";
        this.evento = 0;
        this.activo = 1;
        this.revisor = new Empleado();
    }

    /**
     * @return the id_tema
     */
    public Integer getId_tema()
    {
        return id_tema;
    }

    /**
     * @param id_tema
     *            the id_tema to set
     */
    public void setId_tema(Integer id_tema)
    {
        this.id_tema = id_tema;
    }

    /**
     * @return the id_tema_padre
     */
    public Integer getId_tema_padre()
    {
        return id_tema_padre;
    }

    /**
     * @param id_tema_padre
     *            the id_tema_padre to set
     */
    public void setId_tema_padre(Integer id_tema_padre)
    {
        this.id_tema_padre = id_tema_padre;
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
     * @return the area
     */
    public Area getArea()
    {
        return area;
    }

    /**
     * @param area
     *            the area to set
     */
    public void setArea(Area area)
    {
        this.area = area;
    }

    /**
     * @return the tema
     */
    public String getTema()
    {
        return Util.formatearCadena(tema);
    }

    /**
     * @param tema
     *            the tema to set
     */
    public void setTema(String tema)
    {
        this.tema = tema;
    }

    /**
     * @return the evento
     */
    public Integer getEvento()
    {
        return evento;
    }

    /**
     * @param evento
     *            the evento to set
     */
    public void setEvento(Integer evento)
    {
        this.evento = evento;
    }

    /**
     * @return the activo
     */
    public Integer getActivo()
    {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(Integer activo)
    {
        this.activo = activo;
    }

    /**
     * 
     */
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Tema other = (Tema) obj;
        if (id_tema != other.id_tema)
            return false;
        return true;
    }

    public int hashCode()
    {
        this.id_tema = this.id_tema == null ? -1 : this.id_tema;
        final int prime = 31;
        int result = 1;
        result = prime * result + id_tema;
        return result;
    }

    /**
     * @return the fila
     */
    public Integer getFila()
    {
        return fila;
    }

    /**
     * @param fila the fila to set
     */
    public void setFila(Integer fila)
    {
        this.fila = fila;
    }

    /**
     * @return the revisor
     */
    public Empleado getRevisor()
    {
        return revisor;
    }

    /**
     * @param revisor the revisor to set
     */
    public void setRevisor(Empleado revisor)
    {
        this.revisor = revisor;
    }

}
