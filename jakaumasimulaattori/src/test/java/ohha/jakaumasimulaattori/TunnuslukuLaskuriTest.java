
package ohha.jakaumasimulaattori;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import uusikayttoliittyma.*;

public class TunnuslukuLaskuriTest {
   
    
//    
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
        TilastoAineisto tilastoaineisto = new TilastoAineisto(aineisto,2,Jakauma.NORMAALI);
        for (int i = 1; i <= 5; i++) {
            aineisto[i-1] = i;
        }
        double x = tuksu.laskeKeskiarvo(tilastoaineisto);
        boolean testi = false;
        if (x == 3) {
            testi = true;
            
        }
        
        assertTrue(testi);
        
    }
    
    @Test
    public void karvoLaskuriSuhtautuuNegatiivisiinLukuihin() {
        double[] aineisto = new double[2];
        aineisto[0] = -40;
        aineisto[1] = 20;
        TilastoAineisto tilastoaineisto = new TilastoAineisto(aineisto,2,Jakauma.NORMAALI);
        
        assertEquals(tuksu.laskeKeskiarvo(tilastoaineisto),-10,0.001);
    }
    
    @Test
    public void hajontaLaskuriAntaaOikeitaLukuja() {
        double[] aineisto = new double[20];
        for (int i = 0; i < 20; i++) {
            aineisto[i] = i+1;
        }
        TilastoAineisto tilastoaineisto = new TilastoAineisto(aineisto, 2, Jakauma.NORMAALI);
        
        assertEquals(tuksu.laskeOtoskeskihajonta(tilastoaineisto),5.91,0.01);
    }
    
    
    @Test
    public void hajontaLaskuriHoitaaNegatiiviset() {
        double[] aineisto = new double[20];
        double k = -10;
        for (int i = 0; i < 20; i++) {
            
            aineisto[i] = k+(i+1);
        }
        TilastoAineisto tilastoaineisto = new TilastoAineisto(aineisto, 2, Jakauma.NORMAALI);
        assertEquals(tuksu.laskeOtoskeskihajonta(tilastoaineisto),5.91,0.01);
    }
    
    
  
}
