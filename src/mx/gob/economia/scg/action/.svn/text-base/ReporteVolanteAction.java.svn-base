/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.economia.scg.action;

import java.util.List;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.gob.economia.scg.form.ReporteVolanteForm;
import mx.gob.economia.scg.model.Asunto;
import mx.gob.economia.scg.model.Empleado;
import mx.gob.economia.scg.model.Expediente;
import mx.gob.economia.scg.model.Tema;
import mx.gob.economia.scg.service.AsuntoService;
import mx.gob.economia.scg.service.ExpedienteService;
import mx.gob.economia.scg.service.TemaService;
import mx.gob.economia.scg.util.Constantes;
import mx.gob.economia.scg.util.GeneradorArchivos;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import mx.gob.economia.scg.model.CriterioReporteVolante;
import mx.gob.economia.scg.service.CorreoService;

/**
 *
 * @author javier
 */
public class ReporteVolanteAction extends DispatchAction {

    private TemaService temaService;
    private ExpedienteService expedienteService;
    private AsuntoService asuntoService;
    private CorreoService correoService;

    public ActionForward inicio(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        String forward = "SUCCESS";
        ReporteVolanteForm reporteForm = (ReporteVolanteForm) form;
        Empleado usuario = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);
        reporteForm.setTemas(this.temaService.listTemas(usuario.getArea().getId_area()));
        reporteForm.setExpedientes(this.expedienteService.listExpedientes(usuario.getArea().getId_area()));
        reporteForm.setCriterioReporte(new CriterioReporteVolante());
        return mapping.findForward(forward);
    }

    public ActionForward confirmaGeneracionArchivos(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        String forward = "CONFIRMACION";
        ReporteVolanteForm reporteForm = (ReporteVolanteForm) form;
        Tema tema = this.temaService.getTema(reporteForm.getCriterioReporte().getId_tema()) == null ? new Tema() : this.temaService.getTema(reporteForm.getCriterioReporte().getId_tema());
        Expediente expediente = this.expedienteService.getExpediente(reporteForm.getCriterioReporte().getId_expediente()) == null ? new Expediente() : this.expedienteService.getExpediente(reporteForm.getCriterioReporte().getId_expediente());
        
        reporteForm.setTema(tema);
        reporteForm.setExpediente(expediente);

        return mapping.findForward(forward);
    }

    public ActionForward iniciaGeneracionArchivos(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        String forward = "TERMINO";
        ReporteVolanteForm reporteForm = (ReporteVolanteForm) form;
        List<Asunto> lista = this.asuntoService.listaAsuntosReporteVolante(reporteForm.getCriterioReporte());
        log.debug("LISTA " + lista.size());
        for (Asunto bean : lista) {
            log.debug("BEAN " + bean.getAsunto());
            bean.setId_asunto_ref(bean.getId_asunto_ref() == null ? "" : "");

            Integer id_estatus = bean.getEstatus();
            switch (id_estatus) {
                case 0:
                    bean.setEstatus_desc("Pendiente");
                    break;
                case 1:
                    bean.setEstatus_desc("Pendiente");
                    break;
                case 2:
                    bean.setEstatus_desc("Atendido");
                    break;
                case 3:
                    bean.setEstatus_desc("Concluido");
                    break;
                case 4:
                    bean.setEstatus_desc("En Tiempo");
                    break;
                case 5:
                    bean.setEstatus_desc("Por Vencer");
                    break;
                case 6:
                    bean.setEstatus_desc("Vencido");
                    break;
                case 7:
                    bean.setEstatus_desc("En Captura");
                    break;
                case 8:
                    bean.setEstatus_desc("En Revisión");
                    break;
                case 9:
                    bean.setEstatus_desc("Eliminado");
                    break;
                case 10:
                    bean.setEstatus_desc("Archivado");
                    break;
                case -1:
                    bean.setEstatus_desc("Todos");
                    break;
                default:
                    bean.setEstatus_desc("");
                    break;
            }
        }
        String RUTA_LOGO = ResourceBundle.getBundle(Constantes.PROPERTIES).getString("ruta.reporte.logo");

        InputStream xmlResource = this.getServlet().getServletContext().getResourceAsStream("/jasperxml/reporteVolantePdf.jrxml");//el archivo de diseno
        InputStream logo = this.getServlet().getServletContext().getResourceAsStream(RUTA_LOGO);//el logo
        
        URL urlLogo = this.getServlet().getServletContext().getResource(RUTA_LOGO);
        
        Empleado usuario = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);
        String ruta = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        //File flogo = new File(new FileInputStream());
        new GeneradorArchivos(lista, xmlResource, logo, usuario.getCorreo() ,usuario.getCorreo(), correoService, ruta, urlLogo).start();
        // public GeneradorArchivos(List<Asunto> dataSource, InputStream xmlResource, InputStream logo, String nombreArchivo, String destinatario, CorreoService correoService, String ruta, String asunto)

        return mapping.findForward(forward);
    }
    
   

    /**
     * @param temaService the temaService to set
     */
    public void setTemaService(TemaService temaService) {
        this.temaService = temaService;
    }

    /**
     * @param expedienteService the expedienteService to set
     */
    public void setExpedienteService(ExpedienteService expedienteService) {
        this.expedienteService = expedienteService;
    }

    /**
     * @param asuntoService the asuntoService to set
     */
    public void setAsuntoService(AsuntoService asuntoService) {
        this.asuntoService = asuntoService;
    }

    /**
     * @param correoService the correoService to set
     */
    public void setCorreoService(CorreoService correoService) {
        this.correoService = correoService;
    }
}
