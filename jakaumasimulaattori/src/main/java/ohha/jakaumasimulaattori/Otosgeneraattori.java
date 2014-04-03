package ohha.jakaumasimulaattori;
import java.util.*;

public class Otosgeneraattori {
/**
 * 
 * Luokka muodostaa otoksia jakaumista, toisin sanoen taulukkoja joihin on koottu
 * tietyllä tavalla jakautuneita muuttujia.
 */ 
    Satunnaisgeneraattori satunnaisgeneraattori = new Satunnaisgeneraattori();
    
    public double[] binomiAineisto(int n, double p) {
        /** muodostaa aineiston, joka koostuu useasta Bernoulli-havainnosta
        * @param n kertoo muuttujien määrän.
        * @paran p kertoo onnistumistodennäköisyyden Bernoulli-kokeessa
        * 
        */
        double[] palautettava = new double[n];
        
        for (int i = 0; i < n; i++) {
            palautettava[i] = satunnaisgeneraattori.generoiBernoulli(p);
        } 
        return palautettava;
    }
    
    public double[] eksponenttiAineisto(int n, double lambda) {
        /** Metodi luo aineiston, joka muodostuu useista eksponenttijakaumaa
        * noudattavista satunnaismuuttujista
        * @param n kertoo havaintojen määrän
        * @param lambda on jakaumaa määrittävä parametri, jonka käänteisluku
        * on odotusarvo ja käänteisluvun neliö varianssi
        */ 
        double[] palautettava = new double[n];
        
        for (int i = 0; i < n; i++) {
            palautettava[i] = satunnaisgeneraattori.generoiExp(lambda);
        }
        
        return palautettava;        
        
    }
    
    public double[] normaaliAineisto(int n, double myy, double sigma) {
        /** Metodi luo normaalijakaumaa seuraavan aineiston
       * @param n on muuttujien määrä
       * @param myy kertoo jakauman odotusarvon, joka on myös esim. moodi ja mediaani
       * @param sigma on jakauman varianssi neliöjuuri
       */
        double[] palautettava = new double[n];
        
        for (int i = 0; i < n; i++) {
            palautettava[i] = satunnaisgeneraattori.generoiNormaali(myy,sigma);
        }
        
        return palautettava;
    }
    
    public double[] poissonAineisto(int n, double lambda) {
        /** Metodi luo Poisson-jakautuneen aineiston
        * @param n on generoitavien muuttjien määrä
        * @param lambda on jakauman odotusarvo ja varianssi
        * 
        */
        
        double[] palautettava = new double[n];
        
        for (int i = 0; i < n; i++) {
            palautettava[i] = satunnaisgeneraattori.generoiPoisson(lambda);
        }
        return palautettava;
    }
    
    public double[] gammaAineisto(int n, double alfa, double beta) {
        /**
         * Metodi luo gamma-jakaumaa seuraavan aineiston. 
         * @param n on simuloitavien muuttujien määrä
         * @param alfa on jakaumaa määrittävä parametri
         * @param beta on jakaumaa määrittävä parametri
         */
        
        double[] palautettava = new double[n];
        
        for (int i = 0; i < n; i++) {
            palautettava[i] = satunnaisgeneraattori.generoiGamma(alfa, beta);
        }
        
        return palautettava;
    }
}
