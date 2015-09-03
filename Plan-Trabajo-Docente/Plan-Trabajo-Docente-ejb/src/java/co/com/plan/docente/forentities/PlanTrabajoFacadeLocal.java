/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.plan.docente.forentities;

import co.com.plan.docente.entities.PlanTrabajo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 727855
 */
@Local
public interface PlanTrabajoFacadeLocal {

    void create(PlanTrabajo planTrabajo);

    void edit(PlanTrabajo planTrabajo);

    void remove(PlanTrabajo planTrabajo);

    PlanTrabajo find(Object id);

    List<PlanTrabajo> findAll();

    List<PlanTrabajo> findRange(int[] range);

    int count();
    
}
