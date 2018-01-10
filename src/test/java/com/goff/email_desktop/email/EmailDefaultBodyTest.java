package com.goff.email_desktop.email;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Test;

import com.goff.email_desktop.param.Parameter;

public class EmailDefaultBodyTest {

    @Test
    public void shallCreateDefaultBodyFile() throws IOException {
        EmailDefaultBody.define("");
        final Path pathToDefaultBody = Paths.get(EmailDefaultBody.DEFAULT_BODY_FILE);
        Assert.assertTrue(Files.isRegularFile(pathToDefaultBody));
    }

    @Test
    public void givenStringWithNameParameter_shallAddItToTheBodyFile() throws IOException {
        final String fileString = "Hello " + Parameter.NAME + "!";
        EmailDefaultBody.define(fileString);
        final Path pathToDefaultBody = Paths.get(EmailDefaultBody.DEFAULT_BODY_FILE);
        Assert.assertEquals(fileString, Files.readAllLines(pathToDefaultBody).get(0));
    }

    @Test
    public void givenAlreadyExistingFile_shallTruncateTheBodyFile() throws IOException {
        final String fileString = "eHello!";
        EmailDefaultBody.define(fileString);
        Assert.assertEquals(fileString, EmailDefaultBody.read());
        final String fileStringToTruncate = "New Hello!";
        EmailDefaultBody.define(fileStringToTruncate);
        Assert.assertEquals(fileStringToTruncate, EmailDefaultBody.read());
    }

    @Test
    public void givenFileCreated_shallReadItsContent() throws IOException {
        final String fileString = "Hello!";
        EmailDefaultBody.define(fileString);
        Assert.assertEquals(fileString, EmailDefaultBody.read());
    }

}
