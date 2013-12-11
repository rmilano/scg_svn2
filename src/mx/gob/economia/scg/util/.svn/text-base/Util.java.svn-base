package mx.gob.economia.scg.util;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.providers.encoding.Md5PasswordEncoder;
import org.acegisecurity.providers.ldap.authenticator.LdapShaPasswordEncoder;

public class Util
{

    public static final String FORMATODDMMYYYY = "dd/MM/yyyy";
    public static final String FORMATODDMMYYYYHHMMSS = "dd/MM/yyyy HH:mm:ss";
    public static final String FORMATOYYYYMMDD_HHMMSS = "yyyyMMdd_HHmmss";
    public static final String FORMATO_EVENTO = "dd/MM/yyyy HH:mm";
    public static final int ASCENDENTE = 0;
    public static final int DESCENDENTE = 1;

    public static String formatearCadena(String valor)
    {
        try
        {
            if (valor != null && valor.length() > 0)
            {
                valor = valor.trim().toUpperCase();
                valor = valor.replaceAll("�", "A");
                valor = valor.replaceAll("�", "E");
                valor = valor.replaceAll("�", "I");
                valor = valor.replaceAll("�", "O");
                valor = valor.replaceAll("�", "U");
            }
        } catch (Exception e)
        {
            valor = null;
        }
        return valor;
    }

    public static String encriptarMd5(String valor)
    {
        try
        {
            Md5PasswordEncoder md5 = new Md5PasswordEncoder();
            valor = md5.encodePassword(valor, null);
        } catch (Exception e)
        {
            valor = "";
        }
        return valor;
    }

    public static String encriptarSha(String valor)
    {
        try
        {
            LdapShaPasswordEncoder sha = new LdapShaPasswordEncoder();
            valor = sha.encodePassword(valor, null);
        } catch (Exception e)
        {
            valor = "";
        }
        return valor;
    }

    public static String getAlfaNumAleatorio(int longitud)
    {
        String cadenaAleatoria = "";
        long milis = new GregorianCalendar().getTimeInMillis();
        Random r = new Random(milis);
        int i = 0;
        while (i < longitud)
        {
            char c = (char) r.nextInt(255);
            if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z')
                    || (c >= 'A' && c <= 'Z'))
            {
                cadenaAleatoria += c;
                i++;
            }
        }
        return cadenaAleatoria;
    }

    /**
     * Obtiene la ruta de la aplicacion http://192.25.25.55:8082/HechoEnMexico/
     * 
     * @param request
     * @return
     */
    public static String getRutaAplicacion(HttpServletRequest request)
    {
        String dominio = ResourceBundle.getBundle("Aplicacion.properties").getString("ruta.dominio");
        String ruta = request.getScheme() + "://" + dominio
                + request.getContextPath() + "/";

        return ruta;
    }

    public static Date parsearFecha(String valor)
    {
        Date fecha = null;
        String formato = "";
        try
        {
            formato = valor.charAt(2) == '/' ? "dd/MM/yyyy"
                    : valor.charAt(2) == '-' ? "dd-MM-yyyy"
                    : valor.charAt(4) == '/' ? "yyyy/MM/dd" : valor.charAt(4) == '-' ? "yyyy-MM-dd" : "";
            fecha = parsearFecha(valor, formato);

        } catch (Exception e)
        {
            fecha = null;
        }

        return fecha;
    }

    public static Date parsearFecha(String valor, String formato)
    {

        Date fecha = null;
        SimpleDateFormat formateador = new SimpleDateFormat(formato);
        try
        {
            fecha = formateador.parse(valor);
        } catch (ParseException e)
        {
            fecha = null;
        }
        return fecha;
    }

    public static String formatearFecha(Date date, String formato)
    {

        String fecha = null;
        SimpleDateFormat formateador = new SimpleDateFormat(formato);
        if (date == null)
            fecha = "\\N";
        else
            fecha = formateador.format(date);
        return fecha;
    }

    public static String formatearFecha(Date date)
    {

        String fecha = null;
        SimpleDateFormat formateador = new SimpleDateFormat(FORMATODDMMYYYY);
        fecha = formateador.format(date);
        return fecha;
    }
    /**
     * Obtiene la fecha con el formato dd/MM/yyyy HH24:mm
     * @param date
     * @return 
     */
    public static String formatearFechaEvento(Date date)
    {

        String fecha = null;
        SimpleDateFormat formateador = new SimpleDateFormat(FORMATO_EVENTO);
        fecha = formateador.format(date);
        return fecha;
    }

    /**
     * Asigna un timestamp a una fecha con el formato YYYY-MM-DD HH24:MI 
     * @param date
     * @return 
     */
    public static Date formatearFechaToEvento(String valor)
    {
        Date fecha = null;
        SimpleDateFormat formateador = new SimpleDateFormat(FORMATO_EVENTO);
        try
        {
            fecha = formateador.parse(valor);
        } catch (ParseException e)
        {
            fecha = null;
        }
        return fecha;
    }

    public static void ordena(List lista, final String propiedad,
            final int ordenacion)
    {

        Collections.sort(lista, new Comparator()
        {

            public int compare(Object obj1, Object obj2)
            {

                Class clase = obj1.getClass();
                String getter = "get"
                        + Character.toUpperCase(propiedad.charAt(0))
                        + propiedad.substring(1);
                try
                {
                    Method getPropiedad = clase.getMethod(getter);

                    Object propiedad1 = getPropiedad.invoke(ordenacion == ASCENDENTE ? obj1 : obj2);
                    Object propiedad2 = getPropiedad.invoke(ordenacion == ASCENDENTE ? obj2 : obj1);

                    if (propiedad1 instanceof Comparable
                            && propiedad2 instanceof Comparable)
                    {
                        Comparable prop1 = (Comparable) propiedad1;
                        Comparable prop2 = (Comparable) propiedad2;
                        return prop1.compareTo(prop2);
                    }// CASO DE QUE NO SEA COMPARABLE
                    else if (propiedad1.equals(propiedad2))
                        return 0;
                    else
                        return 1;

                } catch (Exception e)
                {
                    e.printStackTrace();
                }
                return 0;
            }
        });
    }

    public static Map ordenarHashMap(Map hmap)
    {
        HashMap map = new LinkedHashMap();
        List mapKeys = new ArrayList(hmap.keySet());
        Collections.sort(mapKeys);
        for (int i = 0; i < mapKeys.size(); i++)
        {
            map.put(mapKeys.get(i), hmap.get(mapKeys.get(i)));
        }
        return map;
    }

    public static Map ordenarHashValuesMap(Map map)
    {

        List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator()
        {

            public int compare(Object o1, Object o2)
            {
                return ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue());
            }
        });
        Map result = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();)
        {
            Map.Entry entry = (Map.Entry) it.next();
            result.put(entry.getKey(), entry.getValue());
        }
        return result;

    }

    public static Date restarDias(Date date, int dias)
    {
        Calendar fecha = Calendar.getInstance(); // obtiene la fecha actual
        fecha.add(Calendar.DATE, -dias); // incrementa en 30 dï¿œas la fecha
        // actual.
        return fecha.getTime();
    }

    public static Calendar fechaHabil(Calendar fecha, int dias)
    {
        int toAdd = dias < 0 ? -1 : 1;
        dias = dias < 0 ? dias * -1 : dias;
        for (int i = 0; i < dias; i++)
        {
            fecha.add(Calendar.DATE, toAdd);
            while (!esHabil(fecha))
            {
                fecha.add(Calendar.DATE, toAdd);
            }
        }
        return fecha;
    }

    public static boolean esHabil(Calendar fecha)
    {
        switch (fecha.get(Calendar.DAY_OF_WEEK))
        {
            case 1:
                // Domingo
                return false;
            case 7:
                // Sabado
                return false;
            default:
                // Entre semana
                // Obtener los dias festivos, por defaul no son festivos
                boolean isFestivo = false;
                if (isFestivo)
                    return false;
                else
                    return true;
        }
    }

    public static Map<Integer, String> getListTransform(Map<Integer, String> map)
    {
        Map<Integer, String> mapTransform = new HashMap<Integer, String>();
        if (map != null)
        {
            java.util.Iterator<Entry<Integer, String>> it = map.entrySet().iterator();
            while (it.hasNext())
            {
                Map.Entry<Integer, String> pairs = (Map.Entry<Integer, String>) it.next();
                mapTransform.put(pairs.getKey(), getStringTransform(pairs.getValue()));
            }
        }
        return mapTransform;
    }

    public static String getStringTransform(String value)
    {
        String substr = "", substrLasted = "", string = value;
        if (value != null && value.length() > 0)
        {
            string = string.toLowerCase();
            substr = ("" + string.charAt(0)).toUpperCase();
            substrLasted = string.substring(1, string.length());
        }
        return "" + substr + substrLasted;
    }

    public static String getHtml(String contenido)
    {
        return "<HTML><head><meta charset='ISO-8859-1'></head><BODY><TABLE BORDER='0'><TR><TD COLSPAN='3'><img src='cid:cidimage01' /></TD></TR>"
                + "<TR HEIGHT='200px' VALIGN='TOP'><TD WIDTH='5%'>&nbsp;</TD><TD WIDTH='90%'>"
                + contenido
                + "</TD><TD WIDTH='5%'>&nbsp;</TD></TR>"
                /*+ "<TR><TD COLSPAN='3'><HR>Alfonso Reyes No. 30 Col. Hip&oacute;dromo Condesa C.P. 06140, "
                + "Delegaci&oacute;n Cuauht&eacute;moc, M&eacute;xico, D.F. Tel: 01 800 410 2000, 5729 9100 | "
                + "T&eacute;rminos de uso y pol&iacute;ticas de privacidad "
                + "</a></TD></TR>"*/
                +"<TR><TD COLSPAN='3'><img src='cid:cidimage02' /></TD></TR>"
                + "</TABLE></BODY></HTML>";
    }

    public static void descargarArchivo(byte[] arrDatos,
            HttpServletResponse response, String nombreArchivo)
            throws IOException
    {

        String extension = nombreArchivo.substring(nombreArchivo.lastIndexOf("."));
        String content = getContentType(extension);

        OutputStream outputStream = response.getOutputStream();
        if (content != null && content.length() > 0)
            response.setContentType(content);
        response.setContentLength(arrDatos.length);
        response.setHeader("Content-Disposition", "attachment; filename="
                + nombreArchivo);
        outputStream.write(arrDatos, 0, arrDatos.length);
        outputStream.flush();
        outputStream.close();
    }

    public static String getContentType(String extension)
    {

        Map<String, String> tipos = new HashMap<String, String>();
        tipos.put(".DOC", "application/msword");
        tipos.put(".DOCX",
                "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        tipos.put(".PDF", "application/pdf");
        tipos.put(".RAR", "application/x-rar-compressed");
        tipos.put(".REV", "application/x-rar-compressed");
        tipos.put(".R00", "application/x-rar-compressed");
        tipos.put(".R01", "application/x-rar-compressed");
        tipos.put(".ZIP", "application/zip");
        tipos.put(".ZIPX", "application/zip");
        tipos.put(".XLA", "application/vnd.ms-excel");
        tipos.put(".XLC", "application/vnd.ms-excel");
        tipos.put(".XLM", "application/vnd.ms-excel");
        tipos.put(".XLS", "application/vnd.ms-excel");
        tipos.put(".XLT", "application/vnd.ms-excel");
        tipos.put(".XLW", "application/vnd.ms-excel");
        tipos.put(".XLSX", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        tipos.put(".PPT", "application/vnd.ms-powerpoint");
        tipos.put(".PPTX", "application/vnd.openxmlformats-officedocument.presentationml.presentation");
        tipos.put(".TXT", "text/plain");

        String contentType = tipos.get(extension) != null ? tipos.get(extension) : "";

        return contentType;

    }

    public String getDateTime(String format)
    {
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date();
        return dateFormat.format(date);
    }
    /**
     * Obtiene el no. de minutes entre una fecha y otra
     * @param oldTime
     * @param currentTime
     * @return
     */
    public Long compareTimesByMinutes(Timestamp oldTime, Timestamp currentTime)
    {
        Long difmiliSeconds  = currentTime.getTime() - oldTime.getTime(); // obtiene el no. de milisegundos
        difmiliSeconds = (difmiliSeconds/(1000 * 60));// la diferencia en minutos
        return difmiliSeconds;
    }
}
