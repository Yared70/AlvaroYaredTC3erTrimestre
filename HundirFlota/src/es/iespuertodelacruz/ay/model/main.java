/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.iespuertodelacruz.ay.model;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author yared
 */
public class main {
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        Random rnd = new Random();
        
        // Creamos la IA, su escenario(el que atacará el jugador) y se lo asignamos
        InteligenciaArtificial ia = new InteligenciaArtificial(1);
        Escenario escenarioIA = new Escenario(1, 4, ia);
        ia.setEscenario(escenarioIA);
        
        // Creamos al jugador, su escenario(el que atacará la IA) y se lo asignamos
        Jugador jugador = new Jugador("yared");
        Escenario escenarioJugador = new Escenario(2, 4, jugador);
        jugador.setEscenario(escenarioJugador);
        
        // Creamos los 6 barcos, 3 por para cada escenario y los añadimos al escenario correspondiente
        Barco barco = new Barco(3, "A");
        do{
            barco.colocarBarco();
        }while(!escenarioIA.posicionValida(barco));
        
        Barco barco2 = new Barco(3, "B");
        do{
            barco2.colocarBarco();
        }while(!escenarioIA.posicionValida(barco2));
        
        Barco barco3 = new Barco(3, "C");
        do{
            barco3.colocarBarco();
        }while(!escenarioIA.posicionValida(barco3));
         
        Barco barco4 = new Barco(3, "J1");
        do{
            barco4.colocarBarco();
        }while(!escenarioJugador.posicionValida(barco4));
        
        Barco barco5 = new Barco(3, "J2");
        do{
            barco5.colocarBarco();
        }while(!escenarioJugador.posicionValida(barco5));
        
        Barco barco6 = new Barco(3, "J3");
        do{
            barco6.colocarBarco();
        }while(!escenarioJugador.posicionValida(barco6));
        
        
        /*
        Mientras no se destruyan todos los barcos de alguno de los dos escenarios,
        es decir, se gane en ese escenario, la partida no termina
        */
        while(!escenarioIA.ganado() && !escenarioJugador.ganado()){
            
            System.out.println("------- Escenario Jugador ----------");
            System.out.println(jugador.escenario);
            System.out.println(ia.atacardif1(escenarioJugador)); //La ia ataca el escenario del jugador
            System.out.println("------- Escenario Jugador ----------");
            System.out.println(jugador.escenario);
            
            System.out.println("------- Escenario IA ---------");
            System.out.println(ia.escenario);
            System.out.println(jugador.jugar(escenarioIA)); //El jugador ataca el escenario de la IA
            System.out.println("------- Escenario IA ---------");
            System.out.println(ia.escenario);
        }
        
        if(escenarioIA.ganado()){
            System.out.println("Has Ganado!");
        }else{
            System.out.println("Has Perdido");
        }
        

    }
    
}
