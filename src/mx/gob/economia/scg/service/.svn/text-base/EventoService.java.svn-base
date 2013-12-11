package mx.gob.economia.scg.service;

import java.util.List;
import mx.gob.economia.scg.model.CriterioEvento;

import mx.gob.economia.scg.model.Evento;

/**
 *
 * @author valentin.gomez
 */
public interface EventoService
{

    public Evento getEvento(Integer id_evento);

    public List<Evento> getEventosByCriterio(CriterioEvento criterioEvento);

    public Integer countEventoByCriterio(CriterioEvento criterioEvento);

    public List<Evento> listEventos(Integer id_area);

    public Integer saveEvento(Evento evento);

    public void updateEvento(Evento evento);

    public void deleteEvento(Integer id_evento);
}
