/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.ay.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javafx.util.Pair;

/**
 *
 * @author Álvaro y Yared
 */
public class Escenario {

    int id;
    ArrayList<Barco> barcos;
    String escenario[][];
    ArrayList<String> historialPosicionesAtacadas;
    Jugador jugador;
    InteligenciaArtificial ia;

    public Escenario(int id, int size, Jugador jugador) {
        this.id = id;
        this.barcos = new ArrayList<>();
        this.historialPosicionesAtacadas = new ArrayList<>();
        this.escenario = new String[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                escenario[i][j] = "~";
            }

        }
        this.ia = null;
        this.jugador = jugador;
    }
    
    public Escenario(int id, int size, InteligenciaArtificial ia) {
        this.id = id;
        this.barcos = new ArrayList<>();
        this.historialPosicionesAtacadas = new ArrayList<>();
        this.escenario = new String[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                escenario[i][j] = "~";
            }

        }
        this.ia = ia;
        this.jugador = null;
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
    
    public Barco getBarco(int x, int y){
        if(isOcupado(x, y)){
            for (Barco barco : barcos) {
                Iterator<Pair<String, Barco.Estado>> iterator = barco.partes.iterator();
                while (iterator.hasNext()) {
                    String strCoordenadas = iterator.next().getKey();
                    String[] split = strCoordenadas.split(" ");
                    if(Integer.parseInt(split[0]) == x && Integer.parseInt(split[1]) == y){
                        return barco;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String respuesta = "";
        for (int i = 0; i < escenario.length; i++) {
            for (int j = 0; j < escenario.length; j++) {
                respuesta += escenario[i][j] + "\t";
            }
            respuesta += "\n";
        }
        return respuesta;
    }

    public boolean posicionValida(Barco barco) {
        Iterator<Pair<String, Barco.Estado>> iterator = barco.partes.iterator();
        while (iterator.hasNext()) {

            String strCoordenadas = iterator.next().getKey();
            String[] split = strCoordenadas.split(" ");
            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[1]);
            if (isOcupado(x, y)) {
                return false;
            }
        }

        iterator = barco.partes.iterator();
        while (iterator.hasNext()) {

            String strCoordenadas = iterator.next().getKey();
            String[] split = strCoordenadas.split(" ");
            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[1]);
            escenario[x][y] = barco.getNombre();
            barcos.add(barco);
        }

        return true;
    }

    public boolean isOcupado(int x, int y) {
        boolean respuesta = true;
        if (escenario[x][y].equals("~")) {
            respuesta = false;
        }
        return respuesta;
    }
    
    public boolean yaAtacada(int x, int y){
        
        boolean respuesta = false;
        if(historialPosicionesAtacadas.contains("" + x + " " + y)){
            respuesta = true;
            
        }
        return respuesta;
    }
    
    /**
     * Metodo para elegir la casilla a atacar
     * @param x posicion x a atacar
     * @param y posicion y a atacar
     * @return retorna agua, tocado o tocado y hundido dependiendo de
     * como haya ido el ataque
     */
    public String elegirCasilla(int x, int y) {

        historialPosicionesAtacadas.add("" + x + " " + y);
        String respuesta = "";
        if (!isOcupado(x, y)) {
            return "AGUA!";
        } else {

            for (Barco barco : barcos) {

                Iterator<Pair<String, Barco.Estado>> iterator = barco.partes.iterator();
                while (iterator.hasNext()) {
                    
                    Pair<String, Barco.Estado> parteDelBarco = iterator.next();
                    String strCoordenadas = parteDelBarco.getKey();
                    String[] split = strCoordenadas.split(" ");
                    int xBarco = Integer.parseInt(split[0]);
                    int yBarco = Integer.parseInt(split[1]);
                    /*
                    Si la posicion x,y del barco coincide con la x, y de la casilla pasada
                    como parametros, se elimina del barco esa parte y se vuelve a añadir 
                    una en la misma posicion para cambiar el estado de intacto a tocado
                    y pintar una X en el mapa
                    */
                    if (x == xBarco && y == yBarco) {
                        barco.partes.remove(parteDelBarco);
                        barco.partes.add(new Pair("" + x + " " + y, Barco.Estado.TOCADO));
                        escenario[x][y] = "X";
                        respuesta = "TOCADO!";

                        if (barcoHundido(barco)) {
                            respuesta += " y HUNDIDO!";
                            barco.hundirBarco();
                        }
                        
                        return respuesta;
                    }

                }
            }

        }

        return respuesta;

    }
    
    /**
     * Metodo que comprueba si el barco ha sido hundido comprobando si todas
     * sus partes se han hundido
     * @param barco barco del cual se va a realizar la comprobacion
     * @return true si el barco se ha hundido
     */
    public boolean barcoHundido(Barco barco) {

        boolean hundido = false;


        Iterator<Pair<String, Barco.Estado>> iterator = barco.getPartes().iterator();
        int tocados = 0;
        while (iterator.hasNext()) {

            Pair<String, Barco.Estado> parteBarco = iterator.next();
            Barco.Estado estadoBarco = parteBarco.getValue();
            if (estadoBarco.equals(estadoBarco.TOCADO)) {
                tocados += 1;
            }

        }

        if (tocados == barco.getSize()) {
            hundido = true;
        }

        return hundido;

    }
    
       
    /**
     * Metodo para comprobar si se ha ganado la partida comprobando si todos
     * los barcos estan hundidos
     * @return true si todos los barcos se han hundido
     */
    public boolean ganado(){
        
        boolean respuesta = false;
        int hundidos = 0;
        int cantidadBarcos = barcos.size();
        
        for (Barco barco : barcos) {
            if(barco.getEstado().equals(Barco.Estado.HUNDIDO)){
                hundidos +=1;
            } else {
            }
        }
        if(hundidos==cantidadBarcos){
            respuesta = true;
        }
        
        return respuesta;
        
    }

}


