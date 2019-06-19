package co.cmamo.controlador;

import java.io.IOException;
import java.net.URL;

import org.hibernate.validator.internal.util.privilegedactions.GetClassLoader;

import co.cmamo.Main;
import co.cmamo.Persona;
import co.cmamo.ejbs.JavaMailSession;
import co.cmamo.modelo.AdministradorDelegado;
import co.cmamo.util.Utilidades;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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
			} else {
				Utilidades.mostrarMensaje("Error", "Los campos ingresados no aparecen registrador", -2);
			}
		} else {
			Utilidades.mostrarMensaje("Advertencia", "Debe ingresar todos los campos", -1);
		}
	}

	@FXML
	void reminder(ActionEvent event) {
		String correo = txtCorreo.getText().trim();
		if (!correo.isEmpty()) {
			Persona p = delegado.buscarPersonaPorCorreo(correo);

			JavaMailSession javaMail = new JavaMailSession();
			
			if (javaMail.enviarMensaje("Recuperacion de contraseña",
					"Correo electronico: " + p.getCorreo() + "\nClave: " + p.getClave(), 
					p.getCorreo())) {				
				Utilidades.mostrarMensaje("Informacion", "Se envio su clave al correo: " + correo, 0);
			}
			else {
				Utilidades.mostrarMensaje("Error", "No se pudo enviar el mensaje", -2);
			}
		}
		else {
			Utilidades.mostrarMensaje("Advertencia", "Ingrese el correo electronico", -1);
		}
	}

	@FXML
	void initialize() {
//    	logo.setImage(new Image(getClass().getResource("/images/logo.png").getFile()));
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