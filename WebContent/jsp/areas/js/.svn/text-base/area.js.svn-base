$(function(){
    /* Inactiva el tag de area */
    $(".ui-button").click(function(){
        $("#area").attr('readonly',true);
        cerrar('Descripcion');
        cerrar('DatosArea');
    });
});

/* Direcciona la pagina al metodo inicio de formulario*/
function inicio(){
    $("#area").attr("value",'');
    document.forms['areaForm'].action = '../../areas.do?method=init';
   /* document.forms['areaForm'].method.value = 'init';*/
    document.forms['areaForm'].submit();
}

function cancelar(){
    cerrar('Descripcion');
    cerrar('DatosArea');
    $("#ayudaEditar").hide("slow");
    $("#ayudaAgregar").hide("slow");
    $("#divAddArea").hide("slow");
}

function carga(){
    var clave=$("#id").val();
    if(clave!=""&&clave!=null){
        mostrar("DatosArea");
    }
}
function bajaArea(){
    if($("#area").val()==null ||$("#area").val()==''){
        jAlert("Seleccione un Area","Informaci&oacute;n");
    }
    else{
        jConfirm("&#191;Esta seguro de dar de baja el &aacute;rea "+$("#area").val()+" " +
            " &#63;", "Baja de Area", function(ok){
                if(ok)
                {
                    isUsado();
                }
            });
    }
}
function definirAccion() {
    var metodo = $("#metodo").val();
    if(metodo=="" || metodo==null){
        $("#metodo").attr("class",'');
        $("#areaModificar").attr("class",'');
        if($("#areaForm").validate().form())
        {
            $("#area").attr("value",$("#areaModificar").val());
            /*document.forms['areaForm'].method.value = 'updateArea';*/
            document.forms['areaForm'].action = '../../areas.do?method=updateArea';
            document.forms['areaForm'].submit();
            cerrar('DatosArea');
            cerrar('Descripcion');
            $("#area").attr("value",'');
        }
		
    }else{
        $("#metodo").attr("class",'required');
        $("#areaModificar").attr("class",'required');
        if($("#areaForm").validate().form())
        {
            $("#metodo").attr("value",$("#areaModificar").val());
            /*document.forms['areaForm'].method.value = 'saveArea';*/
            document.forms['areaForm'].action = '../../areas.do?method=saveArea';
            document.forms['areaForm'].submit();
            cerrar('DatosArea');
            cerrar('Descripcion');
        }
    }
   
}

function mostrarCampos(){
    if($("#area").val()==null ||$("#area").val()==''){
        jAlert("Seleccione un &Aacute;rea","Informaci&oacute;n");
    }
    else{
        $("#clave").attr('value',"")
        $("#area").attr('readonly',true);
        mostrar('Descripcion');
        mostrar('DatosArea');
        $("#ayudaEditar").hide("slow");
        $("#ayudaAgregar").show("slow");
        $("#divAddArea").show("slow");
        $("#areaModificar").attr("value","");
        $("#metodo").attr("value","INSERTA"); // prepara para generar un área nueva
    }
}

function mostrar(componente) {
    $('#'+componente).show();

}
function cerrar(componente) {
    $('#'+componente).hide();
}

function cargarArea() {
    if($("#area").val()==null ||$("#area").val()==''){
        jAlert("Seleccione un &Aacute;rea","Informaci&oacuten");
    }
    else{
        cerrar('Descripcion');
        mostrar('DatosArea');
        $("#area").removeAttr("readonly");
        $("#divAddArea").show("slow"); // Contiene la tabla para Agregar y Editar
        $("#ayudaAgregar").hide("slow");
        $("#ayudaEditar").show("slow");
        $("#DatosArea").show("slow");
        mostrar('Descripcion');
        $("#metodo").attr("value",""); // prepara para actualizar el área actual
        try{
            $.getJSON("../../areas.do?method=getArea&idArea="+$("#id").val(),
                function(data){
                    $("#area").attr('value',data.area);
                    $("#id_area_padre").attr('value',data.id_area_padre);
                    $("#id").attr('value',data.id_area);
                    $("#clave").attr('value',data.clave);
                    $("#tipo").attr('value',data.tipo);
                    $("#activo").attr('value',data.activo);
                    $("#metodo").attr('value','');
                    $("#areaModificar").attr('value',data.area);
                    $("#unidad-admva").removeAttr("checked");
                    $("#listado-area").removeAttr("checked");                    
                    if(data.unidad_administrativa == "1")
                    $("#unidad-admva").attr("checked",true);
                    if(data.listado_area == "1")
                    $("#listado-area").attr("checked",true);
                    if(data.envio_correo == "1")
                    $("#correoSi").attr("checked",true);
                });
        }catch(ex){
            jAlert("Seleccione un &Aacute;rea","Informaci&oacute;n");
        }
    }
} 

function isUsado(){
    $.getJSON("../../areas.do?method=isUsada&idArea="+$("#id").val(),
        function(data){
            if(data.result=="usado"){
                jConfirm("Algunos empleados pertencen a esta &aacute;rea. Esta seguro de eliminarla? ", "Baja de &Aacute;rea",
                    function(ok){
                        if(ok)
                        {
                            eliminarArea();
                        }
                    });
            }else{
                eliminarArea();
            }
        });
    
}
function eliminarArea(){
    /*document.forms['areaForm'].method.value = 'bajaArea';*/
    document.forms['areaForm'].action = '../../areas.do?method=bajaArea';
    cargarArea();
    cerrar('DatosArea');
    $("#metodo").attr("value",'prueba');
    document.forms['areaForm'].submit();
}