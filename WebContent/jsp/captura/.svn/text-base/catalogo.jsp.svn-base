<!-- Formulario para registrar una nueva area -->
<div id="dialog-form-area" class="captura-catalog-dialog" title="Registrar &Aacute;rea">
    <form action="" name="formulario-areas" id="catalog-area-form">
        <fieldset>
            <label for="tipo">Tipo:</label>
            <h3 class="area-tipo">
                <!--                Interno:<input type="radio" name="tipo" value="0" class="radio radio-arbol-interno" title="arbol-catalogo-areas" alt="modal" checked>-->
                Externo:<input type="radio" name="tipo" value="1" class="radio radio-arbol-externo" title="arbol-catalogo-areas" alt="modal-arbol-externo" checked>
            </h3>
            <label for="dependencia">Dependencia inmediata superior:</label>
            <div id="arbol-catalogo-areas" title="modal-arbol-externo">
                <input type="text" name="area"  class="tree text required ui-widget-content ui-corner-all icon-tree-trigger">
                <input type="hidden" name="id" class="id">
            </div>                        
            <label for="descripcion">Nombre del &Aacute;rea:</label>
            <input type="text" name="descripcion" id="descripcion" value="" class="required text upperCase block ui-widget-content ui-corner-all" maxlength="250"/>
            <label for="clave">Clave:</label>
            <input type="text" name="clave" id="clave" value="" class="text ui-widget-content upperCase block ui-corner-all" maxlength="20"/>
        </fieldset>
    </form>
</div>


<!-- Formulario para registrar un nuevo empleado -->

<div id="dialog-form-empleado" class="captura-catalog-dialog" title="Registrar empleado">
    <form action="" name="formulario-empleado" id="catalog-empleado-form">
        <fieldset>
            <label for="tipo">Tipo:</label>
            <h3 class="area-tipo">
                <!--                Interno:<input type="radio" name="tipo" value="0" class="radio radio-arbol-interno" title="arbol-catalogo-empleado" alt="modal" checked>-->
                Externo:<input id="altaEmpleadoTipo1" type="radio" name="tipo" value="1" class="radio radio-arbol-externo" title="arbol-catalogo-empleado" alt="modal-arbol-externo" checked>
                Sin área:<input id="altaEmpleadoTipo2" type="radio" name="tipo" value="2" class="radio radio-arbol-externo" title="arbol-catalogo-empleado" alt="modal-arbol-noarea">
            </h3>            
            <div id="arbol-catalogo-empleado"  title="modal-arbol-externo">
                <label for="dependencia">Dependencia:</label>
                <input id="altaEmpleadoAreaNombre" type="text" name="area"  class="tree text required ui-widget-content ui-corner-all icon-tree-trigger">
                <input id="altaEmpleadoAreaId" type="hidden" name="id" class="id">
            </div>
            <label for="nombre">Nombre(s):</label>
            <input type="text" name="nombre" id="nombre" value="" class="required text upperCase block ui-widget-content ui-corner-all" />

            <label for="paterno">Apellido paterno:</label>
            <input type="text" name="paterno" id="paterno" value="" class="text required ui-widget-content upperCase block ui-corner-all" />

            <label for="materno">Apellido materno:</label>
            <input type="text" name="materno" id="materno" value="" class="text required ui-widget-content upperCase block ui-corner-all" />

            <label for="email">Email:</label>
            <input type="text" name="email" id="email" value="" class="text email ui-widget-content block ui-corner-all" />

            <label for="rfc">RFC:</label>
            <input type="text" name="rfc" id="rfc" value="" class="text rfc ui-widget-content upperCase block ui-corner-all" />

            <label for="puesto">Puesto:</label>
            <input type="text" name="puesto" id="puesto" value="" class="text ui-widget-content upperCase block ui-corner-all" />
        </fieldset>
    </form>
</div>

<!-- Formulario para registra tema -->
<div id="dialog-form-tema" class="captura-catalog-dialog" title="Registrar Tema">
    <form action="" name="formulario-tema" id="catalogo-tema-form">
        <fieldset>
            <label for="nombre-tema">Nombre del tema:</label>
            <input type="text" name="nombre-tema" id="nombre-tema" value="" class="required text upperCase block ui-widget-content ui-corner-all" />
            <label for="select-empleados-revisor">Revisor:</label>
            <html:select name="capturaForm" property="empleadoRevisor.id_empleado" styleId="select-empleados-revisor" styleClass="required ui-widget-content ">
                <html:optionsCollection name="capturaForm"
                                        property="empleadosRevisores" value="id_empleado"
                                        label="nombre_completo" />
            </html:select>
        </fieldset>
    </form>
</div>

<!-- Formulario para registra subtema -->
<div id="dialog-form-subtema" class="captura-catalog-dialog" title="Registrar Subtema">
    <form action="" name="formulario-subtema" id="catalogo-subtema-form">
        <fieldset>
            <label for="nombre-tema">Nombre del tema:</label>
            <html:select name="capturaForm" property="asunto.temaCatalogo.id_tema" styleId="select-catalogo-temas" styleClass="required element-search ui-widget-content ">
                <html:option value="-1" styleClass="texto-igual">Seleccione una opci&oacute;n</html:option>
                <html:optionsCollection name="temas" value="id_tema" label="tema" />
            </html:select>
            <label for="nombre-subtema">Nombre del Subtema:</label>
            <input type="text" name="nombre-subtema" id="nombre-subtema" value="" class="required text upperCase block ui-widget-content ui-corner-all" />
        </fieldset>
    </form>
</div>