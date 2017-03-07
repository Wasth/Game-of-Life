package tars.control;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import tars.model.LifeEngine;

import java.text.Format;
import java.util.ArrayList;

/**
 * Created by tim on 21.01.16.
 */
public class FieldHandler implements EventHandler<Event> {
    int x;
    int y;
    public static boolean pressed;
    Rectangle[][] rect;
    LifeEngine en;
    GridPane pane;
    Handler hand;
    FieldHandler(int x, int y, Handler hand){
        this.x = x;
        this.y = y;
        this.hand = hand;
        this.rect = hand.rectangle;
        this.en = hand.en;
        this.pane = hand.fieldPane;
    }
    public void handle(Event event) {
//        ArrayList<String> selected =
        pressed = true;
        //System.out.println("DRAGGED");
        if(en.getField()[x][y].equals("alive")){
            en.setValue(x,y,"dead");
        }else{
            en.setValue(x,y,"alive");
        }
            hand.setRectangles();
    }
}
