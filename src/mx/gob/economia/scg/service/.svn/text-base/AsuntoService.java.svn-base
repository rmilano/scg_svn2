package mx.gob.economia.scg.service;

import java.net.URL;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import mx.gob.economia.scg.model.Asunto;
import mx.gob.economia.scg.model.AsuntoDetalle;
import mx.gob.economia.scg.model.CriterioAsunto;
import mx.gob.economia.scg.model.CriterioReporteVolante;
import mx.gob.economia.scg.model.Empleado;
import mx.gob.economia.scg.model.Firma;
import mx.gob.economia.scg.model.BitacoraAsunto;

/**
 * Interface EmpleadoService
 * 
 * @author valentin.gomez
 * 
 */
public interface AsuntoService
{

    public Asunto getAsunto(Integer id_asunto);

    public List<Asunto> listAsuntosEnCaptura(CriterioAsunto criterioAsunto,
            HttpServletRequest request);

    public Integer countAsuntosEnCaptura(CriterioAsunto criterioAsunto,
            HttpServletRequest request);

    public List<Asunto> listAsuntosEnRecepcion(CriterioAsunto criterioAsunto,
            HttpServletRequest request);

    public Integer countAsuntosEnRecepcion(CriterioAsunto criterioAsunto,
            HttpServletRequest request);

    public List<Asunto> listAsuntosEnBandeja(CriterioAsunto criterioAsunto,
            HttpServletRequest request);

    public Integer countAsuntosEnBandeja(CriterioAsunto criterioAsunto,
            HttpServletRequest request);

    public List<Asunto> listAsuntosCcp(CriterioAsunto criterioAsunto,
            HttpServletRequest request);

    public Integer countAsuntosCcp(CriterioAsunto criterioAsunto,
            HttpServletRequest request);

    public List<Asunto> listAsuntosAtendidos(CriterioAsunto criterioAsunto,
            HttpServletRequest request);

    public Integer countAsuntosAtendidos(CriterioAsunto criterioAsunto,
            HttpServletRequest request);

    public List<Asunto> listAsuntosFinalizados(CriterioAsunto criterioAsunto,
            HttpServletRequest request);

    public Integer countAsuntosFinalizados(CriterioAsunto criterioAsunto,
            HttpServletRequest request);

    public Integer countAsuntosArchivados(CriterioAsunto criterioAsunto,
            HttpServletRequest request);

    public List<Asunto> listAsuntosArchivados(CriterioAsunto criterioAsunto,
            HttpServletRequest request);

    public List<Asunto> listAsuntosTurnados(CriterioAsunto criterioAsunto,
            HttpServletRequest request);

    public Integer countAsuntosTurnados(CriterioAsunto criterioAsunto,
            HttpServletRequest request);

    public List<Asunto> listAsuntos(CriterioAsunto criterioAsunto);

    public List<Asunto> listAsuntosIniciales(CriterioAsunto criterioAsunto);

    public List<Asunto> listAsuntosActuales(CriterioAsunto criterioAsunto);

    public List<Asunto> listAsuntosActualesDetallePDF(CriterioAsunto criterioAsunto);

    public Integer countAsuntos(CriterioAsunto criterioAsunto);

    public Integer countAsuntosIniciales(CriterioAsunto criterioAsunto);

    public Integer countAsuntosActuales(CriterioAsunto criterioAsunto);

    public Asunto saveAsunto(Asunto asunto, HttpServletRequest request, URL urlLogo) throws Exception;

    public Asunto updateAsunto(Asunto asunto, HttpServletRequest reqrauest, URL urlLogo) throws Exception;

    public void updateFhLectura(Asunto asunto, HttpServletRequest request);

    public void updateFhLecturaCcp(Asunto asunto, HttpServletRequest request);

    public boolean isEmpleadoDestinatario(Asunto asunto,
            HttpServletRequest request);

    public boolean isEmpleadoRemitenteInicial(Asunto asunto,
            HttpServletRequest request);

    public Asunto saveAtender(Asunto asunto, HttpServletRequest request, URL urlLogo) throws Exception;
    public Asunto saveAtenderParcial(Asunto asunto, HttpServletRequest request, URL urlLogo) throws Exception;

    public Asunto saveTurnar(Asunto asunto, HttpServletRequest request, URL urlLogo) throws Exception;

    public Asunto saveReTurnar(Asunto asunto, HttpServletRequest request, URL urlLogo) throws Exception;

    public Asunto saveEditarTurno(Asunto asunto, HttpServletRequest request, URL urlLogo) throws Exception;

    public Asunto saveFinalizar(Asunto asunto, HttpServletRequest request, URL urlLogo) throws Exception;

    public Asunto saveRechazar(Asunto asunto, HttpServletRequest request, URL urlLogo) throws Exception;

    public void loadBtnDetalleAsunto(Asunto asunto, HttpServletRequest request);

    public void removeButtonsDetalle(HttpServletRequest request);

    public void deleteAsunto(Asunto asunto) throws Exception;

    public Firma getFirmaAsunto(Asunto asunto);

    public void firmarAsunto(Firma firma, Asunto asunto);

    public void loadCatalogosCapturaAsunto(HttpServletRequest request);

    public Asunto getAsuntoCaptura(Integer id_asunto);

    public Asunto getAsuntoDetalleById(Integer id_asunto);

    public List<String> getIdsAsuntoByCriterio(CriterioAsunto criterioAsunto);

    public List<String> getIdsAsuntoCapturaByCriterio(CriterioAsunto criterioAsunto);

    public List<Asunto> listaAsuntosReporteVolante(CriterioReporteVolante criterio);

    public void updateComentario(Asunto asunto, HttpServletRequest request);

    public Integer countAsuntosPendientes(HttpServletRequest request);

    public Integer countAsuntosParaVoBo(HttpServletRequest request);

    public Integer countTotalAsuntosPendientes(HttpServletRequest request);

    public void asignarFhLimite(Asunto asunto, HttpServletRequest request);

    public void asignarFhLimiteDetalle(AsuntoDetalle asuntoDetalle, HttpServletRequest request);

    public List<Empleado> populateBeanEmpleadoCcp(List<Empleado> empleadosCcp);

    public String getEmpleadosCcp(List<Empleado> empleados);

    public String getFolioAsunto(Integer idArea);

    public List<Empleado> addEmpleadoDestinanrioToList(List<Empleado> empleados, Empleado empleadoDestinatario);

    public List<String> listIdsAsuntosEnCaptura(CriterioAsunto criterioAsunto, HttpServletRequest request);

    public BitacoraAsunto getBitacoraAsunto(Integer id_asunto);

    public Integer saveBitacoraAsunto(BitacoraAsunto bitacoraAsunto);

    public void updateBitacoraAsunto(BitacoraAsunto bitacoraAsunto);

    public AsuntoDetalle getDetalleByIdAsuntoDetalle(Integer id_asunto_detalle);

    public Asunto guardaCapturaTurno(Asunto asunto, HttpServletRequest request) throws Exception;

    public List <AsuntoDetalle> listTurnoGuardado(Integer id_asunto_detalle);

    public List<String> getIdsAsuntoByCriterio_ultimo(CriterioAsunto criterioAsunto);

    public AsuntoDetalle listByIdAsuntoDetalle(Integer id_asunto);

    public AsuntoDetalle listByIdAsuntoDetalle_ultimo(Integer id_asunto);

    public List<Asunto> listAsuntosInicialesNuevasReglas(CriterioAsunto criterioAsunto);

    public Integer countAsuntosInicialesNuevasReglas(CriterioAsunto criterioAsunto);

    public void sendMailNuevoAsunto(Asunto asunto, URL urlLogo, HttpServletRequest request);

    public Integer getUltimoEstatusAsuntoByArea(HashMap param);

    public Asunto saveAtenderParcialRechazo(Asunto asunto, HttpServletRequest request, URL urlLogo) throws Exception;

}
