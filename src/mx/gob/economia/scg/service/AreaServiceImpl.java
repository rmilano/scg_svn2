/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.economia.scg.service;

import java.util.ArrayList;
import java.util.List;
import mx.gob.economia.scg.model.Area;
import mx.gob.economia.scg.persistence.AreaDao;
import mx.gob.economia.scg.util.Constantes;

/**
 * 
 * @author gerardo
 */
public class AreaServiceImpl implements AreaService
{

    private AreaDao areaDao;

    /**
     * Lista los hijos de un area en especifico
     * @param idArea
     * @return 
     */
    public List<Area> getAreasByIdPadre(Integer idArea)
    {
        return this.areaDao.getAreasByIdPadre(idArea);
    }

    /**
     * Lista los hijos de un area en especifico
     * @param idArea
     * @return
     */
    public List<Area> getAreasByIdAbuelo(Integer idArea)
    {
        return this.areaDao.getAreasByIdAbuelo(idArea);
    }

    /**
     * La lista de areas de la tabla area_despliegue
     * @param area
     * @return 
     */
    public List<Area> getAreasFromDespĺiegue(Area area)
    {
        List<Area> listadoArea;
        if (area.getListado_area().equals(Constantes.ACTIVADO))
            listadoArea = this.areaDao.getAreasFromDespĺiegue(area.getId_area());
        else
        {
            //listadoArea = this.areaDao.getAreasByIdPadre(area.getId_area());
            listadoArea = this.areaDao.getAreasByIdAbuelo(area.getId_area());
            listadoArea  = listadoArea == null ? new ArrayList<Area>() : listadoArea;
            listadoArea.add(area);// Al listado se agrega el del usuario
        }
        return listadoArea;
    }

    /**
     * Listado de areas por padre
     * @param idArea
     * @return 
     */
    public List<Area> getAreasDescendenciaByIdPadre(Integer idArea)
    {
        return this.areaDao.getAreasDescendenciaByIdPadre(idArea);
    }

    public List<Integer> getListInAreaDespliegue(Integer idArea_ses){
        List<Integer> ListAreaDespliegue = this.areaDao.getListInAreaDespliegue(idArea_ses);
        return ListAreaDespliegue;
    }

    /*
     * Obtiene las subareas correspondientes a partir del area padre. No es
     * recursivo solo obtiene el primer nivel.
     */
    public List<Area> getAreasByPadre(Area area)
    {
        return this.areaDao.getAreasByPadre(area);
    }

    /* Obtiene el area Raiz */
    public Area getAreasRaiz(Integer tipo)
    {
        return this.areaDao.getAreaRaiz(tipo);
    }

    public void updateArea(Area area)
    {
        this.areaDao.updateArea(area);
    }

    public int saveArea(Area area)
    {
        return this.areaDao.saveArea(area);
    }

    public Area getAreaById(Integer idArea)
    {
        return this.areaDao.getAreaById(idArea);
    }
    public Area getAreaByIdWitoutFilter(Integer idArea)
    {
        return this.areaDao.getAreaByIdWitoutFilter(idArea);
    }

    /**
     * @param areaDao
     *            the areaDao to set
     */
    public void setAreaDao(AreaDao areaDao)
    {
        this.areaDao = areaDao;
    }

    /**
     * @param areaDao
     *            the areaDao to set
     */
    public List<Area> getListaAreaById(Integer idArea)
    {
        return this.areaDao.getListaAreaById(idArea);
    }

}
