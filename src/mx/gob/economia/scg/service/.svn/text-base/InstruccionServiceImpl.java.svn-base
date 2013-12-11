package mx.gob.economia.scg.service;

import java.util.List;
import mx.gob.economia.scg.model.Area;
import mx.gob.economia.scg.model.CriterioInstruccion;

import mx.gob.economia.scg.model.Instruccion;
import mx.gob.economia.scg.persistence.InstruccionDao;

/**
 * 
 * @author valentin.gomez
 */
public class InstruccionServiceImpl implements InstruccionService
{

    private InstruccionDao instruccionDao;
    private ArbolService arbolService;

    public Instruccion getInstruccion(Integer id_instruccion)
    {
        Instruccion instruccion = instruccionDao.getInstruccion(id_instruccion);
        instruccion = instruccion == null ? new Instruccion() : instruccion;
        return instruccion;
    }

    public List<Instruccion> listInstrucciones(Integer id_area)
    {
        Area area = this.arbolService.getUnidadAdmva(id_area);
        return instruccionDao.listInstrucciones(id_area);
    }

    public Integer saveInstruccion(Instruccion instruccion)
    {
        return instruccionDao.saveInstruccion(instruccion);
    }

    public void updateInstruccion(Instruccion instruccion)
    {
        instruccionDao.updateInstruccion(instruccion);
    }

    public void deleteInstruccion(Integer id_instruccion)
    {
        instruccionDao.deleteInstruccion(id_instruccion);
    }

    /**
     * @param instruccionDao the instruccionDao to set
     */
    public void setInstruccionDao(InstruccionDao instruccionDao)
    {
        this.instruccionDao = instruccionDao;
    }

    /**
     * @param arbolService the arbolService to set
     */
    public void setArbolService(ArbolService arbolService)
    {
        this.arbolService = arbolService;
    }

    public List<Instruccion> listInstruccionesByPagina(CriterioInstruccion criterio)
    {
        return this.instruccionDao.listInstruccionesByPagina(criterio);
    }

    public int countInstruccionesByCriterio(CriterioInstruccion criterio)
    {
        return this.instruccionDao.countInstruccionesByCriterio(criterio);
    }
}
