/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.economia.scg.form;

import java.util.ArrayList;
import java.util.List;
import mx.gob.economia.scg.model.CriterioEvento;
import mx.gob.economia.scg.model.Evento;
import org.apache.struts.action.ActionForm;

/**
 *
 * @author roque
 */
public class EventoForm extends ActionForm
{
    private Evento evento;
    private List<Evento> eventos;
    private CriterioEvento criterioEvento;

    public EventoForm()
    {
        this.evento = new Evento();
        this.criterioEvento = new CriterioEvento();
        this.eventos = new ArrayList<Evento>();
    }

    /**
     * @return the evento
     */
    public Evento getEvento()
    {
        return evento;
    }

    /**
     * @param evento the evento to set
     */
    public void setEvento(Evento evento)
    {
        this.evento = evento;
    }

    /**
     * @return the criterioEvento
     */
    public CriterioEvento getCriterioEvento()
    {
        return criterioEvento;
    }

    /**
     * @param criterioEvento the criterioEvento to set
     */
    public void setCriterioEvento(CriterioEvento criterioEvento)
    {
        this.criterioEvento = criterioEvento;
    }

    /**
     * @return the eventos
     */
    public List<Evento> getEventos()
    {
        return eventos;
    }

    /**
     * @param eventos the eventos to set
     */
    public void setEventos(List<Evento> eventos)
    {
        this.eventos = eventos;
    }
}
