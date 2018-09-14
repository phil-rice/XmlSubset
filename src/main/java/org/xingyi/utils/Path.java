package org.xingyi.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Path {
    public final List<PathItem> path;

    public Path(List<PathItem> path) {
        this.path = path;
    }

    public Path() {
        this(new ArrayList<>());
    }


    @Override
    public String toString() { return "Path{" + path + '}'; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Path path1 = (Path) o;
        return Objects.equals(path, path1.path);
    }

    @Override
    public int hashCode() { return Objects.hash(path); }

    public Path add(String myName, String name) {
        return new Path(ListOps.add(path, new PathItem(myName), new PathItem(name)));
    }
}
