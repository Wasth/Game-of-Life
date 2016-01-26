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
import tars.model.LifeEngine;

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

    public static Stage settingStage;
    ObservableList<Integer> speedList = FXCollections.observableArrayList();
    public static Color deadFillColor;
    public static Color deadStrokeColor;
    public static Color aliveFillColor;
    public static Color aliveStrokeColor;

    public static boolean stop = false;
    static String path = "";
    volatile LifeEngine en;
    volatile Rectangle rectangle[][];

    @FXML
    void initialize() throws InterruptedException {
        System.out.println("Init GUI");
        en = new LifeEngine(30, 50);
        rectangle = new Rectangle[30][50];
        fieldPane = new GridPane();
        buttonPane.setHgap(5);
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
        deadFillColor = Color.web("030303");
        deadStrokeColor = Color.web("00ff00");
        aliveFillColor = Color.web("00ff00");
        aliveStrokeColor = Color.web("00ff00");
        speedChoiceBox.setValue(50);
        speedChoiceBox.setItems(speedList);
        for (int i = 0; i < en.getMaxSizeX(); i++) {
            for (int j = 0; j < en.getMaxSizeY(); j++) {
                rectangle[i][j] = new Rectangle(10, 10);
                rectangle[i][j].addEventHandler(MouseEvent.MOUSE_PRESSED, new FieldHandler(i, j, this));
            }
        }
        setRectangles();
        pane.getChildren().add(fieldPane);
        setRectangles();
    }

    void setRectangles() {
        fieldPane.getChildren().clear();
        for (int i = 0; i < en.getMaxSizeX(); i++) {
            for (int j = 0; j < en.getMaxSizeY(); j++) {
                if (en.getField()[i][j].equals("alive")) {
                    rectangle[i][j].setFill(aliveFillColor);
                    rectangle[i][j].setStroke(aliveStrokeColor);
                    fieldPane.add(rectangle[i][j], j, i);
                } else {
                    rectangle[i][j].setFill(deadFillColor);
                    rectangle[i][j].setStroke(deadStrokeColor);
                    fieldPane.add(rectangle[i][j], j, i);
                }

            }
        }

    }

    synchronized void setRectangles(GridPane fieldPane, Rectangle[][] rectangle, LifeEngine en) {
        fieldPane.getChildren().clear();
        for (int i = 0; i < en.getMaxSizeX(); i++) {
            for (int j = 0; j < en.getMaxSizeY(); j++) {
                if (en.getField()[i][j].equals("alive")) {
                    rectangle[i][j].setFill(aliveFillColor);
                    rectangle[i][j].setStroke(aliveStrokeColor);
                    fieldPane.add(rectangle[i][j], j, i);
                } else {
                    rectangle[i][j].setFill(deadFillColor);
                    rectangle[i][j].setStroke(deadStrokeColor);
                    fieldPane.add(rectangle[i][j], j, i);
                }
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