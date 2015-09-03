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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "DOCENTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Docente.findAll", query = "SELECT d FROM Docente d"),
    @NamedQuery(name = "Docente.findByCodDocente", query = "SELECT d FROM Docente d WHERE d.codDocente = :codDocente"),
    @NamedQuery(name = "Docente.findByCedDocente", query = "SELECT d FROM Docente d WHERE d.cedDocente = :cedDocente"),
    @NamedQuery(name = "Docente.findByNomDocente", query = "SELECT d FROM Docente d WHERE d.nomDocente = :nomDocente"),
    @NamedQuery(name = "Docente.findByCorDocente", query = "SELECT d FROM Docente d WHERE d.corDocente = :corDocente"),
    @NamedQuery(name = "Docente.findByTelDocente", query = "SELECT d FROM Docente d WHERE d.telDocente = :telDocente"),
    @NamedQuery(name = "Docente.findByEstDocente", query = "SELECT d FROM Docente d WHERE d.estDocente = :estDocente"),
    @NamedQuery(name = "Docente.findByDedDocente", query = "SELECT d FROM Docente d WHERE d.dedDocente = :dedDocente")})
public class Docente implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_DOCENTE")
    @SequenceGenerator(name = "SecuenciaDocente", sequenceName = "SEQ_DOCENTE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SecuenciaDocente")
    private String codDocente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CED_DOCENTE")
    private String cedDocente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "NOM_DOCENTE")
    private String nomDocente;
    @Size(max = 100)
    @Column(name = "COR_DOCENTE")
    private String corDocente;
    @Size(max = 50)
    @Column(name = "TEL_DOCENTE")
    private String telDocente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EST_DOCENTE")
    private String estDocente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "DED_DOCENTE")
    private String dedDocente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codDocente")
    private Collection<PlanTrabajo> planTrabajoCollection;
    @JoinColumn(name = "COD_FACULTAD", referencedColumnName = "COD_FACULTAD")
    @ManyToOne(optional = false)
    private Facultad codFacultad;

    public Docente() {
    }

    public Docente(String codDocente) {
        this.codDocente = codDocente;
    }

    public Docente(String codDocente, String cedDocente, String nomDocente, String estDocente, String dedDocente) {
        this.codDocente = codDocente;
        this.cedDocente = cedDocente;
        this.nomDocente = nomDocente;
        this.estDocente = estDocente;
        this.dedDocente = dedDocente;
    }

    public String getCodDocente() {
        return codDocente;
    }

    public void setCodDocente(String codDocente) {
        this.codDocente = codDocente;
    }

    public String getCedDocente() {
        return cedDocente;
    }

    public void setCedDocente(String cedDocente) {
        this.cedDocente = cedDocente;
    }

    public String getNomDocente() {
        return nomDocente;
    }

    public void setNomDocente(String nomDocente) {
        this.nomDocente = nomDocente;
    }

    public String getCorDocente() {
        return corDocente;
    }

    public void setCorDocente(String corDocente) {
        this.corDocente = corDocente;
    }

    public String getTelDocente() {
        return telDocente;
    }

    public void setTelDocente(String telDocente) {
        this.telDocente = telDocente;
    }

    public String getEstDocente() {
        return estDocente;
    }

    public void setEstDocente(String estDocente) {
        this.estDocente = estDocente;
    }

    public String getDedDocente() {
        return dedDocente;
    }

    public void setDedDocente(String dedDocente) {
        this.dedDocente = dedDocente;
    }

    @XmlTransient
    public Collection<PlanTrabajo> getPlanTrabajoCollection() {
        return planTrabajoCollection;
    }

    public void setPlanTrabajoCollection(Collection<PlanTrabajo> planTrabajoCollection) {
        this.planTrabajoCollection = planTrabajoCollection;
    }

    public Facultad getCodFacultad() {
        return codFacultad;
    }

    public void setCodFacultad(Facultad codFacultad) {
        this.codFacultad = codFacultad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codDocente != null ? codDocente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Docente)) {
            return false;
        }
        Docente other = (Docente) object;
        if ((this.codDocente == null && other.codDocente != null) || (this.codDocente != null && !this.codDocente.equals(other.codDocente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.plan_docente.entities.Docente[ codDocente=" + codDocente + " ]";
    }
    
}
