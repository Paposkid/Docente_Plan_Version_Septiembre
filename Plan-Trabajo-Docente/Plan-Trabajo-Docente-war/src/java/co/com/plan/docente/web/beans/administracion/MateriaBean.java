/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.plan.docente.web.beans.administracion;

import co.com.plan.docente.entities.Materia;
import co.com.plan.docente.forentities.MateriaFacadeLocal;
import co.com.plan.docente.web.util.AccionesGenerico;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author Jorge Montoya Jaramillo <paposkid@gmail.com>
 */
@Named
@ViewScoped
public class MateriaBean implements Serializable {

    /**
     * Creates a new instance of MateriaBean
     */
    public MateriaBean() {
    }

    private static final long serialVersionUID = 1L;

    private Materia materia;
    private List<Materia> materias;
    private AccionesGenerico<Materia> accionesMateria;

    @EJB
    private MateriaFacadeLocal persistenciaMateria;
    @ManagedProperty("#{msg}")
    private ResourceBundle msg;

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public List<Materia> getMaterias() {
        if (materias == null) {
            materias = persistenciaMateria.findAll();
        }
        return materias;
    }

    public void setMaterias(List<Materia> materias) {
        this.materias = materias;
    }

    public AccionesGenerico<Materia> getAccionesMateria() {
        if (this.accionesMateria == null) {
            this.accionesMateria = new co.com.plan.docente.web.util.AccionesGenerico<Materia>() {
                @Override
                public void eliminarGenerico(Materia registro) {
                    persistenciaMateria.remove(registro);
                    limpiar();
                }

                @Override
                public void crearGenerico(Materia registro) {
                    persistenciaMateria.create(registro);
                    limpiar();
                }

                @Override
                public void actualizarGenerico(Materia registro) {
                    persistenciaMateria.edit(registro);
                    limpiar();
                }

                @Override
                public boolean isEmptyGenerico(Materia registro) {
                    return (registro == null || registro.getCodMateria() == null);
                }

                @Override
                public String getMsgTexto() {
                    return "Materia";
                }
            };
        }
        return accionesMateria;
    }

    public void setAccionesMateria(AccionesGenerico<Materia> accionesMateria) {
        this.accionesMateria = accionesMateria;
    }

    private void limpiar() {
        this.materia = null;
        this.materias = null;
    }

    public void nuevoMateria() {
        this.materia = new Materia();
    }

    public void eliminar() {
        try {
            AccionesGenerico.eliminarGenerico(getAccionesMateria(), this.materia, msg);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void aplicarCambiosMateria() {
        try {
            AccionesGenerico.aplicarCambiosGenerico(getAccionesMateria(), this.materia, msg);
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ocurrió un error al guardar el registro.", ""));
        }

    }
}
