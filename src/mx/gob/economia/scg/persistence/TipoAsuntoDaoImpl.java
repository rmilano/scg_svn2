/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.economia.scg.persistence;

import java.util.List;
import mx.gob.economia.scg.model.TipoAsunto;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

/**
 *
 * @author roque
 */
public class TipoAsuntoDaoImpl extends SqlMapClientTemplate implements TipoAsuntoDao
{

    /**
     * Obtiene el bean de tipo de asunto
     * @param idTipoAsunto
     * @return 
     */
    public TipoAsunto getTipoAsunto(Integer idTipoAsunto)
    {
        return (TipoAsunto) queryForObject("TipoAsunto.getTipoAsunto", idTipoAsunto);
    }

    /**
     * Lista el catalogo de tipo de asuntos
     * @return 
     */
    public List<TipoAsunto> listarTiposAsunto()
    {
        return (List<TipoAsunto>) queryForList("TipoAsunto.listTiposDocumento");
    }

    /**
     * Guarda el bean tipo de asunto
     * @param tipoAsunto
     * @return 
     */
    public Integer saveTipoAsunto(TipoAsunto tipoAsunto)
    {
        	return (Integer) insert("TipoAsunto.insert", tipoAsunto);
    }

    /**
     * Actualiza el tipo de asunto
     * @param tipoAsunto 
     */
    public void updateTipoAsunto(TipoAsunto tipoAsunto)
    {
        update("TipoAsunto.update", tipoAsunto);
    }

    /**
     * Borra el tipo de asunto
     * @param idTipoAsunto 
     */
    public void deleteTipoAsunto(Integer idTipoAsunto)
    {
        delete("TipoAsunto.delete", idTipoAsunto);
    }
}
