/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.economia.scg.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.gob.economia.scg.form.TemaForm;
import mx.gob.economia.scg.model.CriterioTema;
import mx.gob.economia.scg.model.Empleado;
import mx.gob.economia.scg.model.Paginador;
import mx.gob.economia.scg.model.Tema;
import mx.gob.economia.scg.service.AreaService;
import mx.gob.economia.scg.service.TemaService;
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
public class TemaAction extends DispatchAction
{

    private TemaService temaService;
    private AreaService areaService;

    /**
     * Inicio del catalogo de temas.
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
        TemaForm temaForm = (TemaForm) form;
        String forward = "INICIO";
        try
        {
            temaForm.setCriterioTema(new CriterioTema());
            temaForm.setTema(new Tema());
            temaForm.setTemas(new ArrayList<Tema>());
        } catch (Exception e)
        {
            log.error("Ha ocurrido un error en el metodo inicio del tema", e);
        }
        return mapping.findForward(forward);
    }

    /**
     * La pantalla de editar tema
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward editarTema(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        TemaForm temaForm = (TemaForm) form;
        String forward = "EDITAR";
        try
        {
            temaForm.setTema(this.temaService.getTema(temaForm.getTema().getId_tema()));
            temaForm.getTema().setArea(this.areaService.getAreaById(temaForm.getTema().getId_area()));
            temaForm.setSubTemas(this.temaService.getTemasByIdPadre(temaForm.getTema().getId_tema()));
        } catch (Exception e)
        {
            log.error("Se ha producido un error al cargar el modulo de edicion de temas", e);
        }
        return mapping.findForward(forward);
    }

    public ActionForward updateTema(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        TemaForm temaForm = (TemaForm) form;
        String forward = "INICIO";
        try
        {
            temaService.updateTema(temaForm.getTema());
            temaForm.setTema(new Tema());
            temaForm.setTemas(this.temaService.getTemasByCriterio(temaForm.getCriterioTema()));
        } catch (Exception e)
        {
            log.error("Se ha producido un error al actualizar el tema", e);
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
     * Pantalla para dar de alta un tema
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward registroTema(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        TemaForm temaForm = (TemaForm) form;
        temaForm.setTema(new Tema());
        String forward = "REGISTRAR";
        return mapping.findForward(forward);
    }

    /**
     * Guarda un tema en base
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward guardarTema(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        TemaForm temaForm = (TemaForm) form;
        String forward = "INICIO";
        try
        {
            this.temaService.saveTema(temaForm.getTema());
            temaForm.getCriterioTema().getTema().setId_area(temaForm.getTema().getId_area());
            temaForm.getCriterioTema().setPaginador(new Paginador());
            temaForm.getCriterioTema().getPaginador().setNumRegistros(this.temaService.countTemaByCriterio(temaForm.getCriterioTema()));
            temaForm.setTemas(this.temaService.getTemasByCriterio(temaForm.getCriterioTema()));
        } catch (Exception e)
        {
            log.debug("Se ha producido un error al momento de guardar el tema", e);
        }
        return mapping.findForward(forward);
    }

    public ActionForward borrarTema(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        TemaForm temaForm = (TemaForm) form;
        String forward = "INICIO";
        try
        {
            Tema tema = this.temaService.getTema(temaForm.getTema().getId_tema());
            tema.setActivo(0);
            this.temaService.updateTema(tema);
            temaForm.getCriterioTema().setPaginador(new Paginador());
            temaForm.getCriterioTema().getPaginador().setNumRegistros(this.temaService.countTemaByCriterio(temaForm.getCriterioTema()));
            temaForm.setTemas(this.temaService.getTemasByCriterio(temaForm.getCriterioTema()));
        } catch (Exception e)
        {
            log.error("Se ha producido un error al eliminar el tema con id->" + temaForm.getTema().getId_tema(), e);
        }
        return mapping.findForward(forward);
    }

    /**
     * Obtiene los temas por criterio, es el inico de la busqueda al dar clic en buscar
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getTemas(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        String forward = "INICIO";
        TemaForm temaForm = (TemaForm) form;
        try
        {
            temaForm.getCriterioTema().setPaginador(new Paginador());
            temaForm.getCriterioTema().getPaginador().setNumRegistros(this.temaService.countTemaByCriterio(temaForm.getCriterioTema()));
            if (temaForm.getCriterioTema().getPaginador().getNumRegistros()>0){
                temaForm.setTemas(this.temaService.getTemasByCriterio(temaForm.getCriterioTema()));
            }
            else{
                temaForm.setTemas(new ArrayList<Tema>());
            }
        } catch (Exception e)
        {
            log.error("Ha ocurrido un error en el metodo getTemas del tema", e);
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
    public ActionForward getTemasPorPagina(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        String forward = "INICIO";
        TemaForm temaForm = (TemaForm) form;
        try
        {
            temaForm.setTemas(this.temaService.getTemasByCriterio(temaForm.getCriterioTema()));
        } catch (Exception e)
        {
            log.error("Ha ocurrido un error en el metodo getTemasPorPagina del tema", e);
        }
        return mapping.findForward(forward);
    }

    /**
     * Guarda un tema por peticion asincrona(ajax).
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception 
     */
    public ActionForward saveTemaAsync(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        response.setContentType("text/json");
        try
        {
            String tema = request.getParameter("descripcionTema");
            String idEmpleadoRevisor = request.getParameter("idEmpleadoRevisor");
            Empleado usuarioSession = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);
            Tema beanTema = new Tema();
            beanTema.setActivo(Constantes.ACTIVADO);
            beanTema.setArea(usuarioSession.getArea());
            beanTema.setEvento(Constantes.NO_ACTIVO);
            beanTema.setTema(tema);
            beanTema.setId_area(usuarioSession.getArea().getId_area());
            beanTema.getRevisor().setId_empleado(Integer.valueOf(idEmpleadoRevisor));
            this.temaService.saveTema(beanTema);
            this.temaService.saveTemaRevisor(beanTema);
            JSONObject jsonArray = JSONObject.fromObject(beanTema);
            PrintWriter pw = response.getWriter();
            pw.write(jsonArray.toString());
            pw.flush();
            pw.close();
            //subir el nuevo tema a session del usuario
            request.getSession().removeAttribute(Constantes.TEMAS);
            request.getSession().setAttribute(Constantes.TEMAS, this.temaService.listTemas(usuarioSession.getId_area()));

        } catch (Exception e)
        {
            log.debug("Error al guardar un tema desde la pantalla de captura", e);
        }
        return null;
    }

    /**
     * Guarda un Subtema por peticion asincrona.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception 
     */
    public ActionForward saveSubTemaAsync(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        response.setContentType("text/json");
        try
        {
            String idTemaPadre = request.getParameter("idTemaPadre");
            String subTema = request.getParameter("descripcionSubtema");
            Empleado usuarioSession = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);
            Tema beanTema = new Tema();
            beanTema.setActivo(Constantes.ACTIVADO);
            beanTema.setArea(usuarioSession.getArea());
            beanTema.setEvento(Constantes.NO_ACTIVO);
            beanTema.setId_tema_padre(Integer.valueOf(idTemaPadre));
            beanTema.setTema(subTema);
            beanTema.setId_area(usuarioSession.getArea().getId_area());
            this.temaService.saveTema(beanTema);
            JSONObject jsonArray = JSONObject.fromObject(beanTema);
            PrintWriter pw = response.getWriter();
            pw.write(jsonArray.toString());
            pw.flush();
            pw.close();
        } catch (Exception e)
        {
            log.debug("Error al guardar el subtema desde la pantalla de captura", e);
        }
        return null;
    }

    /*Subtemas*/
    public ActionForward guardarSubtema(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        TemaForm temaForm = (TemaForm) form;
        String forward = "EDITAR";
        try
        {
            Tema subtema = temaForm.getSubtema();
            subtema.setId_area(temaForm.getTema().getId_area());
            subtema.setId_tema_padre(temaForm.getTema().getId_tema());
            temaService.saveTema(subtema);
            temaForm.setSubTemas(this.temaService.getTemasByIdPadre(temaForm.getTema().getId_tema()));
            temaForm.setSubtema(new Tema());

        } catch (Exception e)
        {

            log.debug("Se ha producido un error al momento de guardar el subtema", e);
        }
        return mapping.findForward(forward);
    }

    public ActionForward borrarSubtema(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        TemaForm temaForm = (TemaForm) form;
        String forward = "EDITAR";
        try
        {
            Tema tema = this.temaService.getTema(temaForm.getSubtema().getId_tema());
            tema.setActivo(0);
            this.temaService.updateTema(tema);
            temaForm.setSubTemas(this.temaService.getTemasByIdPadre(temaForm.getTema().getId_tema()));
        } catch (Exception e)
        {
            log.error("Se ha producido un error al eliminar el subtema con id->" + temaForm.getSubtema().getId_tema(), e);
        }
        return mapping.findForward(forward);
    }

    public ActionForward getSubtema(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        response.setContentType("text/json");
        Tema objeto = temaService.getTema(Integer.valueOf(request.getParameter("id_subtema")));
        JSONObject jsonArray = JSONObject.fromObject(objeto);
        PrintWriter pw = response.getWriter();
        pw.write(jsonArray.toString());
        pw.flush();
        pw.close();
        return null;
    }

    public ActionForward updateSubtema(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        TemaForm temaForm = (TemaForm) form;
        String forward = "EDITAR";
        try
        {
            Tema subtema = temaForm.getSubtema();
            subtema.setId_area(temaForm.getTema().getId_area());
            subtema.setId_tema_padre(temaForm.getTema().getId_tema());
            temaService.updateTema(subtema);
            temaForm.setSubTemas(this.temaService.getTemasByIdPadre(temaForm.getTema().getId_tema()));
            temaForm.setSubtema(new Tema());
        } catch (Exception e)
        {

            log.debug("Se ha producido un error al momento de guardar el subtema", e);
        }
        return mapping.findForward(forward);
    }

    /**
     * @param temaService the temaService to set
     */
    public void setTemaService(TemaService temaService)
    {
        this.temaService = temaService;
    }

    /**
     * @param areaService the areaService to set
     */
    public void setAreaService(AreaService areaService)
    {
        this.areaService = areaService;
    }
}
