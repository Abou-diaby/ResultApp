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

}
