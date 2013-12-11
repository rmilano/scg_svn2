/*
 * Script para el modulo de reportes
 */

$( function() {
    crearArbol("empleado");
    /* Manda llamar a la pantalla de confirmacion */
    $("#reporteVolanteFormDetalle").click( function() {
        if ($("#reporteVolanteForm").validate().form()) {
            $("#name-post-method").attr("value", 'confirmaGeneracionArchivos');
            $("#reporteVolanteForm").submit();            
        }
    });    
    /* regresa a la pantalla de captura de criterios */
    $("#reporteVolanteFormRegresar").click( function() {
       
            $("#name-post-method").attr("value", 'inicio');
            $("#reporteVolanteForm").submit();            
        
    });      
    $("#reporteVolanteFormGenerar").click( function() {
       
            $("#name-post-method").attr("value", 'iniciaGeneracionArchivos');
            $("#reporteVolanteForm").submit();            
        
    });      
});
