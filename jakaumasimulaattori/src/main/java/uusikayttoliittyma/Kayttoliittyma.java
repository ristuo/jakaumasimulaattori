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

public class Kayttoliittyma implements Runnable {
    
    /**
     * Luokka on ohjelman käyttöliittymä, joka hallinnoi muiden ohjelman kompo-
     * nenttien toimintaa
     * 
     */
    
    private JFrame jakaumanValintakehys;
    private JFrame virheilmoitusruutu;
    private KehysAsettelija kehysAsettelija;
    
    @Override
    public void run() {
        this.jakaumanValintakehys = new JFrame("Jakaumasimulaattori");        
        this.kehysAsettelija = new KehysAsettelija(this, jakaumanValintakehys);
        kehysAsettelija.luoJakaumanValintaKehys();
        kehysAsettelija.luoKomponentitJakaumanValintaKehykseen();
    }
    
  
    public void siirryYhteenvetoIkkunaan() {
        jakaumanValintakehys.getContentPane().removeAll();
        jakaumanValintakehys.getContentPane().repaint();
        kehysAsettelija.luoRaporttiKehys(jakaumanValintakehys);
        jakaumanValintakehys.getContentPane().repaint();
        jakaumanValintakehys.repaint();
    }
    
    public void siirryVirheilmoitukseen() {
        this.virheilmoitusruutu = new JFrame("");
        
        kehysAsettelija.luoVirheilmoitusruutu(virheilmoitusruutu);
    }
}
