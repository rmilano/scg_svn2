package mx.gob.economia.scg.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.gob.economia.scg.form.InstruccionForm;
import mx.gob.economia.scg.model.CriterioInstruccion;
import mx.gob.economia.scg.model.Instruccion;
import mx.gob.economia.scg.model.Paginador;
import mx.gob.economia.scg.service.AreaService;
import mx.gob.economia.scg.service.InstruccionService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class InstruccionAction extends DispatchAction {

    
    private AreaService areaService;
    private InstruccionService instruccionService;

    public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String destino = "SUCCESS";
        try {
            InstruccionForm instruccionForm = (InstruccionForm) form;
            instruccionForm.setInstruccion(new Instruccion());
            instruccionForm.setInstrucciones(new ArrayList<Instruccion>());
            instruccionForm.setCriterioInstruccion(new CriterioInstruccion());
        } catch (Exception e) {
            destino = "ERROR";
            request.setAttribute("mensaje", "Error interno, por favor intentelo mas tarde" + e.getMessage());
        }
        return mapping.findForward(destino);
    }

    public ActionForward saveInstruccion(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String destino = "SUCCESS";
        try {
            InstruccionForm instruccionForm = (InstruccionForm) form;
            instruccionService.saveInstruccion(instruccionForm.getInstruccion());
            instruccionForm.setInstrucciones(this.instruccionService.listInstruccionesByPagina(instruccionForm.getCriterioInstruccion()));
            instruccionForm.setInstruccion(new Instruccion());
            
        } catch (Exception ex) {
            destino = "ERROR";
            request.setAttribute("mensaje", "No se han podido dar de alta la Instrucción");
        }
        return mapping.findForward(destino);
    }

   

    public ActionForward bajaInstruccion(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String destino = "SUCCESS";
        try {
            InstruccionForm instruccionForm = (InstruccionForm) form;
            instruccionForm.setInstruccion(
                    instruccionService.getInstruccion(
                    instruccionForm.getInstruccion().getId_instruccion()));
            instruccionForm.getInstruccion().setActivo(0);
            instruccionService.updateInstruccion(instruccionForm.getInstruccion());
            instruccionForm.getCriterioInstruccion().setPaginador(new Paginador());
            instruccionForm.getCriterioInstruccion().getPaginador().setNumRegistros(
                    this.instruccionService.countInstruccionesByCriterio(instruccionForm.getCriterioInstruccion()));
            instruccionForm.setInstrucciones(this.instruccionService.listInstruccionesByPagina(instruccionForm.getCriterioInstruccion()));
            instruccionForm.setInstruccion(new Instruccion());
        } catch (Exception ex) {
            destino = "ERROR";
            request.setAttribute("mensaje", "No se ha podido dar de baja la Instrucción");
        }
        return mapping.findForward(destino);
    }

    public ActionForward updateInstruccion(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String destino = "SUCCESS";
        try {
            InstruccionForm instruccionForm = (InstruccionForm) form;
            instruccionService.updateInstruccion(instruccionForm.getInstruccion());
            instruccionForm.setInstrucciones(this.instruccionService.listInstruccionesByPagina(instruccionForm.getCriterioInstruccion()));
            instruccionForm.setInstruccion(new Instruccion());
            
        } catch (Exception ex) {
            destino = "ERROR";
            request.setAttribute("mensaje", "No se ha podido actualizar la instruccion");
        }
        return mapping.findForward(destino);
    }
    
    public ActionForward listarInstrucciones(ActionMapping mapping,ActionForm form,HttpServletRequest request,
            HttpServletResponse response){
        String destino="";
        try{
            InstruccionForm instruccionForm = (InstruccionForm) form;
            instruccionForm.getCriterioInstruccion().setPaginador(new Paginador());
            instruccionForm.getCriterioInstruccion().getPaginador().setNumRegistros(
                    this.instruccionService.countInstruccionesByCriterio(instruccionForm.getCriterioInstruccion()));
            instruccionForm.setInstrucciones(this.instruccionService.listInstruccionesByPagina(instruccionForm.getCriterioInstruccion()));
            destino="SUCCESS";
        }catch(Exception ex){
                request.setAttribute("mensaje", "No se pueden listar las instrucciones");
                destino="ERROR";
        }
        return mapping.findForward(destino);
    }

    public ActionForward listarInstruccionesByPagina(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String destino = "";
        try {
            InstruccionForm instruccionForm = (InstruccionForm) form;
            if(!(instruccionForm.getCriterioInstruccion().getInstruccion().getArea().getArea().isEmpty()&&
                    instruccionForm.getCriterioInstruccion().getInstruccion().getInstruccion().isEmpty()))
            instruccionForm.setInstrucciones(
                    instruccionService.listInstruccionesByPagina(instruccionForm.getCriterioInstruccion()));
            destino = "SUCCESS";
        } catch (Exception ex) {
            destino = "ERROR";
            request.setAttribute("mensaje", "Error al acceder a los datos");
        }
        return mapping.findForward(destino);
    }

    public ActionForward getInstruccion(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        response.setContentType("text/json");
        Instruccion objeto = instruccionService.getInstruccion(Integer.valueOf(request.getParameter("id_instruccion")));
        objeto.setArea(areaService.getAreaById(objeto.getId_area()));
        JSONObject jsonArray = JSONObject.fromObject(objeto);
        PrintWriter pw = response.getWriter();
        pw.write(jsonArray.toString());
        pw.flush();
        pw.close();
        return null;
    }

    public ActionForward getInstrucciones(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        response.setContentType("text/json");
        Integer idArea = request.getParameter("idArea") == null ? -1 : Integer.parseInt(request.getParameter("idArea"));
        List<Instruccion> instrucciones = this.instruccionService.listInstrucciones(idArea);
        JSONArray jsonArray = JSONArray.fromObject(instrucciones);
        PrintWriter printWriter = response.getWriter();
        printWriter.write(jsonArray.toString());
        printWriter.flush();
        printWriter.close();
        return null;
    }
    

    public void setAreaService(AreaService areaService) {
        this.areaService = areaService;
    }

    /**
     * @param instruccionService the instruccionService to set
     */
    public void setInstruccionService(InstruccionService instruccionService) {
        this.instruccionService = instruccionService;
    }
}
