<%@ include file="/jsp/taglibs.jsp"%>
<link href='<html:rewrite page="/jsp/css/jquery.treeview.css"/>' rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<html:rewrite page="/jsp/js/jquery.treeview.js"/>"></script>
<script type="text/javascript" src="<html:rewrite page="/jsp/js/arbol.js"/>"></script>
<script type="text/javascript" src="<html:rewrite page="/jsp/js/scg.list.empleado.area.js"/>"></script>
<script type="text/javascript" src="<html:rewrite page="/jsp/js/textLimit.min.js"/>"></script>

<link href='<html:rewrite page="/jsp/captura/css/captura.css"/>' rel="stylesheet" type="text/css" />
<link href='<html:rewrite page="/jsp/captura/css/catalogo.css"/>' rel="stylesheet" type="text/css" />
<link href='<html:rewrite page="/jsp/css/style.css"/>' rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<html:rewrite page="/jsp/js/ajaxfileupload.js"/>"></script>
<script type="text/javascript" src="<html:rewrite page="/jsp/js/jquery.paginate.js"/>"></script>
<script type="text/javascript" src="<html:rewrite page="/jsp/js/misc-functions.js"/>"></script>
<script type="text/javascript" src="<html:rewrite page="/jsp/captura/js/captura.js"/>"></script>
<script type="text/javascript" src="<html:rewrite page="/jsp/captura/js/catalogo.js"/>"></script>
<script type="text/javascript" src="<html:rewrite page="/jsp/captura/js/temas.js"/>"></script>
<script type="text/javascript" src="<html:rewrite page="/jsp/captura/js/actualiza.destinatarios.js"/>"></script>
<div class="content border">
    <div class="inner corner">
        <div class="contentform captura-asunto">
            <html:form action="/captura.do" styleId="capturaForm"  enctype="multipart/form-data">
                <html:hidden property="method" styleId="post-method-name" value="inicio" />
                <html:hidden name="capturaForm" property="idx" />
                <html:hidden name="capturaForm" property="empleadoCaptura.id_area" styleId="empleado-captura-id-area" />
                <html:hidden name="capturaForm" property="asunto.id_asunto" styleId="scg-captura-id-asunto" />
                <html:hidden name="capturaForm" property="displayList" styleId="scg-captura-displaylist" />
                <html:hidden name="capturaForm" property="displayTree" styleId="scg-captura-displaytree" />
                <html:hidden name="capturaForm" property="asunto.asunto_detalle.empleado_remi.id_empleado" styleId="idEmpleadoRemiHid" />
                <html:hidden name="capturaForm" property="puestos_remi" styleId="puestos_remi" />
                <html:hidden name="capturaForm" property="puestos_dest" styleId="puestos_dest" />
                <table class="scg-table-busqueda">
                    <tr>
                        <td>
                            <div class="scg-div-busqueda">
                                <table>
                                    <tbody>
                                        <tr>
                                            <td colspan="3" class="texto-igual">
                                                Para realizar la captura, por favor llene los campos que aparecen a continuacion. Las etiquetas marcadas
                                                con <span class="campoRequerido"> * </span> son requeridas.
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                                <table class="captura ui-widget ui-widget-content texto-igual">
                                    <thead>
                                        <tr class="ui-widget-header texto-igual">
                                            <th>Folio:</th>
                                            <th>Referencia:</th>
                                            <th>Confidencial</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td id="captura-label-folio"><html:text name="capturaForm" property="asunto.folio" styleClass="element-search upperCase numeros" styleId="scg-captura-folio" /></td>
                                            <td>
                                                <logic:equal name="capturaForm" property="asunto.id_asunto_ref" value="CCP">
                                                    <bean:write name="capturaForm" property="asunto.id_asunto_ref"/>
                                                </logic:equal>
                                                <logic:notEqual name="capturaForm" property="asunto.id_asunto_ref" value="CCP">
                                                    <html:text name="capturaForm" property="asunto.id_asunto_ref" maxlength="120"  size="55" styleClass="element-search upperCase numeros" styleId="scg-captura-referencia"/>
                                                </logic:notEqual>
                                            </td>
                                            <td>
                                                <html:checkbox styleId="confidencialSi" name="capturaForm" property="asunto.confidencial" value="1"/>
                                                <html:hidden styleId="confidencialNo" name="capturaForm" property="asunto.confidencial" value="0"/>
                                            </td>
                                        </tr>
                                    </tbody>

                                    <thead>
                                        <tr class="ui-widget-header texto-igual">
                                            <th colspan="3"><span class="campoRequerido"> * </span>Documento:</th>
                                        </tr>
                                    </thead>

                                    <tbody>
                                        <tr>
                                            <td colspan="3"><html:text name="capturaForm" property="asunto.no_oficio" maxlength="30"  size="22" styleClass="required upperCase autowidth element-search" styleId="asunto-captura-documento"/></td>
                                        </tr>
                                    </tbody>

                                    <thead>
                                        <tr class="ui-widget-header texto-igual">
                                            <th colspan="3">Antecedente:</th>
                                        </tr>
                                    </thead>

                                    <tbody>
                                        <tr>
                                            <td colspan="3">
                                                <html:text name="capturaForm" property="asunto.antecedente" maxlength="30"  size="22" styleClass="element-search upperCase" styleId="scg-captura-antecedente"/>
                                            </td>
                                        </tr>
                                    </tbody>

                                    <thead>
                                        <tr class="ui-widget-header texto-igual">
                                            <th>Fecha de registro:</th>
                                            <th><span class="campoRequerido"> * </span> Fecha del documento:</th>
                                            <th>Fecha de recepci&oacute;n:</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <logic:equal name="readonlyNoSintesis" value="true">
                                                <td><html:text name="capturaForm" property="asunto.asunto_detalle.fh_registroDDMMYYYY" styleId="fh_registro" styleClass="element-search" readonly="true" /></td>
                                                <td><html:text name="capturaForm" property="asunto.fh_oficioDDMMYYYY" onblur="validafecha(this,'true');" styleId="fh_oficio" styleClass="required element-search ui-datepicker-trigger" readonly="false" /></td>
                                                <td><html:text name="capturaForm" property="asunto.fh_recepcionDDMMYYYY" onblur="validafecha(this,'false');" styleId="fh_recepcion" styleClass="ui-datepicker-trigger element-search" readonly="false" /></td>
                                            </logic:equal>
                                            <logic:equal name="readonlyNoSintesis" value="false">
                                                <td><html:text name="capturaForm" property="asunto.asunto_detalle.fh_registroDDMMYYYY" styleId="fh_registro" styleClass="element-search" readonly="true" /></td>
                                                <td><html:text name="capturaForm" property="asunto.fh_oficioDDMMYYYY" onblur="validafecha(this,'true');" styleId="fh_oficio" styleClass="required element-search ui-datepicker-trigger calendarioBefore" readonly="false" /></td>
                                                <td><html:text name="capturaForm" property="asunto.fh_recepcionDDMMYYYY" onblur="validafecha(this,'false');" styleId="fh_recepcion" styleClass="ui-datepicker-trigger calendarioBefore element-search" readonly="false" /></td>
                                            </logic:equal>
                                        </tr>
                                    </tbody>
                                    <logic:equal name="readonlyNoSintesis" value="false">
                                        <thead>
                                            <tr class="ui-widget-header texto-igual">
                                                <th colspan="3" nowrap="nowrap">&nbsp;
                                                    <span title="Agregar Tema" class="ui-icon-catalog icon-tema"><ins>Registrar Tema</ins></span>
                                                    <span title="Agregar Subtema" class="ui-icon-catalog icon-subtema"><ins>Registrar Subtema</ins></span>
                                                </th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td colspan="3"><hr></td>
                                            </tr>
                                        </tbody>
                                    </logic:equal>
                                    <thead>
                                        <tr class="ui-widget-header texto-igual">
                                            <th colspan="2">Tema:</th>
                                            <th>Subtema:</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td colspan="2">
                                                <logic:notEmpty name="capturaForm" property="asunto.tema">
                                                    <html:select name="capturaForm" property="asunto.tema.id_tema" styleId="select-temas" styleClass="element-search ui-widget-content ">
                                                        <html:option value="" styleClass="texto-igual">Seleccione una opci&oacute;n</html:option>
                                                        <html:optionsCollection name="temas" value="id_tema" label="tema" />
                                                    </html:select>
                                                </logic:notEmpty>
                                            </td>
                                            <td id="evento-subtemas">
                                                <logic:notEmpty name="capturaForm" property="asunto.tema">
                                                    <logic:notEmpty name="capturaForm" property="subtemas" >
                                                        <html:select name="capturaForm" property="asunto.subTema.id_tema" styleId="select-subtemas" styleClass="ui-widget-content select-subtemas element-search">
                                                            <html:option value="-1">Seleccione una opci&oacute;n</html:option>
                                                            <html:optionsCollection name="capturaForm" property="subtemas" value="id_tema" label="tema" />
                                                        </html:select>
                                                    </logic:notEmpty>
                                                </logic:notEmpty>
                                            </td>
                                        </tr>
                                    </tbody>
                                    <thead>
                                        <tr class="ui-widget-header texto-igual">
                                            <th colspan="2">Evento:</th>
                                            <th>Fecha de evento:</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td colspan="2">
                                                <html:select name="capturaForm" property="asunto.evento.id_evento" styleId="select-eventos" styleClass="ui-widget-content element-search texto-igual">
                                                    <html:option value="-1">Seleccione una opci&oacute;n</html:option>
                                                    <html:optionsCollection name="eventos" value="id_evento" label="evento" />
                                                </html:select>

                                            </td>
                                            <td>
                                                  <html:text name="capturaForm" property="asunto.fh_eventoDDMMYYYY"  styleId="calendario-evento" styleClass="date-time-picker element-search" />
                                            </td>
                                        </tr>
                                    </tbody>
                                    <thead>
                                        <tr class="ui-widget-header texto-igual">
                                            <th colspan="3">Expediente:</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td colspan="3">
                                                <html:select name="capturaForm" property="asunto.expediente.id_expediente" styleId="select-expedientes" styleClass="ui-widget-content element-search texto-igual">
                                                       <html:option value="">Seleccione una opci&oacute;n</html:option>
                                                       <html:optionsCollection name="expedientes" value="id_expediente" label="expediente" />
                                               </html:select>
                                               <p align="left">Búsqueda por descripción:<br>
                                               <html:text name="capturaForm" property="expedienteAutoc" styleId="expedienteAutoc" size="120" /></p>
                                            </td>
                                        </tr>
                                    </tbody>

                                    <logic:greaterEqual name="capturaForm" property="asunto.id_asunto" value="0">
                                        <thead>
                                            <tr class="ui-widget-header texto-igual">
                                                <th colspan="2">T&iacute;tulo:</th>
                                                <th>Estatus
                                                </th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr><td colspan="2">
                                                <html:text name="capturaForm" property="asunto.asunto" maxlength="200" size="80" styleClass="upperCase autowidth element-search" styleId="asunto-Titulo"/>
                                                </td>
                                                <td><span id="span-estatus"><bean:write name="capturaForm" property="asunto.estatus_desc"/></span></td>
                                            </tr>
                                        </tbody>
                                    </logic:greaterEqual>
                                    <logic:lessThan name="capturaForm" property="asunto.id_asunto" value="0">
                                        <thead>
                                            <tr class="ui-widget-header texto-igual">
                                                <th colspan="3">T&iacute;tulo:</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td colspan="3"><html:text name="capturaForm" property="asunto.asunto" maxlength="200" size="80" styleClass="upperCase autowidth element-search" styleId="asunto-Titulo" /><span id="span-estatus"></span></td>
                                            </tr>
                                        </tbody>
                                    </logic:lessThan>



                                    <thead>
                                        <tr class="ui-widget-header texto-igual">
                                            <th colspan="3"><span class="campoRequerido"> * </span>Descripci&oacute;n / S&iacute;ntesis:</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td colspan="3">
                                                <html:textarea name="capturaForm" property="asunto.descripcion"  styleId="descripcion_sintesis" styleClass="required upperCase element-search descripcion-asunto" cols="124"/>
                                                <input type="hidden" name="remLen" value="3000"/>
                                            </td>
                                        </tr>
                                    </tbody>
                                    <logic:equal name="isCapturista" value="true">
                                        <thead>
                                            <tr class="ui-widget-header texto-igual">
                                                <th colspan="2"></th>
                                                <th>
                                                    <logic:equal name="readonlyNoSintesis" value="false">
                                                        <html:button styleId="buscar-asunto" styleClass="btn_default texto-igual" value="Buscar" property=""/>
                                                        <html:button styleId="limpiar-criterio-busqueda" styleClass="btn_default texto-igual" value="Limpiar" property=""/>
                                                    </logic:equal>
                                                </th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <logic:notEmpty name="capturaForm" property="criterioAsunto.ids_asunto" >
                                                    <td><span class="result-search">Documentos encontrados(<bean:write name="capturaForm" property="criterioAsunto.paginador.numRegistros"></bean:write>):</span></td>
                                                    <td colspan="2">
                                                        <div id="paginador"></div>
                                                        <html:hidden name="capturaForm" styleId="pagina" property="criterioAsunto.paginador.pagina"/>
                                                        <html:hidden name="capturaForm" styleId="paginas" property="criterioAsunto.paginador.numRegistros"/>
                                                    </td>
                                                </logic:notEmpty>
                                            </tr>
                                        </tbody>
                                    </logic:equal>
                                    <tbody>
                                        <tr>
                                            <td colspan="3"><br></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </td>
                    </tr>
                </table>
                <table class="captura ui-widget ui-widget-content texto-igual">
                    <thead>
                        <tr class="ui-widget-header texto-igual">
                            <th><span class="campoRequerido"> * </span> Tipo de documento:</th>
                            <th colspan="2">Prioridad:</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <html:select name="capturaForm" property="asunto.tipoAsunto.id_tipo_documento" styleClass="tree ui-widget-content required" styleId="asunto-tipo-documento">
                                    <html:option value="">Seleccione una opci&oacute;n</html:option>
                                    <html:optionsCollection name="tipos_documento" value="id_tipo_documento" label="tipo_documento" />
                                </html:select>

                            </td>
                            <td>
                                <html:select name="capturaForm" property="asunto.asunto_detalle.prioridad.id_prioridad" styleId="prioridad" styleClass="">
                                    <html:option value="">Seleccione una opci&oacute;n</html:option>
                                    <html:optionsCollection name="prioridades" value="id_prioridad" label="prioridad_dias" />
                                </html:select>
                            </td>
                            <td>
                                <div id="fh_limite_div_td" style="display: none;"><html:text name="capturaForm" property="asunto.asunto_detalle.fh_limiteDDMMYYYY" styleId="fh_limite" readonly="true" styleClass="calendarioAfter" /></div>
                            </td>
                        </tr>
                    </tbody>
                    <!--<thead>

                        <tr class="ui-widget-header texto-igual">
                            <th colspan="3">&#191;Desea archivar la captura?&nbsp;:</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td colspan="3">
                       		SI&nbsp;<html:radio styleId="asunto-archivado-si" name="capturaForm" property="asunto.archivado" value ="10" />&nbsp;&nbsp;&nbsp;
                      		NO&nbsp;<html:radio styleId="asunto-archivado-no" name="capturaForm" property="asunto.archivado" value ="0" />&nbsp;&nbsp;&nbsp;
                            </td>
                        </tr>
                    </tbody>-->
                    <tbody>
                        <tr>
                            <td colspan="3"><hr></td>
                        </tr>
                    </tbody>
                    <thead>
                        <tr class="ui-widget-header texto-igual">
                            <th><span class="campoRequerido"> * </span>Remitente del documento:</th>
                            <th colspan="2" nowrap="nowrap">
                                <span class="campoRequerido">*</span>&Aacute;rea / Persona:
                                <logic:equal name="isCapturaExternos" value="true">
                                    <logic:equal name="readonlyNoSintesis" value="false">
                                        <span title="Agregar &Aacute;rea" class="ui-icon-catalog icon-area">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Agregar &Aacute;rea</span>
                                        <span title="Agregar empleado" class="ui-icon-catalog icon-empleado">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Agregar Personal</span>
                                    </logic:equal>
                               </logic:equal>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <logic:equal name="usuario_sesion" property="area.listado_area" value="0">
                                <td> <p align="right">
                                    Interno:<html:radio name="capturaForm" property="asunto.asunto_detalle.empleado_remi.area.tipo"
                                                styleClass="radio-arbol-interno" title="arbolA" alt="modal-rama" value ="0" styleId="remi-area-tipo-a" />
                                    <br>Externo:<html:radio name="capturaForm" property="asunto.asunto_detalle.empleado_remi.area.tipo"
                                                styleClass="radio-arbol-externo" title="arbolA" alt="modal-arbol-externo"  value ="1" styleId="remi-area-tipo-b" />                                    
                                </p></td>
                                <td colspan="2">
                                    <div id="arbolA" title="modal-rama">
                                        <logic:equal name="readonlyNoSintesis" value="true">
                                            <html:text name="capturaForm" property="asunto.asunto_detalle.empleado_remi.area.area"
                                                       styleClass="" styleId="asunto-remi-area" disabled="true"/>
                                        </logic:equal>
                                        <logic:equal name="readonlyNoSintesis" value="false">
                                            <html:text name="capturaForm" property="asunto.asunto_detalle.empleado_remi.area.area"
                                                       styleClass="tree icon-tree-trigger" styleId="asunto-remi-area"/>
                                        </logic:equal>
                                        <html:hidden name="capturaForm" property="asunto.asunto_detalle.empleado_remi.area.id_area"
                                                     styleClass="id" />
                                        <html:select name="capturaForm" property="asunto.asunto_detalle.empleado_remi.id_empleado"
                                                     styleClass="tree ui-widget-content" styleId="idEmpleadoRemiSelect">
                                            <html:optionsCollection name="capturaForm" property="empleadosRemitente"
                                                                    value="id_empleado" label="nombre_completo"/>
                                        </html:select>
                                        <em styleId="recarga1" title="recargar" class="ui-icon-catalog icon-reload"></em>
                                    </div>
                                </td>
                            </logic:equal>
                            <logic:equal name="usuario_sesion" property="area.listado_area" value="1">
                                <td><p align="right">
                                    Interno:<html:radio name="capturaForm" property="asunto.asunto_detalle.empleado_remi.area.tipo"
                                                styleClass="radio-arbol-interno radio-default-selected" title="arbolA" alt="modal-rama" value ="0" styleId="scg-remitente-listado"/>
                                    <br>Externo:<html:radio name="capturaForm" property="asunto.asunto_detalle.empleado_remi.area.tipo"
                                                styleClass="radio-arbol-externo" title="arbolA" alt="modal-arbol-externo"  value ="1" styleId="scg-remitente-externo-listado" />
                                    <br>SE<html:radio name="capturaForm" styleId="scg-remitente-interno-se" property="asunto.asunto_detalle.empleado_remi.area.tipo"
                                                styleClass="oficina-se-interno" title="arbolA" alt="modal" value ="0" />
                                    <br>Sin área:<html:radio name="capturaForm" property="asunto.asunto_detalle.empleado_remi.area.tipo"
                                                styleClass="radio-arbol-externo" title="arbolA" alt="modal-arbol-noarea"  value ="1" styleId="scg-remitente-noarea" />
                                </p></td>
                                <td colspan="2">
                                    <div class="ui-remitente-listado-area">
                                        <html:select name="capturaForm" property="asunto.asunto_detalle.empleado_remi.area.id_area" styleClass="required list-form-remitente scg-remitente-list-areas" styleId="remi-area-id">
                                            <html:option value="-1">Seleccione una opci&oacute;n:</html:option>
                                            <html:optionsCollection name="capturaForm" property="areasDependientes"  value="id_area" label="area"/>
                                        </html:select>
                                        <html:select name="capturaForm" property="asunto.asunto_detalle.empleado_remi.area.id_area" styleClass="hidden" styleId="remi-area-id2">
                                            <html:option value="-1">Seleccione una opci&oacute;n:</html:option>
                                            <html:optionsCollection name="capturaForm" property="areasDependientes"  value="id_area" label="area"/>
                                        </html:select>
                                        <html:select name="capturaForm" property="asunto.asunto_detalle.empleado_remi.id_empleado"
                                                     styleClass="scg-listado-remitente list-form-remitente" styleId="idEmpleadoRemitente">
                                            <html:optionsCollection name="capturaForm" property="empleadosRemitente"  value="id_empleado" label="nombre_completo"/>
                                        </html:select>                                        
                                    </div>
                                    <!--Si remitente es externo o desea ver el arbol interno completo-->
                                    <div id="arbol-remitente" title="modal">
                                        <html:text name="capturaForm" property="asunto.asunto_detalle.empleado_remi.area.area"
                                                   styleClass="tree icon-tree-trigger" />
                                        <html:hidden name="capturaForm" property="asunto.asunto_detalle.empleado_remi.area.id_area"
                                                     styleClass="id" />
                                        <html:select name="capturaForm" property="asunto.asunto_detalle.empleado_remi.id_empleado"
                                                     styleClass="tree ui-widget-content" styleId="idEmpleadoRemiSelect">
                                            <html:optionsCollection name="capturaForm" property="empleadosRemitente"
                                                                    value="id_empleado" label="nombre_completo"/>
                                        </html:select>
                                        <em title="recargar" class="ui-icon-catalog icon-reload"></em>
                                    </div>                                    
                                </td>
                            </logic:equal>
                        </tr>
                        <tr class="ui-widget-header texto-igual">
                            <th></th>
                            <th colspan="2">Puesto:</th>
                            </tr>
                            <tr>
                            <td></td>
                            <td colspan="3">
                                    <div id="puesto_remi_div">
                                                <%--<html:select name="capturaForm" property="asunto.asunto_detalle.empleado_remi.id_empleado"
                                                            styleId="puesto_remi" disabled="true">
                                                     <html:optionsCollection name="capturaForm" property="empleadosRemitente"
                                                                            value="id_empleado" label="puesto"/>
                                                </html:select>--%>
                                                <html:text name="capturaForm" property="puestos_remi" styleId="puesto_remi_t" disabled="true" size="95">
                                                </html:text>
                                            </div>
                             </td></tr>

                    </tbody>
                    <thead>
                        <tr class="ui-widget-header texto-igual">
                            <th colspan="3" class="dests-width"><span class="campoRequerido"> * </span>Destinatario(s) del documento:</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr><td><p align="right">
                                <logic:equal name="usuario_sesion" property="area.listado_area" value="0">
                                    Interno: <html:radio name="capturaForm" property="asunto.asunto_detalle.empleado_dest.area.tipo"
                                                styleClass="radio-arbol-interno radio-default-selected" title="arbolD" alt="modal" value ="0" styleId="dest-interno-a" />
                                    <br>Externo: <html:radio name="capturaForm" property="asunto.asunto_detalle.empleado_dest.area.tipo"
                                                styleClass="radio-arbol-externo" title="arbolD" alt="modal-arbol-externo" value ="1" styleId="dest-externo-a" />
                                </logic:equal>
                                <logic:equal name="usuario_sesion" property="area.listado_area" value="1">
                                    Interno: <html:radio name="capturaForm" styleId="scg-interno-listado" property="asunto.asunto_detalle.empleado_dest.area.tipo"
                                                styleClass="oficina-se-interno-list radio-default-selected" title="arbolE" alt="modal"    value ="0" />
                                    <br>Externo: <html:radio name="capturaForm" styleId="scg-externo-listado" property="asunto.asunto_detalle.empleado_dest.area.tipo"
                                                styleClass="oficina-se-externo" title="arbolE" alt="modal-arbol-externo" value ="1" />
                                    <br>SE: <html:radio name="capturaForm" styleId="scg-interno-se" property="asunto.asunto_detalle.empleado_dest.area.tipo"
                                                styleClass="oficina-se-interno" title="arbolE" alt="modal" value ="0" />
                                </logic:equal>
                                </p>
                            </td>
                            <td colspan="2" class="cpp-width">
                                <logic:equal name="usuario_sesion" property="area.listado_area" value="0">
                                    <div id="arbolD">
                                        <logic:equal name="readonlyNoSintesis" value="true">
                                            <html:text name="capturaForm" property="asunto.asunto_detalle.empleado_dest.area.area"
                                                       styleClass="" styleId="dest-empleado-area" disabled="true" />
                                        </logic:equal>
                                        <logic:equal name="readonlyNoSintesis" value="false">
                                            <html:text name="capturaForm" property="asunto.asunto_detalle.empleado_dest.area.area"
                                                   styleClass="tree icon-tree-trigger" styleId="dest-empleado-area" />
                                        </logic:equal>
                                        <html:hidden name="capturaForm" property="asunto.asunto_detalle.empleado_dest.area.id_area"
                                                     styleClass="id" />
                                        <html:select name="capturaForm" property="asunto.asunto_detalle.empleado_dest.id_empleado"
                                                     styleClass="tree ui-widget-content" styleId="idEmpleadoDest">
                                            <html:optionsCollection name="capturaForm" property="empleadosDestinatario"
                                                                    value="id_empleado" label="nombre_completo"/>
                                       </html:select>
                                       <em title="recargar" class="ui-icon-catalog icon-reload"></em>
                                       <span id="agregarDest" class="btn_default texto-igual" title="Agregar Destinatario">Agregar</span>                                       
                                        
                                    </div>
                                </logic:equal>



                                <logic:equal name="usuario_sesion" property="area.listado_area" value="1">
                                    <!-- Solo si el destinatario es interno -->
                                    <div class="ui-listado-area">
                                        <logic:equal name="readonlyNoSintesis" value="true">
                                            <html:select name="capturaForm" property="asunto.asunto_detalle.empleado_dest.area.id_area" styleClass="required" styleId="dest-empleado-area-b" disabled="true">
                                                <html:option value="-1">Seleccione una opci&oacute;n:</html:option>
                                                <html:optionsCollection name="capturaForm" property="areasDependientes"  value="id_area" label="area"/>
                                            </html:select>
                                            <html:select name="capturaForm" property="asunto.asunto_detalle.empleado_dest.id_empleado"
                                                     styleClass="scg-listado-destinatario" styleId="idEmpleadoDest" disabled="true">
                                                <html:optionsCollection name="capturaForm" property="empleadosDestinatario"  value="id_empleado" label="nombre_completo"/>
                                            </html:select>                                            
                                        </logic:equal>
                                        <logic:equal name="readonlyNoSintesis" value="false">
                                            <html:select name="capturaForm" property="asunto.asunto_detalle.empleado_dest.area.id_area" styleClass="required scg-list-areas" styleId="dest-empleado-area-b">
                                                <html:option value="-1">Seleccione una opci&oacute;n:</html:option>
                                                <html:optionsCollection name="capturaForm" property="areasDependientes"  value="id_area" label="area"/>
                                            </html:select>
                                            <html:select name="capturaForm" property="asunto.asunto_detalle.empleado_dest.id_empleado"
                                                     styleClass="scg-listado-destinatario" styleId="idEmpleadoDest">
                                                <html:optionsCollection name="capturaForm" property="empleadosDestinatario"  value="id_empleado" label="nombre_completo"/>
                                            </html:select>                                            
                                        </logic:equal>
                                        <input id="scg-add-destinatario" class="btn_default texto-igual" type="button" value="Agregar" title="Agregar destinatario" />
                                    </div>
                                    <!--Si el destinatario es externo o desea ver el arbol completo-->
                                    <div id="arbolE" title="modal">
                                        <html:text name="capturaForm" property="asunto.asunto_detalle.empleado_dest.area.area"
                                                   styleClass="tree icon-tree-trigger" />
                                        <html:hidden name="capturaForm" property="asunto.asunto_detalle.empleado_dest.area.id_area"
                                                     styleClass="id" />
                                        <html:select name="capturaForm" property="asunto.asunto_detalle.empleado_dest.id_empleado"
                                                     styleClass="tree ui-widget-content" styleId="idEmpleadoDest">
                                            <html:optionsCollection name="capturaForm" property="empleadosDestinatario"
                                                                    value="id_empleado" label="nombre_completo"/>
                                        </html:select>                                        
                                        <em title="recargar" class="ui-icon-catalog icon-reload"></em>
                                        <input type="button" value="Agregar" title="Agregar destinatarios" id="scg-add-destinatario-os" class="btn_default texto-igual" />
                                    </div>
                                </logic:equal>
                          <tr class="ui-widget-header texto-igual">
                            <th></th>
                            <th colspan="2">Puesto:</th>
                            </tr>
                            <tr>
                            <td></td>
                            <td colspan="3">
                                  <div id="puesto_dest_div">
                                  <%--<html:select name="capturaForm" property="asunto.asunto_detalle.empleado_dest.id_empleado"
                                                        styleId="puesto_dest" disabled="true">
                                                <html:optionsCollection name="capturaForm" property="empleadosDestinatario"
                                                                        value="id_empleado" label="puesto"/>
                                           </html:select>--%>
                                  <html:text name="capturaForm" property="puestos_dest" styleId="puesto_dest_t" disabled="true" size="95">
                                            </html:text>
                                  </div>
                            </td>
                          </tr>
                    </tbody>
                    <thead>
                        <tr>
                            <th colspan="3">
                    <table>
                        <thead>
                            <tr class="ui-widget-header">
                                <th>Destinatario:</th>
                                <th>Instruccion:</th>
                                <th>
                                    Comentario personalizado:
                                    <input type="checkbox" name="comentario-personalizado" id="is-comentario-personal" checked />
                                </th>
                                <th>¿Eliminar?:</th>
                            </tr>
                        </thead>
                        <tbody id="tablaDest">
                            <logic:iterate id="empl_dest" name="capturaForm" property="asunto.asunto_detalle.empleadosDest" type="mx.gob.economia.scg.model.Empleado">
                                <tr id='eliminarDest<bean:write name="empl_dest" property="id_empleado"/>' class="dest-tr">
                                    <td>
                                        <bean:write name="empl_dest" property="nombre_completo" />
                                    </td>
                                    <td class='instruccion'>
                                       <html:select name="empl_dest" styleClass="select-instrucciones ui-widget-content" property="instruccion.id_instruccion" styleId="instruccion-id_instruccion">
                                            <html:option value="-1">Seleccione una instrucci&oacute;n</html:option>
                                            <html:optionsCollection name="capturaForm" property="instrucciones" value="id_instruccion" label="instruccion" />
                                        </html:select>
                                    </td>
                                    <td>
                                        <html:textarea styleClass="comentario-personalizado upperCase" name="empl_dest" property="comentario" styleId="comentario-personal"/>
                                    </td>
                                    <td>
                                        <logic:equal name="readonlyNoSintesis" value="false">
                                            <a class='eliminarDest' id="eliminarDestinatario"  name='<bean:write name="empl_dest" property="id_empleado"/>'> <img alt="" height='25' width='25' src='../../imagenes/group_remove.png'></a>
                                        </logic:equal>
                                    </td>
                                </tr>
                            </logic:iterate>
                        </tbody>
                    </table>
                    </th>
                    </tr>
                    </thead>
                    <tbody class="generic-coment">
                        <tr><td colspan="3"><hr></td></tr>
                    </tbody>

                    <thead class="generic-coment">
                        <tr class="ui-widget-header texto-igual">
                            <th colspan="3">Comentarios:</th>
                        </tr>
                    </thead>
                    <tbody class="generic-coment">
                        <tr>
                            <td colspan="3">
                                <html:textarea name="capturaForm" property="asunto.asunto_detalle.comentario" styleClass="upperCase comentario-general" cols="124" styleId="asunto-comentarios" />
                                <input type="hidden" name="remLen2" value="3000"/>
                            </td>
                        </tr>
                    </tbody>


                    <tbody>
                        <tr>
                            <td colspan="3"><hr></td>
                        </tr>
                    </tbody>


                    <!---->
                    <thead>
                        <tr class="ui-widget-header texto-igual">
                            <th colspan="3" class="cpp-width">Con copia para:</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td colspan="3" class="cpp-width">
                                <div id="arbolC">
                                    <logic:equal name="readonlyNoSintesis" value="true">
                                        <input id="nom-empleado_ccp" type="text" name=""  value="" class="" disabled="true" />
                                    </logic:equal>
                                    <logic:equal name="readonlyNoSintesis" value="false">
                                        <input id="nom-empleado_ccp" type="text" name=""  value="" class="tree icon-tree-trigger" />
                                    </logic:equal>
                                    <html:select name="capturaForm" property="asunto.empleado_ccp.id_empleado" styleId="idEmpleadoCcp" styleClass="tree ui-widget-content">
                                        <html:optionsCollection name="capturaForm" property="empleadosConCopiaPara" value="id_empleado"  label="nombre_completo" />
                                    </html:select>
                                    <input type="button" value="Agregar Ccp"  id="agregarCcp" class="btn_default ui-estate-error texto-igual" title="Agregar Ccp"/>
                                    <em title="recargar" class="ui-icon-catalog icon-reload"></em>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                    <thead>
                        <tr class="ui-widget-header texto-igual">
                            <th colspan="2">Copiado a:</th>
                            <th>Eliminar:</th>
                        </tr>
                    </thead>
                    <tbody id="tablaCcp">
                        <tr>
                            <td colspan="2"></td>
                            <td></td>
                        </tr>
                        <logic:iterate id="empl" name="capturaForm"
                                       property="asunto.empleadosCcp"
                                       type="mx.gob.economia.scg.model.Empleado">
                            <tr
                                id='eliminarCcp<bean:write name="empl" property="id_empleado"/>'>
                                <td colspan="2"><bean:write name="empl" property="nombre_completo" /></td>
                                <td><a class='eliminarCcp'
                                       name='<bean:write name="empl" property="id_empleado"/>'> <img alt=""
                                                                                                  height='25' width='25' src='../../imagenes/group_remove.png'>
                                    </a></td>
                            </tr>
                        </logic:iterate>
                    </tbody>

                    <tbody>
                        <tr>
                            <td colspan="3"><hr></td>
                        </tr>
                    </tbody>

                    <thead>
                        <tr class="ui-widget-header texto-igual">
                            <th colspan="3">Documentos adjuntos:</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td colspan="2">
                                <span>Nombre del Documento:</span> <html:text name="capturaForm"
                                           styleId="documento"
                                           property="asunto.asunto_detalle.documento.documento"
                                           maxlength="150" value="" size="32"
                                           styleClass="input_docsAdj upperCase only-chars-file-name" />

                            </td>
                            <td>
                                <html:file name="capturaForm" property="asunto.asunto_detalle.documento.adjunto" styleId="adjunto"/>
                            </td>
                        </tr>
                    </tbody>

                    <thead>
                        <tr class="ui-widget-header texto-igual">
                            <th colspan="2">Tipo de documento anexo:</th>
                            <th>Adjuntar:</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td colspan="2">
                                <html:select name="capturaForm" property="asunto.asunto_detalle.documento.tipo_documento.id_tipo_documento"
                                             styleClass="tree ui-widget-content" styleId="id_tipo_documento">
                                    <html:option value="-1">Seleccione una opci&oacute;n</html:option>
                                    <html:optionsCollection name="tipos_documento"
                                                            value="id_tipo_documento" label="tipo_documento" />
                                </html:select>

                            </td>
                            <td>
                                <input type="button" id="adjuntarDoc" class="btn_default texto-igual" value="Adjuntar Documento" title="Adjuntar Documento" />

                            </td>
                        </tr>
                    </tbody>
                    <thead>
                        <tr class="ui-widget-header texto-igual">
                            <th>Documento:</th>
                            <th>Tipo de documento:</th>
                            <th>Ver/Eliminar:</th>
                        </tr>
                    </thead>
                    <tbody id="tablaDoc">
                        <logic:iterate id="doc" name="capturaForm" property="asunto.asunto_detalle.documentos"
                                       type="mx.gob.economia.scg.model.Documento">
                            <tr id='eliminarDoc<bean:write name="doc" property="idx"/>'>
                                <td><bean:write name="doc" property="documento" /></td>
                                <td><bean:write name="doc" property="tipo_documento.tipo_documento" /></td>
                                <td>
                                    <a class='descargarDoc' name='<bean:write name="doc" property="idx"/>'>
                                        <img alt="" height='25' width='25' src='../../imagenes/file.png'>
                                    </a>

                                    <a class='eliminarDoc' name='<bean:write name="doc" property="idx"/>'>
                                        <img alt="" height='25' width='25' src='../../imagenes/file_remove.png'>
                                    </a>
                                </td>
                            </tr>
                        </logic:iterate>
                    </tbody>
                    <tr>
                    <thead>
                        <tr class="ui-widget-header ">
                            <th colspan="3">Datos del volante</th>
                        </tr>
                    </thead>
                    <thead>
                        <tr class="ui-widget-header ">
                            <%--<th colspan="2">Tipo de Seguimiento:</th>--%>
                            <th colspan="3">Firmante:</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <%-- <td colspan="2">
                                <html:select  name="capturaForm" property="asunto.asunto_detalle.tipoSeguimiento.idTipoSeguimiento" styleId="asunto-idTipoSeguimiento" >
                                    <html:optionsCollection name="capturaForm"
                                                            property="tiposSeguimiento" value="idTipoSeguimiento"
                                                            label="tipoSeguimiento" />
                                </html:select>
                            </td>--%>
                            <td colspan="3">
                                <html:select  name="capturaForm" styleId="list-firmantes" property="asunto.asunto_detalle.empleadoFirmanteVolante.id_empleado" >
                                    <%-- <html:optionsCollection name="capturaForm"
                                                            property="firmantesVolante" value="id_empleado"
                                                            label="nombre_completo" /> --%>
                                    <html:optionsCollection name="capturaForm"
                                                            property="firmantesVolanteRol" value="id_empleado"
                                                            label="nombre_completo" />
                                </html:select>
                            </td>
                        </tr>
                    </tbody>
                    <tbody>
                        <tr><td colspan="3"><hr></td></tr>
                    </tbody>

                    <thead>
                        <tr class="ui-widget-header texto-igual">
                            <th colspan="3">Hist&oacute;rico actualizaciones:</th>
                        </tr>
                    </thead>
                    <tbody>
                        <logic:iterate id="historico" name="capturaForm"
                                       property="asunto.actualizacionesCaptura"
                                       type="mx.gob.economia.scg.model.AsuntoCapturaActualizacion">
                            <tr>
                                <td><bean:write name="historico" property="fh_actualizacion" format="dd/MM/yyyy HH:mm:ss"/></td>
                                <td colspan="2"><bean:write name="historico" property="empleado_act.nombre_completo" /></td>
                            </tr>
                        </logic:iterate>
                    </tbody>
                    <tbody>
                        <tr>
                            <td colspan="3">
                                <logic:empty name="capturaForm" property="asunto.id_asunto">
                                    <input type="button" id="guardar" class="btn_default texto-igual"
                                           value="Vista previa" title="Vista previa" />
                                </logic:empty>
                                <logic:equal name="capturaForm" property="asunto.id_asunto" value="0">
                                    <input type="button" id="guardar" class="btn_default texto-igual"
                                           value="Vista previa" title="Vista previa" />
                                </logic:equal>
                                <logic:lessThan name="capturaForm" property="asunto.id_asunto"  value="0">
                                    <input type="button" id="guardar" class="btn_default texto-igual"
                                           value="Vista previa" title="Vista previa" />
                                </logic:lessThan>


                                <logic:notEmpty name="capturaForm" property="asunto.id_asunto">
                                    <logic:greaterEqual name="capturaForm" property="asunto.id_asunto" value="0">
                                        <logic:present name="showModificarBtn">
                                            <input type="button" id="modificar" class="btn_default" value="Aceptar" title="Aceptar" />
                                        </logic:present>
                                        <logic:present name="showRevisarBtn">
                                            <input type="button" id="modificar" class="btn_default" value="Aceptar" title="Aceptar" />
                                        </logic:present>
                                    </logic:greaterEqual>
                                </logic:notEmpty>
                                <input type="button" id="regresar" class="btn_default regresar texto-igual"
                                       value="Regresar" title="Regresar"/>
                                <logic:equal name="isCapturista" value="true">
                                    <label id="asunto-nuevo">
                                        <a  class="btn_default texto-igual"  href='<html:rewrite page="/captura.do?method=inicio" />'>Limpiar</a>
                                    </label>
                                </logic:equal>
                            </td>
                        </tr>
                    </tbody>
                 </table>
            </html:form>

        </div>
    </div>
</div>

<!--Catalogo de areas y empleados-->
<%@ include file="/jsp/captura/catalogo.jsp"%>


<!--despliegue de areas-->
<div class="modal" title="&Aacute;reas internas">
    <div class="treeviewContent">
        <ul id="tree">
            ${sessionScope.arbol_raiz}
        </ul>
    </div>
</div>
<div class="modal-rama" title="Subareas internas">
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

<logic:present name="errorGuardarEmpl">
    <SCRIPT type="text/javascript"  LANGUAGE="JavaScript">
        $( function() {
            jAlert("error al registrar empleado", "Atenci\u00f3n");
        });
    </SCRIPT>
</logic:present>

<logic:equal name="readonlyNoSintesis" value="true">
    <SCRIPT type="text/javascript"  LANGUAGE="JavaScript">
        $( function() {
            //alert("inicia");
            document.getElementById("scg-captura-referencia").setAttribute("disabled", "true");
            document.getElementById("confidencialSi").setAttribute("disabled", "true");
            document.getElementById("confidencialNo").setAttribute("disabled", "true");
            document.getElementById("asunto-captura-documento").setAttribute("disabled", "true");
            document.getElementById("scg-captura-antecedente").setAttribute("disabled", "true");
            document.getElementById("fh_registro").setAttribute("disabled", "true");
            document.getElementById("fh_oficio").setAttribute("disabled", "true");
            document.getElementById("fh_recepcion").setAttribute("disabled", "true");
            document.getElementById("select-temas").setAttribute("disabled", "true");
            if (!document.getElementById("select-subtemas")==null)
                document.getElementById("select-subtemas").setAttribute("disabled", "true");
            document.getElementById("calendario-evento").setAttribute("disabled", "true");
            document.getElementById("select-expedientes").setAttribute("disabled", "true");
            document.getElementById("select-eventos").setAttribute("disabled", "true");
            document.getElementById("asunto-Titulo").setAttribute("disabled", "true");
            document.getElementById("asunto-tipo-documento").setAttribute("disabled", "true");
            document.getElementById("prioridad").setAttribute("disabled", "true");
            if (document.getElementById("remi-area-tipo-a")!=null){
                document.getElementById("remi-area-tipo-a").setAttribute("disabled", "true");}
            if (document.getElementById("remi-area-tipo-b")!=null)
                document.getElementById("remi-area-tipo-b").setAttribute("disabled", "true");
            if (document.getElementById("remi-area-id")!=null)
                document.getElementById("remi-area-id").setAttribute("disabled", "true");
            if (document.getElementById("idEmpleadoRemitente")!=null)
                document.getElementById("idEmpleadoRemitente").setAttribute("disabled", "true");
            if (document.getElementById("scg-remitente-listado")!=null){
                document.getElementById("scg-remitente-listado").setAttribute("disabled", "true");}
            if (document.getElementById("scg-remitente-externo-listado")!=null)
                document.getElementById("scg-remitente-externo-listado").setAttribute("disabled", "true");
            if (document.getElementById("scg-remitente-interno-se")!=null)
                document.getElementById("scg-remitente-interno-se").setAttribute("disabled", "true");
            document.getElementById("idEmpleadoRemiSelect").setAttribute("disabled", "true");
            if (document.getElementById("dest-interno-a")!=null)
                document.getElementById("dest-interno-a").setAttribute("disabled", "true");
            if (document.getElementById("dest-externo-a")!=null)
                document.getElementById("dest-externo-a").setAttribute("disabled", "true");
            if (document.getElementById("scg-interno-listado")!=null)
                document.getElementById("scg-interno-listado").setAttribute("disabled", "true");
            if (document.getElementById("scg-externo-listado")!=null)
                document.getElementById("scg-externo-listado").setAttribute("disabled", "true");
            if (document.getElementById("scg-interno-se")!=null)
                document.getElementById("scg-interno-se").setAttribute("disabled", "true");
            if (document.getElementById("dest-empleado-area")!=null)
                document.getElementById("dest-empleado-area").setAttribute("disabled", "true");
            //document.getElementById("instruccion-id_instruccion").setAttribute("disabled", "true");
            if (document.getElementById("scg-add-destinatario")!=null)
                document.getElementById("scg-add-destinatario").setAttribute("disabled", "true");
            if (document.getElementById("agregarDest")!=null)
                document.getElementById("agregarDest").setAttribute("disabled", "true");
            document.getElementById("comentario-personal").setAttribute("disabled", "true");
            if (document.getElementById("is-comentario-personal")!=null)
                document.getElementById("is-comentario-personal").setAttribute("disabled", "true");
            document.getElementById("asunto-comentarios").setAttribute("disabled", "true");
            if (document.getElementById("idEmpleadoCcp")!=null)
            document.getElementById("idEmpleadoCcp").setAttribute("disabled", "true");
            if (document.getElementById("asunto-empleadoCcp-area")!=null)
                document.getElementById("asunto-empleadoCcp-area").setAttribute("disabled", "true");
            if (document.getElementById("agregarCcp")!=null)
                document.getElementById("agregarCcp").setAttribute("disabled", "true");
            if (document.getElementById("scg-add-ccp")!=null)
                document.getElementById("scg-add-ccp").setAttribute("disabled", "true");
            if (document.getElementById("asunto-empleadoCcp-area")!=null)
                document.getElementById("asunto-empleadoCcp-area").setAttribute("disabled", "true");
            if (document.getElementById("asunto-empleadoCcp-nombre")!=null)
                document.getElementById("asunto-empleadoCcp-nombre").setAttribute("disabled", "true");
            if (document.getElementById("idEmpleadoDest")!=null)
                document.getElementById("idEmpleadoDest").setAttribute("disabled", "true");
            document.getElementById("documento").setAttribute("disabled", "true");
            document.getElementById("adjunto").setAttribute("disabled", "true");
            document.getElementById("id_tipo_documento").setAttribute("disabled", "true");
            document.getElementById("adjuntarDoc").setAttribute("disabled", "true");
            //document.getElementById("asunto-idTipoSeguimiento").setAttribute("disabled", "true");
            //document.getElementById("list-firmantes").setAttribute("disabled", "true");
            //alert("termina");
 });
    </SCRIPT>
</logic:equal>

<SCRIPT type="text/javascript"  LANGUAGE="JavaScript">
    if (document.getElementById("fh_oficio").value == ""){
        document.getElementById("fh_oficio").value = document.getElementById("fh_registro").value;
    }
    $('#remi-area-id').find('[value=2183]').remove();
    $('#dest-empleado-area-b').find('[value=2183]').remove();
    $('#asunto-empleadoCcp-area').find('[value=2183]').remove();    
    $(function() {
    var availableTags = [      
      <logic:iterate id="exps" name="capturaForm" property="expedientes" type="mx.gob.economia.scg.model.Expediente">
                  "<bean:write name="exps" property="expediente_clave"/>",
      </logic:iterate>
    ];
    var cveExpedientes = [
      <logic:iterate id="exps_cve" name="capturaForm" property="expedientes" type="mx.gob.economia.scg.model.Expediente">
                  "<bean:write name="exps_cve" property="cve_expediente"/>",
      </logic:iterate>
    ];
    $( "#expedienteAutoc" ).autocomplete(availableTags).result(function(event, data, formatted) { // result is a separate function
    changeSelectFromAutocomplete(data,availableTags,cveExpedientes);
});
  });
  $(document).ready(function () { $("input").attr("autocomplete", "on"); });
</SCRIPT>
