package com.goff.email_desktop.graphic.email_manager.listener;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.goff.email_desktop.email.Email;
import com.goff.email_desktop.email.EmailRepository;
import com.goff.email_desktop.graphic.email_manager.FileChooser;
import com.goff.email_desktop.graphic.email_manager.PanelItem;

public class AddButtonListener implements ActionListener {
    private final PanelItem panelItem;

    public AddButtonListener(final PanelItem panelItem) {
        this.panelItem = panelItem;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {

        final Email email = createEmail();

        if (email.missingRequiredField()) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(new JFrame("Error"), "All data is required.", "Error in Add",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (email.repeated(panelItem.listModel.elements())) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(new JFrame("Error"), "E-mail already registered.", "Error in Add",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        new EmailRepository().save(email);
        panelItem.listModel.addElement(email);
        panelItem.list.setSelectedIndex(panelItem.listModel.getSize() - 1);
    }

    private Email createEmail() {
        final Email email = new Email();
        email.setName(JOptionPane.showInputDialog("Student's Name:"));
        email.setDestination(JOptionPane.showInputDialog("Student's e-mail:"));
        email.setAttachment(FileChooser.chooseFileAbsolutePath());
        email.setBody(email.loadDefaultBody());
        return email;
    }

}
