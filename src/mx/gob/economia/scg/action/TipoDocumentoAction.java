/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.gob.economia.scg.action;

import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.gob.economia.scg.form.TipoDocumentoForm;
import mx.gob.economia.scg.model.TipoDocumento;
import mx.gob.economia.scg.service.TipoDocumentoService;
import mx.gob.economia.scg.util.Constantes;
import net.sf.json.JSONObject;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 *
 * @author rodrigo
 */
public class TipoDocumentoAction extends DispatchAction{
    private TipoDocumentoService tipoDocumentoService;

    public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
       TipoDocumentoForm tipoDocumentoForm= (TipoDocumentoForm)form;
       if(request.getParameter("order") == null){
    	   tipoDocumentoForm.setTiposDocumento(tipoDocumentoService.listTipoDocumentos());
       }	   
       return mapping.findForward("SUCCESS");
    }

    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
       String forward="SUCCESS";
       try{
        TipoDocumentoForm tipoDocumentoForm= (TipoDocumentoForm)form;
        tipoDocumentoService.saveTipoDocumento(tipoDocumentoForm.getTipoDocumento());
        tipoDocumentoForm.setTiposDocumento(tipoDocumentoService.listTipoDocumentos());
        //subir el nuevo tema al contexto del servidor
            request.getSession().getServletContext().removeAttribute(Constantes.TIPOS_DOCUMENTO);
            request.getSession().getServletContext().setAttribute(Constantes.TIPOS_DOCUMENTO,tipoDocumentoForm.getTiposDocumento());
        }catch(Exception e){
            log.debug("Error al guardar un tipo de documento desde la pantalla de captura", e);
            forward="ERROR";
        }
       return mapping.findForward(forward);
    }

    public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
       String forward="SUCCESS";
       try{
        TipoDocumentoForm tipoDocumentoForm= (TipoDocumentoForm)form;
        tipoDocumentoService.updateTipoDocumento(tipoDocumentoForm.getTipoDocumento());
        tipoDocumentoForm.setTiposDocumento(tipoDocumentoService.listTipoDocumentos());
        //subir el nuevo tema al contexto del servidor
            request.getSession().getServletContext().removeAttribute(Constantes.TIPOS_DOCUMENTO);
            request.getSession().getServletContext().setAttribute(Constantes.TIPOS_DOCUMENTO,tipoDocumentoForm.getTiposDocumento());
        }catch(Exception e){
            log.debug("Error al actualizar tipo de documento desde la pantalla de captura", e);
            forward="ERROR";
        }
       return mapping.findForward(forward);
    }

    public ActionForward baja(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
       String forward="SUCCESS";
       try{
        TipoDocumentoForm tipoDocumentoForm= (TipoDocumentoForm)form;
        TipoDocumento tipoDocumento=tipoDocumentoService.getTipoDocumento(tipoDocumentoForm.getTipoDocumento().getId_tipo_documento());
        tipoDocumento.setActivo(0);
        tipoDocumentoService.updateTipoDocumento(tipoDocumento);
        tipoDocumentoForm.setTiposDocumento(tipoDocumentoService.listTipoDocumentos());
         //subir el nuevo tema al contexto del servidor
            request.getSession().getServletContext().removeAttribute(Constantes.TIPOS_DOCUMENTO);
            request.getSession().getServletContext().setAttribute(Constantes.TIPOS_DOCUMENTO,tipoDocumentoForm.getTiposDocumento());
        }catch(Exception e){
            log.debug("Error al dar de baja un tipo de documento");
            forward="ERROR";
        }
       return mapping.findForward(forward);
    }

    public ActionForward getTipoDocumento(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception{
    	response.setContentType("text/json");
    		TipoDocumento objeto=tipoDocumentoService.getTipoDocumento(Integer.valueOf(request.getParameter("idTipoDocumento")));
			JSONObject jsonArray = JSONObject.fromObject(objeto);
			PrintWriter pw = response.getWriter();
			pw.write(jsonArray.toString());
			pw.flush();
			pw.close();
			return null;
    }

    public ActionForward isUsado(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception{
    	response.setContentType("text/json");
    		boolean objeto=tipoDocumentoService.isUsado(Integer.valueOf(request.getParameter("idTipoDocumento")));
		String result=objeto?"usado":"no usado";
                HashMap<String,Object> a=new HashMap<String,Object>();
                a.put("result",result);
                JSONObject jsonArray = JSONObject.fromObject(a);
			PrintWriter pw = response.getWriter();
			pw.write(jsonArray.toString());
			pw.flush();
			pw.close();
			return null;
    }

    /**
     * @param tipoDocumentoService the tipoDocumentoService to set
     */
    public void setTipoDocumentoService(TipoDocumentoService tipoDocumentoService) {
        this.tipoDocumentoService = tipoDocumentoService;
    }
}
