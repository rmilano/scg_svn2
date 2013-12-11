/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(function(){
    $("#select-temas").change(function(){
        getTemasFromJson($(this).val());
    });
});

function getTemasFromJson(idTema)
{
    var url = "../../captura.do?method=getTemasAsinc";
    $.post(url,
    {
        idTema:idTema
    }
    , function(data)
    {        
        var options = "", evento = "", calendar = "";
        $.each(data[0], function(key, val){            
            evento = key;
            if(key == "0")// si no tiene eventos, entonces obtenemos los probable hijos
                {
                    $.each(val, function(key,data){
                       options = options + "<option value='"+ data.id_tema + "'>"+ data.tema +"</option>";
                    });                    
                }
        });
        $("#evento-subtemas").children().remove();        
        if(evento == "1")// si tiene eventos muestro el calendario
        {
            calendar = "<input type='text' id='calendario-evento' name='asunto.fh_eventoDDMMYYYY' class='calendario' />";
            $("#evento-subtemas").append(calendar);
            setCalendar($("#calendario-evento"));
        }
        if(options.length > 0 )// Si no tiene eventos y ademas tienes hijos, los muestro
        {
            options = "<option selected value='-1'>Seleccione una opci&oacute;n</option>" + options;
            options = "<select class='ui-widget-content select-subtemas' name='asunto.subTema.id_tema'>" + options + "</select>";
            $("#evento-subtemas").append(options);
        }else{         
        	options = "<input type='hidden' name='asunto.subTema.id_tema' value='-1'/>";
        	$("#evento-subtemas").append(options);
        }
    }
    , "json");
}

