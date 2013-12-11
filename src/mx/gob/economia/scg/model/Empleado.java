package mx.gob.economia.scg.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.gob.economia.scg.util.Util;

public class Empleado implements Serializable
{

    // Constantes
    public static final Integer INTERNO = 0;
    public static final Integer EXTERNO = 1;
    // Propiedades
    private int idx;
    private Integer id_empleado;
    private String correo;
    private String contrasenna;
    private String rol;
    private String nombre;
    private String paterno;
    private String materno;
    private String puesto;
    private Integer activo;
    private String rfc;
    private Integer id_area;
    private Area area = new Area();
    private List<Rol> roles = new ArrayList<Rol>();
    private Instruccion instruccion;
    private Prioridad prioridad;
    private Date fh_limite;
    private String fh_limiteDDMMYYYY;
    private String nombre_completo;
    private String puesto_nombre;
    private String comentario;
    private String comentarioSaltos;
    private Integer ocupado; //Para ver si esta involucrado en algun asunto no finalizado, no se puede eliminar
    private String correo_alterno;

    /**
     * Constructor vacio
     */
    public Empleado()
    {
        this.id_empleado = null;
        this.nombre = "";
        this.paterno = "";
        this.materno = "";
        this.rfc = "";
        this.roles = new ArrayList<Rol>();
        this.instruccion = new Instruccion();
        this.prioridad = new Prioridad();
        this.fh_limite = new Date();
        this.comentario = "";
        this.comentarioSaltos = "";
        this.ocupado = 0;
    }

    public boolean hasComentarioPersonalizado()
    {
        if (this.comentario != null && !this.comentario.trim().equals(""))//si el empleado tiene comentario personalizado
            return true;
        return false;
    }

    /**
     * Constructor con identificador
     *
     * @param contrasenna
     * @param correo
     */
    public Empleado(Integer id_empleado)
    {
        this.id_empleado = id_empleado;
    }

    public Integer getOcupado() {
        return ocupado;
    }

    public void setOcupado(Integer ocupado) {
        this.ocupado = ocupado;
    }

    /**
     * Constructor simple
     *
     * @param contrasenna
     * @param correo
     */
    public Empleado(Integer activo, String contrasenna, String correo)
    {
        this.activo = activo;
        this.contrasenna = contrasenna;
        this.correo = correo;
    }

    /**
     * @return the idx
     */
    public int getIdx()
    {
        return idx;
    }

    /**
     * @param idx
     *            the idx to set
     */
    public void setIdx(int idx)
    {
        this.idx = idx;
    }

    /**
     * @return the id_empleado
     */
    public Integer getId_empleado()
    {
        return id_empleado;
    }

    /**
     * @param id_empleado
     *            the id_empleado to set
     */
    public void setId_empleado(Integer id_empleado)
    {
        this.id_empleado = id_empleado;
    }

    /**
     * @return the correo
     */
    public String getCorreo()
    {
        return correo;
    }

    /**
     * @param correo
     *            the correo to set
     */
    public void setCorreo(String correo)
    {
        this.correo = correo;
    }

    /**
     * @return the contrasenna
     */
    public String getContrasenna()
    {
        return contrasenna;
    }

    /**
     * @param contrasenna
     *            the contrasenna to set
     */
    public void setContrasenna(String contrasenna)
    {
        this.contrasenna = contrasenna;
    }

    /**
     * @return the rol
     */
    public String getRol()
    {
        return rol;
    }

    /**
     * @param rol
     *            the rol to set
     */
    public void setRol(String rol)
    {
        this.rol = rol;
    }

    /**
     * @return the nombre
     */
    public String getNombre()
    {
        return Util.formatearCadena(nombre);
    }

    /**
     * @param nombre
     *            the nombre to set
     */
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    /**
     * @return the paterno
     */
    public String getPaterno()
    {
        return Util.formatearCadena(paterno);
    }

    /**
     * @param paterno
     *            the paterno to set
     */
    public void setPaterno(String paterno)
    {
        this.paterno = paterno;
    }

    /**
     * @return the materno
     */
    public String getMaterno()
    {
        return Util.formatearCadena(materno);
    }

    /**
     * @param materno
     *            the materno to set
     */
    public void setMaterno(String materno)
    {
        this.materno = materno;
    }

    /**
     * @return the puesto
     */
    public String getPuesto()
    {
        return Util.formatearCadena(puesto);
    }

    /**
     * @param puesto
     *            the puesto to set
     */
    public void setPuesto(String puesto)
    {
        this.puesto = puesto;
    }

    /**
     * @return the activo
     */
    public Integer getActivo()
    {
        return activo;
    }

    /**
     * @param activo
     *            the activo to set
     */
    public void setActivo(Integer activo)
    {
        this.activo = activo;
    }

    /**
     * @return the id_area
     */
    public Integer getId_area()
    {
        return id_area;
    }

    /**
     * @param id_area
     *            the id_area to set
     */
    public void setId_area(Integer id_area)
    {
        this.id_area = id_area;
    }

    /**
     * @return the area
     */
    public Area getArea()
    {
        return area;
    }

    /**
     * @param area
     *            the area to set
     */
    public void setArea(Area area)
    {
        this.area = area;
    }

    /**
     * @return the roles
     */
    public List<Rol> getRoles()
    {
        return roles;
    }

    /**
     * @param roles
     *            the roles to set
     */
    public void setRoles(List<Rol> roles)
    {
        this.roles = roles;
    }

    /**
     * @return the nombre_completo
     */
    public String getNombre_completo()
    {
        nombre_completo = nombre_completo != null
                && nombre_completo.trim().length() > 0 ? nombre_completo
                : (this.getNombre() != null ? this.getNombre() + " " : "")
                + (this.getPaterno() != null ? this.getPaterno() + " " : "")
                + (this.getMaterno() != null ? this.getMaterno() + " " : "");
        nombre_completo = nombre_completo != null ? nombre_completo.trim() : "";
        return Util.formatearCadena(nombre_completo);
    }

    /**
     * @param nombre_completo
     *            the nombre_completo to set
     */
    public void setNombre_completo(String nombre_completo)
    {
        this.nombre_completo = nombre_completo;
    }

    /**
     * @return the puesto_nombre
     */
    public String getPuesto_nombre()
    {
        puesto_nombre = (this.getNombre_completo() != null ? this.getNombre_completo() : "")
                + (this.getPuesto() != null ? ", " + this.getPuesto() : "");
        return Util.formatearCadena(puesto_nombre);
    }

    /**
     * @return the rfc
     */
    public String getRfc()
    {
        return Util.formatearCadena(rfc);
    }

    /**
     * @param rfc
     *            the rfc to set
     */
    public void setRfc(String rfc)
    {
        this.rfc = rfc;
    }

    /**
     * @param puesto_nombre
     *            the puesto_nombre to set
     */
    public void setPuesto_nombre(String puesto_nombre)
    {
        this.puesto_nombre = puesto_nombre;
    }

    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Empleado other = (Empleado) obj;
        if (id_empleado != null && !id_empleado.equals(other.id_empleado))
            return false;
        return true;
    }

    public int hashCode()
    {
        if (id_empleado != null)
            return id_empleado.hashCode();
        else
            return super.hashCode();
    }

    public static void asignaIdx(List<Empleado> empleados)
    {
        for (int i = 0; i < empleados.size(); i++)
        {
            empleados.get(i).setIdx(i);
        }
    }

    /**
     * @return the instruccion
     */
    public Instruccion getInstruccion()
    {
        return instruccion;
    }

    /**
     * @param instruccion the instruccion to set
     */
    public void setInstruccion(Instruccion instruccion)
    {
        this.instruccion = instruccion;
    }
    
    

    public Prioridad getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(Prioridad prioridad) {
		this.prioridad = prioridad;
	}

	/**
     * @return the comentario
     */
    public String getComentario()
    {
        comentario = comentario != null && comentario.length() > 3000 ? comentario.substring(0, 2999) : comentario;
        return Util.formatearCadena(comentario);
    }

    /**
     * @param comentario the comentario to set
     */
    public void setComentario(String comentario)
    {
        this.comentario = comentario;
    }
    /**
     * @return the comentarioSaltos
     */
    public String getComentarioSaltos() {
        if (this.comentario == null) {
            comentarioSaltos = "";
        }
        else{
            comentarioSaltos = this.comentario.replaceAll("\n", "<br>");
        }
        return comentarioSaltos;
    }
    /**
     * @return the fh_limite
     */
    public Date getFh_limite() {
        if (this.fh_limiteDDMMYYYY != null
                && this.fh_limiteDDMMYYYY.length() > 0) {
            fh_limite = Util.parsearFecha(this.fh_limiteDDMMYYYY);
        }
        return fh_limite;
    }

    /**
     * @param fh_limite
     *            the fh_limite to set
     */
    public void setFh_limite(Date fh_limite) {
        this.fh_limite = fh_limite;
    }
    /**
     * @return the fh_limiteDDMMYYYY
     */
    public String getFh_limiteDDMMYYYY() {
        if (this.fh_limiteDDMMYYYY == null && this.fh_limite != null) {
            fh_limiteDDMMYYYY = Util.formatearFecha(this.fh_limite);
        }
        return fh_limiteDDMMYYYY;
    }

    /**
     * @param fh_limiteDDMMYYYY
     *            the fh_limiteDDMMYYYY to set
     */
    public void setFh_limiteDDMMYYYY(String fh_limiteDDMMYYYY) {
        this.fh_limiteDDMMYYYY = fh_limiteDDMMYYYY;
    }

    public String getCorreo_alterno() {
        return correo_alterno;
    }

    public void setCorreo_alterno(String correo_alterno) {
        this.correo_alterno = correo_alterno;
    }
}
