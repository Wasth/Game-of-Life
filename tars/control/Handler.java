package tars.control;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tars.Main;
import tars.model.*;

import java.io.*;


/**
 * Created by tim on 20.01.16.
 */
public class Handler {
    @FXML
    FlowPane pane;
    @FXML
    volatile GridPane fieldPane;
    @FXML
    ChoiceBox<Integer> speedChoiceBox;
    @FXML
    FlowPane buttonPane;
    @FXML
    public static String colorSettingPath = "colorSetting.gs";
    public static String sizeSettingPath = "sizeSettings.gs";
    public  SerializeColor serColor;
    public  RGBColor colors;
    public static Stage settingStage;
    ObservableList<Integer> speedList = FXCollections.observableArrayList();
    public static boolean stop = false;
    static String path = "";
    volatile LifeEngine en;
    volatile Rectangle rectangle[][];
    int strokeWidth = 1;
    int rectWidth = 15;
    int rectHeight = 15;
    int lifeH = 30;
    int lifeW = 50;
    @FXML
    void initialize() throws InterruptedException {
        System.out.println("Init GUI");
        if(new File(sizeSettingPath).exists()) {
            try {
                BufferedReader in = new BufferedReader(new FileReader(sizeSettingPath));
                lifeW = Integer.parseInt(in.readLine());
                lifeH = Integer.parseInt(in.readLine());
                rectWidth = Integer.parseInt(in.readLine());
                rectHeight = rectWidth;
                strokeWidth = Integer.parseInt(in.readLine());
                System.out.printf("Size setting file is read.\n");
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int windowW = (int) ((rectWidth+strokeWidth)*lifeW);
        if (windowW < 550) windowW = 550;
        int windowH = (int) (89 + (rectHeight+strokeWidth)*lifeH);
        if (windowH < 419) windowH = 419;
        Main.pane.setPrefWidth(windowW);
        Main.pane.setPrefHeight(windowH);
        System.out.println(windowW+" "+windowH);
        en = new LifeEngine(lifeH, lifeW);
        rectangle = new Rectangle[lifeH][lifeW];
        fieldPane = new GridPane();
        buttonPane.setHgap(15);
        pane.setAlignment(Pos.CENTER);
        speedChoiceBox.getItems().removeAll();
        speedList.add(50);
        speedList.add(100);
        speedList.add(150);
        speedList.add(200);
        speedList.add(300);
        speedList.add(500);
        speedList.add(750);
        speedList.add(1000);
        speedList.add(2000);
        if(!new File(colorSettingPath).exists()) {
            serColor = new SerializeColor();
            colors = new RGBColor();
            serColor.deadFillColor = Color.web("030303");
            serColor.deadStrokeColor = Color.LIMEGREEN;
            serColor.aliveFillColor = Color.web("00ff00");
            serColor.aliveStrokeColor = Color.LIMEGREEN;
            serColor.random = false;
            colors.setColor(serColor);
            System.out.printf("Setting file doesn't exist\n");
        }else{
            try {
                ObjectInputStream reader = new ObjectInputStream(new FileInputStream(colorSettingPath));
                colors = (RGBColor) reader.readObject();
                serColor = new SerializeColor();
                serColor.setColors(colors);
                reader.close();
                System.out.printf("Color setting file is read.\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        speedChoiceBox.setValue(300);
        speedChoiceBox.setItems(speedList);
        for (int i = 0; i < en.getMaxSizeX(); i++) {
            for (int j = 0; j < en.getMaxSizeY(); j++) {
                rectangle[i][j] = new Rectangle(rectWidth, rectHeight);
                rectangle[i][j].setStrokeWidth(strokeWidth);
                rectangle[i][j].addEventHandler(MouseEvent.MOUSE_CLICKED, new FieldHandler(i, j, this));
            }
        }
        en.clearField();
        pane.getChildren().add(fieldPane);
        setRectangles();
    }

    void setRectangles() {
        fieldPane.getChildren().clear();
        for (int i = 0; i < en.getMaxSizeX(); i++) {
            for (int j = 0; j < en.getMaxSizeY(); j++) {
                if (en.getField()[i][j].equals("alive")) {
                    rectangle[i][j].setFill(serColor.aliveFillColor);
                    rectangle[i][j].setStroke(serColor.aliveStrokeColor);
                    fieldPane.add(rectangle[i][j], j, i);
                } else {
                    rectangle[i][j].setFill(serColor.deadFillColor);
                    rectangle[i][j].setStroke(serColor.deadStrokeColor);
                    fieldPane.add(rectangle[i][j], j, i);
                }
                rectangle[i][j].setStrokeWidth(strokeWidth);
            }
        }
    }
    @FXML
    void clickNextGen() {
        boolean tmp = en.nextGeneration();
        setRectangles();
    }

    @FXML
    void clickAutorun() {
        Thread thr = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean tmp = true;
                stop = false;
                while (tmp & !stop) {
                    tmp = en.nextGeneration();
                    Runnable r = () -> {
                        setRectangles();
                    };
                    Platform.runLater(r);
                    try {
                        Thread.sleep(speedChoiceBox.getValue());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        thr.start();
    }

    @FXML
    void clickStop() {
        stop = true;

    }

    @FXML
    void clickClear() {
        en.clearField();
        path = "";
        setRectangles();
    }

    @FXML
    void clickAbout() {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/About.fxml"));
        AnchorPane pane = null;
        try {
            pane = (AnchorPane) loader.load();
        } catch (Exception ex) {
            pane = new AnchorPane();
            ex.printStackTrace();
        }
        stage.setScene(new Scene(pane));
        stage.setTitle("About creator");
        stage.show();
    }

    @FXML
    void clickLoad() {
        File file = new FileChooser().showOpenDialog(Main.stage);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String tmp;
            String cont = "";
            while ((tmp = reader.readLine()) != null) {
                cont = cont + tmp + "\n";
            }
            en.clearField();
            en.setField(getField(cont));

            setRectangles();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void clickSave() {
        if (!path.equals("")) {
            File file = new File(path);
            try {
                FileWriter writer = new FileWriter(file);
                writer.write(getFieldContent(en));
                writer.flush();
                writer.close();
            } catch (IOException e) {

            }

        } else {
            File file = new FileChooser().showSaveDialog(Main.stage);
            path = file.getPath();
            try {
                FileWriter writer = new FileWriter(file);
                writer.write(getFieldContent(en));
                writer.flush();
                writer.close();
            } catch (IOException e) {

            }
        }

    }

    @FXML
    void clickSaveAs() {
        File file = new FileChooser().showSaveDialog(Main.stage);
        path = file.getPath();
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(getFieldContent(en));
            writer.flush();
            writer.close();
        } catch (IOException e) {

        }
    }

    @FXML
    void clickSettings() {
        settingStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/Setting.fxml"));
        AnchorPane pane = null;
        SettingsHandler.setHandler(this);
        try {
            pane = (AnchorPane) loader.load();
        } catch (Exception ex) {
            pane = new AnchorPane();
            ex.printStackTrace();
        }
        SettingsHandler.setHandler(this);
        settingStage.setScene(new Scene(pane));
        settingStage.setTitle("Settings");
        settingStage.show();

    }

    @FXML
    void clickClose() {
        Main.stage.close();
    }

    static String getFieldContent(LifeEngine engine) {
        String content = "";
        content = content + engine.getMaxSizeX() + "_" + engine.getMaxSizeY() + "\n";
        for (int i = 0; i < engine.getMaxSizeX(); i++) {
            for (int j = 0; j < engine.getMaxSizeY(); j++) {
                if (engine.getField()[i][j].equals("alive")) {
                    content = content + "a";
                } else if (engine.getField()[i][j].equals("dead")) {
                    content = content + "d";
                }
            }
            content = content + "\n";
        }
        return content;
    }

    static String[][] getField(String content) {
        char charContent[] = content.toCharArray();
        int x = 0;
        int y = 0;
        String tmpX = "";
        String tmpY = "";
        boolean yc = false;
        int lineCount = 0;
        int xCount = 0;
        int yCount = 0;
        String field[][] = null;
        for (int i = 0; i < charContent.length; i++) {
            if (charContent[i] == '_' && lineCount == 0) {
                yc = true;
            }
            if (!yc && lineCount == 0) {
                tmpX = tmpX + charContent[i];
            } else if (yc && charContent[i] != '\n' && charContent[i] != '_' && lineCount == 0) {
                tmpY = tmpY + charContent[i];
            } else if (charContent[i] == '\n' && lineCount == 0) {
                x = Integer.parseInt(tmpX);
                y = Integer.parseInt(tmpY);
                lineCount++;
                yCount = 0;
                field = new String[x][y];
            } else if (charContent[i] == '\n' && lineCount >= 1) {
                lineCount++;
                xCount++;
                yCount = 0;
            } else if (lineCount >= 1) {

                if (charContent[i] == 'a') {
                    field[xCount][yCount] = "alive";
                    yCount++;
                } else if (charContent[i] == 'd') {
                    field[xCount][yCount] = "dead";
                    yCount++;
                }
            }
        }
        return field;
    }


}