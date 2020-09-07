package com.vkg.pactice.ideas.liftmgmt;

import com.vkg.pactice.ideas.liftmgmt.buttons.Button;
import com.vkg.pactice.ideas.liftmgmt.buttons.PushButton;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class NotifiableLift extends AbstractLift {
    private LiftObservable obj = new LiftObservable();
    Map<String, Button> buttonMap = new HashMap<>();
    public NotifiableLift(String name, int initialFloor) {
        super(name, initialFloor);
    }

    public void addPerson(People people) {
        obj.addObserver(people);
    }

    public void addButton(int floor, Button button) {
        buttonMap.put("F" + floor, button);
    }
    public void addToggleButton(int floor, PushButton button) {
        addButton(floor, button);
        obj.addObserver(button);
    }
    public Button getButton(int targetFloor) {
        return buttonMap.get("F" + targetFloor);
    }

    @Override
    protected void waitForPeople() {
        super.waitForPeople();
        obj.setChanged();
        obj.notifyObservers(this);
    }

    private class LiftObservable extends Observable {
        @Override
        public synchronized void setChanged() {
            super.setChanged();
        }
    }
}
