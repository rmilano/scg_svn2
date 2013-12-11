package mx.gob.economia.scg.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import mx.gob.economia.scg.model.Area;
import mx.gob.economia.scg.model.Asunto;
import mx.gob.economia.scg.model.AsuntoDetalle;
import mx.gob.economia.scg.model.CriterioAsunto;
import mx.gob.economia.scg.model.CriterioReporteVolante;
import mx.gob.economia.scg.model.Documento;
import mx.gob.economia.scg.model.Empleado;
import mx.gob.economia.scg.model.Firma;
import mx.gob.economia.scg.model.FirmaAsunto;
import mx.gob.economia.scg.model.Prioridad;
import mx.gob.economia.scg.model.Rol;
import mx.gob.economia.scg.model.BitacoraAsunto;
import mx.gob.economia.scg.persistence.AsuntoDao;
import mx.gob.economia.scg.util.Constantes;
import mx.gob.economia.scg.util.Util;
import org.apache.log4j.Logger;
/**
 * Implementation AsuntoService
 *
 * @author valentin.gomez
 *
 */
public class AsuntoServiceImpl implements AsuntoService
{

    private AsuntoDao asuntoDao;
    private CorreoService correoService;
    private ArbolService arbolService;
    private EmpleadoService empleadoService;
    private PrioridadService prioridadService;
    private InstruccionService instruccionService;
    private TemaService temaService;
    private EventoService eventoService;
    private ExpedienteService expedienteService;
    private DocumentoService documentoService;
    private AsuntoCapturaActualizacionService asuntoCapturaActualizacionService;
    private AreaService areaService;
    private TipoDocumentoService tipoDocumentoService;
    protected static Logger log = Logger.getLogger("Log");

    /**
     * Obtiene el asunto por el id del asunto
     * @param id_asunto
     * @return 
     */
    public Asunto getAsunto(Integer id_asunto)
    {
        Asunto asunto = new Asunto();
        asunto = asuntoDao.getAsunto(id_asunto);
        if (asunto.getTema().getId_tema_padre()!=null && asunto.getTema().getId_tema_padre()> 0){
            asunto.setSubTema(asunto.getTema());
            asunto.setTema(this.temaService.getTema(asunto.getSubTema().getId_tema_padre()));
        }
        List<AsuntoDetalle> asuntos_detalle = asunto.getAsuntos_detalles();
        for (AsuntoDetalle asuntoDetalle : asuntos_detalle)
        {
            asuntoDetalle.getEmpleado_dest().setComentario(asuntoDetalle.getComentario());
            asuntoDetalle.setComentario("");
            asuntoDetalle.getEmpleado_dest().getInstruccion().setId_instruccion(asunto.getId_instruccion());
            List<Asunto> asuntos_asociados = asuntoDetalle.getAsuntos_asociados();
            if (asuntos_asociados != null && !asuntos_asociados.isEmpty())
            {
                Iterator<Asunto> it = asuntos_asociados.iterator();
                while (it.hasNext())
                {
                    Asunto asociado = (Asunto) it.next();
                    if (asociado.getId_asunto().equals(id_asunto))
                        it.remove();
                }
            }
        }
        //asignar los empleados CCP del asunto
        asunto.setEmpleadosCcp(asuntoDao.listCCpsAsunto(id_asunto));
        asunto.setActualizacionesCaptura(asuntoCapturaActualizacionService.listAsuntoIniciadosActualizacionByIdAsunto(id_asunto));
        //asignar el tipo de documento al asunto
        asunto.setTipoAsunto(this.tipoDocumentoService.getTipoDocumento(asunto.getTipoAsunto().getId_tipo_documento()));
        // Asigna la fecha limite de atencion del asunto
        if (asunto.getAsuntos_detalles().size() > 0)
            asunto.setFh_limite_asunto(asunto.getAsuntos_detalles().get(0).getFh_limite());
        return asunto;
    }

    /**
     * Obtiene el numero de los asuntos con detalles
     * @param criterioAsunto
     * @return 
     */
    public Integer countAsuntos(CriterioAsunto criterioAsunto)
    {
        return asuntoDao.countAsuntos(criterioAsunto);
    }

    /**
     * Obtiene el numero de los asuntos con el detalle inicial
     * @param criterioAsunto
     * @return 
     */
    public Integer countAsuntosIniciales(CriterioAsunto criterioAsunto)
    {
        return asuntoDao.countAsuntosIniciales(criterioAsunto);
    }

    /**
     * Obtiene el numero de los asuntos con el detalle actual
     * @param criterioAsunto
     * @return 
     */
    public Integer countAsuntosActuales(CriterioAsunto criterioAsunto)
    {
        return asuntoDao.countAsuntosActuales(criterioAsunto);
    }

    /**
     * Lista los asuntos con detalles
     * @param criterioAsunto
     * @return 
     */
    public List<Asunto> listAsuntos(CriterioAsunto criterioAsunto)
    {
        // Asigna el numero de registros de la lista a obtener
        criterioAsunto.getPaginador().setNumRegistros(
                this.countAsuntos(criterioAsunto));
        return asuntoDao.listAsuntos(criterioAsunto);
    }

    /**
     * Lista de los asuntos con el detalle inicial
     * @param criterioAsunto
     * @return 
     */
    public List<Asunto> listAsuntosIniciales(CriterioAsunto criterioAsunto)
    {
        // Asigna el numero de registros de la lista a obtener
        //criterioAsunto.getPaginador().setNumRegistros(this.countAsuntosIniciales(criterioAsunto));
        return asuntoDao.listAsuntosIniciales(criterioAsunto);
    }

    /**
     * Agrega el empleado destinatario a la lista en caso que no exista
     * @param empleado
     * @param empleadoDestinatario
     * @return
     */
    public List<Empleado> addEmpleadoDestinanrioToList(List<Empleado> empleados, Empleado empleadoDestinatario)
    {
        Boolean found = false;
        if (empleados.size() < Constantes.ACTIVADO)
            empleados.add(empleadoDestinatario);
        else
        {
            for (Empleado empleado : empleados)
            {
                if (empleado.getId_empleado().equals(empleadoDestinatario.getId_empleado()))
                    found = true;
            }
            if (!found)//si no se encontro en la lista agregarlo
                empleados.add(empleadoDestinatario);
        }
        return empleados;
    }

    /**
     * Obtiene los datos adicionales de los empleados
     * @param empleadosCcp
     */
    public List<Empleado> populateBeanEmpleadoCcp(List<Empleado> empleadosCcp)
    {
        List<Empleado> empleados = new ArrayList<Empleado>();
        for (Empleado empleado : empleadosCcp)
        {
            empleado = this.empleadoService.getEmpleadoById(empleado.getId_empleado());
            empleados.add(empleado);
        }
        return empleados;
    }

    /**
     * Obtiene el listado de los empleados ccp
     * @param empleados
     * @return
     */
    public String getEmpleadosCcp(List<Empleado> empleados)
    {
        String ccp = empleados.size() > Constantes.NO_ACTIVO ? "Ccp \n" : "";
        for (Empleado empleado : empleados)
        {
            ccp = ccp + empleado.getNombre_completo() + ", " + empleado.getPuesto() + "\n";
        }
        return ccp;
    }

    /**
     * Lista de los asuntos con el detalle actual
     * @param criterioAsunto
     * @return 
     */
    public List<Asunto> listAsuntosActuales(CriterioAsunto criterioAsunto)
    {
        // Asigna el numero de registros de la lista a obtener
        criterioAsunto.getPaginador().setNumRegistros(
                this.countAsuntosActuales(criterioAsunto));
        return asuntoDao.listAsuntosActuales(criterioAsunto);
    }

    /**
     * Lista de los asuntos con el detalle actual
     * @param criterioAsunto
     * @return
     */
    public List<Asunto> listAsuntosActualesDetallePDF(CriterioAsunto criterioAsunto)
    {
        // Asigna el numero de registros de la lista a obtener
        criterioAsunto.getPaginador().setNumRegistros(
                this.countAsuntosActuales(criterioAsunto));
        return asuntoDao.listAsuntosActualesDetallePDF(criterioAsunto);
    }

    /**
     * Lista los asuntos en captura del usuario en sesion
     * @param criterioAsunto
     * @param request
     * @return 
     */
    public List<Asunto> listAsuntosEnCaptura(CriterioAsunto criterioAsunto,
            HttpServletRequest request)
    {
        Empleado empleado_ses = (Empleado) request.getSession().getAttribute(
                Constantes.USUARIO_SESION);
        // Asigna el estatus pendiente como criterio
        criterioAsunto.getIds_estatus().clear();
        // se limita la busqueda a los ultimos ids de asuntos registrado agrupados por folio
        criterioAsunto.setId_area_capt(empleado_ses.getArea().getId_area() + "");// ya se filtro por area con los ids de asuntos obtenidos, ya no es necesario cargar nuevamente el filtro

        return this.listAsuntosIniciales(criterioAsunto);
    }
    /**
     * Obtiene la lista de ids de los asuntos en base a un criterio dado
     * @param criterioAsunto
     * @param request
     * @return 
     */
    public List<String> listIdsAsuntosEnCaptura(CriterioAsunto criterioAsunto,
            HttpServletRequest request)
    {
        Empleado empleado_ses = (Empleado) request.getSession().getAttribute(
                Constantes.USUARIO_SESION);
        // Asigna el estatus pendiente como criterio
        criterioAsunto.getIds_estatus().clear();
        criterioAsunto.getIds_asunto().clear();
        // se limita la busqueda a los ultimos ids de asuntos registrado agrupados por folio
        criterioAsunto.setId_area_capt(empleado_ses.getArea().getId_area() + "");// ya se filtro por area con los ids de asuntos obtenidos, ya no es necesario cargar nuevamente el filtro
        
        return this.asuntoDao.listIdsAsuntosIniciales(criterioAsunto);
    }

    /**
     * Obtiene el numero de asuntos en captura del usuario en sesion
     * @param criterioAsunto
     * @param request
     * @return 
     */
    public Integer countAsuntosEnCaptura(CriterioAsunto criterioAsunto,
            HttpServletRequest request)
    {
        Empleado empleado_ses = (Empleado) request.getSession().getAttribute(
                Constantes.USUARIO_SESION);
        //se crea un nuevo criterio solo para obtener los ultimos ids de los asunto agrupados por folio

        // Asigna el estatus pendiente como criterio
        criterioAsunto.getIds_estatus().clear();
        // se limita la busqueda a los ultimos ids de asuntos registrado agrupados por folio
        criterioAsunto.setId_area_capt(empleado_ses.getArea().getId_area() + "");// ya se filtro por area con los ids de asuntos obtenidos, ya no es necesario cargar nuevamente el filtro
        // Asigna el area de captura como criterio
        return this.countAsuntosIniciales(criterioAsunto);
    }

    /**
     * Lista los asuntos pendientes de turnar o atender del usuario en sesion
     * @param criterioAsunto
     * @param request
     * @return 
     */
    public List<Asunto> listAsuntosEnBandeja(CriterioAsunto criterioAsunto,
            HttpServletRequest request)
    {
        Empleado empleado_ses = (Empleado) request.getSession().getAttribute(
                Constantes.USUARIO_SESION);
        // Asigna empleado destinatario como criterio
        criterioAsunto.setId_empleado_dest(empleado_ses.getId_empleado() + "");
        return this.listAsuntosActuales(criterioAsunto);
    }

    /**
     * La lista de asuntos que tiene pendientes para darle atencion
     * @param criterioAsunto
     * @param request
     * @return 
     */
    public Integer countAsuntosPendientes(HttpServletRequest request)
    {
        CriterioAsunto criterioAsunto = new CriterioAsunto();
        criterioAsunto.getIds_estatus().add(Constantes.TURNADO + "");
        criterioAsunto.getIds_estatus().add(Constantes.PENDIENTE + "");
        return this.countAsuntosEnBandeja(criterioAsunto, request);
    }

    /**
     * La lista de asuntos que tiene pendientes para darle el visto bueno
     * @param criterioAsunto
     * @param request
     * @return 
     */
    public Integer countAsuntosParaVoBo(HttpServletRequest request)
    {
        CriterioAsunto criterioAsunto = new CriterioAsunto();
        criterioAsunto.getIds_estatus().add(Constantes.ATENDIDO + "");
        return this.countAsuntosEnBandeja(criterioAsunto, request);
    }

    /**
     * La lista del total de asunto pendientes: Turnados, Pendientes y  Atendidos(VoBo.)
     * @param criterioAsunto
     * @param request
     * @return 
     */
    public Integer countTotalAsuntosPendientes(HttpServletRequest request)
    {
        CriterioAsunto criterioAsunto = new CriterioAsunto();
        criterioAsunto.getIds_estatus().add(Constantes.TURNADO + "");
        criterioAsunto.getIds_estatus().add(Constantes.ATENDIDO + "");
        criterioAsunto.getIds_estatus().add(Constantes.PENDIENTE + "");
        return this.countAsuntosEnBandeja(criterioAsunto, request);
    }

    /**
     * Obtiene el numero de asuntos pendientes de turnar o atender del usuario
     * en sesion
     * @param criterioAsunto
     * @param request
     * @return 
     */
    public Integer countAsuntosEnBandeja(CriterioAsunto criterioAsunto, HttpServletRequest request)
    {
        Empleado empleado_ses = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);
        // Asigna empleado destinatario como criterio
        criterioAsunto.setId_empleado_dest(empleado_ses.getId_empleado() + "");
        return this.countAsuntosActuales(criterioAsunto);
    }

    /**
     * Lista los asuntos en recepcion del usuario en sesion
     * @param criterioAsunto
     * @param request
     * @return 
     */
    public List<Asunto> listAsuntosEnRecepcion(CriterioAsunto criterioAsunto,
            HttpServletRequest request)
    {
        Empleado empleado_ses = (Empleado) request.getSession().getAttribute(
                Constantes.USUARIO_SESION);

        // Asigna el estatus pendientes y turnados como criterio        
        criterioAsunto.getIds_estatus().add(Constantes.PENDIENTE + "");
        criterioAsunto.getIds_estatus().add(Constantes.TURNADO + "");
        criterioAsunto.getIds_estatus().add(Constantes.ATENDIDO + "");
        
        
        //para que pueda ver todos los asuntos que han sido enviados incialmente a su area. Asigna el empleado recepcionista como criterio. 
        criterioAsunto.setId_empleado_recep(empleado_ses.getId_empleado() + "");
        //para que pueda ver todos los asuntos turnado del area inmediate a la que pertenece, excepto los que son del mismo usuario receptor
        criterioAsunto.setIds_empleados_dest(this.empleadoService.getIdsEmpleadosByIdAreaExcledeMe(empleado_ses));
        return this.listAsuntosActuales(criterioAsunto);
    }

    /**
     * Obtiene el numero de asuntos en recepcion del usuario en sesion
     */
    public Integer countAsuntosEnRecepcion(CriterioAsunto criterioAsunto,
            HttpServletRequest request)
    {
    
        Empleado empleado_ses = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);

        // Asigna el estatus pendientes y turnados como criterio        
        criterioAsunto.getIds_estatus().add(Constantes.PENDIENTE + "");
        criterioAsunto.getIds_estatus().add(Constantes.TURNADO + "");
        criterioAsunto.getIds_estatus().add(Constantes.ATENDIDO + "");
        
        //para que pueda ver todos los asuntos que han sido enviados incialmente a su area. Asigna el empleado recepcionista como criterio. 
        criterioAsunto.setId_empleado_recep(empleado_ses.getId_empleado() + "");
        //para que pueda ver todos los asuntos turnado del area inmediate a la que pertenece, excepto los que son del mismo usuario receptor
        criterioAsunto.setIds_empleados_dest(this.empleadoService.getIdsEmpleadosByIdAreaExcledeMe(empleado_ses));
        return this.countAsuntosActuales(criterioAsunto);
    }

    /**
     * Lista los asuntos donde esta copiado el usuario en sesion
     */
    public List<Asunto> listAsuntosCcp(CriterioAsunto criterioAsunto,
            HttpServletRequest request)
    {
        Empleado empleado_ses = (Empleado) request.getSession().getAttribute(
                Constantes.USUARIO_SESION);

        // Asigna empleado con copia para como criterio
        criterioAsunto.setId_empleado_ccp(empleado_ses.getId_empleado() + "");
        return asuntoDao.listAsuntosIniciales(criterioAsunto);
    }

    /**
     * Obtiene el numero de asuntos donde esta copiado el usuario en sesion
     */
    public Integer countAsuntosCcp(CriterioAsunto criterioAsunto,
            HttpServletRequest request)
    {
        Empleado empleado_ses = (Empleado) request.getSession().getAttribute(
                Constantes.USUARIO_SESION);

        // Asigna empleado con copia para como criterio
        criterioAsunto.setId_empleado_ccp(empleado_ses.getId_empleado() + "");
        return asuntoDao.countAsuntosIniciales(criterioAsunto);
    }

    /**
     * Lista de los asuntos atendidos del usuario en sesion
     */
    public List<Asunto> listAsuntosAtendidos(CriterioAsunto criterioAsunto,
            HttpServletRequest request)
    {
        Empleado empleado_ses = (Empleado) request.getSession().getAttribute(
                Constantes.USUARIO_SESION);

        // Asigna el estatus atendido como criterio
        criterioAsunto.setEstatus(Constantes.ATENDIDO + "");

        // Asigna empleado remitente como criterio
        criterioAsunto.setId_empleado_remi(empleado_ses.getId_empleado() + "");
        return this.listAsuntos(criterioAsunto);
    }

    /**
     * Obtiene el numero de asuntos atendidos del usuario en sesion
     */
    public Integer countAsuntosAtendidos(CriterioAsunto criterioAsunto,
            HttpServletRequest request)
    {
        Empleado empleado_ses = (Empleado) request.getSession().getAttribute(
                Constantes.USUARIO_SESION);

        // Asigna el estatus atendido como criterio
        criterioAsunto.setEstatus(Constantes.ATENDIDO + "");

        // Asigna empleado remitente como criterio
        criterioAsunto.setId_empleado_remi(empleado_ses.getId_empleado() + "");
        return this.countAsuntos(criterioAsunto);
    }

    /**
     * Lista de los asuntos atendidos del usuario en sesion
     */
    public List<Asunto> listAsuntosFinalizados(CriterioAsunto criterioAsunto,
            HttpServletRequest request)
    {
        Empleado empleado_ses = (Empleado) request.getSession().getAttribute(
                Constantes.USUARIO_SESION);

        // Asigna el estatus Finalizado como criterio
        criterioAsunto.setEstatus(Constantes.FINALIZADO + "");

        // Asigna empleado remitente como criterio
        criterioAsunto.setId_empleado_remi(empleado_ses.getId_empleado() + "");
        return this.listAsuntos(criterioAsunto);
    }

    /**
     * Obtiene el numero de asuntos Finalizados del usuario en sesion
     * @param criterioAsunto
     * @param request
     * @return 
     */
    public Integer countAsuntosFinalizados(CriterioAsunto criterioAsunto,
            HttpServletRequest request)
    {
        Empleado empleado_ses = (Empleado) request.getSession().getAttribute(
                Constantes.USUARIO_SESION);

        // Asigna el estatus Finalizado como criterio
        criterioAsunto.setEstatus(Constantes.FINALIZADO + "");

        // Asigna empleado remitente como criterio
        criterioAsunto.setId_empleado_remi(empleado_ses.getId_empleado() + "");
        return this.countAsuntos(criterioAsunto);
    }

    /**
     * Lista los asuntos turnados del usuario en sesion
     * @param criterioAsunto
     * @param request
     * @return 
     */
    public List<Asunto> listAsuntosTurnados(CriterioAsunto criterioAsunto,
            HttpServletRequest request)
    {
        Empleado empleado_ses = (Empleado) request.getSession().getAttribute(
                Constantes.USUARIO_SESION);

        // Asigna el estatus turnado como criterio
        criterioAsunto.setEstatus(Constantes.TURNADO + "");

        // Asigna empleado remitente como criterio
        criterioAsunto.setId_empleado_remi(empleado_ses.getId_empleado() + "");
        return this.listAsuntos(criterioAsunto);
    }

    /**
     * Obtiene el numero de asuntos turnados del usuario en sesion
     * @param criterioAsunto
     * @param request
     * @return 
     */
    public Integer countAsuntosTurnados(CriterioAsunto criterioAsunto,
            HttpServletRequest request)
    {
        Empleado empleado_ses = (Empleado) request.getSession().getAttribute(
                Constantes.USUARIO_SESION);

        // Asigna el estatus turnado como criterio
        criterioAsunto.setEstatus(Constantes.TURNADO + "");

        // Asigna empleado remitente como criterio
        criterioAsunto.setId_empleado_remi(empleado_ses.getId_empleado() + "");
        return this.countAsuntos(criterioAsunto);
    }

    /**
     * Guarda el asunto en base
     * @param asunto
     * @param request
     * @param urlLogo
     * @return
     * @throws Exception 
     */
    public Asunto saveAsunto(Asunto asunto, HttpServletRequest request, URL urlLogo) throws Exception
    {
        try
        {
            Empleado empleado_capt = (Empleado) request.getSession().getAttribute(
                    Constantes.USUARIO_SESION);
            // Asigna el empleado capturista
            asunto.setEmpleado_capt(empleado_capt);

            // Asigna la Ip donde se capturo el asunto
            asunto.getAsunto_detalle().setIp(request.getRemoteAddr());

            // Asigna la fecha de registro del asunto
            asunto.getAsunto_detalle().setFh_registro(new Date());

            // Asigna fh limite de acuerdo a la prioridad
            this.asignarFhLimite(asunto, request);

            // Asigna el tema del asunto
            try
            {
                asunto.getTema().setId_tema(asunto.getTema().getId_tema() >= 0 ? asunto.getTema().getId_tema() : asunto.getTema().getId_tema_padre());
            } catch (NullPointerException exc)
            {
                //log.error(exc);
            }

            // Asigna el evento del asunto
            try
            {
                asunto.getEvento().setId_evento(asunto.getEvento().getId_evento() >= 0 ? asunto.getEvento().getId_evento() : null);
            } catch (NullPointerException exc)
            {
                //log.error(exc);
            }

            // Lista de identificadores de asunto que se guardaran en la tabla de
            // asunto concentrado
            List<Map<String, Integer>> ids_asunto = new ArrayList<Map<String, Integer>>();

            // Id adunto padre
            Integer id_asunto_padre = null;

            //	El folio
            String folio = "";

            // Para asignar el asunto a varios destinatarios, se recorre la lista y
            // se guarda un asunto por destinatario
            List<Empleado> empleadosDest = asunto.getAsunto_detalle().getEmpleadosDest();
            for (Empleado empleado_dest : empleadosDest)
            {
                asunto.getAsunto_detalle().setEmpleado_dest(empleado_dest);

                // Obtiene la informacion del empleado destinatario inicial
                Empleado empleado_dest_ini = empleadoService.getEmpleadoById(empleado_dest.getId_empleado());

                // Asigna el destinatario inicial
                asunto.getAsunto_detalle().setEmpleado_dest_ini(empleado_dest_ini);

                // Obtiene la lista de empleados recepcionistas del asunto                
                List<Empleado> empleados_recep = empleadoService.getListReceivingWithRecipient(empleado_dest_ini);

                // Asigna los empleados recepcionistas
                asunto.getAsunto_detalle().setEmpleadosRecep(empleados_recep);

                // Asigna el estatus en captura
                Integer estatus = Constantes.EN_CAPTURA;

                // Verifica si el asunto va a ser archivado
                estatus = asunto.getArchivado().equals(Constantes.ARCHIVADO) ? Constantes.ARCHIVADO : estatus;
                //estatus = asunto.getConfidencial().booleanValue() ? Constantes.CONFIDENCIAL : estatus;// si el asunto es de caracter confidencial // AGG Confidencial -> booleano 20111109
                // Asigna el estatus al asunto
                asunto.setEstatus(estatus);
                asunto.getAsunto_detalle().setEstatus(estatus);

                if (asunto.getFolio().equals(""))//solo la primera vez se calcula el folio
                {
                    folio = this.asuntoDao.getFolioAsunto(empleado_capt.getArea().getId_area());
                    if (asunto.getId_asunto_ref().equals("CCP")){ //si es un nuevo asunto a partir de un CCP agrega una C al final
                        folio = folio + "C";
                    }
                    asunto.setFolio(folio);
                }

                //poner en el asunto las instruciones y comentarios personalizados de cada empleado destinatario
                asunto.setId_instruccion(empleado_dest.getInstruccion().getId_instruccion());
                if (empleado_dest.hasComentarioPersonalizado())//si el empleado tiene comentario personalizado
                    asunto.getAsunto_detalle().setComentario(empleado_dest.getComentario());//se le pone al detalle
                
                // Guardar Asunto en estatus=1 (pendiente) si el remitente y el destinatario
                // pertenecen a áreas internas
                // Ticket 152
                // Rodolfo Milano Oliveros
                // Fecha: 09/12/2013
                Integer tipoAreaRemite = asunto.getAsunto_detalle().getEmpleado_remi().getArea().getTipo();
                Integer tipoAreaDestino = asunto.getAsunto_detalle().getEmpleado_dest().getArea().getTipo();
                if (tipoAreaRemite == 0 && tipoAreaDestino == 0){
                    asunto.setEstatus(Constantes.TURNADO);
                    asunto.getAsunto_detalle().setEstatus(Constantes.TURNADO);
                }

                // Guarda en base de datos el asunto
                asunto = asuntoDao.saveAsunto(asunto);// se crea el padre

                if (asunto.getId_asunto_padre().equals(Constantes.INSTANCIA_CREADA))
                {//La creacion reciente del objeto se toma como padre
                    asunto.setId_asunto_padre(asunto.getId_asunto());// Los hijos se crean con relacion al id del padre
                    asuntoDao.saveAsuntoConsecutivoArea(asunto);// insertar el consecutivo del area
                }
                // Agerga el id del asunto a la lista de identificadores de asunto
                Map<String, Integer> map_ids_asunto = new HashMap<String, Integer>();
                map_ids_asunto.put("id_asunto", asunto.getId_asunto());
                map_ids_asunto.put("id_asunto_detalle", asunto.getAsunto_detalle().getId_asunto_detalle());
                ids_asunto.add(map_ids_asunto);

                if (!asunto.getEstatus().equals(Constantes.ARCHIVADO) && !asunto.getEstatus().equals(Constantes.EN_CAPTURA))
                    // Si el destinatario es interno asigna el estatus pendiente
                    // De lo contrario asigna el estatus finalizado
                    if (empleado_dest_ini.getArea().getTipo().equals(Area.INTERNA))
                    {
                        // Si no tiene recepcionista se lo turna a el mismo
                        /*if (empleados_recep != null && empleados_recep.isEmpty())
                        {*/
                        if (asunto.getAsunto_detalle().getEmpleado_remi().getArea().equals(Area.INTERNA))
                        {
                            asunto.setEstatus(Constantes.TURNADO);
                            asunto.getAsunto_detalle().setEstatus(Constantes.TURNADO);
                            asunto.getAsunto_detalle().setComentario("");
                            asuntoDao.insertAsuntoDetalle(asunto.getAsunto_detalle());
                        }
                    }
                    else
                    {
                        asunto.setEstatus(Constantes.FINALIZADO);
                        asunto.getAsunto_detalle().setEstatus(Constantes.FINALIZADO);
                        asunto.getAsunto_detalle().setComentario("");
                        asuntoDao.insertAsuntoDetalle(asunto.getAsunto_detalle());
                    }

                // Registra asunto en tabla de actualizaciones
                asuntoCapturaActualizacionService.saveAsuntoCapturaActualizacion(asunto, request);

                // Envia un correo notificando que se ha creado un asunto
                if ((asunto.getEstatus().equals(Constantes.TURNADO)||asunto.getEstatus().equals(Constantes.PENDIENTE))
                    &&asunto.getAsunto_detalle().getEmpleado_dest_ini().getArea().getTipo().equals(Area.INTERNA) //Area interna
                    &&asunto.getAsunto_detalle().getEmpleado_dest_ini().getArea().getEnvio_correo().equals(1)) //Area tiene correo activado
                    this.sendMailNuevoAsunto(asunto, urlLogo, request);
            }

            // Guarda el registro del asunto en un concentrado
            Integer id_concentrado = asuntoDao.saveAsuntoConcentrado(ids_asunto);
            asunto.getAsunto_detalle().setId_concentrado(id_concentrado);

            // Sube a request el numero del asuto guardado para mostrar mensaje
            String ids = "";
            int i = 1;
            for (Map<String, Integer> map : ids_asunto)
            {
                ids += map.get("id_asunto");
                if (i < ids_asunto.size())
                    ids += ", ";
                i++;
            }
            request.setAttribute("no_asunto_exitoso", ids);
        } catch (Exception e)
        {
            log.error("Ha ocurrido un error en la transaccion",e);
            //throw new Exception(e.getMessage());
        }
        return asunto;
    }

    /**
     * Guarda el asunto como atendido
     * @param asunto
     * @param request
     * @param urlLogo
     * @return
     * @throws Exception 
     */
    public Asunto saveAtender(Asunto asunto, HttpServletRequest request, URL urlLogo) throws Exception
    {
        Empleado empleado_ses = (Empleado) request.getSession().getAttribute(
                Constantes.USUARIO_SESION);

        // Asigna la fecha de registro del asunto
        asunto.getAsunto_detalle().setFh_registro(new Date());

        // Asigna la Ip donde se capturo el asunto
        asunto.getAsunto_detalle().setIp(request.getRemoteAddr());

        // Asigna el Id del asunto
        asunto.getAsunto_detalle().setId_asunto(asunto.getId_asunto());

        // Obtiene el detalle de asunto final
        AsuntoDetalle asuntoDetFinal = asunto.getAsuntos_detalles().get(asunto.getAsuntos_detalles().size() - Constantes.ACTIVO);
        if (asuntoDetFinal.getEstatus()==14){
            Integer resta = 2;
            asuntoDetFinal = asunto.getAsuntos_detalles().get(asunto.getAsuntos_detalles().size() - resta);
        }

        // Quien remite es la persona a quien fue dirigido originalmente el asunto
        asunto.getAsunto_detalle().setEmpleado_remi(asuntoDetFinal.getEmpleado_dest());

        asuntoDao.borraAtenderParcial(asunto.getUltimaDetalle().getId_asunto_detalle());

        // Asigna el estatus de Atendido
        asunto.getAsunto_detalle().setEstatus(Constantes.ATENDIDO);
        asunto.setEstatus(Constantes.ATENDIDO);
        Boolean found = false;
        // Asigna informacion al asunto con el ultimo detalle
        for (Integer i = 0; i < asunto.getAsuntos_detalles().size() && found == false; i++)
        {
            AsuntoDetalle asuntoDetalle = (AsuntoDetalle) asunto.getAsuntos_detalles().get(i);
            // Si el destinatario del detalle de asunto es el empleado en sesion o alguno de los empleados que pertenece al area del recepcionista
            if (checkEmpleadoDetalleEmpleadosArea(asuntoDetalle.getEmpleado_dest(), empleado_ses) && !asuntoDetalle.getEstatus().equals(Constantes.FINALIZADO))
            {

                found = true; // se ha encontrado el detalle que origino el turno en el arbol
                // Asigna el empleado destinatario
                asunto.getAsunto_detalle().setEmpleado_dest(
                        asuntoDetalle.getEmpleado_remi());

                // Asigna la fecha limite de atencion
                asunto.getAsunto_detalle().setFh_limite(
                        asuntoDetalle.getFh_limite());

                // Asigna el esttus de atendido
                asunto.getAsunto_detalle().setEstatus(Constantes.ATENDIDO);
                if (asuntoDetalle.getEmpleado_remi().getArea().getTipo().equals(Area.EXTERNA))
                {
                    //Como el remitente es externo, porner como destinatario final al firmante que original o en su defecto al capturista
                    Empleado empleadoFirmanteInicial = asuntoDetalle.getEmpleadoFirmanteVolante().getId_empleado() == null ? asunto.getEmpleado_capt() : 
                            asuntoDetalle.getEmpleadoFirmanteVolante();
                    asunto.getAsunto_detalle().setEmpleado_dest(empleadoFirmanteInicial);
                    asunto.getAsunto_detalle().setEstatus(Constantes.FINALIZADO);
                    asunto.setEstatus(Constantes.FINALIZADO);
                }                    

            }
            // Si el estatus del detalle de asunto es pendiente
//            if (asuntoDetalle.getEstatus().equals(Constantes.PENDIENTE))
//            {
//                // Obtiene el remitente inicial
//                Empleado empleado_remi_ini = empleadoService.getEmpleadoById(asuntoDetalle.getEmpleado_remi().getId_empleado());
//
//                // Si el remitente inicial es externo se finaliza el asunto
//                if (empleado_remi_ini.getArea().getTipo().equals(Area.EXTERNA))
//                    asunto.getAsunto_detalle().setEstatus(Constantes.FINALIZADO);
//                asunto.getAsunto_detalle().setEmpleado_dest(empleado_remi_ini);
//            }

        }
        // Actualiza en base de datos el asunto
        asunto = asuntoDao.updateAsunto(asunto);        
        asunto.setTipo_actualizacion(1);
        asuntoCapturaActualizacionService.saveAsuntoCapturaActualizacion(asunto, request);

        // Envia un correo notificando que se ha creado un asunto
        //this.sendMailAsunto(asunto, urlLogo);
        return asunto;
    }

    /**
     * Guarda el asunto como atendido
     * @param asunto
     * @param request
     * @param urlLogo
     * @return
     * @throws Exception
     */
    public Asunto saveAtenderParcial(Asunto asunto, HttpServletRequest request, URL urlLogo) throws Exception
    {
        Empleado empleado_ses = (Empleado) request.getSession().getAttribute(
                Constantes.USUARIO_SESION);

        // Asigna la fecha de registro del asunto
        asunto.getAsunto_detalle().setFh_registro(new Date());

        // Asigna la Ip donde se capturo el asunto
        asunto.getAsunto_detalle().setIp(request.getRemoteAddr());

        // Asigna el Id del asunto
        Integer idAsunto = asunto.getId_asunto();

        // Asigna el Id del detalle
        Integer idAsuntoDetalle = asunto.getUltimaDetalle().getId_asunto_detalle();

        
        // Guarda la respuesta parcial
        asunto = asuntoDao.SaveAtenderParcial(asunto);
        asunto.setTipo_actualizacion(1);
        Integer estatus = asunto.getEstatus();
        asunto.getAsunto_detalle().getEmpleado_dest().setId_empleado(asunto.getUltimaDetalle().getEmpleado_dest().getId_empleado());
        asunto.setEstatus(Constantes.ATENCIONPARCIAL);
        asuntoCapturaActualizacionService.saveAsuntoCapturaActualizacion(asunto, request);
        asunto.setEstatus(estatus);

        // Envia un correo notificando que se ha creado un asunto
        //this.sendMailAsunto(asunto, urlLogo);
        return asunto;
    }

    /**
     * Comprueba si el empleado destinatario del detalle esta contenido en los empleados del area al que pertenece el recepcionista
     * @param empleadoDetalle
     * @param usuarioSession
     * @return
     */
    public Boolean checkEmpleadoDetalleEmpleadosArea(Empleado empleadoDetalle, Empleado usuarioSession)
    {
        Boolean existe = false;
        List<Empleado> empleadosAreas = this.empleadoService.listEmpleadosByIdArea(usuarioSession.getArea().getId_area());
        for (Empleado empleado : empleadosAreas)
        {
            if (empleado.getId_empleado().equals(empleadoDetalle.getId_empleado()))
                existe = true;
        }
        return existe;
    }

    /**
     * Guarda el turnado de un asunto
     * @param asunto
     * @param request
     * @param urlLogo
     * @return 
     */
    public Asunto saveTurnar(Asunto asunto, HttpServletRequest request, URL urlLogo)
    {
        try
        {
            Empleado empleado_ses = (Empleado) request.getSession().getAttribute(
                    Constantes.USUARIO_SESION);

            // Borrar cualquier atención parcial que se tenga
            // Ticket 144
            // Rodolfo Milano Oliveros
            //asuntoDao.borraAtenderParcial(asunto.getAsunto_detalle().getId_asunto_detalle());
            asuntoDao.borraAtenderParcial(asunto.getUltimaDetalle().getId_asunto_detalle());
            
            // Asigna la fecha de registro del asunto
            asunto.getAsunto_detalle().setFh_registro(new Date());

            //El remitente original del asunto
            // Empleado empleadoRemitenteOriginal = asunto.getAsuntos_detalles().get(asunto.getAsuntos_detalles().size() - Constantes.ACTIVADO).getEmpleado_dest();
            Empleado empleadoRemitenteOriginal = asunto.getUltimaDetalle().getEmpleado_dest();
            
            // Asigna el empleado remitente
            /*if (asunto.getAsuntos_detalles().size() > 1){
                if (asunto.getAsuntos_detalles().get(asunto.getAsuntos_detalles().size()-Constantes.ACTIVADO).getEstatus().equals(Constantes.TURNADO) && //La opcion de returnar toma el mismo remitente y finaliza el turno previo.
                    asunto.getAsunto_detalle().getEmpleadosDest().contains(empleadoRemitenteOriginal)) //los destinatarios actual y anterior son el mismo
                {
                    asunto.getAsunto_detalle().setEmpleado_remi(asunto.getUltimaDetalle().getEmpleado_remi());
                    AsuntoDetalle asunto_actualiza = asunto.getAsuntos_detalles().get(asunto.getAsuntos_detalles().size()-Constantes.ACTIVADO);
                    asunto_actualiza.setEstatus(Constantes.FINALIZADO);
                    asuntoDao.updateEstatusAsuntoDetalleByIdAsuntoDetalle(asunto_actualiza); //Finaliza el detalle turnado anterior
                }
                else{
                    asunto.getAsunto_detalle().setEmpleado_remi(empleadoRemitenteOriginal);
                    
                }
            }
            else{*/
                asunto.getAsunto_detalle().setEmpleado_remi(empleadoRemitenteOriginal);
            //}

            // En el destinatario inicial poner el usuario que le esta dando atencion, puede ser la receptora del destinatario inicial
            asunto.getAsunto_detalle().setEmpleado_dest_ini(empleado_ses);

            // Asigna la Ip donde se capturo el asunto
            asunto.getAsunto_detalle().setIp(request.getRemoteAddr());

            // El id asunto del padre
            Integer idAsuntoRaiz = asunto.getId_asunto();

           
            // Asigna el estatus de Turnado
            //Integer estatus = asunto.getConfidencial() ? Constantes.CONFIDENCIAL : Constantes.TURNADO;
            Integer estatus = Constantes.TURNADO; //AGG Confidencial -> booleano 20111109
            asunto.getAsunto_detalle().setEstatus(estatus);
            asunto.setEstatus(estatus);
            // Asigna el Id del asunto            
            asunto.getAsunto_detalle().setId_asunto(asunto.getId_asunto());

            // Asigna fh limite de acuerdo a la prioridad
            this.asignarFhLimite(asunto, request);

            asunto.setEmpleado_capt(empleado_ses);
            // Lista de identificadores de asunto que se guardaran en la tabla de
            // asunto concentrado
            List<Map<String, Integer>> ids_asunto = new ArrayList<Map<String, Integer>>();

            // Obtiene los empleados destinatarios
            List<Empleado> empleadosDest = asunto.getAsunto_detalle().getEmpleadosDest();

            String comentarioGeneral = "";
            // contador
            int i = 0;


            for (Empleado empleado_dest : empleadosDest)
            {
                // Guarda el asunto como turnado
                asunto.getAsunto_detalle().setEmpleado_dest(empleado_dest);
                // Asigna el estatus turnado
                empleado_dest.setComentario(empleado_dest.getComentario() == null ? "" : empleado_dest.getComentario());
                asunto.getAsunto_detalle().setComentario(asunto.getAsunto_detalle().getComentario() == null ? "" : asunto.getAsunto_detalle().getComentario());
                asunto.getAsunto_detalle().setPrioridad(empleado_dest.getPrioridad());
                if (empleado_dest.getFh_limiteDDMMYYYY().equals("")){
                    this.asignarFhLimiteDetalle(asunto.getAsunto_detalle(), request);
                }
                else{
                    asunto.getAsunto_detalle().setFh_limiteDDMMYYYY(empleado_dest.getFh_limiteDDMMYYYY());
                }
                if (empleado_dest.getComentario().length() > Constantes.NO_ACTIVO)
                    asunto.getAsunto_detalle().setComentario(empleado_dest.getComentario());//si tienen comentarios particulares
                if (i == 0)// El primer asunto (la raiz) se recicla con la finalidad de que no quede como un asunto huerfano
                {
                    // Actualiza en base de datos el asunto
                    asunto = asuntoDao.updateAsunto(asunto);
                    asunto.setTipo_actualizacion(1);
                    asuntoCapturaActualizacionService.saveAsuntoCapturaActualizacion(asunto, request);
                    i++;
                }
                else
                {

                    // Asigna el null como id_asunto
                    asunto.setId_asunto(null);
                    // Asigna el empleado capturista
                    asunto.setId_asunto_padre(idAsuntoRaiz);
                    asunto = asuntoDao.saveAsunto(asunto);
                    asunto.setTipo_actualizacion(1);
                    asuntoCapturaActualizacionService.saveAsuntoCapturaActualizacion(asunto, request);
                    // Obtiene la informacion del empleado destinatario inicial
                    Empleado empleado_dest_ini = empleadoService.getEmpleadoById(empleado_dest.getId_empleado());
                }

                // Agerga el id del asunto a la lista de identificadores de asunto
                Map<String, Integer> map_ids_asunto = new HashMap<String, Integer>();
                map_ids_asunto.put("id_asunto", asunto.getId_asunto());
                map_ids_asunto.put("id_asunto_detalle", asunto.getAsunto_detalle().getId_asunto_detalle());
                ids_asunto.add(map_ids_asunto);

                // Envia un correo notificando que se ha creado un asunto
                asunto.getAsunto_detalle().setEmpleado_dest(empleadoService.getEmpleadoById(asunto.getAsunto_detalle().getEmpleado_dest().getId_empleado()));
                if (asunto.getAsunto_detalle().getEmpleado_dest().getArea().getTipo().equals(Area.INTERNA) //Area interna
                    &&asunto.getAsunto_detalle().getEmpleado_dest().getArea().getEnvio_correo().equals(1))
                        this.sendMailAsunto(asunto, urlLogo, request);
            }
            // Guarda el registro del asunto en un concentrado
            Integer id_concentrado = asuntoDao.saveAsuntoConcentrado(ids_asunto);
            asunto.getAsunto_detalle().setId_concentrado(id_concentrado);

        } catch (Exception e)
        {
            log.error("Ha ocurrido un error en la transaccion",e);
        }
        return asunto;
    }

    /**
     * Guarda el returnado de un asunto
     * @param asunto
     * @param request
     * @param urlLogo
     * @return
     */
    public Asunto saveReTurnar(Asunto asunto, HttpServletRequest request, URL urlLogo)
    {
        try
        {
            Empleado empleado_ses = (Empleado) request.getSession().getAttribute(
                    Constantes.USUARIO_SESION);

            Asunto asuntoReturno=new Asunto();
            asuntoReturno.setAntecedente(asunto.getAntecedente());
            asuntoReturno.setAsunto(asunto.getAsunto());
            asuntoReturno.setConfidencial(asunto.getConfidencial());
            String contadorFolio = asuntoDao.getSigContadorFolio(asuntoReturno.getFolio());
            asuntoReturno.setContador_folio(contadorFolio);
            asuntoReturno.setDescripcion(asunto.getDescripcion());
            asuntoReturno.setEmpleadosCcp(asunto.getEmpleadosCcp());
            asuntoReturno.setEmpleado_ccp(asunto.getEmpleado_ccp());
            asuntoReturno.setEvento(asunto.getEvento());
            asuntoReturno.setExpediente(asunto.getExpediente());
            asuntoReturno.setFh_evento(asunto.getFh_evento());
            asuntoReturno.setFh_limite_asunto(asunto.getFh_limite_asunto());
            asuntoReturno.setFh_limite_asuntoDDMMYYYY(asunto.getFh_limite_asuntoDDMMYYYY());
            asuntoReturno.setFh_oficio(asunto.getFh_oficio());
            asuntoReturno.setFh_oficioDDMMYYYY(asunto.getFh_oficioDDMMYYYY());
            asuntoReturno.setFh_recepcion(asunto.getFh_recepcion());
            asuntoReturno.setFh_recepcionDDMMYYYY(asunto.getFh_recepcionDDMMYYYY());
            asuntoReturno.setFolio(asunto.getFolio());
            asuntoReturno.setId_evento(asunto.getId_evento());
            asuntoReturno.setId_expediente(asunto.getId_expediente());
            asuntoReturno.setId_instruccion(asunto.getId_instruccion());
            asuntoReturno.setId_tema(asunto.getId_tema());
            asuntoReturno.setNo_oficio(asunto.getNo_oficio());
            asuntoReturno.setSubTema(asunto.getSubTema());
            asuntoReturno.setTema(asunto.getTema());
            asuntoReturno.setTipoAsunto(asunto.getTipoAsunto());
            asuntoReturno.setfh_eventoDDMMYYYY(asunto.getFh_eventoDDMMYYYY());
            asuntoReturno.setAsunto_detalle(asunto.getAsunto_detalle());
            // Asigna la fecha de registro del asunto
            asuntoReturno.getAsunto_detalle().setFh_registro(new Date());

            //El remitente original del asunto
            Empleado empleadoRemitenteOriginal = asunto.getAsuntos_detalles().get(asunto.getAsuntos_detalles().size() - Constantes.ACTIVADO).getEmpleado_remi();
            asuntoReturno.getAsunto_detalle().setEmpleado_remi(empleadoRemitenteOriginal);
           

            // En el destinatario inicial poner el usuario que le esta dando atencion, puede ser la receptora del destinatario inicial
            asuntoReturno.getAsunto_detalle().setEmpleado_dest_ini(empleado_ses);

            // Asigna la Ip donde se capturo el asunto
            asuntoReturno.getAsunto_detalle().setIp(request.getRemoteAddr());

            // El id asunto del padre
            Integer idAsuntoRaiz = asunto.getId_asunto();


            // Asigna el estatus de Turnado
            //Integer estatus = asunto.getConfidencial() ? Constantes.CONFIDENCIAL : Constantes.TURNADO;
            Integer estatus = Constantes.TURNADO; //AGG Confidencial -> booleano 20111109
            asuntoReturno.getAsunto_detalle().setEstatus(estatus);
            asuntoReturno.setEstatus(estatus);
            // Asigna el Id del asunto
            asuntoReturno.getAsunto_detalle().setId_asunto(asuntoReturno.getId_asunto());

            // Asigna fh limite de acuerdo a la prioridad
            this.asignarFhLimite(asuntoReturno, request);

            asuntoReturno.setEmpleado_capt(empleado_ses);
            // Lista de identificadores de asunto que se guardaran en la tabla de
            // asunto concentrado
            List<Map<String, Integer>> ids_asunto = new ArrayList<Map<String, Integer>>();

            // Obtiene los empleados destinatarios
            List<Empleado> empleadosDest = asuntoReturno.getAsunto_detalle().getEmpleadosDest();

            String comentarioGeneral = "";
            // contador
            int i = 0;


            for (Empleado empleado_dest : empleadosDest)
            {
                // Guarda el asunto como turnado
                asuntoReturno.getAsunto_detalle().setEmpleado_dest(empleado_dest);
                // Asigna el estatus turnado
                empleado_dest.setComentario(empleado_dest.getComentario() == null ? "" : empleado_dest.getComentario());
                asuntoReturno.getAsunto_detalle().setComentario(asuntoReturno.getAsunto_detalle().getComentario() == null ? "" : asuntoReturno.getAsunto_detalle().getComentario());
                asuntoReturno.getAsunto_detalle().setPrioridad(empleado_dest.getPrioridad());
                if (empleado_dest.getFh_limiteDDMMYYYY().equals("")){
                    this.asignarFhLimiteDetalle(asuntoReturno.getAsunto_detalle(), request);
                }
                else{
                    asuntoReturno.getAsunto_detalle().setFh_limiteDDMMYYYY(empleado_dest.getFh_limiteDDMMYYYY());
                }
                if (empleado_dest.getComentario().length() > Constantes.NO_ACTIVO)
                    asuntoReturno.getAsunto_detalle().setComentario(empleado_dest.getComentario());//si tienen comentarios particulares
                /*if (i == 0)// El primer asunto (la raiz) se recicla con la finalidad de que no quede como un asunto huerfano
                {
                    // Actualiza en base de datos el asunto
                    asunto = asuntoDao.updateAsunto(asunto);
                    asunto.setTipo_actualizacion(1);
                    asuntoCapturaActualizacionService.saveAsuntoCapturaActualizacion(asunto, request);
                    i++;
                }
                else
                {*/

                    // Asigna el null como id_asunto
                    asuntoReturno.setId_asunto(null);
                    // Asigna el empleado capturista
                    asuntoReturno.setId_asunto_padre(idAsuntoRaiz);
                    asuntoReturno = asuntoDao.saveAsunto(asuntoReturno);
                    asuntoReturno.setTipo_actualizacion(1);
                    asuntoCapturaActualizacionService.saveAsuntoCapturaActualizacion(asuntoReturno, request);
                    // Obtiene la informacion del empleado destinatario inicial
                    //Empleado empleado_dest_ini = empleadoService.getEmpleadoById(empleado_dest.getId_empleado());
                /*}*/

                // Agerga el id del asunto a la lista de identificadores de asunto
                Map<String, Integer> map_ids_asunto = new HashMap<String, Integer>();
                map_ids_asunto.put("id_asunto", asuntoReturno.getId_asunto());
                map_ids_asunto.put("id_asunto_detalle", asuntoReturno.getAsunto_detalle().getId_asunto_detalle());
                ids_asunto.add(map_ids_asunto);

                // Envia un correo notificando que se ha creado un asunto
                asunto.getAsunto_detalle().setEmpleado_dest(empleadoService.getEmpleadoById(asunto.getAsunto_detalle().getEmpleado_dest().getId_empleado()));
                if (asunto.getAsunto_detalle().getEmpleado_dest().getArea().getTipo().equals(Area.INTERNA) //Area interna
                    &&asunto.getAsunto_detalle().getEmpleado_dest().getArea().getEnvio_correo().equals(1))
                        this.sendMailAsunto(asunto, urlLogo, request);
            }
            // Guarda el registro del asunto en un concentrado
            Integer id_concentradoAsunto=asuntoDao.getIdConcentradoAsunto(asunto.getId_asunto());
            asuntoDao.saveAsuntoConcentradoRet(ids_asunto,id_concentradoAsunto);            
            asuntoReturno.getAsunto_detalle().setId_concentrado(id_concentradoAsunto);
            asunto=asuntoReturno;
            asuntoReturno=null;
        } catch (Exception e)
        {
            log.error("Ha ocurrido un error en la transaccion",e);
            request.setAttribute("exitoMsg", "Ocurri&oacute; un problema, contacte al administrador.");
        }
        return asunto;
    }

/**
     * Guarda el turnado de un asunto
     * @param asunto
     * @param request
     * @param urlLogo
     * @return
     */
    public Asunto saveEditarTurno(Asunto asunto, HttpServletRequest request, URL urlLogo)
    {
        try
        {
            Empleado empleado_ses = (Empleado) request.getSession().getAttribute(
                    Constantes.USUARIO_SESION);

            // Asigna la fecha de registro del asunto
            asunto.getAsunto_detalle().setFh_registro(new Date());

            //El remitente original del asunto
            Empleado empleadoRemitenteOriginal = asunto.getAsuntos_detalles().get(asunto.getAsuntos_detalles().size() - Constantes.ACTIVADO).getEmpleado_dest();
            asunto.getAsunto_detalle().setEmpleado_remi(empleadoRemitenteOriginal);
            asunto.getAsunto_detalle().setEmpleado_dest_ini(empleado_ses);
            // Asigna la Ip donde se capturo el asunto
            asunto.getAsunto_detalle().setIp(request.getRemoteAddr());
            // El id asunto del padre
            Integer idAsuntoRaiz = asunto.getId_asunto();
            // Asigna el estatus de Turnado
            //Integer estatus = asunto.getConfidencial() ? Constantes.CONFIDENCIAL : Constantes.TURNADO;
            Integer estatus = Constantes.TURNADO; //AGG Confidencial -> booleano 20111109
            asunto.getAsunto_detalle().setEstatus(estatus);
            asunto.setEstatus(estatus);
            // Asigna el Id del asunto
            asunto.getAsunto_detalle().setId_asunto(asunto.getId_asunto());
            // Asigna fh limite de acuerdo a la prioridad
            this.asignarFhLimite(asunto, request);
            asunto.setEmpleado_capt(empleado_ses);
            // Lista de identificadores de asunto que se guardaran en la tabla de
            // asunto concentrado
            List<Map<String, Integer>> ids_asunto = new ArrayList<Map<String, Integer>>();
            // Obtiene los empleados destinatarios
            List<Empleado> empleadosDest = asunto.getAsunto_detalle().getEmpleadosDest();
            String comentarioGeneral = "";
            // contador
            int i = 0;
            for (Empleado empleado_dest : empleadosDest){
                // Guarda el asunto como turnado
                asunto.getAsunto_detalle().setEmpleado_dest(empleado_dest);
                // Asigna el estatus turnado
                empleado_dest.setComentario(empleado_dest.getComentario() == null ? "" : empleado_dest.getComentario());
                asunto.getAsunto_detalle().setComentario(asunto.getAsunto_detalle().getComentario() == null ? "" : asunto.getAsunto_detalle().getComentario());
                asunto.getAsunto_detalle().setPrioridad(empleado_dest.getPrioridad());
                if (empleado_dest.getFh_limiteDDMMYYYY().equals("")){
                    this.asignarFhLimiteDetalle(asunto.getAsunto_detalle(), request);
                }
                else{
                    asunto.getAsunto_detalle().setFh_limiteDDMMYYYY(empleado_dest.getFh_limiteDDMMYYYY());
                }
                if (empleado_dest.getComentario().length() > Constantes.NO_ACTIVO)
                    asunto.getAsunto_detalle().setComentario(empleado_dest.getComentario());//si tienen comentarios particulares
                asunto = asuntoDao.updateEditarTurno(asunto);
                asunto.setTipo_actualizacion(1);
                asuntoCapturaActualizacionService.saveAsuntoCapturaActualizacion(asunto, request);                
                i++;
                // Envia un correo notificando que se ha creado un asunto
                asunto.getAsunto_detalle().setEmpleado_dest(empleadoService.getEmpleadoById(asunto.getAsunto_detalle().getEmpleado_dest().getId_empleado()));
                if (asunto.getAsunto_detalle().getEmpleado_dest().getArea().getTipo().equals(Area.INTERNA) //Area interna
                    &&asunto.getAsunto_detalle().getEmpleado_dest().getArea().getEnvio_correo().equals(1))
                        this.sendMailAsunto(asunto, urlLogo, request);
            }            
        } catch (Exception e)
        {
            log.error("Ha ocurrido un error en la transaccion",e);
        }
        return asunto;
    }

    /**
     * Guarda la captura del turno
     * @param asunto
     * @param request
     * @return
     */
    public Asunto guardaCapturaTurno(Asunto asunto, HttpServletRequest request)
    {
        try
        {
            Empleado empleado_ses = (Empleado) request.getSession().getAttribute(
                    Constantes.USUARIO_SESION);
                        
            // Asigna fh limite de acuerdo a la prioridad
            this.asignarFhLimite(asunto, request);
            asunto.setEmpleado_capt(empleado_ses);            
            // Obtiene los empleados destinatarios
            List<Empleado> empleadosDest = asunto.getAsunto_detalle().getEmpleadosDest();            
            // contador
            int i = 0;
            asuntoDao.borraCapturaTurno(asunto.getUltimaDetalle().getId_asunto_detalle());
            for (Empleado empleado_dest : empleadosDest)
            {
                // Guarda el asunto como turnado
                asunto.getAsunto_detalle().setEmpleado_dest(empleado_dest);                
                empleado_dest.setComentario(empleado_dest.getComentario() == null ? "" : empleado_dest.getComentario());
                asunto.getAsunto_detalle().setComentario(asunto.getAsunto_detalle().getComentario() == null ? "" : asunto.getAsunto_detalle().getComentario());
                asunto.getAsunto_detalle().setPrioridad(empleado_dest.getPrioridad());
                if (empleado_dest.getFh_limiteDDMMYYYY().equals("")){
                    this.asignarFhLimiteDetalle(asunto.getAsunto_detalle(), request);
                }
                else{
                    asunto.getAsunto_detalle().setFh_limiteDDMMYYYY(empleado_dest.getFh_limiteDDMMYYYY());
                }
                if (empleado_dest.getComentario().length() > Constantes.NO_ACTIVO)
                    asunto.getAsunto_detalle().setComentario(empleado_dest.getComentario());//si tienen comentarios particulares
                // Actualiza en base de datos el asunto
                asunto = asuntoDao.guardaCapturaTurno(asunto);
            }
        } catch (Exception e)
        {
            log.error("Ha ocurrido un error en la transaccion",e);
        }
        return asunto;
    }

    /**
     * Obtiene el destinatario original del ultimo detalle registrado
     * @param asuntosDetalles
     * @return
     */
    public Empleado getLastEmployedFromDetails(List<AsuntoDetalle> asuntosDetalles)
    {
        Empleado empleadoDestinatario = asuntosDetalles.get(asuntosDetalles.size() - Constantes.ACTIVADO).getEmpleado_dest();
        return empleadoDestinatario;
    }

    /**
     * Guarda el finalizado de un asunto
     * @param asunto
     * @param request
     * @param urlLogo
     * @return
     * @throws Exception
     */
    public Asunto saveFinalizar(Asunto asunto, HttpServletRequest request, URL urlLogo) throws Exception
    {
        Empleado empleado_ses = (Empleado) request.getSession().getAttribute(
                Constantes.USUARIO_SESION);
        // Asigna la fecha de registro del asunto
        asunto.getAsunto_detalle().setFh_registro(new Date());

        // Asigna el empleado remitente
        asunto.getAsunto_detalle().setEmpleado_remi(empleado_ses);

        // Asigna el empleado destinatario
        asunto.getAsunto_detalle().setEmpleado_dest(empleado_ses);

        // Asigna la Ip donde se capturo el asunto
        asunto.getAsunto_detalle().setIp(request.getRemoteAddr());

        // Asigna el estatus de finalizado
        asunto.getAsunto_detalle().setEstatus(Constantes.FINALIZADO);
        asunto.setEstatus(Constantes.FINALIZADO);
        // Asigna el Id del asunto
        asunto.getAsunto_detalle().setId_asunto(asunto.getId_asunto());

        // Actualiza en base de datos el asunto
        asunto = asuntoDao.updateAsunto(asunto);
        asunto.setTipo_actualizacion(1);
        asuntoCapturaActualizacionService.saveAsuntoCapturaActualizacion(asunto, request);
        // Envia un correo notificando que se ha creado un asunto        
        log.debug("saveFinalizar en el serviceImpl" + urlLogo);
        //this.sendMailAsunto(asunto, urlLogo);

        return asunto;
    }

    /**
     * Guarda el rechazado de un asunto
     * @param asunto
     * @param request
     * @param urlLogo
     * @return
     * @throws Exception
     */
    public Asunto saveRechazar(Asunto asunto, HttpServletRequest request, URL urlLogo) throws Exception
    {
        Empleado empleado_ses = (Empleado) request.getSession().getAttribute(
                Constantes.USUARIO_SESION);
        // Asigna la fecha de registro del asunto
        asunto.getAsunto_detalle().setFh_registro(new Date());

        // Obtiene el detalle de asunto final
        AsuntoDetalle asuntoDetFinal = asunto.getAsuntos_detalles().get(asunto.getAsuntos_detalles().size() - Constantes.ACTIVO);

        // Asigna el empleado remitente. El destinatario a quien se envio la respuesta inicialmente.
        asunto.getAsunto_detalle().setEmpleado_remi(asuntoDetFinal.getEmpleado_dest());

        // Asigna el empleado destinatario
        asunto.getAsunto_detalle().setEmpleado_dest(
                asuntoDetFinal.getEmpleado_remi());

        // Asigna la fecha limite de atencion
        asunto.getAsunto_detalle().setFh_limite(asuntoDetFinal.getFh_limite());

        // Asigna la Ip donde se capturo el asunto
        asunto.getAsunto_detalle().setIp(request.getRemoteAddr());

        // Asigna el estatus de turnado
        asunto.getAsunto_detalle().setEstatus(Constantes.TURNADO);
        asunto.setEstatus(Constantes.TURNADO);

        // Asigna el Id del asunto
        asunto.getAsunto_detalle().setId_asunto(asunto.getId_asunto());

        // Actualiza en base de datos el asunto
        asunto = asuntoDao.updateAsunto(asunto);
        asunto.setTipo_actualizacion(1);
        asuntoCapturaActualizacionService.saveAsuntoCapturaActualizacion(asunto, request);
        // Envia un correo notificando que se ha creado un asunto
        asunto.getAsunto_detalle().setEmpleado_dest(empleadoService.getEmpleadoById(asunto.getAsunto_detalle().getEmpleado_dest().getId_empleado()));
        if (asunto.getAsunto_detalle().getEmpleado_dest().getArea().getTipo().equals(Area.INTERNA) //Area interna
                    &&asunto.getAsunto_detalle().getEmpleado_dest().getArea().getEnvio_correo().equals(1)){
                    Integer est = asunto.getEstatus();
                    asunto.setEstatus(99);
                    this.sendMailAsunto(asunto, urlLogo, request);
                    asunto.setEstatus(est);
        }
        return asunto;
    }

    /**
     * Actualiza la fecha de lectura del asunto
     * @param asunto
     * @param request
     */
    public void updateFhLectura(Asunto asunto, HttpServletRequest request)
    {
        int num_detalles = asunto.getAsuntos_detalles().size() - 1;
        AsuntoDetalle asuntoDetActual = asunto.getAsuntos_detalles().get(
                num_detalles);

        if (asuntoDetActual.getFh_lectura() == null)
        {
            // Asigna la fecha actual como la fecha de lectura
            asuntoDetActual.setFh_lectura(new Date());
            asuntoDao.updateFhLectura(asuntoDetActual);
        }
    }

    /**
     * Actualiza la fecha de lectura del asunto Ccp
     * @param asunto
     * @param request
     */
    public void updateFhLecturaCcp(Asunto asunto, HttpServletRequest request)
    {
        Empleado empleado_ses = (Empleado) request.getSession().getAttribute(
                Constantes.USUARIO_SESION);
        HashMap<String, Object> asunto_ccp = new HashMap<String, Object>();
        asunto_ccp.put("fh_lectura", new Date());
        asunto_ccp.put("id_empleado", empleado_ses.getId_empleado());
        asunto_ccp.put("id_asunto", asunto.getId_asunto());
        asuntoDao.updateFhLecturaCcp(asunto_ccp);
    }

    /**
     * Pregunta si el empleado en sesion es el destinatario del asunto actual
     * @param asunto
     * @param request
     * @return
     */
    public boolean isEmpleadoDestinatario(Asunto asunto,
            HttpServletRequest request)
    {
        boolean isEmpleadoDest = false;

        Empleado empleado_ses = (Empleado) request.getSession().getAttribute(
                Constantes.USUARIO_SESION);

        // Obtiene el detalle actual
        int num_detalles = asunto.getAsuntos_detalles().size() - 1;
        AsuntoDetalle asuntoDetActual = asunto.getAsuntos_detalles().get(
                num_detalles);

        // Obtiene el empleado destinatario del asunto detalle actual
        Empleado empleado_dest = asuntoDetActual.getEmpleado_dest();

        // Obtiene el estatus del asunto detalle actual
        Integer estatus = asuntoDetActual.getEstatus();

        // Pregunta si el empleado en sesion es el empleado destinatario del
        // asunto detalle actual
        isEmpleadoDest = empleado_ses.getId_empleado().equals(empleado_dest.getId_empleado());

        // Si el estatus es pendiente
        if (estatus.equals(Constantes.PENDIENTE) || estatus.equals(Constantes.TURNADO))
        {
            // Pregunta si el empleado es recepcionista
            boolean isEmpleadoRecep = empleado_ses.getRoles().contains(
                    new Rol(Rol.RECEPCIONISTA));

            // Si el empelado destinatario es recepcionista
            if (isEmpleadoRecep)
            {
                // Carga el empleado destinatario por medio del id
                empleado_dest = empleadoService.getEmpleadoById(empleado_dest.getId_empleado());

                // Obtiene los empleados receptores
                List<Empleado> empleados_recep = empleadoService.listReceptorasFromAreaAndAreaPadre(empleado_dest);

                // Pregunta si el empleado en sesion es receptor
                isEmpleadoDest = empleados_recep.contains(empleado_ses);
            }
        }
        return isEmpleadoDest;
    }

    /**
     * Pregunta si el empleado en sesion es el destinatario del asunto inicial
     * @param asunto
     * @param request
     * @return
     */
    public boolean isEmpleadoRemitenteInicial(Asunto asunto,
            HttpServletRequest request)
    {
        Empleado empleado_ses = (Empleado) request.getSession().getAttribute(
                Constantes.USUARIO_SESION);
        // Obtiene el detalle inicial
        AsuntoDetalle asuntoDetInicial = asunto.getAsuntos_detalles().size() == Constantes.FIRST ? new AsuntoDetalle() : asunto.getAsuntos_detalles().get(Constantes.FIRST);

        // Pregunta si el empleado en sesion es el empleado remitente del asunto
        // detalle inicial
        Boolean empleadoIgual = asuntoDetInicial.getEmpleado_remi().getId_empleado() == null ? false : asuntoDetInicial.getEmpleado_remi().getId_empleado() == empleado_ses.getId_empleado();
        return empleadoIgual;
    }

    /**
     * Envia un correo con la informacion del asunto que se registro
     */
    public void sendMailNuevoAsunto(Asunto asunto, URL urlLogo, HttpServletRequest request)
    {
        try
        {
            // Documentos adjuntos al correo
            List<File> files = null;
            Empleado empleado_remi = empleadoService.getEmpleadoById(asunto.getAsunto_detalle().getEmpleado_remi().getId_empleado());
            // Contenido del mensaje

            for (Empleado destinatario : asunto.getAsunto_detalle().getEmpleadosDest()){
                    String coment = destinatario.getComentarioSaltos().toUpperCase();
                    String contenido = "<BR/><b>Buen d&iacute;a:</b>"
                        + "<BR/>"
                        + "<BR/><b>Se registr&oacute; un nuevo asunto con la siguiente informaci&oacute;n:</b>"
                        + "<BR/><font color='#BA2025'><b>No.Asunto: </b></font>"
                        + asunto.getId_asunto()
                        + "<BR/><font color='#BA2025'><b>Folio: </b></font>"
                        + asunto.getFolio()
                        + "<BR/><font color='#BA2025'><b>Asunto: </b></font>"
                        + asunto.getAsunto()
                        + "<BR/><font color='#BA2025'><b>Descripci&oacute;n: </b></font>";
                        if (asunto.getConfidencial().equals(1))
                            contenido = contenido + "Confidencial.";
                        else
                            contenido = contenido + ((coment==null||coment=="")?asunto.getDescripcionSaltos().toUpperCase():coment);
                        contenido = contenido + "<BR/><font color='#BA2025'><b>Fecha: </b></font>"
                        + asunto.getFh_oficioDDMMYYYY()
                        + "<BR/><font color='#BA2025'><b>Fecha de recepci&oacute;n: </b></font>"
                        + asunto.getFh_oficioDDMMYYYY()
                        + "<BR/><font color='#BA2025'><b>Fecha l&iacute;mite de Atenci&oacute;n: </b></font>"
                        + (asunto.getAsunto_detalle().getFh_limiteDDMMYYYY()==null?"":asunto.getAsunto_detalle().getFh_limiteDDMMYYYY())
                        + "<BR/><font color='#BA2025'><b>Remitente: </b></font>"
                        + empleado_remi.getNombre_completo()
                        + "<BR/><font color='#BA2025'><b>Destinatario: </b></font>"
                        + destinatario.getNombre_completo()
                        + "<BR/>"
                        + "<BR/>Le sugerimos ingresar al Sistema Automatizado de Control de Gesti&oacute;n para darle seguimiento en la siguiente direcci&oacuten:"
                        + "<BR/>http://gestion.economia.gob.mx/";
                    String html = Util.getHtml(contenido);
                    destinatario = empleadoService.getEmpleadoById(destinatario.getId_empleado());
                    correoService.send(destinatario.getCorreo_alterno()==null?destinatario.getCorreo():destinatario.getCorreo_alterno(),
                        "SACG-SE. Se le ha asignado un nuevo asunto: " + asunto.getAsunto(), html,
                        contenido, files, urlLogo,
                        request);

            }
            

        } catch (Exception e)
        {
            log.error("Ha ocurrido un error en la transaccion",e);
        }
    }

    /**
     * Envia un correo con la informacion del asunto que se registro
     */
    public void sendMailAsunto(Asunto asunto, URL urlLogo, HttpServletRequest request)
    {
        try
        {
            // Documentos adjuntos al correo
            
            List<File> files = new ArrayList<File>();
            /*for (Documento documento : asunto.getAsunto_detalle().getDocumentos())
            {
                byte[] bytes = documento.getBlob();
                //String ruta = documento.getRuta_adjunto();
                String nombre = documento.getDocumento();
                String RUTA = ResourceBundle.getBundle(Constantes.PROPERTIES).getString("documentos.ruta");  //la ruta en donde se va a escribir el archivo en el servidor
                log.debug("Ruta: " + RUTA);
                log.debug("Nombre: " + nombre);
                log.debug("bytes: " + bytes);
                try
                {
                    OutputStream out = new FileOutputStream(RUTA + nombre);
                    out.write(bytes);
                    out.close();
                } catch (Exception exc)
                {
                    log.error(exc);
                }
                log.debug("Ruta: " + RUTA);
                log.debug("Nombre: " + nombre);
                log.debug("bytes: " + bytes);
                files.add(new File(RUTA + nombre));
            }*/


            Empleado empleado_remi = empleadoService.getEmpleadoById(asunto.getAsunto_detalle().getEmpleado_remi().getId_empleado());
            Empleado empleado_dest = empleadoService.getEmpleadoById(asunto.getAsunto_detalle().getEmpleado_dest().getId_empleado());
            // Contenido del mensaje
            String contenido = "<BR/><b>Buen d&iacute;a:</b>" + "<BR/><BR/>";
            Integer estatus = asunto.getAsunto_detalle().getEstatus();
            
            if (estatus.equals(Constantes.ATENDIDO))
                contenido += "<BR/><b>Le notificamos que se le ha asignado un asunto para validarlo, con la siguiente informaci&oacute;n:</b>";
            else if (estatus.equals(Constantes.TURNADO))
                contenido += "<BR/><b>Le notificamos que se le ha asignado un asunto para atenderlo, con la siguiente informaci&oacute;n:</b>";
            else if (estatus.equals(Constantes.FINALIZADO))
                contenido += "<BR/><b>Le notificamos que se ha finalizado un asunto con la siguiente informaci&oacute;n:</b>";
            else if (estatus.equals(99)) // Rechazado equivale a turnado nuevamente
                contenido += "<BR/><b>Le notificamos que se le ha vuelto a asignar un asunto para atenderlo, con la siguiente informaci&oacute;n:</b>";

            contenido += "<BR/><font color='#BA2025'><b>No.Asunto: </b></font>"
                    + asunto.getId_asunto()
                    + "<BR/><font color='#BA2025'><b>Folio: </b></font>"
                    + asunto.getFolio()
                    + "<BR/><font color='#BA2025'><b>Asunto: </b></font>"
                    + asunto.getAsunto()
                    + "<BR/><font color='#BA2025'><b>Descripci&oacute;n: </b></font>";
                    if (asunto.getConfidencial().equals(1))
                        contenido = contenido + "Confidencial.";
                    else
                        contenido = contenido + asunto.getDescripcionSaltos().toUpperCase();
                    contenido= contenido + "<BR/><font color='#BA2025'><b>Fecha del documento: </b></font>"
                    + asunto.getFh_oficioDDMMYYYY()
                    + "<BR/><font color='#BA2025'><b>Fecha l&iacute;mite de Atenci&oacute;n: </b></font>"
                    + (asunto.getAsunto_detalle().getFh_limiteDDMMYYYY()==null?"":asunto.getAsunto_detalle().getFh_limiteDDMMYYYY())
                    + "<BR/><font color='#BA2025'><b>Remitente: </b></font>"
                    + empleado_remi.getNombre_completo()
                    + "<BR/><font color='#BA2025'><b>Destinatario: </b></font>"
                    + empleado_dest.getNombre_completo()
                    + "<BR/><font color='#BA2025'><b>Comentario: </b></font>";
                    if (asunto.getConfidencial().equals(1))
                        contenido = contenido + "Confidencial.";
                    else
                        contenido = contenido + asunto.getAsunto_detalle().getComentarioSaltos().toUpperCase();
                    contenido = contenido + "<BR/>Le sugerimos ingresar al Sistema Automatizado de Control de Gesti&oacute;n para darle seguimiento en la siguiente direcci&oacuten:"
                    + "<BR/>http://gestion.economia.gob.mx/";

            String html = Util.getHtml(contenido);

            String para = (empleado_dest.getCorreo_alterno()==null? empleado_dest.getCorreo() : empleado_dest.getCorreo_alterno());

            String asuntoCorreo = "";
            if (estatus.equals(Constantes.ATENDIDO))
                asuntoCorreo += "SACG-SE. Se le ha asignado un asunto para validarlo";
            else if (estatus.equals(Constantes.TURNADO))
                asuntoCorreo += "SACG-SE. Se le ha asignado un asunto para atenderlo: ";
            else if (estatus.equals(Constantes.FINALIZADO))
                asuntoCorreo += "SACG-SE. Se ha finalizado un asunto";
            else if (estatus.equals(99))
                asuntoCorreo += "SACG-SE. Se ha vuelto a asignar un asunto para atenderlo";
            correoService.send(para, asuntoCorreo + asunto.getAsunto(), html, contenido, files, urlLogo, request);

            // Correo para Ccp
            String contenidoCcp = "<BR/><b>Buen d&iacute;a:</b>"
                    + "<BR/>"
                    + "<BR/><b>Le informamos la situaci&oacute;n actual del asunto:</b>"
                    + "<BR/><font color='#BA2025'><b>No.Asunto: </b></font>"
                    + asunto.getId_asunto()
                    + "<BR/><font color='#BA2025'><b>Folio: </b></font>"
                    + asunto.getFolio()
                    + "<BR/><font color='#BA2025'><b>Asunto: </b></font>"
                    + asunto.getAsunto()
                    + "<BR/><font color='#BA2025'><b>Descripci&oacute;n: </b></font>";
                    if (asunto.getConfidencial().equals(1))
                        contenidoCcp = contenidoCcp + "Confidencial.";
                    else
                        contenidoCcp = contenidoCcp + asunto.getDescripcionSaltos().toUpperCase();
                    contenidoCcp= contenidoCcp + "<BR/><font color='#BA2025'><b>Fecha: </b></font>"
                    + asunto.getFh_oficioDDMMYYYY()
                    + "<BR/><font color='#BA2025'><b>Fecha l&iacute;mite de Atencion: </b></font>"
                    + (asunto.getAsunto_detalle().getFh_limiteDDMMYYYY()==null?"":asunto.getAsunto_detalle().getFh_limiteDDMMYYYY())
                    + "<BR/><font color='#BA2025'><b>Remitente: </b></font>"
                    + empleado_remi.getNombre_completo()
                    + "<BR/><font color='#BA2025'><b>Destinatario: </b></font>"
                    + empleado_dest.getNombre_completo()
                    + "<BR/><font color='#BA2025'><b>Comentario: </b></font>";
                    if (asunto.getConfidencial().equals(1))
                        contenidoCcp = contenidoCcp + "Confidencial.";
                    else
                        contenidoCcp = contenidoCcp + asunto.getAsunto_detalle().getComentarioSaltos().toUpperCase();
                    contenidoCcp= contenidoCcp + "<BR/><b>Le sugerimos ingresar al Sistema Automátizado de Control de Gesti&oacute;n para darle seguimiento en la siguiente direcci&oacute;."
                    + "<BR/>http://gestion.economia.gob.mx/";

            String htmlCcp = Util.getHtml(contenidoCcp);

            List<String> correosCcp = new ArrayList<String>();
            for (Empleado empleado : asunto.getEmpleadosCcp())
            {
                empleado = empleadoService.getEmpleadoById(empleado.getId_empleado());
                if (!correosCcp.contains(empleado.getCorreo()))
                    if (empleado.getArea().getEnvio_correo().equals(1))
                        correosCcp.add(empleado.getCorreo_alterno().equals("")?empleado.getCorreo():empleado.getCorreo_alterno());
            }
            if (!correosCcp.isEmpty())
            {
                String[] paraCcp = (String[]) correosCcp.toArray(new String[0]);
                correoService.send(paraCcp,
                        "SACG-SE. Ha sido copiado en un asunto", htmlCcp,
                        contenido, files, urlLogo, request);
            }
        } catch (Exception e)
        {
            log.error("Ha ocurrido un error en la transaccion",e);
        }
    }

    /**
     * Las reglas de negocio para el flujo del sistema
     * @param asunto
     * @param request
     */
    public void loadBtnDetalleAsunto(Asunto asunto, HttpServletRequest request)
    {
        //	Obtiene el empleado autenticado en la sesion
        Empleado empleado_ses = (Empleado) request.getSession().getAttribute(
                Constantes.USUARIO_SESION);

        //	Obtiene el asunto detalle actual
        int num_detalles = asunto.getAsuntos_detalles().size() - 1;
        AsuntoDetalle asuntoDetActual = asunto.getAsuntos_detalles().size() == Constantes.NO_ACTIVO ? asunto.getAsunto_detalle() : asunto.getAsuntos_detalles().get(num_detalles);
        AsuntoDetalle asuntoDetInicial = asunto.getAsuntos_detalles().size() == Constantes.NO_ACTIVO ? asunto.getAsunto_detalle() : asunto.getAsuntos_detalles().get(Constantes.FIRST);

        //	Obtiene el empleado destinatario del asunto detalle actual
        Empleado empleado_dest_act = empleadoService.getEmpleadoById(asuntoDetActual.getEmpleado_dest().getId_empleado());

        Empleado empleado_captura = empleadoService.getEmpleadoById(asunto.getEmpleado_capt().getId_empleado());
        
        //	Obtiene los empleados receptores
        List<Empleado> empleados_recep = empleadoService.listReceptorasFromAreaAndAreaPadre(empleado_dest_act);

        //	Obtiene las areas del empleado autenticado en la sesion
        //List<Area> areas = this.areaService.getAreasByIdPadre(empleado_ses.getId_area());

        List<Area> areas = this.areaService.getAreasByIdAbuelo(empleado_ses.getId_area());

        List<Area> areasDespliegue = this.areaService.getAreasFromDespĺiegue(empleado_ses.getArea());

        // si el empleado en session y el destinatario son de la misma area
        boolean isSameArea = empleado_ses.getArea().getId_area().equals(empleado_dest_act.getArea().getId_area());

        List<Integer> listInAreaDespliegue = areaService.getListInAreaDespliegue(empleado_ses.getArea().getId_area());

        boolean isDestAreaDespliegue = false;

        if (!listInAreaDespliegue.isEmpty()){
            isDestAreaDespliegue = listInAreaDespliegue.contains(empleado_dest_act.getArea().getId_area());
        }        
        
        // si tiene areas para turnar
        boolean hasAreasParaTurnar = !areas.isEmpty() || !areasDespliegue.isEmpty();
        //	Es empleado Oficial
        boolean isOficial = empleado_ses.getRoles().contains(new Rol(Rol.OFICIAL));
        //	Es empleado Revisor        
        boolean isRevisor = empleado_ses.getRoles().contains(new Rol(Rol.REVISOR));
        //	Es empleado Capturista
        boolean isCapturista = empleado_ses.getRoles().contains(new Rol(Rol.CAPTURISTA));
        //	Es empleado tiene rol Finalizar
        boolean isRolFinalizar = empleado_ses.getRoles().contains(new Rol(Rol.FINALIZAR));
        //	Es empleado recepcionista
        //boolean isRecepcionista = empleados_recep.contains(empleado_ses);
        boolean isRecepcionista = empleado_ses.getRoles().contains(new Rol(Rol.RECEPCIONISTA)) || empleados_recep.contains(empleado_ses);
        //	Es empleado remitente inicial del asunto
        boolean isRemitenteIni = this.isEmpleadoRemitenteInicial(asunto, request);
        //	Es empleado Oficina del Secretario
        boolean isOficinaSecretario = empleado_ses.getArea().getListado_area().equals(Integer.valueOf(Constantes.ACTIVADO));

        //	El estatus del asunto es pendiente
        boolean isEstatusPendiente = asunto.getEstatus().equals(Constantes.PENDIENTE);
        //	Si el remitente es externo
        boolean isRemitenteExterno = asuntoDetActual.getEmpleado_remi().getArea().getTipo().equals(Area.EXTERNA);
        //      El estatus del asunto es archivado
        boolean isArchivado = asunto.getEstatus().equals(Constantes.ARCHIVADO);

        //	Guardar el asunto
        boolean isGuardar = asunto.getId_asunto() < Constantes.NO_ACTIVO && asunto.getArchivado().equals(Constantes.NO_ACTIVO); // solo si el asunto es nuevo y no se archiva
        //	El estatus del asunto es finalizado
        boolean isEstatusFinalizado = asunto.getEstatus().equals(Constantes.FINALIZADO);
        //	El estatus del asunto esta en captura
        boolean isEstatusEnCaptura = asunto.getEstatus().equals(Constantes.EN_CAPTURA);
        //	El estatus del asunto es supervisado
        boolean isEstatusSupervisado = asunto.getEstatus().equals(Constantes.SUPERVISADO);
        //	El estatus del asunto esta en revision
        boolean isEstatusEnRevision = asunto.getEstatus().equals(Constantes.EN_REVISION);
        //	El estatus del asunto es revisado en captura
        boolean isEstatusRevisadoCaptura = asunto.getEstatus().equals(Constantes.REVISADO_CAPTURA);
        //	El estatus del asunto esta en supervision captura
        boolean isEstatusEnSupervision = asunto.getEstatus().equals(Constantes.EN_SUPERVISION_CAPTURA);
        //	El estatus del asunto es atendido
        boolean isEstatusAtendido = asunto.getEstatus().equals(Constantes.ATENDIDO);
        //    	El estatus del asunto es turnado
        boolean isEstatusTurnado = asunto.getEstatus().equals(Constantes.TURNADO);
        //      El estatus del asunto es Confidencial
        //boolean isConfidencial = asunto.getEstatus().equals(Constantes.CONFIDENCIAL);
        boolean isConfidencial = asunto.getConfidencial().equals(Constantes.ACTIVADO); //AGG Confidencial -> booleano 20111109

        boolean isAreaCaptura = asunto.getEmpleado_capt().getArea().getId_area().equals(empleado_ses.getId_area());

        // Obtiene si la consulta es con copia para
        boolean isDetalleCcp = false;
        boolean isRecepcionistaCcp = false;
        List<AsuntoDetalle> asuntos_detalle = asunto.getAsuntos_detalles();
        if (isRecepcionista) {
            for (AsuntoDetalle asuntoDetalle : asuntos_detalle)
            {
                isDetalleCcp = isDetalleCcp || asuntoDetalle.getEmpleadosCcpDetalle().contains(empleado_ses);
                for (Empleado empleadoCcp : asuntoDetalle.getEmpleadosCcpDetalle())
                {
                    isRecepcionistaCcp = isRecepcionistaCcp || empleadoCcp.getArea().getId_area().equals(empleado_ses.getArea().getId_area());
                }
            }
        }

        for (Empleado empleadoCcp : asunto.getEmpleadosCcp())
        {
            isRecepcionistaCcp = isRecepcionistaCcp || empleadoCcp.getArea().getId_area().equals(empleado_ses.getArea().getId_area());
        }

        boolean isCcp = (asunto.getEmpleadosCcp().contains(empleado_ses) || isDetalleCcp || isRecepcionistaCcp);
        //  Es empleado Destinatario

        boolean isDestinatario = empleado_dest_act.getId_empleado().equals(empleado_ses.getId_empleado());

        boolean isFinalizar = (isDestinatario && compruebaRemitenteDestinatarioDetalle(asuntoDetInicial, empleado_ses) && isEstatusAtendido) || (isRolFinalizar && isEstatusAtendido);

        boolean isRechazar = (((isRecepcionista || isDestinatario) && isSameArea && (isEstatusAtendido|| isEstatusFinalizado)) || isFinalizar);

        boolean isDarVoBo = ((isDestinatario || (isRecepcionista && isSameArea)) && !isRemitenteIni && isEstatusAtendido);

        boolean isAtender = ((isDestinatario && (isEstatusTurnado || isEstatusPendiente)) || (isRecepcionista && (isEstatusPendiente || isEstatusTurnado) && isSameArea));

        boolean isGestionarCcp = (isCcp && (isEstatusTurnado || isEstatusAtendido || isEstatusEnRevision || isEstatusPendiente || isEstatusRevisadoCaptura));

        boolean isReturnar = isEstatusTurnado && asuntoDetActual.getEmpleado_remi().getId_area().equals(empleado_ses.getArea().getId_area());

        boolean isTurnar = ((isDestinatario && (isEstatusTurnado || isEstatusAtendido || isEstatusEnRevision))
                             || (isRecepcionista && (isEstatusPendiente || isEstatusTurnado || isEstatusRevisadoCaptura)))
                             && hasAreasParaTurnar
                             && (isSameArea || isDestAreaDespliegue)
                             || isDarVoBo;
                
                //isTurnar = isTurnar || (isEstatusFinalizado && isCapturista && isOficinaSecretario);
                isTurnar = isTurnar || (isEstatusFinalizado && isCapturista);

                //isTurnar = isTurnar || (isEstatusTurnado && isCapturista && isAreaCaptura);

                //isTurnar = isTurnar || isCcp;
                
        //Se habilita modificacion en cualquier estatus previo a Atendido para recepcionista y capturista de area remitente o captura y para el remitente
        /*boolean isModificarSintesis = ((isEstatusPendiente || isEstatusTurnado) //Inicial o Turnado
                                       && (compruebaRemitenteDetalle(asuntoDetInicial,empleado_ses) 
                                           || (asunto.getEmpleado_capt().getArea().getId_area().equals(empleado_ses.getArea().getId_area())
                                               && (isRecepcionista || isCapturista)))
                                       && !hasDetalleAtendido(asunto) // NO tiene turnos atendidos
                                       && !asunto.getFolio().equals(""));//Ya se generó folio*/

        boolean isModificarEliminar = ((isEstatusEnCaptura || isEstatusSupervisado || isEstatusRevisadoCaptura || isArchivado) && (isCapturista || isRevisor || isRemitenteIni) && (!asunto.getFolio().equals("")));

        /*isModificarEliminar = isModificarEliminar || isModificarSintesis;*/

        boolean isRevisarEliminar = ((isEstatusEnRevision || isEstatusEnSupervision) && (isRevisor || isRemitenteIni ));

        boolean isEnviarRevision = (isOficinaSecretario && (isEstatusEnCaptura || isEstatusRevisadoCaptura ||  isGuardar) && !isCcp && !(isRevisor/* || isRemitenteIni*/));

        boolean isEnviarSupervision = (!isOficinaSecretario && (isEstatusEnCaptura || isEstatusSupervisado) && !isCcp && !(isRevisor || isRemitenteIni) && !isRemitenteExterno);

        boolean isEnviarDestinatario = ((isEstatusEnCaptura || isEstatusRevisadoCaptura || ((isEstatusEnSupervision || isEstatusEnRevision) && (isRevisor || isRemitenteIni)) || isEstatusSupervisado || (isArchivado && isCapturista && isOficinaSecretario)) && (!isCcp || isRecepcionistaCcp));

        boolean isEnviarCaptura = false;

        boolean isGenerarVolante = isEstatusTurnado && asuntoDetActual.getEmpleado_remi().getId_area().equals(empleado_ses.getArea().getId_area());
                isGenerarVolante = isGenerarVolante || isEstatusPendiente;

        boolean isRegistrar = asunto.getId_asunto() < Constantes.NO_ACTIVO && (isRemitenteExterno || asunto.getArchivado().equals(Constantes.ARCHIVADO));// solo si el asunto es nuevo ó con estatus de archivado y ademas el remitente es externo.

        boolean isArchivar = asunto.getId_asunto() < Constantes.NO_ACTIVO && asunto.getArchivado().equals(Constantes.ARCHIVADO);

        boolean isFromGestion = asuntoDetActual.getEmpleado_remi().getArea().getId_area().equals(150); //remitente es gestión.

        boolean isToAgenda = asuntoDetActual.getEmpleado_dest().getArea().getId_area().equals(1375); //destinatario es agenda.

        boolean isEmpleadoAgenda = empleado_ses.getArea().getId_area().equals(1375); //empleado de agenda.

        boolean isGestionToAgenda = isFromGestion && isToAgenda && isEmpleadoAgenda;

        boolean isBitacora = true;
        /*boolean isBitacora = empleado_ses.getArea().getId_area().equals(150); // OCS.
                isBitacora = isBitacora || empleado_ses.getArea().getId_area().equals(1375); // Agenda.
                isBitacora = isBitacora || empleado_ses.getArea().getId_area().equals(1374); // ATC.*/

        boolean isEditarTurno = isEstatusTurnado && asuntoDetActual.getEmpleado_remi().getId_area().equals(empleado_ses.getArea().getId_area());
                isEditarTurno = isEditarTurno && asunto.getAtencionParcial()==0; //no está en tramite
                isTurnar = isTurnar && (asunto.getAtencionParcial()==0);//no está en tramite
                isGenerarVolante = isGenerarVolante && (asunto.getAtencionParcial()==0);//no está en tramite

        // Muestre el boton de generar volante si esta En Captura
        isGenerarVolante = isGenerarVolante || isEstatusEnCaptura;

        // No muestre el boton de VoBo cuando el capturista sea el mismo que este atendiendo el asunto
        Integer login_area = empleado_ses.getId_area();
        Integer captura_area = empleado_captura.getId_area();
        if (captura_area != null) {
            if (login_area.intValue() == captura_area.intValue()){
                isDarVoBo = false;                
                isFinalizar = true;
            }
        }

        // Incluir el botón de Generar Volante Atendido
        // Rodolfo Milano Oliveros
        // Ticket 150 - Fecha: 02/12/2013
        Integer estatusAsunto = asunto.getEstatus();
        if (estatusAsunto == 2){
            isGenerarVolante = true;
        }

        // Mostrar el boton de Turnar, si el asunto tiene una atencion parcial
        // en blanco con origen el que esta atendiendo el asunto
        // Rodolfo Milano Oliveros
        // Ticket 144 - Fecha: 21/11/2013
        Integer origenAtencionParcial;
        if (asunto.getAsuntoAtencionParcial() != null){
            origenAtencionParcial = asunto.getAsuntoAtencionParcial().getEmpleado_remi().getId_area();
        } else {
            origenAtencionParcial = -1;
        }

        if (captura_area != null && origenAtencionParcial != -1) {
            if (login_area.intValue() == origenAtencionParcial.intValue()){
                isTurnar = true;
            }
        }

        if (isFinalizar)
            request.getSession().setAttribute("showFinalizarBtn", "showFinalizarBtn");
        if (isRechazar)
            request.getSession().setAttribute("showRechazarBtn", "showRechazarBtn");

        if (isDarVoBo)
            request.getSession().setAttribute("showDarVoBoBtn", "showDarVoBoBtn");
        if (isEstatusEnRevision)
            request.getSession().setAttribute("showAprobadoRevisionBtn", "showAprobadoRevisionBtn");
        if (isEstatusEnSupervision)
            request.getSession().setAttribute("showAprobadoSupervisionBtn", "showAprobadoSupervisionBtn");
        if (isAtender)
            request.getSession().setAttribute("showAtenderBtn", "showAtenderBtn");
        if (isTurnar)
            request.getSession().setAttribute("showTurnarBtn", "showTurnarBtn");
        if (isReturnar)
            request.getSession().setAttribute("showReturnarBtn", "showReturnarBtn");
        if (isGestionarCcp)
            request.getSession().setAttribute("showGestionarCcpBtn", "showGestionarCcpBtn");
        /*if (isModificarSintesis){
            request.getSession().setAttribute("readonlyNoSintesis", "true");
        }*/
        else{
            request.getSession().setAttribute("readonlyNoSintesis", "false");
        }
        if (isModificarEliminar)
        {
            request.getSession().setAttribute("showModificarBtn", "showModificarBtn");
            /*if (!isModificarSintesis)*/
            request.getSession().setAttribute("showEliminarBtn", "showEliminarBtn");
        }
        if(isGestionToAgenda){
            request.getSession().setAttribute("showGestionToAgendaBtn", "showGestionToAgendaBtn");
        }
        if (isRevisarEliminar)
        {
            request.getSession().setAttribute("showRevisarBtn", "showRevisarBtn");
            request.getSession().setAttribute("showEliminarBtn", "showEliminarBtn");
        }
        if (isEnviarRevision)
            request.getSession().setAttribute("showEnviarRevisionBtn", "showEnviarRevisionBtn");
        if (isEnviarDestinatario)
            request.getSession().setAttribute("showEnviarDestinatarioBtn", "showEnviarDestinatarioBtn");
        if (isEnviarSupervision)
            request.getSession().setAttribute("showEnviarSupervisionBtn", "showEnviarSupervisionBtn");

        if (isEnviarCaptura)
            request.getSession().setAttribute("showEnviarCapturaBtn", "showEnviarCapturaBtn");
        if (isGenerarVolante)
            request.getSession().setAttribute("showGenerarVolanteBtn", "showGenerarVolanteBtn");
        if (isGuardar)
            request.getSession().setAttribute("showGuardarBtn", "showGuardarBtn");
        // Elimina de sesion la url del boton regresar y la sube a request
        if (request.getSession().getAttribute("btnRegresar") != null)
        {
            request.setAttribute("btnRegresar", request.getSession().getAttribute("btnRegresar"));
            request.getSession().removeAttribute("btnRegresar");
        }
        // Si es registrar, entonces borrar el resto de los botones
        if (isRegistrar)
            request.getSession().setAttribute("showRegistrarBtn", "showRegistrarBtn");
        // Si se archiva la captura
        if (isArchivar)
            request.getSession().setAttribute("showArchivarBtn", "showArchivarBtn");
        if (isBitacora)
            request.getSession().setAttribute("showBitacoraBtn", "showBitacoraBtn");
        if (isEditarTurno)
            request.getSession().setAttribute("showEditarTurnoBtn", "showEditarTurnoBtn");
    }

    /**
     * Comprueba que no tenga turnos atendidos
     * @param 
     * @param 
     * @return
     */
    public Boolean hasDetalleAtendido(Asunto asunto)
    {
        Boolean found = false;
        List<AsuntoDetalle> asuntos_detalle = asunto.getAsuntos_detalles();
        for (AsuntoDetalle asuntoDetalle : asuntos_detalle){
            found = found || asuntoDetalle.getEstatus().equals(Constantes.ATENDIDO);
            found = found || asuntoDetalle.getEstatus().equals(Constantes.FINALIZADO);
        }        
        
        return found;
    }

    /**
     * Comprueba si el usario en session es el recepcionista del remitente o destinatario del primer detalle
     * @param asuntoDetalle
     * @param usuarioSession
     * @return
     */
    public Boolean compruebaRemitenteDestinatarioDetalle(AsuntoDetalle asuntoDetalleIncial, Empleado usuarioSession)
    {
        Boolean found = false;

        if (asuntoDetalleIncial.getEmpleado_remi().getId_empleado() == null)
            found = false;
        else // si es el recepcionista del remitente o destinatario del primer detalle
            found = usuarioSession.getRoles().contains(new Rol(Rol.RECEPCIONISTA))
                    && (asuntoDetalleIncial.getEmpleado_remi().getArea().getId_area().equals(usuarioSession.getArea().getId_area())
                    || asuntoDetalleIncial.getEmpleado_dest().getArea().getId_area().equals(usuarioSession.getArea().getId_area()));
        return found;
    }

    /**
     * Comprueba si el usario en session es el recepcionista del remitente o destinatario del primer detalle
     * @param asuntoDetalle
     * @param usuarioSession
     * @return
     */
    public Boolean compruebaRemitenteDetalle(AsuntoDetalle asuntoDetalle, Empleado usuarioSession)
    {
        Boolean found = false;

        if (asuntoDetalle.getEmpleado_remi().getId_empleado() == null)
            found = false;
        else // si es el recepcionista o capturista del remitente del detalle
            found = (usuarioSession.getRoles().contains(new Rol(Rol.CAPTURISTA)) || usuarioSession.getRoles().contains(new Rol(Rol.RECEPCIONISTA)))
                    && (asuntoDetalle.getEmpleado_remi().getArea().getId_area().equals(usuarioSession.getArea().getId_area()));
        return found;
    }

    /**
     * Borra las banderas para mostrar botones en el detalle del asunto
     * @param request
     */
    public void removeButtonsDetalle(HttpServletRequest request)
    {
        request.getSession().removeAttribute("showFinalizarBtn");
        request.getSession().removeAttribute("showRechazarBtn");
        request.getSession().removeAttribute("showDarVoBoBtn");
        request.getSession().removeAttribute("showAtenderBtn");
        request.getSession().removeAttribute("showTurnarBtn");
        request.getSession().removeAttribute("showTurnarCcpBtn");
        request.getSession().removeAttribute("showModificarBtn");
        request.getSession().removeAttribute("showGestionToAgendaBtn");
        request.getSession().removeAttribute("readonlyNoSintesis");
        request.getSession().removeAttribute("showEliminarBtn");
        request.getSession().removeAttribute("showRevisarBtn");
        request.getSession().removeAttribute("showEliminarBtn");
        request.getSession().removeAttribute("showEnviarRevisionBtn");
        request.getSession().removeAttribute("showEnviarDestinatarioBtn");
        request.getSession().removeAttribute("showEnviarSupervisionBtn");
        request.getSession().removeAttribute("showEnviarCapturaBtn");
        request.getSession().removeAttribute("showGenerarVolanteBtn");
        request.getSession().removeAttribute("btnRegresar");
        request.getSession().removeAttribute("showRegistrarBtn");
        request.getSession().removeAttribute("showGuardarBtn");
        request.getSession().removeAttribute("showAprobadoRevisionBtn");
        request.getSession().removeAttribute("showArchivarBtn");
        request.getSession().removeAttribute("showBitacoraBtn");
        request.getSession().removeAttribute("showReturnarBtn");
        request.getSession().removeAttribute("showEditarTurnoBtn");
    }

    /**
     * Obtiene la firma de un asunto
     * @param asunto
     * @return
     */
    public Firma getFirmaAsunto(Asunto asunto)
    {
        Firma firma = new Firma();
        // "todo" eliminar una vez implementado
        asunto.setId_asunto(12);
        asunto.getAsunto_detalle().setId_asunto_detalle(132);

        firma.setId_solicitud(asunto.getId_asunto() + ""); // Identificador de
        // la solicitud
        // (id_asunto)
        firma.setTipo_asunto(asunto.getEstatus() + ""); // 0 -> Tipo de asunto
        // (Estatus_asunto)
        firma.setRfc_usuario(asunto.getAsunto_detalle().getEmpleado_remi().getRfc()); // GORV8309207R2 -> RFC del usuario
        // Informacion general
        firma.setId_asunto(asunto.getId_asunto() + ""); // 12 ->id_asunto
        firma.setEstatus(asunto.getEstatus() + ""); // 0 -> estatus
        firma.setNo_asunto(asunto.getNo_oficio()); //100-100-100-100-00554158DFSD
        // -> no_oficio
        firma.setAsunto(asunto.getAsunto()); // SOLICITUD DE VACACIONES ->
        // asunto
        firma.setDescripcion(asunto.getDescripcion()); // SOLICITUD DE VACIONES
        // PARA LOS EMPLEADOS
        // DEL AREA DE
        // SISTEMAS DE LA SECRETARIA DE ECONOMIA -> descripcion
        firma.setId_remitente(asunto.getAsunto_detalle().getEmpleado_remi().getId_empleado()
                + ""); // 125-> id_remitente
        firma.setId_destinatario(asunto.getAsunto_detalle().getEmpleado_dest().getId_empleado()
                + ""); // 250-> id_destinatario
        firma.setFh_asunto(Util.formatearFecha(asunto.getFh_oficio(),
                "dd/MM/yyyy HH:mm:ss")); // 05/01/2011 15:12:32:11 ->
        // fh_registro
        firma.setFh_recepcion(Util.formatearFecha(asunto.getFh_recepcion(),
                "dd/MM/yyyy HH:mm:ss")); // 04/01/2011 15:12:32:11 ->
        // fh_recepcion

        firma.setFh_solicitud(Util.formatearFecha(asunto.getFh_oficio(),
                "dd/MM/yyyy HH:mm:ss")); // 07/01/2011 15:12:32:11 -> fecha de
        // solicitud

        List<String> rutas_doctos = new ArrayList<String>();
        List<Documento> documentos = asunto.getAsunto_detalle().getDocumentos();
        for (Documento documento : documentos)
        {
            rutas_doctos.add(documento.getRuta_adjunto());
        }
        firma.setRutas_doctos(rutas_doctos);
        return firma;
    }

    /**
     * Asigna la prioridad del documento
     * @param asunto
     * @param request 
     */
    public void asignarFhLimite(Asunto asunto, HttpServletRequest request)
    {
        Prioridad prioridad = asunto.getAsunto_detalle().getPrioridad();
        prioridad = this.prioridadService.getPrioridad(prioridad.getId_prioridad());
        prioridad = prioridad == null ? new Prioridad() : prioridad;        
        if (!(prioridad.getDias()==null))
        {
            //Si el no. de dias es mayor a cero, entonces asignar el rango en dias correspondientes
            if (prioridad.getDias() > Constantes.FIRST)
            {
                Date fh_limite = Util.fechaHabil(new GregorianCalendar(), prioridad.getDias()).getTime();
                asunto.getAsunto_detalle().setFh_limite(fh_limite);
                asunto.getAsunto_detalle().setFh_limiteDDMMYYYY(Util.formatearFecha(fh_limite));
            }
            // si el no. de dias para la prioridad seleccionada es 0, entonces no existe una prioridad, se asigna nulo
            if (prioridad.getDias().equals(Constantes.FIRST))
            {
                asunto.getAsunto_detalle().setFh_limite(null);
                asunto.getAsunto_detalle().setFh_limiteDDMMYYYY(null);
            }
        }
        // Si el no. de dias es menor a cero, sigfica que el usuario selecciono la fecha limite de contestacion del documento
        // viene definido en el asunto.getAsunto_detalle().setFh_limite, no hay nada que hacer
    }

    /**
     * Asigna la prioridad del documento
     * @param asunto
     * @param request
     */
    public void asignarFhLimiteDetalle(AsuntoDetalle asuntoDetalle, HttpServletRequest request)
    {
        Prioridad prioridad = asuntoDetalle.getPrioridad();
        prioridad = this.prioridadService.getPrioridad(prioridad.getId_prioridad());
        prioridad = prioridad == null ? new Prioridad() : prioridad;
        if (!(prioridad.getDias()==null))
        {
            //Si el no. de dias es mayor a cero, entonces asignar el rango en dias correspondientes
            if (prioridad.getDias() > Constantes.FIRST)
            {
                Date fh_limite = Util.fechaHabil(new GregorianCalendar(), prioridad.getDias()).getTime();
                asuntoDetalle.setFh_limite(fh_limite);
                asuntoDetalle.setFh_limiteDDMMYYYY(Util.formatearFecha(fh_limite));
            }
            // si el no. de dias para la prioridad seleccionada es 0, entonces no existe una prioridad, se asigna nulo
            if (prioridad.getDias().equals(Constantes.FIRST))
            {
                asuntoDetalle.setFh_limite(null);
                asuntoDetalle.setFh_limiteDDMMYYYY(null);
            }
        }
        // Si el no. de dias es menor a cero, sigfica que el usuario selecciono la fecha limite de contestacion del documento
        // viene definido en el asunto.getAsunto_detalle().setFh_limite, no hay nada que hacer
    }

    /**
     * Lista de los asuntos archivados del usuario en sesion
     * @param criterioAsunto
     * @param request
     * @return
     */
    public List<Asunto> listAsuntosArchivados(CriterioAsunto criterioAsunto,
            HttpServletRequest request)
    {
        Empleado empleado_ses = (Empleado) request.getSession().getAttribute(
                Constantes.USUARIO_SESION);

        // Asigna el estatus pendiente como criterio
        criterioAsunto.setEstatus(Constantes.ARCHIVADO + "");

        // Asigna el area de captura como criterio
        criterioAsunto.setId_area_capt(empleado_ses.getArea().getId_area() + "");

        return this.listAsuntosIniciales(criterioAsunto);
    }

    /**
     * Obtiene el numero de asuntos Archivados del usuario en sesion
     * @param criterioAsunto
     * @param request
     * @return
     */
    public Integer countAsuntosArchivados(CriterioAsunto criterioAsunto,
            HttpServletRequest request)
    {
        Empleado empleado_ses = (Empleado) request.getSession().getAttribute(
                Constantes.USUARIO_SESION);

        // Asigna el estatus pendiente como criterio
        criterioAsunto.setEstatus(Constantes.ARCHIVADO + "");

        // Asigna el area de captura como criterio
        criterioAsunto.setId_area_capt(empleado_ses.getArea().getId_area() + "");

        return this.countAsuntosIniciales(criterioAsunto);
    }

    /**
     * Para la firma digital
     * @param firma
     * @param asunto
     */
    public void firmarAsunto(Firma firma, Asunto asunto)
    {
        FirmaAsunto firmaAsunto = new FirmaAsunto(asunto.getEstatus(),
                new Date(), firma.getFirmaAsunto().getFirma(), firma.getFirmaAsunto().getFirmante(), asunto.getId_asunto(),
                asunto.getAsunto_detalle().getId_asunto_detalle());
    }

    /**
     * Los catalogos del sistema
     * @param request
     */
    public void loadCatalogosCapturaAsunto(HttpServletRequest request)
    {
        Empleado usuario = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);
        ServletContext servletContext = request.getSession().getServletContext();

        // Sube a contexto la lista de tipos de documento
        if (servletContext.getAttribute(Constantes.TIPOS_DOCUMENTO) == null)
            servletContext.setAttribute(Constantes.TIPOS_DOCUMENTO, this.documentoService.listTipoDocumento());

        // Sube a contexto la lista de prioridades
        if (servletContext.getAttribute(Constantes.PRIORIDADES) == null)
            servletContext.setAttribute(Constantes.PRIORIDADES, this.prioridadService.listPrioridades());

        // Sube a contexto la lista de instrucciones
        if (servletContext.getAttribute(Constantes.INSTRUCCIONES) == null)
            servletContext.setAttribute(Constantes.INSTRUCCIONES, this.instruccionService.listInstrucciones(usuario.getId_area()));
        
        // Sube a sesion la lista de temas. Los temas son diferentes para cada area
        if (request.getSession().getAttribute(Constantes.TEMAS) == null)
            request.getSession().setAttribute(Constantes.TEMAS,this.temaService.listTemas(usuario.getId_area()));

        if (request.getSession().getAttribute(Constantes.EVENTOS) == null)
            request.getSession().setAttribute(Constantes.EVENTOS,this.eventoService.listEventos(usuario.getId_area()));
            
        // Sube a session la lista de expedientes. Los expedientes son diferentes para cada area
        if (request.getSession().getAttribute(Constantes.EXPEDIENTES) == null)
            request.getSession().setAttribute(Constantes.EXPEDIENTES, this.expedienteService.listExpedientes(usuario.getId_area()));
    }

    /**
     * Actualizar un asunto
     * @param asunto
     * @param request
     * @param urlLogo
     * @return
     * @throws Exception
     */
    public Asunto updateAsunto(Asunto asunto, HttpServletRequest request, URL urlLogo)
            throws Exception
    {
        try
        {
            Empleado empleado_capt = (Empleado) request.getSession().getAttribute(Constantes.USUARIO_SESION);
            // Obtiene el id concentrado
            Integer id_concentrado = asunto.getAsunto_detalle().getId_concentrado();
            id_concentrado = id_concentrado == null ? -1 : id_concentrado;

            // Asigna fh limite de acuerdo a la prioridad
            this.asignarFhLimite(asunto, request);

            // Lista de ids asunto que se guardaran en la tabla de asunto concentrado
            List<Map<String, Integer>> ids_asunto = new ArrayList<Map<String, Integer>>();

            // El folio
            String folio = "";

            // Asigna el tema del asunto            
            if (asunto.getSubTema() != null && asunto.getSubTema().getId_tema() > Constantes.INSTANCIA_CREADA)//si el tema tiene subtemas el tema que se guarda es el id del subtema
                asunto.getTema().setId_tema(asunto.getSubTema().getId_tema());

            // Obtiene el id asunto padre del asunto a modificar
            Integer id_asunto_padre = asunto.getId_asunto_padre() < 0
                    ? asunto.getId_asunto() : asunto.getId_asunto_padre();

            List<Integer> ids_asunto_ini = new ArrayList<Integer>();
            ids_asunto_ini.add(id_asunto_padre);
            if (!ids_asunto_ini.contains(asunto.getId_asunto()))
                ids_asunto_ini.add(asunto.getId_asunto());

            // Se recorre la lista de asuntos asociados al asunto actual
            List<Asunto> asuntosAsociados = asunto.getAsunto_detalle().getAsuntos_asociados();
            asuntosAsociados = asuntosAsociados == null ? new ArrayList<Asunto>() : asuntosAsociados;
            for (Asunto asunto_asociado : asuntosAsociados)
            {
                if (!ids_asunto_ini.contains(asunto_asociado.getId_asunto()))
                    ids_asunto_ini.add(asunto_asociado.getId_asunto());
            }

            // Para asignar el asunto a varios destinatarios, se recorre la
            // lista y se actualiza/guarda un asunto por destinatario
            List<Empleado> empleadosDest = asunto.getAsunto_detalle().getEmpleadosDest();

            int idx = 0;
            for (Empleado empleado_dest : empleadosDest)
            {
                // Asigna el id asunto
                asunto.setId_asunto(idx < ids_asunto_ini.size() ? ids_asunto_ini.get(idx) : null);

                // Asigna el id padre del asunto
                asunto.setId_asunto_padre(idx == 0 ? Constantes.INSTANCIA_CREADA : id_asunto_padre);

                // Incrementa el contador index 
                idx++;

                // Asigna empleado destinatario al asunto detalle
                asunto.getAsunto_detalle().setEmpleado_dest(empleado_dest);

                // Obtiene la informacion del empleado destinatario inicial
                Empleado empleado_dest_ini = empleadoService.getEmpleadoById(empleado_dest.getId_empleado());

                // Asigna el destinatario inicial
                asunto.getAsunto_detalle().setEmpleado_dest_ini(empleado_dest_ini);

                // Obtiene la lista de empleados recepcionistas del asunto
                List<Empleado> empleados_recep = empleadoService.listReceptorasFromAreaAndAreaPadre(empleado_dest_ini);

                // Asigna los empleados recepcionistas
                asunto.getAsunto_detalle().setEmpleadosRecep(empleados_recep);

                // Asigna el estatus
                Integer estatus;
                if (asunto.getEstatus().equals(Constantes.EN_CAPTURA) || asunto.getEstatus().equals(""))
                    estatus = Constantes.EN_CAPTURA;
                else
                    estatus = asunto.getEstatus();
                // Verifica si el asunto va a ser archivado                
                estatus = asunto.getArchivado().equals(Constantes.ARCHIVADO) ? Constantes.ARCHIVADO : estatus;
                //estatus = asunto.getConfidencial().booleanValue() ? Constantes.CONFIDENCIAL : estatus;// si el asunto es de caracter confidencial //AGG Confidencial -> booleano 20111109

                // Asigna el estatus al asunto
                asunto.setEstatus(estatus);
                asunto.getAsunto_detalle().setEstatus(estatus);

                // solo la primera vez se calcula el folio
                if (asunto.getFolio().equals(""))
                {
                    folio = this.asuntoDao.getFolioAsunto(asunto.getAsunto_detalle().getEmpleado_remi().getArea().getId_area());
                    asunto.setFolio(folio);
                }

                // Si el Id de Asunto existe, borra las relaciones del asunto antes de actualizar
                if (asunto.getId_asunto() != null)
                    asunto = asuntoDao.deleteRelaciones(asunto);

                //poner en el asunto las instruciones y comentarios personalizados de cada empleado destinatario
                asunto.setId_instruccion(empleado_dest.getInstruccion().getId_instruccion());
                if (empleado_dest.hasComentarioPersonalizado())//si el empleado tiene comentario personalizado
                    asunto.getAsunto_detalle().setComentario(empleado_dest.getComentario());//se le pone al detalle

                // Actualiza/guarda en base de datos el asunto 
                asunto = asuntoDao.updateAsuntoCaptura(asunto);// se crea el padre

                // Registra asunto en tabla de actualizaciones
                asuntoCapturaActualizacionService.saveAsuntoCapturaActualizacion(asunto, request);

                // La creacion reciente el objeto se toma como padre
                if (asunto.getId_asunto_padre().equals(Constantes.INSTANCIA_CREADA))
                    // Los hijos se crean con relacion al id del padre
                    asunto.setId_asunto_padre(asunto.getId_asunto());
                // Agerga el id del asunto a la lista de identificadores de asunto
                Map<String, Integer> map_ids_asunto = new HashMap<String, Integer>();
                map_ids_asunto.put("id_asunto", asunto.getId_asunto());
                map_ids_asunto.put("id_asunto_detalle", asunto.getAsunto_detalle().getId_asunto_detalle());
                ids_asunto.add(map_ids_asunto);

                if (!asunto.getEstatus().equals(Constantes.ARCHIVADO) && !asunto.getEstatus().equals(Constantes.EN_CAPTURA))
                    // Si el destinatario es interno asigna el estatus pendiente
                    // De lo contrario asigna el estatus finalizado
                    if (empleado_dest_ini.getArea().getTipo().equals(Area.INTERNA))
                    {
                        // Si no tiene recepcionista se lo turna a el mismo
                        /*if (empleados_recep != null && empleados_recep.isEmpty())
                        {*/
                        if (asunto.getAsunto_detalle().getEmpleado_remi().getArea().equals(Area.INTERNA))
                        {
                            asunto.setEstatus(Constantes.TURNADO);
                            asunto.getAsunto_detalle().setEstatus(Constantes.TURNADO);
                            asunto.getAsunto_detalle().setComentario("");
                            asuntoDao.insertAsuntoDetalle(asunto.getAsunto_detalle());
                        }
                    }
                    else
                    {
                        asunto.setEstatus(Constantes.FINALIZADO);
                        asunto.getAsunto_detalle().setEstatus(Constantes.FINALIZADO);
                        asunto.getAsunto_detalle().setComentario("");
                        asuntoDao.insertAsuntoDetalle(asunto.getAsunto_detalle());
                    }
                // Envia un correo notificando que se ha creado un asunto
                if ((asunto.getEstatus().equals(Constantes.TURNADO)||asunto.getEstatus().equals(Constantes.PENDIENTE))
                    &&asunto.getAsunto_detalle().getEmpleado_dest_ini().getArea().getTipo().equals(Area.INTERNA) //Area interna
                    &&asunto.getAsunto_detalle().getEmpleado_dest_ini().getArea().getEnvio_correo().equals(1)) //Area tiene correo activado
                    this.sendMailNuevoAsunto(asunto, urlLogo, request);
            }

            // Actualiza el registro del asunto en un concentrado
            asuntoDao.updateAsuntoConcentrado(ids_asunto, id_concentrado);

            // Recorre la lista de ids asunto y selecciona los ids que se eliminaran
            for (int i = 0; i < ids_asunto_ini.size(); i++)
            {
                Integer id_asunto = ids_asunto_ini.get(i);
                asunto.setId_asunto(id_asunto);
                if (i >= empleadosDest.size())
                    // Borra las relaciones del asunto antes de actualizar
                    asuntoDao.deleteAsunto(asunto);
            }

            // Sube a request el numero del asuto guardado para mostrar mensaje
            String ids = "";
            int i = 1;
            for (Map<String, Integer> map : ids_asunto)
            {
                ids += map.get("id_asunto");
                if (i < ids_asunto.size())
                    ids += ", ";
                i++;
            }
            request.setAttribute("no_asunto_exitoso", ids);
        } catch (Exception e)
        {
            log.error("Ha ocurrido un error en la transaccion",e);
            throw new Exception(e.getMessage());
        }
        return asunto;
    }

    /**
     * Actualizar un asunto
     * @param id_asunto
     * @return
     */
    public Asunto getAsuntoCaptura(Integer id_asunto)
    {
        //

        // Obtiene el asunto del repositorio
        Asunto asunto = this.getAsunto(id_asunto);
        // Obtiene el primer asunto detalle y lo asigna
        asunto.setAsunto_detalle(asunto.getAsuntos_detalles().get(0));
        // Agrega los destinatarios

        asunto.getAsunto_detalle().getEmpleadosDest().add(asunto.getAsunto_detalle().getEmpleado_dest());
        List<Asunto> asuntos_asociados = asunto.getAsunto_detalle().getAsuntos_asociados();
        asuntos_asociados = asunto.getAsunto_detalle().getAsuntos_asociados() == null ? new ArrayList<Asunto>() : asunto.getAsunto_detalle().getAsuntos_asociados();
        for (Asunto asunto_asociado : asuntos_asociados)
        {
            asunto_asociado.getAsunto_detalle().getEmpleado_dest().setComentario(asunto_asociado.getAsunto_detalle().getComentario() == null ? "" : asunto_asociado.getAsunto_detalle().getComentario());
            asunto_asociado.getAsunto_detalle().setComentario("");
            asunto_asociado.getAsunto_detalle().getEmpleado_dest().getInstruccion().setId_instruccion(asunto_asociado.getId_instruccion());
            asunto.getAsunto_detalle().getEmpleadosDest().add(asunto_asociado.getAsunto_detalle().getEmpleado_dest());

        }
        // Asigna los idx de los documentos
        Documento.asignaIdx(asunto.getAsunto_detalle().getDocumentos());
        int idx = 0;
        for (Documento documento : asunto.getAsunto_detalle().getDocumentos())
        {
            byte[] blob = documentoService.getBlobByIdDocumento(documento.getId_documento());
            documento.setBlob(blob);
            documento.setIdx(idx);
            idx++;
        }

        // Agrega el historico de movimientos del asunto
        asunto.setActualizacionesCaptura(asuntoCapturaActualizacionService.listAsuntoCapturaActualizacionByIdAsunto(id_asunto));

        if (isComentarioGeneral(asunto.getAsunto_detalle().getEmpleadosDest()))
        {
            asunto.getAsunto_detalle().setComentario(asunto.getAsunto_detalle().getEmpleadosDest().get(0).getComentario());
            for (Empleado empleado : asunto.getAsunto_detalle().getEmpleadosDest())
            {
                empleado.setComentario("");
            }
        }
        else
            asunto.getAsunto_detalle().setComentario("");
        return asunto;
    }

    /**
     * Comprueba si asunto tiene comentario general
     * @param empleados
     * @return
     */
    public boolean isComentarioGeneral(List<Empleado> empleados)
    {
        boolean global = true;
        if (empleados != null && !empleados.isEmpty())
        {
            String comentarioIni = empleados.get(0).getComentario() == null ? "" : empleados.get(0).getComentario();
            for (int i = 0; i < empleados.size() && global == true; i++)
            {
                String comentario = empleados.get(i).getComentario() == null ? "" : empleados.get(i).getComentario();
                if (!comentario.equals(comentarioIni))
                    global = false;
                comentarioIni = comentario;
            }
        }
        return global;
    }

    public BitacoraAsunto getBitacoraAsunto(Integer id_asunto)
    {
        BitacoraAsunto bitacoraAsunto = this.asuntoDao.getBitacoraAsunto(id_asunto);
        return bitacoraAsunto;
    }

    public Integer saveBitacoraAsunto(BitacoraAsunto bitacoraAsunto)
    {
        return this.asuntoDao.saveBitacoraAsunto(bitacoraAsunto);
    }

    public void updateBitacoraAsunto(BitacoraAsunto bitacoraAsunto)
    {
        this.asuntoDao.updateBitacoraAsunto(bitacoraAsunto);
    }

    /**
     * Obtiene el folio del asunto
     * @param idArea
     * @return
     */
    public String getFolioAsunto(Integer idArea)
    {
        String folio = this.asuntoDao.getFolioAsunto(idArea);
        return folio;
    }

    /**
     * Actualiza la fecha de lectura del asunto
     * @param asunto
     * @param request
     */
    public void updateComentario(Asunto asunto, HttpServletRequest request)
    {

        asuntoDao.updateComentario(asunto);

    }

    /**
     * Obtiene la lista de id's de un asunto dados por un criterio
     * @param criterioAsunto
     * @return
     */
    public List<String> getIdsAsuntoByCriterio(CriterioAsunto criterioAsunto)
    {
        return this.asuntoDao.getIdsAsuntoByCriterio(criterioAsunto);
    }

    public List<String> getIdsAsuntoCapturaByCriterio(CriterioAsunto criterioAsunto)
    {
        return this.asuntoDao.getIdsAsuntoCapturaByCriterio(criterioAsunto);
    }

    /**
     * Elimina el asunto
     */
    public void deleteAsunto(Asunto asunto) throws Exception
    {
        // Actualiza en base de datos el asunto
        asuntoDao.deleteAsunto(asunto);
    }

    /**
     * @param asuntoDao
     *            the asuntoDao to set
     */
    public void setAsuntoDao(AsuntoDao asuntoDao)
    {
        this.asuntoDao = asuntoDao;
    }

    /**
     * @param correoService
     *            the correoService to set
     */
    public void setCorreoService(CorreoService correoService)
    {
        this.correoService = correoService;
    }

    /**
     * @param arbolService
     *            the arbolService to set
     */
    public void setArbolService(ArbolService arbolService)
    {
        this.arbolService = arbolService;
    }

    /**
     * @param empleadoService
     *            the empleadoService to set
     */
    public void setEmpleadoService(EmpleadoService empleadoService)
    {
        this.empleadoService = empleadoService;
    }

    /**
     * @param prioridadService the prioridadService to set
     */
    public void setPrioridadService(PrioridadService prioridadService)
    {
        this.prioridadService = prioridadService;
    }

    /**
     * @param instruccionService the instruccionService to set
     */
    public void setInstruccionService(InstruccionService instruccionService)
    {
        this.instruccionService = instruccionService;
    }

    /**
     * @param temaService the temaService to set
     */
    public void setTemaService(TemaService temaService)
    {
        this.temaService = temaService;
    }

    /**
     * @param eventoService the eventoService to set
     */
    public void setEventoService(EventoService eventoService)
    {
        this.eventoService = eventoService;
    }

    /**
     * @param expedienteService the expedienteService to set
     */
    public void setExpedienteService(ExpedienteService expedienteService)
    {
        this.expedienteService = expedienteService;
    }

    /**
     * @param documentoService the documentoService to set
     */
    public void setDocumentoService(DocumentoService documentoService)
    {
        this.documentoService = documentoService;
    }

    /**
     * @param asuntoCapturaActualizacionService the asuntoCapturaActualizacionService to set
     */
    public void setAsuntoCapturaActualizacionService(
            AsuntoCapturaActualizacionService asuntoCapturaActualizacionService)
    {
        this.asuntoCapturaActualizacionService = asuntoCapturaActualizacionService;
    }

    public List<Asunto> listaAsuntosReporteVolante(CriterioReporteVolante criterio)
    {
        return this.asuntoDao.listAsuntoReporteVolante(criterio);
    }

    public Asunto getAsuntoDetalleById(Integer id_asunto)
    {
        return this.asuntoDao.getAsuntoDetalleById(id_asunto);
    }

    /**
     * @param areaService the areaService to set
     */
    public void setAreaService(AreaService areaService)
    {
        this.areaService = areaService;
    }

    /**
     * @param tipoDocumentoService the tipoDocumentoService to set
     */
    public void setTipoDocumentoService(TipoDocumentoService tipoDocumentoService)
    {
        this.tipoDocumentoService = tipoDocumentoService;
    }
    public AsuntoDetalle getDetalleByIdAsuntoDetalle(Integer id_asunto_detalle)
    {
        return this.asuntoDao.getDetalleByIdAsuntoDetalle(id_asunto_detalle);
    }
    public List <AsuntoDetalle> listTurnoGuardado(Integer id_asunto_detalle){
        return this.asuntoDao.listTurnoGuardado(id_asunto_detalle);
    }

    public List<String> getIdsAsuntoByCriterio_ultimo(CriterioAsunto criterioAsunto)
    {
        return this.asuntoDao.getIdsAsuntoByCriterio_ultimo(criterioAsunto);
    }

    public AsuntoDetalle listByIdAsuntoDetalle(Integer id_asunto)
    {
        return this.asuntoDao.listByIdAsuntoDetalle(id_asunto);
    }

    public AsuntoDetalle listByIdAsuntoDetalle_ultimo(Integer id_asunto)
    {
        return this.asuntoDao.listByIdAsuntoDetalle_ultimo(id_asunto);
    }

    /**
     * Se agregaron nuevas reglas de negocio para la presentación de los resultados en las carpetas
     * @param criterioAsunto
     * @return
     */
    public List<Asunto> listAsuntosInicialesNuevasReglas(CriterioAsunto criterioAsunto)
    {
        // Asigna el numero de registros de la lista a obtener
        //criterioAsunto.getPaginador().setNumRegistros(this.countAsuntosIniciales(criterioAsunto));
        return asuntoDao.listAsuntosInicialesNuevasReglas(criterioAsunto);
    }

    /**
     * Obtiene el numero de los asuntos con el detalle inicial
     * @param criterioAsunto
     * @return
     */
    public Integer countAsuntosInicialesNuevasReglas(CriterioAsunto criterioAsunto)
    {
        return asuntoDao.countAsuntosInicialesNuevasReglas(criterioAsunto);
    }

    public Integer getUltimoEstatusAsuntoByArea(HashMap param)
    {
        return asuntoDao.ultimoEstatusAsuntoByArea(param);
    }

    public Asunto saveAtenderParcialRechazo(Asunto asunto, HttpServletRequest request, URL urlLogo) throws Exception
    {
        Empleado empleado_ses = (Empleado) request.getSession().getAttribute(
                Constantes.USUARIO_SESION);

        // Asigna la fecha de registro del asunto
        asunto.getAsunto_detalle().setFh_registro(new Date());

        // Asigna la Ip donde se capturo el asunto
        asunto.getAsunto_detalle().setIp(request.getRemoteAddr());

        // Asigna el Id del asunto
        Integer idAsunto = asunto.getId_asunto();

        // Asigna el Id del detalle
        Integer idAsuntoDetalle = asunto.getUltimaDetalle().getId_asunto_detalle();


        // Guarda la respuesta parcial
        asunto = asuntoDao.SaveAtenderParcialRechazo(asunto);
        asunto.setTipo_actualizacion(1);
        Integer estatus = asunto.getEstatus();
        asunto.getAsunto_detalle().getEmpleado_dest().setId_empleado(asunto.getUltimaDetalle().getEmpleado_dest().getId_empleado());
        asunto.setEstatus(Constantes.ATENCIONPARCIAL);
        asuntoCapturaActualizacionService.saveAsuntoCapturaActualizacion(asunto, request);
        asunto.setEstatus(estatus);

        // Envia un correo notificando que se ha creado un asunto
        //this.sendMailAsunto(asunto, urlLogo);
        return asunto;
    }

}