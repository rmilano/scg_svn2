/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.economia.scg.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import mx.gob.economia.scg.util.Constantes;
import org.apache.struts.util.LabelValueBean;

/**
 *
 * @author roque
 */
public class EstatusServiceImpl implements EstatusService
{

    public ArrayList getEstatusAsunto()
    {
        Map<String,Integer> estatusMap = new HashMap<String, Integer>();
        ArrayList estatus = new ArrayList();
        //estatus.add(new LabelValueBean(""+Constantes.PENDIENTE,"PENDIENTE"));
        estatus.add(new LabelValueBean(""+Constantes.EN_CAPTURA,"EN CAPTURA"));
        estatus.add(new LabelValueBean(""+Constantes.PENDIENTE,"INICIAL"));
        estatus.add(new LabelValueBean(""+Constantes.TURNADO,"PENDIENTE"));
        estatus.add(new LabelValueBean(""+Constantes.ATENDIDO,"ATENDIDO"));
        estatus.add(new LabelValueBean(""+Constantes.FINALIZADO,"CONCLUIDO"));
        estatus.add(new LabelValueBean(""+Constantes.ATENCIONPARCIAL,"EN TRAMITE"));
        //estatus.add(new LabelValueBean(""+Constantes.EN_TIEMPO,"EN TIEMPO"));
        //estatus.add(new LabelValueBean(""+Constantes.POR_VENCER,"POR VENCER"));
        //estatus.add(new LabelValueBean(""+Constantes.VENCIDO,"VENCIDO"));
        //estatus.add(new LabelValueBean(""+Constantes.EN_REVISION,"EN REVISION"));
        //estatus.add(new LabelValueBean(""+Constantes.ELIMINADO,"EN ELIMINADO"));
        //estatus.add(new LabelValueBean(""+Constantes.ARCHIVADO,"EN ARCHIVADO"));
        //estatus.add(new LabelValueBean(""+Constantes.REVISADO_CAPTURA,"EN REVISADO CAPTURA"));
        //estatus.add(new LabelValueBean(""+Constantes.EN_SUPERVISION_CAPTURA,"EN SUPERVISION CAPTURA"));
        //estatus.add(new LabelValueBean(""+Constantes.SUPERVISADO,"SUPERVISADO"));
        //estatus.add(new LabelValueBean(""+Constantes.CONFIDENCIAL,"CONFIDENCIAL"));//AGG Confidencial -> booleano 20111109
        return estatus;
    }
}
