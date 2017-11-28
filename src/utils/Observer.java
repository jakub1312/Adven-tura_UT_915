/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 * Rozhranie, ktoré implementuje mapa s krátkým popiskom
 *
 * @author Jakub Ismail
 * @version pro školní rok 2017/20178
 */
public interface Observer {

    /**
     * Metoda, ve které proběhne aktualizace pozorovatele, je volána
     * prostřednictvím metody upozorniPozorovatele z rozhraní Subjekt
     *
     */
    public void update();
}
