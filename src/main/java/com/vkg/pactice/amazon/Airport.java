package com.vkg.pactice.amazon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Airport {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int flightCount = sc.nextInt();
        ArrayList<FlightTime> flightTimes = new ArrayList<>();
        for (int i = 0; i < flightCount; i++) {
            flightTimes.add(readFlightTime(sc, true));
            flightTimes.add(readFlightTime(sc, false));
        }
        Collections.sort(flightTimes);
        int flights = 0;
        int platforms = 0;
        for (int i = 0; i < flightTimes.size(); i++) {
            if(flightTimes.get(i).start) {
                flights++;
                if(platforms < flights) {
                    platforms = flights;
                }
            } else {
                flights--;
            }
        }

        System.out.println(platforms);
    }

    private static FlightTime readFlightTime(final Scanner sc, boolean start) {
        String arr[] = sc.next("\\d*:\\d*").split(":");
        int hr = Integer.parseInt(arr[0], 10);
        int mm = Integer.parseInt(arr[1], 10);
        return new FlightTime(hr, mm, start);
    }
}

class FlightTime implements Comparable<FlightTime> {
    int hr;
    int mm;
    boolean start;

    public FlightTime(final int hr, final int mm, final boolean start) {
        this.hr = hr;
        this.mm = mm;
        this.start = start;
    }

    @Override
    public int compareTo(final FlightTime o) {
        return 60 * (hr - o.hr) + mm - o.mm;
    }
}
