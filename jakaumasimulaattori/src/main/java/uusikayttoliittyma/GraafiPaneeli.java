package uusikayttoliittyma;
import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import ohha.jakaumasimulaattori.*;

public class GraafiPaneeli extends JPanel {
    
    private int etaisyysReunoista = 25;
    private int etaisyysVasemmastaReunasta = 60;
    private NumberFormat nf = NumberFormat.getInstance(); 
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
    
    public void piirraAsteikko(Graphics g) {
        g.drawLine(etaisyysVasemmastaReunasta, etaisyysReunoista, etaisyysVasemmastaReunasta, this.getHeight()-etaisyysReunoista);
        g.drawLine(etaisyysVasemmastaReunasta,this.getHeight()-etaisyysReunoista,this.getWidth()-etaisyysReunoista,this.getHeight()-etaisyysReunoista);
    }
    
    
    
    public void piirraHistogrammi(Graphics g, TilastoAineisto tilastoaineisto){
        
        double max = tunnuslukulaskuri.max(tilastoaineisto);
        double min = tunnuslukulaskuri.min(tilastoaineisto);
        int asteikonKorkeus = this.getHeight()-50;
        double asteikonLeveys = Math.abs(max)-Math.abs(min);
        double binienmaara = 20;
        double oikearaja;
        double vasenraja;
        double binissaOlevatHavainnot;        
        int bininLeveys = (int)((this.getWidth()-100)/binienmaara);
        int bininKorkeus;
        double havaintoja = tilastoaineisto.getN();
        int[] havaintojaBineissa = new int[(int)binienmaara];
        double isoinBini;
        double[] aineisto = tilastoaineisto.getAineisto();
        
        for (int i = 0; i < binienmaara; i++) {
            binissaOlevatHavainnot = 0;
            oikearaja = (i+1)*(asteikonLeveys/binienmaara);
            vasenraja = i*(asteikonLeveys/binienmaara);
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
        
    }
    
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
    
    public void piirraPylvaskuva(Graphics g, TilastoAineisto tilastoaineisto) {
        int max = (int)tunnuslukulaskuri.max(tilastoaineisto);
        int min = (int)tunnuslukulaskuri.min(tilastoaineisto);
        int[] havaintojaPylvaissa = new int[max-min];
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
        for (int i = min; i < max; i++) {
    
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
        System.out.println(havaintojaPylvaissa.length);
        
        double pylvaassaOlevatHavainnotdouble;
        double korkeinPylvasdouble;
        
        for (int k = 0; k < havaintojaPylvaissa.length;k++) {
            pylvaassaOlevatHavainnot = havaintojaPylvaissa[k];
            pylvaassaOlevatHavainnotdouble = (double)havaintojaPylvaissa[k];
            korkeinPylvasdouble = (double) korkeinPylvas;
            pylvaanKorkeus = (int)((pylvaassaOlevatHavainnotdouble/korkeinPylvasdouble)*asteikonKorkeus);
            g.drawRect(k*pylvaanLeveys+etaisyysVasemmastaReunasta+k*pylvaidenEtaisyys + 10,this.getHeight()-etaisyysReunoista-pylvaanKorkeus, pylvaanLeveys, pylvaanKorkeus);
            //System.out.println("pylvaanLeveys: " + pylvaanLeveys + ", k " + k + " pylvaanKorkeus " + pylvaanKorkeus);
            g.drawString("" + (min+k), (int)(etaisyysVasemmastaReunasta + k*(pylvaanLeveys+pylvaidenEtaisyys) + 0.5*pylvaanLeveys) + 10, this.getHeight()-etaisyysReunoista+15);
            
        }
        
    }
    
    public void piirraTeoreettinenJakauma(Graphics g, TilastoAineisto tilastoaineisto) {
        
    }
    
    
}
