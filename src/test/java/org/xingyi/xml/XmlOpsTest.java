package org.xingyi.xml;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xingyi.utils.ListOps;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.xingyi.xml.XmlOps.*;

public class XmlOpsTest {

    Document someXml = parseXml("<hello a='123' b='234' ><first /><second /></hello>");

    @Test
    public void testParseAndPrettyPrintDontThrowExceptions() {
        assertEquals("<hello/>", prettyPrint(parseXml("<hello />")).replaceAll("\\s+", ""));
    }

    @Test
    public void testAttributes() {
        Node hello = someXml.getElementsByTagName("hello").item(0);
        NamedNodeMap attributes = hello.getAttributes();
        Node helloa = attributes.item(0);
        Node hellob = attributes.item(1);
        assertEquals(Arrays.asList(new NameAndValue("@a", helloa), new NameAndValue("@b", hellob)), XmlOps.attributes(hello));
    }

    @Test
    public void testChildElementsWhenNoCHildren() {
        Node hello = parseXml("<hello></hello>").getElementsByTagName("hello").item(0);
        assertEquals(Arrays.asList(), XmlOps.childElements(hello));
    }

    @Test
    public void testChildElements() {
        Node hello = someXml.getElementsByTagName("hello").item(0);
        Node first = hello.getChildNodes().item(0);
        Node second = hello.getChildNodes().item(1);
        assertEquals(Arrays.asList(new NameAndValue("first", first), new NameAndValue("second", second)), XmlOps.childElements(hello));
    }

    @Test
    public void testIsSubsetReturnEmptyListWhenASubset() {
        assertEquals(Arrays.asList(), XmlOps.isASubset("<hello />", "<hello />"));
        assertEquals(Arrays.asList(), XmlOps.isASubset("<hello />", "<hello><a/></hello>"));
        assertEquals(Arrays.asList(), XmlOps.isASubset("<hello><a/></hello>", "<hello><a/><b/></hello>"));
        assertEquals(Arrays.asList(), XmlOps.isASubset("<hello></hello>", "<hello a='123'></hello>"));
        assertEquals(Arrays.asList(), XmlOps.isASubset("<hello a='123' ></hello>", "<hello a='123'></hello>"));
        assertEquals(Arrays.asList(), XmlOps.isASubset("<hello a='123' ></hello>", "<hello a='123'></hello>"));
    }

    List<String> checkIsSubset(String one, String two) {
        return ListOps.map(XmlOps.isASubset(one, two), d -> d.reason);
    }

    @Test
    public void testIsSubsetWhenNotSubset() {
        assertEquals(Arrays.asList("could not find [<hello><a/></hello>] in [<hello/>]"), checkIsSubset("<hello><a /></hello>", "<hello />"));
//        assertEquals(Arrays.asList("could not find [<hello><a /></hello>] in [<hello></hello>]"), checkIsSubset("<hello><a /><b /></hello>", "<hello />"));
//        assertEquals(Arrays.asList("[#document, hello]:could not find the left item in the list on the right"), checkIsSubset("<hello><a /><b /></hello>", "<hello />"));

    }

}
