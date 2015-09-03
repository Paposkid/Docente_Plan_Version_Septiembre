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
@Table(name = "COMISION_ESTUDIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ComisionEstudio.findAll", query = "SELECT c FROM ComisionEstudio c"),
    @NamedQuery(name = "ComisionEstudio.findByCodComision", query = "SELECT c FROM ComisionEstudio c WHERE c.codComision = :codComision"),
    @NamedQuery(name = "ComisionEstudio.findByCenEstudio", query = "SELECT c FROM ComisionEstudio c WHERE c.cenEstudio = :cenEstudio"),
    @NamedQuery(name = "ComisionEstudio.findByTipEstudio", query = "SELECT c FROM ComisionEstudio c WHERE c.tipEstudio = :tipEstudio"),
    @NamedQuery(name = "ComisionEstudio.findByNomEspecEstudio", query = "SELECT c FROM ComisionEstudio c WHERE c.nomEspecEstudio = :nomEspecEstudio"),
    @NamedQuery(name = "ComisionEstudio.findByFecInicio", query = "SELECT c FROM ComisionEstudio c WHERE c.fecInicio = :fecInicio"),
    @NamedQuery(name = "ComisionEstudio.findByFecGraduacion", query = "SELECT c FROM ComisionEstudio c WHERE c.fecGraduacion = :fecGraduacion"),
    @NamedQuery(name = "ComisionEstudio.findByFecObtencionAutorizacion", query = "SELECT c FROM ComisionEstudio c WHERE c.fecObtencionAutorizacion = :fecObtencionAutorizacion"),
    @NamedQuery(name = "ComisionEstudio.findByAporte", query = "SELECT c FROM ComisionEstudio c WHERE c.aporte = :aporte")})
public class ComisionEstudio implements Serializable {
    @Column(name = "HOR_DEDICADAS")
    private BigInteger horDedicadas;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_COMISION")
    private BigDecimal codComision;
    @Basic(optional = false)
    @Column(name = "CEN_ESTUDIO")
    private String cenEstudio;
    @Basic(optional = false)
    @Column(name = "TIP_ESTUDIO")
    private String tipEstudio;
    @Column(name = "NOM_ESPEC_ESTUDIO")
    private String nomEspecEstudio;
    @Basic(optional = false)
    @Column(name = "FEC_INICIO")
    @Temporal(TemporalType.DATE)
    private Date fecInicio;
    @Basic(optional = false)
    @Column(name = "FEC_GRADUACION")
    @Temporal(TemporalType.DATE)
    private Date fecGraduacion;
    @Basic(optional = false)
    @Column(name = "FEC_OBTENCION_AUTORIZACION")
    @Temporal(TemporalType.DATE)
    private Date fecObtencionAutorizacion;
    @Column(name = "APORTE")
    private String aporte;
    @JoinColumn(name = "COD_PLAN_TRABAJO", referencedColumnName = "COD_PLAN_TRABAJO")
    @ManyToOne(optional = false)
    private PlanTrabajo codPlanTrabajo;

    public ComisionEstudio() {
    }

    public ComisionEstudio(BigDecimal codComision) {
        this.codComision = codComision;
    }

    public ComisionEstudio(BigDecimal codComision, String cenEstudio, String tipEstudio, Date fecInicio, Date fecGraduacion, Date fecObtencionAutorizacion) {
        this.codComision = codComision;
        this.cenEstudio = cenEstudio;
        this.tipEstudio = tipEstudio;
        this.fecInicio = fecInicio;
        this.fecGraduacion = fecGraduacion;
        this.fecObtencionAutorizacion = fecObtencionAutorizacion;
    }

    public BigDecimal getCodComision() {
        return codComision;
    }

    public void setCodComision(BigDecimal codComision) {
        this.codComision = codComision;
    }

    public String getCenEstudio() {
        return cenEstudio;
    }

    public void setCenEstudio(String cenEstudio) {
        this.cenEstudio = cenEstudio;
    }

    public String getTipEstudio() {
        return tipEstudio;
    }

    public void setTipEstudio(String tipEstudio) {
        this.tipEstudio = tipEstudio;
    }

    public String getNomEspecEstudio() {
        return nomEspecEstudio;
    }

    public void setNomEspecEstudio(String nomEspecEstudio) {
        this.nomEspecEstudio = nomEspecEstudio;
    }

    public Date getFecInicio() {
        return fecInicio;
    }

    public void setFecInicio(Date fecInicio) {
        this.fecInicio = fecInicio;
    }

    public Date getFecGraduacion() {
        return fecGraduacion;
    }

    public void setFecGraduacion(Date fecGraduacion) {
        this.fecGraduacion = fecGraduacion;
    }

    public Date getFecObtencionAutorizacion() {
        return fecObtencionAutorizacion;
    }

    public void setFecObtencionAutorizacion(Date fecObtencionAutorizacion) {
        this.fecObtencionAutorizacion = fecObtencionAutorizacion;
    }

    public String getAporte() {
        return aporte;
    }

    public void setAporte(String aporte) {
        this.aporte = aporte;
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
        hash += (codComision != null ? codComision.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComisionEstudio)) {
            return false;
        }
        ComisionEstudio other = (ComisionEstudio) object;
        if ((this.codComision == null && other.codComision != null) || (this.codComision != null && !this.codComision.equals(other.codComision))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.plan_docente.entities.ComisionEstudio[ codComision=" + codComision + " ]";
    }

    public BigInteger getHorDedicadas() {
        return horDedicadas;
    }

    public void setHorDedicadas(BigInteger horDedicadas) {
        this.horDedicadas = horDedicadas;
    }
    
}
