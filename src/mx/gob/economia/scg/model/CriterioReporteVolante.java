/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.economia.scg.model;

import java.io.Serializable;
import java.util.Date;
import mx.gob.economia.scg.util.Util;

/**
 *
 * @author javier
 */
public class CriterioReporteVolante implements Serializable
{

    private Integer id_expediente;
    private Integer id_tema;
    private Integer id_area;
    private Date fh_registroIni;
    private Date fh_registroFin;
    private String fh_registroIniDDMMYYYY;
    private String fh_registroFinDDMMYYYY;
    private String tema;
    private String expediente;
    private String area;

    public CriterioReporteVolante()
    {
        id_expediente = -1;
        id_tema = -1;
        id_area = -1;
        fh_registroIni = new Date();
        fh_registroFin = new Date();
        fh_registroIniDDMMYYYY = "";
        fh_registroFinDDMMYYYY = "";
        tema = "";
        expediente = "";
        area = "";
    }

    /**
     * @return the id_expediente
     */
    public Integer getId_expediente()
    {
        return id_expediente;
    }

    /**
     * @param id_expediente the id_expediente to set
     */
    public void setId_expediente(Integer id_expediente)
    {
        this.id_expediente = id_expediente;
    }

    /**
     * @return the id_tema
     */
    public Integer getId_tema()
    {
        return id_tema;
    }

    /**
     * @param id_tema the id_tema to set
     */
    public void setId_tema(Integer id_tema)
    {
        this.id_tema = id_tema;
    }

    /**
     * @return the id_area
     */
    public Integer getId_area()
    {
        return id_area;
    }

    /**
     * @param id_area the id_area to set
     */
    public void setId_area(Integer id_area)
    {
        this.id_area = id_area;
    }

    /**
     * @return the fh_registroIni
     */
    public Date getFh_registroIni()
    {
        if (this.fh_registroIniDDMMYYYY != null
                && this.fh_registroIniDDMMYYYY.length() > 0)
            fh_registroIni = Util.parsearFecha(this.fh_registroIniDDMMYYYY);
        return fh_registroIni;
    }

    /**
     * @param fh_registroIni the fh_registroIni to set
     */
    public void setFh_registroIni(Date fh_registroIni)
    {
        this.fh_registroIni = fh_registroIni;
    }

    /**
     * @return the fh_registroFin
     */
    public Date getFh_registroFin()
    {
        if (this.fh_registroFinDDMMYYYY != null
                && this.fh_registroFinDDMMYYYY.length() > 0)
            fh_registroFin = Util.parsearFecha(this.fh_registroFinDDMMYYYY);
        return fh_registroFin;
    }

    /**
     * @param fh_registroFin the fh_registroFin to set
     */
    public void setFh_registroFin(Date fh_registroFin)
    {
        this.fh_registroFin = fh_registroFin;
    }

    /**
     * @return the fh_registroIniDDMMYYYY
     */
    public String getFh_registroIniDDMMYYYY()
    {
        return fh_registroIniDDMMYYYY;
    }

    /**
     * @param fh_registroIniDDMMYYYY the fh_registroIniDDMMYYYY to set
     */
    public void setFh_registroIniDDMMYYYY(String fh_registroIniDDMMYYYY)
    {
        this.fh_registroIniDDMMYYYY = fh_registroIniDDMMYYYY;
    }

    /**
     * @return the fh_registroFinDDMMYYYY
     */
    public String getFh_registroFinDDMMYYYY()
    {
        return fh_registroFinDDMMYYYY;
    }

    /**
     * @param fh_registroFinDDMMYYYY the fh_registroFinDDMMYYYY to set
     */
    public void setFh_registroFinDDMMYYYY(String fh_registroFinDDMMYYYY)
    {
        this.fh_registroFinDDMMYYYY = fh_registroFinDDMMYYYY;
    }

    /**
     * @return the tema
     */
    public String getTema()
    {
        return tema;
    }

    /**
     * @param tema the tema to set
     */
    public void setTema(String tema)
    {
        this.tema = tema;
    }

    /**
     * @return the expediente
     */
    public String getExpediente()
    {
        return expediente;
    }

    /**
     * @param expediente the expediente to set
     */
    public void setExpediente(String expediente)
    {
        this.expediente = expediente;
    }

    /**
     * @return the area
     */
    public String getArea()
    {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(String area)
    {
        this.area = area;
    }
}
