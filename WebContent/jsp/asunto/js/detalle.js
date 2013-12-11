$( function() {
    /* controles del asunto*/
    $("#atender").click(function(){
        $.blockUI();
        document.forms['asuntoForm'].action = '../../asunto.do?method=inicioAtender';
        document.forms['asuntoForm'].submit();
    });
    $("#darVoBo").click(function(){
        $.blockUI();
        document.forms['asuntoForm'].action = '../../asunto.do?method=inicioDarVoBo';
        document.forms['asuntoForm'].submit();
    });
    $("#clonar-asunto-gestionToAgenda").click(function(){
        $.blockUI();
        $("#asuntoForm").attr("action",'../../captura.do?method=inicioCapGestionToAgenda&id_asunto='+$("#id-asuntoform").val());
        $("#asuntoForm").submit();
    });
    $("#gestionar-ccp").click(function(){
        $.blockUI();
        $("#asuntoForm").attr("action",'../../captura.do?method=inicioModificaCcp&id_asunto='+$("#id-asuntoform").val());
        $("#asuntoForm").submit();
    });
    $("#turnar-inicio").click(function(){
        $.blockUI();
        document.forms['asuntoForm'].action = '../../asunto.do?method=inicioTurnar';
        document.forms['asuntoForm'].submit();
    });
    $("#Returnar-inicio").click(function(){
        $.blockUI();
        document.forms['asuntoForm'].action = '../../asunto.do?method=inicioReTurnar';
        document.forms['asuntoForm'].submit();
    });
    $("#finalizar").click(function(){
        $.blockUI();
        document.forms['asuntoForm'].action = '../../asunto.do?method=inicioFinalizar';
        document.forms['asuntoForm'].submit();
    });
    $("#rechazar").click(function(){
        $.blockUI();
        document.forms['asuntoForm'].action = '../../asunto.do?method=inicioRechazar';
        document.forms['asuntoForm'].submit();
    });
    $("#regresar-consulta").click(function(){
        $.blockUI();
        document.forms['asuntoForm'].action = '../../asunto.do?method=inicioRechazar';
        document.forms['asuntoForm'].submit();
    });
    $("#eliminar").click(function() {
        jConfirm(
            "Desea eliminar esta captura?",
            "Eliminar Captura",
            function(ok) {
                if (ok) {
                    $.blockUI();
                    document.forms['asuntoForm'].action = '../../asunto.do?method=eliminarAsunto';
                    document.forms['asuntoForm'].submit();
                }
            });

    });

    // Modificar el asunto
    $("#modificar-asunto").click(function(){
        $.blockUI();
        $("#asuntoForm").attr("action",'../../captura.do?method=inicioModifica&id_asunto='+$("#id-asuntoform").val());
        $("#asuntoForm").submit();
    });

    // Editar turno
    $("#editar-turno").click(function(){
        $.blockUI();

        $("#asuntoForm").attr("action",'../../asunto.do?method=inicioEditarTurno&p_idAsunto='+$("#id-asuntoform").val()+'&p_idAsuntoDetalle='+$("#id-asuntoDetalle").val());
        $("#asuntoForm").submit();
    });

    //Enviar a revision
    $("#enviar-revisor").click(function(){
        $.blockUI();
        $("#asuntoForm").attr("action",'../../asunto.do?method=enviarRevision');
        $("#asuntoForm").submit();
    });
    //Enviar a supervision
    $("#enviar-supervision").click(function(){
        $.blockUI();
        $("#asuntoForm").attr("action",'../../asunto.do?method=enviarSupervision');
        $("#asuntoForm").submit();
    });
    //Enviar a captura
    $("#enviar-captura").click(function(){
        $.blockUI();
        $("#asuntoForm").attr("action",'../../asunto.do?method=enviarCaptura');
        $("#asuntoForm").submit();
    });
    
    // si desea generar un Volante
    $("#volante").click(function(){
        $("#asuntoForm").attr("action", "../../asunto.do?method=generarVolanteDetallePDF");
        $("#asuntoForm").submit();
    });
    // si desea enviar a destinatarios
    $("#enviar-detinatarios").click(function(){
        $.blockUI();
        $("#asuntoForm").attr("action", "../../asunto.do?method=enviarAsuntoDestinatarios");
        $("#asuntoForm").submit();
    });

    // Si existe el mensaje de 'asunto en uso'
    if($("#msj-asunto-ocupado").length != 0)
    {
        jConfirm($("#msj-asunto-ocupado").text(), "Asunto en uso", function(ok)
        {            
            $("#msj-asunto-ocupado").remove();
            var url = "../../asunto.do?method=removeMessageSessionAsunto";
            $.post(url, {
                deleteMessage: 1
            }, function(data){
                
                }, "");
        });
    }
    $("#obtenerVolante").click(function(){
            $("#asuntoForm").attr("action",'../../asunto.do?method=generarVolanteCorrespondenciaFromSession');
            $("#asuntoForm").submit();
    });

    
//    $(window).unload( function () {
//        var url = "../../asunto.do?method=removeVolante";
//        $.post(url, {}, function(data){
//
//            }, "");
//    });
});
function regresar(url) {
    location.href = url;
}

/*
 * Listenner del documento
 */
$(function(){
    /* Crea los dialogos*/
    $("#dialog-form-bitacora").dialog( {
        autoOpen : false,
        modal : true,
        resizable: true,
        position: ['center',150],
        width: 1050,
        buttons : {
            'Cancelar' : function() {
                $(this).dialog('close');
            },

            'Guardar' : function() {
                var id_asunto = $("#id_asunto").val(),
                bitacora = $("#bitacora").val();
                insertBitacora(id_asunto, bitacora);//guardar bitácora
                $(this).dialog('close');//cerrar ventana
            }
        },
        Cerrar : function() {
            $(this).dialog('close');
        }
    });
    // muestra la bitácora
    $(".icon-bitacora").click(function(){
        $.blockUI();
        $.ajaxSetup({cache: false});
        //$("#dialog-form-bitacora input:text").attr("value","");
        //alert($("#bitacora").val());
        //if ($("#bitacora").val()==""){
            try{
                $.getJSON("../../asunto.do?method=getBitacora&id_asunto="+$("#id_asunto").val(),
                    function(data){
                        $("#bitacora").attr('value',data.bitacora);
                    });
                    $.unblockUI();
            }catch(ex){
                jAlert("Error al recuperar la bitácora");
            }
        //}
        $("#dialog-form-bitacora").dialog('open');
    });
});


/*
 * Registrar Bitácora
 */


function insertBitacora(id_asunto, bitacora)
{
    //alert(id_asunto.val());
    //alert(bitacora.val());
    var url = "../../asunto.do?method=saveBitacora";
    $.post(url,
    {
        id_asunto: id_asunto,
        bitacora: bitacora
    }, function(data){

        });
}