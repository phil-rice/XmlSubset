package org.xingyi.xml;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xingyi.*;
import org.xingyi.utils.ListOps;
import org.xingyi.utils.PathAndT;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringBufferInputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class XmlOps {

    public static DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

    public static Document parseXml(String xml) {
        builderFactory.setIgnoringElementContentWhitespace(true);
        try {
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            return builder.parse(new StringBufferInputStream(xml));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String prettyPrint(Node doc) {
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
//            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.setOutputProperty("omit-xml-declaration", "yes");
            StreamResult result = new StreamResult(new StringWriter());
            DOMSource source = new DOMSource(doc);
            transformer.transform(source, result);
            String xmlString = result.getWriter().toString();
            return xmlString.trim();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    static List<Difference<Xml>> isASubset(Xml one, Xml two) {
        Subset<Xml> subset = new SubsetImpl<>(x -> prettyPrint(x.node));
        return subset.isASubset(new PathAndT<Xml>(new ArrayList<>(), one), new PathAndT<Xml>(new ArrayList<>(), two));
    }

    static List<Difference<Xml>> isASubset(String one, String two) {
        return isASubset(new Xml(parseXml(one)), new Xml(parseXml(two)));
    }

    static public void main(String[] args) throws IOException, SAXException {
        Document xml1 = parseXml("<hello name1='value1' name2='values2'><a elem='one'></a></hello>");
        Document xml2 = parseXml("<hello name1='value1' name2='values2' ><b /><a elem='one'/></hello>");

//        System.out.println(prettyPrint(xml1));
//        System.out.println(new Xml(xml1.getFirstChild()));
        System.out.println(isASubset(Xml.fromDocument(xml1), Xml.fromDocument(xml2)));
    }

    static List<NameAndValue> childElements(Node node) {
        ArrayList<NameAndValue> values = new ArrayList<>();
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            values.add(new NameAndValue(item.getNodeName(), item));
        }
        return values;

    }

    static ArrayList<NameAndValue> attributes(Node node) {
        NamedNodeMap attributes = node.getAttributes();
        ArrayList<NameAndValue> values = new ArrayList<>();
        if (attributes != null)
            for (int i = 0; i < attributes.getLength(); i++) {
                Node item = attributes.item(i);
                values.add(new NameAndValue("@" + item.getNodeName(), item));
            }
        return values;
    }


}

