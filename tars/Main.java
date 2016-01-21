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
import tars.model.LifeEngine;

/**
 * Created by tim on 20.01.16.
 */

/* Небольшая справка по редактированию.
 *  у объекта m.en есть такие функции, которые тебе пригодятся, чтобы задавать параметры:
 *  m.en.nextGeneration(); - внутри объекта LifeEngine(здесь экземпляр - m.en , пользуйся им) есть поле, в котором
 *  происходит жизнь. Данный метод генерирует следущее поколение.
 *  m.en.setValue(int x,int y,String state); - задает состояние клетки с координатой (x,y). Чтобы сделать клетку живой
 *  третьим аргументом передается "alive". Чтобы сделать мертвой - "dead"
 *
 *  В этом же главном классе есть специально подготовленный метод, который выводит все поле. Ибо метод не статичен, я не
 *  могу использовать его(статичные методы могут обращаться к своим полям\методам только если они статичны), пришлось
 *  создавать экземпляр главного класса.
 *
 *  Вот пример вызова метода для вывода поля:
 *  m.showFieldOnConsole(m.en);
 *
 *  Вот и все. Также в строке "m.en = ne LifeEngine(20,20)" можешь изменить размер поля, изменив значение аргумента.
 *          */
public class Main extends Application{
    LifeEngine en;
    Stage stage;
    public static void main(String[] args){

        launch(args);

    }
    public void start(Stage primStage){
        stage = new Stage();
        primStage = stage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/Root.fxml"));
        AnchorPane pane = new AnchorPane();
        try{
            pane = loader.load();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        primStage.setTitle("Game of LIFE");
        primStage.setScene(new Scene(pane));
        primStage.show();
    }
    void showFieldOnConsole(LifeEngine en){
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
