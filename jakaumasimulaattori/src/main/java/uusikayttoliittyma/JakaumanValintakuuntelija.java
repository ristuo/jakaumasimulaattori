
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
    private JRadioButton cauchy;
    private Jakauma valittuJakauma;
    private ParametrienValintakuuntelija parametrienValintakuuntelija;
    private boolean onkoJakaumaa;
    private JRadioButton poisson;
    
    public JakaumanValintakuuntelija(JRadioButton normaali, JRadioButton gamma, JRadioButton binomi, 
            JRadioButton eksponentti, JRadioButton poisson, JRadioButton cauchy) {
        
        this.normaali = normaali;
        this.gamma = gamma;
        this.binomi = binomi;
        this.eksponentti = eksponentti;
        this.poisson = poisson;
        this.cauchy = cauchy;
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
            valittuJakauma=Jakauma.NORMAALI;
        }
        
        if (ae.getSource()==gamma) {
            parametrienValintakuuntelija.asetaKaikkiDisabled();
            parametrienValintakuuntelija.asetaGamma();
            valittuJakauma=Jakauma.GAMMA;
            
        }
        
        if (ae.getSource() == binomi) {
            parametrienValintakuuntelija.asetaKaikkiDisabled();
            parametrienValintakuuntelija.asetaBinomi();
            valittuJakauma=Jakauma.BINOMI;
        }
        
        if (ae.getSource() == eksponentti) {
            parametrienValintakuuntelija.asetaKaikkiDisabled();
            parametrienValintakuuntelija.asetaEksponentti();
            valittuJakauma = Jakauma.EKSPONENTTI;
            
        }
        
        if (ae.getSource() == poisson) {
            parametrienValintakuuntelija.asetaKaikkiDisabled();
            parametrienValintakuuntelija.asetaPoisson();
            valittuJakauma = Jakauma.POISSON;
        }
        
        if (ae.getSource()==cauchy) {
            parametrienValintakuuntelija.asetaKaikkiDisabled();
            parametrienValintakuuntelija.asetaCauchy();
            valittuJakauma = Jakauma.CAUCHY;
        }
        
    }
    
    public void asetaParametrienValintaKuuntelija(ParametrienValintakuuntelija p) {
        this.parametrienValintakuuntelija=p;
    }
    
    public boolean onkoJakaumaa() {
        return this.onkoJakaumaa;
    }
    
    public Jakauma getValittuJakauma() {
        return valittuJakauma;
    }
    
    
}
