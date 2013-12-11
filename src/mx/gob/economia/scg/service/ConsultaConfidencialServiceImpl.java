/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.economia.scg.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import mx.gob.economia.scg.model.Asunto;
import mx.gob.economia.scg.model.CriterioAsunto;
import mx.gob.economia.scg.model.Empleado;
import mx.gob.economia.scg.persistence.AsuntoDao;
import mx.gob.economia.scg.util.Constantes;

/**
 *
 * @author roque
 */
public class ConsultaConfidencialServiceImpl implements ConsultaConfidencialService
{

    private AsuntoDao asuntoDao;

    public Integer countAsuntosConfidenciales(CriterioAsunto criterioAsunto, HttpServletRequest request)
    {
        // El usuario en session
        Empleado usuarioSession = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);
        // Asigna el estatus pendiente como criterio
        //criterioAsunto.setEstatus(Constantes.CONFIDENCIAL + "");
        criterioAsunto.setConfidencial(Constantes.ACTIVADO); //AGG Confidencial -> booleano 20111109
        // Asigna el area de captura como criterio
        criterioAsunto.setId_empleado_dest(usuarioSession.getId_empleado() + "");
        // el numero de asuntos confidenciales
        return asuntoDao.countAsuntosActuales(criterioAsunto);
    }

    public List<Asunto> listAsuntosConfidenciales(CriterioAsunto criterioAsunto, HttpServletRequest request)
    {
        Empleado usuarioSession = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);
        // Asigna el estatus pendiente como criterio
        //criterioAsunto.setEstatus(Constantes.CONFIDENCIAL + "");
        criterioAsunto.setConfidencial(Constantes.ACTIVADO);//AGG Confidencial -> booleano 20111109
        // Asigna el area de captura como criterio
        criterioAsunto.setId_empleado_dest(usuarioSession.getId_empleado() + "");        
        return asuntoDao.listAsuntosActuales(criterioAsunto);
    }

    /**
     * @param asuntoDao the asuntoDao to set
     */
    public void setAsuntoDao(AsuntoDao asuntoDao)
    {
        this.asuntoDao = asuntoDao;
    }
}
