package com.goff.email_desktop.email;

import java.security.GeneralSecurityException;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailProvider {

    public static void send(final String userName, final String password, Email email)
            throws AddressException, MessagingException, GeneralSecurityException {

        final Session defaultSession = ConfigurationSession.createDefaultSession(userName, password);

        final Message message = new MimeMessage(defaultSession);
        message.setFrom(new InternetAddress(userName));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getDestination()));
        message.setSubject("Titulo Email Automatico");
        message.setText("Funcionaaaa!!");

        final Multipart multipart = createAttachment(email.getAttachment());
        message.setContent(multipart);

        Transport.send(message);

    }

    private static Multipart createAttachment(final String filePath)
            throws MessagingException {
        final BodyPart messageBodyPart = new MimeBodyPart();
        final DataSource source = new FileDataSource(filePath);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(filePath.substring(filePath.lastIndexOf("/")));
        final Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        return multipart;
    }
}
