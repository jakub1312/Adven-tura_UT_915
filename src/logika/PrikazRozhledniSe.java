/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

/*******************************************************************************
 * Instance třídy PrikazRozhledniSe představují vypisanie veci a postav v danej miestnosti
 *
 * @author    Jakub Ismail
 * @version   zimný semester 2015/2016
 */
public class PrikazRozhledniSe implements IPrikaz
{
    private static final String NAZEV = "rozhliadniSa";
    private HerniPlan plan;

    /**
     * Konstruktor.
     *
     * @param plan
     */
    public PrikazRozhledniSe(HerniPlan plan)
    {
        this.plan = plan;
    }

    /**
     * Provadi prikaz "rozhledniSe". Vypise seznam vsech veci, postav a vychodu v prostoru
     *
     * @param parametry     parametr muze byt cokoliv
     * @return   zprava, kterou vypise hra hraci
     */
    @Override
    public String proved(String... parametry) {
        return 
        "veci: " + plan.getAktualniProstor().popisVeci() + "\n"
        + "postavy: " + plan.getAktualniProstor().popisPostav();
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
