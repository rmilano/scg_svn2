/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * Guarda las instrucciones y comentarios personzalizados
 */
function actualizaDestinatarios(listIdentifier)
{
    var idRow = "", idInstruccion = "", instruccionPersonalizada = "", comentario = "",comentarioGeneral="",idEmpleado;
    $(listIdentifier).each(function(index, domElement){
        idRow = $(domElement).attr("id");
        idEmpleado = $("#"+idRow + " .eliminarDest").attr("name");
        idInstruccion = $("#"+idRow + " select").val();
        instruccionPersonalizada = $("#"+ idRow + " .instruccion-personalizada").val();
        comentario = $("#" + idRow+ " .comentario-personalizado").val();
        comentarioGeneral = $(".comentario-general").val();
        sendDataEmpleyedAsync(idEmpleado,idInstruccion, instruccionPersonalizada, comentario, comentarioGeneral);
    });
}

/**
 * Envia los datos del empleado al servidor
 */
function sendDataEmpleyedAsync(idEmpleado,idInstruccion, instruccionPersonalizada, comentario, comentarioGeneral)
{
    var url = "../../captura.do?method=actualizaEmpleadoDestinatario";
    $.post(url,
    {
        idEmpleado: idEmpleado,
        idInstruccion: idInstruccion,
        instruccionPersonalizada: instruccionPersonalizada,
        comentarioPersonalizado: comentario,
        comentarioGeneral: comentarioGeneral
    }, function(data){
     
    });    
}