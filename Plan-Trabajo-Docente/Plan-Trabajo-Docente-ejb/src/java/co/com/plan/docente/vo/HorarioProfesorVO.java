/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.plan.docente.vo;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Jorge Montoya
 */
public class HorarioProfesorVO implements Serializable {

    private static final long serialVersionUID = 1L;
    private String diaHora;
    private int intencidadSemanal;
    private String nombreMateria;

    public String getDiaHora() {
        return diaHora;
    }

    public void setDiaHora(String diaHora) {
        this.diaHora = diaHora;
    }

    public int getIntencidadSemanal() {
        return intencidadSemanal;
    }

    public void setIntencidadSemanal(int intencidadSemanal) {
        this.intencidadSemanal = intencidadSemanal;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    @Override
    public String toString() {
        return "HorarioProfesorVO{" + "diaHora=" + diaHora + ", intencidadSemanal=" + intencidadSemanal + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.diaHora);
        hash = 31 * hash + this.intencidadSemanal;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HorarioProfesorVO other = (HorarioProfesorVO) obj;
        if (!Objects.equals(this.diaHora, other.diaHora)) {
            return false;
        }
        if (this.intencidadSemanal != other.intencidadSemanal) {
            return false;
        }
        return true;
    }

    
}
