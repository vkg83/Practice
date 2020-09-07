package com.vkg.pactice.ideas.liftmgmt;

import com.vkg.pactice.ideas.liftmgmt.buttons.Button;

/**
 * Created by Vishnu on 2/3/2018.
 */
public class Floor {
    Button upButton;
    Button downButton;

    public Floor(Button upButton, Button downButton) {
        this.upButton = upButton;
        this.downButton = downButton;
    }

    public Button getDownButton() {
        return downButton;
    }

    public Button getUpButton() {
        return upButton;
    }
}
