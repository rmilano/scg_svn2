/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.economia.scg.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.gob.economia.scg.form.EstadisticaForm;
import mx.gob.economia.scg.model.Empleado;
import mx.gob.economia.scg.service.ExpedienteService;
import mx.gob.economia.scg.service.TemaService;
import mx.gob.economia.scg.util.Constantes;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 *
 * @author gerardo
 */
public class EstadisticaAction extends DispatchAction
{

    private TemaService temaService;
    private ExpedienteService expedienteService;

    public ActionForward inicio(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        String destino = "INICIO";
        EstadisticaForm estadisticaForm = (EstadisticaForm)form;
        Empleado usuarioSession = (Empleado)request.getSession().getAttribute(Constantes.USUARIO_SESION);
        estadisticaForm.setTemas(this.temaService.listTemas(usuarioSession.getArea().getId_area()));
        estadisticaForm.setExpedientes(this.expedienteService.listExpedientes(usuarioSession.getArea().getId_area()));
        return mapping.findForward(destino);
    }

    /**
     * @param temaService the temaService to set
     */
    public void setTemaService(TemaService temaService)
    {
        this.temaService = temaService;
    }

    /**
     * @param expedienteService the expedienteService to set
     */
    public void setExpedienteService(ExpedienteService expedienteService)
    {
        this.expedienteService = expedienteService;
    }
}
