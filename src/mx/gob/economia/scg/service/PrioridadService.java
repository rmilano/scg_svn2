package mx.gob.economia.scg.service;

import java.util.List;

import mx.gob.economia.scg.model.Prioridad;

/**
 *
 * @author valentin.gomez
 */
public interface PrioridadService {

    public Prioridad getPrioridad(Integer id_prioridad);

    public List<Prioridad> listPrioridades();

    public Integer savePrioridad(Prioridad prioridad);

    public void updatePrioridad(Prioridad prioridad);

    public void deletePrioridad(Integer id_prioridad);
}
