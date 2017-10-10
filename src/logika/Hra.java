package logika;

/**
 *  Třída Hra - třída představující logiku adventury.
 * 
 *  Toto je hlavní třída  logiky aplikace.  Tato třída vytváří instanci třídy HerniPlan, která inicializuje mistnosti hry
 *  a vytváří seznam platných příkazů a instance tříd provádějící jednotlivé příkazy.
 *  Vypisuje uvítací a ukončovací text hry.
 *  Také vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jakub Ismail
 *@version    pro školní rok 2015/2016
 */

public class Hra implements IHra {
    private SeznamPrikazu platnePrikazy;    // obsahuje seznam přípustných příkazů
    private HerniPlan herniPlan;
    private boolean konecHry = false;
    

    /**
     *  Vytváří hru a inicializuje místnosti (prostřednictvím třídy HerniPlan) a seznam platných příkazů.
     */
    public Hra() {
        herniPlan = new HerniPlan();
        
        platnePrikazy = new SeznamPrikazu();
        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));
        platnePrikazy.vlozPrikaz(new PrikazJdi(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazSeber(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));
        platnePrikazy.vlozPrikaz(new PrikazBatoh(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazOdhod(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazMluv(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazRozhledniSe(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazVymen(herniPlan));
        
        
        
      
       
    }

    /**
     *  Vrátí úvodní zprávu pro hráče.
     */
     public String vratUvitani() {
        return "Zdravím Vás agent 47!\n" +
               "Potrebujete sa dostať čo najrýchlejšie a nepozorovane z apartmánu Forresters Light na Downovej avenue.\n" +
               "Budova má 20 poschodí a práve sa nachádzate presne uprostred, na 10. poschodí.\n" +
               "Máte na výber niekoľko možných ciest ale vecte, že potrebujete získať čierny kufrík,\n" +
               "ktorý je síce na vašom poschodí, ale neviete v ktorej miestnosti.\n" + 
               "Nájdite kufrík a až potom sa snažte dostať von z budovy.\n" +
               "Pokiaľ sa dostanete z budovy bez kufríka, tak ste nesplnili misiu.\n" +
               "Pokiaľ vás odhalia pri úniku alebo hľadaní kufríka, je po Vás.\n" +
               "\n" +
               "Veľa šťastia!\n" +
               herniPlan.getAktualniProstor().dlouhyPopis();
    }
    
    /**
     *  Vrátí závěrečnou zprávu pro hráče.
     */
    public String vratEpilog() {
        return "Ďakujem, že si si zahral/a. Nech ťa sila sprevádza.";
    }
    
    /** 
     * Vrací true, pokud hra skončila.
     */
     public boolean konecHry() {
        return konecHry;
    }

    /**
     *  Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     *  Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     *  Pokud ano spustí samotné provádění příkazu.
     *
     *@param  radek  text, který zadal uživatel jako příkaz do hry.
     *@return          vrací se řetězec, který se má vypsat na obrazovku
     */
     public String zpracujPrikaz(String radek) {
        String [] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String []parametry = new String[slova.length-1];
        for(int i=0 ;i<parametry.length;i++){
            parametry[i]= slova[i+1];   
        }
        String textKVypsani=" .... ";
        if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.proved(parametry);
            if (herniPlan.jeVyhra()) {
                konecHry = true;
                textKVypsani+= "Výborne agent 47! Splnili ste svoju misiu.";
            }
            if(herniPlan.jeProhra()) {
                konecHry = true;
                textKVypsani+= "Ste mŕtvy. Rozstrielalo vás aspoň 50 agentov na kúsky...";
            }
        }
        else {
            textKVypsani="Neviem čo tým chceš povedať. Tento príkaz nepoznám ";
        }
        return textKVypsani;
    }
    
    
     /**
     *  Nastaví, že je konec hry, metodu využívá třída PrikazKonec,
     *  mohou ji použít i další implementace rozhraní Prikaz.
     *  
     *  @param  konecHry  hodnota false= konec hry, true = hra pokračuje
     */
    void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }
    
     /**
     *  Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     *  kde se jejím prostřednictvím získává aktualní místnost hry.
     *  
     *  @return     odkaz na herní plán
     */
     public HerniPlan getHerniPlan(){
        return herniPlan;
     }
    
}

