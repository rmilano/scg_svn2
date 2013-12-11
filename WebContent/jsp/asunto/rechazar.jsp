<%@ include file="/jsp/arbol.jsp"%>
<script type="text/javascript" src="<html:rewrite page="/jsp/js/ajaxfileupload.js"/>"></script>
<script type="text/javascript" src="<html:rewrite page="/jsp/js/misc-functions.js"/>"></script>
<script type="text/javascript" src="<html:rewrite page="/jsp/asunto/js/rechazar.js"/>"></script>


<html:form action="/asunto.do" styleId="asuntoForm" enctype="multipart/form-data">
    <input type="hidden" id="id_empleado_session" name="id_empleado_session"  value='<bean:write name="usuario_sesion" property="id_empleado" />' />
    <html:hidden property="method" value="" />
    <tiles:insert page="/jsp/asunto/asunto.jsp" />

    <div class="content border">
        <div class="inner corner">
            <div class="contentform rechazar-asunto">


                <table class="captura ui-widget ui-widget-content">
                    <thead>
                        <tr class="ui-widget-header ">
                            <th colspan="3"><span class="campoRequerido"> * </span>Respuesta:</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td colspan="3">
                                <html:textarea name="asuntoForm" styleId="respuesta-rechazar"
                                               property="asunto.asunto_detalle.comentario" cols="70" rows="3"
                                               onkeydown="contador(this,this.form.remLen2,3000);"
                                               onkeyup="contador(this,this.form.remLen2,3000);" styleClass="required upperCase"/>
                                <input type="hidden" name="remLen2" value="3000">
                            </td>
                        </tr>
                    </tbody>
                    <thead>
                        <tr class="ui-widget-header ">
                            <th colspan="3"><span class="campoRequerido"> * </span> Documentos adjuntos:</th>                            
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
                            <th colspan="2"><span class="campoRequerido"> * </span> Tipo:</th>
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

                    </tbody>

                    <tbody>
                        <tr>
                            <td colspan="3"><hr></td>
                        </tr>
                    </tbody>
                    <thead>
                        <tr class="ui-widget-header ">
                            <th colspan="3" class="cpp-width"><span class="campoRequerido"> * </span>Con copia para:</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td colspan="3" class="cpp-width">                                
                                <div id="arbolC">
                                    <input type="text" name="" value="" class="rechazar-desc-ccp tree icon-tree-trigger" />
                                    <html:select name="asuntoForm"
                                                 property="asunto.asunto_detalle.empleado_ccp.id_empleado"
                                                 styleId="idEmpleadoCcp" styleClass="tree rechazar-desc-ccp">
                                        <html:optionsCollection name="asuntoForm"
                                                                property="empleadosConCopiaPara" value="id_empleado"
                                                                label="nombre_completo" />
                                    </html:select>
                                    <span id="agregarCcp" class="btn_default  ui-estate-error" title="Agregar Ccp">Agregar</span>
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
                    </tbody>

                    <tbody>
                        <tr>
                            <td colspan="3"><hr></td>
                        </tr>
                    </tbody>
                    <tbody>
                        <tr>
                            <td colspan="2"><input type="button" id="rechazar" class="btn_default" value="En Trámite" title="En Trámite" />
                            </td>
                            <td>
                                <span title="Mostrar bitácora" class="ui-icon-catalog btn_default icon-bitacora">&nbsp;&nbsp;Mostrar bitácora</span>
                                <input type="button" id="regresar" class="btn_default regresar" value="Regresar" title="Regresar" />
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
