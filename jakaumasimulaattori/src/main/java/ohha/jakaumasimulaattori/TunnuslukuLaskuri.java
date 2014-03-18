
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
            summa += aineisto[i];
        }
        
        return summa/n;
    }
    
    
    public double laskeOtoskeskihajonta(double[] aineisto) {
        //metodi laskee harhattoman estimaatin keskihajonnalle
        double n = aineisto.length;
        double neliosumma = 0;
        
        for (int i = 0; i < n; i++) {
            neliosumma += Math.pow(2, aineisto[i]-this.laskeKeskiarvo(aineisto));
        }
        
        return Math.sqrt(neliosumma/(n-1));        
    }
    
   public double laskeTtestisuure(double[] aineisto){
       return 0;
   }
}
