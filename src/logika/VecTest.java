/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída VecTest slouží ke komplexnímu otestování třídy ... 
 *
 * @author    Jakub Ismail
 * @version   zimný semester 2015/2016
 */
public class VecTest
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

    /**
     * Metoda testuje tridu Vec a jeji metody
     * tj. zjisteni popisu vahy, a jmena a zmena vahy a jmena
     */
    @Test
    public void testNazvuVeci()
    {
        Vec sivyKufrik = new Vec("sivýKufrík",true,false);
        assertEquals("sivýKufrík", sivyKufrik.getNazev());
    }

    /***************************************************************************
     * Testujeme, zda se daná věc vloží
     * @param obsahuje 2 parametry: String jmeno, boolean přenositelná × nepřenositelná
     */
    @Test
    public void vlozenieVeci()
    {
        HerniPlan plan = new HerniPlan();
        Vec vec1 = new Vec("auto", true,true);
        Vec vec2 = new Vec("motorka", true,true);
        Vec vec3 = new Vec("strom", true,true);
        Vec vec4 = new Vec("dom", true,true);        
        assertEquals (true, plan.getBatoh().vlozVec(vec1));
        assertEquals (true, plan.getBatoh().vlozVec(vec2));
        assertEquals (true, !(plan.getBatoh().vlozVec(vec3))); 
        assertEquals (true, !(plan.getBatoh().vlozVec(vec4))); 
    }
    
    @Test
    /***************************************************************************
     * Testuje funkčnost přenositelnosti věci.
     */
    public void testPrenositelnost()
    {
        Vec vec1 = new Vec("disk", true,true);
        assertEquals(true, vec1.isPrenositelna());
        vec1.setPrenositelna(false);
        assertEquals(false, vec1.isPrenositelna());
    }
}
