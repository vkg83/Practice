package com.vkg.pactice.tpt.simulation;

public interface Container {
    boolean isPointInside(Point point);
    Point normalizePointWithBoundary(Point p);
}
