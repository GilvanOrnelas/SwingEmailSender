package com.goff.email_desktop.graphic.listener;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.goff.email_desktop.email.Email;
import com.goff.email_desktop.graphic.PanelItem;

public class ItemSelectionListener implements ListSelectionListener {

    private final PanelItem panelItem;

    public ItemSelectionListener(final PanelItem panelItem) {
        this.panelItem = panelItem;
    }

    @Override
    public void valueChanged(final ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            final boolean oneItemIsSelected = panelItem.list.getSelectedIndex() != -1;
            if (oneItemIsSelected) {
                panelItem.deleteButton.setEnabled(true);
                panelItem.sendButton.setEnabled(true);
                panelItem.sendAllButton.setEnabled(true);
                final Email emailSelecionado = ((JList<Email>) e.getSource()).getSelectedValue();
                panelItem.emailBody.setText(emailSelecionado.getBody());
                panelItem.emailBody.setEnabled(true);
            } else {
                panelItem.deleteButton.setEnabled(false);
                panelItem.sendButton.setEnabled(false);
                panelItem.sendAllButton.setEnabled(false);
            }
        }
    }

}
