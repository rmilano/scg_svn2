/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.economia.scg.form;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;
import mx.gob.economia.scg.model.CriterioReporte;
import mx.gob.economia.scg.model.Expediente;
import mx.gob.economia.scg.model.Reporte;
import mx.gob.economia.scg.model.Tema;
import org.apache.struts.action.ActionForm;
import org.apache.struts.util.LabelValueBean;

/**
 *
 * @author carlos.rivera
 */
public class ReporteForm extends ActionForm
{

    private List<Tema> temas;
    private List<Expediente> expedientes;
    private Map<String, String> estatusMap;
    private CriterioReporte criterioReporte;    
    private Reporte reporteAsuntos;
    private Tema tema;
    private Expediente expediente;
    private List<LabelValueBean> estatusList;
    private String nuevaConsultaDetalle;

    public ReporteForm()
    {                        
        this.temas = new ArrayList<Tema>();
        this.expedientes = new ArrayList<Expediente>();        
        this.reporteAsuntos = new Reporte();
        this.criterioReporte  = new CriterioReporte();
        this.tema = new Tema();
        this.expediente = new Expediente();
        this.nuevaConsultaDetalle = "";
    }

    public List<Expediente> getExpedientes()
    {
        return expedientes;
    }

    public void setExpedientes(List<Expediente> expedientes)
    {
        this.expedientes = expedientes;
    }

    public List<Tema> getTemas()
    {
        return temas;
    }

    public void setTemas(List<Tema> temas)
    {
        this.temas = temas;
    }


    public Map<String, String> getEstatusMap()
    {
        estatusMap = new LinkedHashMap<String, String>();        
        estatusMap.put("0", "PENDIENTE");
        estatusMap.put("1", "PENDIENTE");
        estatusMap.put("2", "ATENDIDO");
        estatusMap.put("3", "CONCLUIDO");
        estatusMap.put("4", "EN TIEMPO");
        estatusMap.put("5", "POR VENCER");
        estatusMap.put("6", "VENCIDO");
        return estatusMap;
    }

    public void setEstatusMap(Map<String, String> estatusMap)
    {
        this.estatusMap = estatusMap;
    }

    public CriterioReporte getCriterioReporte()
    {
        return criterioReporte;
    }

    public void setCriterioReporte(CriterioReporte criterioReporte)
    {
        this.criterioReporte = criterioReporte;
    }

    /**
     * @return the reporteAsuntos
     */
    public Reporte getReporteAsuntos()
    {
        return reporteAsuntos;
    }

    /**
     * @param reporteAsuntos the reporteAsuntos to set
     */
    public void setReporteAsuntos(Reporte reporteAsuntos)
    {
        this.reporteAsuntos = reporteAsuntos;
    }

    /**
     * @return the tema
     */
    public Tema getTema()
    {
        return tema;
    }

    /**
     * @param tema the tema to set
     */
    public void setTema(Tema tema)
    {
        this.tema = tema;
    }

    /**
     * @return the expediente
     */
    public Expediente getExpediente()
    {
        return expediente;
    }

    /**
     * @param expediente the expediente to set
     */
    public void setExpediente(Expediente expediente)
    {
        this.expediente = expediente;
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
     * @return the nuevaConsultaDetalle
     */
    public String getNuevaConsultaDetalle() {
        return nuevaConsultaDetalle;
    }

    /**
     * @param nuevaConsultaDetalle the nuevaConsultaDetalle to set
     */
    public void setNuevaConsultaDetalle(String nuevaConsultaDetalle) {
        this.nuevaConsultaDetalle = nuevaConsultaDetalle;
    }
}
