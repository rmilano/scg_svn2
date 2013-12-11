package mx.gob.economia.scg.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import org.apache.log4j.Logger;

public class UtilsReportes {
		public static Logger log = Logger.getLogger("mx.gob.economia.scg.util.UtilsReportes");
	public static void descargaPdf(byte[] arrDatos, HttpServletResponse res, String content, String nomArchivo) throws IOException {

		OutputStream outputStream = res.getOutputStream();
		res.setContentType(content);
		res.setContentLength(arrDatos.length);
		res.setHeader("Content-Disposition",
					  "attachment; filename="+nomArchivo);
		outputStream.write(arrDatos, 0, arrDatos.length);
		outputStream.flush();
		outputStream.close();
	}

	public static void descargaXls(JasperReport jasperReport,  HashMap parms, JRBeanCollectionDataSource dataSource, HttpServletResponse res,String nomArchivo) throws Exception {		
		ByteArrayOutputStream os = new ByteArrayOutputStream(); 
		JasperPrint print = JasperFillManager.fillReport(jasperReport,parms,dataSource);
		JRXlsExporter exp = new JRXlsExporter();
		exp.setParameter(JRXlsExporterParameter.JASPER_PRINT, print); 
		exp.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, os); 		
		exp.exportReport();
		res.setContentType("application/vnd.ms-excel");
		res.setHeader("Content-Disposition", "attachment; filename="+nomArchivo);
		byte[] bytes = os.toByteArray();
         ServletOutputStream out = res.getOutputStream();
         out.write(bytes, 0, bytes.length);
         out.flush();
         out.close();	
	}

	public static byte [] runReportToRtf(JasperReport jasperReport, HashMap parms, Connection conn) {		
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream(); 
			JasperPrint print = JasperFillManager.fillReport(jasperReport, parms, conn);
			JRRtfExporter exp = new JRRtfExporter();
			exp.setParameter(JRExporterParameter.JASPER_PRINT,  print);
			exp.setParameter(JRExporterParameter.OUTPUT_STREAM,os);
			exp.exportReport();
			if(conn !=null)
				conn.close();
			return os.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}					
	}
	

	public static Connection createConn(DataSource dataSource) {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			log.error("Driver not found.");
			System.exit(1);
		}
		    
		try {
			conn=dataSource.getConnection();
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			log.error("Error de conexion: " + e.getMessage());
			System.exit(4);
		}
		return conn;		
	}
}
