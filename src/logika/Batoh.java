/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

import java.util.*;
/*******************************************************************************
 * Instance třídy Batoh představují zoznam veci, ktore sa v nom nachadzaju, maximalny počet veci 
 * ktory v batohu može byt
 *
 * @author    Jakub Ismail
 * @version   zimný semester 2015/2016
 */
public class Batoh
{
    //== Datové atributy (statické i instancí)======================================
    private Map<String, Vec> seznamVeci;
    private static final int MAX = 2;
    private Set<Vec> bag;
    /**
     * //Kontstruktor triedy, ktory vytvori HashMap pre obsah batohu
     */    
    public Batoh() {
        this.seznamVeci = new HashMap<String, Vec>();
    }

    /**
     * Metoda pre odoberanie veci z batohu
     *
     * @param nazev  nazov odeberanej veci
     * @ return        true bola odobrana false nebolo odobrana
     */
    public boolean odeberVec(String nazev) {
        boolean odebrana = false;
        if(seznamVeci.containsKey(nazev) && seznamVeci.get(nazev).isPrenositelna()) {
            seznamVeci.remove(nazev);
            odebrana = true;
        }
        return odebrana;
    }
    
    /**
     * Ak je v batohu ešte miesto tak metoda tam pridá danú vec
     *
     * @param neco - věc, který se má přidat do batohu
     * @return true pokud se podaří věc přidat
     */
    public boolean vlozVec(Vec neco){
        if (isMisto()){
            seznamVeci.put(neco.getNazev(),neco);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Metoda zistuje či je dana vec v batohu
     * 
     * @param jmeno - nazov veci
     * @return true v pripade, ze vec v batohu je
     * 
     */
    public boolean obsahujeVec(String meno) {
        if (this.seznamVeci.containsKey(meno)) {
            return true;
        }
        return false;
    }

    public Vec getVec(String jmeno){
        if (this.seznamVeci.containsKey(jmeno)){
            return seznamVeci.get(jmeno);
        }
        else { return null;
        }
    }

    /**
     * Metoda zjišťujě kolik věcí je v batohu
     *
     * @return true pokud se může přidat ještě další věc
     */
    public boolean isMisto() {
        return (seznamVeci.size() < MAX);
    }

    /**
     * Metoda, pre vybrani veci zo zoznamu
     * 
     * @param stringi  názov veci, ktoru chceme vybrat
     * 
     * @return ked tam je tak vracia jej nazov ak nie tak null.
     */
    public Vec vyberVec (String stringi) {
        Vec vec;
        for (Vec o : bag) {
            if (stringi.equals(o.getNazev())) {                
                vec=o;

                return vec;
            } 

        }
        return null;
    }

        /**
     * Metoda pro zjištění obsahu batohu
     *
     * @return seznam věcí v batohu
     */
    public String getVeci() {
        String text = "";
        for (String nazev : seznamVeci.keySet()) {
            text +=  nazev + " ";
        }
        return text;
    }

    public Map<String, Vec> getSeznamVeci() {
        return seznamVeci;
    }
}