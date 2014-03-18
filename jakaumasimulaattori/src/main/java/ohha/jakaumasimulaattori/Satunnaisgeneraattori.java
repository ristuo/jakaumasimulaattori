

package ohha.jakaumasimulaattori;
import java.util.*;

public class Satunnaisgeneraattori {
    //luokkaan on koottu erilaisia satunnaismuuttujia generoivat metodit
    
    
    
    public int generoiBernoulli(double p) {
        // metodi generoi yhden bernoulli(p)-satunnaismuuttujan
        if (Math.random() > p) {        
            return 1;
        }
        else return 0;
    }
    
    public double generoiExp(double lambda) {
        // metodi generoi eksponenttijakaumaa noudattavan satunnaismuuttujan.
        // lambda on jakauman parametri, sattumoisin myös odotusarvo
        return (Math.log(Math.random()) - Math.log(lambda))/(-lambda);
    }
    
    public int generoiPoisson(double lambda) {
        // metodi generoi Poisson-satunnaismuuttujan.
        // Tässä käytetään pikku kikkaa: kahden poisson-saapumisen välinen
        // odotusaika seuraa eksponenttijakaumaa ja Poisson-satunnaismuuttuja
        // kertoo montako saapumista on sattunut yhden aikayksikon kuluessa
        
        double a = 0;
        int i = 0;        
        while (a < 1) {
            a = a + this.generoiExp(lambda);
            if (a <= 1) {
                i++;
            }
        }        
        return i;        
    }
    
    public double generoiNormaali(double myy, double sigma) {
        // metodi generoi kahden parametrin normaalijakaumaa
        // seuraavan satunnaismuuttujan
        
        return 0;
    }
    
    public double generoiNormaali(double myy) {
        // metodi generoi yksiparametrista normaalijakaumaa seuraavan 
        // satunnaismuuttujan
        return 0;
    }
    
    public double generoiGamma() {
        return 0;
    }
    
    
    
    
}
