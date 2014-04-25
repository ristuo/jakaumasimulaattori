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
import uusikayttoliittyma.*;
/**
 *
 * @author rtuomainen
 */
public class TilastoaineistoTest {
    
    TilastoAineisto tilastoaineisto;
    Otosgeneraattori otosgeneraattori = new Otosgeneraattori();
    double[] aineisto = new double[2];
    
    public TilastoaineistoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @Test
    public void gammaOdotusarvoOnOikein() {
        tilastoaineisto = new TilastoAineisto(aineisto, 6, 2, Jakauma.GAMMA);
        double odotusarvo = 0;
        try {
            odotusarvo = tilastoaineisto.getOdotusarvo();
        }
        
        catch (Exception e) {
            odotusarvo = 278054;
        }
        assertEquals(odotusarvo, 3, 0.01);
    }
    
    @Test
    public void ekspOdotusarvoOikein() {
        tilastoaineisto = new TilastoAineisto(aineisto, 5, Jakauma.EKSPONENTTI);
        double odotusarvo = 0;
        try {
            odotusarvo = tilastoaineisto.getOdotusarvo();
        }
        
        catch (Exception e) {
            odotusarvo = 25243;
        }

        assertEquals(odotusarvo, 0.2, 0.1);
    }
    
    @Test
    public void binomiOdotusarvoOikein() {
        tilastoaineisto = new TilastoAineisto(aineisto, 5,0.1, Jakauma.BINOMI);
        double odotusarvo;
        try {
            odotusarvo = tilastoaineisto.getOdotusarvo();
        }
        
        catch (Exception e) {
            odotusarvo = 121123;
        }
        
        assertEquals(odotusarvo, 0.5, 0.01);
        
    }
    
    @Test
    public void poissonHajontaOikein() {
        tilastoaineisto = new TilastoAineisto(aineisto, 9, Jakauma.POISSON);
        double hajonta;
        
        try {
            hajonta = tilastoaineisto.getKeskihajonta();
        }
        catch (Exception e) {
            hajonta = 2753;
        }
        
        assertEquals(hajonta, 3, 0.001);
    }
    
    @Test
    public void binomiHajontaOikein() {
        tilastoaineisto = new TilastoAineisto(aineisto, 36, 0.5, Jakauma.BINOMI);
        double hajonta;
        
        try {
            hajonta = tilastoaineisto.getKeskihajonta();
        }
        
        catch (Exception e) {
            hajonta = 2341;
        }
        
        assertEquals(hajonta, 3, 0.01);
    }
    
    @Test
    public void ekspHajontaOikein() {
        tilastoaineisto = new TilastoAineisto(aineisto, 2, Jakauma.EKSPONENTTI);
        double hajonta;
        try {
            hajonta = tilastoaineisto.getKeskihajonta();
        }
        
        catch (Exception e) {
            hajonta = 123121;
        }
        assertEquals(hajonta, 0.5, 0.01);
    }
    
    @Test
    public void gammaHajontaOikein() {
        tilastoaineisto = new TilastoAineisto(aineisto, 27,3, Jakauma.GAMMA);
        double hajonta;
        
        try {
            hajonta = tilastoaineisto.getKeskihajonta();
        }
        catch (Exception e) {
            hajonta = 123123;
        }
        
        assertEquals(1.73, hajonta, 0.01);
    }
    
    
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
