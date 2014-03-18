

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
        return -Math.log(Math.random())/lambda;
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
        // satunnaismuuttujan Box-Muller menetelmän avulla
        
        double u = Math.random();
        double v = Math.random();
        double x = Math.sqrt(-2*Math.log(u))*Math.cos(2*Math.PI*v);
        // täytyy olla aika kova luu että heti näkee ton seuraavan standardi normaalijakaumaa, 
        // mutta wikipedian mukaan näin on asian laita
            
        
        return x+myy;
    }
    
    public double generoiGamma() {
        return 0;
    }
    
    
    
    
}
