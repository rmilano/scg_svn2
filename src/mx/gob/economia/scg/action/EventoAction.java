
package mx.gob.economia.scg.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.gob.economia.scg.form.EventoForm;
import mx.gob.economia.scg.model.CriterioEvento;
import mx.gob.economia.scg.model.Empleado;
import mx.gob.economia.scg.model.Paginador;
import mx.gob.economia.scg.model.Evento;
import mx.gob.economia.scg.service.AreaService;
import mx.gob.economia.scg.service.EventoService;
import mx.gob.economia.scg.util.Constantes;
import net.sf.json.JSONObject;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 *
 * @author roque
 */
public class EventoAction extends DispatchAction
{

    private EventoService eventoService;
    private AreaService areaService;

    /**
     * Inicio del catalogo de eventos.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward inicio(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        EventoForm eventoForm = (EventoForm) form;
        String forward = "INICIO";
        try
        {
            eventoForm.setCriterioEvento(new CriterioEvento());
            eventoForm.setEvento(new Evento());
            eventoForm.setEventos(new ArrayList<Evento>());
        } catch (Exception e)
        {
            log.error("Ha ocurrido un error en el metodo inicio del evento", e);
        }
        return mapping.findForward(forward);
    }

    /**
     * La pantalla de editar evento
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward editarEvento(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        EventoForm eventoForm = (EventoForm) form;
        String forward = "EDITAR";
        try
        {
            eventoForm.setEvento(this.eventoService.getEvento(eventoForm.getEvento().getId_evento()));
            eventoForm.getEvento().setArea(this.areaService.getAreaById(eventoForm.getEvento().getId_area()));
        }
        catch (Exception e)
        {
            log.error("Se ha producido un error al cargar el modulo de edicion de eventos", e);
        }
        return mapping.findForward(forward);
    }

    public ActionForward updateEvento(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        EventoForm eventoForm = (EventoForm) form;
        String forward = "INICIO";
        try
        {
            eventoService.updateEvento(eventoForm.getEvento());
            eventoForm.setEvento(new Evento());
            eventoForm.setEventos(this.eventoService.getEventosByCriterio(eventoForm.getCriterioEvento()));
        } catch (Exception e)
        {
            log.error("Se ha producido un error al actualizar el evento", e);
        }
        return mapping.findForward(forward);
    }

    public ActionForward cancelar(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {

        return mapping.findForward("INICIO");
    }

    /**
     * Pantalla para dar de alta un evento
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward registroEvento(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        EventoForm eventoForm = (EventoForm) form;
        eventoForm.setEvento(new Evento());
        String forward = "REGISTRAR";
        return mapping.findForward(forward);
    }

    /**
     * Guarda un evento en base
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward guardarEvento(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        EventoForm eventoForm = (EventoForm) form;
        String forward = "INICIO";
        try
        {
            this.eventoService.saveEvento(eventoForm.getEvento());
            eventoForm.getCriterioEvento().getEvento().setId_area(eventoForm.getEvento().getId_area());
            eventoForm.getCriterioEvento().setPaginador(new Paginador());
            eventoForm.getCriterioEvento().getPaginador().setNumRegistros(this.eventoService.countEventoByCriterio(eventoForm.getCriterioEvento()));
            eventoForm.setEventos(this.eventoService.getEventosByCriterio(eventoForm.getCriterioEvento()));
        } catch (Exception e)
        {
            log.debug("Se ha producido un error al momento de guardar el evento", e);
        }
        return mapping.findForward(forward);
    }

    public ActionForward borrarEvento(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        EventoForm eventoForm = (EventoForm) form;
        String forward = "INICIO";
        try
        {
            Evento evento = this.eventoService.getEvento(eventoForm.getEvento().getId_evento());
            evento.setActivo(0);
            this.eventoService.updateEvento(evento);
            eventoForm.getCriterioEvento().setPaginador(new Paginador());
            eventoForm.getCriterioEvento().getPaginador().setNumRegistros(this.eventoService.countEventoByCriterio(eventoForm.getCriterioEvento()));
            eventoForm.setEventos(this.eventoService.getEventosByCriterio(eventoForm.getCriterioEvento()));
        } catch (Exception e)
        {
            log.error("Se ha producido un error al eliminar el evento con id->" + eventoForm.getEvento().getId_evento(), e);
        }
        return mapping.findForward(forward);
    }

    /**
     * Obtiene los eventos por criterio, es el inico de la busqueda al dar clic en buscar
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getEventos(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        String forward = "INICIO";
        EventoForm eventoForm = (EventoForm) form;
        try
        {
            eventoForm.getCriterioEvento().setPaginador(new Paginador());
            eventoForm.getCriterioEvento().getPaginador().setNumRegistros(this.eventoService.countEventoByCriterio(eventoForm.getCriterioEvento()));
            if (eventoForm.getCriterioEvento().getPaginador().getNumRegistros()>0){
                eventoForm.setEventos(this.eventoService.getEventosByCriterio(eventoForm.getCriterioEvento()));
            }
            else{
                eventoForm.setEventos(new ArrayList<Evento>());
            }
        } catch (Exception e)
        {
            log.error("Ha ocurrido un error en el metodo getEventos del evento", e);
        }
        return mapping.findForward(forward);
    }

    /**
     * La navegación por el paginado
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getEventosPorPagina(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        String forward = "INICIO";
        EventoForm eventoForm = (EventoForm) form;
        try
        {
            eventoForm.setEventos(this.eventoService.getEventosByCriterio(eventoForm.getCriterioEvento()));
        } catch (Exception e)
        {
            log.error("Ha ocurrido un error en el metodo getEventosPorPagina del evento", e);
        }
        return mapping.findForward(forward);
    }

    /**
     * Guarda un evento por peticion asincrona(ajax).
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward saveEventoAsync(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        response.setContentType("text/json");
        try
        {
            String evento = request.getParameter("descripcionEvento");
            String idEmpleadoRevisor = request.getParameter("idEmpleadoRevisor");
            Empleado usuarioSession = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);
            Evento beanEvento = new Evento();
            beanEvento.setActivo(Constantes.ACTIVADO);
            beanEvento.setArea(usuarioSession.getArea());
            beanEvento.setEvento(evento);
            beanEvento.setId_area(usuarioSession.getArea().getId_area());
            this.eventoService.saveEvento(beanEvento);
            JSONObject jsonArray = JSONObject.fromObject(beanEvento);
            PrintWriter pw = response.getWriter();
            pw.write(jsonArray.toString());
            pw.flush();
            pw.close();
            //subir el nuevo evento a session del usuario
            request.getSession().removeAttribute(Constantes.EVENTOS);
            request.getSession().setAttribute(Constantes.EVENTOS, this.eventoService.listEventos(usuarioSession.getArea().getId_area()));

        } catch (Exception e)
        {
            log.debug("Error al guardar un evento desde la pantalla de captura", e);
        }
        return null;
    }

    /**
     * @param eventoService the eventoService to set
     */
    public void setEventoService(EventoService eventoService)
    {
        this.eventoService = eventoService;
    }
    /**
     * @param areaService the areaService to set
     */
    public void setAreaService(AreaService areaService)
    {
        this.areaService = areaService;
    }
}
