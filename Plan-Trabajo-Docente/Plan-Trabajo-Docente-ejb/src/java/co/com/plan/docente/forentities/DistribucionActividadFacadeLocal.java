/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.plan.docente.forentities;

import co.com.plan.docente.entities.DistribucionActividad;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 727855
 */
@Local
public interface DistribucionActividadFacadeLocal {

    void create(DistribucionActividad distribucionActividad);

    void edit(DistribucionActividad distribucionActividad);

    void remove(DistribucionActividad distribucionActividad);

    DistribucionActividad find(Object id);

    List<DistribucionActividad> findAll();

    List<DistribucionActividad> findRange(int[] range);

    int count();
    
}
