/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.iespuertodelacruz.ay.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import javafx.util.Pair;

/**
 *
 * @author yared
 */

/*
public class Juego {

    TreeMap<Integer, Partida> partidas;

    public Juego() {
        this.partidas = new TreeMap<Integer, Partida>();
    }

    public TreeMap<Integer, Partida> getPartidas() {
        return partidas;
    }

    public void setPartidas(TreeMap<Integer, Partida> partidas) {
        this.partidas = partidas;
    }

    public String mostrarHistorial() {

        String respuesta = "";
        TreeMap<Date, Partida> historial = new TreeMap<>();
        Collection<Partida> partidasJugadas = partidas.values();
        Iterator<Partida> iterator = partidasJugadas.iterator();
        while (iterator.hasNext()) {

            Partida partida = iterator.next();
            historial.put(partida.getFecha(), partida);

        }

        Set<Map.Entry<Date, Partida>> setFechaPartida = historial.entrySet();
        Iterator<Map.Entry<Date, Partida>> iterator1 = setFechaPartida.iterator();
        while (iterator1.hasNext()) {

            Map.Entry<Date, Partida> fechaPartida = iterator1.next();
            respuesta += "Partida: " + fechaPartida.getValue().getId()
                    + "\tFecha: " + fechaPartida.getKey()
                    + "\tResultado: " + ((fechaPartida.getValue().isVictoria()) ? "Ganada" : "Perdida") + "\n";

        }

        return respuesta;

    }

    @Override
    public String toString() {

        String respuesta = "";
        Set<Map.Entry<Integer, Partida>> entrySet = partidas.entrySet();
        Iterator<Map.Entry<Integer, Partida>> iterator = entrySet.iterator();
        while (iterator.hasNext()) {

            Map.Entry<Integer, Partida> next = iterator.next();
            respuesta += "Partida: " + next.getKey()
                    + "\tFecha: " + next.getValue().getFecha()
                    + "\tResultado: " + ((next.getValue().isVictoria()) ? "Ganada" : "Perdida") + "\n";

        }

        return respuesta;
    }

    public class Partida {

        int id;
        Date fecha;
        boolean victoria;
        ArrayList<Barco> barcos;
        Escenario escenarios[];

        public Partida() {
            this.id = partidas.lastKey() + 1;
            this.fecha = new Date();
            this.victoria = false;
            this.barcos = new ArrayList<>();
            this.escenarios = new Escenario[2];
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Date getFecha() {
            return fecha;
        }

        public void setFecha(Date fecha) {
            this.fecha = fecha;
        }

        public boolean isVictoria() {
            return victoria;
        }

        public void setVictoria(boolean victoria) {
            this.victoria = victoria;
        }

        public ArrayList<Barco> getBarcos() {
            return barcos;
        }

        public void setBarcos(ArrayList<Barco> barcos) {
            this.barcos = barcos;
        }

        public Escenario[] getEscenarios() {
            return escenarios;
        }

        public void setEscenarios(Escenario[] escenarios) {
            this.escenarios = escenarios;
        }


        
        
        
        class Barco {

            String nombre;
            int size; 
            ArrayList<Pair<String, Estado>> partes;
            Estado estado;

           
            public Barco() {
            }

           
            public Barco(int size, String nombre) {
                this.size = size;
                this.nombre = nombre;
                this.partes = new ArrayList<>(size);
                this.estado = Estado.INTACTO;
            }

            enum Estado {
                INTACTO, TOCADO, HUNDIDO
            }
            
           
            @Override
            public String toString() {
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

          
            public void colocarBarco() {
                partes.clear();
                int x; 
                int y; 
                int ladoTablero = 4; 
                Random rnd = new Random();
                x = rnd.nextInt(4);
                y = rnd.nextInt(4);

                partes.add(new Pair("" + x + " " + y, Estado.INTACTO));

                boolean colocado = false;

                do {
                    int direccion = rnd.nextInt(4);
                    switch (direccion) {
                        case 0:
                            if (x + (this.size - 1) < ladoTablero ) {
                                for (int j = 0; j < this.size - 1; j++) {
                                    x++;
                                    partes.add(new Pair("" + x + " " + y, Estado.INTACTO));
                                }
                                colocado = true;
                            }
                            break;
                        case 1:
                            if (y + (this.size - 1) < ladoTablero) {
                                for (int j = 0; j < this.size - 1; j++) {
                                    y++;
                                    partes.add(new Pair("" + x + " " + y, Estado.INTACTO));
                                }
                                colocado = true;
                            }
                            break;
                        case 2:
                            if (x >= this.size - 1) {
                                for (int j = 0; j < this.size - 1; j++) {
                                    x--;
                                    partes.add(new Pair("" + x + " " + y, Estado.INTACTO));
                                }
                                colocado = true;
                            }
                            break;
                        case 3:
                            if (y >= this.size - 1) {
                                for (int j = 0; j < this.size - 1; j++) {
                                    y--;
                                    partes.add(new Pair("" + x + " " + y, Estado.INTACTO));
                                }
                                colocado = true;
                            }
                            break;
                    }
                } while (!colocado);
            }
        }

        
        class Escenario {

            int id;
            ArrayList<es.iespuertodelacruz.ay.model.Barco> barcos;
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

            public ArrayList<es.iespuertodelacruz.ay.model.Barco> getBarcos() {
                return barcos;
            }

            public void setBarcos(ArrayList<es.iespuertodelacruz.ay.model.Barco> barcos) {
                this.barcos = barcos;
            }

            public String[][] getEscenario() {
                return escenario;
            }

            public void setEscenario(String[][] escenario) {
                this.escenario = escenario;
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

            public boolean colocarBarco(es.iespuertodelacruz.ay.model.Barco barco) {
                Iterator<Pair<String, es.iespuertodelacruz.ay.model.Barco.Estado>> iterator = barco.partes.iterator();
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

            public String elegirCasilla(int x, int y) {

                String respuesta = "";
                if (!isOcupado(x, y)) {
                    return "AGUA!";
                } else {

                    for (es.iespuertodelacruz.ay.model.Barco barco : barcos) {

                        Iterator<Pair<String, es.iespuertodelacruz.ay.model.Barco.Estado>> iterator = barco.partes.iterator();
                        while (iterator.hasNext()) {

                            Pair<String, es.iespuertodelacruz.ay.model.Barco.Estado> parteDelBarco = iterator.next();
                            String strCoordenadas = parteDelBarco.getKey();
                            String[] split = strCoordenadas.split(" ");
                            int xBarco = Integer.parseInt(split[0]);
                            int yBarco = Integer.parseInt(split[1]);
                            if (x == xBarco && y == yBarco) {
                                barco.partes.remove(parteDelBarco);
                                barco.partes.add(new Pair("" + x + " " + y, es.iespuertodelacruz.ay.model.Barco.Estado.TOCADO));
                                respuesta = "TOCADO!";

                                if (barcoHundido(barco)) {
                                    respuesta += " y HUNDIDO!";
                                }
                                return respuesta;
                            }

                        }
                    }

                }

                return respuesta;

            }

            public boolean barcoHundido(es.iespuertodelacruz.ay.model.Barco barco) {

                boolean hundido = false;

                Iterator<Pair<String, es.iespuertodelacruz.ay.model.Barco.Estado>> iterator = barco.getPartes().iterator();
                int tocados = 0;
                while (iterator.hasNext()) {

                    Pair<String, es.iespuertodelacruz.ay.model.Barco.Estado> parteBarco = iterator.next();
                    es.iespuertodelacruz.ay.model.Barco.Estado estadoBarco = parteBarco.getValue();
                    if (estadoBarco.equals(estadoBarco.TOCADO)) {
                        tocados += 1;
                    }

                }

                if (tocados == barco.getSize()) {
                    hundido = true;
                }

                return hundido;

            }

            public boolean ganado() {

                boolean respuesta = false;
                int hundidos = 0;
                int cantidadBarcos = barcos.size();

                for (es.iespuertodelacruz.ay.model.Barco barco : barcos) {
                    if (barcoHundido(barco)) {
                        hundidos += 1;
                    }
                }
                if (hundidos == cantidadBarcos) {
                    respuesta = true;
                }

                return respuesta;

            }

        }
    }

}
*/