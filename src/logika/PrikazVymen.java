/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

/*******************************************************************************
 * Instance třídy PrikazVymen představují vymenenie si veci ktoru chcete zato čo chce dana osoba
 *
 * @author    Jakub Ismail
 * @version   zimný semester 2015/2016
 */
public class PrikazVymen implements IPrikaz    {
    private static final String NAZEV = "vymen";
    private HerniPlan plan;
    private Batoh batoh;

    public PrikazVymen(HerniPlan plan) {
        this.plan = plan;
        this.batoh = plan.getBatoh();

    }
    // kontroluje či nechyba meno veci
    @Override
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            return "Čo mám vymeniť? Musíš zadat názov veci";
        }

        String jmenoVeci = parametry[0];
        // vyberieme vec
        Vec vec = plan.getAktualniProstor().getVec(jmenoVeci);
        if (vec == null) {
            return "Takú vec pri sebe nemáš!";
        }
        else {
            if ((vec.isVymenitelna()) ) {
                if(batoh.obsahujeVec("disk")){
                    // vloži vec do batohu 

                    vec.setPrenositelna(true);

                    batoh.odeberVec("disk");
                    batoh.vlozVec(vec);
                    return "Pani Smithová: Ďakujem Vám agent 47. Náš obchod je týmto uzavretý. Dovidenia\n "+
                    "Vymenil si disk za " + jmenoVeci;
                }
                else{
                    return "Nemáš nič čo by sa dalo vymeniť.";
                }
            }
            else {

                {
                    return "Táto vec sa nedá vymieňať!";
                } 

            }

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

