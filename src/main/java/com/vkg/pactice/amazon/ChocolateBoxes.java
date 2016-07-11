package com.vkg.pactice.amazon;

import java.util.Scanner;

public class ChocolateBoxes {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int noOfBoxes = sc.nextInt();

        Box [] boxes = new Box[noOfBoxes];

        int prevCount = 0;
        for (int i = 0; i < noOfBoxes; i++) {
            int count = prevCount + sc.nextInt();
            boxes[i] = new Box(prevCount + 1, count);
            prevCount = count;
        }

        int countQuestion = sc.nextInt();

        for (int i = 0; i < countQuestion; i++) {
            int index = sc.nextInt();
            int boxIndex = getBoxIndex(boxes, index);
            System.out.println(boxIndex + 1);
        }

    }

    private static int getBoxIndex(final Box[] boxes, final int index) {
        int low  = 0;
        int high = boxes.length - 1;

        int mid = 0;
        while(low <= high) {
            mid = (low + high) / 2;
            if(boxes[mid].hasChocolate(index)) {
                break;
            }
            if(boxes[mid].before(index)) {
                low = mid + 1;
            } else if(boxes[mid].after(index)) {
                high = mid - 1;
            }
        }
        return mid;
    }
}

class Box {
    private int start;
    private int end;

    public Box(final int start, final int end) {
        this.start = start;
        this.end = end;
    }

    public boolean hasChocolate(final int index) {
        return index >= start && index <= end;
    }

    public boolean before(final int index) {
        return end < index;
    }

    public boolean after(final int index) {
        return start > index;
    }
}
