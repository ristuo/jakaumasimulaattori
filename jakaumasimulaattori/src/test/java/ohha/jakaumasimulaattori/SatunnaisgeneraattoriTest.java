/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ohha.jakaumasimulaattori;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rtuomainen
 */
public class SatunnaisgeneraattoriTest {
    
    Satunnaisgeneraattori satunnaisgeneraattori;
    
    public SatunnaisgeneraattoriTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        satunnaisgeneraattori = new Satunnaisgeneraattori();
    }
    
    @After
    public void tearDown() {
    }

    
    @Test
    public void PoissonGeneraattoriPalauttaaPositiivisiaArvoja() {
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
