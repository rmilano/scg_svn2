package mx.gob.economia.scg.service;

import java.util.List;
import mx.gob.economia.scg.model.CriterioInstruccion;

import mx.gob.economia.scg.model.Instruccion;

/**
 * 
 * @author valentin.gomez
 */
public interface InstruccionService {

	Instruccion getInstruccion(Integer id_instruccion);

	List<Instruccion> listInstrucciones(Integer id_area);
        
        int countInstruccionesByCriterio(CriterioInstruccion criterio);
        
        List<Instruccion> listInstruccionesByPagina(CriterioInstruccion criterio);

	Integer saveInstruccion(Instruccion instruccion);

	void updateInstruccion(Instruccion instruccion);

	void deleteInstruccion(Integer id_instruccion);

}
