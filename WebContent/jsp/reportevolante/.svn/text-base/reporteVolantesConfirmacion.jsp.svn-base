<%--
    Document   : reporteVolantesConfirmacion
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
                <table>
                    <tr>
                        <td colspan="3" class="td_concepto_izq_peq">
                            ¿Est&aacute; seguro de generar los archivos con el siguiente criterio?:
                        </td>
                    </tr>
                </table>
                <table border="0"  class="captura ui-widget ui-widget-content">

                    <thead>
                        <tr class="ui-widget-header ">
                            <th class="titTablaPeq">Tema</th>
                            <th class="titTablaPeq">Expediente</th>
                            <th class="titTablaPeq">&Aacute;rea remitente</th>

                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <bean:write name="reporteVolanteForm" property="tema.tema" />
                            </td>
                            <td>
                                <bean:write name="reporteVolanteForm" property="expediente.expediente" />
                            </td>
                            <td>
                                <bean:write name="reporteVolanteForm" property="criterioReporte.area" />
                            </td>
                        </tr>
                    </tbody>
                    <thead>
                        <tr class="ui-widget-header ">                            
                            <th colspan="3" class="titTablaPeq">Fecha del documento</th>

                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td class="RowsDesc" colspan="3"> 

                                Del
                                <bean:write name="reporteVolanteForm" property="criterioReporte.fh_registroIniDDMMYYYY" />
                                Al
                                <bean:write name="reporteVolanteForm" property="criterioReporte.fh_registroFinDDMMYYYY" />
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
                            <td colspan="3"><br/><br/>Los resultados se harán llegar a su correo electrónico una vez que se hayan generado los archivos</td>
                        </tr>
                        <tr>
                            <td colspan="3">
                                <input type="button" class="btn_default mouseOver" value="Regresar" id="reporteVolanteFormRegresar" title="Regresar"/>
                                <input type="button" class="btn_default mouseOver" value="Generar Archivos" id="reporteVolanteFormGenerar" title="Generar X Archivos"/>
                            </td>
                        </tr>
                    </tbody>                    
                </table>
            </html:form>
        </div> <!-- contentform -->
    </div> <!-- inner border -->
</div> <!-- content border -->
