package logika;

import gui.Mapa;
import java.util.*;
import utils.Observer;
import utils.Subject;
/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory, veci, postavy
 *  propojuje prostory vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jakub Ismail
 *@version    pro školní rok 2015/2016
 */
public class HerniPlan implements Subject {
    
    private Hra hra;
    private Prostor aktualniProstor;
    private Prostor viteznyProstor;
    private Batoh batoh;
    private Prostor prostorSProhrou;
    
    private List<Observer> listObserveru = new ArrayList<Observer>();
    
    /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan(Hra hra) {
        zalozProstoryHry();
        batoh = new Batoh();
        this.hra = hra;
    }

    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Taktiež sa vytvárajú veci, miesto kde budú a postavy.
     *  Jako výchozí aktuální prostor nastaví zastávka.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor chodba = new Prostor("chodba","chodba v hoteli",  false, 60, 170);
        Prostor izba10A = new Prostor("izba10A","izba10A a odtiaľto sa ti to začína", false, 50, 95);
        Prostor izba10B = new Prostor("izba10B","izba10B", false, 170,95);
        Prostor izba10C = new Prostor("izba10C","izba10C",false, 50,320);
        Prostor izba10D = new Prostor("izba10D","izba10D",false, 180,320);
        Prostor obyvacka10A = new Prostor("obyvacka10A", "obyvacka10A",true, 70,55);
        Prostor obyvacka10B = new Prostor("obyvacka10B", "obyvacka10B",true, 120,55);
        Prostor obyvacka10C = new Prostor("obyvacka10C", "obyvacka10C",false, 100,390);
        Prostor spalna10A = new Prostor("spalna10A", "spalna10A",false, 50,55);
        Prostor spalna10B = new Prostor("spalna10B", "spalna10B",false, 180,55);
        Prostor spalna10C = new Prostor("spalna10C", "spalna10C",true, 50,380);
        Prostor schody = new Prostor("schody", "schody",false, 160,170);
        Prostor vytah = new Prostor("vytah", "vytah",false, 160,270);
        Prostor lobby = new Prostor("lobby", "lobby",false, 230,170);
        Prostor strecha = new Prostor("strecha", "strecha",false, 230,270);

        // přiřazují se průchody mezi prostory (sousedící prostory)
        chodba.setVychod(izba10A);
        chodba.setVychod(izba10B);
        chodba.setVychod(izba10C);
        chodba.setVychod(izba10D);
        chodba.setVychod(schody);
        chodba.setVychod(vytah);
        izba10A.setVychod(chodba);
        izba10A.setVychod(spalna10A);
        izba10A.setVychod(obyvacka10A);
        izba10B.setVychod(chodba);
        izba10B.setVychod(spalna10B);
        izba10B.setVychod(obyvacka10B);
        izba10C.setVychod(chodba);
        izba10C.setVychod(spalna10C);
        izba10C.setVychod(obyvacka10C);
        izba10D.setVychod(chodba);
        schody.setVychod(chodba);
        schody.setVychod(lobby);
        schody.setVychod(strecha);
        vytah.setVychod(chodba);
        vytah.setVychod(lobby);
        vytah.setVychod(strecha);
        spalna10A.setVychod(izba10A);
        spalna10B.setVychod(izba10B);
        spalna10C.setVychod(izba10C);
        obyvacka10A.setVychod(izba10A);
        obyvacka10B.setVychod(izba10B);
        obyvacka10C.setVychod(izba10C);
        strecha.setVychod(schody);
        strecha.setVychod(vytah);
        
        // Vytvárajú sa postavy a vkladajú do jednotlivých priestorov
        Postava paniSmithova = new Postava("PaniSmithová", "Vy: Pani Smithová. Máte ten kufrík??!\n"+
            " Pani Smithová: Ah, agent 47... idete rovno k veci, to sa mi páči. Dám vám ten kufrík,\n"+
            " ale vy mi musíte tiež niečo dať. Ten disk.\n");
        izba10D.vlozPostavu(paniSmithova);
        
        Postava pomocnik = new Postava("Pomocnik", "Vy: Hej, zistil si už presne kde je ten disk?\n"+
            " Pomocník: Zistil som len, že je v spálni. To, že ktorej izby, to už neviem.\n"+
            " Vy: Tak skúsim prejsť každú izbu.\n"+
            " Pomocník: Dobre, ale pohni si! Vieš aká je Smithová netrpezlivá!!!\n");
        chodba.vlozPostavu(pomocnik);
        
        //Vytvárajú sa veci a vkladajú do jednotlivých miestností
        Vec sivyKufrik = new Vec("sivýKufrík",true, false,"sivyKufrik.png");
        spalna10A.vlozVec(sivyKufrik);
        Vec postel1 = new Vec("posteľ",false,false,"postel1.png");
        spalna10A.vlozVec(postel1);
        Vec postel2 = new Vec("posteľ",false,false,"postel2.png");
        spalna10B.vlozVec(postel2);
        Vec hnedyKufrik = new Vec("hnedýKufrík",true,false,"hnedyKufrik.png");
        obyvacka10C.vlozVec(hnedyKufrik);
        Vec ciernyKufrik = new Vec("čiernyKufrík",true,true,"ciernyKufrik.png");
        izba10D.vlozVec(ciernyKufrik);
        Vec disk = new Vec("disk",true,true,"disk.png");
        spalna10B.vlozVec(disk);
        Vec stolik = new Vec("stolík", false,false,"stolik.png");
        obyvacka10C.vlozVec(stolik);
        Vec skrina = new Vec("skriňa",false,false,"skrina.png");
        spalna10B.vlozVec(skrina);

        aktualniProstor = izba10A;  // hra začíná v izbe 10A    
        viteznyProstor = strecha;   // hra končí výhrou na streche
        prostorSProhrou = lobby;    // hra končí prehrou v lobby
    }

    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */

    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }

    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
        aktualniProstor = prostor;
        notifyObservers();
    }

    /**
     *  Metoda ktorá určuje či aktualny priestor je vitaznym, ale len za predpokladu,
     *  že je v batohu čierny kufrík
     */
    public boolean jeVyhra(){
        if (batoh.obsahujeVec("čiernyKufrík")){
            return (aktualniProstor.equals(viteznyProstor));
        }
        else {
            return false;
        }
    }
    /**
     * Metóda vracia odkaz na aktuálny batoh.
     * @return batoh
     */
    public Batoh getBatoh() {
        return batoh;
    }
    /**
     *  Metoda ktorá určuje či je aktualny priestor s prehrou.
     */
    public boolean jeProhra() {

        return (aktualniProstor.equals(prostorSProhrou));
    }

    
    
    
    /**
     * Abstraktné metody
     * @param observer
     */
    /*
    * Metoda, ktorá pridá pozorovateľa do zoznamu observerov
     */
    @Override
    public void registerObserver(Observer observer) {
        listObserveru.add(observer);
    }
    /*
    * Metoda, ktorá odeberie pozorovateľa zo zoznamu observerov
     */
    @Override
    public void removeObserver(Observer observer) {
        listObserveru.remove(observer);
    }
    /*
    * Metoda, ktorá upozorní pozorovateľa a aktualizuje zoznamu observerov batohu
     */
    @Override
    public void notifyObservers() {
        for (utils.Observer listObserveruItem : listObserveru) {
            listObserveruItem.update();
        }
    }
    
    
    public Hra getHra() {
        return hra;
    }

   

}
