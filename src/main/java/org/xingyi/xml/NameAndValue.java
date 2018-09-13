package org.xingyi.xml;

import org.w3c.dom.Node;
import org.xingyi.utils.ListOps;
import org.xingyi.utils.Path;
import org.xingyi.utils.PathAndT;

import java.util.List;

public class NameAndValue {
    String name;
    Node value;

    public NameAndValue(String name, Node value) {
        this.name = name;
        this.value = value;
    }

    PathAndT<Xml> toPathAndXml(List<Path> pathSoFar, String myName) {
        return new PathAndT(ListOps.add(pathSoFar, new Path(myName), new Path(name)), new Xml(value));
    }

    @Override
    public String toString() { return "Name'" + name + "->" + value + '}'; }
}
