/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.economia.scg.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import mx.gob.economia.scg.model.CriterioReporte;
import mx.gob.economia.scg.model.CriterioReporteVolante;
import mx.gob.economia.scg.model.Expediente;
import mx.gob.economia.scg.model.Reporte;
import mx.gob.economia.scg.model.Tema;
import org.apache.struts.action.ActionForm;
import org.apache.struts.util.LabelValueBean;

/**
 *
 * @author javier
 */
public class ReporteVolanteForm extends ActionForm {

    private List<Tema> temas;
    private List<Expediente> expedientes;
    private CriterioReporteVolante criterioReporte;
    private List<LabelValueBean> estatusList;
    private Tema tema;
    private Expediente expediente;

    public ReporteVolanteForm() {
        this.temas = new ArrayList<Tema>();
        this.expedientes = new ArrayList<Expediente>();
        this.criterioReporte = new CriterioReporteVolante();
    }

    /**
     * @return the temas
     */
    public List<Tema> getTemas() {
        return temas;
    }

    /**
     * @param temas the temas to set
     */
    public void setTemas(List<Tema> temas) {
        this.temas = temas;
    }

    /**
     * @return the expedientes
     */
    public List<Expediente> getExpedientes() {
        return expedientes;
    }

    /**
     * @param expedientes the expedientes to set
     */
    public void setExpedientes(List<Expediente> expedientes) {
        this.expedientes = expedientes;
    }

    /**
     * @return the criterioReporte
     */
    public CriterioReporteVolante getCriterioReporte() {
        return criterioReporte;
    }

    /**
     * @param criterioReporte the criterioReporte to set
     */
    public void setCriterioReporte(CriterioReporteVolante criterioReporte) {
        this.criterioReporte = criterioReporte;
    }

    /**
     * @return the estatusList
     */
    public List<LabelValueBean> getEstatusList() {
        return estatusList;
    }

    /**
     * @param estatusList the estatusList to set
     */
    public void setEstatusList(List<LabelValueBean> estatusList) {
        this.estatusList = estatusList;
    }

    /**
     * @return the tema
     */
    public Tema getTema() {
        return tema;
    }

    /**
     * @param tema the tema to set
     */
    public void setTema(Tema tema) {
        this.tema = tema;
    }

    /**
     * @return the expediente
     */
    public Expediente getExpediente() {
        return expediente;
    }

    /**
     * @param expediente the expediente to set
     */
    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }
}
