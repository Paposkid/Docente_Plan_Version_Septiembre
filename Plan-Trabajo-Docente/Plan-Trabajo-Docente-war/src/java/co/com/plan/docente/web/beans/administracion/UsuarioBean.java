/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.plan.docente.web.beans.administracion;

import co.com.plan.docente.entities.Cargo;
import co.com.plan.docente.entities.Usuario;
import co.com.plan.docente.forentities.CargoFacadeLocal;
import co.com.plan.docente.forentities.UsuarioFacadeLocal;
import co.com.plan.docente.web.util.AccionesGenerico;
import co.com.plan.docente.web.util.CSVExporter;
import co.com.plan.docente.web.util.JSFUtils;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.primefaces.component.datatable.DataTable;

/**
 *
 * @author 727855
 */
@ManagedBean
@ViewScoped
public class UsuarioBean {

    /**
     * Creates a new instance of UsuarioBean
     */
    public UsuarioBean() {
    }
    
      private static final long serialVersionUID = 1L;

    private Usuario usu;
    private List<Usuario> usuarios;
    private List<Cargo> cargos;
    private AccionesGenerico<Usuario> accionesUsuario;
    private Converter cargoConverter;
   //private Converter perfilConverter;
    
    @EJB
    private UsuarioFacadeLocal persistenciaUsu;
    @EJB
    private CargoFacadeLocal persistenciaCargo;
    /*@EJB
    private CargoFacadeLocal persistenciaCargo;*/
    @ManagedProperty("#{msg}")
    private ResourceBundle msg;

    public Converter getCargoConverter() {
         if (this.cargoConverter == null) {
            this.cargoConverter = new Converter() {

                @Override
                public Object getAsObject(FacesContext context, UIComponent component, String value) {
                    Cargo retorno = null;

                    if (value != null && !value.isEmpty()
                            && !value.trim().equals("[TODOS]")
                            && !value.trim().equals("[NINGUNO]")) {
                        BigDecimal loteId = new BigDecimal(value);
                        for (Cargo carg : getCargos()) {
                            if (carg.getCodigoCargo().compareTo(loteId) == 0) {
                                retorno = carg;
                                break;
                            }
                        }
                    }
                    return retorno;
                }

                @Override
                public String getAsString(FacesContext context, UIComponent component, Object value) {
                    String retorno = null;

                    if (value != null && value instanceof Cargo) {
                        retorno = ((Cargo) value).getCodigoCargo().toString();
                    }
                    return retorno;
                }
            };
        }
        return cargoConverter;
    }

    public void setCargoConverter(Converter cargoConverter) {
        this.cargoConverter = cargoConverter;
    }

    /*public Converter getPerfilConverter() {
         if (this.perfilConverter == null) {
            this.perfilConverter = new Converter() {

                @Override
                public Object getAsObject(FacesContext context, UIComponent component, String value) {
                    Perfiles retorno = null;

                    if (value != null && !value.isEmpty()
                            && !value.trim().equals("[TODOS]")
                            && !value.trim().equals("[NINGUNO]")) {
                        BigDecimal loteId = new BigDecimal(value);
                        for (Perfiles perfi : getPerfiles()) {
                            if (perfi.getCodigoPerfil().compareTo(loteId) == 0) {
                                retorno = perfi;
                                break;
                            }
                        }
                    }

                    return retorno;
                }

                @Override
                public String getAsString(FacesContext context, UIComponent component, Object value) {
                    String retorno = null;

                    if (value != null && value instanceof Perfiles) {
                        retorno = ((Perfiles) value).getCodigoPerfil().toString();
                    }

                    return retorno;
                }
            };
        }
        return perfilConverter;
    }*/

  /*  public void setPerfilConverter(Converter perfilConverter) {
        this.perfilConverter = perfilConverter;
    }*/

    public List<Cargo> getCargos() {
        if (cargos == null) {
            cargos = persistenciaCargo.findAll();
        }
        return cargos;
    }

    public void setCargos(List<Cargo> cargos) {
        this.cargos = cargos;
    }

  /*  public List<Perfiles> getPerfiles() {
        if (perfiles == null) {
            perfiles = persistenciaPerfiles.findAll();
        }
        return perfiles;
    }

    public void setPerfiles(List<Perfiles> perfiles) {
        this.perfiles = perfiles;
    }*/

    public Usuario getUsu() {
        return usu;
    }

    public void setUsu(Usuario usu) {
        this.usu = usu;
    }

    public List<Usuario> getUsuarios() {
        if (usuarios == null) {
            usuarios = persistenciaUsu.findAll();
        }
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public AccionesGenerico<Usuario> getAccionesUsuario() {
        if (this.accionesUsuario == null) {
            this.accionesUsuario = new co.com.plan.docente.web.util.AccionesGenerico<Usuario>() {
                @Override
                public void eliminarGenerico(Usuario registro) {
                    persistenciaUsu.remove(registro);
                    limpiarUsu();
                }

                @Override
                public void crearGenerico(Usuario registro) {
                    persistenciaUsu.create(registro);
                    limpiarUsu();
                }

                @Override
                public void actualizarGenerico(Usuario registro) {
                    persistenciaUsu.edit(registro);
                    limpiarUsu();
                }

                @Override
                public boolean isEmptyGenerico(Usuario registro) {
                    return (registro == null || registro.getCodigoUsuario() == null);
                }

                @Override
                public String getMsgTexto() {
                    return "entidad.usuario";
                }

            };
        }
        return accionesUsuario;
    }

    public void setAccionesUsuario(AccionesGenerico<Usuario> accionesUsuario) {
        this.accionesUsuario = accionesUsuario;
    }

    public ResourceBundle getMsg() {
        return msg;
    }

    public void setMsg(ResourceBundle msg) {
        this.msg = msg;
    }

    private void limpiarUsu() {
        this.usu = null;
        this.usuarios = null;
    }

    public void nuevoUsu() {
        this.usu = new Usuario();
    }

    public void eliminarUsu() {
        try {
            AccionesGenerico.eliminarGenerico(getAccionesUsuario(), this.usu, msg);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void aplicarCambiosUsu() {
        try {
            AccionesGenerico.aplicarCambiosGenerico(getAccionesUsuario(), this.usu, msg);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public void descargarCSV(String datatableId, String nombreArchivo) throws IOException {
        DataTable dataTable = (DataTable) JSFUtils.findComponent(FacesContext.getCurrentInstance().getViewRoot(), datatableId);
        CSVExporter csvExporter = new CSVExporter();
        csvExporter.export(FacesContext.getCurrentInstance(), dataTable, nombreArchivo, false, false, "UTF-8", null, null);
    }

}
