package com.vkg.pactice.math;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class MoneyTest {
    @Test
    public void testWithZeroProportion1() {
        final Money m = Money.create(100, 1);

        final List<Money> result = m.split(Arrays.asList(98.0, .5, .6, .9), Money.STRATEGY.LARGEST_DEFICIT);
        //final List<Money> result = m.split(Arrays.asList(4.9, 10.4, 5.7, 5.7, 20.8, 3.6, 7.5, 25.5, 15.9));
        final List<Money> result1 = m.split(Arrays.asList(5.0, 11.0, 6.0, 6.0, 21.0, 4.0, 8.0, 26.0, 16.0), Money.STRATEGY.BIGGEST);

        System.out.println(Arrays.toString(result.toArray()));
    }
}

/*

Total         : 100.0, 	Values : 	 4.90	10.50	 5.60	 5.70	20.80	 3.60	 7.50	25.50	15.90
Rounded Total : 103, 	Values : 	    5	   11	    6	    6	   21	    4	    8	   26	   16
                          Diff : 	-0.10	-0.50	-0.40	-0.30	-0.20	-0.40	-0.50	-0.50	-0.10
                   Change Diff : 	 0.23	-0.17	-0.07	 0.03	 0.13	-0.07	-0.17	-0.17	 0.23
                       Changed : 	 5.13	10.33	 5.53	 5.73	20.93	 3.53	 7.33	25.33	16.13
                     New Value : 	    5	   10	    6	    6	   21	    4	    7	   25	   16
Diff: 0.000000000000001166
 New Total: 100.0000
 */