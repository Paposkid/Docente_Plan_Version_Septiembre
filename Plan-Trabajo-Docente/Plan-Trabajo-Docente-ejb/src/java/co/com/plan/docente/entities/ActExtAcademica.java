/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.plan.docente.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 727855
 */
@Entity
@Table(name = "ACT_EXT_ACADEMICA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActExtAcademica.findAll", query = "SELECT a FROM ActExtAcademica a"),
    @NamedQuery(name = "ActExtAcademica.findByCodActividad", query = "SELECT a FROM ActExtAcademica a WHERE a.codActividad = :codActividad"),
    @NamedQuery(name = "ActExtAcademica.findByNomActividad", query = "SELECT a FROM ActExtAcademica a WHERE a.nomActividad = :nomActividad"),
    @NamedQuery(name = "ActExtAcademica.findByFecInicio", query = "SELECT a FROM ActExtAcademica a WHERE a.fecInicio = :fecInicio"),
    @NamedQuery(name = "ActExtAcademica.findByFec", query = "SELECT a FROM ActExtAcademica a WHERE a.fec = :fec"),
    @NamedQuery(name = "ActExtAcademica.findByVobo", query = "SELECT a FROM ActExtAcademica a WHERE a.vobo = :vobo"),
    @NamedQuery(name = "ActExtAcademica.findByHorDedicadas", query = "SELECT a FROM ActExtAcademica a WHERE a.horDedicadas = :horDedicadas")})
public class ActExtAcademica implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @SequenceGenerator(name = "SecuenciaExtAcademica", sequenceName = "SEQ_EXT_ACADEMICA", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SecuenciaExtAcademica")
    @Column(name = "COD_ACTIVIDAD")
    private BigDecimal codActividad;
    @Basic(optional = false)
    @Column(name = "NOM_ACTIVIDAD")
    private String nomActividad;
    @Basic(optional = false)
    @Column(name = "FEC_INICIO")
    @Temporal(TemporalType.DATE)
    private Date fecInicio;
    @Basic(optional = false)
    @Column(name = "FEC")
    @Temporal(TemporalType.DATE)
    private Date fec;
    @Column(name = "VOBO")
    private String vobo;
    @Basic(optional = false)
    @Column(name = "HOR_DEDICADAS")
    private BigInteger horDedicadas;
    @JoinColumn(name = "COD_PLAN_TRABAJO", referencedColumnName = "COD_PLAN_TRABAJO")
    @ManyToOne(optional = false)
    private PlanTrabajo codPlanTrabajo;

    public ActExtAcademica() {
    }

    public ActExtAcademica(BigDecimal codActividad) {
        this.codActividad = codActividad;
    }

    public ActExtAcademica(BigDecimal codActividad, String nomActividad, Date fecInicio, Date fec, BigInteger horDedicadas) {
        this.codActividad = codActividad;
        this.nomActividad = nomActividad;
        this.fecInicio = fecInicio;
        this.fec = fec;
        this.horDedicadas = horDedicadas;
    }

    public BigDecimal getCodActividad() {
        return codActividad;
    }

    public void setCodActividad(BigDecimal codActividad) {
        this.codActividad = codActividad;
    }

    public String getNomActividad() {
        return nomActividad;
    }

    public void setNomActividad(String nomActividad) {
        this.nomActividad = nomActividad;
    }

    public Date getFecInicio() {
        return fecInicio;
    }

    public void setFecInicio(Date fecInicio) {
        this.fecInicio = fecInicio;
    }

    public Date getFec() {
        return fec;
    }

    public void setFec(Date fec) {
        this.fec = fec;
    }

    public String getVobo() {
        return vobo;
    }

    public void setVobo(String vobo) {
        this.vobo = vobo;
    }

    public BigInteger getHorDedicadas() {
        return horDedicadas;
    }

    public void setHorDedicadas(BigInteger horDedicadas) {
        this.horDedicadas = horDedicadas;
    }

    public PlanTrabajo getCodPlanTrabajo() {
        return codPlanTrabajo;
    }

    public void setCodPlanTrabajo(PlanTrabajo codPlanTrabajo) {
        this.codPlanTrabajo = codPlanTrabajo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codActividad != null ? codActividad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActExtAcademica)) {
            return false;
        }
        ActExtAcademica other = (ActExtAcademica) object;
        if ((this.codActividad == null && other.codActividad != null) || (this.codActividad != null && !this.codActividad.equals(other.codActividad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.plan_docente.entities.ActExtAcademica[ codActividad=" + codActividad + " ]";
    }

}
