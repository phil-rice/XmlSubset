package org.xingyi.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface ListOps {

    static <T, To> List<To> mapWithIndex(List<T> from, Function2<T, Integer, To> fn) {
        List<To> result = new ArrayList<>();
        for (int i = 0; i < from.size(); i++)
            result.add(fn.apply(from.get(i), i));
        return result;
    }

    static <T> List<T> add(List<T> list, T... t) {
        ArrayList<T> result = new ArrayList<>();
        result.addAll(list);
        result.addAll(Arrays.asList(t));
        return result;
    }
    static <T> List<T> append(List<T> one, List<T> two) {
        ArrayList<T> result = new ArrayList<>(one);
        result.addAll(two);
        return result;
    }


    static <From, To> List<To> map(List<From> list, Function<From, To> fn) {
        ArrayList<To> result = new ArrayList<To>();
        for (From f : list)
            result.add(fn.apply(f));
        return result;
    }

    static <From, To> List<To> flatMap(List<From> rawList, Function<From, Optional<To>> fn) {
        ArrayList<To> result = new ArrayList<To>();
        for (From from : rawList) {
            Optional<To> optional = fn.apply(from);
            optional.ifPresent(to -> result.add(to));
        }
        return result;
    }

    static <T> Optional<Integer> findIndexOf(T t, List<T> list, Function2<T, T, Boolean> matches) {
        for (int i = 0; i < list.size(); i++) {
            if (matches.apply( t,list.get(i)))
                return Optional.of(i);
        }
        return Optional.empty();
    }
}
