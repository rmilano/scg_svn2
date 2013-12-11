<%--
    Document   : reporteGeneralDetalle
    Created on : 5/04/2011, 10:03:38 AM
    Author     : javier
--%>

<%@ page import="mx.gob.economia.scg.util.Constantes"%>
<%@ include file="/jsp/taglibs.jsp"%>
<%@ include file="/jsp/arbol.jsp"%>

<link href='<html:rewrite page="/jsp/reportevolante/css/reportes.css"/>' rel="stylesheet" type="text/css" />
<link href='<html:rewrite page="/jsp/css/style.css"/>' rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<html:rewrite page="/jsp/reportevolante/js/reportevolante.js"/>"></script>

<div class="content border">
    <div class="inner corner">
        <div class="contentform reporte">

            <html:form action="/reportevolantes.do" styleId="reporteVolanteForm" method="Post">
                <html:hidden property="method"  value="" styleId="name-post-method" />
                <table class="captura ui-widget ui-widget-content">

                    <thead>
                        <tr class="ui-widget-header ">
                            <th >Tema</th>
                            <th colspan="2"><span class="campoRequerido">*</span>Fecha del documento</th>
                        </tr>
                    </thead>

                    <tbody>
                        <tr>
                            <td>
                                <html:select name="reporteVolanteForm" property="criterioReporte.id_tema" styleId="select-temas" styleClass="required ui-widget-content">
                                    <html:option value="-1">Seleccione una opci&oacute;n</html:option>
                                    <html:optionsCollection name="reporteVolanteForm" property="temas" value="id_tema" label="tema" />
                                </html:select>
                                <html:hidden name="reporteVolanteForm" property="criterioReporte.tema" />
                            </td>
                            <td colspan="2" > 
                                Del
                                <html:text name="reporteVolanteForm" property="criterioReporte.fh_registroIniDDMMYYYY"
                                           readonly="true" size="8" styleClass="12m-restricted-calendar required" />
                                Al
                                <html:text name="reporteVolanteForm" property="criterioReporte.fh_registroFinDDMMYYYY"
                                           readonly="true" size="8" styleClass="12m-restricted-calendar required" />
                            </td>          
                        </tr>
                    </tbody>
                    <thead>
                        <tr class="ui-widget-header ">                            
                            <th>Expediente</th>      
                            <th colspan="2"><span class="campoRequerido">*</span>&Aacute;rea remitente</th>
                        </tr>
                    </thead>

                    <tbody>
                        <tr>
                            <td >
                                <html:select name="reporteVolanteForm" property="criterioReporte.id_expediente" styleId="select-temas" styleClass="required ui-widget-content">
                                    <html:option value="-1">Seleccione una opci&oacute;n</html:option>
                                    <html:optionsCollection name="reporteVolanteForm" property="expedientes" value="id_expediente" label="expediente" />
                                </html:select>
                                <html:hidden name="reporteVolanteForm" property="criterioReporte.expediente" />
                            </td>                                                        
                            <td colspan="2">
                                <div id="arbolA" title="modal-rama">
                                    <html:text name="reporteVolanteForm" property="criterioReporte.area" styleClass="tree icon-tree-trigger required" size="45" />
                                    <html:hidden name="reporteVolanteForm" property="criterioReporte.id_area" styleClass="id" />
                                </div>
                            </td>                                                
                        </tr>
                    </tbody>                  

                    <thead>
                        <tr class="ui-widget-header ">
                            <th colspan="3"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td colspan="3">
                                <input type="button" class="btn_default mouseOver" value="Generar Archivos" id="reporteVolanteFormDetalle" title="Generar Archivos"/>
                            </td>
                        </tr>
                    </tbody>

                </table>

            </html:form>

        </div><!-- contentform -->
    </div><!-- inner corner -->
</div><!-- content border -->

<logic:equal name="usuario_sesion" property="area.listado_area" value="0">
    <div class="modal-rama" title="Subareas internas">
        <div class="treeviewContent">
            <ul class="tree-branch">
                ${arbol_rama}
            </ul>
        </div>
    </div>
</logic:equal>

<logic:equal name="usuario_sesion" property="area.listado_area" value="1">
    <div class="modal-rama" title="Subáreas internas">
        <div class="treeviewContent">
            <ul class="tree-branch">
                ${arbol_raiz}
            </ul>
        </div>
    </div>
</logic:equal>
