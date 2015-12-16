/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.plan.docente.forentities;

import co.com.plan.docente.entities.Docente;
import co.com.plan.docente.entities.PlanTrabajo;
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
public class PlanTrabajoFacade extends AbstractFacade<PlanTrabajo> implements PlanTrabajoFacadeLocal {
    @PersistenceContext(unitName = "Plan-Trabajo-Docente-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlanTrabajoFacade() {
        super(PlanTrabajo.class);
    }

    @Override
    public List<PlanTrabajo> findAllByEstado(String estado) {
      List<PlanTrabajo> retorno = null;
        TypedQuery<PlanTrabajo> findByEstado;
        findByEstado = em.createNamedQuery("PlanTrabajo.findByEstado", PlanTrabajo.class);
        findByEstado.setParameter("estado", estado);
        retorno = findByEstado.getResultList();
        return retorno;
    }
    @Override
    public PlanTrabajo findPlanByPeriodoAndDocente(String periodo,Docente docente) {
      PlanTrabajo retorno = null;
        try {
        TypedQuery<PlanTrabajo> findByPeriodoYDocente;
        findByPeriodoYDocente = em.createNamedQuery("PlanTrabajo.findByPeriodoYDocente", PlanTrabajo.class);
        findByPeriodoYDocente.setParameter("periodoAcademico", periodo);
        findByPeriodoYDocente.setParameter("codDocente", docente);
        retorno = findByPeriodoYDocente.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retorno;
    }
    
}
