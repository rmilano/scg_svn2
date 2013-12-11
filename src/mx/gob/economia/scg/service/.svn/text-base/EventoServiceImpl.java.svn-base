package mx.gob.economia.scg.service;

import java.util.ArrayList;
import java.util.List;
import mx.gob.economia.scg.model.CriterioEvento;
import mx.gob.economia.scg.model.Evento;
import mx.gob.economia.scg.persistence.EventoDao;

/**
 *
 * @author valentin.gomez
 */
public class EventoServiceImpl implements EventoService
{

    private EventoDao eventoDao;
    private ArbolService arbolService;

    /**
     * Obtiene el evento por id
     * @param id_evento
     * @return
     */
    public Evento getEvento(Integer id_evento)
    {
        return eventoDao.getEvento(id_evento);
    }

    /**
     * El no. de registros por criterio
     * @param criterioEvento
     * @return
     */
    public Integer countEventoByCriterio(CriterioEvento criterioEvento)
    {
        return this.eventoDao.countEventosByCriterio(criterioEvento);
    }
    public List<Evento> getEventosByCriterio(CriterioEvento criterioEvento)
    {
        return this.eventoDao.getEventosByCriterio(criterioEvento);
    }
    /**
     *
     * @param id_area
     * @return
     */
    public List<Evento> listEventos(Integer id_area)
    {
        List<Evento> evens=eventoDao.listEventos(id_area);

        return evens!=null? evens: new ArrayList();
    }

    /**
     * Guardar el evento
     * @param evento
     * @return
     */
    public Integer saveEvento(Evento evento)
    {
        return eventoDao.saveEvento(evento);
    }

    /**
     * Actualizar evento
     * @param evento
     */
    public void updateEvento(Evento evento)
    {
        eventoDao.updateEvento(evento);
    }

    /**
     * Borrra evento
     * @param id_evento
     */
    public void deleteEvento(Integer id_evento)
    {
        eventoDao.deleteEvento(id_evento);
    }

    public void setEventoDao(EventoDao eventoDao)
    {
        this.eventoDao = eventoDao;
    }

    /**
     * @param arbolService the arbolService to set
     */
    public void setArbolService(ArbolService arbolService)
    {
        this.arbolService = arbolService;
    }
}
