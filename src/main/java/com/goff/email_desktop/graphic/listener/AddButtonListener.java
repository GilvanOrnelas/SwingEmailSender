package com.goff.email_desktop.graphic.listener;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.goff.email_desktop.email.Email;
import com.goff.email_desktop.graphic.PanelItem;

public class AddButtonListener implements ActionListener {
    private final PanelItem panelItem;

    public AddButtonListener(final PanelItem panelItem) {
        this.panelItem = panelItem;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        if (panelItem.inputField.getText().equals("")) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(new JFrame("Error"), "Insira o e-mail do aluno.", "Error in Add",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        final String chosenFileAbsolutePath = chooseFileAbsolutePath();
        if (chosenFileAbsolutePath == null) {
            return;
        }
        final Email email = new Email();
        email.setAttachment(chosenFileAbsolutePath);
        email.setDestination(panelItem.inputField.getText());

        panelItem.listModel.addElement(email);
        panelItem.list.setSelectedIndex(panelItem.listModel.getSize());
    }

    private String chooseFileAbsolutePath() {
        panelItem.fileChooser = new JFileChooser(System.getProperty("user.home"));
        final int result = panelItem.fileChooser.showOpenDialog(panelItem.addButton);
        if (result == JFileChooser.APPROVE_OPTION) {
            final java.io.File selectedFile = panelItem.fileChooser.getSelectedFile();
            return selectedFile.getAbsolutePath();
        }

        return null;
    }
}
