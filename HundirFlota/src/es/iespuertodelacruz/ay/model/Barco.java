/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.ay.model;

import java.util.ArrayList;
import java.util.Random;
import javafx.util.Pair;

/**
 *
 * @author Álvaro y Yared
 */
public class Barco {
    int size; // 2 o 3
    ArrayList<Pair<String, Estado>> partes; // Array de partes del barco, cada parte tiene una casilla del tablero asignada, y un estado.

    /**
     * Constructor por defecto
     */
    public Barco(){}
    
    /**
     * Constructor con un parametro
     * @param size tamaño del barco
     */
    public Barco(int size) {
        this.size = size;
        this.partes = new ArrayList<>(size);
    }
    
    /**
     * Enum de los posibles estados del barco y de cada una de sus partes
     */
    enum Estado{INTACTO, TOCADO, HUNDIDO}
    
    /**
     * Metodo toString
     * @return devuelve la forma de expresar el barco en cadena de texto
     */
    @Override
    public String toString(){
        String respuesta = "";
        respuesta = partes.stream().map((parte) -> parte.getKey() + ": " + parte.getValue() + "\n").reduce(respuesta, String::concat);
        return respuesta;
    }
    
    /**
     * Método que coloca el barco en una posicion aleatoria del tablero
     */
    public void colocarBarco(){
        int x; // Posteriormente será la coordenada x de la casilla
        int y; // Posteriormente será la coordenada y de la casilla
        int ladoTablero = 4; // Posteriormente el dato lo tendremos en la clase Escenario
        Random rnd = new Random();
        x = rnd.nextInt(4);
        y = rnd.nextInt(4);
        
        partes.add(new Pair("" + x + " " + y, Estado.INTACTO));
        
        boolean colocado = false;
        
        do{
            int direccion = rnd.nextInt(4);
            switch(direccion){
                case 0:
                    if(x + (this.size-1) < ladoTablero   /*Y x+1 no está ocupada*/){
                        for(int j = 0; j < this.size - 1; j++){
                            x++;
                            partes.add(new Pair("" + x + " " + y, Estado.INTACTO));
                        }
                        colocado = true;
                    }
                    break;
                case 1:
                    if(y + (this.size-1) < ladoTablero){
                        for(int j = 0; j < this.size - 1; j++){
                            y++;
                            partes.add(new Pair("" + x + " " + y, Estado.INTACTO));
                        }
                        colocado = true;
                    }
                    break;
                case 2: 
                    if(x >= this.size-1){
                        for(int j = 0; j < this.size - 1; j++){
                            x--;
                            partes.add(new Pair("" + x + " " + y, Estado.INTACTO));
                        }
                        colocado = true;
                    }
                    break;
                case 3: 
                    if(y >= this.size-1){
                        for(int j = 0; j <  this.size - 1; j++){
                            y--;
                            partes.add(new Pair("" + x + " " + y, Estado.INTACTO));
                        }
                        colocado = true;
                    }
                    break;
            }
        }while(!colocado);
        
   }
    
}

class MainPruebaBarco{
    public static void main(String[] args) {
        Barco barco = new Barco(3);
        barco.colocarBarco();
        System.out.println(barco);
    }
}
