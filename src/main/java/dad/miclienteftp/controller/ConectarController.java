package dad.miclienteftp.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.commons.net.ftp.FTPClient;

import dad.miclienteftp.App;
import dad.miclienteftp.resources.FTPColumns;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class ConectarController implements Initializable {

	private ConectarModel conectarModel = new ConectarModel();
	
	@FXML
    private Button cancelarButton;

    @FXML
    private Button conectarButton;

    @FXML
    private PasswordField contraseñaField;

    @FXML
    private TextField puertoField;

    @FXML
    private TextField servidorField;

    @FXML
    private TextField usuarioField;

    @FXML
    private GridPane view;
    
    private Stage stage;
	
	public ConectarController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/IniciarConexion.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		servidorField.textProperty().bindBidirectional(conectarModel.servidorProperty());
		puertoField.textProperty().bindBidirectional(conectarModel.puertoProperty(), new NumberStringConverter());
		usuarioField.textProperty().bindBidirectional(conectarModel.usuarioProperty());
		contraseñaField.textProperty().bindBidirectional(conectarModel.contraseñaProperty());
		
		stage = new Stage();
		stage.setTitle("Conectar");
		stage.setScene(new Scene(getView()));
		stage.initOwner(App.primaryStage);
		stage.initModality(Modality.APPLICATION_MODAL);
		
	}
	
	@FXML
    void onCancelarAction(ActionEvent event) {
		stage.close();
    }

    @FXML
    void onConectarAction(ActionEvent event)  {
    	try {
			conectarModel.setClienteFTP(new FTPClient());
			
			// conectar con el servidor FTP
			conectarModel.getClienteFTP().connect(conectarModel.getServidor(), conectarModel.getPuerto());
			
			// iniciar sesión anónima (login)
			conectarModel.getClienteFTP().login(conectarModel.getUsuario(), conectarModel.getContraseña());
			
			// cambiar el directorio actual en el servidor
			conectarModel.getClienteFTP().changeWorkingDirectory("/debian/dists");

			// recuperar un listado de los ficheros y directorios del directorio actual del
			// servidor
			conectarModel.setFicheros(FTPColumns.toFTPColumnsArray(conectarModel.getClienteFTP().listFiles()));
			
			conectarModel.setConectado(true);
			
			stage.close();
		} catch (IOException e) {
			conectarModel.setConectado(false);
			System.out.println("No se pudo conectar a el servidor FTP");
		}
    }
    
    public void show() {
    	stage.showAndWait();
    }
    
    public boolean IsConectado() {
		return conectarModel.isConectado();
    }
    
    public void setConectado(boolean b) {
    	conectarModel.setConectado(b);
    }
    
    public void updateFicheros() {
    	try {
			conectarModel.setFicheros(FTPColumns.toFTPColumnsArray(conectarModel.getClienteFTP().listFiles()));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
	public Button getCancelarButton() {
		return cancelarButton;
	}

	public Button getConectarButton() {
		return conectarButton;
	}

	public ConectarModel getConectarModel() {
		return conectarModel;
	}
	
	public GridPane getView() {
		return view;
	}
}
