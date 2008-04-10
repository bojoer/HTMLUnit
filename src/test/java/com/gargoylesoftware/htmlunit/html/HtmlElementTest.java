/*
 * Copyright (c) 2002-2008 Gargoyle Software Inc. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 3. The end-user documentation included with the redistribution, if any, must
 *    include the following acknowledgment:
 *
 *       "This product includes software developed by Gargoyle Software Inc.
 *        (http://www.GargoyleSoftware.com/)."
 *
 *    Alternately, this acknowledgment may appear in the software itself, if
 *    and wherever such third-party acknowledgments normally appear.
 * 4. The name "Gargoyle Software" must not be used to endorse or promote
 *    products derived from this software without prior written permission.
 *    For written permission, please contact info@GargoyleSoftware.com.
 * 5. Products derived from this software may not be called "HtmlUnit", nor may
 *    "HtmlUnit" appear in their name, without prior written permission of
 *    Gargoyle Software Inc.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL GARGOYLE
 * SOFTWARE INC. OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
 * OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.gargoylesoftware.htmlunit.html;

import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.NodeList;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebTestCase;

/**
 * Unit tests for {@link HtmlElement}.
 *
 * @version $Revision$
 * @author <a href="mailto:mbowler@GargoyleSoftware.com">Mike Bowler</a>
 * @author Denis N. Antonioli
 * @author Daniel Gredler
 * @author Ahmed Ashour
 */
public class HtmlElementTest extends WebTestCase {

    /**
     * Test hasAttribute() on an element with the attribute.
     * @throws Exception if the test fails
     */
    @Test
    public void testElementHasAttributeWith() throws Exception {
        final String content = "<html><head></head><body id='tag'>text</body></html>";
        final List<String> collectedAlerts = new ArrayList<String>();
        final HtmlPage page = loadPage(content, collectedAlerts);

        final HtmlElement node = page.getDocumentHtmlElement().getHtmlElementById("tag");
        Assert.assertEquals("Element should have attribute", true, node.hasAttribute("id"));
    }

    /**
     * Test hasAttribute() on an element without the attributes.
     * @throws Exception if the test fails
     */
    @Test
    public void testElementHasAttributeNone() throws Exception {
        final String content
            = "<html><head></head><body id='tag'>text</body></html>";
        final List<String> collectedAlerts = new ArrayList<String>();
        final HtmlPage page = loadPage(content, collectedAlerts);

        final HtmlElement node = page.getDocumentHtmlElement().getHtmlElementById("tag");
        Assert.assertEquals("Element should not have attribute", false, node.hasAttribute("foo"));
    }

    /**
     * Test hasAttribute() on an element with the attribute.
     * @throws Exception if the test fails
     */
    @Test
    public void testElementHasAttributeNSWith() throws Exception {
        final String content
            = "<html><head></head><body xmlns:ns='http://foobar' id='tag' ns:foo='bar'>text</body></html>";
        final List<String> collectedAlerts = new ArrayList<String>();
        final HtmlPage page = loadPage(content, collectedAlerts);

        final HtmlElement node = page.getDocumentHtmlElement().getHtmlElementById("tag");
        Assert.assertEquals("Element should have attribute", true, node.hasAttributeNS("http://foobar", "foo"));
    }

    /**
     * Test hasAttribute() on an element without the attributes.
     * @throws Exception if the test fails
     */
    @Test
    public void testElementHasAttributeNSNone() throws Exception {
        final String content
            = "<html><head></head><body id='tag'>text</body></html>";
        final List<String> collectedAlerts = new ArrayList<String>();
        final HtmlPage page = loadPage(content, collectedAlerts);

        final HtmlElement node = page.getDocumentHtmlElement().getHtmlElementById("tag");
        Assert.assertEquals("Element should not have attribute", false, node.hasAttributeNS("http://foobar", "foo"));
    }

    /**
     * Test getAttribute() on an element with the attribute.
     * @throws Exception if the test fails
     */
    @Test
    public void testElementGetAttributeWith() throws Exception {
        final String content
            = "<html><head></head><body id='tag'>text</body></html>";
        final List<String> collectedAlerts = new ArrayList<String>();
        final HtmlPage page = loadPage(content, collectedAlerts);

        final HtmlElement node = page.getDocumentHtmlElement().getHtmlElementById("tag");
        Assert.assertEquals("Element should have attribute", "tag", node.getAttribute("id"));
    }

    /**
     * Test getAttribute() on an element without the attributes.
     * @throws Exception if the test fails
     */
    @Test
    public void testElementGetAttributeNone() throws Exception {
        final String content
            = "<html><head></head><body id='tag'>text</body></html>";
        final List<String> collectedAlerts = new ArrayList<String>();
        final HtmlPage page = loadPage(content, collectedAlerts);

        final HtmlElement node = page.getDocumentHtmlElement().getHtmlElementById("tag");
        Assert.assertEquals("Element should not have attribute", "", node.getAttribute("foo"));
    }
    
    /**
     * Test getAttribute() on an element with the attribute.
     * @throws Exception if the test fails
     */
    @Test
    public void testElementGetAttributeNSWith() throws Exception {
        final String content
            = "<html><head></head><body xmlns:ns='http://foobar' id='tag' ns:foo='bar'>text</body></html>";
        final List<String> collectedAlerts = new ArrayList<String>();
        final HtmlPage page = loadPage(content, collectedAlerts);

        final HtmlElement node = page.getDocumentHtmlElement().getHtmlElementById("tag");
        Assert.assertEquals("Element should have attribute", "bar", node.getAttributeNS("http://foobar", "foo"));
    }

    /**
     * Test getAttribute() on an element without the attributes.
     * @throws Exception if the test fails
     */
    @Test
    public void testElementGetAttributeNSNone() throws Exception {
        final String content
            = "<html><head></head><body id='tag'>text</body></html>";
        final List<String> collectedAlerts = new ArrayList<String>();
        final HtmlPage page = loadPage(content, collectedAlerts);

        final HtmlElement node = page.getDocumentHtmlElement().getHtmlElementById("tag");
        Assert.assertEquals("Element should not have attribute", "", node.getAttributeNS("http://foobar", "foo"));
    }

    /**
     * Test getNamespaceURI on an attribute that has a namespace.
     * @throws Exception if the test fails
     */
    @Test
    public void testElementGetNamespaceURIWith() throws Exception {
        final String content
            = "<html><head></head><body xmlns:ns='http://foobar' id='tag' ns:foo='bar'>text</body></html>";
        final List<String> collectedAlerts = new ArrayList<String>();
        final HtmlPage page = loadPage(content, collectedAlerts);

        final HtmlElement node = page.getDocumentHtmlElement().getHtmlElementById("tag");
        for (final HtmlAttr attr : node.getAttributesCollection()) {
            if (attr.getName().equals("ns:foo")) {
                Assert.assertEquals("Element should have a namespace URI", "http://foobar", attr.getNamespaceURI());
                return;
            }
        }
        Assert.assertFalse("Attribute ns:foo not found.", true);
    }

    /**
     * Test getNamespaceURI on an attribute that has a namespace.
     * @throws Exception if the test fails
     */
    @Test
    public void testElementGetNamespaceURINone() throws Exception {
        final String content
            = "<html><head></head><body xmlns:ns='http://foobar' id='tag' ns:foo='bar'>text</body></html>";
        final List<String> collectedAlerts = new ArrayList<String>();
        final HtmlPage page = loadPage(content, collectedAlerts);

        final HtmlElement node = page.getDocumentHtmlElement().getHtmlElementById("tag");
        for (final HtmlAttr attr : node.getAttributesCollection()) {
            if (attr.getName().equals("id")) {
                Assert.assertEquals("Element should not have a namespace URI", null, attr.getNamespaceURI());
                return;
            }
        }
        Assert.assertFalse("Attribute ns:foo not found.", true);
    }

    /**
     * Test getLocalName on an attribute that has a local name.
     * @throws Exception if the test fails
     */
    @Test
    public void testElementGetLocalNameWith() throws Exception {
        final String content
            = "<html><head></head><body xmlns:ns='http://foobar' id='tag' ns:foo='bar'>text</body></html>";
        final List<String> collectedAlerts = new ArrayList<String>();
        final HtmlPage page = loadPage(content, collectedAlerts);

        final HtmlElement node = page.getDocumentHtmlElement().getHtmlElementById("tag");
        for (final HtmlAttr attr : node.getAttributesCollection()) {
            if (attr.getName().equals("ns:foo")) {
                Assert.assertEquals("Element should have a local name", "foo", attr.getLocalName());
                return;
            }
        }
        Assert.assertFalse("Attribute ns:foo not found.", true);
    }

    /**
     * Test getLocalName on an attribute that has a local name.
     * @throws Exception if the test fails
     */
    @Test
    public void testElementGetLocalNameNone() throws Exception {
        final String content
            = "<html><head></head><body xmlns:ns='http://foobar' id='tag' ns:foo='bar'>text</body></html>";
        final List<String> collectedAlerts = new ArrayList<String>();
        final HtmlPage page = loadPage(content, collectedAlerts);

        final HtmlElement node = page.getDocumentHtmlElement().getHtmlElementById("tag");
        for (final HtmlAttr attr : node.getAttributesCollection()) {
            if (attr.getName().equals("id")) {
                // This is not standard, but to change it now would break backwards compatibility.
                Assert.assertEquals("Element should not have a local name", "id", attr.getLocalName());
                return;
            }
        }
        Assert.assertFalse("Attribute ns:foo not found.", true);
    }

    /**
     * Test getPrefix on an attribute that has a prefix.
     * @throws Exception if the test fails
     */
    @Test
    public void testElementGetPrefixWith() throws Exception {
        final String content
            = "<html><head></head><body xmlns:ns='http://foobar' id='tag' ns:foo='bar'>text</body></html>";
        final List<String> collectedAlerts = new ArrayList<String>();
        final HtmlPage page = loadPage(content, collectedAlerts);

        final HtmlElement node = page.getDocumentHtmlElement().getHtmlElementById("tag");
        for (final HtmlAttr attr : node.getAttributesCollection()) {
            if (attr.getName().equals("ns:foo")) {
                Assert.assertEquals("Element should have a prefix", "ns", attr.getPrefix());
                return;
            }
        }
        Assert.assertFalse("Attribute ns:foo not found.", true);
    }

    /**
     * Test getPrefix on an attribute that has a prefix.
     * @throws Exception if the test fails
     */
    @Test
    public void testElementGetPrefixNone() throws Exception {
        final String content
            = "<html><head></head><body xmlns:ns='http://foobar' id='tag' ns:foo='bar'>text</body></html>";
        final List<String> collectedAlerts = new ArrayList<String>();
        final HtmlPage page = loadPage(content, collectedAlerts);

        final HtmlElement node = page.getDocumentHtmlElement().getHtmlElementById("tag");
        for (final HtmlAttr attr : node.getAttributesCollection()) {
            if (attr.getName().equals("id")) {
                Assert.assertEquals("Element should not have a prefix", null, attr.getPrefix());
                return;
            }
        }
        Assert.assertFalse("Attribute ns:foo not found.", true);
    }

    /**
     * Test setPrefix on an attribute that has a prefix.
     * @throws Exception if the test fails
     */
    @Test
    public void testElementSetPrefix() throws Exception {
        final String content
            = "<html><head></head><body xmlns:ns='http://foobar' id='tag' ns:foo='bar'>text</body></html>";
        final List<String> collectedAlerts = new ArrayList<String>();
        final HtmlPage page = loadPage(content, collectedAlerts);

        final HtmlElement node = page.getDocumentHtmlElement().getHtmlElementById("tag");
        for (final HtmlAttr attr : node.getAttributesCollection()) {
            if (attr.getName().equals("ns:foo")) {
                attr.setPrefix("other");
                Assert.assertEquals("Element should have a changed prefix", "other", attr.getPrefix());
                Assert.assertEquals("setPrefix should change qualified name", "other:foo", attr.getName());
                return;
            }
        }
        Assert.assertFalse("Attribute ns:foo not found.", true);
    }

    /**
     * Test setAttribute() on an element with the attribute.
     * @throws Exception if the test fails
     */
    @Test
    public void testElementSetAttributeWith() throws Exception {
        final String content
            = "<html><head></head><body id='tag'>text</body></html>";
        final List<String> collectedAlerts = new ArrayList<String>();
        final HtmlPage page = loadPage(content, collectedAlerts);

        final HtmlElement node = page.getDocumentHtmlElement().getHtmlElementById("tag");
        node.setAttribute("id", "other");
        Assert.assertEquals("Element should have attribute", "other", node.getAttribute("id"));
    }

    /**
     * Test setAttribute() on an element without the attributes.
     * @throws Exception if the test fails
     */
    @Test
    public void testElementSetAttributeNone() throws Exception {
        final String content
            = "<html><head></head><body id='tag'>text</body></html>";
        final List<String> collectedAlerts = new ArrayList<String>();
        final HtmlPage page = loadPage(content, collectedAlerts);

        final HtmlElement node = page.getDocumentHtmlElement().getHtmlElementById("tag");
        node.setAttribute("foo", "other");
        Assert.assertEquals("Element should have attribute", "other", node.getAttribute("foo"));
    }
    
    /**
     * Test setAttribute() on an element with the attribute.
     * @throws Exception if the test fails
     */
    @Test
    public void testElementSetAttributeNSWith() throws Exception {
        final String content
            = "<html><head></head><body xmlns:ns='http://foobar' id='tag' ns:foo='bar'>text</body></html>";
        final List<String> collectedAlerts = new ArrayList<String>();
        final HtmlPage page = loadPage(content, collectedAlerts);

        final HtmlElement node = page.getDocumentHtmlElement().getHtmlElementById("tag");
        node.setAttributeNS("http://foobar", "ns:foo", "other");
        Assert.assertEquals("Element should have attribute", "other", node.getAttributeNS("http://foobar", "foo"));
    }

    /**
     * Test setAttribute() on an element without the attributes.
     * @throws Exception if the test fails
     */
    @Test
    public void testElementSetAttributeNSNone() throws Exception {
        final String content
            = "<html><head></head><body id='tag'>text</body></html>";
        final List<String> collectedAlerts = new ArrayList<String>();
        final HtmlPage page = loadPage(content, collectedAlerts);

        final HtmlElement node = page.getDocumentHtmlElement().getHtmlElementById("tag");
        node.setAttributeNS("http://foobar", "ns:foo", "other");
        Assert.assertEquals("Element should not have attribute", "other", node.getAttributeNS("http://foobar", "foo"));
    }

    /**
     * Test removeAttribute() on an element with the attribute.
     * @throws Exception if the test fails
     */
    @Test
    public void testElementRemoveAttributeWith() throws Exception {
        final String content
            = "<html><head></head><body id='tag'>text</body></html>";
        final List<String> collectedAlerts = new ArrayList<String>();
        final HtmlPage page = loadPage(content, collectedAlerts);

        final HtmlElement node = page.getDocumentHtmlElement().getHtmlElementById("tag");
        node.removeAttribute("id");
        Assert.assertEquals("Element should not have removed attribute", "", node.getAttribute("id"));
    }

    /**
     * Test removeAttribute() on an element without the attributes.
     * @throws Exception if the test fails
     */
    @Test
    public void testElementRemoveAttributeNone() throws Exception {
        final String content
            = "<html><head></head><body id='tag'>text</body></html>";
        final List<String> collectedAlerts = new ArrayList<String>();
        final HtmlPage page = loadPage(content, collectedAlerts);

        final HtmlElement node = page.getDocumentHtmlElement().getHtmlElementById("tag");
        node.removeAttribute("foo");
        Assert.assertEquals("Element should not have attribute", "", node.getAttribute("foo"));
    }
    
    /**
     * Test removeAttribute() on an element with the attribute.
     * @throws Exception if the test fails
     */
    @Test
    public void testElementRemoveAttributeNSWith() throws Exception {
        final String content
            = "<html><head></head><body xmlns:ns='http://foobar' id='tag' ns:foo='bar'>text</body></html>";
        final List<String> collectedAlerts = new ArrayList<String>();
        final HtmlPage page = loadPage(content, collectedAlerts);

        final HtmlElement node = page.getDocumentHtmlElement().getHtmlElementById("tag");
        node.removeAttributeNS("http://foobar", "foo");
        Assert.assertEquals("Element should not have removed attribute", "",
            node.getAttributeNS("http://foobar", "foo"));
    }

    /**
     * Test removeAttribute() on an element without the attributes.
     * @throws Exception if the test fails
     */
    @Test
    public void testElementRemoveAttributeNSNone() throws Exception {
        final String content
            = "<html><head></head><body id='tag'>text</body></html>";
        final List<String> collectedAlerts = new ArrayList<String>();
        final HtmlPage page = loadPage(content, collectedAlerts);

        final HtmlElement node = page.getDocumentHtmlElement().getHtmlElementById("tag");
        node.removeAttributeNS("http://foobar", "foo");
        Assert.assertEquals("Element should not have attribute", "", node.getAttributeNS("http://foobar", "foo"));
    }

    /**
     * Verifies that cloned node attributes have the same initial values, but changes can be made
     * to the clone without affecting the original node, and that the id attribute is treated the
     * same as all the other attributes. See bug 1707726.
     * @throws Exception if an error occurs
     */
    @Test
    public void testClonedNodeAttributes() throws Exception {
        final String html = "<html><body id='a' title='b'><script>\n"
            + "var x = document.body.cloneNode(true);\n"
            + "alert(document.body==x);\n"
            + "alert(document.getElementById('a')==document.body);\n"
            + "alert(document.body.id);\n"
            + "alert(x.id);\n"
            + "alert(document.body.title);\n"
            + "alert(x.title);\n"
            + "x.title='c';\n"
            + "alert(document.body.title);\n"
            + "alert(x.title);\n"
            + "</script></body></html>";
        final List<String> collectedAlerts = new ArrayList<String>();
        loadPage(html, collectedAlerts);
        final String[] expectedAlerts = {"false", "true", "a", "a", "b", "b", "b", "c"};
        assertEquals(expectedAlerts, collectedAlerts);
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    public void testGetEnclosingForm() throws Exception {
        final String htmlContent = ""
            + "<html><head><title>foo</title></head><body>\n"
            + "<form id='form1'>\n"
            + "<table><tr><td><input type='text' id='foo'/></td></tr></table>\n"
            + "</form></body></html>";
        final HtmlPage page = loadPage(htmlContent);
        final HtmlForm form = (HtmlForm) page.getHtmlElementById("form1");

        final HtmlInput input = (HtmlInput) form.getHtmlElementById("foo");
        assertSame(form, input.getEnclosingForm());
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    public void testGetEnclosing() throws Exception {
        final String htmlContent = ""
            + "<html><head><title>foo</title></head><body>\n"
            + "<form id='form1'>\n"
            + "<table id='table1'>\n"
            + "<tr id='tr1'><td id='td1'>foo</td></tr>\n"
            + "<tr id='tr2'><td id='td2'>foo</td></tr>\n"
            + "</table>\n"
            + "</form></body></html>";
        final HtmlPage page = loadPage(htmlContent);

        final HtmlElement td1 = page.getHtmlElementById("td1");
        assertEquals("tr1", td1.getEnclosingElement("tr").getId());
        assertEquals("tr1", td1.getEnclosingElement("TR").getId());
        assertEquals("table1", td1.getEnclosingElement("table").getId());
        assertEquals("form1", td1.getEnclosingElement("form").getId());

        final HtmlElement td2 = page.getHtmlElementById("td2");
        assertEquals("tr2", td2.getEnclosingElement("tr").getId());
        assertEquals("tr2", td2.getEnclosingElement("TR").getId());
        assertEquals("table1", td2.getEnclosingElement("table").getId());
        assertEquals("form1", td2.getEnclosingElement("form").getId());
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    public void testAsText_WithComments() throws Exception {
        final String htmlContent
            = "<html><head><title>foo</title></head><body>\n"
            + "<p id='p1'>foo<!--bar--></p>\n"
            + "</body></html>";
        final HtmlPage page = loadPage(htmlContent);
        final HtmlElement element = page.getHtmlElementById("p1");
        assertEquals("foo", element.asText());
    }

    /**
     * Tests constants.
     */
    @Test
    public void testConstants() {
        assertEquals("", HtmlElement.ATTRIBUTE_NOT_DEFINED);
        assertEquals("", HtmlElement.ATTRIBUTE_VALUE_EMPTY);
        assertTrue("Not the same object",
            HtmlElement.ATTRIBUTE_NOT_DEFINED != HtmlElement.ATTRIBUTE_VALUE_EMPTY);
    }

    static class HtmlAttributeChangeListenerTestImpl implements HtmlAttributeChangeListener {
        private final List<String> collectedValues_ = new ArrayList<String>();
        @Test
        public void attributeAdded(final HtmlAttributeChangeEvent event) {
            collectedValues_.add("attributeAdded: " + event.getHtmlElement().getTagName() + ','
                    + event.getName() + ',' + event.getValue());
        }
        @Test
        public void attributeRemoved(final HtmlAttributeChangeEvent event) {
            collectedValues_.add("attributeRemoved: " + event.getHtmlElement().getTagName() + ','
                    + event.getName() + ',' + event.getValue());
        }
    
        @Test
        public void attributeReplaced(final HtmlAttributeChangeEvent event) {
            collectedValues_.add("attributeReplaced: " + event.getHtmlElement().getTagName() + ','
                    + event.getName() + ',' + event.getValue());
        }
        List<String> getCollectedValues() {
            return collectedValues_;
        }
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    public void testHtmlAttributeChangeListener_AddAttribute() throws Exception {
        final String htmlContent
            = "<html><head><title>foo</title>\n"
            + "<script>\n"
            + "  function clickMe() {\n"
            + "    var p1 = document.getElementById('p1');\n"
            + "    p1.setAttribute('title', 'myTitle');\n"
            + "  }\n"
            + "</script>\n"
            + "</head>\n"
            + "<body id='myBody'>\n"
            + "<p id='p1'></p>\n"
            + "<input id='myButton' type='button' onclick='clickMe()'>\n"
            + "</body></html>";

        final String[] expectedValues =
        {"attributeAdded: p,title,myTitle",
            "attributeAdded: p,title,myTitle",
            "attributeAdded: p,title,myTitle"};
        final HtmlPage page = loadPage(htmlContent);
        final HtmlBody body = (HtmlBody) page.getHtmlElementById("myBody");
        final HtmlElement p1 = page.getHtmlElementById("p1");
        
        final HtmlAttributeChangeListenerTestImpl listenerImpl = new HtmlAttributeChangeListenerTestImpl();
        p1.addHtmlAttributeChangeListener(listenerImpl);
        body.addHtmlAttributeChangeListener(listenerImpl);
        page.addHtmlAttributeChangeListener(listenerImpl);
        final HtmlButtonInput myButton = (HtmlButtonInput) page.getHtmlElementById("myButton");
        
        myButton.click();
        assertEquals(expectedValues, listenerImpl.getCollectedValues());
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    public void testHtmlAttributeChangeListener_ReplaceAttribute() throws Exception {
        final String htmlContent
            = "<html><head><title>foo</title>\n"
            + "<script>\n"
            + "  function clickMe() {\n"
            + "    var p1 = document.getElementById('p1');\n"
            + "    p1.setAttribute('title', p1.getAttribute('title') + 'a');\n"
            + "  }\n"
            + "</script>\n"
            + "</head>\n"
            + "<body id='myBody'>\n"
            + "<p id='p1' title='myTitle'></p>\n"
            + "<input id='myButton' type='button' onclick='clickMe()'>\n"
            + "</body></html>";
        
        final String[] expectedValues =
        {"attributeReplaced: p,title,myTitle",
            "attributeReplaced: p,title,myTitle",
            "attributeReplaced: p,title,myTitle"};
        final HtmlPage page = loadPage(htmlContent);
        final HtmlBody body = (HtmlBody) page.getHtmlElementById("myBody");
        final HtmlElement p1 = page.getHtmlElementById("p1");
        final HtmlAttributeChangeListenerTestImpl listenerImpl = new HtmlAttributeChangeListenerTestImpl();
        page.addHtmlAttributeChangeListener(listenerImpl);
        body.addHtmlAttributeChangeListener(listenerImpl);
        p1.addHtmlAttributeChangeListener(listenerImpl);
        final HtmlButtonInput myButton = (HtmlButtonInput) page.getHtmlElementById("myButton");
        
        myButton.click();
        assertEquals(expectedValues, listenerImpl.getCollectedValues());
        assertEquals("myTitle" + 'a', p1.getAttributeValue("title"));
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    public void testHtmlAttributeChangeListener_RemoveAttribute() throws Exception {
        final String htmlContent
            = "<html><head><title>foo</title>\n"
            + "<script>\n"
            + "  function clickMe() {\n"
            + "    var p1 = document.getElementById('p1');\n"
            + "    p1.removeAttribute('title');\n"
            + "  }\n"
            + "</script>\n"
            + "</head>\n"
            + "<body id='myBody'>\n"
            + "<p id='p1' title='myTitle'></p>\n"
            + "<input id='myButton' type='button' onclick='clickMe()'>\n"
            + "</body></html>";
        
        final String[] expectedValues =
        {"attributeRemoved: p,title,myTitle",
            "attributeRemoved: p,title,myTitle",
            "attributeRemoved: p,title,myTitle"};
        final HtmlPage page = loadPage(htmlContent);
        final HtmlBody body = (HtmlBody) page.getHtmlElementById("myBody");
        final HtmlElement p1 = page.getHtmlElementById("p1");
        final HtmlAttributeChangeListenerTestImpl listenerImpl = new HtmlAttributeChangeListenerTestImpl();
        page.addHtmlAttributeChangeListener(listenerImpl);
        body.addHtmlAttributeChangeListener(listenerImpl);
        p1.addHtmlAttributeChangeListener(listenerImpl);
        final HtmlButtonInput myButton = (HtmlButtonInput) page.getHtmlElementById("myButton");
        
        myButton.click();
        assertEquals(expectedValues, listenerImpl.getCollectedValues());
        assertSame(HtmlElement.ATTRIBUTE_NOT_DEFINED, p1.getAttributeValue("title"));
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    public void testHtmlAttributeChangeListener_RemoveListener() throws Exception {
        final String htmlContent
            = "<html><head><title>foo</title>\n"
            + "<script>\n"
            + "  function clickMe() {\n"
            + "    var p1 = document.getElementById('p1');\n"
            + "    p1.setAttribute('title', p1.getAttribute('title') + 'a');\n"
            + "  }\n"
            + "</script>\n"
            + "</head>\n"
            + "<body>\n"
            + "<p id='p1' title='myTitle'></p>\n"
            + "<input id='myButton' type='button' onclick='clickMe()'>\n"
            + "</body></html>";
        
        final String[] expectedValues = {"attributeReplaced: p,title,myTitle"};
        final HtmlPage page = loadPage(htmlContent);
        final HtmlElement p1 = page.getHtmlElementById("p1");
        final HtmlAttributeChangeListenerTestImpl listenerImpl = new HtmlAttributeChangeListenerTestImpl();
        p1.addHtmlAttributeChangeListener(listenerImpl);
        final HtmlButtonInput myButton = (HtmlButtonInput) page.getHtmlElementById("myButton");
        
        myButton.click();
        p1.removeHtmlAttributeChangeListener(listenerImpl);
        myButton.click();
        assertEquals(expectedValues, listenerImpl.getCollectedValues());
        assertEquals("myTitle" + 'a' + 'a', p1.getAttributeValue("title"));
    }
    
    /**
     * @throws Exception if the test fails
     */
    @Test
    public void testMouseOver() throws Exception {
        final String content = "<html>\n"
            + "<head>\n"
            + "<script>\n"
            + "  function mouseOverMe() {\n"
            + "    document.getElementById('myTextarea').value+='mouseover-';\n"
            + "  }\n"
            + "</script>\n"
            + "</head>\n"
            + "<body id='myBody' onmouseover='mouseOverMe()'>\n"
            + "<textarea id='myTextarea'></textarea>\n"
            + "</body></html>";
        final HtmlPage page = loadPage(content);
        final HtmlBody body = (HtmlBody) page.getHtmlElementById("myBody");
        body.mouseOver();
        final HtmlTextArea textArea = (HtmlTextArea) page.getHtmlElementById("myTextarea");
        assertEquals("mouseover-", textArea.getText());
    }
    
    /**
     * @throws Exception if the test fails
     */
    @Test
    public void testMouseMove() throws Exception {
        final String content = "<html>\n"
            + "<head>\n"
            + "<script>\n"
            + "  function mouseMoveMe() {\n"
            + "    document.getElementById('myTextarea').value+='mousemove-';\n"
            + "  }\n"
            + "</script>\n"
            + "</head>\n"
            + "<body id='myBody' onmousemove='mouseMoveMe()'>\n"
            + "<textarea id='myTextarea'></textarea>\n"
            + "</body></html>";
        final HtmlPage page = loadPage(content);
        final HtmlBody body = (HtmlBody) page.getHtmlElementById("myBody");
        body.mouseMove();
        final HtmlTextArea textArea = (HtmlTextArea) page.getHtmlElementById("myTextarea");
        assertEquals("mousemove-", textArea.getText());
    }
    
    /**
     * @throws Exception if the test fails
     */
    @Test
    public void testMouseOut() throws Exception {
        final String content = "<html>\n"
            + "<head>\n"
            + "<script>\n"
            + "  function mouseOutMe() {\n"
            + "    document.getElementById('myTextarea').value+='mouseout-';\n"
            + "  }\n"
            + "</script>\n"
            + "</head>\n"
            + "<body id='myBody' onmouseout='mouseOutMe()'>\n"
            + "<textarea id='myTextarea'></textarea>\n"
            + "</body></html>";
        final HtmlPage page = loadPage(content);
        final HtmlBody body = (HtmlBody) page.getHtmlElementById("myBody");
        body.mouseOut();
        final HtmlTextArea textArea = (HtmlTextArea) page.getHtmlElementById("myTextarea");
        assertEquals("mouseout-", textArea.getText());
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    public void testMouseDown() throws Exception {
        testMouseDown(BrowserVersion.FIREFOX_2, "mousedown-0");
        testMouseDown(BrowserVersion.INTERNET_EXPLORER_6_0, "mousedown-1");
    }

    private void testMouseDown(final BrowserVersion browserVersion, final String expected) throws Exception {
        final String content = "<html>\n"
            + "<head>\n"
            + "<script>\n"
            + "  function mouseDownMe(e) {\n"
            + "    document.getElementById('myTextarea').value+='mousedown-' + e.button;\n"
            + "  }\n"
            + "</script>\n"
            + "</head>\n"
            + "<body id='myBody' onmousedown='mouseDownMe(event)'>\n"
            + "<textarea id='myTextarea'></textarea>\n"
            + "</body></html>";
        final List<String> emptyList = Collections.emptyList();
        final HtmlPage page = loadPage(browserVersion, content, emptyList);
        final HtmlBody body = (HtmlBody) page.getHtmlElementById("myBody");
        body.mouseDown();
        final HtmlTextArea textArea = (HtmlTextArea) page.getHtmlElementById("myTextarea");
        assertEquals(expected, textArea.getText());
    }
    
    /**
     * @throws Exception if the test fails
     */
    @Test
    public void testMouseUp() throws Exception {
        final String content = "<html>\n"
            + "<head>\n"
            + "<script>\n"
            + "  function mouseUpMe() {\n"
            + "    document.getElementById('myTextarea').value+='mouseup-';\n"
            + "  }\n"
            + "</script>\n"
            + "</head>\n"
            + "<body id='myBody' onmouseup='mouseUpMe()'>\n"
            + "<textarea id='myTextarea'></textarea>\n"
            + "</body></html>";
        final HtmlPage page = loadPage(content);
        final HtmlBody body = (HtmlBody) page.getHtmlElementById("myBody");
        body.mouseUp();
        final HtmlTextArea textArea = (HtmlTextArea) page.getHtmlElementById("myTextarea");
        assertEquals("mouseup-", textArea.getText());
    }
    
    /**
     * @throws Exception if the test fails
     */
    @Test
    public void testRightClick() throws Exception {
        testRightClick(BrowserVersion.INTERNET_EXPLORER_7_0, "mousedown-2-mouseup-2-contextmenu-0-");
        testRightClick(BrowserVersion.FIREFOX_2, "mousedown-3-mouseup-3-contextmenu-3-");
    }

    private void testRightClick(final BrowserVersion browserVersion, final String expected)
        throws Exception {
        final String content = "<html>\n"
            + "<head>\n"
            + "<script>\n"
            + "  function divMouseEvent(e) {\n"
            + "    var textarea = document.getElementById('myTextarea');\n"
            + "    if (window.event)\n"
            + "      textarea.value += event.type + '-' + event.button + '-';\n"
            + "    else\n"
            + "      textarea.value += e.type + '-' + e.which + '-';\n"
            + "  }\n"
            + "  function loadFunction(e) {\n"
            + "     document.getElementById('myDiv').onmousedown   = divMouseEvent;\n"
            + "     document.getElementById('myDiv').onmouseup     = divMouseEvent;\n"
            + "     document.getElementById('myDiv').oncontextmenu = divMouseEvent;\n"
            + "  }\n"
            + "</script>\n"
            + "</head>\n"
            + "<body onload='loadFunction()'>\n"
            + "  <div id='myDiv'>Hello</div><br>\n"
            + "  <textarea id='myTextarea'></textarea>\n"
            + "</body></html>";
        final HtmlPage page = loadPage(browserVersion, content, null);
        final HtmlDivision div = (HtmlDivision) page.getHtmlElementById("myDiv");
        div.rightClick();
        final HtmlTextArea textArea = (HtmlTextArea) page.getHtmlElementById("myTextarea");
        assertEquals(expected, textArea.getText());
    }

    /**
     * Test the mouse down, then mouse up.
     *
     * @throws Exception if the test fails
     */
    @Test
    public void testMouse_Down_Up() throws Exception {
        testMouse_Down_Up(BrowserVersion.INTERNET_EXPLORER_7_0, "mousedown-1-mouseup-1-");
        testMouse_Down_Up(BrowserVersion.FIREFOX_2, "mousedown-1-mouseup-1-");
    }

    private void testMouse_Down_Up(final BrowserVersion browserVersion, final String expected)
        throws Exception {
        final String content = "<html>\n"
            + "<head>\n"
            + "<script>\n"
            + "  function divMouseEvent(e) {\n"
            + "    var textarea = document.getElementById('myTextarea');\n"
            + "    if (window.event)\n"
            + "      textarea.value += event.type + '-' + event.button + '-';\n"
            + "    else\n"
            + "      textarea.value += e.type + '-' + e.which + '-';\n"
            + "  }\n"
            + "  function loadFunction(e) {\n"
            + "     document.getElementById('myDiv').onmousedown=divMouseEvent;\n"
            + "     document.getElementById('myDiv').onmouseup  =divMouseEvent;\n"
            + "  }\n"
            + "</script>\n"
            + "</head>\n"
            + "<body onload='loadFunction()'>\n"
            + "  <div id='myDiv'>Hello</div><br>\n"
            + "  <textarea id='myTextarea'></textarea>\n"
            + "</body></html>";
        final HtmlPage page = loadPage(browserVersion, content, null);
        final HtmlDivision div = (HtmlDivision) page.getHtmlElementById("myDiv");
        div.mouseDown();
        div.mouseUp();
        final HtmlTextArea textArea = (HtmlTextArea) page.getHtmlElementById("myTextarea");
        assertEquals(expected, textArea.getText());
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    public void testAsXml_separateLineforEmptyElements() throws Exception {
        final String content = "<html><head><title>foo</title></head>\n"
            + "<body><table><tr><td></tr></table>\n"
            + "</body></html>";
        final List<String> collectedAlerts = new ArrayList<String>();
        final HtmlPage page = loadPage(content, collectedAlerts);
        assertTrue(page.asXml().indexOf("/> ") == -1);
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    public void testGetElementsByTagName() throws Exception {
        final String html
            = "<html><head><title>First</title></head>\n"
            + "<body>\n"
            + "<form><input type='button' name='button1' value='pushme'></form>\n"
            + "<div>a</div> <div>b</div> <div>c</div>\n"
            + "</body></html>";

        final HtmlPage page = loadPage(html);
        final HtmlElement body = page.getBody();

        final NodeList inputs = body.getElementsByTagName("input");
        assertEquals(1, inputs.getLength());
        assertEquals("button", inputs.item(0).getAttributes().getNamedItem("type").getNodeValue());

        final NodeList divs = body.getElementsByTagName("div");
        assertEquals(3, divs.getLength());

        final HtmlDivision newDiv = new HtmlDivision(null, HtmlDivision.TAG_NAME, page, null);
        body.appendChild(newDiv);
        assertEquals(4, divs.getLength());
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    public void type() throws Exception {
        final String html = "<html><head><script>\n"
            + "  function test() {\n"
            + "    alert(document.getElementById('myInput').value);\n"
            + "  }\n"
            + "</script></head>\n"
            + "<body>\n"
            + "  <input id='myButton' type='button' onclick='test()'>\n"
            + "  <input id='myInput' onclick='test()'>\n"
            + "</body></html>";

        final String[] expectedAlerts = {"Hello Cruel World"};
        final List<String> collectedAlerts = new ArrayList<String>();
        final HtmlPage page = (HtmlPage) loadPage(BrowserVersion.getDefault(), html, collectedAlerts);
        final HtmlTextInput input = (HtmlTextInput) page.getHtmlElementById("myInput");
        input.type("Hello Cruel World");
        assertEquals("Hello Cruel World", input.getValueAttribute());
        ((HtmlButtonInput) page.getHtmlElementById("myButton")).click();
        assertEquals(expectedAlerts, collectedAlerts);
    }
}
