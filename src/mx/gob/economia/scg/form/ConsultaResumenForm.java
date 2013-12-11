/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.gob.economia.scg.form;

import org.apache.struts.action.ActionForm;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class ConsultaResumenForm extends ActionForm {
    private List<String> resumen_asuntos;

    public List<String> getResumen_asuntos() {
        return resumen_asuntos;
    }

    public void setResumen_asuntos(List<String> resumen_asuntos) {
        this.resumen_asuntos = resumen_asuntos;
    }
}
