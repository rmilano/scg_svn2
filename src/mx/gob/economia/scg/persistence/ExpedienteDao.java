package mx.gob.economia.scg.persistence;

import mx.gob.economia.scg.model.Expediente;
import java.util.List;
import mx.gob.economia.scg.model.CriterioExpediente;
/**
 *
 * @author valentin.gomez
 */
public interface ExpedienteDao {
   Expediente getExpediente(Integer id_expediente);

   List<Expediente> listExpedientes(Integer id_area);

   Integer saveExpediente(Expediente expediente);

   void updateExpediente(Expediente expediente);

   void deleteExpediente(Integer id_expediente);
   
   Integer countExpedientesByPagina(CriterioExpediente criterioExpediente);
    
   List<Expediente> listExpedientesByPagina(CriterioExpediente criterioExpediente);
}
