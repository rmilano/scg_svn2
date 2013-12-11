/*
 * Listener del documento
 */
var aux = "";
var instrucciones = "";
var prioridades = "";
var errorPrioridades="";
var dias = new Array();

$( function() {    
    var i = 0; // Para validar el campo "documento"
    $.datepicker.setDefaults($.datepicker.regional['es']);
    crearArbol('empleado');
    //la configuracion del calendario
    
    /*$('.fhs_limite').live('click', function(){
    	$(".fhs_limite").datepicker( {
            showOn :'button',
            buttonText :'',
            buttonImage :'../../imagenes/jquery-ui/calendar.png',
            buttonImageOnly :true,
            changeMonth :true,
            changeYear :true,
            dateFormat :'dd/mm/yy',
            minDate :'0d'
        });
    });*/
    

    //turnar del documento
    $("#save-turnar").click( function() {
        if($("#asuntoForm").validate().form()){
            var select_dest = $("#select-dest").val();
            var id_empleado_session = $("#id_empleado_session").val();

            var error =""
            if(select_dest==id_empleado_session){
                error += " <span class='msgError'> - El empleado destinatario debe ser diferente al usuario de sesi\u00f3n.</span>";
            }

            var filaDest = $(".dest-tr").attr('id');
            if(filaDest == null){
                error += " <span class='msgError'> - Debe ingresar por lo menos un destinatario</span>";
            }


            /*var fh_limite_asunto = $.datepicker.parseDate('dd/mm/yy', $("#fh_limite_asunto").val());
            var fh_limite = $("#fh_limite").datepicker("getDate");*/

            if (error != "") {
                var msgError = "<span class='campoVacio'>Se encontrar\u00f3n inconsistencias.\n</span>";
                jAlert(msgError + error, "Atenci\u00f3n");
                return false;
            }else{
                if (validaPrioridades($("#tablaDest tr"))){

                    actualizaDestinatarios($("#tablaDest tr"));
                    //$.blockUI();
                    if (confirm("Presione ACEPTAR si el volante impreso ya ha sido firmado para generar el turno.\nDe lo contrario presione CANCELAR")){
                        document.forms['asuntoForm'].method.value = "turnarGenerarVolante";
                        document.forms['asuntoForm'].submit();
                    }
                }
                else{
                    var msgError = "<span class='prioridades'>Se encontraron inconsistencias en la sección seguimiento.\n</span>";
                    jAlert(msgError + errorPrioridades, "Atenci\u00f3n");
                }
                
            }
        }
        else
            jAlert("Se requieren datos para completar la captura. \n Favor de llenar los campos marcados con *", "Datos requeridos");
    });

    //turnar del documento
    $("#save-returnar").click( function() {
        if($("#asuntoForm").validate().form()){
            var select_dest = $("#select-dest").val();
            var id_empleado_session = $("#id_empleado_session").val();

            var error =""
            if(select_dest==id_empleado_session){
                error += " <span class='msgError'> - El empleado destinatario debe ser diferente al usuario de sesi\u00f3n.</span>";
            }

            var filaDest = $(".dest-tr").attr('id');
            if(filaDest == null){
                error += " <span class='msgError'> - Debe ingresar por lo menos un destinatario</span>";
            }


            /*var fh_limite_asunto = $.datepicker.parseDate('dd/mm/yy', $("#fh_limite_asunto").val());
            var fh_limite = $("#fh_limite").datepicker("getDate");*/

            if (error != "") {
                var msgError = "<span class='campoVacio'>Se encontrar\u00f3n inconsistencias.\n</span>";
                jAlert(msgError + error, "Atenci\u00f3n");
                return false;
            }else{
                if (validaPrioridades($("#tablaDest tr"))){

                    actualizaDestinatarios($("#tablaDest tr"));
                    document.forms['asuntoForm'].method.value = "returnarGenerarVolante";
                    document.forms['asuntoForm'].submit();
                    //$.blockUI();
                    /*if (confirm("Presione ACEPTAR si el volante impreso ya ha sido firmado para generar el turno.\nDe lo contrario presione CANCELAR")){
                        document.forms['asuntoForm'].method.value = "turnarGenerarVolante";
                        document.forms['asuntoForm'].submit();
                    }*/
                }
                else{
                    var msgError = "<span class='prioridades'>Se encontraron inconsistencias en la sección seguimiento.\n</span>";
                    jAlert(msgError + errorPrioridades, "Atenci\u00f3n");
                }

            }
        }
        else
            jAlert("Se requieren datos para completar la captura. \n Favor de llenar los campos marcados con *", "Datos requeridos");
    });

    //turnar del documento
    $("#save-editar-turno").click( function() {
        if($("#asuntoForm").validate().form()){
            var select_dest = $("#select-dest").val();
            var id_empleado_session = $("#id_empleado_session").val();

            var error =""
            if(select_dest==id_empleado_session){
                error += " <span class='msgError'> - El empleado destinatario debe ser diferente al usuario de sesi\u00f3n.</span>";
            }

            var filaDest = $(".dest-tr").attr('id');
            if(filaDest == null){
                error += " <span class='msgError'> - Debe ingresar por lo menos un destinatario</span>";
            }


            /*var fh_limite_asunto = $.datepicker.parseDate('dd/mm/yy', $("#fh_limite_asunto").val());
            var fh_limite = $("#fh_limite").datepicker("getDate");*/

            if (error != "") {
                var msgError = "<span class='campoVacio'>Se encontrar\u00f3n inconsistencias.\n</span>";
                jAlert(msgError + error, "Atenci\u00f3n");
                return false;
            }else{
                if (validaPrioridades($("#tablaDest tr"))){

                    actualizaDestinatarios($("#tablaDest tr"));
                    //$.blockUI();
                    if (confirm("Presione ACEPTAR si el volante impreso ya ha sido firmado para generar el turno.\nDe lo contrario presione CANCELAR")){
                        document.forms['asuntoForm'].method.value = "editarTurnoGenerarVolante";
                        document.forms['asuntoForm'].submit();
                    }
                }
                else{
                    var msgError = "<span class='prioridades'>Se encontraron inconsistencias en la sección seguimiento.\n</span>";
                    jAlert(msgError + errorPrioridades, "Atenci\u00f3n");
                }

            }
        }
        else
            jAlert("Se requieren datos para completar la captura. \n Favor de llenar los campos marcados con *", "Datos requeridos");
    });

    //generar volante
    $("#genera-volante").click( function() {        
        if($("#asuntoForm").validate().form()){
            var select_dest = $("#select-dest").val();
            var id_empleado_session = $("#id_empleado_session").val();

            var error =""
            if(select_dest==id_empleado_session){
                error += " <span class='msgError'> - El empleado destinatario debe ser diferente al usuario de sesi\u00f3n.</span>";
            }

            var filaDest = $(".dest-tr").attr('id');
            if(filaDest == null){
                error += " <span class='msgError'> - Debe ingresar por lo menos un destinatario</span>";
            }


            /*var fh_limite_asunto = $.datepicker.parseDate('dd/mm/yy', $("#fh_limite_asunto").val());
            var fh_limite = $("#fh_limite").datepicker("getDate");*/

            if (error != "") {
                var msgError = "<span class='campoVacio'>Se encontrar\u00f3n inconsistencias.\n</span>";
                jAlert(msgError + error, "Atenci\u00f3n");
                return false;
            }else{
                if (validaPrioridades($("#tablaDest tr"))){
                    actualizaDestinatarios($("#tablaDest tr"));
                    //$.blockUI();
                    if (confirm("Se generará un volante previo")){
                        document.forms['asuntoForm'].method.value = "generarVolanteDetalleUPDF";
                        document.forms['asuntoForm'].submit();
                    }
                }
                else{
                    var msgError = "<span class='prioridades'>Se encontraron inconsistencias en la sección seguimiento.\n</span>";
                    jAlert(msgError + errorPrioridades, "Atenci\u00f3n");
                }
            }
        }
        else
            jAlert("Se requieren datos para completar la captura. \n Favor de llenar los campos marcados con *", "Datos requeridos");
    });

    // Ir al inicio del asunto
    $("#regresar").click( function() {
        document.forms['asuntoForm'].action = '../../asunto.do';
        document.forms['asuntoForm'].method.value = 'inicio';
        document.forms['asuntoForm'].submit();
    });

    $("#guardar_capt").click( function() {        
        var select_dest = $("#select-dest").val();
        var id_empleado_session = $("#id_empleado_session").val();
        var error ="";
        if(select_dest==id_empleado_session){
            error += " <span class='msgError'> - El empleado destinatario debe ser diferente al usuario de sesi\u00f3n.</span>";
        }
        var filaDest = $(".dest-tr").attr('id');
        if(filaDest == null){
            error += " <span class='msgError'> - Debe ingresar por lo menos un destinatario</span>";
        }
        if (error != "") {
            var msgError = "<span class='campoVacio'>Se encontrar\u00f3n inconsistencias.\n</span>";
            jAlert(msgError + error, "Atenci\u00f3n");
            return false;
        }else{
            actualizaDestinatarios($("#tablaDest tr"));
            var idEmpleadoDest = $("#id_empleadoFirmante").val()
            guardarCapturaTurno(idEmpleadoDest);
        }        
    });

    // Agregar con copia para
    $("#agregarCcp").click(
        function() {
            var idEmpleado = $("#idEmpleadoCcp").val(), nombreEmpleado = $("#arbolC :selected").text();
            if(idEmpleado == null || idEmpleado == '' || idEmpleado < 0)
                updateTips("Este campo es requerido",500, "ui-estate-error");
            else                
                agregarCcp(idEmpleado, nombreEmpleado);
        });

    // Eliminar con copia para
    $(".eliminarCcp").live("click", function() {
        var idEmpleado = $(this).attr("name");
        eliminarCcp(idEmpleado);
    });

    // Eliminar documento adjunto
    $(".eliminarDoc").live("click", function() {
        var idx = $(this).attr("name");
        eliminarDoc(idx);
    });
	
    $("#documento").click( function() {
        //alert("Documento")
        if(i==0)
        {
            $("#documento").val("");
            i++;
        }
    });

    // Adjuntar documento
    $("#adjuntarDoc").click(function() {
            
        var campoVacio = "";
        var tipoDocumento = $("#id_tipo_documento").val();
        var tipo_documento = $("#id_tipo_documento :selected").text();
        var adjunto = $("#adjunto").val();
        var documento = $("#documento").val();
        var msgError = "<span class='campoVacio'> Los siguientes campos son requeridos:\n </span>";
        //var msgSuccess = "<span class='msgSuccess'> Documento adjuntado correctamente. </span>";

        if(tipoDocumento == -1)
        {
            campoVacio += " <span class='msgError'> - Tipo de Documento.\n </span>";
        }
            
        if(documento==""
            || documento == "Escriba el nombre del documento"
            || documento == "Escriba el nombre"
            || documento == "Escriba un nombre"
            || documento == "Nombre del documento" )

            {
            campoVacio += " <span class='msgError'> - Documento.\n </span>";
        }
        if(adjunto == "")
        {
            //alert("")
            campoVacio += " <span class='msgError'> - Documento adjunto.\n </span>";
        }

        if(campoVacio != "")
        {
            jAlert(msgError + campoVacio, "Atenci\u00f3n");
            return false;
        }
        else
        {
            $.blockUI();
            adjuntarDoc(documento, tipoDocumento, tipo_documento, 'adjunto');
            $("#documento").val("");
            $("#adjunto").val("");
            $("#id_tipo_documento").val(-1);            
            $.unblockUI();
        }
    });
    
    // cambio en la prioridad del documento
    /*$("#prioridad").change(function() {
        if($("#prioridad").val()== 5){
            $("#fh_limite_div_td").show();
            $("#fh_limite_div_th").show();
        }else{
            $("#fh_limite_div_td").hide();
            $("#fh_limite_div_th").hide();
        }
    });
    $("#prioridad").change();*/
    
    // agregar destinatario 
    $("#agregarDest").click(	function() {
        
        
        var idEmpleado = $("#idEmpleadoDest").val(), nombreEmpleado = $("#arbolD :selected").text();
        var idAreaEmpleadoCaptura = $("#empleado-captura-id-area").val();
        if(idEmpleado == null || idEmpleado == '' || idEmpleado < 0)
        {
            updateTips("Este campo es requerido",500, "ui-estate-error");
        }
        else
            agregarDest(idEmpleado, nombreEmpleado,idAreaEmpleadoCaptura);
    });
    //eliminar destinatario
    $(".eliminarDest").live("click", function() {
        var idEmpleado = $(this).attr("name");
        eliminarDest(idEmpleado);
    });
    
    // si desea generar un Volante
    $("#volante").click(function(){
        if($("#asuntoForm").validate().form()){
            $("#idmetodo").attr("value", "generarVolantePDF");
            $("#asuntoForm").submit();
        }
    });  
    //si el comentarios es general o personalizado
    $("#is-comentario-personal").change(function(){
        unComment($(this).is(':checked'));
    });
    //mostrar el comentario general solo si tiene texto definido
    validaComentarioInstruccionPersonalizado();
    //los cambios en la instruccion personzada
    $('.select-instrucciones').live('change', function() {
        listeningChangeOnInstruccion($(this).val(),$(this).parent().parent().attr("id"));
    });  
    
    //El turnado en forma de listado o al area que compete    //Por default presentar las areas internas con el tipo de listado: Para el area del secretario
    $("#arbolE").hide();
    $("#arbolE").attr("disabled", true);
    //Si desde la oficina del secretario se desea enviar un asunto a destinatario interno
    $("#scg-interno-listado").click(function()
    {
        // ocultar el arbol completo y el externo
        $("#arbolE").hide();
        $("#arbolE").attr("disabled", true);
        //mostrar el listado de areas inmediatas
        $(".ui-listado-area").show();
        $(".ui-listado-area").removeAttr("disabled");

    });
    // Para mostrar todo el arbol de la se
    $("#scg-interno-se").click(function()
    {
        //Ocultar el listado de areas internas inmendiatas
        $(".ui-listado-area").hide();
        $(".ui-listado-area").attr("disabled", true);
        // Mostrar el arbol completo y el externo
        $("#arbolE").show();
        $("#arbolE").removeAttr("disabled");
        $("#arbolE").attr("title","modal");

    });
    // agregar desde la oficina del secretario
    $("#scg-add-destinatario-os").click(	function() {
        var contenedor = $(this).parent().attr("id");
        var idEmpleado = $("#" + contenedor + " #idEmpleadoDest").val(),
        nombreEmpleado = $("#" + contenedor + " #idEmpleadoDest :selected").text();
        var idAreaEmpleadoCaptura = $("#empleado-captura-id-area").val();
        if(idEmpleado == null || idEmpleado == '' || idEmpleado < 0){
            updateTips("Este campo es requerido", 500, "ui-estate-error-dest");
        }
        else if( idEmpleado == $("#idEmpleadoRemiSelect").val())
        {
            jAlert("El remitente y el destinatario no deben ser la misma persona.", "Atencion");
            return;
        }
        else{
            agregarDest(idEmpleado, nombreEmpleado,idAreaEmpleadoCaptura);
        }
    });
    // cada vez que cargue la pagina seleccionar por default el arbol interno
    $(".radio-default-selected").attr('checked', true);

    // Si abandona la pagina, quitar el asunto de la session. Debe estar disponible para alguien mas
    $(window).unload( function () {
        var url = "../../asunto.do?method=removeAsuntoFromListSession";
        $.post(url, {}, function(data){
            
            }, "");
    } );
});

function unComment(isComment)
{    
    if(isComment)
    {        
        $(".comentario-personalizado").removeAttr("disabled");        
        $(".comentario-general").attr("disabled","disabled");
        $(".comentario-personalizado").attr("value",$(".comentario-general").val());
        $(".comentario-general").attr("value","");        
    }
    else
    {        
        $(".comentario-personalizado").attr("disabled","disabled");       
        $(".comentario-general").removeAttr("disabled");
        $(".comentario-general").attr("value",$(".comentario-personalizado").val());
        $(".comentario-personalizado").attr("value","");        
    }

}

/*
 * Remover empleado
 */
function removeItemFromEmpleadoDest()
{
    var idEmpleadoSession = $("#id_empleado_session").val();
    //.live("click"
    $("#select-dest option[value="+idEmpleadoSession+"]").remove();
}

/*
 * Implementacion de adjuntar documentos
 */
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
                "<a class='descargarDocDet' name='"+ data.idx+"'>" +
                "<img height='25' width='25' src='../../imagenes/file.png'>" +
                "</a>" +
                "<a class='eliminarDoc' name='"+ data.idx+"'>" +
                "<img height='25' width='25' src='../../imagenes/file_remove.png'>" +
                "</a>" +
                "</td>" +
                "</tr>");
	    	
        },
        error: function (data, status, e)
        {
        }
    });
}

/*
 * Implementacion de agregar con copia para
 */
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
/*
 * Implementacion de eliminar con copia para 
 */
function eliminarCcp(idEmpleado) {
    var url = "../../asunto.do?method=eliminarConCopiaPara";
    $.post(url, {
        idEmpleado :idEmpleado
    }, function(data) {
        $("#eliminarCcp" + idEmpleado).remove();
    });
}


/*
 * Eliminar documento adjunto
 */
function eliminarDoc(idx) {
    var url = "../../asunto.do?method=eliminarDocumento";
    $.post(url, {
        idx :idx
    }, function(data) {
        $("#eliminarDoc" + idx).remove();
    });
}


function agregarDest(idEmpleado, nombreEmpleado, idArea) {
    $.blockUI();
    var url = "../../asunto.do?method=agregarDestinatario";
    var disabled = "";
    if ($("#is-comentario-personal").is(':checked')){
        disabled = "";
    }
    else{
        disabled = "disabled=\"disabled\"";
    }
    $.post(url, {
        idEmpleado :idEmpleado,
        nombreEmpleado :nombreEmpleado
    }, function(data) {
        if(data == 'success'){
            
            var idFilaEmpleadoDestinatario = "eliminarDest" + idEmpleado;
            //alert(idFilaEmpleadoDestinatario);
            $("#tablaDest").append(
                "<tr id='" + idFilaEmpleadoDestinatario + "' class='dest-tr'>" +
                "<td id=nombreEmp"+idFilaEmpleadoDestinatario+">" + nombreEmpleado + "</td>" +
                "<td>" + getInstruccionesByEmpleado(idArea,idFilaEmpleadoDestinatario) + "</td>"+
                "<td>" + getPrioridadesByEmpleado(idFilaEmpleadoDestinatario) +
                      "<div id='fh_limite_div_td"+idFilaEmpleadoDestinatario+"' style='display: none;'>" +
                      "<input type='text' class='fhs_limite' name='fh_limite"+idFilaEmpleadoDestinatario+"' id='fh_limite"+idFilaEmpleadoDestinatario+"' readonly='readonly' /></div>"+
                "</td>"+
                "<td><textarea class='comentario-personalizado upperCase'"+disabled+"></textarea></td>" +
                "<td><a class='eliminarDest' name='"+ idEmpleado+"'>" + "<img height='25' width='25' src='../../imagenes/group_remove.png'></a></td>" +
                "</tr>");            
              };              
              $(".fhs_limite").datepicker( {
                  showOn :'button',
                  buttonText :'',
                  buttonImage :'../../imagenes/jquery-ui/calendar.png',
                  buttonImageOnly :true,
                  changeMonth :true,
                  changeYear :true,
                  dateFormat :'dd/mm/yy',
                  minDate :'0d'
              });
              //$(".comentario-personalizado").textLimit(1000);// El comentario personalizado del asunto tiene un limite de 1000 caracteres        
    });
    $.unblockUI();
}
/*
 * Implementacion de elimiar destinatario
 */
function eliminarDest(idEmpleado) {
    var url = "../../asunto.do?method=eliminarDestinatario";
    $.post(url, {
        idEmpleado :idEmpleado
    }, function(data) {
        $("#eliminarDest" + idEmpleado).remove();
    });
}

/**
 * Muestra los comentarios dependiendo de la seleccion del usuario
 */
function validaComentarioInstruccionPersonalizado()
{    
    if($(".comentario-general").val().length > 0)
    {        
        $(".comentario-general").removeAttr("disabled");
        $(".comentario-personalizado").attr("disabled","disabled");
        $("#is-comentario-personal").attr("checked", false);
    }
    else        
        $(".comentario-general").attr("disabled","disabled");
}

/**
 * Si se ha seleccionado una instruccion deshabilida el texto para la instruccion personalizada
 */
function listeningChangeOnInstruccion(value, idParent)
{
    //solo si el valor es diferende de -1
    $("#" + idParent + " input.instruccion-personalizada").removeAttr("disabled");
    $($("#" + idParent + " input.instruccion-personalizada").css("border","1px solid #CCDDEE"));
    if(value != -1)
    {
        $("#" + idParent + " input.instruccion-personalizada").attr("disabled","disabled","");
        $($("#" + idParent + " input.instruccion-personalizada").css("border","1px solid #520c24"));
    }
}

function getInstruccionesByEmpleado(idArea,idFilaEmpleadoDestinatario)
{
    
    var  url = "../../empleados.do?method=getInstrucciones";
    var instruccionList = "";

    if(instrucciones.length < 1)// si no se han obtenido previamente las instrucciones
    {        
        
        instruccionList =  $.ajax({
            url: url,
            type: "POST",
            data: ({
                idArea : idArea
            }),
            dataType: "json",
            async:false,
            success: function(data){

            }
        }).responseText;

        var jsonList  = $.parseJSON(instruccionList);

        instruccionList = "<option value='-1'>Otra instrucci&oacute;n:</option>";
        $.each(jsonList, function(key, value){
            instruccionList = instruccionList + "<option  value='"+ value.id_instruccion + "'>" + value.instruccion + "</option>";
        });

        instruccionList = "<select id='instruccion"+idFilaEmpleadoDestinatario+"' class='select-instrucciones ui-widget-content'>" + instruccionList + "</select>"
        instrucciones = instruccionList;
    }
    //alert(instrucciones)
    return instrucciones;
}

function getPrioridadesByEmpleado(idFilaEmpleadoDestinatario)
{
    
    var  url = "../../empleados.do?method=getPrioridades";
    var prioridadList = "";

    /*if(prioridades.length < 1)// si no se han obtenido previamente las instrucciones
    { */       
        
        prioridadList =  $.ajax({
            url: url,
            type: "POST",
            data: ({
            }),
            dataType: "json",
            async:false,
            success: function(data){
            }
        }).responseText;

        var jsonList  = $.parseJSON(prioridadList);

        prioridadList = "<option value='-1'>Seleccione una opci&oacute;n:</option>";
        dias.length = 0;
        $.each(jsonList, function(key, value){
            prioridadList = prioridadList + "<option  value='"+ value.id_prioridad + "'>" + value.prioridad + "</option>";
            dias[value.id_prioridad] = value.fh_limiteDDMMYYYY;
        });    
        //alert(idFilaEmpleadoDestinatario);
        prioridadList = "<select class='select-prioridades ui-widget-content required' id='prioridades"+idFilaEmpleadoDestinatario+"'" +
        		" onChange=\"if (this.value<=1){document.getElementById('fh_limite_div_td"+idFilaEmpleadoDestinatario+"').style.display='none'; } " +
        		            "else {document.getElementById('fh_limite_div_td"+idFilaEmpleadoDestinatario+"').style.display='block'; "+
                                    "} actualizaFechaPrioridad(document.getElementById('fh_limite"+idFilaEmpleadoDestinatario+"'),this.value,'"+idFilaEmpleadoDestinatario+"'); \" >" + prioridadList + "</select>";
        prioridades = prioridadList;
    /*}*/
    //alert(prioridades)
    return prioridades;
}

function actualizaFechaPrioridad(campo,id,idFilaEmpleadoDestinatario){
    campo.value=dias[id];    
    if (id==5){
       //$(".fh_limite").show();
       $("#fh_limite"+idFilaEmpleadoDestinatario).datepicker('enable');
    }
    else {
       //$".fhs_limite").hide();
       $("#fh_limite"+idFilaEmpleadoDestinatario).datepicker('disable');
    }
}

function validaPrioridades(listIdentifier)
{
    var idRow = "", correcto = false, correctoGlobal = true, idPrioridad = "", fec_prioridad="", NomEmpleado="";
    errorPrioridades="";
    $(listIdentifier).each(function(index, domElement){
        correcto = false
        idRow = $(domElement).attr("id");
        idPrioridad = $("#prioridades"+idRow).val();
        fec_prioridad = $("#fh_limite"+idRow).val();
        NomEmpleado = $("#nombreEmp"+idRow).html();
        if (idPrioridad<1){
            correcto=false;
            errorPrioridades=errorPrioridades+"\nEl seguimiento es obligatorio para "+NomEmpleado+".";
        }
        else{
            correcto=true;
        }
        if (idPrioridad==5 && fec_prioridad==""){
            correcto=false;
            errorPrioridades=errorPrioridades+"\nDebe seleccionar la fecha del seguimiento para "+NomEmpleado+".";
        }
        correctoGlobal=correcto&&correctoGlobal;
    });
    return correctoGlobal;
}

function actualizaDestinatarios(listIdentifier)
{
    var idRow = "", idInstruccion = "", instruccionPersonalizada = "", idPrioridad = "",
        comentario = "",comentarioGeneral="",idEmpleado="", fec_prioridad="";
    $(listIdentifier).each(function(index, domElement){
        idRow = $(domElement).attr("id");
        idEmpleado = $("#"+idRow + " .eliminarDest").attr("name");
        idInstruccion = $("#"+idRow + " select").val();
        instruccionPersonalizada = $("#"+ idRow + " .instruccion-personalizada").val();        
        idPrioridad = $("#prioridades"+idRow).val();
        fec_prioridad = $("#fh_limite"+idRow).val();
        comentario = $("#" + idRow+ " .comentario-personalizado").val();
        comentarioGeneral = $(".comentario-general").val();
        sendDataEmpleyedAsync(idEmpleado,idInstruccion, instruccionPersonalizada, comentario, comentarioGeneral,idPrioridad,fec_prioridad);
    });
}

/**
 * Envia los datos del empleado al servidor
 */
function sendDataEmpleyedAsync(idEmpleado,idInstruccion, instruccionPersonalizada, comentario, comentarioGeneral,idPrioridad,fec_prioridad)
{    
    var url = "../../asunto.do?method=actualizaEmpleadoDestinatario";
    $.post(url,{
        idEmpleado: idEmpleado,
        idInstruccion: idInstruccion,        
        comentarioPersonalizado: comentario,
        comentarioGeneral: comentarioGeneral,
        idPrioridad : idPrioridad,
        fec_prioridad : fec_prioridad
    }, function(data) {});
}

function guardarCapturaTurno(idEmpleadoFirmante)
{
    var url = "../../asunto.do?method=guardaCapturaTurno";
    $.blockUI();
    $.post(url,{
    idEmpleadoFirmante:idEmpleadoFirmante
    }, function(data) {});
    $.unblockUI();
}


// Cargar destinatario para edición
    $("#carga-destinatario")
    {
        var id_empleado_destino = $("#id_empleado_destino").val();
        var nombre_empleado_destino = $("#nombre_empleado_destino").val();
        var id_area_emp_cap = $("#id_area_emp_cap").val();
        /*var prueba =$("#prueba").val();
        alert(prueba);*/
        if (id_empleado_destino!=null){
            alert(id_empleado_destino);
            alert(nombre_empleado_destino);
            alert(id_area_emp_cap);
            agregarDest(id_empleado_destino, nombre_empleado_destino,id_area_emp_cap);
        }
    }

/*********************************************************************************************/

//Agregar los datos de los destinatarios cargados
function actualizaDest(idEmpleado, nombreEmpleado, idArea, idInstruccion, idPrioridad, comentario) {
    $(".comentario-general").attr("value","");
    $("#is-comentario-personal").attr("checked", true);
    agregarDestInicio(idEmpleado, nombreEmpleado, idArea, idInstruccion, idPrioridad, comentario);    
}

function agregarDestInicio(idEmpleado, nombreEmpleado, idArea, idInstruccion, idPrioridad, comentario) {
    $.blockUI();
    var url = "../../asunto.do?method=agregarDestinatario";
    var disabled = "";
    if ($("#is-comentario-personal").is(':checked')){
        disabled = "";
    }
    else{
        disabled = "disabled=\"disabled\"";
    }
    $.post(url, {
        idEmpleado :idEmpleado,
        nombreEmpleado :nombreEmpleado
    }, function(data) {
        if(data == 'success'){

            var idFilaEmpleadoDestinatario = "eliminarDest" + idEmpleado;
            //Saltos de línea
            var comentarioSaltos = comentario;            
            while (comentarioSaltos.indexOf('&lt;br&gt;')>0){
                comentarioSaltos=comentarioSaltos.replace('&lt;br&gt;','\n');
            }

            //alert(idFilaEmpleadoDestinatario);
            $("#tablaDest").append(
                "<tr id='" + idFilaEmpleadoDestinatario + "' class='dest-tr'>" +
                "<td id=nombreEmp"+idFilaEmpleadoDestinatario+">" + nombreEmpleado + "</td>" +
                "<td>" + getInstruccionesByEmpleadoInicio(idArea,idFilaEmpleadoDestinatario,idInstruccion) + "</td>"+
                "<td>" + getPrioridadesByEmpleadoInicio(idFilaEmpleadoDestinatario,idPrioridad) +
                      "<div id='fh_limite_div_td"+idFilaEmpleadoDestinatario+"' style='display: none;'>" +
                      "<input type='text' class='fhs_limite' name='fh_limite"+idFilaEmpleadoDestinatario+"' id='fh_limite"+idFilaEmpleadoDestinatario+"' readonly='readonly' /></div>"+
                "</td>"+
                "<td><textarea class='comentario-personalizado upperCase'"+disabled+">"+comentarioSaltos+"</textarea></td>" +
                "<td><a class='eliminarDest' name='"+ idEmpleado+"'>" + "<img height='25' width='25' src='../../imagenes/group_remove.png'></a></td>" +
                "</tr>");
              };
              $(".fhs_limite").datepicker( {
                  showOn :'button',
                  buttonText :'',
                  buttonImage :'../../imagenes/jquery-ui/calendar.png',
                  buttonImageOnly :true,
                  changeMonth :true,
                  changeYear :true,
                  dateFormat :'dd/mm/yy',
                  minDate :'0d'
              });
              //$(".comentario-personalizado").textLimit(1000);// El comentario personalizado del asunto tiene un limite de 1000 caracteres
    });
    $.unblockUI();
}

function getInstruccionesByEmpleadoInicio(idArea,idFilaEmpleadoDestinatario,idInstruccion)
{

    var  url = "../../empleados.do?method=getInstrucciones";
    var instruccionList = "";

    if(instrucciones.length < 1)// si no se han obtenido previamente las instrucciones
    {

        instruccionList =  $.ajax({
            url: url,
            type: "POST",
            data: ({
                idArea : idArea
            }),
            dataType: "json",
            async:false,
            success: function(data){

            }
        }).responseText;

        var jsonList  = $.parseJSON(instruccionList);

        instruccionList = "<option value='-1'>Otra instrucci&oacute;n:</option>";
        $.each(jsonList, function(key, value){
            if (idInstruccion==value.id_instruccion){
                instruccionList = instruccionList + "<option  value='"+ value.id_instruccion + "' selected>" + value.instruccion + "</option>";
            }
            else{
                instruccionList = instruccionList + "<option  value='"+ value.id_instruccion + "'>" + value.instruccion + "</option>";
            }
        });

        instruccionList = "<select id='instruccion"+idFilaEmpleadoDestinatario+"' class='select-instrucciones ui-widget-content' >" + instruccionList + "</select>"
        instrucciones = instruccionList;
    }
    //alert(instrucciones)
    return instrucciones;
}

function getPrioridadesByEmpleadoInicio(idFilaEmpleadoDestinatario,idPrioridad)
{

    var  url = "../../empleados.do?method=getPrioridades";
    var prioridadList = "";

    /*if(prioridades.length < 1)// si no se han obtenido previamente las instrucciones
    { */

        prioridadList =  $.ajax({
            url: url,
            type: "POST",
            data: ({
            }),
            dataType: "json",
            async:false,
            success: function(data){
            }
        }).responseText;

        var jsonList  = $.parseJSON(prioridadList);

        prioridadList = "<option value='-1'>Seleccione una opci&oacute;n:</option>";
        $.each(jsonList, function(key, value){
            if (idPrioridad==value.id_prioridad){
                prioridadList = prioridadList + "<option  value='"+ value.id_prioridad + "' selected>" + value.prioridad + "</option>";
            }
            else{
                prioridadList = prioridadList + "<option  value='"+ value.id_prioridad + "'>" + value.prioridad + "</option>";
            }
        });
        //alert(idFilaEmpleadoDestinatario);
        prioridadList = "<select class='select-prioridades ui-widget-content required' id='prioridades"+idFilaEmpleadoDestinatario+"'" +
        		" onChange=\"if (this.value<=1){document.getElementById('fh_limite_div_td"+idFilaEmpleadoDestinatario+"').style.display='none'; } " +
        		            "else {document.getElementById('fh_limite_div_td"+idFilaEmpleadoDestinatario+"').style.display='block'; "+
                                    "} actualizaFechaPrioridad(document.getElementById('fh_limite"+idFilaEmpleadoDestinatario+"'),this.value,'"+idFilaEmpleadoDestinatario+"'); \" >" + prioridadList + "</select>";
        prioridades = prioridadList;
    /*}*/
    //alert(prioridades)
    return prioridades;
}

