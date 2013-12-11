package mx.gob.economia.scg.form;

import java.util.LinkedHashMap;
import java.util.Map;

public class ConsultaBandejaForm extends ConsultaForm {

	private static final long serialVersionUID = -3196442061674235989L;
	private Map<String, String> estatusMap;

	/**
	 * @return the estatusMap
	 */
	public Map<String, String> getEstatusMap() {
		estatusMap = new LinkedHashMap<String, String>();

		estatusMap.put("0", "PENDIENTE");
		estatusMap.put("1", "POR ATENDER");
		estatusMap.put("2", "PARA VO.BO.");
		estatusMap.put("3", "CONCLUIDO");
		estatusMap.put("4", "EN TIEMPO");
		estatusMap.put("5", "POR VENCER");
		estatusMap.put("6", "VENCIDO");

		return estatusMap;
	}

	/**
	 * @param estatusMap
	 *            the estatusMap to set
	 */
	public void setEstatusMap(Map<String, String> estatusMap) {
		this.estatusMap = estatusMap;
	}

}
