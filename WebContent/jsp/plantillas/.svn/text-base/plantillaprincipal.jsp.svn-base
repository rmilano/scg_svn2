<%@ include file="/jsp/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html:html>
<head>

<META HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE text/html; charset=UTF-8" >

<%--Estilos jquery--%>
<link href='<html:rewrite page="/jsp/css/jquery.ui.min.css"/>' rel="stylesheet" type="text/css" />
<link href='<html:rewrite page="/jsp/css/plantillaPrincipal.css"/>' rel="stylesheet" type="text/css" />
<link href='<html:rewrite page="/jsp/css/fg.menu.css"/>' rel="stylesheet" type="text/css" />
<link href='<html:rewrite page="/jsp/css/fg.custom.menu.css"/>' rel="stylesheet" type="text/css" />
<link href='<html:rewrite page="/jsp/css/error.css"/>' rel="stylesheet" type="text/css" />
<link href='<html:rewrite page="/jsp/css/displaytag.css"/>' rel="stylesheet" type="text/css" />
<link href='<html:rewrite page="/jsp/css/jquery.alerts.css"/>' rel="stylesheet" type="text/css" />
<link href='<html:rewrite page="/jsp/css/autocomplete.css"/>' rel="stylesheet" type="text/css" />
<link href='<html:rewrite page="/jsp/css/jquery-ui-timepicker-addon.css"/>' rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<html:rewrite page="/jsp/js/jquery.min.js"/>"></script>
<script type="text/javascript" src="<html:rewrite page="/jsp/js/jquery.tools.min.js"/>"></script>
<script type="text/javascript" src="<html:rewrite page="/jsp/js/jquery.ui.min.js"/>"></script>
<script type="text/javascript" src="<html:rewrite page="/jsp/js/autocomplete/jquery.autocomplete.pack.js"/>"></script>
<script type="text/javascript" src="<html:rewrite page="/jsp/js/autocomplete/jquery.select-autocomplete.js"/>"></script>
<script type="text/javascript" src="<html:rewrite page="/jsp/js/jquery.ui.datepicker-es.js"/>"></script>
<script type="text/javascript" src="<html:rewrite page="/jsp/js/jquery-ui-timepicker-addon.js"/>"></script>

<script type="text/javascript" src="<html:rewrite page="/jsp/js/fg.menu.js"/>"></script>
<script type="text/javascript" src="<html:rewrite page="/jsp/js/fg.custom.menu.js"/>"></script>
<script type="text/javascript" src="<html:rewrite page="/jsp/js/jquery.corner.js"/>"></script>
<script type="text/javascript" src="<html:rewrite page="/jsp/js/jquery.validate.js"/>"></script>
<script type="text/javascript" src="<html:rewrite page="/jsp/js/config.js"/>"></script>
<script type="text/javascript" src="<html:rewrite page="/jsp/js/jquery.alerts.js"/>"></script>
<script type="text/javascript" src="<html:rewrite page="/jsp/js/jquery.numeric.js"/>"></script>
<script type="text/javascript" src="<html:rewrite page="/jsp/js/jquery.alphanumeric.js"/>"></script>
<script type="text/javascript" src="<html:rewrite page="/jsp/js/jquery.blockUI.js"/>"></script>

<html:base />
<title><tiles:getAsString name="titulo" /></title>
</head>
<body>

<input type="hidden" id="contexto_scg" value='<html:rewrite page=""/>'/>
<div class="principal">

<div class="encabezado"><tiles:insert attribute="encabezado" /></div>
<div class="menu"><tiles:insert attribute="menu" /></div>
<div class="cuerpo">
<div class="titulo">
	<input type="hidden" id="contexto_scg" value='<html:rewrite page=""/>'/>
    <label class="pagina"><tiles:getAsString name="titulo" />  SACG-1.3</label>
	<logic:present name="usuario_sesion">
            <label class="usuario">
                <bean:write name="usuario_sesion" property="nombre" />&nbsp;
                <bean:write name="usuario_sesion" property="paterno" />&nbsp;
                <bean:write name="usuario_sesion" property="materno" />
                
                <span class="cerrarSesion">
                   <a href="<html:rewrite page="/" />j_acegi_logout" title="Cerrar Sesi&oacute;n">Cerrar sesi&oacute;n</a>
<!--                   <a href="http://desarrollo.economia.gob.mx:4280/opensso/UI/Logout">Cerrar sesi&oacute;n</a>-->
                </span>

            </label>
        </logic:present>        
</div>

<tiles:insert attribute="cuerpo" /></div>

<div class="pie"><tiles:insert attribute="pie" /></div>

</div>
</body>
<HEAD>
<META HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE">
</HEAD>

</html:html>