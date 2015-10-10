/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.plan.docente.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 727855
 */
@Entity
@Table(name = "PLAN_TRABAJO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlanTrabajo.findAll", query = "SELECT p FROM PlanTrabajo p"),
    @NamedQuery(name = "PlanTrabajo.findByCodPlanTrabajo", query = "SELECT p FROM PlanTrabajo p WHERE p.codPlanTrabajo = :codPlanTrabajo"),
    @NamedQuery(name = "PlanTrabajo.findByFecPlanTrabajo", query = "SELECT p FROM PlanTrabajo p WHERE p.fecPlanTrabajo = :fecPlanTrabajo"),
    @NamedQuery(name = "PlanTrabajo.findByFechCalificacion", query = "SELECT p FROM PlanTrabajo p WHERE p.fechCalificacion = :fechCalificacion"),
    @NamedQuery(name = "PlanTrabajo.findByFechCalFin", query = "SELECT p FROM PlanTrabajo p WHERE p.fechCalFin = :fechCalFin"),
    @NamedQuery(name = "PlanTrabajo.findByObservacion", query = "SELECT p FROM PlanTrabajo p WHERE p.observacion = :observacion")})
public class PlanTrabajo implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @SequenceGenerator(name = "SecuenciaPlan", sequenceName = "SEQ_PLAN_TRABAJO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SecuenciaPlan")
    @Column(name = "COD_PLAN_TRABAJO")
    private BigDecimal codPlanTrabajo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FEC_PLAN_TRABAJO")
    @Temporal(TemporalType.DATE)
    private Date fecPlanTrabajo;
    @Column(name = "FECH_CALIFICACION")
    @Temporal(TemporalType.DATE)
    private Date fechCalificacion;
    @Column(name = "FECH_CAL_FIN")
    @Temporal(TemporalType.DATE)
    private Date fechCalFin;
    @Lob
    @Column(name = "EVIDENCIA")
    private String evidencia;
    @Size(max = 4000)
    @Column(name = "OBSERVACION")
    private String observacion;
    @JoinColumn(name = "COD_DOCENTE", referencedColumnName = "COD_DOCENTE")
    @ManyToOne(optional = false)
    private Docente codDocente;
    @JoinColumn(name = "COD_COORDINADOR", referencedColumnName = "COD_COORDINADOR")
    @ManyToOne(optional = false)
    private Coordinador codCoordinador;
    @JoinColumn(name = "COD_CONSEJO", referencedColumnName = "COD_CONSEJO")
    @ManyToOne(optional = false)
    private Consejo codConsejo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codPlantTrabajo")
    private Collection<Publicacion> publicacionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codPlanTrabajo")
    private Collection<Seguimiento> seguimientoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codPlanTrabajo")
    private Collection<DocenciaDirecta> docenciaDirectaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codPlanTrabajo")
    private Collection<ComisionEstudio> comisionEstudioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codPlanTrabajo")
    private Collection<ActExtAcademica> actExtAcademicaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codPlanTrabajo")
    private Collection<DistribucionActividad> distribucionActividadCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codPlanTrabajo")
    private Collection<OtraActividad> otraActividadCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codPlanTrabajo")
    private Collection<Investigacion> investigacionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codPlanTrabajo")
    private Collection<AsesoriaProyecto> asesoriaProyectoCollection;

    public PlanTrabajo() {
    }

    public PlanTrabajo(BigDecimal codPlanTrabajo) {
        this.codPlanTrabajo = codPlanTrabajo;
    }

    public PlanTrabajo(BigDecimal codPlanTrabajo, Date fecPlanTrabajo) {
        this.codPlanTrabajo = codPlanTrabajo;
        this.fecPlanTrabajo = fecPlanTrabajo;
    }

    public BigDecimal getCodPlanTrabajo() {
        return codPlanTrabajo;
    }

    public void setCodPlanTrabajo(BigDecimal codPlanTrabajo) {
        this.codPlanTrabajo = codPlanTrabajo;
    }

    public Date getFecPlanTrabajo() {
        return fecPlanTrabajo;
    }

    public void setFecPlanTrabajo(Date fecPlanTrabajo) {
        this.fecPlanTrabajo = fecPlanTrabajo;
    }

    public Date getFechCalificacion() {
        return fechCalificacion;
    }

    public void setFechCalificacion(Date fechCalificacion) {
        this.fechCalificacion = fechCalificacion;
    }

    public Date getFechCalFin() {
        return fechCalFin;
    }

    public void setFechCalFin(Date fechCalFin) {
        this.fechCalFin = fechCalFin;
    }

    public String getEvidencia() {
        return evidencia;
    }

    public void setEvidencia(String evidencia) {
        this.evidencia = evidencia;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Docente getCodDocente() {
        return codDocente;
    }

    public void setCodDocente(Docente codDocente) {
        this.codDocente = codDocente;
    }

    public Coordinador getCodCoordinador() {
        return codCoordinador;
    }

    public void setCodCoordinador(Coordinador codCoordinador) {
        this.codCoordinador = codCoordinador;
    }

    public Consejo getCodConsejo() {
        return codConsejo;
    }

    public void setCodConsejo(Consejo codConsejo) {
        this.codConsejo = codConsejo;
    }

    @XmlTransient
    public Collection<Publicacion> getPublicacionCollection() {
        if (publicacionCollection == null) {
            publicacionCollection = new ArrayList<>();
        }
        return publicacionCollection;
    }

    public void setPublicacionCollection(Collection<Publicacion> publicacionCollection) {
        this.publicacionCollection = publicacionCollection;
    }

    @XmlTransient
    public Collection<Seguimiento> getSeguimientoCollection() {
         if (seguimientoCollection == null) {
            seguimientoCollection = new ArrayList<>();
        }
        return seguimientoCollection;
    }

    public void setSeguimientoCollection(Collection<Seguimiento> seguimientoCollection) {
        this.seguimientoCollection = seguimientoCollection;
    }

    @XmlTransient
    public Collection<DocenciaDirecta> getDocenciaDirectaCollection() {
        if (docenciaDirectaCollection == null) {
            docenciaDirectaCollection = new ArrayList<>();
        }
        return docenciaDirectaCollection;
    }

    public void setDocenciaDirectaCollection(Collection<DocenciaDirecta> docenciaDirectaCollection) {
        this.docenciaDirectaCollection = docenciaDirectaCollection;
    }

    @XmlTransient
    public Collection<ComisionEstudio> getComisionEstudioCollection() {
        if (comisionEstudioCollection == null) {
            comisionEstudioCollection = new ArrayList<>();
        }
        return comisionEstudioCollection;
    }

    public void setComisionEstudioCollection(Collection<ComisionEstudio> comisionEstudioCollection) {
        this.comisionEstudioCollection = comisionEstudioCollection;
    }

    @XmlTransient
    public Collection<ActExtAcademica> getActExtAcademicaCollection() {
        if (actExtAcademicaCollection == null) {
            actExtAcademicaCollection = new ArrayList<>();
        }
        return actExtAcademicaCollection;
    }

    public void setActExtAcademicaCollection(Collection<ActExtAcademica> actExtAcademicaCollection) {
        this.actExtAcademicaCollection = actExtAcademicaCollection;
    }

    @XmlTransient
    public Collection<DistribucionActividad> getDistribucionActividadCollection() {
        if (distribucionActividadCollection == null) {
            distribucionActividadCollection = new ArrayList<>();
        }
        return distribucionActividadCollection;
    }

    public void setDistribucionActividadCollection(Collection<DistribucionActividad> distribucionActividadCollection) {
        this.distribucionActividadCollection = distribucionActividadCollection;
    }

    @XmlTransient
    public Collection<OtraActividad> getOtraActividadCollection() {
        if (otraActividadCollection == null) {
            otraActividadCollection = new ArrayList<>();
        }
        return otraActividadCollection;
    }

    public void setOtraActividadCollection(Collection<OtraActividad> otraActividadCollection) {
        this.otraActividadCollection = otraActividadCollection;
    }

    @XmlTransient
    public Collection<Investigacion> getInvestigacionCollection() {
        if (investigacionCollection == null) {
            investigacionCollection = new ArrayList<>();
        }
        return investigacionCollection;
    }

    public void setInvestigacionCollection(Collection<Investigacion> investigacionCollection) {
        this.investigacionCollection = investigacionCollection;
    }

    @XmlTransient
    public Collection<AsesoriaProyecto> getAsesoriaProyectoCollection() {
        if (asesoriaProyectoCollection == null) {
            asesoriaProyectoCollection = new ArrayList<>();
        }
        return asesoriaProyectoCollection;
    }

    public void setAsesoriaProyectoCollection(Collection<AsesoriaProyecto> asesoriaProyectoCollection) {
        this.asesoriaProyectoCollection = asesoriaProyectoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codPlanTrabajo != null ? codPlanTrabajo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlanTrabajo)) {
            return false;
        }
        PlanTrabajo other = (PlanTrabajo) object;
        if ((this.codPlanTrabajo == null && other.codPlanTrabajo != null) || (this.codPlanTrabajo != null && !this.codPlanTrabajo.equals(other.codPlanTrabajo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.plan_docente.entities.PlanTrabajo[ codPlanTrabajo=" + codPlanTrabajo + " ]";
    }
    
}
