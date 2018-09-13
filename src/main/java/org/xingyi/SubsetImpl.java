package org.xingyi;

import org.xingyi.utils.HasChildAndEquals;
import org.xingyi.utils.ListOps;
import org.xingyi.utils.OptionalOps;
import org.xingyi.utils.PathAndT;

import java.util.List;
import java.util.Optional;

public class SubsetImpl<T extends HasChildAndEquals<T>> implements Subset<T> {

    private boolean debug = false;

    public List<Difference<T>> isASubset(PathAndT<T> one, PathAndT<T> two) {
        if (debug) System.out.println("isASubset " + one + " ---------- " + two);
        List<Difference<T>> diffs = one.t.isEqualIgnoringChildren(one.path, two.t);
        return diffs.isEmpty() ? checkChildren(one, two) : diffs;
    }

    List<Difference<T>> checkChildren(PathAndT<T> one, PathAndT<T> two) {
        if (debug) System.out.println("checkChildren (" + one + "---" + two);
        return ListOps.<PathAndT<T>, Difference<T>>flatMap(one.children(), oneChild -> isIn(oneChild, two));
    }

    private Optional<Difference<T>> isIn(PathAndT<T> oneChild, PathAndT<T> two) {
        Optional<Integer> indexOfChildTwo = ListOps.findIndexOf(oneChild, two.children(), (childOne, childtwo) -> isASubset(childOne, childtwo).isEmpty());
        if (debug) System.out.println("isIn " + indexOfChildTwo + "(" + oneChild + "---" + two);
        return OptionalOps.invert(indexOfChildTwo, () -> new Difference<T>(oneChild.path, oneChild.t, two.t, "could not find the left item in the list on the right"));
    }
}
