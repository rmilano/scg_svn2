/*
 * El listener del documento
 */

$( function() {
	$("#firmaBtn").click( function() {
		
		alert ("Inicio proceso obtener firmas");
		alert ("Firmante: " + document.firma.CommonName);
		alert ("Firma: " + document.firma.SimpleSign);
		/*var signs = document.firma.Pkcs7Sign;
		alert ("firma documento: " + signs[0]);*/
		
		$("#firma").val(document.firma.CommonName);
		$("#firmante").val(document.firma.SimpleSign);
		
		if($("#firma").val()!='' && $("#firmante").val()!=''){
			document.forms['capturaForm'].action = '../../captura.do?method=firmarAsunto';
            document.forms['capturaForm'].submit();
		}else{
			alert('Error');
		}    
			
	});
});
