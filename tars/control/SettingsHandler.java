package tars.control;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

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

    static Handler hand;
    @FXML
    void initialize(){
        livingFillColor.setValue(Handler.aliveFillColor);
        livingStrokeColor.setValue(Handler.aliveStrokeColor);
        deadFillColor.setValue(Handler.deadFillColor);
        deadStrokeColor.setValue(Handler.deadStrokeColor);
    }
    @FXML
    void clickApply(){
        Handler.aliveFillColor = livingFillColor.getValue();
        Handler.aliveStrokeColor = livingStrokeColor.getValue();
        Handler.deadFillColor = deadFillColor.getValue();
        Handler.deadStrokeColor = deadStrokeColor.getValue();
        hand.setRectangles();
        Handler.settingStage.close();
    }
    @FXML
    void clickCancel(){
        Handler.settingStage.close();
    }
    public static void setHandler(Handler handler){
        hand = handler;
    }
}
