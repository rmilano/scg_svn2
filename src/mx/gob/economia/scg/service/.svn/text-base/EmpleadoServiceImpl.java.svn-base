package mx.gob.economia.scg.service;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import mx.gob.economia.scg.model.CriterioEmpleado;

import org.acegisecurity.context.SecurityContextHolder;

import mx.gob.economia.scg.model.Empleado;
import mx.gob.economia.scg.model.EmpleadoAreaRol;
import mx.gob.economia.scg.model.Rol;
import mx.gob.economia.scg.persistence.EmpleadoDao;
import mx.gob.economia.scg.util.Constantes;
import mx.gob.economia.scg.util.Util;
import org.apache.log4j.Logger;

/**
 * Implementation EmpleadoService
 * 
 * @author valentin.gomez
 * 
 */
public class EmpleadoServiceImpl implements EmpleadoService
{

    private EmpleadoDao empleadoDao;
    private CorreoService correoService;
    private List<Empleado> empleados = new ArrayList<Empleado>();
    protected static Logger log = Logger.getLogger("Log");

    /**
     * Actualizar empleado
     * @param empleado 
     */
    public void updateEmpleado(Empleado empleado)
    {
        empleadoDao.updateEmpleado(empleado);
    }

    /**
     * Guardar Empleado
     * @param empleado
     * @return 
     */
    public int saveEmpleado(Empleado empleado)
    {
        return empleadoDao.saveEmpleado(empleado);
    }

    /**
     * Obtiene el empleado por la clave
     */
    public Empleado getEmpleado(String clave)
    {
        return empleadoDao.getEmpleado(clave);
    }

    /**
     * Obtiene la lista de los empleados
     */
    public List<Empleado> listEmpleados()
    {
        return empleadoDao.listEmpleados();
    }

    /**
     * Obtiene el arreglo de Roles Integer a Una lista de objetos
     * @param selectedRol
     * @param empleado
     * @return 
     */
    public List<Rol> getRolListFromSelectedRol(Integer[] selectedRol, Empleado empleado)
    {
        List<Rol> empleadoRoles = new ArrayList<Rol>();

        Rol rolEmpleado = new Rol();
        rolEmpleado.setId_rol(Constantes.ROL_EMPLEADO);

        // Si el empleado es interno, se agrega el rol de empleado
        if (empleado.getArea().getTipo().equals(Constantes.AREA_INTERNA))
            empleadoRoles.add(rolEmpleado);
        for (Integer rol : selectedRol)
        {
            Rol r = new Rol();
            r.setId_rol(rol);
            empleadoRoles.add(r);
        }
        return empleadoRoles;
    }

    /**
     * Define un rol determinado para el empleado
     * @param idRol
     * @return 
     */
    public Rol setRolDefault(Integer idRol)
    {
        Rol rol = new Rol();
        rol.setId_rol(idRol);
        rol.setRol("");       
        return rol;
    }

    /**
     * Si el usuario es interno, se agrega el rol de empleado
     * @param empleado
     * @param idRol 
     */
    public void setRolEmpleadoForInternalUser(Empleado empleado)
    {
        if(empleado.getArea().getTipo().equals(Constantes.AREA_INTERNA))
            empleado.getRoles().add(this.setRolDefault(Constantes.ROL_EMPLEADO));
    }
    
    /**
     * La lista de empleado por criterio
     * @param criterioEmpleado
     * @return 
     */
    public List<Empleado> getEmpleadosByCriterio(CriterioEmpleado criterioEmpleado)
    {
        return empleadoDao.getEmpleadosByCriterio(criterioEmpleado);
    }

    /**
     * Obtiene el numero de registro de la lista de empleados
     * @param criterioEmpleado
     * @return 
     */
    public Integer countEmpleadosByCriterio(CriterioEmpleado criterioEmpleado)
    {
        return empleadoDao.countEmpleadosByCriterio(criterioEmpleado);
    }

    /**
     * Envia un correo con la informacion del empleado que se registro
     */
    public void sendMailEmpleado(Empleado empleado, HttpServletRequest request, URL urlLogo)
    {
        try
        {
            // Documentos adjuntos al correo
            List<File> files = null;

            // Contenido del mensaje
            String contenido = "<BR/><BR/>" + empleado.getCorreo()
                    + "<BR/><BR/>" + "<BR/><BR/>" + empleado.getContrasenna()
                    + "<BR/><BR/>";

            String html = Util.getHtml(contenido);

            correoService.send(empleado.getCorreo(),
                    "Validacion de Cuenta de Correo.", html, contenido, files, urlLogo, request);

        } catch (Exception e)
        {
            log.error("Ha ocurrido un error ->", e);
        }
    }

    /**
     * Convierte la lista de roles a un array de strings
     * @param roles
     * @return 
     */
    public Integer[] getSelectedRolesFromEmpleado(List<Rol> roles)
    {
        Integer[] selectedRoles = new Integer[roles.size()];
        for (int i = 0; i < roles.size(); i++)
        {
            selectedRoles[i] = roles.get(i).getId_rol();
        }
        return selectedRoles;
    }

    /**
     * Obtiene la lista de los empleados
     * @param id_area
     * @return
     */
    public List<Empleado> listEmpleadosByIdArea(Integer id_area)
    {
        List<Empleado> empleados = empleadoDao.listEmpleadosByIdArea(id_area);
        for (int i = 0; i < empleados.size(); i++)
        {
            empleados.get(i).setIdx(i);
        }
        return empleados;
    }

    /**
     * Obtiene la lista de los empleados
     * @param id_area
     * @return
     */
    public List<Empleado> listEmpleadosByIdAreaOrdFirm(Integer id_area)
    {
        List<Empleado> empleados = empleadoDao.listEmpleadosByIdAreaOrdFirm(id_area);
        for (int i = 0; i < empleados.size(); i++)
        {
            empleados.get(i).setIdx(i);
        }
        return empleados;
    }

    /**
     * Obtiene la lista de los empleados firmantes por rol
     * @param id_area
     * @return
     */
    public List<Empleado> listByIdAreaOrderByFirmanteRol(Integer id_area)
    {
        List<Empleado> empleados = empleadoDao.listByIdAreaOrderByFirmanteRol(id_area);
        for (int i = 0; i < empleados.size(); i++)
        {
            empleados.get(i).setIdx(i);
        }
        return empleados;
    }

    /**
     * Obtiene la lista de los empleados
     * @param id_area
     * @return
     */
    public List<Empleado> listEmpleadosByIdAreaOrdDest(Integer id_area)
    {
        List<Empleado> empleados = empleadoDao.listEmpleadosByIdAreaOrdDest(id_area);
        for (int i = 0; i < empleados.size(); i++)
        {
            empleados.get(i).setIdx(i);
        }
        return empleados;
    }

    /**
     * Obtiene la lista de los empleados
     * @param id_area
     * @return
     */
    public List<Empleado> listEmpleadosByIdAreaOrdRemi(Integer id_area)
    {
        List<Empleado> empleados = empleadoDao.listEmpleadosByIdAreaOrdRemi(id_area);
        for (int i = 0; i < empleados.size(); i++)
        {
            empleados.get(i).setIdx(i);
        }
        return empleados;
    }
    /**
     * Obtiene la lista de los empleados
     * @param id_area
     * @return
     */
    public List<Empleado> listEmpleadosByIdAreaOrdReDe(Integer id_area)
    {
        List<Empleado> empleados = empleadoDao.listEmpleadosByIdAreaOrdReDe(id_area);
        for (int i = 0; i < empleados.size(); i++)
        {
            empleados.get(i).setIdx(i);
        }
        return empleados;
    }

    /**
     * Obtiene los empleados del area
     * @param idArea
     * @return 
     */
    public List<String> getIdsEmpleadosByIdArea(Integer idArea)
    {
        List<String> idsEmpleados = empleadoDao.getIdsEmpleadosByIdArea(idArea);
        return idsEmpleados;
    }

    /**
     * Obtiene los empleados del area sin el empleado que se pasa por valor
     * @param empleado
     * @return 
     */
    public List<String> getIdsEmpleadosByIdAreaExcledeMe(Empleado empleado)
    {
        List<String> idsEmpleados = empleadoDao.getIdsEmpleadosByIdAreaExcledeMe(empleado);
        return idsEmpleados;
    }

    public Empleado getEmpleadoById(Integer id_empleado)
    {
        Empleado empleado = empleadoDao.getEmpleadoById(id_empleado);
        empleado = empleado == null ? new Empleado() : empleado;
        return empleado;
    }

    public Empleado getEmpleadoByIdWF(Integer id_empleado)
    {
        Empleado empleado = empleadoDao.getEmpleadoByIdWF(id_empleado);
        empleado = empleado == null ? new Empleado() : empleado;
        return empleado;
    }

    public Empleado getEmpleadoAutenticado()
    {
        String clave = SecurityContextHolder.getContext().getAuthentication().getName();
        return empleadoDao.getEmpleado(clave);
    }

    /*
     * Obtiene una lista de las receptoras inmediatas superior a un empleado
     * 
     * @param empleado: bean de empleado con los roles respectivos
     * 
     * @return List empleado: regresa una lista de área receptoras del área a
     * fin.
     */
    public List<Empleado> listReceptorasInmediataSuperior(Empleado empleado)
    {
        this.empleados.clear();// limpiamos la lista de posibles iteraciones
        // anteriores
        try
        {
            this.addElementToListReceptoras(empleado);
            if (this.empleados.isEmpty())
                this.addElementToListReceptoras(empleado); //				throw new Exception("La lista se encuentra vacia");
        } catch (Exception e)
        {
            log.error("Ha ocurrido un error, favor de verificar:"
                    + e.getMessage());
        }
        return this.empleados;
    }

    /**
     * Obtiene las receptoras del area del empleado y los empleados receptores del area
     * superior
     * @param empleado
     * @return 
     */
    public List<Empleado> listReceptorasFromAreaAndAreaPadre(Empleado empleado)
    {
        EmpleadoAreaRol empleadoAreaRol = new EmpleadoAreaRol();
        empleadoAreaRol.setIdRol(Constantes.ROL_RECEPCIONISTA);
        empleadoAreaRol.getIdsArea().add(String.valueOf(empleado.getArea().getId_area()));
        if (String.valueOf(empleado.getArea().getId_area_padre()).equals("")) {
            empleadoAreaRol.getIdsArea().add(String.valueOf(empleado.getArea().getId_area_padre()));
        }
        List<Empleado> empleadosWithRolReceptor = this.empleadoDao.listEmpleadoWithRol(empleadoAreaRol);
        empleadosWithRolReceptor = empleadosWithRolReceptor == null ? new ArrayList<Empleado>() : empleadosWithRolReceptor;
        
        if(empleadosWithRolReceptor.isEmpty())// si no existen receptoras, agregar al empleados destinatario como receptor
          empleadosWithRolReceptor.add(empleado);
            
        return empleadosWithRolReceptor;
    }
    /**
     * Obtiene los revisores del area del empleado y los empleados revisores del area
     * superior
     * @param empleado
     * @return 
     */
    public List<Empleado> listRevisorFromAreaAndAreaPadre(Empleado empleado)
    {
        EmpleadoAreaRol empleadoAreaRol = new EmpleadoAreaRol();
        empleadoAreaRol.setIdRol(Constantes.ROL_REVISOR);
        empleadoAreaRol.getIdsArea().add(String.valueOf(empleado.getArea().getId_area()));
        empleadoAreaRol.getIdsArea().add(String.valueOf(empleado.getArea().getId_area_padre()));
        List<Empleado> empleadosWithRolRevisor = this.empleadoDao.listEmpleadoWithRol(empleadoAreaRol);
        empleadosWithRolRevisor = empleadosWithRolRevisor == null ? new ArrayList<Empleado>() : empleadosWithRolRevisor;
        
        if(empleadosWithRolRevisor.isEmpty())// si no existen receptoras, agregar al empleados destinatario como receptor
          empleadosWithRolRevisor.add(empleado);
            
        return empleadosWithRolRevisor;
    }
    
    /*
     * Busca las receptoras inmedias de un empleados y los agrega a la list
     * 
     * @param empleado: bean de empleado con los roles respectivos
     * 
     * @return void
     */
    public void addElementToListReceptoras(Empleado empleado)
    {
        try
        {
            if (this.hasRol(empleado, Constantes.ROL_RECEPTORA))
                if (!this.empleados.contains(empleado))
                    this.empleados.add(empleado);
            List<Empleado> empleadosInArea = this.listEmpleadosByIdArea(empleado.getArea().getId_area());
            for (Empleado empleadoInArea : empleadosInArea)
            {
                if (this.hasRol(empleadoInArea, Constantes.ROL_RECEPTORA))
                    if (!this.empleados.contains(empleadoInArea))
                        this.empleados.add(empleadoInArea);
            }
            List<Empleado> empleadosInAreaPadre = this.listEmpleadosByIdArea(empleado.getArea().getId_area_padre());
            for (Empleado empleadoInPoolAreaPadre : empleadosInAreaPadre)
            {
                if (this.hasRol(empleadoInPoolAreaPadre,
                        Constantes.ROL_RECEPTORA))
                    if (!this.empleados.contains(empleadoInPoolAreaPadre))
                        this.empleados.add(empleadoInPoolAreaPadre);

            }
            // si no se han encontrado elementos en la lista y el area padre es
            // nula, continuar con el área padre
            if (this.empleados.isEmpty()
                    && !empleadosInAreaPadre.isEmpty()
                    && empleadosInAreaPadre.get(Constantes.FIRST).getArea().getId_area_padre() != null)
                this.addElementToListReceptoras(empleadosInAreaPadre.get(Constantes.FIRST));

        } catch (Exception e)
        {
            System.out.println("Ha ocurrido un error al encontrar las áreas receptoras :"
                    + e.getMessage());
        }
    }

    /**
     * Obtiene los usuario con el rol de receptor de un area en particular
     * @param destinatario
     * @return 
     */
    public List<Empleado> listReceptorasFromArea(Empleado destinatario)
    {
        List<Empleado> receptoras = new ArrayList<Empleado>();
        try
        {
            List<Empleado> empleadosDeAreaDestinatario = this.listEmpleadosByIdArea(destinatario.getArea().getId_area_padre());
            for (Empleado empleado : empleadosDeAreaDestinatario)
            {
                if (hasRol(empleado, Constantes.ROL_RECEPTORA))// si el empleado tiene el rol de receptora, entonces se agrega a la lista
                    receptoras.add(empleado);
            }
        } catch (Exception e)
        {
            log.error("Ha ocurrido un error ->", e);
        }
        return receptoras;
    }

    /**
     * Obtiene la lista de las receptoras de un area, si la lista esta vacia, se agrega al detinatario final
     * @param destinatario
     * @return 
     */
    public List<Empleado> getListReceivingWithRecipient(Empleado destinatario)
    {
        List<Empleado> receiving = this.listReceptorasFromArea(destinatario);
        if (receiving.isEmpty())// si no existen receptoras en el area, entonces el asunto le llega al destinario final directamente
            receiving.add(destinatario);
        return receiving;
    }

    /**
     * Comprueba si el empleado a fin tiene un rol
     * @param empleado
     * @param rol
     * @return 
     */
    public boolean hasRol(Empleado empleado, String rol)
    {
        List<Rol> roles = empleado.getRoles();
        for (Iterator<Rol> it = roles.iterator(); it.hasNext();)
        {
            Rol rolin = it.next();
            if (rolin.getRol().equals(rol))
                return true;
        }
        return false;
    }

    /**
     * @param empleadoDao
     *            the empleadoDao to set
     */
    public void setEmpleadoDao(EmpleadoDao empleadoDao)
    {
        this.empleadoDao = empleadoDao;
    }

    /**
     * @param correoService
     *            the correoService to set
     */
    public void setCorreoService(CorreoService correoService)
    {
        this.correoService = correoService;
    }

    public List<Rol> listRoles()
    {
        return this.empleadoDao.listarRoles();
    }

    public boolean isUsado(Integer id_empleado)
    {
        return this.empleadoDao.isUsado(id_empleado);
    }
}
