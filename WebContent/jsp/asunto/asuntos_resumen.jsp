<%@ include file="/jsp/taglibs.jsp"%>
<link href='<html:rewrite page="/jsp/asunto/css/asuntos_area.css"/>' rel="stylesheet" type="text/css" />
<div class="content border">
    <div class="inner corner">
        <div class="contentform">
            <table>
                <tbody>
                    <tr>
                        <td>
                            <logic:present name="usuario_sesion">
                                <hr class="hr_subtitulo">
                            </logic:present>

                            <div class="corner">
                                <table>
                                    <logic:iterate id="resumen" name="ConsultaResumenForm" property="resumen_asuntos" scope="Session" type="java.lang.String">
                                        <tr>
                                            <td align="right">
                                                <bean:write name="resumen"/><br>
                                            </td>
                                        </tr>
                                    </logic:iterate>
                                </table>
                            </div>
                            <hr class="hr_subtitulo">
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
<br>
