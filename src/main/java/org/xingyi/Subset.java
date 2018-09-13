package org.xingyi;

import org.xingyi.utils.*;

import java.util.List;

public interface Subset<T extends HasChildAndEquals<T>> {
    List<Difference<T>> isASubset(PathAndT<T> one, PathAndT<T> two);

}


