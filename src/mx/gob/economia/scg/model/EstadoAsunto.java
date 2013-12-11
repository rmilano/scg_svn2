/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.economia.scg.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author roque
 */
public class EstadoAsunto implements Serializable
{

    private Integer idAsunto;
    private Boolean isBusy;
    private String message;
    private Integer idUsuario;
    private Timestamp timeStamp;
    private String email;
    public EstadoAsunto()
    {
        this.idAsunto = -1;
        this.isBusy = false;
        this.message = "";
        this.idUsuario = null;
        this.email = "";
    }

    /**
     * @return the idAsunto
     */
    public Integer getIdAsunto()
    {
        return idAsunto;
    }

    /**
     * @param idAsunto the idAsunto to set
     */
    public void setIdAsunto(Integer idAsunto)
    {
        this.idAsunto = idAsunto;
    }

    /**
     * @return the isBusy
     */
    public Boolean getIsBusy()
    {
        return isBusy;
    }

    /**
     * @param isBusy the isBusy to set
     */
    public void setIsBusy(Boolean isBusy)
    {
        this.isBusy = isBusy;
    }

    /**
     * @return the message
     */
    public String getMessage()
    {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message)
    {
        this.message = message;
    }

    /**
     * @return the timeStamp
     */
    public Timestamp getTimeStamp()
    {
        return timeStamp;
    }

    /**
     * @param timeStamp the timeStamp to set
     */
    public void setTimeStamp(Timestamp timeStamp)
    {
        this.timeStamp = timeStamp;
    }

    /**
     * @return the idUsuario
     */
    public Integer getIdUsuario()
    {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(Integer idUsuario)
    {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the email
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email)
    {
        this.email = email;
    }
}
