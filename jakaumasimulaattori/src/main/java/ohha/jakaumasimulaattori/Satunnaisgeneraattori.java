

package ohha.jakaumasimulaattori;
import java.util.*;

public class Satunnaisgeneraattori {
    /**
    *@author Risto Tuomainen
    * Luokkaan on koottu erilaisia satunnaismuuttujia generoivat metodit. Metodeilla
    * voi generoida Bernoulli, normaali, poisson, gamma tai eksponenttijakaumia
    * seuraavia satunnaismuuttujia.
    * 
    */
    
    
    public double generoiBernoulli(double p) {
        /** metodi generoi yhden bernoulli(p)-satunnaismuuttujan
         * 
         */
        if (Math.random() < p) {        
            return 1;
        }
        else return 0;
        
        
        /** @param p Onnistumistodennäköisyys Bernoulli-kokeessa
        * @return 1 Kuvaa onnistunutta koetta, 0 epäonnistumista
        */
                
    }
    
    public double generoiExp(double lambda) {
        /**
         * metodi generoi eksponenttijakaumaa noudattavan satunnaismuuttujan. Laskussa
         * käytetään tunnettua käänteisfunktiomenetelmää.
         * 
         * @param lambda on jakauman parametri. Eksponenttijakauma kertoo odotus-
         * ajan kahden Poisson-saapumisen välillä, ja lambda on Poisson-jakauman 
         * odotusarvo.
         */
        
        return -Math.log(Math.random())/lambda;
        
        /**
         * @return Arvo mallintaa Poisson-saapumisten välistä aikaa
         */
    }
    
    public double generoiPoisson(double lambda) {
        /**
         * 
         * metodi generoi Poisson-satunnaismuuttujan.Laskennassa käytetään pikku kikkaa: kahden poisson-saapumisen välinen
         * odotusaika seuraa eksponenttijakaumaa ja Poisson-satunnaismuuttuja
         * kertoo montako saapumista on sattunut yhden aikayksikon kuluessa
         * 
         * @see generoiExp        
         */

        
        double a = 0;
        double i = 0;        
        while (a < 1) {
            a = a + this.generoiExp(lambda);
            if (a <= 1) {
                i++;
            }
        }        
        return i;        
    }
    
    private double normaalinTiheysfunktionArvoKohdassaX(double myy, double sigma, double x) {
        /**
         * metodi laskee normaaln tiheysfunktion arvon.
         * @param myy jakauman odotusarvo
         * @param sigma jakauman varianssi neliöjuuri
         * @param muuttujan x arvo
         */
        return 1/(Math.sqrt((2*Math.PI))*sigma)*Math.exp(-0.5*(Math.pow((x-myy)/sigma,2)));
    }
    
    public double generoiNormaali(double myy, double sigma) {
        
        /**
         * metodi generoi kahden parametrin normaalijakaumaa
         * seuraavan satunnaismuuttujan.
         * Tässä käytetään rejection sampling -menetelmää äärimmäisen tehottomasti
         * toteutettuna.
         * Ajatuksena on laskea satunnaisia pisteitä xy-koordinaatistosta ja sitten
         * hylätä ne, jotka eivät ole normaalijakauman määräämässä alueessa. nythän
         * hännistä jää osa jakaumaa ulos, mutta se ei haittaa, koska todennäköisyys
         * muutenkaan saada mitään lukuja sieltä on niin pieni
         * 
         * @param myy jakauman odotusarvo
         * @param sigma jakauman varianssi neliöjuuri
         * 
         */

        
        double korkein = this.normaalinTiheysfunktionArvoKohdassaX(myy, sigma, myy);
        double suorakulmionKorkeus = korkein;
        double suorakulmionLeveys = 7*sigma;
        double x = 0;
        double y = 27;
        double fx = 0;
        
        while (y > fx) {
            
            x = Math.random()*suorakulmionLeveys + myy;
            fx = this.normaalinTiheysfunktionArvoKohdassaX(myy, sigma, x);
            y = Math.random()*suorakulmionKorkeus;
            
        }      
        
        // tämä if siksi, että muuttujan x arvoja on laskettu vain keskiarvon oikealta 
        // puolen
      
        if (Math.random() > 0.5) {
            return myy-Math.abs(myy-x);
        }       
        return x;
    }
    
    
    public double generoiGamma(double alfa, double beta) {
        /**
         * Metodi generoi gamma-jakaumaa seuraavan satunnaismuuttujan Marsaglian ja Tsangin menetelmällä.
         * @param alfa on jakaumaa määrittävä parametri
         * @param beta on jakaumaa määrittävä parametri
         */
        
        double d = alfa - 1/3;
        double c = 1/Math.sqrt(9*d);
        double u = Math.random();
        double z = this.generoiNormaali(0,1);
        double v = Math.pow(1+c*z, 3);
        
        if (z > -1/c && Math.log(u) < 0.5*Math.pow(z,2)+d-d*v+d*Math.log(v)) {
            return d*v/beta;
        }
        else return this.generoiGamma(alfa, beta);
        
    }
    
    
    
    
}
