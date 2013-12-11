package mx.gob.economia.scg.service;

import java.util.ArrayList;
import java.util.List;

import mx.gob.economia.scg.model.Empleado;
import mx.gob.economia.scg.model.Rol;
import mx.gob.economia.scg.util.Constantes;
import mx.gob.economia.scg.util.Util;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;

public class AutenticacionServiceImpl implements UserDetailsService, AutenticacionService {

	private LdapService ldapService;
	private EmpleadoService empleadoService;

	public UserDetails loadUserByUsername(String correo)
			throws UsernameNotFoundException, DataAccessException {
		Empleado empleado = null;
		try {
			// Autenticacion por medio de ldap "todo"
			//empleado = ldapService.getEmpleado(correo);
			empleado = empleadoService.getEmpleado(correo);
			if (empleado == null || empleado.getArea()==null)
				throw new UsernameNotFoundException("Empleado " + correo
						+ " no encontrado");
			//empleado.setContrasenna(Util.encriptarSha("prueba"));
		} catch (Exception e) {			
			throw new DataRetrievalFailureException(
					"Error para obtener usuario", e);
		}

		return new org.acegisecurity.userdetails.User(empleado.getCorreo(),
				empleado.getContrasenna(), empleado.getActivo().equals(
						Constantes.ACTIVO), true, true, true,
				getAuthorities(empleado));
	}

	
	public GrantedAuthority[] getAuthorities(Empleado empleado) {
		GrantedAuthority[] g = null;
		List authList = null;
		if (empleado != null) {
			authList = new ArrayList();
			List<Rol> roles = empleado.getRoles();
			for (Rol rol : roles) {
				authList.add(new GrantedAuthorityImpl("ROLE_"+rol.getRol()));
			}
//			authList.add(new GrantedAuthorityImpl(empleado.getRol()));
		}
		g = (GrantedAuthority[]) authList.toArray(new GrantedAuthority[] {});
		return g;
	}

	/**
	 * @param ldapService
	 *            the ldapService to set
	 */
	public void setLdapService(LdapService ldapService) {
		this.ldapService = ldapService;
	}

	/**
	 * @param empleadoService
	 *            the empleadoService to set
	 */
	public void setEmpleadoService(EmpleadoService empleadoService) {
		this.empleadoService = empleadoService;
	}

}
