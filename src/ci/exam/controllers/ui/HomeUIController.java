package ci.exam.controllers.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ci.exam.controllers.BaseController;
import ci.exam.ui.FXMLPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class HomeUIController extends BaseController implements Initializable {
	@FXML
    private Label successRate;
    
    @FXML
    private TextField mat;

    /**
     * Modifie le resultat sélectionnée.
     * 
     * @param event L'événement déclencheur
     * @throws IOException Si une exception d'entrée ou sortie se produit
     */
    @FXML
    void doViewResult(ActionEvent event) throws IOException {
        String matricule = mat.getText().trim(); // Récupérer le matricule depuis le champ de texte

        // Vérifier si le matricule est valide et non vide
        if (matricule != null && !matricule.isEmpty() && matricule.length() >= 7) {
            res = getOne(matricule);
            if (res != null) {
                navigate(event, FXMLPage.VIEW.getPage());
            } else {
                showAlert(AlertType.WARNING, "Avertissement", "Matricule incorrect", "Aucun résultat trouvé pour ce matricule.");
            }
        } else {
            showAlert(AlertType.WARNING, "Avertissement", "Matricule incorrect", "Veuillez renseigner un matricule valide (au moins 7 caractères).");
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Calculer le pourcentage de succès et mettre à jour l'affichage
        calculPct();
        successRate.setText(getFormattedSuccessRate() + " %");

        // Mettre à jour userMat lorsque le texte change
        mat.setOnKeyReleased(e -> {
            if (mat.getText().length() >= 7) userMat = mat.getText().trim();
        });
    }
    
    /**
     * Calcule le taux de réussite formaté à deux décimales.
     * 
     * @return Le taux de réussite formaté
     */
    private String getFormattedSuccessRate() {
        // Formater le taux de réussite en pourcentage avec 2 chiffres après la virgule
        return String.format("%.2f", rate * 100);
    }

    /**
     * Affiche une alerte avec un titre, un en-tête et un message.
     * 
     * @param type Type d'alerte
     * @param title Titre de l'alerte
     * @param headerText En-tête de l'alerte
     * @param contentText Contenu de l'alerte
     */
    private void showAlert(AlertType type, String title, String headerText, String contentText) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}
