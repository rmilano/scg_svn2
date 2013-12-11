package mx.gob.economia.scg.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.economia.scg.form.ConsultaForm;
import mx.gob.economia.scg.model.Asunto;
import mx.gob.economia.scg.model.CriterioAsunto;
import mx.gob.economia.scg.service.ArbolService;
import mx.gob.economia.scg.service.AsuntoService;
import mx.gob.economia.scg.service.EmpleadoService;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 * DispatchAction para la consulta de asuntos
 * 
 * @author valentin.gomez
 */
public class ConsultaAction extends DispatchAction {
	AsuntoService asuntoService;
	EmpleadoService empleadoService;
	ArbolService arbolService;

	/**
	 * Muestra la pantalla de consulta de asuntos
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

		ConsultaForm cF = (ConsultaForm) form;
		try {
			if (request.getParameter("order") == null) {
				cF.setCriterioAsunto(new CriterioAsunto());
				cF.setAsuntos(new ArrayList<Asunto>());
			}
			destino = "SUCCESS";
		} catch (Exception e) {
			e.printStackTrace();
			request
					.setAttribute("mensaje",
							"Error interno. Por favor, int?ntelo otra vez en unos minutos.");
		}
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

		ConsultaForm cF = (ConsultaForm) form;
		String destino = "ERROR";

		try {
			// Obtiene los asuntos en base a criterios
			List<Asunto> asuntos = asuntoService.listAsuntosActuales(cF
					.getCriterioAsunto());
			Asunto.asignaIdx(asuntos);
			cF.setAsuntos(asuntos);
			destino = "SUCCESS";
		} catch (Exception e) {
			e.printStackTrace();
			request
					.setAttribute("mensaje",
							"Error interno. Por favor, int?ntelo otra vez en unos minutos.");
		}
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
	public ActionForward obtenerAsuntosDetallePDF(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		ConsultaForm cF = (ConsultaForm) form;
		String destino = "ERROR";

		try {
			// Obtiene los asuntos en base a criterios
			List<Asunto> asuntos = asuntoService.listAsuntosActualesDetallePDF(cF
					.getCriterioAsunto());
			Asunto.asignaIdx(asuntos);
			cF.setAsuntos(asuntos);
			destino = "SUCCESS";
		} catch (Exception e) {
			e.printStackTrace();
			request
					.setAttribute("mensaje",
							"Error interno. Por favor, int?ntelo otra vez en unos minutos.");
		}
		return mapping.findForward(destino);
	}

	/**
	 * @param asuntoService
	 *            the asuntoService to set
	 */
	public void setAsuntoService(AsuntoService asuntoService) {
		this.asuntoService = asuntoService;
	}

	/**
	 * @param arbolService
	 *            the arbolService to set
	 */
	public void setArbolService(ArbolService arbolService) {
		this.arbolService = arbolService;
	}

	/**
	 * @param empleadoService
	 *            the empleadoService to set
	 */
	public void setEmpleadoService(EmpleadoService empleadoService) {
		this.empleadoService = empleadoService;
	}

}
