package mx.gob.economia.scg.model;
//aurora.paniagua

import java.io.Serializable;

public class TemaRevisor implements Serializable
{
    // Propiedades

    private Integer id_tema;
    private Integer id_empleado_revi;
    private Tema tema;
    private Empleado empleado_revi;

    /**
     * @return the id_tema
     */
    public Integer getId_tema()
    {
        return id_tema;
    }

    /**
     * @param id_tema the id_tema to set
     */
    public void setId_tema(Integer id_tema)
    {
        this.id_tema = id_tema;
    }

    /**
     * @return the id_empleado_revi
     */
    public Integer getId_empleado_revi()
    {
        return id_empleado_revi;
    }

    /**
     * @param id_empleado_revi the id_empleado_revi to set
     */
    public void setId_empleado_revi(Integer id_empleado_revi)
    {
        this.id_empleado_revi = id_empleado_revi;
    }

    /**
     * @return the tema
     */
    public Tema getTema()
    {
        return tema;
    }

    /**
     * @param tema the tema to set
     */
    public void setTema(Tema tema)
    {
        this.tema = tema;
    }

    /**
     * @return the empleado_revi
     */
    public Empleado getEmpleado_revi()
    {
        return empleado_revi;
    }

    /**
     * @param empleado_revi the empleado_revi to set
     */
    public void setEmpleado_revi(Empleado empleado_revi)
    {
        this.empleado_revi = empleado_revi;
    }
}
