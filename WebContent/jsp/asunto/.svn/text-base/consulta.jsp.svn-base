<%@ include file="/jsp/arbol.jsp"%>
<%@ page import="mx.gob.economia.scg.util.Constantes"%>
<link href='<html:rewrite page="/jsp/asunto/css/asuntos.css"/>'
      rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<html:rewrite page="/jsp/asunto/js/consulta.js"/>"></script>
<script type="text/javascript" src="<html:rewrite page="/jsp/js/jquery.paginate.js"/>"></script>
<html:form action="/consulta.do" styleId="consultaForm">
    <html:hidden property="method" value="" />
    <html:hidden name="consultaForm" property="id_asunto" />
    <html:hidden name="consultaForm" property="idx" />
    <html:hidden name="consultaForm" styleId="nuevaconsulta" property="nuevaConsulta"/>

    <div class="content border">
        <div class="inner corner">
            <div class="contentform">
                <table width="100%" border="0">
                    <tr>
                        <td nowrap="nowrap">
                            <table width="100%" border="0">
                                <tr>
                                    <td class="td_concepto_izq_gde" colspan="6">
									Asunto
                                        <html:img src="../../imagenes/down.png" styleId="imgVerAsunto"
                                                  width="20px" height="20px" title="Ver criterios" alt="" style="display: none"/>
                                        <html:img src="../../imagenes/up.png" styleId="imgOculAsunto"
                                                  width="20px" height="20px" title="Ocultar criterios" alt="" /></td>
                                </tr>
                                <tr id="trAsunto1">
                                    <td class="td_concepto_izq_peq">Folio:</td>
                                    <td class="td_concepto_izq_peq">T&iacute;tulo</td>
                                    <td class="td_concepto_izq_peq">Documento:</td>
                                    <td class="td_concepto_izq_peq" colspan="3">Estatus:</td>
                                </tr>
                                <tr id="trAsunto2">
                                    <td class="td_concepto_izq_peq">
                                        <html:text name="consultaForm" property="criterioAsunto.folio"
                                                   maxlength="50" size="15" styleClass="criterio upperCase" />
                                    </td>
                                    <td class="td_concepto_izq_peq">
                                        <html:text name="consultaForm" property="criterioAsunto.asunto"
                                                   maxlength="200" size="50" styleClass="criterio upperCase" />
                                    </td>
                                    <td class="td_concepto_izq_peq">
                                        <html:text name="consultaForm" property="criterioAsunto.no_oficio"
                                                   maxlength="30" size="10" styleClass="criterio upperCase" />
                                    </td>
                                    <td class="td_concepto_izq_peq" colspan="3">
                                        <bean:define id="estatus_map" name="consultaForm" property="estatusMap"/>
                                        <html:select property="criterioAsunto.estatus" name="consultaForm"
                                                     styleClass="criterio">
                                            <html:option value="">Seleccione una opci&oacute;n</html:option>
                                            <html:options collection="estatus_map"
                                                          property="key"
                                                          labelProperty="value" />
                                        </html:select>
                                    </td>
                                </tr>
                            </table>
                            <table class="criterio-consulta ui-widget ui-widget-content">
                                <thead>
                                    <tr class="ui-widget-header ">
                                        <th colspan="3">Descripci&oacute;n:</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>
                                            <html:text name="consultaForm" property="criterioAsunto.descripcion"
                                                       maxlength="150" size="100" styleClass="criterio upperCase" />
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td nowrap="nowrap">
                            <table width="100%" border="0">
                                <tr id="trAsunto3">
                                    <td class="td_concepto_izq_peq">Fecha de registro del:</td>
                                    <td class="td_concepto_izq_peq">al:</td>
                                    <td class="td_concepto_izq_peq">Fh. Lectura del:</td>
                                    <td class="td_concepto_izq_peq">al:</td>
                                    <td class="td_concepto_izq_peq">Fh. Atenci&oacute;n del:</td>
                                    <td class="td_concepto_izq_peq">al:</td>
                                </tr>
                                <tr id="trAsunto4">
                                    <td class="td_concepto_izq_peq" nowrap="nowrap">
                                        <html:text name="consultaForm" property="criterioAsunto.fh_oficio_iniDDMMYYYY"
                                                   styleId="fh_oficio_ini" readonly="true" size="8" styleClass="criterio" />
                                    </td>
                                    <td class="td_concepto_izq_peq" nowrap="nowrap">
                                        <html:text name="consultaForm" property="criterioAsunto.fh_oficio_finDDMMYYYY"
                                                   styleId="fh_oficio_fin" readonly="true" size="8" styleClass="criterio" />
                                    </td>
                                    <td class="td_concepto_izq_peq" nowrap="nowrap">
                                        <html:text name="consultaForm" property="criterioAsunto.fh_lectura_iniDDMMYYYY"
                                                   styleId="fh_lectura_ini" readonly="true" size="8" styleClass="criterio" />
                                    </td>
                                    <td class="td_concepto_izq_peq" nowrap="nowrap">
                                        <html:text name="consultaForm" property="criterioAsunto.fh_lectura_finDDMMYYYY"
                                                   styleId="fh_lectura_fin" readonly="true" size="8" styleClass="criterio" />
                                    </td>
                                    <td class="td_concepto_izq_peq" nowrap="nowrap">
                                        <html:text name="consultaForm" property="criterioAsunto.fh_registro_iniDDMMYYYY"
                                                   styleId="fh_registro_ini" readonly="true" size="8" styleClass="criterio" />
                                    </td>
                                    <td class="td_concepto_izq_peq" nowrap="nowrap">
                                        <html:text name="consultaForm" property="criterioAsunto.fh_registro_finDDMMYYYY"
                                                   styleId="fh_registro_fin" readonly="true" size="8" styleClass="criterio" />
                                    </td>
                                </tr>
                                <hr id="hrAsunto" />
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <table width="100" border="0">
                                <tr>
                                    <td class="td_concepto_izq_gde" colspan="6">
									Captura 
                                        <html:img src="../../imagenes/down.png" styleId="imgVerCaptura"
                                                  width="20px" height="20px" title="Ver criterios" alt="" />
                                        <html:img src="../../imagenes/up.png" styleId="imgOculCaptura"
                                                  width="20px" height="20px" title="Ocultar criterios" alt="" />
                                    </td>
                                </tr>
                                <tr id="trCaptura1" style="display: none">
                                    <td class="td_concepto_izq_peq">&Aacute;rea:</td>
                                    <td class="td_concepto_izq_peq">Nombre:</td>
                                    <td class="td_concepto_izq_peq">Paterno:</td>
                                    <td class="td_concepto_izq_peq" colspan="3">Materno:</td>
                                </tr>
                                <tr id="trCaptura2" style="display: none">
                                    <td class="td_concepto_izq_peq">
                                        <div id="arbolA">
                                            <html:text name="consultaForm" property="criterioAsunto.area_capt"
                                                       disabled="true" styleClass="tree criterio" />
                                            <html:hidden name="consultaForm" property="criterioAsunto.id_area_capt"
                                                         styleClass="id criterio" />
                                        </div>
                                    </td>
                                    <td class="td_concepto_izq_peq">
                                        <html:text name="consultaForm" property="criterioAsunto.nombre_capt"
                                                   maxlength="150" styleClass="criterio upperCase" />
                                    </td>
                                    <td class="td_concepto_izq_peq">
                                        <html:text name="consultaForm" property="criterioAsunto.paterno_capt"
                                                   maxlength="100" styleClass="criterio upperCase" />
                                    </td>
                                    <td class="td_concepto_izq_peq" colspan="3">
                                        <html:text name="consultaForm" property="criterioAsunto.materno_capt"
                                                   maxlength="100" styleClass="criterio upperCase" />
                                    </td>
                                </tr>
                                <hr id="hrCaptura">
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <table width="100%" border="0">
                                <tr>
                                    <td class="td_concepto_izq_gde" colspan="6">
									Remitente 
                                        <html:img src="../../imagenes/down.png" width="20px" height="20px"
                                                  styleId="imgVerRemi" title="Ver criterios" alt="" />
                                        <html:img src="../../imagenes/up.png" width="20px" height="20px"
                                                  styleId="imgOculRemi" title="Ocultar criterios" alt="" />
                                    </td>
                                </tr>
                                <tr id="trRemitente1" style="display: none">
                                    <td class="td_concepto_izq_peq">&Aacute;rea:</td>
                                    <td class="td_concepto_izq_peq">Nombre:</td>
                                    <td class="td_concepto_izq_peq">Paterno:</td>
                                    <td class="td_concepto_izq_peq" colspan="3">Materno:</td>
                                </tr>
                                <tr id="trRemitente2" style="display: none">
                                    <td class="td_concepto_izq_peq">
                                        <div id="arbolB">
                                            <html:text name="consultaForm" property="criterioAsunto.area_remi"
                                                       disabled="true" styleClass="tree criterio" />
                                            <html:hidden name="consultaForm" property="criterioAsunto.id_area_remi"
                                                         styleClass="id criterio" />
                                        </div>
                                    </td>
                                    <td class="td_concepto_izq_peq">
                                        <html:text name="consultaForm" property="criterioAsunto.nombre_remi"
                                                   maxlength="150" styleClass="criterio upperCase" />
                                    </td>
                                    <td class="td_concepto_izq_peq">
                                        <html:text name="consultaForm" property="criterioAsunto.paterno_remi"
                                                   maxlength="100" styleClass="criterio upperCase" />
                                    </td>
                                    <td class="td_concepto_izq_peq" colspan="3">
                                        <html:text name="consultaForm" property="criterioAsunto.materno_remi"
                                                   maxlength="100" styleClass="criterio upperCase" />
                                    </td>
                                </tr>
                                <hr id="hrRemi">
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <table width="100%" border="0">
                                <hr id="hrRemi">
                                <tr>
                                    <td class="td_concepto_izq_gde" colspan="6">
									Destinatario
                                        <html:img src="../../imagenes/down.png" width="20px" height="20px"
                                                  styleId="imgVerDestina" title="Ver criterios" alt="" />
                                        <html:img src="../../imagenes/up.png" width="20px" height="20px"
                                                  styleId="imgOculDestina" title="Ocultar criterios" alt="" />
                                    </td>
                                </tr>
                                <tr id="trDestina1" style="display: none">
                                    <td class="td_concepto_izq_peq">&Aacute;rea:</td>
                                    <td class="td_concepto_izq_peq">Nombre:</td>
                                    <td class="td_concepto_izq_peq">Paterno:</td>
                                    <td class="td_concepto_izq_peq" colspan="3">Materno:</td>
                                </tr>
                                <tr id="trDestina2" style="display: none">
                                    <td class="td_concepto_izq_peq">
                                        <div id="ramaA" title="modal-rama">
                                            <html:text name="consultaForm" property="criterioAsunto.area_dest"
                                                       disabled="true" styleClass="tree criterio" />
                                            <html:hidden name="consultaForm" property="criterioAsunto.id_area_dest"
                                                         styleClass="id criterio" />
                                        </div>
                                    </td>
                                    <td class="td_concepto_izq_peq">
                                        <html:text name="consultaForm" property="criterioAsunto.nombre_dest"
                                                   maxlength="150" styleClass="criterio upperCase" />
                                    </td>
                                    <td class="td_concepto_izq_peq">
                                        <html:text name="consultaForm" property="criterioAsunto.paterno_dest"
                                                   maxlength="100" styleClass="criterio upperCase" />
                                    </td>
                                    <td class="td_concepto_izq_peq" colspan="3">
                                        <html:text name="consultaForm" property="criterioAsunto.materno_dest"
                                                   maxlength="100" styleClass="criterio upperCase" />
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="6" align="center">
                            <input type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"
                                   id="limpiar" value="Limpiar Formulario" title="Limpiar Formulario" onmouseover="style.cursor='pointer'" />
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <input type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" onclick="limpiarPaginas();"
                                   id="obtenerAsuntos" value="Buscar" title="Buscar" onmouseover="style.cursor='pointer'" />
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <input type="button" onclick="obtenerAsunto('-1');"
                                   class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"
                                   id="agregarAsunto" value="Agregar Asunto" title="Agregar Asunto"
                                   onmouseover="style.cursor='pointer'" />
                        </td>
                    </tr>
                </table>
            </div><!-- content border -->
        </div><!-- inner corner -->
    </div><!-- contentForm -->

    <table>
        <logic:notEmpty name="consultaForm" property="asuntos">

            <thead>
                <tr class="ui-widget-header">                          
                    <th colspan="11">Total:  <bean:write name="consultaForm"  property="criterioAsunto.paginador.numRegistros" />  </th>
                </tr>
            </thead>
            <tr>
                <td>
                    <dt:table id="asunto" name="sessionScope.consultaForm.asuntos"
                              requestURI="/consulta.do?method=inicio&order=true" requestURIcontext="true"
                              excludedParams="*" sort="list" pagesize="5" >
                        <dt:column property="contador_folio" title="Folio" sortable="true"></dt:column>
                        <dt:column property="asunto" title="Titulo" sortable="true"></dt:column>
                        <dt:column property="no_oficio" title="Documento" sortable="true"></dt:column>
                        <dt:column property="fh_oficio" title="Fecha de registro" sortable="true" format="{0,date,dd-MM-yyyy}"></dt:column>
                        <dt:column property="asunto_detalle.fh_lectura" title="¿Le&iacute;do?" sortable="true" format="{0,date,dd-MM-yyyy}"></dt:column>
                        <dt:column property="asunto_detalle.empleado_remi.area.area" title="Area Remitente" sortable="true"></dt:column>
                        <dt:column property="asunto_detalle.empleado_remi.nombre_completo" title="Remitente" sortable="true"></dt:column>
                        <dt:column property="asunto_detalle.empleado_dest.area.area" title="Area Destinatario" sortable="true"></dt:column>
                        <dt:column property="asunto_detalle.empleado_dest.nombre_completo" title="Destinatario" sortable="true"></dt:column>
                        <dt:column property="asunto_detalle.fh_limite" title="Fh. Limite" sortable="true" format="{0,date,dd-MM-yyyy}"></dt:column>
                        <dt:column title="Estatus" sortable="true" class="${asunto.color_aten}">
                            <bean:write name="asunto" property="estatus_desc" />
                        </dt:column>
                        <dt:column title="">
                            <input type="button"
                                   onclick="obtenerAsunto('<bean:write name="asunto" property="id_asunto" />');"
                                   onmouseover="style.cursor='pointer'"
                                   class="btn_cntr ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"
                                   id="limpiar" value="Ver detalle" title="Ver detalle" />
                        </dt:column>
                    </dt:table>
                    <html:hidden name="consultaForm" styleId="pagina" property="criterioAsunto.paginador.pagina"/>
                    <html:hidden name="consultaForm" styleId="paginas" property="criterioAsunto.paginador.paginas"/>
                    <div id="paginador">
                    </div>
                </td>
            </tr>
        </logic:notEmpty>
    </table>
</html:form>
<div class="modal">
    <div class="treeviewContent">
        <ul id="tree">
            ${sessionScope.arbol_raiz}
        </ul>
    </div>
</div>
<div class="modal-rama">
    <div class="treeviewContent">
        <ul class="tree-branch">
            ${arbol_rama}
        </ul>
    </div>
</div>