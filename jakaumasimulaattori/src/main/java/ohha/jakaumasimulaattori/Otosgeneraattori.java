package ohha.jakaumasimulaattori;
import java.util.*;
import uusikayttoliittyma.Jakauma;
/**
 * 
 * Luokka muodostaa otoksia jakaumista.
 */ 

public class Otosgeneraattori {
    /** Luokka käyttää satunnaisgeneraattoria yksittäisten havaintojen muodostamiseen. */
    Satunnaisgeneraattori satunnaisgeneraattori = new Satunnaisgeneraattori();
    
    
    
    /** muodostaa aineiston, jonka jokainen muuttuja on usean bernoulli-havainnon summa.
        * @param n kertoo muuttujien määrän.
        * @paran p kertoo onnistumistodennäköisyyden Bernoulli-kokeessa
        * @return metodi palauttaa tilastoaineisto-olion.
        */
    public TilastoAineisto binomiAineisto(int otoskoko, double n, double p) {
        
        double[] palautettava = new double[otoskoko];
        double onnistumisia;
        for (int k = 0; k < otoskoko; k++) {
            onnistumisia = 0;
            for (int i = 0; i < n; i++) {
                onnistumisia = onnistumisia + satunnaisgeneraattori.generoiBernoulli(p);
            } 
            palautettava[k] = onnistumisia;
        }
        return new TilastoAineisto(palautettava, n, p, Jakauma.BINOMI);
    }
    
        /** Metodi luo aineiston, joka muodostuu useista eksponenttijakaumaa
        * noudattavista satunnaismuuttujista. 
        * @param n kertoo havaintojen määrän
        * @param lambda on jakaumaa määrittävä parametri, jonka käänteisluku
        * on odotusarvo ja käänteisluvun neliö varianssi
        * @return metodi palauttaa tilastoaineisto-olion
        */   
    public TilastoAineisto eksponenttiAineisto(int n, double lambda) {
 
        double[] palautettava = new double[n];
        
        for (int i = 0; i < n; i++) {
            palautettava[i] = satunnaisgeneraattori.generoiExp(lambda);
        }
        
        return new TilastoAineisto(palautettava, lambda, Jakauma.EKSPONENTTI);        
        
    }

    
       /** Metodi luo normaalijakaumaa seuraavan aineiston
       * @param n on muuttujien määrä
       * @param myy kertoo jakauman odotusarvon, joka on myös esim. moodi ja mediaani
       * @param sigma on jakauman varianssi neliöjuuri
       * @return metodi palauttaa tilastoaineisto-olion
       */
    public TilastoAineisto normaaliAineisto(int n, double myy, double sigma) {

        double[] palautettava = new double[n];
        
        for (int i = 0; i < n; i++) {
            palautettava[i] = satunnaisgeneraattori.generoiNormaali(myy,sigma);
        }
        
        return new TilastoAineisto(palautettava, myy, sigma, Jakauma.NORMAALI);
    }
        /** Metodi luo Poisson-jakautuneen aineiston
        * @param n on generoitavien muuttjien määrä
        * @param lambda on jakauman odotusarvo ja varianssi
        * @return metodi palauttaa tilastoaineisto-olion
        */            
    public TilastoAineisto poissonAineisto(int n, double lambda) {

        double[] palautettava = new double[n];
        
        for (int i = 0; i < n; i++) {
            palautettava[i] = satunnaisgeneraattori.generoiPoisson(lambda);
        }
        return new TilastoAineisto(palautettava, lambda, Jakauma.POISSON);
    }

    
     /**
     * Metodi luo gamma-jakaumaa seuraavan aineiston. 
     * @param n on simuloitavien muuttujien määrä
     * @param alfa on jakaumaa määrittävä parametri
     * @param beta on jakaumaa määrittävä parametri
     * @return Metodi palauttaa tilastoaineisto-olion
     */
    public TilastoAineisto gammaAineisto(int n, double alfa, double beta) {

        
        double[] palautettava = new double[n];
        
        for (int i = 0; i < n; i++) {
            palautettava[i] = satunnaisgeneraattori.generoiGamma(alfa, beta);
        }
        
        return new TilastoAineisto(palautettava, alfa, beta, Jakauma.GAMMA);
    }
    
    /**
     * Metodi luo cauchy-jakaumaa seuraavan aineiston.
     * @param n on simuloitavien muuttujien määrä eli jokin kokonaisluku.
     * @param x0 on jakauman sijaintiparametri, eli mikä tahansa double
     * @param gamma on jakauman hajontaparametri eli nollaa suurempi double
     * @return Metodi palauttaa TilastoAineisto-olion.
     */    
    public TilastoAineisto cauchyAineisto(int n, double gamma, double x0) {
        double[] palautettava = new double[n];
        
        for (int i = 0; i < n; i++) {
            palautettava[i] = satunnaisgeneraattori.generoiCauchy(gamma, x0);
        }        
        return new TilastoAineisto(palautettava, gamma, x0, Jakauma.CAUCHY);
    }
}
