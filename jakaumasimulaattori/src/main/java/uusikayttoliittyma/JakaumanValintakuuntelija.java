
package uusikayttoliittyma;
import java.util.*;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;
import javax.swing.*;
import java.io.*;
import java.awt.Component;
import java.awt.event.*;

/**
 * 
 * @author rtuomainen
 */
public class JakaumanValintakuuntelija implements ActionListener {
   
    /**
     * Luokan tarkoitus on seurata käyttäjän toimintaa jakaumavalikon suhteen ja
     * välittää tiedot käyttäjän valinnoista muille luokille, kuten parametrien-
     * valintakuuntelijalle
     * 
     * @see ParametrienValintakuuntelija
     * 
     * 
     */
    
    private JRadioButton normaali;
    private JRadioButton gamma;
    private JRadioButton binomi;
    private JRadioButton eksponentti;
    private String valittuButton;
    private ParametrienValintakuuntelija parametrienValintakuuntelija;
    private boolean onkoJakaumaa;
    
    public JakaumanValintakuuntelija(JRadioButton normaali, JRadioButton gamma, JRadioButton binomi, JRadioButton eksponentti) {
        
        this.normaali = normaali;
        this.gamma = gamma;
        this.binomi = binomi;
        this.eksponentti = eksponentti;
        
    }
    
    public void actionPerformed(ActionEvent ae) {
        
        this.onkoJakaumaa=true;
        parametrienValintakuuntelija.nollaaKaikkiParametrivalikot();
        parametrienValintakuuntelija.maalaaKaikkiParametrivalikotValkoisiksi();
        
        if (parametrienValintakuuntelija.onkoTiedostoa()) {
            parametrienValintakuuntelija.asetaOK();
            
        }
        
        
        if (ae.getSource() == normaali) {
            parametrienValintakuuntelija.asetaKaikkiDisabled();
            parametrienValintakuuntelija.asetaNormaali();
            valittuButton="normaali";
        }
        
        if (ae.getSource()==gamma) {
            parametrienValintakuuntelija.asetaKaikkiDisabled();
            parametrienValintakuuntelija.asetaGamma();
            valittuButton="gamma";
            
        }
        
        if (ae.getSource() == binomi) {
            parametrienValintakuuntelija.asetaKaikkiDisabled();
            parametrienValintakuuntelija.asetaBinomi();
            valittuButton="binomi";
        }
        
        if (ae.getSource() == eksponentti) {
            parametrienValintakuuntelija.asetaKaikkiDisabled();
            parametrienValintakuuntelija.asetaEksponentti();
            valittuButton = "eksponentti";
            
        }
        
        
    }
    
    public void asetaParametrienValintaKuuntelija(ParametrienValintakuuntelija p) {
        this.parametrienValintakuuntelija=p;
    }
    
    public boolean onkoJakaumaa() {
        return this.onkoJakaumaa;
    }
    
    public String getValittuButton() {
        return valittuButton;
    }
    
    
}
