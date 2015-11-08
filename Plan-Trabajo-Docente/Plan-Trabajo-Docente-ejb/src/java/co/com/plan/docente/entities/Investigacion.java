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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 727855
 */
@Entity
@Table(name = "INVESTIGACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Investigacion.findAll", query = "SELECT i FROM Investigacion i"),
    @NamedQuery(name = "Investigacion.findByCodInvestigacion", query = "SELECT i FROM Investigacion i WHERE i.codInvestigacion = :codInvestigacion"),
    @NamedQuery(name = "Investigacion.findByNomSemillero", query = "SELECT i FROM Investigacion i WHERE i.nomSemillero = :nomSemillero"),
    @NamedQuery(name = "Investigacion.findByTipVinculo", query = "SELECT i FROM Investigacion i WHERE i.tipVinculo = :tipVinculo"),
    @NamedQuery(name = "Investigacion.findByActDesarrollada", query = "SELECT i FROM Investigacion i WHERE i.actDesarrollada = :actDesarrollada"),
    @NamedQuery(name = "Investigacion.findByActProductos", query = "SELECT i FROM Investigacion i WHERE i.actProductos = :actProductos"),
    @NamedQuery(name = "Investigacion.findByHorSemanal", query = "SELECT i FROM Investigacion i WHERE i.horSemanal = :horSemanal"),
    @NamedQuery(name = "Investigacion.findByVobo", query = "SELECT i FROM Investigacion i WHERE i.vobo = :vobo")})
public class Investigacion implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_INVESTIGACION")
    @SequenceGenerator(name = "SecuenciaInvestigacion", sequenceName = "SEQ_INVESTIGACION", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SecuenciaInvestigacion")
    private BigDecimal codInvestigacion;
    @Basic(optional = false)
    @Column(name = "NOM_SEMILLERO")
    private String nomSemillero;
    @Basic(optional = false)
    @Column(name = "TIP_VINCULO")
    private String tipVinculo;
    @Basic(optional = false)
    @Column(name = "ACT_DESARROLLADA")
    private String actDesarrollada;
    @Basic(optional = false)
    @Column(name = "ACT_PRODUCTOS")
    private String actProductos;
    @Basic(optional = false)
    @Column(name = "HOR_SEMANAL")
    private BigInteger horSemanal;
    @Column(name = "VOBO")
    private String vobo;
    @JoinColumn(name = "COD_PLAN_TRABAJO", referencedColumnName = "COD_PLAN_TRABAJO")
    @ManyToOne(optional = false)
    private PlanTrabajo codPlanTrabajo;

    public Investigacion() {
    }

    public Investigacion(BigDecimal codInvestigacion) {
        this.codInvestigacion = codInvestigacion;
    }

    public Investigacion(BigDecimal codInvestigacion, String nomSemillero, String tipVinculo, String actDesarrollada, String actProductos, BigInteger horSemanal) {
        this.codInvestigacion = codInvestigacion;
        this.nomSemillero = nomSemillero;
        this.tipVinculo = tipVinculo;
        this.actDesarrollada = actDesarrollada;
        this.actProductos = actProductos;
        this.horSemanal = horSemanal;
    }

    public BigDecimal getCodInvestigacion() {
        return codInvestigacion;
    }

    public void setCodInvestigacion(BigDecimal codInvestigacion) {
        this.codInvestigacion = codInvestigacion;
    }

    public String getNomSemillero() {
        return nomSemillero;
    }

    public void setNomSemillero(String nomSemillero) {
        this.nomSemillero = nomSemillero;
    }

    public String getTipVinculo() {
        return tipVinculo;
    }

    public void setTipVinculo(String tipVinculo) {
        this.tipVinculo = tipVinculo;
    }

    public String getActDesarrollada() {
        return actDesarrollada;
    }

    public void setActDesarrollada(String actDesarrollada) {
        this.actDesarrollada = actDesarrollada;
    }

    public String getActProductos() {
        return actProductos;
    }

    public void setActProductos(String actProductos) {
        this.actProductos = actProductos;
    }

    public BigInteger getHorSemanal() {
        return horSemanal;
    }

    public void setHorSemanal(BigInteger horSemanal) {
        this.horSemanal = horSemanal;
    }

    public String getVobo() {
        return vobo;
    }

    public void setVobo(String vobo) {
        this.vobo = vobo;
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
        hash += (codInvestigacion != null ? codInvestigacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Investigacion)) {
            return false;
        }
        Investigacion other = (Investigacion) object;
        if ((this.codInvestigacion == null && other.codInvestigacion != null) || (this.codInvestigacion != null && !this.codInvestigacion.equals(other.codInvestigacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.plan_docente.entities.Investigacion[ codInvestigacion=" + codInvestigacion + " ]";
    }
    
}
