package com.goff.email_desktop.email;

import java.security.GeneralSecurityException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class EmailProviderTest {

    @Test
    public void shallSendSimpleEmail() throws AddressException, MessagingException, GeneralSecurityException {
        final Email email = new Email();
        email.setDestination("gilvanornelasff@gmail.com");
        EmailProvider.send("goff.teste@gmail.com", "", email);
    }

}
