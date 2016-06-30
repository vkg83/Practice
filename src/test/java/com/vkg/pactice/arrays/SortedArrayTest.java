package com.vkg.pactice.arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortedArrayTest {

    private SortedArray sortedArray;

    @Before
    public void setUp() throws Exception {
        sortedArray = new SortedArray();
    }

    @Test
    public void shouldIntersectDuplicates() throws Exception {
        verifyIntersection(Arrays.asList(1, 2, 3, 3, 4, 5, 6), Arrays.asList(3, 3, 5), Arrays.asList(3, 3, 5));
    }

    @Test
    public void shouldIntersect() throws Exception {
        verifyIntersection(Arrays.asList(1, 2, 3, 3, 4, 5, 6), Arrays.asList(3, 5), Arrays.asList(3, 5));
    }

    @Test
    public void shouldUnionDuplicates() throws Exception {
        verifyUnion(Arrays.asList(1, 2, 3, 3, 4, 5, 6), Arrays.asList(3, 3, 5), Arrays.asList(1, 2, 3, 3, 4, 5, 6));
    }

    @Test
    public void shouldUnion() throws Exception {
        verifyUnion(Arrays.asList(1, 2, 3, 3, 4, 5, 6), Arrays.asList(3, 5, 7), Arrays.asList(1, 2, 3, 3, 4, 5, 6, 7));
    }

    private void verifyIntersection(final List<Integer> arrA, final List<Integer> arrB, final List<Integer> expected) {
        final ArrayList<Integer> actual = sortedArray.intersect(arrA, arrB);
        Assert.assertEquals(expected, actual);
    }
    private void verifyUnion(final List<Integer> arrA, final List<Integer> arrB, final List<Integer> expected) {
        final ArrayList<Integer> actual = sortedArray.union(arrA, arrB);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldRemoveDuplicate() throws Exception {
        final ArrayList<Integer> integers = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 3, 4, 5, 6, 7, 7, 7,7, 8,9,9));
        sortedArray.removeDuplicates(integers);
        System.out.println(integers);

    }
}