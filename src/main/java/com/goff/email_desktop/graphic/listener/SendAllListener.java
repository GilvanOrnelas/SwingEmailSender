package com.goff.email_desktop.graphic.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.goff.email_desktop.graphic.PanelItemNames;

public class SendAllListener implements ActionListener {

    @Override
    public void actionPerformed(final ActionEvent e) {
        if (e.getActionCommand().equals(PanelItemNames.sendAll)) {
        }
    }
}
