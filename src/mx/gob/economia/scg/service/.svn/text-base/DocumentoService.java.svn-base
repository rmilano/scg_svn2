package mx.gob.economia.scg.service;

import java.sql.Blob;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import mx.gob.economia.scg.model.TipoDocumento;


/**
 * Interface EmpleadoService
 * 
 * @author valentin.gomez
 * 
 */
public interface DocumentoService {
	
	public void loadTiposDocumento(HttpServletRequest request);
	
	public List<TipoDocumento> listTipoDocumento();
	
	public byte[] getBlobByIdDocumento(Integer id_documento);

        public byte[] getBlobAtenParByIdDocumento(Integer idAsuntoDetalle,Integer id_documento);

}
