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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
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
@Table(name = "FACULTAD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Facultad.findAll", query = "SELECT f FROM Facultad f"),
    @NamedQuery(name = "Facultad.findByCodFacultad", query = "SELECT f FROM Facultad f WHERE f.codFacultad = :codFacultad"),
    @NamedQuery(name = "Facultad.findByNomFacultad", query = "SELECT f FROM Facultad f WHERE f.nomFacultad = :nomFacultad"),
    @NamedQuery(name = "Facultad.findByEstFacultad", query = "SELECT f FROM Facultad f WHERE f.estFacultad = :estFacultad")})
public class Facultad implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_FACULTAD")
    @SequenceGenerator(name = "SecuenciaFacultad", sequenceName = "SEQ_FACULTAD", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SecuenciaFacultad")
    private BigDecimal codFacultad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "NOM_FACULTAD")
    private String nomFacultad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EST_FACULTAD")
    private Character estFacultad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codFacultad")
    private Collection<Docente> docenteCollection;

    public Facultad() {
    }

    public Facultad(BigDecimal codFacultad) {
        this.codFacultad = codFacultad;
    }

    public Facultad(BigDecimal codFacultad, String nomFacultad, Character estFacultad) {
        this.codFacultad = codFacultad;
        this.nomFacultad = nomFacultad;
        this.estFacultad = estFacultad;
    }

    public BigDecimal getCodFacultad() {
        return codFacultad;
    }

    public void setCodFacultad(BigDecimal codFacultad) {
        this.codFacultad = codFacultad;
    }

    public String getNomFacultad() {
        return nomFacultad;
    }

    public void setNomFacultad(String nomFacultad) {
        this.nomFacultad = nomFacultad;
    }

    public Character getEstFacultad() {
        return estFacultad;
    }

    public void setEstFacultad(Character estFacultad) {
        this.estFacultad = estFacultad;
    }

    @XmlTransient
    public Collection<Docente> getDocenteCollection() {
        return docenteCollection;
    }

    public void setDocenteCollection(Collection<Docente> docenteCollection) {
        this.docenteCollection = docenteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codFacultad != null ? codFacultad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Facultad)) {
            return false;
        }
        Facultad other = (Facultad) object;
        if ((this.codFacultad == null && other.codFacultad != null) || (this.codFacultad != null && !this.codFacultad.equals(other.codFacultad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.plan_docente.entities.Facultad[ codFacultad=" + codFacultad + " ]";
    }

}
