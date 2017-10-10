/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

/*******************************************************************************
 * Instance třídy PrikazBatoh představují batoh a vypisanie veci ktore sa v nom nachadzaju
 *
 * @author    Jakub Ismail
 * @version   zimný semester 2015/2016
 */
public class PrikazBatoh implements IPrikaz
{
    private static final String NAZEV = "batoh";
    private HerniPlan plan;

    /**
     *  Konstruktor třídy
     *  
     *  @param plan herní plán
     */    
    public PrikazBatoh(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     *  Provádí příkaz "batoh". Vypíše všechny věci, které jsou v batohu.
     *
     *@param parametry - jako  parametr může být cokoliv
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String proved(String... parametry) {
        if (plan.getBatoh().getVeci().equals("")) {
            return "Zatiaľ pri sebe nič nemáš";
        }
        else {
            return "Máš pri sebe tieto veci: " + plan.getBatoh().getVeci(); 
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
}
