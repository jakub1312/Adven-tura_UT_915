package logika;

/**
 *  Třída PrikazJdi implementuje pro hru příkaz chod.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Jarmila Pavlickova, Luboš Pavlíček, Jakub Ismail
 *@version    pro školní rok 2015/2016
 */
class PrikazJdi implements IPrikaz {
    private static final String NAZEV = "chod";
    private HerniPlan plan;
    

    /**
     *  Konstruktor třídy
     *  
     *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
     */    
    public PrikazJdi(HerniPlan plan) {
        this.plan = plan;
        
    }

    @Override
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "Kam chceš ísť? Musíš zadať názov východu";
        }

        String smer = parametry[0];

        // zkoušíme přejít do sousedního prostoru
        Prostor sousedniProstor = plan.getAktualniProstor().vratSousedniProstor(smer);

        if (sousedniProstor == null) {
            return "Tam sa odtiaľto ísť nedá!";
        }
        else {
            if (sousedniProstor.jeZamcena()) {
                return "dvere do miestnosti "+sousedniProstor.getNazev()
                     +" sú zamknuté";
            }
            plan.setAktualniProstor(sousedniProstor);
            return sousedniProstor.dlouhyPopis();
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
