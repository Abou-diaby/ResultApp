package ci.exam.tests;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ci.exam.model.Result;

public class JUnitTests {
	
	private Result result;
    
    @BeforeEach
    public void setUp() {
        // Initialisation avec des données d'exemple
        result = new Result(1, "0075762", true, 15.75f, "Diaby", "Aboubacar", java.sql.Date.valueOf("2002-07-03"), "EST LOKO");
    }
    
    // Test 1: Test de recherche par matricule valide
    @Test
    public void testValidMatricule() {
        assertEquals("0075762", result.getMatricule(), () -> "Le matricule doit être valide.");
    }
    
    // Test 2: Test de recherche par matricule inexistant
    @Test
    public void testInvalidMatricule() {
        assertNotEquals("0024577", result.getMatricule(), () -> "Le matricule ne doit pas être inexistant.");
    }
    
    // Test 3: Test de validation des champs de texte
    @Test
    public void testTextFields() {
        assertNotNull(result.getNom(), () -> "Le nom ne doit pas être null.");
        assertNotNull(result.getPrenom(), () -> "Le prénom ne doit pas être null.");
        assertNotNull(result.getEcole(), () -> "L'école ne doit pas être null.");
    }
    
    // Test 4: Test de la connexion à la base de données
    @Test
    public void testDatabaseConnection() {
        assertDoesNotThrow(() -> {
            try (Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://ls-0f19f4268096a452a869b6f8467bc299c51da519.cz6cgwgke8xd.eu-west-3.rds.amazonaws.com:3306/db0075762", 
                    "user0075762", 
                    "Yf3IgyBsOPa34WR")) {
                
                // Vérifie que la connexion n'est pas null
                assertNotNull(connection, () -> "La connexion ne doit pas être null.");
                
            } catch (SQLException e) {
                // Lève une exception en cas d'erreur de connexion à la base de données
                fail("Échec de la connexion à la base de données : " + e.getMessage());
            }
        }, "La connexion à la base de données ne doit pas lever d'exception.");
    }
    
    // Test 5: Test de l’affichage des résultats
    @Test
    public void testResultDisplay() {
        assertTrue(result.getStatut(), () -> "Le statut doit être 'Succès'.");
    }
}
