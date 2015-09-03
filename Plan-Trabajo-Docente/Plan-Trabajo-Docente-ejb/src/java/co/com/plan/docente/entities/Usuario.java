/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.plan.docente.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
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
@Table(name = "USUARIOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findAllActivos", query = "SELECT u FROM Usuario u WHERE u.usuEstado = :usuEstado AND u.documentoUsuario = :documentoUsuario"),
    @NamedQuery(name = "Usuario.findByCodigoUsuario", query = "SELECT u FROM Usuario u WHERE u.codigoUsuario = :codigoUsuario"),
    @NamedQuery(name = "Usuario.findByNombreUsuario", query = "SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario"),
    @NamedQuery(name = "Usuario.findByNombrePassword", query = "SELECT u FROM Usuario u WHERE TRIM(LOWER(u.nombreUsuario)) = TRIM(LOWER(:nombreUsuario)) AND u.passwordUsuario = :passwordUsuario"),
    @NamedQuery(name = "Usuario.findByApellidoUsuario", query = "SELECT u FROM Usuario u WHERE u.apellidoUsuario = :apellidoUsuario"),
    @NamedQuery(name = "Usuario.findByCorreoUsuario", query = "SELECT u FROM Usuario u WHERE u.correoUsuario = :correoUsuario"),
    @NamedQuery(name = "Usuario.findByDocumentoUsuario", query = "SELECT u FROM Usuario u WHERE u.documentoUsuario = :documentoUsuario"),
    @NamedQuery(name = "Usuario.findByPasswordUsuario", query = "SELECT u FROM Usuario u WHERE u.passwordUsuario = :passwordUsuario"),
    @NamedQuery(name = "Usuario.findByFechaIngresoUsuario", query = "SELECT u FROM Usuario u WHERE u.fechaIngresoUsuario = :fechaIngresoUsuario"),
    @NamedQuery(name = "Usuario.findByPerfilUsuario", query = "SELECT u FROM Usuario u WHERE u.perfilUsuario = :perfilUsuario")})
public class Usuario implements Serializable {
    @Column(name = "USU_ESTADO")
    private BigInteger usuEstado;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_USUARIO")
    @SequenceGenerator(name = "SecuenciaUsuario", sequenceName = "SEQ_USUARIO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SecuenciaUsuario")
    private BigDecimal codigoUsuario;
    @Size(max = 200)
    @Column(name = "NOMBRE_USUARIO")
    private String nombreUsuario;
    @Size(max = 200)
    @Column(name = "APELLIDO_USUARIO")
    private String apellidoUsuario;
    @Size(max = 100)
    @Column(name = "CORREO_USUARIO")
    private String correoUsuario;
    @Column(name = "DOCUMENTO_USUARIO")
    private BigInteger documentoUsuario;
    @Size(max = 30)
    @Column(name = "PASSWORD_USUARIO")
    private String passwordUsuario;
    @Column(name = "FECHA_INGRESO_USUARIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngresoUsuario;
    @Column(name = "PERFIL_USUARIO")
    private BigInteger perfilUsuario;
    @JoinColumn(name = "CARGO_USUARIO", referencedColumnName = "CODIGO_CARGO")
    @ManyToOne
    private Cargo cargoUsuario;

    public Usuario() {
    }

    public Usuario(BigDecimal codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public BigDecimal getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(BigDecimal codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidoUsuario() {
        return apellidoUsuario;
    }

    public void setApellidoUsuario(String apellidoUsuario) {
        this.apellidoUsuario = apellidoUsuario;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public BigInteger getDocumentoUsuario() {
        return documentoUsuario;
    }

    public void setDocumentoUsuario(BigInteger documentoUsuario) {
        this.documentoUsuario = documentoUsuario;
    }

    public String getPasswordUsuario() {
        return passwordUsuario;
    }

    public void setPasswordUsuario(String passwordUsuario) {
        this.passwordUsuario = passwordUsuario;
    }

    public Date getFechaIngresoUsuario() {
        return fechaIngresoUsuario;
    }

    public void setFechaIngresoUsuario(Date fechaIngresoUsuario) {
        this.fechaIngresoUsuario = fechaIngresoUsuario;
    }

    public BigInteger getPerfilUsuario() {
        return perfilUsuario;
    }

    public void setPerfilUsuario(BigInteger perfilUsuario) {
        this.perfilUsuario = perfilUsuario;
    }

    public Cargo getCargoUsuario() {
        return cargoUsuario;
    }

    public void setCargoUsuario(Cargo cargoUsuario) {
        this.cargoUsuario = cargoUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoUsuario != null ? codigoUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.codigoUsuario == null && other.codigoUsuario != null) || (this.codigoUsuario != null && !this.codigoUsuario.equals(other.codigoUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.plan.docente.entities.Usuario[ codigoUsuario=" + codigoUsuario + " ]";
    }

    public BigInteger getUsuEstado() {
        return usuEstado;
    }

    public void setUsuEstado(BigInteger usuEstado) {
        this.usuEstado = usuEstado;
    }
    
}
