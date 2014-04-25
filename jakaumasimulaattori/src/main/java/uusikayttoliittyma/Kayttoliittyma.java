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
import java.awt.Toolkit;

public class Kayttoliittyma implements Runnable {
    
    /**
     * Luokka on ohjelman käyttöliittymä, joka hallinnoi muiden ohjelman kompo-
     * nenttien toimintaa
     * 
     */
    
    private JFrame jakaumanValintakehys;
    private JFrame virheilmoitusruutu;
    private KehysAsettelija kehysAsettelija;
    private JFrame graafiIkkuna;
    
    @Override
    public void run() {
        this.jakaumanValintakehys = new JFrame("Jakaumasimulaattori");        
        this.kehysAsettelija = new KehysAsettelija(this, jakaumanValintakehys);
        kehysAsettelija.luoJakaumanValintaKehys();
        kehysAsettelija.luoKomponentitJakaumanValintaKehykseen();
        jakaumanValintakehys.setSize(501,600);
    }
    
    public void sulje() {
        graafiIkkuna.dispose();
        jakaumanValintakehys.dispose();
    }
  
    public void siirryYhteenvetoIkkunaan() {
        jakaumanValintakehys.getContentPane().removeAll();
        jakaumanValintakehys.getContentPane().repaint();
        kehysAsettelija.luoRaporttiKehys(jakaumanValintakehys);
        jakaumanValintakehys.getContentPane().repaint();
        jakaumanValintakehys.repaint();
        jakaumanValintakehys.setSize(500, 601);


    }
    
    public void siirryVirheilmoitukseen() {
        this.virheilmoitusruutu = new JFrame("");        
        kehysAsettelija.luoVirheilmoitusruutu(virheilmoitusruutu);
    }
    
    public void luoGraafiIkkuna() {
        this.graafiIkkuna = new GraafiIkkuna();
        graafiIkkuna.setPreferredSize(new Dimension(600,600));
        graafiIkkuna.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        graafiIkkuna.setVisible(true);
        kehysAsettelija.luoGraafi(graafiIkkuna);
        graafiIkkuna.pack();
    }

}
