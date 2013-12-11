<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/jsp/arbol.jsp"%>
<script type="text/javascript" src="<html:rewrite page="/jsp/areas/js/arbol.js"/>"></script>
<script type="text/javascript" src="<html:rewrite page="/jsp/areas/js/area.js"/>"></script>
<link href='<html:rewrite page="/jsp/areas/css/area_1.css"/>' rel="stylesheet" type="text/css" />

<div class="content border">
    <div class="inner corner">
        <div class="contentform">

            <html:form action="/areas.do" styleId="areaForm" method="Post">
                <html:hidden property="method" value="" />
                <html:hidden name="areaForm" property="area.activo" value="1" styleId="activo"/>

                <table border="0">
                    <tr>
                        <td class="td_concepto_izq_peq" width="25%">
                            Tipo de &Aacute;rea:
                        </td>
                        <td colspan="2" class="td_concepto_izq_peq">
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
                    </tr>
                    <tr>
                        <td class="td_concepto_izq_peq"  width="25%">
                            <span class="campoRequerido"> * </span>&Aacute;rea:
                        </td>
                        <td colspan="3">
                            <div id="arbolB">
                                <html:text property="area.area" name="areaForm" styleClass="tree required icon-tree-trigger" styleId="area"/>
                                <html:hidden name="areaForm" styleClass="id" styleId="id" property="area.id_area" value=""/>
                                <html:hidden name="areaForm" styleId="id_area_padre" property="area.id_area_padre" value=""/>
                            </div>
                        </td>
                    </tr>

                    <tr><td>&emsp;</td></tr>
                    <tr> <!-- BOTONES -->
                        <td  width="25%">
                            <input  type="button" class="btn_default" onmouseover="style.cursor='pointer'"
                                    onclick="mostrarCampos();"
                                    value="Agregar &Aacute;rea" title="Agregar &Aacute;rea" />
                        </td>
                        <td>
                            <input  type="button" class="btn_default" onmouseover="style.cursor='pointer'"
                                    onclick="cargarArea();"
                                    value="Editar &Aacute;rea" title="Editar &Aacute;rea" />
                        </td>
                        <td>
                            <input  type="button" class="btn_default" onmouseover="style.cursor='pointer'"
                                    onclick="bajaArea();"
                                    value="Eliminar &Aacute;rea" title="Eliminar &Aacute;rea" />
                        </td>
                    </tr>
                </table>

            </div> <!-- contentform -->
        </div> <!-- inner border -->
    </div> <!-- content border -->



    <div id="divAddArea" style="display:none">

        <div class="content border">
            <div class="inner corner">
                <div class="contentform">

                    <table id="Descripcion"  style="display:none;" border="0">
                        <tr>
                            <td class="td_concepto_izq_peq" width="25%">
                               <span class="campoRequerido"> * </span> Descripci&oacute;n &Aacute;rea:
                            </td>
                            <td align="left">
                                <html:text property="areaModificar" styleClass="required upperCase" styleId="areaModificar" value="" name="areaForm"/>
                                <html:hidden property="metodo" styleClass="required upperCase" styleId="metodo" value="" name="areaForm"/>
                            </td>
                        </tr>
                    </table>
					<table id="DatosArea" style="display:none;" border="0">
		               <tr>
		                   <td class="td_concepto_izq_peq" width="25%">
		                     <span class="campoRequerido"> * </span>   Clave:
		                    </td>
		                    <td>
		                        <html:text property="area.clave"  name="areaForm" styleId="clave" styleClass="required upperCase" maxlength="10"/>
		                    </td>
		                </tr>
                                <tr>
		                   <td class="td_concepto_izq_peq" width="25%">
		                        ¿Se le envían correos?:
		                    </td>
		                    <td>
                                        <html:checkbox styleId="correoSi" name="areaForm" property="area.envio_correo" value="1"/>
                                        <html:hidden styleId="correoNo" name="areaForm" property="area.envio_correo" value="0"/>
		                    </td>
		                </tr>
                                <!--
		               <tr>
		                   <td class="td_concepto_izq_peq" width="25%">
		                        ¿Es unidad administrativa?:
		                    </td>
		                    <td>                                        
                                        <html:checkbox styleId="unidad-admva" value="1" name="areaForm" property="area.unidad_administrativa"></html:checkbox>
		                    </td>
		                </tr>
		               <tr>
		                   <td class="td_concepto_izq_peq" width="25%">
		                        ¿Requiere listado de &Aacute;reas?:
		                    </td>
		                    <td>
                                        <html:checkbox name="areaForm" value="1" styleId="listado-area" property="area.listado_area"></html:checkbox>
		                    </td>
		                </tr>
                                -->
		                <tr>
		                    <td colspan="2">
		                        <input  type="button" class="btn_default" onmouseover="style.cursor='pointer'"
		                            onclick="definirAccion();"
		                            value="Guardar" title="Guardar" />
		                        <input  type="button" class="btn_default" onmouseover="style.cursor='pointer'"
		                            onclick="cancelar();"
		                            value="Cancelar" title="Cancelar" />
		                    </td>
		                </tr>
		                <tr>
		                    <td colspan="2">
		                        &emsp;&emsp;&emsp;&emsp;
		                    </td>
		                </tr>
		         </table>
                    <br/>
                    <div class="modal">
                        <div class="treeviewContent">
                            <ul id="tree">

                                <logic:equal name="isAdministrador" value="true">
                                    <c:if test="${areaForm.area.tipo==0||areaForm.area.tipo==null}" >
                                        ${sessionScope.arbol_raiz}
                                    </c:if>
                                    <c:if test="${areaForm.area.tipo==1}" >
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
                    </div>

                </html:form>

            </div> <!-- contentform -->
        </div> <!-- inner border -->
    </div> <!-- content border -->

</div> <!-- divAddArea -->
