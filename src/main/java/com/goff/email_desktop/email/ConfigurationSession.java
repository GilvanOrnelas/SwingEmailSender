package com.goff.email_desktop.email;

import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class ConfigurationSession {

    public static Session createDefaultSession(final String username, final String password) {

        final Session session = Session.getInstance(createProperties(), new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        return session;
    }

    private static Properties createProperties() {
        final Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        return props;
    }
}
