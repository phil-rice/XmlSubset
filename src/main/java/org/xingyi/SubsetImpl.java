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

    private List<Difference<T>> isIn(PathAndT<T> oneChild, PathAndT<T> twoParent) {
        List<PathAndT<T>> candidates = ListOps.filter(twoParent.children(), (childtwo) -> !oneChild.t.isEqualIgnoringChildren(childtwo.t).isPresent());
        if (candidates.size() == 0)
            return Arrays.asList(new Difference<T>(oneChild.path, oneChild.t, twoParent.t, "could not find [" + printer.apply(oneChild.t) + "] in [" + printer.apply(twoParent.t) + "]"));
        List<List<Difference<T>>> differences = ListOps.map(candidates, childtwo -> isASubset(oneChild, childtwo));
        Optional<Integer> indexOfChild = ListOps.findIndexOf(differences, List::isEmpty);
        if (indexOfChild.isPresent()) return Arrays.asList();
        return ListOps.findSmallest(differences, List::size).orElse(Arrays.asList());
    }
}

