<%-- 
    Document   : alta
    Created on : 04-jul-2011, 12:31:44
    Author     : roque
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/jsp/arbol.jsp"%>

<link href='<html:rewrite page="/jsp/css/style.css"/>' rel="stylesheet" type="text/css" />
<link href='<html:rewrite page="/jsp/expediente/css/expediente.css"/>' rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<html:rewrite page="/jsp/js/jquery.paginate.js"/>"></script>
<script type="text/javascript" src="<html:rewrite page="/jsp/expediente/js/expediente.js"/>"></script>

<div class="content border">
    <div class="inner corner">
        <div class="contentform">
            <html:form action="/expediente.do" styleId="expedienteForm" method="Post">
                <html:hidden styleId="method" property="method" value="init" />
                <html:hidden name="expedienteForm" styleId="pagina" property="criterioExpediente.paginador.pagina"/>
                <html:hidden name="expedienteForm" styleId="paginas" property="criterioExpediente.paginador.paginas"/>
                <html:hidden name="expedienteForm" styleId="expediente-id" property="expediente.id_expediente"/>


                <table class="expediente ui-widget ui-widget-content texto-igual">
                    <thead>
                        <tr class="ui-widget-header texto-igual">
                            <th colspan="2">Area:</th>
                            <th>Expediente:</th>                            
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td id="captura-label-folio" colspan="2">
                                <div id="arbol-tema" title="modal">
                                    <html:text name="expedienteForm" property="criterioExpediente.expediente.area.area" styleClass="tree icon-tree-trigger upperCase" styleId="nombre-area"/>
                                    <html:hidden name="expedienteForm" styleClass="id" styleId="id" property="criterioExpediente.expediente.area.id_area"/>
                                </div>
                            </td>
                            <td> 
                                <html:text name="expedienteForm" styleId="descripcion-expediente" styleClass="upperCase" property="criterioExpediente.expediente.expediente" /> 
                            </td>
                        </tr>
                    </tbody>
                    <tbody>
                        <tr>
                            <td colspan="3">            
                                <input type="button" title="Buscar" id="buscar-expediente" value="Buscar" class="btn_default mouseOver" />
                                <input type="button" title="Limpiar" id="limpiar-expediente" value="Limpiar" class="btn_default mouseOver" />
                                <input type="button" title="Nuevo expediente" id="crear-expediente" value="Agregar" class="btn_default mouseOver" />

                            </td>
                        </tr>
                    </tbody>
                </table>
            </html:form>
        </div>
    </div>
</div>

<logic:notEmpty name="expedienteForm" property="expedientes">
    <div class="content border">
        <div class="inner corner">
            <div class="contentform">

                <table class="catalogo-expediente ui-widget ui-widget-content texto-igual">
                    <tr>
                        <td colspan="3">
                            <dt:table id="expedientes" name="sessionScope.expedienteForm.expedientes"
                                      requestURI="/expediente.do?method=listarExpedientesByPagina&order=true" requestURIcontext="true"
                                      excludedParams="*" sort="list">
                                <dt:column property="id_expediente" title="ID" sortable="true"></dt:column>
                                <dt:column property="expediente" title="Expediente" sortable="true"></dt:column>
                                <dt:column property="cve_expediente" title="Clave" sortable="true"></dt:column>
                                <dt:column property="fh_vigencia" title="Vigencia" sortable="true" format="{0,date,dd-MM-yyyy}"></dt:column>
                                <dt:column title="">                                        
                                    <span  id="<bean:write name="expedientes" property="id_expediente" />" title="Editar Expediente" class="edit open icon-instruccion">Editar</span>
                                    <span id="<bean:write name="expedientes" property="id_expediente" />" title="Borrar Expediente" class="delete">Borrar</span>
                                </dt:column>
                            </dt:table>
                            <div id="paginador"></div>
                        </td>
                    </tr>
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
