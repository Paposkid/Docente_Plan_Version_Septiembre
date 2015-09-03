/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.plan.docente.forentities;

import co.com.plan.docente.entities.Coordinador;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 727855
 */
@Local
public interface CoordinadorFacadeLocal {

    void create(Coordinador coordinador);

    void edit(Coordinador coordinador);

    void remove(Coordinador coordinador);

    Coordinador find(Object id);

    List<Coordinador> findAll();

    List<Coordinador> findRange(int[] range);

    int count();
    
}
