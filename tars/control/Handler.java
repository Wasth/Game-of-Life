package tars.control;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import tars.Main;
import tars.model.LifeEngine;

/**
 * Created by tim on 20.01.16.
 */
public class Handler{
    @FXML
    FlowPane pane;
    public static int runTime = 250;
    public static boolean stop = false;
    volatile LifeEngine en;
    @FXML
    volatile GridPane fieldPane;
    volatile Rectangle rectangle [] [];
    @FXML
    void initialize() throws InterruptedException {
        System.out.println("Init GUI");
        en = new LifeEngine(30,50);
        rectangle = new Rectangle[30][50];
        fieldPane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        for(int i = 0;i < en.getMaxSizeX();i++){
            for(int j = 0;j < en.getMaxSizeY();j++){
                rectangle[i][j] = new Rectangle(10,10);
                rectangle[i][j].addEventHandler(MouseEvent.MOUSE_CLICKED, new FieldHandler(i,j,this));
                rectangle[i][j].setStroke(Color.LIME);
                rectangle[i][j].setFill(Color.BLACK);
                fieldPane.add(rectangle[i][j],j,i);
            }
        }
        pane.getChildren().add(fieldPane);
        setRectangles();

    }
     synchronized void setRectangles(){
         fieldPane.getChildren().clear();
        for(int i = 0;i < en.getMaxSizeX();i++){
            for(int j = 0;j < en.getMaxSizeY();j++){
                if(en.getField()[i][j].equals("alive")){
                    rectangle[i][j].setFill(Color.LIME);
                    fieldPane.add(rectangle[i][j],j,i);
                }else{
                    rectangle[i][j].setFill(Color.BLACK);
                    fieldPane.add(rectangle[i][j],j,i);
                }
                rectangle[i][j].setStroke(Color.LIMEGREEN);
            }
        }

    }
    synchronized void setRectangles(GridPane fieldPane,Rectangle [] [] rectangle,LifeEngine en){
        fieldPane.getChildren().clear();
        for(int i = 0;i < en.getMaxSizeX();i++){
            for(int j = 0;j < en.getMaxSizeY();j++){
                if(en.getField()[i][j].equals("alive")){
                    rectangle[i][j].setFill(Color.LIME);
                    fieldPane.add(rectangle[i][j],j,i);
                }else{
                    rectangle[i][j].setFill(Color.BLACK);
                    fieldPane.add(rectangle[i][j],j,i);
                }
                rectangle[i][j].setStroke(Color.LIMEGREEN);
            }
        }

    }
    @FXML
    void clickNextGen(){
        boolean tmp = en.nextGeneration();
        setRectangles();
    }
    @FXML
    void clickAutorun(){
        System.out.printf("Run!\n");
        Thread thr = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean tmp = true;
                stop = false;
                while(tmp & !stop){
                    tmp = en.nextGeneration();
                    Runnable r = () -> {
                        setRectangles();
                    };
                    Platform.runLater(r);
                    try {
                        Thread.sleep(runTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        thr.start();
    }
    @FXML
    void clickStop(){
        stop = true;
        System.out.printf("Autorun stopped\n");
    }
    @FXML
    void clickClear(){
        en.clearField();
        setRectangles();
        System.out.printf("Field cleared!\n");
    }
}
