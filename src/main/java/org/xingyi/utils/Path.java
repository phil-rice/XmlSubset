package org.xingyi.utils;

import java.util.Objects;

public class Path {
    public Path(String pathFragment) {
        this.pathFragment = pathFragment;
    }

    String pathFragment;

    @Override
    public String toString() {
        return pathFragment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Path path = (Path) o;
        return Objects.equals(pathFragment, path.pathFragment);
    }

    @Override
    public int hashCode() {

        return Objects.hash(pathFragment);
    }
}
