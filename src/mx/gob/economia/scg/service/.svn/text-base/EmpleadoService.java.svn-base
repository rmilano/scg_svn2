package mx.gob.economia.scg.service;

import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import mx.gob.economia.scg.model.CriterioEmpleado;

import mx.gob.economia.scg.model.Empleado;
import mx.gob.economia.scg.model.Rol;

/**
 * Interface EmpleadoService
 * 
 * @author valentin.gomez
 * 
 */
public interface EmpleadoService
{

    public Empleado getEmpleado(String clave);

    public Empleado getEmpleadoById(Integer id_empleado);

    public Empleado getEmpleadoByIdWF(Integer id_empleado);

    public List<Empleado> listEmpleados();

    public List<Empleado> listEmpleadosByIdArea(Integer id_area);
    public List<Empleado> listEmpleadosByIdAreaOrdFirm(Integer id_area);
    public List<Empleado> listEmpleadosByIdAreaOrdDest(Integer id_area);
    public List<Empleado> listEmpleadosByIdAreaOrdRemi(Integer id_area);
    public List<Empleado> listEmpleadosByIdAreaOrdReDe(Integer id_area);
    public List<Empleado> listByIdAreaOrderByFirmanteRol(Integer id_area);

    public void sendMailEmpleado(Empleado empleado, HttpServletRequest request, URL urlLogo);

    public void updateEmpleado(Empleado empleado);

    public int saveEmpleado(Empleado empleado);

    public List<Rol> listRoles();

    public Empleado getEmpleadoAutenticado();

    public boolean hasRol(Empleado empleado, String rol);

    public List<Empleado> listReceptorasInmediataSuperior(Empleado empleado);

    public void addElementToListReceptoras(Empleado empleado);

    public boolean isUsado(Integer id_empleado);

    public List<String> getIdsEmpleadosByIdArea(Integer idArea);

    public List<String> getIdsEmpleadosByIdAreaExcledeMe(Empleado empleado);

    public List<Empleado> listReceptorasFromArea(Empleado destinatario);

    public List<Empleado> getListReceivingWithRecipient(Empleado destinatario);

    public List<Empleado> getEmpleadosByCriterio(CriterioEmpleado criterioEmpleado);

    public Integer countEmpleadosByCriterio(CriterioEmpleado criterioEmpleado);

    public Integer[] getSelectedRolesFromEmpleado(List<Rol> roles);

    public List<Rol> getRolListFromSelectedRol(Integer[] selectedRol, Empleado empleado);

    public Rol setRolDefault(Integer idRol);

    public void setRolEmpleadoForInternalUser(Empleado empleado);

    public List<Empleado> listReceptorasFromAreaAndAreaPadre(Empleado empleado);

    public List<Empleado> listRevisorFromAreaAndAreaPadre(Empleado empleado);
}
