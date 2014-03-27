
package ohha.jakaumasimulaattori;

/**
 *
 * @author rtuomainen
 */
public class TunnuslukuLaskuri {
// luokka sisältää erilaisia tunnuslukuja laskevia metodeja
    
    public double laskeKeskiarvo(double[] aineisto){
        // laskee otoskeskiarvon
        double summa = 0;
        double n = aineisto.length;
        
        for (int i = 0; i < n; i++) {
            summa = summa + aineisto[i];
        }
        
        return summa/n;
    }
    
     public double laskeKeskiarvo(int[] aineisto){
        // laskee otoskeskiarvon
        double summa = 0;
        double n = aineisto.length;
        
        for (int i = 0; i < n; i++) {
            summa = summa + (double)aineisto[i];
        }
       
        return summa/n;
    }
    
    public double laskeSumma(double[] aineisto) {
        return this.laskeKeskiarvo(aineisto)*aineisto.length;
    }
    
    public double laskeSumma(int[] aineisto) {
        return this.laskeKeskiarvo(aineisto)*aineisto.length;
    }
    
    public double laskeOtoskeskihajonta(double[] aineisto) {
        //metodi laskee harhattoman estimaatin keskihajonnalle
        double n = aineisto.length;
        double neliosumma = 0;
        
        for (int i = 0; i < n; i++) {
            neliosumma += Math.pow(aineisto[i]-this.laskeKeskiarvo(aineisto),2);
        }
        
        return Math.sqrt(neliosumma/(n-1));        
    }
    
    public double laskeOtoskeskihajonta(int[] aineisto) {
        double n = aineisto.length;
        double neliosumma = 0;
        
        for (int i = 0; i < n; i++) {
            neliosumma += Math.pow((double)aineisto[i] - this.laskeKeskiarvo(aineisto), 2);
        }
        
        return Math.sqrt(neliosumma/(n-1));
    }
    
    public double laskeBinomiAineistonOtoskeskihajona(int[] aineisto) {
           return this.laskeKeskiarvo(aineisto)*(1-this.laskeKeskiarvo(aineisto))/aineisto.length;
    }
    
   public double laskeTtestisuure(double[] aineisto, double h0){
       return Math.sqrt(aineisto.length)*(h0-this.laskeKeskiarvo(aineisto))/this.laskeOtoskeskihajonta(aineisto);
   }
   
   
}
