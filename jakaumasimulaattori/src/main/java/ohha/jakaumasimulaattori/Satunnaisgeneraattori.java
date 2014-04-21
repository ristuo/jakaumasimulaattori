

package ohha.jakaumasimulaattori;
import java.util.*;
/**
*@author Risto Tuomainen
* Luokkaan on koottu erilaisia satunnaismuuttujia generoivat metodit. Metodeilla
* voi generoida Bernoulli, normaali, poisson, gamma tai eksponenttijakaumia
* seuraavia satunnaismuuttujia.
* 
*/
public class Satunnaisgeneraattori {

    

    
    /** Metodi generoi yhden bernoulli(p)-satunnaismuuttujan. Bernoulli-muuttuja 
    * kertoo onko jossain satunnaiskokeessa, jossa onnistumistodennäköisyys on 
    * p, onnistuttu.
    * 
    * @param p Onnistumistodennäköisyys Bernoulli-kokeessa. Koska kyse on 
    * todennäköisyydestä, arvon täytyy olla väliltä [0,1]
    * @return 1 Kuvaa onnistunutta koetta, 0 epäonnistumista
    */
    public double generoiBernoulli(double p) {

        if (Math.random() < p) {        
            return 1;
        }
        else return 0;                
    }
    
    /**
    * Metodi generoi eksponenttijakaumaa noudattavan satunnaismuuttujan. Laskussa
    * käytetään tunnettua käänteisfunktiomenetelmää.
    * 
    * @param lambda on jakauman parametri. Eksponenttijakauma kertoo odotus-
    * ajan kahden Poisson-saapumisen välillä, ja lambda on Poisson-jakauman 
    * odotusarvo.
    * @return Metodi palauttaa eksponenttijakaumaa seuraavansa arvon double-tyyppisenä. Arvo mallintaa
    * Poisson-saapumisten välistä aikaa
    */  
    public double generoiExp(double lambda) {        
        return -Math.log(Math.random())/lambda;
    }
 
    
    /** 
    * Metodi generoi Poisson-satunnaismuuttujan.Laskennassa käytetään sitä tietoa, että kahden poisson-saapumisen välinen
    * odotusaika seuraa eksponenttijakaumaa ja Poisson-satunnaismuuttuja
    * kertoo montako saapumista on sattunut yhden aikayksikon kuluessa
    * 
    * @param Lambda on jakauman määrittävä parametrien, joka sattumoisin on myös jakauman odotusarvo sekä varianssi.
    * @return Metodi palauttaa generoidun satunnaismuuttujan arvon double-tyyppisenä, vaikka Poisson-muuttujan 
    * arvot varsinaisesti ovat aina kokonaislukuja.
    * @see generoiExp        
    */
    public double generoiPoisson(double lambda) {


        
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
    
    
    /**
    * metodi laskee normaaln tiheysfunktion arvon.
    * @param myy jakauman odotusarvo
    * @param sigma jakauman varianssi neliöjuuri
    * @param muuttujan x arvo
    */ 
    private double normaalinTiheysfunktionArvoKohdassaX(double myy, double sigma, double x) {

        return 1/(Math.sqrt((2*Math.PI))*sigma)*Math.exp(-0.5*(Math.pow((x-myy)/sigma,2)));
    }
    
    
    /**
    * Metodi generoi kahden parametrin normaalijakaumaa
    * seuraavan satunnaismuuttujan. Tässä käytetään rejection sampling -menetelmää 
    * äärimmäisen tehottomasti toteutettuna.
    * Ajatuksena on laskea satunnaisia pisteitä xy-koordinaatistosta ja sitten
    * hylätä ne, jotka eivät ole normaalijakauman määräämässä alueessa. nythän
    * hännistä jää osa jakaumaa ulos, mutta se ei haittaa, koska todennäköisyys
    * muutenkaan saada mitään lukuja sieltä on niin pieni
    * 
    * @param myy jakauman odotusarvo, joka voi olla mikä tahansa double-tyyppinen luku.
    * @param sigma jakauman varianssi neliöjuuri, joka voi olla mikä tahansa double-tyyppinen luku.
    * @return Metodi palauttaa double-tyyppisenä lukuna simuloidun arvon.
    */
    public double generoiNormaali(double myy, double sigma) {
        
        

        
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
    
    /**
    * Metodi generoi gamma-jakaumaa seuraavan satunnaismuuttujan Marsaglian ja Tsangin menetelmällä,
    * joka on toteutettu rekursiivisesti.
    * @param alfa on jakaumaa määrittävä parametri, ns. spread parameter.
    * @param beta on jakaumaa määrittävä parametri, ns. rate parameter.
    * @return Metodi palauttaa double-tyyppisen simuloidun arvon.
    */  
    public double generoiGamma(double alfa, double beta) {

        
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
    
    /** Metodi palauttaa Cauchy-jakaumaa seuraavan satunnaismuuttujan arvon. Laskennassa
     * käytetään käänteisfunktiomenetelmää.
     * 
     * @param x0 on mikä tahansa double-tyyppinen luku. x0 on jakauman ns. sijaintiparametri.
     * @param gamma on positiivinen double-tyyppinen luku. Gamma on jakauman ns. hajontaparametri.
     * @return 
     */    
    public double generoiCauchy(double gamma, double x0) {
        return gamma*Math.tan(Math.PI*(Math.random()-0.5))+x0;  
    }
    
    
    
}
