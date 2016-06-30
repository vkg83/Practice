package com.vkg.pactice.arrays;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SparseCube {
    Map<Point3D, Integer> values = new HashMap<Point3D, Integer>();
    public long query(Point3D p1, Point3D p2) {
        long result = 0;
        for (Point3D v: values.keySet()) {
            if(v.isBetween(p1, p2)) {
                result += values.get(v);
            }
        }
        return result;
    }

    public void update(Point3D p, int value) {
        values.put(p, value);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        for (int i = 0; i < cases; i++) {
            int cubeSize = sc.nextInt();
            int operationCount = sc.nextInt();
            SparseCube cube = new SparseCube();
            for (int j = 0; j < operationCount; j++) {
                String str = sc.next("[A-Z]+");
                if("UPDATE".equals(str)) {
                    final Point3D p = cube.readPoint3D(sc);
                    int value = sc.nextInt();
                    cube.update(p, value);
                } else {
                    final Point3D p1 = cube.readPoint3D(sc);
                    final Point3D p2 = cube.readPoint3D(sc);
                    final long value = cube.query(p1, p2);
                    System.out.println(value);
                }
            }
        }

    }

    private Point3D readPoint3D(final Scanner sc) {
        int x = sc.nextInt();
        int y = sc.nextInt();
        int z = sc.nextInt();
        return new Point3D(x, y, z);
    }
}

class Point3D {
    private final int x;
    private final int y;
    private final int z;

    public Point3D(final int x, final int y, final int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public boolean isBetween(final Point3D p1, final Point3D p2) {
        return this.x >= p1.x && this.x <= p2.x
            && this.y >= p1.y && this.y <= p2.y
            && this.z >= p1.z && this.z <= p2.z;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Point3D point3D = (Point3D) o;

        if (x != point3D.x) return false;
        if (y != point3D.y) return false;
        return z == point3D.z;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + z;
        return result;
    }
}