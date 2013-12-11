package mx.gob.economia.scg.service;

import java.io.File;
import java.net.URL;
import java.util.List;
import javax.servlet.http.HttpServletRequest;



/**
 * Interface UsuarioService
 * 
 * @author valentin.gomez
 * 
 */
public interface CorreoService {

	public void send(String[] para, String asunto, String contenidoHtml, String contenido,
			List<File> archivosAdjuntos, URL urlLogo, HttpServletRequest request);
	
	public void send(String para, String asunto, String contenidoHtml, String contenido,
			List<File> archivosAdjuntos, URL ulrLogo, HttpServletRequest request);
	
}