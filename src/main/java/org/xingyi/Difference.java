package org.xingyi;

import org.xingyi.utils.Path;

import java.util.List;

public class Difference<T> {
    final public Path path;
    final public T left;
    final public T right;
    final public String reason;

    public Difference(Path path, T left, T right, String reason) {
        this.path = path;
        this.left = left;
        this.right = right;
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "Difference{" + path +
                ", reason='" + reason +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
