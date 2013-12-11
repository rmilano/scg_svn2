package mx.gob.economia.scg.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.gob.economia.scg.util.Constantes;
import mx.gob.economia.scg.util.Util;

public class AsuntoDetalle implements Serializable {

    // Propiedades
    private Integer id_asunto_detalle;
    private Integer id_asunto;
    private Date fh_lectura;
    private Date fh_registro;
    private String fh_registroDDMMYYYY;
    private Date fh_limite;
    private String fh_limiteDDMMYYYY;
    private Empleado empleado_remi = new Empleado();
    private Empleado empleado_dest = new Empleado();
    private Empleado empleado_revi = new Empleado();
    private List<Empleado> empleadosDest = new ArrayList<Empleado>();
    private Empleado empleado_dest_ini = new Empleado();
    private String comentario;
    private String comentarioSaltos;
    private String ip;
    private Integer estatus;
    private String estatus_desc;
    private List<Documento> documentos = new ArrayList<Documento>();
    private Documento documento = new Documento();
    private List<Empleado> empleadosCcp = new ArrayList<Empleado>();
    private List<Empleado> empleadosCcpDetalle = new ArrayList<Empleado>();
    private Empleado empleado_ccp = new Empleado();
    private List<Empleado> empleadosRecep = new ArrayList<Empleado>();
    Prioridad prioridad = new Prioridad();
    private Integer id_concentrado;
    private List<Asunto> asuntos_asociados = new ArrayList<Asunto>();
    private List<String> ids_asunto = new ArrayList<String>();
    private TipoSeguimiento tipoSeguimiento;
    private Empleado empleadoFirmanteVolante;
    private Integer id_instruccion;
    private Instruccion instruccion = new Instruccion();
    private String instruccionAdicional;

    public AsuntoDetalle() {
        this.documento = new Documento();
        this.documentos = new ArrayList<Documento>();
        this.empleado_dest = new Empleado();
        this.empleado_remi = new Empleado();
        this.empleadosCcp = new ArrayList<Empleado>();
        this.empleadosCcpDetalle = new ArrayList<Empleado>();
        this.empleado_ccp = new Empleado();
        this.empleadosRecep = new ArrayList<Empleado>();
        this.prioridad = new Prioridad();
        this.fh_limite = new Date();
        this.asuntos_asociados = new ArrayList<Asunto>();
        this.empleado_revi = new Empleado();
        this.tipoSeguimiento = new TipoSeguimiento();
        this.empleadoFirmanteVolante = new Empleado();
        this.instruccionAdicional ="";
    }

    /**
     * @return the id_asunto_detalle
     */
    public Integer getId_asunto_detalle() {
        return id_asunto_detalle;
    }

    /**
     * @param id_asunto_detalle
     *            the id_asunto_detalle to set
     */
    public void setId_asunto_detalle(Integer id_asunto_detalle) {
        this.id_asunto_detalle = id_asunto_detalle;
    }

    /**
     * @return the id_asunto
     */
    public Integer getId_asunto() {
        return id_asunto;
    }

    /**
     * @param id_asunto
     *            the id_asunto to set
     */
    public void setId_asunto(Integer id_asunto) {
        this.id_asunto = id_asunto;
    }

    /**
     * @return the fh_lectura
     */
    public Date getFh_lectura() {
        return fh_lectura;
    }

    /**
     * @param fh_lectura
     *            the fh_lectura to set
     */
    public void setFh_lectura(Date fh_lectura) {
        this.fh_lectura = fh_lectura;
    }

    /**
     * @return the fh_registro
     */
    public Date getFh_registro() {
        if (this.fh_registroDDMMYYYY != null
                && this.fh_registroDDMMYYYY.length() > 0) {
            fh_registro = Util.parsearFecha(this.fh_registroDDMMYYYY);
        }
        return fh_registro;
    }

    /**
     * @param fh_registro
     *            the fh_registro to set
     */
    public void setFh_registro(Date fh_registro) {
        this.fh_registro = fh_registro;
    }

    /**
     * @return the fh_registroDDMMYYYY
     */
    public String getFh_registroDDMMYYYY() {
        if (this.fh_registroDDMMYYYY == null && this.fh_registro != null) {
            fh_registroDDMMYYYY = Util.formatearFecha(this.fh_registro);
        }
        return fh_registroDDMMYYYY;
    }

    /**
     * @param fh_registroDDMMYYYY
     *            the fh_registroDDMMYYYY to set
     */
    public void setFh_registroDDMMYYYY(String fh_registroDDMMYYYY) {
        this.fh_registroDDMMYYYY = fh_registroDDMMYYYY;
    }

    /**
     * @return the fh_limite
     */
    public Date getFh_limite() {
        if (this.fh_limiteDDMMYYYY != null
                && this.fh_limiteDDMMYYYY.length() > 0) {
            fh_limite = Util.parsearFecha(this.fh_limiteDDMMYYYY);
        }
        return fh_limite;
    }

    /**
     * @param fh_limite
     *            the fh_limite to set
     */
    public void setFh_limite(Date fh_limite) {
        this.fh_limite = fh_limite;
    }

    /**
     * @return the fh_limiteDDMMYYYY
     */
    public String getFh_limiteDDMMYYYY() {
        if (this.fh_limiteDDMMYYYY == null && this.fh_limite != null) {
            fh_limiteDDMMYYYY = Util.formatearFecha(this.fh_limite);
        }
        return fh_limiteDDMMYYYY;
    }

    /**
     * @param fh_limiteDDMMYYYY
     *            the fh_limiteDDMMYYYY to set
     */
    public void setFh_limiteDDMMYYYY(String fh_limiteDDMMYYYY) {
        this.fh_limiteDDMMYYYY = fh_limiteDDMMYYYY;
    }

    /**
     * @return the empleado_remi
     */
    public Empleado getEmpleado_remi() {
        return empleado_remi;
    }

    /**
     * @param empleado_remi
     *            the empleado_remi to set
     */
    public void setEmpleado_remi(Empleado empleado_remi) {
        this.empleado_remi = empleado_remi;
    }

    /**
     * @return the empleado_dest
     */
    public Empleado getEmpleado_dest() {
        return empleado_dest;
    }

    /**
     * @param empleado_dest
     *            the empleado_dest to set
     */
    public void setEmpleado_dest(Empleado empleado_dest) {
        this.empleado_dest = empleado_dest;
    }

    /**
     * @return the comentario
     */
    public String getComentario() {
        comentario = comentario!=null && comentario.length() > 4000 ? comentario.substring(0, 3999):comentario;
        return Util.formatearCadena(comentario);  
    }

    /**
     * @param comentario
     *            the comentario to set
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    /**
     *  the comentarioSaltos
     */
    
    public void setComentarioSaltos(String comentarioSaltos) {
        this.comentarioSaltos=comentarioSaltos;
    }

    public String getComentarioSaltos() {
        comentarioSaltos = this.comentario.replaceAll("\n", "<br>");
        return comentarioSaltos;
    }
    /**
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip
     *            the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return the estatus
     */
    public Integer getEstatus() {
        return estatus;
    }

    /**
     * @param estatus
     *            the estatus to set
     */
    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }

    /**
     * @return the estatus_desc
     */
    public String getEstatus_desc() {
        if (estatus != null) {
            if (estatus.equals(Constantes.PENDIENTE)) {
                estatus_desc = "INICIAL";
            } else if (estatus.equals(Constantes.TURNADO)) {
                estatus_desc = "PENDIENTE";
            } else if (estatus.equals(Constantes.ATENDIDO)) {
                estatus_desc = "ATENDIDO";
            } else if (estatus.equals(Constantes.FINALIZADO)) {
                estatus_desc = "CONCLUIDO";
            } else if (estatus.equals(Constantes.EN_TIEMPO)) {
                estatus_desc = "EN TIEMPO";
            } else if (estatus.equals(Constantes.POR_VENCER)) {
                estatus_desc = "POR VENCER";
            } else if (estatus.equals(Constantes.VENCIDO)) {
                estatus_desc = "VENCIDO";
            } else if (estatus.equals(Constantes.EN_CAPTURA)) {
                estatus_desc = "EN CAPTURA";
            } else if (estatus.equals(Constantes.EN_REVISION)) {
                estatus_desc = "EN REVISION";
            } else if (estatus.equals(Constantes.ELIMINADO)) {
                estatus_desc = "ELIMINADO";
            } else if (estatus.equals(Constantes.ARCHIVADO)) {
                estatus_desc = "ARCHIVADO";
            } else if (estatus.equals(Constantes.REVISADO_CAPTURA)) {
                estatus_desc = "REVISADO CAPTURA";
            } else if (estatus.equals(Constantes.EN_SUPERVISION_CAPTURA)) {
                estatus_desc = "EN SUPERVISION CAPTURA";
            } else if (estatus.equals(Constantes.SUPERVISADO)) {
                estatus_desc = "SUPERVISADO";
            }
             //else if (estatus.equals(Constantes.CONFIDENCIAL)) {
               // estatus_desc = "CONFIDENCIAL"; AGG Confidencial -> booleano 20111109
            //}
        }
        return estatus_desc;
    }

    /**
     * @param estatus_desc
     *            the estatus_desc to set
     */
    public void setEstatus_desc(String estatus_desc) {
        this.estatus_desc = estatus_desc;
    }

    /**
     * @return the documentos
     */
    public List<Documento> getDocumentos() {
        return documentos;
    }

    /**
     * @param documentos
     *            the documentos to set
     */
    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }

    /**
     * @return the documento
     */
    public Documento getDocumento() {
        return documento;
    }

    /**
     * @param documento
     *            the documento to set
     */
    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    /**
     * @return the empleadosCcp
     */
    public List<Empleado> getEmpleadosCcp() {
        return empleadosCcp;
    }

    /**
     * @param empleadosCcp
     *            the empleadosCcp to set
     */
    public void setEmpleadosCcp(List<Empleado> empleadosCcp) {
        this.empleadosCcp = empleadosCcp;
    }

    /**
     * @return the empleadosCcpDetalle
     */
    public List<Empleado> getEmpleadosCcpDetalle() {
        return empleadosCcpDetalle;
    }

    /**
     * @param empleadosCcpDetalle
     *            the empleadosCcpDetalle to set
     */
    public void setEmpleadosCcpDetalle(List<Empleado> empleadosCcpDetalle) {
        this.empleadosCcpDetalle = empleadosCcpDetalle;
    }

    /**
     * @return the empleado_ccp
     */
    public Empleado getEmpleado_ccp() {
        return empleado_ccp;
    }

    /**
     * @param empleado_ccp
     *            the empleado_ccp to set
     */
    public void setEmpleado_ccp(Empleado empleado_ccp) {
        this.empleado_ccp = empleado_ccp;
    }

    /**
     * @return the empleado_dest_ini
     */
    public Empleado getEmpleado_dest_ini() {
        return empleado_dest_ini;
    }

    /**
     * @param empleado_dest_ini
     *            the empleado_dest_ini to set
     */
    public void setEmpleado_dest_ini(Empleado empleado_dest_ini) {
        this.empleado_dest_ini = empleado_dest_ini;
    }

    /**
     * @return the empleadosRecep
     */
    public List<Empleado> getEmpleadosRecep() {
        return empleadosRecep;
    }

    /**
     * @param empleadosRecep
     *            the empleadosRecep to set
     */
    public void setEmpleadosRecep(List<Empleado> empleadosRecep) {
        this.empleadosRecep = empleadosRecep;
    }

    /**
     * @return the prioridad
     */
    public Prioridad getPrioridad() {
        return prioridad;
    }

    /**
     * @param prioridad
     *            the prioridad to set
     */
    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

    /**
     * @return the empleadosDest
     */
    public List<Empleado> getEmpleadosDest() {
        return empleadosDest;
    }

    /**
     * @param empleadosDest the empleadosDest to set
     */
    public void setEmpleadosDest(List<Empleado> empleadosDest) {
        this.empleadosDest = empleadosDest;
    }

    /**
     * @return the id_concentrado
     */
    public Integer getId_concentrado() {
        return id_concentrado;
    }

    /**
     * @param id_concentrado the id_concentrado to set
     */
    public void setId_concentrado(Integer id_concentrado) {
        this.id_concentrado = id_concentrado;
    }

    /**
     * @return the asuntos_asociados
     */
    public List<Asunto> getAsuntos_asociados() {
        return asuntos_asociados;
    }

    /**
     * @param asuntos_asociados the asuntos_asociados to set
     */
    public void setAsuntos_asociados(List<Asunto> asuntos_asociados) {
        this.asuntos_asociados = asuntos_asociados;
    }

    /**
     * @return the ids_asunto
     */
    public List<String> getIds_asunto() {
        return ids_asunto;
    }

    /**
     * @param ids_asunto the ids_asunto to set
     */
    public void setIds_asunto(List<String> ids_asunto) {
        this.ids_asunto = ids_asunto;
    }

    /**
     * @return the empleado_revi
     */
    public Empleado getEmpleado_revi() {
        return empleado_revi;
    }

    /**
     * @param empleado_revi the empleado_revi to set
     */
    public void setEmpleado_revi(Empleado empleado_revi) {
        this.empleado_revi = empleado_revi;
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
     * @return the empleadoFirmanteVolante
     */
    public Empleado getEmpleadoFirmanteVolante()
    {
        return empleadoFirmanteVolante;
    }

    /**
     * @param empleadoFirmanteVolante the empleadoFirmanteVolante to set
     */
    public void setEmpleadoFirmanteVolante(Empleado empleadoFirmanteVolante)
    {
        this.empleadoFirmanteVolante = empleadoFirmanteVolante;
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

    public String getInstruccionAdicional() {
        return instruccionAdicional;
    }

    public void setInstruccionAdicional(String instruccionAdicional) {
        this.instruccionAdicional = instruccionAdicional;
    }
}
