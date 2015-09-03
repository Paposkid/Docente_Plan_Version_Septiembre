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
@Table(name = "ACTIVIDAD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actividad.findAll", query = "SELECT a FROM Actividad a"),
    @NamedQuery(name = "Actividad.findByCodActividad", query = "SELECT a FROM Actividad a WHERE a.codActividad = :codActividad"),
    @NamedQuery(name = "Actividad.findByNomActividad", query = "SELECT a FROM Actividad a WHERE a.nomActividad = :nomActividad"),
    @NamedQuery(name = "Actividad.findBySiglas", query = "SELECT a FROM Actividad a WHERE a.siglas = :siglas")})
public class Actividad implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_ACTIVIDAD")
    private BigDecimal codActividad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "NOM_ACTIVIDAD")
    private String nomActividad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "SIGLAS")
    private String siglas;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codActividad")
    private Collection<DistribucionActividad> distribucionActividadCollection;

    public Actividad() {
    }

    public Actividad(BigDecimal codActividad) {
        this.codActividad = codActividad;
    }

    public Actividad(BigDecimal codActividad, String nomActividad, String siglas) {
        this.codActividad = codActividad;
        this.nomActividad = nomActividad;
        this.siglas = siglas;
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

    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }

    @XmlTransient
    public Collection<DistribucionActividad> getDistribucionActividadCollection() {
        return distribucionActividadCollection;
    }

    public void setDistribucionActividadCollection(Collection<DistribucionActividad> distribucionActividadCollection) {
        this.distribucionActividadCollection = distribucionActividadCollection;
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
        if (!(object instanceof Actividad)) {
            return false;
        }
        Actividad other = (Actividad) object;
        if ((this.codActividad == null && other.codActividad != null) || (this.codActividad != null && !this.codActividad.equals(other.codActividad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.plan_docente.entities.Actividad[ codActividad=" + codActividad + " ]";
    }
    
}
