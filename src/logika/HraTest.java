package logika;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Hra
 *
 * @author    Jarmila Pavlíčková, Jakub Ismail
 * @version  pro školní rok 2015/2016
 */
public class HraTest {
    private Hra hra1;

    //== Datové atributy (statické i instancí)======================================

    //== Konstruktory a tovární metody =============================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    //== Příprava a úklid přípravku ================================================

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {
        hra1 = new Hra();
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /***************************************************************************
     * testuje koniec hry pri presúvaní sa miestnostťami a následne aj po príakze konec
     * 
     */
    @Test
    public void testPrubehHry() {
        assertEquals("izba10A", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("chod chodba");
        assertEquals(false, hra1.konecHry());
        assertEquals("chodba", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("chod izba10A");
        assertEquals(false, hra1.konecHry());
        assertEquals("izba10A", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("koniec");
        assertEquals(true, hra1.konecHry());
    }
    
    /**
     * Testuje lobby ako priestor s prehrou
     */
    @Test
    public void testProhra() {
        
        hra1.zpracujPrikaz("chod lobby");
        assertEquals(false, hra1.konecHry());
    }
    
    @Test
    public void testKoniec() {
        
        hra1.zpracujPrikaz("koniec");
        assertEquals(true, hra1.konecHry());
    }
    
 
    
}
