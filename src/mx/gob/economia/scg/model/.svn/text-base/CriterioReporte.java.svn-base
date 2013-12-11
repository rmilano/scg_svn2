/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.economia.scg.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import mx.gob.economia.scg.util.Constantes;
import mx.gob.economia.scg.util.Util;

/**
 *
 * @author gerardo
 * 
 */
public class CriterioReporte implements Serializable
{

    private Integer id_area;
    private Integer id_area_dest;
    private Integer id_expediente;
    private Integer id_tema;
    private Integer id_estatus;
    private Integer area_tipo;
    private Integer area_tipo_dest;
    private Integer id_area_destinatario;
    private String tema;
    private String expediente;
    private String area;
    private String area_dest;
    private String estatus;
    private Date fh_registroIni;
    private Date fh_registroFin;
    private String fh_registroIniDDMMYYYY;
    private String fh_registroFinDDMMYYYY;
    private List<Integer> ids_area;
    private Integer id_area_subconsulta;
    private Paginador paginador = new Paginador();    

    public CriterioReporte()
    {
        this.id_area = -1;
        this.id_area_dest = -1;
        this.id_expediente = -1;
        this.id_tema = -1;
        this.id_estatus = -1;
        this.tema = "";
        this.expediente = "";
        this.area = "";
        this.area_dest = "";
        this.estatus = "";
        this.fh_registroIniDDMMYYYY = "";
        this.fh_registroFinDDMMYYYY = "";
        this.ids_area = new ArrayList<Integer>();
        this.id_area_subconsulta = -1;
        this.paginador = new Paginador();
        this.area_tipo = Constantes.AREA_INTERNA;
        this.area_tipo_dest = Constantes.AREA_INTERNA;
    }

    public String getEstatus()
    {
        return estatus;
    }

    public void setEstatus(String estatus)
    {
        this.estatus = estatus;
    }

    public int getId_area()
    {
        return id_area;
    }

    public void setId_area(int id_area)
    {
        this.id_area = id_area;
    }

    public int getId_tema()
    {
        return id_tema;
    }

    public void setId_tema(int id_tema)
    {
        this.id_tema = id_tema;
    }

    public int getId_expediente()
    {
        return id_expediente;
    }

    public void setId_expediente(int id_expediente)
    {
        this.id_expediente = id_expediente;
    }

    public Date getFh_registroIni()
    {
        if (this.fh_registroIniDDMMYYYY != null
                && this.fh_registroIniDDMMYYYY.length() > 0)
        {
            fh_registroIni = Util.parsearFecha(this.fh_registroIniDDMMYYYY);
        }
        return fh_registroIni;
    }

    public void setFh_registroIni(Date fh_registroIni)
    {
        this.fh_registroIni = fh_registroIni;
    }

    public String getFh_registroIniDDMMYYYY()
    {
        if (this.fh_registroIniDDMMYYYY == null && this.fh_registroIni != null)
        {
            fh_registroIniDDMMYYYY = Util.formatearFecha(this.fh_registroIni);
        }
        return fh_registroIniDDMMYYYY;
    }

    public void setFh_registroIniDDMMYYYY(String fh_registroIniDDMMYYYY)
    {
        this.fh_registroIniDDMMYYYY = fh_registroIniDDMMYYYY;
    }

    public Date getFh_registroFin()
    {
        if (this.fh_registroFinDDMMYYYY != null
                && this.fh_registroFinDDMMYYYY.length() > 0)
        {
            fh_registroFin = Util.parsearFecha(this.fh_registroFinDDMMYYYY);
        }
        return fh_registroFin;
    }

    public void setFh_registroFin(Date fh_registroFin)
    {
        this.fh_registroFin = fh_registroFin;
    }

    public String getFh_registroFinDDMMYYYY()
    {
        if (this.fh_registroFinDDMMYYYY == null && this.fh_registroFin != null)
        {
            fh_registroFinDDMMYYYY = Util.formatearFecha(this.fh_registroFin);
        }
        return fh_registroFinDDMMYYYY;
    }

    public void setFh_registroFinDDMMYYYY(String fh_registroFinDDMMYYYY)
    {
        this.fh_registroFinDDMMYYYY = fh_registroFinDDMMYYYY;
    }


    /**
     * @param ids_area the ids_area to set
     */
    public void setIds_area(List<Integer> ids_area)
    {
        this.setIds_area(ids_area);
    }

    /**
     * @return the tema
     */
    public String getTema()
    {
        return tema;
    }

    /**
     * @param tema the tema to set
     */
    public void setTema(String tema)
    {
        this.tema = tema;
    }

    /**
     * @return the expediente
     */
    public String getExpediente()
    {
        return expediente;
    }

    /**
     * @param expediente the expediente to set
     */
    public void setExpediente(String expediente)
    {
        this.expediente = expediente;
    }

    /**
     * @return the area
     */
    public String getArea()
    {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(String area)
    {
        this.area = area;
    }

    /**
     * @return the id_estatus
     */
    public Integer getId_estatus()
    {
        return id_estatus;
    }

    /**
     * @param id_estatus the id_estatus to set
     */
    public void setId_estatus(Integer id_estatus)
    {
        this.id_estatus = id_estatus;
    }

    /**
     * @return the ids_area
     */
    public List<Integer> getIds_area()
    {
        return ids_area;
    }

    /**
     * @return the id_area_subconsulta
     */
    public Integer getId_area_subconsulta() {
        return id_area_subconsulta;
    }

    /**
     * @param id_area_subconsulta the id_area_subconsulta to set
     */
    public void setId_area_subconsulta(Integer id_area_subconsulta) {
        this.id_area_subconsulta = id_area_subconsulta;
    }

    /**
     * @return the paginador
     */
    public Paginador getPaginador() {
        return paginador;
    }

    /**
     * @param paginador the paginador to set
     */
    public void setPaginador(Paginador paginador) {
        this.paginador = paginador;
    }

    /**
     * @return the area_tipo
     */
    public Integer getArea_tipo()
    {
        return area_tipo;
    }

    /**
     * @param area_tipo the area_tipo to set
     */
    public void setArea_tipo(Integer area_tipo)
    {
        this.area_tipo = area_tipo;
    }

    /**
     * @return the id_area_destinatario
     */
    public Integer getId_area_destinatario()
    {
        return id_area_destinatario;
    }

    /**
     * @param id_area_destinatario the id_area_destinatario to set
     */
    public void setId_area_destinatario(Integer id_area_destinatario)
    {
        this.id_area_destinatario = id_area_destinatario;
    }

    public Integer getArea_tipo_dest() {
        return area_tipo_dest;
    }

    public void setArea_tipo_dest(Integer area_tipo_dest) {
        this.area_tipo_dest = area_tipo_dest;
    }

    public String getArea_dest() {
        return area_dest;
    }

    public void setArea_dest(String area_dest) {
        this.area_dest = area_dest;
    }

    public Integer getId_area_dest() {
        return id_area_dest;
    }

    public void setId_area_dest(Integer id_area_dest) {
        this.id_area_dest = id_area_dest;
    }
     
    
}
