/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Instance třídy PrikazOdhod představují odhodenie zadanej veci z batohu
 *
 * @author    Jakub Ismail
 * @version   zimný semester 2015/2016
 */
public class PrikazOdhod implements IPrikaz
{
    private static final String NAZEV = "odhod";
    private HerniPlan plan;
    private Batoh batoh;
    //== Datové atributy (statické i instancí)======================================

    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public PrikazOdhod(HerniPlan plan)
    {
        this.plan = plan;
        this.batoh = plan.getBatoh();
    }

    /**
     * Provadi prikaz "odhod". Odhodi zadanou vec z batohu do daneho prostoru.
     *
     * @param parametry     vec, kterou chceme odhodit
     * @return   zprava, kterou vypise hra hraci
     */
    @Override
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybi druhe slovo (nazev odhazovane veci), tak ....
            return "Čo chceš odhodiť? Zadaj názov veci.";
        }

        String nazevVeci = parametry[0];

        Prostor aktualniProstor = plan.getAktualniProstor();
        Vec vec = batoh.getVec(nazevVeci);

        if (vec == null) {
            return nazevVeci + " pri sebe nemáš";
        }
        else {
            plan.getBatoh().odeberVec(nazevVeci);
            aktualniProstor.vlozVec(vec);
            return "Vec " + nazevVeci + " si odhodil ale bude v tejto miestnosti.";
        }
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
    
    //== Nesoukromé metody (instancí i třídy) ======================================


    //== Soukromé metody (instancí i třídy) ========================================

}
