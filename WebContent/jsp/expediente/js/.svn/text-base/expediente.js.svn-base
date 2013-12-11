
$(function(){
    crearArbol("");
    $("#buscar-expediente").click(function(){
         
        if($("#nombre-area").val()==""&&$("#descripcion-expediente").val()=="")
            jAlert("Debe capturar al menos un filtro","Información");
        else{
            
            $("#method").attr("value", 'listarExpedientes');
            $("#expedienteForm").submit(); 
        }
    });    
    $("#crear-expediente").click(function(){
        $("#method").attr("value", 'inicioNuevoExpediente');
        $("#expedienteForm").submit(); 
    });
    $(".edit").click(function(){
        $("#expediente-id").attr("value",$(this).attr("id"));
        $("#method").attr("value", 'inicioEditarExpediente');
        $("#expedienteForm").submit();              
    });

    $(".delete").click(function(){
        $("#expediente-id").attr("value",$(this).attr("id"));
        jConfirm("<span class='message-confirm'>&#191;Esta seguro de eliminar el Expediente&#63;</span>", "Eliminar Expediente", function(ok){
            if(ok)
            {                
                $("#method").attr("value", 'bajaExpediente');
                $("#expedienteForm").submit(); 
            }
        });
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
            document.forms[0].method.value='listarExpedientesByPagina';
            document.forms[0].submit();
        }
    });
 
});
