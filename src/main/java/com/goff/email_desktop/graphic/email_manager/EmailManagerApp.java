package com.goff.email_desktop.graphic.email_manager;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

import com.goff.email_desktop.email.Email;
import com.goff.email_desktop.email.EmailRepository;
import com.goff.email_desktop.graphic.email_manager.listener.AddButtonListener;
import com.goff.email_desktop.graphic.email_manager.listener.DeleteButtonListener;
import com.goff.email_desktop.graphic.email_manager.listener.EditButtonListener;
import com.goff.email_desktop.graphic.email_manager.listener.EditDefaultBodyButtonListener;
import com.goff.email_desktop.graphic.email_manager.listener.ItemSelectionListener;
import com.goff.email_desktop.graphic.email_manager.listener.SaveBodyButtonListener;
import com.goff.email_desktop.graphic.email_manager.listener.SendAllListener;
import com.goff.email_desktop.graphic.email_manager.listener.SendListener;

public class EmailManagerApp extends JPanel {
    private final PanelItem panelItem = new PanelItem();

    public EmailManagerApp() {
        super(new BorderLayout());
        createList();

        createAddButton();
        createDeleteButton();
        createEditButton();
        createSendButton();
        createSendAllButton();
        createBodyMessageArea();
        createDefaultBodyMessageArea();
        createSaveTextButton();
        createEditDefaultBodyButton();

        new Panel(panelItem).organize(this);
    }

    private void createEditDefaultBodyButton() {
        panelItem.editDefaultBodyButton = new JButton(PanelItemNames.editDefaultBody);
        panelItem.editDefaultBodyButton.setActionCommand(PanelItemNames.editDefaultBody);
        panelItem.editDefaultBodyButton.addActionListener(new EditDefaultBodyButtonListener(panelItem));
    }

    private void createSaveTextButton() {
        panelItem.saveBodyButton = new JButton(PanelItemNames.saveBody);
        panelItem.saveBodyButton.setActionCommand(PanelItemNames.saveBody);
        panelItem.saveBodyButton.addActionListener(new SaveBodyButtonListener(panelItem));
    }

    private void createBodyMessageArea() {
        panelItem.emailBody = new JTextArea(15, 40);
        panelItem.emailBody.setEnabled(false);

    }

    private void createDefaultBodyMessageArea() {
        panelItem.defaultBodyMessage = new JTextArea(15, 40);

    }

    private void createAddButton() {
        panelItem.addButton = new JButton(PanelItemNames.addString);
        panelItem.addButton.setActionCommand(PanelItemNames.addString);
        panelItem.addButton.addActionListener(new AddButtonListener(panelItem));
    }

    private void createList() {
        panelItem.listModel = new DefaultListModel<>();
        final List<Email> emails = new EmailRepository().readAll();
        for (final Email email : emails) {
            panelItem.listModel.addElement(email);
        }
        panelItem.list = new JList<>(panelItem.listModel);
        panelItem.list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        panelItem.list.addListSelectionListener(new ItemSelectionListener(panelItem));

    }

    private void createSendAllButton() {
        panelItem.sendAllButton = new JButton("Send all");
        panelItem.sendAllButton.setToolTipText("Send all the e-mails.");
        panelItem.sendAllButton.setActionCommand(PanelItemNames.sendAll);
        panelItem.sendAllButton.addActionListener(new SendAllListener(panelItem));
    }

    private void createSendButton() {
        panelItem.sendButton = new JButton("Send selected");
        panelItem.sendButton.setToolTipText("Send only the selected one.");
        panelItem.sendButton.setActionCommand(PanelItemNames.send);
        panelItem.sendButton.setEnabled(false);
        panelItem.sendButton.addActionListener(new SendListener(panelItem.list));
    }

    private void createDeleteButton() {
        panelItem.deleteButton = new JButton(PanelItemNames.deleteString);
        panelItem.deleteButton.setActionCommand(PanelItemNames.deleteString);
        panelItem.deleteButton.setEnabled(false);
        panelItem.deleteButton.addActionListener(new DeleteButtonListener(panelItem));
    }

    private void createEditButton() {
        panelItem.editButton = new JButton(PanelItemNames.editString);
        panelItem.editButton.setActionCommand(PanelItemNames.editString);
        panelItem.editButton.setEnabled(false);
        panelItem.editButton.addActionListener(new EditButtonListener(panelItem));
    }
}
