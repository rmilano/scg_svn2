<%@ include file="/jsp/taglibs.jsp"%>
<link href='<html:rewrite page="/jsp/inicio/css/login.css"/>' rel="stylesheet" type="text/css" />
<link href='<html:rewrite page="/jsp/inicio/css/jquery.scroller.css"/>' rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<html:rewrite page="/jsp/inicio/js/jquery.scroller.js"/>"></script>
<script type="text/javascript" src="<html:rewrite page="/jsp/js/autenticacion.js"/>"></script>

<div class="content border">
    <div class="inner corner">
        <div class="superior">

            <div class="div_textos">
                <table id="tblMision" border="0">
                        <tr>
                            <td class="td_concepto_izq_gde">
                                <html:img src="../../imagenes/txt.png" width="25px" height="25px" title="SCG"></html:img>
                                Sistema de Control de Gesti&oacute;n
                            </td>
                        </tr>
                        <tr><td><hr class="hr_subtitulo"></td></tr>
                        <tr>
                            <td class="td_descripcion_just_peq" width="100%">
                                El SCG cuenta con el diagnóstico o análisis para entender las causas raíces que condicionan
                                el comportamiento de los sistemas físicos, permite establecer los vínculos funcionales que
                                ligan las variables técnicas-organizativas-sociales con el resultado económico de la empresa y
                                es el punto de partida para la mejora de los estándares; mediante la planificación orienta
                                las acciones en correspondencia con las estrategias trazadas, hacia mejores resultados; y,
                                finalmente, cuenta con el control para saber si los resultados satisfacen los objetivos trazados.
                            </td>
                        </tr>
                </table>
              <hr class="hr_titulo">
                <table id="tblMision" border="0">
                        <tr>
                            <td class="td_concepto_izq_gde">
                                <html:img src="../../imagenes/txt.png" width="25px" height="25px" title="Misi&oacute;n"></html:img>
                                Misi&oacute;n
                            </td>
                        </tr>
                        <tr><td><hr class="hr_subtitulo"></td></tr>
                        <tr>
                            <td class="td_descripcion_just_peq" width="100%">
                                La Misión es la razón de ser de una persona, equipo y empresa, con lo que le permite existir,
                                lograr su sostenibilidad o rentabilidad.
                                La declaración de Misión describe el propósito general de la organización.
                                La Misión es lo que es la organización: HOY,
                                es el propósito central para el que se crea un ente.
                            </td>
                        </tr>
                    </table>
                <hr class="hr_titulo">
                    <table id="tblVision" border="0">
                        <tr>
                            <td class="td_concepto_izq_gde">
                                <html:img src="../../imagenes/txt.png" width="25px" height="25px" title="Visi&oacute;n"></html:img>
                                Visi&oacute;n
                            </td>
                        </tr>
                        <tr><td><hr class="hr_subtitulo"></td></tr>
                        <tr>
                            <td class="td_descripcion_just_peq">
                                La Visión es una declaración acerca de lo que su organización quiere llegar a ser.
                                Debe tener resonancia con todos los miembros de la organización y permitirles sentirse orgullosos,
                                emocionados, y ser parte de algo mucho más grande que ellos mismos.
                                Una visión debe potenciar las capacidades de la organización y la imagen de sí misma.
                                La Visión le da forma y dirección al futuro de la organización.
                            </td>
                        </tr>
                    </table>
                <hr class="hr_titulo">
            </div> <!-- textos -->

            
            <div class="login">
                <div class="img_login">
                    <html:img src="../../imagenes/login.png" title="Login"></html:img>
                </div>
                    <form name="loginForm" id="uid-form" action="<html:rewrite page="/j_acegi_security_check"/>" method="post">
                        <%--<label class="td_titulo"><bean:message key="label.autenticacion" /></label>--%>
                        <label class="td_concepto_izq_peq"><bean:message key="label.usuario" /></label>
                        <label>
                            <input type='text' name='j_username' id="uid" class="required" /></label>
                        <label class="td_concepto_izq_peq"><bean:message key="label.contrasenna" /></label>
                        <label>
                            <input type="password" name="j_password" class="text required" value="prueba" /></label>
                            <br/>
                        <label> <input name="login" class="btn_login enterButton" type="submit" value="Entrar" title="Iniciar sesión"></label>
						<logic:present parameter="errorContrasenna">
                            <label><font color="RED">La contrase&ntilde;a es incorrecta</font></label>
                        </logic:present>
                    </form>
            </div><!-- login -->

            <div class="div_avisos">
                <table id="tblAvisos" border="0">
                        <tr>
                            <td class="td_concepto_izq_gde">
                                <html:img src="../../imagenes/aviso.png" width="20px" height="20px" title="Avisos"></html:img>
                                Avisos
                            </td>
                        </tr>
                        <tr><td><hr class="hr_subtitulo"></td></tr>
                        <tr>
                            <td class="td_descripcion_just_peq">
                              <html:img src="../../imagenes/mex.png" width="15px" height="15px" title="Avisos"></html:img>
                                "Informe Doing Business 2011: Creando oportunidades para emprendedores.
                                Doing Business, en su octava edición, forma parte de una serie de informes anuales publicados por el Banco Mundial
                                y la Corporación Financiera Internacional (IFC).
                            </td>
                        </tr>
                    </table>
            </div><!-- avisos -->



    </div> <!-- superior -->
        
    <div class="pelicula">
      <span class="td_concepto_izq_peq">Enlaces Externos</span>
         <table id="tblAvisos" border="0">
            <tr>
              <td class="td_concepto_izq_gde" width="100%" align="center">

                <!-- wrapper for navigator elements -->
                 <div class="navi"></div>
                    <!-- "previous page" action -->
                    <a class="prev browse left"></a>
                        <!-- root element for scrollable -->
                        <div class="scrollable" id="chained">
                           <!-- root element for the items -->
                           <div class="items">
                              <!-- 1-5 -->
                              <div>
                                <a href="http://www.economia.gob.mx/swb/es/economia/p_Logotipo_Hecho_Mexico">
                                    <html:img src="../../imagenes/hnm.jpg" title="Portal Hecho en México" ></html:img></a>
                                <a href="http://www.practicascomerciales.economia.gob.mx/">
                                    <html:img src="../../imagenes/practicascomerciales.jpg" title="Portal Prácticas Comerciales"></html:img></a>
                                <a href="http://www.economia.gob.mx/swb/es/economia/p_cpyme_Capacitacion_Virtual_PyME">
                                    <html:img src="../../imagenes/pyme.jpg" title="Portal PyMe"></html:img></a>
                                <a href="http://www.economia.gob.mx/swb/es/economia/p_Logotipo_Hecho_Mexico">
                                    <html:img src="../../imagenes/tuempresa.png" title="Portal Tu Empresa"></html:img></a>
                                <a href="http://www.economia.gob.mx/swb/es/economia/p_Catalogo_Mexicano_Normas">
                                    <html:img src="../../imagenes/dgn.jpg" title="Portal DGN"></html:img></a>
                                <a href="http://www.economia.gob.mx/swb/es/economia/p_e5cinco">
                                    <html:img src="../../imagenes/e5cincop.jpg" title="Portal e5cinco"></html:img></a>
                                <a href="http://www.bicentenario.gob.mx/">
                                    <html:img src="../../imagenes/LogoMexico.png" title="Portal e5cinco"></html:img></a>
                                <a href="http://www.promexico.gob.mx/">
                                    <html:img src="../../imagenes/logoProMexico.png" title="Portal e5cinco"></html:img></a>
                                <a href="http://www.coremisgm.gob.mx/">
                                    <html:img src="../../imagenes/sgm.gif" title="Portal e5cinco"></html:img></a>
                                <%--<a href="http://www.economia.gob.mx/swb/es/economia/p_e5cinco" border="0"><html:img src="../../imagenes/e5cincop.jpg" title="Portal e5cinco"></html:img></a>
                                <a href="http://www.economia.gob.mx/swb/es/economia/p_e5cinco" border="0"><html:img src="../../imagenes/e5cincop.jpg" title="Portal e5cinco"></html:img></a>
                                <a href="http://www.economia.gob.mx/swb/es/economia/p_e5cinco" border="0"><html:img src="../../imagenes/e5cincop.jpg" title="Portal e5cinco"></html:img></a>--%>
                              </div>
                           </div>
                       </div>
                    <a class="next browse right"></a>
                </td>
            </tr>
          </table>
      </div> <!-- pelicula -->

  </div> <!-- inner corner -->
</div> <!-- content border -->


