package mx.gob.economia.scg.persistence;

import java.util.List;

import mx.gob.economia.scg.model.TipoDocumento;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

/**
 * Implementation DocumentoDao
 * 
 * @author valentin.gomez
 * 
 */
public class DocumentoDaoImpl extends SqlMapClientTemplate implements
		DocumentoDao {

	
	public List<TipoDocumento> listTipoDocumento() {
		List<TipoDocumento> tiposDocumento = queryForList(
				"Documento.listTipoDocumento", null);
		return tiposDocumento;
	}
}
