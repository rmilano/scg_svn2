package mx.gob.economia.scg.service;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import mx.gob.economia.scg.model.Empleado;
import mx.gob.economia.scg.util.Constantes;

/**
 * Implementation CorreoService
 * 
 * @author gerardo.roque
 * @modify valentin.gomez
 * 
 */
public class LdapServiceImpl implements LdapService {

	String inicialContextFactory;
	String providerUrl;
	String securityAuthentication;
	String securityPrincipal;
	String securityCredentials;

	/**
	 * Obtiene una referencia al DirContext
	 */
	public DirContext getDirContext() throws Exception {
		Hashtable<String, String> env = new Hashtable<String, String>(11);
		env.put(Context.INITIAL_CONTEXT_FACTORY, inicialContextFactory);
		env.put(Context.PROVIDER_URL, providerUrl);
		env.put(Context.SECURITY_AUTHENTICATION, securityAuthentication);
		env.put(Context.SECURITY_PRINCIPAL, securityPrincipal);
		env.put(Context.SECURITY_CREDENTIALS, securityCredentials);
		return new InitialDirContext(env);
	}

	/**
	 * Realiza la busqueda basica en un sub-arbol
	 */
	public Empleado getEmpleado(String clave) {
		try {
			Attributes atributos = this.buscarEmpleado("uid", clave,
					"ou=Direcciones");

			byte[] byteArr = (byte[]) atributos.get("userPassword").get();
			Integer activo = atributos.get("activado") != null ? (Integer) atributos
					.get("activado").get()
					: Constantes.ACTIVO;
			String contrasenna = new String(byteArr, "US-ASCII");
			String correo = atributos.get("correo") != null ? (String) atributos
					.get("correo").get()
					: null;

			return new Empleado(activo, contrasenna, correo);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * Realiza la busqueda basica en un sub-arbol
	 */
	public Attributes buscarEmpleado(String atributo, String valor,
			String grupoPadre) throws Exception {

		DirContext ctx = getDirContext();
		SearchControls ctls = new SearchControls();
		ctls.setReturningObjFlag(true);
		ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		String filter = "(&(" + atributo + "=" + valor + "))";

		// Busca los atributos del objeto
		NamingEnumeration<SearchResult> enumeracion = ctx.search(grupoPadre,
				filter, ctls);

		SearchResult sr = null;
		try {
			while (enumeracion.hasMore()) {
				sr = (SearchResult) enumeracion.next();
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
		ctx.close();
		return sr.getAttributes();
	}

	/**
	 * @return the inicialContextFactory
	 */
	public String getInicialContextFactory() {
		return inicialContextFactory;
	}

	/**
	 * @param inicialContextFactory
	 *            the inicialContextFactory to set
	 */
	public void setInicialContextFactory(String inicialContextFactory) {
		this.inicialContextFactory = inicialContextFactory;
	}

	/**
	 * @return the providerUrl
	 */
	public String getProviderUrl() {
		return providerUrl;
	}

	/**
	 * @param providerUrl
	 *            the providerUrl to set
	 */
	public void setProviderUrl(String providerUrl) {
		this.providerUrl = providerUrl;
	}

	/**
	 * @return the securityAuthentication
	 */
	public String getSecurityAuthentication() {
		return securityAuthentication;
	}

	/**
	 * @param securityAuthentication
	 *            the securityAuthentication to set
	 */
	public void setSecurityAuthentication(String securityAuthentication) {
		this.securityAuthentication = securityAuthentication;
	}

	/**
	 * @return the securityPrincipal
	 */
	public String getSecurityPrincipal() {
		return securityPrincipal;
	}

	/**
	 * @param securityPrincipal
	 *            the securityPrincipal to set
	 */
	public void setSecurityPrincipal(String securityPrincipal) {
		this.securityPrincipal = securityPrincipal;
	}

	/**
	 * @return the securityCredentials
	 */
	public String getSecurityCredentials() {
		return securityCredentials;
	}

	/**
	 * @param securityCredentials
	 *            the securityCredentials to set
	 */
	public void setSecurityCredentials(String securityCredentials) {
		this.securityCredentials = securityCredentials;
	}
}