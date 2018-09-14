package org.xingyi.xml;

import org.junit.Assert;
import org.junit.Test;
import org.xingyi.utils.Path;
import org.xingyi.utils.PathAndT;
import org.xingyi.utils.PathItem;

import javax.xml.soap.Node;

import java.util.Arrays;

import static org.mockito.Mockito.mock;

public class NameAndValueSpec {

    org.w3c.dom.Node node = mock(Node.class);
    NameAndValue nameAndValue = new NameAndValue("someName", node);
    PathItem a = new PathItem("a");
    PathItem b = new PathItem("b");
    PathItem someName = new PathItem("someName");
    PathItem myName = new PathItem("myName");

    @Test
    public void testToPathAndXml() {
        Path pathSoFar = new Path(Arrays.asList(a, b));
        Assert.assertEquals(new PathAndT(new Path(Arrays.asList(a, b, myName, someName)), new Xml(node)), nameAndValue.toPathAndXml(pathSoFar, "myName"));
    }
}
