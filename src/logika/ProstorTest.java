package logika;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída ProstorTest slouží ke komplexnímu otestování
 * třídy Prostor
 *
 * @author    Jarmila Pavlíčková, Jakub Ismail
 * @version   pro skolní rok 2015/2016
 */
public class ProstorTest
{
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
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /**
     * Testuje, zda jsou správně nastaveny průchody mezi prostory hry. Prostory
     * nemusí odpovídat vlastní hře, 
     */
    @Test
    public  void testLzeProjit() {		
        Prostor prostor1 = new Prostor("izba10A", "", false);
        Prostor prostor2 = new Prostor("chodba", "chodba v hoteli", false);
        prostor1.setVychod(prostor2);
        prostor2.setVychod(prostor1);
        assertEquals(prostor2, prostor1.vratSousedniProstor("chodba"));
        assertEquals(null, prostor2.vratSousedniProstor("schody"));
    }
    
    @Test
    /**
     * Testuje metodu vlozVec
     */
    public void testVlozVec()
    {
        Vec vec1 = new Vec("disk", true, true);
        Vec vec2 = new Vec("čiernyKufrík", true, true);
        Prostor prostor1 = new Prostor("chodba", "", false);
        prostor1.vlozVec(vec1);
        prostor1.vlozVec(vec2);
        assertEquals(true, prostor1.obsahujeVec("disk"));
        assertEquals(true, prostor1.obsahujeVec("čiernyKufrík"));
        assertEquals(false, prostor1.obsahujeVec("sivýKufrík"));
    }
}

