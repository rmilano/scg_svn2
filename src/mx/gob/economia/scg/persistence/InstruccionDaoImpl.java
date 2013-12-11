package mx.gob.economia.scg.persistence;

import java.util.List;
import mx.gob.economia.scg.model.CriterioInstruccion;

import mx.gob.economia.scg.model.Instruccion;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

/**
 * 
 * @author valentin.gomez
 */
public class InstruccionDaoImpl extends SqlMapClientTemplate implements
		InstruccionDao {

	public Instruccion getInstruccion(Integer id_instruccion) {
		return (Instruccion) queryForObject("Instruccion.getInstruccion",
				id_instruccion);
	}

	public List<Instruccion> listInstrucciones(Integer id_area) {
		return (List<Instruccion>) queryForList("Instruccion.listInstrucciones", id_area);
	}

	public Integer saveInstruccion(Instruccion instruccion) {
		return (Integer) insert("Instruccion.insert", instruccion);
	}

	public void updateInstruccion(Instruccion instruccion) {
		update("Instruccion.update", instruccion);
	}

	public void deleteInstruccion(Integer id_instruccion) {
		delete("Instruccion.delete", id_instruccion);
	}

    public List<Instruccion> listInstruccionesByPagina(CriterioInstruccion criterio) {
       return (List<Instruccion>) queryForList("Instruccion.listInstruccionesByPagina",criterio);
    }

    public int countInstruccionesByCriterio(CriterioInstruccion criterio) {
        return (Integer) queryForObject("Instruccion.countInstruccionesByCriterio",criterio);
    }
}
