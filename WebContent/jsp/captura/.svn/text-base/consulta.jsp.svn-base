<%@ include file="/jsp/arbol.jsp"%>
<link href='<html:rewrite page="/jsp/asunto/css/asuntos.css"/>' rel="stylesheet" type="text/css" />
<link href='<html:rewrite page="/jsp/css/style.css"/>' rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<html:rewrite page="/jsp/asunto/js/consulta.js"/>"></script>
<script type="text/javascript" src="<html:rewrite page="/jsp/js/jquery.paginate.js"/>"></script>
<% String actionForm = "/consulta-captura.do";
   actionForm = actionForm + "?p_bandeja=";
   actionForm = actionForm + request.getParameter("p_bandeja");
   actionForm = actionForm + "&p_estatus=";
   actionForm = actionForm + request.getParameter("p_estatus");
   actionForm = actionForm + "&p_confidencial=";
   actionForm = actionForm + request.getParameter("p_confidencial");
   actionForm = actionForm + "&p_copia=";
   actionForm = actionForm + request.getParameter("p_copia");
   actionForm = actionForm + "&p_historico=";
   actionForm = actionForm + request.getParameter("p_historico");
%>
<html:form action='<%= actionForm %>' styleId="consultaCapturaForm">

    <html:hidden property="method" styleId="method" value=""/>
    <html:hidden name="consultaCapturaForm" property="id_asunto" />
    <html:hidden name="consultaCapturaForm" property="idx" />
    <html:hidden name="consultaCapturaForm" styleId="nuevaconsulta" property="nuevaConsulta"/>    
    <html:hidden name="consultaCapturaForm" styleId="chksRepForm" property="chksRep"/>

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
                                        <html:text name="consultaCapturaForm" property="criterioAsunto.folio"
                                                   maxlength="50" size="15" styleClass="criterio upperCase"/>
                                    </td>
                                    <td>
                                        <html:text name="consultaCapturaForm" property="criterioAsunto.id_asunto_ref"
                                                   maxlength="50" size="15" styleClass="criterio upperCase"/>
                                    </td>
                                    <td>
                                        <html:text name="consultaCapturaForm" property="criterioAsunto.asunto"
                                                   maxlength="200" size="50" styleClass="criterio upperCase"/>
                                    </td>
                                    <td>
                                        <html:select property="criterioAsunto.id_tipo_documento" name="consultaCapturaForm" styleClass="criterio">
                                            <html:option value="-1">Seleccione una opci&oacute;n</html:option>
                                            <html:optionsCollection name="consultaCapturaForm"  property="tiposDocumento" value="id_tipo_documento" label="tipo_documento" />
                                        </html:select>
                                    </td>
                                    <td>
                                        <html:text name="consultaCapturaForm" property="criterioAsunto.no_oficio"
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
                                        <html:select property="criterioAsunto.id_prioridad" name="consultaCapturaForm" styleClass="criterio">
                                            <html:option value="-1">Seleccione una opci&oacute;n</html:option>
                                            <html:optionsCollection name="consultaCapturaForm"  property="prioridades" value="id_prioridad" label="prioridad" />
                                        </html:select>
                                    </td>
                                    <td colspan="2">
                                        <html:text name="consultaCapturaForm" property="criterioAsunto.fh_limite_iniDDMMYYYY" styleId=""  styleClass="criterio calendario" />
                                    </td>
                                </tr>
                            </tbody>

                            <thead>
                                <tr class="ui-widget-header">
                                    <th>Clave expediente</th>
                                    <th>Expediente</th>
                                    <th colspan="2">Temas</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>
                                        <html:text name="consultaCapturaForm" property="expedientes" value="" styleId="cve_expediente" disabled="true" maxlength="20" />
                                    </td>
                                    <td>
                                        <html:select property="criterioAsunto.id_expediente" name="consultaCapturaForm" styleClass="criterio" styleId="cve_expediente_sel">
                                            <html:option value="-1">Seleccione una opci&oacute;n</html:option>
                                            <html:optionsCollection name="consultaCapturaForm" property="expedientes" value="id_expediente" label="cve_expediente" />
                                        </html:select>
                                        <html:select property="criterioAsunto.id_expediente" name="consultaCapturaForm" styleClass="criterio" onchange="actualizaCve()" styleId="expediente_sel">
                                            <html:option value="-1">Seleccione una opci&oacute;n</html:option>
                                            <html:optionsCollection name="consultaCapturaForm" property="expedientes" value="id_expediente" label="expediente" />
                                        </html:select>
                                    </td>

                                    <td colspan="2">
                                        <html:select property="criterioAsunto.id_tema" name="consultaCapturaForm" styleClass="criterio">
                                            <html:option value="-1">Seleccione una opci&oacute;n</html:option>
                                            <html:optionsCollection name="consultaCapturaForm"  property="temas" value="id_tema" label="tema" />
                                        </html:select>

                                    </td>
                                </tr>
                            </tbody>

                        </table>
                        <table class="criterio-consulta ui-widget ui-widget-content">
                            <thead>
                                <tr class="ui-widget-header ">
                                    <th colspan="2">Descripci&oacute;n:</th>
                                    <th>Estatus:</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td colspan="2">
                                        <html:text name="consultaCapturaForm" property="criterioAsunto.descripcion"
                                                   maxlength="150" size="100" styleClass="criterio upperCase" />
                                    </td>
                                    <td>
                                        <html:select  name="consultaCapturaForm" styleId="select-estatus" styleClass="criterio" property="criterioAsunto.estatus">
                                            <html:option value="">Seleccione una opci&oacute;n</html:option>
                                            <html:options collection="status" property="label" labelProperty="value" />

                                        </html:select>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <table class="criterio-consulta ui-widget ui-widget-content">
                            <thead>
                                <tr class="ui-widget-header ">
                                    <th>Fecha documento inicial</th>
                                    <th>Fecha documento final</th>
                                    <th>Fecha registro inicial</th>
                                    <th>Fecha registro final</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>
                                        <html:text name="consultaCapturaForm" property="criterioAsunto.fh_oficio_iniDDMMYYYY"
                                                   styleId="fh_oficio_ini" readonly="true" size="8" styleClass="criterio"/>
                                    </td>
                                    <td>
                                        <html:text name="consultaCapturaForm" property="criterioAsunto.fh_oficio_finDDMMYYYY"
                                                   styleId="fh_oficio_fin" readonly="true" size="8" styleClass="criterio" />
                                    </td>
                                    <td>
                                        <html:text name="consultaCapturaForm" property="criterioAsunto.fh_registro_iniDDMMYYYY"
                                                   styleId="fh_registro_ini" readonly="true" size="8" styleClass="criterio" />
                                    </td>
                                    <td>
                                        <html:text name="consultaCapturaForm" property="criterioAsunto.fh_registro_finDDMMYYYY"
                                                   styleId="fh_registro_fin" readonly="true" size="8" styleClass="criterio" />
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <table class="criterio-consulta ui-widget ui-widget-content">
                            <thead>
                                <tr class="ui-widget-header ">
                                    <th>Evento</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>
                                        <html:select property="criterioAsunto.id_evento" name="consultaCapturaForm" styleClass="criterio">
                                            <html:option value="-1">Seleccione una opci&oacute;n</html:option>
                                            <html:optionsCollection name="consultaCapturaForm"  property="eventos" value="id_evento" label="evento" />
                                        </html:select>
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
						Todo:<html:radio name="consultaCapturaForm" property="criterioAsunto.tipo_area_remi"
                                                    styleClass="radio-arbol-todo" title="ramaA" alt="modal-arbol-todo"  value ="0" />
                                                Interno:<html:radio name="consultaCapturaForm" property="criterioAsunto.tipo_area_remi"
                                                    styleClass="radio-arbol-interno" title="ramaA" alt="modal" value ="1" />
                               			Externo:<html:radio name="consultaCapturaForm" property="criterioAsunto.tipo_area_remi"
                                                    styleClass="radio-arbol-externo" title="ramaA" alt="modal-arbol-externo"  value ="2" />
                                        <div id="ramaA" title="modal-rama">
                                            <html:text name="consultaCapturaForm" property="criterioAsunto.area_remi"
                                                       disabled="true" styleClass="tree criterio" />
                                            <html:hidden name="consultaCapturaForm" property="criterioAsunto.id_area_remi"
                                                         styleClass="id criterio" />
                                        </div>
                                    </td>
                                    <td>
                                        <html:text name="consultaCapturaForm" property="criterioAsunto.nombre_remi"
                                                   maxlength="150" styleClass="criterio upperCase" />
                                    </td>
                                    <td>
                                        <html:text name="consultaCapturaForm" property="criterioAsunto.paterno_remi"
                                                   maxlength="100" styleClass="criterio upperCase" />
                                    </td>
                                    <td>
                                        <html:text name="consultaCapturaForm" property="criterioAsunto.materno_remi"
                                                   maxlength="100" styleClass="criterio upperCase" />
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <h3 class="head ui-accordion-header ui-state-default ui-widget-content ui-widget-header">
                    <span class="ui-icon ui-icon-triangle-1-e"></span>
                    <a>Criterios de Destinatario</a>
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
						 Todo:<html:radio name="consultaCapturaForm" property="criterioAsunto.tipo_area_dest"
                                                    styleClass="radio-arbol-todo" title="arbolA" alt="modal-todo" value ="0" />
                                                 Interno:<html:radio name="consultaCapturaForm" property="criterioAsunto.tipo_area_dest"
                                                    styleClass="radio-arbol-interno" title="arbolA" alt="modal" value ="1" />
                               			 Externo:<html:radio name="consultaCapturaForm" property="criterioAsunto.tipo_area_dest"
                                                    styleClass="radio-arbol-externo" title="arbolA" alt="modal-arbol-externo"  value ="2" />
                                        <div id="arbolA">
                                            <html:text name="consultaCapturaForm" property="criterioAsunto.area_dest"
                                                       disabled="true" styleClass="tree criterio" />
                                            <html:hidden name="consultaCapturaForm" property="criterioAsunto.id_area_dest"
                                                         styleClass="id criterio" />
                                        </div>
                                    </td>

                                    <td>
                                        <html:text name="consultaCapturaForm" property="criterioAsunto.nombre_dest"
                                                   maxlength="150" styleClass="criterio upperCase" />
                                    </td>
                                    <td>
                                        <html:text name="consultaCapturaForm" property="criterioAsunto.paterno_dest"
                                                   maxlength="100" styleClass="criterio upperCase" />
                                    </td>
                                    <td>
                                        <html:text name="consultaCapturaForm" property="criterioAsunto.materno_dest"
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
                                <input type="button" class="btn_default" id="obtenerAsuntosCapturados"
                                       value="Buscar" title="Buscar" onmouseover="style.cursor='pointer'" />
                            </td>
                            <td>
                                <input type="button" onclick="obtenerAsunto('-1');" id="agregarAsunto"
                                       class="btn_default" value="Capturar Asunto" title="Capturar Asunto"	onmouseover="style.cursor='pointer'" />
                            </td>
                            <%--<td>
                                    <input type="button" id="resumenAsuntos"
                                           class="btn_default" value="Reporte Asuntos" title="Reporte Asuntos"	onmouseover="style.cursor='pointer'" />
                                </td> --%>
                            <logic:present name="VOLANTE_CORRESPONDENCIA">
                                <td>
                                    <input type="button" id="obtenerVolante"
                                           class="btn_default" value="Generar Volante" title="Generar Volante"	onmouseover="style.cursor='pointer'" />
                                </td>
                            </logic:present>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <!--Los resultados de la busqueda-->
    <logic:notEmpty name="consultaCapturaForm" property="asuntos">
        <div class="content border">
            <div class="inner corner">
                <div class="contentform accordion ui-accordion">
                    <table>
                        <thead>
                            <tr class="ui-widget-header">
                                <th colspan="11">Total:  <bean:write name="consultaCapturaForm"  property="criterioAsunto.paginador.numRegistros" />  </th>
                            </tr>
                        </thead>
                        <tr>
                            <td>

                                <dt:table id="asunto" name="sessionScope.consultaCapturaForm.asuntos"
                                          requestURI="/consulta-captura.do?method=inicio&order=true" requestURIcontext="true"
                                          excludedParams="*" sort="list">
                                    <%--<dt:column property="folio" title="Folio" sortable="true"></dt:column>--%>
                                    <%--<dt:column title="">
                                        <logic:equal name="asunto" property="estatus" value="1">  
                                            <input type="checkbox"
                                                   onChange="marcaParaReporte(this,'<bean:write name="asunto" property="id_asunto" />');"
                                                   id="<bean:write name="asunto" property="id_asunto" />" value="1" title="chkReporte" />
                                        </logic:equal>
                                        <logic:equal name="asunto" property="estatus" value="14"> 
                                            <input type="checkbox"
                                                   onChange="marcaParaReporte(this,'<bean:write name="asunto" property="id_asunto" />');"
                                                   id="<bean:write name="asunto" property="id_asunto" />" value="1" title="chkReporte" />
                                        </logic:equal>
                                        <logic:notEqual name="asunto" property="estatus" value="1">
                                            <logic:notEqual name="asunto" property="estatus" value="14">
                                               <input type="checkbox"
                                                   onChange="marcaParaReporte(this,'<bean:write name="asunto" property="id_asunto" />');"
                                                   id="<bean:write name="asunto" property="id_asunto" />" value="1" title="chkReporte"
                                                    disabled="disabled" />
                                            </logic:notEqual>
                                        </logic:notEqual>
                                    </dt:column>--%>
                                    <dt:column property="contador_folio" title="Folio" sortable="false"></dt:column>
                                    <dt:column property="asunto" title="Titulo" sortable="false"></dt:column>
                                    <dt:column property="asunto_detalle.fh_lectura" title="¿Le&iacute;do?" sortable="false" format="{0,date,dd/MM/yyyy-HH:mm}"></dt:column>
                                    <dt:column property="no_oficio" title="Documento" sortable="false"></dt:column>
                                    <%--<dt:column property="fh_oficio" title="Fecha de registro" sortable="true" format="{0,date,dd-MM-yyyy}"></dt:column> Alex:20111018--%>
                                    <dt:column property="asunto_detalle.fh_registro" title="Fecha de registro" sortable="false" format="{0,date,dd-MM-yyyy}"></dt:column>
                                    <dt:column property="asunto_detalle.empleado_remi.area.area" title="Area Remitente" sortable="false"></dt:column>
                                    <dt:column property="asunto_detalle.empleado_remi.nombre_completo" title="Remitente" sortable="false"></dt:column>
                                    <dt:column property="asunto_detalle.empleado_dest.area.area" title="Area Destinatario" sortable="false"></dt:column>
                                    <dt:column property="asunto_detalle.empleado_dest.nombre_completo" title="Destinatario" sortable="false"></dt:column>
                                    <dt:column property="estatus_desc" title="Estatus" sortable="false"></dt:column>
                                    <dt:column title="">
                                        <input type="button"
                                               onclick="obtenerAsunto('<bean:write name="asunto" property="id_asunto" />');"
                                               onmouseover="style.cursor='pointer'"
                                               class="btn_default"
                                               id="obtener" value="Ver detalle" title="Ver detalle" />
                                    </dt:column>
                                </dt:table>
                                <html:hidden name="consultaCapturaForm" styleId="pagina" property="criterioAsunto.paginador.pagina"/>
                                <html:hidden name="consultaCapturaForm" styleId="paginas" property="criterioAsunto.paginador.paginas"/>
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


<SCRIPT type="text/javascript"  LANGUAGE="JavaScript">
$( function() {
    document.getElementById('cve_expediente_sel').style.display = "none";
});
</SCRIPT>


