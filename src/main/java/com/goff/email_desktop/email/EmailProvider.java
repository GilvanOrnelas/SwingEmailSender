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

    public static void send(final String userName, final String password, final Email email)
            throws AddressException, MessagingException, GeneralSecurityException {

        final Session defaultSession = ConfigurationSession.createDefaultSession(userName, password);

        final Message message = new MimeMessage(defaultSession);
        message.setFrom(new InternetAddress(userName));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getDestination()));
        message.setSubject("Feedback do " + email.getName());
        message.setText(email.getBody());

        final Multipart multipart = createAttachment(email.getAttachment(), email.getBody());
        message.setContent(multipart);

        email.setSended(false);
        email.setWithSendingError(true);
        Transport.send(message);
        email.setSended(true);
        email.setWithSendingError(false);
    }

    private static Multipart createAttachment(final String filePath, final String messageBody)
            throws MessagingException {
        final BodyPart messageBodyPart = new MimeBodyPart();
        final DataSource source = new FileDataSource(filePath);
        messageBodyPart.setText(messageBody);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(filePath.substring(filePath.lastIndexOf("\\") + 1));

        final Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        return multipart;
    }
}
