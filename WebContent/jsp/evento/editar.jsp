<%-- 
    Document   : alta
    Created on : 04-jul-2011, 12:31:44
    Author     : roque
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/jsp/arbol.jsp"%>

<link href='<html:rewrite page="/jsp/evento/css/evento.css"/>' rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<html:rewrite page="/jsp/areas/js/arbol.js"/>"></script>
<script type="text/javascript" src="<html:rewrite page="/jsp/evento/js/editar.js"/>"></script>

<div class="content border">
    <div class="inner corner">
        <div class="contentform">
            <html:form action="/eventos.do" styleId="eventoForm" method="Post">
                <html:hidden styleId="method" property="method" value="editarEvento"/>

                <table class="catalogo-evento ui-widget ui-widget-content texto-igual">
                    <thead>
                        <tr class="ui-widget-header texto-igual">
                            <th><span class="campoRequerido"> * </span>Area:</th>
                            <th><span class="campoRequerido"> * </span>Evento:</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td id="captura-label-folio">
                                <div id="arbol-evento" title="modal">
                                    <html:text name="eventoForm" property="evento.area.area" styleClass="tree required icon-tree-trigger upperCase" styleId="nombre-evento"/>
                                    <html:hidden name="eventoForm" styleClass="id" styleId="id" property="evento.id_area"/>
                                </div>
                            </td>
                            <td> <html:text name="eventoForm" styleId="descripcion-evento" styleClass="upperCase" property="evento.evento" /> </td>
                            <th>
                                <html:button  property="" styleId="actualizar-evento" styleClass="ui-button ui-widget ui-state-default ui-corne-all ui-button-text-only"  value="Actualizar" />
                                <html:button  property="" styleId="cancelar" styleClass="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"  value="Cancelar" />
                            </th>
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