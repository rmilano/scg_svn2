/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.economia.scg.model;

/**
 *
 * @author roque
 */
public class CriterioTema
{
    private Tema tema;
    private Paginador paginador;

    public CriterioTema()
    {
        this.tema = new Tema();
        this.paginador = new Paginador();
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
     * @return the paginador
     */
    public Paginador getPaginador()
    {
        return paginador;
    }

    /**
     * @param paginador the paginador to set
     */
    public void setPaginador(Paginador paginador)
    {
        this.paginador = paginador;
    }
}
