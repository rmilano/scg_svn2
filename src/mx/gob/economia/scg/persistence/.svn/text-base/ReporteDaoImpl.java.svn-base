/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.economia.scg.persistence;

import java.sql.Connection;
import java.util.List;
import mx.gob.economia.scg.model.Reporte;
import mx.gob.economia.scg.model.CriterioReporte;
import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

/**
 *
 * @author gerardo
 */
public class ReporteDaoImpl extends SqlMapClientTemplate implements ReporteDao
{
    /**
     * Obtiene la conexion
     * @return
     */
    public Connection getDataSourceConnection() 
    {
        Connection connection = null;
        try
        {
            connection = this.getDataSource().getConnection();
        }
        catch(Exception e)
        {            
            Logger log = Logger.getLogger(this.getClass());
            log.error("Se ha producido un error al obtener la conexion" + e.getMessage());
        }
        return connection;
    }

    
    public Integer countDetalle(CriterioReporte criterioReporte)
    {
        Integer numRegistros = (Integer) queryForObject(
                "Reporte.countReporteGeneralDetalle", criterioReporte);
        return numRegistros;
    }    
    
    /**
     * Obtiene el total de asuntos por criterio
     * @param objCriteriosReporte
     * @return
     */
    public List<Reporte> getReporteGeneralAsuntos(CriterioReporte objCriteriosReporte)
    {        
    	List<Reporte> listaReporteGeneral = this.queryForList("Reporte.listaReporteGeneralAsuntos", objCriteriosReporte);    
    	return listaReporteGeneral;
    }
    
    public List<Reporte> getReporteGeneralDetalle(CriterioReporte criterio)
    {
        return this.queryForList("Reporte.listaReporteGeneralDetalle", criterio);
    }     
    public List<Reporte> getAllResultReporteDetalle(CriterioReporte criterio)
    {
        return this.queryForList("Reporte.getAllResultReporteDetalle", criterio);
    }     
    public Reporte getConcentradoAsuntoEstatusArea(CriterioReporte criterio)
    {
        return (Reporte) this.queryForObject("Reporte.getConcentradoAsuntoEstatus", criterio);
    }
    
    public Reporte getConcentradoAsuntoEstatusAreaPriori(CriterioReporte criterio)
    {
        return (Reporte) this.queryForObject("Reporte.getConcentradoAsuntoEstatusPriori", criterio);
    }

    /**
     * Obtiene el total de asuntos por area y estatus
     * @param criterio
     * @return
     */
    public List<Reporte> getTotalAsuntoByCriterio(CriterioReporte criterio)
    {
        return this.queryForList("Reporte.getTotalAsuntosByCriterio", criterio);
    }
   
}

