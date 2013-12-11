/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.economia.scg.persistence;

import java.util.List;
import mx.gob.economia.scg.model.TipoSeguimiento;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

/**
 *
 * @author roque
 */
public class TipoSeguimientoDaoImpl  extends SqlMapClientTemplate  implements TipoSeguimientoDao
{

    /**
     * Obtiene el tipo de seguimiento por id
     * @param idTipoSeguimiento
     * @return
     */
    public TipoSeguimiento getTipoSeguimiento(Integer idTipoSeguimiento)
    {
        return (TipoSeguimiento) queryForObject("TipoSeguimiento.getTipoSeguimientoById", idTipoSeguimiento);
    }

    /**
     * Lista los tipos de seguimiento
     * @return
     */
    public List<TipoSeguimiento> listTipoSeguimientos()
    {
        return (List<TipoSeguimiento>) queryForList("TipoSeguimiento.listTipoSeguimiento");
    }

    public Integer saveTipoSeguimiento(TipoSeguimiento tipoSeguimiento)
    {
        return (Integer)insert("TipoSeguimiento.insert", tipoSeguimiento);
    }

    /**
     * Actualiza los tipos de seguimiento
     * @param tipoSeguimiento
     */
    public void updateTipoSeguimiento(TipoSeguimiento tipoSeguimiento)
    {
        update("TipoSeguimiento.update", tipoSeguimiento);
    }

    /**
     * Borra el tipo de seguimiento
     * @param id_TipoSeguimiento
     */
    public void deleteTipoSeguimiento(Integer id_TipoSeguimiento)
    {
        delete("TipoSeguimiento.delete", id_TipoSeguimiento);
    }
}
