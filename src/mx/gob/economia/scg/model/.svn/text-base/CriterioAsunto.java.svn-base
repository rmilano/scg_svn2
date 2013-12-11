package mx.gob.economia.scg.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.gob.economia.scg.util.Util;

public class CriterioAsunto implements Serializable
{

    // Propiedades
    private String id_asunto;
    private String asunto;
    private String no_oficio;
    private Date fh_registro;// Agregado Charly
    private String fh_registroDDMMYYYY; // Agregado Charly
    private Date fh_oficio_ini;
    private String fh_oficio_iniDDMMYYYY;
    private Date fh_oficio_fin;
    private String fh_oficio_finDDMMYYYY;
    private Date fh_recepcion_ini;
    private String fh_recepcion_iniDDMMYYYY;
    private Date fh_recepcion_fin;
    private String fh_recepcion_finDDMMYYYY;
    private Date fh_lectura_ini;
    private String fh_lectura_iniDDMMYYYY;
    private Date fh_lectura_fin;
    private String fh_lectura_finDDMMYYYY;
    private Date fh_registro_ini;
    private String fh_registro_iniDDMMYYYY;
    private Date fh_registro_fin;
    private String fh_registro_finDDMMYYYY;
    private Date fh_limite_ini;
    private String fh_limite_iniDDMMYYYY;
    private Date fh_limite_fin;
    private String fh_limite_finDDMMYYYY;
    private Date fh_evento;
    private String fh_eventoDDMMYYYY;
    private String estatus;
    private Integer tipo_area_capt;
    private String id_area_capt;
    private String area_capt;
    private String nombre_capt;
    private String paterno_capt;
    private String materno_capt;
    private Integer tipo_area_remi;
    private String id_area_remi;
    private String area_remi;
    private String nombre_remi;
    private String paterno_remi;
    private String materno_remi;
    private Integer tipo_area_dest;
    private String id_area_dest;
    private String area_dest;
    private String nombre_dest;
    private String paterno_dest;
    private String materno_dest;
    private String ids_empleados_ccp;
    private List<String> ids_estatus = new ArrayList<String>();
    private List<String> ids_tema = new ArrayList<String>();
    private List<String> ids_asunto = new ArrayList<String>();
    private List<String> ids_area_dest = new ArrayList<String>();
    private List<String> ids_empleados_remi = new ArrayList<String>();
    private List<String> ids_empleados_dest = new ArrayList<String>();    

    private List<String> ids_empleados_captura = new ArrayList<String>();
    private String id_empleado_ccp;
    private String id_empleado_dest;
    private String id_empleado_remi;
    private String id_empleado_revi;
    private String id_empleado_recep;
    private String descripcion;
    private Paginador paginador = new Paginador();
    private String folio;
    private String contador_folio;
    private String id_asunto_ref;
    private Integer id_tema;
    private Integer id_evento;
    private Integer id_expediente;
    private Integer id_prioridad = null;
    private Integer id_tipo_documento = null;
    private Integer confidencial;
    private String antecedente;
    private Integer busqueda_detalle;
    private Integer busqueda_modificado;
    private Integer historico;
    private Integer enTramite;
    private Integer bandeja;

    public CriterioAsunto()
    {
        this.id_asunto = null;
        this.asunto = "";
        this.no_oficio = "";
        this.fh_registro = null;// Agregado Charly
        this.fh_registroDDMMYYYY = ""; // Agregado Charly
        this.fh_oficio_ini = null;
        this.fh_oficio_iniDDMMYYYY = "";
        this.fh_oficio_fin = null;
        this.fh_oficio_finDDMMYYYY = "";
        this.fh_recepcion_ini = null;
        this.fh_recepcion_iniDDMMYYYY = "";
        this.fh_recepcion_fin = null;
        this.fh_recepcion_finDDMMYYYY = "";
        this.fh_lectura_ini = null;
        this.fh_lectura_iniDDMMYYYY = "";
        this.fh_lectura_fin = null;
        this.fh_lectura_finDDMMYYYY = "";
        this.fh_registro_ini = null;
        this.fh_registro_iniDDMMYYYY = "";
        this.fh_registro_fin = null;
        this.fh_registro_finDDMMYYYY = "";
        this.fh_limite_ini = null;
        this.fh_limite_iniDDMMYYYY = "";
        this.fh_limite_fin = null;
        this.fh_limite_finDDMMYYYY = "";
        this.fh_evento = null;
        this.fh_eventoDDMMYYYY = "";
        this.estatus = null;
        this.id_area_capt = null;
        this.tipo_area_capt = 0;
        this.nombre_capt = "";
        this.paterno_capt = "";
        this.materno_capt = "";
        this.id_area_remi = null;
        this.nombre_remi = "";
        this.paterno_remi = "";
        this.materno_remi = "";
        this.id_area_dest = null;
        this.nombre_dest = "";
        this.paterno_dest = "";
        this.materno_dest = "";
        this.ids_estatus = new ArrayList<String>();
        this.ids_asunto = new ArrayList<String>();
        this.ids_area_dest = new ArrayList<String>();
        this.id_empleado_ccp = "";
        this.id_empleado_dest = "";
        this.id_empleado_remi = "";
        this.id_empleado_revi = "";
        this.id_empleado_recep = "";
        this.paginador = new Paginador();
        this.descripcion = "";
        this.tipo_area_remi = 0;
        this.tipo_area_dest = 0;
        this.folio = "";
        this.contador_folio = "";
        this.id_asunto_ref = "";
        this.id_prioridad = null;
        this.id_tipo_documento = null;
        this.confidencial = 0;
        this.antecedente = "";
        this.busqueda_detalle = 0;
        this.busqueda_modificado = 0;
        this.ids_empleados_ccp = "";
        this.historico=0;
        this.enTramite=0;
        this.bandeja=0;
    }

    /**
     * @return the ids_asunto
     */
    public List<String> getIds_asunto()
    {
        return ids_asunto;
    }

    /**
     * @param ids_asunto
     *            the ids_asunto to set
     */
    public void setIds_asunto(List<String> ids_asunto)
    {
        this.ids_asunto = ids_asunto;
    }

    /**
     * @return the id_asunto
     */
    public String getId_asunto()
    {
        return id_asunto;
    }

    /**
     * @param id_asunto
     *            the id_asunto to set
     */
    public void setId_asunto(String id_asunto)
    {
        this.id_asunto = id_asunto;
    }

    /**
     * @return the asunto
     */
    public String getAsunto()
    {
        return Util.formatearCadena(asunto);
    }

    /**
     * @param asunto
     *            the asunto to set
     */
    public void setAsunto(String asunto)
    {
        this.asunto = asunto;
    }

    /**
     * @return the no_oficio
     */
    public String getNo_oficio()
    {
        return Util.formatearCadena(no_oficio);
    }

    /**
     * @param no_oficio
     *            the no_oficio to set
     */
    public void setNo_oficio(String no_oficio)
    {
        this.no_oficio = no_oficio;
    }

    /**
     * @return the fh_oficio_ini
     */
    public Date getFh_oficio_ini()
    {
        if (this.fh_oficio_iniDDMMYYYY != null
                && this.fh_oficio_iniDDMMYYYY.length() > 0)
        {
            fh_oficio_ini = Util.parsearFecha(this.fh_oficio_iniDDMMYYYY);
        } else
        {
            fh_oficio_ini = null;
        }
        return fh_oficio_ini;
    }

    /**
     * @param fh_oficio_ini
     *            the fh_oficio_ini to set
     */
    public void setFh_oficio_ini(Date fh_oficio_ini)
    {
        this.fh_oficio_ini = fh_oficio_ini;
    }

    /**
     * @return the fh_oficio_iniDDMMYYYY
     */
    public String getFh_oficio_iniDDMMYYYY()
    {
        return fh_oficio_iniDDMMYYYY;
    }

    /**
     * @param fh_oficio_iniDDMMYYYY
     *            the fh_oficio_iniDDMMYYYY to set
     */
    public void setFh_oficio_iniDDMMYYYY(String fh_oficio_iniDDMMYYYY)
    {
        this.fh_oficio_iniDDMMYYYY = fh_oficio_iniDDMMYYYY;
    }

    /**
     * @return the fh_oficio_fin
     */
    public Date getFh_oficio_fin()
    {
        if (this.fh_oficio_finDDMMYYYY != null
                && this.fh_oficio_finDDMMYYYY.length() > 0)
        {
            fh_oficio_fin = Util.parsearFecha(this.fh_oficio_finDDMMYYYY);
        } else
        {
            fh_oficio_fin = null;
        }
        return fh_oficio_fin;
    }

    /**
     * @param fh_oficio_fin
     *            the fh_oficio_fin to set
     */
    public void setFh_oficio_fin(Date fh_oficio_fin)
    {
        this.fh_oficio_fin = fh_oficio_fin;
    }

    /**
     * @return the fh_oficio_finDDMMYYYY
     */
    public String getFh_oficio_finDDMMYYYY()
    {
        return fh_oficio_finDDMMYYYY;
    }

    /**
     * @param fh_oficio_finDDMMYYYY
     *            the fh_oficio_finDDMMYYYY to set
     */
    public void setFh_oficio_finDDMMYYYY(String fh_oficio_finDDMMYYYY)
    {
        this.fh_oficio_finDDMMYYYY = fh_oficio_finDDMMYYYY;
    }

    /**
     * @return the fh_recepcion_ini
     */
    public Date getFh_recepcion_ini()
    {
        if (this.fh_recepcion_iniDDMMYYYY != null
                && this.fh_recepcion_iniDDMMYYYY.length() > 0)
        {
            fh_recepcion_ini = Util.parsearFecha(this.fh_recepcion_iniDDMMYYYY);
        } else
        {
            fh_recepcion_ini = null;
        }
        return fh_recepcion_ini;
    }

    /**
     * @param fh_recepcion_ini
     *            the fh_recepcion_ini to set
     */
    public void setFh_recepcion_ini(Date fh_recepcion_ini)
    {
        this.fh_recepcion_ini = fh_recepcion_ini;
    }

    /**
     * @return the fh_recepcion_iniDDMMYYYY
     */
    public String getFh_recepcion_iniDDMMYYYY()
    {
        return fh_recepcion_iniDDMMYYYY;
    }

    /**
     * @param fh_recepcion_iniDDMMYYYY
     *            the fh_recepcion_iniDDMMYYYY to set
     */
    public void setFh_recepcion_iniDDMMYYYY(String fh_recepcion_iniDDMMYYYY)
    {
        this.fh_recepcion_iniDDMMYYYY = fh_recepcion_iniDDMMYYYY;
    }

    /**
     * @return the fh_recepcion_fin
     */
    public Date getFh_recepcion_fin()
    {
        if (this.fh_recepcion_finDDMMYYYY != null
                && this.fh_recepcion_finDDMMYYYY.length() > 0)
        {
            fh_recepcion_fin = Util.parsearFecha(this.fh_recepcion_finDDMMYYYY);
        } else
        {
            fh_recepcion_fin = null;
        }
        return fh_recepcion_fin;
    }

    /**
     * @param fh_recepcion_fin
     *            the fh_recepcion_fin to set
     */
    public void setFh_recepcion_fin(Date fh_recepcion_fin)
    {
        this.fh_recepcion_fin = fh_recepcion_fin;
    }

    /**
     * @return the fh_recepcion_finDDMMYYYY
     */
    public String getFh_recepcion_finDDMMYYYY()
    {
        return fh_recepcion_finDDMMYYYY;
    }

    /**
     * @param fh_recepcion_finDDMMYYYY
     *            the fh_recepcion_finDDMMYYYY to set
     */
    public void setFh_recepcion_finDDMMYYYY(String fh_recepcion_finDDMMYYYY)
    {
        this.fh_recepcion_finDDMMYYYY = fh_recepcion_finDDMMYYYY;
    }

    /**
     * @return the fh_lectura_ini
     */
    public Date getFh_lectura_ini()
    {
        if (this.fh_lectura_iniDDMMYYYY != null
                && this.fh_lectura_iniDDMMYYYY.length() > 0)
        {
            fh_lectura_ini = Util.parsearFecha(this.fh_lectura_iniDDMMYYYY);
        } else
        {
            fh_lectura_ini = null;
        }
        return fh_lectura_ini;
    }

    /**
     * @param fh_lectura_ini
     *            the fh_lectura_ini to set
     */
    public void setFh_lectura_ini(Date fh_lectura_ini)
    {
        this.fh_lectura_ini = fh_lectura_ini;
    }

    /**
     * @return the fh_lectura_iniDDMMYYYY
     */
    public String getFh_lectura_iniDDMMYYYY()
    {
        return fh_lectura_iniDDMMYYYY;
    }

    /**
     * @param fh_lectura_iniDDMMYYYY
     *            the fh_lectura_iniDDMMYYYY to set
     */
    public void setFh_lectura_iniDDMMYYYY(String fh_lectura_iniDDMMYYYY)
    {
        this.fh_lectura_iniDDMMYYYY = fh_lectura_iniDDMMYYYY;
    }

    /**
     * @return the fh_lectura_fin
     */
    public Date getFh_lectura_fin()
    {
        if (this.fh_lectura_finDDMMYYYY != null
                && this.fh_lectura_finDDMMYYYY.length() > 0)
        {
            fh_lectura_fin = Util.parsearFecha(this.fh_lectura_finDDMMYYYY);
        } else
        {
            fh_lectura_fin = null;
        }
        return fh_lectura_fin;
    }

    /**
     * @param fh_lectura_fin
     *            the fh_lectura_fin to set
     */
    public void setFh_lectura_fin(Date fh_lectura_fin)
    {
        this.fh_lectura_fin = fh_lectura_fin;
    }

    /**
     * @return the fh_lectura_finDDMMYYYY
     */
    public String getFh_lectura_finDDMMYYYY()
    {
        return fh_lectura_finDDMMYYYY;
    }

    /**
     * @param fh_lectura_finDDMMYYYY
     *            the fh_lectura_finDDMMYYYY to set
     */
    public void setFh_lectura_finDDMMYYYY(String fh_lectura_finDDMMYYYY)
    {
        this.fh_lectura_finDDMMYYYY = fh_lectura_finDDMMYYYY;
    }

    /**
     * @return the fh_registro_ini
     */
    public Date getFh_registro_ini()
    {
        if (this.fh_registro_iniDDMMYYYY != null
                && this.fh_registro_iniDDMMYYYY.length() > 0)
        {
            fh_registro_ini = Util.parsearFecha(this.fh_registro_iniDDMMYYYY);
        } else
        {
            fh_registro_ini = null;
        }
        return fh_registro_ini;
    }

    /**
     * @param fh_registro_ini
     *            the fh_registro_ini to set
     */
    public void setFh_registro_ini(Date fh_registro_ini)
    {
        this.fh_registro_ini = fh_registro_ini;
    }

    /**
     * @return the fh_registro_iniDDMMYYYY
     */
    public String getFh_registro_iniDDMMYYYY()
    {
        return fh_registro_iniDDMMYYYY;
    }

    /**
     * @param fh_registro_iniDDMMYYYY
     *            the fh_registro_iniDDMMYYYY to set
     */
    public void setFh_registro_iniDDMMYYYY(String fh_registro_iniDDMMYYYY)
    {
        this.fh_registro_iniDDMMYYYY = fh_registro_iniDDMMYYYY;
    }

    /**
     * @return the fh_registro_fin
     */
    public Date getFh_registro_fin()
    {
        if (this.fh_registro_finDDMMYYYY != null
                && this.fh_registro_finDDMMYYYY.length() > 0)
        {
            fh_registro_fin = Util.parsearFecha(this.fh_registro_finDDMMYYYY);
        } else
        {
            fh_registro_fin = null;
        }
        return fh_registro_fin;
    }

    /**
     * @param fh_registro_fin
     *            the fh_registro_fin to set
     */
    public void setFh_registro_fin(Date fh_registro_fin)
    {
        this.fh_registro_fin = fh_registro_fin;
    }

    /**
     * @return the fh_registro_finDDMMYYYY
     */
    public String getFh_registro_finDDMMYYYY()
    {
        return fh_registro_finDDMMYYYY;
    }

    /**
     * @param fh_registro_finDDMMYYYY
     *            the fh_registro_finDDMMYYYY to set
     */
    public void setFh_registro_finDDMMYYYY(String fh_registro_finDDMMYYYY)
    {
        this.fh_registro_finDDMMYYYY = fh_registro_finDDMMYYYY;
    }

    /**
     * @return the fh_limite_ini
     */
    public Date getFh_limite_ini()
    {
        if (this.fh_limite_iniDDMMYYYY != null
                && this.fh_limite_iniDDMMYYYY.length() > 0)
        {
            fh_limite_ini = Util.parsearFecha(this.fh_limite_iniDDMMYYYY);
        } else
        {
            fh_limite_ini = null;
        }
        return fh_limite_ini;
    }

    /**
     * @param fh_limite_ini
     *            the fh_limite_ini to set
     */
    public void setFh_limite_ini(Date fh_limite_ini)
    {
        this.fh_limite_ini = fh_limite_ini;
    }

    /**
     * @return the fh_limite_iniDDMMYYYY
     */
    public String getFh_limite_iniDDMMYYYY()
    {
        return fh_limite_iniDDMMYYYY;
    }

    /**
     * @param fh_limite_iniDDMMYYYY
     *            the fh_limite_iniDDMMYYYY to set
     */
    public void setFh_limite_iniDDMMYYYY(String fh_limite_iniDDMMYYYY)
    {
        this.fh_limite_iniDDMMYYYY = fh_limite_iniDDMMYYYY;
    }

    /**
     * @return the fh_limite_fin
     */
    public Date getFh_limite_fin()
    {
        if (this.fh_limite_finDDMMYYYY != null
                && this.fh_limite_finDDMMYYYY.length() > 0)
        {
            fh_limite_fin = Util.parsearFecha(this.fh_limite_finDDMMYYYY);
        } else
        {
            fh_limite_fin = null;
        }
        return fh_limite_fin;
    }

    /**
     * @param fh_limite_fin
     *            the fh_limite_fin to set
     */
    public void setFh_limite_fin(Date fh_limite_fin)
    {
        this.fh_limite_fin = fh_limite_fin;
    }

    /**
     * @return the fh_limite_finDDMMYYYY
     */
    public String getFh_limite_finDDMMYYYY()
    {
        return fh_limite_finDDMMYYYY;
    }

    /**
     * @param fh_limite_finDDMMYYYY
     *            the fh_limite_finDDMMYYYY to set
     */
    public void setFh_limite_finDDMMYYYY(String fh_limite_finDDMMYYYY)
    {
        this.fh_limite_finDDMMYYYY = fh_limite_finDDMMYYYY;
    }

    /**
     * @return the fh_registro
     */
    public Date getFh_registro()
    {
        if (this.fh_registroDDMMYYYY != null
                && this.fh_registroDDMMYYYY.length() > 0)
        {
            fh_registro = Util.parsearFecha(this.fh_registroDDMMYYYY);
        } else
        {
            fh_registro = null;
        }
        return fh_registro;
    }

    /**
     * @param fh_registro
     *            the fh_registro to set
     */
    public void setFh_registro(Date fh_registro)
    {
        this.fh_registro = fh_registro;
    }

    /**
     * @return the fh_registroDDMMYYYY
     */
    public String getFh_registroDDMMYYYY()
    {
        return fh_registroDDMMYYYY;
    }

    /**
     * @param fh_registroDDMMYYYY
     *            the fh_registroDDMMYYYY to set
     */
    public void setFh_registroDDMMYYYY(String fh_registroDDMMYYYY)
    {
        this.fh_registroDDMMYYYY = fh_registroDDMMYYYY;
    }

    /**
     * @return the estatus
     */
    public String getEstatus()
    {
        return estatus;
    }

    /**
     * @param estatus
     *            the estatus to set
     */
    public void setEstatus(String estatus)
    {
        this.estatus = estatus;
    }

    /**
     * @return the id_area_capt
     */
    public String getId_area_capt()
    {
        return id_area_capt;
    }

    /**
     * @param id_area_capt
     *            the id_area_capt to set
     */
    public void setId_area_capt(String id_area_capt)
    {
        this.id_area_capt = id_area_capt;
    }

    /**
     * @return the tipo_area_capt
     */
    public Integer getTipo_area_capt()
    {
        return tipo_area_capt;
    }

    /**
     * @param tipo_area_capt the tipo_area_capt to set
     */
    public void setTipo_area_capt(Integer tipo_area_capt)
    {
        this.tipo_area_capt = tipo_area_capt;
    }

    /**
     * @return the nombre_capt
     */
    public String getNombre_capt()
    {
        return Util.formatearCadena(nombre_capt);
    }

    /**
     * @param nombre_capt
     *            the nombre_capt to set
     */
    public void setNombre_capt(String nombre_capt)
    {
        this.nombre_capt = nombre_capt;
    }

    /**
     * @return the paterno_capt
     */
    public String getPaterno_capt()
    {
        return Util.formatearCadena(paterno_capt);
    }

    /**
     * @param paterno_capt
     *            the paterno_capt to set
     */
    public void setPaterno_capt(String paterno_capt)
    {
        this.paterno_capt = paterno_capt;
    }

    /**
     * @return the materno_capt
     */
    public String getMaterno_capt()
    {
        return Util.formatearCadena(materno_capt);
    }

    /**
     * @param materno_capt
     *            the materno_capt to set
     */
    public void setMaterno_capt(String materno_capt)
    {
        this.materno_capt = materno_capt;
    }

    /**
     * @return the id_area_remi
     */
    public String getId_area_remi()
    {
        return id_area_remi;
    }

    /**
     * @param id_area_remi
     *            the id_area_remi to set
     */
    public void setId_area_remi(String id_area_remi)
    {
        this.id_area_remi = id_area_remi;
    }

    /**
     * @return the nombre_remi
     */
    public String getNombre_remi()
    {
        return Util.formatearCadena(nombre_remi);
    }

    /**
     * @param nombre_remi
     *            the nombre_remi to set
     */
    public void setNombre_remi(String nombre_remi)
    {
        this.nombre_remi = nombre_remi;
    }

    /**
     * @return the paterno_remi
     */
    public String getPaterno_remi()
    {
        return Util.formatearCadena(paterno_remi);
    }

    /**
     * @param paterno_remi
     *            the paterno_remi to set
     */
    public void setPaterno_remi(String paterno_remi)
    {
        this.paterno_remi = paterno_remi;
    }

    /**
     * @return the materno_remi
     */
    public String getMaterno_remi()
    {
        return Util.formatearCadena(materno_remi);
    }

    /**
     * @param materno_remi
     *            the materno_remi to set
     */
    public void setMaterno_remi(String materno_remi)
    {
        this.materno_remi = materno_remi;
    }

    /**
     * @return the id_area_dest
     */
    public String getId_area_dest()
    {
        return id_area_dest;
    }

    /**
     * @param id_area_dest
     *            the id_area_dest to set
     */
    public void setId_area_dest(String id_area_dest)
    {
        this.id_area_dest = id_area_dest;
    }

    /**
     * @return the nombre_dest
     */
    public String getNombre_dest()
    {
        return Util.formatearCadena(nombre_dest);
    }

    /**
     * @param nombre_dest
     *            the nombre_dest to set
     */
    public void setNombre_dest(String nombre_dest)
    {
        this.nombre_dest = nombre_dest;
    }

    /**
     * @return the paterno_dest
     */
    public String getPaterno_dest()
    {
        return Util.formatearCadena(paterno_dest);
    }

    /**
     * @param paterno_dest
     *            the paterno_dest to set
     */
    public void setPaterno_dest(String paterno_dest)
    {
        this.paterno_dest = paterno_dest;
    }

    /**
     * @return the materno_dest
     */
    public String getMaterno_dest()
    {
        return Util.formatearCadena(materno_dest);
    }

    /**
     * @param materno_dest
     *            the materno_dest to set
     */
    public void setMaterno_dest(String materno_dest)
    {
        this.materno_dest = materno_dest;
    }

    /**
     * @return the area_capt
     */
    public String getArea_capt()
    {
        return area_capt;
    }

    /**
     * @param area_capt
     *            the area_capt to set
     */
    public void setArea_capt(String area_capt)
    {
        this.area_capt = area_capt;
    }

    /**
     * @return the area_remi
     */
    public String getArea_remi()
    {
        return area_remi;
    }

    /**
     * @param area_remi
     *            the area_remi to set
     */
    public void setArea_remi(String area_remi)
    {
        this.area_remi = area_remi;
    }

    /**
     * @return the area_dest
     */
    public String getArea_dest()
    {
        return area_dest;
    }

    /**
     * @param area_dest
     *            the area_dest to set
     */
    public void setArea_dest(String area_dest)
    {
        this.area_dest = area_dest;
    }

    /**
     * @return the ids_area_dest
     */
    public List<String> getIds_area_dest()
    {
        return ids_area_dest;
    }

    /**
     * @param ids_area_dest
     *            the ids_area_dest to set
     */
    public void setIds_area_dest(List<String> ids_area_dest)
    {
        this.ids_area_dest = ids_area_dest;
    }

    /**
     * @return the id_empleado_ccp
     */
    public String getId_empleado_ccp()
    {
        return id_empleado_ccp;
    }

    /**
     * @param id_empleado_ccp
     *            the id_empleado_ccp to set
     */
    public void setId_empleado_ccp(String id_empleado_ccp)
    {
        this.id_empleado_ccp = id_empleado_ccp;
    }

    /**
     * @return the id_empleado_dest
     */
    public String getId_empleado_dest()
    {
        return id_empleado_dest;
    }

    /**
     * @param id_empleado_dest
     *            the id_empleado_dest to set
     */
    public void setId_empleado_dest(String id_empleado_dest)
    {
        this.id_empleado_dest = id_empleado_dest;
    }

    /**
     * @return the id_empleado_remi
     */
    public String getId_empleado_remi()
    {
        return id_empleado_remi;
    }

    /**
     * @param id_empleado_remi
     *            the id_empleado_remi to set
     */
    public void setId_empleado_remi(String id_empleado_remi)
    {
        this.id_empleado_remi = id_empleado_remi;
    }

    /**
     * @return the id_empleado_recep
     */
    public String getId_empleado_recep()
    {
        return id_empleado_recep;
    }

    /**
     * @param id_empleado_recep
     *            the id_empleado_recep to set
     */
    public void setId_empleado_recep(String id_empleado_recep)
    {
        this.id_empleado_recep = id_empleado_recep;
    }

    /**
     * @return the ids_estatus
     */
    public List<String> getIds_estatus()
    {
        return ids_estatus;
    }

    /**
     * @param ids_estatus
     *            the ids_estatus to set
     */
    public void setIds_estatus(List<String> ids_estatus)
    {
        this.ids_estatus = ids_estatus;
    }

    /**
     * @return the paginador
     */
    public Paginador getPaginador()
    {
        return paginador;
    }

    /**
     * @param paginador
     *            the paginador to set
     */
    public void setPaginador(Paginador paginador)
    {
        this.paginador = paginador;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion()
    {
        return Util.formatearCadena(descripcion);
    }

    /**
     * @param descripcion
     *            the descripcion to set
     */
    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    /**
     * @return the tipo_area_remi
     */
    public Integer getTipo_area_remi()
    {
        return tipo_area_remi;
    }

    /**
     * @param tipo_area_remi the tipo_area_remi to set
     */
    public void setTipo_area_remi(Integer tipo_area_remi)
    {
        this.tipo_area_remi = tipo_area_remi;
    }

    /**
     * @return the tipo_area_dest
     */
    public Integer getTipo_area_dest()
    {
        return tipo_area_dest;
    }

    /**
     * @param tipo_area_dest the tipo_area_dest to set
     */
    public void setTipo_area_dest(Integer tipo_area_dest)
    {
        this.tipo_area_dest = tipo_area_dest;
    }

    /**
     * @return the folio
     */
    public String getFolio()
    {
        return Util.formatearCadena(folio);
    }

    /**
     * @param folio the folio to set
     */
    public void setFolio(String folio)
    {
        this.folio = folio;
    }

    /**
     * @return the contador_folio
     */
    public String getContador_folio()
    {
        return (contador_folio);
    }

    /**
     * @param folio the contador_folio to set
     */
    public void setContador_folio(String contador_folio)
    {
        this.contador_folio = contador_folio;
    }

    /**
     * @return the id_tema
     */
    public Integer getId_tema()
    {
        return id_tema;
    }

    /**
     * @param id_tema the id_tema to set
     */
    public void setId_tema(Integer id_tema)
    {
        this.id_tema = id_tema;
    }
    /**
     * @return the id_expediente
     */
    public Integer getId_expediente()
    {
        return id_expediente;
    }

    /**
     * @param id_expediente the id_expediente to set
     */
    public void setId_expediente(Integer id_expediente)
    {
        this.id_expediente = id_expediente;
    }

    /**
     * @return the ids_empleados_remi
     */
    public List<String> getIds_empleados_remi()
    {
        return ids_empleados_remi;
    }

    /**
     * @param ids_empleados_remi the ids_empleados_remi to set
     */
    public void setIds_empleados_remi(List<String> ids_empleados_remi)
    {
        this.ids_empleados_remi = ids_empleados_remi;
    }

    /**
     * @return the id_asunto_ref
     */
    public String getId_asunto_ref()
    {
        return id_asunto_ref;
    }

    /**
     * @param id_asunto_ref the id_asunto_ref to set
     */
    public void setId_asunto_ref(String id_asunto_ref)
    {
        this.id_asunto_ref = id_asunto_ref;
    }

    /**
     * @return the ids_tema
     */
    public List<String> getIds_tema()
    {
        return ids_tema;
    }

    /**
     * @param ids_tema the ids_tema to set
     */
    public void setIds_tema(List<String> ids_tema)
    {
        this.ids_tema = ids_tema;
    }

    /**
     * @return the ids_empleados_captura
     */
    public List<String> getIds_empleados_captura()
    {
        return ids_empleados_captura;
    }

    /**
     * @param ids_empleados_captura the ids_empleados_captura to set
     */
    public void setIds_empleados_captura(List<String> ids_empleados_captura)
    {
        this.ids_empleados_captura = ids_empleados_captura;
    }

    /**
     * @return the id_empleado_revi
     */
    public String getId_empleado_revi() {
        return id_empleado_revi;
    }

    /**
     * @param id_empleado_revi the id_empleado_revi to set
     */
    public void setId_empleado_revi(String id_empleado_revi) {
        this.id_empleado_revi = id_empleado_revi;
    }

    /**
     * @return the ids_empleados_dest
     */
    public List<String> getIds_empleados_dest()
    {
        return ids_empleados_dest;
    }

    /**
     * @param ids_empleados_dest the ids_empleados_dest to set
     */
    public void setIds_empleados_dest(List<String> ids_empleados_dest)
    {
        this.ids_empleados_dest = ids_empleados_dest;
    }

    /**
     * @return the ids_empleados_ccp
     */
    public String getIds_empleados_ccp()
    {
        return ids_empleados_ccp;
    }

    /**
     * @param ids_empleados_ccp the ids_empleados_ccp to set
     */
    public void setIds_empleados_ccp(String ids_empleados_ccp)
    {
        this.ids_empleados_ccp = ids_empleados_ccp;
    }

    /**
     * @return the fh_evento
     */
    public Date getFh_evento()
    {
        if (this.fh_eventoDDMMYYYY != null
                && this.fh_eventoDDMMYYYY.length() > 0)
        {
            this.fh_evento = Util.formatearFechaToEvento(this.fh_eventoDDMMYYYY);
        } else
        {
            fh_evento = null;
        }
        return fh_evento;
    }

    /**
     * @param fh_evento the fh_evento to set
     */
    public void setFh_evento(Date fh_evento)
    {
        this.fh_evento = fh_evento;
    }

    /**
     * @return the fh_eventoDDMMYYYY
     */
    public String getFh_eventoDDMMYYYY()
    {
        return fh_eventoDDMMYYYY;
    }

    /**
     * @param fh_eventoDDMMYYYY the fh_eventoDDMMYYYY to set
     */
    public void setFh_eventoDDMMYYYY(String fh_eventoDDMMYYYY)
    {
        this.fh_eventoDDMMYYYY = fh_eventoDDMMYYYY;
    }

    /**
     * @return the id_prioridad
     */
    public Integer getId_prioridad()
    {
        return id_prioridad;
    }

    /**
     * @param id_prioridad the id_prioridad to set
     */
    public void setId_prioridad(Integer id_prioridad)
    {
        this.id_prioridad = id_prioridad;
    }

    /**
     * @return the id_tipo_documento
     */
    public Integer getId_tipo_documento()
    {
        return id_tipo_documento;
    }

    /**
     * @param id_tipo_documento the id_tipo_documento to set
     */
    public void setId_tipo_documento(Integer id_tipo_documento)
    {
        this.id_tipo_documento = id_tipo_documento;
    }

    /**
     * @return the confidencial
     */
    public Integer getConfidencial()
    {
        return confidencial;
    }

    /**
     * @param id_tipo_documento the id_tipo_documento to set
     */
    public void setConfidencial(Integer confidencial)
    {
        this.confidencial = confidencial;
    }

    /**
     * @return the antecedente
     */
    public String getAntecedente()
    {
        return antecedente;
    }

    /**
     * @param fh_eventoDDMMYYYY the fh_eventoDDMMYYYY to set
     */
    public void setAntecedente(String antecedente)
    {
        this.antecedente = antecedente;
    }

    /**
     * @return the busqueda_detalle
     */
    public Integer getBusqueda_detalle()
    {
        return busqueda_detalle;
    }

    /**
     * @param busqueda_detalle the busqueda_detalle to set
     */
    public void setBusqueda_detalle(Integer busqueda_detalle)
    {
        this.busqueda_detalle = busqueda_detalle;
    }

    /**
     * @return the busqueda_detalle
     */
    public Integer getBusqueda_modificado()
    {
        return busqueda_modificado;
    }

    /**
     * @param busqueda_detalle the busqueda_detalle to set
     */
    public void setBusqueda_modificado(Integer busqueda_modificado)
    {
        this.busqueda_modificado = busqueda_modificado;
    }
    public Integer getHistorico() {
        return historico;
    }

    public void setHistorico(Integer historico) {
        this.historico = historico;
    }

    public Integer getEnTramite() {
        return enTramite;
    }

    public void setEnTramite(Integer enTramite) {
        this.enTramite = enTramite;
    }

    public Integer getId_evento() {
        return id_evento;
    }

    public void setId_evento(Integer id_evento) {
        this.id_evento = id_evento;
    }

    public Integer getBandeja() {
        return bandeja;
    }

    public void setBandeja(Integer bandeja) {
        this.bandeja = bandeja;
    }

}
