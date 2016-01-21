package tars.control;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import tars.model.LifeEngine;

/**
 * Created by tim on 20.01.16.
 */
public class Handler{
    @FXML
    FlowPane pane;
    int XI;
    int XJ;
    volatile LifeEngine en;
    @FXML
    volatile GridPane fieldPane;
    volatile Rectangle rectangle [] [];
    @FXML
    void initialize() throws InterruptedException {
        System.out.println("Init GUI");
        en = new LifeEngine(30,20);
        rectangle = new Rectangle[30][20];
        fieldPane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        for(int i = 0;i < en.getMaxSizeX();i++){
            for(int j = 0;j < en.getMaxSizeY();j++){
                rectangle[i][j] = new Rectangle(10,10);
                XI=i;
                XJ=j;
                rectangle[i][j].addEventHandler(MouseEvent.MOUSE_CLICKED, new FieldHandler(i,j,rectangle,en,fieldPane));
                rectangle[i][j].setStroke(Color.LIME);
                rectangle[i][j].setFill(Color.BLACK);
                fieldPane.add(rectangle[i][j],j,i);
            }
        }
        pane.getChildren().add(fieldPane);

        en.setValue(1,4,"alive");
        en.setValue(1,5,"alive");
        en.setValue(2,3,"alive");
        en.setValue(2,6,"alive");
        en.setValue(3,3,"alive");
        en.setValue(3,6,"alive");
        en.setValue(4,1,"alive");
        en.setValue(4,3,"alive");
        en.setValue(4,6,"alive");
        en.setValue(4,8,"alive");
        en.setValue(5,1,"alive");
        en.setValue(5,2,"alive");
        en.setValue(5,7,"alive");
        en.setValue(5,8,"alive");
        setRectangles(fieldPane,rectangle,en);

    }
     void setRectangles(GridPane pane,Rectangle [] [] rect,LifeEngine en){
         fieldPane.getChildren().clear();
        for(int i = 0;i < en.getMaxSizeX();i++){
            for(int j = 0;j < en.getMaxSizeY();j++){
                if(en.getField()[i][j].equals("alive")){
                    rectangle[i][j].setFill(Color.LIMEGREEN);

                    fieldPane.add(rectangle[i][j],j,i);
                }else{
                    rectangle[i][j].setFill(Color.BLACK);
                    fieldPane.add(rectangle[i][j],j,i);
                }
                rectangle[i][j].setStroke(Color.LIME);
            }
        }
    }
    @FXML
    void clickNextGen(){
        en.nextGeneration();
        setRectangles(fieldPane,rectangle,en);
    }
}
