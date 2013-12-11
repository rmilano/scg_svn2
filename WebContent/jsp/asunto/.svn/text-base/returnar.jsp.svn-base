<%@ include file="/jsp/arbol.jsp"%>
<%
  String path = request.getContextPath();

  String getProtocol=request.getScheme();
  String getDomain=request.getServerName();
  String getPort=Integer.toString(request.getServerPort());

  String getPath = getProtocol+"://"+getDomain+":"+getPort+path+"/";

  String getURI=request.getRequestURI();
%>
<link href='<html:rewrite page="/jsp/asunto/css/asuntos.css"/>' rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<html:rewrite page="/jsp/js/ajaxfileupload.js"/>"></script>
<script type="text/javascript" src="<html:rewrite page="/jsp/js/misc-functions.js"/>"></script>
<script type="text/javascript" src="<html:rewrite page="/jsp/asunto/js/turnar.js"/>"></script>
<script type="text/javascript" src="<html:rewrite page="/jsp/js/textLimit.min.js"/>"></script>
<script type="text/javascript" src="<html:rewrite page="/jsp/js/scg.list.empleado.area.js"/>"></script>

<html:form action="/asunto.do" styleId="asuntoForm" enctype="multipart/form-data">
    <input type="hidden" id="id_empleado_session" name="id_empleado_session"  value='<bean:write name="usuario_sesion" property="id_empleado" />' />
    <html:hidden styleId="idmetodo" property="method" value="" />
    <html:hidden name="asuntoForm" styleId="empleado-captura-id-area" property="empleadoCaptura.id_area" />    
    <tiles:insert page="/jsp/asunto/asunto.jsp" />

    <div class="content border">
        <div class="inner corner">
            <div class="contentform turnar-asunto">


                <table class="captura ui-widget ui-widget-content">
                    <%--thead>
                        <tr class="ui-widget-header ">
                            <th colspan="3"><span class="campoRequerido"> * </span>Turnar a:</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td colspan="3">
                                <div id="arbolA" title="modal-rama">
                                    <html:text name="asuntoForm" property="asunto.asunto_detalle.empleado_dest.area.area"
                                               styleClass="tree icon-tree-trigger"/>
                                    <html:hidden name="asuntoForm"
                                                 property="asunto.asunto_detalle.empleado_dest.area.id_area"
                                                 styleClass="id" />
                                    <html:select  name="asuntoForm" styleId="select-dest"
                                                  property="asunto.asunto_detalle.empleado_dest.id_empleado"
                                                  styleClass="tree required">
                                        <html:optionsCollection name="asuntoForm"
                                                                property="empleadosDestinatario" value="id_empleado"
                                                                label="nombre_completo" />
                                    </html:select>
                                </div>
                            </td>

                        </tr>
                    </tbody--%>
                    <!---->
                    <thead>
                        <tr class="ui-widget-header ">
                            <th colspan="2" class="dests-width"><span class="campoRequerido"> * </span>Turnado a:</th>     
                            <th>Confidencial:<html:checkbox name="asuntoForm" property="asunto.confidencial" value="1"/></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <logic:equal name="usuario_sesion" property="area.listado_area" value="1">
                                <td>

                                    Interno <html:radio name="asuntoForm" styleId="scg-interno-listado" property="asunto.asunto_detalle.empleado_dest.area.tipo"
                                                styleClass="oficina-se-interno-list radio-default-selected" title="arbolE" alt="modal"    value ="0" />
                                    SE<html:radio name="asuntoForm" styleId="scg-interno-se" property="asunto.asunto_detalle.empleado_dest.area.tipo"
                                                styleClass="oficina-se-interno" title="arbolE" alt="modal" value ="0" />
                                </td>
                            </logic:equal>
                            <logic:equal name="usuario_sesion" property="area.listado_area" value="0">
                                <td colspan="3" class="cpp-width">

                                    <div id="arbolD" title="modal-rama"><input type="text" name=""  value=""
                                                                               class="tree icon-tree-trigger desc-area-empleado" /> <html:select name="asuntoForm"
                                                     property="asunto.asunto_detalle.empleado_dest.id_empleado" styleId="idEmpleadoDest"
                                                     styleClass="tree desc-area-empleado">
                                            <html:optionsCollection name="asuntoForm"
                                                                    property="empleadosDestinatario" value="id_empleado"
                                                                    label="nombre_completo" />
                                        </html:select>
                                        <!--                                        <span id="agregarDest" class="btn_default  ui-estate-error" title="Agregar Destinatario">Agregar</span>-->
                                        <input type="button" value="Agregar"  id="agregarDest" class="btn_default ui-estate-error texto-igual" title="Agregar destinatario"/>
                                        <em title="recargar" class="ui-icon-catalog icon-reload"></em>
                                    </div>
                                </td>
                            </logic:equal>
                            <!--                                Para las persona de la oficina del secretario-->
                            <logic:equal name="usuario_sesion" property="area.listado_area" value="1">
                                <td colspan="2" class="cpp-width">
                                    <!-- Solo si el destinatario es interno -->
                                    <div class="ui-listado-area">
                                        <html:select name="asuntoForm" property="asunto.asunto_detalle.empleado_dest.area.id_area" styleClass="required scg-list-areas" styleId="dest-empleado-area-b">
                                            <html:option value="-1">Seleccione una opci&oacute;n:</html:option>
                                            <html:optionsCollection name="asuntoForm" property="areasDependientes"  value="id_area" label="area"/>
                                        </html:select>
                                        <html:select name="asuntoForm" property="asunto.asunto_detalle.empleado_dest.id_empleado"
                                                     styleClass="scg-listado-destinatario" styleId="idEmpleadoDest">
                                            <html:optionsCollection name="asuntoForm" property="empleadosDestinatario"  value="id_empleado" label="nombre_completo"/>
                                        </html:select>
                                        <!--                                        <span id="scg-add-destinatario" class="btn_default " title="Agregar Destinatario">Agregar</span>-->
                                        <input type="button" value="Agregar"  id="scg-add-destinatario" class="btn_default " title="Agregar Destinatario"/>                                        
                                    </div>
                                    <!--Si el destinatario es externo o desea ver el arbol completo-->
                                    <div id="arbolE" title="modal">
                                        <html:text name="asuntoForm" property="asunto.asunto_detalle.empleado_dest.area.area"
                                                   styleClass="tree icon-tree-trigger desc-area-empleado" />
                                        <html:hidden name="asuntoForm" property="asunto.asunto_detalle.empleado_dest.area.id_area"
                                                     styleClass="id" />
                                        <html:select name="asuntoForm" property="asunto.asunto_detalle.empleado_dest.id_empleado"
                                                     styleClass="tree ui-widget-content desc-area-empleado" styleId="idEmpleadoDest">
                                            <html:optionsCollection name="asuntoForm" property="empleadosDestinatario"
                                                                    value="id_empleado" label="nombre_completo"/>
                                        </html:select>
                                        <em title="recargar" class="ui-icon-catalog icon-reload"></em>
                                        <!--                                        <span id="scg-add-destinatario-os" class="btn_default texto-igual" title="Agregar Destinatario">Agregar</span>-->
                                        <input type="button" value="Agregar" id="scg-add-destinatario-os" class="btn_default texto-igual" title="Agregar Destinatario" />                                        
                                    </div>
                                </td>
                            </logic:equal>
                        </tr>
                    </tbody>

                    <thead>
                        <tr>
                            <th colspan="3">
                    <table>
                        <thead>
                            <tr class="ui-widget-header">
                                <th>Turnado a:</th>
                                <th>Instrucci&oacute;n:</th>
                                <th><span class="campoRequerido"> * </span>Seguimiento:</th>
                                <th>
                                    Comentario/Instrucci&oacute;n personalizada:
                                    <input type="checkbox" name="comentario-personalizado" id="is-comentario-personal" checked />
                                </th>
                                <th>¿Eliminar?:</th>
                            </tr>
                        </thead>
                        <tbody id="tablaDest">
                            <logic:iterate id="empl_dest" name="asuntoForm" property="asunto.asunto_detalle.empleadosDest" type="mx.gob.economia.scg.model.Empleado">
                                <tr id='eliminarDest<bean:write name="empl_dest" property="id_empleado"/>' class="dest-tr">
                                    <td>
                                        <bean:write name="empl_dest" property="nombre_completo" />
                                    </td>
                                    <td>
                                        <html:select name="empl_dest" styleClass="select-instrucciones ui-widget-content" property="instruccion.id_instruccion">
                                            <html:option value="-1">Seleccione instrucci&oacute;n:</html:option>
                                            <html:optionsCollection name="asuntoForm" property="instrucciones" value="id_instruccion" label="instruccion" />
                                        </html:select>
                                    </td>
                                    <td>
                                        <html:select name="empl_dest" property="prioridad.id_prioridad"
                                             styleId="prioridad" styleClass="select-prioridades ui-widget-content required">
                                    		<html:option value="-1">Seleccione una opci&oacute;n</html:option>
                                    		<html:optionsCollection name="asuntoForm" property="prioridades" value="id_prioridad" label="prioridad_dias" />
                                		</html:select>
                                		<div id="fh_limite_div_td" style="display: none;"><html:text name="empl_dest" property="fh_limiteDDMMYYYY" styleId="fh_limite" readonly="true" styleClass="calendario" /></div>                                		
                                    </td>
                                    <td>
                                        <html:textarea styleClass="comentario-personalizado upperCase" name="empl_dest" property="comentario" />
                                    </td>
                                    <td>
                                        <a class='eliminarDest' name='<bean:write name="empl_dest" property="id_empleado"/>'> <img alt="" height='25' width='25' src='../../imagenes/group_remove.png'></a>
                                    </td>
                                </tr>
                            </logic:iterate>
                        </tbody>
                    </table>
                    </th>
                    </tr>
                    </thead>
                    <tr>
                        <td colspan="3"><hr></td>
                    </tr>
                    </tbody>

                    <!---->
                    <thead>
                        <tr class="ui-widget-header">
                            <th colspan="3">Comentario:</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td colspan="3">
                                <html:textarea name="asuntoForm" property="asunto.asunto_detalle.comentario"  disabled="true" styleClass="upperCase comentario-general"/>
                                <input type="hidden" name="remLen2" value="3000">
                            </td>
                        </tr>
                    </tbody>
                    <thead>
                        <tr class="ui-widget-header ">
                            <th colspan="3">Instrucción adicional (Agenda):</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td colspan="3">
                                <html:text name="asuntoForm" property="asunto.asunto_detalle.instruccionAdicional"  styleClass="upperCase comentario-general"
                                           size="100"/>
                                <input type="hidden" name="remLen3" value="200">
                            </td>
                        </tr>
                    </tbody>
<%--
                    <thead>
                        <tr class="ui-widget-header ">
                            <th colspan="2"><span class="campoRequerido"> * </span>Seguimiento:</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td colspan="2">
                                <html:select name="asuntoForm" property="asunto.asunto_detalle.prioridad.id_prioridad"
                                             styleId="prioridad" styleClass="required">
                                    <html:option value="">Seleccione una opci&oacute;n</html:option>
                                    <html:optionsCollection name="prioridades" value="id_prioridad" label="prioridad_dias" />
                                </html:select>
                            </td>
                            <td>
                                <!--<div id="fh_limite_div_td" style="display: none;"><html:text name="asuntoForm" property="asunto.asunto_detalle.fh_limiteDDMMYYYY" styleId="fh_limite" readonly="true" styleClass="calendarioAfter" /></div>--!>
                                <div id="fh_limite_div_td" style="display: none;"><html:text name="asuntoForm" property="asunto.asunto_detalle.fh_limiteDDMMYYYY" styleId="fh_limite" readonly="true" styleClass="calendario" /></div>
                            </td>
                        </tr>
                    </tbody>
 --%>
                    <thead>
                        <tr class="ui-widget-header ">
                            <th colspan="3">Firmante:</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td colspan="3">
                                <html:select  name="asuntoForm" property="asunto.asunto_detalle.empleadoFirmanteVolante.id_empleado"
                                              styleClass="tree" styleId="id_empleadoFirmante">
                                    <%-- <html:optionsCollection name="asuntoForm"
                                                            property="firmantesVolante" value="id_empleado"
                                                            label="nombre_completo" /> --%>
                                    <html:optionsCollection name="asuntoForm"
                                                            property="firmantesVolanteRol" value="id_empleado"
                                                            label="nombre_completo" />
                                </html:select>
                            </td>
                        </tr>
                    </tbody>

                    <tbody>
                        <tr>
                            <td colspan="3"><hr></td>
                        </tr>
                    </tbody>
                    <thead>
                        <tr class="ui-widget-header ">
                            <th colspan="3"><span class="campoRequerido">  </span> Documentos adjuntos:</th>                            
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td colspan="2">
                                <html:text name="asuntoForm"
                                           styleId="documento"
                                           property="asunto.asunto_detalle.documento.documento"
                                           maxlength="150" value="Nombre del documento" size="32"
                                           styleClass="input_docsAdj upperCase" />
                            </td>
                            <td>
                                <html:file name="asuntoForm" styleId="adjunto"
                                           property="asunto.asunto_detalle.documento.adjunto" />
                            </td>
                        </tr>
                    </tbody>
                    <thead>
                        <tr class="ui-widget-header ">
                            <th colspan="2"><span class="campoRequerido">  </span> Tipo:</th>
                            <th>Adjuntar:</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td colspan="2">
                                <html:select name="asuntoForm"
                                             property="asunto.asunto_detalle.documento.tipo_documento.id_tipo_documento"
                                             styleClass="tree" styleId="id_tipo_documento">
                                    <html:option value="-1">Seleccione un tipo de documento</html:option>
                                    <html:optionsCollection name="tipos_documento"
                                                            value="id_tipo_documento" label="tipo_documento" />
                                </html:select>
                            </td>
                            <td>
                                <input type="button" id="adjuntarDoc" class="btn_default" value="Adjuntar Documento" title="Adjuntar Documento" />
                            </td>
                        </tr>
                    </tbody>
                    <thead>
                        <tr class="ui-widget-header">
                            <th>Documento:</th>
                            <th>Tipo de documento:</th>
                            <th>Ver/Eliminar:</th>
                        </tr>
                    </thead>
                    <tbody id="tablaDoc">
                          <logic:iterate id="doc" name="asuntoForm" property="asunto.asunto_detalle.documentos"
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

                    <tbody>
                        <tr>
                            <td colspan="3"><hr></td>
                        </tr>
                    </tbody>
                    <thead>
                        <tr class="ui-widget-header ">
                            <th colspan="3" class="cpp-width"><span class="campoRequerido">  </span>Con copia para:</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td colspan="3" class="cpp-width">                                
                                <div id="arbolC">
                                    <input type="text" name="" value="" class="tree icon-tree-trigger" />
                                    <html:select name="asuntoForm"
                                                 property="asunto.asunto_detalle.empleado_ccp.id_empleado"
                                                 styleId="idEmpleadoCcp" styleClass="tree">
                                        <html:optionsCollection name="asuntoForm"
                                                                property="empleadosConCopiaPara" value="id_empleado"
                                                                label="nombre_completo" />
                                    </html:select>
<!--                                    <span id="agregarCcp" class="btn_default  ui-estate-error" title="Agregar Ccp">Agregar</span>-->
                                    <input type="button" value="Agregar" id="agregarCcp" class="btn_default  ui-estate-error" title="Agregar Ccp" />                                        
                                </div>
                            </td>
                        </tr>
                    </tbody>

                    <thead>
                        <tr class="ui-widget-header">
                            <th colspan="2">Copiado a:</th>
                            <th>Eliminar:</th>
                        </tr>
                    </thead>
                    <tbody id="tablaCcp">
                        <tr>
                            <td colspan="2"></td>
                            <td></td>
                        </tr>
                        <logic:iterate id="empl" name="asuntoForm"
                                       property="asunto.asunto_detalle.empleadosCcp"
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
                    <tbody>
                        <tr>
                            <td colspan="3"><hr></td>
                        </tr>
                    </tbody>
                    <tbody>
                        <tr>
                            <td>
                                <input type="button" id="save-returnar" class="btn_default" value="" title="Returnar" style='background:url("<%=getPath%>imagenes/rayo_r.png"); height:20px; width:25px' />
                            </td>
                            <td>
                                <input type="button" id="genera-volante" class="btn_default" value="Generar Volante" title="Generar volante" />
                            </td>
                            <td>
                                <input type="button" id="guardar_capt" class="btn_default" value="Guardar" title="Guardar" />
                                &nbsp;&nbsp;
                                <input type="button" id="regresar" class="btn_default regresar" value="Regresar" title="Regresar" />
                                &nbsp;&nbsp;
                                <span title="Mostrar bitácora" class="ui-icon-catalog btn_default icon-bitacora">&nbsp;&nbsp;Mostrar bitácora</span>
                            </td>
                            <td>
                            </td>
                        </tr>
                    </tbody>

                </table>

            </div>
        </div>
    </div>
</html:form>
<div class="modal-rama">
    <div class="treeviewContent">
        <ul class="tree-branch" >
            ${arbol_rama}
        </ul>
    </div>
</div>
<div class="modal">
    <div class="treeviewContent">
        <ul id="tree">
            ${arbol_raiz}
        </ul>
    </div>
</div>
<logic:iterate id="empleado_dest" name="asuntoForm" property="empleadosDestinatario" type="mx.gob.economia.scg.model.Empleado">
    <script type="text/javascript">       
        actualizaDest(<bean:write name="empleado_dest" property="id_empleado" />,
                     '<bean:write name="empleado_dest" property="nombre_completo" />',
                      <bean:write name="usuario_sesion" property="id_area" />,
                      <bean:write name="asuntoForm" property="asunto.asunto_detalle.id_instruccion" />,
                      <bean:write name="empleado_dest" property="prioridad.id_prioridad" />,
                      '<bean:write name="empleado_dest" property="comentarioSaltos" />');
        /*document.getElementById('instruccioneliminarDest').value = ;
        document.getElementById('prioridadeseliminarDest;*/
    </script>
</logic:iterate>
    <script type="text/javascript">
        document.getElementById('idEmpleadoDest').options.length = 0;
        $('#dest-empleado-area-b').find('[value=2183]').remove();
    </script>
<!--<div id="dialog-confirm" title="¿Generar volante de correspondencia?">
    <p>
        <span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>
        ¿Desea generar el volante de correspondencia junto con el turno?
    </p>
</div>-->

