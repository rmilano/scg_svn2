<%-- 
    Document   : alta
    Created on : 04-jul-2011, 12:31:44
    Author     : roque
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/jsp/arbol.jsp"%>

<link href='<html:rewrite page="/jsp/tema/css/tema.css"/>' rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<html:rewrite page="/jsp/areas/js/arbol.js"/>"></script>
<script type="text/javascript" src="<html:rewrite page="/jsp/tema/js/editar.js"/>"></script>

<div class="content border">
    <div class="inner corner">
        <div class="contentform">
            <html:form action="/temas.do" styleId="temaForm" method="Post">
                <html:hidden styleId="method" property="method" value="editarTema"/>

                <table class="catalogo-tema ui-widget ui-widget-content texto-igual">
                    <thead>
                        <tr class="ui-widget-header texto-igual">
                            <th><span class="campoRequerido"> * </span>Area:</th>
                            <th><span class="campoRequerido"> * </span>Tema:</th>                            
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td id="captura-label-folio"> 
                                <div id="arbol-tema" title="modal">
                                    <html:text name="temaForm" property="tema.area.area" styleClass="tree required icon-tree-trigger upperCase" styleId="nombre-tema"/>
                                    <html:hidden name="temaForm" styleClass="id" styleId="id" property="tema.id_area"/>
                                </div>
                            </td>
                            <td> <html:text name="temaForm" styleId="descripcion-tema" styleClass="upperCase" property="tema.tema" /> </td>                            
                            <th>
                                <html:button  property="" styleId="actualizar-tema" styleClass="ui-button ui-widget ui-state-default ui-corne-all ui-button-text-only"  value="Actualizar" />
                                <html:button  property="" styleId="cancelar" styleClass="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"  value="Cancelar" />
                                <html:button  property="" styleId="crear-subtema" styleClass="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only icon-instruccion"  value="Agregar Subtema" />
                            </th>
                        </tr>
                    </tbody>
                </table>
                <html:hidden property="subtema.id_tema" name="temaForm" styleId="id_subtema" />
                <html:hidden property="subtema.tema" name="temaForm" styleId="subtema" />
                <html:hidden property="subtema.activo" name="temaForm" styleId="activo" />
            </html:form>
        </div>
    </div>
</div>


<div class="content border">
    <div class="inner corner">
        <div class="contentform">

            <table class="catalogo-tema ui-widget ui-widget-content texto-igual">
                <thead>
                    <tr class="ui-widget-header texto-igual">
                        <th colspan="3">Subtemas relacionados:</th>
                    </tr>
                </thead>
                <logic:notEmpty name="temaForm" property="subTemas">
                    <tbody>
                        <tr>
                            <td colspan="3">
                                <dt:table id="tema" name="sessionScope.temaForm.subTemas"
                                          requestURI="/temas.do?method=editarTema&order=true" requestURIcontext="true"
                                          excludedParams="*" sort="list">
                                    <dt:column property="fila" title="No." sortable="true"></dt:column>
                                    <dt:column property="tema" title="Subtema" sortable="true"></dt:column>
                                    <dt:column title="">                                        
                                        <span id="<bean:write name="tema" property="id_tema" />" class="editar icon-instruccion">editar</span>
                                        <span id="<bean:write name="tema" property="id_tema" />" class="delete">Borrar</span>
                                    </dt:column>
                                </dt:table>                                
                            </td>
                        </tr>
                    </tbody>
                </logic:notEmpty>
                <logic:empty name="temaForm" property="subTemas">
                    <tbody>
                        <tr>
                            <td colspan="3">No se encontraron Subtemas relacionados</td>
                        </tr>
                    </tbody>                    
                </logic:empty>
            </table>
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
        

<div id="dialog-form-instruccion" class="captura-catalog-dialog" title="Subtema">
    <form id="catalog-instruccion-form">
        <fieldset>  
            <label for="subtemaBuffer">Subtema</label>
            <input type="text" class="upperCase block" name="subtemaBuffer" id="subtemaBuffer" size="50" maxlength="100"/>
        </fieldset>
    </form>
</div>