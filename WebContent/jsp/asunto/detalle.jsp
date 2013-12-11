<%@ include file="/jsp/arbol.jsp"%>
<%
  String path = request.getContextPath();

  String getProtocol=request.getScheme();
  String getDomain=request.getServerName();
  String getPort=Integer.toString(request.getServerPort());

  String getPath = getProtocol+"://"+getDomain+":"+getPort+path+"/";

  String getURI=request.getRequestURI();
%>
<script type="text/javascript" src="<html:rewrite page="/jsp/asunto/js/detalle.js"/>"></script>

<html:form action="/asunto.do" styleId="asuntoForm" >
    <tiles:insert page="/jsp/asunto/asunto.jsp" />
    <html:hidden property="method" value="" />
    <html:hidden name="asuntoForm" property="asunto.id_asunto" styleId="id-asuntoform" />
    <html:hidden name="asuntoForm" property="asunto.ultimaDetalle.id_asunto_detalle" styleId="id-asuntoDetalle" />
    <logic:present name="exitoMsg">
        <label class="exitoMsg"><bean:write name="exitoMsg" /></label>
    </logic:present>

    <table class="asunto-detalle ui-widget ui-widget-content">
        <thead>
            <tr class="ui-widget-header ">
                <logic:present name="showAtenderBtn">
                    <th>
                        <input type="button" id="atender" class="btn_default" value="Atender" title="Atender" />
                    </th>
                </logic:present>
                <logic:present name="showDarVoBoBtn">
                    <th>
                        <input type="button" id="darVoBo" class="btn_default" value="Dar Vo.Bo." title="Dar Vo.Bo." />
                    </th>
                </logic:present>
                <logic:present name="showTurnarBtn">
                    <th>
                        <input type="button" id="turnar-inicio" class="btn_default" value="" title="Turnar" style='background:url("<%=getPath%>imagenes/rayo.png"); height:20px; width:25px' />
                    </th>
                </logic:present>
                <logic:present name="showReturnarBtn">
                    <th>
                        <input type="button" id="Returnar-inicio" class="btn_default" value="" title="Re turnar" style='background:url("<%=getPath%>imagenes/rayo_r.png"); height:20px; width:25px' />
                    </th>
                </logic:present>
                <logic:present name="showGestionarCcpBtn">
                    <th>
                        <input type="button" id="gestionar-ccp" class="btn_default" value="Replicar CCP" title="Replicar CCP" />
                    </th>
                </logic:present>
                <logic:present name="showFinalizarBtn">
                    <th>
                        <input type="button" id="finalizar" class="btn_default" value="Finalizar" title="Finalizar" />
                    </th>
                </logic:present>
                <logic:present name="showRechazarBtn">
                    <th>
                        <input type="button" id="rechazar" class="btn_default" value="En Trámite" title="En Trámite" />
                    </th>
                </logic:present>
                <logic:present name="showEliminarBtn">
                    <th>
                        <input type="button" id="eliminar" class="btn_default" value="Eliminar" title="Eliminar" />
                    </th>
                </logic:present>
                <logic:present name="btnRegresar">
                    <th>
                        <input type="button" class="btn_default" title="Regresar" value="Regresar" onclick="location.href='<html:rewrite page=""/><bean:write name="btnRegresar" />'"/>
                    </th>
                </logic:present>
                <logic:notPresent name="btnRegresar">
                    <th>
                        <input type="button" id="regresar" class="btn_default regresar" value="Regresar" title="Regresar" />
                    </th>
                </logic:notPresent>                
                <logic:present name="showModificarBtn">    
                    <th>
                        <input type="button" id="modificar-asunto" class="btn_default modificar" value="Modificar" title="Modificar" />
                    </th>
                </logic:present>
                <logic:present name="showGestionToAgendaBtn">
                    <th>
                        <input type="button" id="clonar-asunto-gestionToAgenda" class="btn_default CopiarAsunto" value="Copiar asunto" title="Copiar asunto" />
                    </th>
                </logic:present>
<%--                <logic:present name="showRevisarBtn">    --%>
<%--                    <th>--%>
<%--                        <input type="button" id="modificar-asunto" class="btn_default modificar" value="Revisar" title="Revisar" />--%>
<%--                    </th>--%>
<%--                </logic:present> --%>
<%--                <logic:present name="showEnviarRevisionBtn">--%>
<%--                    <th>--%>
<%--                        <input type="button" id="enviar-revisor" class="btn_default revisor" value="Enviar a revisi&oacute;n" title="Enviar a revisi&oacute;n" />--%>
<%--                    </th>--%>
<%--                </logic:present>--%>
                <logic:present name="showEnviarDestinatarioBtn">
                    <th>
                        <input type="button" id="enviar-detinatarios" class="btn_default revisor" value="Enviar a destinatarios" title="Enviar a destinatarios" />
                    </th>
                </logic:present>
<%--                <logic:present name="showEnviarSupervisionBtn">  --%>
<%--                    <th>--%>
<%--                        <input type="button" id="enviar-supervision" class="btn_default revisor" value="Enviar a supervisi&oacute;n" title="Enviar a supervisi&oacute;n" />--%>
<%--                    </th>--%>
<%--                </logic:present>--%>
                <logic:present name="showEnviarCapturaBtn">
                    <th>
                        <input type="button" id="enviar-captura" class="btn_default revisor" value="Enviar a captura" title="Enviar a captura" />
                    </th>
                </logic:present>                
<%--                <logic:notPresent name="VOLANTE_CORRESPONDENCIA">--%>
<%--                    <logic:present name="showGenerarVolanteBtn">--%>
<%--                        <th>--%>
<%--                            <input type="button" id="volante" class="btn_default" value="Generar Volante" title="Generar volante"/>--%>
<%--                        </th>--%>
<%--                    </logic:present>--%>
<%--                </logic:notPresent>--%>
                <logic:present name="showGenerarVolanteBtn">
                    <th>
                        <input type="button" id="volante" class="btn_default" value="Generar Volante" title="Generar volante"/>
                    </th>
                </logic:present>
                    <logic:present name="showEditarTurnoBtn">
                        <logic:present parameter="id_asunto">
                            <th>
                                <input type="button" id="editar-turno" class="btn_default modificar" value="Editar turno" title="Editar turno" />
                            </th>
                        </logic:present>
                </logic:present>
                <logic:present name="showBitacoraBtn">
                    <th>
                        <span title="Mostrar bitácora" class="ui-icon-catalog btn_default icon-bitacora">&nbsp;&nbsp;Mostrar bitácora</span>
                    </th>
                </logic:present>
            </tr>
        </thead>
    </table>
    <logic:present name="mensajeAsuntoEnUso">            
        <label id="msj-asunto-ocupado" class="hidden"><bean:write name="mensajeAsuntoEnUso" /></label>
    </logic:present>
</html:form>



