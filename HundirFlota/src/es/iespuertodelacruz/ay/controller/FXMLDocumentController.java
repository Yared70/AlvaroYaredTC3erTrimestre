/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.ay.controller;

import es.iespuertodelacruz.ay.model.Barco;
import es.iespuertodelacruz.ay.model.Escenario;
import es.iespuertodelacruz.ay.model.HundirFlota;
import es.iespuertodelacruz.ay.model.InteligenciaArtificial;
import es.iespuertodelacruz.ay.model.Jugador;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

/**
 *
 * @author Yared
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;

    InteligenciaArtificial ia;
    Escenario escenarioIA;
    Escenario escenarioJugador;
    Jugador jugador;
    Barco barco;
    @FXML
    private GridPane panelIA;
    @FXML
    private GridPane panelJugador;
    @FXML
    private TextArea txaSalida;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        iniciarNuevoJuego();
    }
    
    public void iniciarNuevoJuego(){
        reiniciarGrid();
        ia = new InteligenciaArtificial(1);
        escenarioIA = new Escenario(1, 4, ia);
        ia.setEscenario(escenarioIA);

        jugador = new Jugador("Jugador1");
        escenarioJugador = new Escenario(2, 4, jugador);
        jugador.setEscenario(escenarioJugador);

        //Barcos de la IA
        barco = new Barco(3, "A");
        do {
            barco.colocarBarco();
        } while (!escenarioIA.posicionValida(barco));

        Barco barco2 = new Barco(2, "B");
        do {
            barco2.colocarBarco();
        } while (!escenarioIA.posicionValida(barco2));

        Barco barco3 = new Barco(2, "C");
        do {
            barco3.colocarBarco();
        } while (!escenarioIA.posicionValida(barco3));

        //Barcos del Jugador
        Barco barco4 = new Barco(3, "B1");
        do {
            barco4.colocarBarco();
        } while (!escenarioJugador.posicionValida(barco4));

        Barco barco5 = new Barco(2, "B2");
        do {
            barco5.colocarBarco();
        } while (!escenarioJugador.posicionValida(barco5));

        Barco barco6 = new Barco(2, "B3");
        do {
            barco6.colocarBarco();
        } while (!escenarioJugador.posicionValida(barco6));

        colocarBarcoGrid(barco4);
        colocarBarcoGrid(barco5);
        colocarBarcoGrid(barco6);
        
        txaSalida.setText("Leyenda:\n"
                + "Los barcos del jugador se muestran como B1, B2 y B3.\n"
                + "Cada disparo se refleja por su resultado:\n"
                + "A: Agua\n"
                + "T: Tocado\n"
                + "H: Hundido\n");
    }
    
    @FXML
    private void switchToFacil(ActionEvent event) throws IOException {
        HundirFlota.setRoot("/es/iespuertodelacruz/ay/view/inicio");
    }
    
    public void reiniciarGrid(){
        for (Node node : panelJugador.getChildren()) {
            Button boton = (Button) node;
            boton.setText("  ");
        }
        for (Node node : panelIA.getChildren()) {
            Button boton = (Button) node;
            boton.setText("  ");
            boton.setDisable(false);
        }
    }

    private void colocarBarcoGrid(Barco barco) {
        Iterator<Pair<String, Barco.Estado>> iterator = barco.getPartes().iterator();
        while (iterator.hasNext()) {

            String strCoordenadas = iterator.next().getKey();
            String[] split = strCoordenadas.split(" ");
            Integer x = (Integer.parseInt(split[0]) > 0) ? Integer.parseInt(split[0]) : null;
            Integer y = (Integer.parseInt(split[1]) > 0) ? Integer.parseInt(split[1]) : null;
            //Button boton = panelIA.();
            for (Node node : panelJugador.getChildren()) {
                Button boton = (Button) node;
                if (Objects.equals(GridPane.getRowIndex(boton), x) && Objects.equals(GridPane.getColumnIndex(boton), y)) {
                    boton.setText(barco.getNombre());
                }
            }

        }
    }

    @FXML
    private void seleccionarCasilla(ActionEvent event) {
        Button boton = (Button) event.getSource();
        int x = (GridPane.getRowIndex(boton) == null) ? 0 : GridPane.getRowIndex(boton);
        int y = (GridPane.getColumnIndex(boton) == null) ? 0 : GridPane.getColumnIndex(boton);

        String respuestaJugador = escenarioIA.elegirCasilla(x, y);

        if (respuestaJugador.equals("AGUA!")) {
            boton.setText("A");
            txaSalida.appendText(jugador.getNombre() + ": " + respuestaJugador + "\n");
        } else if ((respuestaJugador).equals("TOCADO!")) {
            boton.setText("T");
            txaSalida.appendText(jugador.getNombre() + ": " + respuestaJugador + "\n");
        } else {
            txaSalida.appendText(jugador.getNombre() + ": " + respuestaJugador + "\n");
            Barco barcoHundido = escenarioIA.getBarco(x, y);
            System.out.println(barcoHundido);
            Iterator<Pair<String, Barco.Estado>> iterator = barcoHundido.getPartes().iterator();
            while (iterator.hasNext()) {
                String strCoordenadas = iterator.next().getKey();
                String[] split = strCoordenadas.split(" ");
                Integer xHundido = (Integer.parseInt(split[0]) > 0) ? Integer.parseInt(split[0]) : null;
                Integer yHundido = (Integer.parseInt(split[1]) > 0) ? Integer.parseInt(split[1]) : null;
                
                for (Node node : panelIA.getChildren()) {
                    Button botonHundido = (Button) node;
                    if (Objects.equals(GridPane.getRowIndex(botonHundido), xHundido) && Objects.equals(GridPane.getColumnIndex(botonHundido), yHundido)) {
                        botonHundido.setText("H");
                    }
                }

            }

        }
        boton.setDisable(true);

        String respuestaIA = ia.atacardif2(escenarioJugador);
        System.out.println(respuestaIA);
        String[] splitIA = ia.getHistorialPosicionesAtacadas().get((ia.getHistorialPosicionesAtacadas().size())-1).split(" ");
        
        Integer xIA = (Integer.parseInt(splitIA[0]) > 0) ? Integer.parseInt(splitIA[0]) : null;
        Integer yIA = (Integer.parseInt(splitIA[1]) > 0) ? Integer.parseInt(splitIA[1]) : null;
        
        if (respuestaIA.contains("AGUA!")) {
            setEstadoCasilla(xIA, yIA, "A");
            txaSalida.appendText("IA: AGUA!\n");
        } else if ((respuestaIA).endsWith("TOCADO!")) {
            setEstadoCasilla(xIA, yIA, "T");
            txaSalida.appendText("IA: TOCADO!\n");
        }else{
            txaSalida.appendText("IA: TOCADO! y HUNDIDO!\n");
            xIA = (xIA == null)?0:xIA;
            yIA = (yIA == null)?0:yIA;
            Barco barcoHundido = escenarioJugador.getBarco(xIA, yIA);
            Iterator<Pair<String, Barco.Estado>> iterator = barcoHundido.getPartes().iterator();
            while (iterator.hasNext()) {

                String strCoordenadas = iterator.next().getKey();
                String[] split = strCoordenadas.split(" ");
                Integer xHundido = (Integer.parseInt(split[0]) > 0) ? Integer.parseInt(split[0]) : null;
                Integer yHundido = (Integer.parseInt(split[1]) > 0) ? Integer.parseInt(split[1]) : null;
                
                for (Node node : panelJugador.getChildren()) {
                    Button botonHundido = (Button) node;
                    if (Objects.equals(GridPane.getRowIndex(botonHundido), xHundido) && Objects.equals(GridPane.getColumnIndex(botonHundido), yHundido)) {
                        setEstadoCasilla(xHundido, yHundido, "H");
                    }
                }

            }
        }

        if (escenarioIA.ganado()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Has ganado");
            alert.showAndWait();
            iniciarNuevoJuego();
        }else if (escenarioJugador.ganado()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Has perdido");
            alert.showAndWait();
            iniciarNuevoJuego();
        }
    }
    
    private void setEstadoCasilla(Integer x, Integer y, String estado){
        for (Node node : panelJugador.getChildren()) {
            Button boton = (Button) node;
            if (Objects.equals(GridPane.getRowIndex(boton), x) && Objects.equals(GridPane.getColumnIndex(boton), y)) {
                boton.setText(estado);
            }
        }
    }

}
