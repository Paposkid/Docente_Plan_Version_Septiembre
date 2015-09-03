/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.plan.docente.forentities;

import co.com.plan.docente.entities.DocenciaDirecta;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 727855
 */
@Local
public interface DocenciaDirectaFacadeLocal {

    void create(DocenciaDirecta docenciaDirecta);

    void edit(DocenciaDirecta docenciaDirecta);

    void remove(DocenciaDirecta docenciaDirecta);

    DocenciaDirecta find(Object id);

    List<DocenciaDirecta> findAll();

    List<DocenciaDirecta> findRange(int[] range);

    int count();
    
}
