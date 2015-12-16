/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.plan.docente.web.beans.gestion;

import co.com.plan.docente.entities.ActExtAcademica;
import co.com.plan.docente.entities.Actividad;
import co.com.plan.docente.entities.AsesoriaProyecto;
import co.com.plan.docente.entities.ComisionEstudio;
import co.com.plan.docente.entities.Consejo;
import co.com.plan.docente.entities.Coordinador;
import co.com.plan.docente.entities.DistribucionActividad;
import co.com.plan.docente.entities.DocenciaDirecta;
import co.com.plan.docente.entities.Docente;
import co.com.plan.docente.entities.Facultad;
import co.com.plan.docente.entities.Investigacion;
import co.com.plan.docente.entities.Materia;
import co.com.plan.docente.entities.OtraActividad;
import co.com.plan.docente.entities.Parametro;
import co.com.plan.docente.entities.PlanTrabajo;
import co.com.plan.docente.entities.Publicacion;
import co.com.plan.docente.entities.Usuario;
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
import co.com.plan.docente.web.seguridad.SeguridadBean;
import co.com.plan.docente.web.util.Constantes;
import co.com.plan.docente.web.util.Util;
import javax.faces.application.FacesMessage;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.primefaces.event.FlowEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author 727855
 */
@ManagedBean
@ViewScoped
public class CrearPlanBean {

    /**
     * Creates a new instance of CrearPlanBean
     */
    public CrearPlanBean() {
    }
    private static final long serialVersionUID = 1L;

    private PlanTrabajo planTrabajo;
    private Docente docente;
    private Coordinador coordinador;
    private List<Docente> docentes;
    private Materia materia;
    private List<Materia> materias;
    private ActExtAcademica actExtAcademica;
    private List<ActExtAcademica> listActExtAcademicas;
    private Actividad actividad;
    private List<Actividad> Actividades;
    private Investigacion investigacion;
    private List<Investigacion> investigaciones;
    private AsesoriaProyecto asesoriaProyecto;
    private List<AsesoriaProyecto> listAsesoriaProyectos;
    private ComisionEstudio comisionEstudio;
    private List<ComisionEstudio> listComisionEstudios;
    private Consejo consejo;
    private List<Consejo> listConsejos;
    private DistribucionActividad distribucionActividad;
    private List<DistribucionActividad> listDistribucionActividades;
    private DocenciaDirecta docenciaDirecta;
    private List<DocenciaDirecta> listDocenciaDirecta;
    private Facultad facultad;
    private List<Facultad> facultades;
    private Publicacion publicacion;
    private List<Publicacion> listPublicaciones;
    private OtraActividad otraActividad;
    private List<OtraActividad> listOtrasActividades;
    private Usuario usuario;
    private Parametro parametro;
    private List<HorarioProfesorVO> listHorarioProfesorVO;
    private HorarioProfesorVO horarioProfesorVO;
    private Converter facultadConverter;
    private Converter materiaConverter;
    private Converter horasConverter;
    private boolean mostar;
    private boolean skip;
    private String codigoMateria;
    private int horasLegales;
    private int totalHorasPlan;
    private int totalHorasDocenciaDirecta;
    private int totalHorasComison;
    private int totalHorasInvestigacion;
    private int totalHorasExtesion;
    private int totalHorasPublicaciones;
    private int totalHorasAsesorias;
    private int totalHorasOtrasActividades;
    private String diaHorario;
    private int horadia;
    private Map<String, HorarioProfesorVO> horarioMap;
    private List<HorarioProfesorVO> listHorarioMostrar;
    private String tipoDePlan;
    private String semestreAcademico;

//<editor-fold defaultstate="collapsed" desc="Inyecciones EJB">
    @EJB
    private DocenteFacadeLocal persistenciaDocente;
    @EJB
    private FacultadFacadeLocal persistenciaFacultad;
    @EJB
    private UsuarioFacadeLocal persistenciaUsuario;
    @EJB
    private DocenciaDirectaFacadeLocal persistenciaDocenciaDirecta;
    @EJB
    private InvestigacionFacadeLocal persistenciaInvestigación;
    @EJB
    private MateriaFacadeLocal persistenciaMateria;
    @EJB
    private ParametroFacadeLocal persistenciaParametro;
    @EJB
    private PlanTrabajoFacadeLocal persitenciaPlan;
    @EJB
    private CoordinadorFacadeLocal persitenciaCoordinador;
    @EJB
    private AsesoriaProyectoFacadeLocal persitenciaAsesoriaProyecto;
    @EJB
    private PublicacionFacadeLocal persitenciaPublicacion;
    @EJB
    private ComisionEstudioFacadeLocal persitenciaComisionEstudio;
    @EJB
    private DistribucionActividadFacadeLocal persitenciaDistribucionActividad;
    @EJB
    private OtraActividadFacadeLocal persitenciaOtraActividad;
    @EJB
    private ActExtAcademicaFacadeLocal persitenciaActExtAcademica;

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">

    public String getSemestreAcademico() {
        return semestreAcademico;
    }

    public void setSemestreAcademico(String semestreAcademico) {
        this.semestreAcademico = semestreAcademico;
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

    public String getTipoDePlan() {
        return tipoDePlan;
    }

    public void setTipoDePlan(String tipoDePlan) {
        this.tipoDePlan = tipoDePlan;
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

    public Coordinador getCoordinador() {
        if (coordinador == null) {
            coordinador = new Coordinador();
        }
        return coordinador;
    }

    public void setCoordinador(Coordinador Coordinador) {
        this.coordinador = Coordinador;
    }

    public PlanTrabajo getPlanTrabajo() {
        if (planTrabajo == null) {
            planTrabajo = new PlanTrabajo();
        }
        return planTrabajo;
    }

    public void setPlanTrabajo(PlanTrabajo planTrabajo) {
        this.planTrabajo = planTrabajo;
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

    public List<HorarioProfesorVO> getListHorarioProfesorVO() {
        if (listHorarioProfesorVO == null) {
            listHorarioProfesorVO = new ArrayList<>();
        }
        return listHorarioProfesorVO;
    }

    public void setListHorarioProfesorVO(List<HorarioProfesorVO> listHorarioProfesorVO) {
        this.listHorarioProfesorVO = listHorarioProfesorVO;
    }

    public HorarioProfesorVO getHorarioProfesorVO() {
        if (horarioProfesorVO == null) {
            horarioProfesorVO = new HorarioProfesorVO();
        }
        return horarioProfesorVO;
    }

    public void setHorarioProfesorVO(HorarioProfesorVO horarioProfesorVO) {
        this.horarioProfesorVO = horarioProfesorVO;
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

    public int getTotalHorasComison() {
        return totalHorasComison;
    }

    public void setTotalHorasComison(int totalHorasComison) {
        this.totalHorasComison = totalHorasComison;
    }

    public int getTotalHorasInvestigacion() {
        return totalHorasInvestigacion;
    }

    public void setTotalHorasInvestigacion(int totalHorasInvestigacion) {
        this.totalHorasInvestigacion = totalHorasInvestigacion;
    }

    public int getTotalHorasExtesion() {
        return totalHorasExtesion;
    }

    public void setTotalHorasExtesion(int totalHorasExtesion) {
        this.totalHorasExtesion = totalHorasExtesion;
    }

    public int getTotalHorasPublicaciones() {
        return totalHorasPublicaciones;
    }

    public void setTotalHorasPublicaciones(int totalHorasPublicaciones) {
        this.totalHorasPublicaciones = totalHorasPublicaciones;
    }

    public int getTotalHorasAsesorias() {
        return totalHorasAsesorias;
    }

    public void setTotalHorasAsesorias(int totalHorasAsesorias) {
        this.totalHorasAsesorias = totalHorasAsesorias;
    }

    public int getTotalHorasOtrasActividades() {
        return totalHorasOtrasActividades;
    }

    public void setTotalHorasOtrasActividades(int totalHorasOtrasActividades) {
        this.totalHorasOtrasActividades = totalHorasOtrasActividades;
    }

    public int getHorasLegales() {
        return horasLegales;
    }

    public void setHorasLegales(int horasLegales) {
        this.horasLegales = horasLegales;
    }

    public Parametro getParametro() {
        return parametro;
    }

    public void setParametro(Parametro parametro) {
        this.parametro = parametro;
    }

    public int getTotalHorasPlan() {
        return totalHorasPlan;
    }

    public void setTotalHorasPlan(int totalHorasPlan) {
        this.totalHorasPlan = totalHorasPlan;
    }

    public int getTotalHorasDocenciaDirecta() {
        return totalHorasDocenciaDirecta;
    }

    public void setTotalHorasDocenciaDirecta(int totalHorasDocenciaDirecta) {
        this.totalHorasDocenciaDirecta = totalHorasDocenciaDirecta;
    }

    public Publicacion getPublicacion() {
        if (publicacion == null) {
            publicacion = new Publicacion();
        }
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        if (!listPublicaciones.isEmpty()) {
            listPublicaciones.remove(publicacion);
        }
        this.publicacion = publicacion;
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

    public String getCodigoMateria() {
        return codigoMateria;
    }

    public void setCodigoMateria(String codigoMateria) {
        this.codigoMateria = codigoMateria;
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

    public List<Docente> getDocentes() {
        return docentes;
    }

    public void setDocentes(List<Docente> docentes) {
        this.docentes = docentes;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public List<Materia> getMaterias() {
        if (materias == null) {
            materias = persistenciaMateria.findMateriasActivas();
        }
        return materias;
    }

    public void setMaterias(List<Materia> materias) {
        this.materias = materias;
    }

    public ActExtAcademica getActExtAcademica() {
        if (actExtAcademica == null) {
            actExtAcademica = new ActExtAcademica();
        }
        return actExtAcademica;
    }

    public void setActExtAcademica(ActExtAcademica actExtAcademica) {
        if (!listActExtAcademicas.isEmpty()) {
            listActExtAcademicas.remove(actExtAcademica);
        }
        this.actExtAcademica = actExtAcademica;
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

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public List<Actividad> getActividades() {
        if (Actividades == null) {
            Actividades = new ArrayList<>();
        }
        return Actividades;
    }

    public void setActividades(List<Actividad> Actividades) {
        this.Actividades = Actividades;
    }

    public Investigacion getInvestigacion() {
        if (investigacion == null) {
            investigacion = new Investigacion();
        }
        return investigacion;
    }

    public void setInvestigacion(Investigacion investigacion) {
        if (!investigaciones.isEmpty()) {
            investigaciones.remove(investigacion);
        }
        this.investigacion = investigacion;
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

    public AsesoriaProyecto getAsesoriaProyecto() {
        if (asesoriaProyecto == null) {
            asesoriaProyecto = new AsesoriaProyecto();
        }
        return asesoriaProyecto;
    }

    public void setAsesoriaProyecto(AsesoriaProyecto asesoriaProyecto) {
        this.asesoriaProyecto = asesoriaProyecto;
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

    public ComisionEstudio getComisionEstudio() {
        if (comisionEstudio == null) {
            comisionEstudio = new ComisionEstudio();
        }
        return comisionEstudio;
    }

    public void setComisionEstudio(ComisionEstudio comisionEstudio) {
        if (!listComisionEstudios.isEmpty()) {
            listComisionEstudios.remove(comisionEstudio);
        }
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

    public Consejo getConsejo() {
        if (consejo == null) {
            consejo = new Consejo();
        }
        return consejo;
    }

    public void setConsejo(Consejo consejo) {
        if (!listConsejos.isEmpty()) {
            listConsejos.remove(consejo);
        }
        this.consejo = consejo;
    }

    public List<Consejo> getListConsejos() {
        if (listConsejos == null) {
            listConsejos = new ArrayList<>();
        }
        return listConsejos;
    }

    public void setListConsejos(List<Consejo> listConsejos) {
        this.listConsejos = listConsejos;
    }

    public DistribucionActividad getDistribucionActividad() {
        return distribucionActividad;
    }

    public void setDistribucionActividad(DistribucionActividad distribucionActividad) {
        this.distribucionActividad = distribucionActividad;
    }

    public List<DistribucionActividad> getListDistribucionActividades() {
        if (listDistribucionActividades == null) {
            listDistribucionActividades = new ArrayList<>();
        }
        return listDistribucionActividades;
    }

    public void setListDistribucionActividades(List<DistribucionActividad> listDistribucionActividades) {
        this.listDistribucionActividades = listDistribucionActividades;
    }

    public DocenciaDirecta getDocenciaDirecta() {
        if (docenciaDirecta == null) {
            docenciaDirecta = new DocenciaDirecta();
        }
        return docenciaDirecta;
    }

    public void setDocenciaDirecta(DocenciaDirecta docenciaDirecta) {
        if (!listDocenciaDirecta.isEmpty()) {
            listDocenciaDirecta.remove(docenciaDirecta);
        }
        this.docenciaDirecta = docenciaDirecta;
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

    public Facultad getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    public List<Facultad> getFacultades() {
        if (facultades == null) {
            facultades = new ArrayList<>();
        }
        return facultades;
    }

    public void setFacultades(List<Facultad> facultades) {
        this.facultades = facultades;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Converter getFacultadConverter() {
        return facultadConverter;
    }

    public void setFacultadConverter(Converter facultadConverter) {
        this.facultadConverter = facultadConverter;
    }

    public boolean isMostar() {
        return mostar;
    }

    public void setMostar(boolean mostar) {
        this.mostar = mostar;
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
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

    public Converter getMateriaConverter() {
        if (this.materiaConverter == null) {
            this.materiaConverter = new Converter() {

                @Override
                public Object getAsObject(FacesContext context, UIComponent component, String value) {
                    Materia retorno = null;

                    if (value != null && !value.isEmpty()
                            && !value.trim().equals("[TODOS]")
                            && !value.trim().equals("[NINGUNO]")) {
                        BigDecimal materiaId = new BigDecimal(value);
                        for (Materia mate : getMaterias()) {
                            if (mate.getCodMateria().compareTo(materiaId) == 0) {
                                retorno = mate;
                                break;
                            }
                        }
                    }

                    return retorno;
                }

                @Override
                public String getAsString(FacesContext context, UIComponent component, Object value) {
                    String retorno = null;

                    if (value != null && value instanceof Materia) {
                        retorno = ((Materia) value).getCodMateria().toString();
                    }

                    return retorno;
                }
            };
        }
        return materiaConverter;
    }

    public void setMateriaConverter(Converter materiaConverter) {
        this.materiaConverter = materiaConverter;
    }

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
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido al asistente de creación de su plan de trabajo.", ""));
        } catch (Exception e) {
            e.printStackTrace();
            //LoggerFacade.registerError(e.getMessage(), e);
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
            if (getListDocenciaDirecta().isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe agregar al menos un curso de Docencia Directa.", ""));
                return event.getOldStep();
            } else {
                if (totalHorasDocenciaDirecta >= 0 && totalHorasDocenciaDirecta < 4) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Docencia Directa debe tener al menos 12 horas y tiene: " + totalHorasDocenciaDirecta, ""));
                    return event.getOldStep();
                } else if (totalHorasDocenciaDirecta > 24) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Docencia Directa no debe superar las 18 horas y tiene: " + totalHorasDocenciaDirecta, ""));
                    return event.getOldStep();
                }
            }
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

    public void eliminarMateria(DocenciaDirecta docenciaDirecta) {
        try {
            totalHorasDocenciaDirecta = totalHorasDocenciaDirecta - docenciaDirecta.getHorSemanal().intValue();
            horarioMap.remove(horarioProfesorVO.getDiaHora());
            listDocenciaDirecta.remove(docenciaDirecta);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Curso eliminado.", ""));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarInvestigacion(Investigacion investigacion) {
        try {
            totalHorasInvestigacion = totalHorasInvestigacion - investigacion.getHorSemanal().intValue();
            investigaciones.remove(investigacion);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Investigación eliminada.", ""));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarExtension(ActExtAcademica actExtAcademica) {
        try {
            totalHorasExtesion = totalHorasExtesion - actExtAcademica.getHorDedicadas().intValue();
            listActExtAcademicas.remove(actExtAcademica);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Extesión eliminada.", ""));
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void eliminarPublicacion(Publicacion publicacion) {
        try {
            totalHorasPublicaciones = totalHorasPublicaciones - publicacion.getHorDedicadas().intValue();
            listPublicaciones.remove(publicacion);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Publicación eliminada.", ""));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarAsesoria(AsesoriaProyecto asesoriaProyecto) {
        try {
            totalHorasAsesorias = totalHorasAsesorias - asesoriaProyecto.getHorDedicadas().intValue();
            listAsesoriaProyectos.remove(asesoriaProyecto);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Asesoria eliminada.", ""));
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
            //Deberia ser newstep
            //EL método se llama solo cuando se agrege cada actividad y no por el flow
            //para controloar los desplazamientos y evitar resumas
            if (evento.equalsIgnoreCase("docenciaDirecta")) {
                totalHorasPlan = totalHorasPlan + intensidad;
            } else if (evento.equalsIgnoreCase("investigacion")) {
                totalHorasPlan = totalHorasPlan + intensidad;
            } else if (evento.equalsIgnoreCase("extension")) {
                totalHorasPlan = totalHorasPlan + intensidad;
            } else if (evento.equalsIgnoreCase("comision")) {
                totalHorasPlan = totalHorasPlan + intensidad;
            } else if (evento.equalsIgnoreCase("tabPublicaciones")) {
                totalHorasPlan = totalHorasPlan + intensidad;
            } else if (evento.equalsIgnoreCase("tabAsesorias")) {
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

    public String agregarMateria() {
        try {
            if (docenciaDirecta.getCodMateria() == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "La asignatura es obligatoria.", ""));
                return null;
            } else if (docenciaDirecta.getGrupo().isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "El grupo es obligatorio.", ""));
                return null;
            } else if (docenciaDirecta.getNmrEstudiantes() == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "El número de estudiantes es obligatorio.", ""));
                return null;
            } else if (horarioProfesorVO == null || horarioProfesorVO.getDiaHora().equalsIgnoreCase("[NINGUNO]")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe Selecionar un Horario.", ""));
                return null;
            } else {
                docenciaDirecta.setHorSemanal(new BigInteger(horarioProfesorVO.getIntencidadSemanal() + "".trim()));
                if (llenarHorario(docenciaDirecta.getCodMateria().getNomMateria())) {
                    totalHorasDocenciaDirecta = totalHorasDocenciaDirecta + docenciaDirecta.getHorSemanal().intValue();
                    getListDocenciaDirecta().add(docenciaDirecta);
                    sumarHoras("docenciaDirecta", docenciaDirecta.getHorSemanal().intValue());
                    limpiarMateria();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Curso agregado.", ""));
                } else {
                    limpiarMateria();
                }
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrio un Error agregando el Curso.", ""));
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
            limpiarMateria();
            ex.printStackTrace();
        }
        return retono;
    }

    public void limpiarHorario() {
        horarioProfesorVO = new HorarioProfesorVO();
    }

    public void limpiarMateria() {
        limpiarHorario();
        diaHorario = "";
        horadia = 0;
        docenciaDirecta = new DocenciaDirecta();
    }

    public String agregarInvestigacion() {

        try {
            if (horarioProfesorVO == null
                    || investigacion.getActDesarrollada().equals("")
                    || investigacion.getNomSemillero().equals("")
                    || investigacion.getActProductos().equals("")
                    || investigacion.getTipVinculo().equalsIgnoreCase("NINGUNO")
                    || horarioProfesorVO == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe ingresar valores en los campos diferentes a cero.", ""));
                return null;
            } else {
                investigacion.setHorSemanal(new BigInteger(horarioProfesorVO.getIntencidadSemanal() + "".trim()));
                if (llenarHorario(investigacion.getNomSemillero())) {
                    totalHorasInvestigacion = totalHorasInvestigacion + investigacion.getHorSemanal().intValue();
                    getInvestigaciones().add(investigacion);
                    sumarHoras("investigacion", investigacion.getHorSemanal().intValue());
                    investigacion = new Investigacion();
                    limpiarHorario();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Investigación agregada.", ""));
                } else {
                    investigacion = new Investigacion();
                }
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrio un Error agregando la investigación.", ""));
            e.printStackTrace();
        }
        return null;
    }

    public String agregarExtension() {

        try {
            if (actExtAcademica.getNomActividad().equals("")
                    || horarioProfesorVO == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe ingresar valores en los campos diferentes a cero.", ""));
                return null;
            } else {
                actExtAcademica.setHorDedicadas(new BigInteger(horarioProfesorVO.getIntencidadSemanal() + "".trim()));
                if (llenarHorario(actExtAcademica.getNomActividad())) {
                    totalHorasExtesion = totalHorasExtesion + actExtAcademica.getHorDedicadas().intValue();
                    getListActExtAcademicas().add(actExtAcademica);
                    sumarHoras("extension", actExtAcademica.getHorDedicadas().intValue());
                    actExtAcademica = new ActExtAcademica();
                    limpiarHorario();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Actividad de Extensión Académica agregada.", ""));
                } else {
                    actExtAcademica = new ActExtAcademica();
                }
                /*actExtAcademica.setFec(null);
                 actExtAcademica.setFecInicio(new Date());
                 actExtAcademica.setNomActividad("NINGUNA");
                 actExtAcademica.setHorDedicadas(new BigInteger("0"));*/
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrio un Error agregando la actividad de Extensión Académica.", ""));
            e.printStackTrace();
        }
        return null;
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
                            "La fecha de graducaión no puede ser mayor a la fecha de inicio del estudio.", ""));
                    return null;
                }
                pasoCompracionAprobacion = Util.compararFechas(comisionEstudio.getFecObtencionAutorizacion(), comisionEstudio.getFecInicio());
                if (!pasoCompracionAprobacion) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "La Fecha Obtención de Autorización no puede ser mayor a la fecha de inicio del estudio.", ""));
                    return null;
                }
                comisionEstudio.setHorDedicadas(new BigInteger(horarioProfesorVO.getIntencidadSemanal() + "".trim()));
                if (llenarHorario(comisionEstudio.getNomEspecEstudio())) {
                    totalHorasComison = totalHorasComison + comisionEstudio.getHorDedicadas().intValue();
                    getListComisionEstudios().add(comisionEstudio);
                    sumarHoras("comision", comisionEstudio.getHorDedicadas().intValue());
                    comisionEstudio = new ComisionEstudio();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Comisión de estudio agregada.", ""));
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

    public String agregarPublicacon() {

        try {
            if (horarioProfesorVO == null
                    || publicacion.getNomArticulo().equals("")
                    || publicacion.getCoautor().equals("")
                    || publicacion.getTemPrincipal().equals("")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe ingresar valores en los campos diferentes a cero.", ""));
                return null;
            } else {
                publicacion.setHorDedicadas(new BigInteger(horarioProfesorVO.getIntencidadSemanal() + "".trim()));
                if (llenarHorario(publicacion.getTemPrincipal())) {
                    totalHorasPublicaciones = totalHorasPublicaciones + publicacion.getHorDedicadas().intValue();
                    getListPublicaciones().add(publicacion);
                    sumarHoras("tabPublicaciones", publicacion.getHorDedicadas().intValue());
                    publicacion = new Publicacion();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Publicación agregada.", ""));
                    limpiarHorario();
                } else {
                    publicacion = new Publicacion();
                }
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrio un Error agregando la Publicación.", ""));
            e.printStackTrace();
        }
        return null;
    }

    public String agregarAsesorias() {

        try {
            if (horarioProfesorVO == null
                    || asesoriaProyecto.getNomEstudiante().equals("")
                    || asesoriaProyecto.getAspRelevante().equals("")
                    || asesoriaProyecto.getTitulo().equals("")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe ingresar valores en los campos diferentes a cero.", ""));
                return null;
            } else {
                asesoriaProyecto.setHorDedicadas(new BigInteger(horarioProfesorVO.getIntencidadSemanal() + "".trim()));
                if (llenarHorario(asesoriaProyecto.getTitulo())) {
                    totalHorasAsesorias = totalHorasAsesorias + asesoriaProyecto.getHorDedicadas().intValue();
                    getListAsesoriaProyectos().add(asesoriaProyecto);
                    sumarHoras("tabAsesorias", asesoriaProyecto.getHorDedicadas().intValue());
                    asesoriaProyecto = new AsesoriaProyecto();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Asesoría agregada.", ""));
                    limpiarHorario();

                } else {
                    asesoriaProyecto = new AsesoriaProyecto();
                }
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrio un Error agregando la Asesoría.", ""));
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

    public String guardar() {

        try {
            if (totalHorasPlan != horasLegales) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "EL  plan debe tener : " + horasLegales + "horas y tiene: " + totalHorasPlan + " horas por favor ajuste su plan.", ""));
                return null;
            } else {
                planTrabajo = new PlanTrabajo();
                //coordinador = new Coordinador();
                //docente = new Docente();
                //consejo = new Consejo();
                //coordinador.setCodCoordinador(BigDecimal.ONE);
                //consejo.setCodConsejo(BigDecimal.ONE);
                //planTrabajo.setDocenciaDirectaCollection(listDocenciaDirecta);
                //planTrabajo.setAsesoriaProyectoCollection(listAsesoriaProyectos);
                //planTrabajo.setPublicacionCollection(listPublicaciones);
                //planTrabajo.setComisionEstudioCollection(listComisionEstudios);
                //planTrabajo.setDistribucionActividadCollection(listDistribucionActividades);
                //planTrabajo.setInvestigacionCollection(investigaciones);
                //planTrabajo.setOtraActividadCollection(listOtrasActividades);
                //planTrabajo.setActExtAcademicaCollection(listActExtAcademicas);
                planTrabajo.setFecPlanTrabajo(new Date());
                planTrabajo.setEstado("0");
                planTrabajo.setTipoPlan(tipoDePlan);
                planTrabajo.setCodDocente(docente);
                planTrabajo.setPeriodoAcademico(semestreAcademico);
                persitenciaPlan.create(planTrabajo);
                for (DocenciaDirecta docAux : listDocenciaDirecta) {
                    docAux.setCodPlanTrabajo(planTrabajo);
                    persistenciaDocenciaDirecta.create(docAux);
                }
                for (AsesoriaProyecto aseAux : listAsesoriaProyectos) {
                    aseAux.setCodPlanTrabajo(planTrabajo);
                    persitenciaAsesoriaProyecto.create(aseAux);
                }
                for (Publicacion pubAux : listPublicaciones) {
                    pubAux.setCodPlantTrabajo(planTrabajo);
                    persitenciaPublicacion.create(pubAux);
                }
                for (ComisionEstudio comiAux : listComisionEstudios) {
                    comiAux.setCodPlanTrabajo(planTrabajo);
                    persitenciaComisionEstudio.create(comiAux);
                }
                /*for (DistribucionActividad disActAux : listDistribucionActividades) {
                    disActAux.setCodPlanTrabajo(planTrabajo);
                    persitenciaDistribucionActividad.create(disActAux);
                }*/
                for (Investigacion invAux : investigaciones) {
                    invAux.setCodPlanTrabajo(planTrabajo);
                    persistenciaInvestigación.create(invAux);
                }
                for (OtraActividad otraAux : listOtrasActividades) {
                    otraAux.setCodPlanTrabajo(planTrabajo);
                    persitenciaOtraActividad.create(otraAux);
                }
                for (ActExtAcademica actExtAux : listActExtAcademicas) {
                    actExtAux.setCodPlanTrabajo(planTrabajo);
                    persitenciaActExtAcademica.create(actExtAux);
                }
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "El Plan de Trabajo se guardo.", ""));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrio un Error guardando el Plan de Trabajo.", ""));
            e.printStackTrace();
        }
        return null;
    }

   
}
