/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.economia.scg.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import mx.gob.economia.scg.model.Asunto;
import mx.gob.economia.scg.model.AsuntoCapturaActualizacion;
import mx.gob.economia.scg.model.AsuntoDetalle;
import mx.gob.economia.scg.model.Documento;
import mx.gob.economia.scg.model.Empleado;
import mx.gob.economia.scg.persistence.AsuntoCapturaActualizacionDao;
import mx.gob.economia.scg.util.Constantes;

/**
 * 
 * @author valentin.gomez
 */
public class AsuntoCapturaActualizacionServiceImpl implements
		AsuntoCapturaActualizacionService {
	private AsuntoCapturaActualizacionDao asuntoCapturaActualizacionDao;

	public List<AsuntoCapturaActualizacion> listAsuntoCapturaActualizacionByIdAsunto(Integer id_asunto) {
		List<AsuntoCapturaActualizacion> listAsuntoCaptAct = asuntoCapturaActualizacionDao.listByIdAsunto(id_asunto);
		return listAsuntoCaptAct;
	}

        public List<AsuntoCapturaActualizacion> listAsuntoIniciadosActualizacionByIdAsunto(Integer id_asunto) {
		List<AsuntoCapturaActualizacion> listAsuntoCaptAct = asuntoCapturaActualizacionDao.listIniciadosByIdAsunto(id_asunto);
		return listAsuntoCaptAct;
	}

	public void saveAsuntoCapturaActualizacion(
			Asunto asunto, HttpServletRequest request) {
		
		Empleado empleado_act = (Empleado) request.getSession()
		.getAttribute(Constantes.USUARIO_SESION);
		
		AsuntoCapturaActualizacion asuntoCaptAct = new AsuntoCapturaActualizacion();
		asuntoCaptAct.setId_asunto(asunto.getId_asunto());
		asuntoCaptAct.setFh_actualizacion(new Date());
		asuntoCaptAct.setId_empleado_act(empleado_act.getId_empleado());
		asuntoCaptAct.setIp_act(request.getRemoteAddr());
		asuntoCaptAct.setAsunto(asunto.getAsunto());
		asuntoCaptAct.setDescripcion(asunto.getDescripcion());
		asuntoCaptAct.setNo_oficio(asunto.getNo_oficio());
		asuntoCaptAct.setFh_oficio(asunto.getFh_oficio());
		asuntoCaptAct.setId_empleado_capt(asunto.getEmpleado_capt().getId_empleado());
		asuntoCaptAct.setId_empleado_remi(asunto.getAsunto_detalle().getEmpleado_remi().getId_empleado());
		asuntoCaptAct.setId_empleado_dest(asunto.getAsunto_detalle().getEmpleado_dest().getId_empleado());
		asuntoCaptAct.setId_asunto_ref(asunto.getId_asunto_ref());
		asuntoCaptAct.setEstatus(asunto.getEstatus());
		asuntoCaptAct.setFh_recepcion(asunto.getFh_recepcion());
                if(asunto.getTema() != null)
                    asuntoCaptAct.setId_tema(asunto.getTema().getId_tema());
		asuntoCaptAct.setId_expediente(asunto.getExpediente().getId_expediente());
		asuntoCaptAct.setId_instruccion(asunto.getInstruccion().getId_instruccion());
		asuntoCaptAct.setFolio(asunto.getFolio());
		asuntoCaptAct.setId_asunto_padre(asunto.getId_asunto_padre());
		asuntoCaptAct.setFh_limite(asunto.getAsunto_detalle().getFh_limite());
		asuntoCaptAct.setFh_registro(asunto.getAsunto_detalle().getFh_registro());
		asuntoCaptAct.setId_empleado_ini(asunto.getAsunto_detalle().getEmpleado_dest_ini().getId_empleado());
		asuntoCaptAct.setComentario(asunto.getAsunto_detalle().getComentario());
                asuntoCaptAct.setConfidencial(asunto.getConfidencial());
                asuntoCaptAct.setTipo_actualizacion(asunto.getTipo_actualizacion()); 

                //agregar confidencial

		asuntoCaptAct.setId_empleado_firma(-1);
		asuntoCaptAct.setFirma("");
		
		String ids_empleados_ccp = "";
		List<Empleado> empleados_ccp = asunto.getEmpleadosCcp();
		for (Empleado empleado_ccp : empleados_ccp) {
			ids_empleados_ccp  = ids_empleados_ccp + empleado_ccp.getId_empleado()+"|";
		}
		asuntoCaptAct.setIds_empleados_ccp(ids_empleados_ccp);

                String ids_empleados_detalle_ccp = "";
                for (AsuntoDetalle asuntoDetalle : asunto.getAsuntos_detalles()) {
                    for (Empleado empleados_detalle_ccp : asuntoDetalle.getEmpleadosCcpDetalle()) {
                           ids_empleados_detalle_ccp  = ids_empleados_detalle_ccp + empleados_detalle_ccp.getId_empleado()+"|";
                    }
                }
		asuntoCaptAct.setIds_detalle_empleados_ccp(ids_empleados_ccp);
		
		String documentos = "";
		List<Documento> documentosList = asunto.getAsunto_detalle().getDocumentos();
		for (Documento documento : documentosList) {
			documentos  = documentos + documento.getDocumento()+"|";
		}
		asuntoCaptAct.setDocumentos(documentos);
		asuntoCapturaActualizacionDao.saveAsuntoCapturaActualizacion(asuntoCaptAct);
	}

	/**
	 * @param asuntoCapturaActualizacionDao
	 *            the asuntoCapturaActualizacionDao to set
	 */
	public void setAsuntoCapturaActualizacionDao(
			AsuntoCapturaActualizacionDao asuntoCapturaActualizacionDao) {
		this.asuntoCapturaActualizacionDao = asuntoCapturaActualizacionDao;
	}
}
