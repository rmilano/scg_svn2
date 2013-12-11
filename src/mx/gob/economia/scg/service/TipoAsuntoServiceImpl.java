/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.economia.scg.service;

import java.util.List;
import mx.gob.economia.scg.model.TipoAsunto;
import mx.gob.economia.scg.persistence.TipoAsuntoDao;

/**
 *
 * @author roque
 */
public class TipoAsuntoServiceImpl implements TipoAsuntoService
{
    private TipoAsuntoDao tipoAsuntoDao;
    
    /**
     * Obtiene el tipo de asunto por id
     * @param idTipoAsunto
     * @return 
     */
    public TipoAsunto getTipoAsunto(Integer idTipoAsunto)
    {
        return this.tipoAsuntoDao.getTipoAsunto(idTipoAsunto);
    }

    /**
     * Lita el catalogo de asuntos
     * @return 
     */
    public List<TipoAsunto> listTipoAsuntos()
    {
        return this.tipoAsuntoDao.listarTiposAsunto();
    }

    /**
     * Guarda en base el bean de tipo de asunto
     * @param tipoAsunto
     * @return 
     */
    public Integer saveTipoAsunto(TipoAsunto tipoAsunto)
    {
        return this.tipoAsuntoDao.saveTipoAsunto(tipoAsunto);
    }

    /**
     * Actualiza el base de datos el tipo de asunto
     * @param tipoAsunto 
     */
    public void updateTipoAsunto(TipoAsunto tipoAsunto)
    {
        this.tipoAsuntoDao.updateTipoAsunto(tipoAsunto);
    }

    /**
     * Borra el asunto por id
     * @param idTipoAsunto 
     */
    public void deleteTipoAsunto(Integer idTipoAsunto)
    {
        this.tipoAsuntoDao.deleteTipoAsunto(idTipoAsunto);
    }

    /**
     * @param tipoAsuntoDao the tipoAsuntoDao to set
     */
    public void setTipoAsuntoDao(TipoAsuntoDao tipoAsuntoDao)
    {
        this.tipoAsuntoDao = tipoAsuntoDao;
    }

}
