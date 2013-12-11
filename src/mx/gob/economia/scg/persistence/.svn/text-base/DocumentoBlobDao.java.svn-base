package mx.gob.economia.scg.persistence;

import mx.gob.economia.scg.model.DocumentoBlob;
import mx.gob.economia.scg.model.Documento;

/**
 * Interface DocumentoBlobDao
 * 
 * @author valentin.gomez
 * 
 */
public interface DocumentoBlobDao {

	public byte[] getBlobByIdDocumento(Integer id_documento);
	
	public void saveDocumentoBlob(DocumentoBlob documentoBlob);
	
	public void deleteDocumentoBlobByIdAsunto(Integer id_asunto);

        public byte[] getBlobAtenParByIdDocumento(Integer idAsuntoDetalle, Integer id_documento);
}
