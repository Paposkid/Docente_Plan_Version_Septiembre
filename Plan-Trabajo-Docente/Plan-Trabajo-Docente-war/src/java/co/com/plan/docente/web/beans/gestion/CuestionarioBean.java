/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.plan.docente.web.beans.gestion;

import co.com.plan.docente.entities.Docente;
import co.com.plan.docente.entities.PlanTrabajo;
import co.com.plan.docente.forentities.DocenteFacadeLocal;
import co.com.plan.docente.forentities.PlanTrabajoFacadeLocal;
import co.com.plan.docente.web.util.EnvioCorreo;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    private String periodoAcademicoPlan;
    private String doc;
    private Docente docente;
    private PlanTrabajo planTrabajo;

    @EJB
    private DocenteFacadeLocal persistenciaDocente;
    @EJB
    private PlanTrabajoFacadeLocal persitenciaPlan;

    public String enviar() {
        String retorno = "";
        String pantallaPlan = "/secure/gestionPlan/crearPlanDeTrabajo";
        String pantallaPlanEspecial = "/secure/gestionPlan/crearPlanDeTrabajoEspecial";
        String pantallaPlanEspecialMedio = "/secure/gestionPlan/crearPlanDeTrabajoEspecialMedio";
        EnvioCorreo correo = new EnvioCorreo();
        try {
            correo.enviarCorreo("paposkid@gmail.com","dimmunile1349","papometal85@hotmail.com", "Plan trabajo", "Prueba correo Plan. todo oki.");
            periodoAcademicoPlan = setearPeriodoAcademico();
            docente = persistenciaDocente.findByCedula(cedulaDocente);
            if (docente != null) {
                planTrabajo = persitenciaPlan.findPlanByPeriodoAndDocente(periodoAcademicoPlan, docente);
                if (planTrabajo != null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "El docente ya tiene un plan plan de trabajo para el semestre: " + periodoAcademicoPlan, ""));
                    return retorno;
                }
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
            ec.getRequestMap().put("periodoAcademicoPlan", periodoAcademicoPlan);
            
            if (tipoPlan.equalsIgnoreCase("C")) {
                retorno = pantallaPlanEspecial;
            } else if (tipoPlan.equalsIgnoreCase("M")) {
                retorno = pantallaPlanEspecialMedio;
            } else {
                retorno = pantallaPlan;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retorno;
    }

    public String setearPeriodoAcademico() {
        String periodoAcademico = "";
        Calendar cal = Calendar.getInstance();
        String semestre = "";
        try {
            semestre = setearSemestre(cal.get(Calendar.MONTH));
            periodoAcademico = cal.get(Calendar.YEAR) + "-" + semestre;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return periodoAcademico;
    }

    public String setearSemestre(int mes) {
        String retorno = "";
        try {
            if (mes <= 6) {
                retorno = "1";
            } else {
                retorno = "2";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retorno;
    }

//<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public PlanTrabajo getPlanTrabajo() {
        return planTrabajo;
    }

    public void setPlanTrabajo(PlanTrabajo planTrabajo) {
        this.planTrabajo = planTrabajo;
    }

    public String getPeriodoAcademicoPlan() {
        return periodoAcademicoPlan;
    }

    public void setPeriodoAcademicoPlan(String periodoAcademicoPlan) {
        this.periodoAcademicoPlan = periodoAcademicoPlan;
    }

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
