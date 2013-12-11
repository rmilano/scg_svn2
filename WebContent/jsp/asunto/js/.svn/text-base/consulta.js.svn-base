var chksRep = new Array();

$( function() {
    /*Configuracion regional del calendario*/
    $.datepicker.setDefaults($.datepicker.regional['es']);
    /* Configura el arbol sin agregar empleados del area*/
    crearArbol('');
    /* Obtiene un asunto*/
    $("#obtenerAsuntos").click(
        function() {            
            $("#nuevaconsulta").attr('value',"si");
            var bandeja = GetURLParameter('p_bandeja');
            var estatus = GetURLParameter('p_estatus');
            var confidencial = GetURLParameter('p_confidencial');
            var copia = GetURLParameter('p_copia');
            var historico = GetURLParameter('p_historico');
            /*document.forms[0].method.value = 'obtenerAsuntos&p_bandeja='+bandeja+'&p_estatus='+estatus
                                             +'&p_confidencial='+confidencial+'&p_copia='+copia
                                             +'&p_historico='+historico;*/
            $("#consultaBandejaForm").attr("action","../../consulta-bandeja.do?method=obtenerAsuntos&p_bandeja="+bandeja+"&p_estatus="+estatus
                                             +"&p_confidencial="+confidencial+"&p_copia="+copia
                                             +"&p_historico="+historico);
            document.forms[0].submit();
        });
    //Limpiar criterios de busqueda
    $("#consulta-limpiar").click(
        function() {
            document.forms[0].method.value = 'inicio';
            document.forms[0].submit();
        });
    $("#obtenerAsuntosRecibidos").click(
        function() { 
            $.blockUI();
            $("#nuevaconsulta").attr('value',"si");
            $("#consultaRecepcionForm").attr("action",'../../consulta-recepcion.do?method=obtenerAsuntos');
            $("#consultaRecepcionForm").submit();
        });
        
        
    $("#obtenerAsuntosCapturados").click(
        function() { 
            $.blockUI();
            $("#nuevaconsulta").attr('value',"si");
            $("#consultaCapturaForm").attr("action",'../../consulta-captura.do?method=obtenerAsuntos');
            $("#consultaCapturaForm").submit();
        });
    /* Configuración del paginador*/
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
            $("#nuevaconsulta").attr('value',"no");
            $.blockUI();
            document.forms[0].method.value='obtenerAsuntos';
            document.forms[0].submit();
        }
    });
    /* Los calendarios */
    $("#fh_oficio_ini").datepicker( {
        showOn :'button',
        buttonText :'',
        buttonImage :'../../imagenes/jquery-ui/calendar.png',
        buttonImageOnly :true,
        changeMonth :true,
        changeYear :true,
        dateFormat :'dd/mm/yy',
        maxDate :'0d'
    });

    $("#fh_oficio_fin").datepicker( {
        showOn :'button',
        buttonText :'',
        buttonImage :'../../imagenes/jquery-ui/calendar.png',
        buttonImageOnly :true,
        changeMonth :true,
        changeYear :true,
        dateFormat :'dd/mm/yy',
        maxDate :'0d'
    });

    $("#fh_lectura_ini").datepicker( {
        showOn :'button',
        buttonText :'',
        buttonImage :'../../imagenes/jquery-ui/calendar.png',
        buttonImageOnly :true,
        changeMonth :true,
        changeYear :true,
        dateFormat :'dd/mm/yy',
        maxDate :'0d'
    });

    $("#fh_lectura_fin").datepicker( {
        showOn :'button',
        buttonText :'',
        buttonImage :'../../imagenes/jquery-ui/calendar.png',
        buttonImageOnly :true,
        changeMonth :true,
        changeYear :true,
        dateFormat :'dd/mm/yy',
        maxDate :'0d'
    });

    $("#fh_registro_ini").datepicker( {
        showOn :'button',
        buttonText :'',
        buttonImage :'../../imagenes/jquery-ui/calendar.png',
        buttonImageOnly :true,
        changeMonth :true,
        changeYear :true,
        dateFormat :'dd/mm/yy',
        maxDate :'0d'
    });

    $("#fh_registro_fin").datepicker( {
        showOn :'button',
        buttonText :'',
        buttonImage :'../../imagenes/jquery-ui/calendar.png',
        buttonImageOnly :true,
        changeMonth :true,
        changeYear :true,
        dateFormat :'dd/mm/yy',
        maxDate :'0d'
    });
    $("#limpiar").click( function() {
        $(".criterio").attr('value', '');
    });
    // Si desea generar o no el volante de correspondencia
    $("#obtenerVolante").click(function(){
        $("#consultaCapturaForm").attr("action",'../../asunto.do?method=generarVolanteCorrespondenciaFromSession');
        $("#consultaCapturaForm").submit();
    });
    // Reporte de resumen de asuntos Consulta en captura
    $("#resumenAsuntos").click(function(){
        $("#chksRepForm").attr('value',chksRep.join(","));
        if (!$("#chksRepForm").val()==""){
            var originalAction = $("#consultaCapturaForm").attr("action");
            $("#consultaCapturaForm").attr("action",'../../consulta-captura.do?method=generaReporteResumenAsuntos');
            $("#consultaCapturaForm").submit();
            $("#consultaCapturaForm").attr("action",originalAction);
        }
        else{
            alert("No hay asuntos seleccionados");
        }
    });
    // Reporte de resumen de asuntos Bandejas
    $("#resumenAsuntosBandeja").click(function(){
        $("#chksRepForm").attr('value',chksRep.join(","));
        if (!$("#chksRepForm").val()==""){
            var originalAction = $("#consultaBandejaForm").attr("action");
            $("#consultaBandejaForm").attr("action",'../../consulta-bandeja.do?method=generaReporteResumenAsuntos');
            $("#consultaBandejaForm").submit();
            $("#consultaBandejaForm").attr("action",originalAction);
        }
        else{
            alert("No hay asuntos seleccionados");
        }
    });

    $(window).unload( function () {
        var url = "../../asunto.do?method=removeVolante";
        $.post(url, {}, function(data){
            alert("ajaxFileUpload");
        }, "");
    });
});

/* Implementacipón de obtener asunto*/
function obtenerAsunto(id_asunto) {
    document.forms[0].id_asunto.value = id_asunto;
    $.blockUI();
    if (id_asunto < 0) {
        document.forms[0].action = '../../captura.do?method=inicio';
    } else {
        document.forms[0].action = '../../asunto.do?method=verDetalle&id_asunto='+id_asunto;
    }
    document.forms[0].submit();
}

function obtenerAsuntoBandeja(id_asunto,confidencial,destinatario,empleado_sesion) {
   if (confidencial==1&&destinatario!=empleado_sesion){
       alert("El asunto es confidencial. \nSólo el desitinatario puede ver el detalle.");
   }
   else{
        document.forms[0].id_asunto.value = id_asunto;
        $.blockUI();
        if (id_asunto < 0) {
            document.forms[0].action = '../../captura.do?method=inicio';
        } else {
            document.forms[0].action = '../../asunto.do?method=verDetalle&id_asunto='+id_asunto;
        }
        document.forms[0].submit();
   }
}

/* Implementacipón de obtener asunto*/
function modificarAsunto(id_asunto) {
    document.forms[0].id_asunto.value = id_asunto;
    document.forms[0].action = '../../captura.do?method=inicioModifica&id_asunto='+id_asunto;
    document.forms[0].submit();
}

/*Actualizar clave de expediente*/

function actualizaCve(){
    document.getElementById("cve_expediente_sel").selectedIndex = document.getElementById("expediente_sel").selectedIndex;    
    cve_exp = document.getElementById("cve_expediente_sel").options[document.getElementById("cve_expediente_sel").selectedIndex].label;
    document.getElementById("cve_expediente").value = cve_exp;    
    //alert("Sale");
}

function GetURLParameter(sParam)
{
    var sPageURL = window.location.search.substring(1);
    var sURLVariables = sPageURL.split('&');
    for (var i = 0; i < sURLVariables.length; i++)
    {
        var sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] == sParam)
        {
            return sParameterName[1];
        }
    }
    return '';
}

function marcaTodos(todos){
    //alert("Entra");
    var checkboxes = document.getElementsByName('chksReporte');
    for(var i=0, n=checkboxes.length;i<n;i++) {
       checkboxes[i].checked = todos.checked;
       marcaParaReporte(checkboxes[i],checkboxes[i].id);
    }
    return null;
}

function marcaParaReporte(chk,idAsunto){
    if (chk.checked==true){
        if(!$.inArray(idAsunto, chksRep))
            // incluye un pequeño delay
            myVar=setTimeout(function(){return true},3000);
            chksRep.push(idAsunto);
    }
    else{
        var index = chksRep.indexOf(idAsunto);
        chksRep.splice(index, 1);
    }
    chksRep.sort(function(a,b){return a - b});
}


