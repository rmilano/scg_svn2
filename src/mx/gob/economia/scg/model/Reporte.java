package mx.gob.economia.scg.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import mx.gob.economia.scg.util.Constantes;

public class Reporte implements Serializable
{

    private Integer idArea;
    private Integer total;
    private Integer pendiente;
    private Integer turnado;
    private Integer atendido;
    private Integer finalizado;
    private Integer enTiempo;
    private Integer porVencer;
    private Integer vencido;
    private Integer enCaptura;
    private Integer enRevision;
    private Integer eliminado;
    private Integer archivado;
    private List<Reporte> detalleAsunto;
    private CriterioReporte criterio;
    private Empleado empleado;
    private Empleado destinatario;
    private String asunto;
    private String folio;
    private String noOficio;
    private String idAsuntoRef;
    private Integer estatus;
    private String estatus_desc;
    private Integer rownum;
    private String atencionParcial;

    public Reporte()
    {
        this.idArea = -1;
        this.total = 0;
        this.pendiente = 0;
        this.turnado = 0;
        this.atendido = 0;
        this.finalizado = 0;
        this.enTiempo = 0;
        this.porVencer = 0;
        this.vencido = 0;
        this.enCaptura = 0;
        this.enRevision = 0;
        this.eliminado = 0;
        this.archivado = 0;
        this.detalleAsunto = new ArrayList<Reporte>();
        this.criterio = new CriterioReporte();
        this.empleado = new Empleado();
        this.destinatario = new Empleado();
        this.asunto = "";
        this.folio = "";
        this.noOficio = "";
        this.idAsuntoRef = "";
        this.estatus = Constantes.PENDIENTE;
        this.estatus_desc = "";
        this.atencionParcial = "";
        this.rownum = 0;
    }

    /**
     * @return the idArea
     */
    public Integer getIdArea()
    {
        return idArea;
    }

    /**
     * @param idArea the idArea to set
     */
    public void setIdArea(Integer idArea)
    {
        this.idArea = idArea;
    }

    /**
     * @return the total
     */
    public Integer getTotal()
    {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(Integer total)
    {
        this.total = total;
    }

    /**
     * @return the pendiente
     */
    public Integer getPendiente()
    {
        return pendiente;
    }

    /**
     * @param pendiente the pendiente to set
     */
    public void setPendiente(Integer pendiente)
    {
        this.pendiente = pendiente;
    }

    /**
     * @return the turnado
     */
    public Integer getTurnado()
    {
        return turnado;
    }

    /**
     * @param turnado the turnado to set
     */
    public void setTurnado(Integer turnado)
    {
        this.turnado = turnado;
    }

    /**
     * @return the atendido
     */
    public Integer getAtendido()
    {
        return atendido;
    }

    /**
     * @param atendido the atendido to set
     */
    public void setAtendido(Integer atendido)
    {
        this.atendido = atendido;
    }

    /**
     * @return the finalizado
     */
    public Integer getFinalizado()
    {
        return finalizado;
    }

    /**
     * @param finalizado the finalizado to set
     */
    public void setFinalizado(Integer finalizado)
    {
        this.finalizado = finalizado;
    }

    /**
     * @return the enTiempo
     */
    public Integer getEnTiempo()
    {
        return enTiempo;
    }

    /**
     * @param enTiempo the enTiempo to set
     */
    public void setEnTiempo(Integer enTiempo)
    {
        this.enTiempo = enTiempo;
    }

    /**
     * @return the porVencer
     */
    public Integer getPorVencer()
    {
        return porVencer;
    }

    /**
     * @param porVencer the porVencer to set
     */
    public void setPorVencer(Integer porVencer)
    {
        this.porVencer = porVencer;
    }

    /**
     * @return the vencido
     */
    public Integer getVencido()
    {
        return vencido;
    }

    /**
     * @param vencido the vencido to set
     */
    public void setVencido(Integer vencido)
    {
        this.vencido = vencido;
    }

    /**
     * @return the enRevision
     */
    public Integer getEnRevision()
    {
        return enRevision;
    }

    /**
     * @param enRevision the enRevision to set
     */
    public void setEnRevision(Integer enRevision)
    {
        this.enRevision = enRevision;
    }

    /**
     * @return the eliminado
     */
    public Integer getEliminado()
    {
        return eliminado;
    }

    /**
     * @param eliminado the eliminado to set
     */
    public void setEliminado(Integer eliminado)
    {
        this.eliminado = eliminado;
    }

    /**
     * @return the archivado
     */
    public Integer getArchivado()
    {
        return archivado;
    }

    /**
     * @param archivado the archivado to set
     */
    public void setArchivado(Integer archivado)
    {
        this.archivado = archivado;
    }

    /**
     * @return the detalleAsunto
     */
    public List<Reporte> getDetalleAsunto()
    {
        return detalleAsunto;
    }

    /**
     * @param detalleAsunto the detalleAsunto to set
     */
    public void setDetalleAsunto(List<Reporte> detalleAsunto)
    {
        this.detalleAsunto = detalleAsunto;
    }

    /**
     * @return the enCaptura
     */
    public Integer getEnCaptura()
    {
        return enCaptura;
    }

    /**
     * @param enCaptura the enCaptura to set
     */
    public void setEnCaptura(Integer enCaptura)
    {
        this.enCaptura = enCaptura;
    }

    /**
     * @return the criterio
     */
    public CriterioReporte getCriterio()
    {
        return criterio;
    }

    /**
     * @param criterio the criterio to set
     */
    public void setCriterio(CriterioReporte criterio)
    {
        this.criterio = criterio;
    }

    /**
     * @return the empleado
     */
    public Empleado getEmpleado()
    {
        return empleado;
    }

    /**
     * @param empleado the empleado to set
     */
    public void setEmpleado(Empleado empleado)
    {
        this.empleado = empleado;
    }

    /**
     * @return the asunto
     */
    public String getAsunto()
    {
        return asunto;
    }

    /**
     * @param asunto the asunto to set
     */
    public void setAsunto(String asunto)
    {
        this.asunto = asunto;
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
     * @return the noOficio
     */
    public String getNoOficio()
    {
        return noOficio;
    }

    /**
     * @param noOficio the noOficio to set
     */
    public void setNoOficio(String noOficio)
    {
        this.noOficio = noOficio;
    }

    /**
     * @return the idAsuntoRef
     */
    public String getIdAsuntoRef()
    {
        return idAsuntoRef;
    }

    /**
     * @param idAsuntoRef the idAsuntoRef to set
     */
    public void setIdAsuntoRef(String idAsuntoRef)
    {
        this.idAsuntoRef = idAsuntoRef;
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
     * @return the estatus
     */
    public Integer getEstatus()
    {
        return estatus;
    }

    /**
     * @param estatus the estatus to set
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
            if ((estatus.equals(Constantes.TURNADO) || estatus.equals(Constantes.PENDIENTE)) && (this.atencionParcial != null))
                estatus_desc = "EN TRAMITE";
            else if(estatus.equals(Constantes.PENDIENTE))
                estatus_desc = "INICIAL";
            else if (estatus.equals(Constantes.TURNADO))
                estatus_desc = "PENDIENTE";
            else if (estatus.equals(Constantes.ATENCIONPARCIAL))
                estatus_desc = "EN TRAMITE";
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
            /*else if (estatus.equals(Constantes.CONFIDENCIAL))
                estatus_desc = "CONFIDENCIAL";*/


        return estatus_desc;
    }

    /**
     * @param estatus_desc the estatus_desc to set
     */
    public void setEstatus_desc(String estatus_desc)
    {
        this.estatus_desc = estatus_desc;
    }

    public String getAtencionParcial()
    {
        return this.atencionParcial;
    }

    public void setAtencionParcial(String atencionParcial)
    {
        this.atencionParcial = atencionParcial;
    }

    /**
     * @return the rownum
     */
    public Integer getRownum()
    {
        return rownum;
    }

    /**
     * @param rownum the rownum to set
     */
    public void setRownum(Integer rownum)
    {
        this.rownum = rownum;
    }
}
