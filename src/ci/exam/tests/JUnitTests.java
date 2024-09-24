package ci.exam.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
}
