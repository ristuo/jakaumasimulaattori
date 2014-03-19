
package ohha.jakaumasimulaattori;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class TunnuslukuLaskuriTest {
   
    
    
    TunnuslukuLaskuri tuksu;
    
    public TunnuslukuLaskuriTest() {
    }
    
    
    
    @Before
    public void setUp() {
        tuksu = new TunnuslukuLaskuri();
    }
    
  

    
     @Test
    public void laskuriLaskeeKeskiarvonOikein() {
        double[] aineisto = new double[5];
        for (int i = 1; i <= 5; i++) {
            aineisto[i-1] = i;
        }
        double x = tuksu.laskeKeskiarvo(aineisto);
        boolean testi = false;
        if (x == 3) {
            testi = true;
            
        }
        
        assertTrue(testi);
        
    }
    
    public void karvoLaskuriSuhtautuuNegatiivisiinLukuihin() {
        double[] aineisto = new double[2];
        aineisto[0] = -40;
        aineisto[1] = 20;
        
        assertEquals(tuksu.laskeKeskiarvo(aineisto),-10,0.001);
    }
    
    public void hajontaLaskuriAntaaOikeitaLukuja() {
        double[] aineisto = new double[20];
        for (int i = 0; i < 20; i++) {
            aineisto[i] = i+1;
        }
        
       assertEquals(tuksu.laskeOtoskeskihajonta(aineisto),5.91,0.0001);
    }
    
    public void hajontaLaskuriHoitaaNegatiiviset() {
        double[] aineisto = new double[20];
        double k = -10;
        for (int i = 0; i < 20; i++) {
            
            aineisto[i] = k+(i+1);
        }
        
       assertEquals(tuksu.laskeOtoskeskihajonta(aineisto),5.91,0.0001);
    }
    
    
  
}
