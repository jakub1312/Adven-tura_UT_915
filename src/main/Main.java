package main;

import gui.Mapa;
import gui.MenuLista;
import gui.PanelVeci;
import gui.PanelBatohu;
import gui.PanelVychodu;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import logika.*;
import uiText.TextoveRozhrani;

/**
 *
 * @author Jakub Ismail
 */
    public class Main extends Application {

    private IHra hra;
    private Stage stage;
     
    
    private TextArea centralText;
    
    private TextField zadejPrikazTextArea; 

    private Mapa mapa; 
    private PanelBatohu panelBatohu;
    private PanelVychodu panelVychodu;
    private PanelVeci panelVeci;
    
    private MenuLista menuLista; 
    
    
    
   @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        
        setHra(new Hra());
        mapa = new Mapa(hra);
        menuLista = new MenuLista(hra, this, stage);
        
        BorderPane hlavny = new BorderPane();
                
        // Text s prubehem hry
        centralText = new TextArea();
        getCentralText().setFont(Font.font("Book Antiqua", FontWeight.LIGHT, 14));
        getCentralText().setText(hra.vratUvitani());
        getCentralText().setEditable(false);
        getCentralText().setPrefWidth(500);
        
        //label s textem zadej prikaz
        Label zadejPrikazLabel = new Label("Zadaj príkaz: ");
        zadejPrikazLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        
       
        // text area do ktere piseme prikazy
        zadejPrikazTextArea = new TextField("Sem zadaj príkaz");
        zadejPrikazTextArea.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                String vstupniPrikaz = zadejPrikazTextArea.getText();
                String odpovedHry = hra.zpracujPrikaz(vstupniPrikaz);
                                                
                getCentralText().appendText("\n" + vstupniPrikaz + "\n");
                getCentralText().appendText("\n" + odpovedHry + "\n");
                
                zadejPrikazTextArea.setText("");
                
                if (hra.konecHry()) {
                    zadejPrikazTextArea.setEditable(false);
                    getCentralText().appendText(hra.vratEpilog());
                }
            }
        });
        
        
        
         //dolni lista s elementy
        FlowPane dolniLista = new FlowPane();
        dolniLista.getChildren().addAll(zadejPrikazLabel, zadejPrikazTextArea);
        dolniLista.setAlignment(Pos.CENTER);
        
        //lavy panel, na ktorom sú panely batoh, veci a textové rozhranie hry
        BorderPane levy = new BorderPane();
        PanelBatohu panelBatohu = new PanelBatohu(hra.getHerniPlan(),centralText);
        PanelVeci panelVeci = new PanelVeci(hra.getHerniPlan(),centralText);       
        PanelVychodu panelVychodu = new PanelVychodu(hra.getHerniPlan(),centralText,zadejPrikazTextArea);
        
        levy.setLeft(panelBatohu.getList());
        levy.setRight(panelVeci.getList());
        levy.setCenter(getCentralText());
        
        //pravy panel, na ktorom sa nachádza mapa a východy
        BorderPane pravy = new BorderPane();
        pravy.setRight(getMapa());
        pravy.setLeft(panelVychodu.getList());
        
        hlavny.setRight(pravy);
        hlavny.setLeft(levy);
        hlavny.setBottom(dolniLista);
        hlavny.setTop(menuLista);
        
        Scene scene = new Scene(hlavny, 1200, 500);
        primaryStage.setTitle("Dobrodružstvá Agenta47 - Adventúra 4IT115");

        primaryStage.setScene(scene);
        primaryStage.show();
        zadejPrikazTextArea.requestFocus();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            launch(args);
        }
        else{
            if (args[0].equals("-txt")) {
                IHra hra = new Hra();
                TextoveRozhrani textHra = new TextoveRozhrani(hra);
                textHra.hraj();
            }
            else{
                System.out.println("Neplatny parametr");
                System.exit(1);
            }
        }
    }
    
        
        
    
    /**
     * @return the stage
     */
    public Stage getStage() {
        return stage;
    }
    
    /**
     * @param hra the hra to set
     */
    public void setHra(IHra hra) {
        this.hra = hra;
    }
    
    
    
    /**
     * Metóda vracajúca mapu
     * @return the mapa
     */
    public Mapa getMapa() {
        return mapa;
    }

    /**
     * Metoda vracajúca hlavný text
     * @return the centralText
     */
    public TextArea getCentralText() {
        return centralText;
    }
    /**
     * Metoda vracejici odkaz na objekt TextField
     *
     * @return TextArea s prikazem
     */
    public TextField getZadejPrikazTextArea(){
        return zadejPrikazTextArea;
    }
    
    
    
    
}
