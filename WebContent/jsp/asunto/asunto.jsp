<%@ include file="/jsp/taglibs.jsp"%>
<link href='<html:rewrite page="/jsp/asunto/css/asuntos.css"/>' rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<html:rewrite page="/jsp/asunto/js/asuntos.js"/>"></script>

<div class="content border">
    <div class="inner corner">
        <div class="contentform">
            <html:hidden name="asuntoForm" property="asunto.fh_limite_asuntoDDMMYYYY" styleId="fh_limite_asunto"/>

            <table class="historico ui-widget ui-widget-content">
                <thead>
                    <tr class="ui-widget-header ">
                        <th>Folio:</th>
                        <!--<th>Referencia</th>-->
                        <th colspan="2">Estatus:</th>
                        <!--<th>Tipo Seguimiento:</th>-->
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><bean:write name="asuntoForm" property="asunto.contador_folio" /></td>
                        <%-- <td><bean:write name="asuntoForm" property="asunto.id_asunto_ref" /></td> --%>
                        <td colspan="2"><bean:write name="asuntoForm" property="asunto.estatus_desc" /></td>
                        <!--<td>
                            <logic:notEmpty name="asuntoForm"  property="asunto.ultimaDetalle.tipoSeguimiento.tipoSeguimiento">
                                <bean:write name="asuntoForm" property="asunto.ultimaDetalle.tipoSeguimiento.tipoSeguimiento" />
                            </logic:notEmpty>
                        </td>-->
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
                            <logic:present name="asuntoForm" property="asunto.expediente.expediente">
                                <bean:write name="asuntoForm" property="asunto.expediente.expediente" />
                            </logic:present>
                            &nbsp;
                        </td>
                        <td><bean:write name="asuntoForm" property="asunto.tema.tema" /></td>
                        <td><bean:write name="asuntoForm" property="asunto.subTema.tema" /></td>
                    </tr>
                </tbody>
                <thead>
                    <tr class="ui-widget-header ">
                        <th colspan="2">Evento:</th>
                        <th>Fecha de evento:</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td colspan="2">
                            <logic:present name="asuntoForm" property="asunto.evento.evento">
                                <bean:write name="asuntoForm" property="asunto.evento.evento" />
                            </logic:present>
                            &nbsp;
                        </td>
                        <td><bean:write name="asuntoForm" property="asunto.fh_eventoDDMMYYYY" /></td>
                    </tr>
                </tbody>
                <thead>
                    <tr class="ui-widget-header ">
                        <!--<th>Instrucci&oacute;n:</th>-->
                        <th>Prioridad documento:</th>
                        <th>Referencia</th>
                        <th>Tipo de documento:</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <!--<td><bean:write name="asuntoForm" property="asunto.instruccion.instruccion" /></td>-->
                        <td><bean:write name="asuntoForm" property="asunto.prioridad.prioridad_dias" /></td>
                        <td><bean:write name="asuntoForm" property="asunto.id_asunto_ref" /></td>
                        <td><bean:write name="asuntoForm" property="asunto.tipoAsunto.tipo_documento" /></td>
                    </tr>
                </tbody>
                <thead>
                    <tr class="ui-widget-header ">
                        <th>T&iacute;tulo:</th>
                        <th>Antecedente:</th>
                        <th>Confidencial:</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><bean:write name="asuntoForm" property="asunto.asunto" /></td>
                        <td><bean:write name="asuntoForm" property="asunto.antecedente" /></td>
                        <td>                            
                          <logic:notEmpty name="asuntoForm" property="asunto.confidencial">
                              <logic:equal name="asuntoForm" property="asunto.confidencial" value="1">
                                  Si
                              </logic:equal>
                              <logic:equal name="asuntoForm" property="asunto.confidencial" value="0">
                                  No
                              </logic:equal>
                          </logic:notEmpty>
                        </td>
                    </tr>
                </tbody>

                <thead>
                    <tr class="ui-widget-header ">
                        <th>Documento:</th>
                        <!--<th>Fecha de registro:</th>  Alex:20111020-->
                        <th>Fecha del documento:</th>
                        <th>Fecha de recepci&oacute;n:</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><bean:write name="asuntoForm" property="asunto.no_oficio" /></td>
                        <td><bean:write name="asuntoForm" property="asunto.fh_oficioDDMMYYYY" /></td>
                        <td><bean:write name="asuntoForm" property="asunto.fh_recepcionDDMMYYYY" /></td>
                    </tr>
                </tbody>

                <thead>
                    <tr class="ui-widget-header ">
                        <th colspan="3">Descripci&oacute;n:</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td colspan="3" style="text-align:left"><bean:write name="asuntoForm" property="asunto.descripcionSaltos" filter="false"/></td>
                    </tr>
                    <tr>
                        <td colspan="3" style="text-align:right">
                            <span title="Mostrar bitácora" class="ui-icon-catalog btn_default icon-bitacora">Mostrar bitácora</span>
                        </td>
                    </tr>
                </tbody>

                <thead>
                    <tr class="ui-widget-header ">
                        <th colspan="3">Con copia para:</th>
                    </tr>
                </thead>
                <tbody>
                    <logic:iterate id="empl" name="asuntoForm"
                                   property="asunto.empleadosCcp"
                                   type="mx.gob.economia.scg.model.Empleado">
                        <tr>                            
                            <td colspan="2"><bean:write name="empl" property="nombre_completo" /></td>
                            <td class="area-empleado"><bean:write name="empl" property="area.area" /></td>
                        </tr>
                    </logic:iterate>

                </tbody>
            </table>
        </div> <!-- contentform -->
    </div><!-- inner corner -->
</div><!-- content border -->

<div class="content border">
    <div class="inner corner">
        <div class="contentform accordion ui-accordion historico-asunto">

            <h3 class="head ui-accordion-header ui-state-default ui-widget-content ui-widget-header">
                <span class="ui-icon ui-icon-triangle-1-e"></span>
                <a>Hist&oacute;rico</a>
                <span title="Ver todo el historial" class="ui-icon-display expandable-accordion"></span>
                <span title="Ocultar el historial" class="ui-icon-display collapsable-accordion"></span>

            </h3>
            <div class="ui-accordion-content ui-helper-reset ui-widget-content ui-corner-bottom ui-accordion-content-active">
                <div class="accordion ui-accordion accordion-inner">

                    <logic:iterate id="asunto_det" name="asuntoForm" property="asunto.asuntos_detalles">
                        <h3 class="head ui-accordion-header ui-state-default ui-widget-content ui-widget-header">
                            <span class="ui-icon ui-icon-triangle-1-e"></span>
                            <a>
                                <span class="head-detalle-bold">REM:</span><bean:write name="asunto_det" property="empleado_remi.nombre_completo" /> ->
                                <span class="head-detalle-bold">DEST:</span><bean:write name="asunto_det" property="empleado_dest.nombre_completo" />
                                <span class="head-detalle-bold">FH.ATENCI&Oacute;N:</span><bean:write name="asunto_det" property="fh_registroDDMMYYYY" /> -
                                <span class="head-detalle-bold">ESTATUS:</span><bean:write name="asunto_det" property="estatus_desc" />
                            </a>
                        </h3>
                        <div class="ui-accordion-content ui-helper-reset ui-widget-content ui-corner-bottom ui-accordion-content-active">
                            <table class="historico ui-widget ui-widget-content">
                                <thead>
                                    <tr class="ui-widget-header">
                                        <!--<th>Fecha atenci&oacute;n:</th> Alex:20111020-->
                                        <th>Fecha de Registro:</th>
                                        <th>Fecha L&iacute;mite:</th>
                                        <th>Estatus:</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td><bean:write name="asunto_det" property="fh_registroDDMMYYYY" /></td>
                                        <td><bean:write name="asunto_det" property="fh_limiteDDMMYYYY" /></td>
                                        <td><bean:write name="asunto_det" property="estatus_desc" /></td>
                                    </tr>
                                </tbody>
                                <thead>
                                    <tr class="ui-widget-header ">
                                        <th colspan="3">Comentario:</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td colspan="3" class="comentario" style="text-align:left"><bean:write name="asunto_det" property="empleado_dest.comentarioSaltos" filter="false"/></td>
                                    </tr>
                                </tbody>
                                <logic:notEqual name="asunto_det" property="instruccionAdicional" value="">
                                    <thead>
                                        <tr class="ui-widget-header ">
                                            <th colspan="3">Instrucción adicional (Agenda):</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td colspan="3" class="comentario" style="text-align:left"><bean:write name="asunto_det" property="instruccionAdicional" filter="false"/></td>
                                        </tr>
                                    </tbody>
                                </logic:notEqual>
                                <!-- Si el estatus es diferente de Finalizado (3)-->
                                <logic:notEqual name="asunto_det" property="estatus" value="3">
                                    <thead>
                                        <tr class="ui-widget-header ">
                                            <th colspan="2">Remitente:</th>                                            
                                                <th>Destinatario:</th>                                            
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td colspan="2">
                                                <bean:write name="asunto_det" property="empleado_remi.nombre_completo" />,&nbsp;
                                                <span class="area-empleado"><bean:write name="asunto_det" property="empleado_remi.area.area" /></span>
                                            </td>
                                            <td>
                                                <logic:notEmpty name="asunto_det" property="empleado_dest.nombre_completo">
                                                    <bean:write name="asunto_det" property="empleado_dest.nombre_completo" />,&nbsp;
                                                    <span class="area-empleado"><bean:write name="asunto_det" property="empleado_dest.area.area" /></span>
                                                </logic:notEmpty>
                                            </td>
                                        </tr>
                                    </tbody>
                                    <thead>
                                        <tr class="ui-widget-header ">
                                            <th>Instrucción:</th>                                        
                                            <th colspan="2">Prioridad turno:</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>
                                                <bean:write name="asunto_det" property="instruccion.instruccion" />
                                            </td>
                                            <td colspan="2">
                                                <bean:write name="asunto_det" property="prioridad.prioridad_dias" />
                                            </td>
                                        </tr>
                                    </tbody>
                                </logic:notEqual>
                                <!-- Si el estatus es diferente igual a Finalizado (3)-->
                                <logic:equal name="asunto_det" property="estatus" value="3">
                                    <thead>
                                        <tr class="ui-widget-header ">
                                            <th colspan="4">Concluido por:</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td colspan="4">
                                                <bean:write name="asunto_det" property="empleado_remi.nombre_completo" />,&nbsp;
                                                <span class="area-empleado"><bean:write name="asunto_det" property="empleado_remi.area.area" /></span>
                                            </td>
                                        </tr>
                                    </tbody>
                                </logic:equal>


                                <logic:notEmpty name="asunto_det" property="documentos">
                                    <thead>
                                        <tr class="ui-widget-header ">
                                            <th colspan="3">Documentos adjuntos:</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <logic:iterate id="doc" name="asunto_det" property="documentos" type="mx.gob.economia.scg.model.Documento">
                                            <tr>
                                                <td colspan="2"><bean:write name="doc" property="documento" /></td>
                                                <td>
                                                    <a class='descargarDoc mouseOver'
                                                       <logic:notEqual name="asunto_det" property="estatus" value="14">
                                                           title='<bean:write name="doc" property="id_documento"/>'
                                                       </logic:notEqual>
                                                       <logic:equal name="asunto_det" property="estatus" value="14">
                                                           title='<bean:write name="asunto_det" property="id_asunto_detalle"/>-<bean:write name="doc" property="id_documento"/>'
                                                       </logic:equal>
                                                       name='<bean:write name="asunto_det" property="id_asunto_detalle"/>'> 
                                                       <img alt="documento adjunto" height='25' width='25' src='../../imagenes/file.png'> </a>
                                                </td>
                                            </tr>
                                        </logic:iterate>
                                    </tbody>
                                </logic:notEmpty>

                                <!-- Fin AGG 20111103 -->
                                <logic:notEmpty name="asunto_det" property="empleadosCcpDetalle">
                                    <thead>
                                        <tr class="ui-widget-header ">
                                            <th colspan="3">Con copia para:</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <logic:iterate id="empl2" name="asunto_det"
                                           property="empleadosCcpDetalle"
                                           type="mx.gob.economia.scg.model.Empleado">
                                        <tr>
                                                <td colspan="2"><bean:write name="empl2" property="nombre_completo" /></td>
                                                <td class="area-empleado"><bean:write name="empl2" property="area.area" /></td>
                                        </tr>
                                    </logic:iterate>
                                    </tbody>
                                </logic:notEmpty>
                                <!-- Fin AGG 20111103 -->

                                <logic:present name="asunto_det"
                                               property="asuntos_asociados">
                                    <thead>
                                        <tr class="ui-widget-header ">
                                            <th colspan="3">Asuntos asociados:</th>
                                        </tr>
                                        <tr class="ui-widget-header ">
                                            <th>Asunto:</th>
                                            <th colspan="2">Destinatario:</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <logic:iterate id="asociado" name="asunto_det"
                                                       property="asuntos_asociados"
                                                       type="mx.gob.economia.scg.model.Asunto">
                                            <tr>                            
                                                <td><bean:write name="asociado" property="descripcion" /></td>
                                                <td><bean:write name="asociado" property="asunto_detalle.empleado_dest.nombre_completo" /></td>
                                                <td>
                                                    <input type="button"
                                                           onclick="obtenerAsunto('<bean:write name="asociado" property="id_asunto" />');"
                                                           onmouseover="style.cursor='pointer'"
                                                           class="btn_default"
                                                           id="limpiar" value="Ver detalle" title="Ver detalle" />
                                                </td>
                                            </tr>
                                        </logic:iterate>

                                    </tbody>
                                </logic:present>


                            </table>
                        </div>

                    </logic:iterate>
                </div>
            </div>
        </div> <!-- contentform -->
    </div><!-- inner corner -->
</div><!-- content border -->

<!-- histórico de actualizaciones de estatus-->

<div class="content border">
    <div class="inner corner">
        <div class="contentform accordion ui-accordion historico-asunto">

            <h3 class="head ui-accordion-header ui-state-default ui-widget-content ui-widget-header">
                <span class="ui-icon ui-icon-triangle-1-e"></span>
                <a>Hist&oacute;rico de movimientos</a>
            </h3>
            <div class="ui-accordion-content ui-helper-reset ui-widget-content ui-corner-bottom ui-accordion-content-active">
                <div class="accordion ui-accordion accordion-inner">

                    <logic:iterate id="historico" name="asuntoForm"
                                       property="asunto.actualizacionesCaptura"
                                       type="mx.gob.economia.scg.model.AsuntoCapturaActualizacion">
                        <logic:equal name="historico" property="tipo_actualizacion" value="1">
                            <h3 class="ui-state-default ui-widget-header">
                                <span class=""></span>
                                <a>
                                    <span class="head-detalle-bold">Actualizaci&oacute;n: </span><bean:write name="historico" property="fh_actualizacion" format="dd/MM/yyyy HH:mm:ss"/> -
                                    <span class="head-detalle-bold">Por: </span><bean:write name="historico" property="empleado_act.nombre_completo" />
                                    <span class="head-detalle-bold">Modificaci&oacute;n: </span><bean:write name="historico" property="estatus_desc" />
                                </a>
                            </h3>
                        </logic:equal>
                    </logic:iterate>
                </div>
            </div>
        </div> <!-- contentform -->
    </div><!-- inner corner -->
</div><!-- content border -->

<!-- histórico de actualizaciones de descripción -->
<%--
<div class="content border">
    <div class="inner corner">
        <div class="contentform accordion ui-accordion historico-asunto">

            <h3 class="head ui-accordion-header ui-state-default ui-widget-content ui-widget-header">
                <span class="ui-icon ui-icon-triangle-1-e"></span>
                <a>Hist&oacute;rico de la descripción</a>
            </h3>
            <div class="ui-accordion-content ui-helper-reset ui-widget-content ui-corner-bottom ui-accordion-content-active">
                <div class="accordion ui-accordion accordion-inner">
                    <logic:iterate id="historico" name="asuntoForm"
                                       property="asunto.actualizacionesDescripcion"
                                       type="mx.gob.economia.scg.model.AsuntoCapturaActualizacion">
                            <h3 class="head ui-accordion-header ui-state-default ui-widget-content ui-widget-header">
                                <span class="ui-icon ui-icon-triangle-1-e"></span>
                                <a>
                                    <span class="head-detalle-bold">Actualizaci&oacute;n: </span><bean:write name="historico" property="fh_actualizacion" format="dd/MM/yyyy HH:mm:ss"/> -
                                    <span class="head-detalle-bold">Por:</span><bean:write name="historico" property="empleado_act.nombre_completo" />
                                </a>
                            </h3>
                            <div class="ui-accordion-content ui-helper-reset ui-widget-content ui-corner-bottom ui-accordion-content-active">
                                <table class="historico ui-widget ui-widget-content">
                                    <thead>
                                        <tr class="ui-widget-header ">
                                            <th colspan="3">Descripci&oacute;n:</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td colspan="3" class="comentario" style="text-align:left"><bean:write name="historico" property="descripcion" filter="false"/></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>                        
                    </logic:iterate>
                </div>
            </div>
        </div> <!-- contentform -->
    </div><!-- inner corner -->
</div><!-- content border -->
--%>

<!--despliegue de bitácora-->
<div id="dialog-form-bitacora" title="Bit&aacute;cora">
    <html:hidden name="asuntoForm" property="asunto.id_asunto" styleId="id_asunto"/>
    <%--<textarea rows="20" cols="200" id="bitacora" styleClass="upperCase"></textarea>--%>
    <html:textarea name="asuntoForm" property="bitacora" styleId="bitacora" styleClass="upperCase" cols="200" rows="20"/>
</div>