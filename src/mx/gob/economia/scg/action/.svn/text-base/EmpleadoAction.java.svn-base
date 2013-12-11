package mx.gob.economia.scg.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.gob.economia.scg.form.AreaForm;
import mx.gob.economia.scg.form.EmpleadoForm;
import mx.gob.economia.scg.model.Asunto;
import mx.gob.economia.scg.model.CriterioEmpleado;
import mx.gob.economia.scg.model.Empleado;
import mx.gob.economia.scg.model.Instruccion;
import mx.gob.economia.scg.model.Prioridad;
import mx.gob.economia.scg.model.Paginador;
import mx.gob.economia.scg.model.Rol;
import mx.gob.economia.scg.service.ArbolService;
import mx.gob.economia.scg.service.AreaService;
import mx.gob.economia.scg.service.EmpleadoService;
import mx.gob.economia.scg.service.InstruccionService;
import mx.gob.economia.scg.service.PrioridadService;
import mx.gob.economia.scg.util.Constantes;
import mx.gob.economia.scg.util.Util;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class EmpleadoAction extends DispatchAction
{

    private ArbolService arbolService;
    private EmpleadoService empleadoService;
    private AreaService areaService;
    private InstruccionService instruccionService;
    private PrioridadService prioridadService;

    /**
     * El inicio del catalogo de empleados
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception 
     */
    public ActionForward inicio(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        EmpleadoForm empleadoForm = (EmpleadoForm) form;
        String forward = "INICIO";
        try
        {
            empleadoForm.setEmpleadoCreado(new Empleado());
            empleadoForm.setCriterioEmpleado(new CriterioEmpleado());
            empleadoForm.setEmpleados(new ArrayList<Empleado>());
            empleadoForm.setRoles(this.empleadoService.listRoles());
        } catch (Exception e)
        {
            log.error("Ha ocurrido un error en el metodo inicio del empleado action", e);
        }
        return mapping.findForward(forward);
    }

    /**
     * La lista de empleados por criterio
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception 
     */
    public ActionForward getEmpleados(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        String forward = "INICIO";
        EmpleadoForm empleadoForm = (EmpleadoForm) form;
        try
        {
            empleadoForm.getCriterioEmpleado().setPaginador(new Paginador());

            Integer numRegistros = this.empleadoService.countEmpleadosByCriterio(empleadoForm.getCriterioEmpleado());
            empleadoForm.setEmpleados(new ArrayList<Empleado>());
            empleadoForm.getCriterioEmpleado().getPaginador().setNumRegistros(numRegistros);
            if (numRegistros>0)
                empleadoForm.setEmpleados(this.empleadoService.getEmpleadosByCriterio(empleadoForm.getCriterioEmpleado()));
        } catch (Exception e)
        {
            log.error("Ha ocurrido un error en el metodo getEmpleados del tema", e);
        }
        return mapping.findForward(forward);
    }

    /**
     * La lista de empleados por pagina
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception 
     */
    public ActionForward getEmpleadosPorPagina(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        String forward = "INICIO";
        EmpleadoForm empleadoForm = (EmpleadoForm) form;
        try
        {
            empleadoForm.setEmpleados(this.empleadoService.getEmpleadosByCriterio(empleadoForm.getCriterioEmpleado()));
        } catch (Exception e)
        {
            log.error("Ha ocurrido un error en el metodo getEmpleadosPorPagina del tema", e);
        }
        return mapping.findForward(forward);
    }

    /**
     * Inicio Editar empleado
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception 
     */
    public ActionForward inicioEditarEmpleado(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        String forward = "EDITAR";
        EmpleadoForm empleadoForm = (EmpleadoForm) form;
        try
        {
            //empleadoForm.setEmpleadoCreado(this.empleadoService.getEmpleadoById(empleadoForm.getEmpleadoCreado().getId_empleado()));
            empleadoForm.setEmpleadoCreado(this.empleadoService.getEmpleadoByIdWF(empleadoForm.getEmpleadoCreado().getId_empleado()));
            empleadoForm.setSelectedRoles(empleadoService.getSelectedRolesFromEmpleado(empleadoForm.getEmpleadoCreado().getRoles()));
        } catch (Exception e)
        {
            log.error("Ha ocurrido un error mientras se editaba el empleado", e);
        }
        return mapping.findForward(forward);
    }

    /**
     * Pantalla de inicio para la creacion de un nuevo empleado
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception 
     */
    public ActionForward inicioGuardarEmpleado(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        String forward = "EDITAR";
        EmpleadoForm empleadoForm = (EmpleadoForm) form;
        try
        {
            empleadoForm.setSelectedRoles(new Integer[Constantes.ACTIVADO]);
            empleadoForm.setEmpleadoCreado(new Empleado());
        } catch (Exception e)
        {
            log.error("Ha ocurrido un error en el metodo inicioGuardarEmpleado del EmpleadoAction", e);
        }
        return mapping.findForward(forward);
    }

    /**
     * Metodo para actualizar el empleado desde el catalogo
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception 
     */
    public ActionForward actualizarEmpleado(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        String forward = "INICIO";
        EmpleadoForm empleadoForm = (EmpleadoForm) form;
        try
        {
            //Si es empleado nuevo y ademas no existe
            Empleado empleado_aux = empleadoService.getEmpleado(empleadoForm.getEmpleadoCreado().getCorreo().trim());

            // El area del empleado
            empleadoForm.getEmpleadoCreado().setId_area(empleadoForm.getEmpleadoCreado().getArea().getId_area());
            //usuario activo
            empleadoForm.getEmpleadoCreado().setActivo(Constantes.ACTIVADO);

            //Si es usuario nuevo y no existe
            if (empleadoForm.getEmpleadoCreado().getId_empleado() == null && empleado_aux == null)
            {
                empleadoService.setRolEmpleadoForInternalUser(empleadoForm.getEmpleadoCreado());
                empleadoService.saveEmpleado(empleadoForm.getEmpleadoCreado());
            }
            else //actualizar empleado
            {
                // los roles del empleado
                empleadoForm.getEmpleadoCreado().setRoles(this.empleadoService.getRolListFromSelectedRol(empleadoForm.getSelectedRoles(), empleadoForm.getEmpleadoCreado()));

                //Los roles que selecciono el usuario
                this.empleadoService.updateEmpleado(empleadoForm.getEmpleadoCreado());
            }
        } catch (Exception e)
        {
            log.error("Ha ocurrido un error en el metodo actualizaEmpleado del empleadoAction", e);
        }
        response.sendRedirect("empleados.do?method=getEmpleadosPorPagina");
        return null;
    }

    /**
     * Elimina un empleado del catalogo
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception 
     */
    public ActionForward borrarEmpleado(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        String forward = "INICIO";
        EmpleadoForm empleadoForm = (EmpleadoForm) form;
        try
        {
            //Si es empleado nuevo y ademas no existe
            empleadoForm.setEmpleadoCreado(this.empleadoService.getEmpleadoById(empleadoForm.getEmpleadoCreado().getId_empleado()));
            empleadoForm.getEmpleadoCreado().setActivo(Constantes.NO_ACTIVO);
            this.empleadoService.updateEmpleado(empleadoForm.getEmpleadoCreado());
        } catch (Exception e)
        {
            log.error("Ha ocurrido un error en el metodo actualizaEmpleado del empleadoAction", e);
        }
        response.sendRedirect("empleados.do?method=getEmpleadosPorPagina");
        return null;
    }

    /**
     * Metodo inicio del catalogo
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception 
     */
    public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        String destino = "";
        try
        {
            AreaForm areaForm = (AreaForm) form;
            if (request.getParameter("order") == null)
            {
                areaForm.setEmpleado(new Empleado());
                areaForm.setEmpleados(new ArrayList<Empleado>());
                if (areaForm.getEmpleado().getRoles() == null
                        || areaForm.getEmpleado().getRoles().isEmpty())
                {
                    areaForm.getEmpleado().setRoles(empleadoService.listRoles());
                }
            }
            destino = "SUCCESS";
        } catch (Exception e)
        {
            log.error("Ha ocurrido un error->", e);
            destino = "ERROR";
            request.setAttribute("mensaje", "Error interno, por favor intentelo mas tarde" + e.getMessage());
        }
        return mapping.findForward(destino);
    }

    public ActionForward saveEmpleado(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        String destino = "";
        try
        {
            AreaForm areaForm = (AreaForm) form;
            Empleado empleado = areaForm.getEmpleado();

            Empleado empleado_aux = empleadoService.getEmpleado(empleado.getCorreo().trim());
            if (empleado_aux != null)
            {
                String errorGuardarEmpl = "El empleado con correo " + empleado_aux.getCorreo().trim() + ", ya ha sido registrado en el area " + empleado_aux.getArea().getArea();
                request.setAttribute("errorGuardarEmpl", errorGuardarEmpl);
                destino = "SUCCESS";
                return mapping.findForward(destino);
            }
            int idArea = empleado.getId_area();
            List<Rol> roles = new ArrayList<Rol>();
            if (areaForm.getSelectedItems() != null)
            {
                for (Integer id_rol : areaForm.getSelectedItems())
                {
                    Rol r = new Rol();
                    r.setId_rol(id_rol);
                    roles.add(r);
                }
            }
            empleado.setRoles(roles);
            empleadoService.saveEmpleado(empleado);
            if (areaForm.getEmpleado().getId_area() != null)
            {
                areaForm.setEmpleados(empleadoService.listEmpleadosByIdArea(areaForm.getEmpleado().getId_area()));
            }
            areaForm.setEmpleado(new Empleado());
            areaForm.getEmpleado().setId_area(idArea);
            if (areaForm.getEmpleado().getRoles() == null
                    || areaForm.getEmpleado().getRoles().isEmpty())
            {
                areaForm.getEmpleado().setRoles(empleadoService.listRoles());
            }
            destino = "SUCCESS";
        } catch (Exception ex)
        {
            destino = "ERROR";
            log.error("Ha ocurrido un error->", ex);
            request.setAttribute("mensaje", "No se han podido dar de alta al Empleado");
            return mapping.findForward(destino);
        }
        return mapping.findForward(destino);
    }

    /*Guardar empleado por peticion asincrona (ajax)*/
    public ActionForward saveEmpleadoAsin(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        Empleado empleado = new Empleado();
        List<Rol> roles = new ArrayList<Rol>();
        Rol rol = new Rol();
        try
        {
            rol.setId_rol(Constantes.ROL_EMPLEADO);
            roles.add(rol);
            empleado.setActivo(Constantes.ACTIVO);
            empleado.setCorreo(request.getParameter("email"));
            empleado.setId_area(Integer.parseInt(request.getParameter("idArea")));
            empleado.setNombre(request.getParameter("nombre"));
            empleado.setPaterno(request.getParameter("paterno"));
            empleado.setMaterno(request.getParameter("materno"));
            empleado.setPuesto(request.getParameter("puesto"));
            empleado.setRfc(request.getParameter("rfc"));
            empleado.setRoles(roles);

            Empleado empleado_aux = empleadoService.getEmpleado(empleado.getCorreo().trim());
            if (empleado_aux != null)
            {
                String errorGuardarEmpl = "El empleado con correo " + empleado_aux.getCorreo().trim() + ", ya ha sido registrado en el area " + empleado_aux.getArea().getArea();
                request.setAttribute("errorGuardarEmpl", errorGuardarEmpl);
                throw new Exception(errorGuardarEmpl);
            }
            this.empleadoService.saveEmpleado(empleado);
        } catch (Exception e)
        {
            log.error("Ha ocurrido un error->", e);
            request.setAttribute("mensaje", e.getMessage());
        }
        return null;
    }

    public ActionForward bajaEmpleado(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        String destino = "";
        try
        {
            AreaForm areaForm = (AreaForm) form;
            Empleado empleado = empleadoService.getEmpleadoById(areaForm.getEmpleado().getId_empleado());
            empleado.setActivo(0);
            int idArea = empleado.getId_area();
            empleadoService.updateEmpleado(empleado);
            if (areaForm.getEmpleado().getId_area() != null)
            {
                areaForm.setEmpleados(empleadoService.listEmpleadosByIdArea(areaForm.getEmpleado().getId_area()));
            }
            if (areaForm.getEmpleado().getRoles() == null
                    || areaForm.getEmpleado().getRoles().isEmpty())
            {
                areaForm.getEmpleado().setRoles(empleadoService.listRoles());
            }
            areaForm.setEmpleado(new Empleado());
            areaForm.getEmpleado().setId_area(idArea);
            destino = "SUCCESS";
        } catch (Exception ex)
        {
            destino = "ERROR";
            log.error("Ha ocurrido un error->", ex);
            request.setAttribute("mensaje", "No se ha podido dar de baja al Empleado");
            return mapping.findForward(destino);
        }
        return mapping.findForward(destino);
    }

    public ActionForward updateEmpleado(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        String destino = "";
        try
        {
            AreaForm areaForm = (AreaForm) form;
            Empleado empleado = areaForm.getEmpleado();
            List<Rol> roles = new ArrayList<Rol>();
            int idArea = empleado.getId_area();
            if (areaForm.getSelectedItems() != null)
            {
                for (Integer id_rol : areaForm.getSelectedItems())
                {
                    Rol r = new Rol();
                    r.setId_rol(id_rol);
                    roles.add(r);
                }
            }
            empleado.setRoles(roles);
            empleadoService.updateEmpleado(empleado);
            if (areaForm.getEmpleado().getId_area() != null)
            {
                areaForm.setEmpleados(empleadoService.listEmpleadosByIdArea(areaForm.getEmpleado().getId_area()));
            }
            areaForm.setEmpleado(new Empleado());
            areaForm.getEmpleado().setId_area(idArea);
            if (areaForm.getEmpleado().getRoles() == null
                    || areaForm.getEmpleado().getRoles().isEmpty())
            {
                areaForm.getEmpleado().setRoles(empleadoService.listRoles());
            }
            destino = "SUCCESS";
        } catch (Exception ex)
        {
            destino = "ERROR";
            log.error("Ha ocurrido un error->", ex);
            request.setAttribute("mensaje", "No se han podido actualizar los datos del Empleado");
            return mapping.findForward(destino);
        }
        return mapping.findForward(destino);
    }

    public ActionForward listarEmpleados(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        String destino = "";
        try
        {
            AreaForm areaForm = (AreaForm) form;
            areaForm.setEmpleados(empleadoService.listEmpleadosByIdArea(areaForm.getEmpleado().getId_area()));
            if (areaForm.getEmpleado().getRoles() == null
                    || areaForm.getEmpleado().getRoles().isEmpty())
            {
                areaForm.getEmpleado().setRoles(empleadoService.listRoles());
            }
            destino = "SUCCESS";
        } catch (Exception ex)
        {
            destino = "ERROR";
            log.error("Ha ocurrido un error->", ex);
            request.setAttribute("mensaje", "Error al acceder a los datos");
            return mapping.findForward(destino);
        }
        return mapping.findForward(destino);
    }

    public ActionForward getEmpleado(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        response.setContentType("text/json");
        Empleado objeto = empleadoService.getEmpleadoById(Integer.valueOf(request.getParameter("idEmpleado")));
        JSONObject jsonArray = JSONObject.fromObject(objeto);
        PrintWriter pw = response.getWriter();
        pw.write(jsonArray.toString());
        pw.flush();
        pw.close();
        return null;
    }

    public ActionForward isUsado(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        response.setContentType("text/json");
        boolean objeto = empleadoService.isUsado(Integer.valueOf(request.getParameter("idEmpleado")));
        HashMap<String, Object> hm = new HashMap<String, Object>();
        String a = objeto ? "usado" : "no usado";
        hm.put("result", a);
        JSONObject jsonArray = JSONObject.fromObject(hm);
        PrintWriter pw = response.getWriter();
        pw.write(jsonArray.toString());
        pw.flush();
        pw.close();
        return null;
    }

    public ActionForward getInstrucciones(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
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
    
    public ActionForward getPrioridades(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        response.setContentType("text/json");        
        List<Prioridad> prioridades = this.prioridadService.listPrioridades();
        prioridades=asignarFhLimite(prioridades);
        JSONArray jsonArray = JSONArray.fromObject(prioridades);
        PrintWriter printWriter = response.getWriter();
        printWriter.write(jsonArray.toString());
        printWriter.flush();
        printWriter.close();
        return null;
    }

    public List<Prioridad> asignarFhLimite(List<Prioridad> prioridades)
    {
        List<Prioridad> prioridadesCF = new ArrayList<Prioridad>();
        for (Prioridad prioridad : prioridades)
        {
            if (!(prioridad.getDias()==null))
            {
                //Si el no. de dias es mayor a cero, entonces asignar el rango en dias correspondientes
                if (prioridad.getDias() > Constantes.FIRST)
                {
                    Date fh_limite = Util.fechaHabil(new GregorianCalendar(), prioridad.getDias()).getTime();
                    prioridad.setFh_limite(fh_limite);
                    prioridad.setFh_limiteDDMMYYYY(Util.formatearFecha(fh_limite));
                }
                // si el no. de dias para la prioridad seleccionada es 0, entonces no existe una prioridad, se asigna nulo
                if (prioridad.getDias().equals(Constantes.FIRST))
                {
                    prioridad.setFh_limite(null);
                    prioridad.setFh_limiteDDMMYYYY(null);
                }
            }
            prioridadesCF.add(prioridad);
        }
    return prioridadesCF;

        // Si el no. de dias es menor a cero, sigfica que el usuario selecciono la fecha limite de contestacion del documento
        // viene definido en el asunto.getAsunto_detalle().setFh_limite, no hay nada que hacer
    }

    public void setArbolService(ArbolService arbolService)
    {
        this.arbolService = arbolService;
    }

    public void setEmpleadoService(EmpleadoService empleadoService)
    {
        this.empleadoService = empleadoService;
    }

    public void setAreaService(AreaService areaService)
    {
        this.areaService = areaService;
    }

    /**
     * @param instruccionService the instruccionService to set
     */
    public void setInstruccionService(InstruccionService instruccionService)
    {
        this.instruccionService = instruccionService;
    }

	public void setPrioridadService(PrioridadService prioridadService) {
		this.prioridadService = prioridadService;
	}
    
}
