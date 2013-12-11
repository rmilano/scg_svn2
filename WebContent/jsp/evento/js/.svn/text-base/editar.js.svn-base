/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


$(function(){    
   
    $(".edit").click(function(){
        var idEvento = $(this).attr("id");
        $("#evento-editar").attr("value",idEvento);
        document.forms[0].method.value='editarEvento';
        document.forms[0].submit();      
    });
    $("#actualizar-evento").click(function(){
        if($("#descripcion-evento").val==""||$("#nombre-area").val()==""){
            var mensaje="Se produjo el siguiente error:";
            mensaje=mensaje+($("#descripcion-evento").val==""?"\n*Debe capturar un evento":"");
            mensaje=mensaje+($("#nombre-area").val==""?"\n*Debe seleccionar un área":"");
            jAlert(mensaje,"");
        }else{
        document.forms[0].method.value='updateEvento';
        document.forms[0].submit(); 
        }
    });
    $("#cancelar").click(function(){
        
        document.forms[0].method.value='cancelar';
        document.forms[0].submit();
    });
    
});

// muestra el formulario para agregar area
    $(".icon-instruccion").click(function(){
        $("#dialog-form-instruccion").dialog('open');
    });