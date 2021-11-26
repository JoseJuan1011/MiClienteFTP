package dad.miclienteftp;

import dad.miclienteftp.controller.MainController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

	private MainController controller = new MainController();
	
	public static Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene = new Scene(controller.getView());
		
		App.primaryStage = primaryStage;
		
		primaryStage.setTitle("Mi Cliente FTP");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
