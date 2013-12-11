package mx.gob.economia.scg.form;

import java.util.ArrayList;
import java.util.List;

import mx.gob.economia.scg.model.Prioridad;

import org.apache.struts.action.ActionForm;

/**
 * 
 * @author valentin.gomez
 */
public class PrioridadForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2707521495057160598L;

	private Prioridad prioridad = new Prioridad();
	private List<Prioridad> prioridades = new ArrayList<Prioridad>();

	
	public PrioridadForm() {
	}

	/**
	 * Constructor
	 * 
	 * @param prioridad
	 * @param prioridades
	 */
	public PrioridadForm(Prioridad prioridad, List<Prioridad> prioridades) {
		this.prioridad = prioridad;
		this.prioridades = prioridades;
	}

	/**
	 * @return the prioridad
	 */
	public Prioridad getPrioridad() {
		return prioridad;
	}

	/**
	 * @param prioridad
	 *            the prioridad to set
	 */
	public void setPrioridad(Prioridad prioridad) {
		this.prioridad = prioridad;
	}

	/**
	 * @return the prioridades
	 */
	public List<Prioridad> getPrioridades() {
		return prioridades;
	}

	/**
	 * @param prioridades
	 *            the prioridades to set
	 */
	public void setPrioridades(List<Prioridad> prioridades) {
		this.prioridades = prioridades;
	}

}
