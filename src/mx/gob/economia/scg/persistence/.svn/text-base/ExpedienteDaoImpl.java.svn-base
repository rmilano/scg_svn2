package mx.gob.economia.scg.persistence;

import java.util.List;
import mx.gob.economia.scg.model.CriterioExpediente;

import mx.gob.economia.scg.model.Expediente;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

/**
 * 
 * @author valentin.gomez
 */
public class ExpedienteDaoImpl extends SqlMapClientTemplate implements
		ExpedienteDao {

	public Expediente getExpediente(Integer id_expediente) {
		return (Expediente) queryForObject("Expediente.getExpediente",
				id_expediente);
	}

	public List<Expediente> listExpedientes(Integer id_area) {
           List<Expediente> expedientes;
           expedientes = queryForList("Expediente.listExpedientes", id_area);
	   return expedientes;
	}

	public Integer saveExpediente(Expediente expediente) {
		return (Integer) insert("Expediente.insert", expediente);
	}

	public void updateExpediente(Expediente expediente) {
		update("Expediente.update", expediente);
	}

	public void deleteExpediente(Integer id_expediente) {
		delete("Expediente.delete", id_expediente);
	}

    public Integer countExpedientesByPagina(CriterioExpediente criterioExpediente) {
        return (Integer) queryForObject("Expediente.countExpedientesByCriterio",
				criterioExpediente);
    }

    public List<Expediente> listExpedientesByPagina(CriterioExpediente criterioExpediente) {
        
        return (List<Expediente>) queryForList("Expediente.listExpedientesByPagina",
				criterioExpediente);
    }
}
