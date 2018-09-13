package org.xingyi.utils;

import java.util.List;

public class PathAndT<T extends HasChildren<T>> {
    public final List<Path> path;
    public final T t;

    public PathAndT(List<Path> path, T t) {
        this.path = path;
        this.t = t;
    }

    public List<PathAndT<T>> children() {
        return t.children(path);
    }

    @Override
    public String toString() {
        return "PathAnd{" + path + " ==> " + t + '}';
    }
}
