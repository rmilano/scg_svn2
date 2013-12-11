package mx.gob.economia.scg.model;

import java.io.Serializable;
import java.util.Date;
import mx.gob.economia.scg.util.Util;


public class Expediente implements Serializable
{
    // Propiedades

    private Integer id_expediente;
    private Integer id_area;
    private Area area;
    private String cve_expediente;
    private String expediente;
    private Date fh_vigencia;
    private Integer activo;
    private String fh_vigenciaDDMMYYYY;
    private boolean bandera;
    private String expediente_clave;

    public Expediente()
    {
        this.id_expediente = -1;
        this.id_area = -1;
        this.area = new Area();
        this.cve_expediente = "";
        this.expediente = "";
        this.fh_vigencia = null;        
        this.activo = 1;
        bandera = false;
        expediente_clave = "";
    }

    /**
     * @return the id_expediente
     */
    public Integer getId_expediente()
    {
        return id_expediente;
    }

    /**
     * @param id_expediente
     *            the id_expediente to set
     */
    public void setId_expediente(Integer id_expediente)
    {
        this.id_expediente = id_expediente;
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
     * @return the expediente
     */
    public String getExpediente()
    {
       return expediente;
    }

    /**
     * @param expediente
     *            the expediente to set
     */
    public void setExpediente(String expediente)
    {
        this.expediente = expediente;
    }

    /**
     * @return the fh_vigencia
     */
    public Date getFh_vigencia()
    {
        if (this.fh_vigenciaDDMMYYYY != null
                && this.fh_vigenciaDDMMYYYY.length() > 0)
            fh_vigencia = Util.parsearFecha(this.fh_vigenciaDDMMYYYY);
        return fh_vigencia;
    }

    /**
     * @param fh_vigencia
     *            the fh_vigencia to set
     */
    public void setFh_vigencia(Date fh_vigencia)
    {
        this.fh_vigencia = fh_vigencia;
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
        final Expediente other = (Expediente) obj;
        if (id_expediente != other.id_expediente)
            return false;
        return true;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + id_expediente;
        return result;
    }

    /**
     * @return the cve_expediente
     */
    public String getCve_expediente()
    {
        return Util.formatearCadena(cve_expediente);
    }

    /**
     * @param cve_expediente the cve_expediente to set
     */
    public void setCve_expediente(String cve_expediente)
    {
        this.cve_expediente = cve_expediente;
    }

    /**
     * @return the fecha_vigenciaDDMMYYYY
     */
    public String getFh_vigenciaDDMMYYYY()
    {        
        if (this.fh_vigenciaDDMMYYYY == null
                && this.fh_vigencia != null)
            fh_vigenciaDDMMYYYY = Util.formatearFecha(this.fh_vigencia);
        return fh_vigenciaDDMMYYYY;
    }

    /**
     * @param fecha_vigenciaDDMMYYYY the fecha_vigenciaDDMMYYYY to set
     */
    public void setFh_vigenciaDDMMYYYY(String fh_vigenciaDDMMYYYY)
    {
        this.fh_vigenciaDDMMYYYY = fh_vigenciaDDMMYYYY;
    }

    /**
     * @return the bandera
     */
    public boolean isBandera()
    {
        return bandera;
    }

    /**
     * @param bandera the bandera to set
     */
    public void setBandera(boolean bandera)
    {
        this.bandera = bandera;
    }

    public String getExpediente_clave() {
         /*String concatExpediente;
         concatExpediente =  expediente_clave;
         return Util.formatearCadena(concatExpediente);*/
        return expediente_clave.substring(expediente_clave.indexOf(" - ")+3);
    }

    public void setExpediente_clave(String expediente_clave) {
        this.expediente_clave = expediente_clave;
    }

}
