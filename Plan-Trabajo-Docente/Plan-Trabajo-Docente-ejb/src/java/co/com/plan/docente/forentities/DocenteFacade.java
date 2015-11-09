/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.plan.docente.forentities;

import co.com.plan.docente.entities.Docente;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author 727855
 */
@Stateless
public class DocenteFacade extends AbstractFacade<Docente> implements DocenteFacadeLocal {
    @PersistenceContext(unitName = "Plan-Trabajo-Docente-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DocenteFacade() {
        super(Docente.class);
    }

    @Override
    public Docente findByCedula(String documnetoId) {
        Docente doc = null;
        TypedQuery<Docente> findByCedDocente;
        findByCedDocente = em.createNamedQuery("Docente.findByCedDocente", Docente.class);
        findByCedDocente.setParameter("cedDocente", documnetoId);
        doc = findByCedDocente.getSingleResult();

        return doc;
    }
}
