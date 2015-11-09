/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.plan.docente.web.beans.gestion;

import co.com.plan.docente.entities.Docente;
import co.com.plan.docente.forentities.DocenteFacadeLocal;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author Jorge Montoya
 */
@ManagedBean
@ViewScoped
public class CuestionarioBean {

    /**
     * Creates a new instance of CuestionarioBean
     */
    public CuestionarioBean() {
    }
    private String cedulaDocente;
    private String tipoPlan;
    private String doc;
    private Docente docente;

    @EJB
    private DocenteFacadeLocal persistenciaDocente;

    public String enviar() {
        String retorno = "";
        String pantallaPlan = "/secure/gestionPlan/crearPlanDeTrabajo";
        String pantallaPlanEspecial = "/secure/gestionPlan/crearPlanDeTrabajoEspecial";
        String pantallaPlanEspecialMedio = "/secure/gestionPlan/crearPlanDeTrabajoEspecialMedio";
        try {
            docente = persistenciaDocente.findByCedula(cedulaDocente);
            if (docente != null) {
                //consultarsi tiene un plan para este semestre o periodo
            } else {
            }
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "El docente no existe o está inactivo.", ""));
            return retorno;
        }
        try {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.getRequestMap().put("docente", docente);
            ec.getRequestMap().put("tipoPlan", tipoPlan);
            if (tipoPlan.equalsIgnoreCase("C")) {
                retorno = pantallaPlanEspecial;
            } else if(tipoPlan.equalsIgnoreCase("M")){
                retorno = pantallaPlanEspecialMedio;
            }else {
                retorno = pantallaPlan;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retorno;
    }

//<editor-fold defaultstate="collapsed" desc="Getters & Setters">

    public String getCedulaDocente() {
        return cedulaDocente;
    }

    public void setCedulaDocente(String cedulaDocente) {
        this.cedulaDocente = cedulaDocente;
    }

    public String getTipoPlan() {
        return tipoPlan;
    }

    public void setTipoPlan(String tipoPlan) {
        this.tipoPlan = tipoPlan;
    }

    public Docente getDocente() {
        if (docente == null) {
            docente = new Docente();
        }
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    //</editor-fold>
}
