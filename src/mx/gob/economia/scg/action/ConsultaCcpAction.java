package mx.gob.economia.scg.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.economia.scg.form.ConsultaCcpForm;
import mx.gob.economia.scg.model.Asunto;
import mx.gob.economia.scg.model.CriterioAsunto;
import mx.gob.economia.scg.model.Empleado;
import mx.gob.economia.scg.model.Paginador;
import mx.gob.economia.scg.model.Rol;
import mx.gob.economia.scg.service.ArbolService;
import mx.gob.economia.scg.service.AsuntoService;
import mx.gob.economia.scg.service.EmpleadoService;
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
 * DispatchAction para la consulta de asuntos con copia para
 * 
 * @author valentin.gomez
 */
public class ConsultaCcpAction extends DispatchAction
{
    AsuntoService asuntoService;
    EmpleadoService empleadoService;
    ArbolService arbolService;
    private TemaService temaService;
    private ExpedienteService expedienteService;
    private TipoDocumentoService tipoDocumentoService;
    private PrioridadService prioridadService;

    /**
     * Muestra la pantalla de consulta de asuntos con copia para
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

        ConsultaCcpForm cF = (ConsultaCcpForm) form;
        try
        {
            Empleado empleado_ses = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);
            cF.setTemas(getTemaService().listTemas(empleado_ses.getId_area()));
            cF.setExpedientes(getExpedienteService().listExpedientes(empleado_ses.getId_area()));
            cF.setTiposDocumento(tipoDocumentoService.listTipoDocumentos());
            cF.setPrioridades(prioridadService.listPrioridades());
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
            HttpServletRequest request, HttpServletResponse response)
    {

        ConsultaCcpForm cF = (ConsultaCcpForm) form;
        String destino = "ERROR";

        try
        {
            // Obtiene los asuntos en base a criterios
            List<Asunto> asuntos = null;
            String ids_empleados_ccp = "";
            if (cF.getNuevaConsulta().equals("si"))
            {
                cF.getCriterioAsunto().setPaginador(new Paginador());
                Empleado empleado_ses = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);
                if (empleado_ses.getRoles().contains(new Rol(Rol.RECEPCIONISTA)) || empleado_ses.getRoles().contains(new Rol(Rol.CAPTURISTA))){
                    List<Empleado> empleados_ccp = this.empleadoService.listEmpleadosByIdArea(empleado_ses.getId_area());
                    for (int i = 0; i < empleados_ccp.size(); i++)
                    {
                        Empleado empleado = (Empleado) empleados_ccp.get(i);
                        if (i==0){
                            ids_empleados_ccp = empleado.getId_empleado().toString();
                        }
                        else{
                            ids_empleados_ccp += "," +empleado.getId_empleado().toString();
                        }
                    }
                    cF.getCriterioAsunto().setIds_empleados_ccp(ids_empleados_ccp);
                }
                else{
                    cF.getCriterioAsunto().setIds_empleados_ccp(empleado_ses.getId_empleado() + "");
                }
                // Asigna empleado con copia para como criterio
                
                cF.getCriterioAsunto().getPaginador().setNumRegistros(asuntoService.countAsuntosIniciales(cF.getCriterioAsunto()));
                if(cF.getCriterioAsunto().getPaginador().getNumRegistros()>0){
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
                    "Error interno. Por favor, intï¿½ntelo otra vez en unos minutos.");
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
