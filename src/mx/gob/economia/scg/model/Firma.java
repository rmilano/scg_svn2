package mx.gob.economia.scg.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Firma implements Serializable
{

    String id_solicitud; // Identificador de la solicitud (id. asunto)
    String tipo_asunto; // Tipo de asunto (Estatus_asunto)
    String rfc_usuario; // RFC del usuario
    // Información general
    String id_asunto; // id_asunto
    String estatus; // estatus
    String no_asunto; // no_asunto
    String asunto; // asunto
    String descripcion; // descripcion
    String id_remitente; // id_remitente
    String id_destinatario; // id_destinatario
    String fh_asunto; // fh_asunto
    String fh_recepcion; // fh_recepcion
    String fh_solicitud; // fecha de solicitud
    List<String> rutas_doctos = new ArrayList<String>();
    String cadena;
    String archivos;
    FirmaAsunto firmaAsunto = new FirmaAsunto();
    FirmaDocumento firmaDocumento = new FirmaDocumento();

    public Firma()
    {
    }

    /**
     * @return the id_solicitud
     */
    public String getId_solicitud()
    {
        return id_solicitud;
    }

    /**
     * @param id_solicitud
     *            the id_solicitud to set
     */
    public void setId_solicitud(String id_solicitud)
    {
        this.id_solicitud = id_solicitud;
    }

    /**
     * @return the tipo_asunto
     */
    public String getTipo_asunto()
    {
        return tipo_asunto;
    }

    /**
     * @param tipo_asunto
     *            the tipo_asunto to set
     */
    public void setTipo_asunto(String tipo_asunto)
    {
        this.tipo_asunto = tipo_asunto;
    }

    /**
     * @return the rfc_usuario
     */
    public String getRfc_usuario()
    {
        return rfc_usuario;
    }

    /**
     * @param rfc_usuario
     *            the rfc_usuario to set
     */
    public void setRfc_usuario(String rfc_usuario)
    {
        this.rfc_usuario = rfc_usuario;
    }

    /**
     * @return the id_asunto
     */
    public String getId_asunto()
    {
        return id_asunto;
    }

    /**
     * @param id_asunto
     *            the id_asunto to set
     */
    public void setId_asunto(String id_asunto)
    {
        this.id_asunto = id_asunto;
    }

    /**
     * @return the estatus
     */
    public String getEstatus()
    {
        return estatus;
    }

    /**
     * @param estatus
     *            the estatus to set
     */
    public void setEstatus(String estatus)
    {
        this.estatus = estatus;
    }

    /**
     * @return the no_asunto
     */
    public String getNo_asunto()
    {
        return no_asunto;
    }

    /**
     * @param no_asunto
     *            the no_asunto to set
     */
    public void setNo_asunto(String no_asunto)
    {
        this.no_asunto = no_asunto;
    }

    /**
     * @return the asunto
     */
    public String getAsunto()
    {
        return asunto;
    }

    /**
     * @param asunto
     *            the asunto to set
     */
    public void setAsunto(String asunto)
    {
        this.asunto = asunto;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion()
    {
        return descripcion;
    }

    /**
     * @param descripcion
     *            the descripcion to set
     */
    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    /**
     * @return the id_remitente
     */
    public String getId_remitente()
    {
        return id_remitente;
    }

    /**
     * @param id_remitente
     *            the id_remitente to set
     */
    public void setId_remitente(String id_remitente)
    {
        this.id_remitente = id_remitente;
    }

    /**
     * @return the id_destinatario
     */
    public String getId_destinatario()
    {
        return id_destinatario;
    }

    /**
     * @param id_destinatario
     *            the id_destinatario to set
     */
    public void setId_destinatario(String id_destinatario)
    {
        this.id_destinatario = id_destinatario;
    }

    /**
     * @return the fh_asunto
     */
    public String getFh_asunto()
    {
        return fh_asunto;
    }

    /**
     * @param fh_asunto
     *            the fh_asunto to set
     */
    public void setFh_asunto(String fh_asunto)
    {
        this.fh_asunto = fh_asunto;
    }

    /**
     * @return the fh_recepcion
     */
    public String getFh_recepcion()
    {
        return fh_recepcion;
    }

    /**
     * @param fh_recepcion
     *            the fh_recepcion to set
     */
    public void setFh_recepcion(String fh_recepcion)
    {
        this.fh_recepcion = fh_recepcion;
    }

    /**
     * @return the fh_solicitud
     */
    public String getFh_solicitud()
    {
        return fh_solicitud;
    }

    /**
     * @param fh_solicitud
     *            the fh_solicitud to set
     */
    public void setFh_solicitud(String fh_solicitud)
    {
        this.fh_solicitud = fh_solicitud;
    }

    /**
     * @return the rutas_doctos
     */
    public List<String> getRutas_doctos()
    {
        return rutas_doctos;
    }

    /**
     * @param rutas_doctos
     *            the rutas_doctos to set
     */
    public void setRutas_doctos(List<String> rutas_doctos)
    {
        this.rutas_doctos = rutas_doctos;
    }

    /**
     * @return the cadena
     */
    public String getCadena()
    {
        cadena = new String("||");
        cadena += this.id_solicitud != null ? this.id_solicitud + "|" : "|";
        cadena += this.tipo_asunto != null ? this.tipo_asunto + "|" : "|";
        cadena += this.rfc_usuario != null ? this.rfc_usuario + "|" : "|";
        cadena += this.id_asunto != null ? this.id_asunto + "|" : "|";
        cadena += this.estatus != null ? this.estatus + "|" : "|";
        cadena += this.no_asunto != null ? this.no_asunto + "|" : "|";
        cadena += this.asunto != null ? this.asunto + "|" : "|";
        cadena += this.descripcion != null ? this.descripcion + "|" : "|";
        cadena += this.id_remitente != null ? this.id_remitente + "|" : "|";
        cadena += this.id_destinatario != null ? this.id_destinatario + "|"
                : "|";
        cadena += this.fh_asunto != null ? this.fh_asunto + "|" : "|";
        cadena += this.fh_recepcion != null ? this.fh_recepcion + "|" : "|";
        cadena += this.fh_solicitud != null ? this.fh_solicitud + "|" : "|";
        cadena += "|";
        return cadena;
    }

    /**
     * @param cadena
     *            the cadena to set
     */
    public void setCadena(String cadena)
    {
        this.cadena = cadena;
    }

    /**
     * @return the archivos
     */
    public String getArchivos()
    {
        archivos = new String();
        archivos += "";
        int i = 1;
        for (String ruta_docto : this.rutas_doctos)
        {
            archivos += ruta_docto;
            if (rutas_doctos.size() > i)
                archivos += "|";
            i++;
        }
        return archivos;
    }

    /**
     * @param archivos
     *            the archivos to set
     */
    public void setArchivos(String archivos)
    {
        this.archivos = archivos;
    }

    /**
     * @return the firmaAsunto
     */
    public FirmaAsunto getFirmaAsunto()
    {
        return firmaAsunto;
    }

    /**
     * @param firmaAsunto
     *            the firmaAsunto to set
     */
    public void setFirmaAsunto(FirmaAsunto firmaAsunto)
    {
        this.firmaAsunto = firmaAsunto;
    }

    /**
     * @return the firmaDocumento
     */
    public FirmaDocumento getFirmaDocumento()
    {
        return firmaDocumento;
    }

    /**
     * @param firmaDocumento
     *            the firmaDocumento to set
     */
    public void setFirmaDocumento(FirmaDocumento firmaDocumento)
    {
        this.firmaDocumento = firmaDocumento;
    }
}
