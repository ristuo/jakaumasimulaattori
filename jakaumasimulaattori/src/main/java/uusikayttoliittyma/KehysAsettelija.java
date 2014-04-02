

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

public class KehysAsettelija {
    
    private Kayttoliittyma kayttoliittyma;
    private JFrame jakaumanValintakehys;
    private Valintakuuntelija valintakuuntelija;
    private JakaumanValintakuuntelija jakaumanValintakuuntelija;
    private ParametrienValintakuuntelija parametrienValintakuuntelija;
    
    public KehysAsettelija(Kayttoliittyma kayttoliittyma, JFrame jakaumanValintakehys, Valintakuuntelija valintakuuntelija) {
        this.kayttoliittyma=kayttoliittyma;
        this.jakaumanValintakehys = jakaumanValintakehys;
        this.valintakuuntelija = valintakuuntelija;
        
    }
    
    public void luoJakaumanValintaKehys() {
        
        this.jakaumanValintakehys.setPreferredSize(new Dimension(500, 600));
        this.jakaumanValintakehys.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.jakaumanValintakehys.setVisible(true);
        this.jakaumanValintakehys.pack();
        
    }
    
    public void luoKomponentitJakaumanValintaKehykseen() {
        Container container = jakaumanValintakehys.getContentPane();
        container.setLayout(new BorderLayout());
        container.add(this.luoJakaumaValikko(), BorderLayout.NORTH);       
        container.add(this.luoParametriValikko(), BorderLayout.CENTER);
        container.add(this.luoTiedostoJaOKNappulat(), BorderLayout.SOUTH);
        
    }
    
    private JPanel luoJakaumaValikko() {
        
        
        JPanel jakaumapaneeli = new JPanel();
        ButtonGroup jakaumanappularyhma = new ButtonGroup();
        jakaumapaneeli.setLayout(new BoxLayout(jakaumapaneeli, BoxLayout.Y_AXIS));
        
        JRadioButton normaali = new JRadioButton("Normaalijakauma");
        JRadioButton binomi = new JRadioButton("Binomijakauma");
        JRadioButton eksponentti = new JRadioButton("Eksponenttijakauma");
        JRadioButton gamma = new JRadioButton("Gammajakauma");
        
        jakaumanappularyhma.add(normaali);
        jakaumanappularyhma.add(gamma);
        jakaumanappularyhma.add(eksponentti);
        jakaumanappularyhma.add(binomi);
        
        this.jakaumanValintakuuntelija = new JakaumanValintakuuntelija(normaali,gamma,binomi,eksponentti);
        normaali.addActionListener(jakaumanValintakuuntelija);
        gamma.addActionListener(jakaumanValintakuuntelija);
        binomi.addActionListener(jakaumanValintakuuntelija);
        eksponentti.addActionListener(jakaumanValintakuuntelija);
        
        jakaumapaneeli.add(new JLabel("Valitse simuloitava jakauma"));
        jakaumapaneeli.add(gamma);
        jakaumapaneeli.add(binomi);
        jakaumapaneeli.add(eksponentti);
        jakaumapaneeli.add(normaali);
        
        return jakaumapaneeli;
    }
    
    private JPanel luoParametriValikko() {
        JPanel parametriPaneeli = new JPanel();
        BoxLayout gridlayout = new BoxLayout(parametriPaneeli, BoxLayout.Y_AXIS);
        parametriPaneeli.setLayout(gridlayout);
        
        JTextField myy = new JTextField("\u03BC");
        myy.setEnabled(false);
        
        JTextField sigma = new JTextField("\u03C3");
        sigma.setEnabled(false);
        
        JTextField lambda = new JTextField("\u03BB");
        lambda.setEnabled(false);
        
        JTextField alfa = new JTextField("\u03B1");
        alfa.setEnabled(false);
        
        JTextField beta = new JTextField("\u03B2");
        beta.setEnabled(false);
        
        JTextField n = new JTextField("n");
        n.setEnabled(false);
        
        JTextField p = new JTextField("p");
        p.setEnabled(false);
        
        JTextField otoskoko = new JTextField("Otoskoko");
        otoskoko.setEnabled(true);
        
        parametriPaneeli.add(new JLabel("Valitse jakauman parametrit"));
    
        parametriPaneeli.add(myy);
    
        parametriPaneeli.add(sigma);
    
        parametriPaneeli.add(lambda);
     
        parametriPaneeli.add(alfa);
    
        parametriPaneeli.add(beta);
   

        parametriPaneeli.add(n);
        
        parametriPaneeli.add(p);
        
        parametriPaneeli.add(otoskoko);
        
        
        this.parametrienValintakuuntelija = new ParametrienValintakuuntelija(myy,sigma,lambda,alfa,beta, n, p, otoskoko, this);
        this.jakaumanValintakuuntelija.asetaParametrienValintaKuuntelija(parametrienValintakuuntelija);
        this.parametrienValintakuuntelija.setJakaumanValintakuuntelija(jakaumanValintakuuntelija);
        myy.addActionListener(parametrienValintakuuntelija);
        sigma.addActionListener(parametrienValintakuuntelija);
        lambda.addActionListener(parametrienValintakuuntelija);
        alfa.addActionListener(parametrienValintakuuntelija);
        beta.addActionListener(parametrienValintakuuntelija);
        
        return parametriPaneeli;
    }
    
    
    private JPanel luoTiedostoJaOKNappulat() {
        JPanel tiedostoJaOK = new JPanel();
        
        JButton ok = new JButton("Generoi aineisto");
        ok.addActionListener(parametrienValintakuuntelija);
        ok.setEnabled(false);
        
        JButton tiedosto = new JButton("Valitse tallennustiedosto");
        tiedosto.addActionListener(parametrienValintakuuntelija);
        
        parametrienValintakuuntelija.setOK(ok);
        parametrienValintakuuntelija.setTiedosto(tiedosto);
        
        tiedostoJaOK.add(ok);
        tiedostoJaOK.add(tiedosto);
        
        return tiedostoJaOK;
        
    }
    
    public JFrame getFrame() {
        return this.jakaumanValintakehys;
    }
}