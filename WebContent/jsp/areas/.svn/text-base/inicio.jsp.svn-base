<%-- 
    Document   : inicio
    Created on : 28-oct-2010, 18:32:34
    Author     : gerardo
--%>
<%@ include file="/jsp/arbol.jsp"%>
<script type="text/javascript" src="<html:rewrite page="/jsp/areas/js/arbol.js"/>"></script>
<!--Paginas demos para el componente de arboles-->
<div>
    <html:form action="/areas.do" styleId="areaForm">
        <div id="arbolA">
            <input type="text" name="" disabled value="" class="tree icon-tree-trigger" />
            <html:hidden name="areaForm" styleClass="id"  value="" property="area.id_area" />            
            <html:select property="empleado.id_empleado" styleClass="tree" name="areaForm" >
                <html:optionsCollection name="areaForm" property="empleados" value="id_empleado" label="nombre" />
            </html:select>
        </div>

        <div id="arbolB">
            <input type="text" name="" disabled value="" class="tree" />
            <html:hidden name="areaForm" styleClass="id"  value="" property="area.id_area" />
            <html:select property="empleado.id_empleado" styleClass="tree" name="areaForm" >
                <html:optionsCollection name="areaForm" property="empleados" value="id_empleado" label="nombre" />
            </html:select>
        </div>
        <div id="ramaA" title="dialog-rama">
            <input type="text" name="" disabled value="" class="tree" />
            <html:hidden name="areaForm" styleClass="id"  value="" property="area.id_area" />
            <html:select property="empleado.id_empleado" styleClass="tree" name="areaForm" >
                <html:optionsCollection name="areaForm" property="empleados" value="id_empleado" label="nombre" />
            </html:select>
        </div>

    </html:form>
</div>
<div class="modal">
    <div class="treeviewContent">
        <ul id="tree">
            ${arbol_raiz}
        </ul>
    </div>
</div>

<div class="modal-rama">
    <div class="treeviewContent">
        <ul class="tree-branch" >
            ${arbol_rama}
        </ul>
    </div>
</div>
