/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.plan.docente.web.beans.administracion;

import co.com.plan.docente.entities.Docente;
import co.com.plan.docente.entities.Facultad;
import co.com.plan.docente.entities.Usuario;
import co.com.plan.docente.forentities.DocenteFacadeLocal;
import co.com.plan.docente.forentities.FacultadFacadeLocal;
import co.com.plan.docente.forentities.UsuarioFacadeLocal;
import co.com.plan.docente.web.util.AccionesGenerico;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.faces.convert.Converter;

/**
 *
 * @author Jorge Montoya Jaramillo <paposkid@gmail.com>
 */
@Named
@ViewScoped
public class DocenteBean implements Serializable {

    /**
     * Creates a new instance of DocenteBean
     */
    public DocenteBean() {
    }
    private static final long serialVersionUID = 1L;

    private Docente docente;
    private List<Docente> docentes;
    List<Facultad> facultades;
    Usuario usuario;
    private AccionesGenerico<Docente> accionesDocente;
    private Converter facultadConverter;
    private boolean mostar;

    @EJB
    private DocenteFacadeLocal persistenciaDocente;
    @EJB
    private FacultadFacadeLocal persistenciaFacultad;
    @EJB
    private UsuarioFacadeLocal persistenciaUsuario;
    @ManagedProperty("#{msg}")
    private ResourceBundle msg;

    public boolean isMostar() {
        return mostar;
    }

    public void setMostar(boolean mostar) {
        this.mostar = mostar;
    }

    public Docente getDocente() {
        return docente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Facultad> getFacultades() {
        if (facultades == null) {
            facultades = persistenciaFacultad.findActivas();
        }
        return facultades;
    }

    public void setFacultades(List<Facultad> facultades) {
        this.facultades = facultades;
    }

    public Converter getFacultadConverter() {
        if (this.facultadConverter == null) {
            this.facultadConverter = new Converter() {

                @Override
                public Object getAsObject(FacesContext context, UIComponent component, String value) {
                    Facultad retorno = null;

                    if (value != null && !value.isEmpty()
                            && !value.trim().equals("[TODOS]")
                            && !value.trim().equals("[NINGUNO]")) {
                        BigDecimal faculId = new BigDecimal(value);
                        for (Facultad fac : getFacultades()) {
                            if (fac.getCodFacultad().compareTo(faculId) == 0) {
                                retorno = fac;
                                break;
                            }
                        }
                    }

                    return retorno;
                }

                @Override
                public String getAsString(FacesContext context, UIComponent component, Object value) {
                    String retorno = null;

                    if (value != null && value instanceof Facultad) {
                        retorno = ((Facultad) value).getCodFacultad().toString();
                    }

                    return retorno;
                }
            };
        }
        return facultadConverter;
    }

    public void setFacultadConverter(Converter facultadConverter) {
        this.facultadConverter = facultadConverter;
    }

    public void setDocente(Docente docente) {
        if (docente != null) {
             mostar = true;
        }
        this.docente = docente;
    }

    public List<Docente> getDocentes() {
        if (docentes == null) {
            docentes = persistenciaDocente.findAll();
        }
        return docentes;
    }

    public void setDocentes(List<Docente> docentes) {
        this.docentes = docentes;
    }

    public AccionesGenerico<Docente> getAccionesDocente() {
        if (this.accionesDocente == null) {
            this.accionesDocente = new co.com.plan.docente.web.util.AccionesGenerico<Docente>() {
                @Override
                public void eliminarGenerico(Docente registro) {
                    persistenciaDocente.remove(registro);
                    limpiar();
                }

                @Override
                public void crearGenerico(Docente registro) {
                    persistenciaDocente.create(registro);
                    limpiar();
                }

                @Override
                public void actualizarGenerico(Docente registro) {
                    persistenciaDocente.edit(registro);
                    limpiar();
                }

                @Override
                public boolean isEmptyGenerico(Docente registro) {
                    return (registro == null || registro.getCodDocente() == null);
                }

                @Override
                public String getMsgTexto() {
                    return "Docente";
                }
            };
        }
        return accionesDocente;
    }

    public void setAccionesDocente(AccionesGenerico<Docente> accionesDocente) {
        this.accionesDocente = accionesDocente;
    }

    public ResourceBundle getMsg() {
        return msg;
    }

    public void setMsg(ResourceBundle msg) {
        this.msg = msg;
    }

    private void limpiar() {
        this.docente = null;
        this.docentes = null;
    }

    public void nuevoDocente() {
        this.docente = new Docente();
    }

    public void eliminar() {
        try {
            AccionesGenerico.eliminarGenerico(getAccionesDocente(), this.docente, msg);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void aplicarCambiosDocente() {
        try {
            AccionesGenerico.aplicarCambiosGenerico(getAccionesDocente(), this.docente, msg);
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ocurrió un error al guardar el registro.", ""));
        }

    }

    public void consultarUsuario() {

        try {
            usuario = persistenciaUsuario.findUsuariosActivos(new BigInteger(docente.getCedDocente()));
            if (usuario != null && usuario.getCargoUsuario().getNombreCargo().equalsIgnoreCase("DOCENTE")) {
                if (usuario.getCorreoUsuario() != null) {
                    this.docente.setCorDocente(usuario.getCorreoUsuario());
                    this.docente.setNomDocente(usuario.getNombreUsuario()+" "+ usuario.getApellidoUsuario());
                    mostar = true;
                } else {
                    mostar = false;
                }

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "El documento ingresado no existe, está inactivo ó no es un docente.", ""));
            }
        } catch (Exception e) {
            // LoggerFacade.registerError(e.getMessage(), e);
            mostar = false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrio un error consultando la información.", ""));
        }
    }

}
