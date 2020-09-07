package com.vkg.pactice.ideas.liftmgmt.buttons;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Vishnu on 2/3/2018.
 */
public class PushButton implements Button, Observer {
    Button button;
    boolean pressed;

    public PushButton(Button button) {
        this.button = button;
    }

    @Override
    public void press() {
        if(!pressed) {
            button.press();
        } else {
            System.out.println(button + " is Already Pressed");
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        pressed = false;
    }
}
