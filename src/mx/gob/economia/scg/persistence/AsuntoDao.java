package mx.gob.economia.scg.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.gob.economia.scg.model.Asunto;
import mx.gob.economia.scg.model.Empleado;
import mx.gob.economia.scg.model.AsuntoDetalle;
import mx.gob.economia.scg.model.CriterioAsunto;
import mx.gob.economia.scg.model.CriterioReporteVolante;
import mx.gob.economia.scg.model.BitacoraAsunto;

/**
 * Interface AsuntoDao
 * 
 * @author valentin.gomez
 * 
 */
public interface AsuntoDao
{

    public Asunto getAsunto(Integer id_asunto);

    public List<Asunto> listAsuntos(CriterioAsunto criterioAsunto);

    public List<Empleado> listCCpsAsunto(Integer id_asunto);

    public List<Asunto> listAsuntosIniciales(CriterioAsunto criterioAsunto);

    public List<Asunto> listAsuntosActuales(CriterioAsunto criterioAsunto);

    public List<Asunto> listAsuntosActualesDetallePDF(CriterioAsunto criterioAsunto);

    public Integer countAsuntos(CriterioAsunto criterioAsunto);

    public Integer countAsuntosIniciales(CriterioAsunto criterioAsunto);

    public Integer countAsuntosActuales(CriterioAsunto criterioAsunto);

    public Asunto saveAsunto(Asunto asunto) throws Exception;

    public Asunto SaveAtenderParcial(Asunto asunto) throws Exception;

    public void borraAtenderParcial(Integer idAsunto_detalle);

    public int saveAsuntoConcentrado(List<Map<String, Integer>> ids_asunto);

    public int saveAsuntoConcentradoRet(List<Map<String, Integer>> ids_asunto,int id_concentrado);

    public int getIdConcentrado(Integer idAsunto);

    public int getIdConcentradoAsunto(Integer idAsunto);

    public int updateAsuntoConcentrado(List<Map<String, Integer>> ids_asunto, Integer id_concentrado);

    public Asunto updateAsunto(Asunto asunto) throws Exception;

    public Asunto updateAsuntoCaptura(Asunto asunto) throws Exception;

    public Asunto deleteRelaciones(Asunto asunto) throws Exception;

    public int updateFhLectura(AsuntoDetalle asunto_detalle);

    public int updateFhLecturaCcp(HashMap<String, Object> asunto_ccp);

    public AsuntoDetalle insertAsuntoDetalle(AsuntoDetalle asunto_detalle);

    public void deleteAsunto(Asunto asunto) throws Exception;

    public Integer saveAsuntoConsecutivoArea(Asunto asunto);

    public String getFolioAsunto(Integer idArea);

    public List<String> getIdsAsuntoByCriterio(CriterioAsunto criterioAsunto);

    public List<String> getIdsAsuntoCapturaByCriterio(CriterioAsunto criterioAsunto);

    public void updateEstatusAsunto(Asunto asunto);

    public void updateEstatusAsuntoDetalle(AsuntoDetalle asuntoDetalle);

    public void updateEstatusAsuntoDetalleByIdAsuntoDetalle(AsuntoDetalle asuntoDetalle);

    public void updateEstatusAsuntoDetalleRevisor(AsuntoDetalle asuntoDetalle);

    public List<Asunto> listAsuntoReporteVolante(CriterioReporteVolante criterio);

    public Integer countAsuntosEnRevision(CriterioAsunto criterioAsunto);

    public String getSigContadorFolio(String folio);

    public List<Asunto> listAsuntosByDistinctFolio(CriterioAsunto criterioAsunto);

    public List<Asunto> listAsuntosByDistinctFolioRevisor(CriterioAsunto criterioAsunto);

    public Asunto getAsuntoDetalleById(Integer id_asunto);    

    public int updateComentario(Asunto asunto);

    public List<String> getLastIdsAsuntoByCriterio(CriterioAsunto criterioAsunto);

    public List<String> listIdsAsuntosIniciales(CriterioAsunto criterioAsunto);

    public BitacoraAsunto getBitacoraAsunto(Integer id_asunto);

    public Integer saveBitacoraAsunto(BitacoraAsunto bitacoraAsunto);

    public void updateBitacoraAsunto(BitacoraAsunto bitacoraAsunto);

    public AsuntoDetalle getDetalleByIdAsuntoDetalle(Integer id_asunto_detalle);

    public Asunto guardaCapturaTurno(Asunto asunto);

    public void borraCapturaTurno(Integer idAsunto_detalle);

    public List <AsuntoDetalle> listTurnoGuardado(Integer id_asunto_detalle);

    public Asunto updateEditarTurno(Asunto asunto) throws Exception;

    public List<String> getIdsAsuntoByCriterio_ultimo(CriterioAsunto criterioAsunto);

    public AsuntoDetalle listByIdAsuntoDetalle(Integer idAsunto);

    public AsuntoDetalle listByIdAsuntoDetalle_ultimo(Integer idAsunto);

    public List<Asunto> listAsuntosInicialesNuevasReglas(CriterioAsunto criterioAsunto);

    public Integer countAsuntosInicialesNuevasReglas(CriterioAsunto criterioAsunto);

    public Integer ultimoEstatusAsuntoByArea(HashMap param);

    public Asunto SaveAtenderParcialRechazo(Asunto asunto);

}
