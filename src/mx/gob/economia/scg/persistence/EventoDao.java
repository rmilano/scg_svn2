package mx.gob.economia.scg.persistence;

import mx.gob.economia.scg.model.Evento;
import java.util.List;
import mx.gob.economia.scg.model.CriterioEvento;

/**
 *
 * @author valentin.gomez
 */
public interface EventoDao
{

    public Evento getEvento(Integer id_evento);

    public List<Evento> getEventosByCriterio(CriterioEvento criterioEvento);

    public Integer countEventosByCriterio(CriterioEvento criterioEvento);

    public List<Evento> listEventos(Integer id_area);

    public Integer saveEvento(Evento evento);

    public void updateEvento(Evento evento);

    public void deleteEvento(Integer id_evento);
}
