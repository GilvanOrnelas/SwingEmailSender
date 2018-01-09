package com.goff.email_desktop.graphic.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ListSelectionModel;

import com.goff.email_desktop.graphic.PanelItem;

public class DeleteButtonListener implements ActionListener {
    private final PanelItem panelItem;

    public DeleteButtonListener(final PanelItem panelItem) {
        this.panelItem = panelItem;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {

        final ListSelectionModel lsm = panelItem.list.getSelectionModel();
        int firstSelected = lsm.getMinSelectionIndex();
        final int lastSelected = lsm.getMaxSelectionIndex();
        panelItem.listModel.removeRange(firstSelected, lastSelected);

        final int size = panelItem.listModel.size();

        if (size == 0) {
            //List is empty: disable delete, up, and down buttons.
            panelItem.deleteButton.setEnabled(false);
            panelItem.sendButton.setEnabled(false);
            panelItem.sendAllButton.setEnabled(false);

        } else {
            //Adjust the selection.
            if (firstSelected == panelItem.listModel.getSize()) {
                //Removed item in last position.
                firstSelected--;
            }
            panelItem.list.setSelectedIndex(firstSelected);
        }
    }
}
