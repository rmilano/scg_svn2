/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


$(function(){
    crearArbol("");
    $("#crear-evento").click(function(){
        if($("#descripcion-evento").val()==""||$("#nombre-evento").val()==""){
            var mensaje="Se produjo el siguiente error:";
            mensaje=mensaje+($("#descripcion-evento").val()==""?"\n*Debe capturar un evento":"");
            mensaje=mensaje+($("#nombre-evento").val()==""?"\n*Debe seleccionar un área":"");
            jAlert(mensaje,"Información");
        }else{
            document.forms[0].method.value='guardarEvento';
            document.forms[0].submit();
        }
    });
    $("#cancelar-evento").click(function(){

        document.forms[0].method.value='cancelar';
        document.forms[0].submit();
    });
});