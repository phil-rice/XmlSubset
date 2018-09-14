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
        assertEquals(Arrays.asList(1, 3, 5), ListOps.flatMap(Arrays.asList(1, 2, 3, 4, 5), i -> i % 2 == 1 ? Optional.of(i) : Optional.empty()));
        assertEquals(Arrays.asList(2, 4), ListOps.flatMap(Arrays.asList(1, 2, 3, 4, 5), i -> i % 2 == 0 ? Optional.of(i) : Optional.empty()));
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
        assertEquals(Optional.of(0), ListOps.findIndexOf(1, Arrays.asList(2, 4, 6, 8), (l, r) -> l + 1 == r));
        assertEquals(Optional.of(1), ListOps.findIndexOf(3, Arrays.asList(2, 4, 6, 8), (l, r) -> l + 1 == r));
        assertEquals(Optional.of(2), ListOps.findIndexOf(5, Arrays.asList(2, 4, 6, 8), (l, r) -> l + 1 == r));
        assertEquals(Optional.of(3), ListOps.findIndexOf(7, Arrays.asList(2, 4, 6, 8), (l, r) -> l + 1 == r));
        assertEquals(Optional.empty(), ListOps.findIndexOf(2, Arrays.asList(2, 4, 6, 8), (l, r) -> l + 1 == r));
    }

    @Test
    public void testFilter() {
        assertEquals(Arrays.asList(1, 2, 3), ListOps.filter(Arrays.asList(1, 2, 3, 4, 5), x -> x < 4));
        assertEquals(Arrays.asList(), ListOps.filter(Arrays.asList(1, 2, 3, 4, 5), x -> x < 0));
    }


}
