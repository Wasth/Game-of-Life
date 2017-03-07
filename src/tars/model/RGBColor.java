package tars.model;

import java.io.Serializable;

/**
 * Created by tim on 27.01.16.
 */
public class RGBColor implements Serializable {
    public double afr = 0.5;
    public double afg = 0.5;
    public double afb = 0.5;
    public double asr = 0.5;
    public double asg = 0.5;
    public double asb = 0.5;
    public double afp = 0.5;
    public double asp = 0.5;
    public double dfr = 0.5;
    public double dfg = 0.5;
    public double dfb = 0.5;
    public double dsr = 0.5;
    public double dsg = 0.5;
    public double dsb = 0.5;
    public double dfp = 0.5;
    public double dsp = 0.5;
    public boolean random;
    public void setColor(SerializeColor ser){

        afr = ser.aliveFillColor.getRed();
        afg = ser.aliveFillColor.getGreen();
        afb = ser.aliveFillColor.getBlue();

        asr = ser.aliveStrokeColor.getRed();
        asg = ser.aliveStrokeColor.getGreen();
        asb = ser.aliveStrokeColor.getBlue();

        afp = ser.aliveFillColor.getOpacity();
        asp = ser.aliveStrokeColor.getOpacity();

        dfr = ser.deadFillColor.getRed();
        dfg = ser.deadFillColor.getGreen();
        dfb = ser.deadFillColor.getBlue();

        dsr = ser.deadStrokeColor.getRed();
        dsg = ser.deadStrokeColor.getGreen();
        dsb = ser.deadStrokeColor.getBlue();

        dfp = ser.deadFillColor.getOpacity();
        dsp = ser.deadStrokeColor.getOpacity();

        random = ser.random;
    }
}
