package tars;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import tars.control.Handler;
import tars.model.LifeEngine;

/**
 * Created by tim on 20.01.16.
 */


public class Main extends Application{
    public static AnchorPane pane;
    public static Stage stage;
    public static void main(String[] args){

        launch(args);

    }
    public void start(Stage primStage){
        stage = new Stage();
        primStage = stage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/Root.fxml"));
        pane = new AnchorPane();
        try{
            pane = loader.load();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        primStage.setTitle("Game of LIFE");
        primStage.setScene(new Scene(pane));
        primStage.show();
    }
    public void stop(){
        Handler.stop = true;
    }
    public static void showFieldOnConsole(LifeEngine en){
        System.out.println("New step");
        for(int i = 0;i < en.getMaxSizeX();i++){
            for(int j = 0;j < en.getMaxSizeY();j++){
                if(en.getField()[i][j].equals("alive")){
                    System.out.print(en.getField()[i][j].charAt(0)+" | ");
                }else{
                    System.out.print("_ | ");
                }

            }
            System.out.printf("\n");

        }
    }
}
