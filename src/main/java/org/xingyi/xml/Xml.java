package org.xingyi.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xingyi.Difference;
import org.xingyi.utils.HasChildAndEquals;
import org.xingyi.utils.ListOps;
import org.xingyi.utils.Path;
import org.xingyi.utils.PathAndT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Xml implements HasChildAndEquals<Xml> {

    Node node;

    public static Xml fromDocument(Document document){
        return new Xml(document.getFirstChild());
    }

    public Xml(Node node) {
        this.node = node;
    }

    @Override
    public List<PathAndT<Xml>> children(List<Path> path) {
        return ListOps.map(ListOps.append(XmlOps.attributes(node), XmlOps.childElements(node)), nameAndValue -> nameAndValue.toPathAndXml(path, node.getNodeName()));
    }

    @Override
    public List<Difference<Xml>> isEqualIgnoringChildren(List<Path> path, Xml other) {
        if (node.getNodeName().equals(other.node.getNodeName()))
            return new ArrayList<>();
        return Arrays.asList(new Difference<>(path, this, other, "Node name mismatch. Looking for " + node.getNodeName() + " found " + other.node.getNodeName()));
    }

    @Override
    public String toString() {
        return "Xml{" +
                "node=" + node +
                "children=" + children(new ArrayList<>()) +
                '}';
    }
}
