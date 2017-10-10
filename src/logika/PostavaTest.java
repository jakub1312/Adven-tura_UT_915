/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída PostavaTest slouží ke komplexnímu otestování třídy ... 
 *
 * @author    Jakub Ismail
 * @version   zimný semester 2015/2016
 */
public class PostavaTest
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
    @Test
    /***************************************************************************
     * Tato metoda testuje či je osobe sprvne pridelene meno a rec
     */
    public void testPostavy()
    {
        Postava paniSmithova = new Postava("paniSmithova","proslov jak se patri");        
        assertEquals("paniSmithova", paniSmithova.getJmeno());
        assertEquals("proslov jak se patri", paniSmithova.getRec());
    }

    
}
