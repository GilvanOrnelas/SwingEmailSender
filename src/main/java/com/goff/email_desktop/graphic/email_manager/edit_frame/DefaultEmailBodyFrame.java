package com.goff.email_desktop.graphic.email_manager.edit_frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.goff.email_desktop.email.EmailDefaultBody;
import com.goff.email_desktop.param.Parameter;

public class DefaultEmailBodyFrame extends JFrame implements ActionListener {

    private final JButton insertNameVariableButton = new JButton("Insert Name");

    private final JButton saveTextButton = new JButton("Save");
    private final JButton saveAndCloseButton = new JButton("Save & Close");

    private final JTextArea defaultBody = new JTextArea(15, 40);

    public DefaultEmailBodyFrame() {
        final JPanel p = createPanelWithButtons();
        defaultBody.setText(EmailDefaultBody.read());

        getContentPane().add(p, "South");
        getContentPane().add(new JScrollPane(defaultBody), "Center");

        setTitle("E-mail body");
    }

    private JPanel createPanelWithButtons() {
        final JPanel p = new JPanel();

        p.add(saveTextButton);
        p.add(insertNameVariableButton);
        p.add(saveAndCloseButton);
        saveAndCloseButton.addActionListener(this);
        insertNameVariableButton.addActionListener(this);
        saveTextButton.addActionListener(this);
        return p;
    }

    @Override
    public void actionPerformed(final ActionEvent evt) {
        final Object source = evt.getSource();
        if (source == insertNameVariableButton) {
            defaultBody.replaceSelection(Parameter.NAME);
        } else if (source == saveTextButton) {
            EmailDefaultBody.define(defaultBody.getText());
        } else if (source == saveAndCloseButton) {
            EmailDefaultBody.define(defaultBody.getText());
            this.dispose();
        }
    }

}
