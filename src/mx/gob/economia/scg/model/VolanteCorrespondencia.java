/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.economia.scg.model;

import java.io.InputStream;
import mx.gob.economia.scg.util.Constantes;

/**
 *
 * @author roque
 */
public class VolanteCorrespondencia
{
    private Area areaRemitente;
    private Area areaProcedencia;
    private Empleado destinatario;
    private Empleado firmanteProcedencia;
    private Empleado firmanteRemitente;
    private String fechaVolante;
    private String folio;
    private Integer consecutivo;
    private Prioridad prioridad;
    private String fechaLimiteAtencion;
    private String referencia;
    private String documento;
    private String comentario;    
    private TipoSeguimiento tipoSeguimiento;
    private String folioConsecutivoTurnado;
    private Instruccion instruccion;
    private InputStream logo;
    private String ccps;
    private Integer confidencial;
    private String confidencial_img;
    private String logo_img;
    private String descripcion;
    private String instruccionAdicional;
    private String promotor;
    private String expediente;
    private String sintesis;
    private String fechaRespuesta;
    private String respuesta;

    public VolanteCorrespondencia()
    {
        this.areaProcedencia = new Area();
        this.areaRemitente = new Area();
        this.destinatario = new Empleado();
        this.firmanteProcedencia = new Empleado();
        this.firmanteRemitente = new Empleado();
        this.fechaVolante = "";
        this.folio = "";
        this.consecutivo = 0;
        this.prioridad = new Prioridad();
        this.fechaLimiteAtencion = "";
        this.referencia = "";
        this.documento = "";
        this.comentario = "";        
        this.tipoSeguimiento = new TipoSeguimiento();
        this.folioConsecutivoTurnado = "";
        this.instruccion = new Instruccion();
        this.confidencial = 0;
        this.confidencial_img = "";
        this.logo_img = "";
        this.descripcion="";
        this.instruccionAdicional="";
        this.promotor="";
        this.expediente="";
        this.sintesis="";
        this.fechaRespuesta="";
        this.respuesta="";
    }


    /**
     * @return the areaRemitente
     */
    public Area getAreaRemitente()
    {
        return areaRemitente;
    }

    /**
     * @param areaRemitente the areaRemitente to set
     */
    public void setAreaRemitente(Area areaRemitente)
    {
        this.areaRemitente = areaRemitente;
    }

    /**
     * @return the areaProcedencia
     */
    public Area getAreaProcedencia()
    {
        return areaProcedencia;
    }

    /**
     * @param areaProcedencia the areaProcedencia to set
     */
    public void setAreaProcedencia(Area areaProcedencia)
    {
        this.areaProcedencia = areaProcedencia;
    }

    /**
     * @return the destinatario
     */
    public Empleado getDestinatario()
    {
        return destinatario;
    }

    /**
     * @param destinatario the destinatario to set
     */
    public void setDestinatario(Empleado destinatario)
    {
        this.destinatario = destinatario;
    }

    /**
     * @return the firmanteProcedencia
     */
    public Empleado getFirmanteProcedencia()
    {
        return firmanteProcedencia;
    }

    /**
     * @param firmanteProcedencia the firmanteProcedencia to set
     */
    public void setFirmanteProcedencia(Empleado firmanteProcedencia)
    {
        this.firmanteProcedencia = firmanteProcedencia;
    }

    /**
     * @return the firmanteRemitente
     */
    public Empleado getFirmanteRemitente()
    {
        return firmanteRemitente;
    }

    /**
     * @param firmanteRemitente the firmanteRemitente to set
     */
    public void setFirmanteRemitente(Empleado firmanteRemitente)
    {
        this.firmanteRemitente = firmanteRemitente;
    }

    /**
     * @return the fechaVolante
     */
    public String getFechaVolante()
    {
        return fechaVolante;
    }

    /**
     * @param fechaVolante the fechaVolante to set
     */
    public void setFechaVolante(String fechaVolante)
    {
        this.fechaVolante = fechaVolante;
    }

    /**
     * @return the folio
     */
    public String getFolio()
    {
        return folio;
    }

    /**
     * @param folio the folio to set
     */
    public void setFolio(String folio)
    {
        this.folio = folio;
    }

    /**
     * @return the consecutivo
     */
    public Integer getConsecutivo()
    {
        return consecutivo;
    }

    /**
     * @param consecutivo the consecutivo to set
     */
    public void setConsecutivo(Integer consecutivo)
    {
        this.consecutivo = consecutivo;
    }

    /**
     * @return the prioridad
     */
    public Prioridad getPrioridad()
    {
        return prioridad;
    }

    /**
     * @param prioridad the prioridad to set
     */
    public void setPrioridad(Prioridad prioridad)
    {
        this.prioridad = prioridad;
    }

    /**
     * @return the fechaLimiteAtencion
     */
    public String getFechaLimiteAtencion()
    {
        return fechaLimiteAtencion;
    }

    /**
     * @param fechaLimiteAtencion the fechaLimiteAtencion to set
     */
    public void setFechaLimiteAtencion(String fechaLimiteAtencion)
    {
        this.fechaLimiteAtencion = fechaLimiteAtencion;
    }

    /**
     * @return the referencia
     */
    public String getReferencia()
    {
        return referencia;
    }

    /**
     * @param referencia the referencia to set
     */
    public void setReferencia(String referencia)
    {
        this.referencia = referencia;
    }

    /**
     * @return the documento
     */
    public String getDocumento()
    {
        return documento;
    }

    /**
     * @param documento the documento to set
     */
    public void setDocumento(String documento)
    {
        this.documento = documento;
    }

    /**
     * @return the comentario
     */
    public String getComentario()
    {
        return comentario;
    }

    /**
     * @param comentario the comentario to set
     */
    public void setComentario(String comentario)
    {
        this.comentario = comentario;
    }

    /**
     * @return the tipoSeguimiento
     */
    public TipoSeguimiento getTipoSeguimiento()
    {
        return tipoSeguimiento;
    }

    /**
     * @param tipoSeguimiento the tipoSeguimiento to set
     */
    public void setTipoSeguimiento(TipoSeguimiento tipoSeguimiento)
    {
        this.tipoSeguimiento = tipoSeguimiento;
    }

    /**
     * @return the folioConsecutivoTurnado
     */
    public String getFolioConsecutivoTurnado()
    {
        folioConsecutivoTurnado = this.folio + "-" + Constantes.NO_ACTIVO + this.consecutivo ;
        return folioConsecutivoTurnado;
    }

    /**
     * @param folioConsecutivoTurnado the folioConsecutivoTurnado to set
     */
    public void setFolioConsecutivoTurnado(String folioConsecutivoTurnado)
    {
        this.folioConsecutivoTurnado = folioConsecutivoTurnado;
    }

    /**
     * @return the instruccion
     */
    public Instruccion getInstruccion()
    {
        return instruccion;
    }

    /**
     * @param instruccion the instruccion to set
     */
    public void setInstruccion(Instruccion instruccion)
    {
        this.instruccion = instruccion;
    }

    /**
     * @return the logo
     */
    public InputStream getLogo()
    {
        return logo;
    }

    /**
     * @param logo the logo to set
     */
    public void setLogo(InputStream logo)
    {
        this.logo = logo;
    }

    /**
     * @return the ccps
     */
    public String getCcps()
    {
        return ccps;
    }

    /**
     * @param ccps the confidencial to set
     */
    public void setCcps(String ccps)
    {
        this.ccps = ccps;
    }

    /**
     * @return the confidencial
     */
    public Integer getConfidencial()
    {
        return confidencial;
    }

    /**
     * @param ccps the confidencial to set
     */
    public void setConfidencial(Integer confidencial)
    {
        this.confidencial = confidencial;
    }

    /**
     * @return the confidencial_img
     */
    public String getConfidencial_img()
    {
        return confidencial_img;
    }

    /**
     * @param ccps the confidencial_img to set
     */
    public void setConfidencial_img(String confidencial_img)
    {
        this.confidencial_img = confidencial_img;
    }

    /**
     * @return the logo_img
     */
    public String getLogo_img()
    {
        return logo_img;
    }

    /**
     * @param ccps the logo_img to set
     */
    public void setLogo_img(String logo_img)
    {
        this.logo_img = logo_img;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getInstruccionAdicional() {
        return instruccionAdicional;
    }

    public void setInstruccionAdicional(String instruccionAdicional) {
        this.instruccionAdicional = instruccionAdicional;
    }

    public String getPromotor() {
        return promotor;
    }

    public void setPromotor(String promotor) {
        this.promotor = promotor;
    }

    public String getExpediente() {
        return expediente;
    }

    public void setExpediente(String expediente) {
        this.expediente = expediente;
    }

    public String getSintesis() {
        return sintesis;
    }

    public void setSintesis(String sintesis) {
        this.sintesis = sintesis;
    }

    public String getFechaRespuesta() {
        return fechaRespuesta;
    }

    public void setFechaRespuesta(String fechaRespuesta) {
        this.fechaRespuesta = fechaRespuesta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    } 

}
