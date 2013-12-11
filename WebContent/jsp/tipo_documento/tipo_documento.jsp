<!--Catalogo de tipo de documentos: altas bajas y cambios-->

<%@ include file="/jsp/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link href='<html:rewrite page="/jsp/tipo_documento/css/tipo_documento.css"/>' rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<html:rewrite page="/jsp/tipo_documento/js/tipo_documento.js"/>"></script>

<html:form styleId="tipoDocumentoForm" action="/tipo-documento.do" method="Post">
    
<div class="content border">
    <div class="inner corner">
        <div class="contentform">
            
            <span class="td_concepto_izq_gde">Cat&aacute;logo de Tipos de Documento </span>
            <input type="button" onclick="nuevo();" class="btn_default" value="Nuevo Tipo de Documento"/>
            <logic:notEmpty name="tipoDocumentoForm" property="tiposDocumento">
                <dt:table id="TiposDocumento" pagesize="5"
                          name="sessionScope.tipoDocumentoForm.tiposDocumento"
                          requestURI="/tipo-documento.do?method=init&order=true" requestURIcontext="true"
						excludedParams="*" sort="list" defaultsort="1">
                    <dt:column property="id_tipo_documento" title="Clave del Tipo" sortable="true"/>
                    <dt:column property="tipo_documento" title="Tipo de Documento" sortable="true"/>
                    <dt:column property="firma" title="Firma" sortable="true"/>
                    <dt:column title="Editar">
                        <input type="button" value="Editar" class="btn_default"
                                   onclick="traerDatos('<bean:write name="TiposDocumento"
                                               property="id_tipo_documento"/>');"/>
                    </dt:column>
                        <dt:column title="Eliminar">
                            <input type="button" value="Eliminar" class="btn_default" 
                                   onclick="eliminarTipo('<bean:write name="TiposDocumento"
                                               property="id_tipo_documento"/>');"/>
                        </dt:column>
                    </dt:table>
                </logic:notEmpty>
            <br><br>
        </div>
    </div>
</div>
<div class="content border" style="display:none;" id="add">
    <div class="inner corner">
        <div class="contentform">
            
            <html:hidden property="method" value="" />
            <html:hidden name="tipoDocumentoForm" property="tipoDocumento.activo" styleId="activo" value="1"/>
            <html:hidden name="tipoDocumentoForm" property="tipoDocumento.id_tipo_documento" styleId="id_tipo_documento"/>
            <span class="td_concepto_izq_gde">Escriba los datos correctamente</span><br><br>
            <span class="td_concepto_izq_peq"><span class="campoRequerido">*</span>Tipo de Documento:</span>
            <html:text property="tipoDocumento.tipo_documento" name="tipoDocumentoForm" styleId="tipo_documento" styleClass="required upperCase"/>
            <span class="td_concepto_izq_peq"><span class="campoRequerido"> * </span>Firma:</span>
            <html:select property="tipoDocumento.firma" name="tipoDocumentoForm" styleId="firma" styleClass="required">
                <html:option value="0">No requerida</html:option>
                <html:option value="1">Requerida</html:option>
            </html:select>
             <input class="btn_default" type="button" value="Guardar" onclick="definirAccion();"/>
             <input class="btn_default" type="button" value="Cancelar" onclick="cancelar();"/>
        </div>
    </div>
</div>
</html:form>