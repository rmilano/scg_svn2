<%--
    Document   : reporteGeneralDetalle
    Created on : 5/04/2011, 10:03:38 AM
    Author     : javier
--%>

<%@ page import="mx.gob.economia.scg.util.Constantes"%>
<%@ include file="/jsp/taglibs.jsp"%>
<%@ include file="/jsp/arbol.jsp"%>

<link href='<html:rewrite page="/jsp/reportes/css/reportes.css"/>' rel="stylesheet" type="text/css" />
<link href='<html:rewrite page="/jsp/css/style.css"/>' rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<html:rewrite page="/jsp/js/jquery.paginate.js"/>"></script>
<script type="text/javascript" src="<html:rewrite page="/jsp/reportes/js/reportes.js"/>"></script>

<div class="content border">
    <div class="inner corner">
        <div class="contentform reporte">

            <html:form action="/reportes.do" styleId="reporteForm" method="Post">
                <html:hidden property="method"  value="" styleId="name-post-method" />
                <html:hidden property="criterioReporte.id_area_subconsulta"  styleId="idareasubconsulta" /> 
                <html:hidden name="reporteForm" styleId="nuevaconsulta" property="nuevaConsultaDetalle"/>
                <html:hidden name="reporteForm" styleId="pagina" property="criterioReporte.paginador.pagina"/>
                <html:hidden name="reporteForm" styleId="paginas" property="criterioReporte.paginador.paginas"/>                
                <table class="captura ui-widget ui-widget-content">

                    <thead>
                        <tr class="ui-widget-header ">
                            <th >Tema</th>
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
                            <th colspan="2"><span class="campoRequerido">*</span>&Aacute;rea remitente</th>
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
                                    <html:hidden name="reporteForm" property="criterioReporte.id_area"
                                                 styleClass="id" />                                                                       
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
                            <th colspan="2" ><span class="campoRequerido">*</span>Fecha del documento</th>
                            <th >Estatus</th>                            
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td class="RowsDesc"> 
                                Del
                                <html:text name="reporteForm" property="criterioReporte.fh_registroIniDDMMYYYY"
                                           readonly="true" size="8" styleClass="calendario required" />
                            </td>
                            <td class="RowsDesc">
                                Al
                                <html:text name="reporteForm" property="criterioReporte.fh_registroFinDDMMYYYY"
                                           readonly="true" size="8" styleClass="calendario required" />
                            </td>
                            <td class="RowsDesc">
                                <html:select name="reporteForm" property="criterioReporte.id_estatus">
                                    <html:optionsCollection name="reporteForm" property="estatusList" label="label" value="value" />
                                </html:select>
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
                                <input type="button" class="btn_default mouseOver" value="Generar Reporte" id="reporteFormDetalle" title="Generar reporte"/>
                                <input type="button" class="btn_default" id="consulta-limpiar-detalle" value="Limpiar Formulario"
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

                <table class="captura ui-widget ui-widget-content">

                    <thead>
                        <tr class="ui-widget-header ">
                            <th >Tema</th>
                            <th >Expediente</th>
                            <th >&Aacute;rea remitente</th>

                        </tr>
                    </thead>
                    <tbody>
                        <tr class="RowsDesc">
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
                            <th>Peri&oacute;do</th>
                            <th>Estatus</th>
                            <th>Total</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr class="RowsDesc">
                            <td>
                                Del &nbsp;<bean:write name="reporteForm" property="criterioReporte.fh_registroIniDDMMYYYY"/>
                                &nbsp; Al
                                <bean:write name="reporteForm" property="criterioReporte.fh_registroFinDDMMYYYY"/>
                            </td>
                            <td>
                                <bean:write name="reporteForm" property="criterioReporte.estatus"/>
                            </td>
                            <td>
                                <bean:write name="reporteForm" property="criterioReporte.paginador.numRegistros"/>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <table class="captura ui-widget ui-widget-content">
                    <thead>
                        <tr >
                            <th>No.</th>
                            <th colspan="3">Empleado Remitente</th>
                            <th colspan="2">Folio</th>
                            <th colspan="2">Documento</th>
                            <th colspan="4">Estatus</th>
                            <th colspan="1">Empleado Destinatario</th>
                        </tr>
                    </thead>
                    <tbody>
                        <logic:iterate id="registro"  name="reporteForm" property="reporteAsuntos.detalleAsunto" type="mx.gob.economia.scg.model.Reporte">
                            <tr class="RowsDesc">
                                <td><bean:write name="registro" property="rownum"/></td>
                                <td colspan="3"><bean:write name="registro" property="empleado.nombre_completo"/></td>
                                <td colspan="2"><bean:write name="registro" property="folio"/></td>
                                <td colspan="2"><bean:write name="registro" property="noOficio"/></td>
                                <td colspan="4"><bean:write name="registro" property="estatus_desc"/></td>
                                <td colspan="1"><bean:write name="registro" property="destinatario.nombre_completo"/></td>
                            </tr>
                        </logic:iterate>
                    </tbody>
                    <thead>
                        <tr class="ui-widget-header">
                            <th colspan="13"></th>
                        </tr>
                    </thead>   
                    <tbody>
                        <tr>
                            <td colspan="13">
                                <div id="paginador"></div> 
                            </td>
                        </tr>
                    </tbody>
                    <thead>
                        <tr class="ui-widget-header">
                            <th colspan="13"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td colspan="6">
                                <label id="asunto-nuevo">
                                    <a title="Pdf" class="btn_default_sl icon-pdf-export"  href='<html:rewrite page="/reportes.do?method=reporteGeneralDetallePdf" />'>Pdf</a>
                                </label>
                            </td>
                            <td colspan="7">
                                <!--                                <label id="asunto-nuevo">
                                                                    <a title="Excel" class="btn_default icon-xls-export"  href='<html:rewrite page="/reportes.do?method=reporteGeneralDetalleXls" />'> Excel
                                                                    </a>
                                                                </label>-->
                            </td>
                        </tr>

                </table>
            </div><!-- contentform -->
        </div><!-- inner corner -->
    </div><!-- content border -->

</logic:notEmpty>

<logic:present name="FOUND" >
    <label class="message-nofound">No se encontraron resultados en la b&uacute;squeda</label>            
</logic:present>

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

