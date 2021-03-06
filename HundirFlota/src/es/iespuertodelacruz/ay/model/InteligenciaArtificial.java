/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.iespuertodelacruz.ay.model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 *
 * @author yared y Alvaro
 */
public class InteligenciaArtificial {

    int dificultad;
    ArrayList<String> historialPosicionesAtacadas;
    ArrayList<String> posicionesAtacadasTocado;
    String ultimaPosicionTocada;
    Escenario escenario;
    String mapaTocados[][];
    Stack<String> nextPosiciones;
    boolean primerTocado;

    public InteligenciaArtificial(int dificultad) {
        this.dificultad = dificultad;
        this.historialPosicionesAtacadas = new ArrayList<String>();
        this.posicionesAtacadasTocado = new ArrayList<String>();
        this.nextPosiciones = new Stack<>();
        this.ultimaPosicionTocada = null;
        this.escenario = null;
        this.mapaTocados = null;
        this.primerTocado = false;
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
        historialPosicionesAtacadas.add(x + " " + y);
        return respuesta;
    }

    public String atacardif2(Escenario escenarioJugador) {
        Random rnd = new Random();
        String respuesta = "";
        String strEstado;
        int x, y;
        if (nextPosiciones.isEmpty()) {
            do {
                x = rnd.nextInt(escenarioJugador.escenario.length);
                y = rnd.nextInt(escenarioJugador.escenario.length);
            } while (escenarioJugador.yaAtacada(x, y) || ((x + y) % 2) != 0);
            strEstado = escenarioJugador.elegirCasilla(x, y);
            respuesta = "La IA ha atacado la posicion " + x + ", " + y + "\n"
                    + strEstado;

        } else {
            String[] coordenadas = nextPosiciones.pop().split(" ");
            x = Integer.parseInt(coordenadas[0]);
            y = Integer.parseInt(coordenadas[1]);
            strEstado = escenarioJugador.elegirCasilla(x, y);
            respuesta = "La IA ha atacado la posicion " + x + ", " + y + "\n"
                    + strEstado;
        }
        historialPosicionesAtacadas.add(x + " " + y);

        if (strEstado.equals("TOCADO!")) {

            addAdyacentes(x, y, escenarioJugador);

            posicionesAtacadasTocado.add(x + " " + y);

        } else if (strEstado.contains("HUNDIDO!")) {
            nextPosiciones.clear();
            rellenarPila(escenarioJugador);
        }
        return respuesta;

    }

    public String atacardif3(Escenario escenarioJugador) {
        Random rnd = new Random();
        String respuesta = "";
        String strEstado;
        int x, y;
        if (!primerTocado) {
            if (nextPosiciones.isEmpty()) {
                do {
                    x = rnd.nextInt(escenarioJugador.escenario.length);
                    y = rnd.nextInt(escenarioJugador.escenario.length);
                } while (escenarioJugador.yaAtacada(x, y) || ((x + y) % 2) != 0);
                strEstado = escenarioJugador.elegirCasilla(x, y);
                respuesta = "La IA ha atacado la posicion " + x + ", " + y + "\n"
                        + strEstado;

            } else {
                String[] coordenadas = nextPosiciones.pop().split(" ");
                x = Integer.parseInt(coordenadas[0]);
                y = Integer.parseInt(coordenadas[1]);
                strEstado = escenarioJugador.elegirCasilla(x, y);
                respuesta = "La IA ha atacado la posicion " + x + ", " + y + "\n"
                        + strEstado;
            }
            historialPosicionesAtacadas.add(x + " " + y);
            if (strEstado.equals("TOCADO!")) {
                posicionesAtacadasTocado.add(x + " " + y);
                addAdyacentes(x, y, escenarioJugador);
                ultimaPosicionTocada = "" + x + " " + y;
                primerTocado = true;
            } else if (strEstado.contains("HUNDIDO!")) {
                nextPosiciones.clear();
                rellenarPila(escenarioJugador);
            }
        } else {

            if (nextPosiciones.isEmpty()) {
                String[] ataqueAnterior = ultimaPosicionTocada.split(" ");
                int xAtaqueAnterior = Integer.parseInt(ataqueAnterior[0]);
                int yAtaqueAnterior = Integer.parseInt(ataqueAnterior[1]);
                addAdyacentes(xAtaqueAnterior, yAtaqueAnterior, escenarioJugador);
            }
            
                String[] coordenadas = nextPosiciones.pop().split(" ");
                x = Integer.parseInt(coordenadas[0]);
                y = Integer.parseInt(coordenadas[1]);
                strEstado = escenarioJugador.elegirCasilla(x, y);
                respuesta = "La IA ha atacado la posicion " + x + ", " + y + "\n"
                        + strEstado;
                historialPosicionesAtacadas.add(x + " " + y);

                if (strEstado.equals("TOCADO!")) {
                    primerTocado = true;
                    posicionesAtacadasTocado.add(x + " " + y);
                    String[] ataqueAnterior = ultimaPosicionTocada.split(" ");
                    int xAtaqueAnterior = Integer.parseInt(ataqueAnterior[0]);
                    int yAtaqueAnterior = Integer.parseInt(ataqueAnterior[1]);
                    ultimaPosicionTocada = "" + x + " " + y;

                    if (x > xAtaqueAnterior && (x + 1) < escenarioJugador.escenario.length
                            && !escenarioJugador.yaAtacada((x + 1), y)) {
                        nextPosiciones.clear();
                        nextPosiciones.add((x + 1) + " " + y);
                        if ((xAtaqueAnterior - 1) > 0 && !escenarioJugador.yaAtacada((xAtaqueAnterior - 1), y)) {
                            nextPosiciones.add((xAtaqueAnterior - 1) + " " + y);
                        }
                    } else if (x < xAtaqueAnterior && (x - 1) > 0 && !escenarioJugador.yaAtacada((x - 1), y)) {
                        nextPosiciones.clear();
                        nextPosiciones.add((x - 1) + " " + y);
                        if ((xAtaqueAnterior + 1) < escenarioJugador.escenario.length && !escenarioJugador.yaAtacada((xAtaqueAnterior + 1), y)) {
                            nextPosiciones.add((xAtaqueAnterior + 1) + " " + y);
                        }
                    } else if (y > yAtaqueAnterior && (y + 1) < escenarioJugador.escenario.length
                            && !escenarioJugador.yaAtacada(x, (y + 1))) {
                        nextPosiciones.clear();
                        nextPosiciones.add(x + " " + (y + 1));
                        if ((yAtaqueAnterior - 1) > 0 && !escenarioJugador.yaAtacada(x, (yAtaqueAnterior - 1))) {
                            nextPosiciones.add(x + " " + (yAtaqueAnterior - 1));
                        }
                    } else if (y < yAtaqueAnterior && (y - 1) > 0 && !escenarioJugador.yaAtacada(x, y - 1)) {
                        nextPosiciones.clear();
                        nextPosiciones.add(x + " " + (y - 1));
                        if ((yAtaqueAnterior + 1) < escenarioJugador.escenario.length && !escenarioJugador.yaAtacada(x, yAtaqueAnterior + 1)) {
                            nextPosiciones.add(x + " " + (yAtaqueAnterior + 1));
                        }
                    }
                } else if (strEstado.contains("HUNDIDO!")) {
                    nextPosiciones.clear();
                    rellenarPila(escenarioJugador);
                    primerTocado = false;
                }
            }

        
        System.out.println(nextPosiciones);
        return respuesta;
    }

    private void rellenarPila(Escenario escenarioJugador) {

        for (String posicionTocada : posicionesAtacadasTocado) {
            String[] split = posicionTocada.split(" ");
            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[1]);
            Barco barcoTocado = escenarioJugador.getBarco(x, y);
            if (!barcoTocado.estado.equals(Barco.Estado.HUNDIDO)) {
                addAdyacentes(x, y, escenarioJugador);
            }
        }
    }

    private void addAdyacentes(int x, int y, Escenario escenarioJugador) {
        if (x > 0 && !escenarioJugador.yaAtacada(x - 1, y)) {
            nextPosiciones.add((x - 1) + " " + y);
        }
        if (x < escenarioJugador.escenario.length - 1 && !escenarioJugador.yaAtacada(x + 1, y)) {
            nextPosiciones.add((x + 1) + " " + y);
        }
        if (y > 0 && !escenarioJugador.yaAtacada(x, y - 1)) {
            nextPosiciones.add(x + " " + (y - 1));
        }
        if (y < escenarioJugador.escenario.length - 1 && !escenarioJugador.yaAtacada(x, y + 1)) {
            nextPosiciones.add(x + " " + (y + 1));
        }
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
