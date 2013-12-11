package mx.gob.economia.scg.persistence;

import java.util.List;

import mx.gob.economia.scg.model.Prioridad;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

/**
 * 
 * @author valentin.gomez
 */
public class PrioridadDaoImpl extends SqlMapClientTemplate implements
		PrioridadDao {

	public Prioridad getPrioridad(Integer id_prioridad) {
		return (Prioridad) queryForObject("Prioridad.getPrioridad",
				id_prioridad);
	}

	public List<Prioridad> listPrioridades() {
		return (List<Prioridad>) queryForList("Prioridad.listPrioridades");
	}

	public Integer savePrioridad(Prioridad prioridad) {
		return (Integer) insert("Prioridad.insert", prioridad);
	}

	public void updatePrioridad(Prioridad prioridad) {
		update("Prioridad.update", prioridad);
	}

	public void deletePrioridad(Integer id_prioridad) {
		delete("Prioridad.delete", id_prioridad);
	}
}
