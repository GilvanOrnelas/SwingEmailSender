package com.goff.email_desktop.graphic.email_manager.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

import com.goff.email_desktop.email.Email;
import com.goff.email_desktop.email.EmailRepository;
import com.goff.email_desktop.graphic.email_manager.PanelItem;

public class DeleteButtonListener implements ActionListener {
    private final PanelItem panelItem;

    public DeleteButtonListener(final PanelItem panelItem) {
        this.panelItem = panelItem;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        final ListSelectionModel emails = panelItem.list.getSelectionModel();
        int firstSelected = emails.getMinSelectionIndex();
        final Email email = panelItem.listModel.get(firstSelected);

        if (JOptionPane.showConfirmDialog(null,
                "Are you sure to remove " + email.getDestination() + "?") != JOptionPane.OK_OPTION) {
            return;
        }
        new EmailRepository().remove(email);
        panelItem.listModel.remove(firstSelected);

        final boolean emailsAreEmpty = panelItem.listModel.size() == 0;

        if (emailsAreEmpty) {
            panelItem.deleteButton.setEnabled(false);
            panelItem.sendButton.setEnabled(false);
            panelItem.editButton.setEnabled(false);
            panelItem.emailBody.setText(null);
            panelItem.sendAllButton.setEnabled(false);

        } else {
            if (firstSelected == panelItem.listModel.getSize()) {
                firstSelected--;
            }
            panelItem.list.setSelectedIndex(firstSelected);
        }
    }
}
