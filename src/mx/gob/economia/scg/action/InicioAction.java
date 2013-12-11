package mx.gob.economia.scg.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.economia.scg.form.InicioForm;
import mx.gob.economia.scg.model.CriterioAsunto;
import mx.gob.economia.scg.model.Empleado;
import mx.gob.economia.scg.model.Rol;
import mx.gob.economia.scg.persistence.RolDoctoDao;
import mx.gob.economia.scg.service.ArbolService;
import mx.gob.economia.scg.service.AsuntoRevisionService;
import mx.gob.economia.scg.service.AsuntoService;
import mx.gob.economia.scg.service.ConsultaConfidencialService;
import mx.gob.economia.scg.service.EmpleadoService;
import mx.gob.economia.scg.util.Constantes;
import org.apache.log4j.Logger;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InicioAction extends Action {

	EmpleadoService empleadoService;
	ArbolService arbolService;
	private AsuntoService asuntoService;
	private AsuntoRevisionService asuntoRevisionService;
	private ConsultaConfidencialService consultaConfidencialService;
	RolDoctoDao rolDoctoDao;
        protected static Logger log = Logger.getLogger("Log");

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		InicioForm iF = (InicioForm) form;
		String destino = "ERROR";
		try {
			
			if (request.getSession().getAttribute(Constantes.USUARIO_SESION) == null) {
				// Recupera el usuario autenticado y lo sube a sesi?n
				Empleado usuario = empleadoService.getEmpleadoAutenticado();
				request.getSession().setAttribute(Constantes.USUARIO_SESION,
						usuario);
                                Logger log = Logger.getLogger(this.getClass());
                                log.error("El usuario en sesion: " + usuario.getId_empleado() + " " + usuario.getNombre_completo());
				
				boolean isCapturista = usuario.getRoles().contains(new Rol(Rol.CAPTURISTA)); 
				request.getSession().setAttribute("isCapturista", isCapturista);

				boolean isRecepcionista = usuario.getRoles().contains(new Rol(Rol.RECEPCIONISTA));
				request.getSession().setAttribute("isRecepcionista", isRecepcionista);

				boolean isAdministrador = usuario.getRoles().contains(new Rol(Rol.ADMINISTRADOR));
				request.getSession().setAttribute("isAdministrador", isAdministrador);

				boolean isRevisor = usuario.getRoles().contains(new Rol(Rol.REVISOR));
				request.getSession().setAttribute("isRevisor", isRevisor);
                                
				boolean isReporteDetalle = usuario.getRoles().contains(new Rol(Rol.REPORTEDETALLE));
				request.getSession().setAttribute("isReporteDetalle", isReporteDetalle);

                                boolean isCapturaExternos = usuario.getRoles().contains(new Rol(Rol.CAPTURAEXTERNOS));
				request.getSession().setAttribute("isCapturaExternos", isCapturaExternos);
			}
			arbolService.loadArboles(request);

                        CriterioAsunto criterioAsunto = new CriterioAsunto();
                        criterioAsunto.setBusqueda_modificado(1);
                        Empleado empleado_ses = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);
                        criterioAsunto.setId_empleado_dest(empleado_ses.getId_empleado() + "");
                        criterioAsunto.setConfidencial(null); //
                        iF.setNumModificados(asuntoService.countAsuntosIniciales(criterioAsunto));
                        /*
                        //los asuntos por revisar
                        iF.setNumPorRevisar(this.asuntoRevisionService.countAsuntosPorRevisar(request));

                        //los asuntos que fueron revisados
                        iF.setNumRevisados(this.asuntoRevisionService.countAsuntosRevisados(request));

                        //los asuntos por supervisar
                        iF.setNumPorSupervisar(this.asuntoRevisionService.countAsuntosPorSupervisar(request));

                        //los asuntos supervisados
                        iF.setNumSupervisados(this.asuntoRevisionService.countAsuntosSupervisados(request));

                        //El numero de asuntos confidenciales
                        iF.setNumConfidenciales(this.consultaConfidencialService.countAsuntosConfidenciales(new CriterioAsunto(), request));

			// Cuenta el numero de asuntos en recepcion
			CriterioAsunto criterioAsunto = new CriterioAsunto();
			Integer numEnRecepcion = asuntoService.countAsuntosEnRecepcion(criterioAsunto, request);
			iF.setNumEnRecepcion(numEnRecepcion);
			
			
			// Cuenta el numero de asuntos capturados
			criterioAsunto = new CriterioAsunto();
			Integer numCapturados = asuntoService.countAsuntosEnCaptura(criterioAsunto, request);
			iF.setNumCapturados(numCapturados);
			
			// Cuenta el numero de asuntos turnados
			criterioAsunto = new CriterioAsunto();
			Integer numTurnados = asuntoService.countAsuntosTurnados(criterioAsunto, request);
			iF.setNumTurnados(numTurnados);
			
			// Cuenta el numero de asuntos atendidos
			criterioAsunto = new CriterioAsunto();
			Integer numAtendidos = asuntoService.countAsuntosAtendidos(criterioAsunto, request);
			iF.setNumAtendidos(numAtendidos);
			
			// Cuenta el numero de asuntos por atender: Los que han sido turnados 
			Integer numPorAtender = asuntoService.countAsuntosPendientes(request);
			iF.setNumPorAtender(numPorAtender);

			// Cuenta el numero de asuntos para visto bueno
			Integer numParaVoBo = asuntoService.countAsuntosParaVoBo(request);
			iF.setNumParaVoBo(numParaVoBo);
			
			// Cuenta el numero de asuntos pendientes: Turnados + Pendientes + Atendidos(para visto bueno)
			Integer numPendientes = Constantes.FIRST + numParaVoBo + numPorAtender;						
			iF.setNumPendientes(numPendientes);
			
			// Cuenta el numero de asuntos Finalizados
			criterioAsunto = new CriterioAsunto();
			Integer numFinalizados = asuntoService.countAsuntosFinalizados(criterioAsunto, request);
			iF.setNumFinalizados(numFinalizados);
			
			// Cuenta el numero de asuntos ccp
			criterioAsunto = new CriterioAsunto();
			Integer numCcp = asuntoService.countAsuntosCcp(criterioAsunto, request);
			iF.setNumCcp(numCcp);
			
			// Cuenta el numero de asuntos archivados
			criterioAsunto = new CriterioAsunto();
			Integer numArchivados = asuntoService.countAsuntosArchivados(criterioAsunto, request);
			iF.setNumArchivados(numArchivados);
			*/
			destino = "SUCCESS";
		} catch (Exception e) {
			log.error("Ha ocurrido un error en la transaccion",e);
			request
					.setAttribute("mensaje",
							"Error interno. Por favor, int?ntelo otra vez en unos minutos.");
		}
		return mapping.findForward(destino);
	}

	/**
	 * @param arbolService
	 *            the arbolService to set
	 */
	public void setArbolService(ArbolService arbolService) {
		this.arbolService = arbolService;
	}

	/**
	 * @param empleadoService
	 *            the empleadoService to set
	 */
	public void setEmpleadoService(EmpleadoService empleadoService) {
		this.empleadoService = empleadoService;
	}

	/**
	 * @param asuntoService
	 *            the asuntoService to set
	 */
	public void setAsuntoService(AsuntoService asuntoService) {
		this.asuntoService = asuntoService;
	}

	/**
	 * @param rolDoctoDao the rolDoctoDao to set
	 */
	public void setRolDoctoDao(RolDoctoDao rolDoctoDao) {
		this.rolDoctoDao = rolDoctoDao;
	}

    /**
     * @param asuntoRevisionService the asuntoRevisionService to set
     */
    public void setAsuntoRevisionService(AsuntoRevisionService asuntoRevisionService)
    {
        this.asuntoRevisionService = asuntoRevisionService;
    }

    /**
     * @param consultaConfidencialService the consultaConfidencialService to set
     */
    public void setConsultaConfidencialService(ConsultaConfidencialService consultaConfidencialService)
    {
        this.consultaConfidencialService = consultaConfidencialService;
    }
}
