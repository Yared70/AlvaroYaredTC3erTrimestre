/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.iespuertodelacruz.ay.model;

import java.util.ArrayList;

/**
 *
 * @author yared
 */
public class Escenario {
    
    int id;
    ArrayList<Barco> barcos;
    String escenario[][];

    public Escenario(int id, int size) {
        this.id = id;
        this.barcos = new ArrayList<Barco>();
        this.escenario = new String[size][size];
        for (int i = 0; i < escenario.length; i++) {
            for (int j = 0; j < 10; j++) {
                escenario[i][j] = "";
            }
            
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Barco> getBarcos() {
        return barcos;
    }

    public void setBarcos(ArrayList<Barco> barcos) {
        this.barcos = barcos;
    }

    public String[][] getEscenario() {
        return escenario;
    }

    public void setEscenario(String[][] escenario) {
        this.escenario = escenario;
    }
    
    
    
    
    
    
}
