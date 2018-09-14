package org.xingyi.utils;

import org.xingyi.Difference;

import java.util.List;
import java.util.Optional;

public interface HasChildAndEquals<T extends HasChildren<T>> extends HasChildren<T> {
    Optional<String> isEqualIgnoringChildren(T other);
}
