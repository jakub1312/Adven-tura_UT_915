/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import logika.IHra;
import main.Main;
import utils.Observer;

/**
 *
 * @author Jakub Ismail
 */
public class Mapa extends AnchorPane implements Observer {

    private IHra hra;
    private Circle bodka;

    public Mapa(IHra hra) {
        this.hra = hra;
        hra.getHerniPlan().registerObserver(this);
        init();
    }
    
    
    private void init() {
        ImageView obrazekImageView = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/planek.png"), 300, 450, false, true));
        bodka = new Circle(5, Paint.valueOf("white"));
        this.getChildren().addAll(obrazekImageView, bodka);
        update();
    }
    
    public void newGame(IHra novaHra){
        hra = novaHra;
        hra.getHerniPlan().removeObserver(this);
        hra.getHerniPlan().registerObserver(this);
        update();
    }

    @Override
    public void update() {
       this.setTopAnchor(bodka, hra.getHerniPlan().getAktualniProstor().getPosTop());
       this.setLeftAnchor(bodka, hra.getHerniPlan().getAktualniProstor().getPosLeft());
    }


}
