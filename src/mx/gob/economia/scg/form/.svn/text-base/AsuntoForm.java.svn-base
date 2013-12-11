package mx.gob.economia.scg.form;

import java.util.ArrayList;
import java.util.List;
import mx.gob.economia.scg.model.Area;

import mx.gob.economia.scg.model.Asunto;
import mx.gob.economia.scg.model.AsuntoDetalle;
import mx.gob.economia.scg.model.Empleado;
import mx.gob.economia.scg.model.Expediente;
import mx.gob.economia.scg.model.Firma;
import mx.gob.economia.scg.model.Instruccion;
import mx.gob.economia.scg.model.Tema;
import mx.gob.economia.scg.model.Evento;
import mx.gob.economia.scg.model.TipoSeguimiento;

import org.apache.struts.action.ActionForm;

public class AsuntoForm extends ActionForm
{

    /**
     *
     */
    private static final long serialVersionUID = -2452762297043411998L;
    // Propiedades
    private Integer id_asunto;
    private Asunto asunto = new Asunto();
    private List<Empleado> empleadosRemitente = new ArrayList<Empleado>();
    private List<Empleado> empleadosDestinatario = new ArrayList<Empleado>();
    private List<Empleado> empleadosConCopiaPara = new ArrayList<Empleado>();
    private List<Expediente> expedientes = new ArrayList<Expediente>();
    private List<Tema> temas = new ArrayList<Tema>();
    private List<Tema> subtemas = new ArrayList<Tema>();
    private List<Evento> eventos = new ArrayList<Evento>();
    private List<Area> areasDependientes = new ArrayList<Area>();
    private List<Instruccion> instrucciones = new ArrayList<Instruccion>();
    private AsuntoDetalle asunto_detalle = new AsuntoDetalle();
    private Firma firma = new Firma();
    private String enviarcorreo;
    private Empleado empleadoCaptura;
    private List<TipoSeguimiento> tiposSeguimiento = new ArrayList<TipoSeguimiento>();
    private List<Empleado> firmantesVolante = new ArrayList<Empleado>();
    private List<Empleado> firmantesVolanteRol = new ArrayList<Empleado>();
    private String bitacora = "";
    private String expedienteAutoc =""; //campo para autocomplete
 
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
    public Asunto getAsunto()
    {
        return asunto;
    }

    /**
     * @param asunto
     *            the asunto to set
     */
    public void setAsunto(Asunto asunto)
    {
        this.asunto = asunto;
    }

    /**
     * @return the empleadosRemitente
     */
    public List<Empleado> getEmpleadosRemitente()
    {
        return empleadosRemitente;
    }

    /**
     * @param empleadosRemitente
     *            the empleadosRemitente to set
     */
    public void setEmpleadosRemitente(List<Empleado> empleadosRemitente)
    {
        this.empleadosRemitente = empleadosRemitente;
    }

    /**
     * @return the empleadosDestinatario
     */
    public List<Empleado> getEmpleadosDestinatario()
    {
        return empleadosDestinatario;
    }

    /**
     * @param empleadosDestinatario
     *            the empleadosDestinatario to set
     */
    public void setEmpleadosDestinatario(List<Empleado> empleadosDestinatario)
    {
        this.empleadosDestinatario = empleadosDestinatario;
    }

    /**
     * @return the empleadosConCopiaPara
     */
    public List<Empleado> getEmpleadosConCopiaPara()
    {
        return empleadosConCopiaPara;
    }

    /**
     * @param empleadosConCopiaPara
     *            the empleadosConCopiaPara to set
     */
    public void setEmpleadosConCopiaPara(List<Empleado> empleadosConCopiaPara)
    {
        this.empleadosConCopiaPara = empleadosConCopiaPara;
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
     * @return the firma
     */
    public Firma getFirma()
    {
        return firma;
    }

    /**
     * @param firma the firma to set
     */
    public void setFirma(Firma firma)
    {
        this.firma = firma;
    }

    /**
     * @return the expedientes
     */
    public List<Expediente> getExpedientes()
    {
        return expedientes;
    }

    /**
     * @param expedientes the expedientes to set
     */
    public void setExpedientes(List<Expediente> expedientes)
    {
        this.expedientes = expedientes;
    }

    /**
     * @return the temas
     */
    public List<Tema> getTemas()
    {
        return temas;
    }

    /**
     * @param temas the temas to set
     */
    public void setTemas(List<Tema> temas)
    {
        this.temas = temas;
    }

    /**
     * @return the instrucciones
     */
    public List<Instruccion> getInstrucciones()
    {
        return instrucciones;
    }

    /**
     * @param instrucciones the instrucciones to set
     */
    public void setInstrucciones(List<Instruccion> instrucciones)
    {
        this.instrucciones = instrucciones;
    }

    /**
     * @return the areasDependientes
     */
    public List<Area> getAreasDependientes()
    {
        return areasDependientes;
    }

    /**
     * @param areasDependientes the areasDependientes to set
     */
    public void setAreasDependientes(List<Area> areasDependientes)
    {
        this.areasDependientes = areasDependientes;
    }

    /**
     * @return the enviarcorreo
     */
    public String getEnviarcorreo()
    {
        return enviarcorreo;
    }

    /**
     * @param enviarcorreo the enviarcorreo to set
     */
    public void setEnviarcorreo(String enviarcorreo)
    {
        this.enviarcorreo = enviarcorreo;
    }

    /**
     * @return the empleadoCaptura
     */
    public Empleado getEmpleadoCaptura()
    {
        return empleadoCaptura;
    }

    /**
     * @param empleadoCaptura the empleadoCaptura to set
     */
    public void setEmpleadoCaptura(Empleado empleadoCaptura)
    {
        this.empleadoCaptura = empleadoCaptura;
    }

    /**
     * @return the subtemas
     */
    public List<Tema> getSubtemas()
    {
        return subtemas;
    }

    /**
     * @param subtemas the subtemas to set
     */
    public void setSubtemas(List<Tema> subtemas)
    {
        this.subtemas = subtemas;
    }

    /**
     * @return the tiposSeguimiento
     */
    public List<TipoSeguimiento> getTiposSeguimiento()
    {
        return tiposSeguimiento;
    }

    /**
     * @param tiposSeguimiento the tiposSeguimiento to set
     */
    public void setTiposSeguimiento(List<TipoSeguimiento> tiposSeguimiento)
    {
        this.tiposSeguimiento = tiposSeguimiento;
    }

    /**
     * @return the firmantesVolante
     */
    public List<Empleado> getFirmantesVolante()
    {
        return firmantesVolante;
    }

    /**
     * @return the firmantesVolanteRol
     */
    public List<Empleado> getFirmantesVolanteRol()
    {
        return firmantesVolanteRol;
    }

    /**
     * @param firmantesVolante the firmantesVolante to set
     */
    public void setFirmantesVolante(List<Empleado> firmantesVolante)
    {
        this.firmantesVolante = firmantesVolante;
    }

    /**
     * @param firmantesVolante the firmantesVolante to set
     */
    public void setFirmantesVolanteRol(List<Empleado> firmantesVolanteRol)
    {
        this.firmantesVolanteRol = firmantesVolanteRol;
    }

    /**
     * @return the eventos
     */
    public List<Evento> getEventos()
    {
        return eventos;
    }

    /**
     * @param temas the temas to set
     */
    public void setEventos(List<Evento> eventos)
    {
        this.eventos = eventos;
    }

    public String getBitacora() {
        return bitacora;
    }

    public void setBitacora(String bitacora) {
        this.bitacora = bitacora;
    }

    public String getExpedienteAutoc() {
        return expedienteAutoc;
    }

    public void setExpedienteAutoc(String expedienteAutoc) {
        this.expedienteAutoc = expedienteAutoc;
    }
    
}
