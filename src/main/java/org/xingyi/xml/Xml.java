package org.xingyi.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xingyi.Difference;
import org.xingyi.utils.*;

import java.util.*;

public class Xml implements HasChildAndEquals<Xml> {

    Node node;

    public static Xml fromDocument(Document document) {
        return new Xml(document.getFirstChild());
    }

    public Xml(Node node) {
        this.node = node;
    }

    @Override
    public List<PathAndT<Xml>> children(Path path) {
        return ListOps.map(
                ListOps.append(XmlOps.attributes(node),
                        XmlOps.childElements(node)),
                nameAndValue -> nameAndValue.toPathAndXml(path, node.getNodeName()));
    }

    @Override
    public Optional<String> isEqualIgnoringChildren(Xml other) {
        return OptionalOps.fromBoolean(
                !node.getNodeName().equals(other.node.getNodeName()),
                () -> "Node name mismatch. Looking for " + node.getNodeName() + " found " + other.node.getNodeName());
    }

    @Override
    public String toString() {
//        List<PathAndT<Xml>> children = children(new ArrayList<>());
        return "Xml{" +
                "node=" + XmlOps.prettyPrint(node) +
//                "children=" + children +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Xml xml = (Xml) o;
        return Objects.equals(node, xml.node);
    }

    @Override
    public int hashCode() {
        return Objects.hash(node);
    }
}
