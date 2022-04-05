/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.ay.controller;

import es.iespuertodelacruz.ay.model.HundirFlota;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Samuel González Machado
 */
public class InicioController {   

    @FXML
    private Button btnFacil;

    @FXML
    private void switchToFacil(ActionEvent event) throws IOException {
        HundirFlota.setRoot("/es/iespuertodelacruz/ay/view/facil");
    }
    
}
