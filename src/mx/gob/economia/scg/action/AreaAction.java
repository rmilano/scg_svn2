package mx.gob.economia.scg.action;
 
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.economia.scg.form.AreaForm;
import mx.gob.economia.scg.model.Area;
import mx.gob.economia.scg.model.Empleado;
import mx.gob.economia.scg.service.ArbolService;
import mx.gob.economia.scg.service.AreaService;
import mx.gob.economia.scg.service.EmpleadoService;
import mx.gob.economia.scg.util.Constantes;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
//comentario
/**
 * DispatchAction para el tratamiento de asuntos
 * 
 * @author gerardo.roque
 */
public class AreaAction extends DispatchAction
{

    private ArbolService arbolService;
    private EmpleadoService empleadoService;
    private AreaService areaService;

    /**
     * Guarda un area
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward saveArea(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        String destino = "";
        try
        {
            AreaForm areaForm = (AreaForm) form;
            Area area = areaForm.getArea();
            area.setId_area_padre(area.getId_area());
            area.setArea(areaForm.getMetodo());            
            // Guarda el area en repositorio
            areaService.saveArea(area);

            areaForm.setArea(new Area());
            areaForm.setMetodo("");
            destino = "REGMOD";
        } catch (Exception ex)
        {
            destino = "ERROR";
            ex.printStackTrace();
            request.setAttribute("mensaje", "No se han podido guardar el área");
            return mapping.findForward(destino);
        }
        return mapping.findForward(destino);
    }

    /**
     * Guarda area utilizando Ajax
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward saveAreaAjax(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        Area area = new Area();
        try
        {
            // Asigna valores al area
            area.setActivo(Constantes.ACTIVO);
            area.setArea(request.getParameter("nombre"));
            area.setClave(request.getParameter("clave"));
            area.setId_area_padre(Integer.parseInt(request.getParameter("idAreaPadre")));
            area.setTipo(Integer.parseInt(request.getParameter("tipo")));

            // Guarda el area en repositorio
            this.areaService.saveArea(area);

        } catch (Exception e)
        {
            request.setAttribute("mensaje", "Ocurrio un erro al insertar el área.");
        }
        return null;
    }

    /**
     * Da de baja un area
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward bajaArea(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        String destino = "";
        try
        {
            AreaForm areaForm = (AreaForm) form;

            // Obtiene el area por medio del id
            Area area = areaService.getAreaById(areaForm.getArea().getId_area());

            // Asigna el area como no activa
            area.setActivo(Constantes.NO_ACTIVO);

            // Actualiza el area en repositorio
            areaService.updateArea(area);

            areaForm.setArea(new Area());
            destino = "REGMOD";
        } catch (Exception ex)
        {
            destino = "ERROR";
            ex.printStackTrace();
            request.setAttribute("mensaje", "No se ha podido eliminar el área");
            return mapping.findForward(destino);
        }
        return mapping.findForward(destino);
    }

    /**
     * Actualiza un area
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward updateArea(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        String destino = "";
        try
        {
            AreaForm areaForm = (AreaForm) form;
            Area area = areaForm.getArea();
            // Actualiza un area en repositorio
            areaService.updateArea(area);

            destino = "REGMOD";
        } catch (Exception ex)
        {
            destino = "ERROR";
            ex.printStackTrace();
            request.setAttribute("mensaje",
                    "No se ha podido actualizar el área");
            return mapping.findForward(destino);
        }
        return mapping.findForward(destino);
    }

    /**
     * Redirige al modulo de areas
     *
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
        String destino = "";
        try
        {
            destino = "REGMOD";
        } catch (Exception e)
        {
            destino = "ERROR";
            request.setAttribute("mensaje",
                    "Error interno, por favor intentelo mas tarde"
                    + e.getMessage());

        }
        return mapping.findForward(destino);
    }

    /**
     * Obtiene la informacion del area seleccionada
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward cargarArea(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        String destino = "";
        try
        {
            AreaForm areaForm = (AreaForm) form;
            destino = "REGMOD";

            // Obtiene el area por medio del id
            areaForm.setArea(areaService.getAreaById(areaForm.getArea().getId_area()));
        } catch (Exception e)
        {
            destino = "ERROR";
            request.setAttribute("mensaje",
                    "Error interno, por favor intentelo mas tarde"
                    + e.getMessage());

        }
        return mapping.findForward(destino);
    }

    /**
     * Obtiene las areas en funcion del area padre.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getAreasByParent(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        response.setContentType("text/json");
        PrintWriter pw = response.getWriter();
        String idArea = request.getParameter("idArea")!= null ? request.getParameter("idArea"):"-1";
        try
        {
            List<Area> areas = this.areaService.getAreasByIdPadre(Integer.parseInt(idArea));
            JSONArray json = JSONArray.fromObject(areas);
            pw.write(json.toString());
            pw.flush();
            pw.close();

        } catch (Exception e)
        {
            pw.write("error");
            pw.flush();
            pw.close();
        }

        return null;
    }

    /**
     * Obtiene el area por medio Ajax
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getArea(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        response.setContentType("text/json");

        // Obtiene el area por medio del id
        Area objeto = areaService.getAreaById(Integer.valueOf(request.getParameter("idArea")));
        JSONObject jsonArray = JSONObject.fromObject(objeto);
        PrintWriter pw = response.getWriter();
        pw.write(jsonArray.toString());
        pw.flush();
        pw.close();
        return null;
    }

    /**
     * Inicio del catalogo de areas
     *
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
        String destino = "";
        try
        {
            destino = "SUCCESS";
        } catch (Exception e)
        {
            destino = "ERROR";
            request.setAttribute("mensaje",
                    "Error interno, por favor intentelo mas tarde"
                    + e.getMessage());
        }
        return mapping.findForward(destino);
    }

    /**
     * Obtiene los empleados del area seleccionada por medio Ajax
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getEmpleadosByArea(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        response.setContentType("text/json");
        String idParameter = request.getParameter("idArea");
        String origen = request.getParameter("origen");
        idParameter = idParameter != null ? idParameter.trim() : "-1";
        Integer id = Integer.parseInt(idParameter);

        // Obtiene los empleados del area por medio del id_area
        List<Empleado> empleados = new ArrayList <Empleado>();
        origen = origen == null ? "" : origen;
        if (origen=="")
             empleados= this.empleadoService.listEmpleadosByIdArea(id);
        else if (origen.equals("remi"))
            empleados = this.empleadoService.listEmpleadosByIdAreaOrdRemi(id);
        else if (origen.equals("dest"))
            empleados = this.empleadoService.listEmpleadosByIdAreaOrdDest(id);
        else if (origen.equals("rede"))
            empleados = this.empleadoService.listEmpleadosByIdAreaOrdReDe(id);
        Empleado.asignaIdx(empleados);
        JSONArray json = JSONArray.fromObject(empleados);
        PrintWriter pw = response.getWriter();
        pw.write(json.toString());
        pw.flush();
        pw.close();
        return null;
    }

    /**
     * Valida si el area a dar de baja tiene elementos asociados
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward isUsada(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        response.setContentType("text/json");
        String result = new String();
        if (empleadoService.listEmpleadosByIdArea(
                (Integer.parseInt(request.getParameter("idArea")))).isEmpty())
        {
            result = "no usado";
        } else
        {
            result = "usado";
        }
        HashMap<String, Object> hm = new HashMap<String, Object>();
        hm.put("result", result);
        JSONObject json = JSONObject.fromObject(hm);
        PrintWriter pw = response.getWriter();
        pw.write(json.toString());
        pw.flush();
        pw.close();
        return null;
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
     * @param areaService
     *            the areaService to set
     */
    public void setAreaService(AreaService areaService)
    {
        this.areaService = areaService;
    }
}
