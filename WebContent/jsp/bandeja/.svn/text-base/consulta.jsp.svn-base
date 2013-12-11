<!--Pantalla de inico de bandeja-->
<%@ include file="/jsp/arbol.jsp"%>
<link href='<html:rewrite page="/jsp/asunto/css/asuntos.css"/>' rel="stylesheet" type="text/css" />
<link href='<html:rewrite page="/jsp/css/style.css"/>' rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<html:rewrite page="/jsp/asunto/js/consulta.js"/>"></script>
<script type="text/javascript" src="<html:rewrite page="/jsp/js/jquery.paginate.js"/>"></script>
<% String actionForm = "/consulta-bandeja.do";
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
<html:form action='<%= actionForm %>' styleId="consultaBandejaForm">
    <html:hidden property="method" value="inicio" />
    <html:hidden name="consultaBandejaForm" property="id_asunto" />
    <html:hidden name="consultaBandejaForm" property="idx" />
    <html:hidden name="consultaBandejaForm" styleId="nuevaconsulta" property="nuevaConsulta"/>
    <html:hidden name="consultaBandejaForm" styleId="chksRepForm" property="chksRep"/>
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
                                        <html:text name="consultaBandejaForm" property="criterioAsunto.folio"
                                                   maxlength="50" size="15" styleClass="criterio upperCase"/>
                                    </td>
                                    <td>
                                        <html:text name="consultaBandejaForm" property="criterioAsunto.id_asunto_ref"
                                                   maxlength="50" size="15" styleClass="criterio upperCase"/>
                                    </td>
                                    <td>
                                        <html:text name="consultaBandejaForm" property="criterioAsunto.asunto"
                                                   maxlength="200" size="50" styleClass="criterio upperCase"/>
                                    </td>
                                    <td>
                                        <html:select property="criterioAsunto.id_tipo_documento" name="consultaBandejaForm" styleClass="criterio">
                                            <html:option value="-1">Seleccione una opci&oacute;n</html:option>
                                            <html:optionsCollection name="consultaBandejaForm"  property="tiposDocumento" value="id_tipo_documento" label="tipo_documento" />
                                        </html:select>
                                    </td>
                                    <td>
                                        <html:text name="consultaBandejaForm" property="criterioAsunto.no_oficio"
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
                                        <html:select property="criterioAsunto.id_prioridad" name="consultaBandejaForm" styleClass="criterio">
                                            <html:option value="-1">Seleccione una opci&oacute;n</html:option>
                                            <html:optionsCollection name="consultaBandejaForm"  property="prioridades" value="id_prioridad" label="prioridad" />
                                        </html:select>
                                    </td>
                                    <td colspan="2">
                                        <html:text name="consultaBandejaForm" property="criterioAsunto.fh_limite_iniDDMMYYYY" styleId=""  styleClass="criterio calendario" />
                                    </td>
                                </tr>
                            </tbody>

                            <thead>
                                <tr class="ui-widget-header">
                                    <th colspan="2">Expediente</th>
                                    <th>Temas</th>
                                    <th>Evento</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td colspan="2">
                                        <html:select property="criterioAsunto.id_expediente" name="consultaBandejaForm" styleClass="criterio">
                                            <html:option value="-1">Seleccione una opci&oacute;n</html:option>
                                            <html:optionsCollection name="consultaBandejaForm" property="expedientes" value="id_expediente" label="expediente" />
                                        </html:select>
                                    </td>
                                    <td>
                                        <html:select property="criterioAsunto.id_tema" name="consultaBandejaForm" styleClass="criterio">
                                            <html:option value="-1">Seleccione una opci&oacute;n</html:option>
                                            <html:optionsCollection name="consultaBandejaForm"  property="temas" value="id_tema" label="tema" />
                                        </html:select>
                                    </td>
                                    <td>
                                        <html:select property="criterioAsunto.id_evento" name="consultaBandejaForm" styleClass="criterio">
                                            <html:option value="-1">Seleccione una opci&oacute;n</html:option>
                                            <html:optionsCollection name="consultaBandejaForm"  property="eventos" value="id_evento" label="evento" />
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
                                        <html:text name="consultaBandejaForm" property="criterioAsunto.descripcion"
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
                                    <td><html:text name="consultaBandejaForm" property="criterioAsunto.fh_oficio_iniDDMMYYYY"
                                               styleId="fh_oficio_ini" readonly="true" size="8" styleClass="criterio ui-datepicker-trigger calendarioBefore" />
                                    </td>
                                    <td><html:text name="consultaBandejaForm" property="criterioAsunto.fh_oficio_finDDMMYYYY"
                                               styleId="fh_oficio_fin" readonly="true" size="8" styleClass="criterio ui-datepicker-trigger calendarioBefore" />
                                    </td>
                                    <td><html:text name="consultaBandejaForm" property="criterioAsunto.fh_lectura_iniDDMMYYYY"
                                               styleId="fh_lectura_ini" readonly="true" size="8" styleClass="criterio ui-datepicker-trigger calendarioBefore" />
                                    </td>
                                    <td><html:text name="consultaBandejaForm" property="criterioAsunto.fh_lectura_finDDMMYYYY"
                                               styleId="fh_lectura_fin" readonly="true" size="8" styleClass="criterio ui-datepicker-trigger calendarioBefore" />
                                    </td>
                                    <td><html:text name="consultaBandejaForm" property="criterioAsunto.fh_registro_iniDDMMYYYY"
                                               styleId="fh_registro_ini" readonly="true" size="8" styleClass="criterio ui-datepicker-trigger calendarioBefore" />
                                    </td>
                                    <td><html:text name="consultaBandejaForm" property="criterioAsunto.fh_registro_finDDMMYYYY"
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
						Todo:<html:radio name="consultaBandejaForm" property="criterioAsunto.tipo_area_capt"
                                                    styleClass="radio-arbol-todo" title="ramaA" alt="modal-arbol-todo"  value ="0" />
                                                Interno:<html:radio name="consultaBandejaForm" property="criterioAsunto.tipo_area_capt"
                                                    styleClass="radio-arbol-interno" title="ramaA" alt="modal" value ="1" />
                               			Externo:<html:radio name="consultaBandejaForm" property="criterioAsunto.tipo_area_capt"
                                                    styleClass="radio-arbol-externo" title="ramaA" alt="modal-arbol-externo"  value ="2" />
                                        <div id="ramaA" title="modal-rama">
                                            <html:text name="consultaBandejaForm" property="criterioAsunto.area_capt"
                                                       disabled="true" styleClass="tree criterio" />
                                            <html:hidden name="consultaBandejaForm" property="criterioAsunto.id_area_capt"
                                                         styleClass="id criterio" />
                                        </div>
                                    </td>
                                    <td>
                                        <html:text name="consultaBandejaForm" property="criterioAsunto.nombre_capt"
                                                   maxlength="150" styleClass="criterio upperCase" />
                                    </td>
                                    <td>
                                        <html:text name="consultaBandejaForm" property="criterioAsunto.paterno_capt"
                                                   maxlength="100" styleClass="criterio upperCase" />
                                    </td>
                                    <td>
                                        <html:text name="consultaBandejaForm" property="criterioAsunto.materno_capt"
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
						Todo:<html:radio name="consultaBandejaForm" property="criterioAsunto.tipo_area_remi"
                                                    styleClass="radio-arbol-todo" title="ramaA" alt="modal-arbol-todo"  value ="0" />
                                                Interno:<html:radio name="consultaBandejaForm" property="criterioAsunto.tipo_area_remi"
                                                    styleClass="radio-arbol-interno" title="ramaA" alt="modal" value ="1" />
                               			Externo:<html:radio name="consultaBandejaForm" property="criterioAsunto.tipo_area_remi"
                                                    styleClass="radio-arbol-externo" title="ramaA" alt="modal-arbol-externo"  value ="2" />
                                        <div id="ramaA" title="modal-rama">
                                            <html:text name="consultaBandejaForm" property="criterioAsunto.area_remi"
                                                       disabled="true" styleClass="tree criterio" />
                                            <html:hidden name="consultaBandejaForm" property="criterioAsunto.id_area_remi"
                                                         styleClass="id criterio" />
                                        </div>
                                    </td>
                                    <td>
                                        <html:text name="consultaBandejaForm" property="criterioAsunto.nombre_remi"
                                                   maxlength="150" styleClass="criterio upperCase" />
                                    </td>
                                    <td>
                                        <html:text name="consultaBandejaForm" property="criterioAsunto.paterno_remi"
                                                   maxlength="100" styleClass="criterio upperCase" />
                                    </td>
                                    <td>
                                        <html:text name="consultaBandejaForm" property="criterioAsunto.materno_remi"
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
						Todo:<html:radio name="consultaBandejaForm" property="criterioAsunto.tipo_area_dest"
                                                    styleClass="radio-arbol-todo" title="ramaA" alt="modal-arbol-todo"  value ="0" />
                                                Interno:<html:radio name="consultaBandejaForm" property="criterioAsunto.tipo_area_dest"
                                                    styleClass="radio-arbol-interno" title="arbolC" alt="modal" value ="0" />
                               			Externo:<html:radio name="consultaBandejaForm" property="criterioAsunto.tipo_area_dest"
                                                    styleClass="radio-arbol-externo" title="arbolC" alt="modal-arbol-externo"  value ="1" />
                                        <div id="arbolC">
                                            <html:text name="consultaBandejaForm" property="criterioAsunto.area_dest"
                                                       disabled="true" styleClass="tree criterio" />
                                            <html:hidden name="consultaBandejaForm" property="criterioAsunto.id_area_dest"
                                                         styleClass="id criterio" />
                                        </div>
                                    </td>
                                    <td>
                                        <html:text name="consultaBandejaForm" property="criterioAsunto.nombre_dest"
                                                   maxlength="150" styleClass="criterio upperCase" />
                                    </td>
                                    <td>
                                        <html:text name="consultaBandejaForm" property="criterioAsunto.paterno_dest"
                                                   maxlength="100" styleClass="criterio upperCase" />
                                    </td>
                                    <td>
                                        <html:text name="consultaBandejaForm" property="criterioAsunto.materno_dest"
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
                            <% if(request.getParameter("p_bandeja").equals("2")) { %>
                                <td>
                                    <input type="button" id="resumenAsuntosBandeja"
                                           class="btn_default" value="Reporte Asuntos Pendientes" title="Reporte Asuntos Pendientes"	onmouseover="style.cursor='pointer'" />
                                </td>
                            <%}%> <%-- fin de if--%>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <%--<logic:notEmpty name="consultaBandejaForm" property="asuntos">--%>
        <div class="content border">
            <div class="inner corner">
                <div class="contentform accordion ui-accordion">
                    <table>
                        <thead>
                            <tr class="ui-widget-header">                                
                                <th colspan="2">  <% if(request.getParameter("p_bandeja").equals("1")) {
                                                              out.println("Bandeja de Entrada");
                                                              if( request.getParameter("p_estatus").equals("0")) out.println(" - Inicial");
                                                              if( request.getParameter("p_estatus").equals("1")) out.println(" - Recibido");
                                                              if( request.getParameter("p_estatus").equals("14")) out.println(" - En trámite");
                                                              if( request.getParameter("p_estatus").equals("2")) out.println(" - Para visto bueno");
                                                              if( request.getParameter("p_copia").equals("1")) out.println(" - Con copia");
                                                      }
                                                      else if (request.getParameter("p_bandeja").equals("2")) {
                                                              out.println("Bandeja de Salida");
                                                              if( request.getParameter("p_estatus").equals("1")) out.println(" - Pendiente");
                                                              if( request.getParameter("p_estatus").equals("2")) out.println(" - Atendido");
                                                              if( request.getParameter("p_estatus").equals("14")) out.println(" - En trámite");
                                                      }
                                                      else if (request.getParameter("p_bandeja").equals("3")){
                                                              if( request.getParameter("p_estatus").equals("7")) out.println("En captura");
                                                              if( request.getParameter("p_estatus").equals("3")) out.println("Concluido");
                                                              if( request.getParameter("p_estatus").equals("-1") && request.getParameter("p_historico").equals("1")) out.println("Seguimiento de asuntos");
                                                      }
                                                   %> </th>
                            </tr>
                            <% if(request.getParameter("p_bandeja").equals("2")) { %>
                                <tr class="ui-widget-header">
                                    <th width="120px"><p align="left">
                                    <input type="checkbox" onChange="marcaTodos(this)"
                                                           id="marcarTodos" value="1" title="chkTodos" />
                                        Marcar todos. </p>
                                    </th>
                                    <th>Total:  <bean:write name="consultaBandejaForm"  property="criterioAsunto.paginador.numRegistros" />  </th>
                                </tr>
                            <%} else {%>
                                <tr class="ui-widget-header">
                                    <th>Total:  <bean:write name="consultaBandejaForm"  property="criterioAsunto.paginador.numRegistros" />  </th>
                                </tr>
                            <%}%>
                        </thead>                        
                        <tr>
                            <td colspan="2">
                                <dt:table id="asunto" name="sessionScope.consultaBandejaForm.asuntos"
                                          requestURI="/consulta-bandeja.do?method=inicio&order=true" requestURIcontext="true"
                                          excludedParams="*" sort="list">
                                    <% if(request.getParameter("p_bandeja").equals("2")) { %>
                                        <dt:column title="">
                                            <%--<logic:equal name="asunto" property="estatus" value="1">
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
                                            </logic:notEqual>--%>
                                            <input type="checkbox"
                                                       onChange="marcaParaReporte(this,'<bean:write name="asunto" property="id_asunto" />');"
                                                       id="<bean:write name="asunto" property="id_asunto" />" value="1" title="chkReporte" name="chksReporte"/>
                                        </dt:column>
                                    <%}%> <%-- fin de if--%>
                                    <dt:column property="contador_folio" title="Folio" sortable="false" class="${asunto.color_confidencial}"></dt:column>
                                    <dt:column property="asunto" title="Titulo" sortable="false" class="${asunto.color_confidencial}"></dt:column>
                                    <dt:column property="no_oficio" title="Documento" sortable="false" class="${asunto.color_confidencial}"></dt:column>
                                    <dt:column property="asunto_detalle.fh_registro" title="Fecha de registro" sortable="false" format="{0,date,dd-MM-yyyy}" class="${asunto.color_confidencial}"></dt:column>
                                    <dt:column property="asunto_detalle.fh_lectura" title="¿Le&iacute;do?" sortable="false" format="{0,date,dd-MM-yyyy}" class="${asunto.color_confidencial}"></dt:column>
                                    <dt:column property="asunto_detalle.empleado_remi.area.area" title="Area Remitente" sortable="false" class="${asunto.color_confidencial}"></dt:column>
                                    <dt:column property="asunto_detalle.empleado_remi.nombre_completo" title="Remitente" sortable="false" class="${asunto.color_confidencial}"></dt:column>
                                    <dt:column property="asunto_detalle.empleado_dest.area.area" title="Area Destino" sortable="false" class="${asunto.color_confidencial}"></dt:column>
                                    <dt:column property="asunto_detalle.empleado_dest.nombre_completo" title="Destinatario" sortable="false" class="${asunto.color_confidencial}"></dt:column>
                                    <dt:column property="asunto_detalle.fh_limite" title="Fh.Limite" sortable="false" format="{0,date,dd-MM-yyyy}" class="${asunto.color_confidencial}"></dt:column>
                                    <dt:column title="Estatus" sortable="false" class="${asunto.color_aten}">
                                        <bean:write name="asunto" property="estatus_desc" />
                                    </dt:column>
                                    <dt:column title="">
                                        <input type="button"
                                               onclick="obtenerAsuntoBandeja('<bean:write name="asunto" property="id_asunto" />','<bean:write name="asunto" property="confidencial" />',
                                                                             '<bean:write name="asunto" property="asunto_detalle.empleado_dest.id_empleado" />',
                                                                             '<bean:write name="usuario_sesion" property="id_empleado" />');"
                                               onmouseover="style.cursor='pointer'"
                                               class="btn_default"
                                               id="" value="Ver detalle" title="Ver detalle" />
                                    </dt:column>
                                </dt:table>
                                <html:hidden name="consultaBandejaForm" styleId="pagina" property="criterioAsunto.paginador.pagina"/>
                                <html:hidden name="consultaBandejaForm" styleId="paginas" property="criterioAsunto.paginador.paginas"/>
                                <div id="paginador">
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    <%--</logic:notEmpty>--%>
</html:form>

<logic:present name="FOUND" >
    <label class="message-nofound">No se encontraron resultados en la b&uacute;squeda</label>            
</logic:present>

<!--despliegue de areas-->
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