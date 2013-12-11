/*
 * Catalogo para el tipo de documentos
 */


/*
 * Guarda o actuliza tipo de documento
 */
function definirAccion(){
    if($("#tipoDocumentoForm").validate().form()){
        if ($("#id_tipo_documento").val() == null
            || $("#id_tipo_documento").val() == '') {
            document.forms["tipoDocumentoForm"].method.value = "save";
            document.forms["tipoDocumentoForm"].submit();
        } else {
            document.forms["tipoDocumentoForm"].method.value = "update";
            document.forms["tipoDocumentoForm"].submit();
        }
    }
}

/*
 * Obtener datos por tipo de documento
 */
function traerDatos(id_tipo_documento){
    try{
        $.getJSON("../../tipo-documento.do?method=getTipoDocumento&idTipoDocumento="+id_tipo_documento,
            function(data){
                $("#firma").attr('value',data.firma);
                $("#tipo_documento").attr('value',data.tipo_documento);
                $("#id_tipo_documento").attr('value',data.id_tipo_documento);
                mostrar();
            });
    }catch(e){
        jAlert("No se pudieron cargar los datos","Error");
        cerrar();
    }
}

/*
 * Eliminara tipo de documento por id
 */
function eliminarTipo(id_tipo_documento){
    jConfirm("Esta seguro de dar de baja este Tipo?", "Baja de Tipo de Documento", function(ok){
        if(ok)
        {
            if(isUsado(id_tipo_documento)){
                $("#id_tipo_documento").attr('value',id_tipo_documento);
                document.forms['tipoDocumentoForm'].method.value = 'baja';
                document.forms['tipoDocumentoForm'].submit();
            }
        }
    });
}

/*
 * Mostrar formulario de tipo de documento
 */
function mostrar(){
    $("#add").show();
}

/*
 * Nuevo tipo de documento
 */
function nuevo(){
    mostrar();
    limpiar();
}

/*
 * Cancelar guardar/actualizar tipo de documento
 */
function cancelar(){
    $("#add").hide();
}

/*
 * Limpiar formulario de tipo de documento
 */
function limpiar(){
    $("#firma").attr('value','');
    $("#tipo_documento").attr('value','');
    $("#id_tipo_documento").attr('value','');
}

/*
 * Comprobar si el tipo de documento esta siendo usuado
 */
function isUsado(id_tipo_documento){
    try{
        $.getJSON("../../tipo-documento.do?method=isUsado&idTipoDocumento="+id_tipo_documento,
            function(data){
                if(data.result=='usado'){
                    jConfirm("�Este tipo de documento es usado actualmente,desea eliminarlo realmente?", "Baja de Tipo de Documento", function(ok){
                        if(ok)
                        {
                            eliminarDatos(id_tipo_documento);
                        }
                    });
                }else{
                    eliminarDatos(id_tipo_documento);
                }
                    
            });
    }catch(e){
        jAlert("No se pudieron cargar los datos","Error");
    }
}

/*
 * Implementacion de la funcion eliminar tipo de edocumento
 */
function eliminarDatos(id_tipo_documento){
    $("#id_tipo_documento").attr('value',id_tipo_documento);
    document.forms['tipoDocumentoForm'].method.value = 'baja';
    document.forms['tipoDocumentoForm'].submit();
}