package mx.gob.economia.scg.form;

import java.util.ArrayList;
import java.util.List;
import mx.gob.economia.scg.model.CriterioAsunto;
import mx.gob.economia.scg.model.Empleado;
import mx.gob.economia.scg.model.TipoAsunto;
import mx.gob.economia.scg.util.Constantes;
import mx.gob.economia.scg.util.Util;

public class CapturaForm extends AsuntoForm
{

    /**
     * 
     */
    private static final long serialVersionUID = -2452762297043411998L;
    // Propiedades
    private int idx;
    private String nombre_completo;
    private CriterioAsunto criterioAsunto;
    private Empleado empleadoCaptura;
    private List<TipoAsunto> tiposAsunto;
    private Integer displayList;
    private Integer displayTree;
    private Empleado empleadoRevisor;
    private List<Empleado> empleadosRevisores;
    private String puestos_remi;
    private String puestos_dest;

    public CapturaForm()
    {
        this.criterioAsunto = new CriterioAsunto();
        this.empleadoCaptura = new Empleado();
        this.tiposAsunto = new ArrayList<TipoAsunto>();
        this.displayList = Constantes.ACTIVADO;
        this.displayTree = Constantes.FIRST;
        this.empleadoRevisor = new Empleado();
        this.empleadosRevisores = new ArrayList<Empleado>();
        this.puestos_remi = "";
        this.puestos_dest = "";
    }

    /**
     * @return the idx
     */
    public int getIdx()
    {
        return idx;
    }

    /**
     * @return the nombre_completo
     */
    public String getNombre_completo()
    {
        return Util.formatearCadena(nombre_completo);
    }

    /**
     * @param nombre_completo
     *            the nombre_completo to set
     */
    public void setNombre_completo(String nombre_completo)
    {
        this.nombre_completo = nombre_completo;
    }

    /**
     * @return the criterioAsunto
     */
    public CriterioAsunto getCriterioAsunto()
    {
        return criterioAsunto;
    }

    /**
     * @param criterioAsunto the criterioAsunto to set
     */
    public void setCriterioAsunto(CriterioAsunto criterioAsunto)
    {
        this.criterioAsunto = criterioAsunto;
    }

    /**
     * @return the empleadoCaptura
     */
    public Empleado getEmpleadoCaptura()
    {
        return empleadoCaptura;
    }

    /**
     * @param empleadoCaptura the empleadoCaptura to set
     */
    public void setEmpleadoCaptura(Empleado empleadoCaptura)
    {
        this.empleadoCaptura = empleadoCaptura;
    }

    /**
     * @return the tiposAsunto
     */
    public List<TipoAsunto> getTiposAsunto()
    {
        return tiposAsunto;
    }

    /**
     * @param tiposAsunto the tiposAsunto to set
     */
    public void setTiposAsunto(List<TipoAsunto> tiposAsunto)
    {
        this.tiposAsunto = tiposAsunto;
    }

    /**
     * @return the displayList
     */
    public Integer getDisplayList()
    {
        return displayList;
    }

    /**
     * @param displayList the displayList to set
     */
    public void setDisplayList(Integer displayList)
    {
        this.displayList = displayList;
    }

    /**
     * @return the displayTree
     */
    public Integer getDisplayTree()
    {
        return displayTree;
    }

    /**
     * @param displayTree the displayTree to set
     */
    public void setDisplayTree(Integer displayTree)
    {
        this.displayTree = displayTree;
    }

    /**
     * @return the empleadoRevisor
     */
    public Empleado getEmpleadoRevisor()
    {
        return empleadoRevisor;
    }

    /**
     * @param empleadoRevisor the empleadoRevisor to set
     */
    public void setEmpleadoRevisor(Empleado empleadoRevisor)
    {
        this.empleadoRevisor = empleadoRevisor;
    }

    /**
     * @return the empleadosRevisores
     */
    public List<Empleado> getEmpleadosRevisores()
    {
        return empleadosRevisores;
    }

    /**
     * @param empleadosRevisores the empleadosRevisores to set
     */
    public void setEmpleadosRevisores(List<Empleado> empleadosRevisores)
    {
        this.empleadosRevisores = empleadosRevisores;
    }

    public String getPuestos_dest() {
        return puestos_dest;
    }

    public void setPuestos_dest(String puestos_dest) {
        this.puestos_dest = puestos_dest;
    }

    public String getPuestos_remi() {
        return puestos_remi;
    }

    public void setPuestos_remi(String puestos_remi) {
        this.puestos_remi = puestos_remi;
    }
}
