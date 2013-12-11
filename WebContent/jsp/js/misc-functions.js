/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/*Agrega la clase de requerido*/
function updateTips( message , time, clase)
{
    var tips = $("." + clase);
    var tagHtml = $("<label class='error ui-estate-error'>" + message + "</label>");
    tips.addClass( "error" );
    tips.after(tagHtml);
    
    setTimeout(function() {
        tips.removeClass( "error", time );
        $("label.ui-estate-error").remove();
    }, time );
}



function checkLength( o, n, min, max ) {
    if ( o.val().length > max || o.val().length < min ) {
        o.addClass( "ui-state-error" );
        updateTips( "Length of " + n + " must be between " +
            min + " and " + max + "." );
        return false;
    } else {
        return true;
    }
}


/**
 * Configura el paginador
 */
function configurePaginate(paginas, pagina, namePostMethod, formFromSubmit, hiddenElementFromMehod)
{
    /* Configuración del paginador*/
    $("#paginador").paginate({
        count : paginas,
        start : pagina,
        display : 10,
        border : false,
        text_color : '#646463',
        background_color : 'none',
        text_hover_color : '#ba2025',
        background_hover_color : 'none',
        images : false,
        mouse : 'press',
        onChange :
        function(page){
            $("#pagina").attr('value',page);            
            $(hiddenElementFromMehod).attr("value",namePostMethod);
            $(formFromSubmit).unbind('submit').submit();//evita la validacion que tiene el formulario
        }
    });
}

function validatedListElement(elements)
{   
    var validate = 0;
    $.each(elements, function(){     
        if(  $.trim( $(this).val()).length > 0 && $(this).val() != "-1")//Si los input text estan vacio ó si los elementos select no tienen seleccionado
        {
            validate = validate || 1; // una opcion valida
        }
        else
        {            
            validate = validate || 0;
        }
    });    
    return validate;
}