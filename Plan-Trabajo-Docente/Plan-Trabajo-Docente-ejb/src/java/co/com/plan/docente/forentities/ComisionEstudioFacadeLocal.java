/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.plan.docente.forentities;

import co.com.plan.docente.entities.ComisionEstudio;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 727855
 */
@Local
public interface ComisionEstudioFacadeLocal {

    void create(ComisionEstudio comisionEstudio);

    void edit(ComisionEstudio comisionEstudio);

    void remove(ComisionEstudio comisionEstudio);

    ComisionEstudio find(Object id);

    List<ComisionEstudio> findAll();

    List<ComisionEstudio> findRange(int[] range);

    int count();
    
}
