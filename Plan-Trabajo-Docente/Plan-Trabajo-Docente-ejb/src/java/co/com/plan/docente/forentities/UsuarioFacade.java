/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.plan.docente.forentities;

import co.com.plan.docente.entities.Facultad;
import co.com.plan.docente.entities.Usuario;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author 727855
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {
    @PersistenceContext(unitName = "Plan-Trabajo-Docente-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    /**
     *
     * @param usuNombre
     * @param usuContrasena
     * @return
     */
    @Override
    public Usuario iniciarSession(String usuNombre, String usuContrasena) {
        Usuario retorno = null;
        try {
            TypedQuery<Usuario> findByNombrePassword;
            findByNombrePassword = em.createNamedQuery("Usuario.findByNombrePassword", Usuario.class);
            findByNombrePassword.setParameter("nombreUsuario", usuNombre);
            findByNombrePassword.setParameter("passwordUsuario", usuContrasena);
            retorno = findByNombrePassword.getSingleResult();

            if (retorno != null) {
                retorno.getPerfilUsuario();//Se cargan los permisos
            }
        } catch (Exception e) {
            retorno = null;
        }
        return retorno;
    }

    @Override
    public void actualizarClave(String usuNombre, String usuContrasena) {
        Usuario retorno = null;
        TypedQuery<Usuario> findByNombreUsuario;
        findByNombreUsuario = em.createNamedQuery("Usuario.findByNombreUsuario", Usuario.class);
        findByNombreUsuario.setParameter("nombreUsuario", usuNombre);
        retorno = findByNombreUsuario.getSingleResult();

        retorno.setPasswordUsuario(usuContrasena);
        em.merge(retorno);
    }
    
     /**
     *
     * @param documento
     * @return lista de usuarios activos
     */
    @Override
    public Usuario findUsuariosActivos(BigInteger documento) {

        Usuario usuarios = null;
        try {
        TypedQuery<Usuario> findAllActivos;
        findAllActivos = em.createNamedQuery("Usuario.findAllActivos", Usuario.class);
        findAllActivos.setParameter("usuEstado", 1);
        findAllActivos.setParameter("documentoUsuario", documento);
        usuarios = findAllActivos.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuarios;
    }
    
}
