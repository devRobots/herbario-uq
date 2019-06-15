package co.cmamo.controlador;

import java.io.IOException;

import co.cmamo.Main;
import co.cmamo.modelo.AdministradorDelegado;
import co.cmamo.util.Utilidades;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginControlador {
	private AdministradorDelegado delegado = AdministradorDelegado.administradorDelegado;

	private Stage escenarioInicial;
	
	private AnchorPane borderPane;
	
	@FXML
	private ImageView logo;

    @FXML
    private TextField txtCorreo;

    @FXML
    private PasswordField txtClave;

    @FXML
    void login(ActionEvent event) {
    	String correo = txtCorreo.getText().trim();
    	String clave = txtClave.getText().trim();
    	
    	if (!correo.isEmpty() && !clave.isEmpty()) {
    		if (delegado.iniciarSesion(correo, clave)) {
        		Utilidades.mostrarMensaje("Informacion", "Bienvenido!", 0);
        		txtClave.clear();
        		new ManejadorEscenarios(new Stage());
    		}
    		else {    			
    			Utilidades.mostrarMensaje("Error", "Los campos ingresados no aparecen registrador", -2);
    		}
		}
    	else {
    		Utilidades.mostrarMensaje("Advertencia", "Debe ingresar todos los campos", -1);
    	}
    }

    @FXML
    void reminder(ActionEvent event) {

    }

    @FXML
    void initialize() {
    	
    }

    public LoginControlador() {
		// TODO Auto-generated constructor stub
	}
    
    public LoginControlador(Stage escenario) {
    	escenario.setMinWidth(400);
		escenario.setMinHeight(400);
		escenario.setMaxWidth(600);
		escenario.setMaxHeight(600);
		
		escenarioInicial = escenario;

		try {
			// se inicializa el escenario
			escenario.setTitle("Herbario UQ");

			// se carga la vista
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./vista/log_in.fxml"));

			borderPane = (AnchorPane) loader.load();

			// se carga la escena
			Scene scene = new Scene(borderPane);
			escenario.setScene(scene);
			escenario.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setEscenarioInicial(Stage escenarioInicial) {
		this.escenarioInicial = escenarioInicial;
	}
}