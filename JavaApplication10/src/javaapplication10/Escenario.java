/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication10;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author yared
 */
public class Escenario {

    int id;
    ArrayList<Barco> barcos;
    String escenario[][];

    public Escenario(int id, int size) {
        this.id = id;
        this.barcos = new ArrayList<Barco>();
        this.escenario = new String[size][size];
        for (int i = 0; i < escenario.length; i++) {
            for (int j = 0; j < escenario[0].length; j++) {
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

    public void addBarco(Barco barco) {

        int cantidad = barco.getSize();
        int nextPos = 0;
        boolean ocupado = false;

        Random rnd = new Random();
        int x = rnd.nextInt(escenario.length);
        int y = rnd.nextInt(escenario.length);
        System.out.println(x);
        System.out.println(y);

        int sig = rnd.nextInt(3);
        while (cantidad > 0) {
            ocupado = false;
            switch (sig) {
                case 0:
                    if ((y - cantidad) < 0 && (x + cantidad) > escenario.length - 1
                            && (y + cantidad) > escenario.length - 1
                            && (x - cantidad) < 0) {
                        sig = rnd.nextInt(3);
                        x = rnd.nextInt(escenario.length);
                        y = rnd.nextInt(escenario.length);
                    } else if ((y - cantidad) < 0) {
                        sig = rnd.nextInt(3);
                        break;
                    } else {
                        nextPos = cantidad -1;
                        while (nextPos >= 0) {

                            if (!escenario[x][y - nextPos].equals("~")) {
                                nextPos = 0;
                                ocupado = true;
                            } else {
                                nextPos--;
                            }
                        }
                        if (ocupado) {
                            sig = rnd.nextInt(3);
                            x = rnd.nextInt(escenario.length);
                            y = rnd.nextInt(escenario.length);

                        } else {
                            escenario[x][y] = barco.getId();
                            cantidad--;
                            while (cantidad > 0) {
                                escenario[x][y - cantidad] = barco.getId();
                                cantidad -= 1;
                            }
                        }
                        break;
                    }
                case 1:
                    if ((y - cantidad) < 0 && (x + cantidad) > escenario.length - 1
                            && (y + cantidad) > escenario.length - 1
                            && (x - cantidad) < 0) {
                        sig = rnd.nextInt(3);
                        x = rnd.nextInt(escenario.length);
                        y = rnd.nextInt(escenario.length);
                    } else if ((x + cantidad) > escenario.length - 1) {
                        sig = rnd.nextInt(3);
                        break;
                    } else {
                        nextPos = cantidad -1;
                        while (nextPos >= 0) {

                            if (!escenario[x + nextPos][y].equals("~")) {
                                nextPos = 0;
                                ocupado = true;
                            } else {
                                nextPos--;
                            }
                        }
                        if (ocupado) {
                            sig = rnd.nextInt(3);
                            x = rnd.nextInt(escenario.length);
                            y = rnd.nextInt(escenario.length);
                        } else {
                            escenario[x][y] = barco.getId();
                            cantidad--;
                            while (cantidad > 0) {

                                escenario[x + cantidad][y] = barco.getId();
                                cantidad -= 1;
                            }
                        }
                        break;
                    }

                case 2:
                    if ((y - cantidad) < 0 && (x + cantidad) > escenario.length - 1
                            && (y + cantidad) > escenario.length - 1
                            && (x - cantidad) < 0) {
                        sig = rnd.nextInt(3);
                        x = rnd.nextInt(escenario.length);
                        y = rnd.nextInt(escenario.length);
                    } else if ((y + cantidad) > escenario.length - 1) {
                        sig = rnd.nextInt(3);
                        break;
                    } else {
                        nextPos = cantidad -1;
                        while (nextPos >= 0) {

                            if (!escenario[x][y + nextPos].equals("~")) {
                                nextPos = 0;
                                ocupado = true;
                            } else {
                                nextPos--;
                            }
                        }
                        if (ocupado) {
                            sig = rnd.nextInt(3);
                            x = rnd.nextInt(escenario.length);
                            y = rnd.nextInt(escenario.length);

                        } else {
                            escenario[x][y] = barco.getId();
                            cantidad--;
                            while (cantidad > 0) {

                                escenario[x][y + cantidad] = barco.getId();
                                cantidad -= 1;
                            }
                        }
                        break;
                    }

                case 3:
                    if ((y - cantidad) < 0 && (x + cantidad) > escenario.length - 1
                            && (y + cantidad) > escenario.length - 1
                            && (x - cantidad) < 0) {
                        sig = rnd.nextInt(3);
                        x = rnd.nextInt(escenario.length);
                        y = rnd.nextInt(escenario.length);
                    } else if ((x - cantidad) < 0) {
                        sig = rnd.nextInt(3);
                        break;
                    } else {
                        nextPos = cantidad -1;
                        while (nextPos >= 0) {

                            if (!escenario[x - cantidad][y].equals("~")) {
                                nextPos = 0;
                                ocupado = true;
                            } else {
                                nextPos--;
                            }
                        }
                        if (ocupado) {
                            sig = rnd.nextInt(3);
                            x = rnd.nextInt(escenario.length);
                            y = rnd.nextInt(escenario.length);
                        } else {
                            escenario[x][y] = barco.getId();
                            cantidad--;
                            while (cantidad > 0) {

                                escenario[x - cantidad][y] = barco.getId();
                                cantidad -= 1;
                            }
                        }
                        break;
                    }

            }
        }
    }

    @Override
    public String toString() {

        String respuesta = "";
        for (int i = 0; i < escenario.length + 1; i++) {
            respuesta += "-\t";
        }
        respuesta += "\n";
        for (int i = 0; i < escenario.length; i++) {
            respuesta += "|";
            for (int j = 0; j < escenario[0].length; j++) {
                respuesta += escenario[i][j] + "\t";
            }
            respuesta += "|\n";
        }
        for (int i = 0; i < escenario.length + 1; i++) {
            respuesta += "-\t";
        }
        return respuesta;

    }

}
