package com.goff.email_desktop.email.report;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ReportMapperTest {

    private final String email = "gilvanornelasff@gmail.com";

    @BeforeClass
    public void load() throws IOException {
        ReportMapper.load();
    }

    @Test
    public void shallLoadPropertiesFile() throws IOException {
        ReportMapper.load();
    }

    @Test
    public void shallAddNewProperty() throws IOException {
        addTestProperty();
    }

    @Test
    public void givenPropertyKeyAlreadyExists_shallReplaceItsValue() throws IOException {
        addTestProperty();
        final String valueReportPath2 = "/caminho/exemplo/feedback2.xml";
        ReportMapper.add(email, valueReportPath2);
        Assert.assertEquals(ReportMapper.get(email), valueReportPath2);
    }

    @Test
    public void shallRemovePropertyByKey() throws IOException {
        addTestProperty();
        ReportMapper.remove(email);
        ReportMapper.remove("NonExistentProperty");
        Assert.assertTrue(ReportMapper.get(email) == null);
    }

    private void addTestProperty() throws IOException {
        final String valueReportPath = "/caminho/exemplo/feedback.xml";
        ReportMapper.add(email, valueReportPath);
        Assert.assertEquals(ReportMapper.get(email), valueReportPath);
    }

}
