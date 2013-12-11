<%-- 
    Document   : alta
    Created on : 04-jul-2011, 12:31:44
    Author     : roque
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/jsp/arbol.jsp"%>

<link href='<html:rewrite page="/jsp/css/style.css"/>' rel="stylesheet" type="text/css" />
<link href='<html:rewrite page="/jsp/evento/css/evento.css"/>' rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<html:rewrite page="/jsp/areas/js/arbol.js"/>"></script>
<script type="text/javascript" src="<html:rewrite page="/jsp/js/jquery.paginate.js"/>"></script>
<script type="text/javascript" src="<html:rewrite page="/jsp/evento/js/consulta-eliminar.js"/>"></script>

<div class="content border">
    <div class="inner corner">
        <div class="contentform">
            <html:form action="/eventos.do" styleId="eventoForm" method="Post">
                <html:hidden property="method" value="inicio" />
                <html:hidden name="eventoForm" styleId="pagina" property="criterioEvento.paginador.pagina"/>
                <html:hidden name="eventoForm" styleId="paginas" property="criterioEvento.paginador.paginas"/>
                <html:hidden name="eventoForm" styleId="evento-editar" property="evento.id_evento"/>

                <table class="catalogo-evento ui-widget ui-widget-content texto-igual">
                    <thead>
                        <tr class="ui-widget-header texto-igual">
                            <th>Area:</th>
                            <th>Nombre del Evento:</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td id="captura-label-folio">
                                <div id="arbol-evento" title="modal">
                                    <html:text name="eventoForm" property="criterioEvento.evento.area.area" styleClass="tree required icon-tree-trigger upperCase" styleId="nombre-evento"/>
                                    <html:hidden name="eventoForm" styleClass="id" styleId="id" property="criterioEvento.evento.area.id_area"/>
                                </div>
                            </td>
                            <td> <html:text name="eventoForm" styleId="descripcion-evento" styleClass="upperCase" property="criterioEvento.evento.evento" /> </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <html:button  property="" styleId="buscar-evento" styleClass="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"  value="Buscar" />
                                <html:button  property="" styleId="limpiar-evento" styleClass="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"  value="Limpiar" />
                                <html:button  property="" styleId="crear-evento" styleClass="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"  value="Agregar" />
                            </td>
                        </tr>
                    </tbody>
                </table>
            </html:form>
        </div>
    </div>
</div>

<logic:notEmpty name="eventoForm" property="eventos">
    <div class="content border">
        <div class="inner corner">
            <div class="contentform">

                <table class="catalogo-evento ui-widget ui-widget-content texto-igual">
                    <tbody>
                        <tr>
                            <td colspan="3">
                                <dt:table id="evento" name="sessionScope.eventoForm.eventos"
                                          requestURI="/eventos.do?method=getEventosPorPagina&order=true" requestURIcontext="true"
                                          excludedParams="*" sort="list">
                                    <dt:column property="fila" title="No." sortable="true"></dt:column>
                                    <dt:column property="evento" title="Evento" sortable="true"></dt:column>
                                    <dt:column title="">
                                        <span id="<bean:write name="evento" property="id_evento" />" title="Editar evento" class="edit">Editar</span>
                                        <span id="<bean:write name="evento" property="id_evento" />" title="Borrar evento" class="delete">Borrar</span>
                                    </dt:column>
                                </dt:table>
                                <div id="paginador"></div>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</logic:notEmpty>

<!--despliegue de areas-->
<div class="modal" title="&Aacute;reas internas">
    <div class="treeviewContent">
        <ul id="tree">
            ${sessionScope.arbol_raiz}
        </ul>
    </div>
</div>
