/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.plan.docente.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 727855
 */
@Entity
@Table(name = "COORDINADOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Coordinador.findAll", query = "SELECT c FROM Coordinador c"),
    @NamedQuery(name = "Coordinador.findByCodCoordinador", query = "SELECT c FROM Coordinador c WHERE c.codCoordinador = :codCoordinador"),
    @NamedQuery(name = "Coordinador.findByCedCoordinador", query = "SELECT c FROM Coordinador c WHERE c.cedCoordinador = :cedCoordinador"),
    @NamedQuery(name = "Coordinador.findByNomCoordinador", query = "SELECT c FROM Coordinador c WHERE c.nomCoordinador = :nomCoordinador"),
    @NamedQuery(name = "Coordinador.findByCorCoordinador", query = "SELECT c FROM Coordinador c WHERE c.corCoordinador = :corCoordinador"),
    @NamedQuery(name = "Coordinador.findByTelCoordinador", query = "SELECT c FROM Coordinador c WHERE c.telCoordinador = :telCoordinador")})
public class Coordinador implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_COORDINADOR")
    private BigDecimal codCoordinador;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CED_COORDINADOR")
    private String cedCoordinador;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "NOM_COORDINADOR")
    private String nomCoordinador;
    @Size(max = 100)
    @Column(name = "COR_COORDINADOR")
    private String corCoordinador;
    @Size(max = 50)
    @Column(name = "TEL_COORDINADOR")
    private String telCoordinador;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codCoordinador")
    private Collection<PlanTrabajo> planTrabajoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codCoordinador")
    private Collection<Seguimiento> seguimientoCollection;

    public Coordinador() {
    }

    public Coordinador(BigDecimal codCoordinador) {
        this.codCoordinador = codCoordinador;
    }

    public Coordinador(BigDecimal codCoordinador, String cedCoordinador, String nomCoordinador) {
        this.codCoordinador = codCoordinador;
        this.cedCoordinador = cedCoordinador;
        this.nomCoordinador = nomCoordinador;
    }

    public BigDecimal getCodCoordinador() {
        return codCoordinador;
    }

    public void setCodCoordinador(BigDecimal codCoordinador) {
        this.codCoordinador = codCoordinador;
    }

    public String getCedCoordinador() {
        return cedCoordinador;
    }

    public void setCedCoordinador(String cedCoordinador) {
        this.cedCoordinador = cedCoordinador;
    }

    public String getNomCoordinador() {
        return nomCoordinador;
    }

    public void setNomCoordinador(String nomCoordinador) {
        this.nomCoordinador = nomCoordinador;
    }

    public String getCorCoordinador() {
        return corCoordinador;
    }

    public void setCorCoordinador(String corCoordinador) {
        this.corCoordinador = corCoordinador;
    }

    public String getTelCoordinador() {
        return telCoordinador;
    }

    public void setTelCoordinador(String telCoordinador) {
        this.telCoordinador = telCoordinador;
    }

    @XmlTransient
    public Collection<PlanTrabajo> getPlanTrabajoCollection() {
        return planTrabajoCollection;
    }

    public void setPlanTrabajoCollection(Collection<PlanTrabajo> planTrabajoCollection) {
        this.planTrabajoCollection = planTrabajoCollection;
    }

    @XmlTransient
    public Collection<Seguimiento> getSeguimientoCollection() {
        return seguimientoCollection;
    }

    public void setSeguimientoCollection(Collection<Seguimiento> seguimientoCollection) {
        this.seguimientoCollection = seguimientoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codCoordinador != null ? codCoordinador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Coordinador)) {
            return false;
        }
        Coordinador other = (Coordinador) object;
        if ((this.codCoordinador == null && other.codCoordinador != null) || (this.codCoordinador != null && !this.codCoordinador.equals(other.codCoordinador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.plan_docente.entities.Coordinador[ codCoordinador=" + codCoordinador + " ]";
    }
    
}
