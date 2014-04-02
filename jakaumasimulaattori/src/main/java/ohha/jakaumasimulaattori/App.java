package ohha.jakaumasimulaattori;
import java.io.*;
import kayttoliittyma.*;
import uusikayttoliittyma.*;

public class App 
{
    
   
    
    public static void main( String[] args )
    {
//        TunnuslukuLaskuri tuksu = new TunnuslukuLaskuri();
//        Otosgeneraattori otos = new Otosgeneraattori();
//        Satunnaisgeneraattori sat = new Satunnaisgeneraattori();
//        TiedostonKasittelija tk;
////        for (int i = 0; i < 100; i++) {
////            System.out.println(sat.generoiNormaali(5.5,1));
////        }
////        
//        System.out.println(tuksu.laskeKeskiarvo(otos.poissonAineisto(2700,3.5)));
//        double[] aineisto = otos.normaaliAineisto(100,5,10);
//        System.out.println(tuksu.laskeKeskiarvo(aineisto));
//        
//        double summa = 0;
//        for (int i = 0; i <= 10; i++) {
//            summa = summa+aineisto[i];
//            System.out.println(sat.generoiBernoulli(0.3));
//        }
//        
//        System.out.println(summa);
//
//        File aineistotulostus = new File("aineisto.csv");
//        tk = new TiedostonKasittelija();
//        try {
//        tk.tulostaAineistoTiedostoonCSV(aineistotulostus, aineisto);
//        }
//        
//        catch (IOException e) {
//            System.out.println("ei toimi");
//        }
//        
//        double[] aineisto2 = otos.poissonAineisto(10000, 0.5);
//        System.out.println("poissonin keskiarvo"+tuksu.laskeKeskiarvo(aineisto2));
////        Kayttoliittyma kali = new Kayttoliittyma();
////        kali.run();
//        System.out.println("keskihajonta" + tuksu.laskeOtoskeskihajonta(aineisto));
//        for (int i = 0; i <= 99; i++) {
//            System.out.println(aineisto2[i]);
//        }
        
//        JakaumanValinta jk = new JakaumanValinta();
//        
//        jk.run();
        
        Kayttoliittyma kayttis = new Kayttoliittyma();
        
        kayttis.run();
    }
}
