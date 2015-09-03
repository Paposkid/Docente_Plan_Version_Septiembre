/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.plan.docente.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 727855
 */
@Entity
@Table(name = "DOCENCIA_DIRECTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DocenciaDirecta.findAll", query = "SELECT d FROM DocenciaDirecta d"),
    @NamedQuery(name = "DocenciaDirecta.findByCodDocencia", query = "SELECT d FROM DocenciaDirecta d WHERE d.codDocencia = :codDocencia"),
    @NamedQuery(name = "DocenciaDirecta.findByGrupo", query = "SELECT d FROM DocenciaDirecta d WHERE d.grupo = :grupo"),
    @NamedQuery(name = "DocenciaDirecta.findByNmrEstudiantes", query = "SELECT d FROM DocenciaDirecta d WHERE d.nmrEstudiantes = :nmrEstudiantes"),
    @NamedQuery(name = "DocenciaDirecta.findByHorSemanal", query = "SELECT d FROM DocenciaDirecta d WHERE d.horSemanal = :horSemanal"),
    @NamedQuery(name = "DocenciaDirecta.findByEvaEstudiante", query = "SELECT d FROM DocenciaDirecta d WHERE d.evaEstudiante = :evaEstudiante"),
    @NamedQuery(name = "DocenciaDirecta.findByEvaJefe", query = "SELECT d FROM DocenciaDirecta d WHERE d.evaJefe = :evaJefe")})
public class DocenciaDirecta implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_DOCENCIA")
    private BigDecimal codDocencia;
    @Basic(optional = false)
    @Column(name = "GRUPO")
    private String grupo;
    @Basic(optional = false)
    @Column(name = "NMR_ESTUDIANTES")
    private BigInteger nmrEstudiantes;
    @Basic(optional = false)
    @Column(name = "HOR_SEMANAL")
    private BigInteger horSemanal;
    @Size(max = 50)
    @Column(name = "EVA_ESTUDIANTE")
    private String evaEstudiante;
    @Size(max = 50)
    @Column(name = "EVA_JEFE")
    private String evaJefe;
    @JoinColumn(name = "COD_PLAN_TRABAJO", referencedColumnName = "COD_PLAN_TRABAJO")
    @ManyToOne(optional = false)
    private PlanTrabajo codPlanTrabajo;
    @JoinColumn(name = "COD_MATERIA", referencedColumnName = "COD_MATERIA")
    @ManyToOne(optional = false)
    private Materia codMateria;

    public DocenciaDirecta() {
    }

    public DocenciaDirecta(BigDecimal codDocencia) {
        this.codDocencia = codDocencia;
    }

    public DocenciaDirecta(BigDecimal codDocencia, String grupo, BigInteger nmrEstudiantes, BigInteger horSemanal) {
        this.codDocencia = codDocencia;
        this.grupo = grupo;
        this.nmrEstudiantes = nmrEstudiantes;
        this.horSemanal = horSemanal;
    }

    public BigDecimal getCodDocencia() {
        return codDocencia;
    }

    public void setCodDocencia(BigDecimal codDocencia) {
        this.codDocencia = codDocencia;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public BigInteger getNmrEstudiantes() {
        return nmrEstudiantes;
    }

    public void setNmrEstudiantes(BigInteger nmrEstudiantes) {
        this.nmrEstudiantes = nmrEstudiantes;
    }

    public BigInteger getHorSemanal() {
        return horSemanal;
    }

    public void setHorSemanal(BigInteger horSemanal) {
        this.horSemanal = horSemanal;
    }

    public String getEvaEstudiante() {
        return evaEstudiante;
    }

    public void setEvaEstudiante(String evaEstudiante) {
        this.evaEstudiante = evaEstudiante;
    }

    public String getEvaJefe() {
        return evaJefe;
    }

    public void setEvaJefe(String evaJefe) {
        this.evaJefe = evaJefe;
    }

    public PlanTrabajo getCodPlanTrabajo() {
        return codPlanTrabajo;
    }

    public void setCodPlanTrabajo(PlanTrabajo codPlanTrabajo) {
        this.codPlanTrabajo = codPlanTrabajo;
    }

    public Materia getCodMateria() {
        return codMateria;
    }

    public void setCodMateria(Materia codMateria) {
        this.codMateria = codMateria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codDocencia != null ? codDocencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocenciaDirecta)) {
            return false;
        }
        DocenciaDirecta other = (DocenciaDirecta) object;
        if ((this.codDocencia == null && other.codDocencia != null) || (this.codDocencia != null && !this.codDocencia.equals(other.codDocencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.plan_docente.entities.DocenciaDirecta[ codDocencia=" + codDocencia + " ]";
    }
    
}
