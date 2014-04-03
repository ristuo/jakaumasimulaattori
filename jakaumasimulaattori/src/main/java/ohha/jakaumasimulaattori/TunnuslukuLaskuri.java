
package ohha.jakaumasimulaattori;

/**
 *
 * @author rtuomainen
 */
public class TunnuslukuLaskuri {
    /**
     * Luokan avulla voi laskea erilaisia tunnuslukuja aineistosta
     * @param aineisto on jokin tilastoaineisto
     * @return 
     */

    
    public double laskeKeskiarvo(double[] aineisto){
        /**
         * metodi laskee aineiston otoskeskiarvon tunnetulla kaavalla
         * @param aineisto on jokin yksiulotteinen taulukko, joka sisältää aineiston
         */
 
        double summa = 0;
        double n = aineisto.length;
        
        for (int i = 0; i < n; i++) {
            summa = summa + aineisto[i];
        }
        
        return summa/n;
    }
    
       
    public double laskeSumma(double[] aineisto) {
        /**
         * Metodi laskee aineiston muuttujien summan
         * @param aineisto on jokin yksiulotteinen taulukko, johon aineiston arvot on koottu
         */
        return this.laskeKeskiarvo(aineisto)*aineisto.length;
    }
    
    
    public double laskeOtoskeskihajonta(double[] aineisto) {
        
        /**
         * Metodi laskee tunnetulla kaavalla otoskeskihajonnan aineistosta.
         * @param aineisto on jokin yksiulotteinen taulukko, joka sisältää aineiston
         */
        double n = aineisto.length;
        double neliosumma = 0;
        
        for (int i = 0; i < n; i++) {
            neliosumma += Math.pow(aineisto[i]-this.laskeKeskiarvo(aineisto),2);
        }
        
        return Math.sqrt(neliosumma/(n-1));        
    }
    
    
    
    public double laskeBinomiAineistonOtoskeskihajona(double[] aineisto) {
        /**
         * metodi laskee binomijakaumaa seuraavan aineiston otoskeskihajonnan, mikä 
         * tapahtuu eri kaavalla kuin muissa tapauksissa
         * @param aineisto on yksiulotteinen taulukko, johon aineisto on tallennettu
         */
           return this.laskeKeskiarvo(aineisto)*(1-this.laskeKeskiarvo(aineisto))/aineisto.length;
    }
    
   public double laskeTtestisuure(double[] aineisto, double h0){
       /**
        * Metodi laskee t-testisuureen arvon tunnetulla kaavalla.
        * @param aineisto on yksiulotteinen taulukko, joka sisaltaa aineiston
        * @param h0 on otoskeskiarvoa koskeva nollahypoteesi
        */
       return Math.sqrt(aineisto.length)*(h0-this.laskeKeskiarvo(aineisto))/this.laskeOtoskeskihajonta(aineisto);
   }
   
   
}
