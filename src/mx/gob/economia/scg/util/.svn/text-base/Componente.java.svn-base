/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.economia.scg.util;

/**
 *
 * @author gerardo
 */
public abstract class Componente
{

    private String nombre;
    private Integer id;

    public Componente(String nombre, Integer id)
    {
        this.nombre = nombre;
        this.id = id;
    }

    abstract public void agregar(Componente c);

    abstract public void remover(Componente c);

    abstract public String mostrar(String profundidad);

    /**
     * @return the nombre
     */
    public String getNombre()
    {
        return Util.formatearCadena(nombre);
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    /**
     * @return the id
     */
    public Integer getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id)
    {
        this.id = id;
    }
}
