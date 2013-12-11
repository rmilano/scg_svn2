<%@ include file="/jsp/arbol.jsp"%>


<div class="content border">
    <div class="inner corner">
        <div class="contentform captura-asunto">
            <html:form action="/estadisticas.do" styleId="estadisticaForm">
                <html:hidden property="method" value="" />                
                <table class="captura ui-widget ui-widget-content">

                    <thead>
                        <tr class="ui-widget-header ">
                            <th>Folio:</th>
                            <th ><span class="campoRequerido"> * </span>Fecha de registro:</th>
                            <th>Fecha de recepci&oacute;n:</th>

                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <html:select name="estadisticaForm" property="tema.id_tema" styleClass="ui-widget-content">
                                    <html:option value="-1">Seleccione una opci&oacute;n</html:option>
                                    <html:optionsCollection name="temas" value="id_tema" label="tema" />
                                </html:select>

                            </td>
                            <td></td>
                            <td></td>
                        </tr>
                    </tbody>
                </table>
            </html:form>
        </div>
    </div>
</div>