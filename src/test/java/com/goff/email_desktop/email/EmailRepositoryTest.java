package com.goff.email_desktop.email;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Test;

public class EmailRepositoryTest {

    private Email emailSample() {
        final Email email = new Email();
        email.setBody("Email's body.");
        email.setAttachment("/file/uri/sample.xls");
        email.setDestination("goff.teste@gmail.com");
        email.setName("Gilvan");
        return email;
    }

    private Email anotherEmailSample() {
        final Email email = new Email();
        email.setBody("Email's body.");
        email.setAttachment("/file/uri/sample.xls");
        email.setDestination("gilvanornelasff@gmail.com");
        email.setName("Gilvan Fernandes");
        return email;
    }

    @Test
    public void givenEmail_shallSaveItInJsonFile() throws IOException {
        new EmailRepository().save(emailSample());
    }

    @Test
    public void givenEmailSaved_shallRecoverIt() throws IOException {
        saveAndReadTest();
    }

    @Test
    public void givenMoreThanOneEmailSaved_shallRecoverThemAll() throws IOException {
        final EmailRepository repository = new EmailRepository();
        repository.save(emailSample());
        repository.save(anotherEmailSample());
        final List<Email> emails = repository.readAll();
        Assert.assertTrue(emails.size() >= 2);
    }

    @Test(expected = NoSuchElementException.class)
    public void givenEmailSaved_shallRemoveIt() throws IOException {
        saveAndReadTest();
        final EmailRepository repository = new EmailRepository();
        repository.remove(emailSample());
        repository.read(emailSample().getDestination());
    }

    private void saveAndReadTest() throws IOException {
        final EmailRepository repository = new EmailRepository();
        repository.save(emailSample());
        final Email emailRead = repository.read(emailSample().getDestination());
        Assert.assertEquals(emailSample(), emailRead);
    }

}
