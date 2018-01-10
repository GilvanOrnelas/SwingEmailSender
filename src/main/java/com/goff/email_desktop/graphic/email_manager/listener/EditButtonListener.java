package com.goff.email_desktop.graphic.email_manager.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.goff.email_desktop.email.Email;
import com.goff.email_desktop.graphic.email_manager.PanelItem;
import com.goff.email_desktop.graphic.email_manager.edit_frame.EditEmailFrame;

public class EditButtonListener implements ActionListener {
    private final PanelItem panelItem;

    public EditButtonListener(final PanelItem panelItem) {
        this.panelItem = panelItem;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {

        final Email email = panelItem.list.getSelectedValue();

        final JFrame f = new EditEmailFrame(email, panelItem);
        f.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        f.pack();
        f.setVisible(true);
    }
}
