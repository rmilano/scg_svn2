/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.economia.scg.service;

import javax.servlet.ServletContext;

/**
 *
 * @author roque
 */
public interface ServerConfig
{
    public ServletContext getServletContextWrapper();
    public void setServletContextWrapper(ServletContext servletContextWrapper);
}
