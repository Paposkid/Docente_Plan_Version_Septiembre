/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.plan.docente.web.util;

import java.util.Iterator;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 727855
 */
public class JSFUtils {

    static public void addMessage(Severity severity, String text) {
        addMessage(null, severity, text, null, true);
    }

    static public void addMessage(String clientId, Severity severity, String title, String msg, boolean flashRedirect) {

        // mantiene mensajes en redireccion
        if (flashRedirect) {
            FacesContext.getCurrentInstance().getExternalContext()
                    .getFlash().setKeepMessages(true);
        }
        FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(severity, title, msg));
    }

    static public void addInfo(ActionEvent actionEvent, String title, String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, title, msg));
    }

    static public void addWarn(ActionEvent actionEvent, String title, String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, title, msg));
    }

    static public void addError(ActionEvent actionEvent, String title, String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg));
    }

    static public void addFatal(ActionEvent actionEvent, String title, String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, title, msg));
    }

    public static <T> T findBean(String beanName) {
        FacesContext context = FacesContext.getCurrentInstance();
        return (T) context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "}", Object.class);
    }

    public static String getUserName() {
        return FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
    }

    public static String getServerName() {
        ServletRequest request = (ServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return request.getServerName();
    }

    public static Object getBean(String name) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, name);
    }

    public static void setBean(String name, Object value) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getApplication().getELResolver().setValue(facesContext.getELContext(), null, name, value);
    }

    public static <T extends Object> T getBean(String name, Class<T> beanClass) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return (T) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, name);
    }

    public static String getRequestParameter(String key) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);
    }

    public static Object getRequestAttribute(String key) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get(key);
    }

    public static HttpSession getSession() {
        if (FacesContext.getCurrentInstance() != null) {
            return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        } else {
            return null;
        }
    }

    public static boolean isUserInRole(String role) {
        return FacesContext.getCurrentInstance().getExternalContext().isUserInRole(role);
    }

    public static void redirect(String outcome) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getApplication().getNavigationHandler().
                handleNavigation(facesContext,
                        null,
                        outcome);
        facesContext.responseComplete();

    }

    public static void putRequestAttribute(String key, String value) {
        FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(key, value);
    }

    public static void putRequestAttribute(String key, Object value) {
        FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(key, value);
    }

    public static UIComponent findComponent(UIComponent base, String id) {
        if (id.equals(base.getId())) {
            return base;
        }

        UIComponent kid = null;
        UIComponent result = null;
        Iterator<UIComponent> kids = base.getFacetsAndChildren();
        while (kids.hasNext() && (result == null)) {
            kid = (UIComponent) kids.next();
            if (id.equals(kid.getId())) {
                result = kid;
                break;
            }
            result = findComponent(kid, id);
            if (result != null) {
                break;
            }
        }
        return result;
    }
    static final Locale defaultLocale = new Locale("es", "CO");

    public static Locale getLocale() {
        return defaultLocale;
    }
}
