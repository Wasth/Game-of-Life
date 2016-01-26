package tars.control;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * Created by tim on 24.01.16.
 */
public class MoveHandler implements EventHandler<Event> {
    Handler hand;
    public MoveHandler(Handler hand){
        this.hand = hand;
    }
    public void handle(Event event) {
        System.out.printf(FieldHandler.pressed+"\n");
        if(FieldHandler.pressed) {

            MouseEvent ev = (MouseEvent) event;
            int j = (int) (ev.getSceneX() / 11);
            int i = (int) (ev.getSceneY() / 11) - 3;
            System.out.printf("%d %d\n", i, j);
            if (hand.en.getField()[i][j].equals("alive")) {
                hand.en.setValue(i, j, "dead");
            } else {
                hand.en.setValue(i, j, "alive");
            }
            hand.setRectangles();
        }
    }
}
