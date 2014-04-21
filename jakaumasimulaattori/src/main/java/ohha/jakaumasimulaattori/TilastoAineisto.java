

package ohha.jakaumasimulaattori;
import uusikayttoliittyma.*;

/**
 * 
 * @author rtuomainen
 * TilastoAineisto-olio kapseloi generoidun tilastoaineiston, joka olennaisesti on 
 * yksiuloitteinen taulukko. Näin aineisto vie tuntea parametrinsa ja tyyppinsä.
 */

public class TilastoAineisto {
    
    double[] aineisto;
    double odotusarvo;
    double varianssi;
    double parametri1;
    double parametri2;
    double min;
    double max;
    Jakauma jakauma;
    
    public TilastoAineisto(double[] aineisto, double parametri1, double parametri2, Jakauma jakauma) {
        this.aineisto = aineisto;
        this.parametri1 = parametri1;
        this.parametri2 = parametri2;
        this.jakauma = jakauma;
    }
    
    public TilastoAineisto(double[] aineisto, double parametri1, Jakauma jakauma) {
        this.aineisto = aineisto;
        this.parametri1 = parametri1;
        this.jakauma = jakauma;
    }
    
    /**
     * Metodi laskee jakauman odotusarvon. Odotusarvo luonnollisesti riippuu parametreista
     * ja siitä mikä jakauma on kyseessä.
     *  
     * @return metodi palauttaa double-tyyppisenä arvona jakauman odotusarvon.
     * @throws Exception. Cauchy-jakaumalla ei ole mitään momentteja, ei siis odotusarvoakaan. 
     * Niinpä yritys laskea sen odotusarvo johtaa poikkeukseen.
     */
    private double laskeOdotusarvo() throws Exception {
        if (jakauma == Jakauma.NORMAALI) {
            return parametri1;
        }
        
        if (jakauma == Jakauma.POISSON) {
            return parametri1;
        }
        
        if (jakauma == Jakauma.BINOMI) {
            return parametri1*parametri2;
        }
        
        if (jakauma == Jakauma.GAMMA) {
            return parametri1/parametri2;
        }
        
        if (jakauma == Jakauma.EKSPONENTTI) {
            return 1/parametri1;
        }
        
        else throw new Exception();
    }
    
    public double[] getAineisto() {
        return aineisto;
    }
    
    
    public double getN() {
        return (double)this.aineisto.length;
    }
    
    public Jakauma getJakauma() {
        return this.jakauma;
    }
}
