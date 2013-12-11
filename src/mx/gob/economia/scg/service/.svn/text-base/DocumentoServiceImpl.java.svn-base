package mx.gob.economia.scg.service;

import java.sql.Blob;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import mx.gob.economia.scg.model.TipoDocumento;
import mx.gob.economia.scg.persistence.DocumentoBlobDao;
import mx.gob.economia.scg.persistence.DocumentoDao;
import mx.gob.economia.scg.util.Constantes;

/**
 * Implementation DocumentoService
 * 
 * @author valentin.gomez
 * 
 */
public class DocumentoServiceImpl implements DocumentoService {

	private DocumentoDao documentoDao;

	private DocumentoBlobDao documentoBlobDao;

	public void loadTiposDocumento(HttpServletRequest request) {
		if (request.getSession().getAttribute(Constantes.TIPOS_DOCUMENTO) == null) {
			request.getSession().setAttribute(Constantes.TIPOS_DOCUMENTO,
					this.listTipoDocumento());
		}
	}

	public List<TipoDocumento> listTipoDocumento() {
		return documentoDao.listTipoDocumento();
	}
	
	public byte[] getBlobByIdDocumento(Integer id_documento){
		return documentoBlobDao.getBlobByIdDocumento(id_documento);
	}

        public byte[] getBlobAtenParByIdDocumento(Integer idAsuntoDetalle,Integer id_documento){
		return documentoBlobDao.getBlobAtenParByIdDocumento(idAsuntoDetalle,id_documento);
	}

	/**
	 * @param documentoDao
	 *            the documentoDao to set
	 */
	public void setDocumentoDao(DocumentoDao documentoDao) {
		this.documentoDao = documentoDao;
	}

	/**
	 * @param documentoBlobDao
	 *            the documentoBlobDao to set
	 */
	public void setDocumentoBlobDao(DocumentoBlobDao documentoBlobDao) {
		this.documentoBlobDao = documentoBlobDao;
	}

}
