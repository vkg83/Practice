package com.vkg.elevator;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ElevatorClient {

    public static void main(String[] args) {
        List<VanController> controllers = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            ElevatorVan v = new ElevatorVan();
            controllers.add(new VanController(v));
        }

        ElevatorRequestScheduler scheduler = new ElevatorRequestScheduler(controllers);
        List<Button> floorButtons = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Button b = new Button(scheduler, i);
            floorButtons.add(b);
        }

        Scanner sc = new Scanner(System.in);
        int floor;
        do {
            floor = sc.nextInt();
            if (floor >= 0 && floor < floorButtons.size()) {
                floorButtons.get(floor).press();
            }
        } while (floor >= 0);
    }
}
