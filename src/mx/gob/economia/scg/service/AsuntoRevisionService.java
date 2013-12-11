/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.economia.scg.service;

import java.net.URL;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import mx.gob.economia.scg.model.Asunto;
import mx.gob.economia.scg.model.CriterioAsunto;

/**
 *
 * @author gerardo
 */
public interface AsuntoRevisionService
{
    public void enviarAsuntoRevision(String folio);
    public Integer countAsuntosPorRevisar(HttpServletRequest request);
    public List<Asunto> listAsuntosByDistinctFolio(CriterioAsunto criterioAsunto);
    public List<Asunto> listAsuntosByDistinctFolioRevisor(CriterioAsunto criterioAsunto);
    public List<Asunto> listAsuntosEnRevision(CriterioAsunto criterioAsunto,HttpServletRequest request);
    public void setAsuntoRevisado(String folio, Integer id_empleado_revi);
    public Integer countAsuntosRevisados(CriterioAsunto criterioAsunto,HttpServletRequest request);
    public List<Asunto> listAsuntosRevisados(CriterioAsunto criterioAsunto,HttpServletRequest request);
    public void enviarAsuntoDestinatario(Asunto asunto, URL urlLogo, HttpServletRequest request);
    public void enviarAsuntoSupervicion(String folio);
    public Integer countAsuntosPorSupervisar(HttpServletRequest request);
    public Integer countAsuntosSupervisados(HttpServletRequest request);
    public List<Asunto> listAsuntosPorSupervisar(CriterioAsunto criterioAsunto,HttpServletRequest request);
    public void setAsuntoSupervisado(String folio);
    public List<Asunto> listAsuntosSupervisados(CriterioAsunto criterioAsunto,HttpServletRequest request);
}
