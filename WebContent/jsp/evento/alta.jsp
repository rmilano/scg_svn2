<%-- 
    Document   : alta
    Created on : 20-feb-2012
    Author     : 
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/jsp/arbol.jsp"%>

<link href='<html:rewrite page="/jsp/evento/css/evento.css"/>' rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<html:rewrite page="/jsp/areas/js/arbol.js"/>"></script>
<script type="text/javascript" src="<html:rewrite page="/jsp/evento/js/alta.js"/>"></script>

<div class="content border">
    <div class="inner corner">
        <div class="contentform">
            <html:form action="/eventos.do" styleId="eventoForm" method="Post">
                <html:hidden property="method" value="inicio" />
                <html:hidden name="eventoForm" styleId="evento-alta" property="evento.id_evento"/>

                <table class="catalogo-evento ui-widget ui-widget-content texto-igual">
                    <thead>
                        <tr class="ui-widget-header texto-igual">
                            <th><span class="campoRequerido"> * </span>Area:</th>
                            <th colspan="2"><span class="campoRequerido"> * </span>Nombre del Evento:</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td id="captura-label-folio">
                                <div id="arbol-evento" title="modal">
                                    <html:text name="eventoForm" property="evento.area.area" styleClass="tree required icon-tree-trigger upperCase" styleId="nombre-evento"/>
                                    <html:hidden name="eventoForm" styleClass="id" styleId="id" property="evento.id_area" value=""/>
                                </div>
                            </td>
                            <td> <html:text name="eventoForm" styleId="descripcion-evento" styleClass="upperCase required" property="evento.evento" /> </td>
                            <td>
                                <html:button  property="" styleId="crear-evento" styleClass="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"  value="Guardar" />
                                <html:button  property="" styleId="cancelar-evento" styleClass="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"  value="Cancelar" />
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
