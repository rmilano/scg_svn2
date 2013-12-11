package mx.gob.economia.scg.persistence;

//import java.sql.Connection;
//import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.gob.economia.scg.model.Asunto;
import mx.gob.economia.scg.model.AsuntoDetalle;
import mx.gob.economia.scg.model.CriterioAsunto;
import mx.gob.economia.scg.model.Documento;
import mx.gob.economia.scg.model.DocumentoBlob;
import mx.gob.economia.scg.model.Empleado;
import mx.gob.economia.scg.model.BitacoraAsunto;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import mx.gob.economia.scg.model.CriterioReporteVolante;
import mx.gob.economia.scg.util.Constantes;
import org.apache.log4j.Logger;

/**
 * Implementation AsuntoDao
 * 
 * @author valentin.gomez
 * 
 */
public class AsuntoDaoImpl extends SqlMapClientTemplate implements AsuntoDao
{

    DocumentoBlobDao documentoBlobDao;

    /**
     * Actualiza el estatus del asunto. Generalmente el asunto se encuntra en capatura-> revision
     * @param asunto
     */
    public void updateEstatusAsunto(Asunto asunto)
    {
        update("AsuntoRevision.updateEstatusAsunto", asunto);
    }

    /**
     * Actualiza el estatus del asunto_detalle. Generalmente el asunto se encuntra en capatura-> revision
     * @param asunto
     */
    public void updateEstatusAsuntoDetalle(AsuntoDetalle asuntoDetalle)
    {
        update("AsuntoDetalle.updateEstatus", asuntoDetalle);
    }

    /**
     * Actualiza el estatus del id_asunto_detalle.
     * @param asuntoDetalle
     */
    public void updateEstatusAsuntoDetalleByIdAsuntoDetalle(AsuntoDetalle asuntoDetalle)
    {
        update("AsuntoDetalle.updateEstatusByIdAsuntoDetalle", asuntoDetalle);
    }

    /**
     * Actualiza el estatus del asunto
     * @param asuntoDetalle 
     */
    public void updateEstatusAsuntoDetalleRevisor(AsuntoDetalle asuntoDetalle)
    {
        update("Asunto.updateEstatusRevisor", asuntoDetalle);
    }

    /**
     * Obtiene el asunto por su identificador unico
     * @param id_asunto
     * @return 
     */
    public Asunto getAsunto(Integer id_asunto)
    {
        Asunto asunto = (Asunto) queryForObject("Asunto.getByIdAsunto",
                id_asunto);
        if (asunto.getContador_folio()==null){
            String contadorFolio = (String) queryForObject("Asunto.getContadorFoliobyId",id_asunto);
            asunto.setContador_folio(contadorFolio);
        }
        return asunto;
    }

    /**
     * Obtiene la bitácora de notas
     * @param id_asunto
     * @return bitácora
     */
    public BitacoraAsunto getBitacoraAsunto(Integer id_asunto)
    {
        BitacoraAsunto bitacoraAsunto = (BitacoraAsunto) queryForObject("Asunto.getBitacoraAsunto",id_asunto);
        return bitacoraAsunto;
    }

    public Integer saveBitacoraAsunto(BitacoraAsunto bitacoraAsunto)
    {
        delete("Asunto.deleteBitacoraAsunto", bitacoraAsunto.getId_asunto());
        String bita = bitacoraAsunto.getBitacora();
        Integer len = bita.length();
        if (len > 3900){
            bitacoraAsunto.setBitacora1(bitacoraAsunto.getBitacora().substring(0, 3900));
            if (len < 7800){
                bitacoraAsunto.setBitacora2(bitacoraAsunto.getBitacora().substring(3901,len));
            }
            else {
                bitacoraAsunto.setBitacora2(bitacoraAsunto.getBitacora().substring(3901,7800));
            }
        }
        else{
            bitacoraAsunto.setBitacora1(bitacoraAsunto.getBitacora().substring(0, len));
        }
        return (Integer) insert("Asunto.insertBitacoraAsunto", bitacoraAsunto);
    }

    public void updateBitacoraAsunto(BitacoraAsunto bitacoraAsunto)
    {
        update("Asunto.updateBitacoraAsunto", bitacoraAsunto);
    }

    /**
     * Lista de asuntos por criterio
     * @param criterioAsunto
     * @return 
     */
    public List<Asunto> listAsuntos(CriterioAsunto criterioAsunto)
    {
        List<Asunto> asuntos = queryForList("Asunto.listByCriterio",
                criterioAsunto);
        return asuntos;
    }

    public List<Empleado> listCCpsAsunto(Integer id_asunto)
    {
        List<Empleado> empleados = queryForList("Asunto.listEmpleadoCcpByIdAsunto",id_asunto);
        return empleados;
    }
    
    /**
     * Lista de los asunto iniciales
     * @param criterioAsunto
     * @return 
     */
    public List<Asunto> listAsuntosIniciales(CriterioAsunto criterioAsunto)
    {
        List<String> idsEstatus=criterioAsunto.getIds_estatus();
        String estatus ="";

        criterioAsunto.setEnTramite(Constantes.ACTIVADO);
        if (!criterioAsunto.getEstatus().equals("14")){
                criterioAsunto.setEnTramite(0);
        }

        for (int i = 0; i < idsEstatus.size(); i++){
            estatus = criterioAsunto.getIds_estatus().get(i);
            if (estatus.equals(Constantes.ATENCIONPARCIAL.toString())){
                criterioAsunto.setEnTramite(Constantes.INSTANCIA_CREADA);
                criterioAsunto.getIds_estatus().remove(estatus);
            }
        }
        List<Asunto> asuntos = queryForList("Asunto.listInicialesByCriterio",
                criterioAsunto);
        return asuntos;
    }

    /**
     * Obtiene la lista de ids de asuntos por criterio
     * @param criterioAsunto
     * @return 
     */
    public List<String> listIdsAsuntosIniciales(CriterioAsunto criterioAsunto)
    {
        List<String> idAsuntos = queryForList("Asunto.listIdsAsuntoInicialesByCriterio", criterioAsunto);
        return idAsuntos;
    }

    /**
     * Lista los asuntos por folio
     * @param criterioAsunto
     * @return
     */
    public List<Asunto> listAsuntosByDistinctFolio(CriterioAsunto criterioAsunto)
    {
        List<Asunto> asuntos = queryForList("AsuntoRevision.listAsuntosByDistinctFolio", criterioAsunto);
        return asuntos;
    }

    public List<Asunto> listAsuntosByDistinctFolioRevisor(CriterioAsunto criterioAsunto)
    {
        List<Asunto> asuntos = queryForList("AsuntoRevision.listAsuntosByDistinctFolioRevisor", criterioAsunto);
        return asuntos;
    }

    public List<Asunto> listAsuntosActuales(CriterioAsunto criterioAsunto)
    {
        List<Asunto> asuntos = queryForList("Asunto.listActualesByCriterio",
                criterioAsunto);
        return asuntos;
    }

    public List<Asunto> listAsuntosActualesDetallePDF(CriterioAsunto criterioAsunto)
    {
        List<Asunto> asuntos = queryForList("Asunto.listActualesByCriterioDetallePDF",
                criterioAsunto);
        return asuntos;
    }

    public Integer countAsuntos(CriterioAsunto criterioAsunto)
    {
        Integer numRegistros = (Integer) queryForObject(
                "Asunto.countByCriterio", criterioAsunto);
        return numRegistros;
    }

    public Integer countAsuntosIniciales(CriterioAsunto criterioAsunto)
    {
        List<String> idsEstatus=criterioAsunto.getIds_estatus();
        String estatus ="";
        for (int i = 0; i < idsEstatus.size(); i++){
            estatus = criterioAsunto.getIds_estatus().get(i);
            if (estatus.equals(Constantes.ATENCIONPARCIAL.toString())){
                criterioAsunto.setEnTramite(Constantes.INSTANCIA_CREADA);
                criterioAsunto.getIds_estatus().remove(estatus);
            }
        }
        Integer numRegistros = (Integer) queryForObject(
                "Asunto.countInicialesByCriterio", criterioAsunto);
        return numRegistros;
    }

    /**
     * Obtiene el total de asuntos en revision
     * @param criterioAsunto
     * @return
     */
    public Integer countAsuntosEnRevision(CriterioAsunto criterioAsunto)
    {
        Integer numRegistros = (Integer) queryForObject("AsuntoRevision.countAsuntosRevision", criterioAsunto);
        return numRegistros;
    }

    public Integer countAsuntosActuales(CriterioAsunto criterioAsunto)
    {
        Integer numRegistros = (Integer) queryForObject(
                "Asunto.countActualesByCriterio", criterioAsunto);
        return numRegistros;
    }

    public Integer saveAsuntoConsecutivoArea(Asunto asunto)
    {
        return (Integer) insert("Asunto.insertConsecutivoAsuntoArea", asunto);
    }

    public Asunto saveAsunto(Asunto asunto) throws Exception
    {
//        SqlMapSession session = null;
//        Connection connection = null;
        // Lista auxiliar para insertar en la tabla de documentos blob
        List<Documento> documentos_blob = new ArrayList<Documento>();
//        try
//        {
//            connection = this.getDataSource().getConnection();
//            session = this.getSqlMapClient().openSession(connection);
//            connection.setAutoCommit(false);

        // Guarda asunto en repositorio
        Integer id_asunto = (Integer) insert("Asunto.insert", asunto);
        asunto.setId_asunto(id_asunto);
        asunto.getAsunto_detalle().setId_asunto(id_asunto);

        // Guarda detalle de asunto en repositorio
        Integer id_asunto_detalle = (Integer) //            session.
                insert("AsuntoDetalle.insert", asunto.getAsunto_detalle());
        asunto.getAsunto_detalle().setId_asunto_detalle(id_asunto_detalle);

        // Guarda "con copia para" de un detallde de asunto en repositorio
        List<Empleado> empleadosCcp = asunto.getEmpleadosCcp();
        delete("Asunto.deleteAsuntoCcp",asunto.getId_asunto());
        for (int i = 0; i < empleadosCcp.size(); i++)
        {
            Empleado empleadoCcp = empleadosCcp.get(i);
            HashMap<String, Integer> asuntoEmpMap = new HashMap<String, Integer>();
            asuntoEmpMap.put("id_empleado", empleadoCcp.getId_empleado());
            asuntoEmpMap.put("id_asunto", id_asunto);
//                session.
            insert("Asunto.insertCcp", asuntoEmpMap);
        }

        // Guarda "recepcionistas" de un detallde de asunto en repositorio
        List<Empleado> empleadosRecep = asunto.getAsunto_detalle().getEmpleadosRecep();
        for (Empleado empleadoRecep : empleadosRecep)
        {
            HashMap<String, Integer> asuntoEmpMap = new HashMap<String, Integer>();
            asuntoEmpMap.put("id_empleado", empleadoRecep.getId_empleado());
            asuntoEmpMap.put("id_asunto", id_asunto);
            asuntoEmpMap.put("id_asunto_detalle", id_asunto_detalle);
//                session.
            insert("AsuntoDetalle.insertRecepcionista",
                    asuntoEmpMap);
        }
        // Guarda documentos adjuntos de un asunto en repositorio
        List<Documento> documentos = asunto.getAsunto_detalle().getDocumentos();
        for (int i = 0; i < documentos.size(); i++)
        {
            Documento documento = documentos.get(i);
            documento.setId_asunto_detalle(id_asunto_detalle);
            documento.setId_asunto(id_asunto);
            Integer id_documento = (Integer) //                session.
                    insert("Documento.insert", documento);
            documento.setId_documento(id_documento);
            documentos_blob.add(documento);
        }
//            connection.commit();
//        } catch (SQLException e)
//        {
//            e.printStackTrace();
//            try
//            {
//                if (connection != null)
//                {
//                    connection.rollback();
//                }
//                asunto = null;
//                throw new Exception("No se puedo guardar el asunto. Consulte al administrador del sistema");
//            } catch (SQLException ignored)
//            {
//            }
//        } finally
//        {
//            try
//            {
//                connection.setAutoCommit(true);
//                this.getSqlMapClient().endTransaction();
//            } catch (SQLException ignored)
//            {
//            }
//        }
        // Guarda documentos adjuntos de un asunto en repositorio
        // Guarda documentos adjuntos de un asunto en repositorio
        for (int j = 0; j < documentos_blob.size(); j++)
        {
            Documento documento_blob = documentos_blob.get(j);
            DocumentoBlob documentoBlob = new DocumentoBlob(documento_blob.getBlob(), documento_blob.getId_asunto(), documento_blob.getId_asunto_detalle(), documento_blob.getId_documento());
            documentoBlobDao.saveDocumentoBlob(documentoBlob);
        }
        return asunto;
    }

    public Asunto updateEditarTurno(Asunto asunto) throws Exception
    {
        List<Documento> documentos_blob = new ArrayList<Documento>();
        // Guarda asunto en repositorio
        Integer id_asunto = asunto.getId_asunto();
        // Guarda detalle de asunto en repositorio
        Integer id_asunto_detalle = asunto.getAsunto_detalle().getId_asunto_detalle();
        delete("AsuntoDetalle.deleteEdicionTurnoDocumento",id_asunto_detalle);
        delete("AsuntoDetalle.deleteEdicionTurnoDocBlob",id_asunto_detalle);
        //delete("AsuntoDetalle.deleteEdicionTurno",id_asunto_detalle);
        insert("AsuntoDetalle.update", asunto.getAsunto_detalle());

        // Guarda "con copia para" de un detallde de asunto en repositorio
        List<Empleado> empleadosCcp = asunto.getEmpleadosCcp();
        delete("Asunto.deleteAsuntoCcp",asunto.getId_asunto());
        for (int i = 0; i < empleadosCcp.size(); i++)
        {
            Empleado empleadoCcp = empleadosCcp.get(i);
            HashMap<String, Integer> asuntoEmpMap = new HashMap<String, Integer>();
            asuntoEmpMap.put("id_empleado", empleadoCcp.getId_empleado());
            asuntoEmpMap.put("id_asunto", id_asunto);
//                session.
            insert("Asunto.insertCcp", asuntoEmpMap);
        }
        
        // Guarda documentos adjuntos de un asunto en repositorio
        List<Documento> documentos = asunto.getAsunto_detalle().getDocumentos();
        for (int i = 0; i < documentos.size(); i++)
        {
            Documento documento = documentos.get(i);
            documento.setId_asunto_detalle(id_asunto_detalle);
            documento.setId_asunto(id_asunto);
            Integer id_documento = (Integer) //                session.
                    insert("Documento.insert", documento);
            documento.setId_documento(id_documento);
            documentos_blob.add(documento);
        }
        // Guarda documentos adjuntos de un asunto en repositorio
        for (int j = 0; j < documentos_blob.size(); j++)
        {
            Documento documento_blob = documentos_blob.get(j);
            DocumentoBlob documentoBlob = new DocumentoBlob(documento_blob.getBlob(), documento_blob.getId_asunto(), documento_blob.getId_asunto_detalle(), documento_blob.getId_documento());
            documentoBlobDao.saveDocumentoBlob(documentoBlob);
        }
        return asunto;
    }

    public Asunto updateAsuntoCaptura(Asunto asunto) throws Exception
    {
//    	SqlMapSession session = null;
//        Connection connection = null;

        // Lista auxiliar para insertar en la tabla de documentos blob
        List<Documento> documentos_blob = new ArrayList<Documento>();
//        try
//        {
//            connection = this.getDataSource().getConnection();
//            session = this.getSqlMapClient().openSession(connection);
//            connection.setAutoCommit(false);

        // Guarda asunto en repositorio
        Integer id_asunto = asunto.getId_asunto();
        if (id_asunto != null)
        {
            //session.
            update("Asunto.updateCaptura", asunto);
        }
        else
        {
            id_asunto = (Integer) //session.
                    insert("Asunto.insert", asunto);
            asunto.setId_asunto(id_asunto);
        }
        asunto.getAsunto_detalle().setId_asunto(id_asunto);

        // Guarda detalle de asunto en repositorio
        Integer id_asunto_detalle = (Integer) //session.
                insert("AsuntoDetalle.insert", asunto.getAsunto_detalle());
        asunto.getAsunto_detalle().setId_asunto_detalle(id_asunto_detalle);

        // Guarda "con copia para" de un detallde de asunto en repositorio
        List<Empleado> empleadosCcp = asunto.getEmpleadosCcp();
        delete("Asunto.deleteAsuntoCcp",asunto.getId_asunto());
        for (int i = 0; i < empleadosCcp.size(); i++)
        {
            Empleado empleadoCcp = empleadosCcp.get(i);
            HashMap<String, Integer> asuntoEmpMap = new HashMap<String, Integer>();
            asuntoEmpMap.put("id_empleado", empleadoCcp.getId_empleado());
            asuntoEmpMap.put("id_asunto", id_asunto);
            //session.
            insert("Asunto.insertCcp", asuntoEmpMap);
        }

        // Guarda "recepcionistas" de un detallde de asunto en repositorio
        List<Empleado> empleadosRecep = asunto.getAsunto_detalle().getEmpleadosRecep();
        for (Empleado empleadoRecep : empleadosRecep)
        {
            HashMap<String, Integer> asuntoEmpMap = new HashMap<String, Integer>();
            asuntoEmpMap.put("id_empleado", empleadoRecep.getId_empleado());
            asuntoEmpMap.put("id_asunto", id_asunto);
            asuntoEmpMap.put("id_asunto_detalle", id_asunto_detalle);
            //session.
            insert("AsuntoDetalle.insertRecepcionista",
                    asuntoEmpMap);
        }
        // Guarda documentos adjuntos de un asunto en repositorio
        List<Documento> documentos = asunto.getAsunto_detalle().getDocumentos();
        for (int i = 0; i < documentos.size(); i++)
        {
            Documento documento = documentos.get(i);
            documento.setId_asunto_detalle(id_asunto_detalle);
            documento.setId_asunto(id_asunto);
            Integer id_documento = (Integer) //session.
                    insert("Documento.insert", documento);
            documento.setId_documento(id_documento);
//                DocumentoBlob documentoBlob = new DocumentoBlob(documento.getBlob(), documento.getId_asunto(), documento.getId_asunto_detalle(), documento.getId_documento());
////                documentoBlobDao.saveDocumentoBlob(documentoBlob);
//                session.insert("DocumentoBlob.insert", documentoBlob);
            documentos_blob.add(documento);
        }
//            connection.commit();
//        } catch (SQLException e)
//        {
//            e.printStackTrace();
////            try
////            {
////                if (connection != null)
////                {
////                    connection.rollback();
////                }
////                asunto = null;
//                throw new Exception("No se puedo guardar el asunto. Consulte al administrador del sistema");
////            } catch (SQLException ignored)
////            {
////            }
//        } finally
//        {
////            try
////            {
////                connection.setAutoCommit(true);
////                this.getSqlMapClient().endTransaction();
////            } catch (SQLException ignored)
////            {
////            }
//        }

        // Guarda documentos adjuntos de un asunto en repositorio
        for (int i = 0; i < documentos_blob.size(); i++)
        {
            Documento documento = documentos_blob.get(i);
            DocumentoBlob documentoBlob = new DocumentoBlob(documento.getBlob(), documento.getId_asunto(), documento.getId_asunto_detalle(), documento.getId_documento());
            documentoBlobDao.saveDocumentoBlob(documentoBlob);
        }
        return asunto;
    }

    public Asunto deleteRelaciones(Asunto asunto) throws Exception
    {
//        SqlMapSession session = null;
//        Connection connection = null;
//        try
//        {
//            connection = this.getDataSource().getConnection();
//            session = this.getSqlMapClient().openSession(connection);
//            connection.setAutoCommit(false);

        // Borra el concentrado de asuntos
//            session.
        delete("AsuntoDetalle.deleteConcentrado", asunto.getId_asunto());

        // 	Borra los ccp del asunto
//            session.
        delete("Asunto.deleteAsuntoCcp",asunto.getId_asunto());
        List<AsuntoDetalle> asuntos_detalles = asunto.getAsuntos_detalles();
        for (int i = 0; i < asuntos_detalles.size(); i++)
        {
            delete("Asunto.deleteAsuntoCcpTurno", asuntos_detalles.get(i).getId_asunto_detalle());
        }

        // Borra los ccp del asunto
//            session.
        delete("Asunto.deleteAsuntoRecepcion", asunto.getId_asunto());

        // Borra los documentos del asunto
//            session.
        delete("Documento.deleteAsuntoDocumento", asunto.getId_asunto());

        // Borra los detalles de asunto
//            session.
        delete("AsuntoDetalle.delete", asunto.getId_asunto());

//            connection.commit();
//        } catch (SQLException e)
//        {
//            e.printStackTrace();
//            try
//            {
//                if (connection != null)
//                {
//                    connection.rollback();
//                }
//                asunto = null;
//                throw new Exception("No se puedo guardar el asunto. Consulte al administrador del sistema");
//            } catch (SQLException ignored)
//            {
//            }
//        } finally
//        {
//            try
//            {
//                connection.setAutoCommit(true);
//                this.getSqlMapClient().endTransaction();
//            } catch (SQLException ignored)
//            {
//            }
//        }
        documentoBlobDao.deleteDocumentoBlobByIdAsunto(asunto.getId_asunto());
        return asunto;
    }

    public Asunto updateAsunto(Asunto asunto) throws Exception
    {
        // Actualiza asunto en repositorio
        Logger log = Logger.getLogger(this.getClass());
//            session.
        update("Asunto.update", asunto);

        // Guarda detalle de asunto en repositorio
        Integer id_asunto_detalle = (Integer) //            session.
                insert("AsuntoDetalle.insert", asunto.getAsunto_detalle());
        asunto.getAsunto_detalle().setId_asunto_detalle(id_asunto_detalle);
        // Guarda "con copia para" de un detallde de asunto en repositorio
        List<Empleado> empleadosCcp = asunto.getAsunto_detalle().getEmpleadosCcpDetalle();
        delete("Asunto.deleteAsuntoCcp",asunto.getId_asunto());
        for (int i = 0; i < empleadosCcp.size(); i++)
        {
            Empleado empleadoCcp = empleadosCcp.get(i);
            HashMap<String, Object> asuntoEmpMap = new HashMap<String, Object>();
            asuntoEmpMap.put("id_empleado", empleadoCcp.getId_empleado());
            //asuntoEmpMap.put("id_asunto", asunto.getId_asunto()); //Comentado AGG 20111103
            //insert("Asunto.insertCcp", asuntoEmpMap); //Comentado AGG 20111103
            asuntoEmpMap.put("id_asunto_detalle", asunto.getAsunto_detalle().getId_asunto_detalle());
            insert("Asunto.insertCcpTurno", asuntoEmpMap);
        }

        // Guarda documentos adjuntos de un asunto en repositorio
        List<Documento> documentos = asunto.getAsunto_detalle().getDocumentos();
        for (int i = 0; i < documentos.size(); i++)
        {
            Documento documento = documentos.get(i);
            documento.setId_asunto_detalle(id_asunto_detalle);
            documento.setId_asunto(asunto.getId_asunto());
            Integer id_documento = (Integer) //                session.
                    insert("Documento.insert",
                    documento);
            documento.setId_documento(id_documento);
        }
        // Guarda documentos adjuntos de un asunto en repositorio
        List<Documento> documentos_blob = asunto.getAsunto_detalle().getDocumentos();
        for (int i = 0; i < documentos_blob.size(); i++)
        {
            Documento documento_blob = documentos_blob.get(i);
            DocumentoBlob documentoBlob = new DocumentoBlob(documento_blob.getBlob(), documento_blob.getId_asunto(), documento_blob.getId_asunto_detalle(), documento_blob.getId_documento());
            documentoBlobDao.saveDocumentoBlob(documentoBlob);
        }
        return asunto;
    }

    public AsuntoDetalle insertAsuntoDetalle(AsuntoDetalle asunto_detalle)
    {
        // Guarda detalle de asunto en repositorio
        Integer id_asunto_detalle = (Integer) insert(
                "AsuntoDetalle.insert", asunto_detalle);
        asunto_detalle.setId_asunto_detalle(id_asunto_detalle);
        this.borraCapturaTurno(id_asunto_detalle);
        this.borraAtenderParcial(id_asunto_detalle);
        return asunto_detalle;
    }

    public int updateFhLectura(AsuntoDetalle asunto_detalle)
    {
        return update("AsuntoDetalle.updateFhLectura", asunto_detalle);
    }

    public int updateComentario(Asunto asunto)
    {
        return update("Asunto.updateComentario", asunto);
    }

    public int updateFhLecturaCcp(HashMap<String, Object> asunto_ccp)
    {
        return update("Asunto.updateFhLecturaCcp", asunto_ccp);
    }

    public void deleteAsunto(Asunto asunto) throws Exception
    {
//    	SqlMapSession session = null;
//        Connection connection = null;
//        try
//        {
//            connection = this.getDataSource().getConnection();
//            session = this.getSqlMapClient().openSession(connection);
//            connection.setAutoCommit(false);

        // Borra los ccp del asunto
//            session.
        delete("Asunto.deleteAsuntoRecepcion", asunto.getId_asunto());

        // Borra los detalles de asunto
//            session.
        List<AsuntoDetalle> asuntos_detalles = asunto.getAsuntos_detalles();
        for (int i = 0; i < asuntos_detalles.size(); i++)
        {
            delete("Asunto.deleteAsuntoCcpTurno", asuntos_detalles.get(i).getId_asunto_detalle());
        }


        delete("AsuntoDetalle.delete", asunto.getId_asunto());
        // Borra el id relacionado del asunto en la relacion asunto_captura_Acutalizacion
//            session.
        delete("AsuntoCapturaActualizacion.delete", asunto.getId_asunto());

        // 	Borra los ccp del asunto
//            session.
        delete("Asunto.deleteAsuntoCcp", asunto.getId_asunto());

        // 	Borra el concentrado asunto
//            session.
        delete("AsuntoCapturaActualizacion.deleteConcentradoAsunto", asunto.getId_asunto());

        //	Borra los documentos del asunto
//            session.
        delete("Documento.deleteAsuntoDocumento", asunto.getId_asunto());

        // Borra los detalles de asunto
//            session.
        delete("Asunto.deleteAsuntoConsecutivoArea", asunto.getId_asunto());

        // Borra los detalles de asunto
//            session.
        delete("Asunto.delete", asunto.getId_asunto());

//            connection.commit();
//        } catch (SQLException e)
//        {
//            e.printStackTrace();
//            try
//            {
//                if (connection != null)
//                {
//                    connection.rollback();
//                }
//                asunto = null;
//                throw new Exception("No se puedo guardar el asunto. Consulte al administrador del sistema");
//            } catch (SQLException ignored)
//            {
//            }
//        } finally
//        {
//            try
//            {
//                connection.setAutoCommit(true);
//                this.getSqlMapClient().endTransaction();
//            } catch (SQLException ignored)
//            {
//            }
//        }
        // Borra los blob de la tabla de documentos
        documentoBlobDao.deleteDocumentoBlobByIdAsunto(asunto.getId_asunto());
    }

    public int saveAsuntoConcentrado(List<Map<String, Integer>> ids_asuntoMap)
    {
        Integer id_concentrado = null;
//        try
//        {
//            this.getSqlMapClient().startTransaction();
//            this.getSqlMapClient().startBatch();
        id_concentrado = (Integer) queryForObject(
                "Asunto.getIdConcentrado");
        for (Map<String, Integer> ids_asunto : ids_asuntoMap)
        {
            HashMap<String, Integer> concentradoMap = new HashMap<String, Integer>();
            concentradoMap.put("id_concentrado", id_concentrado);
            concentradoMap.put("id_asunto", ids_asunto.get("id_asunto"));
            concentradoMap.put("id_asunto_detalle", ids_asunto.get("id_asunto_detalle"));
            insert("Asunto.insertConcentrado", concentradoMap);
        }
//            this.getSqlMapClient().executeBatch();
//            this.getSqlMapClient().commitTransaction();
//            this.getSqlMapClient().endTransaction();
//
//        } catch (SQLException e)
//        {
//            e.printStackTrace();
//            try
//            {
//                this.getSqlMapClient().endTransaction();
//            } catch (SQLException e1)
//            {
//                e1.printStackTrace();
//            }
//        }
        return id_concentrado;
    }

     public int saveAsuntoConcentradoRet(List<Map<String, Integer>> ids_asuntoMap,int id_concentrado)
    {
        for (Map<String, Integer> ids_asunto : ids_asuntoMap)
        {
            HashMap<String, Integer> concentradoMap = new HashMap<String, Integer>();
            concentradoMap.put("id_concentrado", id_concentrado);
            concentradoMap.put("id_asunto", ids_asunto.get("id_asunto"));
            concentradoMap.put("id_asunto_detalle", ids_asunto.get("id_asunto_detalle"));
            insert("Asunto.insertConcentrado", concentradoMap);
        }
        return id_concentrado;
    }

    public int updateAsuntoConcentrado(List<Map<String, Integer>> ids_asuntoMap, Integer id_concentrado)
    {
//        try
//        {
//            this.getSqlMapClient().startTransaction();
//            this.getSqlMapClient().startBatch();
        for (Map<String, Integer> ids_asunto : ids_asuntoMap)
        {
            HashMap<String, Integer> concentradoMap = new HashMap<String, Integer>();
            concentradoMap.put("id_concentrado", id_concentrado);
            concentradoMap.put("id_asunto", ids_asunto.get("id_asunto"));
            concentradoMap.put("id_asunto_detalle", ids_asunto.get("id_asunto_detalle"));
            insert("Asunto.insertConcentrado", concentradoMap);
        }
//            this.getSqlMapClient().executeBatch();
//            this.getSqlMapClient().commitTransaction();
//            this.getSqlMapClient().endTransaction();

//        } catch (SQLException e)
//        {
//            e.printStackTrace();
//            try
//            {
//                this.getSqlMapClient().endTransaction();
//            } catch (SQLException e1)
//            {
//                e1.printStackTrace();
//            }
//        }
        return id_concentrado;
    }

    /**
     * Obtiene el folio del asunto en base al area
     * @param idArea
     * @return
     */
    public String getFolioAsunto(Integer idArea)
    {
        return (String) this.queryForObject("Asunto.getFolioAsunto", idArea);
    }

    /**
     * @param documentoBlobDao
     *            the documentoBlobDao to set
     */
    public void setDocumentoBlobDao(DocumentoBlobDao documentoBlobDao)
    {
        this.documentoBlobDao = documentoBlobDao;
    }

    /**
     * Obtiene la lista de id's de asunto para un criterio dado desde la pantalla de captura
     * @param criterioAsunto
     * @return
     */
    public List<String> getIdsAsuntoByCriterio(CriterioAsunto criterioAsunto)
    {
        return (List<String>) this.queryForList("Asunto.getIdsAsuntoByCriterio", criterioAsunto);
    }

    /**
     * Obtiene el ultimo id del asunto agrupado por folio en base al criterio dado
     * @param criterioAsunto
     * @return 
     */
    public List<String> getLastIdsAsuntoByCriterio(CriterioAsunto criterioAsunto)
    {
        return (List<String>) this.queryForList("Asunto.getLastIdsAsuntoByCriterio", criterioAsunto);
    }

    public List<String> getIdsAsuntoCapturaByCriterio(CriterioAsunto criterioAsunto)
    {
        return (List<String>) this.queryForList("Asunto.getIdsAsuntoCapturaByCriterio", criterioAsunto);
    }

    public List<Asunto> listAsuntoReporteVolante(CriterioReporteVolante criterio)
    {
        List<Asunto> asuntos = queryForList("Asunto.listAsuntosVolante", criterio);
        return asuntos;
    }

    public int getIdConcentrado(Integer idAsunto)
    {
        return (Integer) this.queryForObject("Asunto.getIdConcentrado", idAsunto);
    }

    public int getIdConcentradoAsunto(Integer idAsunto)
    {
        return (Integer) this.queryForObject("Asunto.getIdConcentradoAsunto", idAsunto);
    }


    public String getSigContadorFolio(String folio)
    {
        return (String) this.queryForObject("Asunto.getSigContadorFolio", folio);
    }

    public Asunto getAsuntoDetalleById(Integer id_asunto)
    {
        List<Asunto> asuntos = queryForList("Asunto.getDetalleByIdAsunto", id_asunto);
        return asuntos.size() > 0 ? asuntos.get(0) : new Asunto();
    }
    public AsuntoDetalle getDetalleByIdAsuntoDetalle(Integer id_asunto_detalle)
    {
        return (AsuntoDetalle) this.queryForObject("AsuntoDetalle.getDetalleByIdAsuntoDetalle",id_asunto_detalle);
    }

    public void borraAtenderParcial(Integer idAsunto_detalle){
        delete("Asunto.deleteCcpAtencionParcial",idAsunto_detalle);
        delete("Documento.deleteAtencionParcial",idAsunto_detalle);
        delete("AsuntoDetalle.deleteAtencionParcial",idAsunto_detalle);
    }

    public void borraCapturaTurno(Integer idAsunto_detalle){
        delete("Asunto.deleteCcpTurnoCapt",idAsunto_detalle);
        delete("Documento.deleteCapt",idAsunto_detalle);
        delete("AsuntoDetalle.deleteCapturaTurno",idAsunto_detalle);
    }

    public Asunto guardaCapturaTurno(Asunto asunto){
        // Actualiza asunto en repositorio
        Logger log = Logger.getLogger(this.getClass());
        // Guarda detalle de asunto en repositorio
        asunto.getAsunto_detalle().setId_asunto(asunto.getId_asunto());
        asunto.getAsunto_detalle().setId_asunto_detalle(asunto.getUltimaDetalle().getId_asunto_detalle());
        //para guardar el empleado que captura, uso el remitente.
        asunto.getAsunto_detalle().setEmpleado_remi(asunto.getEmpleado_capt());  //El empleado remi se usa como de captura al insertar
        Integer id_asunto_detalle = (Integer) insert("AsuntoDetalle.guardaCapturaTurno", asunto.getAsunto_detalle());
        asunto.getAsunto_detalle().setId_asunto_detalle(id_asunto_detalle);
        // Guarda "con copia para"
        List<Empleado> empleadosCcp = asunto.getAsunto_detalle().getEmpleadosCcp();
        delete("Asunto.deleteCcpTurnoCapt", asunto.getAsunto_detalle().getId_asunto_detalle());
        for (int i = 0; i < empleadosCcp.size(); i++)
        {
            Empleado empleadoCcp = empleadosCcp.get(i);
            HashMap<String, Object> asuntoEmpMap = new HashMap<String, Object>();
            asuntoEmpMap.put("id_empleado", empleadoCcp.getId_empleado());
            //asuntoEmpMap.put("id_asunto", asunto.getId_asunto()); //Comentado AGG 20111103
            //insert("Asunto.insertCcp", asuntoEmpMap); //Comentado AGG 20111103
            asuntoEmpMap.put("id_asunto_detalle", asunto.getAsunto_detalle().getId_asunto_detalle());
            insert("Asunto.insertCcpTurnoCapt", asuntoEmpMap);
        }

        // Guarda documentos adjuntos de un asunto en repositorio
        List<Documento> documentos = asunto.getAsunto_detalle().getDocumentos();
        delete("Documento.deleteCapt",asunto.getAsunto_detalle().getId_asunto_detalle());
        for (int i = 0; i < documentos.size(); i++)
        {
            Documento documento = documentos.get(i);
            documento.setId_asunto_detalle(id_asunto_detalle);
            documento.setId_asunto(asunto.getId_asunto());
            Integer id_documento = (Integer) //                session.
                    insert("Documento.insertCapt",documento);
        }        
        return asunto;
    }

    public Asunto SaveAtenderParcial(Asunto asunto){
        // Actualiza asunto en repositorio
        Logger log = Logger.getLogger(this.getClass());
        // Guarda detalle de asunto en repositorio
        asunto.getAsunto_detalle().setId_asunto(asunto.getId_asunto());
        asunto.getAsunto_detalle().setId_asunto_detalle(asunto.getUltimaDetalle().getId_asunto_detalle());
        //para guardar el empleado que captura, uso el remitente.
        asunto.getAsunto_detalle().setEmpleado_remi(asunto.getEmpleado_capt());  //El empleado remi se usa como de captura al insertar
        delete("Asunto.deleteCcpAtencionParcial", asunto.getAsunto_detalle().getId_asunto_detalle());
        delete("Documento.deleteAtencionParcial",asunto.getAsunto_detalle().getId_asunto_detalle());
        delete("AsuntoDetalle.deleteAtencionParcial",asunto.getUltimaDetalle().getId_asunto_detalle());
        Integer id_asunto_detalle = (Integer) insert("AsuntoDetalle.guardaAtencionParcial", asunto.getAsunto_detalle());
        asunto.getAsunto_detalle().setId_asunto_detalle(id_asunto_detalle);
        // Guarda "con copia para"
        List<Empleado> empleadosCcp = asunto.getAsunto_detalle().getEmpleadosCcp();        
        for (int i = 0; i < empleadosCcp.size(); i++)
        {
            Empleado empleadoCcp = empleadosCcp.get(i);
            HashMap<String, Object> asuntoEmpMap = new HashMap<String, Object>();
            asuntoEmpMap.put("id_empleado", empleadoCcp.getId_empleado());
            //asuntoEmpMap.put("id_asunto", asunto.getId_asunto()); //Comentado AGG 20111103
            //insert("Asunto.insertCcp", asuntoEmpMap); //Comentado AGG 20111103
            asuntoEmpMap.put("id_asunto_detalle", asunto.getAsunto_detalle().getId_asunto_detalle());
            insert("Asunto.insertCcpAtencionParcial", asuntoEmpMap);
        }
        // Guarda documentos adjuntos de un asunto en repositorio
        List<Documento> documentos = asunto.getAsunto_detalle().getDocumentos();        
        for (int i = 0; i < documentos.size(); i++)
        {
            Documento documento = documentos.get(i);
            documento.setId_asunto_detalle(id_asunto_detalle);
            documento.setId_asunto(asunto.getId_asunto());
            Integer id_documento = (Integer) //                session.
                    insert("Documento.insertAtencionParcial",documento);
        }
        return asunto;
    }

    public List <AsuntoDetalle> listTurnoGuardado(Integer id_asunto_detalle){
        List <AsuntoDetalle> asuntosDetalle;
        asuntosDetalle=queryForList("AsuntoDetalle.listCapturaTurno",id_asunto_detalle);
        return asuntosDetalle;
    }

    public List<String> getIdsAsuntoByCriterio_ultimo(CriterioAsunto criterioAsunto)
    {
        return (List<String>) this.queryForList("Asunto.getIdsAsuntoByCriterio_ultimo", criterioAsunto);
    }

    public AsuntoDetalle listByIdAsuntoDetalle(Integer idAsunto)
    {
        return (AsuntoDetalle) this.queryForObject("AsuntoDetalle.listByIdAsuntoDetalle", idAsunto);
    }

    public AsuntoDetalle listByIdAsuntoDetalle_ultimo(Integer idAsunto)
    {
        return (AsuntoDetalle) this.queryForObject("AsuntoDetalle.listByIdAsuntoDetalle_ultimo", idAsunto);
    }

    /**
     * Lista de los asunto iniciales
     * @param criterioAsunto
     * @return
     */
    public List<Asunto> listAsuntosInicialesNuevasReglas(CriterioAsunto criterioAsunto)
    {
        List<String> idsEstatus=criterioAsunto.getIds_estatus();
        String estatus ="";

        criterioAsunto.setEnTramite(Constantes.ACTIVADO);
        if (!criterioAsunto.getEstatus().equals("14")){
                criterioAsunto.setEnTramite(0);
        }

        for (int i = 0; i < idsEstatus.size(); i++){
            estatus = criterioAsunto.getIds_estatus().get(i);
            if (estatus.equals(Constantes.ATENCIONPARCIAL.toString())){
                criterioAsunto.setEnTramite(Constantes.INSTANCIA_CREADA);
                criterioAsunto.getIds_estatus().remove(estatus);
            }
        }
        List<Asunto> asuntos = queryForList("Asunto.listAsuntosInicialesNuevasReglas",
                criterioAsunto);
        return asuntos;
    }

    public Integer countAsuntosInicialesNuevasReglas(CriterioAsunto criterioAsunto)
    {
        List<String> idsEstatus=criterioAsunto.getIds_estatus();
        String estatus ="";
        for (int i = 0; i < idsEstatus.size(); i++){
            estatus = criterioAsunto.getIds_estatus().get(i);
            if (estatus.equals(Constantes.ATENCIONPARCIAL.toString())){
                criterioAsunto.setEnTramite(Constantes.INSTANCIA_CREADA);
                criterioAsunto.getIds_estatus().remove(estatus);
            }
        }
        Integer numRegistros = (Integer) queryForObject(
                "Asunto.countAsuntosInicialesNuevasReglas", criterioAsunto);
        return numRegistros;
    }

    public Integer ultimoEstatusAsuntoByArea(HashMap param)
    {
        return (Integer) this.queryForObject("AsuntoDetalle.ultimoEstatusAsuntoByArea", param);
    }

    public Asunto SaveAtenderParcialRechazo(Asunto asunto){
        // Actualiza asunto en repositorio
        Logger log = Logger.getLogger(this.getClass());
        // Guarda detalle de asunto en repositorio
        asunto.getAsunto_detalle().setId_asunto(asunto.getId_asunto());
        asunto.getAsunto_detalle().setId_asunto_detalle(asunto.getUltimaDetalle().getId_asunto_detalle());
        //para guardar el empleado que captura, uso el remitente.
        asunto.getAsunto_detalle().setEmpleado_remi(asunto.getEmpleado_capt());  //El empleado remi se usa como de captura al insertar
        delete("Asunto.deleteCcpAtencionParcial", asunto.getUltimaDetalle().getId_asunto_detalle());
        delete("Documento.deleteAtencionParcial",asunto.getUltimaDetalle().getId_asunto_detalle());
        delete("AsuntoDetalle.deleteAtencionParcialRechazo",asunto.getId_asunto());

        // Insertar respuesta en blanco, para identificarla en el rechazo
        asunto.getAsunto_detalle().setComentario("");


        Integer id_asunto_detalle = (Integer) insert("AsuntoDetalle.guardaAtencionParcialRechazo", asunto.getAsunto_detalle());
        asunto.getAsunto_detalle().setId_asunto_detalle(id_asunto_detalle);
        // Guarda "con copia para"
        List<Empleado> empleadosCcp = asunto.getAsunto_detalle().getEmpleadosCcp();
        for (int i = 0; i < empleadosCcp.size(); i++)
        {
            Empleado empleadoCcp = empleadosCcp.get(i);
            HashMap<String, Object> asuntoEmpMap = new HashMap<String, Object>();
            asuntoEmpMap.put("id_empleado", empleadoCcp.getId_empleado());
            //asuntoEmpMap.put("id_asunto", asunto.getId_asunto()); //Comentado AGG 20111103
            //insert("Asunto.insertCcp", asuntoEmpMap); //Comentado AGG 20111103
            asuntoEmpMap.put("id_asunto_detalle", asunto.getAsunto_detalle().getId_asunto_detalle());
            insert("Asunto.insertCcpAtencionParcial", asuntoEmpMap);
        }
        // Guarda documentos adjuntos de un asunto en repositorio
        List<Documento> documentos = asunto.getAsunto_detalle().getDocumentos();
        for (int i = 0; i < documentos.size(); i++)
        {
            Documento documento = documentos.get(i);
            documento.setId_asunto_detalle(id_asunto_detalle);
            documento.setId_asunto(asunto.getId_asunto());
            Integer id_documento = (Integer) //                session.
                    insert("Documento.insertAtencionParcial",documento);
        }
        return asunto;
    }

}
