/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.iespuertodelacruz.ay.model;

import java.util.Scanner;

/**
 *
 * @author yared
 */
public class Jugador {

    String nombre;
    Escenario escenario;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.escenario = null;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Escenario getEscenario() {
        return escenario;
    }

    public void setEscenario(Escenario escenario) {
        this.escenario = escenario;
    }

    public String jugar(Escenario escenarioIA) {

        String respuesta = "";
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce la coordenada x");
        int x = sc.nextInt();
        System.out.println("Introduce la coordenada y");
        int y = sc.nextInt();
        while (x > escenarioIA.escenario.length -1 && y > escenarioIA.escenario.length -1) {
            System.out.println("Te has salido del mapa, elije otra casilla para atacar");
            System.out.println("Introduce la coordenada x");
            x = sc.nextInt();
            System.out.println("Introduce la coordenada y");
            y = sc.nextInt();
        }
        respuesta = escenarioIA.elegirCasilla(x, y);

        return respuesta;

    }

}
