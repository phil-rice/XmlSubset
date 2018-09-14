package org.xingyi.utils;

import java.util.List;

public interface HasChildren<T extends HasChildren<T>> {
    List<PathAndT<T>> children(Path path);
}
