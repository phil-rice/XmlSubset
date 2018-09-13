package org.xingyi.utils;

public interface Function2<T1, T2, To> {
    To apply(T1 t1, T2 t2);
}
