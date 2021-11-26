package dad.miclienteftp.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.miclienteftp.App;
import dad.miclienteftp.resources.FTPColumns;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;

public class MainController implements Initializable {

	private ConectarController conectarController = new ConectarController();

	@FXML
	private MenuItem conectarItem;

	@FXML
	private Button descargarButton;

	@FXML
	private MenuItem desconectarItem;

	@FXML
	private TableView<FTPColumns> directorioTableView;

	@FXML
	private TableColumn<FTPColumns, String> nombreColumn;

	@FXML
	private TableColumn<FTPColumns, Integer> tamañoColumn;

	@FXML
	private TableColumn<FTPColumns, String> tipoColumn;

	@FXML
	private VBox view;

	public MainController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MiClienteFTP.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		conectarItem.disableProperty().bind(conectarController.getConectarModel().conectadoProperty());
		desconectarItem.disableProperty().bind(conectarController.getConectarModel().conectadoProperty().not());

		nombreColumn.setCellValueFactory(new PropertyValueFactory<FTPColumns, String>("nombre"));
		tamañoColumn.setCellValueFactory(new PropertyValueFactory<FTPColumns, Integer>("tamaño"));
		tipoColumn.setCellValueFactory(new PropertyValueFactory<FTPColumns, String>("tipo"));

	}

	@FXML
	void OnConectarAction(ActionEvent event) {
		conectarController.show();
		if (conectarController.IsConectado()) {
			directorioTableView.getItems().addAll(conectarController.getConectarModel().getFicheros());
		}
	}

	@FXML
	void OnDescargarAction(ActionEvent event) throws IOException {
		if (directorioTableView.getSelectionModel().getSelectedItem() != null
				&& directorioTableView.getSelectionModel().getSelectedItem().getTipo().equals("Fichero")) {
			DirectoryChooser dc = new DirectoryChooser();
			dc.setInitialDirectory(new File("/"));
			File selectedDirectory = dc.showDialog(App.primaryStage);
			File selectedFile = new File(directorioTableView.getSelectionModel().getSelectedItem().getNombre());
			
			FileOutputStream fos = new FileOutputStream(selectedDirectory+"/"+selectedFile);
			conectarController.getConectarModel().getClienteFTP().retrieveFile(selectedFile.getName(), fos);
			fos.flush();
			fos.close();
		}
	}

	@FXML
	void OnDesconectarAction(ActionEvent event) {
		try {
			conectarController.getConectarModel().getClienteFTP().disconnect();
			directorioTableView.getItems().clear();
			conectarController.setConectado(false);
		} catch (IOException e) {
			System.out.println("No se pudo desconectar del servidor FTP");
			e.printStackTrace();
		}
	}

	@FXML
	void onDirectorioMouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2 && directorioTableView.getSelectionModel().getSelectedItem() != null) {
			if (directorioTableView.getSelectionModel().getSelectedItem().getTipo() == "Directorio") {
				try {
					String path = conectarController.getConectarModel().getClienteFTP().printWorkingDirectory();
					String destino = directorioTableView.getSelectionModel().getSelectedItem().getNombre();
					conectarController.getConectarModel().getClienteFTP().changeWorkingDirectory(path+"/"+destino);
					
					directorioTableView.getItems().clear();
					conectarController.updateFicheros();
					directorioTableView.getItems().addAll(conectarController.getConectarModel().getFicheros());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	public ConectarController getConectarController() {
		return conectarController;
	}

	public MenuItem getConectarItem() {
		return conectarItem;
	}

	public Button getDescargarButton() {
		return descargarButton;
	}

	public MenuItem getDesconectarItem() {
		return desconectarItem;
	}

	public TableView<?> getDirectorioTableView() {
		return directorioTableView;
	}

	public VBox getView() {
		return view;
	}
}
