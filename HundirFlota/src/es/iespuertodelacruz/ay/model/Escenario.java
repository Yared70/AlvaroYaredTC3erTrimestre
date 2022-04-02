/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.ay.model;

import java.util.ArrayList;
import java.util.Iterator;
import javafx.util.Pair;

/**
 *
 * @author Álvaro Hernández Rocío
 */
public class Escenario {
    int id;
    ArrayList<Barco> barcos;
    String escenario[][];

    public Escenario(int id, int size) {
        this.id = id;
        this.barcos = new ArrayList<>();
        this.escenario = new String[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                escenario[i][j] = "~";
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
    
    @Override
    public String toString(){
        String respuesta = "";
        for(int i = 0; i < escenario.length; i++){
            for(int j = 0; j < escenario.length; j++){
                respuesta += escenario[i][j] + "\t";
            }
            respuesta += "\n";
        }
        return respuesta;
    }
    
    public boolean colocarBarco(Barco barco){
        Iterator<Pair<String, Barco.Estado>> iterator = barco.partes.iterator();
        while(iterator.hasNext()){
            
            String strCoordenadas = iterator.next().getKey();
            String[] split = strCoordenadas.split(" ");
            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[1]);
            if(isOcupado(x, y)){
                return false;
            }
        }
        
        iterator = barco.partes.iterator();
        while(iterator.hasNext()){
            
            String strCoordenadas = iterator.next().getKey();
            String[] split = strCoordenadas.split(" ");
            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[1]);  
            escenario[x][y] = barco.getNombre();
        }
        
        
        
        return true;
    }
    
    public boolean isOcupado(int x, int y){
        boolean respuesta = true;
        if(escenario[x][y].equals("~")){
            respuesta = false;
        }
        return respuesta;
    }
}

class MainEscenario{
    public static void main(String[] args) {
        Escenario escenario = new Escenario(1, 4);
        System.out.println(escenario);
    }
}
