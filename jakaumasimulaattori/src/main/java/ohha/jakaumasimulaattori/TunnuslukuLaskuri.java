
package ohha.jakaumasimulaattori;
import java.util.*;
     /**
     * Luokan avulla voi laskea erilaisia tunnuslukuja aineistosta
     */
public class TunnuslukuLaskuri {
   

    
        /**
         * Metodi laskee aineiston otoskeskiarvon tunnetulla kaavalla
         * @param aineisto on jokin TilastoAineisto-olio, joka sisältää aineiston
         */
    public double laskeKeskiarvo(TilastoAineisto tilastoaineisto){
        
 
        double summa = 0;
        double n = tilastoaineisto.getN();
        double[] aineisto = tilastoaineisto.getAineisto();
        
        for (int i = 0; i < n; i++) {
            summa = summa + aineisto[i];
        }
        
        return summa/n;
    }
    
    
    
        /**
         * Metodi laskee aineiston muuttujien summan
         * @param aineisto on jokin tilastoaineisto
         */      
    public double laskeSumma(TilastoAineisto tilastoaineisto) {

        return this.laskeKeskiarvo(tilastoaineisto)*tilastoaineisto.getN();
    }
    

    
    
    /**
    * Metodi laskee tunnetulla kaavalla otoskeskihajonnan aineistosta.
    * @param aineisto on jokin tilastoaineisto
    */
    public double laskeOtoskeskihajonta(TilastoAineisto tilastoaineisto) {
        

        double n = tilastoaineisto.getN();
        double neliosumma = 0;
        double[] aineisto = tilastoaineisto.getAineisto();
        
        for (int i = 0; i < n; i++) {
            neliosumma += Math.pow(aineisto[i]-this.laskeKeskiarvo(tilastoaineisto),2);
        }
        
        return Math.sqrt(neliosumma/(n-1));        
    }
    
    

    /**
    * metodi laskee binomijakaumaa seuraavan aineiston otoskeskihajonnan, mikä 
    * tapahtuu eri kaavalla kuin muissa tapauksissa
    * @param aineisto tilastoaineisto
    */
    public double laskeBinomiAineistonOtoskeskihajonta(TilastoAineisto tilastoaineisto) {

           return this.laskeKeskiarvo(tilastoaineisto)*(1-this.laskeKeskiarvo(tilastoaineisto))/tilastoaineisto.getN();
    }

    /**
    * Metodi laskee t-testisuureen arvon tunnetulla kaavalla.
    * @param tilastoaineisto on normaalijakaumaa seuraava tilastoaineisto.
    * @param h0 on otoskeskiarvoa koskeva nollahypoteesi
    * @return Metodi palauttaa double-tyyppisen arvon, joka kuvaa kuinka hyvin aineisto
    * on sopusoinnussa nollahypoteesin kanssa
    */
   public double laskeTtestisuure(TilastoAineisto tilastoaineisto, double h0){
       return Math.sqrt(tilastoaineisto.getN())*(this.laskeKeskiarvo(tilastoaineisto)-h0)/this.laskeOtoskeskihajonta(tilastoaineisto);
   }
   
   
   /**
    * Metodi kertoo aineiston suurimman arvon
    * @param tilastoaineisto on jokin tilastoaineisto
    * @return Metodi palauttaa suurimman arvon double-tyyppisenä
    */
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
   
   
   /**
    * Metodi kertoo aineiston pienimmän arvon
    * @param tilastoaineisto on jokin tilastoaineisto
    * @return metodi palauttaa pienimmän arvon double-tyyppisenä
    */
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
   
   
    /**
     * Metodi kertoo kokonaislukutaulukon suurimman arvon.
     * @param aineisto on jokin yksiulotteinen kokonaislukutaulukko.
     * @return Metodi palauttaa suurimman arvon muunnettuna double-tyyppiseksi.
     */
    public double maxint(int[] aineisto) {
       
       int max = aineisto[0];
       
       for (int i = 0; i < aineisto.length; i++) {
           if (aineisto[i] > max) {
               max=aineisto[i];
           }
       }
       
       return (double)max;
   }
  
    
    /**
     * Metodi kertoo, montako eri arvoa aineistossa esiintyy. Tämä tapahtuu HashSet-rakennetta käyttäen.
     * @param aineisto on jokin double-arvoja sisältävä yksiulotteinen taulukko.
     * @return metodi palauttaa erilaisten arvojen määrän kokonaislukuna.
     */
    public int montakoErilaistaHavaintoa(double[] aineisto) {
        
        HashSet<Double> havaintojoukko = new HashSet<Double>();
        
        for (int i = 0; i < aineisto.length; i++) {
            havaintojoukko.add(aineisto[i]);
            
        }
        
        return havaintojoukko.size();
    }
}
