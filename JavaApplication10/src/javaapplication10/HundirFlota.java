/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication10;

import java.util.Random;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Yared
 */
//public class HundirFlota extends Application {
public class HundirFlota{    
    /*
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/es/iespuertodelacruz/ay/view/FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    /*
    public static void main(String[] args) {
        launch(args);
    }
    */
    
    public static void main(String[] args) {
        
        Escenario escenario1 = new Escenario(1, 4);
        
        Barco barco1 = new Barco("b1", 3, escenario1);
        
        Barco barco2 = new Barco("b2", 3, escenario1);
        

        escenario1.addBarco(barco1);
        escenario1.addBarco(barco2);
        
        System.out.println(escenario1);

        
    }
    
}
