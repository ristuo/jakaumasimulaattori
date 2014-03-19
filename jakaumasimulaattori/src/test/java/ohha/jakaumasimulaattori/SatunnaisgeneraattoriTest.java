
package ohha.jakaumasimulaattori;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class SatunnaisgeneraattoriTest {
    
    Satunnaisgeneraattori satunnaisgeneraattori;
    
    public SatunnaisgeneraattoriTest() {
    }
       
    @Before
    public void setUp() {
        satunnaisgeneraattori = new Satunnaisgeneraattori();
    }
    

    @Test
    public void normaaliJakaumaGeneroiMielekkaitaArvoja() {
        double x = satunnaisgeneraattori.generoiNormaali(5.5,0.5);
        boolean testi = true;
        
        if (Math.abs(x-5.5) > 2) {
            testi = false;
        }
       
        assertTrue(testi);
    }
    
    
    @Test
    public void poissonGeneraattoriPalauttaaPositiivisiaArvoja() {
        int x = satunnaisgeneraattori.generoiPoisson(2.5);
        boolean testi = true;
        
        if (x < 0) {
            testi = false;
        }
        
        assertTrue(testi);
    }
    
    
    
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
