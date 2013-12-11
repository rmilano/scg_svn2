package mx.gob.economia.scg.service;

import java.util.List;
import mx.gob.economia.scg.model.Prioridad;
import mx.gob.economia.scg.persistence.PrioridadDao;

/**
 * 
 * @author valentin.gomez
 */
public class PrioridadServiceImpl implements PrioridadService {
	private PrioridadDao prioridadDao;

	public Prioridad getPrioridad(Integer id_prioridad) {
		return prioridadDao.getPrioridad(id_prioridad);
	}

	public List<Prioridad> listPrioridades() {
		return prioridadDao.listPrioridades();
	}

	public Integer savePrioridad(Prioridad prioridad) {
		return prioridadDao.savePrioridad(prioridad);
	}

	public void updatePrioridad(Prioridad prioridad) {
		prioridadDao.updatePrioridad(prioridad);
	}

	public void deletePrioridad(Integer id_prioridad) {
		prioridadDao.deletePrioridad(id_prioridad);
	}

	/**
	 * @param prioridadDao the prioridadDao to set
	 */
	public void setPrioridadDao(PrioridadDao prioridadDao) {
		this.prioridadDao = prioridadDao;
	}
	
}
