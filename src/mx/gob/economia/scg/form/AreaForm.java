/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.economia.scg.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;
import mx.gob.economia.scg.model.Area;
import mx.gob.economia.scg.model.Empleado;

/**
 *
 * @author gerardo
 */
public class AreaForm extends ActionForm
{
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private Area area;
    private List<Area> areas;
    private List<Empleado> empleados;
    private Empleado empleado;
    private String tree;
    private String metodo;
    private Integer[] selectedItems;

    /**
     * @return the area
     */
    public AreaForm()
    {
        this.area = new Area();
        this.areas = new ArrayList<Area>();
        this.empleados = new ArrayList<Empleado>();
        this.empleado = new Empleado();
        this.tree = "";
        //this.selectedItems.;
        this.metodo=new String();
    }
    public Area getArea()
    {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(Area area)
    {
        this.area = area;
    }

    /**
     * @return the areas
     */
    public List<Area> getAreas()
    {
        return areas;
    }

    /**
     * @param areas the areas to set
     */
    public void setAreas(List<Area> areas)
    {
        this.areas = areas;
    }

    /**
     * @return the empleados
     */
    public List<Empleado> getEmpleados()
    {
        return empleados;
    }

    /**
     * @param empleados the empleados to set
     */
    public void setEmpleados(List<Empleado> empleados)
    {
        this.empleados = empleados;
    }

    /**
     * @return the empleado
     */
    public Empleado getEmpleado()
    {
        return empleado;
    }

    /**
     * @param empleado the empleado to set
     */
    public void setEmpleado(Empleado empleado)
    {
        this.empleado = empleado;
    }

    /**
     * @return the tree
     */
    public String getTree()
    {
        return tree;
    }

    /**
     * @param tree the tree to set
     */
    public void setTree(String tree)
    {
        this.tree = tree;
    }
	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}
	public String getMetodo() {
		return metodo;
	}

    /**
     * @return the selectedItems
     */
    public Integer[] getSelectedItems() {
        return selectedItems;
    }

    /**
     * @param selectedItems the selectedItems to set
     */
    public void setSelectedItems(Integer[] selectedItems) {
        this.selectedItems = selectedItems;
    }
}
