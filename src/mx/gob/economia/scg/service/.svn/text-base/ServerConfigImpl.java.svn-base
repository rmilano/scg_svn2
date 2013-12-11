/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.economia.scg.service;

import javax.servlet.ServletContext;
import org.springframework.web.context.ServletContextAware;

/**
 *
 * @author roque
 */
public class ServerConfigImpl implements ServletContextAware
{
    private ServletContext servletContextWrapper;

    /**
     * El servlet de la interfaz ServletContextAware
     * @param servletContext
     */
    public void setServletContext(ServletContext servletContext)
    {
        this.setServletContextWrapper(servletContext);
    }

    /**
     * @return the servletContextWrapper
     */
    public ServletContext getServletContextWrapper()
    {
        return servletContextWrapper;
    }

    /**
     * @param servletContextWrapper the servletContextWrapper to set
     */
    public void setServletContextWrapper(ServletContext servletContextWrapper)
    {
        this.servletContextWrapper = servletContextWrapper;
    }

}
