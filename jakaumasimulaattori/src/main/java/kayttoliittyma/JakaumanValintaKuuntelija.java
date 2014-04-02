
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
import java.awt.Component;
public class JakaumanValintaKuuntelija implements ActionListener {
    
    
    
    
    private boolean onkoJakaumaValittuna = false;
    
    private boolean onkoTiedostoa;
    private JFrame jakaumanValintaKehys;
    private JButton ok;
    private JButton tiedostonValitsija;
    private JRadioButton normaali;
    private JRadioButton gamma;
    private JRadioButton binomi;
    private JRadioButton eksponentti;
    boolean normaaliValittu = false;
    boolean eksponenttiValittu=false;
    boolean binomiValittu=false;
    boolean gammaValittu=false;      
    private File tallennusTiedosto;
    private Component[] jakaumanappulat;
    private JFrame parametrinValintaKehys;
    private JakaumanValinta jakaumanValinta;
    
    public JakaumanValintaKuuntelija(JakaumanValinta jakaumanvalinta, JRadioButton normaali, JRadioButton gamma, JRadioButton binomi, JRadioButton eksponentti, JButton ok, JButton tiedostonValitsija, JFrame jakaumanValintaKehys, JFrame parametrinValintaKehys) {
        this.jakaumanValinta = jakaumanvalinta;
        this.jakaumanappulat = jakaumanappulat;
        this.ok = ok;
        this.jakaumanValintaKehys = jakaumanValintaKehys;
        this.tiedostonValitsija = tiedostonValitsija;
        this.parametrinValintaKehys = parametrinValintaKehys;
        this.normaali=normaali;
        this.binomi=binomi;
        this.eksponentti=eksponentti;
        this.gamma=gamma;
        
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == null) {
            
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
           
                jakaumanValintaKehys.setVisible(false);
                parametrinValintaKehys.setVisible(true);
                
                jakaumanValinta.luoKomponentitParametrienValintaIkkunaan(parametrinValintaKehys, this);
//                if (this.normaaliValittu) {
//                    normaali.setEnabled(true);
//                }
//                
//        }
        
//        if (e.getSource() == normaali) {
//            this.normaaliValittu=true;
//        }
        
                if (normaali.isSelected()) {
                    this.normaaliValittu=true;
                }
                
                if (gamma.isSelected()) {
                    this.gammaValittu=true;
                }
                
                if (eksponentti.isSelected()) {
                    this.eksponenttiValittu=true;
                }
                
                if (binomi.isSelected()) {
                    this.binomiValittu=true;
                }
            }
        
//        
        
        }
    
        public boolean onkoNormaaliValittu() {
            return normaaliValittu;
        }
        
        public boolean onkoBinomiValittu() {
            return binomiValittu;
        }
        
        public boolean onkoGammaValittu() {
            return gammaValittu;
        }
        
        public boolean onkoEksponenttiValittu() {
            return eksponenttiValittu;
        }
    
        
       
}
