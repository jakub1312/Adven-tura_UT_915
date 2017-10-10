/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;


/*******************************************************************************
 * Instance třídy PrikazMluv představují prehovorenie so zadanou osobou v aktualnej miestnosti
 *
 * @author    Jakub Ismail
 * @version   zimný semester 2015/2016
 */
public class PrikazMluv implements IPrikaz
{
    private static final String NAZEV = "hovor";
    private HerniPlan plan;

    //== Datové atributy (statické i instancí)======================================

    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public PrikazMluv(HerniPlan plan)
    {
        this.plan = plan;

    }

    //== Nesoukromé metody (instancí i třídy) ======================================

    /**
     * Provadi prikaz "mluv". Mluvi se zadanou postavou
     *
     * @param jmeno     jmeno postavy, se kterou chceme mluvit
     * @return   zprava, kterou vypise hra hraci
     */
    @Override
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            // chyba meno posatavy
            return "S kým chceš rozprávať? Zadaj meno postavy.";
        }

        String jmenoPostavy = parametry[0];

        Prostor aktualniProstor = plan.getAktualniProstor();
        Postava postava = aktualniProstor.najdiPostavu(jmenoPostavy);
        if (postava == null){
            return "S kým chceš rozprávať? Takáto postava tu nie je."; 
        }

        return postava.getRec();
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

    //== Soukromé metody (instancí i třídy) ========================================

}
