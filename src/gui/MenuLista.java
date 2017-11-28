/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import logika.*;
import main.Main;

/**
 *
 * @author Jakub Ismail
 */
public class MenuLista extends MenuBar{
    
    private IHra hra;
    private Main main;
    private Stage stage;
    
    public MenuLista(IHra hra, Main main, Stage stage){
        this.hra = hra;
        this.main = main;
        this.stage = stage;
        init();
    }
    
    
    
    private void init(){
        
        Menu novySoubor = new Menu("Adventúra");
        Menu napoveda = new Menu("Help");
        
        MenuItem novaHra = new MenuItem("Nová hra");
        // new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/ikona.png")))
        
        novaHra.setAccelerator(KeyCombination.keyCombination("Ctrl+H"));
        MenuItem konecHry = new MenuItem("Koniec hry");
        
        novySoubor.getItems().addAll(novaHra, konecHry);
        
        MenuItem oProgramu = new MenuItem("O programe");
        MenuItem napovedaItem = new MenuItem("Nápoveda");
        
        napoveda.getItems().addAll(oProgramu, napovedaItem);
        
        this.getMenus().addAll(novySoubor, napoveda);
        
        konecHry.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        
        novaHra.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                main.start(stage);
            }
        });
        
        oProgramu.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            
                Alert oProgramuAlert = new Alert(Alert.AlertType.INFORMATION);
                
                oProgramuAlert.setTitle("O programe");
                oProgramuAlert.setHeaderText("Adventura Agent47");
                oProgramuAlert.setContentText("Hra pre kurz 4IT115");
                oProgramuAlert.initOwner(main.getStage());
                
                oProgramuAlert.showAndWait();
            }
        });
        
        napovedaItem.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                Stage stage = new Stage();
                stage.setTitle("Nápoveda");
                
                WebView webView = new WebView();
                
                webView.getEngine().load(Main.class.getResource("/zdroje/napoveda.html").toExternalForm());
                
                stage.setScene(new Scene(webView, 500,500));
                stage.show();
            
            }
        });
    }
}
