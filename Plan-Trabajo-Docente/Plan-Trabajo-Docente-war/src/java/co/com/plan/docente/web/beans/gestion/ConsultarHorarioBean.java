/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.plan.docente.web.beans.gestion;

import co.com.plan.docente.entities.PlanTrabajo;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Jorge Montoya
 */
@ManagedBean
@ViewScoped
public class ConsultarHorarioBean {

    /**
     * Creates a new instance of ConsultarHorarioBean
     */
    public ConsultarHorarioBean() {
    }
    private String cedulaDocente;
    private PlanTrabajo planTrabajo;
    
    public String enviar() {
        String retorno = "";
        try {
            
        } catch (Exception e) {
            
        }
        return retorno;
    }

    public String getCedulaDocente() {
        return cedulaDocente;
    }

    public void setCedulaDocente(String cedulaDocente) {
        this.cedulaDocente = cedulaDocente;
    }

    public PlanTrabajo getPlanTrabajo() {
        return planTrabajo;
    }

    public void setPlanTrabajo(PlanTrabajo planTrabajo) {
        this.planTrabajo = planTrabajo;
    }
}
