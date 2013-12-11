<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/jsp/arbol.jsp"%>
<link href='<html:rewrite page="/jsp/css/style.css"/>' rel="stylesheet" type="text/css" />
<link href='<html:rewrite page="/jsp/empleados/css/empleado_1.css"/>' rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<html:rewrite page="/jsp/js/jquery.paginate.js"/>"></script>
<script type="text/javascript" src="<html:rewrite page="/jsp/js/misc-functions.js"/>"></script>
<script type="text/javascript" src="<html:rewrite page="/jsp/empleados/js/consulta-eliminar-empleado.js"/>"></script>


<div class="content border">
    <div class="inner corner">
        <div class="contentform">
            <html:form action="/empleados.do" styleId="form-empleado" method="Post">
                <html:hidden styleId="method" property="method" value="inicio"/>
                <html:hidden name="empleadoForm" styleId="pagina" property="criterioEmpleado.paginador.pagina"/>
                <html:hidden name="empleadoForm" styleId="paginas" property="criterioEmpleado.paginador.paginas"/>
                <html:hidden name="empleadoForm" styleId="empleado-editar" property="empleadoCreado.id_empleado"/>


                <table class="empleado ui-widget ui-widget-content texto-igual">
                    <thead>
                        <tr class="ui-widget-header texto-igual">
                            <th>Tipo:</th>                                            
                            <th colspan="2">&Aacute;rea:</th>                                            
                        </tr>
                    </thead>
                    <tbody>
                        <tr>    

                            <td>Interno:<html:radio name="empleadoForm" property="criterioEmpleado.empleado.area.tipo"
                                        styleClass="radio-arbol-interno" title="arbol-catalogo-empleado" alt="modal" value ="0" />
                                Externo:<html:radio name="empleadoForm" property="criterioEmpleado.empleado.area.tipo"
                                            styleClass="radio-arbol-externo" title="arbol-catalogo-empleado" alt="modal-arbol-externo"  value ="1" />
                            </td>
                            <td colspan="2">
                                <div id="arbol-catalogo-empleado" title="modal">
                                    <html:text name="empleadoForm" property="criterioEmpleado.empleado.area.area" styleClass="icon-tree-trigger tree element-search" />
                                    <html:hidden name="empleadoForm" property="criterioEmpleado.empleado.area.id_area"
                                                 styleClass="id" />                                                                       
                                </div>
                            </td>

                        </tr>
                    </tbody>

                    <thead>
                        <tr class="ui-widget-header texto-igual">                                                                      
                            <th>Nombre de empleado:</th>                                            
                            <th>Apellido paterno:</th>                                            
                            <th>Apellido materno:</th>                                            
                        </tr>
                    </thead>
                    <tbody>
                        <tr>    
                            <td>
                                <html:text name="empleadoForm" property="criterioEmpleado.empleado.nombre" styleClass="upperCase element-search" /> 
                            </td>
                            <td>
                                <html:text name="empleadoForm" property="criterioEmpleado.empleado.paterno" styleClass="upperCase element-search" /> 
                            </td>
                            <td>
                                <html:text name="empleadoForm" property="criterioEmpleado.empleado.materno" styleClass="upperCase element-search" /> 
                            </td>
                        </tr>
                    </tbody>

                    <thead>
                        <tr class="ui-widget-header texto-igual">                                                                      
                            <th>Correo:</th>                                            
                            <th>Puesto:</th>                                            
                            <th>RFC:</th>                                            
                        </tr>
                    </thead>
                    <tbody>
                        <tr>    
                            <td>
                                <html:text name="empleadoForm" property="criterioEmpleado.empleado.correo" styleClass="element-search" /> 
                            </td>
                            <td>
                                <html:text name="empleadoForm" property="criterioEmpleado.empleado.puesto" styleClass="upperCase element-search" /> 
                            </td>
                            <td>
                                <html:text name="empleadoForm" property="criterioEmpleado.empleado.rfc" styleClass="upperCase element-search" /> 
                            </td>
                        </tr>
                    </tbody>

                    <tbody>
                        <tr>    
                            <td colspan="3">
                                <input type="button" title="Buscar" id="buscar-empleados" value="Buscar" class="btn_default mouseOver" />
                                <input type="button" title="Limpiar" id="limpiar-criterio" value="Limpiar" class="btn_default mouseOver" />
                                <input type="button" title="Nuevo empleado" id="crear-empleado" value="Nuevo empleado" class="btn_default mouseOver" />
                            </td>
                        </tr>
                    </tbody>

                </table>                    
            </html:form>
        </div> <!-- contentform -->
    </div> <!-- inner corner -->
</div> <!-- content border -->


<logic:notEmpty name="empleadoForm" property="empleados">
    <div class="content border">
        <div class="inner corner">
            <div class="contentform">

                <table class="ui-widget ui-widget-content texto-igual">
                    <tbody>
                        <tr>
                            <td colspan="3">
                                <dt:table id="empleado" name="sessionScope.empleadoForm.empleados"
                                          requestURI="/empleados.do?method=getEmpleadosPorPagina&order=true" requestURIcontext="true"
                                          excludedParams="*" sort="list">
                                    <dt:column property="nombre_completo" title="Nombre" sortable="true"></dt:column>
                                    <dt:column property="correo" title="Correo" sortable="true"></dt:column>
                                    <dt:column property="puesto" title="Puesto" sortable="true"></dt:column>
                                    <dt:column property="rfc" title="RFC" sortable="true"></dt:column>
                                    <dt:column property="ocupado" title="Asuntos activos" sortable="true"></dt:column>
                                    <dt:column title="Estatus">
                                        <logic:equal name="empleado" property="activo" value="1">
                                             Activo
                                        </logic:equal>
                                        <logic:equal name="empleado" property="activo" value="0">
                                             Baja
                                        </logic:equal>
                                    </dt:column>
                                    <dt:column title="">
                                        <span id="<bean:write name="empleado" property="id_empleado" />" title="Editar Empleado" class="edit">editar</span>
                                        <logic:equal name="empleado" property="ocupado" value="0">
                                            <span id="<bean:write name="empleado" property="id_empleado" />" title="Borrar Empleado" class="delete">Borrar</span>
                                        </logic:equal>
                                        <logic:notEqual name="empleado" property="ocupado" value="0">
                                            &nbsp;
                                        </logic:notEqual>
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


<div class="modal" title="&Aacute;reas internas">
    <div class="treeviewContent">
        <ul id="tree">
            ${sessionScope.arbol_raiz}
        </ul>
    </div>
</div>
<div class="modal-arbol-externo" title="&Aacute;reas externas">
    <div class="treeviewContent">
        <ul class="tree-branch">
            ${arbol_externo}
        </ul>
    </div>
</div>
