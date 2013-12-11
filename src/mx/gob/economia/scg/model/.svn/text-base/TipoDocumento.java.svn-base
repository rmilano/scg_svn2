package mx.gob.economia.scg.model;

import java.io.Serializable;
import mx.gob.economia.scg.util.Util;

public class TipoDocumento implements Serializable
{

    // Propiedades
    private Integer id_tipo_documento;
    private String tipo_documento;
    private Integer firma;
    private Integer activo;

    public TipoDocumento()
    {
    }

    public TipoDocumento(Integer id_tipo_documento, String tipo_documento, Integer firma, Integer activo)
    {
        this.id_tipo_documento = id_tipo_documento;
        this.tipo_documento = tipo_documento;
        this.firma = firma;
        this.activo = activo;
    }

    /**
     * @return the id_tipo_documento
     */
    public Integer getId_tipo_documento()
    {
        return id_tipo_documento;
    }

    /**
     * @param id_tipo_documento
     *            the id_tipo_documento to set
     */
    public void setId_tipo_documento(Integer id_tipo_documento)
    {
        this.id_tipo_documento = id_tipo_documento;
    }

    /**
     * @return the tipo_documento
     */
    public String getTipo_documento()
    {
        return Util.formatearCadena(tipo_documento);
    }

    /**
     * @param tipo_documento
     *            the tipo_documento to set
     */
    public void setTipo_documento(String tipo_documento)
    {
        this.tipo_documento = tipo_documento;
    }

    /**
     * @return the firma
     */
    public Integer getFirma()
    {
        return firma;
    }

    /**
     * @param firma the firma to set
     */
    public void setFirma(Integer firma)
    {
        this.firma = firma;
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
}
