package logika;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída SeznamPrikazuTest slouží ke komplexnímu otestování třídy  
 * SeznamPrikazu
 * 
 * @author    Luboš Pavlíček
 * @version   pro školní rok 2015/2016
 */
public class SeznamPrikazuTest
{
    private Hra hra;
    private PrikazKonec prKonec;
    private PrikazJdi prJdi;
    
    @Before
    public void setUp() {
        hra = new Hra();
        prKonec = new PrikazKonec(hra);
        prJdi = new PrikazJdi(hra.getHerniPlan());
    }

    @Test
    public void testVlozeniVybrani() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prKonec);
        seznPrikazu.vlozPrikaz(prJdi);
        assertEquals(prKonec, seznPrikazu.vratPrikaz("koniec"));
        assertEquals(prJdi, seznPrikazu.vratPrikaz("chod"));
        assertEquals(null, seznPrikazu.vratPrikaz("napoveda"));
    }
    
    @Test
    public void testJePlatnyPrikaz() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prKonec);
        seznPrikazu.vlozPrikaz(prJdi);
        assertEquals(true, seznPrikazu.jePlatnyPrikaz("koniec"));
        assertEquals(true, seznPrikazu.jePlatnyPrikaz("chod"));
        assertEquals(false, seznPrikazu.jePlatnyPrikaz("napoveda"));
        assertEquals(false, seznPrikazu.jePlatnyPrikaz("Koniec"));
    }
    
    @Test
    public void testNazvyPrikazu() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prKonec);
        seznPrikazu.vlozPrikaz(prJdi);
        String nazvy = seznPrikazu.vratNazvyPrikazu();
        assertEquals(true, nazvy.contains("koniec"));
        assertEquals(true, nazvy.contains("chod"));
        assertEquals(false, nazvy.contains("napoveda"));
        assertEquals(false, nazvy.contains("Koniec"));
    }
    
}
