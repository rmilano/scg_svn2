/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.gob.economia.scg.persistence;

import mx.gob.economia.scg.model.Prioridad;
import java.util.List;

/**
 * 
 * @author valentin.gomez
 */
public interface PrioridadDao {
	public Prioridad getPrioridad(Integer id_prioridad);

	public List<Prioridad> listPrioridades();

	public Integer savePrioridad(Prioridad prioridad);

	public void updatePrioridad(Prioridad prioridad);

	public void deletePrioridad(Integer id_prioridad);

}
