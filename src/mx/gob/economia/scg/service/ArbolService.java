/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.economia.scg.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import mx.gob.economia.scg.model.Area;
import mx.gob.economia.scg.model.Empleado;

/**
 *
 * @author gerardo
 */
public interface ArbolService
{
    public void loadArboles(HttpServletRequest request);

    public String getArbolAreas(Integer tipo);

    public List<Area> getSubArbolByArea(Integer idArea);
    
    public List<Area> listSubArbolByArea(Integer idArea);
    
    public List<Empleado> getEmpleadosByIdArea(Integer idArea);

    public String getRamaArbolAreas(Area padre);

    public Area getUnidadAdmva(Integer idArea);
    public void cleanListArea();
}
