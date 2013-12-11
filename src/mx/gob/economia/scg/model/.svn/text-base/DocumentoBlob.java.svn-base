package mx.gob.economia.scg.model;

import java.io.Serializable;

public class DocumentoBlob implements Serializable
{

    // Propiedades
    private Integer id_documento;
    private Integer id_asunto;
    private Integer id_asunto_detalle;
    private byte[] blob;

    /**
     * Constructor
     * @param blob
     * @param id_asunto
     * @param id_asunto_detalle
     * @param id_documento
     */
    public DocumentoBlob(byte[] blob, Integer id_asunto,
            Integer id_asunto_detalle, Integer id_documento)
    {
        this.blob = blob;
        this.id_asunto = id_asunto;
        this.id_asunto_detalle = id_asunto_detalle;
        this.id_documento = id_documento;
    }

    public DocumentoBlob()
    {
    }

    /**
     * @return the id_documento
     */
    public Integer getId_documento()
    {
        return id_documento;
    }

    /**
     * @param id_documento the id_documento to set
     */
    public void setId_documento(Integer id_documento)
    {
        this.id_documento = id_documento;
    }

    /**
     * @return the id_asunto
     */
    public Integer getId_asunto()
    {
        return id_asunto;
    }

    /**
     * @param id_asunto the id_asunto to set
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
     * @param id_asunto_detalle the id_asunto_detalle to set
     */
    public void setId_asunto_detalle(Integer id_asunto_detalle)
    {
        this.id_asunto_detalle = id_asunto_detalle;
    }

    /**
     * @return the blob
     */
    public byte[] getBlob()
    {
        return blob;
    }

    /**
     * @param blob the blob to set
     */
    public void setBlob(byte[] blob)
    {
        this.blob = blob;
    }
}
