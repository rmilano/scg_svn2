package mx.gob.economia.scg.persistence;

import java.sql.SQLException;
import java.util.List;

import mx.gob.economia.scg.model.AsuntoCapturaActualizacion;
import mx.gob.economia.scg.model.Asunto;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

/**
 * 
 * @author valentin.gomez
 */
public class AsuntoCapturaActualizacionDaoImpl extends SqlMapClientTemplate
		implements AsuntoCapturaActualizacionDao {
	public List<AsuntoCapturaActualizacion> listByIdAsunto(Integer id_asunto) {
		List<AsuntoCapturaActualizacion> asuntoCapturaActualizacion = this
				.queryForList("AsuntoCapturaActualizacion.listByIdAsunto",
						id_asunto);
		return asuntoCapturaActualizacion;
	}
        public List<AsuntoCapturaActualizacion> listIniciadosByIdAsunto(Integer id_asunto) {
		List<AsuntoCapturaActualizacion> asuntoCapturaActualizacion = this
				.queryForList("AsuntoCapturaActualizacion.listIniciadosByIdAsunto",
						id_asunto);
		return asuntoCapturaActualizacion;
	}

        public List<AsuntoCapturaActualizacion> listModificadosByIdAsunto(Integer id_asunto) {
		List<AsuntoCapturaActualizacion> asuntoCapturaActualizacion = this
				.queryForList("AsuntoCapturaActualizacion.listModificadosByIdAsunto",
						id_asunto);
		return asuntoCapturaActualizacion;
	}

	public void saveAsuntoCapturaActualizacion(
			AsuntoCapturaActualizacion asuntoCapturaActualizacion) {
		insert("AsuntoCapturaActualizacion.insert", asuntoCapturaActualizacion);
	}
        public void updateAsuntoCapturaActualizacion(Asunto asunto){
            update("AsuntoCapturaActualizacion.updateEstatusAsuntoCapAct", asunto);
        } 
}
