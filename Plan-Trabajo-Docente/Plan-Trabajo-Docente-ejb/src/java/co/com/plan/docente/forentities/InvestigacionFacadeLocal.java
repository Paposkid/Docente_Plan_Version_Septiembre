/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.plan.docente.forentities;

import co.com.plan.docente.entities.Investigacion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 727855
 */
@Local
public interface InvestigacionFacadeLocal {

    void create(Investigacion investigacion);

    void edit(Investigacion investigacion);

    void remove(Investigacion investigacion);

    Investigacion find(Object id);

    List<Investigacion> findAll();

    List<Investigacion> findRange(int[] range);

    int count();
    
}
