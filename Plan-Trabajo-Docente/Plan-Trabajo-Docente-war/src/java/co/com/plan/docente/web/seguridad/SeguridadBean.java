/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.plan.docente.web.seguridad;

import co.com.plan.docente.entities.Usuario;
import co.com.plan.docente.forentities.UsuarioFacadeLocal;
import co.com.plan.docente.web.util.JSFUtils;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author 727855
 */
@ManagedBean
@SessionScoped
public class SeguridadBean {

    /**
     * Creates a new instance of SeguridadBean
     */
    public SeguridadBean() {
    }
    private static final long serialVersionUID = 1L;
    public static final String AUTH_KEY = "plan.user.name";
    @Size(max = 32)
    @NotNull
    private String usuNombre;
    @Size(max = 32)
    @NotNull
    private String usuContrasena;
    private Usuario usuario;

    @EJB
    private UsuarioFacadeLocal persistenciaUsuario;

    public String getUsuNombre() {
        return usuNombre;
    }

    public void setUsuNombre(String usuNombre) {
        this.usuNombre = usuNombre;
    }

    public String getUsuContrasena() {
        return usuContrasena;
    }

    public void setUsuContrasena(String usuContrasena) {
        this.usuContrasena = usuContrasena;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Inicia Sesión.
     * <p>
     * Devuelve mensaje de error si no encuentra el usuario por los parámetros
     * dados.<br/>
     * Carga el usuario en sesión con los permisos respectivos del rol
     * ({@link co.com.claro.ejb.entities.Opcion}).<br/>
     * Transfiere a la ventana de inicio si inició sesión correctamente.
     * </p>
     *
     * @return
     */
    public String autenticar() {
        try {
            String retorno = null;
            //String estadoUsuario = this.seguridadFacade.obtenerEstadoDeUsuario(usuNombre);
            this.usuario = this.persistenciaUsuario.iniciarSession(usuNombre, usuContrasena);
            if (usuario != null) {
                //this.base = this.persistenciaUsuario.obtenerBaseDeUsuario(usuario);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(AUTH_KEY, this);
                setUsuNombre(usuario.getNombreUsuario());
                return "/secure/inicio?faces-redirect=true";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: Usuario y/o Contraseña no válido.", ""));
            }
            return retorno;
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ha ocurrido Error y no ha sido posible iniciar sesión en este momento por favor intente nuevamente", null));
            return null;
        }
    }

    /**
     * Cierra la sesión.
     * <p>
     * Elimina también el usuario en sesión.</p>
     *
     * @return transfiere a login
     */
    public String cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(AUTH_KEY);
        this.usuario = null;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login?faces-redirect=true";
    }

    /**
     * Valida si tiene una opción dada entre las opciones del usuario en sesión.
     *
     * @param opcId
     * @return verdadero si tiene la opción.
     */
    public boolean tieneOpcion(int opcId) {
        boolean retorno = false;
        if (usuario != null) {
           if (usuario.getCargoUsuario().getCodigoCargo().compareTo(BigDecimal.ONE) == 0) {
                retorno = true;
            }
        }
        return retorno;
    }

    public String actualizarClave() {
        persistenciaUsuario.actualizarClave(usuNombre, usuContrasena);
        JSFUtils.addMessage(FacesMessage.SEVERITY_INFO, "La contraseña ha sido actualizada correctamente.");
        return "/secure/inicio?faces-redirect=true";
    }
}
