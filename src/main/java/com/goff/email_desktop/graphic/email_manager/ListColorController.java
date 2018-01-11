package com.goff.email_desktop.graphic.email_manager;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.goff.email_desktop.email.Email;

public class ListColorController extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(final JList<?> list, final Object selectedObject,
            final int index, final boolean isSelected, final boolean cellHasFocus) {
        super.getListCellRendererComponent(list, selectedObject, index, isSelected, cellHasFocus);
        final Email selectedEmail = (Email) selectedObject;

        if (selectedEmail.isWithSendingError()) {
            setForeground(Color.RED);
        } else {
            if (selectedEmail.isSended()) {
                setForeground(Color.GREEN);
            } else {
                setForeground(Color.BLUE);
            }
        }
        return this;
    }
}
