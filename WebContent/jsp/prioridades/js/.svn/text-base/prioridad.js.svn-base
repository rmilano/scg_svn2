function definirAccion(){
	if ($("#prioridadForm").validate().form()) {
		if($("#id_prioridad").val()==null||$("#id_prioridad").val()==''){
			document.forms["prioridadForm"].method.value="guardar";
			document.forms["prioridadForm"].submit();
		}else{
			document.forms["prioridadForm"].method.value="actualizar";
			document.forms["prioridadForm"].submit();
		}
	}
}

/*
 * Obtener datos por id de prioridad
 */
function traerDatos(id_prioridad){
     try{
        $.getJSON("../../prioridad.do?method=getPrioridad&idPrioridad="+id_prioridad,
            function(data){
                $("#prioridad").attr('value',data.prioridad);
                $("#dias").attr('value',data.dias);
                $("#color").attr('value',data.color);
                $("#id_prioridad").attr('value',data.id_prioridad);
                $("#color").change();
                mostrar();
            });
        
    }catch(e){
        jAlert("No se pudieron cargar los datos","Error");
        cerrar();
    }    
}

/*
 * Eliminar prioridad
 */
function eliminar(id_prioridad){
    jConfirm("Esta seguro de eliminar esta Prioridad?", "Eliminar Prioridad", function(ok){
        if(ok)
        {
            $("#id_prioridad").attr('value',id_prioridad);
			document.forms['prioridadForm'].method.value = 'eliminar';
			document.forms['prioridadForm'].submit();
                        }
        });
}

/*
 * Lista de prioridades
 */
function mostrar(){
     $("#add").show();
}

/*
 * Nueva prioridad
 */
function nuevo(){
    mostrar();
    limpiar();
}

/*
 * Cancelar prioridad
 */
function cancelar(){
    $("#add").hide();
}

/*
 * Limpiar formulario de prioridades
 */
function limpiar(){
	$("#prioridad").attr('value','');
    $("#dias").attr('value','');
    $("#color").attr('value','');
    $("#id_prioridad").attr('value','');
}
