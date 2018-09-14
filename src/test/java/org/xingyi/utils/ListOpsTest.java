package org.xingyi.utils;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class ListOpsTest {

    @Test
    public void testMapWithIndex() {
        assertEquals(Arrays.asList(), ListOps.mapWithIndex(Arrays.<String>asList(), (s, i) -> s + i));
        assertEquals(Arrays.asList("a0", "b1"), ListOps.mapWithIndex(Arrays.asList("a", "b"), (s, i) -> s + i));
    }

    @Test
    public void testMapWithIndexDoesntAffectInput() {
        List<String> from = Arrays.asList("a", "b");
        assertEquals(Arrays.asList("a0", "b1"), ListOps.mapWithIndex(from, (s, i) -> s + i));
        assertEquals(Arrays.asList("a", "b"), from);
    }

    @Test
    public void testAdd() {
        assertEquals(Arrays.asList(1, 2), ListOps.add(Arrays.asList(1, 2)));
        assertEquals(Arrays.asList(1, 2, 3, 4), ListOps.add(Arrays.asList(1, 2), 3, 4));
    }

    @Test
    public void testAddDoesntAffectInput() {
        List<Integer> list = Arrays.asList(1, 2);
        assertEquals(Arrays.asList(1, 2, 3, 4), ListOps.add(list, 3, 4));
        assertEquals(Arrays.asList(1, 2), list);
    }

    @Test
    public void testMap() {
        assertEquals(Arrays.asList(), ListOps.map(Arrays.<Integer>asList(), (i) -> i.toString()));
        assertEquals(Arrays.asList("1", "2"), ListOps.map(Arrays.asList(1, 2), (i) -> i.toString()));
    }

    @Test
    public void testMapDoesntAffectInput() {
        List<Integer> list = Arrays.asList(1, 2);
        assertEquals(Arrays.asList("1", "2"), ListOps.map(list, (i) -> i.toString()));
        assertEquals(Arrays.asList(1, 2), list);
    }

    @Test
    public void testFlatMap() {
        assertEquals(Arrays.asList(1, 3, 5), ListOps.flatMap(Arrays.asList(1, 2, 3, 4, 5), i -> i % 2 == 1 ? Arrays.asList(i) : Arrays.asList()));
        assertEquals(Arrays.asList(2, 4), ListOps.flatMap(Arrays.asList(1, 2, 3, 4, 5), i -> i % 2 == 0 ? Arrays.asList(i) : Arrays.asList()));
    }

    @Test
    public void testAppend() {
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), ListOps.append(Arrays.asList(1, 2, 3), Arrays.asList(4, 5)));
        assertEquals(Arrays.asList(1, 2, 3), ListOps.append(Arrays.asList(1, 2, 3), Arrays.asList()));
        assertEquals(Arrays.asList(4, 5), ListOps.append(Arrays.asList(), Arrays.asList(4, 5)));
        assertEquals(Arrays.asList(), ListOps.append(Arrays.asList(), Arrays.asList()));
    }

    @Test
    public void testAppendDoesntAffectEitherList() {
        List<Integer> list123 = Arrays.asList(1, 2, 3);
        List<Integer> list45 = Arrays.asList(4, 5);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), ListOps.append(list123, list45));
        assertEquals(Arrays.asList(1, 2, 3), list123);
        assertEquals(Arrays.asList(4, 5), list45);
    }

    @Test
    public void testFindIndexOf() {
        assertEquals(Optional.of(0), ListOps.findIndexOf(Arrays.asList(2, 4, 6, 8), l -> l + 1 == 3));
        assertEquals(Optional.of(1), ListOps.findIndexOf(Arrays.asList(2, 4, 6, 8), l -> l + 1 == 5));
        assertEquals(Optional.of(2), ListOps.findIndexOf(Arrays.asList(2, 4, 6, 8), l -> l + 1 == 7));
        assertEquals(Optional.of(3), ListOps.findIndexOf(Arrays.asList(2, 4, 6, 8), l -> l + 1 == 9));
        assertEquals(Optional.empty(), ListOps.findIndexOf(Arrays.asList(2, 4, 6, 8), l -> l + 1 == 12));
    }

    @Test
    public void testFilter() {
        assertEquals(Arrays.asList(1, 2, 3), ListOps.filter(Arrays.asList(1, 2, 3, 4, 5), x -> x < 4));
        assertEquals(Arrays.asList(), ListOps.filter(Arrays.asList(1, 2, 3, 4, 5), x -> x < 0));
    }

    @Test
    public void testFindLargest() {
        assertEquals(Optional.empty(), ListOps.findSmallest(Arrays.<Integer>asList(), i -> i));
        assertEquals(Optional.of("1"), ListOps.findSmallest(Arrays.asList("1", "2", "12", "11"), Integer::parseInt));
        assertEquals(Optional.of("12"), ListOps.findSmallest(Arrays.asList("1", "2", "12", "11"), i-> -Integer.parseInt(i)));
    }
}
