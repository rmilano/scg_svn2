package mx.gob.economia.scg.persistence;

import java.util.List;
import mx.gob.economia.scg.model.CriterioEmpleado;

import mx.gob.economia.scg.model.Empleado;
import mx.gob.economia.scg.model.EmpleadoAreaRol;
import mx.gob.economia.scg.model.Rol;

/**
 * Interface EmpleadoDao
 * 
 * @author valentin.gomez
 * 
 */
public interface EmpleadoDao
{

    public Empleado getEmpleado(String clave);

    public Empleado getEmpleadoById(Integer id_empleado);

    public Empleado getEmpleadoByIdWF(Integer id_empleado);

    public List<Empleado> listEmpleados();

    public List<Empleado> listEmpleadosByIdArea(Integer id_area);

    public List<Empleado> listEmpleadosByIdAreaOrdFirm(Integer id_area);
    public List<Empleado> listEmpleadosByIdAreaOrdDest(Integer id_area);
    public List<Empleado> listByIdAreaOrderByFirmanteRol(Integer id_area);
    public List<Empleado> listEmpleadosByIdAreaOrdRemi(Integer id_area);
    public List<Empleado> listEmpleadosByIdAreaOrdReDe(Integer id_area);

    public int saveEmpleado(Empleado empleado);

    public void updateEmpleado(Empleado empleado);

    public List<Rol> listarRoles();

    public boolean isUsado(Integer id_empleado);

    public List<String> getIdsEmpleadosByIdArea(Integer id_area);

    public List<String> getIdsEmpleadosByIdAreaExcledeMe(Empleado empleado);

    public List<Empleado> getEmpleadosByCriterio(CriterioEmpleado criterioEmpleado);

    public Integer countEmpleadosByCriterio(CriterioEmpleado criterioEmpleado);

    public List<Empleado> listEmpleadoWithRol(EmpleadoAreaRol empleadoAreaRol);
}
