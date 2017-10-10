package logika;

import java.util.*;

/**
 * Trida Prostor - popisuje jednotlivé prostory (místnosti) hry
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * "Prostor" reprezentuje jedno místo (místnost, prostor, ..) ve scénáři hry.
 * Prostor může mít sousední prostory připojené přes východy. Pro každý východ
 * si prostor ukládá odkaz na sousedící prostor.
 *
 * @author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jakub Ismail
 * @version pro školní rok 2015/2016
 */
public class Prostor {

    private String nazev;
    private String popis;
    private Set<Prostor> vychody;   // obsahuje sousední místnosti

    private Map<String, Postava> seznamPostav;
    public Map<String, Vec> seznamVeci; // obsahuje seznam veci
    private Set <Vec> veci;
    private boolean zamcena;

    /**
     * Vytvoření prostoru se zadaným popisem, např. "kuchyň", "hala", "trávník
     * před domem"
     *
     * @param nazev nazev prostoru, jednoznačný identifikátor, jedno slovo nebo
     * víceslovný název bez mezer.
     * @param popis Popis prostoru.
     */
    public Prostor(String nazev, String popis, boolean zamcena) {
        this.nazev = nazev;
        this.popis = popis;
        this.zamcena = zamcena;

        veci = new HashSet<>();
        vychody = new HashSet<>();
        seznamVeci = new HashMap<>();
        seznamPostav = new HashMap<>();
    }

    /**
     * Definuje východ z prostoru (sousední/vedlejsi prostor). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední prostor uveden
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední místnosti).
     * Druhé zadání stejného prostoru tiše přepíše předchozí zadání (neobjeví se
     * žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     * @param vedlejsi prostor, který sousedi s aktualnim prostorem.
     *
     */
    public void setVychod(Prostor vedlejsi) {
        vychody.add(vedlejsi);
    }

    /**
     * Metoda zisťuje, či je miestnosť zamčená
     * @param zamcena
     */
    public boolean jeZamcena(){
        return zamcena;
    }

    /**
     * Metoda equals pro porovnání dvou prostorů. Překrývá se metoda equals ze
     * třídy Object. Dva prostory jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     *
     * Bližší popis metody equals je u třídy Object.
     *
     * @param o object, který se má porovnávat s aktuálním
     * @return hodnotu true, pokud má zadaný prostor stejný název, jinak false
     */  
    @Override
    public boolean equals(Object obj) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == obj) {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (!(obj instanceof Prostor)) {
            return false;    // pokud parametr není typu Prostor, vrátíme false
        }
        // přetypujeme parametr na typ Prostor 
        Prostor druhy = (Prostor) obj;

        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů. 
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.

        return (java.util.Objects.equals(this.nazev, druhy.nazev));       
    }

    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object
     */
    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }

    /**
     * Vrací název prostoru (byl zadán při vytváření prostoru jako parametr
     * konstruktoru)
     *
     * @return název prostoru
     */
    public String getNazev() {
        return nazev;       
    }

    /**
     * Vrací "dlouhý" popis prostoru, který může vypadat následovně: Jsi v
     * mistnosti/prostoru vstupni hala budovy VSE na Jiznim meste. vychody:
     * chodba bufet ucebna
     *
     * @return Dlouhý popis prostoru
     */
    public String dlouhyPopis() {
        return "Si v miestnosti " + popis + ".\n"
        + popisVychodu() +"\n";
        
    }

    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "vychody: hala ".
     *
     * @return Popis východů - názvů sousedních prostorů
     */
    private String popisVychodu() {
        String vracenyText = "vychody:";
        for (Prostor sousedni : vychody) {
            vracenyText += " " + sousedni.getNazev();
            if (sousedni.jeZamcena()){
                vracenyText+= "(zamknuté)";
            }
        }
        return vracenyText;
    }

    /**
     * Vrací prostor, který sousedí s aktuálním prostorem a jehož název je zadán
     * jako parametr. Pokud prostor s udaným jménem nesousedí s aktuálním
     * prostorem, vrací se hodnota null.
     *
     * @param nazevSouseda Jméno sousedního prostoru (východu)
     * @return Prostor, který se nachází za příslušným východem, nebo hodnota
     * null, pokud prostor zadaného jména není sousedem.
     */
    public Prostor vratSousedniProstor(String nazevSouseda) {
        if (nazevSouseda == null) {
            return null;
        }
        for (Prostor sousedni : vychody) {
            if (sousedni.getNazev().equals(nazevSouseda)) {
                return sousedni;
            }
        }
        return null;  // nenajdeny priestor
    }

    /**
     * Vrací kolekci obsahující prostory, se kterými tento prostor sousedí.
     * Takto získaný seznam sousedních prostor nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Prostor.
     *
     * @return Nemodifikovatelná kolekce prostorů (východů), se kterými tento
     * prostor sousedí.
     */
    public Collection<Prostor> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }

    
    
    /**
     * Metoda pro vlozeni veci do prostoru.
     *
     * @param neco vec vkladana do prostoru
     *
     */
    public void vlozVec(Vec neco) {
        seznamVeci.put(neco.getNazev(), neco);

    }

    /**
     * Metoda, ktera slouzi k zjistovani, zda se tomto prostoru hry nachazi vec zadaneho nazvu.
     *
     * @param nazev Nazev hledane veci
     * @return Vraci true  pokud vec jejiz nazev je zadanjako parametr je v aktualnim prostoru.
     * Pokud vec s udanym nazvem v aktualnim prostoru neni, vraci se false.
     */
    public boolean jeVecVProstoru(String nazev) {
        return seznamVeci.containsKey(nazev);
    }

    /**
     * metoda vybere věc ze seznamu věcí
     * 
     * @param název věci, která se má vyhledat
     * 
     * @return v případě, že byla věc vyhledána metoda vrací tuto věc, jinak vrací hodnotu null
     * 
     */
    public Vec vyberVec (String stringi) {
        Vec vec;
        for (Vec obj : veci) {
            if (stringi.equals(obj.getNazev())) {                
                vec=obj;

                return vec;
            } 

        }
        return null;
    }


    /**
     * Metoda zjistuje, zda se v mistnosti vyskytje vec uvedena v parametru
     * 
     * @param jmeno - nazev veci
     * @return true v pripade, ze vec v mistnosti je
     * 
     */
    public boolean obsahujeVec(String jmeno) {
        if (this.seznamVeci.containsKey(jmeno)) {
            return true;
        }
        return false;
    }    

    /**
     * Vraci textovy retezec, ktery popisuje veci, ktere se nachazeji v prostoru, oddelene carkou, napriklad:
     * "veci: jablko, zidle ".
     *
     * @return popis veci - nazvy veci v prostoru
     */
    public String popisVeci() {
        StringBuffer vystup = new StringBuffer();
        boolean prvniZnak = true;
        for (String nazev : seznamVeci.keySet()) {
            if(!prvniZnak) {
                vystup.append(',');
                vystup.append(' ');
            }
            prvniZnak = false;
            vystup.append(nazev);
        }
        return vystup.toString();
    }

    /**
     * Vraci vec, ktera se nachazi v prostoru, ne ve vecech.
     *
     * @param nazev     nazev hledane veci
     * @return vec       vrati nalezenou vec
     */
    public Vec vecVMistnosti(String nazev) {
        return seznamVeci.get(nazev);
    }

    
    /**
     * Vlozi postavu do prostoru.
     *
     * @ param postava      postava pridavana do prostoru
     *
     */
    public void vlozPostavu (Postava postava) {
        seznamPostav.put(postava.getJmeno(), postava);
    }

    /**
     * Metoda pro odebirani postav z prostoru.
     *
     * @param jmeno     jmeno odebirane postavy
     * @return postava      odebirana postava, null v pripade, ze postava neni v prostoru
     */
    public Postava odeberPostavu(String jmeno) {
        if (seznamPostav.containsKey(jmeno))
        {
            return seznamPostav.remove(jmeno);
        }
        else
        {
            return null;
        }
    }

    /**
     * Vraci jmeno postavy pokud se nachazi v prostoru
     *
     * @param jmeno   jmeno hledane postavy
     * @ return         nalezena postava
     */
    public Postava najdiPostavu (String jmeno) {
        return seznamPostav.get(jmeno);
    }

    /**
     * Vraci textovy retezec, ktery popisuje postavy, ktere se nachazeji v prostoru,oddelene carkou, napriklad:
     * "postavy: prasatko, krystufek robin".
     *
     * @return popis postav - nazvy postav v prostoru
     */
    public String popisPostav() {
        StringBuffer vystup = new StringBuffer();
        boolean prvniZnak = true;
        for (String jmeno : seznamPostav.keySet()) {
            if(!prvniZnak) {
                vystup.append(',');
                vystup.append(' ');
            }
            prvniZnak = false;
            vystup.append(jmeno);
        }
        return vystup.toString();
    }

    /**
     * Metoda vracia vec zo zoznamu
     */
    public Vec getVec(String nazevVeci) {
        return seznamVeci.get(nazevVeci);
    }
}
