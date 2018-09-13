package org.xingyi.utils;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PathAndT<?> pathAndT = (PathAndT<?>) o;
        return Objects.equals(path, pathAndT.path) &&
                Objects.equals(t, pathAndT.t);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, t);
    }
}
