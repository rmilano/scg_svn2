package mx.gob.economia.scg.model;

import java.io.Serializable;
import mx.gob.economia.scg.util.Util;

public class Evento implements Serializable
{
    // Propiedades

    private Integer id_evento;
    private String evento;
    private Integer activo;
    private Integer fila;
    private Integer id_area;
    private Area area;

    public Evento()
    {
        this.id_evento = -1;
        this.evento = "";
        this.activo = 1;
        this.area = new Area();
    }

    /**
     * @return the id_evento
     */
    public Integer getId_evento()
    {
        return id_evento;
    }

    /**
     * @param id_evento
     *            the id_evento to set
     */
    public void setId_evento(Integer id_evento)
    {
        this.id_evento = id_evento;
    }

    /**
     * @return the evento
     */
    public String getEvento()
    {
        return Util.formatearCadena(evento);
    }

    /**
     * @param evento
     *            the evento to set
     */
    public void setEvento(String evento)
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
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Evento other = (Evento) obj;
        if (id_evento != other.id_evento)
            return false;
        return true;
    }

    @Override
    public int hashCode()
    {
        this.id_evento = this.id_evento == null ? -1 : this.id_evento;
        final int prime = 31;
        int result = 1;
        result = prime * result + id_evento;
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
}
