package com.goff.email_desktop.graphic.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;

import com.goff.email_desktop.email.Email;
import com.goff.email_desktop.graphic.PanelItemNames;

public class SendListener implements ActionListener {
    private final JList<Email> list;

    public SendListener(final JList<Email> list) {
        this.list = list;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        final Email emailSelecionado = list.getSelectedValue();

        if (e.getActionCommand().equals(PanelItemNames.send)) {
            System.out.println(emailSelecionado.getBody() + emailSelecionado.getDestination());
        }
    }
}
