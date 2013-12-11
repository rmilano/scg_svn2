package mx.gob.economia.scg.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import mx.gob.economia.scg.util.Util;

import org.apache.struts.upload.FormFile;

public class Documento implements Serializable
{

    // Propiedades
    private int idx;
    private Integer id_documento;
    private Integer id_asunto;
    private Integer id_asunto_detalle;
    private AsuntoDetalle asunto_detalle;
    private byte[] blob;
    private String documento;
    private FormFile adjunto;
    private String ruta_adjunto;
    private TipoDocumento tipo_documento = new TipoDocumento();

    public Documento()
    {
        documento = "";

    }

    public Documento(String documento, Integer id_documento,
            TipoDocumento tipo_documento, int idx)
    {
        this.documento = documento;
        this.id_documento = id_documento;
        this.idx = idx;
        this.tipo_documento = tipo_documento;
    }

    /**
     * @return the idx
     */
    public int getIdx()
    {
        return idx;
    }

    /**
     * @param idx
     *            the idx to set
     */
    public void setIdx(int idx)
    {
        this.idx = idx;
    }

    /**
     * @return the id_documento
     */
    public Integer getId_documento()
    {
        return id_documento;
    }

    /**
     * @param id_documento
     *            the id_documento to set
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
     * @return the asunto_detalle
     */
    public AsuntoDetalle getAsunto_detalle()
    {
        return asunto_detalle;
    }

    /**
     * @param asunto_detalle
     *            the asunto_detalle to set
     */
    public void setAsunto_detalle(AsuntoDetalle asunto_detalle)
    {
        this.asunto_detalle = asunto_detalle;
    }

    /**
     * @return the blob
     */
    public byte[] getBlob()
    {
        if (this.adjunto != null)
        {
            byte[] fileData = null;
            try
            {
                fileData = this.adjunto.getFileData();
            } catch (FileNotFoundException e)
            {
                fileData = null;
            } catch (IOException e)
            {
                fileData = null;
            }
            blob = fileData != null && fileData.length > 0 ? fileData : null;
        }
        return blob;
    }

    /**
     * @param blob
     *            the blob to set
     */
    public void setBlob(byte[] blob)
    {
        this.blob = blob;
    }

    /**
     * @return the documento
     */
    public String getDocumento()
    {
        if (adjunto != null && documento != null && documento.length() > 0
                && !documento.contains("."))
        {
            String nombre_adjunto = adjunto.getFileName();
            String extension = nombre_adjunto.substring(nombre_adjunto.lastIndexOf("."));
            documento = documento.trim() + extension;
        }
        return Util.formatearCadena(documento);
    }

    /**
     * @param documento
     *            the documento to set
     */
    public void setDocumento(String documento)
    {
        this.documento = documento;
    }

    /**
     * @return the tipo_documento
     */
    public TipoDocumento getTipo_documento()
    {
        return tipo_documento;
    }

    /**
     * @param tipo_documento
     *            the tipo_documento to set
     */
    public void setTipo_documento(TipoDocumento tipo_documento)
    {
        this.tipo_documento = tipo_documento;
    }

    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Documento other = (Documento) obj;
        if (id_documento != other.id_documento)
            return false;
        if (this.getDocumento() != null && other.documento != null
                && !this.getDocumento().equals(other.documento))
            return false;
        return true;
    }

    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (id_documento != null ? id_documento : 0);
        return result;
    }

    public static void asignaIdx(List<Documento> documentos)
    {
        for (int i = 0; i < documentos.size(); i++)
        {
            documentos.get(i).setIdx(i);
        }
    }

    /**
     * @return the adjunto
     */
    public FormFile getAdjunto()
    {
        return adjunto;
    }

    /**
     * @param adjunto
     *            the adjunto to set
     */
    public void setAdjunto(FormFile adjunto)
    {
        this.adjunto = adjunto;
    }

    /**
     * @return the ruta_adjunto
     */
    public String getRuta_adjunto()
    {
        return ruta_adjunto;
    }

    /**
     * @param ruta_adjunto the ruta_adjunto to set
     */
    public void setRuta_adjunto(String ruta_adjunto)
    {
        this.ruta_adjunto = ruta_adjunto;
    }
}
