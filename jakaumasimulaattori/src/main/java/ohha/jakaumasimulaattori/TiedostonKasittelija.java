package ohha.jakaumasimulaattori;
import java.util.*;
import java.io.*;


public class TiedostonKasittelija {
// luokan tehtavana on tuottaa tiedostoja aineistosta
// oon vähän ns. kujalla tän kanssa    
    
    
    public void tulostaAineistoTiedostoon(File tiedosto, double[] aineisto) {
        
        FileWriter tulostin;
        
        try {
            tulostin = new FileWriter(tiedosto);
            for (int i = 0; i < aineisto.length; i++) {
                String tulostus = "" + aineisto[i];
                tulostin.append(tulostus + ";");
            }
            tulostin.close();
        }
        
        catch (IOException e) {
            System.out.println("Ei pysty");
        }
        
    }
}
