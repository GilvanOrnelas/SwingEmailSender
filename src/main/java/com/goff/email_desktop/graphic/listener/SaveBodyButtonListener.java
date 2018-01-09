package com.goff.email_desktop.graphic.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.goff.email_desktop.graphic.PanelItem;

public class SaveBodyButtonListener implements ActionListener {
    private final PanelItem panelItem;

    public SaveBodyButtonListener(final PanelItem panelItem) {
        this.panelItem = panelItem;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {

        final int index = panelItem.list.getSelectedIndex();
        final int size = panelItem.listModel.getSize();
        if (index == -1 || index + 1 == size) {
            panelItem.listModel.addElement(null);
            panelItem.list.setSelectedIndex(size);

            //Otherwise insert the new one after the current selection,
            //and select new one.
        } else {
            panelItem.listModel.insertElementAt(null, index + 1);
            panelItem.list.setSelectedIndex(index + 1);
        }
    }
}
