/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.economia.scg.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.gob.economia.scg.form.ExpedienteForm;
import mx.gob.economia.scg.model.CriterioExpediente;
import mx.gob.economia.scg.model.Expediente;
import mx.gob.economia.scg.model.Paginador;
import mx.gob.economia.scg.service.AreaService;
import mx.gob.economia.scg.service.ExpedienteService;
import net.sf.json.JSONObject;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 *
 * @author rodrigo.martinez
 */
public class ExpedienteAction extends DispatchAction
{

    private ExpedienteService expedienteService;
    private AreaService areaService;

    /**
     * Inicio del catalogo de expedientes
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception 
     */
    public ActionForward init(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ExpedienteForm expedienteForm = (ExpedienteForm) form;
        String forward = "SUCCESS";
        try
        {
            expedienteForm.setExpediente(new Expediente());
            expedienteForm.setExpedientes(new ArrayList<Expediente>());
            expedienteForm.setCriterioExpediente(new CriterioExpediente());
        } catch (Exception e)
        {
            forward = "ERROR";
            log.error("Ha ocurrido un error en el metodo inicio del catalogo de Expedientes", e);
        }
        return mapping.findForward(forward);
    }

    /**
     * La lista de expedientes
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return 
     */
    public ActionForward listarExpedientes(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response)
    {
        String destino = "SUCCESS";
        try
        {
            ExpedienteForm expedienteForm = (ExpedienteForm) form;
            expedienteForm.getCriterioExpediente().setPaginador(new Paginador());
            expedienteForm.getCriterioExpediente().getPaginador().setNumRegistros(
                    this.expedienteService.countExpedientesByCriterio(expedienteForm.getCriterioExpediente()));
            expedienteForm.setExpedientes(this.expedienteService.listExpedientesByPagina(expedienteForm.getCriterioExpediente()));

        } catch (Exception ex)
        {
            request.setAttribute("mensaje", "No se pueden listar los expedientes");
            destino = "ERROR";
        }
        return mapping.findForward(destino);
    }

    /**
     * La navegacion por pagina
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception 
     */
    public ActionForward listarExpedientesByPagina(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        String destino = "SUCCESS";
        try
        {
            ExpedienteForm expedienteForm = (ExpedienteForm) form;
            if (!(expedienteForm.getCriterioExpediente().getExpediente().getArea().getArea().isEmpty()
                    && expedienteForm.getCriterioExpediente().getExpediente().getExpediente().isEmpty()))
                expedienteForm.setExpedientes(
                        expedienteService.listExpedientesByPagina(expedienteForm.getCriterioExpediente()));
        } catch (Exception ex)
        {
            destino = "ERROR";
            request.setAttribute("mensaje", "Error al acceder a los datos");
        }
        return mapping.findForward(destino);
    }

    /**
     * Pantalla de inicio de un nuevo expediente
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception 
     */
    public ActionForward inicioNuevoExpediente(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        String forward = "EDITAR";
        ExpedienteForm expedienteForm = (ExpedienteForm) form;
        try
        {
            expedienteForm.setExpediente(new Expediente());
        } catch (Exception e)
        {
            log.error("Ha ocurrido un error en el inicio de la pantalla de creacion de expediente", e);
        }
        return mapping.findForward(forward);
    }

    /**
     * Editar un nuevo expediente
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception 
     */
    public ActionForward inicioEditarExpediente(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        String forward = "ACTUALIZAR";
         ExpedienteForm expedienteForm = (ExpedienteForm) form;
        try
        {
           expedienteForm.setExpediente(this.expedienteService.getExpediente(expedienteForm.getExpediente().getId_expediente()));
           expedienteForm.getExpediente().setArea(this.areaService.getAreaById(expedienteForm.getExpediente().getId_area()));
        }
        catch(Exception e)
        {
            log.error("Ha ocurrido un error en el metodo inicioEditarExpediente del ExpedienteAction", e);
        }
        return mapping.findForward(forward);
    }
    /**
     * Guarda un expediente
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception 
     */
    public ActionForward saveExpediente(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        String destino = "SUCCESS";
        try
        {
            ExpedienteForm expedienteForm = (ExpedienteForm) form;
            expedienteForm.getExpediente().setBandera(true);
            expedienteService.saveExpediente(expedienteForm.getExpediente());
            if (!(expedienteForm.getCriterioExpediente().getExpediente().getArea().getArea().isEmpty()
                    && expedienteForm.getCriterioExpediente().getExpediente().getExpediente().isEmpty()))
                expedienteForm.setExpedientes(this.expedienteService.listExpedientesByPagina(
                        expedienteForm.getCriterioExpediente()));
            expedienteForm.setExpediente(new Expediente());

        } catch (Exception ex)
        {

            destino = "ERROR";
            request.setAttribute("mensaje", "No se han podido dar de alta el Expediente");
        }
        return mapping.findForward(destino);
    }

    /**
     * Baja de expedientes
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception 
     */
    public ActionForward bajaExpediente(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        String destino = "SUCCESS";
        try
        {
            ExpedienteForm expedienteForm = (ExpedienteForm) form;
            expedienteForm.setExpediente(
                    expedienteService.getExpediente(
                    expedienteForm.getExpediente().getId_expediente()));
            expedienteForm.getExpediente().setBandera(true);
            expedienteForm.getExpediente().setActivo(0);
            expedienteService.updateExpediente(expedienteForm.getExpediente());
            expedienteForm.getCriterioExpediente().setPaginador(new Paginador());
            expedienteForm.getCriterioExpediente().getPaginador().setNumRegistros(
                    this.expedienteService.countExpedientesByCriterio(expedienteForm.getCriterioExpediente()));
            expedienteForm.setExpedientes(this.expedienteService.listExpedientesByPagina(expedienteForm.getCriterioExpediente()));
            expedienteForm.setExpediente(new Expediente());
        } catch (Exception ex)
        {
            destino = "ERROR";
            request.setAttribute("mensaje", "No se ha podido dar de baja el Expediente");
        }
        return mapping.findForward(destino);
    }

    /**
     * Actualiza un expediente
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception 
     */
    public ActionForward updateExpediente(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        String destino = "SUCCESS";
        try
        {
            ExpedienteForm expedienteForm = (ExpedienteForm) form;
            expedienteForm.getExpediente().setBandera(true);
            expedienteService.updateExpediente(expedienteForm.getExpediente());

            expedienteForm.setExpedientes(this.expedienteService.listExpedientesByPagina(expedienteForm.getCriterioExpediente()));
            expedienteForm.setExpediente(new Expediente());

        } catch (Exception ex)
        {
            destino = "ERROR";
            request.setAttribute("mensaje", "No se ha podido actualizar el expediente");
        }
        return mapping.findForward(destino);
    }

    /**
     * Obtiene el expediente
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception 
     */
    public ActionForward getExpediente(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        response.setContentType("text/json");
        Expediente objeto = expedienteService.getExpediente(Integer.valueOf(request.getParameter("id_expediente")));
        objeto.setArea(areaService.getAreaById(objeto.getId_area()));
        JSONObject jsonArray = JSONObject.fromObject(objeto);
        PrintWriter pw = response.getWriter();
        pw.write(jsonArray.toString());
        pw.flush();
        pw.close();
        return null;
    }

    public void setExpedienteService(ExpedienteService expedienteService)
    {
        this.expedienteService = expedienteService;
    }

    /**
     * @param areaService the areaService to set
     */
    public void setAreaService(AreaService areaService)
    {
        this.areaService = areaService;
    }
}
