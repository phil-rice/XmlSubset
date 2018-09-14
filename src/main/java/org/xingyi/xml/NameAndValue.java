package org.xingyi.xml;

import org.w3c.dom.Node;
import org.xingyi.utils.ListOps;
import org.xingyi.utils.Path;
import org.xingyi.utils.PathItem;
import org.xingyi.utils.PathAndT;

import java.util.List;
import java.util.Objects;

public class NameAndValue {
    String name;
    Node value;

    public NameAndValue(String name, Node value) {
        this.name = name;
        this.value = value;
    }

    PathAndT<Xml> toPathAndXml(Path pathSoFar, String myName) {
        return new PathAndT(pathSoFar.add(myName, name), new Xml(value));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NameAndValue that = (NameAndValue) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }

    @Override
    public String toString() { return "Name'" + name + "->[" + value + "]}"; }
}
