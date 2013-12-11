
$(function(){
    crearArbol("");
    $("#buscar-instruccion").click(function(){
         
        if($("#nombre-area").val()==""&&$("#descripcion-instruccion").val()=="")
            jAlert("Debe capturar al menos un filtro","Información");
        else{
            $("#method").attr("value", 'listarInstrucciones');
            $("#instruccionForm").submit(); 
        }
    });
    $("#limpiar-instruccion").click(function(){         
        $("#method").attr("value", 'init');
        $("#instruccionForm").submit();       
    });
    
    $(".edit").click(function(){
       
        $.getJSON("../../instruccion.do?method=getInstruccion&id_instruccion="+this.id,
            function(data){
                $("#id_instruccion").attr('value',data.id_instruccion);
                $("#bufferInstruccion").attr('value',data.instruccion);
                $("#id_area").attr('value',data.id_area);
                $("#id_areaBuffer").attr('value',data.id_area);
                $("#areaBuffer").attr('value',data.area.area);
                $("#activo").attr('value',data.activo);
            });
    });

    $(".delete").click(function(){
        var idInstruccion = $(this).attr("id");
        $("#instruccion-editar").attr("value",idInstruccion);        
        jConfirm("<span class='message-confirm'>&#191;Esta seguro de eliminar la Instruccion&#63;</span>", "Eliminar Instruccion", function(ok){
            if(ok)
            {
                $("#id_instruccion").attr("value", idInstruccion);
                $("#method").attr("value", 'bajaInstruccion');
                $("#instruccionForm").submit(); 
            }
        });
    });
    
    //Configuracion del paginador
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
            document.forms[0].method.value='listarInstruccionesByPagina';
            document.forms[0].submit();
        }
    });
     
    $("#dialog-form-instruccion").dialog( {
        autoOpen : false,
        modal : true,
        buttons : {
            'Cancelar' : function() {
                $(this).dialog('close');
                $("#id_instruccion").attr('value',"");
                $("#instruccionDescripcion").attr('value',"");
                $("#id_areaBuffer").attr('value',"");
                $("#id_area").attr('value',"")
                $("#bufferInstruccion").attr('value',"");
                $("#areaBuffer").attr('value',"");
                $("#activo").attr('value',"1");
            },

            'Aceptar' : function() {     
                if($("#bufferInstruccion").val()==""||$("#areaBuffer").val()==""){
                    var mensaje=$("#bufferInstruccion").val()==""&&$("#areaBuffer").val()==""?
                    "Debe seleccionar un area y escribir una instrucción":$("#areaBuffer").val()==""?
                    "Debe seleccionar un area":"Debe capturar una instrucción";
                    jAlert(mensaje,"Mensaje");
                    
                }else{
                    
                    $("#activo").attr("value",1);
                    $("#id_area").attr("value",$("#id_areaBuffer").val());
                    $("#instruccionDescripcion").attr("value",$("#bufferInstruccion").val());
                    if($("#catalog-instruccion-form").validate().form())
                    {                    
                        if($("#id_instruccion").val()==""||$("#id_instruccion").val()==-1){
                            $("#id_instruccion").attr("value","");
                            $("#method").attr("value", 'saveInstruccion');
                            $("#instruccionForm").submit();
                        }else{
                            $("#method").attr("value", 'updateInstruccion');
                            $("#instruccionForm").submit(); 
                        }  
                    }     
                }      
            }
        },
        Cerrar : function() {
            $(this).dialog('close');
            $("#id_instruccion").attr('value',"");
            $("#instruccionDescripcion").attr('value',"");
            $("#id_areaBuffer").attr('value',"");
            $("#id_area").attr('value',"")
            $("#bufferInstruccion").attr('value',"");
            $("#areaBuffer").attr('value',"");
            $("#activo").attr('value',"1");
        }
    });    
    // muestra el formulario para agregar area
    $(".icon-instruccion").click(function(){        
        $("#dialog-form-instruccion").dialog('open');
    });
    
});
