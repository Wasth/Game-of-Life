package tars.control;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import tars.model.SerializeColor;

import java.io.*;

/**
 * Created by tim on 26.01.16.
 */
public class SettingsHandler {
    @FXML
    ColorPicker livingFillColor;
    @FXML
    ColorPicker livingStrokeColor;
    @FXML
    ColorPicker deadFillColor;
    @FXML
    ColorPicker deadStrokeColor;
    @FXML
    TextField fieldWidth;
    @FXML
    TextField fieldHeight;
    @FXML
    TextField cellSize;
    @FXML
    TextField strokeSize;
    static Handler handler;
    @FXML
    void initialize(){
        livingFillColor.setValue(handler.serColor.aliveFillColor);
        livingStrokeColor.setValue(handler.serColor.aliveStrokeColor);
        deadFillColor.setValue(handler.serColor.deadFillColor);
        deadStrokeColor.setValue(handler.serColor.deadStrokeColor);
        fieldWidth.setText(""+handler.lifeW);
        fieldHeight.setText(""+handler.lifeH);
        cellSize.setText(""+handler.rectWidth);
        strokeSize.setText(""+handler.strokeWidth);
    }
    @FXML
    void clickApply(){
        handler.serColor.aliveFillColor = livingFillColor.getValue();
        handler.serColor.aliveStrokeColor = livingStrokeColor.getValue();
        handler.serColor.deadFillColor = deadFillColor.getValue();
        handler.serColor.deadStrokeColor = deadStrokeColor.getValue();
            try {
                ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(Handler.colorSettingPath));
                handler.colors.setColor(handler.serColor);
                if(handler.colors != null){
                    writer.writeObject(handler.colors);
                    System.out.printf("Writed in %s\n",new File(Handler.colorSettingPath).getAbsolutePath());
                    writer.flush();
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                PrintWriter out = new PrintWriter(Handler.sizeSettingPath);
                String txt = fieldWidth.getText()+"\n"+fieldHeight.getText()+"\n"+cellSize.getText()+"\n"+strokeSize.getText();
                out.write(txt);
                out.flush();
                out.close();
            }catch (Exception e) {

            }
        handler.setRectangles();
        Handler.settingStage.close();
    }
    @FXML
    void clickCancel(){
        Handler.settingStage.close();
    }
    public static void setHandler(Handler hand){
        handler = hand;
    }
}
