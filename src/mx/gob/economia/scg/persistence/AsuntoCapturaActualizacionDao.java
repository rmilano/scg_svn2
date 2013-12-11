/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.gob.economia.scg.persistence;

import java.sql.SQLException;
import java.util.List;

import mx.gob.economia.scg.model.AsuntoCapturaActualizacion;
import mx.gob.economia.scg.model.Asunto;

/**
 *
 * @author valentin.gomez
 */
public interface AsuntoCapturaActualizacionDao
{
    public List<AsuntoCapturaActualizacion> listByIdAsunto(Integer id_asunto);
    public List<AsuntoCapturaActualizacion> listIniciadosByIdAsunto(Integer id_asunto);
    public List<AsuntoCapturaActualizacion> listModificadosByIdAsunto(Integer id_asunto);
    public void saveAsuntoCapturaActualizacion(AsuntoCapturaActualizacion asuntoCapturaActualizacion);
    public void updateAsuntoCapturaActualizacion(Asunto asunto);
}
