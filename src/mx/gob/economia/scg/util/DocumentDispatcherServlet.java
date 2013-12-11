/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.economia.scg.util;

/**
 *
 * @author javier
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class for Servlet: DocumentDispatcherServlet
 *
 */
public class DocumentDispatcherServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

    static final long serialVersionUID = 1L;
    private static Logger log = Logger.getLogger(DocumentDispatcherServlet.class);
    private String CONTENT_TYPE = null;
    private ServletOutputStream out = null;

    public synchronized void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public synchronized void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("doGet");
        response.setContentType(CONTENT_TYPE);
        out = response.getOutputStream();
        try {
            buildDocument(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (out != null) {
            out.close();
        }

    }

    public synchronized void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("doPost");
        response.setContentType(CONTENT_TYPE);
        out = response.getOutputStream();
        try {
            buildDocument(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (out != null) {
            out.close();
        }
    }

    private void buildDocument(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
        if (out != null) {
            String strPath = request.getParameter("ruta");
            log.debug("buildDocument: if: strPath: " + strPath);

            int position = strPath.lastIndexOf(".");
            int positiondiagonal = strPath.lastIndexOf("/");
            String nombrearchivo = strPath.substring(positiondiagonal, strPath.length());
            int size = strPath.length();

            log.debug("nombre: " + nombrearchivo);

            String extencion = strPath.substring(position, size);

            if (extencion.equals(".pdf")) {
                CONTENT_TYPE = "application/pdf";
            } else if (extencion.equals(".jpg")) {
                CONTENT_TYPE = "image/jpeg";
            } else if (extencion.equals(".xls")) {
                CONTENT_TYPE = "application/vnd.ms-excel";
            } else if (extencion.equals(".xlsx")) {
                CONTENT_TYPE = "application/vnd.ms-excel";
            } else if (extencion.equals(".doc")) {
                CONTENT_TYPE = "application/msword";
            } else if (extencion.equals(".docx")) {
                CONTENT_TYPE = "application/msword";
            } else if (extencion.equals(".gif")) {
                CONTENT_TYPE = "image/gif";
            }

            if (CONTENT_TYPE != null) {
                response.setContentType(CONTENT_TYPE);
            }

            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            response.setHeader("Content-disposition", "attachment; filename=" + nombrearchivo);

            FileInputStream fis = new FileInputStream(strPath);

            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            try {
                bis = new BufferedInputStream(fis);
                bos = new BufferedOutputStream(out);
                byte[] buff = new byte[2048];
                int bytesRead;
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }

            } catch (IOException ioe) {
                log.error("ERR:" + ioe.toString());
            } finally {
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.close();
                }
            }
        } else {
            throw new Exception("No es posible construir el documento [ ServletOutputStream out -> null ]");
        }
    }
}