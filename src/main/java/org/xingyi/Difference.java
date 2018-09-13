package org.xingyi;

import org.xingyi.utils.Path;

import java.util.List;

public class Difference<T> {
    List<Path> path;
    private final T left;
    private final T right;
    private String reason;

    public Difference(List<Path> path, T left, T right, String reason) {
        this.path = path;
        this.left = left;
        this.right = right;
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "Difference{" +
                "path=" + path +
                ", reason='" + reason + '\'' +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
