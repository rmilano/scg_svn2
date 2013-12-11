/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Listenner del documento
 */
$(function(){
    /* Crea los dialogos*/
    $("#dialog-form-area").dialog( {
        autoOpen : false,
        modal : true,
        buttons : {
            'Cancelar' : function() {
                $(this).dialog('close');
            },

            'Aceptar' : function() {                
                var nombre = $("#dialog-form-area #descripcion").val(), clave = $("#dialog-form-area #clave").val(),
                idAreaPadre = $("#dialog-form-area #arbol-catalogo-areas input.id").val(),
                tipo = $("#dialog-form-area .radio:checked").val();                
                if($("#catalog-area-form").validate().form())
                {                    
                    insertArea(nombre, clave, idAreaPadre, tipo);//guardar empleado                    
                    $(this).dialog('close');//cerrar ventana
                }                
            }
        },
        Cerrar : function() {
            $(this).dialog('close');
        }
    });    
    // muestra el formulario para agregar area
    $(".icon-area").click(function(){        
        $("#dialog-form-area").dialog('open');
        $("#dialog-form-area input:text").attr("value","");
    });

    // muestra el formulario para agregar Tema
    $(".icon-tema").click(function(){        
        $("#dialog-form-tema").dialog('open');  
        $("#dialog-form-tema input:text").attr("value","");
    });
    // muestra el formulario para agregar Subtema
    $(".icon-subtema").click(function(){        
        $("#dialog-form-subtema").dialog('open');  
        $("#dialog-form-subtema input:text").attr("value","");// si vuelve abrir el dialogo que no aparezca el antrior
        $("#select-catalogo-temas option[value='-1']").attr('selected',true);
    });


    /* Crea el formulario para los empleados*/
    $("#dialog-form-empleado").dialog( {
        autoOpen : false,
        modal : true,
        buttons : {
            'Cancelar' : function() {
                $(this).dialog('close');
            },

            'Aceptar' : function() {
                if($("#catalog-empleado-form").validate().form())
                {
                    var idArea = $("#dialog-form-empleado #arbol-catalogo-empleado input.id").val(),
                    nombre = $("#dialog-form-empleado #nombre").val(),
                    paterno = $("#dialog-form-empleado #paterno").val(),
                    materno = $("#dialog-form-empleado #materno").val(),
                    email = $("#dialog-form-empleado #email").val(),
                    rfc = $("#dialog-form-empleado #rfc").val(),
                    puesto = $("#dialog-form-empleado #puesto").val();
                    insertEmployed(idArea, nombre, paterno, materno, email, rfc, puesto);                    
                    $(this).dialog('close');//cerrar ventana
                }
            }
        },
        Cerrar : function() {
            $(this).dialog('close');
        }
    });
    /* Crea el formulario para los temas*/
    $("#dialog-form-tema").dialog( {
        autoOpen : false,
        modal : true,
        buttons : {
            'Cancelar' : function() {
                $(this).dialog('close');
            },

            'Aceptar' : function() {
                if($("#catalogo-tema-form").validate().form())
                {   
                    insertTema($("#catalogo-tema-form #nombre-tema").val(),$("#catalogo-tema-form #select-empleados-revisor").val(),"select-temas");
                    $(this).dialog('close');//cerrar ventana
                }
            }
        },
        Cerrar : function() {
            $(this).dialog('close');
        }
    });
    /* Crea el formulario para los subtemas*/
    $("#dialog-form-subtema").dialog( {
        autoOpen : false,
        modal : true,
        buttons : {
            'Cancelar' : function() {
                $(this).dialog('close');
            },

            'Aceptar' : function() {
                if($("#catalogo-subtema-form").validate().form())
                {     
                    var idTemaPadre = $("#catalogo-subtema-form #select-catalogo-temas").val(),
                    descripcionSubtema = $("#catalogo-subtema-form #nombre-subtema").val();
                    insertSubtema(idTemaPadre, descripcionSubtema);
                    $(this).dialog('close');//cerrar ventana
                }
            }
        },
        Cerrar : function() {
            $(this).dialog('close');
        }
    });
    $(".icon-empleado").click(function(){        
        $("#dialog-form-empleado").dialog('open');
        $("#dialog-form-empleado input").attr("value","");
        configureTextDefault();
    });
    // Elimiar las clases requerido para email, rfc y puesto cuando sean empleados externos
    $("#dialog-form-empleado .radio-arbol-interno").click(function(){        
        if(!$("#dialog-form-empleado #email").hasClass('required'))
            $("#dialog-form-empleado #email").addClass("required");
        if(!$("#dialog-form-empleado #rfc").hasClass('required'))
            $("#dialog-form-empleado #rfc").addClass("required");
    });
    $("#dialog-form-empleado .radio-arbol-externo").click(function(){        
        $("#dialog-form-empleado #email").removeClass("required");
        $("#dialog-form-empleado #rfc").removeClass("required");        
    });
});

/*
 * Direcciona el flujo de la aplicacion al metodo indicado
 */
function redirect(method)
{
    $.blockUI();
    document.forms['capturaForm'].method.value = method;
    document.forms['capturaForm'].submit();
}

/*
 * Registrar unan nueva area
 */
function insertArea(nombre, clave, idAreaPadre, tipo)
{
    var url = "../../areas.do?method=saveAreaAjax";
    $.post(url,
    {
        nombre: nombre,
        clave: clave,
        idAreaPadre: idAreaPadre,
        tipo: tipo
    }, function(data){
    
        });
}

/*
 * Registrar nuevo empleado
 */
function insertEmployed(idArea, nombre, paterno, materno, email, rfc, puesto)
{
    var url = "../../empleados.do?method=saveEmpleadoAsin";
    $.post(url,
    {        
        idArea: idArea,
        nombre: nombre,
        paterno: paterno,
        materno: materno,
        email: email,
        rfc: rfc,
        puesto: puesto
    }, function(data){

        });
}

/*
 * Registrar nuevo Tema
 */
function insertTema(tema, idEmpleadoRevisor,idDom)
{
    var url = "../../temas.do?method=saveTemaAsync";
    $.post(url,
    {        
        descripcionTema: tema,
        idEmpleadoRevisor: idEmpleadoRevisor
    }, function(data){
        $("#" + idDom).append("<option value='"+data.id_tema+"'>"+data.tema+"</option>");
        $("#dialog-form-subtema #select-catalogo-temas").append("<option value='"+data.id_tema+"'>"+data.tema+"</option>");
        //$.create('option', {'value': data.id_tema}, data.tema).appendTo('#' + idDom);
    });
}
/*
 * Registrar nuevo Subtema
 */
function insertSubtema(idTemaPadre, descripcionSubtema)
{
    var url = "../../temas.do?method=saveSubTemaAsync";
    $.post(url,
    {        
        idTemaPadre:idTemaPadre,
        descripcionSubtema: descripcionSubtema        
    }, function(data){
        //$("#" + idDom).append("<option value='"+data.id_tema+"'>"+data.tema+"</option>");
        //$.create('option', {'value': data.id_tema}, data.tema).appendTo('#' + idDom);
    });
}