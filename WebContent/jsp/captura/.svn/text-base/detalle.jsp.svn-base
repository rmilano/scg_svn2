<!--Plantilla para el detalle de una asunto-->

<%@ include file="/jsp/arbol.jsp"%>
<script type="text/javascript" src="<html:rewrite page="/jsp/asunto/js/detalle.js"/>"></script>

<html:form action="/captura.do" styleId="capturaForm" enctype="multipart/form-data">
    <tiles:insert page="/jsp/asunto/asunto.jsp" />
    <html:hidden property="method" value="" />
    <logic:present name="exitoMsg">
        <label class="exitoMsg"><bean:write name="exitoMsg" /></label>
    </logic:present>

    <table>
        <tbody valign="top">
            <tr>
                <logic:present name="showAtenderBtn">
                    <td><input type="button" id="atender" class="btn_default"
                               value="Atender" title="Atender" /></td>
                    </logic:present>
                    <logic:present name="showTurnarBtn">
                    <td><input type="button" id="turnar" class="btn_default"
                               value="Turnar" title="Turnar" /></td>
                    </logic:present>

                <logic:present name="showFinalizarBtn">
                    <td><input type="button" id="finalizar" class="btn_default"
                               value="Finalizar" title="Finalizar" /></td>
                    </logic:present>
                    <logic:present name="showRechazarBtn">
                    <td><input type="button" id="rechazar" class="btn_default"
                               value="En Trámite" title="En Trámite" /></td>
                    </logic:present>

                <td align="center">
                    <input type="button" id="regresar"  class="btn_default regresar" value="Regresar" title="Regresar" />
                </td>
            </tr>
        </tbody>
    </table>

</html:form>



