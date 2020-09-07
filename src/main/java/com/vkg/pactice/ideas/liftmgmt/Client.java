package com.vkg.pactice.ideas.liftmgmt;

import com.vkg.pactice.ideas.liftmgmt.buttons.*;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) throws InterruptedException {
        LiftManager manager = new LiftManager();
        NotifiableLift l1 = new NotifiableLift("L1", 0);
        manager.addLift(l1);
        NotifiableLift l2 = new NotifiableLift("L2", 0);
        manager.addLift(l2);
        NotifiableLift l3 = new NotifiableLift("L3", 1);
        manager.addLift(l3);
        NotifiableLift l4 = new NotifiableLift("L4", 1);
        manager.addLift(l4);
        List<Button> buttons = new ArrayList<>();
        buttons.addAll(addButtonsToLift(l1));
        buttons.addAll(addButtonsToLift(l2));
        buttons.addAll(addButtonsToLift(l3));
        buttons.addAll(addButtonsToLift(l4));
        List<Floor> floors = addFloorButtons(manager);
        int size = buttons.size();
        manager.start();
        for (int i = 0; i < 20; i++) {
            int currentFloor = (int)(Math.random() * 10);
            int targetFloor = (int)(Math.random() * 10);
            People p = new People("P"+i, currentFloor, targetFloor);
            p.observeLifts(l1, l2, l3, l4);
            Button button = getFloorButton(floors.get(currentFloor), currentFloor, targetFloor);//buttons.get((int)(Math.random() * size));
            synchronized (Client.class) {
                Client.class.wait(1000);
            }
            if(button != null) p.pressButton(button);
        }

        synchronized (Client.class) {
            Client.class.wait(5000);
        }
        manager.shutdown();
    }

    private static Button getFloorButton(Floor floor, int currentFloor, int targetFloor) {
        Button button;
        if(currentFloor > targetFloor) {
            button = floor.getDownButton();
        } else {
            button = floor.getUpButton();
        }

        return button;
    }

    private static List<Floor> addFloorButtons(LiftManager manager) {
        List<Floor> buttons = new ArrayList<>();
        Button bb = new FloorButton(manager, 0, Direction.UP);
        buttons.add(new Floor(bb, null));
        for(int i = 1; i < 9; i++) {
            Button bu = new FloorButton(manager, i, Direction.UP);
            Button bd = new FloorButton(manager, i, Direction.DOWN);
            buttons.add(new Floor(bu, bd));
        }
        Button bt = new FloorButton(manager, 9, Direction.DOWN);
        buttons.add(new Floor(null, bt));

        return buttons;
    }

    private static List<Button> addButtonsToLift(NotifiableLift lift) {
        List<Button> buttons = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            LiftFloorButton button = new LiftFloorButton(lift, i);
            lift.addButton(i, button);
            buttons.add(button);
        }
        return buttons;
    }
}
