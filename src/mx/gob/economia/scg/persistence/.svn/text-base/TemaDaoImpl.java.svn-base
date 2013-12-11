package mx.gob.economia.scg.persistence;

import java.util.List;
import mx.gob.economia.scg.model.CriterioTema;

import mx.gob.economia.scg.model.Tema;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

/**
 * 
 * @author valentin.gomez
 */
public class TemaDaoImpl extends SqlMapClientTemplate implements
        TemaDao
{
    
    /**
     * Obtiene el tema por id
     * @param id_tema
     * @return
     */
    public Tema getTema(Integer id_tema)
    {
        return (Tema) queryForObject("Tema.getTema",
                id_tema);
    }

    /**
     * Obtiene los temas por criterio
     * @param criterioTema
     * @return
     */
    public List<Tema> getTemasByCriterio(CriterioTema criterioTema)
    {
        return(List<Tema>) queryForList("Tema.listTemasCriterio", criterioTema);
    }
    /**
     * Obtiene el no. de registros por criterio
     * @param criterioTema
     * @return
     */
    public Integer countTemaByCriterio(CriterioTema criterioTema)
    {
        return (Integer ) queryForObject("Tema.countTemasCriterio", criterioTema);
    }
    /**
     * Listado de temas por area
     * @param id_area
     * @return
     */
    public List<Tema> listTemas(Integer id_area)
    {
        return (List<Tema>) queryForList("Tema.listTemas", id_area);
    }
    /**
     * Lista de temas por padre
     * @param idTemaPadre
     * @return
     */
    public List<Tema> listTemasByIdPadre(Integer idTemaPadre)
    {
        return (List<Tema>) queryForList("Tema.getTemasByIdPadre", idTemaPadre);
    }

    /**
     * Lista los temas correspondientes a cada revisor
     * @param idEmpleado
     * @return
     */
    public List<String> getIdsTemaByRevisor(Integer idEmpleado)
    {
        return (List<String>) queryForList("Tema.getIdsTemaByRevisor", idEmpleado);
    }

    /**
     * Guardar tema
     * @param tema
     * @return
     */
    public Integer saveTema(Tema tema)
    {
        return (Integer) insert("Tema.insert", tema);
    }
    /**
     * Guardar la relacion tema revisor
     * @param tema
     * @return
     */
    public Integer saveTemaRevisor(Tema tema)
    {
        return (Integer) insert("Tema.insertTemaRevisor", tema);
    }

    /**
     * Actualizar tema
     * @param tema
     */
    public void updateTema(Tema tema)
    {
        update("Tema.update", tema);
    }

    /**
     * Borrar tema
     * @param id_tema
     */
    public void deleteTema(Integer id_tema)
    {
        delete("Tema.delete", id_tema);
    }
}
