/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uusikayttoliittyma;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import ohha.jakaumasimulaattori.*;
/**
 *
 * @author rtuomainen
 */
public class YhteenvetoKuuntelija implements ActionListener {
    
    private JButton piirrahistogrammi;
    private KehysAsettelija kehysAsettelija;
    private TilastoAineisto tilastoaineisto;
    private Kayttoliittyma kayttis;
    
    public YhteenvetoKuuntelija(JButton histogrammi, TilastoAineisto tilastoaineisto, Kayttoliittyma kayttis) {
        this.piirrahistogrammi = histogrammi;
        this.tilastoaineisto = tilastoaineisto;
        this.kayttis=kayttis;
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == piirrahistogrammi)
            kayttis.luoGraafiIkkuna();
    }
}
