/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.economia.scg.service;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import mx.gob.economia.scg.model.Asunto;
import mx.gob.economia.scg.model.AsuntoDetalle;
import mx.gob.economia.scg.model.CriterioReporteVolante;
import mx.gob.economia.scg.model.Empleado;
import mx.gob.economia.scg.model.Instruccion;
import mx.gob.economia.scg.model.TipoSeguimiento;
import mx.gob.economia.scg.model.VolanteCorrespondencia;
import mx.gob.economia.scg.model.Prioridad;
import mx.gob.economia.scg.persistence.AsuntoDao;
import mx.gob.economia.scg.util.Constantes;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.apache.log4j.Logger;

/**
 *
 * @author javier
 */
public class ReporteVolanteServiceImpl implements ReporteVolanteService
{

    private AsuntoDao asuntoDao;
    private AsuntoService asuntoService;
    private EmpleadoService empleadoService;
    private PrioridadService prioridadService;
    private AreaService areaService;
    private InstruccionService instruccionService;
    private TipoSeguimientoService tipoSeguimientoService;
    protected static Logger log = Logger.getLogger("Log");

    public List<Asunto> listAsuntos(CriterioReporteVolante criterio)
    {
        return asuntoDao.listAsuntoReporteVolante(criterio);
    }

    public void generarVolantesCorrespondenciaPDF(HttpServletRequest request, Asunto asunto)
    {
        try
        {
            JasperDesign jasperDesign;
            asunto.getAsunto_detalle().setFh_registro(new Date());
            String RUTA_LOGO = ResourceBundle.getBundle(Constantes.PROPERTIES).getString("ruta.reporte.logo");
            String RUTA_JRXLM = ResourceBundle.getBundle(Constantes.PROPERTIES).getString("ruta.reporte.volante.jrxml");
            String RUTA_CONFIDENCIAL = ResourceBundle.getBundle(Constantes.PROPERTIES).getString("ruta.imagen.confidencial");
            String RUTA_SUBREPORTE_JRXLM = ResourceBundle.getBundle(Constantes.PROPERTIES).getString("ruta.subreporte.volante.jrxml");
            String NOMBRE_ARCHIVO = ResourceBundle.getBundle(Constantes.PROPERTIES).getString("ruta.reporte.volante.nombrearchivo");
            Empleado usuarioSession = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);

            /*
             *
             * Rodolfo Milano Oliveros
             * Problemas con Volante
             * 
            List<Empleado> empleadosX = asunto.getAsunto_detalle().getEmpleadosDest();
            AsuntoDetalle asuntoDetalleX = asunto.getAsunto_detalle();
            Empleado empleadoDestinatarioX = asunto.getAsunto_detalle().getEmpleado_dest();
            Integer id_asunto = asunto.getId_asunto();
            Asunto asuntoX = asuntoService.getAsunto(id_asunto);
            Asunto asuntoY =  asuntoDao.getAsuntoDetalleById(id_asunto);
            */

            // Se agrega el empleado destinatario en caso que no se encuentre en la lista de destinatarios
            asunto.getAsunto_detalle().setEmpleadosDest(
                    this.asuntoService.addEmpleadoDestinanrioToList(
                    asunto.getAsunto_detalle().getEmpleadosDest(), asunto.getAsunto_detalle().getEmpleado_dest()));
            // se hace los mismos para los empleados ccp
            if(asunto.getAsunto_detalle().getEmpleadosCcp().size() < Constantes.ACTIVO)
                asunto.getAsunto_detalle().setEmpleadosCcp(asunto.getEmpleadosCcp());


            //El reporte principal
            HashMap<String, Object> parametersMap = new HashMap<String, Object>();//los parametros
            InputStream xmlResource = request.getSession().getServletContext().getResourceAsStream(RUTA_JRXLM);//el archivo de diseno
            InputStream logo = request.getSession().getServletContext().getResourceAsStream(RUTA_LOGO);//el logo
            String logo_img = request.getSession().getServletContext().getRealPath(RUTA_LOGO);
            String confidencial_img = request.getSession().getServletContext().getRealPath(RUTA_CONFIDENCIAL);//marca de agua CONFIDENCIAL
            List<VolanteCorrespondencia> dataSource = new ArrayList<VolanteCorrespondencia>();// El subreporte

            // El subreporte
            InputStream supreportXmlResource = request.getSession().getServletContext().getResourceAsStream(RUTA_SUBREPORTE_JRXLM);//el archivo de diseno
            jasperDesign = JRXmlLoader.load(supreportXmlResource);//se crea el rescurso
            JasperReport subreport = JasperCompileManager.compileReport(jasperDesign);// compilacion del xml de diseno

            List<Empleado> collectionSubreport = asunto.getAsunto_detalle().getEmpleadosCcp();
            collectionSubreport = collectionSubreport == null ? new ArrayList<Empleado>() : collectionSubreport;

            //Poner datos adicionales a los con copia para
            collectionSubreport = this.asuntoService.populateBeanEmpleadoCcp(collectionSubreport);
            String ccpList = this.asuntoService.getEmpleadosCcp(collectionSubreport);

            // El data source del subreporte
            JRBeanCollectionDataSource subReportDataSourceCollection = new JRBeanCollectionDataSource(collectionSubreport);//se crea la coleccion
            parametersMap.put("SUB_REPORT_DS", subReportDataSourceCollection);
            parametersMap.put("SUBREPORT_JASPER", subreport);
            // Los datos del volante


            AsuntoDetalle asuntoDetalleProcedencia = asunto.getAsuntos_detalles().size() > 0 ?
                //asunto.getAsuntos_detalles().get(asunto.getAsuntos_detalles().size() - Constantes.ACTIVADO) : new AsuntoDetalle();
                asunto.getAsuntos_detalles().get(asunto.getAsuntos_detalles().size() - Constantes.ACTIVADO) : asunto.getAsunto_detalle();

            //La fecha limite de atencion
            this.asuntoService.asignarFhLimite(asunto, request);
            // la fecha de registro
            asunto.getAsunto_detalle().setFh_registro(new Date());
            Integer consecutivo = Constantes.ACTIVADO;
            dataSource.clear();
            Date datePrioridad = new Date();
            Calendar calendario = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            for (Empleado empleado : asunto.getAsunto_detalle().getEmpleadosDest())
            {
                VolanteCorrespondencia volanteCorrespondencia = new VolanteCorrespondencia();
                // Instruccion instruccion = empleado.getInstruccion();
                Instruccion instruccion = asunto.getAsunto_detalle().getInstruccion();
                
                String comentario = (empleado.getComentario() == null || empleado.getComentario().equals("")) ? asunto.getDescripcion() : empleado.getComentario();
                //comentario = comentario.equals("") && !asunto.getDescripcion().equals("")? asunto.getDescripcion(): comentario;
                if (empleado.getPrioridad().getId_prioridad().equals(-1)){
                    empleado.getPrioridad().setId_prioridad(asunto.getAsunto_detalle().getPrioridad().getId_prioridad());
                }
                Prioridad prioridad = this.prioridadService.getPrioridad(empleado.getPrioridad().getId_prioridad());
                if (usuarioSession.getArea().getId_area().equals(1375)){                    
                    switch(prioridad.getId_prioridad()){
                        case 1: 
                            prioridad.setPrioridad("");
                        break;
                        case 2: 
                            prioridad.setPrioridad("ORDINARIO");
                        break;
                        case 3: 
                            prioridad.setPrioridad("URGENTE");
                        break;
                        case 4: 
                            prioridad.setPrioridad("EXTRAURGENTE");
                        break;
                        case 5: 
                            prioridad.setPrioridad("OTRO");
                        break;
                    }
                    
                }
                volanteCorrespondencia.setPrioridad(prioridad);

                volanteCorrespondencia.setFechaLimiteAtencion(empleado.getFh_limiteDDMMYYYY());// verificar la fecha
                if (volanteCorrespondencia.getFechaLimiteAtencion().equals("")){
                    if (!empleado.getPrioridad().getId_prioridad().equals(1)){
                        datePrioridad = new Date();
                        calendario = Calendar.getInstance();
                        sdf = new SimpleDateFormat("dd/MM/yyyy");
                        calendario.setTime(datePrioridad);
                        calendario.add(Calendar.DATE,volanteCorrespondencia.getPrioridad().getDias());
                        volanteCorrespondencia.setFechaLimiteAtencion(sdf.format(calendario.getTime()));
                    }
                }
                String fLimite = asunto.getAsunto_detalle().getFh_limiteDDMMYYYY(); 
                if (fLimite != null && !fLimite.equals("")) {
                    volanteCorrespondencia.setFechaLimiteAtencion(asunto.getAsunto_detalle().getFh_limiteDDMMYYYY());// verificar la fecha
                }
                //los datos adicionales del empleado
                empleado = this.empleadoService.getEmpleadoById(empleado.getId_empleado());
                empleado.setArea(this.areaService.getAreaById(empleado.getId_area()));
                volanteCorrespondencia.setAreaRemitente(usuarioSession.getArea());
                volanteCorrespondencia.setDestinatario(empleado);
                volanteCorrespondencia.setFechaVolante(asunto.getAsunto_detalle().getFh_registroDDMMYYYY());
                /*volanteCorrespondencia.setFolio(asunto.getFolio());
                volanteCorrespondencia.setConsecutivo(consecutivo);*/
                if (asunto.getContador_folio()==null){
                    volanteCorrespondencia.setFolio(asunto.getFolio());
                }
                else{
                    volanteCorrespondencia.setFolio(asunto.getContador_folio());
                }
                //volanteCorrespondencia.setPrioridad(this.prioridadService.getPrioridad(asunto.getAsunto_detalle().getPrioridad().getId_prioridad()));
                //volanteCorrespondencia.setFechaLimiteAtencion(asunto.getAsunto_detalle().getFh_limiteDDMMYYYY());// verificar la fecha
                volanteCorrespondencia.setReferencia(asunto.getId_asunto_ref());
                if (usuarioSession.getArea().getId_area()==1375){//Si es agenda del CS
                    volanteCorrespondencia.setAreaProcedencia(this.areaService.getAreaByIdWitoutFilter(asunto.getAsuntos_detalles().get(0).getEmpleado_remi().getId_area()));
                    volanteCorrespondencia.setFirmanteProcedencia(asunto.getAsuntos_detalles().get(0).getEmpleado_remi());
                }
                else
                {
                    volanteCorrespondencia.setAreaProcedencia(asuntoDetalleProcedencia.getEmpleado_remi().getArea());
                    volanteCorrespondencia.setFirmanteProcedencia(asuntoDetalleProcedencia.getEmpleado_remi());
                }
                volanteCorrespondencia.setDocumento(asunto.getNo_oficio());
                volanteCorrespondencia.setDescripcion("");
                volanteCorrespondencia.setComentario(comentario);

                Empleado firmante = asunto.getAsunto_detalle().getEmpleadoFirmanteVolante().getId_empleado() == null ?
                    this.empleadoService.getEmpleadoById(asunto.getAsunto_detalle().getEmpleado_remi().getId_empleado()) :
                    this.empleadoService.getEmpleadoById(asunto.getAsunto_detalle().getEmpleadoFirmanteVolante().getId_empleado());
                volanteCorrespondencia.setFirmanteRemitente(firmante);

                TipoSeguimiento tipoSeguimiento = asunto.getAsunto_detalle().getTipoSeguimiento().getIdTipoSeguimiento() == null ? new TipoSeguimiento() :
                this.tipoSeguimientoService.getTipoSeguimiento(asunto.getAsunto_detalle().getTipoSeguimiento().getIdTipoSeguimiento());
                volanteCorrespondencia.setTipoSeguimiento(tipoSeguimiento);
                volanteCorrespondencia.setInstruccion(this.instruccionService.getInstruccion(instruccion.getId_instruccion()));
                volanteCorrespondencia.setLogo_img(logo_img);
                volanteCorrespondencia.setLogo(logo);
                volanteCorrespondencia.setCcps(ccpList);
                volanteCorrespondencia.setConfidencial(asunto.getConfidencial());
                volanteCorrespondencia.setConfidencial_img(confidencial_img);
                volanteCorrespondencia.setInstruccionAdicional(asunto.getAsunto_detalle().getInstruccionAdicional());
                dataSource.add(volanteCorrespondencia);
                consecutivo++;
            }
            parametersMap.put("LOGO", logo);//la imagen como parametro
            JRBeanCollectionDataSource dataSourceCollection = new JRBeanCollectionDataSource(dataSource);//se crea la coleccion

            jasperDesign = JRXmlLoader.load(xmlResource);//se crea el rescurso
            JasperReport reportPath = JasperCompileManager.compileReport(jasperDesign);// compilacion del xml de diseno
            JasperPrint print = JasperFillManager.fillReport(reportPath, parametersMap, dataSourceCollection);//la impresion del reporte
            byte[] reportePdf = JasperExportManager.exportReportToPdf(print);//se crea el flujo de bytes
            request.getSession().setAttribute(Constantes.VOLANTE_CORRESPONDENCIA, reportePdf);
            //UtilsReportes.descargaPdf(reportePdf, response, "application/pdf", NOMBRE_ARCHIVO);//el header y la impresion del reporte
        } catch (Exception e)
        {
            log.error("Se ha producido un error en la creacion del volante ->", e);
        }
    }

    public void generarVolanteCorrespondenciaUltimoDetallePDF(HttpServletRequest request, Asunto asunto)
    {
        try
        {
            JasperDesign jasperDesign;
            
            asunto.setAsunto_detalle(asunto.getAsunto_detalle().getEmpleadosDest().isEmpty() ? asunto.getUltimaDetalle() : asunto.getAsunto_detalle());
            
            asunto.getAsunto_detalle().setFh_registro(new Date());
            String RUTA_LOGO = ResourceBundle.getBundle(Constantes.PROPERTIES).getString("ruta.reporte.logo");
            String RUTA_JRXLM = ResourceBundle.getBundle(Constantes.PROPERTIES).getString("ruta.reporte.volante.jrxml");
            String RUTA_CONFIDENCIAL = ResourceBundle.getBundle(Constantes.PROPERTIES).getString("ruta.imagen.confidencial");
            String RUTA_SUBREPORTE_JRXLM = ResourceBundle.getBundle(Constantes.PROPERTIES).getString("ruta.subreporte.volante.jrxml");
            String NOMBRE_ARCHIVO = ResourceBundle.getBundle(Constantes.PROPERTIES).getString("ruta.reporte.volante.nombrearchivo");
            Empleado usuarioSession = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);

            // Se agrega el empleado destinatario en caso que no se encuentre en la lista de destinatarios
            asunto.getAsunto_detalle().setEmpleadosDest(
                    this.asuntoService.addEmpleadoDestinanrioToList(
                    asunto.getAsunto_detalle().getEmpleadosDest(), asunto.getAsunto_detalle().getEmpleado_dest()));
            // se hace los mismos para los empleados ccp
            if(asunto.getAsunto_detalle().getEmpleadosCcp().size() < Constantes.ACTIVO)
                asunto.getAsunto_detalle().setEmpleadosCcp(asunto.getEmpleadosCcp());


            //El reporte principal
            HashMap<String, Object> parametersMap = new HashMap<String, Object>();//los parametros
            InputStream xmlResource = request.getSession().getServletContext().getResourceAsStream(RUTA_JRXLM);//el archivo de diseno
            InputStream logo = request.getSession().getServletContext().getResourceAsStream(RUTA_LOGO);//el logo
            String logo_img = request.getSession().getServletContext().getRealPath(RUTA_LOGO);
            String confidencial_img = request.getSession().getServletContext().getRealPath(RUTA_CONFIDENCIAL);//marca de agua CONFIDENCIAL
            List<VolanteCorrespondencia> dataSource = new ArrayList<VolanteCorrespondencia>();// El subreporte

            // El subreporte
            InputStream supreportXmlResource = request.getSession().getServletContext().getResourceAsStream(RUTA_SUBREPORTE_JRXLM);//el archivo de diseno
            jasperDesign = JRXmlLoader.load(supreportXmlResource);//se crea el rescurso
            JasperReport subreport = JasperCompileManager.compileReport(jasperDesign);// compilacion del xml de diseno

            List<Empleado> collectionSubreport = asunto.getAsunto_detalle().getEmpleadosCcp();
            collectionSubreport = collectionSubreport == null ? new ArrayList<Empleado>() : collectionSubreport;

            //Poner datos adicionales a los con copia para
            collectionSubreport = this.asuntoService.populateBeanEmpleadoCcp(collectionSubreport);
            String ccpList = this.asuntoService.getEmpleadosCcp(collectionSubreport);

            // El data source del subreporte
            JRBeanCollectionDataSource subReportDataSourceCollection = new JRBeanCollectionDataSource(collectionSubreport);//se crea la coleccion
            parametersMap.put("SUB_REPORT_DS", subReportDataSourceCollection);
            parametersMap.put("SUBREPORT_JASPER", subreport);
            // Los datos del volante


            AsuntoDetalle asuntoDetalleProcedencia = asunto.getAsuntos_detalles().size() > 0 ?
                //asunto.getAsuntos_detalles().get(asunto.getAsuntos_detalles().size() - Constantes.ACTIVADO) : new AsuntoDetalle();
                asunto.getAsuntos_detalles().get(asunto.getAsuntos_detalles().size() - Constantes.ACTIVADO) : asunto.getAsunto_detalle();

            //AsuntoDetalle asuntoDetalleProcedencia = asunto.getAsunto_detalle();
            //Empleado empleadoRemitenteOriginal = asunto.getAsuntos_detalles().get(asunto.getAsuntos_detalles().size() - Constantes.ACTIVADO).getEmpleado_dest();

            // Asigna el empleado remitente
            /*if (asunto.getUltimaDetalle().getEstatus().equals(Constantes.TURNADO) && //La opcion de returnar toma el mismo remitente
                asunto.getUltimaDetalle().getEmpleado_dest().getId_empleado().equals(empleadoRemitenteOriginal.getId_empleado())){
                asunto.getAsunto_detalle().setEmpleado_remi(asunto.getUltimaDetalle().getEmpleado_remi());                
            }
            else{
            asunto.getAsunto_detalle().setEmpleado_remi(empleadoRemitenteOriginal);
            }*/


            //La fecha limite de atencion
            this.asuntoService.asignarFhLimite(asunto, request);
            // la fecha de registro
            asunto.getAsunto_detalle().setFh_registro(new Date());
            Integer consecutivo = Constantes.ACTIVADO;
            Date datePrioridad = new Date();
            Calendar calendario = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            dataSource.clear();
            for (Empleado empleado : asunto.getAsunto_detalle().getEmpleadosDest())
            {
                VolanteCorrespondencia volanteCorrespondencia = new VolanteCorrespondencia();
                Instruccion instruccion = empleado.getInstruccion();
                String comentario = (empleado.getComentario() == null || empleado.getComentario().equals("")) ? asunto.getDescripcion() : empleado.getComentario();
                //comentario = comentario.equals("") && !asunto.getDescripcion().equals("")? asunto.getDescripcion(): comentario;
                if (empleado.getPrioridad().getId_prioridad().equals(-1)){
                    empleado.getPrioridad().setId_prioridad(asunto.getAsunto_detalle().getPrioridad().getId_prioridad());
                }
                volanteCorrespondencia.setPrioridad(this.prioridadService.getPrioridad(empleado.getPrioridad().getId_prioridad()));
                volanteCorrespondencia.setFechaLimiteAtencion(empleado.getFh_limiteDDMMYYYY());// verificar la fecha
                if (volanteCorrespondencia.getFechaLimiteAtencion().equals("") ){
                    if (!empleado.getPrioridad().getId_prioridad().equals(1)){
                        datePrioridad = new Date();
                        calendario = Calendar.getInstance();
                        sdf = new SimpleDateFormat("dd/MM/yyyy");
                        calendario.setTime(datePrioridad);
                        calendario.add(Calendar.DATE,volanteCorrespondencia.getPrioridad().getDias());
                        volanteCorrespondencia.setFechaLimiteAtencion(sdf.format(calendario.getTime()));
                    }
                }
                //los datos adicionales del empleado
                empleado = this.empleadoService.getEmpleadoById(empleado.getId_empleado());
                empleado.setArea(this.areaService.getAreaById(empleado.getId_area()));
                volanteCorrespondencia.setAreaRemitente(usuarioSession.getArea());                
                volanteCorrespondencia.setDestinatario(empleado);
                volanteCorrespondencia.setFechaVolante(asunto.getAsunto_detalle().getFh_registroDDMMYYYY());
                /*volanteCorrespondencia.setFolio(asunto.getFolio());
                volanteCorrespondencia.setConsecutivo(0);*/
                if (asunto.getContador_folio()==null){
                    volanteCorrespondencia.setFolio(asunto.getFolio());
                }
                else{
                    volanteCorrespondencia.setFolio(asunto.getContador_folio());
                }
                //volanteCorrespondencia.setPrioridad(this.prioridadService.getPrioridad(asunto.getAsunto_detalle().getPrioridad().getId_prioridad()));
                //volanteCorrespondencia.setFechaLimiteAtencion(asunto.getAsunto_detalle().getFh_limiteDDMMYYYY());// verificar la fecha                
                volanteCorrespondencia.setReferencia(asunto.getId_asunto_ref());
                if (usuarioSession.getArea().getId_area()==1375){//Si es agenda del CS
                    volanteCorrespondencia.setAreaProcedencia(this.areaService.getAreaByIdWitoutFilter(asunto.getAsuntos_detalles().get(0).getEmpleado_remi().getId_area()));
                    volanteCorrespondencia.setFirmanteProcedencia(asunto.getAsuntos_detalles().get(0).getEmpleado_remi());
                }
                else
                {
                    volanteCorrespondencia.setAreaProcedencia(asuntoDetalleProcedencia.getEmpleado_remi().getArea());
                    volanteCorrespondencia.setFirmanteProcedencia(asuntoDetalleProcedencia.getEmpleado_remi());
                }      
                volanteCorrespondencia.setDocumento(asunto.getNo_oficio());
                volanteCorrespondencia.setDescripcion(asunto.getDescripcion());
                volanteCorrespondencia.setComentario(comentario);

                Empleado firmante = asunto.getAsunto_detalle().getEmpleadoFirmanteVolante().getId_empleado() == null ?
                    this.empleadoService.getEmpleadoById(asunto.getAsunto_detalle().getEmpleado_remi().getId_empleado()) :
                    this.empleadoService.getEmpleadoById(asunto.getAsunto_detalle().getEmpleadoFirmanteVolante().getId_empleado());
                volanteCorrespondencia.setFirmanteRemitente(firmante);

                TipoSeguimiento tipoSeguimiento = asunto.getAsunto_detalle().getTipoSeguimiento().getIdTipoSeguimiento() == null ? new TipoSeguimiento() :
                this.tipoSeguimientoService.getTipoSeguimiento(asunto.getAsunto_detalle().getTipoSeguimiento().getIdTipoSeguimiento());
                volanteCorrespondencia.setTipoSeguimiento(tipoSeguimiento);
                volanteCorrespondencia.setInstruccion(this.instruccionService.getInstruccion(instruccion.getId_instruccion()));
                volanteCorrespondencia.setLogo_img(logo_img);
                volanteCorrespondencia.setLogo(logo);
                volanteCorrespondencia.setCcps(ccpList);
                volanteCorrespondencia.setConfidencial(asunto.getConfidencial());
                volanteCorrespondencia.setConfidencial_img(confidencial_img);
                volanteCorrespondencia.setInstruccionAdicional(asunto.getAsunto_detalle().getInstruccionAdicional());
                dataSource.add(volanteCorrespondencia);
                consecutivo++;
            }
            parametersMap.put("LOGO", logo);//la imagen como parametro
            JRBeanCollectionDataSource dataSourceCollection = new JRBeanCollectionDataSource(dataSource);//se crea la coleccion

            jasperDesign = JRXmlLoader.load(xmlResource);//se crea el rescurso
            JasperReport reportPath = JasperCompileManager.compileReport(jasperDesign);// compilacion del xml de diseno
            JasperPrint print = JasperFillManager.fillReport(reportPath, parametersMap, dataSourceCollection);//la impresion del reporte
            byte[] reportePdf = JasperExportManager.exportReportToPdf(print);//se crea el flujo de bytes
            request.getSession().setAttribute(Constantes.VOLANTE_CORRESPONDENCIA, reportePdf);
            //UtilsReportes.descargaPdf(reportePdf, response, "application/pdf", NOMBRE_ARCHIVO);//el header y la impresion del reporte
        } catch (Exception e)
        {
            log.error("Se ha producido un error en la creacion del volante ->", e);
        }
    }

    /**
     * @param asuntoDao the asuntoDao to set
     */
    public void setAsuntoDao(AsuntoDao asuntoDao)
    {
        this.asuntoDao = asuntoDao;
    }

    /**
     * @param asuntoService the asuntoService to set
     */
    public void setAsuntoService(AsuntoService asuntoService)
    {
        this.asuntoService = asuntoService;
    }

    /**
     * @param empleadoService the empleadoService to set
     */
    public void setEmpleadoService(EmpleadoService empleadoService)
    {
        this.empleadoService = empleadoService;
    }

    /**
     * @param prioridadService the prioridadService to set
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
     * @param instruccionService the instruccionService to set
     */
    public void setInstruccionService(InstruccionService instruccionService)
    {
        this.instruccionService = instruccionService;
    }

    /**
     * @param tipoSeguimientoService the tipoSeguimientoService to set
     */
    public void setTipoSeguimientoService(TipoSeguimientoService tipoSeguimientoService)
    {
        this.tipoSeguimientoService = tipoSeguimientoService;
    }

    public void generarVolantesCorrespondenciaPDF_Atendido(HttpServletRequest request, Asunto asunto)
    {
        try 
        {
            JasperDesign jasperDesign;
            asunto.getAsunto_detalle().setFh_registro(new Date());
            String RUTA_LOGO = ResourceBundle.getBundle(Constantes.PROPERTIES).getString("ruta.reporte.logo");

            String RUTA_JRXLM = ResourceBundle.getBundle(Constantes.PROPERTIES).getString("ruta.reporte.volante.atendido.jrxml");
            String RUTA_CONFIDENCIAL = ResourceBundle.getBundle(Constantes.PROPERTIES).getString("ruta.imagen.confidencial");
            String RUTA_SUBREPORTE_JRXLM = ResourceBundle.getBundle(Constantes.PROPERTIES).getString("ruta.subreporte.volante.jrxml");
            String NOMBRE_ARCHIVO = ResourceBundle.getBundle(Constantes.PROPERTIES).getString("ruta.reporte.volante.nombrearchivo.atendido");
            Empleado usuarioSession = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);

            // Se agrega el empleado destinatario en caso que no se encuentre en la lista de destinatarios
            asunto.getAsunto_detalle().setEmpleadosDest(
                    this.asuntoService.addEmpleadoDestinanrioToList(
                    asunto.getAsunto_detalle().getEmpleadosDest(), asunto.getAsunto_detalle().getEmpleado_dest()));
            // se hace los mismos para los empleados ccp
            if(asunto.getAsunto_detalle().getEmpleadosCcp().size() < Constantes.ACTIVO)
                asunto.getAsunto_detalle().setEmpleadosCcp(asunto.getEmpleadosCcp());


            //El reporte principal
            HashMap<String, Object> parametersMap = new HashMap<String, Object>();//los parametros
            InputStream xmlResource = request.getSession().getServletContext().getResourceAsStream(RUTA_JRXLM);//el archivo de diseno
            InputStream logo = request.getSession().getServletContext().getResourceAsStream(RUTA_LOGO);//el logo
            String logo_img = request.getSession().getServletContext().getRealPath(RUTA_LOGO);
            String confidencial_img = request.getSession().getServletContext().getRealPath(RUTA_CONFIDENCIAL);//marca de agua CONFIDENCIAL
            List<VolanteCorrespondencia> dataSource = new ArrayList<VolanteCorrespondencia>();// El subreporte

            // El subreporte
            InputStream supreportXmlResource = request.getSession().getServletContext().getResourceAsStream(RUTA_SUBREPORTE_JRXLM);//el archivo de diseno
            jasperDesign = JRXmlLoader.load(supreportXmlResource);//se crea el rescurso
            JasperReport subreport = JasperCompileManager.compileReport(jasperDesign);// compilacion del xml de diseno

            List<Empleado> collectionSubreport = asunto.getAsunto_detalle().getEmpleadosCcp();
            collectionSubreport = collectionSubreport == null ? new ArrayList<Empleado>() : collectionSubreport;

            //Poner datos adicionales a los con copia para
            collectionSubreport = this.asuntoService.populateBeanEmpleadoCcp(collectionSubreport);
            String ccpList = this.asuntoService.getEmpleadosCcp(collectionSubreport);

            // El data source del subreporte
            JRBeanCollectionDataSource subReportDataSourceCollection = new JRBeanCollectionDataSource(collectionSubreport);//se crea la coleccion
            parametersMap.put("SUB_REPORT_DS", subReportDataSourceCollection);
            parametersMap.put("SUBREPORT_JASPER", subreport);
            // Los datos del volante


            AsuntoDetalle asuntoDetalleProcedencia = asunto.getAsuntos_detalles().size() > 0 ?
                //asunto.getAsuntos_detalles().get(asunto.getAsuntos_detalles().size() - Constantes.ACTIVADO) : new AsuntoDetalle();
                asunto.getAsuntos_detalles().get(asunto.getAsuntos_detalles().size() - Constantes.ACTIVADO) : asunto.getAsunto_detalle();

            //La fecha limite de atencion
            this.asuntoService.asignarFhLimite(asunto, request);
            // la fecha de registro
            asunto.getAsunto_detalle().setFh_registro(new Date());
            Integer consecutivo = Constantes.ACTIVADO;
            dataSource.clear();
            Date datePrioridad = new Date();
            Calendar calendario = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            for (Empleado empleado : asunto.getAsunto_detalle().getEmpleadosDest())
            {
                VolanteCorrespondencia volanteCorrespondencia = new VolanteCorrespondencia();
                // Instruccion instruccion = empleado.getInstruccion();
                Instruccion instruccion = asunto.getAsunto_detalle().getInstruccion();

                String comentario = (empleado.getComentario() == null || empleado.getComentario().equals("")) ? asunto.getDescripcion() : empleado.getComentario();
                //comentario = comentario.equals("") && !asunto.getDescripcion().equals("")? asunto.getDescripcion(): comentario;
                if (empleado.getPrioridad().getId_prioridad().equals(-1)){
                    empleado.getPrioridad().setId_prioridad(asunto.getAsunto_detalle().getPrioridad().getId_prioridad());
                }
                volanteCorrespondencia.setPrioridad(this.prioridadService.getPrioridad(empleado.getPrioridad().getId_prioridad()));
                volanteCorrespondencia.setFechaLimiteAtencion(empleado.getFh_limiteDDMMYYYY());// verificar la fecha
                if (volanteCorrespondencia.getFechaLimiteAtencion().equals("")){
                    if (!empleado.getPrioridad().getId_prioridad().equals(1)){
                        datePrioridad = new Date();
                        calendario = Calendar.getInstance();
                        sdf = new SimpleDateFormat("dd/MM/yyyy");
                        calendario.setTime(datePrioridad);
                        calendario.add(Calendar.DATE,volanteCorrespondencia.getPrioridad().getDias());
                        volanteCorrespondencia.setFechaLimiteAtencion(sdf.format(calendario.getTime()));
                    }
                }
                String fLimite = asunto.getAsunto_detalle().getFh_limiteDDMMYYYY();
                if (fLimite != null && !fLimite.equals("")) {
                    volanteCorrespondencia.setFechaLimiteAtencion(asunto.getAsunto_detalle().getFh_limiteDDMMYYYY());// verificar la fecha
                }
                //los datos adicionales del empleado
                empleado = this.empleadoService.getEmpleadoById(empleado.getId_empleado());
                empleado.setArea(this.areaService.getAreaById(empleado.getId_area()));
                volanteCorrespondencia.setAreaRemitente(usuarioSession.getArea());
                volanteCorrespondencia.setDestinatario(empleado);
                volanteCorrespondencia.setFechaVolante(asunto.getFh_oficioDDMMYYYY());
                /*volanteCorrespondencia.setFolio(asunto.getFolio());
                volanteCorrespondencia.setConsecutivo(consecutivo);*/
                if (asunto.getContador_folio()==null){
                    volanteCorrespondencia.setFolio(asunto.getFolio());
                }
                else{
                    volanteCorrespondencia.setFolio(asunto.getContador_folio());
                }
                //volanteCorrespondencia.setPrioridad(this.prioridadService.getPrioridad(asunto.getAsunto_detalle().getPrioridad().getId_prioridad()));
                //volanteCorrespondencia.setFechaLimiteAtencion(asunto.getAsunto_detalle().getFh_limiteDDMMYYYY());// verificar la fecha
                volanteCorrespondencia.setReferencia(asunto.getId_asunto_ref());
                if (usuarioSession.getArea().getId_area()==1375){//Si es agenda del CS
                    volanteCorrespondencia.setAreaProcedencia(this.areaService.getAreaByIdWitoutFilter(asunto.getAsuntos_detalles().get(0).getEmpleado_remi().getId_area()));
                    volanteCorrespondencia.setFirmanteProcedencia(asunto.getAsuntos_detalles().get(0).getEmpleado_remi());
                }
                else
                {
                    volanteCorrespondencia.setAreaProcedencia(asuntoDetalleProcedencia.getEmpleado_remi().getArea());
                    volanteCorrespondencia.setFirmanteProcedencia(asuntoDetalleProcedencia.getEmpleado_remi());
                }
                volanteCorrespondencia.setDocumento(asunto.getNo_oficio());
                volanteCorrespondencia.setDescripcion("");
                volanteCorrespondencia.setComentario(comentario);

                Empleado firmante = asunto.getAsunto_detalle().getEmpleadoFirmanteVolante().getId_empleado() == null ?
                    this.empleadoService.getEmpleadoById(asunto.getAsunto_detalle().getEmpleado_remi().getId_empleado()) :
                    this.empleadoService.getEmpleadoById(asunto.getAsunto_detalle().getEmpleadoFirmanteVolante().getId_empleado());
                volanteCorrespondencia.setFirmanteRemitente(firmante);

                TipoSeguimiento tipoSeguimiento = asunto.getAsunto_detalle().getTipoSeguimiento().getIdTipoSeguimiento() == null ? new TipoSeguimiento() :
                this.tipoSeguimientoService.getTipoSeguimiento(asunto.getAsunto_detalle().getTipoSeguimiento().getIdTipoSeguimiento());
                volanteCorrespondencia.setTipoSeguimiento(tipoSeguimiento);
                volanteCorrespondencia.setInstruccion(this.instruccionService.getInstruccion(instruccion.getId_instruccion()));
                volanteCorrespondencia.setLogo_img(logo_img);
                volanteCorrespondencia.setLogo(logo);
                volanteCorrespondencia.setCcps(ccpList);
                volanteCorrespondencia.setConfidencial(asunto.getConfidencial());
                volanteCorrespondencia.setConfidencial_img(confidencial_img);
                // volanteCorrespondencia.setInstruccionAdicional(asunto.getAsunto_detalle().getInstruccionAdicional());
                // Nuevos valores
                String promoNombre = asunto.getAsuntos_detalles().get(0).getEmpleado_remi().getNombre();
                String promoApePat = asunto.getAsuntos_detalles().get(0).getEmpleado_remi().getPaterno();
                String promoApeMat = asunto.getAsuntos_detalles().get(0).getEmpleado_remi().getMaterno();
                String promotor = promoNombre+" "+promoApePat+" "+promoApeMat;
                volanteCorrespondencia.setPromotor(promotor);
                volanteCorrespondencia.setExpediente(asunto.getExpediente().getExpediente());
                volanteCorrespondencia.setSintesis(asunto.getDescripcion());
                volanteCorrespondencia.setFechaRespuesta(asunto.getAsunto_detalle().getFh_registroDDMMYYYY());
                volanteCorrespondencia.setRespuesta(asunto.getAsunto_detalle().getComentario());

                // By Rodolfo Milano Oliveros 06/11/2013
                // correcciones Ticket 124
                // Se adecuo esta parte para que tomara el caso de varios turnos:
                // utiliza el ultimo registro de getActualizacionesCaptura
                // Se solicitó que el Folio se reemplazara por el No de Oficio, que es el que
                // usan mientras no toda la Secretaria este usando el sistema
                // Se considera luego si queda asi, o se coloca el No de Folio
                Integer contadorActualizaciones = asunto.getActualizacionesCaptura().size()-1;
                volanteCorrespondencia.setInstruccionAdicional(asunto.getActualizacionesCaptura().get(contadorActualizaciones).getComentario());
                volanteCorrespondencia.setFolio(asunto.getNo_oficio());

                Integer idPrior = asunto.getAsuntos_detalles().get(0).getPrioridad().getId_prioridad();
                Prioridad prioridad = prioridadService.getPrioridad(idPrior);
                volanteCorrespondencia.setPrioridad(prioridad);
                dataSource.add(volanteCorrespondencia);
                consecutivo++;
            }
            parametersMap.put("LOGO", logo);//la imagen como parametro
            JRBeanCollectionDataSource dataSourceCollection = new JRBeanCollectionDataSource(dataSource);//se crea la coleccion

            jasperDesign = JRXmlLoader.load(xmlResource);//se crea el rescurso
            JasperReport reportPath = JasperCompileManager.compileReport(jasperDesign);// compilacion del xml de diseno
            JasperPrint print = JasperFillManager.fillReport(reportPath, parametersMap, dataSourceCollection);//la impresion del reporte
            byte[] reportePdf = JasperExportManager.exportReportToPdf(print);//se crea el flujo de bytes
            request.getSession().setAttribute(Constantes.VOLANTE_CORRESPONDENCIA, reportePdf);
            //UtilsReportes.descargaPdf(reportePdf, response, "application/pdf", NOMBRE_ARCHIVO);//el header y la impresion del reporte
        } catch (Exception e)
        {
            log.error("Se ha producido un error en la creacion del volante ->", e);
        }
    }
}
