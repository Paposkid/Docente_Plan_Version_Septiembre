/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.plan.docente.forentities;

import co.com.plan.docente.entities.Parametro;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 727855
 */
@Local
public interface ParametroFacadeLocal {

    void create(Parametro parametro);

    void edit(Parametro parametro);

    void remove(Parametro parametro);

    Parametro find(Object id);

    List<Parametro> findAll();

    List<Parametro> findRange(int[] range);

    int count();
    
}
