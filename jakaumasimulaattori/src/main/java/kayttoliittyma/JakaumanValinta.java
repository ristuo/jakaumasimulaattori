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
import java.awt.Component;

public class JakaumanValinta implements Runnable {

    private JFrame jakaumanValintaKehys;

    private JFrame parametrienValintaKehys;
    private TiedostonValintaKuuntelija tiedostonValintaKuuntelija;
    
    
    public JakaumanValinta() {
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
//        luoJakaumaValikonNappulat(jakaumat);
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
        
        JButton ok = new JButton("Siirry parametrien määrittelemiseen");
        JButton tiedostonValitsija = new JButton("Valitse tulostustiedosto");
       
        
        
        JPanel alapaneeli = new JPanel(new GridLayout(2,1,10,10));
        alapaneeli.add(ok);
        alapaneeli.add(tiedostonValitsija);
        container.add(alapaneeli, BorderLayout.SOUTH);
        
    
          
        
        
//        Component[] jakaumanappulat = jakaumat.getComponents();
        
        
        JakaumanValintaKuuntelija jakaumanValintaKuuntelija = new JakaumanValintaKuuntelija(this, normaali, binomi, eksponentti, gamma, ok, tiedostonValitsija, this.getFrame(), parametrienValintaKehys);
        ok.addActionListener(jakaumanValintaKuuntelija);
        normaali.addActionListener(jakaumanValintaKuuntelija);
        binomi.addActionListener(jakaumanValintaKuuntelija);
        gamma.addActionListener(jakaumanValintaKuuntelija);
        eksponentti.addActionListener(jakaumanValintaKuuntelija);
        
      //  luoKomponentitParametrienValintaIkkunaan(parametrienValintaKehys, jakaumanValintaKuuntelija);
    
//        JRadioButton x1 = (JRadioButton)jakaumanappulat[1];
//        x1.addActionListener(jakaumanValintaKuuntelija);
        
    }
    
    
//    private void luoJakaumaValikonNappulat(JPanel jakaumat) {
//        jakaumat.add(new JLabel("Valitse simuloitava jakauma"));
//        JRadioButton normaali = new JRadioButton("Normaalijakauma");
//        JRadioButton gamma = new JRadioButton("Gammajakauma"); 
//        JRadioButton binomi = new JRadioButton("Binomijakauma");
//        JRadioButton eksponentti = new JRadioButton("Eksponenttijakauma");
//        JRadioButton poisson = new JRadioButton("Poisson");
//        
//        ButtonGroup bg = new ButtonGroup();
//        
//        bg.add(normaali);
//        bg.add(gamma);
//        bg.add(binomi);
//        bg.add(eksponentti);
//        bg.add(poisson);
//        
//        
//        jakaumat.add(normaali);
//        jakaumat.add(gamma);
//        jakaumat.add(binomi);
//        jakaumat.add(eksponentti);
//        jakaumat.add(poisson);
//      
//    }
   
    
    
    public void luoKomponentitParametrienValintaIkkunaan(JFrame frame, JakaumanValintaKuuntelija kuuntelija) {
  
        Container container = frame.getContentPane();
        GridLayout gridlayout = new GridLayout(6,2);
        container.setLayout(gridlayout);
        
//        JRadioButton normaali = (JRadioButton)jakaumaNappulat[1];
//        JRadioButton gamma = (JRadioButton)jakaumaNappulat[2];
//        JRadioButton binomi = (JRadioButton)jakaumaNappulat[3];
//        JRadioButton eksponentti = (JRadioButton)jakaumaNappulat[4];
        
        
        
        //luodaan tarvitut valikot
        JLabel myyLabel = new JLabel("\u03BC");
        JTextField myy = new JTextField();
        myy.setEnabled(false);
        
        JLabel sigmaLabel = new JLabel("\u03C3");
        JTextField sigma = new JTextField();
        sigma.setEnabled(false);
        
        JLabel lambdaLabel = new JLabel("\u03BB");
        JTextField lambda = new JTextField();
        lambda.setEnabled(false);
        
        JLabel alfaLabel = new JLabel("\u03B1");
        JTextField alfa = new JTextField();
        alfa.setEnabled(false);
        
        JLabel betaLabel = new JLabel("\u03B2");
        JTextField beta = new JTextField();
        beta.setEnabled(false);
        
        JLabel otoskokoLabel = new JLabel("Otoskoko");
        JTextField otoskoko = new JTextField();
        otoskoko.setEnabled(true);
        
        container.add(myyLabel);
        container.add(myy);
        container.add(sigmaLabel);
        container.add(sigma);
        container.add(lambdaLabel);
        container.add(lambda);
        container.add(alfaLabel);
        container.add(alfa);
        container.add(betaLabel);
        container.add(beta);
        container.add(otoskokoLabel);
        container.add(otoskoko);
        
        if (kuuntelija.binomiValittu) {
            
        }
        
        if (kuuntelija.normaaliValittu) {
            myy.setEnabled(true);
            sigma.setEnabled(true);
        }
//        if (normaali.isSelected()) {
//            myy.setEnabled(true);
//            sigma.setEnabled(true);
//            System.out.println("toimii");
//        }
//        
        
    }

    public JFrame getFrame() {
        return jakaumanValintaKehys;
    }
}