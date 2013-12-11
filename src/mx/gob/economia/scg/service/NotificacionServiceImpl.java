package mx.gob.economia.scg.service;

import java.util.Date;
import javax.servlet.ServletContext;
import mx.gob.economia.scg.util.Constantes;
import mx.gob.economia.scg.util.Util;
import org.apache.log4j.Logger;

/**
 * Implementation UsuarioService
 * 
 * @author valentin.gomez
 * 
 */
public class NotificacionServiceImpl implements NotificacionService
{

    private CorreoService correoService;
    private ServerConfigImpl servletConfig;
    protected Logger log = Logger.getLogger(this.getClass());

    public void notificar()
    {
        log.info("Notifica a las " + Util.formatearFecha(new Date(), Util.FORMATODDMMYYYYHHMMSS));
        correoService.getClass();
    }

    /**
     * Borrar los asunto del contexto
     */
    public void bajaAsuntosContextoServer()
    {
        try
        {
            log.info("Removiendo asuntos del contexto del servidor");
            ServletContext sc = this.servletConfig.getServletContextWrapper();
            sc.removeAttribute(Constantes.MAP_ASUNTOS_USO);
            log.info("Asuntos del contexto del servidor dados de baja satisfactoriamente");
        } catch (Exception e)
        {
            log.error("Ha ocurrido un error al intentar dar de baja los asunto del contexto del servidor", e);
        }
    }

    /**
     * @param correoService the correoService to set
     */
    public void setCorreoService(CorreoService correoService)
    {
        this.correoService = correoService;
    }

    /**
     * @param servletConfig the servletConfig to set
     */
    public void setServletConfig(ServerConfigImpl servletConfig)
    {
        this.servletConfig = servletConfig;
    }
}
