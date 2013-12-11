/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.economia.scg.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.log4j.Logger;

/**
 *
 * @author javier
 */
public class UtilDocumentDispatcher {

    public void escribeArchivoDisco(String strFilePath, byte[] reportePdf) {
        try {
            FileOutputStream fos = new FileOutputStream(strFilePath);

            fos.write(reportePdf);

            fos.close();

        } catch (FileNotFoundException ex) {
            Logger log = Logger.getLogger(this.getClass());
            log.error("FileNotFoundException : " + ex);
        } catch (IOException ioe) {
            Logger log = Logger.getLogger(this.getClass());
            log.error("IOException : " + ioe);
        }
    }
}
