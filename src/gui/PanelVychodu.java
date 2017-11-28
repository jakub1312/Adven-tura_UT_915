/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import logika.HerniPlan;
import logika.IHra;
import logika.Prostor;
import utils.Observer;


    /*
    * Konstruktor pre panel s východmi.
    */
public class PanelVychodu extends ListView implements Observer{
    
    private IHra hra;
    private HerniPlan plan;
    ListView <String> list;
    private ObservableList<String> data = FXCollections.observableArrayList();
    
    private TextArea centralText;
    private TextField zadejPrikazTextArea;

    public PanelVychodu(HerniPlan plan, TextArea text,TextField field){
        this.plan = plan;
        plan.registerObserver(this);

        centralText = text;
        zadejPrikazTextArea = field;
        init();
      }

    public PanelVychodu(IHra hra) {
        this.hra = hra;
    }
    /*
    *
    * Metoda vytvára list pre východy a pridáva funkciu dvojkliku
    */
    private void init(){
        list = new ListView<>();
        data = FXCollections.observableArrayList();
        list.setItems(data);
        list.setPrefWidth(100);
        
        list.setOnMouseClicked(new EventHandler<MouseEvent>(){
            
            public void handle(MouseEvent click){
                if (click.getClickCount() == 2) {
                    
                    String vstupniPrikaz = "chod "+list.getSelectionModel().getSelectedItem();
                    String odpovedHry = plan.getHra().zpracujPrikaz("chod "+list.getSelectionModel().getSelectedItem());
                
                    centralText.appendText("\n" + vstupniPrikaz + "\n");
                    centralText.appendText("\n" + odpovedHry + "\n");
                    
                    
                    if (plan.getHra().konecHry()){
                    zadejPrikazTextArea.setEditable(false);
                    centralText.appendText(plan.getHra().vratEpilog());
                    }
                    plan.notifyObservers();
                }
            }
        });
        update();
      }
        
    /*
    *
    * Metoda vracia List
    */
    public ListView<String> getList(){
        return list;
      }

    @Override
    public void update() {
        data.clear();

        for (Prostor prostor : plan.getAktualniProstor().getVychody()) {
            data.add(prostor.getNazev());

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
     * Metoda přeregistrující observer
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
