package mx.gob.economia.scg.model;

import java.io.Serializable;
import java.util.Date;

public class FirmaAsunto implements Serializable
{

    Integer id_asunto;
    Integer id_asunto_detalle;
    Integer estatus;
    String firmante;
    String firma;
    Date fh_registro;

    /**
     * Constructor
     * @param estatus
     * @param fh_registro
     * @param firma
     * @param firmante
     * @param id_asunto
     * @param id_asunto_detalle
     */
    public FirmaAsunto(Integer estatus, Date fh_registro, String firma,
            String firmante, Integer id_asunto, Integer id_asunto_detalle)
    {
        this.estatus = estatus;
        this.fh_registro = fh_registro;
        this.firma = firma;
        this.firmante = firmante;
        this.id_asunto = id_asunto;
        this.id_asunto_detalle = id_asunto_detalle;
    }

    public FirmaAsunto()
    {
    }

    /**
     * @return the id_asunto
     */
    public Integer getId_asunto()
    {
        return id_asunto;
    }

    /**
     * @param id_asunto
     *            the id_asunto to set
     */
    public void setId_asunto(Integer id_asunto)
    {
        this.id_asunto = id_asunto;
    }

    /**
     * @return the id_asunto_detalle
     */
    public Integer getId_asunto_detalle()
    {
        return id_asunto_detalle;
    }

    /**
     * @param id_asunto_detalle
     *            the id_asunto_detalle to set
     */
    public void setId_asunto_detalle(Integer id_asunto_detalle)
    {
        this.id_asunto_detalle = id_asunto_detalle;
    }

    /**
     * @return the estatus
     */
    public Integer getEstatus()
    {
        return estatus;
    }

    /**
     * @param estatus
     *            the estatus to set
     */
    public void setEstatus(Integer estatus)
    {
        this.estatus = estatus;
    }

    /**
     * @return the firmante
     */
    public String getFirmante()
    {
        return firmante;
    }

    /**
     * @param firmante
     *            the firmante to set
     */
    public void setFirmante(String firmante)
    {
        this.firmante = firmante;
    }

    /**
     * @return the firma
     */
    public String getFirma()
    {
        return firma;
    }

    /**
     * @param firma
     *            the firma to set
     */
    public void setFirma(String firma)
    {
        this.firma = firma;
    }

    /**
     * @return the fh_registro
     */
    public Date getFh_registro()
    {
        return fh_registro;
    }

    /**
     * @param fh_registro
     *            the fh_registro to set
     */
    public void setFh_registro(Date fh_registro)
    {
        this.fh_registro = fh_registro;
    }
}
