/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.plan.docente.web.beans.administracion;

import co.com.plan.docente.entities.Facultad;
import co.com.plan.docente.forentities.FacultadFacadeLocal;
import co.com.plan.docente.web.util.AccionesGenerico;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

/**
 *
 * @author Jorge Montoya Jaramillo <paposkid@gmail.com>
 */
@Named
@ViewScoped
public class FacultadBean implements Serializable {

    /**
     * Creates a new instance of FacultadBean
     */
    public FacultadBean() {
    }
    private static final long serialVersionUID = 1L;

    private Facultad facultad;
    private List<Facultad> facultades;
    private AccionesGenerico<Facultad> accionesFacultad;

    @EJB
    private FacultadFacadeLocal persistenciaFacultad;
    @ManagedProperty("#{msg}")
    private ResourceBundle msg;

    public Facultad getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    public List<Facultad> getFacultades() {
        if (facultades == null) {
            facultades = persistenciaFacultad.findAll();
        }
        return facultades;
    }

    public void setFacultades(List<Facultad> facultades) {
        this.facultades = facultades;
    }

    public AccionesGenerico<Facultad> getAccionesFacultad() {
        if (this.accionesFacultad == null) {
            this.accionesFacultad = new co.com.plan.docente.web.util.AccionesGenerico<Facultad>() {
                @Override
                public void eliminarGenerico(Facultad registro) {
                    persistenciaFacultad.remove(registro);
                    limpiar();
                }

                @Override
                public void crearGenerico(Facultad registro) {
                    persistenciaFacultad.create(registro);
                    limpiar();
                }

                @Override
                public void actualizarGenerico(Facultad registro) {
                    persistenciaFacultad.edit(registro);
                    limpiar();
                }

                @Override
                public boolean isEmptyGenerico(Facultad registro) {
                    return (registro == null || registro.getDocenteCollection()== null);
                }

                @Override
                public String getMsgTexto() {
                    return "Facultad";
                }
            };
        }
        return accionesFacultad;
    }

    public void setAccionesFacultad(AccionesGenerico<Facultad> accionesFacultad) {
        this.accionesFacultad = accionesFacultad;
    }

    public ResourceBundle getMsg() {
        return msg;
    }

    public void setMsg(ResourceBundle msg) {
        this.msg = msg;
    }
    
     private void limpiar() {
        this.facultad = null;
        this.facultades = null;
    }

    public void nuevo() {
        this.facultad = new Facultad();
    }

    public void eliminar() {
        try {
            AccionesGenerico.eliminarGenerico(getAccionesFacultad(), this.facultad, msg);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void aplicarCambios() {
        try {
            AccionesGenerico.aplicarCambiosGenerico(getAccionesFacultad(), this.facultad, msg);
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ocurrió un error al guardar el registro.", ""));
        }

    }
    
    
    
}
