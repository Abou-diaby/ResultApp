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
            // Informer l'utilisateur que la connexion a échoué
            System.out.println("Une erreur est survenue lors de la connexion. L'application fonctionne en mode déconnecté.");
            connexion = null; // Retourne explicitement null en cas d'échec de connexion
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
    /**
     * Récupère les résultats, à partir d'un matricule, de la base de données.
     * 
     * @param mat Le matricule du candidat
     * @return Le resultat trouvé
     */
    public Result getOne(String mat) {
        Connection connection = null;

        try {
            connection = getConnection();
            if (connection == null) {
                // Si la connexion est null, cela signifie que la base de données n'est pas accessible
                System.out.println("Impossible de récupérer les données. Mode déconnecté activé.");
                return null; // Retourner null ou un objet "Résultat" par défaut si la connexion échoue
            }

            String query = "SELECT r.id, r.moyenne, r.statut, e.matricule, e.nom, e.prenom, e.date_naissance, e.ecole " +
                    "FROM resultats r " +
                    "JOIN etudiants e ON r.etudiant_id = e.id " +
                    "WHERE e.matricule = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setString(1, mat);

                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        int id = rs.getInt("r.id");
                        String matricule = rs.getString("e.matricule");
                        boolean statut = rs.getInt("r.statut") == 1;
                        float moyenne = rs.getFloat("r.moyenne");
                        String nom = rs.getString("e.nom");
                        String prenom = rs.getString("e.prenom");
                        Date dateNaissance = rs.getDate("e.date_naissance");
                        String ecole = rs.getString("e.ecole");
                        return new Result(id, matricule, statut, moyenne, nom, prenom, dateNaissance, ecole);
                    }
                }
            } catch (SQLException e) {
                System.out.println("Erreur: " + e.getMessage());
            }
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Erreur: " + e.getMessage());
                }
            }
        }

        return null;
    }

    /**
     * Récupère les données depuis la base de données et calcule les totaux pour le pourcentage.
     */
    public void fetchData() {
        total = 0;
        totalSucces = 0;

        try (Connection connection = getConnection()) {
            if (connection == null) {
                System.out.println("Connexion échouée. Mode déconnecté activé.");
                return; // Sort de la méthode si aucune connexion n'a pu être établie
            }

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT statut, moyenne FROM resultats");

            while (rs.next()) {
                int statut = rs.getInt("statut");
                float moyenne = rs.getFloat("moyenne");

                total += moyenne;
                if (statut == 1) {
                    totalSucces += moyenne;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des données: " + e.getMessage());
        }

        rate = (total > 0) ? totalSucces / total : 0;
    }

}

