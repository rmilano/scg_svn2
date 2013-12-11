<%@page import="mx.gob.economia.scg.util.Constantes"%>
<%@ include file="/jsp/taglibs.jsp"%>
<%@ page import="mx.gob.economia.scg.model.Empleado"%>
<%@ page import="mx.gob.economia.scg.model.Rol"%>

<div class="menu_principal">
    <logic:present name="usuario_sesion">
        <a tabindex="0" href="<html:rewrite page='/'/>" class="fg-button fg-button-icon-right ui-widget ui-state-default ui-corner-all"><span class="ui-icon ui-icon-triangle-1-s"></span>Inicio</a>
        <div id="inicio" class="hidden">

        </div>
        <a tabindex="0" href="#menu-asuntos" class="fg-button fg-button-icon-right ui-widget ui-state-default ui-corner-all menu" id="asuntos"><span class="ui-icon ui-icon-triangle-1-s"></span>Mi panel</a>
        <div id="menu-asuntos" class="hidden">
            <ul>
                    <li> <a class="linkInicio" href='<html:rewrite page="/consulta-bandeja.do?method=inicio&p_bandeja=1&p_estatus=-1&p_confidencial=0&p_copia=0&p_historico=0" />'>
                        Bandeja de entrada
                    </a>
                    <ul>
                        <li> <a class="linkInicio" href='<html:rewrite page="/consulta-bandeja.do?method=inicio&p_bandeja=1&p_estatus=0&p_confidencial=0&p_copia=0&p_historico=0" />'>
                                Inicial
                            </a>
                        </li>
                        <li> <a class="linkInicio" href='<html:rewrite page="/consulta-bandeja.do?method=inicio&p_bandeja=1&p_estatus=14&p_confidencial=0&p_copia=0&p_historico=0" />'>
                                En trámite
                            </a>
                        </li>
                        <li> <a class="linkInicio" href='<html:rewrite page="/consulta-bandeja.do?method=inicio&p_bandeja=1&p_estatus=1&p_confidencial=0&p_copia=0&p_historico=0" />'>
                                Recibido
                            </a>
                        </li>
                        <li> <a class="linkInicio" href='<html:rewrite page="/consulta-bandeja.do?method=inicio&p_bandeja=1&p_estatus=2&p_confidencial=0&p_copia=0&p_historico=0" />'>
                                Para visto bueno
                            </a>
                        </li>
                        <li> <a class="linkInicio" href='<html:rewrite page="/consulta-bandeja.do?method=inicio&p_bandeja=1&p_estatus=-1&p_confidencial=0&p_copia=1&p_historico=0" />'>
                                Con copia
                            </a>
                        </li>
                    </ul>
                </li>
                <br/>
                <li> <a class="linkInicio" href='<html:rewrite page="/consulta-bandeja.do?method=inicio&p_bandeja=2&p_estatus=-1&p_confidencial=0&p_copia=0&p_historico=0" />'>
                        Bandeja de salida
                    </a>
                    <ul>
                        <li> <a class="linkInicio" href='<html:rewrite page="/consulta-bandeja.do?method=inicio&p_bandeja=2&p_estatus=1&p_confidencial=0&p_copia=0&p_historico=0" />'>
                                Pendiente
                            </a>
                        </li>
                        <li> <a class="linkInicio" href='<html:rewrite page="/consulta-bandeja.do?method=inicio&p_bandeja=2&p_estatus=14&p_confidencial=0&p_copia=0&p_historico=0" />'>
                                En trámite
                            </a>
                        </li>
                        <li> <a class="linkInicio" href='<html:rewrite page="/consulta-bandeja.do?method=inicio&p_bandeja=2&p_estatus=2&p_confidencial=0&p_copia=0&p_historico=0" />'>
                                Atendido
                            </a>
                        </li>
                    </ul>
                </li> <br/>
                <logic:equal name="isCapturista" value="true">
                    <li>  <a class="linkInicio" href='<html:rewrite page="/consulta-bandeja.do?method=inicio&p_bandeja=3&p_estatus=7&p_confidencial=0&p_copia=0&p_historico=0" />'>
                             En captura
                          </a>
                    </li>
                </logic:equal>
                <li> <a class="linkInicio" href='<html:rewrite page="/consulta-bandeja.do?method=inicio&p_bandeja=3&p_estatus=3&p_confidencial=0&p_copia=0&p_historico=0" />'>
                          Concluido
                     </a>
                </li>
                <!--
                <li> <a class="linkInicio" href='<html:rewrite page="/consulta-bandeja.do?method=inicio&p_bandeja=3&p_estatus=-1&p_confidencial=0&p_copia=0&p_historico=1" />'>
                          Seguimiento de asuntos
                     </a>
                 </li>
                -->
                <logic:equal name="isCapturista" value="true">
                    <li>
                        <a  class="linkInicio"  href='<html:rewrite page="/consulta-captura.do?method=inicio" />'>
                            Generales
                        </a>
                        <ul>
                            <li>
                                <a  class="linkInicio"  href='<html:rewrite page="/captura.do?method=inicio" />'>Capturar nuevo</a>
                            </li>
                        </ul>
                    </li>
                </logic:equal>
            </ul>
        </div>
        <!-- Inicio Reportes -->
        <a tabindex="0" href="#menu-reportes" class="fg-button fg-button-icon-right ui-widget ui-state-default ui-corner-all" id="reportes"><span class="ui-icon ui-icon-triangle-1-s"></span>Reportes</a>
        <div id="menu-reportes" class="hidden">
            <ul>
                <li><a href='<html:rewrite page="/reportes.do?method=inicio" />'> Reportes general</a></li>
                <logic:equal name="isReporteDetalle" value="true">
                    <li><a href='<html:rewrite page="/reportes.do?method=reporteGeneralDetalle&nuevaConsultaDetalle=menu" />'> Reportes a detalle</a></li>
                </logic:equal>
                <li><a href='<html:rewrite page="/reportevolantes.do?method=inicio" />'>Archivo de volantes </a></li>
            </ul>
        </div>
        <!-- Fin Reportes -->

        <logic:equal name="isAdministrador" value="true">
            <a tabindex="0" href="#menu-admin" class="fg-button fg-button-icon-right ui-widget ui-state-default ui-corner-all" id="admin"><span class="ui-icon ui-icon-triangle-1-s"></span>Administraci&oacute;n</a>
            <div id="menu-admin" class="hidden">
                <ul>
                    <li><a href='<html:rewrite page="/empleados.do?method=inicio" />'>
                            Cat&aacute;logo de Empleados
                        </a></li>
                    <li><a href='<html:rewrite page="/areas.do?method=init" />'>
                            Cat&aacute;logo de &Aacute;reas
                        </a></li>
                    <li><a href='<html:rewrite page="/tipo-documento.do?method=init" />'>
                            Cat&aacute;logo de Tipos de Documentos
                        </a></li>
                    <li><a href='<html:rewrite page="/prioridad.do?method=inicio" />'>
                            Cat&aacute;logo de Prioridades
                        </a></li>
                    <li><a href='<html:rewrite page="/temas.do?method=inicio" />'>
                            Cat&aacute;logo de Temas
                        </a></li>
                    <li><a href='<html:rewrite page="/eventos.do?method=inicio" />'>
                            Cat&aacute;logo de Eventos
                        </a></li>
                    <li><a href='<html:rewrite page="/instruccion.do?method=init" />'>
                            Cat&aacute;logo de Instrucciones
                        </a></li>
                    <li><a href='<html:rewrite page="/expediente.do?method=init" />'>
                            Cat&aacute;logo de Expedientes
                        </a></li>
                </ul>
            </div>
        </logic:equal>
        <a tabindex="0" href="#menu-ayuda" class="fg-button fg-button-icon-right ui-widget ui-state-default ui-corner-all" id="ayuda"><span class="ui-icon ui-icon-triangle-1-s"></span>Ayuda</a>
        <div id="menu-ayuda" class="hidden">
            <ul>
                <li><a href="<html:rewrite page="/jsp/inicio/manual-scg-2.0.pdf" />">Manual de usuario</a></li>
            </ul>

        </div>
    </logic:present>
</div>
