/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.gob.economia.scg.action;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.gob.economia.scg.form.ConsultaResumenForm;
import mx.gob.economia.scg.model.Empleado;
import mx.gob.economia.scg.service.AsuntoService;
import mx.gob.economia.scg.util.Constantes;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 *
 * @author Administrador
 */
public class ConsultaResumenAction extends DispatchAction {
    AsuntoService asuntoService;

    
    public ActionForward getResumenAsuntos(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
            String destino="RESUMEN";
            Empleado empleado = new Empleado();
            ConsultaResumenForm resumen = (ConsultaResumenForm)form;
            List asuntos_resumen= new ArrayList();
            empleado =(Empleado)request.getSession().getAttribute(Constantes.USUARIO_SESION);
//            asuntos_resumen.add(this.asuntoService.CuentaAsuntosEnCaptura(empleado));
//            asuntos_resumen.add(this.asuntoService.CuentaAsuntosEnRecepcion(empleado));
//            asuntos_resumen.add(this.asuntoService.CuentaAsuntosEnBandeja(empleado));
//            asuntos_resumen.add(this.asuntoService.CuentaAsuntosCcp(empleado));
            resumen.setResumen_asuntos(asuntos_resumen);
            return mapping.findForward(destino);
    }

    
    public void setAsuntoService(AsuntoService asuntoService) {
        this.asuntoService = asuntoService;
    }
    
}

