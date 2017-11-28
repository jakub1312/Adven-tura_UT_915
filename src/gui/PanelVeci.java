/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import logika.HerniPlan;
import logika.Hra;
import logika.IHra;
import logika.Vec;
import logika.Prostor;
import utils.Observer;

//autor Jakub Ismail

    /*
    * Konstruktor pre panel vecí v priestore.
    */
public class PanelVeci extends ListView implements Observer{
    
    private IHra hra;
    private HerniPlan plan;
    ListView<Object> list;
    private ObservableList<Object> data = FXCollections.observableArrayList();
    private TextArea centralText;

    
    public PanelVeci(HerniPlan plan, TextArea text) {
       this.plan = plan;
       plan.registerObserver(this);
       centralText = text;
       init();
    }

    public PanelVeci(IHra hra) {
        this.hra = hra;
    }

    /*
    * Metoda vytvorí list pre veci v prostoru a pridáva funckiu dvojkliku.
    */
    private void init() {
        list = new ListView<>();
        data = FXCollections.observableArrayList();
        list.setItems(data);
        list.setPrefWidth(125);
        
        list.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent click){
                if (click.getClickCount() == 2){
                    int index = list.getSelectionModel().getSelectedIndex();
                    Map<String, Vec> seznam;
                    seznam = plan.getAktualniProstor().getSeznamVeci();
                    String nazev = "";
                    int pomocna = 0;
                    for (String x : seznam.keySet()) {
                       if(pomocna == index)
                       {
                           nazev = x;
                       }
                       pomocna++;
                    }
                    String vstupniPrikaz = "zober "+nazev;
                    String odpovedHry = plan.getHra().zpracujPrikaz("zober "+nazev);
                
                    centralText.appendText("\n" + vstupniPrikaz + "\n");
                    centralText.appendText("\n" + odpovedHry + "\n");
               
                    plan.notifyObservers();
                }
            }
        });
        update();
    }
    /*
    * Metoda vrací list.
    */
    public ListView<Object> getList() {
        return list;
    }
    /*
    * Metoda aktualizuje list veci v priestore. Zobrazuje obrázky veci, které jsou v prostoru.
    */
    @Override 
    public void update() {        
        Map<String, Vec> seznam;
        seznam = plan.getAktualniProstor().getSeznamVeci();
        data.clear();
        for (String y : seznam.keySet()) {
        Vec pomocna = seznam.get(y);
        ImageView obrazek = new ImageView(new Image(main.Main.class.getResourceAsStream("/zdroje/"+pomocna.getObrazek()), 80, 80, false, false));
        data.add(obrazek);
        }
    }
    /**
     * Metoda zaregistruje pozorovatele k hernému plánu pri spustení nové hry.
     * @param plan
     */
    public void nastaveniHernihoPlanu (HerniPlan plan){
        this.plan = plan;
        plan.registerObserver(this);
        this.update();
    }
     /**
     * Metoda preregistrujuca observer
     *
     * @param hra parametr obsahující odkaz na hru
     */
    public void novaHra(IHra hra) {
        this.hra.getHerniPlan().removeObserver(this);
        this.hra = hra;
        this.hra.getHerniPlan().registerObserver(this);
        update();
    }
}