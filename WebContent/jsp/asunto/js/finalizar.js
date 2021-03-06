$( function() {
    var i = 0; // Para validar el campo "documento"
    /* configuracion regional del calendario*/
    $.datepicker.setDefaults($.datepicker.regional['es']);
    crearArbol('empleado');
    /* Configuracion del calendario*/
    $("#fh_limite").datepicker( {
        showOn :'button',
        buttonText :'',
        buttonImage :'../../imagenes/jquery-ui/calendar.png',
        buttonImageOnly :true,
        changeMonth :true,
        changeYear :true,
        dateFormat :'dd/mm/yy',
        minDate :'0d'
    });
    
    
    /* finalizar el asunto*/
    $("#finalizar").click( function() {
        if($("#asuntoForm").validate().form()){
            $.blockUI();
            document.forms['asuntoForm'].method.value = 'finalizar';
            document.forms['asuntoForm'].submit();
        }
    });

    /* Ir al inicio del modulo de asuntos*/
    $("#regresar").click( function() {
        document.forms['asuntoForm'].action = '../../asunto.do';
        document.forms['asuntoForm'].method.value = 'inicio';
        document.forms['asuntoForm'].submit();
    });

    /* Agregar con copia para*/
    $("#agregarCcp").click(
        function() {
            var idEmpleado = $("#idEmpleadoCcp").val(), nombreEmpleado = $(
                "#arbolC :selected").text();
            agregarCcp(idEmpleado, nombreEmpleado);
        });

    /* Eliminar con copia para*/
    $(".eliminarCcp").live("click", function() {
        var idEmpleado = $(this).attr("name");
        eliminarCcp(idEmpleado);
    });

    /* Eliminar documento adjunto*/
    $(".eliminarDoc").live("click", function() {
        var idx = $(this).attr("name");
        eliminarDoc(idx);
    });

    /* Obterner valor del documento*/
    $("#documento").click( function() {
        if(i==0)
        {
            $("#documento").val("");
            i++;
        }
    });

    /* Guardar asunto*/
    $("#guardar").click( function() {
        if ($("#capturaForm").validate().form()) {
            document.forms['capturaForm'].method.value = 'guardarAsunto';
            document.forms['capturaForm'].submit();
        }
    });

    /* Adjuntar documento*/
    $("#adjuntarDoc").click(function() {
        var campoVacio = "";
        var tipoDocumento = $("#id_tipo_documento").val();
        var tipo_documento = $("#id_tipo_documento :selected").text();
        var adjunto = $("#adjunto").val();
        var documento = $("#documento").val();
        var msgError = "<span class='campoVacio'> Los siguientes campos son requeridos:\n </span>";
        //var msgSuccess = "<span class='msgSuccess'> Documento adjuntado correctamente. </span>";
        if(tipoDocumento == -1) {
            campoVacio += " <span class='msgError'> - Tipo de Documento.\n </span>";
        }
        if(documento==""
            || documento == "Escriba el nombre del documento"
            || documento=="Escriba el nombre"
            || documento=="Escriba un nombre"
            || documento == "Nombre del documento"	){
            campoVacio += " <span class='msgError'> - Documento.\n </span>";
        }
        if(adjunto == "") {  
            campoVacio += " <span class='msgError'> - Documento adjunto.\n </span>";
        }
        if (campoVacio != "") {
            jAlert(msgError + campoVacio, "Atenci\u00f3n");
            return false;
        } else {
            $.blockUI();
            adjuntarDoc(documento, tipoDocumento, tipo_documento, 'adjunto');
            $("#documento").val("");
            $("#adjunto").val("");
            $("#id_tipo_documento").val(-1);
            //jAlert(msgSuccess, "Listo!")
            $.unblockUI();
        }
    });
});

/* Implementacion de la funcion adjuntar documento*/
function adjuntarDoc(documento, id_tipo_documento, tipo_documento, adjunto) {
    var url = "../../asunto.do?method=adjuntarDocumento&documento="+documento+"&id_tipo_documento="+id_tipo_documento+"&tipo_documento="+tipo_documento;
    $.ajaxFileUpload(
    {
        url:url,
        secureuri:false,
        fileElementId:adjunto,
        dataType: 'json',
        success: function (data, status)
        {
            $("#tablaDoc").append(
                "<tr id='eliminarDoc" + data.idx + "'>" +
                "<td>" + data.documento + "</td>" +
                "<td>" + tipo_documento + "</td>" +
                "<td>" +
                "<a class='eliminarDoc' name='"+ data.idx+"'>" +
                "<img height='25' width='25' src='../../imagenes/file_remove.png'>" +
                "</a>" +
                "<a class='descargarDocDet' name='"+ data.idx+"'>" +
                "<img height='25' width='25' src='../../imagenes/file.png'>" +
                "</a>" +
                "</td>" +
                "</tr>");
	    	
        },
        error: function (data, status, e)
        {
        }
    });
}

/* Implementacion de la funcion agregar con copia para*/
function agregarCcp(idEmpleado, nombreEmpleado) {
    var url = "../../asunto.do?method=agregarConCopiaPara";
    $.post(url, {
        idEmpleado :idEmpleado,
        nombreEmpleado :nombreEmpleado
    }, function(data) {
        if(data == 'success'){
            $("#tablaCcp").append(
                "<tr id='eliminarCcp" + idEmpleado + "'>" +
                "<td colspan='2'>" + nombreEmpleado + "</td>" +
                "<td>" +
                "<a class='eliminarCcp' name='"+ idEmpleado+"'>" +
                "<img height='25' width='25' src='../../imagenes/group_remove.png'>" +
                "</a>" +
                "</td>" +
                "</tr>");
        }
		
    });
}
/* Eliminar con copia para*/
function eliminarCcp(idEmpleado) {
    var url = "../../asunto.do?method=eliminarConCopiaPara";
    $.post(url, {
        idEmpleado :idEmpleado
    }, function(data) {
        $("#eliminarCcp" + idEmpleado).remove();
    });
}



/*
 * Elimar documentos
 */
function eliminarDoc(idx) {
    var url = "../../asunto.do?method=eliminarDocumento";
    $.post(url, {
        idx :idx
    }, function(data) {
        $("#eliminarDoc" + idx).remove();
    });
}

