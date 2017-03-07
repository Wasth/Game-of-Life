package tars.model;
import javafx.scene.paint.Color;

import java.io.Serializable;

/**
 * Created by tim on 27.01.16.
 */
public class SerializeColor{
    public Color deadFillColor;
    public Color deadStrokeColor;
    public Color aliveFillColor;
    public Color aliveStrokeColor;
    public boolean random;
    public void setColors(RGBColor color){
        aliveFillColor = Color.color(color.afr,color.afg,color.afb,color.afp);
        aliveStrokeColor = Color.color(color.asr,color.asg,color.asb,color.asp);
        deadFillColor = Color.color(color.dfr,color.dfg,color.dfb,color.dfp);
        deadStrokeColor = Color.color(color.dsr,color.dsg,color.dsb,color.dsp);
    }
}
