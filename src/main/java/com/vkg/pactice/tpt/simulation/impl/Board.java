package com.vkg.pactice.tpt.simulation.impl;

import com.vkg.pactice.tpt.simulation.Container;
import com.vkg.pactice.tpt.simulation.Point;

public class Board implements Container {
    private Point bottomLeft;
    private Point topRight;

    public Board(final Point bottomLeft, final Point topRight) {
        this.bottomLeft = bottomLeft;
        this.topRight = topRight;
    }

    @Override
    public boolean isPointInside(final Point point) {
        return false;
    }

    @Override
    public Point normalizePointWithBoundary(final Point p) {
        return null;
    }
}
