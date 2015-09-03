/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.plan.docente.web.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author 727855
 */
public class FormatoFechasUtil {

    /**
     * Formato con el que se presenta en pantalla una fecha y hora
     */
    public static final String FORMATO_FECHA_HORA_PRESENTACION = "dd/MM/yyyy";
    private static final String[] nombreMeses = new java.text.DateFormatSymbols(new java.util.Locale("ES")).getMonths();

    public static final Date stringToDate(String formato, String fecha) throws ParseException {
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat(formato);
        return formatoDelTexto.parse(fecha);
    }

    public static final String dateToString(String formato, Date fecha) throws ParseException {
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat(formato);
        return formatoDelTexto.format(fecha);
    }

    public static final String obtenerNombreMes(int numMes) {
        return nombreMeses[numMes - 1];
    }
}
