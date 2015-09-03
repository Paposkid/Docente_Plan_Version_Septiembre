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
@Table(name = "MATERIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Materia.findAll", query = "SELECT m FROM Materia m"),
    @NamedQuery(name = "Materia.findByCodMateria", query = "SELECT m FROM Materia m WHERE m.codMateria = :codMateria"),
    @NamedQuery(name = "Materia.findByNomMateria", query = "SELECT m FROM Materia m WHERE m.nomMateria = :nomMateria"),
    @NamedQuery(name = "Materia.findByEstMateria", query = "SELECT m FROM Materia m WHERE m.estMateria = :estMateria"),
    @NamedQuery(name = "Materia.findByCodigoMateria", query = "SELECT m FROM Materia m WHERE m.matCodigoMateria = :matCodigoMateria")})
public class Materia implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "EST_MATERIA")
    private String estMateria;
    @Size(max = 30)
    @Column(name = "MAT_CODIGO_MATERIA")
    private String matCodigoMateria;

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_MATERIA")
    @SequenceGenerator(name = "SecuenciaMateria", sequenceName = "SEQ_MATERIA", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SecuenciaMateria")
    private BigDecimal codMateria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOM_MATERIA")
    private String nomMateria;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codMateria")
    private Collection<DocenciaDirecta> docenciaDirectaCollection;

    public Materia() {
    }

    public Materia(BigDecimal codMateria) {
        this.codMateria = codMateria;
    }

    public Materia(BigDecimal codMateria, String nomMateria, String estMateria) {
        this.codMateria = codMateria;
        this.nomMateria = nomMateria;
        this.estMateria = estMateria;
    }

    public BigDecimal getCodMateria() {
        return codMateria;
    }

    public void setCodMateria(BigDecimal codMateria) {
        this.codMateria = codMateria;
    }

    public String getNomMateria() {
        return nomMateria;
    }

    public void setNomMateria(String nomMateria) {
        this.nomMateria = nomMateria;
    }

    public String getEstMateria() {
        return estMateria;
    }

    public void setEstMateria(String estMateria) {
        this.estMateria = estMateria;
    }

    @XmlTransient
    public Collection<DocenciaDirecta> getDocenciaDirectaCollection() {
        return docenciaDirectaCollection;
    }

    public void setDocenciaDirectaCollection(Collection<DocenciaDirecta> docenciaDirectaCollection) {
        this.docenciaDirectaCollection = docenciaDirectaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codMateria != null ? codMateria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Materia)) {
            return false;
        }
        Materia other = (Materia) object;
        if ((this.codMateria == null && other.codMateria != null) || (this.codMateria != null && !this.codMateria.equals(other.codMateria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.plan.docente.entities.Materia[ codMateria=" + codMateria + " ]";
    }

    public String getMatCodigoMateria() {
        return matCodigoMateria;
    }

    public void setMatCodigoMateria(String matCodigoMateria) {
        this.matCodigoMateria = matCodigoMateria;
    }

}
