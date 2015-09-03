/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.plan.docente.forentities;

import co.com.plan.docente.entities.AsesoriaProyecto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 727855
 */
@Local
public interface AsesoriaProyectoFacadeLocal {

    void create(AsesoriaProyecto asesoriaProyecto);

    void edit(AsesoriaProyecto asesoriaProyecto);

    void remove(AsesoriaProyecto asesoriaProyecto);

    AsesoriaProyecto find(Object id);

    List<AsesoriaProyecto> findAll();

    List<AsesoriaProyecto> findRange(int[] range);

    int count();
    
}
