package mx.gob.economia.scg.service;

import java.util.List;
import mx.gob.economia.scg.model.CriterioExpediente;

import mx.gob.economia.scg.model.Expediente;

/**
 * 
 * @author valentin.gomez
 */
public interface ExpedienteService {

	Expediente getExpediente(Integer id_expediente);

	List<Expediente> listExpedientes(Integer id_area);

	Integer saveExpediente(Expediente expediente);

	void updateExpediente(Expediente expediente);

	void deleteExpediente(Integer id_expediente);
            
        Integer countExpedientesByCriterio(CriterioExpediente criterioExpediente);
        
        List<Expediente> listExpedientesByPagina(CriterioExpediente criterioExpediente);

}
