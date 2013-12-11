/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.gob.economia.scg.form;

import java.util.ArrayList;
import java.util.List;
import mx.gob.economia.scg.model.TipoDocumento;
import org.apache.struts.action.ActionForm;

/**
 *
 * @author rodrigo
 */
public class TipoDocumentoForm extends ActionForm{
    private TipoDocumento tipoDocumento;
    private List<TipoDocumento> tiposDocumento;

    /**
     * @return the tipoDocumento
     */

    public TipoDocumentoForm(){
        super();
        tipoDocumento=new TipoDocumento();
        tiposDocumento=new ArrayList<TipoDocumento>();
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * @param tipoDocumento the tipoDocumento to set
     */
    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    /**
     * @return the tiposDocumento
     */
    public List<TipoDocumento> getTiposDocumento() {
        return tiposDocumento;
    }

    /**
     * @param tiposDocumento the tiposDocumento to set
     */
    public void setTiposDocumento(List<TipoDocumento> tiposDocumento) {
        this.tiposDocumento = tiposDocumento;
    }


}
