/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.economia.scg.form;

import java.util.ArrayList;
import java.util.List;
import mx.gob.economia.scg.model.CriterioTema;
import mx.gob.economia.scg.model.Tema;
import org.apache.struts.action.ActionForm;

/**
 *
 * @author roque
 */
public class TemaForm extends ActionForm
{
    private Tema tema;
    private Tema subtema;
    private List<Tema> temas;
    private List<Tema> subTemas;
    private CriterioTema criterioTema;

    public TemaForm()
    {
        this.tema = new Tema();
        this.criterioTema = new CriterioTema();
        this.temas = new ArrayList<Tema>();
        this.subtema=new Tema();
    }

    /**
     * @return the tema
     */
    public Tema getTema()
    {
        return tema;
    }

    /**
     * @param tema the tema to set
     */
    public void setTema(Tema tema)
    {
        this.tema = tema;
    }

    /**
     * @return the criterioTema
     */
    public CriterioTema getCriterioTema()
    {
        return criterioTema;
    }

    /**
     * @param criterioTema the criterioTema to set
     */
    public void setCriterioTema(CriterioTema criterioTema)
    {
        this.criterioTema = criterioTema;
    }

    /**
     * @return the temas
     */
    public List<Tema> getTemas()
    {
        return temas;
    }

    /**
     * @param temas the temas to set
     */
    public void setTemas(List<Tema> temas)
    {
        this.temas = temas;
    }

    /**
     * @return the subTemas
     */
    public List<Tema> getSubTemas()
    {
        return subTemas;
    }

    /**
     * @param subTemas the subTemas to set
     */
    public void setSubTemas(List<Tema> subTemas)
    {
        this.subTemas = subTemas;
    }

    /**
     * @return the subtema
     */
    public Tema getSubtema() {
        return subtema;
    }

    /**
     * @param subtema the subtema to set
     */
    public void setSubtema(Tema subtema) {
        this.subtema = subtema;
    }
}
