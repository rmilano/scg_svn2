/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(function(){    
    //Agregar remitente: si se ha seleccionado un area, obtener los empleados correspondientes
    $(".scg-remitente-list-areas").change(function(){
        var idArea = $(".scg-remitente-list-areas :selected").val();     
        if(idArea > -1)
        {
            loadEmployed(".ui-remitente-listado-area .scg-listado-remitente", idArea,"remi");
        }
    });
    //Agregar destinatarios: si se ha seleccionado un area, obtener los empleados correspondientes
    $(".scg-list-areas").change(function(){
        var idArea = $(".scg-list-areas :selected").val();     
        if(idArea > -1)
        {
            loadEmployed(".ui-listado-area .scg-listado-destinatario", idArea,"dest");
        }
    });

    // En los con copia para: si se ha seleccionado un area, obtener los empleados correspondientes
    $(".scg-list-areas-ccp").change(function(){
        var idArea = $(".scg-list-areas-ccp :selected").val();
        if(idArea > -1)
        {
            loadEmployed(".ui-listado-area-ccp .scg-listado-ccp", idArea,"dest");
        }
    });

    // El boton agregar en los destinatarios
    $("#scg-add-destinatario").click(function(){
        var idEmpleado = $(".ui-listado-area .scg-listado-destinatario :selected").attr("value"),
        nombreEmpleado = $(".ui-listado-area .scg-listado-destinatario :selected").text();
        var idAreaEmpleadoCaptura = $("#empleado-captura-id-area").val();          
        if(idEmpleado == null || idEmpleado == '' || idEmpleado < 0)
            updateTips("Este campo es requerido", 500, "scg-listado-destinatario");
        else // si el remitente y el destinatario son los mismos y ademas si los elementos existen en el dom del documento (length > 0), entonces:
            if( ($("#idEmpleadoDest").val() == $("#idEmpleadoRemiSelect").val()) && $("#idEmpleadoDest").length > 0 && $("#idEmpleadoRemiSelect").length > 0)
            {
                alert('->' + $("#idEmpleadoDest").length  + '->' + $("#idEmpleadoRemiSelect").length);
                jAlert("El remitente y el destinatario no deben ser la misma persona.", "Atencion");        
                return;
            }        
            else
                agregarDest(idEmpleado, nombreEmpleado,idAreaEmpleadoCaptura);     
    });
    // El boton agregar con copia para
    $("#scg-add-ccp").click(function(){
        var idEmpleado = $(".ui-listado-area-ccp .scg-listado-ccp :selected").attr("value"),
        nombreEmpleado = $(".ui-listado-area-ccp .scg-listado-ccp :selected").text();        
        if(idEmpleado == null || idEmpleado == '' || idEmpleado < 0)
            updateTips("Este campo es requerido", 500, "scg-listado-ccp");
        else
            agregarCcp(idEmpleado, nombreEmpleado);
    });
});