package mx.gob.economia.scg.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mx.gob.economia.scg.model.Area;
import mx.gob.economia.scg.model.CriterioTema;

import mx.gob.economia.scg.model.Tema;
import mx.gob.economia.scg.persistence.TemaDao;
import mx.gob.economia.scg.util.Constantes;

/**
 * 
 * @author valentin.gomez
 */
public class TemaServiceImpl implements TemaService
{

    private TemaDao temaDao;
    private ArbolService arbolService;

    /**
     * Obtiene el tema por id
     * @param id_tema
     * @return
     */
    public Tema getTema(Integer id_tema)
    {
        return temaDao.getTema(id_tema);
    }

    /**
     * El no. de registros por criterio
     * @param criterioTema
     * @return
     */
    public Integer countTemaByCriterio(CriterioTema criterioTema)
    {
        return this.temaDao.countTemaByCriterio(criterioTema);
    }
    public List<Tema> getTemasByCriterio(CriterioTema criterioTema)
    {
        return this.temaDao.getTemasByCriterio(criterioTema);
    }
    /**
     * Los temas son por unidad administrativa. Se hace un recorrido de la estructura
     * en forma ascendente para optener la unidad administrativa inmediata superior
     * @param id_area
     * @return
     */
    public List<Tema> listTemas(Integer id_area)
    {
        Area area = this.arbolService.getUnidadAdmva(id_area);//el area administrativa inmediata superior
        return temaDao.listTemas(area.getId_area());
    }

    /**
     * Guardar el tema
     * @param tema
     * @return
     */
    public Integer saveTema(Tema tema)
    {
        return temaDao.saveTema(tema);
    }
    /**
     * Guardar la relacion tema revisor
     * @param tema
     * @return
     */
    public Integer saveTemaRevisor(Tema tema)
    {
        return temaDao.saveTemaRevisor(tema);
    }

    /**
     * Actualizar tema
     * @param tema
     */
    public void updateTema(Tema tema)
    {
        temaDao.updateTema(tema);
    }

    /**
     * Borrra tema
     * @param id_tema
     */
    public void deleteTema(Integer id_tema)
    {
        temaDao.deleteTema(id_tema);
    }

    /**
     * Obtiene el subtema
     * @param idTema
     * @return
     */
    public Map<Integer, List<Tema>> getSubtemasFromId(Integer idTema)
    {
        Tema tema = this.getTema(idTema);//obtengo el tema a fin
        List<Tema> temas = new ArrayList<Tema>();
        Map<Integer, List<Tema>> result = new HashMap<Integer, List<Tema>>();
        if (tema.getEvento().equals(Constantes.NO_ACTIVO))//si el tema principal no es un evento, se obtienen los hijos
        {
            temas = this.getTemasByIdPadre(tema.getId_tema());
        }
        result.put(tema.getEvento(), temas);
        return result;
    }

    /**
     * Las lista de temas que tienen un padre en particular
     * @param idTemaPadre
     * @return
     */
    public List<Tema> getTemasByIdPadre(Integer idTemaPadre)
    {
        return temaDao.listTemasByIdPadre(idTemaPadre);
    }

    /**
     * Obtienes los temas correspondientes a un revisor
     * @param idEmpleado
     * @return
     */
    public List<String> getIdsTemaByRevisor(Integer idEmpleado)
    {
        return this.temaDao.getIdsTemaByRevisor(idEmpleado);
    }
    /**
     * @param temaDao the temaDao to set
     */
    public void setTemaDao(TemaDao temaDao)
    {
        this.temaDao = temaDao;
    }

    /**
     * @param arbolService the arbolService to set
     */
    public void setArbolService(ArbolService arbolService)
    {
        this.arbolService = arbolService;
    }
}
