/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.ay.model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javafx.util.Pair;

/**
 *
 * @author Álvaro y Yared
 */
public class Barco {
    String nombre;
    int size;
    ArrayList<Pair<String, Estado>> partes; // Array de partes del barco, cada parte tiene una casilla del tablero asignada, y un estado.
    Estado estado; // Estado del barco

    /**
     * Constructor por defecto
     */
    public Barco(){}
    
    /**
     * Constructor con dos parametros
     * @param size tamaño del barco
     * @param nombre Nombre del barco
     */
    public Barco(int size, String nombre) {
        this.size = size;
        this.nombre = nombre;
        this.partes = new ArrayList<>(size);
        this.estado = Estado.INTACTO;
    }
    
    /**
     * Enum de los posibles estados del barco y de cada una de sus partes
     */
    public enum Estado{INTACTO, TOCADO, HUNDIDO}
    
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ArrayList<Pair<String, Estado>> getPartes() {
        return partes;
    }

    public void setPartes(ArrayList<Pair<String, Estado>> partes) {
        this.partes = partes;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    public void hundirBarco(){
        ArrayList<Pair<String, Estado>> partesAux = new ArrayList<>();
        for (Pair<String, Estado> parte : partes) {
            partesAux.add(new Pair(parte.getKey(), Estado.HUNDIDO));
        }
        partes.clear();
        partes = partesAux;
        this.estado = Estado.HUNDIDO;
    }
    
    /**
     * Método que coloca el barco en una posicion aleatoria del tablero
     * @param escenario
     */
    public void colocarBarco(Escenario escenario){
        partes.clear();
        int x;
        int y;
        int ladoTablero = escenario.escenario.length;
        Random rnd = new Random();
        do{
            x = rnd.nextInt(ladoTablero);
            y = rnd.nextInt(ladoTablero);
        }while( (x-size-1) < 0 && (y - size-1) < 0 && (x + size-1) > ladoTablero-1 && (y + size-1) > ladoTablero-1);
        System.out.println(x + " " + y);
        
        
        partes.add(new Pair("" + x + " " + y, Estado.INTACTO));
        
        boolean colocado = false;
        
        do{
            int direccion = rnd.nextInt(4);
            switch(direccion){
                case 0:
                    if(x + (this.size-1) < ladoTablero  ){
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

