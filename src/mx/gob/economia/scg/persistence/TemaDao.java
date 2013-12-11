package mx.gob.economia.scg.persistence;

import mx.gob.economia.scg.model.Tema;
import java.util.List;
import mx.gob.economia.scg.model.CriterioTema;

/**
 *
 * @author valentin.gomez
 */
public interface TemaDao
{

    public Tema getTema(Integer id_tema);

    public List<Tema> getTemasByCriterio(CriterioTema criterioTema);

    public Integer countTemaByCriterio(CriterioTema criterioTema);

    public List<Tema> listTemas(Integer id_area);

    public List<Tema> listTemasByIdPadre(Integer idTemaPadre);

    public Integer saveTema(Tema tema);

    public void updateTema(Tema tema);

    public void deleteTema(Integer id_tema);

    public List<String> getIdsTemaByRevisor(Integer idEmpleado);

    public Integer saveTemaRevisor(Tema tema);
}
