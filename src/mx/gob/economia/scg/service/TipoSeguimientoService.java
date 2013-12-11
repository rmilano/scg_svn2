/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.economia.scg.service;

import java.util.List;
import mx.gob.economia.scg.model.TipoSeguimiento;

/**
 *
 * @author roque
 */
public interface TipoSeguimientoService
{

    public TipoSeguimiento getTipoSeguimiento(Integer id_TipoSeguimiento);

    public List<TipoSeguimiento> listTipoSeguimientos();

    public Integer saveTipoSeguimiento(TipoSeguimiento TipoSeguimiento);

    public void updateTipoSeguimiento(TipoSeguimiento TipoSeguimiento);

    public void deleteTipoSeguimiento(Integer id_TipoSeguimiento);
}
