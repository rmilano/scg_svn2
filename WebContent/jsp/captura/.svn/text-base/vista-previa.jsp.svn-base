<%@ include file="/jsp/arbol.jsp"%>
<%@ include file="/jsp/taglibs.jsp"%>
<link href='<html:rewrite page="/jsp/captura/css/captura.css"/>' rel="stylesheet" type="text/css" />
<link href='<html:rewrite page="/jsp/captura/css/vista-previa.css"/>' rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<html:rewrite page="/jsp/captura/js/vista-previa.js"/>"></script>

<div class="content border">
    <div class="inner corner">
        <div class="contentform preview">
            <html:form action="/captura.do" styleId="capturaForm">
                <html:hidden property="method" value="inicio"  styleId="post-method-name"/>
                <html:hidden name="capturaForm" property="idx" />

                <table class="captura ui-widget ui-widget-content">
                    <thead>
                        <tr class="ui-widget-header texto-igual">
                            <th>Folio:</th>
                            <th>Referencia:</th>
                            <th>Documento:</th>                                            
                        </tr>                      
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <bean:write name="capturaForm" property="asunto.folio"/>
                                <html:hidden name="capturaForm" styleId="folio"  property="asunto.folio" />
                            </td>
                            <td>
                                <bean:write name="capturaForm" property="asunto.id_asunto_ref"/>                                
                            </td>
                            <td>                                
                                <bean:write name="capturaForm" property="asunto.no_oficio"/>
                            </td>
                        </tr>
                    </tbody>
                    <thead>
                        <tr class="ui-widget-header texto-igual">
                            <th>Fecha de registro:</th>
                            <th>Fecha del documento:</th>                                            
                            <th>Fecha de recepci&oacute;n:</th>                                            
                        </tr>
                    </thead>
                    <tbody>
                        <tr class="ui-widget-header ">
                            <td><bean:write name="capturaForm" property="asunto.asunto_detalle.fh_registroDDMMYYYY" /></td>
                            <td><bean:write name="capturaForm" property="asunto.fh_oficioDDMMYYYY" /></td>                            
                            <td><bean:write name="capturaForm" property="asunto.fh_recepcionDDMMYYYY" /></td>                            
                        </tr>
                    </tbody>
                    <thead>
                        <tr class="ui-widget-header ">
                            <th>Expediente:</th>
                            <th>Tema:</th>
                            <th>Subtema:</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <logic:notEmpty name="capturaForm" property="asunto.expediente">
                                    <bean:write name="capturaForm" property="asunto.expediente.expediente"/>
                                </logic:notEmpty>&nbsp;
                            </td>
                            <td>
                                <logic:notEmpty name="capturaForm" property="asunto.tema">
                                    <bean:write name="capturaForm" property="asunto.tema.tema"/>
                                </logic:notEmpty>&nbsp;
                            </td>
                            <td>
                                <logic:notEmpty name="capturaForm" property="asunto.subTema">                                    
                                        <bean:write name="capturaForm" property="asunto.subTema.tema"/>                                    
                                </logic:notEmpty>&nbsp;
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
                                <logic:notEmpty name="capturaForm" property="asunto.evento">
                                        <bean:write name="capturaForm" property="asunto.evento.evento" ></bean:write>
                                </logic:notEmpty>
                                &nbsp;
                            </td>
                            <td><bean:write name="capturaForm" property="asunto.fh_eventoDDMMYYYY"></bean:write></td>                                                        
                        </tr>
                    </tbody>

                    <thead>
                        <tr class="ui-widget-header texto-igual">                            
                            <th colspan="2">Prioridad:</th>
                            <th>Fecha prioridad</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>                            
                            <td colspan="2"><bean:write name="capturaForm" property="asunto.asunto_detalle.prioridad.prioridad" ></bean:write></td>
                            <td><bean:write name="capturaForm" property="asunto.asunto_detalle.fh_limiteDDMMYYYY" ></bean:write></td>
                        </tr>
                    </tbody>


                    <thead>
                        <tr class="ui-widget-header ">
                            <th class="justify">T&iacute;tulo:</th>
                            <th class="justify">Antecedente:</th>
                            <th class="justify">Confidencial:</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <bean:write name="capturaForm" property="asunto.asunto"/>
                            </td>
                            <td>
                                <bean:write name="capturaForm" property="asunto.antecedente"/>
                            </td>
                            <td>
                                  <logic:notEmpty name="capturaForm" property="asunto.confidencial">
                                      <logic:equal name="capturaForm" property="asunto.confidencial" value="1">
                                          Si
                                      </logic:equal>
                                      <logic:equal name="capturaForm" property="asunto.confidencial" value="0">
                                          No
                                      </logic:equal>
                                  </logic:notEmpty>
                             </td>
                        </tr>
                    </tbody>

                    <thead>
                        <tr class="ui-widget-header ">
                            <th colspan="3" class="center">Descripci&oacute;n / S&iacute;ntesis:</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td colspan="3" style="text-align:left">
                                <bean:write name="capturaForm" property="asunto.descripcionSaltos" filter="false"/>
                            </td>
                        </tr>
                    </tbody>

                    <thead>
                        <tr class="ui-widget-header ">
                            <th colspan="2">Tipo de documento:</th>
                            <th>Archivado:</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td colspan="2"><bean:write name="capturaForm" property="asunto.tipoAsunto.tipo_documento" /></td>
                            <td><bean:write name="capturaForm" property="asunto.descripcionArchivado" /></td>
                        </tr>
                    </tbody>


                    <!--                    <thead>
                                            <tr class="ui-widget-header ">
                                                <th colspan="3" class="center">
                    <logic:equal name="capturaForm" property="asunto.asunto_detalle.prioridad.id_prioridad" value="-2">
                        Fecha l&iacute;mite de atenci&oacute;n:
                    </logic:equal>
                    <logic:notEqual name="capturaForm" property="asunto.asunto_detalle.prioridad.id_prioridad" value="-2">
                        Prioridad:
                    </logic:notEqual>
                </th>
            </tr>
        </thead>
        <tbody>
            <tr>

                <td colspan="3" class="center">
                    <logic:equal name="capturaForm" property="asunto.asunto_detalle.prioridad.id_prioridad" value="-2">
                        <bean:write name="capturaForm" property="asunto.asunto_detalle.fh_limiteDDMMYYYY" />
                    </logic:equal>
                    <logic:notEqual name="capturaForm" property="asunto.asunto_detalle.prioridad.id_prioridad" value="-2">
                        <bean:write name="capturaForm" property="asunto.asunto_detalle.prioridad.prioridad" />
                    </logic:notEqual>
                </td>
            </tr>
        </tbody>-->

                    <tbody>
                        <tr>
                            <td colspan="3"><hr></td>
                        </tr>
                    </tbody>

                    <thead>
                        <tr class="ui-widget-header ">
                            <th class="center">Remitente:</th>
                            <th colspan="2" class="center">&Aacute;rea:</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td class="center">
                                <logic:equal name="capturaForm" property="asunto.asunto_detalle.empleado_remi.area.tipo" value="0">
                                    Interno:
                                </logic:equal>
                                <logic:equal name="capturaForm" property="asunto.asunto_detalle.empleado_remi.area.tipo" value="1">
                                    Externo:
                                </logic:equal>
                                <bean:write name="capturaForm" property="asunto.asunto_detalle.empleado_remi.nombre_completo" />
                            </td>
                            <td colspan="2" class="center">
                                <bean:write name="capturaForm" property="asunto.asunto_detalle.empleado_remi.area.area" />
                            </td>
                        </tr>
                    </tbody>
                    <thead>
                        <tr class="ui-widget-header">
                            <th>Destinatarios:</th>
                            <th>Instruccion:</th>
                            <th>Comentarios:</th>
                        </tr>
                    </thead>
                    <tbody id="tablaDest">
                        <tr>
                            <td colspan="3"></td>
                        </tr>
                        <logic:iterate id="empl" name="capturaForm" property="asunto.asunto_detalle.empleadosDest" type="mx.gob.economia.scg.model.Empleado">
                            <tr id='eliminarCcp<bean:write name="empl" property="id_empleado"/>'>
                                <td><bean:write name="empl" property="nombre_completo" /></td>
                                <td>
                                    <logic:notEmpty name="empl" property="instruccion">
                                        <bean:write name="empl" property="instruccion.instruccion" />                                        
                                    </logic:notEmpty>
                                </td>                               
                                <td><bean:write name="empl" property="comentario" /></td>
                            </tr>
                        </logic:iterate>
                    </tbody>
                    <thead>
                        <tr class="ui-widget-header">
                            <th colspan="3">Copiado a:</th>
                        </tr>
                    </thead>
                    <tbody id="tablaCcp">
                        <tr>
                            <td colspan="3"></td>
                        </tr>
                        <logic:iterate id="empl" name="capturaForm"
                                       property="asunto.empleadosCcp"
                                       type="mx.gob.economia.scg.model.Empleado">
                            <tr
                                id='eliminarCcp<bean:write name="empl" property="id_empleado"/>'>
                                <td colspan="3"><bean:write name="empl" property="nombre_completo" /></td>
                            </tr>
                        </logic:iterate>
                    </tbody>

                    <tbody>
                        <tr>
                            <td colspan="3"><hr></td>
                        </tr>
                    </tbody>

                    <thead>
                        <tr class="ui-widget-header">
                            <th colspan="2" class="center">Documento:</th>
                            <th class="center">Tipo de documento:</th>
                        </tr>
                    </thead>
                    <tbody id="tablaDoc">
                        <logic:iterate id="doc" name="capturaForm" property="asunto.asunto_detalle.documentos"
                                       type="mx.gob.economia.scg.model.Documento">
                            <tr id='eliminarDoc<bean:write name="doc" property="idx"/>'>
                                <td colspan="2"><bean:write name="doc" property="documento" /></td>
                                <td><bean:write name="doc" property="tipo_documento.tipo_documento" /></td>
                            </tr>
                        </logic:iterate>
                    </tbody>

                    <tbody>
                        <tr><td colspan="3"><hr></td></tr>
                    </tbody>

                    <thead>
                        <tr class="ui-widget-header">
                            <th colspan="3" class="center">Comentarios:</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td colspan="3" class="justify">
                                <textarea class="upperCase comentario-general" disabled="disabled"><bean:write name="capturaForm" property="asunto.asunto_detalle.comentario" /></textarea>

                            </td>
                        </tr>
                    </tbody>
                    <thead>
                        <tr class="ui-widget-header ">
                            <th colspan="3">Datos del volante</th>
                        </tr>
                    </thead>
                    <thead>
                        <tr class="ui-widget-header ">
                            <th>Referencia:</th>
                            <th>Tipo de Seguimiento:</th>
                            <th>Firmante:</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <bean:write name="capturaForm" property="asunto.id_asunto_ref" />
                            </td>
                            <td>
                                <bean:write name="capturaForm" property="asunto.asunto_detalle.tipoSeguimiento.tipoSeguimiento" />
                            </td>
                            <td>
                                <bean:write name="capturaForm" property="asunto.asunto_detalle.empleadoFirmanteVolante.nombre_completo" />
                            </td>
                        </tr>
                    </tbody>


                    <tbody>
                        <tr>
                            <td colspan="3" class="center">
                                <input type="button" id="regresar" class="btn_default" value="Editar" title="Editar"/>

                                <logic:present name="showGuardarBtn">
                                    <input type="button" id="guardar" class="btn_default" value="Guardar" title="Guardar" />
                                    <input type="button" id="guardar-enviar-detinatarios" class="btn_default revisor" value="Guardar y enviar a destinatarios" title="Enviar a destinatarios" />
                                </logic:present>
<%--                                <logic:present name="showAprobadoRevisionBtn">--%>
<%--                                    <input type="button" id="scg-aprovado" class="btn_default" value="Aprobado" title="Aprobado asunto en revisi&oacute;n" /><!-- Se actualiza el asunto y se cambia de estaus a revisado -->--%>
<%--                                </logic:present>--%>
<%--                                <logic:present name="showAprobadoSupervisionBtn">--%>
<%--                                    <input type="button" id="scg-supervisado" class="btn_default" value="Aprovado" title="Aprueba el asunto en supervisi&oacute;n" /><!-- Si el capturista modifica el asunto -->--%>
<%--                                </logic:present>--%>
                                <logic:present name="showEnviarDestinatarioBtn">
                                    <input type="button" id="modificar-enviar-detinatarios" class="btn_default revisor" value="Modificar y enviar a destinatarios" title="Enviar a destinatarios" />
                                </logic:present>
                                <logic:present name="showModificarBtn">
                                    <input type="button" id="scg-actualiza-asunto" class="btn_default" value="Actualizar" title="Actualiza el asunto" /><!-- Si el capturista modifica el asunto -->
                                </logic:present>
<%--                                <logic:present name="showEnviarRevisionBtn">--%>
<%--                                    <input type="button" id="scg-enviar-revision" class="btn_default" value="Enviar a revisi&oacute;n" title="Enviar la captura a revisi&oacute;n" />--%>
<%--                                </logic:present>--%>
                                <logic:present name="showArchivarBtn">
                                    <input type="button" id="guardar" class="btn_default" value="Archivar" title="Archivar asunto" />
                                </logic:present>                               
                            </td>
                        </tr>
                    </tbody>

                </table>
            </html:form>

        </div>
    </div>
</div>
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
<div class="modal-arbol-externo">
    <div class="treeviewContent">
        <ul class="tree-branch">
            ${arbol_externo}
        </ul>
    </div>
</div>
<logic:present name="no_asunto_exitoso">
    <input type="hidden" id="ids_exito" value='<bean:write name="no_asunto_exitoso"/>'/>
        <SCRIPT type="text/javascript"  LANGUAGE="JavaScript">
            $( function() {
                var ids = $("#ids_exito").val();
                jAlert("Se guardo la captura con el folio: \n"+ $("#folio").val()+ " </div>", "Se guardo exitosamente la captura",function(){
                    location.href='<html:rewrite page="/consulta-captura.do?method=inicio"/>';
                });
            });
        </SCRIPT>
</logic:present>
