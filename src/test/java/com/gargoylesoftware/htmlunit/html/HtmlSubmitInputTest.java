/*
 * Copyright (c) 2002-2012 Gargoyle Software Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gargoylesoftware.htmlunit.html;

import java.net.URL;
import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.gargoylesoftware.htmlunit.BrowserRunner;
import com.gargoylesoftware.htmlunit.BrowserRunner.Alerts;
import com.gargoylesoftware.htmlunit.WebDriverTestCase;
import com.gargoylesoftware.htmlunit.util.NameValuePair;

/**
 * Tests for {@link HtmlSubmitInput}.
 *
 * @version $Revision$
 * @author <a href="mailto:mbowler@GargoyleSoftware.com">Mike Bowler</a>
 * @author Marc Guillemot
 * @author Ahmed Ashour
 */
@RunWith(BrowserRunner.class)
public class HtmlSubmitInputTest extends WebDriverTestCase {

    /**
     * @throws Exception if the test fails
     */
    @Test
    public void testSubmit() throws Exception {
        final String html
            = "<html><head><title>foo</title></head><body>\n"
            + "<form id='form1' method='post'>\n"
            + "<input type='submit' name='aButton' value='foo'/>\n"
            + "<input type='suBMit' name='button' value='foo'/>\n"
            + "<input type='submit' name='anotherButton' value='foo'/>\n"
            + "</form></body></html>";

        final WebDriver wd = loadPageWithAlerts2(html);

        final WebElement button = wd.findElement(By.name("button"));
        button.click();

        assertEquals("foo", wd.getTitle());

        assertEquals(Collections.singletonList(new NameValuePair("button", "foo")),
            getMockWebConnection().getLastParameters());
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @Alerts({ "foo", "bar" })
    public void testClick_onClick() throws Exception {
        final String html
            = "<html><head><title>foo</title></head><body>\n"
            + "<form id='form1' onSubmit='alert(\"bar\"); return false;'>\n"
            + "    <input type='submit' name='button' value='foo' onClick='alert(\"foo\")'/>\n"
            + "</form></body></html>";

        final WebDriver wd = loadPage2(html);

        final WebElement button = wd.findElement(By.name("button"));
        button.click();

        assertEquals(getExpectedAlerts(), getCollectedAlerts(wd));
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    public void testClick_onClick_JavascriptReturnsTrue() throws Exception {
        final String html
            = "<html><head><title>First</title></head><body>\n"
            + "<form name='form1' method='get' action='foo.html'>\n"
            + "<input name='button' type='submit' value='PushMe' id='button1'"
            + "onclick='return true'/></form>\n"
            + "</body></html>";
        final String secondHtml = "<html><head><title>Second</title></head><body></body></html>";

        getMockWebConnection().setResponse(new URL(getDefaultUrl(), "foo.html"), secondHtml);

        final WebDriver wd = loadPageWithAlerts2(html);

        final WebElement button = wd.findElement(By.id("button1"));
        button.click();

        assertEquals("Second", wd.getTitle());
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @Alerts("1")
    public void testOutsideForm() throws Exception {
        final String html =
            "<html><head></head>\n"
            + "<body>\n"
            + "<input id='myInput' type='submit' onclick='alert(1)'>\n"
            + "</body></html>";

        final WebDriver wd = loadPage2(html);
        final WebElement input = wd.findElement(By.id("myInput"));
        input.click();

        assertEquals(getExpectedAlerts(), getCollectedAlerts(wd));
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    public void doubleSubmission() throws Exception {
        final String html = "<html>\n"
            + "<head>\n"
            + "  <script type='text/javascript'>\n"
            + "    function submitForm() {\n"
            + "      document.deliveryChannelForm.submitBtn.disabled = true;\n"
            + "      document.deliveryChannelForm.submit();\n"
            + "    }\n"
            + "  </script>"
            + "</head>\n"
            + "<body>\n"
            + "  <form action='test' name='deliveryChannelForm'>\n"
            + "    <input name='submitBtn' type='submit' value='Save' title='Save' onclick='submitForm();'>\n"
            + "  </form>"
            + "</body>\n"
            + "</html>";

        getMockWebConnection().setDefaultResponse("");
        final WebDriver wd = loadPageWithAlerts2(html);
        final WebElement input = wd.findElement(By.name("submitBtn"));
        input.click();

        assertEquals(2, getMockWebConnection().getRequestCount());
    }

}
