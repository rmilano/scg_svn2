<%-- 
    Document   : alta
    Created on : 04-jul-2011, 12:31:44
    Author     : roque
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/jsp/arbol.jsp"%>

<link href='<html:rewrite page="/jsp/css/style.css"/>' rel="stylesheet" type="text/css" />
<link href='<html:rewrite page="/jsp/tema/css/tema.css"/>' rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<html:rewrite page="/jsp/areas/js/arbol.js"/>"></script>
<script type="text/javascript" src="<html:rewrite page="/jsp/js/jquery.paginate.js"/>"></script>
<script type="text/javascript" src="<html:rewrite page="/jsp/tema/js/consulta-eliminar.js"/>"></script>

<div class="content border">
    <div class="inner corner">
        <div class="contentform">
            <html:form action="/temas.do" styleId="temaForm" method="Post">
                <html:hidden property="method" value="inicio" />
                <html:hidden name="temaForm" styleId="pagina" property="criterioTema.paginador.pagina"/>
                <html:hidden name="temaForm" styleId="paginas" property="criterioTema.paginador.paginas"/>
                <html:hidden name="temaForm" styleId="tema-editar" property="tema.id_tema"/>

                <table class="catalogo-tema ui-widget ui-widget-content texto-igual">
                    <thead>
                        <tr class="ui-widget-header texto-igual">
                            <th>Area:</th>
                            <th>Nombre del Tema:</th>                            
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td id="captura-label-folio">
                                <div id="arbol-tema" title="modal">
                                    <html:text name="temaForm" property="criterioTema.tema.area.area" styleClass="tree required icon-tree-trigger upperCase" styleId="nombre-tema"/>
                                    <html:hidden name="temaForm" styleClass="id" styleId="id" property="criterioTema.tema.area.id_area"/>
                                </div>
                            </td>
                            <td> <html:text name="temaForm" styleId="descripcion-tema" styleClass="upperCase" property="criterioTema.tema.tema" /> </td>                            
                        </tr>
                        <tr>
                            <td colspan="2">
                                <html:button  property="" styleId="buscar-tema" styleClass="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"  value="Buscar" />
                                <html:button  property="" styleId="limpiar-tema" styleClass="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"  value="Limpiar" />
                                <html:button  property="" styleId="crear-tema" styleClass="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"  value="Agregar" />
                            </td>
                        </tr>
                    </tbody>
                </table>
            </html:form>
        </div>
    </div>
</div>

<logic:notEmpty name="temaForm" property="temas">
    <div class="content border">
        <div class="inner corner">
            <div class="contentform">

                <table class="catalogo-tema ui-widget ui-widget-content texto-igual">
                    <tbody>
                        <tr>
                            <td colspan="3">
                                <dt:table id="tema" name="sessionScope.temaForm.temas"
                                          requestURI="/temas.do?method=getTemasPorPagina&order=true" requestURIcontext="true"
                                          excludedParams="*" sort="list">
                                    <dt:column property="fila" title="No." sortable="true"></dt:column>
                                    <dt:column property="tema" title="Tema" sortable="true"></dt:column>                                    
                                    <dt:column title="">                                        
                                        <span id="<bean:write name="tema" property="id_tema" />" title="Editar tema" class="edit">Editar</span>
                                        <span id="<bean:write name="tema" property="id_tema" />" title="Borrar Tema" class="delete">Borrar</span>
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
