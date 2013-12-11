/*
 * Listenner del documento
 */
var msg ="";
$(function(){
    crearArbol("");
    $("#guardar-empleados").click(function(){
        var fields = $("input[name='selectedRoles']").serializeArray();              
        if($("#form-empleado-editar").validate().form())
        {            
            if ($("#pass").val()!=$("#repass").val()){
                alert("El password no coincide");
            }
               if (fields.length == 0)
              {
                  alert("Debe seleccionar al menos un rol");
              }
            else{
                $("#method").attr("value","actualizarEmpleado");
                $("#form-empleado-editar").submit();
            }
        }
    });
    $(".radio-arbol-externo").click(function(){
        $(".sp-correo").text("");
        $(".email").removeClass("required");
    });
    $(".radio-arbol-interno").click(function(){
        $(".sp-correo").text("");
        $(".email").removeClass("required");        
        $(".sp-correo").text("*");
        $(".email").addClass("required");
    });
});


