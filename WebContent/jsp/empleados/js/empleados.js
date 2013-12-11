/*
 * Listenner del documento
 */
$(function(){
    $(".ui-button").click(function(){
        llenarTabla();
        mostrar("divAddDatosEmpleado");
    });
    
  /*  $(".modal").dialog({
    	close: function(){ 
    		llenarTabla();
    		mostrar("divAddDatosEmpleado");
    	}
    });
    */
});

/*
 * Dirije el flujo a la funcion de inicio
 */
function inicio(){
    $("#area").attr("value",'');
    document.forms['areaForm'].method.value = 'init';
    document.forms['areaForm'].submit();
}

/*
 * Cancelar
 */
function cancel(){
    limpiarBox();
    cerrar('divAddEmpleado');
    cerrar('ListaRoles');
    cerrar('DatosEmpleado');
    $("#id_ayuda_opc").hide("slow");
    $("#divAddEmpleado").hide("slow");
}

/*
 * Mostrar el formulario de empleados
 */
function mostrarCampos() {
    if ($("#area").val() == null || $("#area").val() == '') {
        jAlert("Primero seleccione un Area!", "Informacion");
    } else {
        mostrar('divAddEmpleado');
        if ($("#tipo").val() != 1) {
            mostrar('ListaRoles');
            mostrar('InputRfc');
        }
        limpiarBox();
        mostrar('DatosEmpleado');
        mostrar('ayudaRegMod');
        $(".required").attr('value', '');
        $("#id_empleado").attr('value', '');
        $("#puesto").attr('value', '');
        $("#rfc").attr('value', '');
        $("#id_ayuda_opc").show("slow");
        $("#divAddEmpleado").show("slow");
        configureTextDefault();
    }
}

/*
 * Confirmar eliminar empleado
 */
function eliminarEmpleado(id_empleado){
    jConfirm("&#191;Desea eliminar este usuario&#63;", "Baja de Usuario", function(ok){
        if(ok)
        {   
            isUsado(id_empleado);
        }
    });
}

/*
 * Listado de empleados
 */
function llenarTabla(){
    document.forms['areaForm'].method.value = 'listarEmpleados';
    document.forms['areaForm'].submit();
}

/*
 * Definir accion
 */
function definirAccion(){
    var idEmpleado = $("#id_empleado").val();
    if ($("#areaForm").validate().form()) {
        if (idEmpleado == "" || idEmpleado == null) {
            cerrar('DatosEmpleado');
            cerrar('ListaRoles');
            document.forms['areaForm'].method.value = 'saveEmpleado';
            document.forms['areaForm'].submit();
        } else {
            cerrar('DatosEmpleado');
            cerrar('ListaRoles');
            document.forms['areaForm'].method.value = 'updateEmpleado';
            document.forms['areaForm'].submit();
        }
        cerrar('divAddEmpleado');
    }
	
}

/*
 * Mostrar contenedor del detalle del empleado
 */
function mostrar(componente) {
    $("#" + componente).show();
}

function cerrar(componente) {
    $("#"+componente).hide();
}

/*
 * Editar empleado seleccionado
 */
function cargarEmpleado(id_empleado) {
    $.blockUI();
    mostrar('divAddEmpleado');
    if($("#tipo").val()!=1){
        mostrar('ListaRoles');
    }
    mostrar('DatosEmpleado');
    mostrar('ayudaRegMod');
    $("#divAddDatosEmpleado").show("slow");
    limpiarBox();
    $.getJSON("../../empleados.do?method=getEmpleado&idEmpleado="+id_empleado,
        function(data){
            $("#id_empleado").attr('value',data.id_empleado);
            $("#correo").attr('value',data.correo);
            $("#id").attr('value',data.id_area);
            $("#paterno").attr('value',data.paterno);
            $("#materno").attr('value',data.materno);
            $("#nombre").attr('value',data.nombre);
            $("#puesto").attr('value',data.puesto);
            $("#rfc").attr('value',data.rfc);
            $("#activo").attr('value',data.activo);
            $.each(data.roles, function(key, value)
            {
                $('#ListaRoles input[value='+value.id_rol+']').attr('checked', true);

            });
        });
    $.unblockUI();
}

/*
 * Limpiar campos para editar empleado
 */
function limpiarBox(){
    for(i=0;i<5;i++){
        if(i!=1)
        {
            $('#ListaRoles input[value='+i+']').attr('checked', false);
        }
    }

}

/*
 * Comprobar si el empleado no esta siendo usado
 */
function isUsado(id_empleado){
    $.getJSON("../../empleados.do?method=isUsado&idEmpleado="+id_empleado,
        function(data){
            if(data.result=="usado"){
                jConfirm("El empleado es participe de algun asunto�Esta seguro de eliminarlo?", "Baja de Usuario", function(ok){
                    if(ok)
                    {
                        bajaEmpleado(id_empleado);
                    }
                });
            }else{
                bajaEmpleado(id_empleado);
            }
        });
}

/*
 * Eliminar empleado
 */
function bajaEmpleado(id_empleado){
    $("#id_empleado").attr('value',id_empleado);
    document.forms['areaForm'].method.value = 'bajaEmpleado';
    document.forms['areaForm'].submit();
}