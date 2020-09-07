package com.vkg.pactice.ideas.liftmgmt;

import com.vkg.pactice.ideas.liftmgmt.buttons.Button;

import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Vishnu on 2/3/2018.
 */
public class People implements Observer {
    String name;
    int currentFloor;
    int targetFloor;
    boolean inside;

    public People(String name, int currentFloor, int targetFloor) {
        this.name = name;
        this.currentFloor = currentFloor;
        this.targetFloor = targetFloor;
    }

    @Override
    public void update(Observable o, Object arg) {

        NotifiableLift lift = (NotifiableLift) arg;
        int floor = lift.getCurrentFloor();
        if(inside && floor == targetFloor) {
            System.out.println(this + " is Going out to the lift " + lift);
        }
        if(!inside && floor == currentFloor) {
            System.out.println(this + " is Entering into the lift " + lift);
            Button button = lift.getButton(targetFloor);
            if (button == null) {
                System.out.println("No button in lift for floor " + targetFloor);
            } else
                button.press();
        }
    }

    @Override
    public String toString() {
        return "Person " + name + " " + currentFloor +
                "->" + targetFloor;
    }

    public void observeLifts(NotifiableLift... lifts) {
        Arrays.stream(lifts).forEach(l -> l.addPerson(this));
    }

    public void pressButton(Button button) {
        button.press();
        System.out.println("by " + this);
    }
}
