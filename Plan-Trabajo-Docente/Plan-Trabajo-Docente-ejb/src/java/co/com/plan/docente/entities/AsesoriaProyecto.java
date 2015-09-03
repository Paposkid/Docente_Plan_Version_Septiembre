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
@Table(name = "ASESORIA_PROYECTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AsesoriaProyecto.findAll", query = "SELECT a FROM AsesoriaProyecto a"),
    @NamedQuery(name = "AsesoriaProyecto.findByCodAsesoria", query = "SELECT a FROM AsesoriaProyecto a WHERE a.codAsesoria = :codAsesoria"),
    @NamedQuery(name = "AsesoriaProyecto.findByNomEstudiante", query = "SELECT a FROM AsesoriaProyecto a WHERE a.nomEstudiante = :nomEstudiante"),
    @NamedQuery(name = "AsesoriaProyecto.findByTitulo", query = "SELECT a FROM AsesoriaProyecto a WHERE a.titulo = :titulo"),
    @NamedQuery(name = "AsesoriaProyecto.findByAspRelevante", query = "SELECT a FROM AsesoriaProyecto a WHERE a.aspRelevante = :aspRelevante"),
    @NamedQuery(name = "AsesoriaProyecto.findByEvaEstudiante", query = "SELECT a FROM AsesoriaProyecto a WHERE a.evaEstudiante = :evaEstudiante"),
    @NamedQuery(name = "AsesoriaProyecto.findByEvaJefe", query = "SELECT a FROM AsesoriaProyecto a WHERE a.evaJefe = :evaJefe")})
public class AsesoriaProyecto implements Serializable {
    @Column(name = "HOR_DEDICADAS")
    private BigInteger horDedicadas;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_ASESORIA")
    private BigDecimal codAsesoria;
    @Basic(optional = false)
    @Column(name = "NOM_ESTUDIANTE")
    private String nomEstudiante;
    @Basic(optional = false)
    @Column(name = "TITULO")
    private String titulo;
    @Column(name = "ASP_RELEVANTE")
    private String aspRelevante;
    @Column(name = "EVA_ESTUDIANTE")
    private String evaEstudiante;
    @Column(name = "EVA_JEFE")
    private String evaJefe;
    @JoinColumn(name = "COD_PLAN_TRABAJO", referencedColumnName = "COD_PLAN_TRABAJO")
    @ManyToOne(optional = false)
    private PlanTrabajo codPlanTrabajo;

    public AsesoriaProyecto() {
    }

    public AsesoriaProyecto(BigDecimal codAsesoria) {
        this.codAsesoria = codAsesoria;
    }

    public AsesoriaProyecto(BigDecimal codAsesoria, String nomEstudiante, String titulo) {
        this.codAsesoria = codAsesoria;
        this.nomEstudiante = nomEstudiante;
        this.titulo = titulo;
    }

    public BigDecimal getCodAsesoria() {
        return codAsesoria;
    }

    public void setCodAsesoria(BigDecimal codAsesoria) {
        this.codAsesoria = codAsesoria;
    }

    public String getNomEstudiante() {
        return nomEstudiante;
    }

    public void setNomEstudiante(String nomEstudiante) {
        this.nomEstudiante = nomEstudiante;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAspRelevante() {
        return aspRelevante;
    }

    public void setAspRelevante(String aspRelevante) {
        this.aspRelevante = aspRelevante;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codAsesoria != null ? codAsesoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsesoriaProyecto)) {
            return false;
        }
        AsesoriaProyecto other = (AsesoriaProyecto) object;
        if ((this.codAsesoria == null && other.codAsesoria != null) || (this.codAsesoria != null && !this.codAsesoria.equals(other.codAsesoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.plan_docente.entities.AsesoriaProyecto[ codAsesoria=" + codAsesoria + " ]";
    }

    public BigInteger getHorDedicadas() {
        return horDedicadas;
    }

    public void setHorDedicadas(BigInteger horDedicadas) {
        this.horDedicadas = horDedicadas;
    }
    
}
