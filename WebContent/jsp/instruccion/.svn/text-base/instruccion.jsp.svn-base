<%-- 
    Document   : alta
    Created on : 04-jul-2011, 12:31:44
    Author     : roque
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/jsp/arbol.jsp"%>

<link href='<html:rewrite page="/jsp/css/style.css"/>' rel="stylesheet" type="text/css" />
<link href='<html:rewrite page="/jsp/instruccion/css/instruccion.css"/>' rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<html:rewrite page="/jsp/instruccion/js/instruccion.js"/>"></script>
<script type="text/javascript" src="<html:rewrite page="/jsp/js/jquery.paginate.js"/>"></script>

<div class="content border">
    <div class="inner corner">
        <div class="contentform">
            <html:form action="/instruccion.do" styleId="instruccionForm" method="Post">
                <html:hidden styleId="method" property="method" value="init" />
                <html:hidden name="instruccionForm" styleId="pagina" property="criterioInstruccion.paginador.pagina"/>
                <html:hidden name="instruccionForm" styleId="paginas" property="criterioInstruccion.paginador.paginas"/>
                <html:hidden name="instruccionForm" styleId="id_instruccion" property="instruccion.id_instruccion"/>

                <table class="catalogo-instruccion ui-widget ui-widget-content texto-igual">
                    <thead>
                        <tr class="ui-widget-header texto-igual">
                            <th>Area:</th>
                            <th colspan="2">Instrucción:</th>                            
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td id="captura-label-folio" colspan="2">
                                <div id="arbol-tema" title="modal">
                                    <html:text name="instruccionForm" property="criterioInstruccion.instruccion.area.area" styleClass="tree icon-tree-trigger upperCase" styleId="nombre-area"/>
                                    <html:hidden name="instruccionForm" styleClass="id" styleId="id" property="criterioInstruccion.instruccion.area.id_area"/>
                                </div>
                            </td>
                            <td> 
                                <html:text name="instruccionForm" styleId="descripcion-instruccion" styleClass="upperCase" property="criterioInstruccion.instruccion.instruccion" /> 
                            </td>
                        </tr>
                    </tbody>
                    <tbody>
                        <tr>
                            <td colspan="3">
                                <input type="button" title="Buscar" id="buscar-instruccion" value="Buscar" class="btn_default mouseOver" />
                                <input type="button" title="Limpiar" id="limpiar-instruccion" value="Limpiar" class="btn_default mouseOver" />
                                <input type="button" title="Nueva instruccion" id="crear-instruccion" value="Agregar" class="btn_default mouseOver icon-instruccion" />

                            </td>
                        </tr>
                    </tbody>
                </table>
                <html:hidden name="instruccionForm" styleId="id_area" property="instruccion.id_area"/>
                <html:hidden name="instruccionForm" property="instruccion.instruccion" styleId="instruccionDescripcion"/>
                <html:hidden name="instruccionForm" styleId="activo" property="instruccion.activo" value="1"/>                     

            </html:form>
        </div>
    </div>
</div>

<logic:notEmpty name="instruccionForm" property="instrucciones">
    <div class="content border">
        <div class="inner corner">
            <div class="contentform">

                <table class="ui-widget ui-widget-content texto-igual">
                    <tr>
                        <td colspan="3">
                            <dt:table id="instrucciones" name="sessionScope.instruccionForm.instrucciones"
                                      requestURI="/instruccion.do?method=listarInstruccionesByPagina&order=true" requestURIcontext="true"
                                      excludedParams="*" sort="list">
                                <dt:column property="id_instruccion" title="Clave" sortable="true"></dt:column>
                                <dt:column property="instruccion" title="Instrucción" sortable="true"></dt:column>
                                <dt:column title="">                                        
                                    <span  id="<bean:write name="instrucciones" property="id_instruccion" />" title="Editar Instrucci&oacute;n" class="edit open icon-instruccion">editar</span>
                                    <span id="<bean:write name="instrucciones" property="id_instruccion" />" title="Borrar Instrucci&oacute;n" class="delete">Borrar</span>
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

<div id="dialog-form-instruccion" class="captura-catalog-dialog" title="Instrucción">
    <form id="catalog-instruccion-form">
        <fieldset>  
            <label for="areaBuffer">Area:</label>
            <div id="arbol-catalogo-empleado"  title="modal">                
                <input type="text" name="areaBuffer" class="tree text required ui-widget-content ui-corner-all icon-tree-trigger" id="areaBuffer"/>
                <input type="hidden" class="id" id="id_areaBuffer" />
            </div>
            <label for="bufferInstruccion">Instrucción:</label>
            <input type="text" class="upperCase block" name="bufferInstruccion" id="bufferInstruccion" />
        </fieldset>
    </form>
</div>

