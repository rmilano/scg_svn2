/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.gob.economia.scg.service;

import java.util.List;
import mx.gob.economia.scg.model.TipoDocumento;
import mx.gob.economia.scg.persistence.TipoDocumentoDao;
import org.acegisecurity.context.SecurityContextHolder;

/**
 * 
 * @author rodrigo
 */
public class TipoDocumentoServiceImpl implements TipoDocumentoService {
	private TipoDocumentoDao tipoDocumentoDao;

	public TipoDocumento getTipoDocumento(Integer id_tipo_documento) {
		return tipoDocumentoDao.getTipoDocumento(id_tipo_documento);
	}

	public List<TipoDocumento> listTipoDocumentos() {
		return tipoDocumentoDao.listarTiposDocumento();
	}

	public Integer saveTipoDocumento(TipoDocumento tipoDocumento) {
		return tipoDocumentoDao.saveTipoDocumento(tipoDocumento);
	}

	public void updateTipoDocumento(TipoDocumento tipoDocumento) {
		tipoDocumentoDao.updateTipoDocumento(tipoDocumento);
	}

	public void deleteTipoDocumento(Integer id_tipo_documento) {
		tipoDocumentoDao.deleteTipoDocumento(id_tipo_documento);
	}

	/**
	 * @param tipoDocumentoDao
	 *            the tipoDocumentoDao to set
	 */
	public void setTipoDocumentoDao(TipoDocumentoDao tipoDocumentoDao) {
		this.tipoDocumentoDao = tipoDocumentoDao;
	}

	public boolean isUsado(Integer id_tipo_documento) {
		return tipoDocumentoDao.isUsado(id_tipo_documento);
	}
}
