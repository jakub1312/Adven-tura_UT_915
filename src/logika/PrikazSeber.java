/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

/*******************************************************************************
 * Instance třídy PrikazSeber představují prikaz na zobranie určitej veci pričom musi splnovať podmienky
 *
 * @author    Jakub Ismail
 * @version   zimný semester 2015/2016
 */
public class PrikazSeber implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================

    //== Konstruktory a tovární metody =============================================

    private static final String NAZEV = "zober";
    private HerniPlan plan;
    private Batoh batoh;

    /**
     *  Konstruktor třídy
     *  
     *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
     */    
    public PrikazSeber(HerniPlan plan) {
        this.plan = plan;
        this.batoh = plan.getBatoh();
    }

    /**
     *  Provádí příkaz "seber". Zkouší se vyjít do zadaného prostoru. Pokud prostor
     *  existuje, vstoupí se do nového prostoru. Pokud zadaný sousední prostor
     *  (východ) není, vypíše se chybové hlášení.
     *
     *@param parametry - jako  parametr obsahuje jméno prostoru (východu),
     *                         do kterého se má jít.
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            // chýba názov veci
            return "Čo chceš zobrať? Musíš zadať názov vecí";
        }

        /*String jmenoVeci = parametry[0];
        // vyberieme vec
        boolean existuje = plan.getAktualniProstor().obsahujeVec(jmenoVeci);
        if (existuje) {
            Vec vec;
            vec = plan.getAktualniProstor().getVec(jmenoVeci);
            if ((vec.isPrenositelna())){    // vložíme vec do batohu 
                if (batoh.isMisto()) {
                    plan.getAktualniProstor().vyberVec(jmenoVeci);
                    if (batoh.vlozVec(vec)) {
                        return "Zobral si " + jmenoVeci; // uspesna akcia
                    } else {
                        return "Nepodarilo sa zobrať predmet"; // neuspesna akcia
                    }
                } else {
                    return "Máš plné ruky, už si nevieš nič zobrať. Najprv musíš niečo vyhodiť";  // zisti plny batoh
                }
            } else {
                return "Viem, že si riadny chlap, ale toto proste neunesieš";   // vec nemoze byt sebrana
            }
        }
        else {
            return "Takáto vec tu nie je!";
        }

    }*/
     String nazevSbiraneVeci = parametry[0];
     Prostor aktualniProstor = plan.getAktualniProstor();
     Vec sbirana = aktualniProstor.odeberVec(nazevSbiraneVeci);
        
        if (sbirana == null) {
            return "To tu není!";
        }
        else {
            
        
        //Dá se věc sebrat?
        if(sbirana.isPrenositelna()){         
            //je v batohu místo?
            if(batoh.vlozVec(sbirana) == true){
                    //věc se dá sebrat a v batohu je místo
                    batoh.vlozVec(sbirana);
                    plan.notifyObservers();
                    return "Zobral si " + sbirana.getNazev() ; 
            }
         }
        else{
                //věc se nedá sebrat
                aktualniProstor.vlozVec(sbirana);
                return "To je moc velký!";
            }
        //není v něm místo
        aktualniProstor.vlozVec(sbirana);
        return "Máš plný batoh";
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
