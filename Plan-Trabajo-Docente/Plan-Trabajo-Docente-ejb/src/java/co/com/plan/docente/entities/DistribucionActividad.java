/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.plan.docente.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "DISTRIBUCION_ACTIVIDAD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DistribucionActividad.findAll", query = "SELECT d FROM DistribucionActividad d"),
    @NamedQuery(name = "DistribucionActividad.findByCodDistribucion", query = "SELECT d FROM DistribucionActividad d WHERE d.codDistribucion = :codDistribucion"),
    @NamedQuery(name = "DistribucionActividad.findByHora", query = "SELECT d FROM DistribucionActividad d WHERE d.hora = :hora"),
    @NamedQuery(name = "DistribucionActividad.findByDia", query = "SELECT d FROM DistribucionActividad d WHERE d.dia = :dia")})
public class DistribucionActividad implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_DISTRIBUCION")
    private BigDecimal codDistribucion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "HORA")
    private String hora;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "DIA")
    private String dia;
    @JoinColumn(name = "COD_PLAN_TRABAJO", referencedColumnName = "COD_PLAN_TRABAJO")
    @ManyToOne(optional = false)
    private PlanTrabajo codPlanTrabajo;
    @JoinColumn(name = "COD_ACTIVIDAD", referencedColumnName = "COD_ACTIVIDAD")
    @ManyToOne(optional = false)
    private Actividad codActividad;

    public DistribucionActividad() {
    }

    public DistribucionActividad(BigDecimal codDistribucion) {
        this.codDistribucion = codDistribucion;
    }

    public DistribucionActividad(BigDecimal codDistribucion, String hora, String dia) {
        this.codDistribucion = codDistribucion;
        this.hora = hora;
        this.dia = dia;
    }

    public BigDecimal getCodDistribucion() {
        return codDistribucion;
    }

    public void setCodDistribucion(BigDecimal codDistribucion) {
        this.codDistribucion = codDistribucion;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public PlanTrabajo getCodPlanTrabajo() {
        return codPlanTrabajo;
    }

    public void setCodPlanTrabajo(PlanTrabajo codPlanTrabajo) {
        this.codPlanTrabajo = codPlanTrabajo;
    }

    public Actividad getCodActividad() {
        return codActividad;
    }

    public void setCodActividad(Actividad codActividad) {
        this.codActividad = codActividad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codDistribucion != null ? codDistribucion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DistribucionActividad)) {
            return false;
        }
        DistribucionActividad other = (DistribucionActividad) object;
        if ((this.codDistribucion == null && other.codDistribucion != null) || (this.codDistribucion != null && !this.codDistribucion.equals(other.codDistribucion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.plan_docente.entities.DistribucionActividad[ codDistribucion=" + codDistribucion + " ]";
    }
    
}
