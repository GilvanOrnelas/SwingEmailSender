package com.goff.email_desktop.email;

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

    public static void send(final String userName, final String password)
            throws AddressException, MessagingException {

        final Session defaultSession = ConfigurationSession.createDefaultSession(userName, password);

        final Message message = new MimeMessage(defaultSession);
        message.setFrom(new InternetAddress("goff.teste@gmail.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("gilvanornelasff@gmail.com"));
        message.setSubject("Testing Subject");
        message.setText("Testando email.");

        final Multipart multipart = createAttachment("", "");
        message.setContent(multipart);

        Transport.send(message);

    }

    private static Multipart createAttachment(final String filePath, final String fileName)
            throws MessagingException {
        final BodyPart messageBodyPart = new MimeBodyPart();
        final DataSource source = new FileDataSource(filePath + fileName);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(fileName);
        final Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        return multipart;
    }
}
