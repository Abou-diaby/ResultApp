package ci.exam;

import java.io.IOException;

import ci.exam.ui.FXMLPage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ResultApp extends Application {

	@SuppressWarnings("exports")
	@Override
	public void start(Stage stage) throws Exception {
		try {
			Parent root = FXMLLoader.load(FXMLPage.HOME.getPage());

			Scene scene = new Scene(root);
			// Ajout du fichier CSS
			scene.getStylesheets().add("styles.css");
			
			// Charger l'icône
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/icon.png")));
			
			stage.setScene(scene);
			stage.setTitle("Résultat de l'examen national");
			stage.setResizable(false);
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace(); // Affichage de l'erreur dans la console
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
