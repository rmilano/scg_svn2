package mx.gob.economia.scg.action;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.economia.scg.form.ConsultaBandejaForm;
import mx.gob.economia.scg.model.Asunto;
import mx.gob.economia.scg.model.CriterioAsunto;
import mx.gob.economia.scg.model.Empleado;
import mx.gob.economia.scg.model.Paginador;
import mx.gob.economia.scg.model.ReportePendientes;
import mx.gob.economia.scg.model.Reporte;
import mx.gob.economia.scg.model.Rol;
import mx.gob.economia.scg.service.AreaService;
import mx.gob.economia.scg.service.ArbolService;
import mx.gob.economia.scg.service.AsuntoService;
import mx.gob.economia.scg.service.EmpleadoService;
import mx.gob.economia.scg.service.EventoService;
import mx.gob.economia.scg.service.ExpedienteService;
import mx.gob.economia.scg.service.TemaService;
import mx.gob.economia.scg.service.TipoDocumentoService;
import mx.gob.economia.scg.service.PrioridadService;
import mx.gob.economia.scg.util.Constantes;
import mx.gob.economia.scg.util.UtilsReportes;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 * DispatchAction para la consulta de asuntos pendientes de atender/turnar
 * 
 * @author valentin.gomez
 */
public class ConsultaBandejaAction extends DispatchAction
{

    AsuntoService asuntoService;
    EmpleadoService empleadoService;
    ArbolService arbolService;
    private TemaService temaService;
    private EventoService eventoService;
    private ExpedienteService expedienteService;
    private TipoDocumentoService tipoDocumentoService;
    private PrioridadService prioridadService;
    private AreaService areaService;

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
            HttpServletRequest request, HttpServletResponse response)
    {
        String destino = "SUCCESS";

        ConsultaBandejaForm cF = (ConsultaBandejaForm) form;
        try
        {
            Integer bandeja = 1;
            Integer estatus = -1;
            Integer copia = 0;
            Integer historico = 0;
            if (request.getParameter("p_bandeja")!=null)
                bandeja = Integer.valueOf(request.getParameter("p_bandeja"));
            if (request.getParameter("p_copia")!=null)
                copia = Integer.valueOf(request.getParameter("p_copia"));
            if (request.getParameter("p_historico")!=null)
                historico = Integer.valueOf(request.getParameter("p_historico"));
            if (request.getParameter("p_estatus")!=null)
                estatus = Integer.valueOf(request.getParameter("p_estatus"));
            Empleado empleado_ses = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);
            cF.setTemas(temaService.listTemas(empleado_ses.getId_area()));
            cF.setEventos(eventoService.listEventos(empleado_ses.getId_area()));
            cF.setExpedientes(expedienteService.listExpedientes(empleado_ses.getId_area()));
            cF.setTiposDocumento(tipoDocumentoService.listTipoDocumentos());
            cF.setPrioridades(prioridadService.listPrioridades());
            // Si no existe "order" inicializa criterios y la lista de asuntos
            if (request.getParameter("order") == null)
            {
                cF.setCriterioAsunto(new CriterioAsunto());
                cF.setAsuntos(new ArrayList<Asunto>());
            }
            // Los pendientes es la suma de todos los estatus
            cF.getCriterioAsunto().setEstatus("");                        
            cF.getCriterioAsunto().getIds_estatus().clear();
            if (estatus.equals(-1)){
                cF.getCriterioAsunto().getIds_estatus().add(Constantes.PENDIENTE + "");
                cF.getCriterioAsunto().getIds_estatus().add(Constantes.TURNADO + "");
                cF.getCriterioAsunto().getIds_estatus().add(Constantes.ATENDIDO + "");
                cF.getCriterioAsunto().getIds_estatus().add(Constantes.ATENCIONPARCIAL + "");
                //cF.getCriterioAsunto().getIds_estatus().add(Constantes.FINALIZADO + "");
            }
            else{
                if (!estatus.equals(Constantes.ATENCIONPARCIAL)){
                    cF.getCriterioAsunto().getIds_estatus().add(estatus+"");
                }
                else{
                    cF.getCriterioAsunto().setEnTramite(Constantes.ACTIVADO);
                }
            }
            //List<Asunto> asuntos = asuntoService.listAsuntosEnBandeja(cF.getCriterioAsunto(), request);
            List<Asunto> asuntos = null;
            cF.getCriterioAsunto().setPaginador(new Paginador());
            String ids_empleados_ccp = "";
            cF.getCriterioAsunto().setBusqueda_detalle(historico);            
            //cF.getCriterioAsunto().setId_area_capt(empleado_ses.getId_area().toString());
            if (bandeja==1){
                if ((empleado_ses.getRoles().contains(new Rol(Rol.RECEPCIONISTA))) && copia==0 && historico==0){
                    cF.getCriterioAsunto().setId_area_dest(empleado_ses.getId_area().toString());
                    cF.getCriterioAsunto().setId_area_remi(null);
                    cF.getCriterioAsunto().setId_empleado_remi(null);
                    cF.getCriterioAsunto().setId_empleado_dest(null);
                }
                else if (copia==0 && historico==0){
                    cF.getCriterioAsunto().setId_empleado_dest(empleado_ses.getId_empleado().toString());
                    cF.getCriterioAsunto().setId_area_remi(null);
                    cF.getCriterioAsunto().setId_area_dest(null);
                    cF.getCriterioAsunto().setId_empleado_remi(null);
                }
                else if (historico==1){                    
                    if (empleado_ses.getRoles().contains(new Rol(Rol.RECEPCIONISTA))){
                        cF.getCriterioAsunto().setId_area_dest(empleado_ses.getId_area().toString());
                        cF.getCriterioAsunto().setId_area_remi(null);
                        cF.getCriterioAsunto().setId_empleado_remi(null);
                        cF.getCriterioAsunto().setId_empleado_dest(null);
                    }
                    else{
                        cF.getCriterioAsunto().setId_empleado_dest(empleado_ses.getId_empleado().toString());
                        cF.getCriterioAsunto().setId_empleado_remi(null);
                        cF.getCriterioAsunto().setId_area_dest(null);
                        cF.getCriterioAsunto().setId_area_remi(null);
                    }
                }
                if ((empleado_ses.getRoles().contains(new Rol(Rol.RECEPCIONISTA)) || empleado_ses.getRoles().contains(new Rol(Rol.CAPTURISTA))) && copia==1){
                    List<Empleado> empleados_ccp = this.empleadoService.listEmpleadosByIdArea(empleado_ses.getId_area());
                    for (int i = 0; i < empleados_ccp.size(); i++)
                    {
                        Empleado empleado = (Empleado) empleados_ccp.get(i);
                        if (i==0){
                            ids_empleados_ccp = empleado.getId_empleado().toString();
                        }
                        else{
                            ids_empleados_ccp += "," +empleado.getId_empleado().toString();
                        }
                    }
                    cF.getCriterioAsunto().setIds_empleados_ccp(ids_empleados_ccp);
                }
                else if(copia == 1)
                {
                    cF.getCriterioAsunto().setIds_empleados_ccp(empleado_ses.getId_empleado() + "");
                }
            }
            else if (bandeja==2){
                if (empleado_ses.getRoles().contains(new Rol(Rol.CAPTURISTA)) && copia==0 && historico==0){
                    cF.getCriterioAsunto().setHistorico(historico);
                    cF.getCriterioAsunto().setId_area_remi(empleado_ses.getId_area().toString());
                    cF.getCriterioAsunto().setId_area_dest(null);
                    cF.getCriterioAsunto().setId_empleado_remi(null);
                    cF.getCriterioAsunto().setId_empleado_dest(null);
                }
                else if (copia==0 && historico==0){
                    cF.getCriterioAsunto().setId_empleado_remi(empleado_ses.getId_empleado().toString());
                    cF.getCriterioAsunto().setId_area_remi(null);
                    cF.getCriterioAsunto().setId_area_dest(null);
                    cF.getCriterioAsunto().setId_empleado_dest(null);
                }
                else if (historico==1){
                    cF.getCriterioAsunto().setHistorico(historico);
                    if (empleado_ses.getRoles().contains(new Rol(Rol.CAPTURISTA))){
                        cF.getCriterioAsunto().setId_area_remi(empleado_ses.getId_area().toString());
                        cF.getCriterioAsunto().setId_area_dest(null);
                        cF.getCriterioAsunto().setId_empleado_remi(null);
                        cF.getCriterioAsunto().setId_empleado_dest(null);
                    }
                    else{
                        cF.getCriterioAsunto().setId_empleado_remi(empleado_ses.getId_empleado().toString());
                        cF.getCriterioAsunto().setId_empleado_dest(null);
                        cF.getCriterioAsunto().setId_area_dest(null);
                        cF.getCriterioAsunto().setId_area_remi(null);
                    }
                }
            }
            else if (bandeja==3){
                if (estatus.equals(7)){
                    cF.getCriterioAsunto().setEstatus(estatus+"");                    
                        cF.getCriterioAsunto().setId_area_capt(empleado_ses.getId_area().toString());
                }
                else if(estatus.equals(3)){
                    cF.getCriterioAsunto().setEstatus(estatus+"");
                    if (empleado_ses.getRoles().contains(new Rol(Rol.CAPTURISTA))||empleado_ses.getRoles().contains(new Rol(Rol.RECEPCIONISTA))){
                        cF.getCriterioAsunto().setId_area_capt(empleado_ses.getId_area().toString());
                    }
                    else {
                          cF.getCriterioAsunto().setId_empleado_dest(empleado_ses.getId_empleado().toString());
                    }
                }
                else if (estatus.equals(-1) && (historico==1)){
                    cF.getCriterioAsunto().setHistorico(historico);
                    cF.getCriterioAsunto().setBusqueda_detalle(historico);
                    cF.getCriterioAsunto().getIds_estatus().clear();
                    cF.getCriterioAsunto().getIds_estatus().add(Constantes.TURNADO + "");
                    cF.getCriterioAsunto().getIds_estatus().add(Constantes.ATENDIDO + "");
                    if (empleado_ses.getRoles().contains(new Rol(Rol.CAPTURISTA)) || empleado_ses.getRoles().contains(new Rol(Rol.RECEPCIONISTA))){
                        cF.getCriterioAsunto().setId_area_remi(empleado_ses.getId_area().toString());
                        cF.getCriterioAsunto().setId_area_dest(null);
                        cF.getCriterioAsunto().setId_empleado_remi(null);
                        cF.getCriterioAsunto().setId_empleado_dest(null);
                    }
                    else{
                        cF.getCriterioAsunto().setId_empleado_remi(empleado_ses.getId_empleado().toString());
                        cF.getCriterioAsunto().setId_empleado_dest(null);
                        cF.getCriterioAsunto().setId_area_dest(null);
                        cF.getCriterioAsunto().setId_area_remi(null);
                    }
                }
            }
            cF.getCriterioAsunto().setConfidencial(null);
            cF.getCriterioAsunto().setEstatus(estatus.toString());
            cF.getCriterioAsunto().setBandeja(bandeja);

            CriterioAsunto criterio = cF.getCriterioAsunto();

            // Se cambio esta linea por las nuevas reglas
            // cF.getCriterioAsunto().getPaginador().setNumRegistros(asuntoService.countAsuntosIniciales(cF.getCriterioAsunto()));
            cF.getCriterioAsunto().getPaginador().setNumRegistros(asuntoService.countAsuntosInicialesNuevasReglas(cF.getCriterioAsunto()));
            if (cF.getCriterioAsunto().getPaginador().getNumRegistros() > 0){
                // Se cambio esta linea por las nuevas reglas
                // asuntos = asuntoService.listAsuntosIniciales(cF.getCriterioAsunto());
                asuntos = asuntoService.listAsuntosInicialesNuevasReglas(cF.getCriterioAsunto());
            }

            asuntos = asuntos == null ? new ArrayList<Asunto>() : asuntos;
            Asunto.asignaIdx(asuntos);
            cF.setAsuntos(asuntos);
            request.getSession().removeAttribute(Constantes.MSG_FOUND);
        } catch (Exception e)
        {
            log.error("Se ha producido un error->", e);
            request.setAttribute("mensaje", "Error interno. Por favor, intentelo otra vez en unos minutos.");
        }
        //cF.getCriterioAsunto().setPaginador(new Paginador());
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
            HttpServletRequest request, HttpServletResponse response)
    {
ConsultaBandejaForm cF = (ConsultaBandejaForm) form;
        String destino = "ERROR";

        try
        {
            // Obtiene los asuntos en base a criterios
            List<Asunto> asuntos = null;

            CriterioAsunto criterio = cF.getCriterioAsunto();
            
            if (cF.getNuevaConsulta().equals("si"))
            {
                cF.getCriterioAsunto().setPaginador(new Paginador());
                Empleado empleado_ses = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);
                cF.getCriterioAsunto().setId_area_capt(empleado_ses.getId_area().toString());
                cF.getCriterioAsunto().getPaginador().setNumRegistros(asuntoService.countAsuntosInicialesNuevasReglas(cF.getCriterioAsunto()));
                if (cF.getCriterioAsunto().getPaginador().getNumRegistros() > 0){
                    asuntos = asuntoService.listAsuntosInicialesNuevasReglas(cF.getCriterioAsunto());
                }
            }
            else
                asuntos = asuntoService.listAsuntosInicialesNuevasReglas(cF.getCriterioAsunto());
            asuntos = asuntos == null ? new ArrayList<Asunto>() : asuntos;
            // mostrar mensaje de atribuno no encontrado
            if (cF.getCriterioAsunto().getPaginador().getNumRegistros() < Constantes.ACTIVO){
                request.getSession().setAttribute(Constantes.MSG_FOUND, Constantes.MSG_FOUND);
            }
            else{
                request.getSession().removeAttribute(Constantes.MSG_FOUND);
                Asunto.asignaIdx(asuntos);
            }

            
            cF.setAsuntos(asuntos);
            destino = "SUCCESS";
        } catch (Exception e)
        {
            log.error("Se ha producido un error->", e);
            request.setAttribute("mensaje",
                    "Error interno. Por favor, intentelo otra vez en unos minutos.");
        }
        return mapping.findForward(destino);
    }

    /**
     * Obtiene los asuntos que estan pendientes por atender
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return consulta con resultados de la consulta
     */
    public ActionForward obtenerAsuntosPendientesPorAtender(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
    {

        ConsultaBandejaForm cF = (ConsultaBandejaForm) form;
        String destino = "ERROR";

        try
        {
            // Obtiene los asuntos en base a criterios
            List<Asunto> asuntos = null;
            cF.getCriterioAsunto().getIds_estatus().clear();
            cF.getCriterioAsunto().getIds_estatus().add(Constantes.TURNADO + "");
            cF.getCriterioAsunto().getIds_estatus().add(Constantes.PENDIENTE + "");

            if (cF.getNuevaConsulta().equals("si"))
            {
                cF.getCriterioAsunto().setPaginador(new Paginador());
                Empleado empleado_ses = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);
                cF.getCriterioAsunto().setId_empleado_dest(empleado_ses.getId_empleado().toString());
                cF.getCriterioAsunto().getPaginador().setNumRegistros(asuntoService.countAsuntosIniciales(cF.getCriterioAsunto()));
                if (cF.getCriterioAsunto().getPaginador().getNumRegistros() > 0){
                    asuntos = asuntoService.listAsuntosIniciales(cF.getCriterioAsunto());
                }
            }
            else
                asuntos = asuntoService.listAsuntosIniciales(cF.getCriterioAsunto());
            asuntos = asuntos == null ? new ArrayList<Asunto>() : asuntos;
            // mostrar mensaje de atribuno no encontrado
            if (cF.getCriterioAsunto().getPaginador().getNumRegistros() < Constantes.ACTIVO){
                request.getSession().setAttribute(Constantes.MSG_FOUND, Constantes.MSG_FOUND);
            }
            else{
                request.getSession().removeAttribute(Constantes.MSG_FOUND);
                Asunto.asignaIdx(asuntos);
            }
            
            cF.setAsuntos(asuntos);
            destino = "SUCCESS";
        } catch (Exception e)
        {
            log.error("Se ha producido un error->", e);
            request.setAttribute("mensaje",
                    "Error interno. Por favor, int�ntelo otra vez en unos minutos.");
        }
        return mapping.findForward(destino);
    }

    public ActionForward generaReporteResumenAsuntos(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response){
        ConsultaBandejaForm cCF = (ConsultaBandejaForm) form;
        try{
            List<Asunto> asuntos = new ArrayList <Asunto> ();
            String chkResp = "";
            chkResp = cCF.getChksRep();
            String[] chkRespsS= chkResp.split(",");
            List <String> chkRespsL = new ArrayList<String>();
            for (String chkResps :  chkRespsS){
                chkRespsL.add(chkResps);
            }
            asuntos.addAll(cCF.getAsuntos());
            /*Dias vencido*/
            final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día
            java.util.Date hoy = new Date(); //Fecha de hoy
            long diferencia;/*( hoy.getTime() - fecha.getTime() )/MILLSECS_PER_DAY; */
            /************************************************/

            for (Asunto asunto : cCF.getAsuntos() ){ //Quitar los asuntos que no esten marcados para reporte.
                /*for (String chkResps :  chkRespsA){
                    if (!chkResps.equals(asunto.getId_asunto().toString())){
                        asuntos.remove(asunto);
                    }
                }*/
                if (!chkRespsL.contains(asunto.getId_asunto().toString())){
                        asuntos.remove(asunto);
                    }
            }

            Integer ultimoEstatusArea;
            String ultimoEstatusArea_desc;

            HashMap<String, Integer> paramConsulta = new HashMap<String, Integer>();//los parametros
            Empleado empleado_ses = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);

            for (int i = 0; i < asuntos.size(); i++){ //Completar los datos necesarios para el reporte
                asuntos.set(i, asuntoService.getAsunto(asuntos.get(i).getId_asunto()));
                asuntos.get(i).setIdx(i+1);
                asuntos.get(i).setUltimaDetalle(asuntos.get(i).getAsuntos_detalles().get(asuntos.get(i).getAsuntos_detalles().size()-1));
                asuntos.get(i).getUltimaDetalle().setPrioridad(prioridadService.getPrioridad(asuntos.get(i).getUltimaDetalle().getPrioridad().getId_prioridad()));
                /*Dias vencido*/
                if (asuntos.get(i).getUltimaDetalle().getFh_limite()!=null){
                    diferencia = ( hoy.getTime() - asuntos.get(i).getUltimaDetalle().getFh_limite().getTime() )/MILLSECS_PER_DAY;
                }
                else{
                    diferencia = 0;
                }
                asuntos.get(i).setTipo_actualizacion((int) (long) diferencia);
                /*-------*/
                /*Remitente inicial (1er detalle)*/
                asuntos.get(i).getUltimaDetalle().setEmpleado_remi(asuntos.get(i).getAsuntos_detalles().get(0).getEmpleado_remi());
                /*Expediente*/
                asuntos.get(i).getExpediente().setExpediente((asuntos.get(i).getExpediente().getExpediente()==null) ? "Sin expediente" : asuntos.get(i).getExpediente().getExpediente());
                /*Asignar la ultima respuesta que se tiene*/
                if (asuntos.get(i).getEstatus()!=1){
                    asuntos.get(i).setUltimaRespuesta((asuntos.get(i).getActualizacionesCaptura().get(0).getComentario()==null) ? "En espera de respuesta" : asuntos.get(i).getActualizacionesCaptura().get(0).getComentario());
                }
                else {
                    asuntos.get(i).setUltimaRespuesta("En espera de respuesta");
                }
                // El reporte debe salir con el estatus correcto
                // Se debe buscar el estatus de acuerdo a lo mostrado en detalle
                paramConsulta.put("id_asunto", asuntos.get(i).getId_asunto());
                paramConsulta.put("id_area", empleado_ses.getId_area());
                ultimoEstatusArea = asuntoService.getUltimoEstatusAsuntoByArea(paramConsulta);

                switch (ultimoEstatusArea)
                {
                    case 0:
                        ultimoEstatusArea_desc = "Inicial";
                        break;
                    case 1:
                        ultimoEstatusArea_desc = "Pendiente";
                        break;
                    case 2:
                        ultimoEstatusArea_desc = "Atendido";
                        break;
                    case 3:
                        ultimoEstatusArea_desc = "Concluido";
                        break;
                    case 14:
                        ultimoEstatusArea_desc = "En Trámite";
                        break;
                    default:
                        ultimoEstatusArea_desc = "";
                        break;
                }
                asuntos.get(i).setUltimoEstatusArea(ultimoEstatusArea_desc);
            }
            /*************************************************************************/
            String anio = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
            String unidad = areaService.getAreaById(empleado_ses.getId_area()).getArea();
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String hora = formato.format(new Date());
            /*************************************************************************/
            JasperDesign jasperDesign;
            String RUTA_LOGO = ResourceBundle.getBundle(Constantes.PROPERTIES).getString("ruta.reporte.logo");
            String RUTA_JRXLM = ResourceBundle.getBundle(Constantes.PROPERTIES).getString("ruta.reporte.resumenAsuntos.jrxml");
            String NOMBRE_ARCHIVO = ResourceBundle.getBundle(Constantes.PROPERTIES).getString("ruta.reporte.resumenAsuntos.nombrearchivo");
            HashMap<String, Object> parametersMap = new HashMap<String, Object>();//los parametros
            InputStream xmlResource = this.getServlet().getServletContext().getResourceAsStream(RUTA_JRXLM);//el archivo de diseno
            //InputStream logo = this.getServlet().getServletContext().getResourceAsStream(RUTA_LOGO);//el logo
            String logo = this.getServlet().getServletContext().getRealPath(RUTA_LOGO);//el logo
            parametersMap.put("LOGO", logo);//la imagen como parametro
            parametersMap.put("ANIO", anio);
            parametersMap.put("UNIDAD", unidad);
            parametersMap.put("HORA", hora);            

            String titulo = "Relación de asuntos PENDIENTES";
            parametersMap.put("TITULO", titulo.toUpperCase());

            List<Asunto> dataSource = new ArrayList<Asunto>();
            dataSource = new ArrayList<Asunto>();            
            dataSource.addAll(asuntos);
            JRBeanCollectionDataSource dataSourceCollection = new JRBeanCollectionDataSource(dataSource);//se crea la coleccion
            jasperDesign = JRXmlLoader.load(xmlResource);//se crea el rescurso
            JasperReport reportPath = JasperCompileManager.compileReport(jasperDesign);// compilacion del xml de diseno
            JasperPrint print = JasperFillManager.fillReport(reportPath, parametersMap, dataSourceCollection);//la impresion del reporte
            byte[] reportePdf = JasperExportManager.exportReportToPdf(print);//se crea el flujo de bytes
            UtilsReportes.descargaPdf(reportePdf, response, "application/pdf", NOMBRE_ARCHIVO);//el header y la impresion del reporte


        } catch (Exception e)
        {
            log.error("Se ha producido un error mienstras se generaba el reporte", e);
        }
        return null;
    }

    /**
     * Obtiene los asuntos que estan pendientes para visto bueno
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return consulta con resultados de la consulta
     */
    public ActionForward obtenerAsuntosPendientesVoBo(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
    {

        ConsultaBandejaForm cF = (ConsultaBandejaForm) form;
        String destino = "ERROR";

        try
        {
            // Obtiene los asuntos en base a criterios
            List<Asunto> asuntos = null;
            cF.getCriterioAsunto().getIds_estatus().clear();
            cF.getCriterioAsunto().getIds_estatus().add(Constantes.ATENDIDO + "");

            if (cF.getNuevaConsulta().equals("si"))
            {
                cF.getCriterioAsunto().setPaginador(new Paginador());
                Empleado empleado_ses = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);
                cF.getCriterioAsunto().setId_empleado_dest(empleado_ses.getId_empleado().toString());
                cF.getCriterioAsunto().getPaginador().setNumRegistros(asuntoService.countAsuntosIniciales(cF.getCriterioAsunto()));
                if (cF.getCriterioAsunto().getPaginador().getNumRegistros() > 0){
                    asuntos = asuntoService.listAsuntosIniciales(cF.getCriterioAsunto());
                }
            }
            else
                asuntos = asuntoService.listAsuntosIniciales(cF.getCriterioAsunto());
            asuntos = asuntos == null ? new ArrayList<Asunto>() : asuntos;
            // mostrar mensaje de atribuno no encontrado
            if (cF.getCriterioAsunto().getPaginador().getNumRegistros() < Constantes.ACTIVO){
                request.getSession().setAttribute(Constantes.MSG_FOUND, Constantes.MSG_FOUND);
            }
            else{
                request.getSession().removeAttribute(Constantes.MSG_FOUND);
                Asunto.asignaIdx(asuntos);
            }

            cF.setAsuntos(asuntos);
            destino = "SUCCESS";
        } catch (Exception e)
        {
            log.error("Se ha producido un error->", e);
            request.setAttribute("mensaje",
                    "Error interno. Por favor, intentelo otra vez en unos minutos.");
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
     * @return the temaService
     */
    public TemaService getTemaService()
    {
        return temaService;
    }

    /**
     * @param temaService the temaService to set
     */
    public void setTemaService(TemaService temaService)
    {
        this.temaService = temaService;
    }

    public EventoService getEventoService()
    {
        return eventoService;
    }

    /**
     * @param temaService the temaService to set
     */
    public void setEventoService(EventoService eventoService)
    {
        this.eventoService = eventoService;
    }

    /**
     * @return the expedienteService
     */
    public ExpedienteService getExpedienteService()
    {
        return expedienteService;
    }

    /**
     * @param expedienteService the expedienteService to set
     */
    public void setExpedienteService(ExpedienteService expedienteService)
    {
        this.expedienteService = expedienteService;
    }

    /**
     * @return the tipoDocumentoService
     */
    public TipoDocumentoService getTipoDocumentoService()
    {
        return tipoDocumentoService;
    }

    /**
     * @param expedienteService the tipoDocumentoService to set
     */
    public void setTipoDocumentoService(TipoDocumentoService tipoDocumentoService)
    {
        this.tipoDocumentoService = tipoDocumentoService;
    }

    /**
     * @return the prioridadService
     */
    public PrioridadService getPrioridadService()
    {
        return prioridadService;
    }

    /**
     * @param expedienteService the prioridadService to set
     */
    public void setPrioridadService(PrioridadService prioridadService)
    {
        this.prioridadService = prioridadService;
    }

    public AreaService getAreaService() {
        return areaService;
    }

    public void setAreaService(AreaService areaService) {
        this.areaService = areaService;
    }
    
}
