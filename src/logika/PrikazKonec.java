package logika;

/**
 *  Třída PrikazKonec implementuje pro hru příkaz konec.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Jarmila Pavlickova, Jakub Ismail
 *@version    pro školní rok 2015/2016
 *  
 */

class PrikazKonec implements IPrikaz {

    private static final String NAZEV = "koniec";

    private Hra hra;

    /**
     *  Konstruktor třídy
     *  
     *  @param hra odkaz na hru, která má být příkazem konec ukončena
     */    
    public PrikazKonec(Hra hra) {
        this.hra = hra;
    }

    /**
     * V případě, že příkaz má jen jedno slovo "konec" hra končí(volá se metoda setKonecHry(true))
     * jinak pokračuje např. při zadání "konec a".
     * 
     * @return zpráva, kterou vypíše hra hráči
     */

    @Override
    public String proved(String... parametry) {
        if (parametry.length > 0) {
            return "Ukončiť čo? Nechápem, prečo si zadal druhé slovo.";
        }
        else {
            hra.setKonecHry(true);
            return "Hra ukončená príkazom koniec";
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
