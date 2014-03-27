
package kayttoliittyma;
import java.awt.event.*;

import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;
import javax.swing.*;
import java.io.*;

public class JakaumanValintaKuuntelija implements ActionListener {
    
    
    
    
    private boolean onkoJakaumaValittuna = false;
    private boolean onkoTiedostoa;
    private JFrame jakaumanValintaKehys;
    private JButton ok;
    private JButton tiedostonValitsija;
    private ButtonGroup jakaumat;
    private File tallennusTiedosto;
    
    
    public JakaumanValintaKuuntelija(ButtonGroup jakaumat, JButton ok, JButton tiedostonValitsija, JFrame jakaumanValintaKehys) {
        this.jakaumat = jakaumat;
        this.ok = ok;
        this.jakaumanValintaKehys = jakaumanValintaKehys;
        this.tiedostonValitsija = tiedostonValitsija;
        
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == jakaumat) {
            
        }
        
        if (e.getSource() == tiedostonValitsija) {
            int x;
        
        JFileChooser tiedostonvalitsija = new JFileChooser();
        x=tiedostonvalitsija.showSaveDialog(jakaumanValintaKehys);         
        if (x==tiedostonvalitsija.APPROVE_OPTION) {
            this.tallennusTiedosto = tiedostonvalitsija.getSelectedFile();
            onkoJakaumaValittuna = true;
        }
        else onkoTiedostoa = false;
        }
        
        if (e.getSource() == ok) {
            //if (jakaumat.) {
                
            }
        }
    }
    
    
    public boolean onkoValittuJakauma() {
        return this.onkoJakaumaValittuna;
    }
}
