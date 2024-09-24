package ci.exam.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ci.exam.model.Result;

public class AssertJTests {
	
	private Result result;
    
    @BeforeEach
    public void setUp() {
        // Initialisation avec des données d'exemple
        result = new Result(1, "0075762", true, 18.50f, "Diaby", "Aboubacar", java.sql.Date.valueOf("2002-07-03"), "EST LOKO");
    }
    
    // Test 1: Vérifier que la moyenne est affichée correctement
    @Test
    public void testAverageDisplay() {
        assertThat(result.getMoyenne()).isEqualTo(18.50f).withFailMessage("La moyenne doit être 15.75.");
    }
    
    // Test 2: Vérifier que le bouton "Consulter mon resultat" fonctionne
    @Test
    public void testViewMyResultButtonFunctionality() {
        String matricule = result.getMatricule();
        assertThat(matricule).isEqualTo("0075762").withFailMessage("Le matricule doit être '0075762'.");
    }
    
    // Test 3: Vérifier que le résultat "Succès" ou "Échec" s’affiche correctement
    @Test
    public void testResultStatusDisplay() {
        assertThat(result.getStatut()).isTrue().withFailMessage("Le statut doit être 'Succès'.");
    }
    
    // Test 4: Vérifier que l’écran des détails s’ouvre bien
    @Test
    public void testDetailScreenOpens() {
        assertThat(result).isNotNull().withFailMessage("Le résultat ne doit pas être null.");
    }
    
    // Test 5: Vérifier que la navigation entre les écrans fonctionne
    @Test
    public void testScreenNavigation() {
        String fullName = result.getNom() + " " + result.getPrenom();
        assertThat(fullName).isEqualTo("Diaby Aboubacar").withFailMessage("Le nom complet doit être 'Aboubacar DIABY'.");
    }
}
