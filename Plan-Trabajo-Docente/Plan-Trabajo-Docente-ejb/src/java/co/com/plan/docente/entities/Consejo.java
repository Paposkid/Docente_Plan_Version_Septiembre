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
@Table(name = "CONSEJO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Consejo.findAll", query = "SELECT c FROM Consejo c"),
    @NamedQuery(name = "Consejo.findByCodConsejo", query = "SELECT c FROM Consejo c WHERE c.codConsejo = :codConsejo"),
    @NamedQuery(name = "Consejo.findByCedConsejo", query = "SELECT c FROM Consejo c WHERE c.cedConsejo = :cedConsejo"),
    @NamedQuery(name = "Consejo.findByNomConsejo", query = "SELECT c FROM Consejo c WHERE c.nomConsejo = :nomConsejo"),
    @NamedQuery(name = "Consejo.findByCorConsejo", query = "SELECT c FROM Consejo c WHERE c.corConsejo = :corConsejo"),
    @NamedQuery(name = "Consejo.findByTelCoordinador", query = "SELECT c FROM Consejo c WHERE c.telCoordinador = :telCoordinador")})
public class Consejo implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @SequenceGenerator(name = "SecuenciaConsejo", sequenceName = "SEQ_CONSEJO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SecuenciaConsejo")
    @Column(name = "COD_CONSEJO")
    private BigDecimal codConsejo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CED_CONSEJO")
    private String cedConsejo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "NOM_CONSEJO")
    private String nomConsejo;
    @Size(max = 100)
    @Column(name = "COR_CONSEJO")
    private String corConsejo;
    @Size(max = 50)
    @Column(name = "TEL_COORDINADOR")
    private String telCoordinador;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codConsejo")
    private Collection<PlanTrabajo> planTrabajoCollection;

    public Consejo() {
    }

    public Consejo(BigDecimal codConsejo) {
        this.codConsejo = codConsejo;
    }

    public Consejo(BigDecimal codConsejo, String cedConsejo, String nomConsejo) {
        this.codConsejo = codConsejo;
        this.cedConsejo = cedConsejo;
        this.nomConsejo = nomConsejo;
    }

    public BigDecimal getCodConsejo() {
        return codConsejo;
    }

    public void setCodConsejo(BigDecimal codConsejo) {
        this.codConsejo = codConsejo;
    }

    public String getCedConsejo() {
        return cedConsejo;
    }

    public void setCedConsejo(String cedConsejo) {
        this.cedConsejo = cedConsejo;
    }

    public String getNomConsejo() {
        return nomConsejo;
    }

    public void setNomConsejo(String nomConsejo) {
        this.nomConsejo = nomConsejo;
    }

    public String getCorConsejo() {
        return corConsejo;
    }

    public void setCorConsejo(String corConsejo) {
        this.corConsejo = corConsejo;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codConsejo != null ? codConsejo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Consejo)) {
            return false;
        }
        Consejo other = (Consejo) object;
        if ((this.codConsejo == null && other.codConsejo != null) || (this.codConsejo != null && !this.codConsejo.equals(other.codConsejo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.plan_docente.entities.Consejo[ codConsejo=" + codConsejo + " ]";
    }

}
