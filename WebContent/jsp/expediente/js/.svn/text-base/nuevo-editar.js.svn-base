
$(function(){
    crearArbol("");
    $("#guardar-expediente").click(function(){
        if($("#expedienteForm").validate().form())
        {
            $("#method").attr("value", 'saveExpediente');
            $("#expedienteForm").submit();    
        }        
    });
    crearArbol("");
    $("#editar-expediente").click(function(){
        if($("#expedienteForm").validate().form())
        {
            $("#method").attr("value", 'updateExpediente');
            $("#expedienteForm").submit();    
        }        
    });
});
