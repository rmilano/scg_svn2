/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.economia.scg.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import mx.gob.economia.scg.model.Area;
import mx.gob.economia.scg.model.Reporte;
import mx.gob.economia.scg.model.CriterioReporte;
import mx.gob.economia.scg.persistence.ReporteDao;

import org.apache.log4j.Logger;

/**
 *
 * @author gerardo
 */
public class ReporteServiceImpl implements ReporteService
{

    private ReporteDao reporteDao;
    private AreaService areaService;
    private ArbolService arbolService;

    /**
     * Obtiene la conexion
     * @return
     */
    public Connection getDataSourceConnection()
    {
        return this.reporteDao.getDataSourceConnection();
    }

    /**
     * Obtiene los totales de los asuntos detallado por estatus
     * @param criterio
     * @return
     */
    /*
    public Reporte getConcentradoAsuntoEstatusArea(CriterioReporte criterio)
    {
        List<Area> listaSubAreasInmediatas;
        Reporte result = new Reporte();
        Reporte swapReporte = new Reporte();
        Logger log = Logger.getLogger(this.getClass());
        criterio.getIds_area().clear();//limpiamos los ids del criterio de posibles consultas anteriores
        criterio.getIds_area().add(criterio.getId_area());// en la lista de areas se guarda incialmente el area padre para el primer registro del detalle
        swapReporte = this.reporteDao.getConcentradoAsuntoEstatusArea(criterio);// este es el primer detalle
        swapReporte.setCriterio(criterio);
        result.getDetalleAsunto().add(swapReporte);//se agrega el primer registro en el detalle
        result.setTotal(result.getTotal() + swapReporte.getTotal());//acumulamos el total
        listaSubAreasInmediatas = this.areaService.getAreasByIdPadre(criterio.getId_area());//ahora se obtienen las areas dependientes del area padre
        for (Area area : listaSubAreasInmediatas)//por cada subarea se calculan los asunto correspondientes
        {
            criterio.getIds_area().clear();//nuevamente se limpia la lista de areas en el criterio
            criterio.getIds_area().add(area.getId_area());//agregamos el area a la lista
            this.arbolService.cleanListArea();
            List<Area> areasDependientes = this.arbolService.getSubArbolByArea(area.getId_area());// y se calculan las areas dependientes
            for (Area areaDependiente : areasDependientes)//de las areas obtenidas, agregar las subareas
            {

                criterio.getIds_area().add(areaDependiente.getId_area());
            }
            swapReporte = this.reporteDao.getConcentradoAsuntoEstatusArea(criterio);// el detalle siguiente
            swapReporte.getCriterio().setArea(area.getArea());
            swapReporte.getCriterio().setId_area(area.getId_area());
            result.getDetalleAsunto().add(swapReporte);//se agrega el primer registro en el detalle
            result.setTotal(result.getTotal() + swapReporte.getTotal());//acumulamos el total
        }
        return result;
    }*/
    public Reporte getConcentradoAsuntoEstatusArea(CriterioReporte criterio)
    {
        List<Area> listaAreasReporte;
        List<Area> listaSubAreasInmediatas;
        Reporte result = new Reporte();
        Reporte swapReporte = new Reporte();
        Logger log = Logger.getLogger(this.getClass());

        Integer id_area = criterio.getId_area();
        listaAreasReporte = this.areaService.getListaAreaById(id_area);

        // Agregar el resto de las areas (hijas) a la lista
        listaSubAreasInmediatas = this.areaService.getAreasByIdPadre(id_area);//ahora se obtienen las areas dependientes del area padre
        listaAreasReporte.addAll(listaSubAreasInmediatas);

        for (Area area : listaAreasReporte)//por cada subarea se calculan los asunto correspondientes
        {
            criterio.getIds_area().clear();//nuevamente se limpia la lista de areas en el criterio
            //criterio.getIds_area().add(area.getId_area());//agregamos el area a la lista
            criterio.setId_area(area.getId_area());
            swapReporte = this.reporteDao.getConcentradoAsuntoEstatusAreaPriori(criterio);// el detalle siguiente
            swapReporte.getCriterio().setArea(area.getArea());
            swapReporte.getCriterio().setId_area(area.getId_area());
            result.getDetalleAsunto().add(swapReporte);//se agrega el primer rgetConcentradoAsuntoEstatusAreaPrioriegistro en el detalle
            result.setTotal(result.getTotal() + swapReporte.getTotal());//acumulamos el total
        }
        return result;
    }


/*
 Anterior cambiado por Rodolfo Milano

    public Reporte getConcentradoAsuntoEstatusArea(CriterioReporte criterio)
    {
        List<Area> listaSubAreasInmediatas;
        Reporte result = new Reporte();
        Reporte swapReporte = new Reporte();
        Logger log = Logger.getLogger(this.getClass());
        criterio.getIds_area().clear();//limpiamos los ids del criterio de posibles consultas anteriores
        criterio.getIds_area().add(criterio.getId_area());// en la lista de areas se guarda incialmente el area padre para el primer registro del detalle
        swapReporte = this.reporteDao.getConcentradoAsuntoEstatusArea(criterio);// este es el primer detalle
        swapReporte.setCriterio(criterio);
        result.getDetalleAsunto().add(swapReporte);//se agrega el primer registro en el detalle
        result.setTotal(result.getTotal() + swapReporte.getTotal());//acumulamos el total
        listaSubAreasInmediatas = this.areaService.getAreasByIdPadre(criterio.getId_area());//ahora se obtienen las areas dependientes del area padre
        for (Area area : listaSubAreasInmediatas)//por cada subarea se calculan los asunto correspondientes
        {
            criterio.getIds_area().clear();//nuevamente se limpia la lista de areas en el criterio
            //criterio.getIds_area().add(area.getId_area());//agregamos el area a la lista
            criterio.setId_area(area.getId_area());
            swapReporte = this.reporteDao.getConcentradoAsuntoEstatusAreaPriori(criterio);// el detalle siguiente
            swapReporte.getCriterio().setArea(area.getArea());
            swapReporte.getCriterio().setId_area(area.getId_area());
            result.getDetalleAsunto().add(swapReporte);//se agrega el primer rgetConcentradoAsuntoEstatusAreaPrioriegistro en el detalle
            result.setTotal(result.getTotal() + swapReporte.getTotal());//acumulamos el total
        }
        return result;
    }

 */

    /**
     * Obtiene el total de asunto con todo los estatus
     * @param criterio
     * @return
     */
    public Map<String, List<Reporte>> getTotalAsuntoByCriterio(CriterioReporte criterio)
    {
        //por implementar
        return null;
    }

    /**
     * Lista los ausnto por criterio 
     * @param criterioReporte
     * @return
     */
    public List<Reporte> getReporteGeneralAsuntos(CriterioReporte criterioReporte)
    {
        return reporteDao.getReporteGeneralAsuntos(criterioReporte);
    }

    public Integer countDetalle(CriterioReporte criterio)
    {
        return reporteDao.countDetalle(criterio);
    }

    /**
     * Lista los ausnto por criterio
     * @param criterioReporte
     * @return
     */
    public Reporte getReporteGeneralDetalle(CriterioReporte criterio)
    {



        Logger log = Logger.getLogger(this.getClass());
        List<Area> listaSubAreasInmediatas;
        Reporte result = new Reporte();
        Reporte swapReporte = new Reporte();

        criterio.getIds_area().clear();//limpiamos los ids del criterio de posibles consultas anteriores
        criterio.getIds_area().add(criterio.getId_area());// en la lista de areas se guarda incialmente el area padre para el primer registro del detalle

        if (criterio.getId_area_subconsulta() == -1) //checa si desde la pantalla del detalle se cambio el área para una consulta nueva
        {
            criterio.setId_area_subconsulta(criterio.getId_area());
        }
        criterio.setArea(this.areaService.getAreaById(criterio.getId_area_subconsulta()).getArea());

        if (criterio.getId_area() == criterio.getId_area_subconsulta()) //checa si el area que se seleccion a buscar sea la misma que de la subconsulta
        {
            criterio.getPaginador().setNumRegistros(this.countDetalle(criterio));
            criterio.getPaginador().setMaximo(criterio.getPaginador().getNumRegistros());

            List<Reporte> primeralista = this.reporteDao.getReporteGeneralDetalle(criterio);// este es el primer detalle

            swapReporte.setCriterio(criterio);
            result.getDetalleAsunto().addAll(primeralista);//se agrega el primer registro en el detalle            
        }
        else
        {
            criterio.getPaginador().setNumRegistros(0);

            listaSubAreasInmediatas = this.areaService.getAreasDescendenciaByIdPadre(criterio.getId_area_subconsulta());//ahora se obtienen las areas dependientes del area padre

            criterio.getIds_area().clear();
            criterio.getIds_area().add(criterio.getId_area_subconsulta());
            for (Area area : listaSubAreasInmediatas)
            {
                criterio.getIds_area().add(area.getId_area());
            }
            criterio.getPaginador().setNumRegistros(criterio.getPaginador().getNumRegistros() + this.countDetalle(criterio));
            criterio.getPaginador().setMaximo(criterio.getPaginador().getNumRegistros());

            List<Reporte> nuevalista = this.reporteDao.getReporteGeneralDetalle(criterio);// este es el primer detalle

            result.getDetalleAsunto().addAll(nuevalista);
        }
        return result;
    }

    /**
     * Obtiene el total de registros del reporte a detalle
     * @param criterio
     * @return 
     */
    public Reporte getAllResultReporteDetalle(CriterioReporte criterio)
    {
        Logger log = Logger.getLogger(this.getClass());
        List<Area> listaSubAreasInmediatas;
        Reporte result = new Reporte();
        Reporte swapReporte = new Reporte();

        criterio.getIds_area().clear();//limpiamos los ids del criterio de posibles consultas anteriores
        criterio.getIds_area().add(criterio.getId_area());// en la lista de areas se guarda incialmente el area padre para el primer registro del detalle

        if (criterio.getId_area_subconsulta() == -1) //checa si desde la pantalla del detalle se cambio el área para una consulta nueva
        {
            criterio.setId_area_subconsulta(criterio.getId_area());
        }
        criterio.setArea(this.areaService.getAreaById(criterio.getId_area_subconsulta()).getArea());

        if (criterio.getId_area() == criterio.getId_area_subconsulta()) //checa si el area que se seleccion a buscar sea la misma que de la subconsulta
        {
            criterio.getPaginador().setNumRegistros(this.countDetalle(criterio));
            criterio.getPaginador().setMaximo(criterio.getPaginador().getNumRegistros());

            List<Reporte> primeralista = this.reporteDao.getAllResultReporteDetalle(criterio);// este es el primer detalle

            swapReporte.setCriterio(criterio);
            result.getDetalleAsunto().addAll(primeralista);//se agrega el primer registro en el detalle            
        }
        else
        {
            criterio.getPaginador().setNumRegistros(0);

            listaSubAreasInmediatas = this.areaService.getAreasDescendenciaByIdPadre(criterio.getId_area_subconsulta());//ahora se obtienen las areas dependientes del area padre

            criterio.getIds_area().clear();
            criterio.getIds_area().add(criterio.getId_area_subconsulta());
            for (Area area : listaSubAreasInmediatas)
            {
                criterio.getIds_area().add(area.getId_area());
            }
            criterio.getPaginador().setNumRegistros(criterio.getPaginador().getNumRegistros() + this.countDetalle(criterio));
            criterio.getPaginador().setMaximo(criterio.getPaginador().getNumRegistros());

            List<Reporte> nuevalista = this.reporteDao.getAllResultReporteDetalle(criterio);// este es el primer detalle

            result.getDetalleAsunto().addAll(nuevalista);
        }
        return result;
    }

    /**
     * @param reporteDao the reporteDao to set
     */
    public void setReporteDao(ReporteDao reporteDao)
    {
        this.reporteDao = reporteDao;
    }

    /**
     * @param areaService the areaService to set
     */
    public void setAreaService(AreaService areaService)
    {
        this.areaService = areaService;
    }

    /**
     * @param arbolService the arbolService to set
     */
    public void setArbolService(ArbolService arbolService)
    {
        this.arbolService = arbolService;
    }

}
