package com.goff.email_desktop.file;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FileSeekerTest {

    private String resourcePath;

    @Before
    public void loadRelativeResourcePath() {
        resourcePath = FileSeekerTest.class.getClassLoader().getResource("files").toString()
                .replace("target/test-classes/files", "src/test/resources/").replace("file:", "");
    }

    @Test
    public void givenSomePath_shallFindFeedbackFilesInsideIt() throws IOException {
        final Set<String> absolutePaths = FileSeeker.findFeedBackFilesIn(resourcePath);
        System.out.println(absolutePaths);
        assertTrue("Não foi possível encontrar o arquivo.", !absolutePaths.isEmpty());
    }

    @Test
    public void givenSomePath_shallFindAllFeedbackFilesInsideItAndSubDirectories() throws IOException {
        final Set<String> absolutePaths = FileSeeker.findFeedBackFilesIn(resourcePath);
        System.out.println(absolutePaths);
        Assert.assertEquals(3, absolutePaths.size());
        absolutePaths.stream().forEach(xls -> Assert.assertTrue(xls.endsWith("feedback.xls")));
    }
}
