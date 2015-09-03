/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.plan.docente.forentities;

import co.com.plan.docente.entities.OtraActividad;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 727855
 */
@Local
public interface OtraActividadFacadeLocal {

    void create(OtraActividad otraActividad);

    void edit(OtraActividad otraActividad);

    void remove(OtraActividad otraActividad);

    OtraActividad find(Object id);

    List<OtraActividad> findAll();

    List<OtraActividad> findRange(int[] range);

    int count();
    
}
