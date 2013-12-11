/* 
 * El script configura los efectos de redondeo de divs, los calendarios,
 * validacion de formularios...
 * 
 */
$(function(){
    //Los bordes de los divs redondeados
    $(".corner").corner();
    // Los borde con un ancho especifico
    $(".border").corner("round 8px").parent().css('padding', '2px').corner("round 10px");
    //Las validaciones por cada formulario
    $("form").validate();
    $(".menu_principal .fg-button").mouseover(function(){
        $(this).click();
    });

    //el idioma para  datepicker
    $.datepicker.setDefaults($.datepicker.regional['es']);
    var date = new Date();
    $(".calendario").datepicker( {
        showOn :'both',
        buttonText :'',
        buttonImage :'../../imagenes/jquery-ui/calendar.png',
        buttonImageOnly :true,
        changeMonth :true,
        changeYear :true,
        dateFormat :'dd/mm/yy',
        beforeShowDay: $.datepicker.noWeekends        
    });    
    $(".3m-restricted-calendar").datepicker( {
        showOn :'both',
        buttonText :'',
        buttonImage :'../../imagenes/jquery-ui/calendar.png',
        buttonImageOnly :true,
        changeMonth :true,
        changeYear :true,
        dateFormat :'dd/mm/yy',
        minDate: "-3M 0D", 
        maxDate: '0d',
        beforeShowDay: $.datepicker.noWeekends        
    });    
    $(".12m-restricted-calendar").datepicker( {
        showOn :'both',
        buttonText :'',
        buttonImage :'../../imagenes/jquery-ui/calendar.png',
        buttonImageOnly :true,
        changeMonth :true,
        changeYear :true,
        dateFormat :'dd/mm/yy',
        minDate: "-12M 0D", 
        maxDate: '0d',
        beforeShowDay: $.datepicker.noWeekends        
    });    
    
    $(".calendarioBefore").datepicker( {
        showOn :'both',
        buttonText: '',        
        buttonImage :'../../imagenes/jquery-ui/calendar.png',
        buttonImageOnly :true,
        changeMonth :true,
        changeYear :true,
        dateFormat :'dd/mm/yy',
        maxDate :'0d',        
        beforeShowDay: $.datepicker.noWeekends       
    });    

    $(".calendarioAfter").datepicker( {
        showOn :'both',
        buttonText :'',        
        buttonImage :'../../imagenes/jquery-ui/calendar.png',
        buttonImageOnly :true,
        changeMonth :true,
        changeYear :true,
        dateFormat :'dd/mm/yy',
        minDate :'0d',
        beforeShowDay: $.datepicker.noWeekends        
    });
    //la fecha actual para el calendario
    $(".calendario-set-current-day").datepicker("setDate",date);
    // Peliula de imagenes en la página de Autenticacion.
    $("#chained").scrollable({
        circular: true,
        mousewheel: true
    }).navigator().autoscroll({
        interval: 3000
    });
    $(".regresar").click(function(){
        history.back(-1);
    }
    );

    //determinar el arbol a mostrar: externo o interno
    $(".radio-arbol-interno").click(function(){        
        var idDiv = $(this).attr("title"),
        classDialog = $(this).attr("alt");
        $("#" + idDiv).attr("title",classDialog);//relacionar el div de arboles con el dialogo de arbol correspondiente
    });
    $(".radio-arbol-externo").click(function(){
        var idDiv = $(this).attr("title"),
        classDialog = $(this).attr("alt");
        $("#" + idDiv).attr("title",classDialog);//relacionar el div de arboles con el dialogo de arbol correspondiente
    });

 
    $(".only-numeric").numeric();

    $(".only-alphanumeric").alphanumeric();

    $('.only-chars-file-name').alphanumeric({
        ichars:'.,#%&*|\/:><?�"'
    });

    // 	el acordion
    $('.accordion .head').click( function() {
        $(this).next().toggle('slow');
    }).next().hide();
	
    // expandir el acordion del historico
    $(".expandable-accordion").click( function() {
        $(".ui-accordion-content").show();
        return false;
    });
    
    // contraer el acordion
    $(".collapsable-accordion").click( function() {
        $(".ui-accordion-content").hide();
        return false;
    });
    //poner los valores predeterminados en las cajas de texto
    configureTextDefault();
    // los autocomplete
    $('.selector-autocomplete').select_autocomplete();
    //el time picker
    $('.date-time-picker').datetimepicker({
        timeOnlyTitle: 'Marca de tiempo',
        timeText: 'Tiempo',
        hourText: 'Hora',
        minuteText: 'Мinuto',
        secondText: 'Segundo',
        currentText: 'Actual',
        closeText: 'Aceptar',
        hour: 9
    });
});

/*
 * Configura los campos definidos por una clase con un valor por default
 */
function configureTextDefault()
{
    if ($("input.rfc").val()==""){
        $("input.rfc").attr("value","AAAA000000").select();
    }

}
/*
 * Implementacion del contador de caracteres
 */
function contador(campo, cuentacampo, limite) {
    if (campo.value.length > limite)
        campo.value = campo.value.substring(0, limite);
    else
        cuentacampo.value = limite - campo.value.length;
}

/**
 * Agreagar los calendarios de reciente creacion
 */

function setCalendar(element)
{
    $(element).datepicker( {
        showOn :'both',
        buttonText :'',
        buttonImage :'../../imagenes/jquery-ui/calendar.png',
        buttonImageOnly :true,
        changeMonth :true,
        changeYear :true,
        dateFormat :'dd/mm/yy'
    });
}