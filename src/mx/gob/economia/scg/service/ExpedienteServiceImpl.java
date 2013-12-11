package mx.gob.economia.scg.service;

import java.util.List;
import mx.gob.economia.scg.model.Area;
import mx.gob.economia.scg.model.CriterioExpediente;

import mx.gob.economia.scg.model.Expediente;
import mx.gob.economia.scg.persistence.ExpedienteDao;

/**
 * 
 * @author valentin.gomez
 */
public class ExpedienteServiceImpl implements ExpedienteService {

    private ExpedienteDao expedienteDao;
    private ArbolService arbolService;

    public Expediente getExpediente(Integer id_expediente) {
        return expedienteDao.getExpediente(id_expediente);
    }

    public List<Expediente> listExpedientes(Integer id_area) {
        Area area = this.arbolService.getUnidadAdmva(id_area);
        return expedienteDao.listExpedientes(area.getId_area());
    }

    public Integer saveExpediente(Expediente expediente) {
        return expedienteDao.saveExpediente(expediente);
    }

    public void updateExpediente(Expediente expediente) {
        expedienteDao.updateExpediente(expediente);
    }

    public void deleteExpediente(Integer id_expediente) {
        expedienteDao.deleteExpediente(id_expediente);
    }

    /**
     * @param expedienteDao the expedienteDao to set
     */
    public void setExpedienteDao(ExpedienteDao expedienteDao) {
        this.expedienteDao = expedienteDao;
    }

    /**
     * @param arbolService the arbolService to set
     */
    public void setArbolService(ArbolService arbolService) {
        this.arbolService = arbolService;
    }

    public Integer countExpedientesByCriterio(CriterioExpediente criterioExpediente) {
        return this.expedienteDao.countExpedientesByPagina(criterioExpediente);
    }

    public List<Expediente> listExpedientesByPagina(CriterioExpediente criterioExpediente) {
        return this.expedienteDao.listExpedientesByPagina(criterioExpediente);
    }
}
