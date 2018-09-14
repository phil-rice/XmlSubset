package org.xingyi.utils;

public class PathItem {
    public PathItem(String pathFragment) {
        this.pathFragment = pathFragment;
    }

    String pathFragment;

    @Override
    public String toString() {
        return pathFragment;
    }
}
