package com.goff.email_desktop.email;

import java.security.GeneralSecurityException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.junit.Test;

import com.goff.email_desktop.email.EmailProvider;

public class EmailTest {

    @Test
    public void test() throws AddressException, MessagingException, GeneralSecurityException {
    	Email email = new Email();
    	email.setAttachment("C:/Users/Gil/Desktop/backup.txt");
    	email.setDestination("gilvanornelasff@gmail.com");
        EmailProvider.send("goff.teste@gmail.com", "jjzdvui1!", email);
    }

}
