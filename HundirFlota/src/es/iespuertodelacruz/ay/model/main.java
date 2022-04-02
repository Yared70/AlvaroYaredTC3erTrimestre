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
        Escenario escenario1 = new Escenario(1, 4);
        Barco barco = new Barco(3, "A");
        do{
            barco.colocarBarco();
        }while(!escenario1.posicionValida(barco));
        System.out.println(escenario1);
        
        Barco barco2 = new Barco(3, "B");
        do{
            barco2.colocarBarco();
        }while(!escenario1.posicionValida(barco2));
        
        Barco barco3 = new Barco(3, "C");
        do{
            barco3.colocarBarco();
        }while(!escenario1.posicionValida(barco3));
         
        /*
        do{
            
            System.out.println("Introduce la casilla x a atacar");
            int x = sc.nextInt();
            System.out.println("Introduce la casilla y");
            int y = sc.nextInt();
            System.out.println(escenario1.elegirCasilla(x, y));
            System.out.println(barco);
            System.out.println(barco2);
            System.out.println(barco3);
            System.out.println(escenario1);
            
            
        }while(!escenario1.ganado());
        if(escenario1.ganado()){
            System.out.println("Has ganado");
        }else{
            System.out.println("Terminado");
        }
        */
        
        int contador = 0;
        while(!escenario1.ganado()){
            
            System.out.println(barco);
            System.out.println(barco2);
            System.out.println(barco3);
            System.out.println(escenario1);
            int x = rnd.nextInt(escenario1.escenario.length);
            int y = rnd.nextInt(escenario1.escenario.length);
            System.out.println(escenario1.elegirCasilla(x, y));
            System.out.println("La IA ha atacado la posicion: " + x + ", " + y);
            contador ++;
        }
        if(escenario1.ganado()){
            System.out.println(escenario1);
            System.out.println("La IA ha ganado con " + contador + " ataques");
        }
        
    }
    
}
