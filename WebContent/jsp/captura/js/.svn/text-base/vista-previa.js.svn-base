/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Listenner del documento
 */
$(function(){
    // si se guarda el asunto
    $("#guardar").click( function()
    {
        $.blockUI();
        document.forms['capturaForm'].action = '../../captura.do?method=guardarAsunto';
        document.forms['capturaForm'].submit();
    });
    
    // si se modifica el asunto
    $("#modificar").click( function()
    {
        $.blockUI();
        document.forms['capturaForm'].action = '../../captura.do?method=modificarAsunto';
        document.forms['capturaForm'].submit();
    });
 
    $("#guardar-enviar-detinatarios").click( function()
    {
        $.blockUI();
        document.forms['capturaForm'].action = '../../captura.do?method=guardarAsuntoEnviarDest';
        document.forms['capturaForm'].submit();
    });
    
    // si se modifica el asunto
    $("#modificar-enviar-detinatarios").click( function()
    {
        $.blockUI();
        document.forms['capturaForm'].action = '../../captura.do?method=modificarAsuntoEnviarDest';
        document.forms['capturaForm'].submit();
    });
    
    
    $("#firmar").click( function()
    {
        $.blockUI();
        document.forms['capturaForm'].action = '../../captura.do?method=getFirmaAsunto';
        document.forms['capturaForm'].submit();
    });

    // si desea editar el asunto nuevamente
    $("#regresar").click(function(){
        $.blockUI();        
        $("#post-method-name").attr("value","editarAsunto");
        $("#capturaForm").submit();                
    });
    // si se actualiza el asunto
    $("#scg-actualiza-asunto").click(function(){
        $.blockUI();
        document.forms['capturaForm'].action = '../../captura.do?method=modificarAsunto';
        document.forms['capturaForm'].submit();
    });
    //Aprovar un asunto del lado del revisor
    $("#scg-aprovado").click(function(){
        $.blockUI();
        document.forms['capturaForm'].action = '../../captura.do?method=aprovarAsunto';
        document.forms['capturaForm'].submit();
    });
    //Aprovar un asunto del lado del supervisor(remitente)
    $("#scg-supervisado").click(function(){
        $.blockUI();
        document.forms['capturaForm'].action = '../../captura.do?method=asuntoSupervisado';
        document.forms['capturaForm'].submit();
    });
    //Guardar y enviar a revision
    $("#scg-enviar-revision").click(function(){
        $.blockUI();
        document.forms['capturaForm'].action = '../../captura.do?method=guardarEnviarRevision';
        document.forms['capturaForm'].submit();
    });
    // Si desea generar o no el volante de correspondencia
    if($("#volante-correspondencia-session").length != 0)
    {
        var text = "Se guardo la captura con el Folio" + $("#folio").val() + " . \n" +  $("#volante-correspondencia-session").text();
        jConfirm(text, "Volante de Correspondencia", function(ok)
        {
            if(ok)
            {
                window.open('../../asunto.do?method=generarVolanteCorrespondenciaFromSession','Volante','width=400,height=200');
                //window.open('../../asunto.do?method=generarVolanteCorrespondenciaFromSession','Help_on_units_fie ld','width=400,height=200,left=200,top=200,resizable');
                location.href='../../consulta-captura.do?method=inicio';
            }
            else
                location.href='../../consulta-captura.do?method=inicio';
        });

    }
});