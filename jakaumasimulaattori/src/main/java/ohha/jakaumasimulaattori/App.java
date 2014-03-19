package ohha.jakaumasimulaattori;


public class App 
{
    
   
    
    public static void main( String[] args )
    {
        TunnuslukuLaskuri tuksu = new TunnuslukuLaskuri();
        Otosgeneraattori otos = new Otosgeneraattori();
        Satunnaisgeneraattori sat = new Satunnaisgeneraattori();
//        for (int i = 0; i < 100; i++) {
//            System.out.println(sat.generoiNormaali(5.5,1));
//        }
//        
        System.out.println(tuksu.laskeKeskiarvo(otos.poissonAineisto(2700,3.5)));
        
        
//        Satunnaisgeneraattori satunnaisgeneraattori = new Satunnaisgeneraattori();
//        System.out.println(satunnaisgeneraattori.generoiNormaali(5.5,1));
    }
}
