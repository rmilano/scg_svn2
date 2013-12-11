package mx.gob.economia.scg.form;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import mx.gob.economia.scg.model.Asunto;
import mx.gob.economia.scg.model.CriterioAsunto;
import mx.gob.economia.scg.model.Evento;
import mx.gob.economia.scg.model.Expediente;
import mx.gob.economia.scg.model.Prioridad;
import mx.gob.economia.scg.model.TipoDocumento;
import mx.gob.economia.scg.model.Tema;

import org.apache.struts.action.ActionForm;

public class ConsultaForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2452762297043411998L;

	// Propiedades
	private int idx;
	private Integer id_asunto;
	private CriterioAsunto criterioAsunto = new CriterioAsunto();
	private List<Asunto> asuntos = new ArrayList<Asunto>();
        private List<Expediente> expedientes = new ArrayList<Expediente>();
        private List<Tema> temas = new ArrayList<Tema>();
        private List<Evento> eventos = new ArrayList<Evento>();
        private List<Prioridad> prioridades = new ArrayList<Prioridad>();
        private List<TipoDocumento> tiposDocumento = new ArrayList<TipoDocumento>();
	private Asunto asunto = new Asunto();
	private Map<String, String> estatusMap;
	private ArrayList estatus = new ArrayList();
        private String nuevaConsulta = "";
        private String chksRep = "";

	/**
	 * @return the idx
	 */
	public int getIdx() {
		return idx;
	}

	/**
	 * @param idx
	 *            the idx to set
	 */
	public void setIdx(int idx) {
		this.idx = idx;
	}

	/**
	 * @return the id_asunto
	 */
	public Integer getId_asunto() {
		return id_asunto;
	}

	/**
	 * @param id_asunto
	 *            the id_asunto to set
	 */
	public void setId_asunto(Integer id_asunto) {
		this.id_asunto = id_asunto;
	}

	/**
	 * @return the criterioAsunto
	 */
	public CriterioAsunto getCriterioAsunto() {
		return criterioAsunto;
	}

	/**
	 * @param criterioAsunto
	 *            the criterioAsunto to set
	 */
	public void setCriterioAsunto(CriterioAsunto criterioAsunto) {
		this.criterioAsunto = criterioAsunto;
	}

	/**
	 * @return the asuntos
	 */
	public List<Asunto> getAsuntos() {
		return asuntos;
	}

	/**
	 * @param asuntos
	 *            the asuntos to set
	 */
	public void setAsuntos(List<Asunto> asuntos) {
		this.asuntos = asuntos;
	}

	/**
	 * @return the asunto
	 */
	public Asunto getAsunto() {
		return asunto;
	}

	/**
	 * @param asunto
	 *            the asunto to set
	 */
	public void setAsunto(Asunto asunto) {
		this.asunto = asunto;
	}

	/**
	 * @return the estatusMap
	 */
	public Map<String, String> getEstatusMap() {
		estatusMap = new LinkedHashMap<String, String>();
		estatusMap.put("0", "Pendiente");
		estatusMap.put("1", "Turnado");
		estatusMap.put("2", "Atendido");
		estatusMap.put("3", "Concluido");
		estatusMap.put("4", "En tiempo");
		estatusMap.put("5", "Por vencer");
		estatusMap.put("6", "Vencido");
		return estatusMap;
	}

	/**
	 * @param estatusMap
	 *            the estatusMap to set
	 */
	public void setEstatusMap(Map<String, String> estatusMap) {
		this.estatusMap = estatusMap;
	}

    /**
     * @return the expedientes
     */
    public List<Expediente> getExpedientes() {
        return expedientes;
    }

    /**
     * @param expedientes the expedientes to set
     */
    public void setExpedientes(List<Expediente> expedientes) {
        this.expedientes = expedientes;
    }

    /**
     * @return the temas
     */
    public List<Tema> getTemas() {
        return temas;
    }

    /**
     * @param temas the temas to set
     */
    public void setTemas(List<Tema> temas) {
        this.temas = temas;
    }

    /**
     * @return the eventos
     */
    public List<Evento> getEventos() {
        return eventos;
    }

    /**
     * @param eventos the temas to set
     */
    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    /**
     * @return the nuevaConsulta
     */
    public String getNuevaConsulta() {
        return nuevaConsulta;
    }

    /**
     * @param nuevaConsulta the nuevaConsulta to set
     */
    public void setNuevaConsulta(String nuevaConsulta) {
        this.nuevaConsulta = nuevaConsulta;
    }

    /**
     * @return the prioridades
     */
    public List<Prioridad> getPrioridades()
    {
        return prioridades;
    }

    /**
     * @param prioridades the prioridades to set
     */
    public void setPrioridades(List<Prioridad> prioridades)
    {
        this.prioridades = prioridades;
    }

    /**
     * @return the tipos_documento
     */
    public List<TipoDocumento> getTiposDocumento()
    {
        return tiposDocumento;
    }

    /**
     * @param tipos_documento the tipos_documento to set
     */
    public void setTiposDocumento(List<TipoDocumento> tiposDocumento)
    {
        this.tiposDocumento = tiposDocumento;
    }

    /**
     * @return the estatus
     */
    public ArrayList getEstatus()
    {
        return estatus;
    }

    /**
     * @param estatus the estatus to set
     */
    public void setEstatus(ArrayList estatus)
    {
        this.estatus = estatus;
    }

    public String getChksRep() {
        return chksRep;
    }

    public void setChksRep(String chksRep) {
        this.chksRep = chksRep;
    }
    
}
