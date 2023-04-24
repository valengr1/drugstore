/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Calendar;

/**
 *
 * @author Valentin
 */
public class Fecha {
     private int dia;
     private int mes;
     private int anio;
    
    public Fecha() {
         anio = Calendar.getInstance().get(Calendar.YEAR);
         mes = Calendar.getInstance().get(Calendar.MONTH) + 1;
         dia = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }
    
    
    
    @Override
    public String toString() {
        return "Fecha{" + "dia=" + dia + ", mes=" + mes + ", anio=" + anio + '}';
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }
    
    
}
