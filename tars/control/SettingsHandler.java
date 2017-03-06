package tars.control;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import tars.model.SerializeColor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

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
    static Handler handler;
    @FXML
    void initialize(){
        livingFillColor.setValue(handler.serColor.aliveFillColor);
        livingStrokeColor.setValue(handler.serColor.aliveStrokeColor);
        deadFillColor.setValue(handler.serColor.deadFillColor);
        deadStrokeColor.setValue(handler.serColor.deadStrokeColor);
    }
    @FXML
    void clickApply(){
        handler.serColor.aliveFillColor = livingFillColor.getValue();
        handler.serColor.aliveStrokeColor = livingStrokeColor.getValue();
        handler.serColor.deadFillColor = deadFillColor.getValue();
        handler.serColor.deadStrokeColor = deadStrokeColor.getValue();
            try {
                ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(Handler.settingPath));
                handler.colors.setColor(handler.serColor);
                if(handler.colors != null){
                    writer.writeObject(handler.colors);
                    System.out.printf("Writed in %s\n",new File(Handler.settingPath).getAbsolutePath());
                    writer.flush();
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
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
