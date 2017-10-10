/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;
import java.util.*;

/*******************************************************************************
 * Instance třídy Vec představují nastavenie prenositelnosti, nakupenia, vymenenia veci
 *
 * @author    Jakub Ismail
 * @version   zimný semester 2015/2016
 */
public class Vec
{
    //== Datové atributy (statické i instancí)======================================
    private String nazev;
    private boolean prenositelna;
    private boolean vymenitelna;
   
    
    
   
    
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public Vec(String nazev, boolean prenositelna, boolean vymenitelna)
    {
        this.nazev = nazev;
        this.prenositelna = prenositelna;
        this.vymenitelna = vymenitelna;
        
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    public String getNazev(){ // vracia názov
        return nazev;
    }
    
    public boolean isPrenositelna(){ // zisťuje, či je prenositeľná
        return prenositelna;
    }
    
    public boolean isVymenitelna(){ // zisťuje, či je vymenitelna
        return vymenitelna;
    }
    
    public void setPrenositelna(boolean vezmi) { // zisťuje, či už bola prenesena
        prenositelna=vezmi;
    }
    
    /**
     * Prekryti metody zdedene ze tridy object. 2 veci se rovnaji, pokud se rovnaji jejich nazvy.
     *
     * @param druhy objekt, se kterym porovnavame nasi vec
     */
    @Override
    public boolean equals(Object obj) {
        // porovnavame zda se nejedna o dva odkazy na stejnou instanci
        if (this == obj) {
            return true;
        }
        // porovnavame jakeho typu je parametr
        if (!(obj instanceof Vec)) {
            return false;    // pokud parametr neni typu Vec, vratime false
        }
        // pretypujeme parametr
        Vec druha = (Vec) obj;

        //metoda equals tridy java.util.Objects porovna hodnoty obou nazvu.
        //Vrati true pro stejne nazvy a i v pripade, ze jsou oba nazvy null,
        //jinak vrati false.

        return java.util.Objects.equals(this.nazev, druha.nazev);
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
    //== Soukromé metody (instancí i třídy) ========================================

}
