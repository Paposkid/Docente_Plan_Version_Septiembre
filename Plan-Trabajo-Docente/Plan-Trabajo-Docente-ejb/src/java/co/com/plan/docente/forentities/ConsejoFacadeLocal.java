/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.plan.docente.forentities;

import co.com.plan.docente.entities.Consejo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 727855
 */
@Local
public interface ConsejoFacadeLocal {

    void create(Consejo consejo);

    void edit(Consejo consejo);

    void remove(Consejo consejo);

    Consejo find(Object id);

    List<Consejo> findAll();

    List<Consejo> findRange(int[] range);

    int count();
    
}
