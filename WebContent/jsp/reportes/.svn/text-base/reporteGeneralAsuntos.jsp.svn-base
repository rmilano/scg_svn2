<%--
    Document   : reporteGeneralAsuntos
    Created on : 9/11/2010, 10:03:38 AM
    Author     : gerardo
--%>

<%@ page import="mx.gob.economia.scg.util.Constantes"%>
<%@ include file="/jsp/taglibs.jsp"%>
<%@ include file="/jsp/arbol.jsp"%>

<link href='<html:rewrite page="/jsp/reportes/css/reportes.css"/>' rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<html:rewrite page="/jsp/js/jquery.paginate.js"/>"></script>
<script type="text/javascript" src="<html:rewrite page="/jsp/reportes/js/reportes.js"/>"></script>

<div class="content border">
    <div class="inner corner">
        <div class="contentform reporte">

            <html:form action="/reportes.do" styleId="reporteForm" method="Post">
                <html:hidden property="method"  value="" styleId="name-post-method" />
                <html:hidden property="criterioReporte.id_estatus"  styleId="idestatus" />                
                <html:hidden property="criterioReporte.id_area_subconsulta"  styleId="idareasubconsulta" /> 
                <html:hidden property="nuevaConsultaDetalle"  value="" /> 

                <table class="captura ui-widget ui-widget-content texto-igual">

                    <thead>
                        <tr class="ui-widget-header ">
                            <th>Tema</th>
                            <th colspan="2">Expediente</th>                           
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <html:select name="reporteForm" property="criterioReporte.id_tema" styleId="select-temas" styleClass="required ui-widget-content">
                                    <html:option value="-1">Seleccione una opci&oacute;n</html:option>
                                    <html:optionsCollection name="reporteForm" property="temas" value="id_tema" label="tema" />
                                </html:select>
                                <html:hidden name="reporteForm" property="criterioReporte.tema" />
                            </td>
                            <td colspan="2">
                                <html:select name="reporteForm" property="criterioReporte.id_expediente" styleId="select-expedientes" styleClass="required ui-widget-content">
                                    <html:option value="-1">Seleccione una opci&oacute;n</html:option>
                                    <html:optionsCollection name="reporteForm" property="expedientes" value="id_expediente" label="expediente" />
                                </html:select>
                                <html:hidden name="reporteForm" property="criterioReporte.expediente" />
                            </td>
                        </tr>
                    </tbody>
                    <thead>
                        <tr class="ui-widget-header ">
                            <th>Tipo &aacute;rea</th>                            
                            <th colspan="2"><span class="campoRequerido"> * </span>&Aacute;rea remitente</th>

                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                Interno:<html:radio name="reporteForm" property="criterioReporte.area_tipo"
                                            styleClass="radio-arbol-interno" title="arbol-reporte-general" alt="modal" value ="0" />
                                Externo:<html:radio name="reporteForm" property="criterioReporte.area_tipo"
                                            styleClass="radio-arbol-externo" title="arbol-reporte-general" alt="modal-arbol-externo"  value ="1" />
                            </td>
                            <td colspan="2">
                                <div id="arbol-reporte-general" title="modal">
                                    <html:text name="reporteForm" property="criterioReporte.area" styleClass="tree required element-search icon-tree-trigger" />
                                    <html:hidden name="reporteForm" property="criterioReporte.id_area" styleClass="id" />                                                                       
                                </div>
                            </td>

                        </tr>
                    </tbody>

                    <thead>
                        <tr class="ui-widget-header ">
                            <th>Tipo &aacute;rea</th>
                            <th colspan="2">&Aacute;rea destinatario</th>

                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                Interno:<html:radio name="reporteForm" property="criterioReporte.area_tipo_dest"
                                            styleClass="radio-arbol-interno" title="arbol-reporte-general-dest" alt="modal" value ="0" />
                                Externo:<html:radio name="reporteForm" property="criterioReporte.area_tipo_dest"
                                            styleClass="radio-arbol-externo" title="arbol-reporte-general-dest" alt="modal-arbol-externo"  value ="1" />
                            </td>
                            <td colspan="2">
                                <div id="arbol-reporte-general-dest" title="modal">
                                    <html:text name="reporteForm" property="criterioReporte.area_dest" styleClass="tree element-search icon-tree-trigger" />
                                    <html:hidden name="reporteForm" property="criterioReporte.id_area_dest" styleClass="id" />
                                </div>
                            </td>

                        </tr>
                    </tbody>

                    <thead>
                        <tr class="ui-widget-header ">                            
                            <th colspan="3" ><span class="campoRequerido">*</span>Fecha del documento:</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td colspan="3"> 
                                Del
                                <html:text name="reporteForm" property="criterioReporte.fh_registroIniDDMMYYYY"
                                           readonly="true" size="8" styleClass="calendario required" />
                                Al <html:text name="reporteForm" property="criterioReporte.fh_registroFinDDMMYYYY"
                                           readonly="true" size="8" styleClass="calendario required" />
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
                                <input type="button" class="btn_default mouseOver" value="Generar Reporte" id="reporteFormGeneral" title="Generar reporte"/>
                                <input type="button" class="btn_default" id="consulta-limpiar" value="Limpiar Formulario"
                                       title="Limpiar Formulario" onmouseover="style.cursor='pointer'" />
                            </td>
                        </tr>
                    </tbody>

                </table>

            </html:form>

        </div><!-- contentform -->
    </div><!-- inner corner -->
</div><!-- content border -->


<!-- INICIA TABLA DE RESULTADOS -->
<logic:notEmpty name="reporteForm" property="reporteAsuntos.detalleAsunto">

    <div class="content border">
        <div class="inner corner">
            <div class="contentform reporte">

                <table class="captura result ui-widget ui-widget-content texto-igual">

                    <thead>
                        <tr class="ui-widget-header ">
                            <th >Tema</th>
                            <th >Expediente</th>
                            <th >&Aacute;rea remitente</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr >
                            <td>
                                <logic:empty name="reporteForm" property="criterioReporte.tema"> 
                                	Tema no seleccionado
                                </logic:empty>
                                <bean:write name="reporteForm" property="criterioReporte.tema"/>
                            </td>
                            <td>
                                <logic:empty name="reporteForm" property="criterioReporte.tema"> 
                                	Expediente no seleccionado
                                </logic:empty>
                                <bean:write name="reporteForm" property="criterioReporte.expediente"/>
                            </td>
                            <td>
                                <bean:write name="reporteForm" property="criterioReporte.area"/>
                            </td>
                        </tr>
                    </tbody>
                    <thead>
                        <tr class="ui-widget-header ">
                            <th colspan="3" >Periodo</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr >
                            <td colspan="4">
                                <bean:write name="reporteForm" property="criterioReporte.fh_registroIniDDMMYYYY"/>
                                &emsp;&emsp;&emsp;&emsp;
                                <bean:write name="reporteForm" property="criterioReporte.fh_registroFinDDMMYYYY"/>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <table class="captura result ui-widget ui-widget-content texto-igual">
                    <thead>
                        <tr >
                            <th>&Aacute;rea</th>
                            <th>En Trámite</th>
                            <th>Pendientes</th>
                            <th>Atendidos</th>
                            <th>Concluidos</th>
                            <th>Total</th>
                        </tr>
                    </thead>
                    <logic:equal name="isReporteDetalle" value="true">
                        <logic:iterate id="registro"  name="reporteForm" property="reporteAsuntos.detalleAsunto" type="mx.gob.economia.scg.model.Reporte">
                            <tbody>
                                <tr >
                                    <td class=""><bean:write name="registro" property="criterio.area"/></td>
                                    <td><a class="reporte-status" type="0" itemprop="<bean:write name="registro" property="criterio.id_area"/>"><bean:write name="registro" property="pendiente"/></a></td>
                                    <td><a class="reporte-status" type="1" itemprop="<bean:write name="registro" property="criterio.id_area"/>"><bean:write name="registro" property="turnado"/></a></td>
                                    <td><a class="reporte-status" type="2" itemprop="<bean:write name="registro" property="criterio.id_area"/>"><bean:write name="registro" property="atendido"/></a></td>
                                    <td><a class="reporte-status" type="3" itemprop="<bean:write name="registro" property="criterio.id_area"/>"><bean:write name="registro" property="finalizado"/></a></td>
                                    <td><a class="reporte-status" type="-1" itemprop="<bean:write name="registro" property="criterio.id_area"/>"><bean:write name="registro" property="total"/></a></td>
                                </tr>
                            </tbody>
                        </logic:iterate>                
                    </logic:equal>                           
                    <logic:notEqual name="isReporteDetalle" value="true">
                        <logic:iterate id="registro"  name="reporteForm" property="reporteAsuntos.detalleAsunto" type="mx.gob.economia.scg.model.Reporte">
                            <tbody>
                                <tr >
                                    <td class=""><bean:write name="registro" property="criterio.area"/></td>
                                    <td><bean:write name="registro" property="pendiente"/></td>
                                    <td><bean:write name="registro" property="turnado"/></td>
                                    <td><bean:write name="registro" property="atendido"/></td>
                                    <td><bean:write name="registro" property="finalizado"/></td>
                                    <td><bean:write name="registro" property="total"/></td>
                                </tr>
                            </tbody>          
                        </logic:iterate>  
                    </logic:notEqual>
                    <thead>
                        <tr class="ui-widget-header">
                            <th colspan="10"></th>
                            <th colspan="3">Total: <bean:write name="reporteForm" property="reporteAsuntos.total" /> </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td colspan="6">
                                <label id="asunto-nuevo">
                                    <a title="Pdf" class="btn_default_sl icon-pdf-export"  href='<html:rewrite page="/reportes.do?method=reporteGeneralAsuntosPdf" />'>Pdf</a>
                                </label>
                            </td>
                            <td colspan="7">
                                <label id="asunto-nuevo">
                                    <a title="Excel" class="btn_default icon-xls-export"  href='<html:rewrite page="/reportes.do?method=reporteGeneralAsuntosXls" />'> Excel
                                    </a>
                                </label>
                            </td>
                        </tr>
                    </tbody>
                </table>

            </div><!-- contentform -->
        </div><!-- inner corner -->
    </div><!-- content border -->

</logic:notEmpty>


<logic:equal name="usuario_sesion" property="area.listado_area" value="0">
    <div class="modal" title="&Aacute;reas internas">
        <div class="treeviewContent">
            <ul id="tree">
                ${sessionScope.arbol_rama}
            </ul>
        </div>
    </div>
</logic:equal>        

<logic:equal name="usuario_sesion" property="area.listado_area" value="1">
    <div class="modal" title="&Aacute;reas internas">
        <div class="treeviewContent">
            <ul id="tree">
                ${sessionScope.arbol_raiz}
            </ul>
        </div>
    </div>
</logic:equal>        
    
<div class="modal-arbol-externo" title="&Aacute;reas externas">
    <div class="treeviewContent">
        <ul class="tree-branch">
            ${arbol_externo}
        </ul>
    </div>
</div>

