/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


$(function(){
    crearArbol("");
    // buscar eventos
    $("#buscar-evento").click(function(){
        if($("#nombre-evento").val()==""&&$("#descripcion-evento").val()==""){
            jAlert("Debe capturar al menos un filtro","Mensaje");
        }else{
            document.forms[0].method.value='getEventos';
            document.forms[0].submit();
        }
    });
    // limpiar criterios de busqueda
    $("#limpiar-evento").click(function(){
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
            document.forms[0].method.value='getEventosPorPagina';
            document.forms[0].submit();
        }
    });
    $(".edit").click(function(){
        var idEvento = $(this).attr("id");
        $("#evento-editar").attr("value",idEvento);
        document.forms[0].method.value='editarEvento';
        document.forms[0].submit();
    });
    $(".delete").click(function(){
        var idEvento = $(this).attr("id");
        $("#evento-editar").attr("value",idEvento);
        jConfirm("<span class='message-confirm'>&#191;Esta seguro de eliminar el evento&#63;", "Eliminar Evento", function(ok){
            if(ok)
            {
                document.forms[0].method.value='borrarEvento';
                document.forms[0].submit();
            }
        });
    });

    $("#crear-evento").click(function(){
        document.forms[0].method.value='registroEvento';
        document.forms[0].submit();
    });
});
