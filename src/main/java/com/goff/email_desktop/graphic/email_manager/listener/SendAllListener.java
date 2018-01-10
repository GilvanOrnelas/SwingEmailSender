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
        while (emails.hasMoreElements()) {
            final Email email = emails.nextElement();
            sendEmail(email);
        }
    }

    private void sendEmail(final Email email) {
        try {
            EmailProvider.send(EmailUser.getEmail(), EmailUser.getPassword(), email);
        } catch (MessagingException | GeneralSecurityException e1) {
            JOptionPane.showMessageDialog(new JFrame("Error"), e1.getMessage(), "Error in e-mail sending",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
