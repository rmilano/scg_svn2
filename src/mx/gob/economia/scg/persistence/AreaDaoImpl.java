/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.economia.scg.persistence;

import java.util.List;
import mx.gob.economia.scg.model.Area;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

/**
 *
 * @author gerardo
 */
public class AreaDaoImpl extends SqlMapClientTemplate implements AreaDao
{

    /**
     * Listado de areas por id_padre
     * @param padre
     * @return 
     */
    public List<Area> getAreasByPadre(Area padre)
    {
        List<Area> areas = this.queryForList("Area.listAreasByParentId", padre.getId_area());
        return areas;
    }

    /**
     * Listado de areas por id_padre
     * @param padre
     * @return
     */
    public List<Area> getAreasByAbuelo(Area area)
    {
        List<Area> areas = this.queryForList("Area.listAreasByGrandId", area.getId_area());
        return areas;
    }

    /**
     * Lista las area de la talba area_despliegue
     * @param area
     * @return 
     */
    public List<Area> getAreasFromDespĺiegue(Integer idArea)
    {
        List<Area> areas = this.queryForList("Area.listAreasByDespliegue", idArea);
        return areas;
    }
    
    /**
     * Listado de area por un padre en especifico
     * @param idArea
     * @return 
     */
    public List<Area> getAreasByIdPadre(Integer idArea)
    {
        List<Area> areas = this.queryForList("Area.listAreasByParentId", idArea);
        return areas;
    }

     /**
     * Listado de area por un padre en especifico
     * @param idArea
     * @return
     */
    public List<Area> getAreasByIdAbuelo(Integer idArea)
    {
        List<Area> areas = this.queryForList("Area.listAreasByGrandId", idArea);
        return areas;
    }

    /**
     * El listado de areas por padre
     * @param idArea
     * @return 
     */
    public List<Area> getAreasDescendenciaByIdPadre(Integer idArea)
    {
        List<Area> areas = this.queryForList("Area.listAreasDescendenciaByParentId", idArea);
        return areas;
    }

    public List<Integer> getListInAreaDespliegue(Integer idArea_ses){
        List<Integer> listInAreaDespliegue = (List<Integer>) queryForList("Area.countInAreaDespliegue", idArea_ses);
        return listInAreaDespliegue;
    }

    /**
     * Obtiene el area por id
     * @param id
     * @return 
     */
    public Area getAreaById(Integer id)
    {
        Area area = (Area) this.queryForObject("Area.getAreaById", id);
        return area;
    }

    /**
     * Obtiene el area sin importar si esta activa o no
     * @param id
     * @return
     */
    public Area getAreaByIdWitoutFilter(Integer id)
    {
        Area area = (Area) this.queryForObject("Area.getAreaByIdWitoutFilter", id);
        return area;
    }

    /**
     * El nodo raiz
     * @param tipo
     * @return 
     */
    public Area getAreaRaiz(Integer tipo)
    {
        Area area = (Area) this.queryForObject("Area.getAreaRaiz", tipo);
        return area;
    }

    /**
     * Actualiza el area
     * @param area 
     */
    public void updateArea(Area area)
    {
        update("Area.update", area);
    }

    /**
     * Registra un area
     * @param area
     * @return 
     */
    public int saveArea(Area area)
    {
        return (Integer) insert("Area.insert", area);
    }

    /**
     * Listado de areas por id_area
     * @param id_area
     * @return
     */
    public List<Area> getListaAreaById(Integer id_area)
    {
        List<Area> areas = this.queryForList("Area.listAreaById", id_area);
        return areas;
    }

}
