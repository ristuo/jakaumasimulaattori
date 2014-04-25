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
    
    private JButton uusisimu;
    private KehysAsettelija kehysAsettelija;
    private TilastoAineisto tilastoaineisto;
    private Kayttoliittyma kayttis;
    
    public YhteenvetoKuuntelija(JButton uusisimu, TilastoAineisto tilastoaineisto, Kayttoliittyma kayttis) {
        this.uusisimu = uusisimu;
        this.tilastoaineisto = tilastoaineisto;
        this.kayttis=kayttis;
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == uusisimu)
            kayttis.sulje();
            kayttis.run();
    }
}
