/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.economia.scg.action;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.gob.economia.scg.form.ReporteForm;
import mx.gob.economia.scg.model.CriterioReporte;
import mx.gob.economia.scg.model.CriterioAsunto;
import mx.gob.economia.scg.model.Empleado;
import mx.gob.economia.scg.model.Expediente;
import mx.gob.economia.scg.model.Paginador;
import mx.gob.economia.scg.model.Reporte;
import mx.gob.economia.scg.model.Asunto;
import mx.gob.economia.scg.model.AsuntoDetalle;
import mx.gob.economia.scg.model.Tema;
import mx.gob.economia.scg.service.AreaService;
import mx.gob.economia.scg.service.ExpedienteService;
import mx.gob.economia.scg.service.ReporteService;
import mx.gob.economia.scg.service.TemaService;
import mx.gob.economia.scg.service.AsuntoService;
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
import org.apache.struts.util.LabelValueBean;

/**
 *
 * @author gerardo
 */
public class ReporteAction extends DispatchAction
{

    private PrioridadService prioridadService;
    private AsuntoService asuntoService;
    private ReporteService reporteService;
    private AreaService areaService;
    private TemaService temaService;
    private ExpedienteService expedienteService;
    //private List<Reporte> dataSourceReporte = new ArrayList<Reporte>();

    /**
     * El inicio de la pantalla de reporte
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward inicio(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        String forward = "SUCCESS";
        ReporteForm reporteForm = (ReporteForm) form;
        reporteForm.getReporteAsuntos().getDetalleAsunto().clear();
        Empleado usuario = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);
        reporteForm.setTemas(this.temaService.listTemas(usuario.getArea().getId_area()));
        reporteForm.setExpedientes(this.expedienteService.listExpedientes(usuario.getArea().getId_area()));
        reporteForm.setCriterioReporte(new CriterioReporte());
        reporteForm.getCriterioReporte().setId_estatus(Constantes.INSTANCIA_CREADA);
        reporteForm.getCriterioReporte().setPaginador(new Paginador());
        return mapping.findForward(forward);
    }

    /**
     * El preporte general de asuntos
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward reporteGeneralAsuntos(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        String destino = "SUCCESS";

        try
        {
            ReporteForm reporteForm = (ReporteForm) form;
            Tema tema = this.temaService.getTema(reporteForm.getCriterioReporte().getId_tema()) == null ? new Tema() : this.temaService.getTema(reporteForm.getCriterioReporte().getId_tema());
            Expediente expediente = this.expedienteService.getExpediente(reporteForm.getCriterioReporte().getId_expediente()) == null ? new Expediente() : this.expedienteService.getExpediente(reporteForm.getCriterioReporte().getId_expediente());
            reporteForm.setTema(tema);
            reporteForm.setExpediente(expediente);
            reporteForm.getCriterioReporte().setTema(reporteForm.getTema().getTema());
            reporteForm.getCriterioReporte().setExpediente(reporteForm.getExpediente().getExpediente());
            reporteForm.setReporteAsuntos(this.reporteService.getConcentradoAsuntoEstatusArea(reporteForm.getCriterioReporte()));

        } catch (Exception e)
        {
            destino = "ERROR";
            log.error("Ha ocurrido un error en la transaccion", e);
            request.setAttribute("mensaje", "Error interno. Por favor, inténtelo nuevamente en unos minutos.");
        }
        return mapping.findForward(destino);
    }

    /**
     * El reporte en formato pdf
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward reporteGeneralAsuntosPdf(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ReporteForm reporteForm = (ReporteForm) form;
        JasperDesign jasperDesign;

        try
        {
            //InputStream xmlResource = this.getClass().getClassLoader().getResourceAsStream("reporteGeneralAsuntosPdf.jrxml");
            HashMap<String, Object> parametersMap = new HashMap<String, Object>();//los parametros
            InputStream xmlResource = this.getServlet().getServletContext().getResourceAsStream("/jasperxml/reporteGeneralAsuntosPdf.jrxml");//el archivo de diseno
            InputStream logo = this.getServlet().getServletContext().getResourceAsStream("/imagenes/SE.jpg");//el logo
            List<Reporte> dataSource = reporteForm.getReporteAsuntos().getDetalleAsunto();// la lista del subreporte

            parametersMap.put("LOGO", logo);//la imagen como parametro
            JRBeanCollectionDataSource dataSourceCollection = new JRBeanCollectionDataSource(dataSource);//se crea la coleccion

            jasperDesign = JRXmlLoader.load(xmlResource);//se crea el rescurso
            JasperReport reportPath = JasperCompileManager.compileReport(jasperDesign);// compilacion del xml de diseno
            JasperPrint print = JasperFillManager.fillReport(reportPath, parametersMap, dataSourceCollection);//la impresion del reporte            
            byte[] reportePdf = JasperExportManager.exportReportToPdf(print);//se crea el flujo de bytes
            UtilsReportes.descargaPdf(reportePdf, response, "application/pdf", "ReporteGeneralAsuntos.pdf");//el header y la impresion del reporte
        } catch (Exception e)
        {
            log.debug("Ha ocurrido un error mientras se generaba el pdf para el reporte general de asunto");
            log.error("Ha ocurrido un error en la transaccion", e);
        }
        return null;
    }

    /**
     * El reporte en formado xls
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward reporteGeneralAsuntosXls(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ReporteForm reporteForm = (ReporteForm) form;
        JasperDesign jasperDesign;
        try
        {

            HashMap<String, Object> parametersMap = new HashMap<String, Object>();//los parametros
            InputStream xmlResource = this.getServlet().getServletContext().getResourceAsStream("/jasperxml/reporteGeneralAsuntosXls.jrxml");//el archivo de diseno
            InputStream logo = this.getServlet().getServletContext().getResourceAsStream("/imagenes/SE.jpg");//el logo
            jasperDesign = JRXmlLoader.load(xmlResource);//se crea el rescurso
            JasperReport reportPath = JasperCompileManager.compileReport(jasperDesign);// compilacion del xml de diseno
            List<Reporte> dataSource = reporteForm.getReporteAsuntos().getDetalleAsunto();// la lista del subreporte
            parametersMap.put("LOGO", logo);//la imagen como parametro
            JRBeanCollectionDataSource dataSourceCollection = new JRBeanCollectionDataSource(dataSource);//se crea la coleccion

            if (this.reporteService.getDataSourceConnection() != null)
            {
                this.reporteService.getDataSourceConnection().close();
            }
            String archivo = "ReporteGeneralAsuntos.xls";
            UtilsReportes.descargaXls(reportPath, parametersMap, dataSourceCollection, response, archivo);

        } catch (Exception e)
        {
            log.debug("Ha ocurrido un error mientras se generaba el pdf para el reporte general de asunto");
            log.error("Ha ocurrido un error en la transaccion", e);
        }
        return null;
    }

    /**
     * El preporte general de asuntos a detalle por area
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward reporteGeneralDetalle(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        String destino = "DETALLE";
        ReporteForm reporteForm = (ReporteForm) form;
        //llena el combo de los estatus
        if (reporteForm.getNuevaConsultaDetalle().equals("menu"))
        {
            reporteForm.getReporteAsuntos().getDetalleAsunto().clear();
            Empleado usuario = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);
            reporteForm.setTemas(this.temaService.listTemas(usuario.getArea().getId_area()));
            reporteForm.setExpedientes(this.expedienteService.listExpedientes(usuario.getArea().getId_area()));
            reporteForm.setCriterioReporte(new CriterioReporte());
            reporteForm.getCriterioReporte().setId_estatus(Constantes.INSTANCIA_CREADA);
            reporteForm.getCriterioReporte().setPaginador(new Paginador());
            List estatus = new ArrayList();
            estatus.add(new LabelValueBean("En Trámite", "0"));
            estatus.add(new LabelValueBean("Pendiente", "1"));
            estatus.add(new LabelValueBean("Atendido", "2"));
            estatus.add(new LabelValueBean("Concluido", "3"));
            estatus.add(new LabelValueBean("Todos", "-1"));
            request.getSession().removeAttribute(Constantes.MSG_FOUND);
            reporteForm.setEstatusList(estatus);
        }
        else
        {
            try
            {

                Integer id_estatus = reporteForm.getCriterioReporte().getId_estatus();
                switch (id_estatus)
                {
                    case 0: // Ahora es "En Tramite"
                        reporteForm.getCriterioReporte().setEstatus("En Trámite");
                        break;
                    case 1: // Pendiente lo que antes era Turnado
                        reporteForm.getCriterioReporte().setEstatus("Pendiente");
                        break;
                    case 2:
                        reporteForm.getCriterioReporte().setEstatus("Atendido");
                        break;
                    case 3:
                        reporteForm.getCriterioReporte().setEstatus("Concluido");
                        break;
                    case -1:
                        reporteForm.getCriterioReporte().setEstatus("Todos");
                        break;
                    default:
                        reporteForm.getCriterioReporte().setEstatus("");
                        break;
                }


                List estatus = new ArrayList();
                estatus.add(new LabelValueBean("En Trámite", "0"));
                estatus.add(new LabelValueBean("Pendiente", "1"));
                estatus.add(new LabelValueBean("Atendido", "2"));
                estatus.add(new LabelValueBean("Concluido", "3"));
                estatus.add(new LabelValueBean("Todos", "-1"));

                reporteForm.setEstatusList(estatus);

                Tema tema = this.temaService.getTema(reporteForm.getCriterioReporte().getId_tema()) == null ? new Tema() : this.temaService.getTema(reporteForm.getCriterioReporte().getId_tema());
                Expediente expediente = this.expedienteService.getExpediente(reporteForm.getCriterioReporte().getId_expediente()) == null ? new Expediente() : this.expedienteService.getExpediente(reporteForm.getCriterioReporte().getId_expediente());
                reporteForm.setTema(tema);
                reporteForm.setExpediente(expediente);
                reporteForm.getCriterioReporte().setTema(reporteForm.getTema().getTema());
                reporteForm.getCriterioReporte().setExpediente(reporteForm.getExpediente().getExpediente());
                if (reporteForm.getNuevaConsultaDetalle().equals("si"))
                {
                    reporteForm.getCriterioReporte().setPaginador(new Paginador());
                }

                Reporte reporte = this.reporteService.getReporteGeneralDetalle(reporteForm.getCriterioReporte());
                List<Reporte> lista = reporte.getDetalleAsunto();

                reporteForm.getReporteAsuntos().setDetalleAsunto(lista);
                if (lista.size() < Constantes.ACTIVADO)
                    request.getSession().setAttribute(Constantes.MSG_FOUND, Constantes.MSG_FOUND);
                else
                    request.getSession().removeAttribute(Constantes.MSG_FOUND);

            } catch (Exception e)
            {
                destino = "ERROR";
                log.error("Ha ocurrido un error en la transaccion", e);
                request.setAttribute("mensaje", "Error interno. Por favor, intételo nuevamente en unos minutos.");
            }
        }
        return mapping.findForward(destino);
    }

    /**
     * El reporte en formato pdf
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward reporteGeneralDetallePdf(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ReporteForm reporteForm = (ReporteForm) form;
        JasperDesign jasperDesign;

        try
        {

            Integer id_estatus = reporteForm.getCriterioReporte().getId_estatus();
            switch (id_estatus)
            {
                case 0:
                    reporteForm.getCriterioReporte().setEstatus("En Trámite");
                    break;
                case 1:
                    reporteForm.getCriterioReporte().setEstatus("Pendiente");
                    break;
                case 2:
                    reporteForm.getCriterioReporte().setEstatus("Atendido");
                    break;
                case 3:
                    reporteForm.getCriterioReporte().setEstatus("Concluido");
                    break;
                case 4:
                    reporteForm.getCriterioReporte().setEstatus("En Tiempo");
                    break;
                case 5:
                    reporteForm.getCriterioReporte().setEstatus("Por Vencer");
                    break;
                case 6:
                    reporteForm.getCriterioReporte().setEstatus("Vencido");
                    break;
                case 7:
                    reporteForm.getCriterioReporte().setEstatus("En Captura");
                    break;
                case 8:
                    reporteForm.getCriterioReporte().setEstatus("En Revisión");
                    break;
                case 9:
                    reporteForm.getCriterioReporte().setEstatus("Eliminado");
                    break;
                case 10:
                    reporteForm.getCriterioReporte().setEstatus("Archivado");
                    break;
                case -1:
                    reporteForm.getCriterioReporte().setEstatus("Todos");
                    break;
                default:
                    reporteForm.getCriterioReporte().setEstatus("");
                    break;
            }


            List estatus = new ArrayList();
            estatus.add(new LabelValueBean("En Trámite", "0"));
            estatus.add(new LabelValueBean("Pendiente", "1"));
            estatus.add(new LabelValueBean("Atendido", "2"));
            estatus.add(new LabelValueBean("Concluido", "3"));
            estatus.add(new LabelValueBean("En Tiempo", "4"));
            estatus.add(new LabelValueBean("Por Vencer", "5"));
            estatus.add(new LabelValueBean("Vencido", "6"));
            estatus.add(new LabelValueBean("En Captura", "7"));
            estatus.add(new LabelValueBean("En Revisión", "8"));
            estatus.add(new LabelValueBean("Eliminado", "9"));
            estatus.add(new LabelValueBean("Archivado", "10"));
            estatus.add(new LabelValueBean("Todos", "-1"));

            reporteForm.setEstatusList(estatus);

            Tema tema = this.temaService.getTema(reporteForm.getCriterioReporte().getId_tema()) == null ? new Tema() : this.temaService.getTema(reporteForm.getCriterioReporte().getId_tema());
            Expediente expediente = this.expedienteService.getExpediente(reporteForm.getCriterioReporte().getId_expediente()) == null ? new Expediente() : this.expedienteService.getExpediente(reporteForm.getCriterioReporte().getId_expediente());
            reporteForm.setTema(tema);
            reporteForm.setExpediente(expediente);
            reporteForm.getCriterioReporte().setTema(reporteForm.getTema().getTema());
            reporteForm.getCriterioReporte().setExpediente(reporteForm.getExpediente().getExpediente());


            //InputStream xmlResource = this.getClass().getClassLoader().getResourceAsStream("reporteGeneralAsuntosPdf.jrxml");
            HashMap<String, Object> parametersMap = new HashMap<String, Object>();//los parametros

            parametersMap.put("tema", reporteForm.getTema().getTema());
            parametersMap.put("expediente", reporteForm.getExpediente().getExpediente());
            parametersMap.put("area", reporteForm.getCriterioReporte().getArea());
            parametersMap.put("fecha1", reporteForm.getCriterioReporte().getFh_registroIniDDMMYYYY());
            parametersMap.put("fecha2", reporteForm.getCriterioReporte().getFh_registroFinDDMMYYYY());
            parametersMap.put("estatus", reporteForm.getCriterioReporte().getEstatus());

            /*
            List<Reporte> dataSource = reporteForm.getReporteAsuntos().getDetalleAsunto();// la lista del subreporte
            */
            Reporte reporte = this.reporteService.getAllResultReporteDetalle(reporteForm.getCriterioReporte());
            List<Reporte> dataSource = reporte.getDetalleAsunto();

            // Nuevo Reporte de Detalle
            // By Rodolfo Milano Oliveros 
            // Recuperar el folio del dataSource del reporte
            // Buscar el asunto a partir del folio
            // Añadir el asunto a la lista de asuntos
            // Hacer el resto del llamado al reporte de detalle de asuntos
            
            String folio,contador_folio = "";
            Integer largoFolio;
            List<Asunto> asuntos = new ArrayList<Asunto>();
            List<String> idsAsuntos;
            CriterioAsunto criterioAsunto = new CriterioAsunto();
            for(int i=0; i < dataSource.size(); i++){

                // Obtener la subcadena para tener el folio y el contador_folio
                folio = dataSource.get(i).getFolio().trim();
                largoFolio = folio.length();
                contador_folio = folio.substring(largoFolio-2);
                folio = folio.substring(0, largoFolio-3);

                criterioAsunto.setFolio(folio);
                criterioAsunto.setContador_folio(contador_folio);

                idsAsuntos = getAsuntoService().getIdsAsuntoByCriterio_ultimo(criterioAsunto);
                for(int j=0; j < idsAsuntos.size(); j++){
                    asuntos.add(getAsuntoService().getAsunto(Integer.parseInt(idsAsuntos.get(j))));
                }
            }
            
            // ******************************************************************************
            // ******************************************************************************
            /*Dias vencido*/
            final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día
            java.util.Date hoy = new Date(); //Fecha de hoy
            long diferencia;/*( hoy.getTime() - fecha.getTime() )/MILLSECS_PER_DAY; */
            /************************************************/

            Integer ultimoEstatusArea;
            String ultimoEstatusArea_desc;

            HashMap<String, Integer> paramConsulta = new HashMap<String, Integer>();//los parametros

            for (int i = 0; i < asuntos.size(); i++){ //Completar los datos necesarios para el reporte

                asuntos.set(i, asuntoService.getAsunto(asuntos.get(i).getId_asunto()));
                asuntos.get(i).setIdx(i+1);
                asuntos.get(i).setUltimaDetalle(asuntos.get(i).getAsuntos_detalles().get(asuntos.get(i).getAsuntos_detalles().size()-1));
                asuntos.get(i).getUltimaDetalle().setPrioridad(prioridadService.getPrioridad(asuntos.get(i).getUltimaDetalle().getPrioridad().getId_prioridad()));
                // asuntos.get(i).getUltimaDetalle().getEmpleado_dest()
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
                paramConsulta.put("id_area", reporteForm.getCriterioReporte().getId_area());
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
            Empleado empleado_ses = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);
            String unidad = areaService.getAreaById(empleado_ses.getId_area()).getArea();
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String hora = formato.format(new Date());
            /*************************************************************************/
            String RUTA_LOGO = ResourceBundle.getBundle(Constantes.PROPERTIES).getString("ruta.reporte.logo");
            String RUTA_JRXLM = ResourceBundle.getBundle(Constantes.PROPERTIES).getString("ruta.reporte.resumenAsuntos.jrxml");
            // String NOMBRE_ARCHIVO = ResourceBundle.getBundle(Constantes.PROPERTIES).getString("ruta.reporte.resumenAsuntos.nombrearchivo");

            //InputStream logo = this.getServlet().getServletContext().getResourceAsStream(RUTA_LOGO);//el logo
            InputStream xmlResource = this.getServlet().getServletContext().getResourceAsStream(RUTA_JRXLM);//el archivo de diseno
            String logo = this.getServlet().getServletContext().getRealPath(RUTA_LOGO);//el logo
            parametersMap.put("LOGO", logo);//la imagen como parametro
            parametersMap.put("ANIO", anio);
            parametersMap.put("UNIDAD", unidad);
            parametersMap.put("HORA", hora);

            String titulo = "REPORTE A DETALLE ("+reporteForm.getCriterioReporte().getEstatus()+")";
            parametersMap.put("TITULO", titulo.toUpperCase());


            StringTokenizer stTexto = new StringTokenizer(reporteForm.getCriterioReporte().getEstatus());
            String complementoTitulo = "";
            while (stTexto.hasMoreElements())
              complementoTitulo += stTexto.nextElement();

            String NOMBRE_ARCHIVO = "ReporteDetalle"+complementoTitulo+".pdf";

            JRBeanCollectionDataSource dataSourceCollection = new JRBeanCollectionDataSource(asuntos);//se crea la coleccion
            jasperDesign = JRXmlLoader.load(xmlResource);//se crea el rescurso
            JasperReport reportPath = JasperCompileManager.compileReport(jasperDesign);// compilacion del xml de diseno
            JasperPrint print = JasperFillManager.fillReport(reportPath, parametersMap, dataSourceCollection);//la impresion del reporte
            byte[] reportePdf = JasperExportManager.exportReportToPdf(print);//se crea el flujo de bytes
            UtilsReportes.descargaPdf(reportePdf, response, "application/pdf", NOMBRE_ARCHIVO);//el header y la impresion del reporte
            // ******************************************************************************
            // ******************************************************************************
            // ******************************************************************************


            // ******************************************************************************
            // El Reporte anterior
            // ******************************************************************************
            // El total de documentos
            /*
            parametersMap.put(Constantes.TOTAL, reporteForm.getCriterioReporte().getPaginador().getNumRegistros());

            parametersMap.put("LOGO", logo);//la imagen como parametro
            JRBeanCollectionDataSource dataSourceCollection = new JRBeanCollectionDataSource(dataSource);//se crea la coleccion

            jasperDesign = JRXmlLoader.load(xmlResource);//se crea el rescurso
            JasperReport reportPath = JasperCompileManager.compileReport(jasperDesign);// compilacion del xml de diseno
            JasperPrint print = JasperFillManager.fillReport(reportPath, parametersMap, dataSourceCollection);//la impresion del reporte
            byte[] reportePdf = JasperExportManager.exportReportToPdf(print);//se crea el flujo de bytes
            UtilsReportes.descargaPdf(reportePdf, response, "application/pdf", "ReporteGeneralDetalle.pdf");//el header y la impresion del reporte
            */
            // ******************************************************************************
            // ******************************************************************************
        } catch (Exception e)
        {
            log.debug("Ha ocurrido un error mientras se generaba el pdf para el reporte general de asunto");
            log.error("Ha ocurrido un error en la transaccion", e);
        }
        return null;
    }

    public ActionForward concentrado(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        if (request.getSession().getAttribute("arbol") == null)
        {
            // request.setAttribute("arbol", this.arbolService.getArbolAreas());
        }
        String destino = "concentrado";
        return mapping.findForward(destino);
    }

    



    /**
     * @param reporteService the reporteService to set
     */
    public void setReporteService(ReporteService reporteService)
    {
        this.reporteService = reporteService;
    }

    /**
     * @param areaService the areaService to set
     */
    public void setAreaService(AreaService areaService)
    {
        this.areaService = areaService;
    }

    /**
     * @param temaService the temaService to set
     */
    public void setTemaService(TemaService temaService)
    {
        this.temaService = temaService;
    }

    /**
     * @param expedienteService the expedienteService to set
     */
    public void setExpedienteService(ExpedienteService expedienteService)
    {
        this.expedienteService = expedienteService;
    }

    /**
     * @return the asuntoService
     */
    public AsuntoService getAsuntoService() {
        return asuntoService;
    }

    /**
     * @param asuntoService the asuntoService to set
     */
    public void setAsuntoService(AsuntoService asuntoService) {
        this.asuntoService = asuntoService;
    }

    /**
     * @return the prioridadService
     */
    public PrioridadService getPrioridadService() {
        return prioridadService;
    }

    /**
     * @param prioridadService the prioridadService to set
     */
    public void setPrioridadService(PrioridadService prioridadService) {
        this.prioridadService = prioridadService;
    }

}
