<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/jsp/arbol.jsp"%>

<link href='<html:rewrite page="/jsp/empleados/css/empleado_1.css"/>' rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<html:rewrite page="/jsp/empleados/js/editar-empleado.js"/>"></script>

<div class="content border">
    <div class="inner corner">
        <div class="contentform">
            <html:form action="/empleados.do" styleId="form-empleado-editar"  method="Post">
                <html:hidden styleId="method" property="method" value="inicio"/>

                <table>
                    <thead>
                        <tr class="ui-widget-header texto-igual">                            
                            <th colspan="2">Datos del empleado</th>                                            
                            <th>Roles</th>                                            
                        </tr>
                    </thead>
                    <tbody>
                    <td colspan="2">
                        <table class="empleado ui-widget ui-widget-content texto-igual">
                            <thead>
                                <tr class="ui-widget-header texto-igual">
                                    <th>Tipo:</th>                                            
                                    <th colspan="2"><span class="campoRequerido"> * </span>&Aacute;rea:</th>                                            
                                </tr>
                            </thead>
                            <tbody>
                                <tr>    

                                    <td>Interno:<html:radio name="empleadoForm" property="empleadoCreado.area.tipo"
                                                styleClass="radio-arbol-interno" title="arbol-catalogo-empleado" alt="modal" value ="0" />
                                        Externo:<html:radio name="empleadoForm" property="empleadoCreado.area.tipo"
                                                    styleClass="radio-arbol-externo" title="arbol-catalogo-empleado" alt="modal-arbol-externo"  value ="1" />
                                    </td>
                                    <td colspan="2">
                                        <div id="arbol-catalogo-empleado" title="modal">
                                            <html:text name="empleadoForm" property="empleadoCreado.area.area" styleClass="tree icon-tree-trigger required" />
                                            <html:hidden name="empleadoForm" property="empleadoCreado.area.id_area" styleClass="id" />                                                                       
                                        </div>
                                    </td>

                                </tr>
                            </tbody>

                            <thead>
                                <tr class="ui-widget-header texto-igual">                                                                      
                                    <th><span class="campoRequerido"> * </span>Nombre de empleado:</th>                                            
                                    <th><span class="campoRequerido"> * </span>Apellido paterno:</th>                                            
                                    <th><span class="campoRequerido"> * </span>Apellido materno:</th>                                            
                                </tr>
                            </thead>
                            <tbody>
                                <tr>    
                                    <td>
                                        <html:text name="empleadoForm" property="empleadoCreado.nombre" styleClass="required upperCase" /> 
                                    </td>
                                    <td>
                                        <html:text name="empleadoForm" property="empleadoCreado.paterno" styleClass="required upperCase" /> 
                                    </td>
                                    <td>
                                        <html:text name="empleadoForm" property="empleadoCreado.materno" styleClass="required upperCase" /> 
                                    </td>
                                </tr>
                            </tbody>

                            <thead>
                                <tr class="ui-widget-header texto-igual">                                                                      
                                    <th><span class="campoRequerido sp-correo"> * </span>Correo:</th>                                            
                                    <th>Puesto:</th>                                            
                                    <th><span class="campoRequerido"> * </span>RFC:</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>    
                                    <td>
                                        <html:text name="empleadoForm" property="empleadoCreado.correo" styleClass="required email" /> 
                                    </td>
                                    <td>
                                        <html:text name="empleadoForm" property="empleadoCreado.puesto" styleClass="upperCase" /> 
                                    </td>
                                    <td>
                                        <html:text name="empleadoForm" property="empleadoCreado.rfc" styleClass="rfc upperCase" /> 
                                    </td>
                                </tr>
                            </tbody>

                            <thead>
                                <tr class="ui-widget-header texto-igual">
                                    <th><span class="campoRequerido"> * </span>Contraseña:</th>
                                    <th><span class="campoRequerido"> * </span>Repita la contraseña:</th>
                                    <th>Activo:</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>
                                        <html:password name="empleadoForm" property="empleadoCreado.contrasenna" styleId="pass" />
                                    </td>
                                    <td>
                                        <html:password name="empleadoForm" property="empleadoCreado.contrasenna" styleId="repass" />
                                    </td>
                                    <td>
                                        <html:checkbox styleId="sctivoSi" name="empleadoForm" property="empleadoCreado.activo" value="1"/>
                                        <html:hidden styleId="activoNo" name="empleadoForm" property="empleadoCreado.activo" value="0"/>
                                    </td>
                                </tr>
                            </tbody>
                            <thead>
                                <tr class="ui-widget-header texto-igual">
                                    <th colspan="3">Correo de notificaciones (Alterno):</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>
                                        <html:text name="empleadoForm" property="empleadoCreado.correo_alterno" styleClass="email" />
                                    </td>
                                </tr>
                            </tbody>

                            <tbody>
                                <tr>    
                                    <td colspan="3">
                                        <input type="button" title="Guardar" id="guardar-empleados" value="Guardar" class="btn_default mouseOver" />
                                        <input type="button" title="Cancelar" id="cancelar" value="Cancelar" class="btn_default mouseOver regresar" />
                                    </td>
                                </tr>
                            </tbody>
                        </table>                    
                    </td>                                            
                    <td>
                        <logic:iterate name="empleadoForm" property="roles" id="rolcolecction">
                            <html:multibox property="selectedRoles" >
                                <bean:write name="rolcolecction" property="id_rol" />
                            </html:multibox>
                            <bean:write name="rolcolecction" property="rol" /><br/>                                    
                        </logic:iterate>
                            
                    </td>                                            
                    </tbody>
                </table>
            </html:form>
        </div> <!-- contentform -->
    </div> <!-- inner corner -->
</div> <!-- content border -->
  <!-- divAddEmpleado --> 
  <logic:notEqual name="empleadoForm" property="empleadoCreado.ocupado" value="0">
    <logic:equal name="empleadoForm" property="empleadoCreado.activo" value="1">
        <script type="text/javascript">            
             document.getElementById("sctivoSi").setAttribute("disabled", "true");
        </script>
    </logic:equal>
</logic:notEqual>

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
