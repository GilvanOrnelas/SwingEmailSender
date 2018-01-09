package com.goff.email_desktop.email;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.junit.Test;

import com.goff.email_desktop.email.EmailProvider;

public class EmailTest {

    @Test
    public void test() throws AddressException, MessagingException {
        EmailProvider.send("goff.teste@gmail.com", "");
    }

}
