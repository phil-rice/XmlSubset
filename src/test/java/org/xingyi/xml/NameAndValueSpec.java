package org.xingyi.xml;

import org.junit.Assert;
import org.junit.Test;
import org.xingyi.utils.Path;
import org.xingyi.utils.PathAndT;

import javax.xml.soap.Node;

import java.util.Arrays;

import static org.mockito.Mockito.mock;

public class NameAndValueSpec {

    org.w3c.dom.Node node = mock(Node.class);
    NameAndValue nameAndValue = new NameAndValue("someName", node);
    Path a = new Path("a");
    Path b = new Path("b");
    Path someName = new Path("someName");
    Path myName = new Path("myName");

    @Test
    public void testToPathAndXml() {
        Assert.assertEquals(new PathAndT(Arrays.asList(a, b, myName,someName ), new Xml(node)), nameAndValue.toPathAndXml(Arrays.asList(a, b), "myName"));
    }
}
