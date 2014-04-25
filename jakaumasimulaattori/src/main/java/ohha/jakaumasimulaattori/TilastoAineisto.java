

package ohha.jakaumasimulaattori;
import uusikayttoliittyma.*;

/**
 * 
 * 
 * TilastoAineisto-olio kapseloi generoidun tilastoaineiston, joka olennaisesti on 
 * yksiuloitteinen taulukko. Näin aineisto vie tuntea parametrinsa ja tyyppinsä.
 * @author rtuomainen
 */

public class TilastoAineisto {
    
    /** Aineisto on yksiulotteinen taulukko, joko sisältää simuloidut arvot    */
    private double[] aineisto;
    /** Odotusarvo kertoo simuloidun jakauman odotusarvon */
    private double odotusarvo;
    /** keskihajonta on jakauman varianssin neliöjuuri */    
    private double keskihajonta;
    /** parametri1 on jakaumaa määrittävä reaaliluku */   
    private double parametri1;
    /** parametri2 on jakaumaa määrittävä reaaliluku */
    private double parametri2;
    /** jakauma kertoo mitä jakaumaa aineisto simuloi */
    private Jakauma jakauma;
    TunnuslukuLaskuri tunnuslukulaskuri = new TunnuslukuLaskuri();
    
    
    /**
     * Kaksiparametrinen konstruktori luo tilastoaineisto, jota määrittää kaksi parametria.
     */
    public TilastoAineisto(double[] aineisto, double parametri1, double parametri2, Jakauma jakauma) {
        this.aineisto = aineisto;
        this.parametri1 = parametri1;
        this.parametri2 = parametri2;
        this.jakauma = jakauma;
        this.laskeOdotusarvo();
        this.laskeKeskihajonta();
    }
    
    /**
     * 
     * Yksiparametrinen konstruktori luo aineiston, jota määrittää vain yksi paarametri.
     */
    public TilastoAineisto(double[] aineisto, double parametri1, Jakauma jakauma) {
        this.aineisto = aineisto;
        this.parametri1 = parametri1;
        this.jakauma = jakauma;
        this.laskeOdotusarvo();
        this.laskeKeskihajonta();
    }
    
    /**
     * Metodi laskee jakauman odotusarvon. Odotusarvo luonnollisesti riippuu parametreista
     * ja siitä mikä jakauma on kyseessä.
     */
    private void laskeOdotusarvo() {
        if (jakauma == Jakauma.NORMAALI) {
            odotusarvo = parametri1;
        }
        
        if (jakauma == Jakauma.POISSON) {
            odotusarvo = parametri1;
        }
        
        if (jakauma == Jakauma.BINOMI) {
            odotusarvo = parametri1*parametri2;
        }
        
        if (jakauma == Jakauma.GAMMA) {
            odotusarvo = parametri1/parametri2;
        }
        
        if (jakauma == Jakauma.EKSPONENTTI) {
            odotusarvo = 1/parametri1;
        }

    }
    
    /**
     * Metodi laskee jakauman keskihajonnan.
     */
    private void laskeKeskihajonta() {
        if (jakauma == Jakauma.NORMAALI) {
            keskihajonta = parametri2;
        }
        
        if (jakauma == Jakauma.POISSON) {
            keskihajonta = Math.sqrt(parametri1);
        }
        
        if (jakauma == Jakauma.BINOMI) {
            keskihajonta = Math.sqrt( parametri1*parametri2*(1-parametri2) );
        }
        
        if (jakauma == Jakauma.GAMMA) {
            keskihajonta = Math.sqrt( parametri1/(parametri2*parametri2) );
        }
        
        if (jakauma == Jakauma.EKSPONENTTI) {
            keskihajonta = Math.sqrt( 1/(parametri1*parametri1) );
        }      

    }
    
    /**
     * Metodi palauttaa aineiston otoskeskihajonnan.
     * @return otoskeskihajonta double-tyyppisenä
     */
    public double getOtoskeskihajonta() {
        
        if (jakauma == Jakauma.BINOMI) {
            return tunnuslukulaskuri.laskeBinomiAineistonOtoskeskihajonta(this);
        }
        
        else return tunnuslukulaskuri.laskeOtoskeskihajonta(this);
    }
    
    public double getOtoskeskiarvo() {
        return tunnuslukulaskuri.laskeKeskiarvo(this);
    }
    
    public double getOdotusarvo() throws Exception {
        if (this.jakauma == Jakauma.CAUCHY) {
            throw new Exception();
        }
        
        return this.odotusarvo;
    }
    
    public double getKeskihajonta() throws Exception {
       if (this.jakauma == Jakauma.CAUCHY) {
           throw new Exception();
       }
       return this.keskihajonta;      
    }
    
    public double getParametri1() {
        return parametri1;
    }
    
    public double getParametri2() {
        return parametri2;
    }
    
    public double[] getAineisto() {
        return aineisto;
    }
    
    /**
     * Metodi kertoo kuinka monta havaintoa aineistossa on
     * @return metodi palauttaa double-tyyppisenä havaintojen määrän.
     */
    public double getN() {
        return (double)this.aineisto.length;
    }
    
    public Jakauma getJakauma() {
        return this.jakauma;
    }
    
    
//    public double getTiheysFunktionArvo(double x) {
//        if (this.jakauma == Jakauma.NORMAALI) {
//            return 1/(Math.sqrt((2*Math.PI))*parametri2)*Math.exp(-0.5*(Math.pow((x-parametri1)/parametri2,2)));
//        }
//        else return 0;
// 
}
