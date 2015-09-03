/*
 * Sistema De Administración De Contratos
 */
package co.com.plan.docente.web.util;

import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Jorge Montoya Jaramillo<paposkid@gmail.com>
 * @param <T>
 */
public abstract class AccionesGenerico<T> {

    public abstract String getMsgTexto();

    public abstract void eliminarGenerico(T registro);

    public abstract void crearGenerico(T registro);

    public abstract void actualizarGenerico(T registro);

    public abstract boolean isEmptyGenerico(T registro);

    public static void eliminarGenerico(AccionesGenerico accionesGenerico, Object registro, ResourceBundle msg) {
         String msgTitulo = ("Correcto");
        FacesMessage.Severity msgSeveridad = FacesMessage.SEVERITY_INFO;
        String msgDetalle;

        if (registro == null || accionesGenerico.isEmptyGenerico(registro)) {
            msgTitulo = ("Error");
            msgSeveridad = FacesMessage.SEVERITY_ERROR;
            msgDetalle = String.format(("No se ha seleccionado ningun registro de \"%s\" para ejecutar la acción."),(accionesGenerico.getMsgTexto()));
        } else {
            accionesGenerico.eliminarGenerico(registro);
            msgDetalle = String.format(("El registro \"%s\" ha sido eliminado correctamente."),(accionesGenerico.getMsgTexto()));
        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                msgSeveridad, msgTitulo + ": " + msgDetalle, null));
    }

    public static void aplicarCambiosGenerico(AccionesGenerico accionesGenerico, Object registro, ResourceBundle msg) {
        String msgTitulo = "Correcto";
        FacesMessage.Severity msgSeveridad = FacesMessage.SEVERITY_INFO;
        String msgDetalle;

        if (registro == null) {
            msgTitulo = ("Error");
            msgSeveridad = FacesMessage.SEVERITY_ERROR;
            msgDetalle = String.format(("No se ha seleccionado ningun registro de \"%s\" para ejecutar la acción."),(accionesGenerico.getMsgTexto()));
        } else if (accionesGenerico.isEmptyGenerico(registro)) {
            try {
                accionesGenerico.crearGenerico(registro);
                msgDetalle = String.format(("El registro \"%s\" ha sido creado correctamente."),(accionesGenerico.getMsgTexto()));
            } catch (Exception ex) {
                msgDetalle = null;
            }
        } else {
            accionesGenerico.actualizarGenerico(registro);
            msgDetalle = String.format(("El registro \"%s\" ha sido actualizado correctamente."),(accionesGenerico.getMsgTexto()));
        }
        if (msgDetalle != null && !msgDetalle.equals("")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    msgSeveridad, msgTitulo + ": " + msgDetalle, null));
        }
    }
}
