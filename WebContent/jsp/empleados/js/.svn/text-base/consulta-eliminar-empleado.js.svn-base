/*
 * Listenner del documento
 */
$(function(){
    crearArbol("");
    
    $("#buscar-empleados").click(function(){
        var elementsForSearchs = $(".element-search");        
        if(validatedListElement(elementsForSearchs))
        {
            $.blockUI(); 
            document.forms['empleadoForm'].method.value = 'getEmpleados';
            document.forms['empleadoForm'].submit(); 
        }
        else//enviar mensaje: Alguno de los campos son requerido        
            updateTips( "Al menos uno de los campos es requerido" , 1500, "element-search");
    });
    
    $("#crear-empleado").click(function(){        
        document.forms['empleadoForm'].method.value = 'inicioGuardarEmpleado';
        document.forms['empleadoForm'].submit(); 
    });
    // limpiar criterios de busqueda
    $("#limpiar-criterio").click(function(){
        document.forms[0].method.value='inicio';
        document.forms[0].submit();
    });

    //Configuracion del paginador
    $("#paginador").paginate({
        count 		:parseInt($("#paginas").val()),
        start 		:parseInt($('#pagina').val()),
        display     :10,
        border				: false,
        text_color  		: '#646463',
        background_color    : 'none',
        text_hover_color  	: '#ba2025',
        background_hover_color	: 'none',
        images		: false,
        mouse		: 'press',
        onChange	:
        function(page){
            $("#pagina").attr('value',page);            
            document.forms[0].method.value='getEmpleadosPorPagina';
            document.forms[0].submit();
        }
    });
    $(".edit").click(function(){
        var idEmpleado = $(this).attr("id");
        $("#empleado-editar").attr("value",idEmpleado);
        document.forms[0].method.value='inicioEditarEmpleado';
        document.forms[0].submit();      
    });
    $(".delete").click(function(){
        var idEmpleado = $(this).attr("id");
        $("#empleado-editar").attr("value",idEmpleado);        
        jConfirm("<span class='message-confirm'>&#191;Esta seguro de eliminar el Empleado&#63;.</span>", "Eliminar Empleado", function(ok){
            if(ok)
            {
                document.forms[0].method.value='borrarEmpleado';
                document.forms[0].submit();
            }
        });
    });    
});
