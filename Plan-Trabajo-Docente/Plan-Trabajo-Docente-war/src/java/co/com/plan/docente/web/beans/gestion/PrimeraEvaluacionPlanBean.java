/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.plan.docente.web.beans.gestion;

import co.com.plan.docente.entities.ActExtAcademica;
import co.com.plan.docente.entities.AsesoriaProyecto;
import co.com.plan.docente.entities.ComisionEstudio;
import co.com.plan.docente.entities.Coordinador;
import co.com.plan.docente.entities.DocenciaDirecta;
import co.com.plan.docente.entities.Docente;
import co.com.plan.docente.entities.Investigacion;
import co.com.plan.docente.entities.OtraActividad;
import co.com.plan.docente.entities.PlanTrabajo;
import co.com.plan.docente.entities.Publicacion;
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
import co.com.plan.docente.vo.HorarioProfesorVO;
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
import org.primefaces.event.FlowEvent;

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
    private List<DocenciaDirecta> listDocenciaDirecta;
    private List<Investigacion> investigaciones;
    private List<OtraActividad> listOtrasActividades;
    private List<AsesoriaProyecto> listAsesoriaProyectos;
    private List<Publicacion> listPublicaciones;
    private List<ComisionEstudio> listComisionEstudios;
    private List<ActExtAcademica> listActExtAcademicas;
    private boolean skip;

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

    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false;
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    public String evaluar() {

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
        try {
            listDocenciaDirecta = new ArrayList();
            investigaciones = new ArrayList();
            listActExtAcademicas = new ArrayList();
            listAsesoriaProyectos = new ArrayList();
            listComisionEstudios = new ArrayList();
            listOtrasActividades = new ArrayList();
            listPublicaciones = new ArrayList();
            if (planTrabajo != null) {
                listDocenciaDirecta.addAll(planTrabajo.getDocenciaDirectaCollection());
                investigaciones.addAll(planTrabajo.getInvestigacionCollection());
                listActExtAcademicas.addAll(planTrabajo.getActExtAcademicaCollection());
                listAsesoriaProyectos.addAll(planTrabajo.getAsesoriaProyectoCollection());
                listComisionEstudios.addAll(planTrabajo.getComisionEstudioCollection());
                listOtrasActividades.addAll(planTrabajo.getOtraActividadCollection());
                listPublicaciones.addAll(planTrabajo.getPublicacionCollection());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public List<DocenciaDirecta> getListDocenciaDirecta() {
        if (listDocenciaDirecta == null) {
            listDocenciaDirecta = new ArrayList<>();
        }
        return listDocenciaDirecta;
    }

    public void setListDocenciaDirecta(List<DocenciaDirecta> listDocenciaDirecta) {
        this.listDocenciaDirecta = listDocenciaDirecta;
    }

    public List<Investigacion> getInvestigaciones() {
        if (investigaciones == null) {
            investigaciones = new ArrayList<>();
        }
        return investigaciones;
    }

    public void setInvestigaciones(List<Investigacion> investigaciones) {
        this.investigaciones = investigaciones;
    }

    public List<ActExtAcademica> getListActExtAcademicas() {
        if (listActExtAcademicas == null) {
            listActExtAcademicas = new ArrayList<>();
        }
        return listActExtAcademicas;
    }

    public void setListActExtAcademicas(List<ActExtAcademica> listActExtAcademicas) {
        this.listActExtAcademicas = listActExtAcademicas;
    }

    public List<ComisionEstudio> getListComisionEstudios() {
        if (listComisionEstudios == null) {
            listComisionEstudios = new ArrayList<>();
        }
        return listComisionEstudios;
    }

    public void setListComisionEstudios(List<ComisionEstudio> listComisionEstudios) {
        this.listComisionEstudios = listComisionEstudios;
    }

    public List<Publicacion> getListPublicaciones() {
        if (listPublicaciones == null) {
            listPublicaciones = new ArrayList<>();
        }
        return listPublicaciones;
    }

    public void setListPublicaciones(List<Publicacion> listPublicaciones) {
        this.listPublicaciones = listPublicaciones;
    }

    public List<AsesoriaProyecto> getListAsesoriaProyectos() {
        if (listAsesoriaProyectos == null) {
            listAsesoriaProyectos = new ArrayList<>();
        }
        return listAsesoriaProyectos;
    }

    public void setListAsesoriaProyectos(List<AsesoriaProyecto> listAsesoriaProyectos) {
        this.listAsesoriaProyectos = listAsesoriaProyectos;
    }

    public List<OtraActividad> getListOtrasActividades() {
        if (listOtrasActividades == null) {
            listOtrasActividades = new ArrayList<>();
        }
        return listOtrasActividades;
    }

    public void setListOtrasActividades(List<OtraActividad> listOtrasActividades) {
        this.listOtrasActividades = listOtrasActividades;
    }

    //</editor-fold>
}
