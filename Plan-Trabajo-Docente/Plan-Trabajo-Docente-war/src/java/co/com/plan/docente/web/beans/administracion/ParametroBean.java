/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.plan.docente.web.beans.administracion;

import co.com.plan.docente.entities.Parametro;
import co.com.plan.docente.forentities.ParametroFacadeLocal;
import co.com.plan.docente.forentities.UsuarioFacadeLocal;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

/**
 * 
 *
 * @author 727855
 */
@ManagedBean
@ViewScoped
public class ParametroBean {

    /**
     * Creates a new instance of ParametroBean
     */
    public ParametroBean() {
    }
    private static final long serialVersionUID = 9086384765824625788L;

    private Parametro parametro;
    private List<Parametro> parametros = new ArrayList<Parametro>();


    @EJB
    private ParametroFacadeLocal persistencia;
    @EJB
    private UsuarioFacadeLocal usuarioFacade;

   /* @PostConstruct
    public void init() {
        try {
            parametros = persistencia.findAll();
            ordenarTabla();
            validarBase();
            cargarInputs();
            bases = baseFacade.findAll();
            empresas = persistenciaEmpresa.findAll();
            usuarios = usuarioFacade.nombresDeUsuarios();
        } catch (Exception exep) {
            LoggerFacade.registerError(exep.getMessage(), exep);
        }
    }

    private void cargarInputs() {
        inputsList.clear();
        for (Parametro p : parametros) {
            if (p.getParTipo().equals("Inputs") || p.getParTipo().equals("Usuarios")) {
                List<String> alist = new ArrayList();
                alist.addAll(Arrays.asList(p.getParValor().split(",")));
                inputsList.put(p.getParNombre(), alist);
            }
        }
    }
*/
    
}
