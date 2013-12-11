<%@ include file="/jsp/taglibs.jsp"%>
<script type="text/javascript" src="<html:rewrite page="/jsp/captura/js/firma.js"/>"></script>
<html:form action="/captura.do" styleId="capturaForm"  enctype="multipart/form-data">

	<html:hidden name="capturaForm" property="firma.firmaAsunto.firmante" styleId="firmante" styleClass="required" />
	<html:hidden name="capturaForm" property="firma.firmaAsunto.firma" styleId="firma" styleClass="required" />

	<span style="font-family: Tahoma; text-align: center">
        <table width="900">
		<tbody>
			<tr>
				<td class="center"><applet code="Applet.FLogin"
					archive="../../CApplet.jar" width=100% height=500 name="firma">
					<param name=CODE value="Applet.FLogin">
					<param name=ARCHIVE value="../../CApplet.jar,../../TUtil.jar">
					<param name="type" value="application/x-java-applet;version=1.5">
					<param name="scriptable" value="false">
					<param name="Login" value="wsuser"></param>
					<param name="Password" value="12121212"></param>
					<param name="Chaing" value='<bean:write name="capturaForm" property="firma.cadena"/>'></param>
					<param name="Files" value='<bean:write name="capturaForm" property="firma.archivos"/>'></param>
				</applet></td>
			</tr>
			<tr>
				<td class="center">
					Una vez procesados los archivos de clic en firmar <input type="button" id="firmaBtn" class="btn_default" value="Firmar" title="Firmar" />
				</td>
			</tr>
		</tbody>
	</table>
    </span>
</html:form>    