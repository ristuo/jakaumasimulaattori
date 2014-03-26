package ohha.jakaumasimulaattori;
import java.util.*;
import java.io.*;


public class TiedostonKasittelija {
// luokan tehtavana on tuottaa tiedostoja aineistosta
// oon vähän ns. kujalla tän kanssa    
    
    
    public void tulostaAineistoTiedostoonCSV(File tiedosto, double[] aineisto) throws IOException {
        
        FileWriter tulostin;
        
            tulostin = new FileWriter(tiedosto);
            for (int i = 0; i < aineisto.length; i++) {
                String tulostus = "" + aineisto[i] + ";";
                tulostin.write(tulostus);
            }
            tulostin.close();
        
    }
}
