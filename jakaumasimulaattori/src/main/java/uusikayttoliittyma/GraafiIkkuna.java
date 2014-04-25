
package uusikayttoliittyma;
import java.awt.*;
import javax.swing.*;
import ohha.jakaumasimulaattori.*;
/**
 * Luokka sisältää graafien esittämisessä käytetyn ikkunan.
 * @author rtuomainen
 */
public class GraafiIkkuna extends JFrame {
    
    /** Graafipaneeli vastaa ikkunan sisällöstä, olennaisesti siis tilastokuvasta */
    private GraafiPaneeli graafipaneeli;
    private Graphics g = this.getGraphics();
    
    public GraafiIkkuna() {      
        
    }
    
    
    /**
     * Metodi piirtää histogrammin ikkunaan.
     * @param tilastoaineisto on jokin tilastoaineisto.
     */
    public void piirraHistogrammi(TilastoAineisto tilastoaineisto) {
        this.getContentPane().add(graafipaneeli);
        graafipaneeli.piirraHistogrammi(g, tilastoaineisto);
    }
    
}
