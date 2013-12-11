<%-- 
    Document   : concentrado
    Created on : 28-ene-2011, 12:25:26
    Author     : gerardo
--%>

<%@ include file="/jsp/arbol.jsp"%>
<%@ page import="mx.gob.economia.scg.util.Constantes"%>
<link href='<html:rewrite page="/jsp/reportes/css/reportes.css"/>' rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<html:rewrite page="/jsp/reportes/js/reporteFechas.js"/>"></script>


<div class="content border">
    <div class="inner corner">
        <div class="contentform">
            <html:form action="/reportes.do?method=reporteGeneralAsuntos" styleId="reporteForm" enctype="multipart/form-data">
                <html:hidden property="method" value="" />
                <table class="criterio-consulta ui-widget ui-widget-content">
                    <thead>
                        <tr class="ui-widget-header ">
                            <th>Fecha Iniciall:</th>
                            <th>Fecha final:</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <input type="text" class="calendario" />                                
                            </td>
                            <td>
                                <input type="text" class="calendario" />
                            </td>
                            <td>
                                <input type="button"  class="btn_default" value="Buscar" />
                            </td>
                        </tr>
                    </tbody>
                </table>

            </html:form>                
        </div>
    </div>
</div>
<div class="content border">
    <div class="inner corner">
        <div class="contentform">
            <table class="criterio-consulta ui-widget ui-widget-content">
                <thead>
                    <tr class="ui-widget-header ">
                        <th colspan="4" style="text-align: center;">ASUNTOS PENDIENTES DEL AREA DE DESARROLLO INFORMATICO</th>
                    </tr>
                </thead>

                                <thead>
                    <tr class="ui-widget-header ">
                        <th>T&iacute;tulo</th>
                        <th>Area remitente</th>
                        <th>Area destinataria</th>
                        <th>Fecha de creacion</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>
                            ASUNTO 1
                        </td>
                        <td>
                            DGI
                        </td>
                        <td>
                            SEGURIDAD LOGICA
                        </td>
                        <td>
                            12/01/2010
                        </td>
                    </tr>
                    <tr>
                        <td>
                            ASUNTO 2
                        </td>
                        <td>
                            DGI
                        </td>
                        <td>
                            SEGURIDAD LOGICA
                        </td>
                        <td>
                            12/01/2010
                        </td>
                    </tr>
                    <tr>
                        <td>
                            ASUNTO 3
                        </td>
                        <td>
                            DGI
                        </td>
                        <td>
                            SEGURIDAD LOGICA
                        </td>
                        <td>
                            12/01/2010
                        </td>
                    </tr>

                </tbody>
                <thead>
                    <tr class="ui-widget-header ">
                        <th></th>
                        <th></th>
                        <th></th>
                        <th>
                            <input type="button" class="icon-pdf-export" value="Exportar a pdf" />
                        </th>
                    </tr>
                </thead>



            </table>

        </div>
    </div>
</div>