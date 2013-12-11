/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.gob.economia.scg.service;

import java.util.List;
import mx.gob.economia.scg.model.TipoDocumento;

/**
 *
 * @author rodrigo
 */
public interface TipoDocumentoService {

    public TipoDocumento getTipoDocumento(Integer id_tipo_documento);

    public List<TipoDocumento> listTipoDocumentos();

    public Integer saveTipoDocumento(TipoDocumento tipoDocumento);

    public void updateTipoDocumento(TipoDocumento tipoDocumento);

    public void deleteTipoDocumento(Integer id_tipo_documento);

    public boolean isUsado(Integer id_tipo_documento);
}
