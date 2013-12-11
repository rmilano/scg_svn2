/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.economia.scg.persistence;

import java.util.List;
import mx.gob.economia.scg.model.Area;

/**
 *
 * @author gerardo
 */
public interface AreaDao
{

    public List<Area> getAreasByPadre(Area area);

    public List<Area> getAreasByAbuelo(Area area);
    
    public List<Area> getAreasByIdPadre(Integer idArea);

    public List<Area> getAreasByIdAbuelo(Integer idArea);
    
    public List<Area> getAreasDescendenciaByIdPadre(Integer idArea);

    public Area getAreaById(Integer id);

    public Area getAreaRaiz(Integer tipo);

    public void updateArea(Area area);

    public int saveArea(Area area);

    public Area getAreaByIdWitoutFilter(Integer id);

    public List<Area> getAreasFromDespĺiegue(Integer idArea);

    public List<Integer> getListInAreaDespliegue(Integer idArea_ses);

    public List<Area> getListaAreaById(Integer id_area);

}
