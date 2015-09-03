/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.plan.docente.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 727855
 */
@Entity
@Table(name = "PARAMETROS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parametro.findAll", query = "SELECT p FROM Parametro p"),
    @NamedQuery(name = "Parametro.findByParDescripcion", query = "SELECT p FROM Parametro p WHERE p.parDescripcion = :parDescripcion"),
    @NamedQuery(name = "Parametro.findByParValor", query = "SELECT p FROM Parametro p WHERE p.parValor = :parValor"),
    @NamedQuery(name = "Parametro.findByParFechaModificacion", query = "SELECT p FROM Parametro p WHERE p.parFechaModificacion = :parFechaModificacion"),
    @NamedQuery(name = "Parametro.findByParUsuarioModificaci\u00f3n", query = "SELECT p FROM Parametro p WHERE p.parUsuarioModificaci\u00f3n = :parUsuarioModificaci\u00f3n"),
    @NamedQuery(name = "Parametro.findByParId", query = "SELECT p FROM Parametro p WHERE p.parId = :parId")})
public class Parametro implements Serializable {
    private static final long serialVersionUID = 1L;
    @Size(max = 200)
    @Column(name = "PAR_DESCRIPCION")
    private String parDescripcion;
    @Size(max = 20)
    @Column(name = "PAR_VALOR")
    private String parValor;
    @Column(name = "PAR_FECHA_MODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date parFechaModificacion;
    @Size(max = 20)
    @Column(name = "PAR_USUARIO_MODIFICACI\u00d3N")
    private String parUsuarioModificación;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PAR_ID")
    private BigDecimal parId;

    public Parametro() {
    }

    public Parametro(BigDecimal parId) {
        this.parId = parId;
    }

    public String getParDescripcion() {
        return parDescripcion;
    }

    public void setParDescripcion(String parDescripcion) {
        this.parDescripcion = parDescripcion;
    }

    public String getParValor() {
        return parValor;
    }

    public void setParValor(String parValor) {
        this.parValor = parValor;
    }

    public Date getParFechaModificacion() {
        return parFechaModificacion;
    }

    public void setParFechaModificacion(Date parFechaModificacion) {
        this.parFechaModificacion = parFechaModificacion;
    }

    public String getParUsuarioModificación() {
        return parUsuarioModificación;
    }

    public void setParUsuarioModificación(String parUsuarioModificación) {
        this.parUsuarioModificación = parUsuarioModificación;
    }

    public BigDecimal getParId() {
        return parId;
    }

    public void setParId(BigDecimal parId) {
        this.parId = parId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (parId != null ? parId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parametro)) {
            return false;
        }
        Parametro other = (Parametro) object;
        if ((this.parId == null && other.parId != null) || (this.parId != null && !this.parId.equals(other.parId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.plan.docente.entities.Parametro[ parId=" + parId + " ]";
    }
    
}
