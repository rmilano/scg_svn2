/*
 * El listener del documento
 */


var instrucciones = "";

$( function() {
    var i = 0; // Para validar el campo "documento"

    $("#guardar").click( function()
    {
        /*if($("#select-temas").val() == "-1" || $("#select-expedientes").val() == "-1")
        {
            jAlert("Los campos de expediente y tema son requeridos", "Atenci\u00f3n");
            return;
        } */
        if(($("#idEmpleadoRemiSelect").val() == null && $("#idEmpleadoRemitente").val() == null)
          ||($("#idEmpleadoRemiSelect").val() == "-1" && $("#idEmpleadoRemitente").val() == null)
          ||($("#idEmpleadoRemiSelect").val() == "" && $("#idEmpleadoRemitente").val() == null)
          ||($("#idEmpleadoRemiSelect").val() == "-1" && $("#idEmpleadoRemitente").val() == "-1")
          ||($("#idEmpleadoRemiSelect").val() == "" && $("#idEmpleadoRemitente").val() == ""))
        {
            jAlert("El remitente es requerido.", "Atenci\u00f3n");
            return;
        }
        if($("#capturaForm").validate().form())
        {
            var error =""
            var filaDest = $(".dest-tr").attr('id');
            if(filaDest == null){
                error += " <span class='msgError'> - Debe ingresar por lo menos un destinatario</span>";
            }

            if (error != "") {
                var msgError = "<span class='campoVacio'>Se encontrar\u00f3n inconsistencias.\n</span>";
                jAlert(msgError + error, "Atenci\u00f3n");
                return false;
            }else{
                actualizaRemitente();
                //Se actualizan las propiedades de cada destinatario: instruccion, comentario
                actualizaDestinatarios($("#tablaDest tr"));
                $.blockUI();
                $("#post-method-name").attr("value","vistaPreviaAsunto");
                $("#capturaForm").submit();
            }
        }
        else
            jAlert("Se requieren datos para completar la captura. \n Favor de llenar los campos marcados con *", "Datos requeridos");
    });

    $("#modificar").click( function()
    {
        if($("#capturaForm").validate().form())
        {
            var error =""
            var filaDest = $(".dest-tr").attr('id');
            if(filaDest == null){
                error += " <span class='msgError'> - Debe ingresar por lo menos un destinatario</span>";
            }
            if (error != "") {
                var msgError = "<span class='campoVacio'>Se encontrar\u00f3n inconsistencias.\n</span>";
                jAlert(msgError + error, "Atenci\u00f3n");
                return false;
            }else{
               actualizaRemitente();
                actualizaDestinatarios($("#tablaDest tr"));
                $.blockUI();
                $("#post-method-name").attr("value","vistaPreviaAsunto");
                $("#capturaForm").submit();
            }
        }
    });



    //crear el arbol
    crearArbol('empleado');

    // agregar destinatario
    $("#agregarDest").click(	function() {
        //var idEmpleado = $("#idEmpleadoDest").val(), nombreEmpleado = $("#arbolD :selected").text();
        var idEmpleado = $("#idEmpleadoDest").val(), nombreEmpleado = $("#idEmpleadoDest :selected").text();
        var idAreaEmpleadoCaptura = $("#empleado-captura-id-area").val();
        //jAlert(" " + $("#idEmpleadoDest").val() + " " + $("#idEmpleadoRemiSelect").val(), "Atencion");
        if(idEmpleado == null || idEmpleado == '' || idEmpleado < 0){
            updateTips("Este campo es requerido", 500, "ui-estate-error-dest");
        }
        else if( $("#idEmpleadoDest").val() == $("#idEmpleadoRemiSelect").val())
        {
            jAlert("El remitente y el destinatario no deben ser la misma persona.", "Atencion");
            return;
        }
        else{
            agregarDest(idEmpleado, nombreEmpleado,idAreaEmpleadoCaptura);
        }

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

    //eliminar destinatario
    $(".eliminarDest").live("click", function() {
        var idEmpleado = $(this).attr("name");
        eliminarDest(idEmpleado);
    });

    // con copia para
    $("#agregarCcp").click(	function() {
        var idEmpleado = $("#idEmpleadoCcp").val(), nombreEmpleado = $("#arbolC :selected").text();
        if(idEmpleado == null || idEmpleado == '' || idEmpleado < 0)
            updateTips("Este campo es requerido", 500, "ui-estate-error");
        else
            agregarCcp(idEmpleado, nombreEmpleado);
    });

    //eliminar con copia para
    $(".eliminarCcp").live("click", function() {
        var idEmpleado = $(this).attr("name");
        eliminarCcp(idEmpleado);
    });

    //eliminar documento adjunto
    $(".eliminarDoc").live("click", function() {
        var idx = $(this).attr("name");
        eliminarDoc(idx);
    });

    //descargar documento adjunto
    $(".descargarDoc").live("click", function() {
        var idx = $(this).attr("name");
        var contexto_scg = $("#contexto_scg").val();
        descargarDoc(contexto_scg, idx);
    });

    $("#documento").click( function() {
        //alert("Documento")
        if(i==0)
        {
            $("#documento").val("");
            i++;
        }
    });

    $("#altaEmpleadoTipo1").change(function() {
        $("#arbol-catalogo-empleado").show();
        $("#altaEmpleadoAreaNombre").attr("value","");        
        $("#altaEmpleadoAreaId").attr("value",0);
    });

    $("#altaEmpleadoTipo2").change(function() {
    //$("#arbol-catalogo-empleado").change(function() {
        $("#arbol-catalogo-empleado").hide();        
        $("#altaEmpleadoAreaNombre").attr("value","SIN AREA");
        $("#altaEmpleadoAreaId").attr("value",2183);
    });

    //adjuntar documento
    $("#adjuntarDoc").click(function() {

        var campoVacio = "";
        var tipoDocumento = $("#id_tipo_documento").val();
        var tipo_documento = $("#id_tipo_documento :selected").text();
        var adjunto = $("#adjunto").val();
        var documento = $("#documento").val();
        var fh_limite = $("#fh_limite").val();
        var ruta_adjunto = adjunto;
        $("#adjunto").val();
        var msgError = "<span class='campoVacio'> Los siguientes campos son requeridos:\n </span>";

        if(tipoDocumento == -1)
        {
            campoVacio += " <span class='msgError'> - Tipo de Documento.\n </span>";
        }

        if(documento==""
            || documento == "Escriba el nombre del documento"
            || documento == "Escriba el nombre"
            || documento == "Escriba un nombre"
            || documento == "Nombre del documento")

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
            adjuntarDoc(documento, tipoDocumento, tipo_documento, ruta_adjunto, 'adjunto');
            $("#adjunto").val("");
            $("#documento").val("");
            $("#id_tipo_documento").val(-1);
            $.unblockUI();
        }

    });

    $("#prioridad").change(function() {
        if($("#prioridad").val()== 5){
            $("#fh_limite_div_td").show();
            $("#fh_limite_div_th").show();
            $("#archivar_div_th").hide();
            $("#archivar_div_td").hide();
        }else{
            $("#fh_limite_div_td").hide();
            $("#fh_limite_div_th").hide();
            $("#archivar_div_th").show();
            $("#archivar_div_td").show();
        }
    });
    $("#prioridad").change();

    // recargar el combo de empleados
    $(".icon-reload").click(function(){
        var idArbol = $(this).parent().attr("id"),
        idArea = $("#" + idArbol + " .id").val();
        loadEmployed("#" + idArbol + " select", idArea,"remi");
    });

    //la paginacion
    $("#paginador").paginate({
        count 		:$("#paginas").val(),
        start 		:$('#pagina').val(),
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
            $("#post-method-name").attr("value",'getAsuntoByIndex');
            $('#capturaForm').unbind('submit').submit();//evita la validacion que tiene el formulario
        }
    });

    //la busqueda de los asuntos
    $("#buscar-asunto").click(function(){
        var elementsForSearchs = $(".element-search");
        if(validatedListElement(elementsForSearchs))
        {
            $.blockUI();
            $("#post-method-name").attr("value",'buscaAsuntos');
            $('#capturaForm').unbind('submit').submit();//evita la validacion que tiene el formulario
        }
        else//enviar mensaje: Alguno de los campos son requerido
            updateTips( "Al menos uno de los campos es requerido" , 1500, "element-search");
    });
    //limpiar criterios de busqueda de asuntos
    $("#limpiar-criterio-busqueda").click(function()
    {
        $("#post-method-name").attr("value",'inicioCriterioBusqueda');
        $('#capturaForm').unbind('submit').submit();//evita la validacion que tiene el formulario
    });

    mostrarVistaRemitente($("#scg-captura-displaylist").val(), $("#scg-captura-displaytree").val());

    //Si desde la oficina del secretario se desea enviar un asunto a destinatario interno
    $("#scg-remitente-listado").click(function()
    {
        // ocultar el arbol completo y el externo
        $("#arbol-remitente").hide();
        $("#arbol-remitente").attr("disabled", true);
        //mostrar el listado de areas inmediatas
        $(".ui-remitente-listado-area").show();
        $(".ui-remitente-listado-area").removeAttr("disabled");
        //La vista a mostrar
        $("#scg-captura-displaylist").attr("value","1");
        $("#scg-captura-displaytree").attr("value","0");
        $("#remi-area-id").empty();        
        $("#remi-area-id").html($("#remi-area-id2").html());
        /*$(".remi-area-id option").each(function() {
            if ( $(this).val() == 2183 ) {
            $(this).remove();
            }
        });*/
        $('#remi-area-id').find('[value=2183]').remove();
        $("#idEmpleadoRemitente").show();
    });
    $("#scg-interno-listado").click(function()
    {
        // ocultar el arbol completo y el externo
        $("#arbolE").hide();
        $("#arbolE").attr("disabled", true);
        //mostrar el listado de areas inmediatas
        $(".ui-listado-area").show();
        $(".ui-listado-area").removeAttr("disabled");
        $("#idEmpleadoRemitente").show();

    });

    //Si desde la oficina del secretario se desea enviar un asunto a destinatario externo
    $("#scg-externo-listado").click(function()
    {
        //Ocultar el listado de areas internas inmendiatas
        $(".ui-listado-area").hide();
        $(".ui-listado-area").attr("disabled", true);
        // Mostrar el arbol completo y el externo
        $("#arbolE").show();
        $("#arbolE").removeAttr("disabled");
        $("#arbolE").attr("title","modal-arbol-externo");
        $("#idEmpleadoRemitente").show();

    });
    $("#scg-remitente-externo-listado").click(function()
    {
        //Ocultar el listado de areas internas inmendiatas
        $(".ui-remitente-listado-area").hide();
        $(".ui-remitente-listado-area").attr("disabled", true);
        // Mostrar el arbol completo y el externo
        $("#arbol-remitente").show();
        $("#arbol-remitente").removeAttr("disabled");
        $("#arbol-remitente").attr("title","modal-arbol-externo");
        //Mostrar el listado o el arbol
        $("#scg-captura-displaylist").attr("value","0");
        $("#scg-captura-displaytree").attr("value","1");
        $("#idEmpleadoRemitente").show();

    });
    // Para mostrar todo el arbol de la se
    $("#scg-remitente-interno-se").click(function()
    {
        //Ocultar el listado de areas internas inmendiatas
        $(".ui-remitente-listado-area").hide();
        $(".ui-remitente-listado-area").attr("disabled", true);
        // Mostrar el arbol completo y el externo
        $("#arbol-remitente").show();
        $("#arbol-remitente").removeAttr("disabled");
        $("#arbol-remitente").attr("title","modal");
        //Mostrar el listado o el arbol
        $("#scg-captura-displaylist").attr("value","0");
        $("#scg-captura-displaytree").attr("value","1");
        $("#idEmpleadoRemitente").show();
    });
    // Para capturar remitente sin area
    $("#scg-remitente-noarea").click(function()
    {        
        // ocultar el arbol completo y el externo
        $("#arbol-remitente").hide();
        $("#arbol-remitente").attr("disabled", true);
        //mostrar el listado de areas inmediatas
        $(".ui-remitente-listado-area").show();
        $(".ui-remitente-listado-area").attr("disabled", false);
        $("#remi-area-id").empty();
        $("#remi-area-id").html($("#remi-area-id2").html());        
        $("#remi-area-id option").each(function(){
                if ($(this).val()!=2183){
                    $(this).remove();
                }
        });
        $("#idEmpleadoRemitente").show();
        //La vista a mostrar
        $("#scg-captura-displaylist").attr("value","0");
        $("#scg-captura-displaytree").attr("value","0");
         $("#remi-area-id").trigger('change');
    });


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
    // cada vez que cargue la pagina seleccionar por default el arbol interno
    $(".radio-default-selected").attr('checked', true);

    $("#limpiar-asunto").click(function(){
        $("#post-method-name").attr("value",'inicio');
        $('#capturaForm').unbind('submit').submit();//evita la validacion que tiene el formulario
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
    //mensaje de recordatorio que los asuntos archivados no se pueden modificar
    $("#asunto-archivado-si").change(function(){
        var ids = $("#ids_exito").val();
        jAlert("Una vez archivado el asunto no se podr&aacute; enviar al destinatario(s)", "Â¿Esta seguro de archivar el asunto?",function(){
            //location.href='<html:rewrite page="/consulta-captura.do?method=inicio"/>';
            });
    });
    /***/

    $('#remi-area-tipo-a').live('change', function() {
        $("#idEmpleadoRemiSelect").empty();
    });
    $('#remi-area-tipo-b').live('change', function() {
        $("#idEmpleadoRemiSelect").empty();
    });
    $('#remi-area-tipo-c').live('change', function() {
        $("#idEmpleadoRemiSelect").empty();
    });
    $('#scg-remitente-listado').live('change', function() {
        $("#idEmpleadoRemitente").empty();
        $("#idEmpleadoRemiSelect").empty();
    });
    $('#scg-remitente-externo-listado').live('change', function() {
        $("#idEmpleadoRemitente").empty();
        $("#idEmpleadoRemiSelect").empty();
    });
    $('#scg-remitente-interno-se').live('change', function() {
        $("#idEmpleadoRemitente").empty();
        $("#idEmpleadoRemiSelect").empty();
    });
    $('#scg-remitente-noarea').live('change', function() {
        $("#idEmpleadoRemitente").empty();
        $("#idEmpleadoRemiSelect").empty();
    });

    $('#idEmpleadoRemitente').live('change', function() {
        actualizaPuestoRemi(this.value)
    });

    $('#idEmpleadoRemiSelect').live('change', function() {
        actualizaPuestoRemi(this.value)
    });

    function actualizaPuestoRemi(id){        
        var puestos_all = "";
        var puesto_des = "";
        puestos_all = $('#puestos_remi').val();
        //alert($('#puesto_remi_t').val());
        puesto_des = puestos_all.substring(puestos_all.indexOf(id));        
        puesto_des = puesto_des.substring(puesto_des.indexOf("-")+1);        
        if (puesto_des.indexOf("-")>0)
            puesto_des = puesto_des.substring(0,puesto_des.indexOf("-")-1);
        else
            puesto_des = puesto_des.substring(0);        
        $('#puesto_remi_t').val(puesto_des);        
    }
    $('#idEmpleadoDest').live('change', function() {
        actualizaPuestoDest(this.value)
    });
    
    function actualizaPuestoDest(id){
        var puestos_all = "";
        var puesto_des = "";
        puestos_all = $('#puestos_dest').val();
        //alert($('#puesto_remi_t').val());
        puesto_des = puestos_all.substring(puestos_all.indexOf(id));
        puesto_des = puesto_des.substring(puesto_des.indexOf("-")+1);
        if (puesto_des.indexOf("-")>0)
            puesto_des = puesto_des.substring(0,puesto_des.indexOf("-")-1);
        else
            puesto_des = puesto_des.substring(0);
        $('#puesto_dest_t').val(puesto_des);
    }


    /****/
    // si se trata de editar el asunto, entonces deshabilidatar el folio
    if($("#scg-captura-id-asunto").val() > 0)
    {
        $("#scg-captura-folio").hide();
        $("#captura-label-folio").append($("#scg-captura-folio").val());
    }
    //$(".descripcion-asunto").textLimit(3000);// La descripcion del asunto tiene un limite de 3000 caracteres
    //$(".comentario-general").textLimit(1000);// El comentario del asunto tiene un limite de 1000 caracteres

    // si en la ediciÃ³n del documento, la prioridad es 'otra' mostrar el calendario
    if($("#prioridad").val() == '7')
        $("#fh_limite_div_td").show();

});

/**
 *Muestra la vista del remitente, si es en forma de lista en forma de arbol
 */
function mostrarVistaRemitente(list, tree)
{
    //Por default presentar las areas internas del destinatario con el tipo de listado: Para el area del secretario
    $("#arbolE").hide();
    $("#arbolE").attr("disabled", true);

    //Para el remitente
    if(list == "1")
    {
        $("#arbol-remitente").hide();
        $("#arbol-remitente").attr("disabled", true);
    }
    if(tree == "1")
    {
        $(".ui-remitente-listado-area").hide();
        $(".ui-remitente-listado-area").attr("disabled", true);
    }
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
        $("#" + idParent + " input.instruccion-personalizada").attr("disabled","disabled");
        $($("#" + idParent + " input.instruccion-personalizada").css("border","1px solid #520c24"));
    }
}

/**
* Identifica si el comentario es general o personalizado
*/
function unComment(isComment)
{
    if(isComment)
    {
        $(".comentario-personalizado").removeAttr("disabled");
        $(".comentario-general").attr("disabled","disabled");
        $(".comentario-general").attr("value","");
    }
    else
    {
        $(".comentario-personalizado").attr("disabled","disabled");
        $(".comentario-general").removeAttr("disabled");
        $(".comentario-personalizado").attr("value","");
    }
}



/*
* Implementacion de adjuntar documento
*/
function valAdjDoc()
{
    var campoVacio = "";
    var tipoDocumento = $("#id_tipo_documento").val();
    var documento = $("#documento").val();
    var adjunto = $("#adjunto").val();

    //alert("Validad Documento adjunto: " + tipoDocumento)

    if(tipoDocumento == -1)
    {
        campoVacio += "Tipo de Documento.\n";
    }
    if(documento == "")
    {
        campoVacio += "Documento.\n";
    }
    if(adjunto == "")
    {
        campoVacio += "Documento adjunto.\n";
    }

    if(campoVacio != "")
    {
        alert("No se deben adjuntar documentos")
        jAlert("Los siguientes campos son requeridos:\n" + campoVacio)
        return false;
    }
    else
    {
        alert("Si se deben adjuntar documentos")
        adjuntarDoc()
        jAlert("Documento adjuntado correctamente.")
    }
    return false;
}

/*
* Descargar documento
*/
function descargarDoc(contexto_scg, idx) {
    var url = contexto_scg+"/captura.do?method=descargarDocumento";
    window.open(url+"&idx="+idx);
}

/*
* Implementacion de la funcion adjuntar documento
*/
function adjuntarDoc(documento, id_tipo_documento, tipo_documento, ruta_adjunto, adjunto) {

    var url = "../../captura.do?method=adjuntarDocumento&documento="+documento+"&id_tipo_documento="+id_tipo_documento+"&tipo_documento="+tipo_documento+"&ruta_adjunto="+ruta_adjunto;

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
                "<td>" +"<a class='descargarDoc' name='"+ data.idx+"'>" +
                "<img height='25' width='25' src='../../imagenes/file.png'>" +
                "<a class='eliminarDoc' name='"+ data.idx+"'>" +
                "<img height='25' width='25' src='../../imagenes/file_remove.png'>" +
                "</a>" +
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
* Agregar con copia para
*/
function agregarCcp(idEmpleado, nombreEmpleado) {
    var url = "../../captura.do?method=agregarConCopiaPara";
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
* Implementacion de elimiar con copia para
*/
function eliminarCcp(idEmpleado) {
    var url = "../../captura.do?method=eliminarConCopiaPara";
    $.post(url, {
        idEmpleado :idEmpleado
    }, function(data) {
        $("#eliminarCcp" + idEmpleado).remove();
    });
}

/*
* Agregar destinatario
*/
function agregarDest(idEmpleado, nombreEmpleado, idArea) {
    var url = "../../captura.do?method=agregarDestinatario";
    var disabled = "";
    if ($("#is-comentario-personal").is(':checked')){
        disabled = "";
    }
    else{
        disabled = "disabled=\"disabled\"";
    }
    //alert(nombreEmpleado)
    $.post(url, {
        idEmpleado :idEmpleado,
        nombreEmpleado :nombreEmpleado
    }, function(data) {
        if(data == 'success'){
            var idFilaEmpleadoDestinatario = "eliminarDest" + idEmpleado;
            $("#tablaDest").append(
                "<tr id='" + idFilaEmpleadoDestinatario + "' class='dest-tr'>" +
                "<td>" + nombreEmpleado + "</td>" +
                "<td class='instruccion'>" + getInstruccionesByEmpleado(idArea,idFilaEmpleadoDestinatario) + "</td>"+
                "<td><textarea class='comentario-personalizado upperCase'"+disabled+"></textarea></td>" +
                "<td><a class='eliminarDest' name='"+ idEmpleado+"'>" + "<img height='25' width='25' src='../../imagenes/group_remove.png'></a></td>" +
                "</tr>");
        //$(".comentario-personalizado").textLimit(1000);// El comentario del asunto tiene un limite de 1000 caracteres
        }
    });
}
//function agregarDest(idEmpleado, nombreEmpleado, idArea) {
//    var url = "../../captura.do?method=agregarDestinatario";
//    $.post(url, {
//        idEmpleado :idEmpleado,
//        nombreEmpleado :nombreEmpleado
//    }, function(data) {
//        if(data == 'success'){
//            var idFilaEmpleadoDestinatario = "eliminarDest" + idEmpleado;
//            $("#tablaDest").append(
//                "<tr id='" + idFilaEmpleadoDestinatario + "' class='dest-tr'>" +
//                "<td>" + nombreEmpleado + "</td>" +
//                "<td><a class='eliminarDest' name='"+ idEmpleado+"'>" + "<img height='25' width='25' src='../../imagenes/group_remove.png'></a></td>" +
//                "</tr>");
//        }
//    });
//}


function getInstruccionesByEmpleado(idArea,idFilaEmpleadoDestinatario)
{
    var  url = "../../empleados.do?method=getInstrucciones";
    var instruccionList = "";

    if(instrucciones.length < 1)// si no se han optenido previamente las instrucciones
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

        instruccionList = "<select class='select-instrucciones ui-widget-content'>" + instruccionList + "</select>"
        instrucciones = instruccionList;
    }
    return instrucciones;
}
/*
* Implementacion de elimiar destinatario
*/
function eliminarDest(idEmpleado) {
    var url = "../../captura.do?method=eliminarDestinatario";
    $.post(url, {
        idEmpleado :idEmpleado
    }, function(data) {
        $("#eliminarDest" + idEmpleado).remove();
    });
}

/*
* Implementacion de elimar documento adjunto
*/
function eliminarDoc(idx) {
    var url = "../../captura.do?method=eliminarDocumento";
    $.post(url, {
        idx :idx
    }, function(data) {
        $("#eliminarDoc" + idx).remove();
    });

}

$("#idEmpleadoDest").live("change", function (){
    var selectedIndex = $("#idEmpleadoDest").attr('selectedIndex');
    $("#puesto_dest").attr('selectedIndex', selectedIndex);
});

function actualizaRemitente(){
     //Actualiza remitente
    remi1 = document.getElementById("idEmpleadoRemiSelect");
    remi2 = document.getElementById("idEmpleadoRemitente");
    remiHid = document.getElementById("idEmpleadoRemiHid"); //pivote para controlar si cambia el remitente
    if (remi1!= null){
        if (remi1.value!=remiHid.value){
            remiHid.value = remi1.value;
            if (remi2 != null){
                remi2.options.length = 0;
                remi2.add(new Option(remi1.options[remi1.selectedIndex].label,remi1.options[remi1.selectedIndex].value));
                remi2.selectedIndex=0;
            }
        }
    }
    if (remi2!=null){
        if (remi2.value!=remiHid.value){
            remiHid.value = remi2.value;
            if (remi1 != null){
                remi1.options.length = 0;
                remi1.add(new Option(remi2.options[remi2.selectedIndex].label,remi2.options[remi2.selectedIndex].value));
                remi1.selectedIndex=0;
            }
        }
    }
}

function changeSelectFromAutocomplete(selected,nomExpedientes,cveExpedientes){
    for (var i = 0; i < nomExpedientes.length; i++) {
        if (nomExpedientes[i]==selected)
            selectExpediente(cveExpedientes[i],selected);
    }
}

function selectExpediente(idExpediente,selected){
    //$("#select-expedientes option").each(function() { this.selected = (this.text == idExpediente+" - "+selected); });
    var compara = idExpediente+" - "+selected;    
    $("#select-expedientes option[text=" + compara +"]").attr("selected","selected") ;
}

// Funciones para hacer la validación de los campos de fecha
function validafecha(fecha,requerido){
    var vfecha = fecha.value;
    if (vfecha != "" && !validaFechaDDMMAAAA(vfecha)){
        alert("Formato de Fecha inválido")
        if (requerido){
            fecha.value = "";
            fecha.focus();
        }
    }
}

// Valida la fecha en formato DD/MM/AAAA
function validaFechaDDMMAAAA(fecha){
	var dtCh= "/";
	var minYear=1900;
	var maxYear=2100;
	function isInteger(s){
		var i;
		for (i = 0; i < s.length; i++){
			var c = s.charAt(i);
			if (((c < "0") || (c > "9"))) return false;
		}
		return true;
	}
	function stripCharsInBag(s, bag){
		var i;
		var returnString = "";
		for (i = 0; i < s.length; i++){
			var c = s.charAt(i);
			if (bag.indexOf(c) == -1) returnString += c;
		}
		return returnString;
	}
	function daysInFebruary (year){
		return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
	}
	function DaysArray(n) {
		for (var i = 1; i <= n; i++) {
			this[i] = 31
			if (i==4 || i==6 || i==9 || i==11) {this[i] = 30}
			if (i==2) {this[i] = 29}
		}
		return this
	}
	function isDate(dtStr){
		var daysInMonth = DaysArray(12)
		var pos1=dtStr.indexOf(dtCh)
		var pos2=dtStr.indexOf(dtCh,pos1+1)
		var strDay=dtStr.substring(0,pos1)
		var strMonth=dtStr.substring(pos1+1,pos2)
		var strYear=dtStr.substring(pos2+1)
		strYr=strYear
		if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1)
		if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1)
		for (var i = 1; i <= 3; i++) {
			if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1)
		}
		month=parseInt(strMonth)
		day=parseInt(strDay)
		year=parseInt(strYr)
		if (pos1==-1 || pos2==-1){
			return false
		}
		if (strMonth.length<1 || month<1 || month>12){
			return false
		}
		if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
			return false
		}
		if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
			return false
		}
		if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
			return false
		}
		return true
	}
	if(isDate(fecha)){
		return true;
	}else{
		return false;
	}
}
