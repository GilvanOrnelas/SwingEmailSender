package com.goff.email_desktop.graphic.email_manager.edit_frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.goff.email_desktop.email.Email;
import com.goff.email_desktop.email.EmailRepository;
import com.goff.email_desktop.graphic.email_manager.FileChooser;
import com.goff.email_desktop.graphic.email_manager.PanelItem;

public class EditEmailFrame extends JFrame implements ActionListener {

    private final JButton changeReportFileButton = new JButton("Change report file");
    private final JButton saveAndCloseButton = new JButton("Save & Close");

    private final JTextArea body = new JTextArea(15, 40);
    private final Email email;
    private final PanelItem panelItem;

    public EditEmailFrame(final Email email, final PanelItem panelItem) {
        this.email = email;
        this.panelItem = panelItem;
        final JPanel p = createPanelWithButtons();
        body.setText(email.getBody());

        getContentPane().add(p, "South");
        getContentPane().add(new JScrollPane(body), "Center");

        setTitle("E-mail body");
    }

    private JPanel createPanelWithButtons() {
        final JPanel p = new JPanel();

        p.add(changeReportFileButton);
        p.add(saveAndCloseButton);
        saveAndCloseButton.addActionListener(this);
        changeReportFileButton.addActionListener(this);
        return p;
    }

    @Override
    public void actionPerformed(final ActionEvent evt) {
        final Object source = evt.getSource();
        if (source == changeReportFileButton) {
            email.setAttachment(FileChooser.chooseFileAbsolutePath());
        } else if (source == saveAndCloseButton) {
            email.setBody(body.getText());
            email.setWithSendingError(false);
            email.setSended(false);
            new EmailRepository().save(email);
            panelItem.emailBody.setText(body.getText());
            this.dispose();
        }
    }

}
