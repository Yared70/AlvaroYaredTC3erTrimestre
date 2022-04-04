/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.iespuertodelacruz.ay.model;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author yared
 */
public class InteligenciaArtificial {

    int dificultad;
    ArrayList<String> historialPosicionesAtacadas;
    ArrayList<String> posicionesAtacadasTocado;
    String ultimaPosicionTocada;
    Escenario escenario;
    String mapaTocados[][];

    public InteligenciaArtificial(int dificultad) {
        this.dificultad = dificultad;
        this.historialPosicionesAtacadas = new ArrayList<String>();
        this.posicionesAtacadasTocado = new ArrayList<String>();
        this.ultimaPosicionTocada = null;
        this.escenario = null;
        this.mapaTocados = null;
    }

    public int getDificultad() {
        return dificultad;
    }

    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }

    public ArrayList<String> getHistorialPosicionesAtacadas() {
        return historialPosicionesAtacadas;
    }

    public void setHistorialPosicionesAtacadas(ArrayList<String> historialPosicionesAtacadas) {
        this.historialPosicionesAtacadas = historialPosicionesAtacadas;
    }

    public Escenario getEscenario() {
        return escenario;
    }

    public void setEscenario(Escenario escenario) {
        this.escenario = escenario;
        mapaTocados = new String[escenario.escenario.length][escenario.escenario.length];
    }

    public ArrayList<String> getPosicionesAtacadasTocado() {
        return posicionesAtacadasTocado;
    }

    public void setPosicionesAtacadasTocado(ArrayList<String> posicionesAtacadasTocado) {
        this.posicionesAtacadasTocado = posicionesAtacadasTocado;
    }

    public String getUltimaPosicionTocada() {
        return ultimaPosicionTocada;
    }

    public void setUltimaPosicionTocada(String ultimaPosicionTocada) {
        this.ultimaPosicionTocada = ultimaPosicionTocada;
    }

    public String[][] getMapaTocados() {
        return mapaTocados;
    }

    public void setMapaTocados(String[][] mapaTocados) {
        this.mapaTocados = mapaTocados;
    }

   

    public String atacardif1(Escenario escenarioJugador) {

        Random rnd = new Random();
        String respuesta = "";

        int x = rnd.nextInt(escenarioJugador.escenario.length);
        int y = rnd.nextInt(escenarioJugador.escenario.length);
        while (escenarioJugador.yaAtacada(x, y)) {
            x = rnd.nextInt(escenarioJugador.escenario.length);
            y = rnd.nextInt(escenarioJugador.escenario.length);
        }
        respuesta = "La IA ha atacado la posicion " + x + ", " + y + "\n"
                + escenarioJugador.elegirCasilla(x, y);

        return respuesta;
    }

    /*
    public String atacardif2(Escenario escenarioJugador) {

        Random rnd = new Random();
        String respuesta = "";

        if (ultimaPosicionTocada==null) {
            int x = rnd.nextInt(escenarioJugador.escenario.length);
            int y = rnd.nextInt(escenarioJugador.escenario.length);
            while (escenarioJugador.yaAtacada(x, y)) {
                x = rnd.nextInt(escenarioJugador.escenario.length);
                y = rnd.nextInt(escenarioJugador.escenario.length);
            }
            respuesta = "La IA ha atacado la posicion " + x + ", " + y + "\n"
                    + escenarioJugador.elegirCasilla(x, y);
            if (escenarioJugador.elegirCasilla(x, y).equals("TOCADO!")) {
                mapaTocados[x][y] = "X";
                ultimaPosicionTocada = "" + x + " " + y;
            }

            return respuesta;

        }else{
            
            String[] split = ultimaPosicionTocada.split(" ");
            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[1]);
            int sig = rnd.nextInt(4);
            switch(sig){
                case 0:
                    int xSig = x+1;
                    int ySig = y;
                    while(xSig > escenarioJugador.escenario.length -1){
                        xSig = x-1;
                        ySig = y;
                    }
                    while (escenarioJugador.yaAtacada(xSig, ySig)) {
                        
            }
            }
            
            
        }
        */
    }
