<%@ include file="/jsp/taglibs.jsp"%>
<link href='<html:rewrite page="/jsp/inicio/css/login.css"/>' rel="stylesheet" type="text/css" />


    <div class="txtMsgsAtencion">Su sesi&oacute;n ha expirado</div>

    <html:img src="../../imagenes/sesExpired.png"
              styleClass="imgSesExpirada" alt="" ></html:img>

    <div class="txtMsgsAtencionLink">
    Para volver al la p&aacute;gina de Inicio de click
        <a href='<html:rewrite page="/autenticacion.do" />' title="Ir al Login">
            <span class="link">
                Aqu&iacute;
            </span>
        </a>
    </div>