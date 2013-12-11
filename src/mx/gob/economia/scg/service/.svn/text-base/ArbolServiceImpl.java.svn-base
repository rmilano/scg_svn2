/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.economia.scg.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import mx.gob.economia.scg.model.Area;
import mx.gob.economia.scg.model.Empleado;
import mx.gob.economia.scg.persistence.AreaDao;
import mx.gob.economia.scg.util.Compuesto;
import mx.gob.economia.scg.util.Constantes;

/**
 *
 * @author gerardo
 */
public class ArbolServiceImpl implements ArbolService
{

    private AreaDao areaDao;
    private static List<Area> areas = new ArrayList<Area>();
    
    /**
     * Carga los arboles correspondientes
     * @param request
     */

    public void loadArboles(HttpServletRequest request)
    {
        Empleado empleado_ses = (Empleado) request.getSession().getAttribute(
                Constantes.USUARIO_SESION);
        if (request.getSession().getAttribute(Constantes.ARBOL_RAIZ) == null)
        {
            request.getSession().setAttribute(Constantes.ARBOL_RAIZ,
                    this.getAreaRoot(Constantes.AREA_INTERNA));
        }
        if (request.getSession().getAttribute(Constantes.ARBOL_RAMA) == null)
        {
            /*request.getSession().setAttribute(Constantes.ARBOL_RAMA,
                    this.getParentArea(empleado_ses.getArea()));*/
            request.getSession().setAttribute(Constantes.ARBOL_RAMA,
                    this.getParentArea(this.areaDao.getAreaById(empleado_ses.getArea().getId_area_padre())));
        }
        if (request.getSession().getAttribute(Constantes.ARBOL_EXTERNO) == null)
        {
            request.getSession().setAttribute(Constantes.ARBOL_EXTERNO,
                    this.getAreaRoot(Constantes.AREA_EXTERNA));
        }

    }

    /**
     * Obtiene la unidad administradtiva inmediata Sueperior
     * @param area
     * @return
     */
    public Area getUnidadAdmva(Integer idArea)
    {        
        Area unidadAdministrativa = this.areaDao.getAreaByIdWitoutFilter(idArea);
        if(unidadAdministrativa.getUnidad_administrativa().equals(Constantes.ACTIVADO))//si el area es unidad administrativa, regrea area.
            return unidadAdministrativa;
        if(unidadAdministrativa.getId_area_padre() != null && unidadAdministrativa.getId_area_padre() != Constantes.INSTANCIA_CREADA )//si no es nula y ademas no es el inicio del arbol
         unidadAdministrativa =  this.getUnidadAdmva(unidadAdministrativa.getId_area_padre());//de lo contrario, se recorre el arbol en forma ascendente
        return unidadAdministrativa;
    }
    /**
     * Obtiene solo la descripcion del area raiz dependiento el tipo de area,
     * area interna o externa. tipo = 0 : interno, tipo = 1 externo
     * @param tipo
     * @return arbol
     */
    public String getAreaRoot(Integer tipo)
    {
        Area areaRaiz = this.areaDao.getAreaRaiz(tipo);
        Compuesto root = new Compuesto(areaRaiz.getArea(), areaRaiz.getId_area());
        root.agregar(new Compuesto("", null));
        return root.mostrar(Constantes.TAG_IDENT);
    }
    
    /**
     * Obtiene solo la descripcion del area a la que pertence el empleado
     * @param area
     * @return
     */
    public String getParentArea(Area padre)
    {
        Compuesto root = new Compuesto(padre.getArea(), padre.getId_area());
        root.agregar(new Compuesto("", null));
        return root.mostrar(Constantes.TAG_IDENT);
    }

    /**
     * Obtiene los arboles con sus respectivo hijos.
     * @param tipo
     * @return
     */
    public String getArbolAreas(Integer tipo)
    {        
        Area areaRaiz = this.areaDao.getAreaRaiz(tipo);
        Compuesto root = new Compuesto(areaRaiz.getArea(), areaRaiz.getId_area());
        this.createTree(root);
        return root.mostrar(Constantes.TAG_IDENT);
    }

    /**
     * Obtiene el area al que pertenece el empleado y sus hijos correspondientes
     * y forma el arbol dinamicamente
     * @param padre
     * @return
     */
    public String getRamaArbolAreas(Area padre)
    {
        Compuesto root = new Compuesto(padre.getArea(), padre.getId_area());
        this.createTree(root);
        return root.mostrar(Constantes.TAG_IDENT);
    }

    /**
     * Obtiene solo las areas hijas sin construir el arbol en la vista
     * @param idArea
     * @return
     */
    public List<Area> getSubArbolByArea(Integer idArea)
    {
        List<Area> listAreas = this.areaDao.getAreasByIdPadre(idArea);//obtener todas las �reas que pertenecen al nodo padre.
        for (Iterator<Area> it = listAreas.iterator(); it.hasNext();)
        {
            Area area = it.next();
            this.areas.add(area);
            this.getSubArbolByArea(area.getId_area());
        }
        return areas;
    }

    /**
     * Obtiene solo las areas hijas sin construir el arbol en la vista
     * @param idArea
     * @return
     */
    public List<Area> listSubArbolByArea(Integer idArea)
    {	this.areas = new ArrayList<Area>();
        return this.getSubArbolByArea(idArea);
    }
    /**
     * Por implementar
     * @param idArea
     * @return
     */
    public List<Empleado> getEmpleadosByIdArea(Integer idArea)
    {
        return null;
    }
    
    /**
     * Limpiar las areas ante una posible consulta previa
     */
    public void cleanListArea()
    {
        this.areas.clear();
    }
    /**
     * Crea el arbol a partir del padre.
     * @param padre
     */
    public void createTree(Compuesto padre)
    {
        List<Area> area = this.areaDao.getAreasByIdPadre(padre.getId());//obtener todas las �reas que pertenecen al nodo padre.
        for (Iterator<Area> it = area.iterator(); it.hasNext();)
        {
            Area areaHijo = it.next();
            Compuesto nodo = new Compuesto(areaHijo.getArea(), areaHijo.getId_area());
            padre.agregar(nodo);
            this.createTree(nodo);
        }
    }

    /**
     * @param areaService the areaDao to set
     */
    public void setAreaDao(AreaDao areaDao)
    {
        this.areaDao = areaDao;
    }
}
