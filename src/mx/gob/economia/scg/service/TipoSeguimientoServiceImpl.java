/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.economia.scg.service;

import java.util.List;
import mx.gob.economia.scg.model.TipoSeguimiento;
import mx.gob.economia.scg.persistence.TipoSeguimientoDao;

/**
 *
 * @author roque
 */
public class TipoSeguimientoServiceImpl implements TipoSeguimientoService
{
    private TipoSeguimientoDao tipoSeguimientoDao;

    /**
     * Obtiene el tipo de seguimiento por id
     * @param id_TipoSeguimiento
     * @return
     */
    public TipoSeguimiento getTipoSeguimiento(Integer id_TipoSeguimiento)
    {
        TipoSeguimiento tipoSeguimiento = tipoSeguimientoDao.getTipoSeguimiento(id_TipoSeguimiento);
        tipoSeguimiento = tipoSeguimiento == null ? new TipoSeguimiento() : tipoSeguimiento;
        return tipoSeguimiento;
    }

    /**
     * Lista los tipos de seguimiento
     * @return
     */
    public List<TipoSeguimiento> listTipoSeguimientos()
    {
        return tipoSeguimientoDao.listTipoSeguimientos();
    }

    /**
     * Guarda el tipo de seguimiento
     * @param TipoSeguimiento
     * @return
     */
    public Integer saveTipoSeguimiento(TipoSeguimiento TipoSeguimiento)
    {
        return tipoSeguimientoDao.saveTipoSeguimiento(TipoSeguimiento);
    }

    /**
     * Actualiza el tipo de seguimiento
     * @param TipoSeguimiento
     */
    public void updateTipoSeguimiento(TipoSeguimiento TipoSeguimiento)
    {
        tipoSeguimientoDao.updateTipoSeguimiento(TipoSeguimiento);
    }

    /**
     * Borra el tipo de seguimiento
     * @param id_TipoSeguimiento
     */
    public void deleteTipoSeguimiento(Integer id_TipoSeguimiento)
    {
        tipoSeguimientoDao.deleteTipoSeguimiento(id_TipoSeguimiento);
    }

    /**
     * @param tipoSeguimientoDao the tipoSeguimientoDao to set
     */
    public void setTipoSeguimientoDao(TipoSeguimientoDao tipoSeguimientoDao)
    {
        this.tipoSeguimientoDao = tipoSeguimientoDao;
    }
}
