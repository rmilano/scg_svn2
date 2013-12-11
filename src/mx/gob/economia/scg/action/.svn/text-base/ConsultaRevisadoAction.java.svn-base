package mx.gob.economia.scg.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.gob.economia.scg.form.ConsultaRevisadoForm;
import mx.gob.economia.scg.model.Asunto;
import mx.gob.economia.scg.model.CriterioAsunto;
import mx.gob.economia.scg.model.Empleado;
import mx.gob.economia.scg.model.Paginador;
import mx.gob.economia.scg.service.ArbolService;
import mx.gob.economia.scg.service.AsuntoRevisionService;
import mx.gob.economia.scg.service.AsuntoService;
import mx.gob.economia.scg.service.EmpleadoService;
import mx.gob.economia.scg.service.ExpedienteService;
import mx.gob.economia.scg.service.TemaService;
import mx.gob.economia.scg.service.TipoDocumentoService;
import mx.gob.economia.scg.service.PrioridadService;
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
public class ConsultaRevisadoAction extends DispatchAction
{

    AsuntoService asuntoService;
    EmpleadoService empleadoService;
    ArbolService arbolService;
    private TemaService temaService;
    private ExpedienteService expedienteService;
    private AsuntoRevisionService asuntoRevisionService;
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
            HttpServletRequest request, HttpServletResponse response)
    {
        String destino = "ERROR";

        ConsultaRevisadoForm cF = (ConsultaRevisadoForm) form;
        try
        {
            Empleado empleado_ses = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);
            cF.setTemas(getTemaService().listTemas(empleado_ses.getId_area()));
            cF.setTiposDocumento(this.tipoDocumentoService.listTipoDocumentos());
            cF.setPrioridades(this.prioridadService.listPrioridades());
            cF.setExpedientes(getExpedienteService().listExpedientes(empleado_ses.getId_area()));
            // Si no existe "order" inicializa criterios y la lista de asuntos
            if (request.getParameter("order") == null)
            {
                cF.setCriterioAsunto(new CriterioAsunto());
                cF.setAsuntos(new ArrayList<Asunto>());
            }
            request.getSession().removeAttribute(Constantes.MSG_FOUND);
            destino = "SUCCESS";
        } catch (Exception e)
        {
            log.error("Se ha producido un error->", e);
            request.setAttribute("mensaje",
                    "Error interno. Por favor, int�ntelo otra vez en unos minutos.");
        }
        cF.getCriterioAsunto().setPaginador(new Paginador());
        return mapping.findForward(destino);
    }

    /**
     * Obtener los asunto que estan en estatus de REVISADO
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward obtenerAsuntos(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
    {

        ConsultaRevisadoForm cF = (ConsultaRevisadoForm) form;
        String destino = "ERROR";

        try
        {
            System.out.println("Entra!!!!!");
            // Obtiene los asuntos en base a criterios
            List<Asunto> asuntos = null;

            if (cF.getNuevaConsulta().equals("si"))
            {
                cF.getCriterioAsunto().setPaginador(new Paginador());
                Empleado empleado_ses = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);
                String id_area_capt = empleado_ses.getArea().getId_area().toString();
                cF.getCriterioAsunto().setId_area_capt(id_area_capt);
                cF.getCriterioAsunto().setEstatus(Integer.toString(Constantes.REVISADO_CAPTURA));
                cF.getCriterioAsunto().getPaginador().setNumRegistros(asuntoService.countAsuntosIniciales(cF.getCriterioAsunto()));
                if (cF.getCriterioAsunto().getPaginador().getNumRegistros() > 0) {
                   asuntos = asuntoService.listAsuntosIniciales(cF.getCriterioAsunto());
                }
            }
            else
            {
                asuntos = asuntoService.listAsuntosIniciales(cF.getCriterioAsunto());
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
            log.error("Se ha producido un error->", e);
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
     * @param asuntoRevisionService the asuntoRevisionService to set
     */
    public void setAsuntoRevisionService(AsuntoRevisionService asuntoRevisionService)
    {
        this.asuntoRevisionService = asuntoRevisionService;
    }

    /**
     * @return the tipoDocumentoService
     */
    public TipoDocumentoService getTipoDocumentoService()
    {
        return tipoDocumentoService;
    }

    /**
     * @param temaService the TipoDocumentoService to set
     */
    public void setTipoDocumentoService(TipoDocumentoService tipoDocumentoService)
    {
        this.tipoDocumentoService = tipoDocumentoService;
    }

    /**
     * @return the prioridadService
     */
    public PrioridadService getPrioridadServiceService()
    {
        return prioridadService;
    }

    /**
     * @param temaService the prioridadService to set
     */
    public void setPrioridadService(PrioridadService prioridadService)
    {
        this.prioridadService = prioridadService;
    }
}
