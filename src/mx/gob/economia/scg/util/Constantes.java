package mx.gob.economia.scg.util;

import java.util.ResourceBundle;

public abstract class Constantes {

	public static final Integer ACTIVO = 1;
	public static final Integer NO_ACTIVO = 0;

	// Constantes para el tipo de lista de empleados
	public static final Integer REMITENTE = 0;
	public static final Integer DESTINATARIO = 1;
	public static final Integer CON_COPIA_PARA = 2;
	// Elementos en la lista
        public static final Integer FIRST = 0;
        // El numero maximo de registros por cada consulta
        public static final Integer NUM_MAX_REG = Integer.parseInt(ResourceBundle.getBundle(Constantes.PROPERTIES).getString("num.max.registros"));
        // El numero maximo de registros por cada consulta en los reportes
        public static final Integer NUM_MAX_REG_REPORTE = Integer.parseInt(ResourceBundle.getBundle(Constantes.PROPERTIES).getString("num.max.registros.reporte"));
        //tipos de areas
        public static final Integer AREA_INTERNA = 0;
        public static final Integer AREA_EXTERNA = 1;
	// Constantes para responder
	public static final String ATENDER = "atender";
	public static final String TURNAR = "turnar";

	public static final String ESTATUS = "estatus";
        // los roles
        public static final Integer ROL_EMPLEADO = 1;
        public static final Integer ROL_RECEPCIONISTA = 3;
        public static final Integer ROL_REVISOR = 5;

	// Constante de sesion del usuario autenticado
	public static final String USUARIO_SESION = "usuario_sesion";
	public static final String VOLANTE_CORRESPONDENCIA = "VOLANTE_CORRESPONDENCIA";
	public static final String TIPOS_DOCUMENTO = "tipos_documento";
	public static final String MSJ_ASUNTO_USO = "mensajeAsuntoEnUso";
	public static final String MAP_ASUNTOS_USO = "mapAsuntoEnUso";
	public static final String PRIORIDADES = "prioridades";
	public static final String ARBOL_RAIZ = "arbol_raiz";
	public static final String ARBOL_RAMA = "arbol_rama";
	public static final String ARBOL_EXTERNO = "arbol_externo";
	public static final String TAG_IDENT = "<li>";
	public static final String ROL_RECEPTORA = "RECEPCIONISTA";
	public static final String CAPTURA_CATALOGO_SESSION = "captura_catalogo_session";
	public static final String TEMAS = "temas";
	public static final String EVENTOS = "eventos";
	public static final String EXPEDIENTES = "expedientes";
	public static final String INSTRUCCIONES = "instrucciones";
	public static final String ROLES = "ROLES";
        public static final Integer ID_AREA_SECRETARIO = 1;
	
	// Estado de un asunto:	
	public static final Integer PENDIENTE = 0;
	public static final Integer TURNADO = 1;
	public static final Integer ATENDIDO = 2;
	public static final Integer FINALIZADO = 3;
	public static final Integer EN_TIEMPO = 4;
	public static final Integer POR_VENCER = 5;
	public static final Integer VENCIDO = 6;
	public static final Integer EN_CAPTURA = 7;
	public static final Integer EN_REVISION = 8;
	public static final Integer ELIMINADO = 9;
	public static final Integer ARCHIVADO = 10;
	public static final Integer REVISADO_CAPTURA = 11;
	public static final Integer EN_SUPERVISION_CAPTURA = 12;
	public static final Integer SUPERVISADO = 13;
        public static final Integer ATENCIONPARCIAL = 14;
	//public static final Integer CONFIDENCIAL = 14; //AGG Confidencial -> booleano 20111109

        // Las descripcion de los estatus

	public static final Integer DESACTIVADO = 0;
	public static final Integer ACTIVADO = 1;
	public static final Integer INSTANCIA_CREADA = -1;
        
        
        //nombre del archivo .properties que contiene los par�metros de configuraci�n
        public static final String PROPERTIES = "Aplicacion";
        //El mensaje de resultados no encuntrados
        public static final String MSG_FOUND = "FOUND";
        //Los totales
        public static final String TOTAL = "TOTAL";

}
