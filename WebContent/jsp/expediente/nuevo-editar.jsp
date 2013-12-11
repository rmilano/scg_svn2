<%-- 
    Document   : alta
    Created on : 04-jul-2011, 12:31:44
    Author     : roque
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/jsp/arbol.jsp"%>

<link href='<html:rewrite page="/jsp/css/style.css"/>' rel="stylesheet" type="text/css" />
<link href='<html:rewrite page="/jsp/expediente/css/expediente.css"/>' rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<html:rewrite page="/jsp/expediente/js/nuevo-editar.js"/>"></script>

<div class="content border">
    <div class="inner corner">
        <div class="contentform">
            <html:form action="/expediente.do" styleId="expedienteForm" method="Post">
                <html:hidden styleId="method" property="method" value="init" />

                <table class="expediente ui-widget ui-widget-content texto-igual">
                    <thead>
                        <tr class="ui-widget-header texto-igual">
                            <th colspan="2">Clave:</th>
                            <th>Expediente:</th>                            
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td colspan="2">
                                <html:text name="expedienteForm" styleClass="upperCase required" property="expediente.cve_expediente" />                                 
                            </td>
                            <td> 
                                <html:text name="expedienteForm" styleClass="upperCase required" property="expediente.expediente" /> 
                            </td>
                        </tr>
                    </tbody>
                    <thead>
                        <tr class="ui-widget-header texto-igual">
                            <th colspan="2">Area:</th>
                            <th>Vigencia:</th>                            
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td id="captura-label-folio" colspan="2">
                                <div id="arbol-tema" title="modal">
                                    <html:text name="expedienteForm" property="expediente.area.area" styleClass="tree icon-tree-trigger required upperCase" styleId="nombre-area"/>
                                    <html:hidden name="expedienteForm" styleClass="id" styleId="id" property="expediente.area.id_area"/>
                                </div>
                            </td>
                            <td> 
                                <html:text name="expedienteForm" styleClass="upperCase calendarioAfter required" property="expediente.fh_vigenciaDDMMYYYY" /> 
                            </td>
                        </tr>
                    </tbody>
                    <tbody>
                        <tr>
                            <td colspan="3">            
                                <input type="button" title="Guardar expediente" id="guardar-expediente" value="Guardar" class="btn_default mouseOver" />
                                <input type="button" title="Cancelar" id="cancelar" value="Cancelar" class="btn_default mouseOver regresar" />
                            </td>
                        </tr>
                    </tbody>
                </table>
            </html:form>
        </div>
    </div>
</div>

<!--despliegue de areas-->
<div class="modal" title="&Aacute;reas internas">
    <div class="treeviewContent">
        <ul id="tree">
            ${sessionScope.arbol_raiz}
        </ul>
    </div>
</div>
