package ohha.jakaumasimulaattori;
import java.io.*;
import kayttoliittyma.*;

public class App 
{
    
   
    
    public static void main( String[] args )
    {
        TunnuslukuLaskuri tuksu = new TunnuslukuLaskuri();
        Otosgeneraattori otos = new Otosgeneraattori();
        Satunnaisgeneraattori sat = new Satunnaisgeneraattori();
        TiedostonKasittelija tk;
//        for (int i = 0; i < 100; i++) {
//            System.out.println(sat.generoiNormaali(5.5,1));
//        }
//        
        System.out.println(tuksu.laskeKeskiarvo(otos.poissonAineisto(2700,3.5)));
        double[] aineisto = otos.normaaliAineisto(100,5,10);
        System.out.println(tuksu.laskeKeskiarvo(aineisto));
        
        double summa = 0;
        for (int i = 0; i <= 10; i++) {
            summa = summa+aineisto[i];
            System.out.println(sat.generoiBernoulli(0.3));
        }
        
        System.out.println(summa);

        File aineistotulostus = new File("aineisto.csv");
        tk = new TiedostonKasittelija();
        try {
        tk.tulostaAineistoTiedostoonCSV(aineistotulostus, aineisto);
        }
        
        catch (IOException e) {
            System.out.println("ei toimi");
        }
        
        Kayttoliittyma kali = new Kayttoliittyma();
        kali.run();
    }
}
