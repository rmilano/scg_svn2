<%--Consulta de asunto modificados--%>
<%@ include file="/jsp/arbol.jsp"%>


<link href='<html:rewrite page="/jsp/asunto/css/asuntos.css"/>' rel="stylesheet" type="text/css" />
<link href='<html:rewrite page="/jsp/css/style.css"/>' rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<html:rewrite page="/jsp/asunto/js/consulta.js"/>"></script>
<script type="text/javascript" src="<html:rewrite page="/jsp/js/jquery.paginate.js"/>"></script>

<html:form action="/consulta-modificado.do" styleId="consultaModificadoForm">
    <html:hidden property="method" value="" />
    <html:hidden name="consultaModificadoForm" property="id_asunto" />
    <html:hidden name="consultaModificadoForm" property="idx" />
    <html:hidden name="consultaModificadoForm" styleId="nuevaconsulta" property="nuevaConsulta"/>

    <div class="content border">
        <div class="inner corner">
            <div class="contentform accordion ui-accordion">
                <h3 class="head ui-accordion-header ui-state-default ui-widget-content ui-widget-header">
                    <span class="ui-icon ui-icon-triangle-1-e"></span>
                    <a>Criterios Generales de B&uacute;squeda</a>
                </h3>
                <div class="ui-accordion-content ui-helper-reset ui-widget-content ui-corner-bottom ui-accordion-content-active">
                    <div class="accordion ui-accordion accordion-inner">
                        <table class="criterio-consulta ui-widget ui-widget-content">
                            <thead>
                                <tr class="ui-widget-header ">
                                    <th>Folio</th>
                                    <th>Referencia</th>
                                    <th>T&iacute;tulo</th>
                                    <th>Tipo documento</th>
                                    <th>Documento</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>
                                        <html:text name="consultaModificadoForm" property="criterioAsunto.folio"
                                                   maxlength="50" size="15" styleClass="criterio upperCase"/>
                                    </td>
                                    <td>
                                        <html:text name="consultaModificadoForm" property="criterioAsunto.id_asunto_ref"
                                                   maxlength="50" size="15" styleClass="criterio upperCase"/>
                                    </td>
                                    <td>
                                        <html:text name="consultaModificadoForm" property="criterioAsunto.asunto"
                                                   maxlength="200" size="50" styleClass="criterio upperCase"/>
                                    </td>
                                    <td>
                                        <html:select property="criterioAsunto.id_tipo_documento" name="consultaModificadoForm" styleClass="criterio">
                                            <html:option value="-1">Seleccione una opci&oacute;n</html:option>
                                            <html:optionsCollection name="consultaModificadoForm"  property="tiposDocumento" value="id_tipo_documento" label="tipo_documento" />
                                        </html:select>
                                    </td>
                                    <td>
                                        <html:text name="consultaModificadoForm" property="criterioAsunto.no_oficio"
                                                   maxlength="30" size="10" styleClass="criterio upperCase"/>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <table class="criterio-consulta ui-widget ui-widget-content">
                            <thead>
                                <tr class="ui-widget-header">
                                    <th colspan="2">Prioridad</th>
                                    <th colspan="2">Fecha l&iacute;mite de atenci&oacute;n</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td colspan="2">
                                        <html:select property="criterioAsunto.id_prioridad" name="consultaModificadoForm" styleClass="criterio">
                                            <html:option value="-1">Seleccione una opci&oacute;n</html:option>
                                            <html:optionsCollection name="consultaModificadoForm"  property="prioridades" value="id_prioridad" label="prioridad" />
                                        </html:select>
                                    </td>
                                    <td colspan="2">
                                        <html:text name="consultaModificadoForm" property="criterioAsunto.fh_limite_iniDDMMYYYY" styleId=""  styleClass="criterio calendario" />
                                    </td>
                                </tr>
                            </tbody>

                            <thead>
                                <tr class="ui-widget-header">
                                    <th colspan="2">Expediente</th>
                                    <th colspan="2">Temas</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>

                                    <td colspan="2">
                                        <html:select property="criterioAsunto.id_expediente" name="consultaModificadoForm" styleClass="criterio">
                                            <html:option value="-1">Seleccione una opci&oacute;n</html:option>
                                            <html:optionsCollection name="consultaModificadoForm" property="expedientes" value="id_expediente" label="expediente" />
                                        </html:select>
                                    </td>

                                    <td colspan="2">
                                        <html:select property="criterioAsunto.id_tema" name="consultaModificadoForm" styleClass="criterio">
                                            <html:option value="-1">Seleccione una opci&oacute;n</html:option>
                                            <html:optionsCollection name="consultaModificadoForm"  property="temas" value="id_tema" label="tema" />
                                        </html:select>

                                    </td>
                                </tr>
                            </tbody>

                        </table>
                        <table class="criterio-consulta ui-widget ui-widget-content">
                            <thead>
                                <tr class="ui-widget-header ">
                                    <th colspan="3">Descripci&oacute;n:</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td colspan="3">
                                        <html:text name="consultaModificadoForm" property="criterioAsunto.descripcion"
                                                   maxlength="150" size="100" styleClass="criterio upperCase" />
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <table class="criterio-consulta ui-widget ui-widget-content">
                            <thead>
                                <tr class="ui-widget-header">
                                    <th colspan="2">Fecha Documento</th>
                                    <th colspan="2">Fecha Lectura</th>
                                    <th colspan="2">Fecha Registro</th>
                                </tr>
                                <tr class="ui-widget-header">
                                    <th>inicial</th>
                                    <th>Final</th>
                                    <th>inicial</th>
                                    <th>Final</th>
                                    <th>inicial</th>
                                    <th>Final</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td><html:text name="consultaModificadoForm" property="criterioAsunto.fh_oficio_iniDDMMYYYY"
                                               styleId="fh_oficio_ini" readonly="true" size="8" styleClass="criterio ui-datepicker-trigger calendarioBefore" />
                                    </td>
                                    <td><html:text name="consultaModificadoForm" property="criterioAsunto.fh_oficio_finDDMMYYYY"
                                               styleId="fh_oficio_fin" readonly="true" size="8" styleClass="criterio ui-datepicker-trigger calendarioBefore" />
                                    </td>
                                    <td><html:text name="consultaModificadoForm" property="criterioAsunto.fh_lectura_iniDDMMYYYY"
                                               styleId="fh_lectura_ini" readonly="true" size="8" styleClass="criterio ui-datepicker-trigger calendarioBefore" />
                                    </td>
                                    <td><html:text name="consultaModificadoForm" property="criterioAsunto.fh_lectura_finDDMMYYYY"
                                               styleId="fh_lectura_fin" readonly="true" size="8" styleClass="criterio ui-datepicker-trigger calendarioBefore" />
                                    </td>
                                    <td><html:text name="consultaModificadoForm" property="criterioAsunto.fh_registro_iniDDMMYYYY"
                                               styleId="fh_registro_ini" readonly="true" size="8" styleClass="criterio ui-datepicker-trigger calendarioBefore" />
                                    </td>
                                    <td><html:text name="consultaModificadoForm" property="criterioAsunto.fh_registro_finDDMMYYYY"
                                               styleId="fh_registro_fin" readonly="true" size="8" styleClass="criterio ui-datepicker-trigger calendarioBefore" />
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <h3 class="head ui-accordion-header ui-state-default ui-widget-content ui-widget-header">
                    <span class="ui-icon ui-icon-triangle-1-e"></span>
                    <a>Criterios de Capturista</a>
                </h3>
                <div class="ui-accordion-content ui-helper-reset ui-widget-content ui-corner-bottom ui-accordion-content-active">
                    <div class="accordion ui-accordion accordion-inner">
                        <table class="criterio-consulta ui-widget ui-widget-content">
                            <thead>
                                <tr class="ui-widget-header ">
                                    <th>&Aacute;rea</th>
                                    <th>Nombre</th>
                                    <th>Paterno</th>
                                    <th>Materno</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>
						Todo:<html:radio name="consultaModificadoForm" property="criterioAsunto.tipo_area_capt"
                                                    styleClass="radio-arbol-todo" title="ramaA" alt="modal-arbol-todo"  value ="0" />
                                                Interno:<html:radio name="consultaModificadoForm" property="criterioAsunto.tipo_area_capt"
                                                    styleClass="radio-arbol-interno" title="ramaA" alt="modal" value ="1" />
                               			Externo:<html:radio name="consultaModificadoForm" property="criterioAsunto.tipo_area_capt"
                                                    styleClass="radio-arbol-externo" title="ramaA" alt="modal-arbol-externo"  value ="2" />
                                        <div id="ramaA" title="modal-rama">
                                            <html:text name="consultaModificadoForm" property="criterioAsunto.area_capt"
                                                       disabled="true" styleClass="tree criterio" />
                                            <html:hidden name="consultaModificadoForm" property="criterioAsunto.id_area_capt"
                                                         styleClass="id criterio" />
                                        </div>
                                    </td>
                                    <td>
                                        <html:text name="consultaModificadoForm" property="criterioAsunto.nombre_capt"
                                                   maxlength="150" styleClass="criterio upperCase" />
                                    </td>
                                    <td>
                                        <html:text name="consultaModificadoForm" property="criterioAsunto.paterno_capt"
                                                   maxlength="100" styleClass="criterio upperCase" />
                                    </td>
                                    <td>
                                        <html:text name="consultaModificadoForm" property="criterioAsunto.materno_capt"
                                                   maxlength="100" styleClass="criterio upperCase" />
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <h3 class="head ui-accordion-header ui-state-default ui-widget-content ui-widget-header">
                    <span class="ui-icon ui-icon-triangle-1-e"></span>
                    <a>Criterios de Remitente</a>
                </h3>
                <div class="ui-accordion-content ui-helper-reset ui-widget-content ui-corner-bottom ui-accordion-content-active">
                    <div class="accordion ui-accordion accordion-inner">
                        <table class="criterio-consulta ui-widget ui-widget-content">
                            <thead>
                                <tr class="ui-widget-header ">
                                    <th>&Aacute;rea</th>
                                    <th>Nombre</th>
                                    <th>Paterno</th>
                                    <th>Materno</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>
						Todo:<html:radio name="consultaModificadoForm" property="criterioAsunto.tipo_area_remi"
                                                    styleClass="radio-arbol-todo" title="ramaA" alt="modal-arbol-todo"  value ="0" />
                                                Interno:<html:radio name="consultaModificadoForm" property="criterioAsunto.tipo_area_remi"
                                                    styleClass="radio-arbol-interno" title="ramaA" alt="modal" value ="1" />
                               			Externo:<html:radio name="consultaModificadoForm" property="criterioAsunto.tipo_area_remi"
                                                    styleClass="radio-arbol-externo" title="ramaA" alt="modal-arbol-externo"  value ="2" />
                                        <div id="ramaA" title="modal-rama">
                                            <html:text name="consultaModificadoForm" property="criterioAsunto.area_remi"
                                                       disabled="true" styleClass="tree criterio" />
                                            <html:hidden name="consultaModificadoForm" property="criterioAsunto.id_area_remi"
                                                         styleClass="id criterio" />
                                        </div>
                                    </td>
                                    <td>
                                        <html:text name="consultaModificadoForm" property="criterioAsunto.nombre_remi"
                                                   maxlength="150" styleClass="criterio upperCase" />
                                    </td>
                                    <td>
                                        <html:text name="consultaModificadoForm" property="criterioAsunto.paterno_remi"
                                                   maxlength="100" styleClass="criterio upperCase" />
                                    </td>
                                    <td>
                                        <html:text name="consultaModificadoForm" property="criterioAsunto.materno_remi"
                                                   maxlength="100" styleClass="criterio upperCase" />
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <table>
                    <tbody>
                        <tr>
                            <td>
                                <input type="button" class="btn_default" id="consulta-limpiar" value="Limpiar Formulario"
                                       title="Limpiar Formulario" onmouseover="style.cursor='pointer'" />
                            </td>
                            <td>
                                <input type="button" class="btn_default" id="obtenerAsuntos" value="Buscar"
                                       title="Buscar" onmouseover="style.cursor='pointer'" />
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <logic:notEmpty name="consultaModificadoForm" property="asuntos">
        <div class="content border">
            <div class="inner corner">
                <div class="contentform accordion ui-accordion">

                    <table>
                        <thead>
                            <tr class="ui-widget-header">                          
                                <th colspan="11">Total:  <bean:write name="consultaModificadoForm"  property="criterioAsunto.paginador.numRegistros" />  </th>
                            </tr>
                        </thead>

                        <tr>
                            <td>
                                <dt:table id="asunto" name="sessionScope.consultaModificadoForm.asuntos"
                                          requestURI="/consulta-modificado.do?method=inicio&order=true" requestURIcontext="true"
                                          excludedParams="*" sort="list"><!--Quitar el tamaño default para que no aparesca el numero de pagina-->
                                    <dt:column property="contador_folio" title="Folio" sortable="true"></dt:column>
                                    <dt:column property="asunto" title="Titulo" sortable="true"></dt:column>
                                    <dt:column property="no_oficio" title="Documento" sortable="true"></dt:column>
                                    <dt:column property="fh_oficio" title="Fecha de registro" sortable="true" format="{0,date,dd-MM-yyyy}"></dt:column>
                                    <dt:column property="asunto_detalle.fh_lectura" title="¿Le&iacute;do?" sortable="true" format="{0,date,dd-MM-yyyy}"></dt:column>
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
                                               class="btn_default"
                                               id="limpiar" value="Ver detalle" title="Ver detalle" />
                                    </dt:column>
                                </dt:table>
                                <html:hidden name="consultaModificadoForm" styleId="pagina" property="criterioAsunto.paginador.pagina"/>
                                <html:hidden name="consultaModificadoForm" styleId="paginas" property="criterioAsunto.paginador.paginas"/>
                                <div id="paginador">
                                </div>                            
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </logic:notEmpty>
</html:form>

<logic:present name="FOUND" >
    <label class="message-nofound">No se encontraron resultados en la b&uacute;squeda</label>            
</logic:present>


<div class="modal" title="&Aacute;reas internas">
    <div class="treeviewContent">
        <ul id="tree">
            ${sessionScope.arbol_raiz}
        </ul>
    </div>
</div>
<div class="modal-rama" title="Subáreas internas">
    <div class="treeviewContent">
        <ul class="tree-branch">
            ${arbol_rama}
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