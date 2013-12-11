package mx.gob.economia.scg.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.economia.scg.form.ConsultaCapturaForm;
import mx.gob.economia.scg.model.Asunto;
import mx.gob.economia.scg.model.CriterioAsunto;
import mx.gob.economia.scg.model.Empleado;
import mx.gob.economia.scg.model.Paginador;
import mx.gob.economia.scg.service.AreaService;
import mx.gob.economia.scg.service.ArbolService;
import mx.gob.economia.scg.service.AsuntoService;
import mx.gob.economia.scg.service.EmpleadoService;
import mx.gob.economia.scg.service.EstatusService;
import mx.gob.economia.scg.service.ExpedienteService;
import mx.gob.economia.scg.service.PrioridadService;
import mx.gob.economia.scg.service.TipoDocumentoService;
import mx.gob.economia.scg.service.TemaService;
import mx.gob.economia.scg.service.EventoService;
import mx.gob.economia.scg.util.Constantes;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 * DispatchAction para la consulta de asuntos capturados
 * 
 * @author valentin.gomez
 */
public class ConsultaCapturaAction extends DispatchAction
{

    AsuntoService asuntoService;
    EmpleadoService empleadoService;
    ArbolService arbolService;
    EventoService eventoService;
    private TemaService temaService;
    private ExpedienteService expedienteService;
    private TipoDocumentoService tipoDocumentoService;
    private PrioridadService prioridadService;
    private EstatusService estatusService;
    private AreaService areaService;
    // private EventoService eventoService;

    /**
     * Muestra la pantalla de consulta de asuntos capturados
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return consulta inicial de asuntos
     */
    public ActionForward inicio(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
    {
        String destino = "ERROR";

        ConsultaCapturaForm cF = (ConsultaCapturaForm) form;
        try
        {
            Empleado empleado_ses = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);
            Integer id_area_busq = empleado_ses.getId_area();
            cF.setTemas(getTemaService().listTemas(id_area_busq));
            // Se agrego la busqueda por evento
            // By Rodolfo Milano Oliveros
            cF.setEventos(getEventoService().listEventos(id_area_busq));
            cF.setExpedientes(getExpedienteService().listExpedientes(id_area_busq));
            cF.setPrioridades(this.prioridadService.listPrioridades());
            cF.setTiposDocumento(this.tipoDocumentoService.listTipoDocumentos());            
            cF.setEstatus(this.estatusService.getEstatusAsunto());
            // Si no existe "order" inicializa criterios y la lista de asuntos
            if (request.getParameter("order") == null)
            {
                cF.setCriterioAsunto(new CriterioAsunto());
                cF.setAsuntos(new ArrayList<Asunto>());
            }
            destino = "SUCCESS";
        } catch (Exception e)
        {
            log.error("Ha ocurrido una excepcion en la consulta de documentos ->" + e);
            request.setAttribute("mensaje",
                    "Error interno. Por favor, intentelo otra vez en unos minutos.");
        }
        cF.getCriterioAsunto().setPaginador(new Paginador());
        request.getSession().setAttribute("status", cF.getEstatus());
        request.getSession().removeAttribute(Constantes.MSG_FOUND);
        return mapping.findForward(destino);
    }

    /**
     * Obtiene los asuntos que coinciden con los criterios seleccionados
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return consulta con resultados de la consulta
     */
    public ActionForward obtenerAsuntos(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
    {

        ConsultaCapturaForm cF = (ConsultaCapturaForm) form;
        String destino = "ERROR";

        try
        {
            // Obtiene los asuntos en base a criterios
            List<Asunto> asuntos = null;

            CriterioAsunto criterio = cF.getCriterioAsunto();

            cF.getCriterioAsunto().setEnTramite(Constantes.ACTIVADO);
            if (!cF.getCriterioAsunto().getEstatus().equals("14")){
                    cF.getCriterioAsunto().setEnTramite(0);
            }
            
            if (cF.getNuevaConsulta().equals("si"))
            {
                cF.getCriterioAsunto().setPaginador(new Paginador());
                cF.getCriterioAsunto().setConfidencial(-1);

                //asuntos = asuntoService.listAsuntosEnCaptura(cF.getCriterioAsunto(), request);
                //cF.getCriterioAsunto().getPaginador().setNumRegistros(asuntos.size());

                
                cF.getCriterioAsunto().getPaginador().setNumRegistros(asuntoService.countAsuntosEnCaptura(cF.getCriterioAsunto(), request));
                if ((cF.getCriterioAsunto().getPaginador().getNumRegistros() > 0) || (cF.getCriterioAsunto().getEstatus().equals("14"))){
                    asuntos = asuntoService.listAsuntosEnCaptura(cF.getCriterioAsunto(), request);
                }
                
            }
            else
            {
                asuntos = asuntoService.listAsuntosEnCaptura(cF.getCriterioAsunto(), request);
            }
            // mostrar mensaje de atribuno no encontrado
            if (cF.getCriterioAsunto().getPaginador().getNumRegistros() < Constantes.ACTIVO){
                request.getSession().setAttribute(Constantes.MSG_FOUND, Constantes.MSG_FOUND);
            }
            else{
                request.getSession().removeAttribute(Constantes.MSG_FOUND);
                Asunto.asignaIdx(asuntos);
            }
                        
            cF.setAsuntos(asuntos);
            destino = "SUCCESS";
        } catch (Exception e)
        {
            log.error("Ha ocurrido un error en consulta de asuntos ->" + e);
            request.setAttribute("mensaje",
                    "Error interno. Por favor, intentelo otra vez en unos minutos.");
        }
        return mapping.findForward(destino);
    }

    /**
     * @param asuntoService
     *            the asuntoService to set
     */
    public void setAsuntoService(AsuntoService asuntoService)
    {
        this.asuntoService = asuntoService;
    }

    /**
     * @param arbolService
     *            the arbolService to set
     */
    public void setArbolService(ArbolService arbolService)
    {
        this.arbolService = arbolService;
    }

    /**
     * @param empleadoService
     *            the empleadoService to set
     */
    public void setEmpleadoService(EmpleadoService empleadoService)
    {
        this.empleadoService = empleadoService;
    }

    /**
     * @return the temaService
     */
    public TemaService getTemaService()
    {
        return temaService;
    }

    /**
     * @param temaService the temaService to set
     */
    public void setTemaService(TemaService temaService)
    {
        this.temaService = temaService;
    }

    /**
     * @return the expedienteService
     */
    public ExpedienteService getExpedienteService()
    {
        return expedienteService;
    }

    /**
     * @param expedienteService the expedienteService to set
     */
    public void setExpedienteService(ExpedienteService expedienteService)
    {
        this.expedienteService = expedienteService;
    }

    /**
     * @param prioridadService the prioridadService to set
     */
    public void setPrioridadService(PrioridadService prioridadService)
    {
        this.prioridadService = prioridadService;
    }

     /**
     * @param tipoDocumentoService the tipoDocumentoService to set
     */
    public void setTipoDocumentoService(TipoDocumentoService tipoDocumentoService)
    {
        this.tipoDocumentoService = tipoDocumentoService;
    }

    /**
    * @param tipoDocumentoService the tipoDocumentoService to get
     */
    public TipoDocumentoService getTipoDocumentoService(TipoDocumentoService tipoDocumentoService)
    {
        return this.tipoDocumentoService;
    }

    /**
     * @param estatusService the estatusService to set
     */
    public void setEstatusService(EstatusService estatusService)
    {
        this.estatusService = estatusService;
    }

    public AreaService getAreaService() {
        return areaService;
    }

    public void setAreaService(AreaService areaService) {
        this.areaService = areaService;
    }

    public EventoService getEventoService() {
        return eventoService;
    }

    public void setEventoService(EventoService eventoService) {
        this.eventoService = eventoService;
    }

}
