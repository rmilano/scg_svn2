/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.economia.scg.service;

import java.util.List;
import mx.gob.economia.scg.model.Area;

/**
 *
 * @author gerardo
 */
public interface AreaService
{

    public List<Area> getAreasByIdPadre(Integer idArea);

    public List<Area> getAreasDescendenciaByIdPadre(Integer idArea);

    public List<Area> getAreasByPadre(Area area);

    public Area getAreasRaiz(Integer tipo);

    public Area getAreaById(Integer idArea);

    public List<Area> getAreasByIdAbuelo(Integer idArea);

    public Area getAreaByIdWitoutFilter(Integer idArea);

    public void updateArea(Area area);

    public int saveArea(Area area);

    public List<Area> getAreasFromDespĺiegue(Area area);

    public List<Integer> getListInAreaDespliegue(Integer idArea_ses);

    public List<Area> getListaAreaById(Integer idArea);
}
