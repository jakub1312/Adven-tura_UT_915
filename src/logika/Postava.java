/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Instance třídy Postava představují priradenie mena a reci postave
 *
 * @author    Jakub Ismail
 * @version   zimný semester 2015/2016
 */
public class Postava
{
    private String jmeno;
    private String rec;
    
    /***************************************************************************
     *  Konstruktor pre postavu meno a rec
     */
    public Postava(String jmeno, String rec)
    {
        this.jmeno = jmeno;
        this.rec = rec;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================

    /**
     * Metoda vracia meno postavy
     *
     * @return jmeno
     */
    public String getJmeno()
    {
        return jmeno;
    }

    /**
     * Metoda vracia rec postavy
     *
     * @return rec
     */
    public String getRec()
    {
        return rec;
    }
    

    //== Soukromé metody (instancí i třídy) ========================================

}
