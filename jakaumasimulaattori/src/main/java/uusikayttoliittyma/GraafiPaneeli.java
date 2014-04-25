package uusikayttoliittyma;
import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import ohha.jakaumasimulaattori.*;


/**
 * Luokan tarkoituso on esittää tilastokuva jostakin aineistosta.
 * @author rtuomainen
 */
public class GraafiPaneeli extends JPanel {
    
    /** etaisyysReunoista kertoo montako pistettä on ikkunan oikean, ala- ja yläreunan ja kuvan välissä */
    private int etaisyysReunoista = 25;
    /** etaisyysVasemmastaReunasta kertoo montako pistettä on ikkunan vasemman reunan ja kuvan välissä */
    private int etaisyysVasemmastaReunasta = 60;
    /** nf on muotoilu, jota käytetään aineistosta laskettujen prosenttiosuuksien muotoiluun y-akselille tulostamista varten */
    private NumberFormat nf = NumberFormat.getInstance(); 
    /** Tilastoaineisto on aineisto, josta halutaan piirtää kuva */
    private TilastoAineisto tilastoaineisto;
    private TunnuslukuLaskuri tunnuslukulaskuri = new TunnuslukuLaskuri();
    
    public GraafiPaneeli(TilastoAineisto tilastoaineisto) {
        nf.setMaximumFractionDigits(1);            
        nf.setGroupingUsed(false);
        this.tilastoaineisto = tilastoaineisto;
    }
    
    @Override 
    public void paintComponent(Graphics g) {
        
        Otosgeneraattori otos = new Otosgeneraattori();
        super.paintComponent(g);
        this.setBackground(Color.white);
        this.piirraAsteikko(g);
        
        if (tilastoaineisto.getJakauma()==Jakauma.BINOMI || tilastoaineisto.getJakauma()==Jakauma.POISSON) {
            this.piirraPylvaskuva(g, tilastoaineisto);
        } 
        
        else this.piirraHistogrammi(g, tilastoaineisto);
        
    }
    
    /** Metodi piirtää x- ja y-akselit */
    public void piirraAsteikko(Graphics g) {
        g.drawLine(etaisyysVasemmastaReunasta, etaisyysReunoista, etaisyysVasemmastaReunasta, this.getHeight()-etaisyysReunoista);
        g.drawLine(etaisyysVasemmastaReunasta,this.getHeight()-etaisyysReunoista,this.getWidth()-etaisyysReunoista,this.getHeight()-etaisyysReunoista);
    }
    
    
    
    /**
     * Metodi piirtää histogrammin
     * @param g on graafiIkkunan grafiikka-olio
     * @param tilastoaineisto on jokin jatkuva tilastoaineisto
     */
    public void piirraHistogrammi(Graphics g, TilastoAineisto tilastoaineisto){
        
        
        
        double max = tunnuslukulaskuri.max(tilastoaineisto); 
        double min = tunnuslukulaskuri.min(tilastoaineisto); 
        int asteikonKorkeus = this.getHeight()-50;
        double asteikonLeveys = Math.abs(max-min);

        double binienmaara;
        double oikearaja;
        double vasenraja;
        double binissaOlevatHavainnot;        


        int bininKorkeus;
        double havaintoja = tilastoaineisto.getN();
        
        

        
        if (havaintoja > 1000) {
            binienmaara = 100;
        }
        
        else if (havaintoja > 100) {
            binienmaara = 20;
        }
        
        else binienmaara = 10;
        
        int bininLeveys = (int)((this.getWidth()-100)/binienmaara);      
        int[] havaintojaBineissa = new int[(int)binienmaara];
        double isoinBini;
        double[] aineisto = tilastoaineisto.getAineisto();        

        for (int i = 0; i < binienmaara; i++) {
            binissaOlevatHavainnot = 0;
            oikearaja = min + (i+1)*(asteikonLeveys/binienmaara);

            vasenraja = min + i*(asteikonLeveys/binienmaara);

            for (int k = 0; k < havaintoja; k++) {
                if (aineisto[k] < oikearaja && aineisto[k] > vasenraja) {
                    binissaOlevatHavainnot++;
                }
            }
            havaintojaBineissa[i] = (int)binissaOlevatHavainnot;

        }
      
        isoinBini = tunnuslukulaskuri.maxint(havaintojaBineissa);
        for (int i = 0; i < binienmaara; i++) {
            binissaOlevatHavainnot = (double)havaintojaBineissa[i];
            bininKorkeus = (int)((binissaOlevatHavainnot/isoinBini)*asteikonKorkeus);
            g.drawRect(i*bininLeveys+etaisyysVasemmastaReunasta,this.getHeight()-etaisyysReunoista-bininKorkeus, bininLeveys, bininKorkeus);

        }
        this.piirraTickLabelitYakseliin(g, isoinBini, havaintoja, etaisyysVasemmastaReunasta, etaisyysReunoista);
        
//        if (tilastoaineisto.getJakauma() == Jakauma.NORMAALI) {
//            this.piirraTeoreettinenJakauma(g, isoinBini);
//        }
        
    }
    
    
    /**
     * Metodi piirtää histogrammin y-akselille tick-merkkejä
     * @param g on graafiIkkunan grafiikka-olio
     * @param isoinBini on histogrammin pylvas, jossa on eniten havaintoja
     * @param havaintoja on aineistossa olevien havaintojen määrä
     * @param etaisyysVasemmastaReunasta kertoo montako pistettä on ikkunan vasemman reunan ja kuvan välissä
     * @param etaisyysReunoista kertoo etäisyyden muihin reunoihin
     */
    private void piirraTickLabelitYakseliin(Graphics g, double isoinBini, double havaintoja, int etaisyysVasemmastaReunasta, int etaisyysReunoista) {
      
        int akselinKorkeus = this.getHeight()-2*etaisyysReunoista;
        int montakoTickia = 4;
        double yhdenTickinKoko = ((isoinBini/havaintoja)/4);
        
        g.drawString(nf.format(100*isoinBini/havaintoja) + "%", etaisyysVasemmastaReunasta-50, etaisyysReunoista+5);
        g.drawLine(etaisyysVasemmastaReunasta, etaisyysReunoista, etaisyysVasemmastaReunasta -5, etaisyysReunoista);
        

        
        for (int i = 0; i < 4; i++) {
            g.drawString(nf.format(100*i*yhdenTickinKoko) + "%", etaisyysVasemmastaReunasta-50, (int)(this.getHeight()-etaisyysReunoista-
                    (((double)i)/montakoTickia)*akselinKorkeus)+5);
            g.drawLine(etaisyysVasemmastaReunasta, (int)(this.getHeight()-etaisyysReunoista-
                    (((double)i)/montakoTickia)*akselinKorkeus), etaisyysVasemmastaReunasta - 5, (int)(this.getHeight()-etaisyysReunoista-
                    (((double)i)/montakoTickia)*akselinKorkeus));
        }
    }
    
    /**
     * Piirtää diskreetistä aineistosta pylväskuvan
     * @param g on graafiIkkunan grafiikka-olio
     * @param tilastoaineisto on diskreetti tilastoaineisto
     */
    public void piirraPylvaskuva(Graphics g, TilastoAineisto tilastoaineisto) {
        int max = (int)tunnuslukulaskuri.max(tilastoaineisto);
        int min = (int)tunnuslukulaskuri.min(tilastoaineisto);
        int[] havaintojaPylvaissa = new int[Math.abs(max-min)+1];
        double[] aineisto = tilastoaineisto.getAineisto();
        int asteikonKorkeus = this.getHeight()-50;
        int asteikonLeveys = Math.abs(max)-Math.abs(min);       
        int pylvaanKorkeus;
        int pylvaassaOlevatHavainnot;
        int korkeinPylvas;
        int pylvaidenEtaisyys = 10;
        double pylvaidenMaara = tunnuslukulaskuri.montakoErilaistaHavaintoa(tilastoaineisto.getAineisto());
        int pylvaanLeveys = (int)((this.getWidth()-100)/pylvaidenMaara)-pylvaidenEtaisyys;
        
        int j = 0; 
        for (int i = min; i <= max; i++) {
    
            pylvaassaOlevatHavainnot = 0;
            for (int k = 0; k < aineisto.length; k++) {
                if (i == (int)aineisto[k]) {
                    pylvaassaOlevatHavainnot++;
                }
            }
            havaintojaPylvaissa[j] = pylvaassaOlevatHavainnot;
            
            j++;
        } 
        
        korkeinPylvas = (int)tunnuslukulaskuri.maxint(havaintojaPylvaissa);

        
        double pylvaassaOlevatHavainnotdouble;
        double korkeinPylvasdouble=0;
        
        for (int k = 0; k < havaintojaPylvaissa.length;k++) {
            pylvaassaOlevatHavainnot = havaintojaPylvaissa[k];
            pylvaassaOlevatHavainnotdouble = (double)havaintojaPylvaissa[k];
            korkeinPylvasdouble = (double) korkeinPylvas;
            pylvaanKorkeus = (int)((pylvaassaOlevatHavainnotdouble/korkeinPylvasdouble)*asteikonKorkeus);
            g.drawRect(k*pylvaanLeveys+etaisyysVasemmastaReunasta+k*pylvaidenEtaisyys + 10,this.getHeight()-etaisyysReunoista-pylvaanKorkeus, pylvaanLeveys, pylvaanKorkeus);
            
            if (havaintojaPylvaissa.length < 20) {
                g.drawString("" + (min+k), (int)(etaisyysVasemmastaReunasta + k*(pylvaanLeveys+pylvaidenEtaisyys) + 0.5*pylvaanLeveys) + 10, this.getHeight()-etaisyysReunoista+15);
        
            }
        }
            this.piirraTickLabelitYakseliin(g, korkeinPylvasdouble, tilastoaineisto.getN(), etaisyysVasemmastaReunasta, etaisyysReunoista);
        
    }
    
//    private void piirraTeoreettinenJakauma(Graphics g, double isoinBini) {
//        double maksimipiste; 
//        double suurinarvo;
//        double max = tunnuslukulaskuri.max(tilastoaineisto);
//        double min = tunnuslukulaskuri.min(tilastoaineisto);
//        int asteikonKorkeus = this.getHeight()-50;
//        double asteikonLeveys = Math.abs(max-min);
//        int akselinleveys = this.getWidth()-etaisyysVasemmastaReunasta - etaisyysReunoista;
//        double x;
//        int ykoordinaatti;
//        double x2;
//        int y2koordinaatti;
//        double pisteita = 100;
//        double pisteenleveys = asteikonLeveys/pisteita;
//        
//        maksimipiste = tilastoaineisto.getParametri1();
//        suurinarvo = tilastoaineisto.getTiheysFunktionArvo(maksimipiste);
//        
//        double numeerinenintegraali;
//        double korkeus1;
//        double korkeus2;
//        double summa = 0;
//        g.setColor(Color.red);
//        for (double i = 1 + etaisyysVasemmastaReunasta; i < akselinleveys; i++) {
//            x = min+i*pisteenleveys;
//            numeerinenintegraali = tilastoaineisto.getTiheysFunktionArvo(x)*pisteenleveys;
//            summa = summa + numeerinenintegraali;
//            korkeus1 = (numeerinenintegraali/(isoinBini/tilastoaineisto.getN()))*asteikonKorkeus;
//            ykoordinaatti = this.getHeight()-etaisyysReunoista - (int)(korkeus1);
//                        
//            
//            
//            x2 = min+(i+1)*pisteenleveys;
//            numeerinenintegraali = tilastoaineisto.getTiheysFunktionArvo(x2)*pisteenleveys;
//            korkeus2 = (numeerinenintegraali/(isoinBini/tilastoaineisto.getN()))*asteikonKorkeus;   
//            
//            y2koordinaatti = this.getHeight() - etaisyysReunoista - (int)(korkeus2);
//            
//            g.drawLine((int)i, ykoordinaatti, (int)i+1, y2koordinaatti);
//        }
//        g.setColor(Color.black);
//
//    }
    
    
}
