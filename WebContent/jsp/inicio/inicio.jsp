<!--Pantalla de inico de session-->

<%@ include file="/jsp/taglibs.jsp"%>
<link href='<html:rewrite page="/jsp/inicio/css/login.css"/>' rel="stylesheet" type="text/css" />

<div class="content border">
    <div class="inicio-parent inner corner">

        <!-- Mis asuntos-->
        <div class="mis-asuntos corner items-panel">
            <label><html:img src="../../imagenes/asuntos.png" styleClass="imgPanel_" alt="" ></html:img><span>Mi panel</span></label>
            <ul>
                    <li>  <a class="linkInicio" href='<html:rewrite page="/consulta-bandeja.do?method=inicio&p_bandeja=1&p_estatus=-1&p_confidencial=0&p_copia=0&p_historico=0" />'>
                        Bandeja de entrada
                    </a>
                    <ul>
                        <li> <a class="linkInicio" href='<html:rewrite page="/consulta-bandeja.do?method=inicio&p_bandeja=1&p_estatus=0&p_confidencial=0&p_copia=0&p_historico=0" />'>
                                Inicial
                            </a>
                        </li>
                        <li> <a class="linkInicio" href='<html:rewrite page="/consulta-bandeja.do?method=inicio&p_bandeja=1&p_estatus=1&p_confidencial=0&p_copia=0&p_historico=0" />'>
                                Recibido
                            </a>
                        </li>
                        <li> <a class="linkInicio" href='<html:rewrite page="/consulta-bandeja.do?method=inicio&p_bandeja=1&p_estatus=14&p_confidencial=0&p_copia=0&p_historico=0" />'>
                                En trámite
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


        <!--                            Panel de administrador-->
        <logic:equal name="isAdministrador" value="true">
            <div class="panel-admon corner items-panel">
                <label><html:img src="../../imagenes/admin1.png" styleClass="imgPanel_" alt="" ></html:img><span>Administraci&oacute;n</span></label>
                <ul>
                    <li> <a class="linkInicio"  href='<html:rewrite page="/empleados.do?method=inicio" />'> Cat&aacute;logo de empleados </a> </li> <br/>
                    <li> <a class="linkInicio"  href='<html:rewrite page="/areas.do?method=init" />'> Cat&aacute;logo de &aacute;reas </a> </li> <br/>
                    <li> <a class="linkInicio"  href='<html:rewrite page="/tipo-documento.do?method=init" />'> Cat&aacute;logo de Tipos de Documento </a> </li><br/>
                    <li> <a class="linkInicio"  href='<html:rewrite page="/prioridad.do?method=inicio" />'> Cat&aacute;logo de Prioridades </a> </li><br/>
                    <li> <a class="linkInicio"  href='<html:rewrite page="/temas.do?method=inicio" />'> Cat&aacute;logo de Temas </a> </li><br/>
                    <li> <a class="linkInicio"  href='<html:rewrite page="/eventos.do?method=inicio" />'> Cat&aacute;logo de Eventos </a> </li><br/>
                    <li> <a class="linkInicio"  href='<html:rewrite page="/instruccion.do?method=init" />'> Cat&aacute;logo de Instrucciones </a> </li><br/>
                    <li> <a class="linkInicio"  href='<html:rewrite page="/expediente.do?method=init" />'> Cat&aacute;logo de Expedientes </a> </li><br/>
                </ul>
            </div>

        </logic:equal>


        <!--        Panel de ayuda-->
        <div class="panel-ayuda corner items-panel">
            <label> <html:img src="../../imagenes/ayuda_48x48.png" styleClass="imgPanel_" alt="" ></html:img><span>Ayuda</span></label>
            <ul>
                <li> <a class="linkInicio" onclick="window.open('<html:rewrite page="/jsp/inicio/manual-scg-2.0.pdf" />');"> Manual de Usuario </a> </li>
            </ul>
        </div>

    </div>
</div>
