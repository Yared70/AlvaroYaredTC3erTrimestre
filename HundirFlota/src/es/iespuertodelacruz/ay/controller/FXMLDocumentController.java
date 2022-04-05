/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.ay.controller;

import es.iespuertodelacruz.ay.model.Barco;
import es.iespuertodelacruz.ay.model.Escenario;
import es.iespuertodelacruz.ay.model.InteligenciaArtificial;
import es.iespuertodelacruz.ay.model.Jugador;
import java.net.URL;
import java.util.Iterator;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;

/**
 *
 * @author Yared
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private TextArea pruebas;
    
    InteligenciaArtificial ia;
    Escenario escenarioIA;
    Escenario escenarioJugador;
    
    Barco barco;
    @FXML
    private GridPane panelIA;
    @FXML
    private GridPane panelJugador;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ia = new InteligenciaArtificial(1);
        escenarioIA = new Escenario(1, 4, ia);
        ia.setEscenario(escenarioIA);
        
        Jugador jugador = new Jugador("Jugador1");
        escenarioJugador = new Escenario(2, 4, jugador);
        jugador.setEscenario(escenarioJugador);
        
        //Barcos de la IA
        barco = new Barco(3, "A");
        do{
            barco.colocarBarco();
        }while(!escenarioIA.posicionValida(barco));
        
        Barco barco2 = new Barco(2, "B");
        do{
            barco2.colocarBarco();
        }while(!escenarioIA.posicionValida(barco2));
        
        Barco barco3 = new Barco(2, "C");
        do{
            barco3.colocarBarco();
        }while(!escenarioIA.posicionValida(barco3));
         
        //Barcos del Jugador
        Barco barco4 = new Barco(3, "J1");
        do{
            barco4.colocarBarco();
        }while(!escenarioJugador.posicionValida(barco4));
        
        Barco barco5 = new Barco(2, "J2");
        do{
            barco5.colocarBarco();
        }while(!escenarioJugador.posicionValida(barco5));
        
        Barco barco6 = new Barco(2, "J3");
        do{
            barco6.colocarBarco();
        }while(!escenarioJugador.posicionValida(barco6));
        
        colocarBarcoGrid(barco4);
        colocarBarcoGrid(barco5);
        colocarBarcoGrid(barco6);
        
        System.out.println(escenarioIA);
        System.out.println(escenarioJugador);
        
    }
    
    private void colocarBarcoGrid(Barco barco){
        Iterator<Pair<String, Barco.Estado>> iterator = barco.getPartes().iterator();
        while (iterator.hasNext()) {

            String strCoordenadas = iterator.next().getKey();
            String[] split = strCoordenadas.split(" ");
            Integer x = ( Integer.parseInt(split[0]) > 0 )?Integer.parseInt(split[0]):null;
            Integer y = ( Integer.parseInt(split[1]) > 0 )?Integer.parseInt(split[1]):null;
            //Button boton = panelIA.();
            for(Node node : panelJugador.getChildren()){
                Button boton = (Button)node;
                if(Objects.equals(GridPane.getRowIndex(boton), x) && Objects.equals(GridPane.getColumnIndex(boton), y)){
                    boton.setText(barco.getNombre());
                }
            }
            
        }
    }

    @FXML
    private void seleccionarCasilla(ActionEvent event) {
        Button boton = (Button)event.getSource();
        //System.out.println(boton.getLayoutX());
        //System.out.println(GridPane.getRowIndex(boton) + ", " + GridPane.getColumnIndex(boton));
        int x = (GridPane.getRowIndex(boton) == null) ? 0:GridPane.getRowIndex (boton);
        int y = (GridPane.getColumnIndex(boton) == null) ? 0:GridPane.getColumnIndex (boton);
        System.out.println(x + ", " + y);
        
        
        System.out.println(escenarioIA.elegirCasilla(x, y));
        System.out.println(ia.atacardif1(escenarioJugador));
        
        
    }
    
}
