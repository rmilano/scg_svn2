package mx.gob.economia.scg.action;

import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.economia.scg.form.AsuntoForm;
import mx.gob.economia.scg.model.Asunto;
import mx.gob.economia.scg.model.AsuntoDetalle;
import mx.gob.economia.scg.model.Documento;
import mx.gob.economia.scg.model.Empleado;
import mx.gob.economia.scg.model.EstadoAsunto;
import mx.gob.economia.scg.model.Instruccion;
import mx.gob.economia.scg.model.Reporte;
//import mx.gob.economia.scg.model.AsuntoCapturaActualizacion;
import mx.gob.economia.scg.model.BitacoraAsunto;
import mx.gob.economia.scg.model.Prioridad;
import mx.gob.economia.scg.service.ArbolService;
import mx.gob.economia.scg.service.AreaService;
import mx.gob.economia.scg.service.AsuntoEstadoService;
import mx.gob.economia.scg.service.AsuntoService;
import mx.gob.economia.scg.service.AsuntoRevisionService;
import mx.gob.economia.scg.service.DocumentoService;
import mx.gob.economia.scg.service.EmpleadoService;
import mx.gob.economia.scg.service.InstruccionService;
import mx.gob.economia.scg.service.PrioridadService;
import mx.gob.economia.scg.service.ReporteVolanteService;
import mx.gob.economia.scg.service.TipoSeguimientoService;
import mx.gob.economia.scg.util.Constantes;
import mx.gob.economia.scg.util.Util;
import mx.gob.economia.scg.util.UtilsReportes;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.json.JSONObject;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 * DispatchAction para el tratamiento de asuntos
 * 
 * @author valentin.gomez
 */
public class AsuntoAction extends DispatchAction
{

    AsuntoService asuntoService;
    EmpleadoService empleadoService;
    ArbolService arbolService;
    DocumentoService documentoService;
    PrioridadService prioridadService;
    private AreaService areaService;
    private InstruccionService instruccionService;
    private AsuntoRevisionService asuntoRevisionService;
    private AsuntoEstadoService asuntoEstadoService;
    private TipoSeguimientoService tipoSeguimientoService;
    private ReporteVolanteService reporteVolanteService;

    /**
     * Muestra pagina inicial de asuntos
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return pantalla inicial de asunto
     */
    public ActionForward inicio(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
    {
        String destino = "DETALLE";
        AsuntoForm aF = (AsuntoForm) form;
        // Obtiene los tipos de documento y los sube a sesion
        documentoService.loadTiposDocumento(request);
        Empleado usuarioSession = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);
        aF.setEmpleadoCaptura(usuarioSession);
        return mapping.findForward(destino);
    }

    /**
     * EnvÃ­a un asunto a revisiÃ³n. Lo unico que cambia es el estatus del asunto
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward enviarRevision(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        try
        {
            AsuntoForm asuntoForm = (AsuntoForm) form;
            this.asuntoRevisionService.enviarAsuntoRevision(asuntoForm.getAsunto().getFolio());
            response.sendRedirect("consulta-captura.do?method=inicio");
        } catch (Exception e)
        {
            log.debug("Ha ocurrido un error mientras el asunto se envia a revision");
            log.error("Se ha producido un error  ->", e);
        }
        return null;
    }

    /**
     * Remove el volante de correspondencia
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward removeVolante(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        try
        {
            request.getSession().removeAttribute(Constantes.VOLANTE_CORRESPONDENCIA);
        } catch (Exception e)
        {
            log.debug("Error al remover el volante de la sesion");
            log.error("Se ha producido un error  ->", e);
        }
        return null;
    }

    /**
     * EnvÃ­a un asunto al supervisor, en este caso, se entiende que el area del capturista
     * no es el area del secretario. Lo manda a su jefe para que lo valide y lo firme
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward enviarSupervision(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        try
        {
            AsuntoForm asuntoForm = (AsuntoForm) form;
            this.asuntoRevisionService.enviarAsuntoSupervicion(asuntoForm.getAsunto().getFolio());
            response.sendRedirect("consulta-captura.do?method=inicio");
        } catch (Exception e)
        {
            log.debug("Ha ocurrido un error mientras el asunto se envia a revision");
            log.error("Se ha producido un error  ->", e);
        }
        return null;
    }

    /**
     * El supervisor envÃ­a el asunto nuevamente al capturista, en este caso, se entiende que el area del capturista
     * no es el area del secretario. Lo manda a su jefe para que lo valide y lo firme
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward enviarCaptura(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        try
        {
            AsuntoForm asuntoForm = (AsuntoForm) form;
            this.asuntoRevisionService.setAsuntoSupervisado(asuntoForm.getAsunto().getFolio());
            response.sendRedirect("consulta-supervisar.do?method=inicio");
        } catch (Exception e)
        {
            log.debug("Ha ocurrido un error mientras el asunto se envia a revision");
            log.error("Se ha producido un error  ->", e);
        }
        return null;
    }

    /**
     * El capturista envia los asunto a los destinatarios. el asunto pasa al estatus 0:Pendiente
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward enviarAsuntoDestinatarios(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        try
        {
            AsuntoForm asuntoForm = (AsuntoForm) form;
            //asuntoForm.setAsunto(asuntoService.getAsuntoDetalleById(asuntoForm.getId_asunto()));
            Integer sizeAsunto = asuntoForm.getAsunto().getAsuntos_detalles().size();
            if (sizeAsunto > Constantes.FIRST)
                asuntoForm.getAsunto().setAsunto_detalle(asuntoForm.getAsunto().getAsuntos_detalles().get(sizeAsunto - Constantes.ACTIVADO));
            this.reporteVolanteService.generarVolantesCorrespondenciaPDF(request, asuntoForm.getAsunto());
            String RUTA_LOGO = ResourceBundle.getBundle(Constantes.PROPERTIES).getString("ruta.reporte.logo");
            URL urlLogo = this.getServlet().getServletContext().getResource(RUTA_LOGO);
            this.asuntoRevisionService.enviarAsuntoDestinatario(asuntoForm.getAsunto(),urlLogo, request);
            response.sendRedirect("consulta-captura.do?method=inicio");
        } catch (Exception e)
        {
            log.debug("Ha ocurrido un error mientras el asunto se envia a revision");
            log.error("Se ha producido un error  ->", e);
        }
        return null;
    }

    /**
     * Obtiene el detalle de un asunto seleccionado
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return pantalla con detalle del asunto
     */
    public ActionForward verDetalle(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        AsuntoForm aF = (AsuntoForm) form;
        String destino = "ERROR";
        try
        {
            // Obtiene el empleado registrado en el sistema
            Empleado empleado_ses = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);

            // Obtiene los tipos de documento y los sube a sesion
            documentoService.loadTiposDocumento(request);

            // Borra las banderas para mostrar botones en el detalle del asunto
            asuntoService.removeButtonsDetalle(request);

            // Busca el asunto por medio del id
            Integer id_asunto_param = request.getParameter("id_asunto") != null ? Integer.valueOf(request.getParameter("id_asunto"))
                    : null;
            Integer id_asunto = aF.getId_asunto() != null ? aF.getId_asunto()
                    : id_asunto_param;

            if (request.getParameter("id_asunto") != null)
                id_asunto = Integer.valueOf(request.getParameter("id_asunto"));
            Asunto asunto = asuntoService.getAsunto(id_asunto);

            /*List<AsuntoCapturaActualizacion> actualizaciones_descripcion = new ArrayList<AsuntoCapturaActualizacion>();
            String descripcion = "";
            for (AsuntoCapturaActualizacion actualizacion : asunto.getActualizacionesCaptura()){                
                if (!actualizacion.getDescripcion().equals(descripcion)){
                    actualizaciones_descripcion.add(actualizacion);
                }
                descripcion = actualizacion.getDescripcion();
            }
            asunto.setActualizacionesDescripcion(actualizaciones_descripcion);*/
            
            aF.setAsunto(asunto);

            // Obtiene si el empleado en sesion es el destinatario del asunto
            boolean isEmpleadoDest = asuntoService.isEmpleadoDestinatario(
                    asunto, request);

            // Si el empleado registrado es el destinatario del asunto
            if (isEmpleadoDest)
                asuntoService.updateFhLectura(asunto, request);
            // Si el empleado registrado esta copiado en el asunto

            boolean isCcp = asunto.getEmpleadosCcp().contains(empleado_ses);
            if (isCcp)
                asuntoService.updateFhLecturaCcp(asunto, request);

            // Sube a sesion las banderas para mostrar botones en el detalle
            asuntoService.loadBtnDetalleAsunto(asunto, request);
            Integer id_prioridad = -1;
            for (Integer i=0; i<asunto.getAsuntos_detalles().size(); i++) {
                id_prioridad=asunto.getAsuntos_detalles().get(i).getPrioridad().getId_prioridad()==null ? -1 : asunto.getAsuntos_detalles().get(i).getPrioridad().getId_prioridad();
                if (id_prioridad==-1){
                    asunto.getAsuntos_detalles().get(i).setPrioridad(new Prioridad ());
                }
                else{
                    asunto.getAsuntos_detalles().get(i).setPrioridad(prioridadService.getPrioridad(id_prioridad));
                }
            }
            asunto.setPrioridad(asunto.getAsuntos_detalles().get(0).getPrioridad());

            //aF.getAsunto().setDescripcion(aF.getAsunto().getDescripcion().replaceAll("\n", "<br>"));

            //El ultimo detalle a la mano
            if (aF.getAsunto().getAsuntos_detalles().size() > Constantes.FIRST)
                aF.getAsunto().setUltimaDetalle(aF.getAsunto().getAsuntos_detalles().get(aF.getAsunto().getAsuntos_detalles().size() - Constantes.ACTIVADO));
            if (aF.getAsunto().getAsuntoAtencionParcial()!=null){
                aF.getAsunto().getAsuntoAtencionParcial().setEstatus_desc("EN TRAMITE");
                aF.getAsunto().getAsuntoAtencionParcial().getEmpleado_dest().setComentario(aF.getAsunto().getAsuntoAtencionParcial().getComentario());
                aF.getAsunto().getAsuntos_detalles().add(aF.getAsunto().getAsuntoAtencionParcial());
            }
            destino = "DETALLE";
            //destino = "ERROR";
        } catch (Exception e)
        {
            log.error("Se ha producido un error  ->", e);
            request.setAttribute("mensaje",
                    "Error interno. Por favor, intentelo otra vez en unos minutos./n"+ e);
        }
        return mapping.findForward(destino);
    }

    /**
     * Muestra la modulo para atender un asunto
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return pantalla para atender un asunto
     */
    public ActionForward inicioAtender(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
    {
        AsuntoForm aF = (AsuntoForm) form;
        String destino = "ERROR";
        try
        {
            EstadoAsunto estadoAsunto = this.asuntoEstadoService.isBusyMatter(request, aF.getAsunto().getId_asunto(), "Atendido");
            Empleado usuarioSession = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);
            if (!estadoAsunto.getIsBusy() || estadoAsunto.getEmail().equals(usuarioSession.getCorreo()))// Si el asunto no esta en uso por otro usuario
            //if (!estadoAsunto.getIsBusy())// Si el asunto no esta en uso
            {
                // Agrega un nuevo detalle de asunto
                AsuntoDetalle detalle = new AsuntoDetalle();
                if (aF.getAsunto().getAtencionParcial()==1){
                    detalle.setComentario(aF.getAsunto().getAsuntoAtencionParcial().getComentarioSaltos());
                    detalle.setDocumentos(aF.getAsunto().getAsuntoAtencionParcial().getDocumentos());
                    detalle.setEmpleadosCcpDetalle(aF.getAsunto().getAsuntoAtencionParcial().getEmpleadosCcpDetalle());
                }

                aF.getAsunto().setAsunto_detalle(detalle);
                destino = "ATENDER";
            } else
            {
                request.getSession().setAttribute(Constantes.MSJ_ASUNTO_USO, estadoAsunto.getMessage());
                response.sendRedirect("asunto.do?method=verDetalle&id_asunto=" + aF.getAsunto().getId_asunto());
                request.setAttribute("mensaje",estadoAsunto.getMessage());
            }
        } catch (Exception e)
        {
            log.error("Se ha producido un error  ->", e);
            request.setAttribute("mensaje",
                    "Error interno. Por favor, intentelo otra vez en unos minutos.");
        }
        return mapping.findForward(destino);
    }

    /**
     * Muestra la modulo para dar visto bueno a un asunto
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return pantalla para dar visto bueno a un asunto
     */
    public ActionForward inicioDarVoBo(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
    {
        AsuntoForm aF = (AsuntoForm) form;
        String destino = "ERROR";
        try
        {
            // Agrega un nuevo detalle de asunto
            aF.getAsunto().setAsunto_detalle(new AsuntoDetalle());

            // Muestra el boton con la etiqueta de visto bueno
            request.setAttribute("showDarVoBoBtn", "showDarVoBoBtn");
            destino = "ATENDER";
        } catch (Exception e)
        {
            log.error("Se ha producido un error  ->", e);
            request.setAttribute("mensaje",
                    "Error interno. Por favor, intentelo otra vez en unos minutos.");
        }
        return mapping.findForward(destino);
    }

    /**
     * Muestra la modulo para turnar un asunto
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return pantalla para turnar asunto
     */
    public ActionForward inicioTurnar(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
    {
        AsuntoForm aF = (AsuntoForm) form;

        String destino = "ERROR";
        try
        {
            EstadoAsunto estadoAsunto = this.asuntoEstadoService.isBusyMatter(request, aF.getAsunto().getId_asunto(), "turnado");
            Empleado usuarioSession = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);
            if (!estadoAsunto.getIsBusy() || estadoAsunto.getEmail().equals(usuarioSession.getCorreo()))// Si el asunto no esta en uso por otro usuario
            {                
                aF.setEmpleadoCaptura(usuarioSession);

                //aF.getAsunto().setAsunto_detalle(new AsuntoDetalle());
                aF.getAsunto().setAsunto_detalle(this.getTurnoGuardado(aF.getAsunto().getUltimaDetalle().getId_asunto_detalle(),usuarioSession.getId_empleado()));
                List <Empleado> destinatariosGuardados = new ArrayList <Empleado>();
                destinatariosGuardados.addAll(aF.getAsunto().getAsunto_detalle().getEmpleadosDest());
                List <Empleado> empleadosCopiados = new ArrayList <Empleado>();
                empleadosCopiados.addAll(aF.getAsunto().getAsunto_detalle().getEmpleadosCcp());
                aF.getAsunto().getAsunto_detalle().getEmpleadosDest().clear();
                //aF.getEmpleadosDestinatario().clear();
                aF.getAsunto().getAsunto_detalle().setComentario(aF.getAsunto().getDescripcion());
                //aF.setEmpleadosDestinatario(new ArrayList<Empleado>());
                aF.setAreasDependientes(this.areaService.getAreasFromDespĺiegue(usuarioSession.getArea()));
                aF.setInstrucciones(this.instruccionService.listInstrucciones(usuarioSession.getId_area()));
                aF.setTiposSeguimiento(this.tipoSeguimientoService.listTipoSeguimientos());
                //aF.setFirmantesVolante(this.empleadoService.listEmpleadosByIdArea(usuarioSession.getId_area()));
                // aF.setFirmantesVolante(this.empleadoService.listEmpleadosByIdAreaOrdFirm(usuarioSession.getId_area()));
                aF.setFirmantesVolanteRol(this.empleadoService.listByIdAreaOrderByFirmanteRol(usuarioSession.getId_area()));
                aF.setEmpleadosDestinatario(destinatariosGuardados);
                aF.getAsunto().getAsunto_detalle().setEmpleadosCcp(empleadosCopiados);                
                if (request.getSession().getAttribute(Constantes.PRIORIDADES) == null)
                    request.getSession().setAttribute(Constantes.PRIORIDADES,
                            prioridadService.listPrioridades());
                destino = "TURNAR";
            } else
            {
                request.getSession().setAttribute(Constantes.MSJ_ASUNTO_USO, estadoAsunto.getMessage());
                response.sendRedirect("asunto.do?method=verDetalle&id_asunto=" + aF.getAsunto().getId_asunto());
            }
        } catch (Exception e)
        {
            log.error("Se ha producido un error  ->", e);
            request.setAttribute("mensaje", "Error interno. Por favor, intentelo otra vez en unos minutos.");
        }
        return mapping.findForward(destino);
    }

    /**
     * Muestra el modulo para editar un turno
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return pantalla para turnar asunto
     */
    public ActionForward inicioEditarTurno(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
    {
        AsuntoForm aF = (AsuntoForm) form;

        String destino = "ERROR";
        try
        {
            EstadoAsunto estadoAsunto = this.asuntoEstadoService.isBusyMatter(request, aF.getAsunto().getId_asunto(), "editando turno");
            Empleado usuarioSession = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);
            if (!estadoAsunto.getIsBusy() || estadoAsunto.getEmail().equals(usuarioSession.getCorreo()))// Si el asunto no esta en uso por otro usuario
            {
                aF.setEmpleadoCaptura(usuarioSession);
                Integer id_asunto_detalle = Integer.valueOf(request.getParameter("p_idAsuntoDetalle"));
                aF.getAsunto().setAsunto_detalle(this.asuntoService.getDetalleByIdAsuntoDetalle(id_asunto_detalle));
                //aF.getAsunto().getAsunto_detalle().getEmpleadosDest().add(aF.getAsunto().getAsunto_detalle().getEmpleado_dest());
                aF.getEmpleadosDestinatario().clear();                
                /*request.setAttribute("id_empleado_destino",aF.getAsunto().getAsunto_detalle().getEmpleado_dest().getId_empleado());
                request.setAttribute("nombre_empleado_destino",aF.getAsunto().getAsunto_detalle().getEmpleado_dest().getNombre_completo());
                request.setAttribute("id_area_emp_capt",usuarioSession.getId_area());*/
                aF.setInstrucciones(this.instruccionService.listInstrucciones(usuarioSession.getId_area()));
                aF.setTiposSeguimiento(this.tipoSeguimientoService.listTipoSeguimientos());
                //aF.setFirmantesVolante(this.empleadoService.listEmpleadosByIdArea(usuarioSession.getId_area()));
                aF.setFirmantesVolante(this.empleadoService.listEmpleadosByIdAreaOrdFirm(usuarioSession.getId_area()));
                aF.setFirmantesVolanteRol(this.empleadoService.listByIdAreaOrderByFirmanteRol(usuarioSession.getId_area()));
                if (aF.getAsunto().getAsunto_detalle().getId_instruccion()==null){
                    aF.getAsunto().getAsunto_detalle().setId_instruccion(aF.getAsunto().getId_instruccion());
                }
                aF.getAsunto().getAsunto_detalle().getEmpleado_dest().getPrioridad().setId_prioridad(aF.getAsunto().getAsunto_detalle().getPrioridad().getId_prioridad());
                if (aF.getAsunto().getAsunto_detalle().getEmpleado_dest().getPrioridad().getId_prioridad()==null){
                    aF.getAsunto().getAsunto_detalle().getEmpleado_dest().getPrioridad().setId_prioridad(-1);
                }
                aF.getAsunto().getAsunto_detalle().getEmpleado_dest().setComentario(aF.getAsunto().getAsunto_detalle().getComentario());
                aF.getEmpleadosDestinatario().add(aF.getAsunto().getAsunto_detalle().getEmpleado_dest());
                if (request.getSession().getAttribute(Constantes.PRIORIDADES) == null)
                    request.getSession().setAttribute(Constantes.PRIORIDADES,prioridadService.listPrioridades());
                destino = "EDITARTURNO";
            } else
            {
                request.getSession().setAttribute(Constantes.MSJ_ASUNTO_USO, estadoAsunto.getMessage());
                response.sendRedirect("asunto.do?method=verDetalle&id_asunto=" + aF.getAsunto().getId_asunto());
            }
        } catch (Exception e)
        {
            log.error("Se ha producido un error  ->", e);
            request.setAttribute("mensaje", "Error interno. Por favor, intentelo otra vez en unos minutos.");
        }
        return mapping.findForward(destino);
    }

    public AsuntoDetalle getTurnoGuardado(Integer id_asunto_detalle, Integer id_empleado_ses){
        List <AsuntoDetalle> turnosGuardados = this.asuntoService.listTurnoGuardado(id_asunto_detalle);
        if (!turnosGuardados.isEmpty()&&turnosGuardados.get(0).getEmpleado_remi().getId_empleado().equals(id_empleado_ses)){
            AsuntoDetalle detalleGuardado = turnosGuardados.get(0);
          List <Empleado> empleados_dest = new ArrayList <Empleado>();
          Integer tam = turnosGuardados.size();
          while (tam>0){
              tam=tam-1;
              Empleado empleado_dest = this.empleadoService.getEmpleadoById(turnosGuardados.get(tam).getEmpleado_dest().getId_empleado());
              empleado_dest.setComentario(turnosGuardados.get(tam).getComentario());
              empleado_dest.getPrioridad().setId_prioridad(turnosGuardados.get(tam).getPrioridad().getId_prioridad());
              empleados_dest.add(empleado_dest);
          }
          detalleGuardado.setEmpleadosDest(empleados_dest);
          detalleGuardado.setEmpleadosCcp(turnosGuardados.get(0).getEmpleadosCcp());
          return detalleGuardado;
        }
        else{
          return new AsuntoDetalle();
        }
    }
    /**
     * Muestra la modulo para turnar un asunto
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return pantalla para turnar asunto
     */
    public ActionForward inicioReTurnar(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
    {
        AsuntoForm aF = (AsuntoForm) form;

        String destino = "ERROR";
        try
        {
            EstadoAsunto estadoAsunto = this.asuntoEstadoService.isBusyMatter(request, aF.getAsunto().getId_asunto(), "returnado");
            Empleado usuarioSession = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);
            if (!estadoAsunto.getIsBusy() || estadoAsunto.getEmail().equals(usuarioSession.getCorreo()))// Si el asunto no esta en uso por otro usuario
            {
                aF.setEmpleadoCaptura(usuarioSession);                
                //aF.getAsunto().setAsunto_detalle(new AsuntoDetalle());
                aF.getAsunto().setAsunto_detalle(this.getTurnoGuardado(aF.getAsunto().getUltimaDetalle().getId_asunto_detalle(),usuarioSession.getId_empleado()));
                List <Empleado> destinatariosGuardados = new ArrayList <Empleado>();
                destinatariosGuardados.addAll(aF.getAsunto().getAsunto_detalle().getEmpleadosDest());
                List <Empleado> empleadosCopiados = new ArrayList <Empleado>();
                empleadosCopiados.addAll(aF.getAsunto().getAsunto_detalle().getEmpleadosCcp());
                aF.getAsunto().getAsunto_detalle().getEmpleadosDest().clear();                
                //aF.getEmpleadosDestinatario().clear();
                aF.getAsunto().getAsunto_detalle().setComentario(aF.getAsunto().getDescripcion());
                //aF.setEmpleadosDestinatario(new ArrayList<Empleado>());
                aF.setAreasDependientes(this.areaService.getAreasFromDespĺiegue(usuarioSession.getArea()));
                aF.setInstrucciones(this.instruccionService.listInstrucciones(usuarioSession.getId_area()));
                aF.setTiposSeguimiento(this.tipoSeguimientoService.listTipoSeguimientos());
                //aF.setFirmantesVolante(this.empleadoService.listEmpleadosByIdArea(usuarioSession.getId_area()));
                aF.setFirmantesVolante(this.empleadoService.listEmpleadosByIdAreaOrdFirm(usuarioSession.getId_area()));
                aF.setFirmantesVolanteRol(this.empleadoService.listByIdAreaOrderByFirmanteRol(usuarioSession.getId_area()));
                aF.getAsunto_detalle().setEmpleadoFirmanteVolante(usuarioSession);
                aF.setEmpleadosDestinatario(destinatariosGuardados);
                aF.getAsunto().getAsunto_detalle().setEmpleadosCcp(empleadosCopiados);
                if (request.getSession().getAttribute(Constantes.PRIORIDADES) == null)
                    request.getSession().setAttribute(Constantes.PRIORIDADES,
                            prioridadService.listPrioridades());
                destino = "RETURNAR";
            } else
            {
                request.getSession().setAttribute(Constantes.MSJ_ASUNTO_USO, estadoAsunto.getMessage());
                response.sendRedirect("asunto.do?method=verDetalle&id_asunto=" + aF.getAsunto().getId_asunto());
            }
        } catch (Exception e)
        {
            log.error("Se ha producido un error  ->", e);
            request.setAttribute("mensaje", "Error interno. Por favor, intentelo otra vez en unos minutos.");
        }
        return mapping.findForward(destino);
    }

    /**
     * Elimina el asunto de la lista en contexto
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward removeAsuntoFromListSession(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
    {
        try
        {
            AsuntoForm asuntoForm = (AsuntoForm) form;
            this.asuntoEstadoService.deleteAsuntoFromListSession(request, asuntoForm.getAsunto().getId_asunto());
        } catch (Exception e)
        {
            log.error("Ha ocurrido un error mientras se borraba el asunto del la lista en contexto", e);
        }
        return null;
    }


    /**
     * Da de baje el mensaje en sesion de un asunto en uso
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward removeMessageSessionAsunto(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
    {
        request.getSession().removeAttribute(Constantes.MSJ_ASUNTO_USO);
        return null;
    }

    
    /**
     * Da de baje el mensaje en sesion de un asunto en uso
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */

    public ActionForward generarVolanteDetalleUPDF(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)
    {
        AsuntoForm cF = (AsuntoForm) form;
        String destino = "ERROR";               
        try
        {
            Empleado usuarioSession = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);
            cF.getAsunto().getUltimaDetalle().setEmpleadosCcp(cF.getAsunto().getEmpleadosCcp());
            //Generar el volante
            this.reporteVolanteService.generarVolanteCorrespondenciaUltimoDetallePDF(request, cF.getAsunto());
            //this.guardarAsuntoGeneraVolante(form, request);             
            String NOMBRE_ARCHIVO = ResourceBundle.getBundle(Constantes.PROPERTIES).getString("ruta.reporte.volante.nombrearchivo");
            byte[] reportePdf = (byte[]) request.getSession().getAttribute(Constantes.VOLANTE_CORRESPONDENCIA);
            if (reportePdf != null)
                UtilsReportes.descargaPdf(reportePdf, response, "application/pdf", NOMBRE_ARCHIVO);//el header y la impresion del reporte
            // Sube a request atributo para mostrar exito
            //request.setAttribute("exitoMsg", "Se guardo con exito");
            request.getSession().removeAttribute(Constantes.VOLANTE_CORRESPONDENCIA);

            destino = "DETALLE";
        } catch (Exception e)
        {
            log.error("Se ha producido un error mienstras se turnaba y generaba el volante", e);
            return mapping.findForward(destino);
        }
        return mapping.findForward(destino);
    }

    // Actualizar aqui el hecho que ya este atendido
    public ActionForward generarVolanteDetallePDF(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)
    {
        AsuntoForm cF = (AsuntoForm) form;
        Integer id_asunto_usar = cF.getAsunto().getId_asunto();
        Asunto asunto = asuntoService.getAsunto(id_asunto_usar);
        String destino = "ERROR";
        AsuntoDetalle asuntoDetalle;
        try
        {
            // Ubicar el detalle
            asuntoDetalle = asuntoService.listByIdAsuntoDetalle_ultimo(id_asunto_usar);
            // cF.setAsunto_detalle(asuntoDetalle);
            asunto.setAsunto_detalle(asuntoDetalle);

            Empleado usuarioSession = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);
            // cF.getAsunto().getUltimaDetalle().setEmpleadosCcp(cF.getAsunto().getEmpleadosCcp());
            asunto.getUltimaDetalle().setEmpleadosCcp(asunto.getEmpleadosCcp());
            //Generar el volante
            Integer status = asunto.getEstatus();
            // this.reporteVolanteService.generarVolanteCorrespondenciaUltimoDetallePDF(request, cF.getAsunto());
             
            if (status.equals(2)){  
                this.reporteVolanteService.generarVolantesCorrespondenciaPDF_Atendido(request, asunto);
            } else {
                this.reporteVolanteService.generarVolantesCorrespondenciaPDF(request, asunto);
            }
             
            //this.guardarAsuntoGeneraVolante(form, request);
            String NOMBRE_ARCHIVO = ResourceBundle.getBundle(Constantes.PROPERTIES).getString("ruta.reporte.volante.nombrearchivo");
            byte[] reportePdf = (byte[]) request.getSession().getAttribute(Constantes.VOLANTE_CORRESPONDENCIA);
            if (reportePdf != null)
                UtilsReportes.descargaPdf(reportePdf, response, "application/pdf", NOMBRE_ARCHIVO);//el header y la impresion del reporte
            // Sube a request atributo para mostrar exito
            //request.setAttribute("exitoMsg", "Se guardo con exito");
            request.getSession().removeAttribute(Constantes.VOLANTE_CORRESPONDENCIA);

            destino = "DETALLE";
        } catch (Exception e)
        {
            log.error("Se ha producido un error mienstras se turnaba y generaba el volante", e);
            return mapping.findForward(destino);
        }
        return mapping.findForward(destino);
    }


    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return 
     */
    public ActionForward generarVolantePDF(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)
    {
        JasperDesign jasperDesign;
        AsuntoForm aF = (AsuntoForm) form;
        String RUTA_LOGO = ResourceBundle.getBundle(Constantes.PROPERTIES).getString("ruta.reporte.logo");
        String RUTA_JRXLM = ResourceBundle.getBundle(Constantes.PROPERTIES).getString("ruta.reporte.volante.jrxml");
        String NOMBRE_ARCHIVO = ResourceBundle.getBundle(Constantes.PROPERTIES).getString("ruta.reporte.volante.nombrearchivo");
        Asunto asunto = this.asuntoService.getAsuntoDetalleById(aF.getId_asunto());

        if (asunto.getEmpleado_revi().getId_empleado() != null)
            asunto.setEmpleado_revi(this.empleadoService.getEmpleadoById(asunto.getEmpleado_revi().getId_empleado()));
        else
        {
        }

        try
        {
            HashMap<String, Object> parametersMap = new HashMap<String, Object>();//los parametros
            InputStream xmlResource = this.getServlet().getServletContext().getResourceAsStream(RUTA_JRXLM);//el archivo de diseno
            InputStream logo = this.getServlet().getServletContext().getResourceAsStream(RUTA_LOGO);//el logo
            List<Reporte> dataSource = new ArrayList<Reporte>();

            dataSource = new ArrayList<Reporte>();
            dataSource.add(new Reporte());

            parametersMap.put("LOGO", logo);//la imagen como parametro

            Empleado usuarioSession = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);
            if (usuarioSession.getArea().getListado_area().equals(Constantes.ID_AREA_SECRETARIO))
            {
                parametersMap.put("oficina", asunto.getAsunto_detalle().getEmpleado_revi().getArea().getArea() != null ? asunto.getAsunto_detalle().getEmpleado_revi().getArea().getArea() : "");
                parametersMap.put("destinatario", asunto.getAsunto_detalle().getEmpleado_revi().getNombre_completo() != null ? asunto.getAsunto_detalle().getEmpleado_revi().getNombre_completo() : "");
                parametersMap.put("puestodestinatario", asunto.getAsunto_detalle().getEmpleado_revi().getPuesto() != null ? asunto.getAsunto_detalle().getEmpleado_revi().getPuesto() : "");
            } else
            {
                //"todo" aqui ira a quien se turna
                parametersMap.put("oficina", asunto.getAsunto_detalle().getEmpleado_dest().getArea().getArea() != null ? asunto.getAsunto_detalle().getEmpleado_dest().getArea().getArea() : "");
                parametersMap.put("destinatario", asunto.getAsunto_detalle().getEmpleado_dest().getNombre_completo() != null ? asunto.getAsunto_detalle().getEmpleado_dest().getNombre_completo() : "");
                parametersMap.put("puestodestinatario", asunto.getAsunto_detalle().getEmpleado_dest().getPuesto() != null ? asunto.getAsunto_detalle().getEmpleado_dest().getPuesto() : "");
            }

            log.debug("Capturista: " + usuarioSession.getArea().getListado_area());

            /*
             * se cambio esta parte el dia 13 de mayo por solicitud de gabriela
            if (usuarioSession.getArea().getListado_area().equals(Constantes.ID_AREA_SECRETARIO)) {
            parametersMap.put("firmante", asunto.getEmpleado_revi().getNombre_completo());
            parametersMap.put("cargo", asunto.getAsunto_detalle().getEmpleado_dest().getPuesto() != null ? asunto.getAsunto_detalle().getEmpleado_dest().getPuesto() : "");
            } else {
            parametersMap.put("firmante", asunto.getAsunto_detalle().getEmpleado_remi().getNombre_completo());
            parametersMap.put("cargo", asunto.getEmpleado_revi().getPuesto() != null ? asunto.getEmpleado_revi().getPuesto() : "");
            }
             * */


            parametersMap.put("firmante", asunto.getAsunto_detalle().getEmpleado_dest().getNombre_completo() != null ? asunto.getAsunto_detalle().getEmpleado_dest().getNombre_completo() : "");
            parametersMap.put("cargo", asunto.getAsunto_detalle().getEmpleado_dest().getPuesto() != null ? asunto.getAsunto_detalle().getEmpleado_dest().getPuesto() : "");

            parametersMap.put("remitente", asunto.getAsunto_detalle().getEmpleado_remi().getNombre_completo() != null ? asunto.getAsunto_detalle().getEmpleado_remi().getNombre_completo() : "");
            parametersMap.put("procedencia", asunto.getAsunto_detalle().getEmpleado_remi().getArea().getArea() != null ? asunto.getAsunto_detalle().getEmpleado_remi().getArea().getArea() : "");

            parametersMap.put("documento", asunto.getAsunto_detalle().getDocumento().getDocumento() != null ? asunto.getAsunto_detalle().getDocumento().getDocumento() : "");

            parametersMap.put("fecha", asunto.getAsunto_detalle().getFh_registroDDMMYYYY() != null ? asunto.getAsunto_detalle().getFh_registroDDMMYYYY() : "");
            parametersMap.put("volante", asunto.getFolio() != null ? asunto.getFolio() : "");
            parametersMap.put("seguimiento", asunto.getInstruccion().getInstruccion() != null ? asunto.getInstruccion().getInstruccion() : "");
            parametersMap.put("responderantesdel", asunto.getAsunto_detalle().getFh_limiteDDMMYYYY() != null ? asunto.getAsunto_detalle().getFh_limiteDDMMYYYY() : "");
            parametersMap.put("referencia", asunto.getId_asunto_ref() != null ? asunto.getId_asunto_ref() : "");

            parametersMap.put("comentarios", asunto.getDescripcion() != null ? asunto.getDescripcion() : "");

            parametersMap.put("tituloremitente", asunto.getEmpleado_revi().getNombre_completo() != null ? asunto.getEmpleado_revi().getNombre_completo() : "");
            parametersMap.put("puestoremitente", asunto.getEmpleado_revi().getPuesto() != null ? asunto.getEmpleado_revi().getPuesto() : "");

            parametersMap.put("respuestaoficina", "Oficina del C. Secretario");
            parametersMap.put("respuestaarea", "AtenciÃ³n Ciudadana");
            parametersMap.put("respuestasecretaria", "SecretarÃ­a de EconomÃ­a");

            parametersMap.put("respuestavolante", asunto.getFolio() != null ? asunto.getFolio() : "");
            parametersMap.put("respuestaseguimiento", "Ordinario");
            parametersMap.put("respuestacontestacion", asunto.getAsunto_detalle().getFh_limiteDDMMYYYY() != null ? asunto.getAsunto_detalle().getFh_limiteDDMMYYYY() : "");
            parametersMap.put("instruccion", asunto.getInstruccion().getInstruccion() != null ? asunto.getInstruccion().getInstruccion() : "");


            parametersMap.put("prioridad", asunto.getAsunto_detalle().getPrioridad().getPrioridad() != null ? asunto.getAsunto_detalle().getPrioridad().getPrioridad() : "");

            JRBeanCollectionDataSource dataSourceCollection = new JRBeanCollectionDataSource(dataSource);//se crea la coleccion

            jasperDesign = JRXmlLoader.load(xmlResource);//se crea el rescurso
            JasperReport reportPath = JasperCompileManager.compileReport(jasperDesign);// compilacion del xml de diseno
            JasperPrint print = JasperFillManager.fillReport(reportPath, parametersMap, dataSourceCollection);//la impresion del reporte            
            byte[] reportePdf = JasperExportManager.exportReportToPdf(print);//se crea el flujo de bytes
            UtilsReportes.descargaPdf(reportePdf, response, "application/pdf", NOMBRE_ARCHIVO);//el header y la impresion del reporte
        } catch (Exception e)
        {
            log.debug("Ha ocurrido un error mientras se generaba el pdf para el reporte general de asunto");
            log.error("Se ha producido un error  ->", e);
        }
        return null;
    }

    /**
     * Muestra la modulo para finalizar un asunto
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return pantalla para finalizar asunto
     */
    public ActionForward inicioFinalizar(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)
    {
        AsuntoForm aF = (AsuntoForm) form;
        String destino = "ERROR";
        try
        {
            aF.getAsunto().setAsunto_detalle(new AsuntoDetalle());
            destino = "FINALIZAR";
        } catch (Exception e)
        {
            log.error("Se ha producido un error  ->", e);
            request.setAttribute("mensaje",
                    "Error interno. Por favor, intentelo otra vez en unos minutos.");
        }
        return mapping.findForward(destino);
    }

    /**
     * Muestra la modulo para rechazar un asunto
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return pantalla para finalizar asunto
     */
    public ActionForward inicioRechazar(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
    {
        AsuntoForm aF = (AsuntoForm) form;
        String destino = "ERROR";
        try
        {
            aF.getAsunto().setAsunto_detalle(new AsuntoDetalle());
            destino = "RECHAZAR";
        } catch (Exception e)
        {
            log.error("Se ha producido un error  ->", e);
            request.setAttribute("mensaje",
                    "Error interno. Por favor, intentelo otra vez en unos minutos.");
        }
        return mapping.findForward(destino);
    }

    /**
     * Agrega un empleado a la lista de con copia para, utilizando Ajax
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return respuesta de Ajax
     */
    public ActionForward agregarConCopiaPara(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)
    {
        AsuntoForm aF = (AsuntoForm) form;
        try
        {
            // Obtiene el id del empleado de request
            String id_empleado = request.getParameter("idEmpleado");
            String nombre_completo = request.getParameter("nombreEmpleado");
            id_empleado = id_empleado != null ? id_empleado.trim() : "-1";

            // Crea el empleado a agregar como ccp
            Empleado empleadoCcp = new Empleado(Integer.parseInt(id_empleado));
            empleadoCcp.setNombre_completo(nombre_completo);

            // Obtiene los empleados con copia para del detalle
            List<Empleado> empleadosCcp = aF.getAsunto().getAsunto_detalle().getEmpleadosCcp();

            // Si el empleado a agregar esta en el detalle del asunto
            boolean isEnDetalle = empleadosCcp.contains(empleadoCcp);

            // Si el empleado a agregar esta en la lista de ccp del asunto
            boolean isEnAsunto = aF.getAsunto().getEmpleadosCcp().contains(
                    empleadoCcp);

            // Si no existe en detalle y no existe en asunto agrega en ccp
            if (!isEnDetalle && !isEnAsunto)
            {
                // Agrega el empleado a la lista de empleados ccp
                if (aF.getAsunto().getAtencionParcial()==0){
                    aF.getAsunto().getEmpleadosCcp().add(empleadoCcp);
                }
                empleadosCcp.add(empleadoCcp);
                // Asigna indices a la lista de empleados ccp
                Empleado.asignaIdx(empleadosCcp);
                PrintWriter pw = response.getWriter();
                pw.write("success");
                pw.flush();
                pw.close();
            }
        } catch (Exception e)
        {
            log.error("Se ha producido un error  ->", e);
            request.setAttribute("mensaje",
                    "Error interno. Por favor, intentelo otra vez en unos minutos.");
        }
        return null;
    }

    /**
     * Elimina un empleado a la lista de con copia para, utilizando Ajax
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return respuesta de Ajax
     */
    public ActionForward eliminarConCopiaPara(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)
    {
        AsuntoForm aF = (AsuntoForm) form;
        try
        {
            // Obtiene el id del empleado de request
            String id_empleado = request.getParameter("idEmpleado");
            id_empleado = id_empleado != null ? id_empleado.trim() : "-1";

            // Obtiene los empleados con copia para del detalle
            List<Empleado> empleadosCcp = aF.getAsunto().getAsunto_detalle().getEmpleadosCcp();

            for (int i = 0; i < empleadosCcp.size(); i++)
            {
                Empleado empleado = (Empleado) empleadosCcp.get(i);

                // Busca el empleado por medio del id y lo elimina de la lista
                if (empleado.getId_empleado().equals(
                        Integer.parseInt(id_empleado)))
                    empleadosCcp.remove(i);
            }
        } catch (Exception e)
        {
            log.error("Se ha producido un error  ->", e);
            request.setAttribute("mensaje",
                    "Error interno. Por favor, intentelo otra vez en unos minutos.");
        }
        return null;
    }

    /**
     * Adjunta un documento a la lista de documentos adjuntos, utilizando Ajax
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return respuesta de Ajax
     */
    public ActionForward adjuntarDocumento(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)
    {
        AsuntoForm aF = (AsuntoForm) form;
        try
        {
            Boolean cargaAtencionParcial = false;
            String documentoStr = request.getParameter("documento");
            Documento documento = new Documento();

            // Asigna el archivo adjunto al documento
            documento.setAdjunto(aF.getAsunto().getAsunto_detalle().getDocumento().getAdjunto());


            if (documentoStr.indexOf("CargaInicial")==0){
                cargaAtencionParcial = true;
                documentoStr=documentoStr.substring(documentoStr.indexOf("CargaInicial")+12);
            }
            // Asigna el nombre del documento
            documento.setDocumento(documentoStr);

            // Asigna el nombre del documento con la extension
            documento.setDocumento(documento.getDocumento());

            // Asigna el tipo del documento
            Integer id_tipo_documento = Integer.valueOf(request.getParameter("id_tipo_documento"));
            documento.getTipo_documento().setId_tipo_documento(
                    id_tipo_documento);

            String tipo_documento = request.getParameter("tipo_documento");

            documento.getTipo_documento().setTipo_documento(tipo_documento);

            // Obtiene los documentos del detalle
            List<Documento> documentos = aF.getAsunto().getAsunto_detalle().getDocumentos();
            boolean existe = documentos.contains(documento);
            // Si no existe en detalle agrega el documento
            if (!existe)
            {
                int idx = -1;
                for (int i = 0; i < documentos.size(); i++)
                {
                    Documento doc = (Documento) documentos.get(i);
                    idx = doc.getIdx();
                }
                documento.setIdx(idx + 1);
                if (!cargaAtencionParcial)
                    documentos.add(documento);
                else{
                    for (int i = 0; i < documentos.size(); i++)
                    {
                       documentos.get(i).setIdx(i+1);
                       if (documentos.get(i).getDocumento().equals(documentoStr)){
                           documento.setIdx(i+1);
                       }
                    }
                }
                // Crea un objetoJSON con el documento a adjuntar
                JSONObject json = JSONObject.fromObject(new Documento(documento.getDocumento(), documento.getId_documento(), documento.getTipo_documento(), documento.getIdx()));
                PrintWriter pw = response.getWriter();
                pw.write(json.toString());
                pw.flush();
                pw.close();
            }
        } catch (Exception e)
        {
            log.error("Se ha producido un error  ->", e);
            request.setAttribute("mensaje",
                    "Error interno. Por favor, intentelo otra vez en unos minutos.");
        }
        return null;
    }

    /**
     * Elimina un documento de la lista de adjuntos, utilizando Ajax
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return respuesta de Ajax
     */
    public ActionForward eliminarDocumento(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)
    {
        AsuntoForm aF = (AsuntoForm) form;
        try
        {
            // Obtiene el idx del documento a eliminar
            int idx = request.getParameter("idx") != null ? Integer.parseInt(request.getParameter("idx")) : -1;

            // Obtiene la lista de documentos adjuntados en el detalle
            List<Documento> documentos = aF.getAsunto().getAsunto_detalle().getDocumentos();
            for (int i = 0; i < documentos.size(); i++)
            {
                Documento documento = (Documento) documentos.get(i);

                // Busca el documento por medio del idx y lo elimina de la lista
                if (documento.getIdx() == idx)
                    documentos.remove(i);
            }
        } catch (Exception e)
        {
            log.error("Se ha producido un error  ->", e);
            request.setAttribute("mensaje",
                    "Error interno. Por favor, intentelo otra vez en unos minutos.");
        }
        return null;
    }

    /**
     * Descarga un documento de la lista de adjuntos, utilizando Ajax
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return abre ventana con documento
     */
    public ActionForward descargarDocumento(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)
    {

        AsuntoForm cF = (AsuntoForm) form;

        response.setContentType("multipart/form-data");
        Boolean atencionParcial =false;
        Integer id_documento = -1;
        Integer id_asunto_detalle = 0;
        byte[] blob;
        try
        {
            // Obtiene el id del documento a descargar
            String id_documentoStr = request.getParameter("id_documento") != null ? request.getParameter("id_documento") : "-1";
            if (id_documentoStr.indexOf("-")>0){
                atencionParcial=true;
                id_documento = Integer.valueOf(id_documentoStr.substring(id_documentoStr.indexOf("-")+1));
                id_asunto_detalle = Integer.valueOf(id_documentoStr.substring(0,id_documentoStr.indexOf("-")));
            }
            else{
                id_documento = request.getParameter("id_documento") != null ? Integer.valueOf(request.getParameter("id_documento")): -1;
                id_asunto_detalle = request.getParameter("id_asunto_detalle") != null ? Integer.valueOf(request.getParameter("id_asunto_detalle")) : -1;
            }
            // Obtiene el indice del documento a descargar
            Integer idx = request.getParameter("idx") != null ? Integer.valueOf(request.getParameter("idx")) : -1;

            // Si el idx es >= 0 descarga el documento de la lista de adjuntos
            if (idx.intValue() >= 0)
            {
                List<Documento> documentos = cF.getAsunto().getAsunto_detalle().getDocumentos();
                // Recorre la lista de adjuntos
                for (Documento documento : documentos)
                {
                    // Descarga el documento del idx obtenido de request
                    if (documento.getIdx() == idx)
                        Util.descargarArchivo(documento.getBlob(), response,
                                documento.getDocumento());
                }
            } else
                for (AsuntoDetalle asuntoDet : cF.getAsunto().getAsuntos_detalles())
                {
                    // Si el id_asunto_detalle es igual al id_asunto_detalle de
                    // request
                    if (asuntoDet.getId_asunto_detalle().equals(
                            id_asunto_detalle))
                        for (Documento documento : asuntoDet.getDocumentos())
                        {
                            // Si el id_documento es igual al id_documento de
                            // request
                            if (documento.getId_documento().equals(id_documento))
                            {
                                if (atencionParcial==false){
                                    blob = documentoService.getBlobByIdDocumento(documento.getId_documento());
                                }
                                 else {
                                    blob = documentoService.getBlobAtenParByIdDocumento(asuntoDet.getId_asunto_detalle(),documento.getId_documento());
                                 }
                                // Descarga el documento seleccionado
                                Util.descargarArchivo(blob, response, documento.getDocumento());
                            }
                        }
                }
        } catch (Exception e)
        {
            log.error("Se ha producido un error  ->", e);
            request.setAttribute("mensaje",
                    "Error interno. Por favor, intentelo otra vez en unos minutos.");
        }
        return null;
    }

    /**
     * Guarda la atencion de un asunto
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return pantalla de exito en la atencion
     */
    public ActionForward atender(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
    {
        AsuntoForm aF = (AsuntoForm) form;
        String destino = "ERROR";
        try
        {
            Asunto asunto = aF.getAsunto();
            // Guarda el asunto como atendido
            String RUTA_LOGO = ResourceBundle.getBundle(Constantes.PROPERTIES).getString("ruta.reporte.logo");
            URL urlLogo = this.getServlet().getServletContext().getResource(RUTA_LOGO);
            if (asunto.getAtencionParcial()==0){
                asunto.getEmpleadosCcp().addAll(asunto.getAsunto_detalle().getEmpleadosCcp());
                asunto.getAsunto_detalle().getEmpleadosCcp().clear();
                asuntoService.saveAtender(asunto, request, urlLogo);
            }
            else {                
                asuntoService.saveAtenderParcial(asunto, request, urlLogo);
            }

            // Busca el asunto por medio del id
            Integer id_asunto = asunto.getId_asunto();
            asunto = asuntoService.getAsunto(id_asunto);
            aF.setAsunto(asunto);            
            // Borra las banderas para mostrar botones en el detalle del asunto
            asuntoService.removeButtonsDetalle(request);
            if (aF.getAsunto().getAsuntoAtencionParcial()!=null){
                aF.getAsunto().getAsuntoAtencionParcial().setEstatus_desc("EN TRAMITE");
                aF.getAsunto().getAsuntoAtencionParcial().getEmpleado_dest().setComentario(aF.getAsunto().getAsuntoAtencionParcial().getComentario());
                aF.getAsunto().getAsuntos_detalles().add(aF.getAsunto().getAsuntoAtencionParcial());
            }
            // Url para regresar despues de guardar
            request.getSession().setAttribute("btnRegresar",
                    "/consulta-bandeja.do?method=inicio&p_bandeja=1&p_estatus=-1&p_confidencial=0&p_copia=0&p_historico=0");
            destino = "DETALLE";
            // By Rodolfo Milano
            // GenerarVolanteAtendido
            // Activa boton Generar Volante (Atendido)
            request.getSession().setAttribute("showGenerarVolanteBtn", "showGenerarVolanteBtn");

            this.asuntoEstadoService.deleteAsuntoFromListSession(request, id_asunto);
            // Muestra el mensaje de exito
            request.setAttribute("exitoMsg", "Se actualizó con éxito");

        } catch (Exception e)
        {
            log.error("Se ha producido un error  ->", e);
            request.setAttribute("mensaje",
                    "Error interno. Por favor, intentelo otra vez en unos minutos.");
        }
        return mapping.findForward(destino);
    }

    /**
     * Turnar y generar volante
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward turnarGenerarVolante(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        AsuntoForm aF = (AsuntoForm) form;
        String destino = "ERROR";
        try
        {
            this.reporteVolanteService.generarVolantesCorrespondenciaPDF(request, aF.getAsunto());

            Asunto asunto = aF.getAsunto();
            
            // Guarda el asunto como turnado
            String RUTA_LOGO = ResourceBundle.getBundle(Constantes.PROPERTIES).getString("ruta.reporte.logo");
            URL urlLogo = this.getServlet().getServletContext().getResource(RUTA_LOGO);
            List<Empleado> empleadosDest = asunto.getAsunto_detalle().getEmpleadosDest();
            asuntoService.saveTurnar(asunto, request, urlLogo);
            // Busca el asunto por medio del id
            Integer id_asunto = asunto.getId_asunto();
            asunto = asuntoService.getAsunto(id_asunto);
            aF.setAsunto(asunto);
             
            // Muestra el mensaje de exito
            request.setAttribute("exitoMsg", "Se turnó con éxito");

            // Borra las banderas para mostrar botones en el detalle del asunto
            asuntoService.removeButtonsDetalle(request);
            asuntoService.loadBtnDetalleAsunto(asunto, request);
            // Url para regresar despues de guardar
            request.getSession().setAttribute("btnRegresar", "/inicio.do");
            destino = "DETALLE";
            this.asuntoEstadoService.deleteAsuntoFromListSession(request, id_asunto);

        } catch (Exception e)
        {
            log.error("Se ha producido un error mienstras se turnaba y generaba el volante");
        }
        return mapping.findForward(destino);
    }

    /**
     * Turnar y generar volante
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward editarTurnoGenerarVolante(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        AsuntoForm aF = (AsuntoForm) form;
        String destino = "ERROR";
        try
        {
            this.reporteVolanteService.generarVolantesCorrespondenciaPDF(request, aF.getAsunto());

            Asunto asunto = aF.getAsunto();

            // Guarda el asunto como turnado
            String RUTA_LOGO = ResourceBundle.getBundle(Constantes.PROPERTIES).getString("ruta.reporte.logo");
            URL urlLogo = this.getServlet().getServletContext().getResource(RUTA_LOGO);
            List<Empleado> empleadosDest = asunto.getAsunto_detalle().getEmpleadosDest();
            asuntoService.saveEditarTurno(asunto, request, urlLogo);
            // Busca el asunto por medio del id
            Integer id_asunto = asunto.getId_asunto();
            asunto = asuntoService.getAsunto(id_asunto);
            aF.setAsunto(asunto);

            // Muestra el mensaje de exito
            request.setAttribute("exitoMsg", "Se turnó con éxito");

            // Borra las banderas para mostrar botones en el detalle del asunto
            asuntoService.removeButtonsDetalle(request);
            asuntoService.loadBtnDetalleAsunto(asunto, request);
            // Url para regresar despues de guardar
            request.getSession().setAttribute("btnRegresar", "/inicio.do");
            destino = "DETALLE";
            this.asuntoEstadoService.deleteAsuntoFromListSession(request, id_asunto);

        } catch (Exception e)
        {
            log.error("Se ha producido un error mienstras se turnaba y generaba el volante");
        }
        return mapping.findForward(destino);
    }

    /**
     * Re Turnar y generar volante
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward returnarGenerarVolante(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        AsuntoForm aF = (AsuntoForm) form;
        String destino = "ERROR";
        try
        {
            this.reporteVolanteService.generarVolantesCorrespondenciaPDF(request, aF.getAsunto());

            Asunto asunto = aF.getAsunto();

            // Guarda el asunto como turnado
            String RUTA_LOGO = ResourceBundle.getBundle(Constantes.PROPERTIES).getString("ruta.reporte.logo");
            URL urlLogo = this.getServlet().getServletContext().getResource(RUTA_LOGO);
            //List<Empleado> empleadosDest = asunto.getAsunto_detalle().getEmpleadosDest();
            Asunto asuntoReturno = asuntoService.saveReTurnar(asunto, request, urlLogo);
            asunto=null;
            // Busca el asunto por medio del id
            Integer id_asunto = asuntoReturno.getId_asunto();
            asuntoReturno = asuntoService.getAsunto(id_asunto);
            aF.setAsunto(asuntoReturno);

            // Muestra el mensaje de exito
            request.setAttribute("exitoMsg", "Se returnó con éxito");

            // Borra las banderas para mostrar botones en el detalle del asunto
            asuntoService.removeButtonsDetalle(request);
            asuntoService.loadBtnDetalleAsunto(asuntoReturno, request);
            // Url para regresar despues de guardar
            request.getSession().setAttribute("btnRegresar", "/inicio.do");
            destino = "DETALLE";
            this.asuntoEstadoService.deleteAsuntoFromListSession(request, id_asunto);

        } catch (Exception e)
        {
            log.error("Se ha producido un error mienstras se turnaba y generaba el volante");
        }
        return mapping.findForward(destino);
    }

    /**
     * Re Turnar y generar volante
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward guardaCapturaTurno(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        AsuntoForm aF = (AsuntoForm) form;
        String destino = "ERROR";
        try
        {   
            Asunto asunto = aF.getAsunto();
            Integer id_empleado_firmante = Integer.parseInt(request.getParameter("idEmpleadoFirmante"));
            asunto.getAsunto_detalle().getEmpleadoFirmanteVolante().setId_empleado(id_empleado_firmante);
            //String RUTA_LOGO = ResourceBundle.getBundle(Constantes.PROPERTIES).getString("ruta.reporte.logo");
            //URL urlLogo = this.getServlet().getServletContext().getResource(RUTA_LOGO);
            //List<Empleado> empleadosDest = asunto.getAsunto_detalle().getEmpleadosDest();
            asunto = asuntoService.guardaCapturaTurno(asunto, request);
        } catch (Exception e)
        {
            log.error("Se ha producido un error mienstras se guarda la captura del turnado");
        }
        return null;
    }

    /**
     * Generar volante de correspondencia de la session
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward generarVolanteCorrespondenciaFromSession(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        AsuntoForm aF = (AsuntoForm) form;
        String destino = "ERROR";
        try
        {
            String NOMBRE_ARCHIVO = ResourceBundle.getBundle(Constantes.PROPERTIES).getString("ruta.reporte.volante.nombrearchivo");
            byte[] reportePdf = (byte[]) request.getSession().getAttribute(Constantes.VOLANTE_CORRESPONDENCIA);
            if (reportePdf != null)
                UtilsReportes.descargaPdf(reportePdf, response, "application/pdf", NOMBRE_ARCHIVO);//el header y la impresion del reporte
            destino = "DETALLE";
        } catch (Exception e)
        {
            log.error("Se ha producido un error mienstras se turnaba y generaba el volante", e);
            return mapping.findForward(destino);
        }
        return mapping.findForward(destino);
    }

    /**
     * Guarda el turnado de un asunto
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return pantalla de exito en el turnado
     */
    public ActionForward turnar(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
    {
        AsuntoForm aF = (AsuntoForm) form;
        String destino = "ERROR";

        try
        {
            Asunto asunto = aF.getAsunto();

            // Guarda el asunto como turnado
            String RUTA_LOGO = ResourceBundle.getBundle(Constantes.PROPERTIES).getString("ruta.reporte.logo");
            URL urlLogo = this.getServlet().getServletContext().getResource(RUTA_LOGO);
            List<Empleado> empleadosDest = asunto.getAsunto_detalle().getEmpleadosDest();
            asuntoService.saveTurnar(asunto, request, urlLogo);

            // Busca el asunto por medio del id
            Integer id_asunto = asunto.getId_asunto();
            asunto = asuntoService.getAsunto(id_asunto);
            aF.setAsunto(asunto);

            // Muestra el mensaje de exito
            request.setAttribute("exitoMsg", "Se turnó con éxito");

            // Borra las banderas para mostrar botones en el detalle del asunto
            asuntoService.removeButtonsDetalle(request);

            // Url para regresar despues de guardar
            request.getSession().setAttribute("btnRegresar",
                    "/consulta-bandeja.do?method=inicio");
            destino = "DETALLE";
            this.asuntoEstadoService.deleteAsuntoFromListSession(request, id_asunto);
        } catch (Exception e)
        {
            log.error("Se ha producido un error  ->", e);
            request.setAttribute("mensaje",
                    "Error interno. Por favor, intentelo otra vez en unos minutos.");
        }        
        return mapping.findForward(destino);
    }

    /**
     * Guarda la finalizacion de un asunto
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return pantalla de exito en la finalizacion
     */
    public ActionForward finalizar(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
    {
        AsuntoForm aF = (AsuntoForm) form;
        String destino = "ERROR";
        try
        {
            Asunto asunto = aF.getAsunto();

            // Guarda el asunto como finalizado
            String RUTA_LOGO = ResourceBundle.getBundle(Constantes.PROPERTIES).getString("ruta.reporte.logo");
            URL urlLogo = this.getServlet().getServletContext().getResource(RUTA_LOGO);


            asuntoService.saveFinalizar(asunto, request, urlLogo);

            // Busca el asunto por medio del id
            Integer id_asunto = asunto.getId_asunto();
            asunto = asuntoService.getAsunto(id_asunto);
            aF.setAsunto(asunto);

            // Muestra el mensaje de exito
            request.setAttribute("exitoMsg", "Se finalizó con éxito");

            // Borra las banderas para mostrar botones en el detalle del asunto
            asuntoService.removeButtonsDetalle(request);

            // Url para regresar despues de guardar
            request.getSession().setAttribute("btnRegresar",
                    "/consulta-bandeja.do?method=inicio");
            destino = "DETALLE";
            
        } catch (Exception e)
        {
            log.error("Se ha producido un error  ->", e);
            request.setAttribute("mensaje",
                    "Error interno. Por favor, intentelo otra vez en unos minutos.");
        }
        return mapping.findForward(destino);
    }

    /**
     * Guarda el rechazo de un asunto
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return pantalla de exito en el rechazo
     */
    public ActionForward rechazar(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
    {
        AsuntoForm aF = (AsuntoForm) form;
        String destino = "ERROR";
        try
        {
            Asunto asunto = aF.getAsunto();

            // Guarda el asunto como rechazado
            String RUTA_LOGO = ResourceBundle.getBundle(Constantes.PROPERTIES).getString("ruta.reporte.logo");
            URL urlLogo = this.getServlet().getServletContext().getResource(RUTA_LOGO);
            asuntoService.saveRechazar(asunto, request, urlLogo);

            // Incluido por Rodolfo Milano
            // Se incluye un pequeño delay para que no tenga problemas con el indice
            Thread.sleep(2000);
            // Se crea una respuesta parcial para que se active el status en trámite
            asuntoService.saveAtenderParcialRechazo(asunto, request, urlLogo);
            
            // Busca el asunto por medio del id
            Integer id_asunto = asunto.getId_asunto();
            asunto = asuntoService.getAsunto(id_asunto);
            aF.setAsunto(asunto);

            // Muestra el mensaje de exito
            request.setAttribute("exitoMsg", "Se colocó en trámite con éxito");

            // Borra las banderas para mostrar botones en el detalle del asunto
            asuntoService.removeButtonsDetalle(request);

            // Url para regresar despues de guardar
            request.getSession().setAttribute("btnRegresar",
                    "/consulta-bandeja.do?method=inicio");
            destino = "DETALLE";
        } catch (Exception e)
        {
            log.error("Se ha producido un error  ->", e);
            request.setAttribute("mensaje",
                    "Error interno. Por favor, intentelo otra vez en unos minutos.");
        }
        return mapping.findForward(destino);
    }

    /**
     * Elimina un asunto del repositorio
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return pantalla de exito en la atencion
     */
    public ActionForward eliminarAsunto(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        AsuntoForm aF = (AsuntoForm) form;
        try
        {
            Asunto asunto = aF.getAsunto();
            asunto.getId_asunto();
            asuntoService.deleteAsunto(asunto);
            response.sendRedirect("consulta-captura.do?method=inicio");
        } catch (Exception e)
        {
            log.error("Se ha producido un error  ->", e);
            request.setAttribute("mensaje", "Error interno. Por favor, intentelo otra vez en unos minutos.");
            response.sendRedirect("consulta-captura.do?method=inicio");
        }
        return null;
    }

    /**
     * Agrega un empleado a la lista de destinatarios, utilizando Ajax
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return respuesta de Ajax
     */
    public ActionForward agregarDestinatario(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)
    {

        AsuntoForm aF = (AsuntoForm) form;
        try
        {
            // Obtiene el id del empleado de request
            String id_empleado = request.getParameter("idEmpleado");
            String nombre_completo = request.getParameter("nombreEmpleado");
            id_empleado = id_empleado != null ? id_empleado.trim() : "-1";

            // Crea el empleado a agregar como destinatario
            Empleado empleadoDest = new Empleado();
            empleadoDest.setId_empleado(Integer.parseInt(id_empleado));
            empleadoDest.setNombre_completo(nombre_completo);

            // Obtiene los empleados destinatarios
            List<Empleado> empleadosDest = aF.getAsunto().getAsunto_detalle().getEmpleadosDest();

            // El empleado esta en la lista de destinatarios
            boolean existe = empleadosDest.contains(empleadoDest);
            // Si no ha sido agregado
            if (!existe)
            {
                // Agrega el empleado a la lista de empleados destinatarios
                empleadosDest.add(empleadoDest);

                // Asigna indices a la lista de destinatarios
                Empleado.asignaIdx(empleadosDest);
                PrintWriter pw = response.getWriter();
                pw.write("success");
                pw.flush();
                pw.close();
            }

        } catch (Exception e)
        {
            log.error("Se ha producido un error  ->", e);
            request.setAttribute("mensaje",
                    "Error interno. Por favor, intentelo otra vez en unos minutos.");
        }
        return null;
    }

    /**
     * Elimina un empleado de la lista de destinatarios, utilizando Ajax
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return respuesta de Ajax
     */
    public ActionForward eliminarDestinatario(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)
    {

        AsuntoForm aF = (AsuntoForm) form;
        try
        {
            // Obtiene el id del empleado de request
            String id_empleado = request.getParameter("idEmpleado");
            id_empleado = id_empleado != null ? id_empleado.trim() : "-1";

            // Obtiene los empleados destinatarios
            List<Empleado> empleadosDest = aF.getAsunto().getAsunto_detalle().getEmpleadosDest();

            for (int i = 0; i < empleadosDest.size(); i++)
            {
                Empleado empleado = (Empleado) empleadosDest.get(i);

                // Busca el empleado por medio del id y lo elimina de la lista
                if (empleado.getId_empleado().equals(
                        Integer.parseInt(id_empleado)))
                    empleadosDest.remove(i);
            }

        } catch (Exception e)
        {
            log.error("Se ha producido un error  ->", e);
            request.setAttribute("mensaje",
                    "Error interno. Por favor, intentelo otra vez en unos minutos.");
        }
        return null;
    }

    /**
     * Actualiza los destinatarios del asunto
     * @param mapping
     * @param form
     * @param request
     * @param reponse
     * @return
     * @throws Exception
     */
    public ActionForward actualizaEmpleadoDestinatario(ActionMapping mapping,
            ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        AsuntoForm asuntoForm = (AsuntoForm) form;
        try
        {
            String idEmpleado = request.getParameter("idEmpleado");
            String comentarioPersonalizado = request.getParameter("comentarioPersonalizado");
            String comentarioGeneral = request.getParameter("comentarioGeneral");
            String idPrioridadRequest = request.getParameter("idPrioridad");
            String fec_prioridad = request.getParameter("fec_prioridad");
            if (comentarioPersonalizado.equals("")) {
                comentarioPersonalizado = comentarioGeneral;
            }

            String instruccionPersonalizada = request.getParameter("instruccionPersonalizada");
            String idInstruccionRequest = request.getParameter("idInstruccion");
            idEmpleado = idEmpleado != null ? idEmpleado.trim() : "-1";
            idInstruccionRequest = idInstruccionRequest != null ? idInstruccionRequest.trim() : "-1";
            Integer idInstruccion = Integer.valueOf(idInstruccionRequest);
            Integer idPrioridad = Integer.valueOf(idPrioridadRequest);

            if (instruccionPersonalizada != null && !instruccionPersonalizada.equals(""))//si tiene instruccion personalizada
            {
                Empleado usuarioSession = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);
                Instruccion instruccion = new Instruccion();
                instruccion.setActivo(Constantes.ACTIVADO);
                instruccion.setId_area(usuarioSession.getId_area());
                instruccion.setId_empleado(Integer.parseInt(idEmpleado));
                instruccion.setInstruccion(instruccionPersonalizada);
                this.instruccionService.saveInstruccion(instruccion);
                idInstruccion = instruccion.getId_instruccion();
            }
            List<Empleado> empleados = asuntoForm.getAsunto().getAsunto_detalle().getEmpleadosDest();
            for (Empleado empleado : empleados)
            {
                if (empleado.getId_empleado().equals(Integer.valueOf(idEmpleado)))
                {
                    empleado.setComentario(comentarioPersonalizado);
                    empleado.getInstruccion().setId_instruccion(idInstruccion);
                    empleado.getInstruccion().setInstruccion(instruccionPersonalizada);
                    empleado.getPrioridad().setId_prioridad(idPrioridad);
                    empleado.setFh_limiteDDMMYYYY(fec_prioridad);
                }
            }            
        } catch (Exception e)
        {
            log.debug("Ha ocurrido un error mientras se actualizaba los datos personalizados de los empleados");
            log.error("Se ha producido un error  ->", e);
        }
        return null;
    }

    /*Obtener bitácora por peticion asincrona (ajax)*/
    public ActionForward getBitacora(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        response.setContentType("text/json");

        // Obtiene el area por medio del id
        BitacoraAsunto objeto = asuntoService.getBitacoraAsunto(Integer.valueOf(request.getParameter("id_asunto")));
        if (objeto!=null){
            if (objeto.getBitacora1()==null){
                objeto.setBitacora("");
            }
            else{
                objeto.setBitacora(objeto.getBitacora1());
                if (objeto.getBitacora2()!=null){
                    objeto.setBitacora(objeto.getBitacora()+objeto.getBitacora2());
                }
            }
        }
        else{
            objeto = new BitacoraAsunto();
        }
        JSONObject jsonArray = JSONObject.fromObject(objeto);
        PrintWriter pw = response.getWriter();
        pw.write(jsonArray.toString());
        pw.flush();
        pw.close();
        return null;
    }

    /*Guardar bitácora por peticion asincrona (ajax)*/
    public ActionForward saveBitacora(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        BitacoraAsunto bitacoraAsunto = new BitacoraAsunto();
        try
        {
            bitacoraAsunto.setId_asunto(Integer.valueOf(request.getParameter("id_asunto")));
            bitacoraAsunto.setBitacora(request.getParameter("bitacora"));

            this.asuntoService.saveBitacoraAsunto(bitacoraAsunto);
        } catch (Exception e)
        {
            log.error("Ha ocurrido un error->", e);
            request.setAttribute("mensaje", e.getMessage());
        }
        return null;
    }

    /**
     * @param asuntoService
     *            the asuntoService to set
     */
    public void setAsuntoService(AsuntoService asuntoService)
    {
        this.asuntoService = asuntoService;
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
     * @param documentoService
     *            the documentoService to set
     */
    public void setDocumentoService(DocumentoService documentoService)
    {
        this.documentoService = documentoService;
    }

    /**
     * @param prioridadService
     *            the prioridadService to set
     */
    public void setPrioridadService(PrioridadService prioridadService)
    {
        this.prioridadService = prioridadService;
    }

    /**
     * @param areaService the areaService to set
     */
    public void setAreaService(AreaService areaService)
    {
        this.areaService = areaService;
    }

    /**
     * @param asuntoRevisionService the asuntoRevisionService to set
     */
    public void setAsuntoRevisionService(AsuntoRevisionService asuntoRevisionService)
    {
        this.asuntoRevisionService = asuntoRevisionService;
    }

    /**
     * @param instruccionService the instruccionService to set
     */
    public void setInstruccionService(InstruccionService instruccionService)
    {
        this.instruccionService = instruccionService;
    }

    /**
     * @param asuntoEstadoService the asuntoEstadoService to set
     */
    public void setAsuntoEstadoService(AsuntoEstadoService asuntoEstadoService)
    {
        this.asuntoEstadoService = asuntoEstadoService;
    }

    /**
     * @param tipoSeguimientoService the tipoSeguimientoService to set
     */
    public void setTipoSeguimientoService(TipoSeguimientoService tipoSeguimientoService)
    {
        this.tipoSeguimientoService = tipoSeguimientoService;
    }

    /**
     * @param reporteVolanteService the reporteVolanteService to set
     */
    public void setReporteVolanteService(ReporteVolanteService reporteVolanteService)
    {
        this.reporteVolanteService = reporteVolanteService;
    }
}
