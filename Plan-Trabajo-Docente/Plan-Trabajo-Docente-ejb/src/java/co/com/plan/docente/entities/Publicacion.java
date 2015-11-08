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
@Table(name = "PUBLICACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Publicacion.findAll", query = "SELECT p FROM Publicacion p"),
    @NamedQuery(name = "Publicacion.findByCodPublicacion", query = "SELECT p FROM Publicacion p WHERE p.codPublicacion = :codPublicacion"),
    @NamedQuery(name = "Publicacion.findByNomArticulo", query = "SELECT p FROM Publicacion p WHERE p.nomArticulo = :nomArticulo"),
    @NamedQuery(name = "Publicacion.findByAutPrincipal", query = "SELECT p FROM Publicacion p WHERE p.autPrincipal = :autPrincipal"),
    @NamedQuery(name = "Publicacion.findByCoautor", query = "SELECT p FROM Publicacion p WHERE p.coautor = :coautor"),
    @NamedQuery(name = "Publicacion.findByTemPrincipal", query = "SELECT p FROM Publicacion p WHERE p.temPrincipal = :temPrincipal")})
public class Publicacion implements Serializable {
    @Column(name = "HOR_DEDICADAS")
    private BigInteger horDedicadas;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_PUBLICACION")
    @SequenceGenerator(name = "SecuenciaPublicacion", sequenceName = "SEQ_PUBLICACIONES", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SecuenciaPublicacion")
    private BigDecimal codPublicacion;
    @Basic(optional = false)
    @Column(name = "NOM_ARTICULO")
    private String nomArticulo;
    @Basic(optional = false)
    @Column(name = "AUT_PRINCIPAL")
    private String autPrincipal;
    @Column(name = "COAUTOR")
    private String coautor;
    @Column(name = "TEM_PRINCIPAL")
    private String temPrincipal;
    @JoinColumn(name = "COD_PLANT_TRABAJO", referencedColumnName = "COD_PLAN_TRABAJO")
    @ManyToOne(optional = false)
    private PlanTrabajo codPlantTrabajo;

    public Publicacion() {
    }

    public Publicacion(BigDecimal codPublicacion) {
        this.codPublicacion = codPublicacion;
    }

    public Publicacion(BigDecimal codPublicacion, String nomArticulo, String autPrincipal) {
        this.codPublicacion = codPublicacion;
        this.nomArticulo = nomArticulo;
        this.autPrincipal = autPrincipal;
    }

    public BigDecimal getCodPublicacion() {
        return codPublicacion;
    }

    public void setCodPublicacion(BigDecimal codPublicacion) {
        this.codPublicacion = codPublicacion;
    }

    public String getNomArticulo() {
        return nomArticulo;
    }

    public void setNomArticulo(String nomArticulo) {
        this.nomArticulo = nomArticulo;
    }

    public String getAutPrincipal() {
        return autPrincipal;
    }

    public void setAutPrincipal(String autPrincipal) {
        this.autPrincipal = autPrincipal;
    }

    public String getCoautor() {
        return coautor;
    }

    public void setCoautor(String coautor) {
        this.coautor = coautor;
    }

    public String getTemPrincipal() {
        return temPrincipal;
    }

    public void setTemPrincipal(String temPrincipal) {
        this.temPrincipal = temPrincipal;
    }

    public PlanTrabajo getCodPlantTrabajo() {
        return codPlantTrabajo;
    }

    public void setCodPlantTrabajo(PlanTrabajo codPlantTrabajo) {
        this.codPlantTrabajo = codPlantTrabajo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codPublicacion != null ? codPublicacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Publicacion)) {
            return false;
        }
        Publicacion other = (Publicacion) object;
        if ((this.codPublicacion == null && other.codPublicacion != null) || (this.codPublicacion != null && !this.codPublicacion.equals(other.codPublicacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.plan_docente.entities.Publicacion[ codPublicacion=" + codPublicacion + " ]";
    }

    public BigInteger getHorDedicadas() {
        return horDedicadas;
    }

    public void setHorDedicadas(BigInteger horDedicadas) {
        this.horDedicadas = horDedicadas;
    }
    
}
