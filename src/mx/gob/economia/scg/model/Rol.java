package mx.gob.economia.scg.model;

import java.io.Serializable;
import mx.gob.economia.scg.util.Util;

public class Rol implements Serializable
{

    public static final Integer ADMINISTRADOR = 0;
    public static final Integer EMPLEADO = 1;
    public static final Integer CAPTURISTA = 2;
    public static final Integer RECEPCIONISTA = 3;
    public static final Integer OFICIAL = 4;
    public static final Integer REVISOR = 5;
    public static final Integer REPORTEDETALLE = 6;
    public static final Integer CAPTURAEXTERNOS = 7;
    public static final Integer FINALIZAR = 8;

    public Rol()
    {
        id_rol = -1;
        rol = "";
    }

    public Rol(Integer id_rol, String rol)
    {
        this.id_rol = id_rol;
        this.rol = rol;
    }

    public Rol(Integer id_rol)
    {
        this.id_rol = id_rol;
    }
    // Propiedades
    private Integer id_rol;
    private String rol;

    /**
     * @return the id_rol
     */
    public Integer getId_rol()
    {
        return id_rol;
    }

    /**
     * @param id_rol
     *            the id_rol to set
     */
    public void setId_rol(Integer id_rol)
    {
        this.id_rol = id_rol;
    }

    /**
     * @return the rol
     */
    public String getRol()
    {
        return Util.formatearCadena(rol);
    }

    /**
     * @param rol
     *            the rol to set
     */
    public void setRol(String rol)
    {
        this.rol = rol;
    }

    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Rol other = (Rol) obj;
        if (id_rol != null && !id_rol.equals(other.id_rol))
            return false;
        return true;
    }

    public int hashCode()
    {
        if (id_rol != null)
            return id_rol.hashCode();
        else
            return super.hashCode();
    }
}
