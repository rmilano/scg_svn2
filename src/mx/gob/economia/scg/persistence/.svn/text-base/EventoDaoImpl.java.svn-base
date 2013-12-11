package mx.gob.economia.scg.persistence;

import java.util.List;
import mx.gob.economia.scg.model.CriterioEvento;

import mx.gob.economia.scg.model.Evento;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

/**
 *
 * @author valentin.gomez
 */
public class EventoDaoImpl extends SqlMapClientTemplate implements EventoDao
{

    /**
     * Obtiene el evento por id
     * @param id_evento
     * @return
     */
    public Evento getEvento(Integer id_evento)
    {
        return (Evento) queryForObject("Evento.getEvento",id_evento);
    }

    /**
     * Obtiene los eventos por criterio
     * @param criterioEvento
     * @return
     */
    public List<Evento> getEventosByCriterio(CriterioEvento criterioEvento)
    {
        return(List<Evento>) queryForList("Evento.listEventosCriterio", criterioEvento);
    }
    /**
     * Obtiene el no. de registros por criterio
     * @param criterioEvento
     * @return
     */
    public Integer countEventosByCriterio(CriterioEvento criterioEvento)
    {
        return (Integer ) queryForObject("Evento.countEventosCriterio", criterioEvento);
    }
    /**
     * Listado de eventos
     * @param
     * @return
     */
    public List<Evento> listEventos (Integer id_area)
    {
        return (List<Evento>) queryForList("Evento.listEventos", id_area);
    }


    /**
     * Guardar evento
     * @param evento
     * @return
     */
    public Integer saveEvento(Evento evento)
    {
        return (Integer) insert("Evento.insert", evento);
    }

    /**
     * Actualizar evento
     * @param evento
     */
    public void updateEvento(Evento evento)
    {
        update("Evento.update", evento);
    }

    /**
     * Borrar evento
     * @param id_evento
     */
    public void deleteEvento(Integer id_evento)
    {
        delete("Evento.delete", id_evento);
    }
}
