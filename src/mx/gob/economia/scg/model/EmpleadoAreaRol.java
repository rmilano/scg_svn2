/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.economia.scg.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author roque
 */
public class EmpleadoAreaRol
{
    private Integer idRol;
    private List<String> idsArea;
    
    public EmpleadoAreaRol()
    {
        this.idRol = 0;
        this.idsArea = new ArrayList<String>();
    }

    /**
     * @return the idRol
     */
    public Integer getIdRol()
    {
        return idRol;
    }

    /**
     * @param idRol the idRol to set
     */
    public void setIdRol(Integer idRol)
    {
        this.idRol = idRol;
    }

    /**
     * @return the idsArea
     */
    public List<String> getIdsArea()
    {
        return idsArea;
    }

    /**
     * @param idsArea the idsArea to set
     */
    public void setIdsArea(List<String> idsArea)
    {
        this.idsArea = idsArea;
    }
    
}
