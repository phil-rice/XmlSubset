package org.xingyi.utils;

public class Path {
    public Path(String pathFragment) {
        this.pathFragment = pathFragment;
    }

    String pathFragment;

    @Override
    public String toString() {
        return pathFragment;
    }
}
