/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.economia.scg.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import mx.gob.economia.scg.model.Empleado;
import mx.gob.economia.scg.model.EstadoAsunto;
import mx.gob.economia.scg.util.Constantes;
import mx.gob.economia.scg.util.Util;
import org.apache.log4j.Logger;
/**
 *
 * @author roque
 */
public class AsuntoEstadoServiceImpl implements AsuntoEstadoService
{

    private Boolean estado = false;
    protected static Logger log = Logger.getLogger("Log");
    //static Logger logger = Logger.getLogger(AsuntoEstadoServiceImpl.class);

    /**
     * Evalúa si el asunto esta siendo usado por alguien mas. 
     * Si no esta usado, lo pone en uso y regresa false
     * Si esta en uso regresa true
     * @param request
     * @return
     */
    public EstadoAsunto isBusyMatter(HttpServletRequest request, Integer idAsunto, String origenMessage)
    {
        ServletContext servletContext = request.getSession().getServletContext();
        Map<Integer, EstadoAsunto> listAsuntosBusy = (Map<Integer, EstadoAsunto>) servletContext.getAttribute(Constantes.MAP_ASUNTOS_USO);
        listAsuntosBusy = listAsuntosBusy == null ? new HashMap<Integer, EstadoAsunto>() : listAsuntosBusy;
        EstadoAsunto estadoAsunto = null;
        try
        {
            if (listAsuntosBusy.containsKey(idAsunto))// si contiene el asunto
            {
                estadoAsunto = (EstadoAsunto) listAsuntosBusy.get(idAsunto);
                estadoAsunto.setIsBusy(true);// esta en uso

                //ver que no haya excedido el limite de tiempo en uso
                Long maxMinUso = Long.parseLong(ResourceBundle.getBundle(Constantes.PROPERTIES).getString("max.minutos.uso.asunto"));
                if (new Util().compareTimesByMinutes(estadoAsunto.getTimeStamp(), new Timestamp(new java.util.Date().getTime())) > maxMinUso)
                    estadoAsunto.setIsBusy(false);//si ha excedido el tiempo en uso, indicar que ahora se puede usar el asunto
            } else// si no lo contiene, entonce subirlo a session
            {
                Empleado usuarioSession = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);
                estadoAsunto = new EstadoAsunto();
                estadoAsunto.setIdAsunto(idAsunto);
                estadoAsunto.setTimeStamp(new Timestamp(new java.util.Date().getTime()));
                estadoAsunto.setIdUsuario(usuarioSession.getId_empleado());
                estadoAsunto.setEmail(usuarioSession.getCorreo());
                estadoAsunto.setMessage("El documento esta siendo " + origenMessage + " por " + estadoAsunto.getEmail());
                listAsuntosBusy.put(idAsunto, estadoAsunto);
                servletContext.removeAttribute(Constantes.MAP_ASUNTOS_USO);
                servletContext.setAttribute(Constantes.MAP_ASUNTOS_USO, listAsuntosBusy);
                estadoAsunto.setIsBusy(false);// el la asunto se sube al contexto, e indicar que se puede usar
            }

        } catch (Exception e)
        {
            log.debug("Ha ocurrido un error en la comprobacion de un asunto en uso ->", e);
        }
        return estadoAsunto;
    }

    /**
     * Elimina de la lista de asunto en uso, el asunto que no se ha
     * @param request
     * @param idAsunto
     * @return
     */
    public Integer deleteAsuntoFromListSession(HttpServletRequest request, Integer idAsunto)
    {
        try
        {
            ServletContext servletContext = request.getSession().getServletContext();
            Map<Integer, EstadoAsunto> listAsuntosContext = (Map<Integer, EstadoAsunto>) servletContext.getAttribute(Constantes.MAP_ASUNTOS_USO);
            listAsuntosContext.remove(idAsunto);
            servletContext.removeAttribute(Constantes.MAP_ASUNTOS_USO);
            servletContext.setAttribute(Constantes.MAP_ASUNTOS_USO, listAsuntosContext);
        } catch (Exception e)
        {
            log.debug("Ha ocurrido un error mientras se borraba el asunto de la lista en el contexto", e);
        }
        return Constantes.FIRST;
    }

    /**
     * @return the estado
     */
    public Boolean getEstado()
    {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(Boolean estado)
    {
        this.estado = estado;
    }
}
