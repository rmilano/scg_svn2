package mx.gob.economia.scg.service;

import java.util.List;
import java.util.Map;
import mx.gob.economia.scg.model.CriterioTema;

import mx.gob.economia.scg.model.Tema;

/**
 * 
 * @author valentin.gomez
 */
public interface TemaService
{

    public Tema getTema(Integer id_tema);

    public List<Tema> getTemasByCriterio(CriterioTema criterioTema);

    public Integer countTemaByCriterio(CriterioTema criterioTema);

    public List<Tema> listTemas(Integer id_area);

    public List<Tema> getTemasByIdPadre(Integer idTemaPadre);

    public Map<Integer, List<Tema>> getSubtemasFromId(Integer id_area);

    public Integer saveTema(Tema tema);

    public void updateTema(Tema tema);

    public void deleteTema(Integer id_tema);

    public List<String> getIdsTemaByRevisor(Integer idEmpleado);

    public Integer saveTemaRevisor(Tema tema);
}
