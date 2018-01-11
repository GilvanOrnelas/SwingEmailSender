package com.goff.email_desktop.graphic.email_manager.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.GeneralSecurityException;
import java.util.Enumeration;

import javax.mail.MessagingException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.goff.email_desktop.email.Email;
import com.goff.email_desktop.email.EmailProvider;
import com.goff.email_desktop.email.EmailRepository;
import com.goff.email_desktop.graphic.email_manager.EmailUser;
import com.goff.email_desktop.graphic.email_manager.PanelItem;

public class SendAllListener implements ActionListener {
    private final PanelItem panelItem;

    public SendAllListener(final PanelItem panelItem) {
        this.panelItem = panelItem;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        final Enumeration<Email> emails = panelItem.listModel.elements();
        final StringBuilder successMessage = new StringBuilder();
        final StringBuilder failMessage = new StringBuilder();
        while (emails.hasMoreElements()) {
            final Email email = emails.nextElement();
            sendEmail(email);
            createResultMessage(successMessage, failMessage, email);
            try {
                Thread.sleep(1000);
            } catch (final InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        showResultMessage(successMessage, failMessage);
    }

    private void sendEmail(final Email email) {
        send(email);
        new EmailRepository().save(email);
    }

    private void send(final Email email) {
        try {
            final String userName = EmailUser.getEmail();
            final String password = EmailUser.getPassword();
            EmailProvider.send(userName, password, email);
        } catch (MessagingException | GeneralSecurityException e1) {
            JOptionPane.showMessageDialog(new JFrame("Error"), e1.getMessage(), "Error in e-mail sending",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showResultMessage(final StringBuilder successMessage, final StringBuilder failMessage) {
        if (successMessage.length() > 2) {
            JOptionPane.showMessageDialog(null, "E-mails sended to " + successMessage.toString(), "Success!",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        if (failMessage.length() > 2) {
            JOptionPane.showMessageDialog(null, "Fail sending e-mail to " + failMessage, "Fail",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createResultMessage(final StringBuilder successMessage, final StringBuilder failMessage,
            final Email email) {
        if (email.isSended()) {
            successMessage.append(email.getDestination() + "; ");
        } else {
            failMessage.append(email.getDestination() + "; ");
        }
    }
}
