package mx.gob.economia.scg.persistence;

import mx.gob.economia.scg.model.DocumentoBlob;
import mx.gob.economia.scg.model.Documento;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

/**
 * Implementation DocumentoBlobDao
 * 
 * @author valentin.gomez
 * 
 */
public class DocumentoBlobDaoImpl extends SqlMapClientTemplate implements
		DocumentoBlobDao {

	public byte[] getBlobByIdDocumento(Integer id_documento) {
		DocumentoBlob documentoBlob = (DocumentoBlob) queryForObject("DocumentoBlob.getBlobByIdDocumento", id_documento);
		return documentoBlob.getBlob();
	}

	public void saveDocumentoBlob(DocumentoBlob documentoBlob) {
		insert("DocumentoBlob.insert", documentoBlob);
	}
	
	public void deleteDocumentoBlobByIdAsunto(Integer id_asunto) {
		delete("DocumentoBlob.deleteByIdAsunto", id_asunto);
	}
        public byte[] getBlobAtenParByIdDocumento(Integer idAsuntoDetalle, Integer id_documento) {
                Documento docParam = new Documento();
                docParam.setId_documento(id_documento);
                docParam.setId_asunto_detalle(idAsuntoDetalle);
		DocumentoBlob documentoBlob = (DocumentoBlob) queryForObject("DocumentoBlob.getBlobAtenParByIdDocumento", docParam);
		return documentoBlob.getBlob();
	}
}
