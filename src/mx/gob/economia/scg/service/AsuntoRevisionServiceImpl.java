/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.economia.scg.service;

import java.net.URL;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import mx.gob.economia.scg.model.Asunto;
import mx.gob.economia.scg.model.AsuntoDetalle;
import mx.gob.economia.scg.model.CriterioAsunto;
import mx.gob.economia.scg.model.Empleado;
import mx.gob.economia.scg.persistence.AsuntoDao;
import mx.gob.economia.scg.persistence.EmpleadoDao;
import mx.gob.economia.scg.persistence.AsuntoCapturaActualizacionDao;
import mx.gob.economia.scg.util.Constantes;
import org.apache.log4j.Logger;

/**
 * Implementa los servicios relacionados a la revision del asunto
 * @author gerardo
 */
public class AsuntoRevisionServiceImpl extends AsuntoServiceImpl implements AsuntoRevisionService
{

    private AsuntoDao asuntoDao;
    private EmpleadoDao empleadoDao;
    private TemaService temaService;
    private AsuntoService asuntoService;
    private AsuntoCapturaActualizacionDao asuntoCapturaActualizacionDao;
 
    /**
     * Lista los asunto en Revision
     * @param criterioAsunto
     * @param request
     * @return
     */
    public List<Asunto> listAsuntosEnRevision(CriterioAsunto criterioAsunto,HttpServletRequest request)
    {
        Empleado usuarioSession = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);
        List<String> idsEmpleados = this.empleadoDao.getIdsEmpleadosByIdArea(usuarioSession.getArea().getId_area());
        criterioAsunto.getIds_estatus().clear();
        criterioAsunto.getIds_empleados_captura().clear();
        criterioAsunto.setIds_empleados_captura(idsEmpleados);
        criterioAsunto.getIds_estatus().add(Integer.toString(Constantes.EN_REVISION));
        criterioAsunto.setIds_tema(this.temaService.getIdsTemaByRevisor(usuarioSession.getId_empleado()));        
        return this.listAsuntosByDistinctFolio(criterioAsunto);
    }
    /**
     * Lista los asuntos por revisar
     * @param criterioAsunto
     * @param request
     * @return
     */
    public List<Asunto> listAsuntosPorSupervisar(CriterioAsunto criterioAsunto,HttpServletRequest request)
    {
        Empleado usuarioSession = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);        
        criterioAsunto.getIds_estatus().clear();
        criterioAsunto.getIds_empleados_remi().clear();
        criterioAsunto.getIds_empleados_remi().add(usuarioSession.getId_empleado() + "");
        criterioAsunto.getIds_estatus().add(Integer.toString(Constantes.EN_SUPERVISION_CAPTURA));
        return this.listAsuntosByDistinctFolio(criterioAsunto);
    }
    /**
     * Obtiene los asunto que ya fueron revisados
     * @param criterioAsunto
     * @param request
     * @return
     */
    public List<Asunto> listAsuntosRevisados(CriterioAsunto criterioAsunto,HttpServletRequest request)
    {
        Empleado usuarioSession = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);
        /*List<String> idsEmpleados = this.empleadoDao.getIdsEmpleadosByIdArea(usuarioSession.getArea().getId_area());
        criterioAsunto.getIds_estatus().clear();
        criterioAsunto.getIds_empleados_captura().clear();
        criterioAsunto.setIds_empleados_captura(idsEmpleados);
        criterioAsunto.getIds_estatus().add(Integer.toString(Constantes.REVISADO_CAPTURA));
        return this.listAsuntosByDistinctFolio(criterioAsunto); AGG 20111212 */
        String id_area_capt = usuarioSession.getArea().getId_area().toString();
        criterioAsunto.setId_area_capt(id_area_capt);
        criterioAsunto.setEstatus(Integer.toString(Constantes.REVISADO_CAPTURA));
        return this.asuntoDao.listAsuntosIniciales(criterioAsunto);
    }
    /**
     * Obtiene los asunto que ya fueron supervisados
     * @param criterioAsunto
     * @param request
     * @return
     */
    public List<Asunto> listAsuntosSupervisados(CriterioAsunto criterioAsunto,HttpServletRequest request)
    {
        Empleado usuarioSession = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);
        List<String> idsEmpleados = this.empleadoDao.getIdsEmpleadosByIdArea(usuarioSession.getArea().getId_area());
        criterioAsunto.getIds_estatus().clear();
        criterioAsunto.getIds_empleados_remi().clear();
        criterioAsunto.setIds_empleados_remi(idsEmpleados);
        criterioAsunto.getIds_estatus().add(Integer.toString(Constantes.SUPERVISADO));
        return this.listAsuntosByDistinctFolio(criterioAsunto);
    }

    /**
     * Obtiene la lista de asunto. La lista aplica un distinct sobre el folio
     * @param criterioAsunto
     * @return
     */
    public List<Asunto> listAsuntosByDistinctFolio(CriterioAsunto criterioAsunto)
    {
        // Asigna el numero de registros de la lista a obtener
        criterioAsunto.getPaginador().setNumRegistros(this.asuntoDao.countAsuntosEnRevision(criterioAsunto));
        return this.asuntoDao.listAsuntosByDistinctFolio(criterioAsunto);
    }
    
    public List<Asunto> listAsuntosByDistinctFolioRevisor(CriterioAsunto criterioAsunto)
    {
        // Asigna el numero de registros de la lista a obtener
        criterioAsunto.getPaginador().setNumRegistros(this.asuntoDao.countAsuntosEnRevision(criterioAsunto));
        return this.asuntoDao.listAsuntosByDistinctFolioRevisor(criterioAsunto);
    }    

    /**
     * Cambia el estatus del asunto a 'EN_REVISION'.Se agrupan los asunto por folio
     * @param folio
     */
    public void enviarAsuntoRevision(String folio)
    {
        Asunto asunto = new Asunto();
        asunto.setFolio(folio);
        asunto.setEstatus(Constantes.EN_REVISION);
        // se actualiza los asunto con el mismo numero de folio
        this.asuntoDao.updateEstatusAsunto(asunto);
        // Se actualizan los detalles del asunto
        CriterioAsunto criterioAsunto = new CriterioAsunto();
        criterioAsunto.setFolio(folio);
        //se obtienen los ids del asunto por folio
        List<String> idsAsuntos = this.asuntoDao.getIdsAsuntoByCriterio(criterioAsunto);
        //se actualizan los detalles
        AsuntoDetalle asuntoDetalle = new AsuntoDetalle();
        asuntoDetalle.setIds_asunto(idsAsuntos);
        asuntoDetalle.setEstatus(Constantes.EN_REVISION);
        this.asuntoDao.updateEstatusAsuntoDetalle(asuntoDetalle);
    }
    /**
     * Cambia el estatus del asunto a 'EN_SUPERVISION_CAPTURA'.Se agrupan los asunto por folio
     * @param folio
     */
    public void enviarAsuntoSupervicion(String folio)
    {
        Asunto asunto = new Asunto();
        asunto.setFolio(folio);
        asunto.setEstatus(Constantes.EN_SUPERVISION_CAPTURA);
        // se actualiza los asunto con el mismo numero de folio
        this.asuntoDao.updateEstatusAsunto(asunto);
        // Se actualizan los detalles del asunto
        CriterioAsunto criterioAsunto = new CriterioAsunto();
        criterioAsunto.setFolio(folio);
        //se obtienen los ids del asunto por folio
        List<String> idsAsuntos = this.asuntoDao.getIdsAsuntoByCriterio(criterioAsunto);
        //se actualizan los detalles
        AsuntoDetalle asuntoDetalle = new AsuntoDetalle();
        asuntoDetalle.setIds_asunto(idsAsuntos);
        asuntoDetalle.setEstatus(Constantes.EN_SUPERVISION_CAPTURA);
        this.asuntoDao.updateEstatusAsuntoDetalle(asuntoDetalle);
    }
    /**
     * El supervisor envia nuevamente el asunto a su capturista.Se agrupan los asunto por folio
     * @param folio
     */
    public void setAsuntoSupervisado(String folio)//setAsuntoSupervisado
    {
        Asunto asunto = new Asunto();
        asunto.setFolio(folio);
        asunto.setEstatus(Constantes.SUPERVISADO);
        // se actualiza los asunto con el mismo numero de folio
        this.asuntoDao.updateEstatusAsunto(asunto);
        // Se actualizan los detalles del asunto
        CriterioAsunto criterioAsunto = new CriterioAsunto();
        criterioAsunto.setFolio(folio);
        //se obtienen los ids del asunto por folio
        List<String> idsAsuntos = this.asuntoDao.getIdsAsuntoByCriterio(criterioAsunto);
        //se actualizan los detalles
        AsuntoDetalle asuntoDetalle = new AsuntoDetalle();
        asuntoDetalle.setIds_asunto(idsAsuntos);
        asuntoDetalle.setEstatus(Constantes.SUPERVISADO);
        this.asuntoDao.updateEstatusAsuntoDetalle(asuntoDetalle);
    }
    /**
     * Cambia el estatus del asunto a 'REVISADO'.Se agrupan los asunto por folio
     * @param folio
     */
    public void setAsuntoRevisado(String folio, Integer id_empleado_revi)
    {
        Logger log = Logger.getLogger(this.getClass());
        Asunto asunto = new Asunto();
        asunto.setFolio(folio);
        asunto.setEstatus(Constantes.REVISADO_CAPTURA);
        // se actualiza los asunto con el mismo numero de folio
        //this.asuntoDao.updateEstatusAsunto(asunto);
        // Se actualizan los detalles del asunto
        CriterioAsunto criterioAsunto = new CriterioAsunto();
        criterioAsunto.setFolio(folio);
        //se obtienen los ids del asunto por folio
        List<String> idsAsuntos = this.asuntoDao.getIdsAsuntoByCriterio(criterioAsunto);
        //se actualizan los detalles
        AsuntoDetalle asuntoDetalle = new AsuntoDetalle();
        asuntoDetalle.setIds_asunto(idsAsuntos);
        log.debug("antes de actualizar el detalle ids: " + idsAsuntos);
        asuntoDetalle.setEstatus(Constantes.REVISADO_CAPTURA);
        
        log.debug("antes de actualizar el detalle: " + asuntoDetalle.getEstatus());
        asuntoDetalle.getEmpleado_revi().setId_empleado(id_empleado_revi);
        this.asuntoDao.updateEstatusAsuntoDetalleRevisor(asuntoDetalle);
        this.asuntoDao.updateEstatusAsuntoDetalle(asuntoDetalle);
        
    }

    /**
     * Envia los asunto a los detinatarios.
     * @param folio
     */
    public void enviarAsuntoDestinatario(Asunto asunto, URL urlLogo, HttpServletRequest request)
    {
        Asunto asunto2 = new Asunto();
        asunto2.setFolio(asunto.getFolio());
        asunto2.setEstatus(Constantes.PENDIENTE);
        // Guardar Asunto en estatus=1 (pendiente) si el remitente y el destinatario
        // pertenecen a áreas internas
        // Ticket 152
        // Rodolfo Milano Oliveros
        Integer tipoAreaRemite = asunto.getAsunto_detalle().getEmpleado_remi().getArea().getTipo();
        Integer tipoAreaDestino = asunto.getAsunto_detalle().getEmpleado_dest().getArea().getTipo();
        if (tipoAreaRemite == 0 && tipoAreaDestino == 0){
            asunto2.setEstatus(Constantes.TURNADO);
        }

        // se actualiza los asunto con el mismo numero de folio
        this.asuntoDao.updateEstatusAsunto(asunto2);
        // Se actualizan los detalles del asunto
        CriterioAsunto criterioAsunto = new CriterioAsunto();
        criterioAsunto.setFolio(asunto.getFolio());
        //se obtienen los ids del asunto por folio
        List<String> idsAsuntos = this.asuntoDao.getIdsAsuntoByCriterio(criterioAsunto);
        //se actualizan los detalles
        AsuntoDetalle asuntoDetalle = new AsuntoDetalle();
        asuntoDetalle.setIds_asunto(idsAsuntos);
        asuntoDetalle.setEstatus(Constantes.PENDIENTE);
        // Guardar Asunto en estatus=1 (pendiente) si el remitente y el destinatario
        // pertenecen a áreas internas
        // Ticket 152
        // Rodolfo Milano Oliveros
        if (tipoAreaRemite == 0 && tipoAreaDestino == 0){
            asuntoDetalle.setEstatus(Constantes.TURNADO);
        }
        this.asuntoDao.updateEstatusAsuntoDetalle(asuntoDetalle);
        asunto.setEstatus(Constantes.PENDIENTE);
        // Guardar Asunto en estatus=1 (pendiente) si el remitente y el destinatario
        // pertenecen a áreas internas
        // Ticket 152
        // Rodolfo Milano Oliveros
        if (tipoAreaRemite == 0 && tipoAreaDestino == 0){
            asunto.setEstatus(Constantes.TURNADO);
        }
        asunto.setTipo_actualizacion(1);
        this.asuntoCapturaActualizacionDao.updateAsuntoCapturaActualizacion(asunto);
        if ((asunto.getEstatus().equals(Constantes.TURNADO)||asunto.getEstatus().equals(Constantes.PENDIENTE))
                    &&asunto.getAsunto_detalle().getEmpleado_dest_ini().getArea().getTipo().equals(Constantes.AREA_INTERNA) //Area interna
                    &&asunto.getAsunto_detalle().getEmpleado_dest_ini().getArea().getEnvio_correo().equals(1)) //Area tiene correo activado
                    asuntoService.sendMailNuevoAsunto(asunto, urlLogo, request);
    }

    /**
     * Obtiene el total de asuntos por revisar
     * @param criterioAsunto
     * @param request
     * @return
     */
    public Integer countAsuntosPorRevisar(HttpServletRequest request)
    {
        Empleado usuarioSession = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);
        //la lista de empleados
        List<String> idsEmpleados = this.empleadoDao.getIdsEmpleadosByIdArea(usuarioSession.getArea().getId_area());
        CriterioAsunto criterioAsunto = new CriterioAsunto();
        criterioAsunto.setIds_empleados_captura(idsEmpleados);// solo los que se capturaron en mi area
        criterioAsunto.getIds_estatus().add(Integer.toString(Constantes.EN_REVISION));        
        criterioAsunto.setIds_tema(this.temaService.getIdsTemaByRevisor(usuarioSession.getId_empleado()));//y por el tema que me corresponde
        return this.asuntoDao.countAsuntosEnRevision(criterioAsunto);
    }
    /**
     * Obtiene el total de asuntos por supervisar
     * @param criterioAsunto
     * @param request
     * @return
     */
    public Integer countAsuntosPorSupervisar(HttpServletRequest request)
    {
        Empleado usuarioSession = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);
        CriterioAsunto criterioAsunto = new CriterioAsunto();
        criterioAsunto.getIds_empleados_remi().add(usuarioSession.getId_empleado() + "");
        criterioAsunto.getIds_estatus().add(Integer.toString(Constantes.EN_SUPERVISION_CAPTURA));
        return this.asuntoDao.countAsuntosIniciales(criterioAsunto);
    }
    /**
     * Obtiene el total de asuntos por que ya fueron supervisados
     * @param request
     * @return
     */
    public Integer countAsuntosSupervisados(HttpServletRequest request)
    {
        Empleado usuarioSession = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);
        //la lista de empleados
        List<String> idsEmpleados = this.empleadoDao.getIdsEmpleadosByIdArea(usuarioSession.getArea().getId_area());
        CriterioAsunto criterioAsunto = new CriterioAsunto();
        criterioAsunto.setIds_empleados_captura(idsEmpleados);
        criterioAsunto.getIds_estatus().add(Integer.toString(Constantes.SUPERVISADO));
        return this.asuntoDao.countAsuntosIniciales(criterioAsunto);
    }
    /**
     * Obtiene el total de asuntos que fueron revisados
     * @param criterioAsunto
     * @param request
     * @return
     */
    public Integer countAsuntosRevisados(CriterioAsunto criterioAsunto,HttpServletRequest request)
    {
        Empleado usuarioSession = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);
        //la lista de empleados
        /*List<String> idsEmpleados = this.empleadoDao.getIdsEmpleadosByIdArea(usuarioSession.getArea().getId_area());
        CriterioAsunto criterioAsunto = new CriterioAsunto();
        criterioAsunto.setIds_empleados_captura(idsEmpleados);
        criterioAsunto.getIds_estatus().add(Integer.toString(Constantes.REVISADO_CAPTURA));
        return this.asuntoDao.countAsuntosIniciales(criterioAsunto); AGG 20111212*/
        String id_area_capt = usuarioSession.getArea().getId_area().toString();
        criterioAsunto.setId_area_capt(id_area_capt);
        criterioAsunto.setEstatus(Integer.toString(Constantes.REVISADO_CAPTURA));
        return this.asuntoDao.countAsuntosIniciales(criterioAsunto);

    }

    /**
     * @param asuntoDao the asuntoDao to set
     */
    public void setAsuntoDao(AsuntoDao asuntoDao)
    {
        this.asuntoDao = asuntoDao;
    }

    /**
     * @param empleadoDao the empleadoDao to set
     */
    public void setEmpleadoDao(EmpleadoDao empleadoDao)
    {
        this.empleadoDao = empleadoDao;
    }

    /**
     * @param temaService the temaService to set
     */
    public void setTemaService(TemaService temaService)
    {
        this.temaService = temaService;
    }
    /**
     * @param asuntoService the asuntoService to set
     */
    public void setAsuntoService(AsuntoService asuntoService)
    {
        this.asuntoService = asuntoService;
    }
    public AsuntoCapturaActualizacionDao getAsuntoCapturaActualizacionDao() {
        return asuntoCapturaActualizacionDao;
    }

    public void setAsuntoCapturaActualizacionDao(AsuntoCapturaActualizacionDao asuntoCapturaActualizacionDao) {
        this.asuntoCapturaActualizacionDao = asuntoCapturaActualizacionDao;
    }

}
