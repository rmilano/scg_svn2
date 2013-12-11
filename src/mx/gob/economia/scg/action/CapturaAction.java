package mx.gob.economia.scg.action;

import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.economia.scg.form.CapturaForm;
import mx.gob.economia.scg.model.Asunto;
import mx.gob.economia.scg.model.Documento;
import mx.gob.economia.scg.model.Empleado;
import mx.gob.economia.scg.model.Expediente;
import mx.gob.economia.scg.model.Firma;
import mx.gob.economia.scg.model.Instruccion;
import mx.gob.economia.scg.model.Paginador;
import mx.gob.economia.scg.model.Prioridad;
import mx.gob.economia.scg.model.Tema;
import mx.gob.economia.scg.model.Evento;
import mx.gob.economia.scg.model.Area;
import mx.gob.economia.scg.model.TipoSeguimiento;
import mx.gob.economia.scg.service.AreaService;
import mx.gob.economia.scg.service.AsuntoRevisionService;
import mx.gob.economia.scg.service.AsuntoService;
import mx.gob.economia.scg.service.EmpleadoService;
import mx.gob.economia.scg.service.ExpedienteService;
import mx.gob.economia.scg.service.InstruccionService;
import mx.gob.economia.scg.service.PrioridadService;
import mx.gob.economia.scg.service.ReporteVolanteService;
import mx.gob.economia.scg.service.TemaService;
import mx.gob.economia.scg.service.EventoService;
import mx.gob.economia.scg.service.TipoAsuntoService;
import mx.gob.economia.scg.service.TipoDocumentoService;
import mx.gob.economia.scg.service.TipoSeguimientoService;
import mx.gob.economia.scg.util.Constantes;
import mx.gob.economia.scg.util.Util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 * DispatchAction para la captura de asuntos
 * 
 * @author valentin.gomez
 */
public class CapturaAction extends DispatchAction
{

    private AsuntoService asuntoService;
    private AsuntoRevisionService asuntoRevisionService;
    private EmpleadoService empleadoService;
    private PrioridadService prioridadService;
    private ExpedienteService expedienteService;
    private TemaService temaService;
    private EventoService eventoService;
    private InstruccionService instruccionService;
    private AreaService areaService;
    private TipoAsuntoService tipoAsuntoService;
    private TipoDocumentoService tipoDocumentoService;
    private TipoSeguimientoService tipoSeguimientoService;
    private ReporteVolanteService reporteVolanteService;

    /**
     * Muestra el formulario de captura de asunto
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return pantalla de captura de asunto
     */
    public ActionForward inicio(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
    {
        CapturaForm cF = (CapturaForm) form;
        String destino = "SUCCESS";
        try
        {
            Empleado usuarioSession = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);
            // Obtiene los catalogos para los combos de la pantalla de captura
            asuntoService.loadCatalogosCapturaAsunto(request);
            cF.setEmpleadosRemitente(new ArrayList<Empleado>());
            cF.setEmpleadosDestinatario(new ArrayList<Empleado>());
            cF.setExpedientes(this.expedienteService.listExpedientes(usuarioSession.getId_area()));
            cF.setTemas(this.temaService.listTemas(usuarioSession.getId_area()));
            cF.setEventos(this.eventoService.listEventos(usuarioSession.getId_area()));
            cF.setAreasDependientes(this.areaService.getAreasFromDespĺiegue(usuarioSession.getArea()));
            cF.setTiposAsunto(tipoAsuntoService.listTipoAsuntos());
            cF.setTiposSeguimiento(this.tipoSeguimientoService.listTipoSeguimientos());
            //cF.setFirmantesVolante(this.empleadoService.listEmpleadosByIdArea(usuarioSession.getId_area()));
            cF.setFirmantesVolante(this.empleadoService.listEmpleadosByIdAreaOrdFirm(usuarioSession.getId_area()));
            cF.setFirmantesVolanteRol(this.empleadoService.listByIdAreaOrderByFirmanteRol(usuarioSession.getId_area()));
            cF.getCriterioAsunto().getIds_asunto().clear();
            cF.setEmpleadoCaptura(usuarioSession);
            cF.setEmpleadosRevisores(this.empleadoService.listRevisorFromAreaAndAreaPadre(usuarioSession));
            request.getSession().setAttribute("arbolExternoOficinaSecretario", Constantes.NO_ACTIVO);
            request.getSession().setAttribute("readonlyNoSintesis", "false");
            Asunto asunto = new Asunto();
            asunto.getAsunto_detalle().setFh_registro(new Date());
            cF.setAsunto(asunto);

        } catch (Exception e)
        {
            log.error("Ha ocurrido un error en el inicio de la captura del documento", e);
        }
        return mapping.findForward(destino);
    }

    /**
     * Si el usuario hace clic en el boton de limpiar los criterios de busqueda, entra al siguiente metodo.
     * A diferecion del metodo inicio, aqui la fecha de recepcion esta vacia
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return 
     */
    public ActionForward inicioCriterioBusqueda(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
    {
        Logger log = Logger.getLogger(this.getClass());
        log.debug("En el inicio");
        CapturaForm cF = (CapturaForm) form;
        String destino = "SUCCESS";
        Empleado usuarioSession = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);
        // Obtiene los catalogos para los combos de la pantalla de captura
        asuntoService.loadCatalogosCapturaAsunto(request);
        cF.setEmpleadosRemitente(new ArrayList<Empleado>());
        cF.setEmpleadosDestinatario(new ArrayList<Empleado>());
        cF.setExpedientes(this.expedienteService.listExpedientes(usuarioSession.getId_area()));
        cF.setTemas(this.temaService.listTemas(usuarioSession.getId_area()));
        cF.setAreasDependientes(this.areaService.getAreasByIdPadre(usuarioSession.getId_area()));
        cF.setTiposAsunto(tipoAsuntoService.listTipoAsuntos());
        cF.getAreasDependientes().add(usuarioSession.getArea());
        cF.getCriterioAsunto().getIds_asunto().clear();
        cF.setEmpleadoCaptura(usuarioSession);
        Asunto asunto = new Asunto();
        asunto.getAsunto_detalle().setFh_registroDDMMYYYY("");
        cF.setAsunto(asunto);
        return mapping.findForward(destino);
    }

    /**
     * Este metodo es usuado para buscar los asuntos desde la pantalla de captura
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward buscaAsuntos(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        CapturaForm capturaForm = (CapturaForm) form;
        String destino = "SUCCESS";
        Empleado usuarioSession = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);
        capturaForm.getCriterioAsunto().setId_area_capt("" + usuarioSession.getId_area());

        try
        {
            capturaForm.getCriterioAsunto().setId_expediente(capturaForm.getAsunto().getExpediente().getId_expediente());
            capturaForm.getCriterioAsunto().setId_tema(capturaForm.getAsunto().getTema().getId_tema());
            capturaForm.getCriterioAsunto().setFolio(capturaForm.getAsunto().getFolio());
            capturaForm.getCriterioAsunto().setFh_oficio_iniDDMMYYYY(capturaForm.getAsunto().getFh_oficioDDMMYYYY());
            capturaForm.getCriterioAsunto().setFh_recepcion_iniDDMMYYYY(capturaForm.getAsunto().getFh_recepcionDDMMYYYY());
            capturaForm.getCriterioAsunto().setFh_registroDDMMYYYY(capturaForm.getAsunto().getAsunto_detalle().getFh_registroDDMMYYYY());
            capturaForm.getCriterioAsunto().setId_asunto_ref(capturaForm.getAsunto().getId_asunto_ref());
            capturaForm.getCriterioAsunto().setConfidencial(capturaForm.getAsunto().getConfidencial());
            capturaForm.getCriterioAsunto().setNo_oficio(capturaForm.getAsunto().getNo_oficio());
            capturaForm.getCriterioAsunto().setAsunto(capturaForm.getAsunto().getAsunto());
            capturaForm.getCriterioAsunto().setDescripcion(capturaForm.getAsunto().getDescripcion());
            capturaForm.getCriterioAsunto().setAntecedente(capturaForm.getAsunto().getAntecedente());
            //capturaForm.getCriterioAsunto().setEstatus(Constantes.EN_CAPTURA + "");//la busqueda solo se realiza sobre el primer registro generado del asunto
            capturaForm.getCriterioAsunto().setFh_eventoDDMMYYYY(capturaForm.getAsunto().getFh_eventoDDMMYYYY());

            Asunto asunto = null;
            //capturaForm.getCriterioAsunto().setIds_asunto(this.asuntoService.getIdsAsuntoCapturaByCriterio(capturaForm.getCriterioAsunto()));
            capturaForm.getCriterioAsunto().setIds_asunto(this.asuntoService.listIdsAsuntosEnCaptura(capturaForm.getCriterioAsunto(), request));
            if (capturaForm.getCriterioAsunto().getIds_asunto().size() > Constantes.FIRST)//si existen resultados de la busqueda
            {
                asunto = asuntoService.getAsuntoCaptura(Integer.valueOf(capturaForm.getCriterioAsunto().getIds_asunto().get(Constantes.FIRST)));
                Expediente expediente = this.expedienteService.getExpediente(asunto.getId_expediente());
                Tema tema = this.temaService.getTema(asunto.getId_tema());
                expediente = expediente == null ? new Expediente() : expediente;
                tema = tema == null ? new Tema() : tema;
                asunto.setExpediente(expediente);
                asunto.setTema(tema);
                // Obtiene la lista de remitentes por medio del id del area
                capturaForm.setEmpleadosRemitente(this.empleadoService.listEmpleadosByIdArea(asunto.getAsunto_detalle().getEmpleado_remi().getArea().getId_area()));
                capturaForm.setAsunto(asunto);//el primer asunto de la lista
                capturaForm.getCriterioAsunto().getPaginador().setNumRegistros(capturaForm.getCriterioAsunto().getIds_asunto().size());//el numero de registros
                capturaForm.getCriterioAsunto().getPaginador().setPaginas(capturaForm.getCriterioAsunto().getIds_asunto().size());//las paginas
                capturaForm.getCriterioAsunto().getPaginador().setPagina(Constantes.ACTIVO);
                //Borra las banderas que muestran los botones del detalle
                asuntoService.removeButtonsDetalle(request);
                //	Obtiene las banderas que muestran los botones del detalle
                asuntoService.loadBtnDetalleAsunto(asunto, request);
            }
            else
            {
                asunto = new Asunto();
                capturaForm.setAsunto(asunto);
                capturaForm.getCriterioAsunto().setPaginador(new Paginador());
            }
            capturaForm.setInstrucciones(this.instruccionService.listInstrucciones(usuarioSession.getId_area()));
        } catch (Exception e)
        {
            log.error("Ha ocurrido un error al consultar los asunto" + e.getMessage());
            log.debug("Se ha generado un error ->", e);
        }

        log.error("El expediente es: " + capturaForm.getAsunto().getExpediente().getId_expediente());
        return mapping.findForward(destino);
    }

    /**
     * Si es la oficina del secretario y le da clic en destinatario externo entonces hacer lo siguiente
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception 
     */
    public ActionForward setExternoArea(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        String destino = "SUCCESS";
        try
        {
            CapturaForm capturaForm = (CapturaForm) form;
            Empleado usuarioSession = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);
            request.getSession().setAttribute("arbolExternoOficinaSecretario", Constantes.ACTIVADO);
            capturaForm.setEmpleadosRemitente(this.empleadoService.listEmpleadosByIdArea(capturaForm.getAsunto().getAsunto_detalle().getEmpleado_remi().getArea().getId_area()));
            if (capturaForm.getAsunto().getTema().getId_tema() > Constantes.INSTANCIA_CREADA)
            {
                capturaForm.getAsunto().setTema(this.temaService.getTema(capturaForm.getAsunto().getTema().getId_tema()));
                List<Tema> temas = this.temaService.getTemasByIdPadre(capturaForm.getAsunto().getTema().getId_tema());
                temas = temas == null ? new ArrayList<Tema>() : temas;
                capturaForm.setSubtemas(temas);
            }
            else
            {
                capturaForm.getAsunto().setTema(new Tema());
                capturaForm.setSubtemas(new ArrayList<Tema>());
            }
            capturaForm.setInstrucciones(this.instruccionService.listInstrucciones(usuarioSession.getId_area()));
            capturaForm.getAsunto().getAsunto_detalle().getEmpleado_dest().getArea().setTipo(Constantes.ACTIVADO);//Externo            
        } catch (Exception e)
        {
            log.debug("Ocurio un error mientras se cargaba el arbol externo");
        }
        return mapping.findForward(destino);
    }

    /**
     * Si es la oficina del secretario y le da clic en destinatario interno entonces hacer lo siguiente
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception 
     */
    public ActionForward setInternoArea(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        String destino = "SUCCESS";
        try
        {
            CapturaForm capturaForm = (CapturaForm) form;
            Empleado usuarioSession = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);
            request.getSession().setAttribute("arbolExternoOficinaSecretario", Constantes.NO_ACTIVO);
            capturaForm.setEmpleadosRemitente(this.empleadoService.listEmpleadosByIdArea(capturaForm.getAsunto().getAsunto_detalle().getEmpleado_remi().getArea().getId_area()));
            if (capturaForm.getAsunto().getTema().getId_tema() > Constantes.INSTANCIA_CREADA)
            {
                capturaForm.getAsunto().setTema(this.temaService.getTema(capturaForm.getAsunto().getTema().getId_tema()));
                List<Tema> temas = this.temaService.getTemasByIdPadre(capturaForm.getAsunto().getTema().getId_tema());
                temas = temas == null ? new ArrayList<Tema>() : temas;
                capturaForm.setSubtemas(temas);
            }
            else
            {
                capturaForm.getAsunto().setTema(new Tema());
                capturaForm.setSubtemas(new ArrayList<Tema>());
            }
            capturaForm.setInstrucciones(this.instruccionService.listInstrucciones(usuarioSession.getId_area()));
            capturaForm.getAsunto().getAsunto_detalle().getEmpleado_dest().getArea().setTipo(Constantes.NO_ACTIVO);//interno            
        } catch (Exception e)
        {
            log.debug("Ocurio un error mientras se cargaba el arbol externo");
        }
        return mapping.findForward(destino);
    }

    /**
     * Guarda un asunto y lo envia a revision
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception 
     */
    public ActionForward guardarEnviarRevision(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        CapturaForm capturaForm = (CapturaForm) form;
        String destino = "VISTAPREVIA";
        try
        {
            this.guardarAsunto(mapping, form, request, response);
            this.asuntoRevisionService.enviarAsuntoRevision(capturaForm.getAsunto().getFolio());
            // Sube a request atributo para mostrar exito
            request.setAttribute("exitoMsg", "Se guardó con éxito");

            // Url para regresar despues de guardar
            request.getSession().setAttribute("btnRegresar", "/inicio.do?method=inicio");
        } catch (Exception e)
        {
            log.error("Se ha generado un error mientras se guardaba el asunto y se enviaba a revision", e);
            return mapping.findForward(destino);
        }
        return mapping.findForward(destino);
    }

    /**
     * El revisor aprueba el asunto. Una vez aprovado, se actulizan los datos (si los cambia)
     * y el estatus del asunto pasa a revisado
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward aprovarAsunto(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        CapturaForm cF = (CapturaForm) form;
        String destino = "ERROR";
        try
        {
            Asunto asunto = cF.getAsunto();

            // Guarda el asunto en repositorio
            String RUTA_LOGO = ResourceBundle.getBundle(Constantes.PROPERTIES).getString("ruta.reporte.logo");
            URL urlLogo = this.getServlet().getServletContext().getResource(RUTA_LOGO);
            asunto = asuntoService.updateAsunto(asunto, request, urlLogo);
            Empleado usuarioSession = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);
            this.asuntoRevisionService.setAsuntoRevisado(asunto.getFolio(), usuarioSession.getId_empleado());

        } catch (Exception e)
        {
            log.debug("Se ha generado un error ->", e);
            request.setAttribute("mensaje",
                    "Error interno. Por favor, intentelo otra vez en unos minutos.");
            return mapping.findForward(destino);
        }
        response.sendRedirect("consulta-revision.do?method=inicio");
        return null;
    }

    /**
     * El supervisor envía el asunto nuevamente al capturista, en este caso, se entiende que el area del capturista
     * no es el area del secretario. Lo manda a su jefe para que lo valide y lo firme
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception 
     */
    public ActionForward asuntoSupervisado(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        CapturaForm cF = (CapturaForm) form;
        String destino = "ERROR";
        try
        {
            Asunto asunto = cF.getAsunto();

            // Guarda el asunto en repositorio
            String RUTA_LOGO = ResourceBundle.getBundle(Constantes.PROPERTIES).getString("ruta.reporte.logo");
            URL urlLogo = this.getServlet().getServletContext().getResource(RUTA_LOGO);
            asunto = asuntoService.updateAsunto(asunto, request, urlLogo);
            this.asuntoRevisionService.setAsuntoSupervisado(asunto.getFolio());

        } catch (Exception e)
        {
            log.debug("Se ha generado un error ->", e);
            request.setAttribute("mensaje",
                    "Error interno. Por favor, intentelo otra vez en unos minutos.");
            return mapping.findForward(destino);
        }
        response.sendRedirect("consulta-supervisar.do?method=inicio");
        return null;
    }

    /**
     * Una vez que se ha realizado la busqueda de un asunto, la navegacion entre
     * paginas es por este metodo
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getAsuntoByIndex(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        CapturaForm cF = (CapturaForm) form;
        String destino = "SUCCESS";
        try
        {
            Integer index = cF.getCriterioAsunto().getPaginador().getPagina() - Constantes.ACTIVADO;
            String id_asunto = (cF.getCriterioAsunto().getIds_asunto().get(index) == null) ? "-1" : cF.getCriterioAsunto().getIds_asunto().get(index);
            Asunto asunto = asuntoService.getAsuntoCaptura(Integer.valueOf(id_asunto));
            Expediente expediente = this.expedienteService.getExpediente(asunto.getId_expediente());
            Tema tema = this.temaService.getTema(asunto.getId_tema());
            expediente = expediente == null ? new Expediente() : expediente;
            tema = tema == null ? new Tema() : tema;
            asunto.setExpediente(expediente);
            asunto.setTema(tema);
            // Obtiene la lista de remitentes por medio del id del area
            cF.setEmpleadosRemitente(this.empleadoService.listEmpleadosByIdArea(asunto.getAsunto_detalle().getEmpleado_remi().getArea().getId_area()));
            // Obtiene la lista de destinatarios por medio del id del area
            cF.setAsunto(asunto);
            //	Borra las banderas que muestran los botones del detalle
            asuntoService.removeButtonsDetalle(request);
            //	Obtiene las banderas que muestran los botones del detalle
            asuntoService.loadBtnDetalleAsunto(asunto, request);
        } catch (Exception e)
        {
            log.error("Ha ocurrido un error al obtener un asunto por pagina" + e.getMessage());
            log.debug("Se ha generado un error ->", e);
        }
        return mapping.findForward(destino);
    }

    /**
     * Obtiene las areas con sus hijos si los tienes. Y la propiedad evento si lo tienes
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward getTemasAsinc(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        response.setContentType("text/json");
        PrintWriter pw = response.getWriter();
        try
        {
            String idTema = (request.getParameter("idTema") == null || request.getParameter("idTema").equals("")) ? "-1" : request.getParameter("idTema");
            Map<Integer, List<Tema>> temasMap = this.temaService.getSubtemasFromId(Integer.parseInt(idTema));
            JSONArray json = JSONArray.fromObject(temasMap);
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
     * Muestra el formulario de para modificar la captura de asunto
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return pantalla de captura de asunto
     */
    public ActionForward inicioModifica(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
    {
        CapturaForm cF = (CapturaForm) form;
        String destino = "SUCCESS";
        Empleado usuarioSession = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);

        // Obtiene los catalogos para los combos de la pantalla de captura
        asuntoService.loadCatalogosCapturaAsunto(request);
        cF.setTiposSeguimiento(this.tipoSeguimientoService.listTipoSeguimientos());
        cF.setFirmantesVolante(this.empleadoService.listEmpleadosByIdArea(usuarioSession.getId_area()));

        Integer id_asunto = null;
        request.getSession().setAttribute("arbolExternoOficinaSecretario", Constantes.NO_ACTIVO);
        if (request.getParameter("id_asunto") != null)
        {
            id_asunto = Integer.valueOf(request.getParameter("id_asunto")); // Obtiene el asunto con el formato para modificar captura
        }
        Asunto asunto = asuntoService.getAsuntoCaptura(id_asunto);
        asunto.setArchivado(asunto.getEstatus().equals(Constantes.ARCHIVADO) ? Constantes.ARCHIVADO : asunto.getArchivado());
        // Obtiene la lista de remitentes por medio del id del area
        cF.setEmpleadosRemitente(this.empleadoService.listEmpleadosByIdArea(asunto.getAsunto_detalle().getEmpleado_remi().getArea().getId_area()));

        // Asinga nuevo empleado al detalle
        //asunto.getAsunto_detalle().setEmpleado_dest(new Empleado());

        // Obtiene la lista de destinatarios por medio del id del area

        cF.setSubtemas(new ArrayList<Tema>());
        if (asunto.getTema().getId_tema_padre() != null && asunto.getTema().getId_tema_padre().intValue() > Constantes.NO_ACTIVO)
        {
            Integer idSubtema = asunto.getTema().getId_tema();
            asunto.setTema(this.temaService.getTema(asunto.getTema().getId_tema_padre()));
            asunto.setSubTema(this.temaService.getTema(idSubtema));
            cF.setSubtemas(this.temaService.getTemasByIdPadre(asunto.getTema().getId_tema()));
        }


        cF.setEmpleadosDestinatario(new ArrayList<Empleado>());

        /*
        if (asunto.getTema().getId_tema_padre() != null && asunto.getTema().getId_tema_padre().intValue() > Constantes.NO_ACTIVO)
        {
        } else
        asunto.getTema().setId_tema_padre(asunto.getTema().getId_tema());
        // Sube a request la lista de subtemas
        if (asunto.getTema().getEvento() == null)
        asunto.getTema().setEvento(Constantes.INSTANCIA_CREADA);
        
        //Si el tema que se tiene  dependen de un tema padre. El tema que se guarda en base es el subtema en caso que exista
        if (asunto.getTema() != null)
        {
        Tema temaPadre = this.temaService.getTema(asunto.getTema().getId_tema_padre());
        temaPadre = temaPadre == null ? new Tema() : temaPadre;
        asunto.setSubTema(asunto.getTema());
        asunto.setTema(temaPadre);
        }
        if (asunto.getTema().getEvento().equals(Constantes.NO_ACTIVO)) // La lista de subtemas
        {
        List<Tema> temas = temaService.getTemasByIdPadre(asunto.getTema().getId_tema());
        cF.setSubtemas(temas);
        }
        
         */
        cF.setExpedientes(this.expedienteService.listExpedientes(usuarioSession.getId_area()));
        cF.setEventos(this.eventoService.listEventos(usuarioSession.getId_area()));
        cF.setAreasDependientes(this.areaService.getAreasByIdPadre(usuarioSession.getId_area()));
        cF.getAreasDependientes().add(usuarioSession.getArea());
        cF.getCriterioAsunto().getIds_asunto().clear();
        cF.setEmpleadoCaptura(usuarioSession);



        cF.setAsunto(asunto);
        cF.setInstrucciones(this.instruccionService.listInstrucciones(usuarioSession.getId_area()));
        //	Borra las banderas que muestran los botones del detalle
        asuntoService.removeButtonsDetalle(request);
        //	Obtiene las banderas que muestran los botones del detalle
        asuntoService.loadBtnDetalleAsunto(asunto, request);
        return mapping.findForward(destino);
    }

    /**
     * Generar un nuevo asunto a partir de un CCP
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return pantalla de captura de asunto
     */
    public ActionForward inicioModificaCcp(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
    {
        CapturaForm cF = (CapturaForm) form;
        String destino = "SUCCESS";
        Empleado usuarioSession = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);

        // Obtiene los catalogos para los combos de la pantalla de captura
        asuntoService.loadCatalogosCapturaAsunto(request);
        cF.setTiposSeguimiento(this.tipoSeguimientoService.listTipoSeguimientos());
        cF.setFirmantesVolante(this.empleadoService.listEmpleadosByIdArea(usuarioSession.getId_area()));

        Integer id_asunto = null;
        request.getSession().setAttribute("arbolExternoOficinaSecretario", Constantes.NO_ACTIVO);
        if (request.getParameter("id_asunto") != null)
        {
            id_asunto = Integer.valueOf(request.getParameter("id_asunto")); // Obtiene el asunto con el formato para modificar captura
        }
        Asunto asunto = asuntoService.getAsuntoCaptura(id_asunto);
        asunto.setArchivado(asunto.getEstatus().equals(Constantes.ARCHIVADO) ? Constantes.ARCHIVADO : asunto.getArchivado());
        // Obtiene la lista de remitentes por medio del id del area
        cF.setEmpleadosRemitente(this.empleadoService.listEmpleadosByIdArea(asunto.getAsunto_detalle().getEmpleado_remi().getArea().getId_area()));
        
        // Obtiene la lista de destinatarios por medio del id del area

        cF.setSubtemas(new ArrayList<Tema>());
        if (asunto.getTema().getId_tema_padre() != null && asunto.getTema().getId_tema_padre().intValue() > Constantes.NO_ACTIVO)
        {
            Integer idSubtema = asunto.getTema().getId_tema();
            asunto.setTema(this.temaService.getTema(asunto.getTema().getId_tema_padre()));
            asunto.setSubTema(this.temaService.getTema(idSubtema));
            cF.setSubtemas(this.temaService.getTemasByIdPadre(asunto.getTema().getId_tema()));
        }


        cF.setEmpleadosDestinatario(new ArrayList<Empleado>());
        cF.setExpedientes(this.expedienteService.listExpedientes(usuarioSession.getId_area()));
        cF.setTemas(this.temaService.listTemas(usuarioSession.getId_area()));
        cF.setAreasDependientes(this.areaService.getAreasByIdPadre(usuarioSession.getId_area()));
        cF.getAreasDependientes().add(usuarioSession.getArea());
        cF.getCriterioAsunto().getIds_asunto().clear();
        cF.setEmpleadoCaptura(usuarioSession);



        cF.setAsunto(asunto);
        cF.setInstrucciones(this.instruccionService.listInstrucciones(usuarioSession.getId_area()));
        cF.getAsunto().setId_asunto_ref("CCP");
        cF.getAsunto().setAntecedente(cF.getAsunto().getFolio());
        cF.getAsunto().getEmpleadosCcp().clear(); //Borra los CCPs
        cF.getAsunto().getActualizacionesCaptura().clear(); //borra actualizaciones de folio origen
        cF.getAsunto().getAsunto_detalle().getEmpleadosDest().clear();
        cF.getAsunto().getAsunto_detalle().getEmpleadosDest().add(this.empleadoService.getEmpleadoById(298)); //setea como destinatario a bruno ferrari.
        cF.getAsunto().getAsunto_detalle().setComentario("");
        cF.getAsunto().setId_asunto(-1);
        cF.getAsunto().setFolio("");
        //	Borra las banderas que muestran los botones del detalle
        asuntoService.removeButtonsDetalle(request);
        //	Obtiene las banderas que muestran los botones del detalle
        asuntoService.loadBtnDetalleAsunto(asunto, request);
        return mapping.findForward(destino);
    }

    /**
     * Generar un nuevo asunto a partir de un turnado de gestiona  agenda
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return pantalla de captura de asunto
     */
    public ActionForward inicioCapGestionToAgenda(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
    {
        CapturaForm cF = (CapturaForm) form;
        String destino = "SUCCESS";
        Empleado usuarioSession = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);

        // Obtiene los catalogos para los combos de la pantalla de captura
        asuntoService.loadCatalogosCapturaAsunto(request);
        cF.setTiposSeguimiento(this.tipoSeguimientoService.listTipoSeguimientos());
        cF.setFirmantesVolante(this.empleadoService.listEmpleadosByIdArea(usuarioSession.getId_area()));

        Integer id_asunto = null;
        request.getSession().setAttribute("arbolExternoOficinaSecretario", Constantes.NO_ACTIVO);
        if (request.getParameter("id_asunto") != null)
        {
            id_asunto = Integer.valueOf(request.getParameter("id_asunto")); // Obtiene el asunto con el formato para modificar captura
        }
        Asunto asunto = asuntoService.getAsuntoCaptura(id_asunto);
        asunto.setArchivado(asunto.getEstatus().equals(Constantes.ARCHIVADO) ? Constantes.ARCHIVADO : asunto.getArchivado());
        // Obtiene la lista de remitentes por medio del id del area
        Area areaRemi = this.areaService.getAreaByIdWitoutFilter(asunto.getAsunto_detalle().getEmpleado_remi().getArea().getId_area());
        if (areaRemi.getActivo()==1) {
            cF.setEmpleadosRemitente(this.empleadoService.listEmpleadosByIdArea(asunto.getAsunto_detalle().getEmpleado_remi().getArea().getId_area()));
        }
        else {
            List <Empleado> remitentes = new ArrayList <Empleado>();
            remitentes.add(asunto.getAsunto_detalle().getEmpleado_remi());
            cF.setEmpleadosRemitente(remitentes);
        }
        // Obtiene la lista de destinatarios por medio del id del area

        cF.setSubtemas(new ArrayList<Tema>());
        if (asunto.getTema().getId_tema_padre() != null && asunto.getTema().getId_tema_padre().intValue() > Constantes.NO_ACTIVO)
        {
            Integer idSubtema = asunto.getTema().getId_tema();
            asunto.setTema(this.temaService.getTema(asunto.getTema().getId_tema_padre()));
            asunto.setSubTema(this.temaService.getTema(idSubtema));
            cF.setSubtemas(this.temaService.getTemasByIdPadre(asunto.getTema().getId_tema()));
        }


        cF.setEmpleadosDestinatario(new ArrayList<Empleado>());
        cF.setExpedientes(this.expedienteService.listExpedientes(usuarioSession.getId_area()));
        cF.setTemas(this.temaService.listTemas(usuarioSession.getId_area()));
        cF.setAreasDependientes(this.areaService.getAreasByIdPadre(usuarioSession.getId_area()));
        cF.getAreasDependientes().add(usuarioSession.getArea());
        cF.getCriterioAsunto().getIds_asunto().clear();
        cF.setEmpleadoCaptura(usuarioSession);



        cF.setAsunto(asunto);
        cF.setInstrucciones(this.instruccionService.listInstrucciones(usuarioSession.getId_area()));
        cF.getAsunto().setId_asunto_ref(cF.getAsunto().getFolio());
        cF.getAsunto().getEmpleadosCcp().clear(); //Borra los CCPs
        cF.getAsunto().getActualizacionesCaptura().clear(); //borra actualizaciones de folio origen
        cF.getAsunto().getAsunto_detalle().getEmpleadosDest().clear();
        cF.getAsunto().getAsunto_detalle().getEmpleadosDest().add(this.empleadoService.getEmpleadoById(298)); //setea como destinatario a bruno ferrari.
        cF.getAsunto().getAsunto_detalle().setComentario("");
        cF.getAsunto().setId_asunto(-1);
        cF.getAsunto().setFolio("");
        //	Borra las banderas que muestran los botones del detalle
        asuntoService.removeButtonsDetalle(request);
        //	Obtiene las banderas que muestran los botones del detalle
        asuntoService.loadBtnDetalleAsunto(asunto, request);
        return mapping.findForward(destino);
    }

    /**
     * Guarda un asuntoen repositorio
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return pantalla de exito de captura de asunto
     */
    public ActionForward getFirmaAsunto(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
    {

        CapturaForm cF = (CapturaForm) form;
        String destino = "ERROR";
        try
        {
            Asunto asunto = cF.getAsunto();

            Firma firma = asuntoService.getFirmaAsunto(asunto);
            cF.setFirma(firma);
            request.setAttribute("firma", firma);
            // Url para regresar despues de guardar
            request.getSession().setAttribute("btnRegresar",
                    "/consulta-captura.do?method=inicio");

            destino = "FIRMA";
        } catch (Exception e)
        {
            log.debug("Se ha generado un error ->", e);
            request.setAttribute("mensaje",
                    "Error interno. Por favor, intentelo otra vez en unos minutos.");
            return mapping.findForward(destino);
        }
        return mapping.findForward(destino);
    }

    /**
     * Guarda un asuntoen repositorio
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return pantalla de exito de captura de asunto
     */
    public ActionForward firmarAsunto(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
    {

        CapturaForm cF = (CapturaForm) form;
        String destino = "ERROR";
        try
        {
            asuntoService.firmarAsunto(cF.getFirma(), cF.getAsunto());
            request.getSession().setAttribute("btnRegresar",
                    "/consulta-captura.do?method=inicio");

            destino = "FIRMA";
        } catch (Exception e)
        {
            log.debug("Se ha generado un error ->", e);
            request.setAttribute("mensaje",
                    "Error interno. Por favor, intentelo otra vez en unos minutos.");
            return mapping.findForward(destino);
        }
        return mapping.findForward(destino);
    }

    /**
     * Guarda un asuntoen repositorio
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return pantalla de exito de captura de asunto
     */
    public ActionForward guardarAsunto(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
    {

        CapturaForm cF = (CapturaForm) form;
        String destino = "ERROR";
        try
        {
            Asunto asunto = cF.getAsunto();


            // Guarda el asunto en repositorio
            String RUTA_LOGO = ResourceBundle.getBundle(Constantes.PROPERTIES).getString("ruta.reporte.logo");
            URL urlLogo = this.getServlet().getServletContext().getResource(RUTA_LOGO);
            asunto.setFolio(""); // El folio se calcula automaticamente, asi, si el usuario introduce el folio inicialmente entonces se ignora
            if (asunto.getSubTema() != null && asunto.getSubTema().getId_tema() > Constantes.INSTANCIA_CREADA)//si el tema tiene subtemas el tema que se guarda es el id del subtema
            {
                asunto.getTema().setId_tema(asunto.getSubTema().getId_tema());
            }

            asunto = asuntoService.saveAsunto(asunto, request, urlLogo);

            // Sube a request atributo para mostrar exito
            request.setAttribute("exitoMsg", "Se guardó con éxito");

            // Url para regresar despues de guardar
            request.getSession().setAttribute("btnRegresar",
                    "/consulta-captura.do?method=inicio");

            destino = "VISTAPREVIA";
        } catch (Exception e)
        {
            log.debug("Ha ocurrido un error mientras se guardaba el asunto", e);
            request.setAttribute("mensaje",
                    "Error interno. Por favor, intentelo otra vez en unos minutos.");
            return mapping.findForward(destino);
        }
        return mapping.findForward(destino);
    }

    /**
     * Guardar y enviar a los destinatarios
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return 
     */
    public ActionForward guardarAsuntoEnviarDest(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
    {
        CapturaForm cF = (CapturaForm) form;
        String destino = "ERROR";
        try
        {
            //Generar el volante
            Empleado usuarioSession = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);
            cF.getAsunto().getAsunto_detalle().setEmpleadosCcp(cF.getAsunto().getEmpleadosCcp());
            String folio = this.asuntoService.getFolioAsunto(usuarioSession.getId_area());
            cF.getAsunto().setFolio(folio);
            if (!(cF.getAsunto().getArchivado().equals(Constantes.ARCHIVADO)))
            {
                this.reporteVolanteService.generarVolantesCorrespondenciaPDF(request, cF.getAsunto());
            }
            // Guarda el asunto
            this.guardarAsunto(mapping, form, request, response);

            // Solo se envia a los destinatarios siempre y cuando no se encuentre archivado
            //if (!cF.getAsunto().getArchivado().equals(Constantes.ARCHIVADO) && !cF.getAsunto().getEstatus().equals(Constantes.CONFIDENCIAL))
            if (!cF.getAsunto().getArchivado().equals(Constantes.ARCHIVADO)) //AGG Confidencial -> booleano 20111109
            {
                String RUTA_LOGO = ResourceBundle.getBundle(Constantes.PROPERTIES).getString("ruta.reporte.logo");
                URL urlLogo = this.getServlet().getServletContext().getResource(RUTA_LOGO);
                asuntoRevisionService.enviarAsuntoDestinatario(cF.getAsunto(), urlLogo, request);
            }
            // Sube a request atributo para mostrar exito
            request.setAttribute("exitoMsg", "Se guardó con éxito");
            // Url para regresar despues de guardar
            request.getSession().setAttribute("btnRegresar", "/consulta-captura.do?method=inicio");
            destino = "VISTAPREVIA";

        } catch (Exception e)
        {
            log.debug("Ocurrio une error al enviar al destinatario" + e);
            request.setAttribute("mensaje",
                    "Error interno. Por favor, intentelo otra vez en unos minutos.");
            return mapping.findForward(destino);
        }
        return mapping.findForward(destino);
    }

    /**
     * Modificar y enviar a los destinatarios
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return 
     */
    public ActionForward modificarAsuntoEnviarDest(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
    {
        CapturaForm cF = (CapturaForm) form;
        String destino = "ERROR";
        try
        {
            this.modificarAsunto(mapping, form, request, response);
            if (!(cF.getAsunto().getArchivado().equals(Constantes.ARCHIVADO)))
            {
                this.reporteVolanteService.generarVolantesCorrespondenciaPDF(request, cF.getAsunto());
            }
            // Solo se envia a los destinatarios siempre y cuando no se encuentre archivado
            //if (!cF.getAsunto().getArchivado().equals(Constantes.ARCHIVADO) && !cF.getAsunto().getEstatus().equals(Constantes.CONFIDENCIAL))
            if (!cF.getAsunto().getArchivado().equals(Constantes.ARCHIVADO)){ //AGG Confidencial -> booleano 20111109
                String RUTA_LOGO = ResourceBundle.getBundle(Constantes.PROPERTIES).getString("ruta.reporte.logo");
                URL urlLogo = this.getServlet().getServletContext().getResource(RUTA_LOGO);
                asuntoRevisionService.enviarAsuntoDestinatario(cF.getAsunto(), urlLogo, request);
            }
            // Sube a request atributo para mostrar exito
            request.setAttribute("exitoMsg", "Se guardó con éxito");

            // Url para regresar despues de guardar
            request.getSession().setAttribute("btnRegresar",
                    "/consulta-captura.do?method=inicio");

            destino = "VISTAPREVIA";
        } catch (Exception e)
        {
            log.debug("Se ha generado un error ->" + e);
            request.setAttribute("mensaje",
                    "Error interno. Por favor, intentelo otra vez en unos minutos.");
            return mapping.findForward(destino);
        }
        return mapping.findForward(destino);
    }

    /**
     * Muestra pantalla para editar la captura de un asunto
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return pantalla de captura de asunto
     */
    public ActionForward editarAsunto(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        CapturaForm capturaForm = (CapturaForm) form;
        String destino = "SUCCESS";
        try
        {
            if (capturaForm.getAsunto().getTema().getId_tema() != null && capturaForm.getAsunto().getTema().getId_tema() > Constantes.NO_ACTIVO){
                capturaForm.setSubtemas(this.temaService.getTemasByIdPadre(capturaForm.getAsunto().getTema().getId_tema()));
            }
            //JTS checa si hubo un remitente seleccionado y lo pone en el combo
            if (capturaForm.getEmpleadosRemitente().isEmpty() && capturaForm.getAsunto().getAsunto_detalle().getEmpleado_remi().getId_empleado() > 0)
            {
                capturaForm.getEmpleadosRemitente().add(capturaForm.getAsunto().getAsunto_detalle().getEmpleado_remi());
            }
            //Poner la lista de empleados del area del empleado remitente
            capturaForm.setEmpleadosRemitente(this.empleadoService.listEmpleadosByIdArea(
                    capturaForm.getAsunto().getAsunto_detalle().getEmpleado_remi().getArea().getId_area()));
            //Los datos completos del empleado remitente
            capturaForm.getAsunto().getAsunto_detalle().setEmpleado_remi(
                    this.empleadoService.getEmpleadoById(capturaForm.getAsunto().getAsunto_detalle().getEmpleado_remi().getId_empleado()));
            //la lista de los empleados destinatarios
            capturaForm.setEmpleadosDestinatario(this.empleadoService.listEmpleadosByIdArea(
                    capturaForm.getAsunto().getAsunto_detalle().getEmpleado_dest().getArea().getId_area()));

        } catch (Exception e)
        {
            request.setAttribute("mensaje", "Ocurrio un error al obtener los empledos del area remitente y destinatario");
            return mapping.findForward(destino);
        }
        return mapping.findForward(destino);
    }

    /**
     * Editar asunto 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return 
     */
    public ActionForward cargaAsuntoSessionOnCatalog(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)
    {
        CapturaForm capturaForm = (CapturaForm) form;
        String destino = "SUCCESS";
        try
        {
            if (request.getSession().getAttribute(
                    Constantes.CAPTURA_CATALOGO_SESSION) != null)
            {
                Asunto asunto = (Asunto) request.getSession().getAttribute(
                        Constantes.CAPTURA_CATALOGO_SESSION);
                capturaForm.setAsunto(asunto);
            }
        } catch (Exception e)
        {
            request.setAttribute("mensaje",
                    "Ocurrio un error al cargar el asunto en session en el formulario");
            return mapping.findForward(destino);
        }
        return mapping.findForward(destino);
    }

    /*
     * Capturar el formulario despues de agregar un area y enviarlo a la funcion
     * editar
     */
    public ActionForward rediredActionFromCatalog(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        CapturaForm capturaForm = (CapturaForm) form;
        request.getSession().setAttribute(Constantes.CAPTURA_CATALOGO_SESSION,
                capturaForm.getAsunto());
        response.sendRedirect("captura.do?method=cargaAsuntoSessionOnCatalog");
        return null;
    }

    /**
     * Muestra el detalle del asunto antes de guardarlo
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return pantalla con detalle del asunto
     */
    public ActionForward vistaPreviaAsunto(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        CapturaForm capturaForm = (CapturaForm) form;
        Integer idTema = 0, idSubtema = 0;
        String forward = "ERROR";
        try
        {
            Empleado usuarioSession = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);
            // Carga los datos adicionales del remitente
            capturaForm.getAsunto().getAsunto_detalle().setEmpleado_remi(
                    this.empleadoService.getEmpleadoByIdWF(capturaForm.getAsunto().getAsunto_detalle().getEmpleado_remi().getId_empleado()));
            if (capturaForm.getAsunto().getAsunto_detalle().getEmpleado_remi().getArea()==null){
                capturaForm.getAsunto().getAsunto_detalle().getEmpleado_remi().setArea(this.areaService.getAreaByIdWitoutFilter(capturaForm.getAsunto().getAsunto_detalle().getEmpleado_remi().getId_area()));
            }
            // Carga los datos adicionales del destinatario
            capturaForm.getAsunto().getAsunto_detalle().setEmpleado_dest(
                    this.empleadoService.getEmpleadoById(capturaForm.getAsunto().getAsunto_detalle().getEmpleado_dest().getId_empleado()));
            //la lista de instrucciones
            capturaForm.setInstrucciones(this.instruccionService.listInstrucciones(usuarioSession.getId_area()));
            //El expediente
            capturaForm.getAsunto().setExpediente(this.expedienteService.getExpediente(capturaForm.getAsunto().getExpediente().getId_expediente()));
            //El tema
            capturaForm.getAsunto().setEvento(this.eventoService.getEvento(capturaForm.getAsunto().getEvento().getId_evento()));
            // El empleado Firmante
            Empleado empleadoFirmante = capturaForm.getAsunto().getAsunto_detalle().getEmpleadoFirmanteVolante().getId_empleado() == null ?
                                        new Empleado() : this.empleadoService.getEmpleadoById(capturaForm.getAsunto().getAsunto_detalle().getEmpleadoFirmanteVolante().getId_empleado());
            empleadoFirmante = empleadoFirmante == null ? new Empleado() : empleadoFirmante;
            capturaForm.getAsunto().getAsunto_detalle().setEmpleadoFirmanteVolante(empleadoFirmante);

            //El tipo de seguimiento
            TipoSeguimiento tipoSeguimiento = this.tipoSeguimientoService.getTipoSeguimiento(capturaForm.getAsunto().getAsunto_detalle().getTipoSeguimiento().getIdTipoSeguimiento());
            tipoSeguimiento = tipoSeguimiento == null ? new TipoSeguimiento() : tipoSeguimiento;
            capturaForm.getAsunto().getAsunto_detalle().setTipoSeguimiento(tipoSeguimiento);

            idTema = capturaForm.getAsunto().getTema().getId_tema();
            idSubtema = capturaForm.getAsunto().getSubTema().getId_tema();
            capturaForm.getAsunto().setTema(this.temaService.getTema(idTema));
            // Solo si el tema seleccionado tiene subtemas
            List<Tema> subtemas = this.temaService.getTemasByIdPadre(idTema);
            capturaForm.getAsunto().setSubTema((subtemas != null && subtemas.size() > Constantes.NO_ACTIVO) ? this.temaService.getTema(idSubtema) : new Tema());
            capturaForm.getAsunto().setExpediente((capturaForm.getAsunto().getExpediente() != null) ? 
                                                     this.expedienteService.getExpediente(capturaForm.getAsunto().getExpediente().getId_expediente())
                                                     : new Expediente());
            capturaForm.getAsunto().setTema((capturaForm.getAsunto().getTema() != null) ?
                                                     capturaForm.getAsunto().getTema()
                                                     : new Tema());
            capturaForm.getAsunto().setEvento((capturaForm.getAsunto().getEvento() != null) ?
                                                     capturaForm.getAsunto().getEvento()
                                                     : new Evento());
            // El tipo de asunto
            capturaForm.getAsunto().setTipoAsunto(this.tipoDocumentoService.getTipoDocumento(capturaForm.getAsunto().getTipoAsunto().getId_tipo_documento()));

            // Las intrucciones por cada empleado
            for (Empleado empleado : capturaForm.getAsunto().getAsunto_detalle().getEmpleadosDest())
            {
                Instruccion instruccion = this.instruccionService.getInstruccion(empleado.getInstruccion().getId_instruccion());
                instruccion = instruccion == null ? new Instruccion() : instruccion;
                empleado.setInstruccion(instruccion);
            }
            // Obtiene la lista de de prioridades
            Prioridad prioridad = this.prioridadService.getPrioridad(capturaForm.getAsunto().getAsunto_detalle().getPrioridad().getId_prioridad());
            if (prioridad != null)
            {
                capturaForm.getAsunto().getAsunto_detalle().setPrioridad(prioridad);
            }

            // el menu de navegacion
            asuntoService.removeButtonsDetalle(request);
            asuntoService.loadBtnDetalleAsunto(capturaForm.getAsunto(), request);
			forward = "VISTAPREVIA";

        } catch (Exception e)
        {
            request.setAttribute("mensaje", "Ocurrio un error al obtener los datos del remitente y destinatario del asunto");
            log.debug("Se ha generado un error: ", e);
            return mapping.findForward(forward);
        }
        return mapping.findForward(forward);
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

        CapturaForm cF = (CapturaForm) form;
        try
        {
            // Obtiene el id del empleado de request
            String id_empleado = request.getParameter("idEmpleado");
            String nombre_completo = request.getParameter("nombreEmpleado");
            id_empleado = id_empleado != null ? id_empleado.trim() : "-1";

            // Crea el empleado a agregar como ccp
            Empleado empleadoCcp = new Empleado();
            empleadoCcp.setId_empleado(Integer.parseInt(id_empleado));
            empleadoCcp.setNombre_completo(nombre_completo);

            // Obtiene los empleados con copia para del detalle
            List<Empleado> empleadosCcp = cF.getAsunto().getEmpleadosCcp();

            // El empelado esta en la lista de empleados con copia para
            boolean existe = empleadosCcp.contains(empleadoCcp);
            // Si no ha sido agregado
            if (!existe)
            {
                // Agrega el empleado a la lista de empleados ccp
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
            log.debug("Se ha generado un error ->", e);
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

        CapturaForm cF = (CapturaForm) form;
        try
        {
            // Obtiene el id del empleado de request
            String id_empleado = request.getParameter("idEmpleado");
            id_empleado = id_empleado != null ? id_empleado.trim() : "-1";

            // Obtiene los empleados con copia para del detalle
            List<Empleado> empleadosCcp = cF.getAsunto().getEmpleadosCcp();

            for (int i = 0; i < empleadosCcp.size(); i++)
            {
                Empleado empleado = (Empleado) empleadosCcp.get(i);

                // Busca el empleado por medio del id y lo elimina de la lista
                if (empleado.getId_empleado().equals(
                        Integer.parseInt(id_empleado)))
                {
                    empleadosCcp.remove(i);
                }
            }

        } catch (Exception e)
        {
            log.debug("Se ha generado un error ->", e);
            request.setAttribute("mensaje",
                    "Error interno. Por favor, intentelo otra vez en unos minutos.");
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

        CapturaForm cF = (CapturaForm) form;
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
            empleadoDest.getInstruccion().setInstruccion("instruccionPersonalizada");


            // Obtiene los empleados destinatarios
            List<Empleado> empleadosDest = cF.getAsunto().getAsunto_detalle().getEmpleadosDest();

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
            log.debug("Se ha generado un error ->", e);
            request.setAttribute("mensaje",
                    "Error interno. Por favor, intentelo otra vez en unos minutos.");
        }
        return null;
    }

    /**
     * Actualiza los empleados destinatarios por peticion ajax
     * @param mapping
     * @param form
     * @param request
     * @param reponse
     * @return
     * @throws Exception
     */
    public ActionForward actualizaEmpleadoDestinatario(ActionMapping mapping,
            ActionForm form, HttpServletRequest request, HttpServletResponse reponse)
            throws Exception
    {
        CapturaForm capturaForm = (CapturaForm) form;
        try
        {
            String idEmpleado = request.getParameter("idEmpleado");
            String comentarioPersonalizado = request.getParameter("comentarioPersonalizado");
            String instruccionPersonalizada = request.getParameter("instruccionPersonalizada");
            String idInstruccionRequest = request.getParameter("idInstruccion");
            String comentarioGeneral = request.getParameter("comentarioGeneral");
            if (comentarioPersonalizado.equals("")) {
                comentarioPersonalizado = comentarioGeneral;
            }
            idEmpleado = idEmpleado != null ? idEmpleado.trim() : "-1";
            idInstruccionRequest = idInstruccionRequest != null ? idInstruccionRequest.trim() : "-1";
            Integer idInstruccion = Integer.valueOf(idInstruccionRequest);

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
            List<Empleado> empleados = capturaForm.getAsunto().getAsunto_detalle().getEmpleadosDest();
            for (Empleado empleado : empleados)
            {
                if (empleado.getId_empleado().equals(Integer.valueOf(idEmpleado)))
                {
                    if (comentarioPersonalizado.equals("")) {
                        comentarioPersonalizado = capturaForm.getAsunto().getAsunto_detalle().getComentario();
                    }
                    if (comentarioPersonalizado != null && comentarioPersonalizado.trim().length() > Constantes.NO_ACTIVO)
                    {
                        empleado.setComentario(comentarioPersonalizado);
                        capturaForm.getAsunto().getAsunto_detalle().setComentario("");
                    }
                    else
                    {
                        empleado.setComentario("");
                    }

                    Instruccion beanInstruccion = this.instruccionService.getInstruccion(idInstruccion);
                    beanInstruccion = beanInstruccion == null ? new Instruccion() : beanInstruccion;
                    empleado.setInstruccion(beanInstruccion);
                }
            }

        } catch (Exception e)
        {
            log.debug("Ha ocurrido un error mientras se actualizaba los datos personalizados de los empleados");
            log.debug("Se ha generado un error ->", e);
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

        CapturaForm cF = (CapturaForm) form;
        try
        {
            // Obtiene el id del empleado de request
            String id_empleado = request.getParameter("idEmpleado");
            id_empleado = id_empleado != null ? id_empleado.trim() : "-1";

            // Obtiene los empleados destinatarios
            List<Empleado> empleadosDest = cF.getAsunto().getAsunto_detalle().getEmpleadosDest();

            for (int i = 0; i < empleadosDest.size(); i++)
            {
                Empleado empleado = (Empleado) empleadosDest.get(i);

                // Busca el empleado por medio del id y lo elimina de la lista
                if (empleado.getId_empleado().equals(
                        Integer.parseInt(id_empleado)))
                {
                    empleadosDest.remove(i);
                }
            }

        } catch (Exception e)
        {
            log.debug("Se ha generado un error ->", e);
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

        CapturaForm cF = (CapturaForm) form;
        try
        {
            Documento documento = new Documento();

            // Asigna el archivo adjunto al documento
            documento.setAdjunto(cF.getAsunto().getAsunto_detalle().getDocumento().getAdjunto());

            // Asigna el nombre de la ruta del documento adjunto
            documento.setRuta_adjunto(request.getParameter("ruta_adjunto"));

            // Asigna el nombre del documento
            documento.setDocumento(request.getParameter("documento"));

            // Asigna el nombre del documento con la extension
            documento.setDocumento(documento.getDocumento());

            // Asigna el tipo del documento
            Integer id_tipo_documento = Integer.valueOf(request.getParameter("id_tipo_documento"));

            documento.getTipo_documento().setId_tipo_documento(
                    id_tipo_documento);

            String tipo_documento = request.getParameter("tipo_documento");

            documento.getTipo_documento().setTipo_documento(tipo_documento);

            // Obtiene los documentos del detalle
            List<Documento> documentos = cF.getAsunto().getAsunto_detalle().getDocumentos();

            // Si no existe en detalle agrega el documento
            boolean existe = documentos.contains(documento);

            if (!existe)
            {
                int idx = -1;
                for (int i = 0; i < documentos.size(); i++)
                {
                    Documento doc = (Documento) documentos.get(i);
                    idx = doc.getIdx();
                }
                documento.setIdx(idx + 1);
                documentos.add(documento);

                // Crea un objetoJSON con el documento a adjuntar
                JSONObject json = JSONObject.fromObject(new Documento(documento.getDocumento(), documento.getId_documento(), documento.getTipo_documento(), documento.getIdx()));
                PrintWriter pw = response.getWriter();
                pw.write(json.toString());
                pw.flush();
                pw.close();
            }
        } catch (Exception e)
        {
            log.debug("Se ha generado un error ->", e);
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
        CapturaForm cF = (CapturaForm) form;
        try
        {
            // Obtiene el idx del documento a eliminar
            int idx = request.getParameter("idx") != null ? Integer.parseInt(request.getParameter("idx")) : -1;

            // Obtiene la lista de documentos adjuntados en el detalle
            List<Documento> documentos = cF.getAsunto().getAsunto_detalle().getDocumentos();
            for (int i = 0; i < documentos.size(); i++)
            {
                Documento documento = (Documento) documentos.get(i);

                // Busca el documento por medio del idx y lo elimina de la lista
                if (documento.getIdx() == idx)
                {
                    documentos.remove(i);
                }
            }
        } catch (Exception e)
        {
            log.debug("Se ha generado un error ->", e);
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

        CapturaForm cF = (CapturaForm) form;

        response.setContentType("multipart/form-data");

        try
        {
            // Obtiene el idx del documento a descargar
            int idx = request.getParameter("idx") != null ? Integer.parseInt(request.getParameter("idx")) : -1;

            // Obtiene la lista de documentos adjuntos
            List<Documento> documentos = cF.getAsunto().getAsunto_detalle().getDocumentos();

            // Recorre la lista de adjuntos
            for (Documento documento : documentos)
            {
                if (documento.getIdx() == idx) // Descarga el documento seleccionado
                {
                    Util.descargarArchivo(documento.getBlob(), response,
                            documento.getDocumento());
                }
            }

        } catch (Exception e)
        {
            log.debug("Se ha generado un error ->", e);
            request.setAttribute("mensaje",
                    "Error interno. Por favor, intentelo otra vez en unos minutos.");
        }
        return null;
    }

    /**
     * Guarda un asuntoen repositorio
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return pantalla de exito de captura de asunto
     */
    public ActionForward modificarAsunto(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
    {
//Rivisar si no tiene hijo ponerle x default -1
        CapturaForm cF = (CapturaForm) form;
        String destino = "ERROR";
        try
        {
            Asunto asunto = cF.getAsunto();

            // Guarda el asunto en repositorio
            String RUTA_LOGO = ResourceBundle.getBundle(Constantes.PROPERTIES).getString("ruta.reporte.logo");
            URL urlLogo = this.getServlet().getServletContext().getResource(RUTA_LOGO);
            asunto = asuntoService.updateAsunto(asunto, request, urlLogo);

            // Sube a request atributo para mostrar exito
            request.setAttribute("exitoMsg", "Se guardó con éxito");

            // Url para regresar despues de guardar
            request.getSession().setAttribute("btnRegresar",
                    "/consulta-captura.do?method=inicio");

            destino = "VISTAPREVIA";
        } catch (Exception e)
        {
            log.debug("Se ha generado un error ->", e);
            request.setAttribute("mensaje",
                    "Error interno. Por favor, intentelo otra vez en unos minutos.");
            return mapping.findForward(destino);
        }
        return mapping.findForward(destino);
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
     * @param empleadoService
     *            the empleadoService to set
     */
    public void setEmpleadoService(EmpleadoService empleadoService)
    {
        this.empleadoService = empleadoService;
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
     * @param expedienteService the expedienteService to set
     */
    public void setExpedienteService(ExpedienteService expedienteService)
    {
        this.expedienteService = expedienteService;
    }

    /**
     * @param temaService the temaService to set
     */
    public void setTemaService(TemaService temaService)
    {
        this.temaService = temaService;
    }

    /**
     * @param instruccionService the instruccionService to set
     */
    public void setInstruccionService(InstruccionService instruccionService)
    {
        this.instruccionService = instruccionService;
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
     * @param tipoAsuntoService the tipoAsuntoService to set
     */
    public void setTipoAsuntoService(TipoAsuntoService tipoAsuntoService)
    {
        this.tipoAsuntoService = tipoAsuntoService;
    }

    /**
     * @param tipoDocumentoService the tipoDocumentoService to set
     */
    public void setTipoDocumentoService(TipoDocumentoService tipoDocumentoService)
    {
        this.tipoDocumentoService = tipoDocumentoService;
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

    /**
     * @param temaService the eventoService to set
     */
    public void setEventoService(EventoService eventoService)
    {
        this.eventoService = eventoService;
    }
}
