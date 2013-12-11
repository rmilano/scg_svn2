<%-- 
    Document   : alta
    Created on : 04-jul-2011, 12:31:44
    Author     : roque
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/jsp/arbol.jsp"%>

<link href='<html:rewrite page="/jsp/tema/css/tema.css"/>' rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<html:rewrite page="/jsp/areas/js/arbol.js"/>"></script>
<script type="text/javascript" src="<html:rewrite page="/jsp/tema/js/alta.js"/>"></script>

<div class="content border">
    <div class="inner corner">
        <div class="contentform">
            <html:form action="/temas.do" styleId="temaForm" method="Post">
                <html:hidden property="method" value="inicio" />
                <html:hidden name="temaForm" styleId="tema-alta" property="tema.id_tema"/>

                <table class="catalogo-tema ui-widget ui-widget-content texto-igual">
                    <thead>
                        <tr class="ui-widget-header texto-igual">
                            <th><span class="campoRequerido"> * </span>Area:</th>
                            <th colspan="2"><span class="campoRequerido"> * </span>Nombre del Tema:</th>                            
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td id="captura-label-folio">
                                <div id="arbol-tema" title="modal">
                                    <html:text name="temaForm" property="tema.area.area" styleClass="tree required icon-tree-trigger upperCase" styleId="nombre-tema"/>
                                    <html:hidden name="temaForm" styleClass="id" styleId="id" property="tema.id_area" value=""/>                                    
                                </div>
                            </td>
                            <td> <html:text name="temaForm" styleId="descripcion-tema" styleClass="upperCase required" property="tema.tema" /> </td>
                            <td>
                                <html:button  property="" styleId="crear-tema" styleClass="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"  value="Guardar" />
                                <html:button  property="" styleId="cancelar-tema" styleClass="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"  value="Cancelar" />
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
