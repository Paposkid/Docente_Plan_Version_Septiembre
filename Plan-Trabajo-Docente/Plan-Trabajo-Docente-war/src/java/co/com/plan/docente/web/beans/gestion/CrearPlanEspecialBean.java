/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.plan.docente.web.beans.gestion;

import co.com.plan.docente.entities.ActExtAcademica;
import co.com.plan.docente.entities.AsesoriaProyecto;
import co.com.plan.docente.entities.ComisionEstudio;
import co.com.plan.docente.entities.Consejo;
import co.com.plan.docente.entities.Coordinador;
import co.com.plan.docente.entities.DistribucionActividad;
import co.com.plan.docente.entities.DocenciaDirecta;
import co.com.plan.docente.entities.Docente;
import co.com.plan.docente.entities.Investigacion;
import co.com.plan.docente.entities.OtraActividad;
import co.com.plan.docente.entities.Parametro;
import co.com.plan.docente.entities.PlanTrabajo;
import co.com.plan.docente.entities.Publicacion;
import co.com.plan.docente.forentities.ComisionEstudioFacadeLocal;
import co.com.plan.docente.forentities.DocenteFacadeLocal;
import co.com.plan.docente.forentities.OtraActividadFacadeLocal;
import co.com.plan.docente.forentities.ParametroFacadeLocal;
import co.com.plan.docente.forentities.PlanTrabajoFacadeLocal;
import co.com.plan.docente.vo.HorarioProfesorVO;
import co.com.plan.docente.web.util.Constantes;
import co.com.plan.docente.web.util.Util;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author Jorge Montoya
 */
@ManagedBean
@ViewScoped
public class CrearPlanEspecialBean {

    /**
     * Creates a new instance of CrearPlanEspecialBean
     */
    public CrearPlanEspecialBean() {
    }
    private PlanTrabajo planTrabajoEspecial;
    private Docente docente;
    private Parametro parametro;
    private ComisionEstudio comisionEstudio;
    private List<ComisionEstudio> listComisionEstudios;
    private Coordinador coordinador;
    private Consejo consejo;
    private OtraActividad otraActividad;
    private List<OtraActividad> listOtrasActividades;
    private int horasLegales;
    private int totalHorasPlan;
    private int totalHorasComison;
    private int totalHorasOtrasActividades;
    private String diaHorario;
    private String tipoDePlan;
    private int horadia;
    private Map<String, HorarioProfesorVO> horarioMap;
    private List<HorarioProfesorVO> listHorarioMostrar;
    private List<HorarioProfesorVO> listHorarioProfesorVO;
    private HorarioProfesorVO horarioProfesorVO;
    private boolean skip;
    private Converter horasConverter;
    private String semestreAcademico;

    //<editor-fold defaultstate="collapsed" desc="Inyecciones EJB">
    @EJB
    private ParametroFacadeLocal persistenciaParametro;
    @EJB
    private PlanTrabajoFacadeLocal persitenciaPlanEspecial;
    @EJB
    private ComisionEstudioFacadeLocal persitenciaComisionEstudio;
    @EJB
    private OtraActividadFacadeLocal persitenciaOtraActividad;

    //</editor-fold>
    @PostConstruct
    public void inicializacion() {
        try {
            Map<String, Object> reqParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestMap();
            docente = ((Docente) reqParameters.get("docente"));
            tipoDePlan = ((String) reqParameters.get("tipoPlan"));
            semestreAcademico = ((String) reqParameters.get("periodoAcademicoPlan"));
            if (parametro == null) {
                parametro = persistenciaParametro.find(new BigDecimal(Constantes.VALOR_UNO));
                horasLegales = Integer.parseInt(parametro.getParValor());
                listHorarioProfesorVO = Util.listaHorario();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido al asistente de creación de su plan de trabajo Especial.", ""));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false;
            listHorarioMostrar = new ArrayList<>();
            for (Map.Entry<String, HorarioProfesorVO> entrySet : horarioMap.entrySet()) {
                String key = entrySet.getKey();
                HorarioProfesorVO value = entrySet.getValue();
                listHorarioMostrar.add(value);
            }//reset in case user goes back
            return "confirm";
        } else {
            if (event.getNewStep().equalsIgnoreCase("tabConfirmacion")) {
                listHorarioMostrar = new ArrayList<>();
                for (Map.Entry<String, HorarioProfesorVO> entrySet : horarioMap.entrySet()) {
                    String key = entrySet.getKey();
                    HorarioProfesorVO value = entrySet.getValue();
                    listHorarioMostrar.add(value);
                }
            }
            return event.getNewStep();
        }
    }

    public String agregarComision() {
        boolean pasoCompracionGraducaion;
        boolean pasoCompracionAprobacion;
        try {
            if (horarioProfesorVO == null
                    || comisionEstudio.getCenEstudio().equals("")
                    || comisionEstudio.getNomEspecEstudio().equals("")
                    || comisionEstudio.getTipEstudio().equalsIgnoreCase("NINGUNO")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe ingresar valores en los campos diferentes a cero.", ""));
                return null;
            } else {
                pasoCompracionGraducaion = Util.compararFechas(comisionEstudio.getFecInicio(), comisionEstudio.getFecGraduacion());
                if (!pasoCompracionGraducaion) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "La fecha de graducaión no puede ser mayor a la fecha de Inicio del Estudio.", ""));
                    return null;
                }
                pasoCompracionAprobacion = Util.compararFechas(comisionEstudio.getFecObtencionAutorizacion(), comisionEstudio.getFecInicio());

                if (!pasoCompracionAprobacion) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "La Fecha Obtención de Autorización no puede ser mayor a la fecha de Inicio del Estudio.", ""));
                    return null;
                }
                comisionEstudio.setHorDedicadas(new BigInteger(horarioProfesorVO.getIntencidadSemanal() + "".trim()));
                if (llenarHorario(comisionEstudio.getNomEspecEstudio())) {
                    totalHorasComison = totalHorasComison + comisionEstudio.getHorDedicadas().intValue();
                    getListComisionEstudios().add(comisionEstudio);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Comisión de estudio agregada.", ""));
                    sumarHoras("comision", comisionEstudio.getHorDedicadas().intValue());
                    comisionEstudio = new ComisionEstudio();
                    limpiarHorario();
                } else {
                    comisionEstudio = new ComisionEstudio();
                }
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrio un Error agregando la Comisión de estudio.", ""));
            e.printStackTrace();
        }
        return null;
    }

    public String agregarOtraActividad() {

        try {
            if (horarioProfesorVO == null
                    || otraActividad.getNomActividad().equals("")
                    || otraActividad.getDesActividad().equals("")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe ingresar valores en los campos diferentes a cero.", ""));
                return null;
            } else {
                otraActividad.setHorDedicado(new BigInteger(horarioProfesorVO.getIntencidadSemanal() + "".trim()));
                if (llenarHorario(otraActividad.getNomActividad())) {
                    totalHorasOtrasActividades = totalHorasOtrasActividades + otraActividad.getHorDedicado().intValue();
                    getListOtrasActividades().add(otraActividad);
                    sumarHoras("tabOtras", otraActividad.getHorDedicado().intValue());
                    otraActividad = new OtraActividad();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Otra Actividad agregada.", ""));
                    limpiarHorario();
                } else {
                    otraActividad = new OtraActividad();
                }
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrio un Error agregando Otra Actividad.", ""));
            e.printStackTrace();
        }
        return null;
    }

    public boolean llenarHorario(String nombreActividad) {
        boolean retono = false;
        HorarioProfesorVO valorAnterior = null;
        if (horarioMap == null) {
            horarioMap = new HashMap<>();
        }
        try {
            horarioProfesorVO.setNombreMateria(nombreActividad);
            valorAnterior = horarioMap.get(horarioProfesorVO.getDiaHora());
            if (valorAnterior != null && valorAnterior.getDiaHora().equalsIgnoreCase(horarioProfesorVO.getDiaHora())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No puede seleccionar ese hoario ya lo tomo.", ""));
            } else {
                horarioMap.put(horarioProfesorVO.getDiaHora(), horarioProfesorVO);
                retono = true;
            }
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No puede seleccionar ese hoario ya lo tomo.", ""));
            ex.printStackTrace();
        }
        return retono;
    }

    public void limpiarHorario() {
        horarioProfesorVO = new HorarioProfesorVO();
    }

    public void eliminarComision(ComisionEstudio comisionEstudio) {
        try {
            totalHorasComison = totalHorasComison - comisionEstudio.getHorDedicadas().intValue();
            listComisionEstudios.remove(comisionEstudio);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Comision eliminada.", ""));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarOtrasActividades(OtraActividad otraActividad) {
        try {
            totalHorasOtrasActividades = totalHorasOtrasActividades - otraActividad.getHorDedicado().intValue();
            listOtrasActividades.remove(otraActividad);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Otra Actividad eliminada.", ""));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String sumarHoras(String evento, int intensidad) {
        try {
            if (evento.equalsIgnoreCase("comision")) {
                totalHorasPlan = totalHorasPlan + intensidad;
            } else if (evento.equalsIgnoreCase("tabOtras")) {
                totalHorasPlan = totalHorasPlan + intensidad;
            }
            if (totalHorasPlan > horasLegales) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Ha superado las " + totalHorasPlan + " horas legales semanales.", ""));
                return null;
            } else if (totalHorasPlan == horasLegales) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Acaba de completar : " + totalHorasPlan + " horas legales semanales.", ""));
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String guardar() {

        try {
            planTrabajoEspecial = new PlanTrabajo();
            planTrabajoEspecial.setFecPlanTrabajo(new Date());
            planTrabajoEspecial.setEstado("0");
            planTrabajoEspecial.setTipoPlan(tipoDePlan);
            planTrabajoEspecial.setCodDocente(docente);
            planTrabajoEspecial.setPeriodoAcademico(semestreAcademico);
            persitenciaPlanEspecial.create(planTrabajoEspecial);
            for (ComisionEstudio comiAux : listComisionEstudios) {
                comiAux.setCodPlanTrabajo(planTrabajoEspecial);
                persitenciaComisionEstudio.create(comiAux);
            }
            for (OtraActividad otraAux : listOtrasActividades) {
                otraAux.setCodPlanTrabajo(planTrabajoEspecial);
                persitenciaOtraActividad.create(otraAux);
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "El Plan de Trabajo Especial se guardo.", ""));

            // }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrio un Error guardando el Plan de Trabajo Especial.", ""));
            e.printStackTrace();
        }
        return "secure/inicio";
    }

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">

    public String getSemestreAcademico() {
        return semestreAcademico;
    }

    public void setSemestreAcademico(String semestreAcademico) {
        this.semestreAcademico = semestreAcademico;
    }
    
    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public String getTipoDePlan() {
        return tipoDePlan;
    }

    public void setTipoDePlan(String tipoDePlan) {
        this.tipoDePlan = tipoDePlan;
    }

    public OtraActividad getOtraActividad() {
        if (otraActividad == null) {
            otraActividad = new OtraActividad();
        }
        return otraActividad;
    }

    public void setOtraActividad(OtraActividad otraActividad) {
        this.otraActividad = otraActividad;
    }

    public int getTotalHorasPlan() {
        return totalHorasPlan;
    }

    public void setTotalHorasPlan(int totalHorasPlan) {
        this.totalHorasPlan = totalHorasPlan;
    }

    public int getTotalHorasComison() {
        return totalHorasComison;
    }

    public void setTotalHorasComison(int totalHorasComison) {
        this.totalHorasComison = totalHorasComison;
    }

    public int getTotalHorasOtrasActividades() {
        return totalHorasOtrasActividades;
    }

    public void setTotalHorasOtrasActividades(int totalHorasOtrasActividades) {
        this.totalHorasOtrasActividades = totalHorasOtrasActividades;
    }

    public String getDiaHorario() {
        return diaHorario;
    }

    public void setDiaHorario(String diaHorario) {
        this.diaHorario = diaHorario;
    }

    public int getHoradia() {
        return horadia;
    }

    public void setHoradia(int horadia) {
        this.horadia = horadia;
    }

    public Map<String, HorarioProfesorVO> getHorarioMap() {
        if (horarioMap == null) {
            horarioMap = new HashMap<>();
        }
        return horarioMap;
    }

    public void setHorarioMap(Map<String, HorarioProfesorVO> horarioMap) {
        this.horarioMap = horarioMap;
    }

    public List<HorarioProfesorVO> getListHorarioMostrar() {
        if (listHorarioMostrar == null) {
            listHorarioMostrar = new ArrayList<>();
        }
        return listHorarioMostrar;
    }

    public void setListHorarioMostrar(List<HorarioProfesorVO> listHorarioMostrar) {
        this.listHorarioMostrar = listHorarioMostrar;
    }

    public HorarioProfesorVO getHorarioProfesorVO() {
        return horarioProfesorVO;
    }

    public void setHorarioProfesorVO(HorarioProfesorVO horarioProfesorVO) {
        this.horarioProfesorVO = horarioProfesorVO;
    }

    public PlanTrabajo getPlanTrabajoEspecial() {
        if (planTrabajoEspecial == null) {
            planTrabajoEspecial = new PlanTrabajo();
        }
        return planTrabajoEspecial;
    }

    public void setPlanTrabajoEspecial(PlanTrabajo planTrabajoEspecial) {
        this.planTrabajoEspecial = planTrabajoEspecial;
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

    public Parametro getParametro() {
        if (parametro == null) {
            parametro = new Parametro();
        }
        return parametro;
    }

    public void setParametro(Parametro parametro) {
        this.parametro = parametro;
    }

    public int getHorasLegales() {
        return horasLegales;
    }

    public void setHorasLegales(int horasLegales) {
        this.horasLegales = horasLegales;
    }

    public ComisionEstudio getComisionEstudio() {
        if (comisionEstudio == null) {
            comisionEstudio = new ComisionEstudio();
        }
        return comisionEstudio;
    }

    public void setComisionEstudio(ComisionEstudio comisionEstudio) {
        this.comisionEstudio = comisionEstudio;
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

    public Coordinador getCoordinador() {
        if (coordinador == null) {
            coordinador = new Coordinador();
        }
        return coordinador;
    }

    public void setCoordinador(Coordinador coordinador) {
        this.coordinador = coordinador;
    }

    public Consejo getConsejo() {
        if (consejo == null) {
            consejo = new Consejo();
        }
        return consejo;
    }

    public void setConsejo(Consejo consejo) {
        this.consejo = consejo;
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

    public Converter getHorasConverter() {
        if (this.horasConverter == null) {
            this.horasConverter = new Converter() {

                @Override
                public Object getAsObject(FacesContext context, UIComponent component, String value) {
                    HorarioProfesorVO retorno = null;

                    if (value != null && !value.isEmpty()
                            && !value.trim().equals("[TODOS]")
                            && !value.trim().equals("[NINGUNO]")) {
                        String dale = value;
                        for (HorarioProfesorVO horario : getListHorarioProfesorVO()) {
                            if (horario.getDiaHora().compareTo(dale) == 0) {
                                retorno = horario;
                                break;
                            }
                        }
                    }

                    return retorno;
                }

                @Override
                public String getAsString(FacesContext context, UIComponent component, Object value) {
                    String retorno = null;

                    if (value != null && value instanceof HorarioProfesorVO) {
                        retorno = ((HorarioProfesorVO) value).getDiaHora();
                    }

                    return retorno;
                }
            };
        }
        return horasConverter;
    }

    public void setHorasConverter(Converter horasConverter) {
        this.horasConverter = horasConverter;
    }

    public List<HorarioProfesorVO> getListHorarioProfesorVO() {
        if (listHorarioProfesorVO == null) {
            listHorarioProfesorVO = new ArrayList<>();
        }
        return listHorarioProfesorVO;
    }

    public void setListHorarioProfesorVO(List<HorarioProfesorVO> listHorarioProfesorVO) {
        this.listHorarioProfesorVO = listHorarioProfesorVO;
    }
    //</editor-fold>
}
