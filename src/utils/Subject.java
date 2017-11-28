/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author Jakub
 */
public interface Subject {
    /**
     * Metoda slouží k zaregistrování pozorovatele, musí to být instance třídy,
     * která implementuje rozhraní Observer.
     *
     * @param pozorovatel registrovaný objekt
     */
    public void registerObserver(Observer observer);

    /**
     * Metoda slouží k zrušení registrace pozorovatele, musí to být instance
     * třídy, která implementuje rozhraní Observer.
     *
     * @param pozorovatel objekt, který již nechce být informován o změnách
     */
    
    public void removeObserver(Observer observer);
    
    /**
     * Metoda, která se volá vždy, když dojde ke změně této instance. Upozorní
     * všechny pozorovatele, že došlo ke změně tak, že zavolá jejich metodu
     * aktualizuj.
     */
    public void notifyObservers();
    
}
