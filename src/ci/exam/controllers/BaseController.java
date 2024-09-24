package ci.exam.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import ci.exam.model.Result;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class BaseController {
	private static final String URL = "jdbc:mysql://ls-0f19f4268096a452a869b6f8467bc299c51da519.cz6cgwgke8xd.eu-west-3.rds.amazonaws.com:3306/db0075762";
    private static final String USER = "user0075762";
    private static final String PASS = "Yf3IgyBsOPa34WR";
    
    protected static String userMat;
    protected static Result res;
    protected static float total = 0;
    protected static float totalSucces = 0; 
    protected static float rate = 0;
    
    /**
     * Navigue vers une autre page.
     * 
     * @param event L'événement déclencheur
     * @param fxmlDocName Le fichier FXML de la page cible
     * @throws IOException Si une exception d'entrée ou sortie se produit
     */
    protected void navigate(Event event, URL fxmlDocName) throws IOException {
        Parent pageParent = FXMLLoader.load(fxmlDocName);
        Scene scene = new Scene(pageParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.hide();
        appStage.setScene(scene);
        appStage.show();
    }

    /**
     * Obtention d'une connexion à la base de données.
     * 
     * @return La connexion à la base de données
     */
    public Connection getConnection() {
        Connection connexion = null;

        try {
            connexion = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Connexion établie.");
        } catch (SQLException e) {
            System.out.println("Une erreur est survenue lors de la connexion. Contenu: " + e.getMessage());
        }

        return connexion;
    }
    /**
     * Calcule le pourcentage de succès basé sur les données récupérées.
     */
    public void calculPct() {
        fetchData();
        rate = total != 0 ? totalSucces / total : 0;
    }
}
