package mx.gob.economia.scg.persistence;

import java.util.HashMap;
import java.util.List;
import mx.gob.economia.scg.model.CriterioEmpleado;
import mx.gob.economia.scg.util.Util;

import mx.gob.economia.scg.model.Empleado;
import mx.gob.economia.scg.model.EmpleadoAreaRol;
import mx.gob.economia.scg.model.Rol;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.apache.log4j.Logger;

/**
 * Implementation EmpleadoDao
 * 
 * @author valentin.gomez
 * 
 */
public class EmpleadoDaoImpl extends SqlMapClientTemplate implements
        EmpleadoDao
{

     protected static Logger log = Logger.getLogger("Log");

    public Empleado getEmpleado(String clave)
    {
        Empleado empleado = (Empleado) queryForObject("Empleado.getByCorreo",
                clave);
        return empleado;
    }

    /**
     * Lista los empleados por criterio
     * @param criterioEmpleado
     * @return 
     */
    public List<Empleado> getEmpleadosByCriterio(CriterioEmpleado criterioEmpleado)
    {
        List<Empleado> empleados = (List<Empleado>) this.queryForList("Empleado.listEmpleadosCriterio", criterioEmpleado);
        return empleados;
    }

    /**
     * El numero de empleados por criterio
     * @param criterioEmpleado
     * @return 
     */
    public Integer countEmpleadosByCriterio(CriterioEmpleado criterioEmpleado)
    {
        return (Integer) this.queryForObject("Empleado.countEmpleadosCriterio", criterioEmpleado);
    }

    /**
     * Obtiene el empleado por id
     * @param id_empleado
     * @return 
     */
    public Empleado getEmpleadoById(Integer id_empleado)
    {
        Empleado empleado = (Empleado) queryForObject("Empleado.getById", id_empleado);
        List<Rol> roles = queryForList("Rol.listRolesByIdEmpleado", id_empleado);
        if (roles.size()>0){
            empleado.setRoles(roles);
        }
        return empleado;
    }

    /**
     * Obtiene el empleado por id sin filtros
     * @param id_empleado
     * @return
     */
    public Empleado getEmpleadoByIdWF(Integer id_empleado)
    {
        Empleado empleado = (Empleado) queryForObject("Empleado.getByIdWF", id_empleado);
        List<Rol> roles = queryForList("Rol.listRolesByIdEmpleado", id_empleado);
        if (roles.size()>0){
            empleado.setRoles(roles);
        }
        return empleado;
    }

    public List<Empleado> listEmpleados()
    {
        List<Empleado> empleados = queryForList("Empleado.listAll", null);
        return empleados;
    }

    /**
     * La lista de empleados por area
     * @param id_area
     * @return 
     */
    public List<Empleado> listEmpleadosByIdArea(Integer id_area)
    {
        List<Empleado> empleados = queryForList("Empleado.listByIdArea",
                id_area);
        return empleados;
    }
    /**
     * La lista de empleados por area ordenado por uso de firmantes
     * @param id_area
     * @return
     */
    public List<Empleado> listEmpleadosByIdAreaOrdFirm(Integer id_area)
    {
        List<Empleado> empleados = queryForList("Empleado.listByIdAreaOrderByFirmante",
                id_area);
        return empleados;
    }

    /**
     * La lista de empleados por area ordenado por uso de firmantes y rol de firmantes
     * @param id_area
     * @return
     */
    public List<Empleado> listByIdAreaOrderByFirmanteRol(Integer id_area)
    {
        List<Empleado> empleados = queryForList("Empleado.listByIdAreaOrderByFirmanteRol",
                id_area);
        return empleados;
    }

    /**
     * La lista de empleados por area  ordenado por uso de destinatarios
     * @param id_area
     * @return
     */
    public List<Empleado> listEmpleadosByIdAreaOrdDest(Integer id_area)
    {
        List<Empleado> empleados = queryForList("Empleado.listByIdAreaOrderByDest",
                id_area);
        return empleados;
    }
    /**
     * La lista de empleados por area ordenado por uso de remitentes
     * @param id_area
     * @return
     */
    public List<Empleado> listEmpleadosByIdAreaOrdRemi(Integer id_area)
    {
        List<Empleado> empleados = queryForList("Empleado.listByIdAreaOrderByRemi",
                id_area);
        return empleados;
    }
    /**
     * La lista de empleados por area ordenado por uso de remitentes
     * @param id_area
     * @return
     */
    public List<Empleado> listEmpleadosByIdAreaOrdReDe(Integer id_area)
    {
        List<Empleado> empleados = queryForList("Empleado.listByIdAreaOrderByReDe",
                id_area);
        return empleados;
    }
    /**
     * Lista los empleados por un rol determinado y por area
     * @param id_area
     * @return 
     */
    public List<Empleado> listEmpleadoWithRol(EmpleadoAreaRol empleadoAreaRol)
    {
        List<Empleado> empleados = queryForList("Empleado.listEmpleadoWithRol",empleadoAreaRol);
        return empleados;
    }

    public List<String> getIdsEmpleadosByIdAreaExcledeMe(Empleado empleado)
    {
        List<String> idsEmpleados = queryForList("Empleado.getIdsEmpleadosByIdAreaExcledeMe", empleado);
        return idsEmpleados;
    }

    public List<String> getIdsEmpleadosByIdArea(Integer id_area)
    {
        List<String> idsEmpleados = queryForList("Empleado.getIdsEmpleadosByIdArea", id_area);
        return idsEmpleados;
    }

    public void updateEmpleado(Empleado empleado)
    {
        try
        {
//                this.getSqlMapClient().startTransaction();
//                this.getSqlMapClient().startBatch();
            delete("Rol.deleteAll", empleado.getId_empleado());
            Integer i = empleado.getContrasenna().indexOf("{SHA}");
            Integer j = empleado.getContrasenna().lastIndexOf("=");

            if (i!=0||j!=32){ //sino esta encriptado
                empleado.setContrasenna(Util.encriptarSha(empleado.getContrasenna())); //encripta password
            }

            update("Empleado.update", empleado);
            for (Rol r : empleado.getRoles())
            {
                HashMap<String, Object> empleado_rol = new HashMap<String, Object>();
                empleado_rol.put("id_empleado", empleado.getId_empleado());
                empleado_rol.put("id_rol", r.getId_rol());
                insert("Rol.insert", empleado_rol);
            }
//                this.getSqlMapClient().executeBatch();
//                this.getSqlMapClient().commitTransaction();
//                this.getSqlMapClient().endTransaction();
        } catch (Exception e)
        {
            log.error("Ha ocurrido una excepcion", e);
        } finally
        {
            try
            {
                this.getSqlMapClient().endTransaction();
            } catch (Exception e)
            {
                log.error("Ha ocurrido una excepcion", e);
            }
        }
    }

    public int saveEmpleado(Empleado empleado)
    {
        int id_empleado = -1;
        try
        {
//            this.getSqlMapClient().startTransaction();
//            this.getSqlMapClient().startBatch();
            //if (empleado.getContrasenna().indexOf("{SHA}")!=1||empleado.getContrasenna().indexOf("=")!=33){ //sino esta encriptado
            empleado.setContrasenna(Util.encriptarSha(empleado.getContrasenna())); //encripta password
            //}
            id_empleado = (Integer) insert("Empleado.insert", empleado);
            for (Rol r : empleado.getRoles())
            {
                HashMap<String, Object> empleado_rol = new HashMap<String, Object>();
                empleado_rol.put("id_empleado", id_empleado);
                empleado_rol.put("id_rol", r.getId_rol());
                insert("Rol.insert", empleado_rol);
            }
//            this.getSqlMapClient().executeBatch();
//            this.getSqlMapClient().commitTransaction();
//            this.getSqlMapClient().endTransaction();
        } catch (Exception e)
        {
            log.error("Ha ocurrido una excepcion", e);
        } 
//        finally
//        {
//            try
//            {
//                this.getSqlMapClient().endTransaction();
//            } catch (Exception e)
//            {
//                log.error("Ha ocurrido una excepcion", e);
//            }
//        }
        return id_empleado;
    }

    public List<Rol> listarRoles()
    {
        return queryForList("Rol.listRoles", null);
    }

    public boolean isUsado(Integer id_empleado)
    {
        List<Integer> lista = queryForList("Empleado.listEmpleadoUsado", id_empleado);
        return !lista.isEmpty();
    }
}
