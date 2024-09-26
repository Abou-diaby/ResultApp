package ci.exam;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import ci.exam.ui.FXMLPage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ResultApp extends Application {

    private static final Logger LOGGER = Logger.getLogger(ResultApp.class.getName());

    @SuppressWarnings("exports")
	@Override
    public void start(Stage stage) throws Exception {
        try {
            // Charger l'interface graphique en premier
            Parent root = FXMLLoader.load(FXMLPage.HOME.getPage());

            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

            stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/icon.png")));
            stage.setScene(scene);
            stage.setTitle("Résultat de l'examen national");
            stage.setResizable(false);
            stage.show();

            // Essayer de se connecter à la base de données après l'affichage
            initializeDatabaseConnection();

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Erreur lors du chargement du fichier FXML.", e);
            showErrorWindow(stage, "Erreur critique : Impossible de charger l'interface.");
        }
    }

    /**
     * Essaye de se connecter à la base de données après l'affichage de la fenêtre.
     */
    private void initializeDatabaseConnection() {
        // Essayer la connexion après affichage de l'interface graphique
        try {
            // Appel à la méthode de connexion (qui peut lancer une exception)
            new ci.exam.controllers.BaseController().getConnection();
        } catch (Exception e) {
            // Logguer l'erreur de connexion sans interrompre l'affichage
            LOGGER.log(Level.WARNING, "Connexion à la base de données échouée.", e);
            showDatabaseErrorMessage();
        }
    }

    /**
     * Affiche une fenêtre d'erreur si la connexion à la base de données échoue.
     */
    private void showDatabaseErrorMessage() {
        // Ici, tu peux montrer un message à l'utilisateur ou simplement logguer l'erreur
        System.out.println("Erreur de connexion à la base de données. L'application fonctionne en mode déconnecté.");
    }

    private void showErrorWindow(Stage stage, String message) {
        // Créer une petite fenêtre pour afficher le message d'erreur
        Stage errorStage = new Stage();
        Scene errorScene = new Scene(new javafx.scene.control.Label(message), 300, 200);
        errorStage.setScene(errorScene);
        errorStage.setTitle("Erreur");
        errorStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
