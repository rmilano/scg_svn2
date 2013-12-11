/*
 * Script para el modulo de reportes
 */

$( function() {
    crearArbol("");
    
    //Limpiar criterios de busqueda
    $("#consulta-limpiar").click(
        function() {            
            document.forms[0].method.value = 'inicio';
            document.forms[0].submit();
        });

   //Limpiar criterios de busqueda detlle
    $("#consulta-limpiar-detalle").click(
        function() {
            document.forms[0].method.value = 'reporteGeneralDetalle';
            document.forms[0].submit();
        });

    /* Realizar reporte general */
    $("#reporteFormGeneral").click( function() {
        if ($("#reporteForm").validate().form()) {
            $.blockUI();
            $("#name-post-method").attr("value", 'reporteGeneralAsuntos');
            $("#reporteForm").submit();            
        }
    });
     /* se ejecuta cuando se hace click en alguna liga del reporte general */
    $(".reporte-status").click( function() {

        $("#idestatus").attr("value", $(this).attr("type"));
        $("#idareasubconsulta").attr("value", $(this).attr("itemprop")); 
        $("#arbol-reporte-general .id").attr("value", $(this).attr("itemprop")); 
        $.blockUI();
        $("#name-post-method").attr("value", 'reporteGeneralDetalle');
        $("#reporteForm").submit();            
        
    });  
     /* Realizar reporte del detalle */
    $("#reporteFormDetalle").click( function() {
        if ($("#reporteForm").validate().form()) {
            $.blockUI();
            $("#nuevaconsulta").attr('value',"si");
            $("#name-post-method").attr("value", 'reporteGeneralDetalle');
            $("#idareasubconsulta").attr("value", $("#arbol-reporte-general .id").val()); 
            $("#reporteForm").submit();            
        }
    });    
    /* Configuracion del paginador */
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
            $("#nuevaconsulta").attr('value',"no");            
            document.forms[0].method.value='reporteGeneralDetalle';
            document.forms[0].submit();
        }
    });    
    
});

/* Exportar reporte a PDF */
function concentradoArea() {
    document.forms[0].action = '../../reportes.do?method=concentradoArea';
    document.forms[0].submit();
}