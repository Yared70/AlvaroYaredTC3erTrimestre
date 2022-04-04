/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.ay.controller;

import es.iespuertodelacruz.ay.model.Barco;
import es.iespuertodelacruz.ay.model.Escenario;
import es.iespuertodelacruz.ay.model.Jugador;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Yared
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private GridPane panel;
    @FXML
    private TextArea pruebas;
    
    Escenario escenario;
    
    Barco barco;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        barco = new Barco(3, "A");
        barco.colocarBarco();
        escenario = new Escenario(1, 4, new Jugador("Jugador1"));
        escenario.posicionValida(barco);
        System.out.println(escenario);
        
    }    

    private void seleccionarCasilla(MouseEvent event) {
        /*Button boton = (Button)event.getSource();
        int num = Integer.parseInt(boton.getText());
        pruebas.appendText("" + num + "\n");
        System.out.println(boton.getText());*/
        
    }
    
    

    @FXML
    private void seleccionarCasilla(ActionEvent event) {
        Button boton = (Button)event.getSource();
        //System.out.println(boton.getLayoutX());
        //System.out.println(GridPane.getRowIndex(boton) + ", " + GridPane.getColumnIndex(boton));
        int x = (GridPane.getRowIndex(boton) == null) ? 0:GridPane.getRowIndex (boton);
        int y = (GridPane.getColumnIndex(boton) == null) ? 0:GridPane.getColumnIndex (boton);
        System.out.println(x + ", " + y);
        
        
        System.out.println(escenario.elegirCasilla(x, y));
        
        
    }
    
}
