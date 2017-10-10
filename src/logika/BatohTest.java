/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída BatohTest slouží ke komplexnímu otestování třídy ... 
 *
 * @author    Jakub Ismail
 * @version   zimný semester 2015/2016
 */
public class BatohTest
{
    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------
    

    /***************************************************************************
     * Inicializace předcházející spuštění každého testu a připravující tzv.
     * přípravek (fixture), což je sada objektů, s nimiž budou testy pracovat.
     */
    @Before
    public void setUp()
    {
        
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každého testu.
     */
    @After
    public void tearDown()
    {
    }
    //== VLASTNÍ TESTY =========================================================
    //
    //     /********************************************************************
    //      *
    //      */
    //     @Test
    //     public void testXxx()
    //     {
    //     }
    /***************************************************************************
     * V tomto teste sa zistuje či sa daju veci vlozit do batohu vzhladom na jeho velkost
     * Do uvahy sa berie aj či je prenositelna alebo nie
     */
    @Test
    public void vlozeniBatoh()
    {
        Batoh batoh = new Batoh();
        
        Vec vec1 = new Vec ("auto",true,true);
        Vec vec2 = new Vec ("motorka",true,true);
        Vec vec3 = new Vec ("strom",false,false);
        Vec vec4 = new Vec ("dom",true,true);
        
        assertEquals (true, batoh.isMisto());
        batoh.vlozVec(vec1);
        
        assertEquals (true, batoh.isMisto()  );
        batoh.vlozVec(vec2);
        
         assertEquals (false, batoh.isMisto()  );
        batoh.vlozVec(vec3);  
        
        assertEquals (true, !(batoh.isMisto() ) );
        batoh.vlozVec(vec4);
    }
}
