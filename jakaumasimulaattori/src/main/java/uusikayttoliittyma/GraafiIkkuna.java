/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uusikayttoliittyma;
import java.awt.*;
import javax.swing.*;
import ohha.jakaumasimulaattori.*;
/**
 *
 * @author rtuomainen
 */
public class GraafiIkkuna extends JFrame {
    
    private GraafiPaneeli graafipaneeli;
    private Graphics g = this.getGraphics();
    
    public GraafiIkkuna() {      
        
    }
    
    public void piirraHistogrammi(TilastoAineisto tilastoaineisto) {
        this.getContentPane().add(graafipaneeli);
        graafipaneeli.piirraHistogrammi(g, tilastoaineisto);
    }
    
}
