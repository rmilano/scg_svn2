<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/jsp/arbol.jsp"%>

<link href='<html:rewrite page="/jsp/empleados/css/empleado_1.css"/>' rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<html:rewrite page="/jsp/areas/js/arbol.js"/>"></script>
<script type="text/javascript" src="<html:rewrite page="/jsp/empleados/js/empleados.js"/>"></script>


<html:form action="/empleados.do" styleId="areaForm" method="Post">

    <div class="content border">
        <div class="inner corner">
            <div class="contentform">

                <html:hidden styleId="method" property="method" value=""/>

                <table border="0" width="100%">
                    <tr>
                        <td class="td_concepto_izq_peq"> Tipo de &Aacute;rea: </td>
                        <td>
                            <html:select property="area.tipo" name="areaForm" styleId="tipo" onchange="inicio();">
                                <logic:equal name="isAdministrador" value="true">
                                    <html:option value="0">Interna</html:option>
                                    <html:option value="1">Externa</html:option>
                                </logic:equal>
                                <logic:notEqual name="isAdministrador" value="true">
                                    <logic:equal name="isCapturista" value="true">
                                        <html:option value="1">Externa</html:option>
                                    </logic:equal>
                                </logic:notEqual>
                            </html:select>
                        </td>
                        <td class="td_concepto_izq_peq"> Seleccione un &Aacute;rea: </td>
                        <td>
                            <div id="arbolA">
                                <html:text property="area.area" name="areaForm" styleClass="tree icon-tree-trigger" styleId="area" />
                                <html:hidden name="areaForm" styleId="id" styleClass="id" property="empleado.id_area"/>
                            </div>
                        </td>
                    </tr>
                    <tr><td>&emsp;</td></tr>
                    <tr>
                        <td colspan="4">
                            <input type="button" class="btn_default" onclick="mostrarCampos();" onmouseover="style.cursor='pointer'"
                                   value="Agregar Empleado" title="Agregar Empleado" />
                        </td>
                    </tr>
                </table>

            </div> <!-- contentform -->
        </div> <!-- inner corner -->
    </div> <!-- content border -->
	<logic:present name="errorGuardarEmpl">
	<div style="color: red;">
	<bean:write name="errorGuardarEmpl"/> 	
	</div>
</logic:present>
    <div id="divAddEmpleado" style="display:none;">
        <div class="content border">
            <div class="inner corner">
                <div class="contentform">
                    <table>
                        <tr>
                            <td>
                                <div id="DatosEmpleado" style="display:none;">
                                    <table>
                                        <tr>
                                            <td colspan="2" class="td_concepto_izq_gde">
                                                Datos del Empleado
                                            </td>
                                        </tr>


                                        <tr>
                                            <td class="td_concepto_izq_peq">
						Nombre(s):
                                            </td>
                                            <td>
                                                <html:text property="empleado.nombre" name="areaForm" styleClass="required upperCase" styleId="nombre"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="td_concepto_izq_peq">
						Apellido Paterno:
                                            </td>
                                            <td>
                                                <html:text property="empleado.paterno" name="areaForm" styleClass="required upperCase" styleId="paterno"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="td_concepto_izq_peq">
						Apellido Materno:
                                            </td>
                                            <td>
                                                <html:text property="empleado.materno" name="areaForm" styleClass="required upperCase" styleId="materno"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="td_concepto_izq_peq">
						Email:
                                            </td>
                                            <td>
                                                <html:text property="empleado.correo" name="areaForm" styleClass="required email" styleId="correo"/>
                                                <html:hidden name="areaForm"  styleId="id_empleado" property="empleado.id_empleado" value=""/>
                                                <html:hidden name="areaForm"  styleId="activo" property="empleado.activo" value="1"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="td_concepto_izq_peq">
										Puesto:
                                            </td>
                                            <td>
                                                <html:text property="empleado.puesto" name="areaForm" styleId="puesto" styleClass="upperCase"/>
                                            </td>
                                        </tr>
                                        <tr id="InputRfc" style="display:none;" class="td_descripcion_izq_peq">
                                            <td class="td_concepto_izq_peq">
										RFC:
                                            </td>
                                            <td>
                                                <html:text property="empleado.rfc" name="areaForm" styleId="rfc" styleClass="rfc upperCase"/>
                                            </td>
                                        </tr>
                                        <tr><td>&emsp;</td></tr>
                                        <tr>
                                            <td colspan="2">
                                                <input type="button" class="btn_default" onclick="definirAccion();" onmouseover="style.cursor='pointer'"
                                                       value="Guardar" title="Guardar"/>
                                                &nbsp;&nbsp;&nbsp;
                                                <input type="button" Class="btn_default" onclick="cancel();" onmouseover="style.cursor='pointer'"
                                                       value="Cancelar" title="Cancelar"/>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </td>
                            <td>
                                <div id="ListaRoles" style="display:none;" class="td_descripcion_izq_peq">
                                    <label class="td_concepto_cntr_peq">Roles</label><br>
                                    <div style="display:none;">
                                        <input  type="checkbox" name="selectedItems" value="1" id="rolesAsignados" readonly checked="true"/>
                                    </div>
                                    <logic:iterate name="areaForm" property="empleado.roles" id="roles">
                                        <html:multibox styleId="rolesAsignados" property="selectedItems">
                                            <bean:write name="roles" property="id_rol" />
                                        </html:multibox>
                                        <bean:write name="roles" property="rol" />
                                        <br>
                                    </logic:iterate>
                                </div>
                            </td>
                        </tr>
                    </table>
                    <br>
                    <br>

                </div> <!-- contentform -->
            </div> <!-- inner corner -->
        </div> <!-- content border -->
    </div>   <!-- divAddEmpleado -->

    <div class="modal">
        <div class="treeviewContent">
            <ul id="tree">
                <logic:equal name="isAdministrador" value="true">
                    <c:if test="${areaForm.area.tipo==0||areaForm.area.tipo==null}">
                        ${sessionScope.arbol_raiz}
                    </c:if>
                    <c:if test="${areaForm.area.tipo==1}">
                        ${sessionScope.arbol_externo}
                    </c:if>
                </logic:equal>
                <logic:notEqual name="isAdministrador" value="true">
                    <logic:equal name="isCapturista" value="true">
                        ${sessionScope.arbol_externo}
                    </logic:equal>
                </logic:notEqual>
            </ul>
        </div>
    </div><!-- arbol - modal -->
</html:form>
<logic:notEmpty name="areaForm" property="empleados">
    <div id="divAddDatosEmpleado">
        <div class="content border">
            <div class="inner corner">
                <div>
                    <dt:table id="Empleados" pagesize="5"
                              name="sessionScope.areaForm.empleados"
                              requestURI="/empleados.do?method=init&order=true" requestURIcontext="true"
                              excludedParams="*" sort="list" defaultsort="1">
                        <dt:column property="nombre" title="Nombre" sortable="true"/>
                        <dt:column property="paterno" title="Apellido Paterno" sortable="true"/>
                        <dt:column property="materno" title="Apellido Materno" sortable="true"/>
                        <dt:column property="correo" title="Correo" sortable="true"/>
                        <dt:column property="puesto" title="Puesto" sortable="true"/>
                        <dt:column title="Editar">
                            <input  type="button" class="btn_default" onmouseover="style.cursor='pointer'"
                                    onclick="cargarEmpleado('<bean:write name="Empleados" property="id_empleado" />');"
                                    onMouseOver="style.cursor='pointer'" title="Editar" value="Editar"/>
                        </dt:column>
                        <dt:column title="Eliminar">
                            <input  type="button" class="btn_default" onmouseover="style.cursor='pointer'"
                                    onclick="eliminarEmpleado('<bean:write name="Empleados" property="id_empleado" />');"
                                    onMouseOver="style.cursor='pointer'" title="Eliminar" value="Eliminar"/>
                        </dt:column>
                    </dt:table>

                    <br><br>
                </div>
            </div> <!-- inner corner -->
        </div> <!-- content border -->
    </div> <!-- divAddDatosEmpleado -->
</logic:notEmpty>
