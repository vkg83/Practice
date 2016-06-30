package com.vkg.pactice.placement;

public class Cell {
    private boolean occupied;

    public boolean isOccupied() {
        return occupied==true;
    }

    public boolean isBlank() {
        return occupied==false;
    }
    public void setOccupied(final boolean occupied) {
        this.occupied = occupied;
    }
}
