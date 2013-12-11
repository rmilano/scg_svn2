package mx.gob.economia.scg.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.economia.scg.form.PrioridadForm;
import mx.gob.economia.scg.model.Prioridad;
import mx.gob.economia.scg.service.PrioridadService;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 * 
 * @author valentin.gomez
 */
public class PrioridadAction extends DispatchAction {
	private PrioridadService prioridadService;

	public ActionForward inicio(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrioridadForm pF = (PrioridadForm) form;
		if (request.getParameter("order") == null) {
			pF.setPrioridades(prioridadService.listPrioridades());
		}
		return mapping.findForward("SUCCESS");
	}

	public ActionForward guardar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String forward = "";
		try {
			PrioridadForm pF = (PrioridadForm) form;
			prioridadService.savePrioridad(pF.getPrioridad());
			pF.setPrioridades(prioridadService.listPrioridades());
			forward = "SUCCESS";
		} catch (Exception e) {
			e.printStackTrace();
			forward = "ERROR";
		}
		return mapping.findForward(forward);
	}

	public ActionForward actualizar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String forward = "";
		try {
			PrioridadForm pF = (PrioridadForm) form;
			prioridadService.updatePrioridad(pF.getPrioridad());
			pF.setPrioridades(prioridadService.listPrioridades());
			forward = "SUCCESS";
		} catch (Exception e) {
			e.printStackTrace();
			forward = "ERROR";
		}
		return mapping.findForward(forward);
	}

	public ActionForward eliminar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String forward = "";
		try {
			PrioridadForm pF = (PrioridadForm) form;

			prioridadService.deletePrioridad(pF.getPrioridad()
					.getId_prioridad());
			pF.setPrioridades(prioridadService.listPrioridades());
			forward = "SUCCESS";
		} catch (Exception e) {
			e.printStackTrace();
			forward = "ERROR";
		}
		return mapping.findForward(forward);
	}

	public ActionForward getPrioridad(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/json");
		Prioridad objeto = prioridadService.getPrioridad(Integer
				.valueOf(request.getParameter("idPrioridad")));
		JSONObject jsonArray = JSONObject.fromObject(objeto);
		PrintWriter pw = response.getWriter();
		pw.write(jsonArray.toString());
		pw.flush();
		pw.close();
		return null;
	}

	/**
	 * @param prioridadService
	 *            the prioridadService to set
	 */
	public void setPrioridadService(PrioridadService prioridadService) {
		this.prioridadService = prioridadService;
	}

}
