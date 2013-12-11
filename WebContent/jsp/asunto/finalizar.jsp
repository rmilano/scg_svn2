<%@ include file="/jsp/arbol.jsp"%>

<link href='<html:rewrite page="/jsp/asunto/css/finalizar.css"/>' rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<html:rewrite page="/jsp/js/ajaxfileupload.js"/>"></script>
<script type="text/javascript" src="<html:rewrite page="/jsp/asunto/js/finalizar.js"/>"></script>

<html:form action="/asunto.do" styleId="asuntoForm" enctype="multipart/form-data">
    <html:hidden property="method" value="" />
    <tiles:insert page="/jsp/asunto/asunto.jsp" />
    <div class="content border">
        <div class="inner corner">
            <div class="contentform">

                <table class="scg-finalizado ui-widget ui-widget-content texto-igual">
                    <thead>
                        <tr class="ui-widget-header texto-igual">
                            <th colspan="3">Comentario:</th>                            
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td colspan="3">
                                <html:textarea name="asuntoForm" styleId="scg-descricion-finalizar"
                                               property="asunto.asunto_detalle.comentario" cols="70" rows="3"
                                               onkeydown="contador(this,this.form.remLen2,3000);"
                                               onkeyup="contador(this,this.form.remLen2,3000);" styleClass="upperCase"/>
                                <input type="hidden" name="remLen2" value="3000" />   
                            </td>             
                        </tr>
                    </tbody>
                    <tbody>
                        <tr>
                            <td colspan="3">
                                <hr/>
                            </td>             
                        </tr>
                    </tbody>
                    <thead>
                        <tr class="ui-widget-header texto-igual">
                            <th colspan="3">Documentos adjuntos:</th>                            
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><html:text name="asuntoForm" styleId="documento"
                                       property="asunto.asunto_detalle.documento.documento"
                                       maxlength="150" value="Nombre del documento" size="32" styleClass="input_docsAdj upperCase only-chars-file-name" />
                            </td>
                            <td colspan="2"><html:file name="asuntoForm" styleId="adjunto"
                                       property="asunto.asunto_detalle.documento.adjunto" />
                            </td>           
                        </tr>
                        <tr>
                            <td><html:select name="asuntoForm"
                                         property="asunto.asunto_detalle.documento.tipo_documento.id_tipo_documento"
                                         styleClass="tree" styleId="id_tipo_documento">
                                    <html:option value="-1">Seleccione un tipo de documento</html:option>
                                    <html:optionsCollection name="tipos_documento"
                                                            value="id_tipo_documento" label="tipo_documento" />
                                </html:select></td>
                            <td colspan="2"><input type="button" id="adjuntarDoc" class="btn_default" value="Adjuntar Documento" title="Adjuntar Documento" />
                            </td>
                        </tr>
                    </tbody>
                    <thead>
                        <tr class="ui-widget-header texto-igual">
                            <th>Documento</th>
                            <th>Tipo documento</th>
                            <th>Eliminar</th>                          
                        </tr>
                    </thead>
                    <tbody id="tablaDoc">                        
                        <logic:iterate id="doc" name="asuntoForm" property="asunto.asunto_detalle.documentos" type="mx.gob.economia.scg.model.Documento">
                            <tr id='eliminarDoc<bean:write name="doc" property="idx"/>'>
                                <td><bean:write name="doc" property="documento" /></td>
                                <td><bean:write name="doc" property="tipo_documento.tipo_documento" /></td>
                                <td>
                                    <a class='eliminarDoc' name='<bean:write name="doc" property="idx"/>'>
                                        <img alt="" height='25' width='25' src='../../imagenes/file_remove.png'>
                                    </a>
                                    <a class='descargarDoc' name='<bean:write name="doc" property="idx"/>'>
                                        <img alt="" height='25' width='25' src='../../imagenes/file.png'>
                                    </a>
                                </td>
                            </tr>
                        </logic:iterate>                         
                    </tbody>
                    <tbody>
                        <tr>
                            <td colspan="3">
                                <hr/>
                            </td>             
                        </tr>
                    </tbody>
                    <thead>
                        <tr class="ui-widget-header texto-igual">
                            <th colspan="3">Con copia para:</th>                            
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td colspan="2">
                                <div id="arbolC">
                                    <input type="text" name="" value="" class="tree icon-tree-trigger" id="scg-desc-area" /> 
                                    <html:select name="asuntoForm"
                                                 property="asunto.asunto_detalle.empleado_ccp.id_empleado"
                                                 styleId="idEmpleadoCcp" styleClass="tree">
                                        <html:optionsCollection name="asuntoForm"
                                                                property="empleadosConCopiaPara" value="id_empleado"
                                                                label="nombre_completo" />
                                    </html:select></div>
                            </td> 
                            <td>
                                <input type="button" id="agregarCcp" class="btn_default" value="+ CCP" title="Agregar Ccp" />
                            </td>
                        </tr>
                    </tbody>
                    <thead>
                        <tr class="ui-widget-header texto-igual">
                            <th colspan="2">Con copia para:</th>    
                            <th>Eliminar</th>
                        </tr>
                    </thead>
                    <tbody id="tablaCcp">
                        <logic:iterate id="empl" name="asuntoForm"
                                       property="asunto.asunto_detalle.empleadosCcp"
                                       type="mx.gob.economia.scg.model.Empleado">
                            <tr
                                id='eliminarCcp<bean:write name="empl" property="id_empleado"/>'>
                                <td colspan="2"><bean:write name="empl" property="nombre_completo" /></td>
                                <td><a class='eliminarCcp' name='<bean:write name="empl" property="id_empleado"/>'> <img alt="" height='25' width='25' src='../../imagenes/group_remove.png' />
                                    </a>
                                </td>
                            </tr>
                        </logic:iterate>
                    </tbody>
                    <tbody>
                        <tr>
                            <td colspan="3">
                                <hr/>
                            </td>             
                        </tr>
                    </tbody>
                    <thead>
                        <tr class="ui-widget-header texto-igual">
                            <th colspan="2">
                                <html:checkbox name="asuntoForm" styleId="documento"
                                               property="enviarcorreo"
                                               value="si" styleClass="input_docsAdj upperCase only-chars-file-name" />Enviar Correo                                                     
                                <input type="button" id="finalizar" class="btn_default"
                                       value="Finalizar" title="Finalizar" />
                            </th>    
                            <th>
                                <span title="Mostrar bitácora" class="ui-icon-catalog btn_default icon-bitacora">&nbsp;&nbsp;Mostrar bitácora</span>
                                <input type="button" id="regresar" class="btn_default regresar" value="Regresar" title="Regresar" />
                            </th>                  
                        </tr>
                    </thead>
                </table>                
            </div>
        </div>
    </div>
</html:form>
<div class="modal">
    <div class="treeviewContent">
        <ul id="tree">
            ${arbol_raiz}
        </ul>
    </div>
</div>
