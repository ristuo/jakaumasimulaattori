package ohha.jakaumasimulaattori;
import java.util.*;
import java.io.*;


public class TiedostonKasittelija {
// luokan tehtavana on tuottaa tiedostoja aineistosta
    
    
    
    public void tulostaAineistoTiedostoon(String tiedostonimi, double[] aineisto) {
        
        PrintWriter tulos;
        
        try {
            tulos = new PrintWriter(new FileWriter(tiedostonimi));
            for (int i = 0; i < aineisto.length; i++) {
                String tulostus = "" + aineisto[i];
                tulos.append(tulostus + ";");
            }
            tulos.close();
        }
        
        catch (IOException e) {
            System.out.println("Ei pysty");
        }
        
    }
}
