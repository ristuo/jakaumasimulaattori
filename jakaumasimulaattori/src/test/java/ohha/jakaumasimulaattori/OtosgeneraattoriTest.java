
package ohha.jakaumasimulaattori;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class OtosgeneraattoriTest {
    
    Otosgeneraattori otosgeneraattori;
    TunnuslukuLaskuri laskuri = new TunnuslukuLaskuri();
    
    public OtosgeneraattoriTest() {
    }
    
   
    
    @Before
    public void setUp() {
        otosgeneraattori = new Otosgeneraattori();
    }
    
    @Test 
    public void binomiAineistonKarvoOnOikein() {
        int[] aineisto = otosgeneraattori.binomiAineisto(1000, 0.3);
        boolean testi = true;
        if (Math.abs(laskuri.laskeSumma(aineisto)-300)>50) {
            testi = false;
        }
       
        assertTrue(testi);
    }
    
    @Test
    public void onkoNormaalijakaumanKarvoOikea() {
        double[] aineisto = otosgeneraattori.normaaliAineisto(1000, 5, 0.1);
        boolean testi = true;
        if (Math.abs(laskuri.laskeKeskiarvo(aineisto)-5) > 0.1) {
            testi = false;
        }
        
        assertTrue(testi);
    }
    
    
    
    @Test
    public void onkoEksponenttiAineistollaOikeaKeskiarvo() {
        double[] aineisto = otosgeneraattori.eksponenttiAineisto(1000, 2.5);
        double summa = 0;
        double keskiarvonEstimaatti;
        boolean testi = false;
        // tunnetusti eksponenttijakauman odotusarvo on lambda-parametrin kaanteisluku
        for (int i = 0; i < 1000; i++) {
            summa += aineisto[i];
        }
        
        keskiarvonEstimaatti = summa/1000;
        // nythän voisi johtaa täsmällisen testin gammajakauman avulla jne., mutta tässä
        // on hieman oiottu mutkia ja päätetty, että keskiarvo on riittävän hyvä, jos se on 
        // välillä 0.3 - 0.5. odotusarvo olisi 0.4
        if (!(keskiarvonEstimaatti > 0.5 || keskiarvonEstimaatti < 0.3)) {
            testi = true;
        }
        
        assertTrue(testi);
        
    }
    
    @Test
    public void onkoPoissonAineistollaOikeaKeskiarvo() {
        double[] aineisto = otosgeneraattori.poissonAineisto(10000, 0.5);
        double x = laskuri.laskeKeskiarvo(aineisto);
        
        boolean testi = true;
       
        if (Math.abs(x - 0.5) > 0.1) {
            testi=false;
        }
        
        assertTrue(testi);
    }
    
    @Test
    public void onkoPoissonAineistollaOikeaOtoskeskihajonta() {
        double[] aineisto = otosgeneraattori.poissonAineisto(10000, 0.5);
        double x = Math.pow(laskuri.laskeOtoskeskihajonta(aineisto),2);
        boolean testi = true;
        if (Math.abs(x-0.5) > 0.1) {
            testi=false;
        }
        assertTrue(testi);
    }
    
    
}
