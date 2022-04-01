/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication10;

import java.util.ArrayList;

/**
 *
 * @author yared
 */
public class Partida {
    
    int id;
    ArrayList<Escenario> escenarios;
    ArrayList<Barco> barcos;

    public Partida(int id) {
        this.id = id;
        this.escenarios = new ArrayList<Escenario>();
        this.barcos = new ArrayList<Barco>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Escenario> getEscenarios() {
        return escenarios;
    }

    public void setEscenarios(ArrayList<Escenario> escenarios) {
        this.escenarios = escenarios;
    }

    public ArrayList<Barco> getBarcos() {
        return barcos;
    }

    public void setBarcos(ArrayList<Barco> barcos) {
        this.barcos = barcos;
    }
    
    
}
