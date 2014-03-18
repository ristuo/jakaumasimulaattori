package ohha.jakaumasimulaattori;
import java.util.*;

public class Otosgeneraattori {
// luokka muodostaa otoksia jakaumista
    
    Satunnaisgeneraattori satunnaisgeneraattori = new Satunnaisgeneraattori();
    
    public double[] binomiAineisto(int n, double p) {
        // muodostaa aineiston, joka siis koostuu useasta Bernoulli-havainnosta
        
        double[] palautettava = new double[n];
        
        for (int i = 0; i < n; i++) {
            palautettava[i] = satunnaisgeneraattori.generoiBernoulli(p);
        } 
        return new double[1];
    }
    
    public double[] eksponenttiAineisto(int n, double lambda) {
        // luo aineiston, joka muodostuu useista eksponenttijakauman muuttujista
        double[] palautettava = new double[n];
        
        for (int i = 0; i < n; i++) {
            palautettava[i] = satunnaisgeneraattori.generoiExp(lambda);
        }
        
        return palautettava;        
        
    }
    
    
    
}
