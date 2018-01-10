package com.goff.email_desktop.graphic.email_manager.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.goff.email_desktop.graphic.email_manager.PanelItem;
import com.goff.email_desktop.graphic.email_manager.edit_frame.DefaultEmailBodyFrame;

public class EditDefaultBodyButtonListener implements ActionListener {
    public EditDefaultBodyButtonListener(final PanelItem panelItem) {
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        final JFrame f = new DefaultEmailBodyFrame();
        f.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        f.pack();
        f.setVisible(true);
    }

}
