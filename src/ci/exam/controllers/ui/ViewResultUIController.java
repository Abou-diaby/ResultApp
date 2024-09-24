package ci.exam.controllers.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ci.exam.controllers.BaseController;
import ci.exam.ui.FXMLPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ViewResultUIController extends BaseController implements Initializable {
	@FXML
    private Label userName;

    @FXML
    private Label userBirthDate;

    @FXML
    private Label userMat;

    @FXML
    private Label userEtab;

    @FXML
    private Label resultMsg;

    @FXML
    private Label userAverage;

    @FXML
    private Rectangle codeColor;

    @FXML
    private Button showDetailsBtn;

    @FXML
    private VBox detailsContainer; // Conteneur pour les détails de l'examen

    @FXML
    private Button backBtn;

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        // Activer le bouton au démarrage
        showDetailsBtn.setDisable(false);

        // Masquer la section des détails au démarrage
        detailsContainer.setVisible(false);
        
        if (res != null) {
            userName.setText("Nom et prénoms: " + res.getNom() + " " + res.getPrenom()); 
            userBirthDate.setText("Date de naissance: " + res.getDateNaissance().toString()); 
            userMat.setText("Matricule: " + res.getMatricule()); 
            userEtab.setText("Établissement: " + res.getEcole()); 
            
         // Ajout d'emoji en fonction du statut
            if (res.getStatut()) {
                resultMsg.setText("Succès 😊");  // Succès avec emoji heureux
                resultMsg.setTextFill(Color.GREEN);
                codeColor.setFill(Color.GREEN);
            } else {
                resultMsg.setText("Échec 😢");  // Échec avec emoji triste
                resultMsg.setTextFill(Color.RED);
                codeColor.setFill(Color.RED);
            }
            
            userAverage.setText(String.format("Moyenne: %.2f", res.getMoyenne())); // Affichage de la moyenne avec 2 chiffres après la virgule
        }
    }
 // Méthode appelée lorsqu'on clique sur le bouton "Afficher les détails"
    @FXML
    private void handleShowDetails(ActionEvent event) {
        // Rendre visible la section des détails
        detailsContainer.setVisible(true);

        // Désactiver le bouton "Afficher les détails" après son utilisation
        showDetailsBtn.setDisable(true);
        
     // Faire disparaître le bouton "Afficher les détails"
        showDetailsBtn.setVisible(false);
    }

    @FXML
    private void handleBackButtonAction() throws IOException {
        // Réinitialiser les champs avant de revenir à l'accueil
        resetFields();

        // Charger la page précédente et le css
        Parent root = FXMLLoader.load(FXMLPage.HOME.getPage());
        Scene scene = new Scene(root);
        scene.getStylesheets().add("styles.css");

        Stage stage = (Stage) backBtn.getScene().getWindow();
        stage.setScene(scene);
    }

    private void resetFields() {
        userName.setText("");
        userBirthDate.setText("");
        userMat.setText("");
        userEtab.setText("");
        resultMsg.setText("");
        resultMsg.setTextFill(Color.BLACK); 
        userAverage.setText("");
        codeColor.setFill(Color.TRANSPARENT); 
        detailsContainer.setVisible(false); // Masquer les détails
        showDetailsBtn.setDisable(false); // Réactiver le bouton
    }
}

