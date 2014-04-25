package ohha.jakaumasimulaattori;
import java.util.*;
import java.io.*;

     /**
     * Luokan tehtävä on tulostaa parametrina saamaansa tiedostoon parametrina saamansa
     * aineisto
     */
    public class TiedostonKasittelija {
  
  
    
        /**
         * Metodi tulostaa tiedostoon saamansa aineiston.
         * @param tiedosto on tiedosto johon aineisto tallennetaan
         * @param aineisto on otosgeneraattorin luoma aineisto
         * @throws IOException mikäli tiedosto ei ole kelvollinen, tulostus metodi heittää
         * input-output-poikkeuksen.
         */  
    public void tulostaAineistoTiedostoonCSV(File tiedosto, TilastoAineisto tilastoaineisto) throws IOException {
        
        FileWriter tulostin;
        
            tulostin = new FileWriter(tiedosto);
            for (int i = 0; i < tilastoaineisto.getAineisto().length; i++) {
                String tulostus = "" + tilastoaineisto.getAineisto()[i] + "\n";
                tulostin.write(tulostus);
            }
            tulostin.close();
        
    }
}
