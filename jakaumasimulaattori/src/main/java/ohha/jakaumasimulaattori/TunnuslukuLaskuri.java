
package ohha.jakaumasimulaattori;
import java.util.*;
/**
 *
 * @author rtuomainen
 */
public class TunnuslukuLaskuri {
    /**
     * Luokan avulla voi laskea erilaisia tunnuslukuja aineistosta
     * @param aineisto on jokin tilastoaineisto-olio
     * @return 
     */

    
    public double laskeKeskiarvo(TilastoAineisto tilastoaineisto){
        /**
         * metodi laskee aineiston otoskeskiarvon tunnetulla kaavalla
         * @param aineisto on jokin TilastoAineisto-olio, joka sisältää aineiston
         */
 
        double summa = 0;
        double n = tilastoaineisto.getN();
        double[] aineisto = tilastoaineisto.getAineisto();
        
        for (int i = 0; i < n; i++) {
            summa = summa + aineisto[i];
        }
        
        return summa/n;
    }
    
       
    public double laskeSumma(TilastoAineisto tilastoaineisto) {
        /**
         * Metodi laskee aineiston muuttujien summan
         * @param aineisto on jokin yksiulotteinen taulukko, johon aineiston arvot on koottu
         */
        return this.laskeKeskiarvo(tilastoaineisto)*tilastoaineisto.getN();
    }
    
    
    public double laskeOtoskeskihajonta(TilastoAineisto tilastoaineisto) {
        
        /**
         * Metodi laskee tunnetulla kaavalla otoskeskihajonnan aineistosta.
         * @param aineisto on jokin yksiulotteinen taulukko, joka sisältää aineiston
         */
        double n = tilastoaineisto.getN();
        double neliosumma = 0;
        double[] aineisto = tilastoaineisto.getAineisto();
        
        for (int i = 0; i < n; i++) {
            neliosumma += Math.pow(aineisto[i]-this.laskeKeskiarvo(tilastoaineisto),2);
        }
        
        return Math.sqrt(neliosumma/(n-1));        
    }
    
    
    
    public double laskeBinomiAineistonOtoskeskihajonta(TilastoAineisto tilastoaineisto) {
        /**
         * metodi laskee binomijakaumaa seuraavan aineiston otoskeskihajonnan, mikä 
         * tapahtuu eri kaavalla kuin muissa tapauksissa
         * @param aineisto on yksiulotteinen taulukko, johon aineisto on tallennettu
         */
           return this.laskeKeskiarvo(tilastoaineisto)*(1-this.laskeKeskiarvo(tilastoaineisto))/tilastoaineisto.getN();
    }

    /**
    * Metodi laskee t-testisuureen arvon tunnetulla kaavalla.
    * @param tilastoaineisto on tilastoaineisto-olio, joka sisaltaa aineiston
    * @param h0 on otoskeskiarvoa koskeva nollahypoteesi
    * @return Metodi palauttaa double-tyyppisen arvon, joka kuvaa kuinka hyvin aineisto
    * on sopusoinnussa nollahypoteesin kanssa
    */
   public double laskeTtestisuure(TilastoAineisto tilastoaineisto, double h0){
       return Math.sqrt(tilastoaineisto.getN())*(h0-this.laskeKeskiarvo(tilastoaineisto))/this.laskeOtoskeskihajonta(tilastoaineisto);
   }
   
   public double max(TilastoAineisto tilastoaineisto) {
       
       double[] aineisto = tilastoaineisto.getAineisto();
       double max = aineisto[0];
       
       for (int i = 0; i < aineisto.length; i++) {
           if (aineisto[i] > max) {
               max=aineisto[i];
           }
       }
       
       return max;
   }
   
   public double min(TilastoAineisto tilastoaineisto) {
       double[] aineisto = tilastoaineisto.getAineisto();
       double min = aineisto[0];
       
       for (int i = 0; i < aineisto.length; i++) {
           if (aineisto[i] < min) {
               min = aineisto[i];
           }
       }
       return min;
   }
   
    public double maxint(int[] aineisto) {
       
       int max = aineisto[0];
       
       for (int i = 0; i < aineisto.length; i++) {
           if (aineisto[i] > max) {
               max=aineisto[i];
           }
       }
       
       return (double)max;
   }
  
    
    public int montakoErilaistaHavaintoa(double[] aineisto) {
        
        HashSet<Double> havaintojoukko = new HashSet<Double>();
        
        for (int i = 0; i < aineisto.length; i++) {
            havaintojoukko.add(aineisto[i]);
            
        }
        
        return havaintojoukko.size();
    }
}
