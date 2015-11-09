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
@Table(name = "OTRAS_ACTIVIDADES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OtraActividad.findAll", query = "SELECT o FROM OtraActividad o"),
    @NamedQuery(name = "OtraActividad.findByCodActividad", query = "SELECT o FROM OtraActividad o WHERE o.codActividad = :codActividad"),
    @NamedQuery(name = "OtraActividad.findByNomActividad", query = "SELECT o FROM OtraActividad o WHERE o.nomActividad = :nomActividad"),
    @NamedQuery(name = "OtraActividad.findByHorDedicado", query = "SELECT o FROM OtraActividad o WHERE o.horDedicado = :horDedicado"),
    @NamedQuery(name = "OtraActividad.findByDesActividad", query = "SELECT o FROM OtraActividad o WHERE o.desActividad = :desActividad")})
public class OtraActividad implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @SequenceGenerator(name = "SecuenciaOtrasActividades", sequenceName = "SEQ_OTRAS_ACTIVID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SecuenciaOtrasActividades")
    @Column(name = "COD_ACTIVIDAD")
    private BigDecimal codActividad;
    @Basic(optional = false)
    @Column(name = "NOM_ACTIVIDAD")
    private String nomActividad;
    @Basic(optional = false)
    @Column(name = "HOR_DEDICADO")
    private BigInteger horDedicado;
    @Basic(optional = false)
    @Column(name = "DES_ACTIVIDAD")
    private String desActividad;
    @JoinColumn(name = "COD_PLAN_TRABAJO", referencedColumnName = "COD_PLAN_TRABAJO")
    @ManyToOne(optional = false)
    private PlanTrabajo codPlanTrabajo;

    public OtraActividad() {
    }

    public OtraActividad(BigDecimal codActividad) {
        this.codActividad = codActividad;
    }

    public OtraActividad(BigDecimal codActividad, String nomActividad, BigInteger horDedicado, String desActividad) {
        this.codActividad = codActividad;
        this.nomActividad = nomActividad;
        this.horDedicado = horDedicado;
        this.desActividad = desActividad;
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

    public BigInteger getHorDedicado() {
        return horDedicado;
    }

    public void setHorDedicado(BigInteger horDedicado) {
        this.horDedicado = horDedicado;
    }

    public String getDesActividad() {
        return desActividad;
    }

    public void setDesActividad(String desActividad) {
        this.desActividad = desActividad;
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
        if (!(object instanceof OtraActividad)) {
            return false;
        }
        OtraActividad other = (OtraActividad) object;
        if ((this.codActividad == null && other.codActividad != null) || (this.codActividad != null && !this.codActividad.equals(other.codActividad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.plan_docente.entities.OtraActividad[ codActividad=" + codActividad + " ]";
    }

}
