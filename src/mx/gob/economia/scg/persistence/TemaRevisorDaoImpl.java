/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.economia.scg.persistence;

import mx.gob.economia.scg.model.Empleado;
import mx.gob.economia.scg.model.Tema;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

/**
 *
 * @author javier
 */
public class TemaRevisorDaoImpl extends SqlMapClientTemplate implements TemaRevisorDao{
    
	public void saveTemaRevisor(Tema tema, Empleado empleado) {
		insert("TemaRevisor.insert", tema);
	}

}
