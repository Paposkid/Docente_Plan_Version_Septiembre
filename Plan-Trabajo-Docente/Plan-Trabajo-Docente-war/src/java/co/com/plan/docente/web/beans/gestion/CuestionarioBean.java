/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.plan.docente.web.beans.gestion;

import javax.annotation.PostConstruct;
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
public class CuestionarioBean {

    /**
     * Creates a new instance of CuestionarioBean
     */
    public CuestionarioBean() {
    }
    private String cedulaDocente;
    private String comisionEstudios;

    @PostConstruct
    public void inicializacion() {
        try {
            String dale = "";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String enviar() {
        String retorno = "";
        String pantallaPlan = "/secure/gestionPlan/crearPlanDeTrabajo";
        String pantallaPlanEspecial = "/secure/gestionPlan/crearPlanDeTrabajo";
        try {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.getRequestMap().put("cedulaDocente", cedulaDocente);
            if (comisionEstudios == "S") {
                retorno = pantallaPlanEspecial;
            } else {
                retorno = pantallaPlan;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pantallaPlan;
    }
//<editor-fold defaultstate="collapsed" desc="Getters & Setters">

    public String getCedulaDocente() {
        return cedulaDocente;
    }

    public void setCedulaDocente(String cedulaDocente) {
        this.cedulaDocente = cedulaDocente;
    }

    public String getComisionEstudios() {
        return comisionEstudios;
    }

    public void setComisionEstudios(String comisionEstudios) {
        this.comisionEstudios = comisionEstudios;
    }
    //</editor-fold>

}
