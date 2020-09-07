package com.vkg.pactice.calendar;

/**
 * Created by Vishnu on 8/19/2018.
 */
public class TimeOffset {
    private int day;
    private int hour;
    private int minute;
    private boolean weekDay;
    private int shift;


    public static void main(String[] args) {
        TimeOffset o1 = new TimeOffset();
        o1.day=0;
        o1.weekDay = true;

        TimeOffset o2 = new TimeOffset();
        o2.day=0;
        o2.shift = 3;
        for(int i = 0; i< 34; i++) {
            o1.day = i;
            System.out.println(o1.calcDay(3));
        }

    }


    private int calcDay(int s) {
        if(weekDay) {
            return (day % 7 < s ? day + 7 : day) - s;
        }
        return day;
    }
}

/**
 *              0   1   2   3
 *  4   5   6   7   8   9   10
 *  11  12  13  14  15  16  17
 *  18  19  20  21  22  23  24
 *  25  26  27  28  29  30
 *
 *              3   4   5   6
 *  0   1   2   10  11  12  13
 *  7   8   9   17  18  19  20
 *  14  15  16  24  25  26  27
 *  21  22  23  31  32  33
 *
 *              3   4   5   6
 *  0   1   2   3   4   5   6
 *  0   1   2   3   4   5   6
 *  0   1   2   3   4   5   6
 *  0   1   2   3   4   5
 */

