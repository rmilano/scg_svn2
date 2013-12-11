/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.economia.scg.persistence;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import mx.gob.economia.scg.model.Reporte;
import mx.gob.economia.scg.model.CriterioReporte;

/**
 *
 * @author gerardo
 */
public interface ReporteDao
{

    public Connection getDataSourceConnection();

    public List<Reporte> getReporteGeneralAsuntos(CriterioReporte objCriteriosReporte);

    public List<Reporte> getReporteGeneralDetalle(CriterioReporte objCriterioReporte);

    public List<Reporte> getTotalAsuntoByCriterio(CriterioReporte objCriterioReporte);

    public Reporte getConcentradoAsuntoEstatusArea(CriterioReporte criterio);

    public Integer countDetalle(CriterioReporte criterioAsunto);

    public List<Reporte> getAllResultReporteDetalle(CriterioReporte criterio);

    public Reporte getConcentradoAsuntoEstatusAreaPriori(CriterioReporte criterio);
}
