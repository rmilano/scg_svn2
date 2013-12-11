/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.economia.scg.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import mx.gob.economia.scg.model.Reporte;
import mx.gob.economia.scg.model.CriterioReporte;

/**
 *
 * @author gerardo
 */
public interface ReporteService
{

    public Connection getDataSourceConnection();

    public List<Reporte> getReporteGeneralAsuntos(CriterioReporte objCriterioReporte);

    public Reporte getReporteGeneralDetalle(CriterioReporte objCriterioReporte);

    public Map<String, List<Reporte>> getTotalAsuntoByCriterio(CriterioReporte objCriterioReporte);//por implementar

    public Reporte getConcentradoAsuntoEstatusArea(CriterioReporte criterio);

    public Integer countDetalle(CriterioReporte criterioReporte);

    public Reporte getAllResultReporteDetalle(CriterioReporte criterio);

}
