package mx.gob.economia.scg.model;

import java.io.Serializable;
import java.util.Date;
import mx.gob.economia.scg.util.Util;

public class Prioridad implements Serializable
{

    // Propiedades
    private Integer id_prioridad;
    private String prioridad;
    private Integer dias;
    private String prioridad_dias;
    private Date fh_limite;
    private String fh_limiteDDMMYYYY;

    public Prioridad()
    {
        this.id_prioridad = -1;
        this.dias = 0;
        this.prioridad = "";
        this.prioridad_dias = "";
        this.fh_limite = new Date();
    }

    public Prioridad(Integer dias, Integer id_prioridad, String prioridad)
    {
        this.dias = dias;
        this.id_prioridad = id_prioridad;
        this.prioridad = prioridad;
    }

    /**
     * @return the id_prioridad
     */
    public Integer getId_prioridad()
    {
        return id_prioridad;
    }

    /**
     * @param id_prioridad
     *            the id_prioridad to set
     */
    public void setId_prioridad(Integer id_prioridad)
    {
        this.id_prioridad = id_prioridad;
    }

    /**
     * @return the prioridad
     */
    public String getPrioridad()
    {
        return Util.formatearCadena(prioridad);
    }

    /**
     * @param prioridad
     *            the prioridad to set
     */
    public void setPrioridad(String prioridad)
    {
        this.prioridad = prioridad;
    }

    /**
     * @return the dias
     */
    public Integer getDias()
    {
        return dias;
    }

    /**
     * @param dias
     *            the dias to set
     */
    public void setDias(Integer dias)
    {
        this.dias = dias;
    }

    /**
     * @return the prioridad_dias
     */
    public String getPrioridad_dias()
    {
        return prioridad_dias;
    }

    /**
     * @param prioridad_dias
     *            the prioridad_dias to set
     */
    public void setPrioridad_dias(String prioridad_dias)
    {
        this.prioridad_dias = prioridad_dias;
    }

    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Prioridad other = (Prioridad) obj;
        if (id_prioridad.intValue() != other.id_prioridad.intValue())
            return false;
        return true;
    }

    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + id_prioridad.intValue();
        return result;
    }
    /**
     * @return the fh_limite
     */
    public Date getFh_limite() {
        if (this.fh_limiteDDMMYYYY != null
                && this.fh_limiteDDMMYYYY.length() > 0) {
            fh_limite = Util.parsearFecha(this.fh_limiteDDMMYYYY);
        }
        return fh_limite;
    }

    /**
     * @param fh_limite
     *            the fh_limite to set
     */
    public void setFh_limite(Date fh_limite) {
        this.fh_limite = fh_limite;
    }

    /**
     * @return the fh_limiteDDMMYYYY
     */
    public String getFh_limiteDDMMYYYY() {
        if (this.fh_limiteDDMMYYYY == null && this.fh_limite != null) {
            fh_limiteDDMMYYYY = Util.formatearFecha(this.fh_limite);
        }
        return fh_limiteDDMMYYYY;
    }

    /**
     * @param fh_limiteDDMMYYYY
     *            the fh_limiteDDMMYYYY to set
     */
    public void setFh_limiteDDMMYYYY(String fh_limiteDDMMYYYY) {
        this.fh_limiteDDMMYYYY = fh_limiteDDMMYYYY;
    }
}
