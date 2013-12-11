package mx.gob.economia.scg.model;

import java.io.Serializable;
import mx.gob.economia.scg.util.Constantes;

public class Paginador implements Serializable
{

    // Propiedades
    private Integer numRegistros;
    private Integer minimo;
    private Integer maximo;
    private Integer paginas;
    private Integer tamPagina;
    private Integer pagina;
    private Integer numMaxRegistros;
    private Integer numMaxRegistrosReporte;

    public Paginador()
    {
        this.numRegistros = 0;
        this.minimo = 0;
        this.maximo = 0;
        this.paginas = 0;
        this.tamPagina = 10;
        this.pagina = 1;
        this.numMaxRegistros = Constantes.NUM_MAX_REG;
        this.numMaxRegistrosReporte = Constantes.NUM_MAX_REG_REPORTE;
    }

    /**
     * @return the numRegistros
     */
    public Integer getNumRegistros()
    {
        return numRegistros;
    }

    /**
     * @param numRegistros
     *            the numRegistros to set
     */
    public void setNumRegistros(Integer numRegistros)
    {
        this.numRegistros = numRegistros;
    }

    /**
     * @return the minimo
     */
    public Integer getMinimo()
    {
        minimo = this.maximo - (this.tamPagina - 1);
        return minimo;
    }

    /**
     * @param minimo
     *            the minimo to set
     */
    public void setMinimo(Integer minimo)
    {
        this.minimo = minimo;
    }

    /**
     * @return the maximo
     */
    public Integer getMaximo()
    {
        maximo = this.tamPagina * this.pagina;
        return maximo;
    }

    /**
     * @param maximo
     *            the maximo to set
     */
    public void setMaximo(Integer maximo)
    {
        this.maximo = maximo;
    }

    /**
     * @return the paginas
     */
    public Integer getPaginas()
    {
        paginas = this.numRegistros % this.tamPagina == 0
                && numRegistros / tamPagina != 0 ? this.numRegistros
                / this.tamPagina : (this.numRegistros / this.tamPagina) + 1;
        return paginas;
    }

    /**
     * @param paginas
     *            the paginas to set
     */
    public void setPaginas(Integer paginas)
    {
        this.paginas = paginas;
    }

    /**
     * @return the tamPagina
     */
    public Integer getTamPagina()
    {
        return tamPagina;
    }

    /**
     * @param tamPagina
     *            the tamPagina to set
     */
    public void setTamPagina(Integer tamPagina)
    {
        this.tamPagina = tamPagina;
    }

    /**
     * @return the pagina
     */
    public Integer getPagina()
    {
        return pagina;
    }

    /**
     * @param pagina
     *            the pagina to set
     */
    public void setPagina(Integer pagina)
    {
        this.pagina = pagina;
    }

    /**
     * @return the numMaxRegistros
     */
    public Integer getNumMaxRegistros()
    {
        return numMaxRegistros;
    }

    /**
     * @param numMaxRegistros the numMaxRegistros to set
     */
    public void setNumMaxRegistros(Integer numMaxRegistros)
    {
        this.numMaxRegistros = numMaxRegistros;
    }

    /**
     * @return the numMaxRegistrosReporte
     */
    public Integer getNumMaxRegistrosReporte()
    {
        return numMaxRegistrosReporte;
    }

    /**
     * @param numMaxRegistrosReporte the numMaxRegistrosReporte to set
     */
    public void setNumMaxRegistrosReporte(Integer numMaxRegistrosReporte)
    {
        this.numMaxRegistrosReporte = numMaxRegistrosReporte;
    }
}
