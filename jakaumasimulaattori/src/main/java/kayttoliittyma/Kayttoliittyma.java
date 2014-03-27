package kayttoliittyma;
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

public class Kayttoliittyma implements Runnable {

    private JFrame jakaumanValintaKehys;

    private JFrame parametrienValintaKehys;
    private TiedostonValintaKuuntelija tiedostonValintaKuuntelija;
    
    
    public Kayttoliittyma() {
    }

    @Override
    public void run() {
        
        
        this.luoKehykset(); 
        luoKomponentitJakaumanValintaKehykseen(jakaumanValintaKehys);
        jakaumanValintaKehys.setVisible(true);
        parametrienValintaKehys.setVisible(false);

    }

    
    private void luoKehykset() {
        jakaumanValintaKehys = new JFrame("Jakaumasimulaattori");
        jakaumanValintaKehys.setPreferredSize(new Dimension(400, 200));
        jakaumanValintaKehys.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jakaumanValintaKehys.pack();
        
        parametrienValintaKehys = new JFrame("Jakaumasimulaattori");
        parametrienValintaKehys.setPreferredSize(new Dimension(400,200));
        parametrienValintaKehys.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        parametrienValintaKehys.pack();
        
    }
    
    
    
    private void luoKomponentitJakaumanValintaKehykseen(JFrame frame) {
        
        Container container = frame.getContentPane();
        
        BorderLayout rajalayout = new BorderLayout();
        FlowLayout flowlayout = new FlowLayout();
        container.setLayout(rajalayout);
        
        JPanel jakaumat = new JPanel();
        jakaumat.setLayout(new BoxLayout(jakaumat, BoxLayout.Y_AXIS));
        container.add(jakaumat, BorderLayout.CENTER);
        ButtonGroup bg = luoJakaumaValikonNappulat(jakaumat);
        
        
        JButton ok = new JButton("Siirry parametrien määrittelemiseen");
        JButton tiedostonValitsija = new JButton("Valitse tulostustiedosto");
        
        JakaumanValintaKuuntelija jakaumanValintaKuuntelija = new JakaumanValintaKuuntelija(bg, ok, tiedostonValitsija, this.getFrame());
        
        
        
        JPanel alapaneeli = new JPanel(new GridLayout(2,1,10,10));
        alapaneeli.add(ok);
        alapaneeli.add(tiedostonValitsija);
        container.add(alapaneeli, BorderLayout.SOUTH);
        
      
        
        tiedostonValintaKuuntelija = new TiedostonValintaKuuntelija(this.getFrame());
        tiedostonValitsija.addActionListener(tiedostonValintaKuuntelija);

        
   
        ok.addActionListener(new ParametrienValintaKuuntelija(parametrienValintaKehys, jakaumanValintaKehys));
    }
    
    
    private ButtonGroup luoJakaumaValikonNappulat(JPanel jakaumat) {
        jakaumat.add(new JLabel("Valitse simuloitava jakauma"));
        JRadioButton normaali = new JRadioButton("Normaalijakauma");
        JRadioButton gamma = new JRadioButton("Gammajakauma"); 
        JRadioButton binomi = new JRadioButton("Binomijakauma");
        JRadioButton eksponentti = new JRadioButton("Eksponenttijakauma");
        JRadioButton poisson = new JRadioButton("Poisson");
        
        ButtonGroup bg = new ButtonGroup();
        
        bg.add(normaali);
        bg.add(gamma);
        bg.add(binomi);
        bg.add(eksponentti);
        bg.add(poisson);
        
        
        
        jakaumat.add(normaali);
        jakaumat.add(gamma);
        jakaumat.add(binomi);
        jakaumat.add(eksponentti);
        jakaumat.add(poisson);
        
        return bg;
    }
   
    
    
    public void luoKomponentitParametrienValintaIkkunaan(JFrame frame) {
        
    }

    public JFrame getFrame() {
        return jakaumanValintaKehys;
    }
}