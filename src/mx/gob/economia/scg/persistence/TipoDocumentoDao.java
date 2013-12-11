/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.gob.economia.scg.persistence;

import mx.gob.economia.scg.model.TipoDocumento;
import java.util.List;
/**
 *
 * @author rodrigo
 */
public interface TipoDocumentoDao {
    public TipoDocumento getTipoDocumento(Integer id_tipo_documento);

    public List<TipoDocumento> listarTiposDocumento();

    public Integer saveTipoDocumento(TipoDocumento tipoDocumento);

    public void updateTipoDocumento(TipoDocumento tipoDocumento);

    public void deleteTipoDocumento(Integer id_tipo_documento);

    public boolean isUsado(Integer id_tipo_documento);
}
