/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.plan.docente.forentities;

import co.com.plan.docente.entities.Materia;
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
public class MateriaFacade extends AbstractFacade<Materia> implements MateriaFacadeLocal {
    @PersistenceContext(unitName = "Plan-Trabajo-Docente-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MateriaFacade() {
        super(Materia.class);
    }
    
     /**
     *
     * @return lista de usuarios activos
     */
    @Override
    public List<Materia> findMateriasActivas() {

        List<Materia> materias = null;
        String activo = "A";
        try {
        TypedQuery<Materia> findByEstMateria;
        findByEstMateria = em.createNamedQuery("Materia.findByEstMateria", Materia.class);
        findByEstMateria.setParameter("estMateria", activo);
        materias = findByEstMateria.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return materias;
    }
}
