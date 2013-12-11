package mx.gob.economia.scg.persistence;

import java.util.List;

import mx.gob.economia.scg.model.TipoDocumento;

/**
 * Interface DocumentoDao
 * 
 * @author valentin.gomez
 * 
 */
public interface DocumentoDao {

	public List<TipoDocumento> listTipoDocumento();
	
}
