/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.plan.docente.forentities;

import co.com.plan.docente.entities.FechaAcademica;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 727855
 */
@Local
public interface FechaAcademicaFacadeLocal {

    void create(FechaAcademica fechaAcademica);

    void edit(FechaAcademica fechaAcademica);

    void remove(FechaAcademica fechaAcademica);

    FechaAcademica find(Object id);

    List<FechaAcademica> findAll();

    List<FechaAcademica> findRange(int[] range);

    int count();
    
}
