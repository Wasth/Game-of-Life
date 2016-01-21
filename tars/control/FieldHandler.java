package tars.control;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import tars.model.LifeEngine;

import java.text.Format;

/**
 * Created by tim on 21.01.16.
 */
public class FieldHandler implements EventHandler<Event> {
    int x;
    int y;
    Rectangle[][] rect;
    LifeEngine en;
    GridPane pane;
    FieldHandler(int x, int y, Rectangle[][] rect, LifeEngine en, GridPane pane){
        this.x = x;
        this.y = y;
        this.rect = rect;
        this.en = en;
        this.pane = pane;
    }
    public void handle(Event event) {
                System.out.println(x+" "+y);
                if(en.getField()[x][y].equals("alive")){
                    en.setValue(x,y,"dead");
                }else{
                    en.setValue(x,y,"alive");

                }

        pane.getChildren().clear();
        for(int i = 0;i < en.getMaxSizeX();i++){
            for(int j = 0;j < en.getMaxSizeY();j++){
                if(en.getField()[i][j].equals("alive")){
                    rect[i][j].setFill(Color.LIMEGREEN);

                    pane.add(rect[i][j],j,i);
                }else{
                    rect[i][j].setFill(Color.BLACK);
                    pane.add(rect[i][j],j,i);
                }
                rect[i][j].setStroke(Color.LIME);
            }
        }


    }
}
