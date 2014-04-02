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
        return palautettava;
    }
    
    public double[] eksponenttiAineisto(int n, double lambda) {
        // luo aineiston, joka muodostuu useista eksponenttijakauman muuttujista
        double[] palautettava = new double[n];
        
        for (int i = 0; i < n; i++) {
            palautettava[i] = satunnaisgeneraattori.generoiExp(lambda);
        }
        
        return palautettava;        
        
    }
    
    public double[] normaaliAineisto(int n, double myy, double sigma) {
        // luo normaalijakaumaa seuraavan aineiston
       
        double[] palautettava = new double[n];
        
        for (int i = 0; i < n; i++) {
            palautettava[i] = satunnaisgeneraattori.generoiNormaali(myy,sigma);
        }
        
        return palautettava;
    }
    
    public double[] poissonAineisto(int n, double lambda) {
        // luo Poisson-jakautuneen aineiston
        
        
        double[] palautettava = new double[n];
        
        for (int i = 0; i < n; i++) {
            palautettava[i] = satunnaisgeneraattori.generoiPoisson(lambda);
        }
        return palautettava;
    }
    
    public double[] gammaAineisto(int n, double alfa, double beta) {
        double[] palautettava = new double[n];
        
        for (int i = 0; i < n; i++) {
            palautettava[i] = satunnaisgeneraattori.generoiGamma(alfa, beta);
        }
        
        return palautettava;
    }
}
