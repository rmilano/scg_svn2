<%@ include file="/jsp/taglibs.jsp"%>
<link href='<html:rewrite page="/jsp/inicio/css/login.css"/>' rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<html:rewrite page="/jsp/js/autenticacion.js"/>"></script>
<!--Archivo empleado para la implementacion del SSO-->
<div class="login">
    <div class="img_login">
        <html:img src="../../imagenes/login.png" title="Login"></html:img>
    </div>
    <form name="loginForm" id="uid-form" action="<html:rewrite page="/j_acegi_security_check"/>" method="post">
        <%--<label class="td_titulo"><bean:message key="label.autenticacion" /></label>--%>
        <%--<%=request.getHeader("CUSTOM-userid")%>--%>
        <label class="td_concepto_izq_peq"><bean:message key="label.usuario" /></label>
        <label>

        </label>
        <label>
            <input type='text' name='j_username' id="uid" value="<%=request.getHeader("CUSTOM-userid")%>@economia.gob.mx" class="required" />
        </label>
        <label class="td_concepto_izq_peq"><bean:message key="label.contrasenna" /></label>
        <label>
            <input type="password" name="j_password" class="text required" value="prueba" /></label>
        <br/>
        <label> <input name="login" class="btn_login enterButton" type="submit" value="Entrar" title="Iniciar sesión"></label>
            <logic:present parameter="errorContrasenna">
            <label><font color="RED">La contrase&ntilde;a es incorrecta</font></label>
        </logic:present>
    </form>
</div><!-- login -->









