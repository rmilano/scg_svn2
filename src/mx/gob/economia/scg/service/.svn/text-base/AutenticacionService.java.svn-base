package mx.gob.economia.scg.service;

import mx.gob.economia.scg.model.Empleado;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.springframework.dao.DataAccessException;

public interface AutenticacionService {
	public UserDetails loadUserByUsername(String correo)
			throws UsernameNotFoundException, DataAccessException;

	public GrantedAuthority[] getAuthorities(Empleado empleado);

}
