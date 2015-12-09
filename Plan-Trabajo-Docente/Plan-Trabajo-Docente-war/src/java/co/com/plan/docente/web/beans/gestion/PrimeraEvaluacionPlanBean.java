/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.plan.docente.web.beans.gestion;

import co.com.plan.docente.entities.Coordinador;
import co.com.plan.docente.entities.Docente;
import co.com.plan.docente.entities.PlanTrabajo;
import co.com.plan.docente.forentities.ActExtAcademicaFacadeLocal;
import co.com.plan.docente.forentities.AsesoriaProyectoFacadeLocal;
import co.com.plan.docente.forentities.ComisionEstudioFacadeLocal;
import co.com.plan.docente.forentities.CoordinadorFacadeLocal;
import co.com.plan.docente.forentities.DistribucionActividadFacadeLocal;
import co.com.plan.docente.forentities.DocenciaDirectaFacadeLocal;
import co.com.plan.docente.forentities.DocenteFacadeLocal;
import co.com.plan.docente.forentities.FacultadFacadeLocal;
import co.com.plan.docente.forentities.InvestigacionFacadeLocal;
import co.com.plan.docente.forentities.MateriaFacadeLocal;
import co.com.plan.docente.forentities.OtraActividadFacadeLocal;
import co.com.plan.docente.forentities.ParametroFacadeLocal;
import co.com.plan.docente.forentities.PlanTrabajoFacadeLocal;
import co.com.plan.docente.forentities.PublicacionFacadeLocal;
import co.com.plan.docente.forentities.UsuarioFacadeLocal;
import co.com.plan.docente.web.util.Constantes;
import co.com.plan.docente.web.util.Util;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Jorge Montoya
 */
@ManagedBean
@ViewScoped
public class PrimeraEvaluacionPlanBean {

    /**
     * Creates a new instance of EvaluarPlanBean
     */
    public PrimeraEvaluacionPlanBean() {
    }
    private PlanTrabajo planTrabajo;
    private List<PlanTrabajo> planes;

    //<editor-fold defaultstate="collapsed" desc="Inyecciones EJB">
    @EJB
    private PlanTrabajoFacadeLocal persitenciaPlan;

    //</editor-fold>
    @PostConstruct
    public void inicializacion() {
        try {
            planes = persitenciaPlan.findAllByEstado("0");
            if (planes.isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No hay planes a evaluar.", ""));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Consulta Exitosa.", ""));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String evaluar(){
        
        try {
            
        } catch (Exception e) {
        }
        return null;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public PlanTrabajo getPlanTrabajo() {
        if (planTrabajo == null) {
            planTrabajo = new PlanTrabajo();
        }
        return planTrabajo;
    }

    public void setPlanTrabajo(PlanTrabajo planTrabajo) {
        this.planTrabajo = planTrabajo;
    }

    public List<PlanTrabajo> getPlanes() {
        if (planes == null) {
            planes = new ArrayList<>();
        }
        return planes;
    }

    public void setPlanes(List<PlanTrabajo> planes) {
        this.planes = planes;
    }
    //</editor-fold>

}
