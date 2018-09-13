package org.xingyi.utils;

import org.xingyi.Difference;
import org.xingyi.utils.HasChildren;
import org.xingyi.utils.Path;

import java.util.List;

public interface HasChildAndEquals<T extends HasChildren<T>> extends HasChildren<T> {
    List<Difference<T>> isEqualIgnoringChildren(List<Path> path, T other);
}
