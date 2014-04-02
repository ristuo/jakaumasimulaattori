
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

public class ParametrienValintakuuntelija implements ActionListener {
    
    private JTextField myy;
    private JTextField sigma;
    private JTextField lambda;
    private JTextField alfa;
    private JTextField beta;
    private JTextField n;
    private JTextField p;
    private JTextField otoskoko;
    private JButton ok;
    private JButton tiedosto;
    private File tallennustiedosto;
    private KehysAsettelija kehysAsettelija;
    private boolean onkoTiedostoa = false;
    private JakaumanValintakuuntelija jakaumanValintakuuntelija;
    
    public ParametrienValintakuuntelija(JTextField myy, JTextField sigma, JTextField lambda, 
            JTextField alfa, JTextField beta, JTextField n, JTextField p, JTextField otoskoko, KehysAsettelija kehysAsettelija) {
        this.myy = myy;
        this.sigma = sigma;
        this.lambda = lambda;
        this.alfa = alfa;
        this.beta = beta;
        this.otoskoko = otoskoko;
        this.n = n;
        this.p = p;        
        this.kehysAsettelija = kehysAsettelija;
    }
    
    
    public void actionPerformed(ActionEvent ae) {
        
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
            }
            else onkoTiedostoa = false;
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
        this.n.setEnabled(true);
        this.p.setEnabled(true);
    }
    
    public void asetaKaikkiDisabled() {
        this.lambda.setEnabled(false);
        this.myy.setEnabled(false);
        this.sigma.setEnabled(false);
        this.alfa.setEnabled(false);
        this.beta.setEnabled(false);
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
}
