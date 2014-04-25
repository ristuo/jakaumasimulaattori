
package uusikayttoliittyma;
import ohha.jakaumasimulaattori.*;
import java.util.*;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Color;
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
     * Luokan tarkoituksena on seurata käyttäjän toimintaa parametrivalikon, 
     * tiedostonvalinnan ja "generoi jakauma" nappulan suhteen.
     */
public class ParametrienValintakuuntelija implements ActionListener {
    
    
    
    
    private Otosgeneraattori otosgeneraattori = new Otosgeneraattori();
    private JTextField myy;
    private JTextField sigma;
    private JTextField lambda;
    private JTextField alfa;
    private JTextField beta;
    private JTextField p;
    private JTextField gamma;
    private JTextField otoskoko;
    private JTextField n;
    private JButton ok;
    private JButton tiedosto;   
    private File tallennustiedosto;
    private KehysAsettelija kehysAsettelija;
    private boolean onkoTiedostoa = false;
    private JakaumanValintakuuntelija jakaumanValintakuuntelija;
    private double parametri1;
    private double parametri2;
    
    
    public ParametrienValintakuuntelija(JTextField myy, JTextField sigma, JTextField lambda, 
            JTextField alfa, JTextField beta, JTextField n, JTextField p, JTextField gamma, JTextField otoskoko, KehysAsettelija kehysAsettelija) {
        this.myy = myy;
        this.sigma = sigma;
        this.lambda = lambda;
        this.alfa = alfa;
        this.beta = beta;
        this.otoskoko = otoskoko;
        this.gamma = gamma; 
        this.n = n;
        this.p = p;        
        this.kehysAsettelija = kehysAsettelija;
    }
    
    
    public void actionPerformed(ActionEvent ae) {
        

        int otoksenkoko = 0;
        TilastoAineisto aineisto = null;
        TiedostonKasittelija tiedostonKasittelija = new TiedostonKasittelija();
        
        // ainut mahdollinen actionevent on tiedoston valitseminen, jos se tehdään ja
        // on valittu jakauma, ok-napin voi laittaa päälle
        if (jakaumanValintakuuntelija.onkoJakaumaa()) {
            this.ok.setEnabled(true);
        }
        
        if (ae.getSource() == tiedosto) {
            int x;
        
            JFileChooser tiedostonvalitsija = new JFileChooser();
            x=tiedostonvalitsija.showSaveDialog(this.kehysAsettelija.getFrame());         
            if (x==tiedostonvalitsija.APPROVE_OPTION) {
                this.tallennustiedosto = tiedostonvalitsija.getSelectedFile();
                this.onkoTiedostoa=true;
                tiedosto.setText("Valittuna: " + tallennustiedosto.getName());
            }
            else onkoTiedostoa = false;
            return;
        }    
        
        if (ae.getSource() == ok) {
            
            try {
                otoksenkoko = Integer.parseInt(otoskoko.getText()); 
                if (otoksenkoko < 0) {
                    throw new IllegalArgumentException();
                }
                otoskoko.setBackground(Color.white);
            }
            
            catch (Exception e) {
                otoskoko.setBackground(Color.red);                
            }
            
          
            
            if (jakaumanValintakuuntelija.getValittuJakauma()==Jakauma.NORMAALI) {
                try { 
                    parametri1 = Double.parseDouble(myy.getText());
                    myy.setBackground(Color.white);
                }
                
                catch (Exception e) {
                    myy.setBackground(Color.red);
                    parametri1 = 0; //tämä tehtiin vain, jotta netbeans ei rumentaisi ohjelmointi
                    //ympäristöä punaisella alleviivauksella ja virheilmoituksella "parametri1 might not have 
                    // been initialized", oikeasti ei generoida mitään aineistoa
                }
                
                try {
                    parametri2 = Double.parseDouble(sigma.getText());
                    if (parametri2 < 0) {
                        throw new IllegalArgumentException();
                    }
                    sigma.setBackground(Color.white);
                }
                
                catch (Exception e) {
                    sigma.setBackground(Color.red);
                    return;
                }
                if (myy.getBackground()==Color.red) {
                    return;
                }
                if (otoskoko.getBackground()!=Color.red) {
                    aineisto = otosgeneraattori.normaaliAineisto(otoksenkoko, parametri1, parametri2);   
                    
                }         
            }
            
            if (jakaumanValintakuuntelija.getValittuJakauma()==Jakauma.EKSPONENTTI) {
                try {
                    parametri1 = Double.parseDouble(lambda.getText());
                    if (parametri1 < 0) {
                        throw new IllegalArgumentException();
                    }
                    lambda.setBackground(Color.white);
                }
                
                catch (Exception e) {
                    lambda.setBackground(Color.red);
                    return;
                }
                
                if (otoskoko.getBackground()!=Color.red) {
                    aineisto = otosgeneraattori.eksponenttiAineisto(otoksenkoko, parametri1);
                }
            }
            
            if (jakaumanValintakuuntelija.getValittuJakauma()==Jakauma.BINOMI) {
                try {
                    parametri1 = Double.parseDouble(p.getText());                    
                    if (parametri1 > 1 || parametri1 < 0) {
                        throw new IllegalArgumentException();
                    }
                    p.setBackground(Color.white);
                }
                
                catch (Exception e) {
                    p.setBackground(Color.red);
                    return;
                }
                
                try {
                    parametri2 = Integer.parseInt(n.getText());
                    if (parametri2 < 0) {
                        throw new IllegalArgumentException();
                    }
                    n.setBackground(Color.white);
                }
                
                catch (Exception e) {
                    n.setBackground(Color.red);
                    return;
                }
                
                
                if (n.getBackground()==Color.red || p.getBackground()==Color.red) {
                    return;
 
                }
                aineisto = otosgeneraattori.binomiAineisto(otoksenkoko, parametri2, parametri1);
            }
            
            if (jakaumanValintakuuntelija.getValittuJakauma()==Jakauma.GAMMA) {
                try {
                    parametri1 = Double.parseDouble(alfa.getText());
                    if (parametri1 < 0) {
                        throw new IllegalArgumentException();
                    }
                    alfa.setBackground(Color.white);
                }
                
                catch (Exception e) {                
                    alfa.setBackground(Color.red);
                    parametri1 = 0; //jälleen parametri asetetaan jotta netbeans ei valittaisi
                    // alustamattomasta muuttujasta
                }                  
                try {
                    parametri2 = Double.parseDouble(beta.getText());
                    if (parametri2 < 0) {
                        throw new IllegalArgumentException();
                    }
                    beta.setBackground(Color.white);
                }
                
                catch (Exception e) {
                    beta.setBackground(Color.red);
                    return;
                }
                
                if (alfa.getBackground()==Color.red || otoskoko.getBackground()==Color.red) {
                    return;
                }
                    aineisto = otosgeneraattori.gammaAineisto(otoksenkoko, parametri1, parametri2);
               
                }                                    
                
            if (jakaumanValintakuuntelija.getValittuJakauma() == Jakauma.POISSON) {
                try {
                    parametri1 = Double.parseDouble(lambda.getText());
                    if (parametri1 < 0) {
                        throw new IllegalArgumentException();
                    }
                }
                
                catch (Exception e) {
                    lambda.setBackground(Color.red);
                    return;
                }
                
                if (lambda.getBackground()==Color.RED) {
                    return;
                }
                
                aineisto = otosgeneraattori.poissonAineisto(otoksenkoko, parametri1);
                
            }
            
            if (jakaumanValintakuuntelija.getValittuJakauma()==Jakauma.CAUCHY) {
                try {
                    parametri1 = Double.parseDouble(gamma.getText());
                    if (!(parametri1 > 0)) {
                        throw new IllegalArgumentException();
                    }
                    gamma.setBackground(Color.white);
                }
                catch (Exception e) {
                    gamma.setBackground(Color.red);
                    
                }
                
                try {
                    parametri2 = Double.parseDouble(myy.getText());
                    myy.setBackground(Color.white);
                }
                
                catch (Exception e) {
                    myy.setBackground(Color.red);
                    return;
                }
                
                if (!(myy.getBackground()==Color.red || gamma.getBackground()==Color.red)) {
                    aineisto = otosgeneraattori.cauchyAineisto(otoksenkoko, parametri1, parametri2);
                    
                }
            }
            
                
            // lopuksi tarkistetaan, onko otoskoolle annettu sopivaa arvoa
            if (otoskoko.getBackground()==Color.red) {
                return;
            }
            
            if (onkoTiedostoa) {
                try {
                
                    tiedostonKasittelija.tulostaAineistoTiedostoonCSV(tallennustiedosto, aineisto);

                
                }
            
                catch (Exception e) {
                    //System.out.println("virhe on täällä");
                    kehysAsettelija.virhe();
                }
            }
            
           kehysAsettelija.setAineisto(aineisto);
           kehysAsettelija.siirryYhteenvetoIkkunaan();
        }
    }
    
    
    

    
    
    public void asetaNormaali() {
        this.myy.setEnabled(true);
        this.sigma.setEnabled(true);
    }
    
    public void asetaGamma() {
        this.alfa.setEnabled(true);
        this.beta.setEnabled(true);
    }
    
    public void asetaPoisson() {
        this.lambda.setEnabled(true);
        
    }
    
    public void asetaEksponentti() {
        this.lambda.setEnabled(true);
    }
    
    public void asetaBinomi() {
        this.p.setEnabled(true);
        this.n.setEnabled(true);
    }

    public void asetaCauchy() {
        this.gamma.setEnabled(true);
        this.myy.setEnabled(true);
    }
    
    public void asetaKaikkiDisabled() {
        this.lambda.setEnabled(false);
        this.myy.setEnabled(false);
        this.sigma.setEnabled(false);
        this.alfa.setEnabled(false);
        this.beta.setEnabled(false);
        this.gamma.setEnabled(false);
        this.n.setEnabled(false);
        this.p.setEnabled(false);
    }
    
    public void setOK(JButton ok) {
        this.ok = ok;
    }
    
    public void setTiedosto(JButton tiedosto) {
        this.tiedosto = tiedosto;
    }
    
    public void asetaOK() {
        this.ok.setEnabled(true);
    }
    
    public void setJakaumanValintakuuntelija(JakaumanValintakuuntelija jk) {
        this.jakaumanValintakuuntelija=jk;
    }
    
    public boolean onkoTiedostoa() {
        return this.onkoTiedostoa;
    }
    
    public void maalaaKaikkiParametrivalikotValkoisiksi() {
        this.myy.setBackground(Color.white);
        this.sigma.setBackground(Color.white);
        this.alfa.setBackground(Color.white);
        this.beta.setBackground(Color.white);
        this.lambda.setBackground(Color.white);
        this.n.setBackground(Color.white);
        this.p.setBackground(Color.white);
        this.otoskoko.setBackground(Color.white);
        this.gamma.setBackground(Color.white);
    }
    
    public void nollaaKaikkiParametrivalikot() {
        this.myy.setText("\u03BC");
        this.sigma.setText("\u03C3");
        this.alfa.setText("\u03B1");
        this.beta.setText("\u03B2");
        this.lambda.setText("\u03BB");
        this.n.setText("n");
        this.p.setText("p");
        this.otoskoko.setText("Otoskoko");
    }
    
    public double getGeneroidunJakaumanparametri1() {
        return parametri1;
    }

    public double getGeneroidunJakaumanparametri2() {
        return parametri2;
    }
}
