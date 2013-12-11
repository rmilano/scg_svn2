package mx.gob.economia.scg.form;

import java.util.ArrayList;
import java.util.List;

import mx.gob.economia.scg.model.Empleado;

import org.apache.struts.action.ActionForm;

public class InicioForm extends ActionForm
{

    /**
     *
     */
    private static final long serialVersionUID = 6768173203825310750L;
    private Empleado empleado = new Empleado();
    private Integer numPorRevisar;//lo que estan en la bandeja del revisor
    private Integer numEnRevision;//los que fueron enviados al revisor del lado del capturista
    private Integer numRevisados;//lo que ya fueron revisados
    private Integer numPendientes;
    private Integer numParaVoBo;
    private Integer numPorAtender;
    private Integer numTurnados;
    private Integer numAtendidos;
    private Integer numCapturados;
    private Integer numEnRecepcion;
    private Integer numFinalizados;
    private Integer numCcp;
    private Integer numArchivados;
    private Integer numPorSupervisar;
    private Integer numSupervisados;
    private Integer numConfidenciales;
    private Integer numModificados;
    private List<Empleado> empleados = new ArrayList<Empleado>();

    /**
     * @return the empleado
     */
    public Empleado getEmpleado()
    {
        return empleado;
    }

    /**
     * @param empleado
     *            the empleado to set
     */
    public void setEmpleado(Empleado empleado)
    {
        this.empleado = empleado;
    }

    /**
     * @return the empleados
     */
    public List<Empleado> getEmpleados()
    {
        return empleados;
    }

    /**
     * @param empleados
     *            the empleados to set
     */
    public void setEmpleados(List<Empleado> empleados)
    {
        this.empleados = empleados;
    }

    /**
     * @return the numPendientes
     */
    public Integer getNumPendientes()
    {
        return numPendientes;
    }

    /**
     * @param numPendientes
     *            the numPendientes to set
     */
    public void setNumPendientes(Integer numPendientes)
    {
        this.numPendientes = numPendientes;
    }

    /**
     * @return the numParaVoBo
     */
    public Integer getNumParaVoBo()
    {
        return numParaVoBo;
    }

    /**
     * @param numParaVoBo
     *            the numParaVoBo to set
     */
    public void setNumParaVoBo(Integer numParaVoBo)
    {
        this.numParaVoBo = numParaVoBo;
    }

    /**
     * @return the numPorAtender
     */
    public Integer getNumPorAtender()
    {
        return numPorAtender;
    }

    /**
     * @param numPorAtender
     *            the numPorAtender to set
     */
    public void setNumPorAtender(Integer numPorAtender)
    {
        this.numPorAtender = numPorAtender;
    }

    /**
     * @return the numTurnados
     */
    public Integer getNumTurnados()
    {
        return numTurnados;
    }

    /**
     * @param numTurnados
     *            the numTurnados to set
     */
    public void setNumTurnados(Integer numTurnados)
    {
        this.numTurnados = numTurnados;
    }

    /**
     * @return the numAtendidos
     */
    public Integer getNumAtendidos()
    {
        return numAtendidos;
    }

    /**
     * @param numAtendidos
     *            the numAtendidos to set
     */
    public void setNumAtendidos(Integer numAtendidos)
    {
        this.numAtendidos = numAtendidos;
    }

    /**
     * @return the numCapturados
     */
    public Integer getNumCapturados()
    {
        return numCapturados;
    }

    /**
     * @param numCapturados
     *            the numCapturados to set
     */
    public void setNumCapturados(Integer numCapturados)
    {
        this.numCapturados = numCapturados;
    }

    /**
     * @return the numEnRecepcion
     */
    public Integer getNumEnRecepcion()
    {
        return numEnRecepcion;
    }

    /**
     * @param numEnRecepcion
     *            the numEnRecepcion to set
     */
    public void setNumEnRecepcion(Integer numEnRecepcion)
    {
        this.numEnRecepcion = numEnRecepcion;
    }

    /**
     * @return the numFinalizados
     */
    public Integer getNumFinalizados()
    {
        return numFinalizados;
    }

    /**
     * @param numFinalizados
     *            the numFinalizados to set
     */
    public void setNumFinalizados(Integer numFinalizados)
    {
        this.numFinalizados = numFinalizados;
    }

    /**
     * @return the numCcp
     */
    public Integer getNumCcp()
    {
        return numCcp;
    }

    /**
     * @param numCcp
     *            the numCcp to set
     */
    public void setNumCcp(Integer numCcp)
    {
        this.numCcp = numCcp;
    }

    /**
     * @return the numArchivados
     */
    public Integer getNumArchivados()
    {
        return numArchivados;
    }

    /**
     * @param numArchivados the numArchivados to set
     */
    public void setNumArchivados(Integer numArchivados)
    {
        this.numArchivados = numArchivados;
    }

    /**
     * @return the numPorRevisar
     */
    public Integer getNumPorRevisar()
    {
        return numPorRevisar;
    }

    /**
     * @param numPorRevisar the numPorRevisar to set
     */
    public void setNumPorRevisar(Integer numPorRevisar)
    {
        this.numPorRevisar = numPorRevisar;
    }

    /**
     * @return the numEnRevision
     */
    public Integer getNumEnRevision()
    {
        return numEnRevision;
    }

    /**
     * @param numEnRevision the numEnRevision to set
     */
    public void setNumEnRevision(Integer numEnRevision)
    {
        this.numEnRevision = numEnRevision;
    }

    /**
     * @return the numRevisados
     */
    public Integer getNumRevisados()
    {
        return numRevisados;
    }

    /**
     * @param numRevisados the numRevisados to set
     */
    public void setNumRevisados(Integer numRevisados)
    {
        this.numRevisados = numRevisados;
    }

    /**
     * @return the numPorSupervisar
     */
    public Integer getNumPorSupervisar()
    {
        return numPorSupervisar;
    }

    /**
     * @param numPorSupervisar the numPorSupervisar to set
     */
    public void setNumPorSupervisar(Integer numPorSupervisar)
    {
        this.numPorSupervisar = numPorSupervisar;
    }

    /**
     * @return the numSupervisados
     */
    public Integer getNumSupervisados()
    {
        return numSupervisados;
    }

    /**
     * @param numSupervisados the numSupervisados to set
     */
    public void setNumSupervisados(Integer numSupervisados)
    {
        this.numSupervisados = numSupervisados;
    }

    /**
     * @return the numConfidenciales
     */
    public Integer getNumConfidenciales()
    {
        return numConfidenciales;
    }

    /**
     * @param numConfidenciales the numConfidenciales to set
     */
    public void setNumConfidenciales(Integer numConfidenciales)
    {
        this.numConfidenciales = numConfidenciales;
    }

    public Integer getNumModificados() {
        return numModificados;
    }

    public void setNumModificados(Integer numModificados) {
        this.numModificados = numModificados;
    }
    
}
