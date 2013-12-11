/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.economia.scg.service;

import javax.servlet.http.HttpServletRequest;
import mx.gob.economia.scg.model.EstadoAsunto;

/**
 *
 * @author roque
 */
public interface AsuntoEstadoService
{
    public EstadoAsunto isBusyMatter(HttpServletRequest request, Integer idAsunto, String origenMessage);
    public Integer deleteAsuntoFromListSession(HttpServletRequest request, Integer idAsunto);
}
