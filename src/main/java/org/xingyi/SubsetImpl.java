package org.xingyi;

import org.xingyi.utils.HasChildAndEquals;
import org.xingyi.utils.ListOps;
import org.xingyi.utils.OptionalOps;
import org.xingyi.utils.PathAndT;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class SubsetImpl<T extends HasChildAndEquals<T>> implements Subset<T> {

    Function<T, String> printer;

    public SubsetImpl(Function<T, String> printer) {
        this.printer = printer;
    }

    public SubsetImpl() {
        this(Object::toString);
    }

    private boolean debug = false;

    <X> void debug(PathAndT<T> t, String message) {
        if (!debug)
            return;

        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < t.path.path.size(); i++)
            buffer.append("  ");
        System.out.println(buffer + message);
    }

    public List<Difference<T>> isASubset(PathAndT<T> one, PathAndT<T> two) {
        debug(one, "isASubset " + one + " ---------- " + two);
        return OptionalOps.fold(one.t.isEqualIgnoringChildren(two.t),
                r -> Arrays.asList(new Difference<T>(one.path, one.t, two.t, r)),
                () -> checkChildren(one, two));
    }

    List<Difference<T>> checkChildren(PathAndT<T> one, PathAndT<T> two) {
        debug(one, "checkChildren (" + one + "---" + two);
        return ListOps.<PathAndT<T>, Difference<T>>flatMap(one.children(), oneChild -> isIn(oneChild, two));
    }

    private Optional<Difference<T>> isIn(PathAndT<T> oneChild, PathAndT<T> two) {
        Optional<Integer> indexOfChildTwo = ListOps.findIndexOf(oneChild, two.children(), (childOne, childtwo) -> isASubset(childOne, childtwo).isEmpty());
        Optional<Difference<T>> result = OptionalOps.invert(indexOfChildTwo, () -> new Difference<T>(oneChild.path, oneChild.t, two.t, "could not find [" + printer.apply(oneChild.t) + "] in [" + printer.apply(two.t) + "]"));
        debug(oneChild, "isIn " + indexOfChildTwo + "(" + oneChild + "---" + two + "           left: " + printer.apply(oneChild.t) + "rightChildren: " + two.children() + " result " + result);
        return result;
    }
}
