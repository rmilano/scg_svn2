/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.gob.economia.scg.persistence;

import java.util.ArrayList;
import java.util.List;
import mx.gob.economia.scg.model.TipoDocumento;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

/**
 * 
 * @author rodrigo
 */
public class TipoDocumentoDaoImpl extends SqlMapClientTemplate implements
		TipoDocumentoDao {

	public TipoDocumento getTipoDocumento(Integer id_tipo_documento) {
		return (TipoDocumento) queryForObject("TipoDocumento.getTipoDocumento",
				id_tipo_documento);
	}

	public List<TipoDocumento> listarTiposDocumento() {
		return (List<TipoDocumento>) queryForList("TipoDocumento.listTiposDocumento");
	}

	public Integer saveTipoDocumento(TipoDocumento tipoDocumento) {
		return (Integer) insert("TipoDocumento.insert", tipoDocumento);
	}

	public void updateTipoDocumento(TipoDocumento tipoDocumento) {
		update("TipoDocumento.update", tipoDocumento);
	}

	public void deleteTipoDocumento(Integer id_tipo_documento) {
		delete("TipoDocumento.delete", id_tipo_documento);
	}

	public boolean isUsado(Integer id_tipo_documento) {
		ArrayList<TipoDocumento> a = (ArrayList<TipoDocumento>) queryForList(
				"TipoDocumento.listUsado", id_tipo_documento);
		return !a.isEmpty();
	}

}
