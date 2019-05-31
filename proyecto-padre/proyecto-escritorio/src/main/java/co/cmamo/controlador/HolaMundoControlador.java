package co.cmamo.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;

public class HolaMundoControlador {

    @FXML
    private Button btnHola;

    @FXML
    void sayHelloWorld(ActionEvent event) {
    	Alert alerta = new Alert(AlertType.CONFIRMATION);
    	alerta.setHeaderText("Hello World!");
    	alerta.setContentText("Hola Mundo");
    	alerta.show();
    }

}	
