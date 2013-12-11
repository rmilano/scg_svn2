package mx.gob.economia.scg.action;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.gob.economia.scg.form.ConsultaConfidencialForm;
import mx.gob.economia.scg.model.Asunto;
import mx.gob.economia.scg.model.CriterioAsunto;
import mx.gob.economia.scg.model.Empleado;
import mx.gob.economia.scg.model.Paginador;
import mx.gob.economia.scg.service.AsuntoService;
import mx.gob.economia.scg.service.ConsultaConfidencialService;
import mx.gob.economia.scg.service.ExpedienteService;
import mx.gob.economia.scg.service.PrioridadService;
import mx.gob.economia.scg.service.TemaService;
import mx.gob.economia.scg.service.TipoDocumentoService;
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
public class ConsultaConfidencialAction extends DispatchAction {

    AsuntoService asuntoService;
    private ConsultaConfidencialService consultaConfidencialService;
    private TemaService temaService;
    private ExpedienteService expedienteService;
    private TipoDocumentoService tipoDocumentoService;
    private PrioridadService prioridadService;

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
            HttpServletRequest request, HttpServletResponse response) {
        String destino = "ERROR";

        ConsultaConfidencialForm cF = (ConsultaConfidencialForm) form;
        try {
            Empleado empleado_ses = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);
            cF.setTemas(getTemaService().listTemas(empleado_ses.getId_area()));
            cF.setExpedientes(getExpedienteService().listExpedientes(empleado_ses.getId_area()));
            cF.setTiposDocumento(tipoDocumentoService.listTipoDocumentos());
            cF.setPrioridades(prioridadService.listPrioridades());
            // Si no existe "order" inicializa criterios y la lista de asuntos
            if (request.getParameter("order") == null) {
                cF.setCriterioAsunto(new CriterioAsunto());
                cF.setAsuntos(new ArrayList<Asunto>());
            }
            request.getSession().removeAttribute(Constantes.MSG_FOUND);
            destino = "SUCCESS";
        } catch (Exception e) {
            log.error("Ha ocurrido una excepcion", e);
            request.setAttribute("mensaje",
                    "Error interno. Por favor, intentelo otra vez en unos minutos.");
        }
        cF.getCriterioAsunto().setPaginador(new Paginador());
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
            HttpServletRequest request, HttpServletResponse response) {

        ConsultaConfidencialForm cF = (ConsultaConfidencialForm) form;
        String destino = "ERROR";

        try {
            // Obtiene los asuntos en base a criterios
            List<Asunto> asuntos = null;

            if (cF.getNuevaConsulta().equals("si")) {
                cF.getCriterioAsunto().setPaginador(new Paginador());
                Empleado usuarioSession = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);
                // Asigna el estatus pendiente como criterio
                //criterioAsunto.setEstatus(Constantes.CONFIDENCIAL + "");
                cF.getCriterioAsunto().setConfidencial(Constantes.ACTIVADO);//AGG Confidencial -> booleano 20111109
                // Asigna el area de captura como criterio
                cF.getCriterioAsunto().setId_empleado_dest(usuarioSession.getId_empleado() + "");
                cF.getCriterioAsunto().getPaginador().setNumRegistros(this.asuntoService.countAsuntosIniciales(cF.getCriterioAsunto()));
                asuntos = this.asuntoService.listAsuntosIniciales(cF.getCriterioAsunto());
            } else {
                asuntos = this.asuntoService.listAsuntosIniciales(cF.getCriterioAsunto());
            }
            // mostrar mensaje de atribuno no encontrado
            if (cF.getCriterioAsunto().getPaginador().getNumRegistros() < Constantes.ACTIVO) {
                request.getSession().setAttribute(Constantes.MSG_FOUND, Constantes.MSG_FOUND);
            } else {
                request.getSession().removeAttribute(Constantes.MSG_FOUND);
            }
            Asunto.asignaIdx(asuntos);
            cF.setAsuntos(asuntos);
            destino = "SUCCESS";
        } catch (Exception e) {
            log.error("Ha ocurrido una excepcion", e);
            request.setAttribute("mensaje",
                    "Error interno. Por favor, int�ntelo otra vez en unos minutos.");
        }
        return mapping.findForward(destino);
    }

    /**
     * @return the temaService
     */
    public TemaService getTemaService() {
        return temaService;
    }

    /**
     * @param temaService the temaService to set
     */
    public void setTemaService(TemaService temaService) {
        this.temaService = temaService;
    }

    /**
     * @return the expedienteService
     */
    public ExpedienteService getExpedienteService() {
        return expedienteService;
    }

    /**
     * @param expedienteService the expedienteService to set
     */
    public void setExpedienteService(ExpedienteService expedienteService) {
        this.expedienteService = expedienteService;
    }

    /**
     * @param consultaConfidencialService the consultaConfidencialService to set
     */
    public void setConsultaConfidencialService(ConsultaConfidencialService consultaConfidencialService) {
        this.consultaConfidencialService = consultaConfidencialService;
    }

    public AsuntoService getAsuntoService() {
        return asuntoService;
    }

    public void setAsuntoService(AsuntoService asuntoService) {
        this.asuntoService = asuntoService;
    }

    public PrioridadService getPrioridadService() {
        return prioridadService;
    }

    public void setPrioridadService(PrioridadService prioridadService) {
        this.prioridadService = prioridadService;
    }

    public TipoDocumentoService getTipoDocumentoService() {
        return tipoDocumentoService;
    }

    public void setTipoDocumentoService(TipoDocumentoService tipoDocumentoService) {
        this.tipoDocumentoService = tipoDocumentoService;
    }
}
