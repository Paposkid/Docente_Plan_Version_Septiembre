/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.plan.docente.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 727855
 */
@Entity
@Table(name = "SEGUIMIENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Seguimiento.findAll", query = "SELECT s FROM Seguimiento s"),
    @NamedQuery(name = "Seguimiento.findByCodSeguimiento", query = "SELECT s FROM Seguimiento s WHERE s.codSeguimiento = :codSeguimiento"),
    @NamedQuery(name = "Seguimiento.findBySemana", query = "SELECT s FROM Seguimiento s WHERE s.semana = :semana"),
    @NamedQuery(name = "Seguimiento.findByObservacion", query = "SELECT s FROM Seguimiento s WHERE s.observacion = :observacion"),
    @NamedQuery(name = "Seguimiento.findByFecSeguimiento", query = "SELECT s FROM Seguimiento s WHERE s.fecSeguimiento = :fecSeguimiento")})
public class Seguimiento implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_SEGUIMIENTO")
    private BigDecimal codSeguimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "SEMANA")
    private String semana;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4000)
    @Column(name = "OBSERVACION")
    private String observacion;
    @Column(name = "FEC_SEGUIMIENTO")
    @Temporal(TemporalType.DATE)
    private Date fecSeguimiento;
    @JoinColumn(name = "COD_PLAN_TRABAJO", referencedColumnName = "COD_PLAN_TRABAJO")
    @ManyToOne(optional = false)
    private PlanTrabajo codPlanTrabajo;
    @JoinColumn(name = "COD_COORDINADOR", referencedColumnName = "COD_COORDINADOR")
    @ManyToOne(optional = false)
    private Coordinador codCoordinador;

    public Seguimiento() {
    }

    public Seguimiento(BigDecimal codSeguimiento) {
        this.codSeguimiento = codSeguimiento;
    }

    public Seguimiento(BigDecimal codSeguimiento, String semana, String observacion) {
        this.codSeguimiento = codSeguimiento;
        this.semana = semana;
        this.observacion = observacion;
    }

    public BigDecimal getCodSeguimiento() {
        return codSeguimiento;
    }

    public void setCodSeguimiento(BigDecimal codSeguimiento) {
        this.codSeguimiento = codSeguimiento;
    }

    public String getSemana() {
        return semana;
    }

    public void setSemana(String semana) {
        this.semana = semana;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getFecSeguimiento() {
        return fecSeguimiento;
    }

    public void setFecSeguimiento(Date fecSeguimiento) {
        this.fecSeguimiento = fecSeguimiento;
    }

    public PlanTrabajo getCodPlanTrabajo() {
        return codPlanTrabajo;
    }

    public void setCodPlanTrabajo(PlanTrabajo codPlanTrabajo) {
        this.codPlanTrabajo = codPlanTrabajo;
    }

    public Coordinador getCodCoordinador() {
        return codCoordinador;
    }

    public void setCodCoordinador(Coordinador codCoordinador) {
        this.codCoordinador = codCoordinador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codSeguimiento != null ? codSeguimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Seguimiento)) {
            return false;
        }
        Seguimiento other = (Seguimiento) object;
        if ((this.codSeguimiento == null && other.codSeguimiento != null) || (this.codSeguimiento != null && !this.codSeguimiento.equals(other.codSeguimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.plan_docente.entities.Seguimiento[ codSeguimiento=" + codSeguimiento + " ]";
    }
    
}
