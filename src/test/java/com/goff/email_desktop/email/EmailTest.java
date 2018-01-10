package com.goff.email_desktop.email;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class EmailTest {

    @Test
    public void givenFileWithNameParameter_shallReplaceTheParameterWithActualName() throws IOException {
        final Email email = new Email();
        email.setName("Gilvan");
        new EmailDefaultBodyTest().givenStringWithNameParameter_shallAddItToTheBodyFile();

        Assert.assertEquals("Hello Gilvan!", email.loadDefaultBody());
    }
}
