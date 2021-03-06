package com.goff.email_desktop.graphic.email_manager.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.GeneralSecurityException;

import javax.mail.MessagingException;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;

import com.goff.email_desktop.email.Email;
import com.goff.email_desktop.email.EmailProvider;
import com.goff.email_desktop.email.EmailRepository;
import com.goff.email_desktop.graphic.email_manager.EmailUser;

public class SendListener implements ActionListener {
    private final JList<Email> list;

    public SendListener(final JList<Email> list) {
        this.list = list;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        final Email selectedEmail = list.getSelectedValue();
        send(selectedEmail);
        new EmailRepository().save(selectedEmail);

    }

    private void send(final Email selectedEmail) {
        try {
            EmailProvider.send(EmailUser.getEmail(), EmailUser.getPassword(), selectedEmail);
            result(selectedEmail);
        } catch (MessagingException | GeneralSecurityException e1) {
            JOptionPane.showMessageDialog(new JFrame("Error"), e1.getMessage(), "Error in e-mail sending",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void result(final Email selectedEmail) {
        if (selectedEmail.isSended()) {
            JOptionPane.showMessageDialog(null, "E-mail sended to " + selectedEmail.getDestination(), "Success!",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Fail sending e-mail to " + selectedEmail.getDestination(), "Fail",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
