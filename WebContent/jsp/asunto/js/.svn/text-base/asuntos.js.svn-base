/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$( function() {
    // ********** VER Y OCULTAR HISTORIAL ********* //
    $("#imgVerHistorial").click( function() {        
        $("#tblHistorial").fadeIn("slow");
        $("#hrAsuntoFuera").fadeIn("slow");
    });
    $("#imgOculHistorial").click( function() {        
        $("#tblHistorial").fadeOut("slow");
        $("#hrAsuntoFuera").fadeOut("slow");
    });
    $(".descargarDoc").live("click", function() {
        var contexto_scg = $("#contexto_scg").val();
        var id_asunto_detalle = $(this).attr("name");
        var id_documento = $(this).attr("title");
        descargarDoc(contexto_scg, id_asunto_detalle, id_documento);
    });
    $(".descargarDocDet").live("click", function() {
        var idx = $(this).attr("name");
        var contexto_scg = $("#contexto_scg").val();
        descargarDocDet(contexto_scg, idx);
    });
});
/* Descargar documento */
function descargarDoc(contexto_scg, id_asunto_detalle, id_documento) {
	
    var url = contexto_scg+"/asunto.do?method=descargarDocumento";
    window.open(url + "&id_asunto_detalle=" + id_asunto_detalle+"&id_documento="+id_documento);
}

function descargarDocDet(contexto_scg, idx) {
    var url = contexto_scg+"/asunto.do?method=descargarDocumento";
    window.open(url + "&idx=" + idx);
}

/* Implementacipón de obtener asunto*/
function obtenerAsunto(id_asunto) {
    if (id_asunto < 0) {
        document.forms[0].action = '../../captura.do?method=inicio';
    } else {
        document.forms[0].action = '../../asunto.do?method=verDetalle&id_asunto='+id_asunto;
    }
    document.forms[0].submit();
}

/* Implementacipón de obtener asunto*/
function obtenerAsuntoVolante(id_asunto) {
    if (id_asunto < 0) {
        document.forms[0].action = '../../captura.do?method=inicio';
    } else {
        document.forms[0].action = '../../asunto.do?method=generarVolantePDF&id_asunto='+id_asunto;
    }
    document.forms[0].submit();
}

/*
 * Bitácora del asunto
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
