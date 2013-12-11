package mx.gob.economia.scg.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import mx.gob.economia.scg.util.Constantes;
import mx.gob.economia.scg.util.Util;
import org.apache.log4j.Logger;

public class Asunto implements java.io.Serializable
{

    // Propiedades
    private Integer idx;
    private Integer id_asunto;
    private String asunto;
    private String descripcion;    
    private String no_oficio;
    private Date fh_oficio;
    private String fh_oficioDDMMYYYY;
    private Date fh_recepcion;
    private String fh_recepcionDDMMYYYY;
    private Integer estatus;
    private String estatus_desc;
    private String estatus_pend_desc;
    private String color_aten;
    private Empleado empleado_capt = new Empleado();
    private AsuntoDetalle asunto_detalle = new AsuntoDetalle();
    private AsuntoDetalle asuntoAtencionParcial = new AsuntoDetalle();
    private List<AsuntoDetalle> asuntos_detalles = new ArrayList<AsuntoDetalle>();
    private Date fh_por_vencer;
    private List<Empleado> empleadosCcp = new ArrayList<Empleado>();
    private Empleado empleado_ccp = new Empleado();
    private String id_asunto_ref;
    private String id_asunto_ref_str;
    private Date fh_limite_asunto;
    private String fh_limite_asuntoDDMMYYYY;
    private Integer id_tema;
    private Tema tema = new Tema();
    private Tema temaCatalogo;
    private Tema subTema = new Tema();
    private Integer id_evento;
    private Evento evento = new Evento();
    private Evento eventoCatalogo;
    private Date fh_evento;
    private String fh_eventoDDMMYYYY;
    private Integer id_expediente;
    private Expediente expediente = new Expediente();
    private Integer id_instruccion;
    private Instruccion instruccion = new Instruccion();
    private String folio;
    private String contador_folio;
    private Integer id_asunto_padre;
    private Integer archivado;
    private String descripcionArchivado;
    private List<AsuntoCapturaActualizacion> actualizacionesCaptura = new ArrayList<AsuntoCapturaActualizacion>();
    private List<AsuntoCapturaActualizacion> actualizacionesDescripcion = new ArrayList<AsuntoCapturaActualizacion>();
    private Empleado empleado_revi = new Empleado();
    private String isComentarioPersonalizado;
    private Integer confidencial;// 1: Asunto confidencial, 0: asunto no confidencial
    private Integer atencionParcial;
    private TipoDocumento tipoAsunto;
    private AsuntoDetalle ultimaDetalle;
    private String antecedente;
    private Integer tipo_actualizacion;
    private String descripcionSaltos;
    private String color_confidencial;
    private Prioridad prioridad;
    private String ultimaRespuesta;
    private String ultimoEstatusArea;

    public Asunto()
    {
        this.asunto = "";
        this.asunto_detalle = new AsuntoDetalle();
        this.asuntoAtencionParcial = new AsuntoDetalle();
        this.asuntos_detalles = new ArrayList<AsuntoDetalle>();
        this.descripcion = "";
        this.descripcionSaltos = "";
        this.empleado_capt = new Empleado();
        this.estatus = Constantes.PENDIENTE;
        this.id_asunto = -1;
        this.id_asunto_padre = -1;
        this.empleadosCcp = new ArrayList<Empleado>();
        this.empleado_ccp = new Empleado();
        this.folio = "";
        this.id_tema = -1;
        this.id_evento = -1;
        this.id_expediente = -1;
        this.id_instruccion = -1;
        this.archivado = 0;
        this.id_asunto_ref_str = "";
        this.id_asunto_ref = "";
        this.actualizacionesCaptura = new ArrayList<AsuntoCapturaActualizacion>();
        this.actualizacionesDescripcion = new ArrayList<AsuntoCapturaActualizacion>();
        this.empleado_revi = new Empleado();
	this.evento = new Evento();
        this.tema = new Tema();
        this.subTema = new Tema();
        this.isComentarioPersonalizado = "";
        this.temaCatalogo = new Tema();
	this.eventoCatalogo = new Evento();
        this.confidencial = 0;
        this.atencionParcial = 0;
        this.tipoAsunto = new TipoDocumento();
        this.ultimaDetalle = new AsuntoDetalle();
        this.antecedente = "";
        this.tipo_actualizacion = 0;
        this.prioridad = new Prioridad();
        this.ultimaRespuesta = "";
        this.ultimoEstatusArea = "";

    }

    /**
     * @return the idx
     */
    public Integer getIdx()
    {
        return idx;
    }

    /**
     * @param idx
     *            the idx to set
     */
    public void setIdx(Integer idx)
    {
        this.idx = idx;
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
     * @return the asunto
     */
    public String getAsunto()
    {
        return Util.formatearCadena(asunto);
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
        descripcion = descripcion != null && descripcion.length() > 4000 ? descripcion.substring(0, 3999) : descripcion;
        return Util.formatearCadena(descripcion);
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
     * @return the no_oficio
     */
    public String getNo_oficio()
    {
        no_oficio = Util.formatearCadena(no_oficio);
        no_oficio = no_oficio != null ? no_oficio : "";
        return no_oficio;
    }

    /**
     * @param no_oficio
     *            the no_oficio to set
     */
    public void setNo_oficio(String no_oficio)
    {
        this.no_oficio = no_oficio;
    }

    /**
     * @return the fh_oficio
     */
    public Date getFh_oficio()
    {
        if (this.fh_oficioDDMMYYYY != null
                && this.fh_oficioDDMMYYYY.length() > 0)
            fh_oficio = Util.parsearFecha(this.fh_oficioDDMMYYYY);
        return fh_oficio;
    }

    /**
     * @param fh_oficio
     *            the fh_oficio to set
     */
    public void setFh_oficio(Date fh_oficio)
    {
        this.fh_oficio = fh_oficio;
    }

    /**
     * @return the fh_oficioDDMMYYYY
     */
    public String getFh_oficioDDMMYYYY()
    {
        if (this.fh_oficioDDMMYYYY == null && this.fh_oficio != null)
            fh_oficioDDMMYYYY = Util.formatearFecha(this.fh_oficio);
        return fh_oficioDDMMYYYY;
    }

    /**
     * @param fh_oficioDDMMYYYY
     *            the fh_oficioDDMMYYYY to set
     */
    public void setFh_oficioDDMMYYYY(String fh_oficioDDMMYYYY)
    {
        this.fh_oficioDDMMYYYY = fh_oficioDDMMYYYY;
    }

    /**
     * @return the fh_recepcion
     */
    public Date getFh_recepcion()
    {
        if (this.fh_recepcionDDMMYYYY != null
                && this.fh_recepcionDDMMYYYY.length() > 0)
            fh_recepcion = Util.parsearFecha(this.fh_recepcionDDMMYYYY);
        return fh_recepcion;
    }

    /**
     * @param fh_recepcion
     *            the fh_recepcion to set
     */
    public void setFh_recepcion(Date fh_recepcion)
    {
        this.fh_recepcion = fh_recepcion;
    }

    /**
     * @return the fh_recepcionDDMMYYYY
     */
    public String getFh_recepcionDDMMYYYY()
    {
        if (this.fh_recepcionDDMMYYYY == null && this.fh_recepcion != null)
            fh_recepcionDDMMYYYY = Util.formatearFecha(this.fh_recepcion);
        return fh_recepcionDDMMYYYY;
    }

    /**
     * @param fh_recepcionDDMMYYYY
     *            the fh_recepcionDDMMYYYY to set
     */
    public void setFh_recepcionDDMMYYYY(String fh_recepcionDDMMYYYY)
    {
        this.fh_recepcionDDMMYYYY = fh_recepcionDDMMYYYY;
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
     * @return the estatus_desc
     */
    public String getEstatus_desc()
    {
        if (estatus != null)
            if ((estatus.equals(Constantes.TURNADO)||estatus.equals(Constantes.PENDIENTE)) && (this.atencionParcial.equals(Constantes.ACTIVO)))
                estatus_desc = "EN TRAMITE";
            else if(estatus.equals(Constantes.PENDIENTE))
                estatus_desc = "INICIAL";
            else if (estatus.equals(Constantes.TURNADO))
                estatus_desc = "PENDIENTE";
            else if (estatus.equals(Constantes.ATENDIDO))
                estatus_desc = "ATENDIDO";
            else if (estatus.equals(Constantes.FINALIZADO))
                estatus_desc = "CONCLUIDO";
            else if (estatus.equals(Constantes.EN_TIEMPO))
                estatus_desc = "EN TIEMPO";
            else if (estatus.equals(Constantes.POR_VENCER))
                estatus_desc = "POR VENCER";
            else if (estatus.equals(Constantes.VENCIDO))
                estatus_desc = "VENCIDO";
            else if (estatus.equals(Constantes.EN_CAPTURA))
                estatus_desc = "EN CAPTURA";
            else if (estatus.equals(Constantes.EN_REVISION))
                estatus_desc = "EN REVISION";
            else if (estatus.equals(Constantes.ELIMINADO))
                estatus_desc = "ELIMINADO";
            else if (estatus.equals(Constantes.ARCHIVADO))
                estatus_desc = "ARCHIVADO";
            else if (estatus.equals(Constantes.REVISADO_CAPTURA))
                estatus_desc = "REVISADO CAPTURA";
            else if (estatus.equals(Constantes.EN_SUPERVISION_CAPTURA))
                estatus_desc = "EN SUPERVISION CAPTURA";
            else if (estatus.equals(Constantes.SUPERVISADO))
                estatus_desc = "SUPERVISADO";
            //else if (estatus.equals(Constantes.CONFIDENCIAL))
              //  estatus_desc = "CONFIDENCIAL"; AGG Confidencial -> booleano 20111109
        return estatus_desc;
    }

    /**
     * @param estatus_desc
     *            the estatus_desc to set
     */
    public void setEstatus_desc(String estatus_desc)
    {
        this.estatus_desc = estatus_desc;
    }

    /**
     * @return the estatus_pend_desc
     */
    public String getEstatus_pend_desc()
    {
        if (estatus != null)
            if (estatus.equals(Constantes.PENDIENTE))
                estatus_pend_desc = "PENDIENTE";
            else if (estatus.equals(Constantes.ATENDIDO))
                estatus_pend_desc = "PARA VO.BO.";
            else if (estatus.equals(Constantes.TURNADO))
                estatus_pend_desc = "POR ATENDER";
            else if (estatus.equals(Constantes.FINALIZADO))
                estatus_pend_desc = "CONCLUIDO";
        return estatus_pend_desc;
    }

    /**
     * @param estatus_pend_desc
     *            the estatus_pend_desc to set
     */
    public void setEstatus_pend_desc(String estatus_pend_desc)
    {
        this.estatus_pend_desc = estatus_pend_desc;
    }

    /**
     * @return the color_aten
     */
    public String getColor_aten()
    {
        if (asunto_detalle != null && asunto_detalle.getFh_limite() != null
                && this.getFh_por_vencer() != null)
        {

            Calendar cal_actual = Calendar.getInstance();
            cal_actual.set(Calendar.HOUR_OF_DAY, 0);
            cal_actual.set(Calendar.MINUTE, 0);
            cal_actual.set(Calendar.SECOND, 0);
            cal_actual.set(Calendar.MILLISECOND, 0);

            color_aten = "verde";
            if (asunto_detalle.getFh_limite().getTime() <= cal_actual.getTime().getTime())
                color_aten = "rojo";
            else
                if (this.getFh_por_vencer().getTime() <= cal_actual.getTime().getTime())
                    color_aten = "amarillo";
        }
        if (asunto_detalle.getEstatus().equals(Constantes.ATENDIDO) || asunto_detalle.getEstatus().equals(Constantes.FINALIZADO))
            return "";
        return color_aten;
    }

    /**
     * @param color_aten
     *            the color_aten to set
     */
    public void setColor_aten(String color_aten)
    {
        this.color_aten = color_aten;
    }

    /**
     * @return the color_aten
     */
    public String getColor_confidencial()
    {
        if (this.confidencial==1&&!this.estatus.equals(7))
            return "confidencial"; //color definido en jsp/css/area.css
        else
            return "";
    }

    /**
     * @param color_aten
     *            the color_aten to set
     */
    public void setColor_confidencial(String color_confidencial)
    {
        this.color_confidencial = color_confidencial;
    }

    /**
     * @return the empleado_capt
     */
    public Empleado getEmpleado_capt()
    {
        return empleado_capt;
    }

    /**
     * @param empleado_capt
     *            the empleado_capt to set
     */
    public void setEmpleado_capt(Empleado empleado_capt)
    {
        this.empleado_capt = empleado_capt;
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
     * @return the asuntos_detalles
     */
    public List<AsuntoDetalle> getAsuntos_detalles()
    {
        return asuntos_detalles;
    }

    /**
     * @return the fh_por_vencer
     */
    public Date getFh_por_vencer()
    {
        return fh_por_vencer;
    }

    /**
     * @param fh_por_vencer
     *            the fh_por_vencer to set
     */
    public void setFh_por_vencer(Date fh_por_vencer)
    {
        this.fh_por_vencer = fh_por_vencer;
    }

    /**
     * @param asuntos_detalles
     *            the asuntos_detalles to set
     */
    public void setAsuntos_detalles(List<AsuntoDetalle> asuntos_detalles)
    {
        this.asuntos_detalles = asuntos_detalles;
    }

    /**
     * @return the empleadosCcp
     */
    public List<Empleado> getEmpleadosCcp()
    {
        return empleadosCcp;
    }

    /**
     * @param empleadosCcp
     *            the empleadosCcp to set
     */
    public void setEmpleadosCcp(List<Empleado> empleadosCcp)
    {
        this.empleadosCcp = empleadosCcp;
    }

    /**
     * @return the empleado_ccp
     */
    public Empleado getEmpleado_ccp()
    {
        return empleado_ccp;
    }

    /**
     * @param empleado_ccp
     *            the empleado_ccp to set
     */
    public void setEmpleado_ccp(Empleado empleado_ccp)
    {
        this.empleado_ccp = empleado_ccp;
    }

    /**
     * @return the id_asunto_ref_str
     */
    public String getId_asunto_ref_str()
    {
        if (this.getId_asunto_ref() == null && this.getId_asunto_ref() != null)
            id_asunto_ref_str = this.getId_asunto_ref() + "";
        return id_asunto_ref_str != null ? id_asunto_ref_str.toUpperCase() : "";
    }

    /**
     * @param id_asunto_ref_str
     *            the id_asunto_ref_str to set
     */
    public void setId_asunto_ref_str(String id_asunto_ref_str)
    {
        this.id_asunto_ref_str = id_asunto_ref_str;
    }

    /**
     * @return the fh_limite_asunto
     */
    public Date getFh_limite_asunto()
    {
        if (this.fh_limite_asuntoDDMMYYYY != null
                && this.fh_limite_asuntoDDMMYYYY.length() > 0)
            fh_limite_asunto = Util.parsearFecha(this.fh_limite_asuntoDDMMYYYY);
        return fh_limite_asunto;
    }

    /**
     * @param fh_limite_asunto
     *            the fh_limite_asunto to set
     */
    public void setFh_limite_asunto(Date fh_limite_asunto)
    {
        this.fh_limite_asunto = fh_limite_asunto;
    }

    /**
     * @return the fh_limite_asuntoDDMMYYYY
     */
    public String getFh_limite_asuntoDDMMYYYY()
    {
        if (this.fh_limite_asuntoDDMMYYYY == null
                && this.fh_limite_asunto != null)
            fh_limite_asuntoDDMMYYYY = Util.formatearFecha(this.fh_limite_asunto);
        return fh_limite_asuntoDDMMYYYY;
    }

    /**
     * @param fh_limite_asuntoDDMMYYYY
     *            the fh_limite_asuntoDDMMYYYY to set
     */
    public void setFh_limite_asuntoDDMMYYYY(String fh_limite_asuntoDDMMYYYY)
    {
        this.fh_limite_asuntoDDMMYYYY = fh_limite_asuntoDDMMYYYY;
    }

    /**
     * @return the id_tema
     */
    public Integer getId_tema()
    {
        return id_tema;
    }

    /**
     * @param id_tema
     *            the id_tema to set
     */
    public void setId_tema(Integer id_tema)
    {
        this.id_tema = id_tema;
    }

    /**
     * @return the tema
     */
    public Tema getTema()
    {
        return tema;
    }

    /**
     * @param tema
     *            the tema to set
     */
    public void setTema(Tema tema)
    {
        this.tema = tema;
    }

    /**
     * @return the fh_evento
     */
    public Date getFh_evento()
    {
        if (this.fh_eventoDDMMYYYY != null
                && this.fh_eventoDDMMYYYY.length() > 0)
            fh_evento = Util.formatearFechaToEvento(this.fh_eventoDDMMYYYY);
        else
            fh_evento = null;
        return fh_evento;
    }

    /**
     * @param fh_evento
     *            the fh_evento to set
     */
    public void setFh_evento(Date fh_evento)
    {
        this.fh_evento = fh_evento;
    }

    /**
     * @return the fh_eventoDDMMYYYY
     */
    public String getFh_eventoDDMMYYYY()
    {
        if (this.fh_eventoDDMMYYYY == null
                && this.fh_evento != null)
            fh_eventoDDMMYYYY = Util.formatearFechaEvento(this.fh_evento);
        return fh_eventoDDMMYYYY;
    }

    /**
     * @param fh_eventoDDMMYYYY
     *            the fh_eventoDDMMYYYY to set
     */
    public void setfh_eventoDDMMYYYY(String fh_eventoDDMMYYYY)
    {
        this.fh_eventoDDMMYYYY = fh_eventoDDMMYYYY;
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
     * @return the expediente
     */
    public Expediente getExpediente()
    {
        return expediente;
    }

    /**
     * @param expediente
     *            the expediente to set
     */
    public void setExpediente(Expediente expediente)
    {
        this.expediente = expediente;
    }

    /**
     * @return the id_instruccion
     */
    public Integer getId_instruccion()
    {
        return id_instruccion;
    }

    /**
     * @param id_instruccion
     *            the id_instruccion to set
     */
    public void setId_instruccion(Integer id_instruccion)
    {
        this.id_instruccion = id_instruccion;
    }

    /**
     * @return the instruccion
     */
    public Instruccion getInstruccion()
    {
        return instruccion;
    }

    /**
     * @param instruccion
     *            the instruccion to set
     */
    public void setInstruccion(Instruccion instruccion)
    {
        this.instruccion = instruccion;
    }

    /**
     * @return the folio
     */
    public String getFolio()
    {
        return folio;
    }

    /**
     * @param folio
     *            the folio to set
     */
    public void setFolio(String folio)
    {
        this.folio = folio;
    }

    /**
     * @return the contador_folio
     */
    public String getContador_folio()
    {
        return contador_folio;
    }

    /**
     * @param contador_folio
     *            the contador_folio to set
     */
    public void setContador_folio(String contador_folio)
    {
        this.contador_folio = contador_folio;
    }

    /**
     * @return the id_asunto_padre
     */
    public Integer getId_asunto_padre()
    {
        return id_asunto_padre;
    }

    /**
     * @param id_asunto_padre
     *            the id_asunto_padre to set
     */
    public void setId_asunto_padre(Integer id_asunto_padre)
    {
        this.id_asunto_padre = id_asunto_padre;
    }

    /**
     * @return the archivado
     */
    public Integer getArchivado()
    {
        return archivado;
    }

    /**
     * @param archivado
     *            the archivado to set
     */
    public void setArchivado(Integer archivado)
    {
        this.archivado = archivado;
    }

    /**
     * @return the actualizacionesCaptura
     */
    public List<AsuntoCapturaActualizacion> getActualizacionesCaptura()
    {
        return actualizacionesCaptura;
    }

    /**
     * @param actualizacionesCaptura
     *            the actualizacionesCaptura to set
     */
    public void setActualizacionesCaptura(
            List<AsuntoCapturaActualizacion> actualizacionesCaptura)
    {
        this.actualizacionesCaptura = actualizacionesCaptura;
    }

    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Asunto other = (Asunto) obj;
        if (id_asunto != other.id_asunto)
            return false;
        return true;
    }

    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + id_asunto;
        return result;
    }

    public static void asignaIdx(List<Asunto> asuntos)
    {
        for (int i = 0; i < asuntos.size(); i++)
        {
            asuntos.get(i).setIdx(i);
        }
    }

    /**
     * @return the subTema
     */
    public Tema getSubTema()
    {
        return subTema;
    }

    /**
     * @param subTema the subTema to set
     */
    public void setSubTema(Tema subTema)
    {
        Logger log = Logger.getLogger(this.getClass());
        log.error("se agrega el subtema" + subTema.getTema());
        this.subTema = subTema;
    }

    /**
     * @return the id_asunto_ref
     */
    public String getId_asunto_ref()
    {
        return id_asunto_ref != null ? id_asunto_ref.toUpperCase() : "";
    }

    /**
     * @param id_asunto_ref the id_asunto_ref to set
     */
    public void setId_asunto_ref(String id_asunto_ref)
    {
        this.id_asunto_ref = id_asunto_ref;
    }

    /**
     * @return the empleado_revi
     */
    public Empleado getEmpleado_revi()
    {
        return empleado_revi;
    }

    /**
     * @param empleado_revi the empleado_revi to set
     */
    public void setEmpleado_revi(Empleado empleado_revi)
    {
        this.empleado_revi = empleado_revi;
    }

    public String getIsComentarioPersonalizado()
    {
        return isComentarioPersonalizado;
    }

    public void setIsComentarioPersonalizado(String isComentarioPersonalizado)
    {
        this.isComentarioPersonalizado = isComentarioPersonalizado;
    }

    /**
     * @return the temaCatalogo
     */
    public Tema getTemaCatalogo()
    {
        return temaCatalogo;
    }

    /**
     * @param temaCatalogo the temaCatalogo to set
     */
    public void setTemaCatalogo(Tema temaCatalogo)
    {
        this.temaCatalogo = temaCatalogo;
    }

    /**
     * @return the confidencial
     */
    public Integer getConfidencial()
    {
        return confidencial;
    }

    /**
     * @param confidencial the confidencial to set
     */
    public void setConfidencial(Integer confidencial)
    {
        this.confidencial = confidencial;
    }

    /**
     * @return the descripcionArchivado
     */
    public String getDescripcionArchivado()
    {
        descripcionArchivado = this.archivado == 0 ? "No" : "SI";
        return descripcionArchivado;
    }

    /**
     * @param descripcionArchivado the descripcionArchivado to set
     */
    public void setDescripcionArchivado(String descripcionArchivado)
    {
        this.descripcionArchivado = descripcionArchivado;
    }

    /**
     * @return the tipoAsunto
     */
    public TipoDocumento getTipoAsunto()
    {
        return tipoAsunto;
    }

    /**
     * @param tipoAsunto the tipoAsunto to set
     */
    public void setTipoAsunto(TipoDocumento tipoAsunto)
    {
        this.tipoAsunto = tipoAsunto;
    }

    /**
     * @return the ultimaDetalle
     */
    public AsuntoDetalle getUltimaDetalle()
    {
        return ultimaDetalle;
    }

    /**
     * @param ultimaDetalle the ultimaDetalle to set
     */
    public void setUltimaDetalle(AsuntoDetalle ultimaDetalle)
    {
        this.ultimaDetalle = ultimaDetalle;
    }

    /**
     * @return the antecedente
     */
    public String getAntecedente()
    {
        return antecedente;
    }

    /**
     * @param antecedente to set
     */
    public void setAntecedente(String antecedente)
    {
        this.antecedente = antecedente;
    }

    public Integer getTipo_actualizacion() {
        return tipo_actualizacion;
    }

    public void setTipo_actualizacion(Integer tipo_actualizacion) {
        this.tipo_actualizacion = tipo_actualizacion;
    }

    public List<AsuntoCapturaActualizacion> getActualizacionesDescripcion() {
        return actualizacionesDescripcion;
    }

    public void setActualizacionesDescripcion(List<AsuntoCapturaActualizacion> actualizacionesDescripcion) {
        this.actualizacionesDescripcion = actualizacionesDescripcion;
    }
	/**
     * @return the id_tema
     */
    public Integer getId_evento()
    {
        return id_evento;
    }

    /**
     * @param id_evento
     *            the id_evento to set
     */
    public void setId_evento(Integer id_evento)
    {
        this.id_evento = id_evento;
    }

    /**
     * @return the evento
     */
    public Evento getEvento()
    {
        return evento;
    }

    /**
     * @param evento
     *            the evento to set
     */
    public void setEvento(Evento evento)
    {
        this.evento = evento;
    }
    /**
     * @return the eventoCatalogo
     */
    public Evento getEventoCatalogo()
    {
        return eventoCatalogo;
    }

    /**
     * @param eventoCatalogo the eventoCatalogo to set
     */
    public void setEventoCatalogo(Evento eventoCatalogo)
    {
        this.eventoCatalogo = eventoCatalogo;
    }
    /**
     * @return the descripcionSaltos
     */
    public String getDescripcionSaltos()
    {
        descripcionSaltos = this.descripcion.replaceAll("\n", "<br>");
        return descripcionSaltos;
    }

    public Integer getAtencionParcial() {
        return atencionParcial;
    }

    public void setAtencionParcial(Integer atencionParcial) {
        this.atencionParcial = atencionParcial;
    }

    public AsuntoDetalle getAsuntoAtencionParcial() {
        return asuntoAtencionParcial;
    }

    public void setAsuntoAtencionParcial(AsuntoDetalle asuntoAtencionParcial) {
        this.asuntoAtencionParcial = asuntoAtencionParcial;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

    public String getUltimaRespuesta() {
        return ultimaRespuesta;
    }

    public void setUltimaRespuesta(String ultimaRespuesta) {
        this.ultimaRespuesta = ultimaRespuesta;
    }

    public String getUltimoEstatusArea() {
        return ultimoEstatusArea;
    }

    public void setUltimoEstatusArea(String ultimoEstatusArea) {
        this.ultimoEstatusArea = ultimoEstatusArea;
    }
}
