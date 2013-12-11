/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.economia.scg.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import mx.gob.economia.scg.model.Asunto;
import mx.gob.economia.scg.model.CriterioReporteVolante;

/**
 *
 * @author javier
 */
public interface ReporteVolanteService
{

    public List<Asunto> listAsuntos(CriterioReporteVolante criterio);

    public void generarVolantesCorrespondenciaPDF(HttpServletRequest request, Asunto asunto);

    public void generarVolanteCorrespondenciaUltimoDetallePDF(HttpServletRequest request, Asunto asunto);

    public void generarVolantesCorrespondenciaPDF_Atendido(HttpServletRequest request, Asunto asunto);

}
