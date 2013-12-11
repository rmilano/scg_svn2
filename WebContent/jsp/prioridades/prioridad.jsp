<!--Catalago de prioridades-->

<%@ include file="/jsp/taglibs.jsp"%>

<link href='<html:rewrite page="/jsp/prioridades/css/prioridad.css"/>' rel="stylesheet" type="text/css" />
<link href='<html:rewrite page="/jsp/css/colorpicker.css"/>' rel="stylesheet" type="text/css" />
<link href='<html:rewrite page="/jsp/css/layout.css"/>' rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<html:rewrite page="/jsp/prioridades/js/prioridad.js"/>"></script>

<html:form styleId="prioridadForm" action="/prioridad.do">
    <div class="content border">
        <div class="inner corner">
            <div class="contentform">
                <span class="td_concepto_izq_gde">
			Cat&aacute;logo de Prioridades
                </span>
                <input type="button" onclick="nuevo();" class="btn_default" value="Nueva Prioridad" />
                <logic:notEmpty name="prioridadForm" property="prioridades">
                    <dt:table id="prio" pagesize="5"
                              name="sessionScope.prioridadForm.prioridades"
                              requestURI="/prioridad.do?method=inicio&order=true"
                              requestURIcontext="true" excludedParams="*" sort="list"
                              defaultsort="1">
                        <dt:column property="id_prioridad" title="Id" sortable="true" />
                        <dt:column property="prioridad" title="Prioridad" sortable="true" />
                        <dt:column property="dias" title="D&iacute;as" sortable="true" />
                        <logic:notEqual name="prio" property="id_prioridad" value="5">
                            <dt:column title="Editar">
                                <input type="button" value="Editar" class="btn_default"
                                       onclick="traerDatos('<bean:write name="prio" property="id_prioridad"/>');" />
                            </dt:column>
                            <dt:column title="Eliminar">
                                <input type="button" value="Eliminar" class="btn_default"
                                       onclick="eliminar('<bean:write name="prio" property="id_prioridad"/>');" />
                            </dt:column>
                        </logic:notEqual>
                        <logic:equal name="prio" property="id_prioridad" value="5">
                            <dt:column title="Editar">
                                &nbsp;
                            </dt:column>
                            <dt:column title="Eliminar">
                                &nbsp;
                            </dt:column>
                        </logic:equal>
                    </dt:table>
                </logic:notEmpty>
                <br/>
                <br/>
            </div>
        </div>
    </div>
    
    <div class="content border" style="display: none;" id="add">
        <div class="inner corner">
            <div class="contentform">
                <html:hidden property="method" value="" />
                <html:hidden name="prioridadForm" property="prioridad.activo" styleId="activo" value="1" />
                <html:hidden name="prioridadForm" property="prioridad.id_prioridad" styleId="id_prioridad" />
                <span class="td_concepto_izq_gde">Escriba los datos correctamente</span>

                <table>
                    <thead>
                        <tr class="ui-widget-header ">
                            <th><span class="campoRequerido"> * </span>Prioridad:</th>
                            <th><span class="campoRequerido"> * </span>Días:</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><html:text property="prioridad.prioridad" name="prioridadForm" styleId="prioridad" maxlength="15" styleClass="required upperCase" /></td>
                            <td><html:text property="prioridad.dias" name="prioridadForm" styleId="dias" styleClass="required number only-numeric upperCase" /></td>
                        </tr>
                    </tbody>
                    <tbody>
                        <tr>
                            <td></td>
                            <td colspan="2">
                                <input class="btn_default" type="button" value="Guardar"  onclick="definirAccion();" />
                                <input class="btn_default" type="button" value="Cancelar" onclick="cancelar();" />
                            </td>
                        </tr>
                    </tbody>
                </table>

            </div>
        </div>
    </div>
</html:form>