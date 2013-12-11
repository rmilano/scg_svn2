package mx.gob.economia.scg.service;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.activation.URLDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import mx.gob.economia.scg.util.Constantes;
import org.apache.log4j.Logger;

import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * Implementation CorreoService
 *
 * @author valentin.gomez
 *
 */
public class CorreoServiceImpl implements CorreoService {

    Logger log = Logger.getLogger(this.getClass());
    // Wrapper de Spring sobre javax.mail
    private JavaMailSenderImpl mailSender;
    // Correo del remitente
    private String from;
    // Indica si está activo el servicio
    public boolean active = true;

    /**
     * Envia un correo electronico a un solo destinatario
     */
    public void send(String para, String asunto, String contenidoHtml, String contenido,
            List<File> archivosAdjuntos, URL urlLogo, HttpServletRequest request) {
        String[] to2 = {para};
        this.send(to2, asunto, contenidoHtml, contenido, archivosAdjuntos, urlLogo, request);
    }

    /**
     * Envia un correo electronico a varios destinatarios
     */
    public void send(String[] para, String asunto, String contenidoHtml, String contenido,
            List<File> archivosAdjuntos, URL urlLogo, HttpServletRequest request) {
        Logger log = Logger.getLogger(this.getClass());
        // El servicio esta activo?
        /*if (!active) {
            return;
        }*/
        // Plantilla para el envío de email
        final MimeMessage mensaje = mailSender.createMimeMessage();
        try {

            MimeMultipart multipart = new MimeMultipart("related");

            InternetAddress[] direccionesPara = new InternetAddress[para.length];
            for (int i = 0; i < para.length; i++) {
                direccionesPara[i] = new InternetAddress(para[i]);
            }
            mensaje.addRecipients(Message.RecipientType.TO, direccionesPara);

            // Adjuntando el asunto del mensaje
            mensaje.setSubject(asunto);
            mensaje.setFrom(new InternetAddress(getFrom()));

            // Adjuntando el mensaje en texto
            if (contenido != null && contenido.trim().length() > 0) {
                mensaje.setText(contenido);
            }

            BodyPart messageBodyPart = null;

            // Obtiene la ruta donde se ubica la imagen
            String RUTA_LOGO = ResourceBundle.getBundle(Constantes.PROPERTIES).getString("ruta.encabezado.mail");
            URL urlLogo2 = request.getSession().getServletContext().getResource(RUTA_LOGO);

            String RUTA_FOOTER = ResourceBundle.getBundle(Constantes.PROPERTIES).getString("ruta.pie.mail");
            URL urlFooter = request.getSession().getServletContext().getResource(RUTA_FOOTER);

            // Adjuntando el mensaje en html
            if (contenidoHtml != null) {

                messageBodyPart = new MimeBodyPart();

                messageBodyPart.setContent(contenidoHtml, "text/html");
                multipart.addBodyPart(messageBodyPart);                
                //URL url = urlLogo;

                URLDataSource fds = new URLDataSource(urlLogo2);
                messageBodyPart = new MimeBodyPart();
                //DataSource source = new FileDataSource(filename);
                messageBodyPart.setDataHandler(new DataHandler(fds));
                messageBodyPart.setHeader("Content-ID", "<cidimage01>");
                multipart.addBodyPart(messageBodyPart);

                URLDataSource fds2 = new URLDataSource(urlFooter);
                messageBodyPart = new MimeBodyPart();
                //DataSource source = new FileDataSource(filename);
                messageBodyPart.setDataHandler(new DataHandler(fds2));
                messageBodyPart.setHeader("Content-ID", "<cidimage02>");
                multipart.addBodyPart(messageBodyPart);


            }
            // Adjuntando los ficheros
            for (int i = 0; archivosAdjuntos != null && i < archivosAdjuntos.size(); i++) {
                File file = new File(archivosAdjuntos.get(i).getAbsolutePath());
                DataSource ds = new FileDataSource(file);

                messageBodyPart = new MimeBodyPart();
                messageBodyPart.setDataHandler(new DataHandler(ds));
                messageBodyPart.setFileName(file.getName());
                messageBodyPart.setDisposition(Part.ATTACHMENT);
                multipart.addBodyPart(messageBodyPart);
            }
            mensaje.setContent(multipart);

            /**********************************************************************/

            Properties wProps = new Properties();
            wProps.setProperty("mail.transport.protocol", "smtp");
            //wProps.setProperty("mail.host", "w-appintrasmtp.inegi.gob.mx");
	    wProps.setProperty("mail.host", "172.18.13.42");
	    wProps.setProperty("mail.smtp.port", "25");
	    wProps.setProperty("mail.smtp.connectiontimeout", "900000");
            Session mailSession = Session.getDefaultInstance(wProps, null);

            MimeMessage wMessage = new MimeMessage(mailSession);
            wMessage.setFrom(new InternetAddress("sacg@economia.gob.mx"));
            wMessage.setSubject(asunto);

            wMessage.setSentDate(new Date());

            String correos_to[] = para;
            for (int i = 0; i < correos_to.length; i++) {
                //wMessage.addRecipient(Message.RecipientType.TO,  new InternetAddress(wEMail_To));
                wMessage.addRecipient(Message.RecipientType.TO,
                                      new InternetAddress(correos_to[i].trim()));
            }

	    //wMessage.addRecipient(Message.RecipientType.CC,new InternetAddress("desarrollo.alejandro@economia.gob.mx"));
	    //wMessage.addRecipient(Message.RecipientType.CC,new InternetAddress("vicente.martinez@economia.gob.mx"));            
            /*URLDataSource fds = new URLDataSource(urlLogo2);
            messageBodyPart = new MimeBodyPart();
            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setHeader("Content-ID", "<cidimage01>");
            multipart.addBodyPart(messageBodyPart);*/

            wMessage.setContent(multipart, "text/html");

            //-(Send Message by EMail)
            Transport wTransport = mailSession.getTransport();
            wTransport.connect();
            wTransport.sendMessage(wMessage, wMessage.getAllRecipients());
            wTransport.close();
	    System.out.println("e-mail enviado correctamente");
            System.out.println("Destinatarios: " + correos_to.toString());

        } catch (MalformedURLException ex) {
            java.util.logging.Logger.getLogger(CorreoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException e) {
            e.printStackTrace();
            new RuntimeException(e);
        }
        //"todo" Descomentar
        //this.mailSender.send(mensaje);
    }

    /**
     * @return the from
     */
    //regina.gandara
    public String getFrom() {
        return from;
    }

    /**
     * @param from the from to set
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @param mailSender the mailSender to set
     */
    public void setMailSender(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }
}