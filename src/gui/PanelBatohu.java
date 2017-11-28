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
import logika.IHra;
import logika.Vec;
import utils.Observer;

//autor Jakub Ismail
    /*
    * Konstruktor pre panel batohu.
    */
public class PanelBatohu extends ListView implements Observer{
    
    private HerniPlan plan;
    ListView<Object> list;
    private ObservableList<Object> data = FXCollections.observableArrayList();
    private TextArea centralText;

    
    public PanelBatohu(HerniPlan plan, TextArea text) {
       this.plan = plan;
       plan.registerObserver(this);
       
       centralText = text;
        init();
    }

    public PanelBatohu(IHra hra) {
        this.hra = hra;
    }

    /*
    *
    * Metoda vytvára list pre batoh a veci v ňom a pridáva funkciu dvojkliku
    */
    private void init() {
        list = new ListView<>();
        data = FXCollections.observableArrayList();
        list.setItems(data);
        list.setPrefWidth(125);
        
        list.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click){
                if (click.getClickCount() == 2) {
                    int index = list.getSelectionModel().getSelectedIndex();
                    
                    Map<String, Vec> seznam;
                    seznam = plan.getBatoh().getSeznamVeci();
                    String nazev = "";
                    int pomocna = 0;
                    for (String x : seznam.keySet()) {
                       if(pomocna == index){
                           nazev = x;
                       }
                       pomocna++;
                    }
                    String vstupniPrikaz = "odhod "+nazev;
                    String odpovedHry = plan.getHra().zpracujPrikaz("odhod "+nazev);

                    centralText.appendText("\n" + vstupniPrikaz + "\n");
                    centralText.appendText("\n" + odpovedHry + "\n");
               
                    plan.notifyObservers();
                }
            }
        });
        update();
    }
    
    /*
    * Metoda vracia list.
    */
    public ListView<Object> getList() {
        return list;
    }
    
    /*
    * Metoda aktualizuje list vecí v batohu a zobrazuje obrázky věcí, které má hráč u sebe.
    */
    @Override 
    public void update() {        
        Map<String, Vec> seznam;
        seznam = plan.getBatoh().getSeznamVeci();
        data.clear();
        for (String x : seznam.keySet()) {
        Vec pomocna = seznam.get(x);
        ImageView obrazek = new ImageView(new Image(main.Main.class.getResourceAsStream("/zdroje/"+pomocna.getObrazek()), 80, 80, false, false));
        data.add(obrazek);
        }
    }
    
    /**
     * Metoda zaregistruje pozorovatele k hernímu plánu při spuštění nové hry.
     * @param plan
     */
    public void nastaveniHernihoPlanu (HerniPlan plan){
        this.plan = plan;
        plan.registerObserver(this);
        this.update();
    }
     /**
     * Metoda preregistrujúca observer
     *
     * @param hra parametr obsahující odkaz na hru
     */
    public void novaHra(IHra hra) {
        this.hra.getHerniPlan().removeObserver(this);
        this.hra = hra;
        this.hra.getHerniPlan().registerObserver(this);
        update();
    }
        private IHra hra;
}
