/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.economia.scg.util;


import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import mx.gob.economia.scg.model.Asunto;
import mx.gob.economia.scg.service.CorreoService;
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
public class GeneradorArchivos extends Thread {
    private InputStream xmlResource;
    private InputStream logo;
    private List<Asunto> dataSource;
    private String nombreArchivo;
    private String destinatario;
    private CorreoService correoService;
    private String contexto;
    private URL urlLogo;
    Logger log = Logger.getLogger(this.getClass());
    
    
    public GeneradorArchivos(List<Asunto> dataSource, InputStream xmlResource, InputStream logo, String nombreArchivo, String destinatario, CorreoService correoService, String contexto, URL urlLogo)
    {
        this.xmlResource = xmlResource;
        this.logo = logo;
        this.dataSource = dataSource;
        this.nombreArchivo = nombreArchivo;
        this.destinatario = destinatario;
        this.correoService = correoService;
        this.contexto = contexto;
        this.urlLogo = urlLogo;
    }
    
    public void generaArchivos(HttpServletRequest request) {
        JasperDesign jasperDesign;

        try {
            HashMap<String, Object> parametersMap = new HashMap<String, Object>();//los parametros


            parametersMap.put("LOGO", logo);//la imagen como parametro
            JRBeanCollectionDataSource dataSourceCollection = new JRBeanCollectionDataSource(dataSource);//se crea la coleccion

            jasperDesign = JRXmlLoader.load(xmlResource);//se crea el rescurso
            JasperReport reportPath = JasperCompileManager.compileReport(jasperDesign);// compilacion del xml de diseno
            JasperPrint print = JasperFillManager.fillReport(reportPath, parametersMap, dataSourceCollection);//la impresion del reporte            
            byte[] reportePdf = JasperExportManager.exportReportToPdf(print);//se crea el flujo de bytes
            
            
            String RUTA = ResourceBundle.getBundle(Constantes.PROPERTIES).getString("documentos.ruta");  //la ruta en donde se va a escribir el archivo en el servidor
            new UtilDocumentDispatcher().escribeArchivoDisco(RUTA + nombreArchivo + ".pdf", reportePdf);
            enviaCorreoArchivo(destinatario, contexto + "/DocumentDispatcherServlet?ruta=" + RUTA + nombreArchivo + ".pdf",urlLogo, request);
            //UtilsReportes.descargaPdf(reportePdf, response, "application/pdf", "ReporteGeneralAsuntos.pdf");//el header y la impresion del reporte
        } catch (Exception e) 
        {
            e.printStackTrace();
        }        
        
        
    }

    public void run(HttpServletRequest request) {
        generaArchivos(request);
    }
    
	public void enviaCorreoArchivo(String destinatario, String Ruta, URL urlLogo, HttpServletRequest request) {
		try {
			// Documentos adjuntos al correo
			List<File> files = null;
			// Contenido del mensaje
                        
			String contenido = "<BR/><BR/>Buen dia:"
					+ "<BR/><BR/>"
					+ "<BR/><BR/>Se genero el reporte de volantes que se encuentra en la siguiente liga:"
					+ "<BR/><BR/><a href='" + Ruta + "' >Descargar</a>"
					+ "<BR/><BR/>Le sugerimos descargar el reporte y almacenarlo.";

			String html = Util.getHtml(contenido);
			List<String> correosAtencion = new ArrayList<String>();
			correosAtencion.add(destinatario);
		
			if (!correosAtencion.isEmpty()) {
				String[] paraAtencion = (String[]) correosAtencion
						.toArray(new String[0]);

				correoService.send(paraAtencion,
						"Reporte de Volantes Concluido", html,
						contenido, files,urlLogo, request);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}     
}
