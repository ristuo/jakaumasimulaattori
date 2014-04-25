

package uusikayttoliittyma;
import ohha.jakaumasimulaattori.*;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.*;
import java.awt.Toolkit;
import java.text.*;

public class KehysAsettelija {
    
    /**
     * Luokan tarkoituksena on vastata erilaisten elementtien luomisesta käyttöliittymän
     * tarvitsemiin ikkunoihin
     * 
     */
    
    private Kayttoliittyma kayttoliittyma;
    private JFrame jakaumanValintakehys;
    private JakaumanValintakuuntelija jakaumanValintakuuntelija;
    private ParametrienValintakuuntelija parametrienValintakuuntelija;
    private TilastoAineisto tilastoaineisto;
    private NumberFormat nf = NumberFormat.getInstance();  
    private TunnuslukuLaskuri tunnuslukulaskuri = new TunnuslukuLaskuri();


    public KehysAsettelija(Kayttoliittyma kayttoliittyma, JFrame jakaumanValintakehys) {
        this.kayttoliittyma=kayttoliittyma;
        this.jakaumanValintakehys = jakaumanValintakehys;
        nf.setMaximumFractionDigits(3);            
        nf.setGroupingUsed(false);
        
    }
    
    public void luoJakaumanValintaKehys() {
        
        this.jakaumanValintakehys.setPreferredSize(new Dimension(500, 600));
        this.keskitaFrame(jakaumanValintakehys);
        this.jakaumanValintakehys.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.jakaumanValintakehys.setVisible(true);
        this.jakaumanValintakehys.pack();
        jakaumanValintakehys.repaint();
    }
    
    public void luoKomponentitJakaumanValintaKehykseen() {
        Container container = jakaumanValintakehys.getContentPane();
        container.setLayout(new BorderLayout());
        container.add(this.luoJakaumaValikko(), BorderLayout.NORTH);       
        container.add(this.luoParametriValikko(), BorderLayout.CENTER);
        container.add(this.luoTiedostoJaOKNappulat(), BorderLayout.SOUTH);
        container.repaint();
        jakaumanValintakehys.repaint();
    }
    
    private JPanel luoJakaumaValikko() {
        
        
        JPanel jakaumapaneeli = new JPanel();
        ButtonGroup jakaumanappularyhma = new ButtonGroup();
        jakaumapaneeli.setLayout(new BoxLayout(jakaumapaneeli, BoxLayout.Y_AXIS));
        
        JRadioButton normaali = new JRadioButton("Normaalijakauma");
        JRadioButton binomi = new JRadioButton("Binomijakauma");
        JRadioButton eksponentti = new JRadioButton("Eksponenttijakauma");
        JRadioButton gamma = new JRadioButton("Gammajakauma");
        JRadioButton poisson = new JRadioButton("Poisson-jakauma");
        JRadioButton cauchy = new JRadioButton("Cauchy-jakauma");
        
        jakaumanappularyhma.add(normaali);
        jakaumanappularyhma.add(gamma);
        jakaumanappularyhma.add(eksponentti);
        jakaumanappularyhma.add(binomi);
        jakaumanappularyhma.add(poisson);
        jakaumanappularyhma.add(cauchy);
        
        this.jakaumanValintakuuntelija = new JakaumanValintakuuntelija(normaali,gamma,binomi,eksponentti, poisson, cauchy);
        normaali.addActionListener(jakaumanValintakuuntelija);
        gamma.addActionListener(jakaumanValintakuuntelija);
        binomi.addActionListener(jakaumanValintakuuntelija);
        eksponentti.addActionListener(jakaumanValintakuuntelija);
        poisson.addActionListener(jakaumanValintakuuntelija);
        cauchy.addActionListener(jakaumanValintakuuntelija);
        
        
        
        jakaumapaneeli.add(new JLabel("Valitse simuloitava jakauma"));
        jakaumapaneeli.add(gamma);
        jakaumapaneeli.add(binomi);
        jakaumapaneeli.add(eksponentti);
        jakaumapaneeli.add(normaali);
        jakaumapaneeli.add(poisson);
        jakaumapaneeli.add(cauchy);
        
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
        
        JTextField gamma = new JTextField("\u03B3");
        gamma.setEnabled(false);        

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
        parametriPaneeli.add(gamma);
        parametriPaneeli.add(otoskoko);
        
        
        this.parametrienValintakuuntelija = new ParametrienValintakuuntelija(myy,sigma,lambda,alfa,beta, n, p, gamma, otoskoko, this);
        this.jakaumanValintakuuntelija.asetaParametrienValintaKuuntelija(parametrienValintakuuntelija);
        this.parametrienValintakuuntelija.setJakaumanValintakuuntelija(jakaumanValintakuuntelija);
        myy.addActionListener(parametrienValintakuuntelija);
        sigma.addActionListener(parametrienValintakuuntelija);
        lambda.addActionListener(parametrienValintakuuntelija);
        alfa.addActionListener(parametrienValintakuuntelija);
        beta.addActionListener(parametrienValintakuuntelija);
        n.addActionListener(jakaumanValintakuuntelija);
        
        return parametriPaneeli;
    }
    
    public void luoRaporttiKehys(JFrame raporttiKehys) {
        
        Container container = raporttiKehys.getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        JTextArea tulostus = new JTextArea();
        JButton uusisimu = new JButton("Simuloi uusi jakauma");
        
        YhteenvetoKuuntelija yhteenvetoKuuntelija = new YhteenvetoKuuntelija(uusisimu, tilastoaineisto, kayttoliittyma);
        uusisimu.addActionListener(yhteenvetoKuuntelija);
        
        try {
            tulostus.append("Jakauman odotusarvo: " + nf.format(tilastoaineisto.getOdotusarvo()) + "\n");
        }
        
        catch (Exception e) {
            tulostus.append("Jakauman odotusarvo: Cauchy-jakaumalla ei ole odotusarvoa. \n");
        }
        tulostus.append("Jakauman otoskeskiarvo: " + nf.format(tilastoaineisto.getOtoskeskiarvo()) + "\n");
        
        try {
            tulostus.append("Jakauman keskihajonta: " + nf.format(tilastoaineisto.getKeskihajonta())+ "\n");
        }
        
        catch (Exception e) {
            tulostus.append("Jakauman keskihajonta: Cauchy-jakaumalla ei ole keskihajontaa \n");
        }
        tulostus.append("Jakauman otoskeskiarvo: " + nf.format(tilastoaineisto.getOtoskeskihajonta()) + "\n");
        
        
        if (jakaumanValintakuuntelija.getValittuJakauma()==Jakauma.NORMAALI) {
 
            tulostus.append("T-testisuure hypoteesille, että odotusarvo poikkeaa todellisesta arvosta: " + nf.format(tunnuslukulaskuri.laskeTtestisuure(tilastoaineisto, parametrienValintakuuntelija.getGeneroidunJakaumanparametri1())));
        }
        

        tulostus.setSize(new Dimension(raporttiKehys.getWidth()-25,raporttiKehys.getHeight() -20));
        container.add(tulostus);
        container.add(uusisimu);
        
        kayttoliittyma.luoGraafiIkkuna();
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
    
    public void virhe() {
        kayttoliittyma.siirryVirheilmoitukseen();
    }
    
    public void luoVirheilmoitusruutu(JFrame virheilmoitusruutu) {
        virheilmoitusruutu.setPreferredSize(new Dimension(300,400));
        virheilmoitusruutu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);        
        virheilmoitusruutu.setVisible(true);
        Container container = virheilmoitusruutu.getContentPane();
                
        container.add(new JLabel("Järjestelmä on suorittanut laittoman toiminnon ja lopetetaan"));
        virheilmoitusruutu.pack();
        
        
    }
    
    public void luoGraafi(JFrame graafiIkkuna) {
        GraafiPaneeli graafipaneeli = new GraafiPaneeli(tilastoaineisto);
        graafiIkkuna.add(graafipaneeli);
        
    }
    
    public JFrame getFrame() {
        return this.jakaumanValintakehys;
    }
    
    public void siirryYhteenvetoIkkunaan() {
        kayttoliittyma.siirryYhteenvetoIkkunaan();
        
    }
    
    public void setAineisto(TilastoAineisto tilastoaineisto) {
        this.tilastoaineisto=tilastoaineisto;
    }
    
    private void keskitaFrame(JFrame keskitettava) {
        Dimension framinKoko = keskitettava.getSize();
        Dimension ruudunKoko = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (ruudunKoko.width - framinKoko.width)/2;
        int y = (ruudunKoko.height - framinKoko.height)/4;
        keskitettava.setLocation(x, y);        
    }
    
    
}
