package com.goff.email_desktop.graphic;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.goff.email_desktop.graphic.listener.AddButtonListener;
import com.goff.email_desktop.graphic.listener.DeleteButtonListener;
import com.goff.email_desktop.graphic.listener.ItemSelectionListener;
import com.goff.email_desktop.graphic.listener.SaveBodyButtonListener;
import com.goff.email_desktop.graphic.listener.SendAllListener;
import com.goff.email_desktop.graphic.listener.SendListener;

public class EmailManagerApp extends JPanel {
    private final PanelItem panelItem = new PanelItem();;

    public EmailManagerApp() {
        super(new BorderLayout());
        final JScrollPane listScrollPane = createList();

        createAddButton();
        createDeleteButton();
        createSendButton();
        createSendAllButton();
        createInputField();
        final JScrollPane bodyMessageWithScroll = createBodyMessageArea();
        final JScrollPane defaultBodyMessageWithScroll = createDefaultBodyMessageArea();
        createSaveTextButton();

        final JPanel buttonPane = createPanels();
        final JPanel textsAreaPanels = createTextAreaPanels(bodyMessageWithScroll, defaultBodyMessageWithScroll);
        final JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, listScrollPane, textsAreaPanels);
        splitPane.setResizeWeight(0.5);
        add(buttonPane, BorderLayout.PAGE_START);
        add(splitPane, BorderLayout.CENTER);
    }

    private JPanel createTextAreaPanels(final JScrollPane bodyMessageWithScroll,
            final JScrollPane defaultBodyMessageWithScroll) {
        final JPanel textsAreaPanels = new JPanel(new GridLayout(2, 2));
        textsAreaPanels.add(new JLabel("E-mail body"));
        textsAreaPanels.add(bodyMessageWithScroll);
        textsAreaPanels.add(new JLabel("E-mail default body."));
        textsAreaPanels.add(defaultBodyMessageWithScroll);
        return textsAreaPanels;
    }

    private void createSaveTextButton() {
        panelItem.saveBodyButton = new JButton(PanelItemNames.saveBody);
        panelItem.saveBodyButton.setActionCommand(PanelItemNames.saveBody);
        panelItem.saveBodyButton.addActionListener(new SaveBodyButtonListener(panelItem));
    }

    private JScrollPane createBodyMessageArea() {
        panelItem.emailBody = new JTextArea(15, 40);
        panelItem.emailBody.setEnabled(false);
        final JScrollPane logScrollPanel = new JScrollPane(panelItem.emailBody);
        return logScrollPanel;
    }

    private JScrollPane createDefaultBodyMessageArea() {
        panelItem.defaultBodyMessage = new JTextArea(15, 40);
        final JScrollPane defaultBodyMessageWithScroll = new JScrollPane(panelItem.defaultBodyMessage);
        return defaultBodyMessageWithScroll;
    }

    private JPanel createPanels() {
        final JPanel sendPanel = new JPanel(new GridLayout(2, 1));
        sendPanel.add(panelItem.sendButton);
        sendPanel.add(panelItem.sendAllButton);

        //Create a control panel, using the default FlowLayout.
        final JPanel buttonPane = new JPanel();
        buttonPane.add(panelItem.inputField);
        buttonPane.add(panelItem.addButton);
        buttonPane.add(panelItem.deleteButton);
        buttonPane.add(sendPanel);
        return buttonPane;
    }

    private void createInputField() {
        panelItem.inputField = new JTextField("aluno.exemplo@gmail.com", 25);
        panelItem.inputField.addActionListener(new AddButtonListener(panelItem));
    }

    private void createAddButton() {
        panelItem.addButton = new JButton(PanelItemNames.addString);
        panelItem.addButton.setActionCommand(PanelItemNames.addString);
        panelItem.addButton.addActionListener(new AddButtonListener(panelItem));
    }

    private JScrollPane createList() {
        panelItem.listModel = new DefaultListModel<>();

        panelItem.list = new JList<>(panelItem.listModel);
        panelItem.list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        panelItem.list.setSelectedIndex(0);
        panelItem.list.addListSelectionListener(new ItemSelectionListener(panelItem));
        final JScrollPane listScrollPane = new JScrollPane(panelItem.list);
        return listScrollPane;
    }

    private void createSendAllButton() {
        panelItem.sendAllButton = new JButton("Enviar todos");
        panelItem.sendAllButton.setToolTipText("Send all the e-mails.");
        panelItem.sendAllButton.setActionCommand(PanelItemNames.sendAll);
        panelItem.sendAllButton.addActionListener(new SendAllListener());
    }

    private void createSendButton() {
        panelItem.sendButton = new JButton("Enviar selecionado");
        panelItem.sendButton.setToolTipText("Send only the selected one.");
        panelItem.sendButton.setActionCommand(PanelItemNames.send);
        panelItem.sendButton.addActionListener(new SendListener(panelItem.list));
    }

    private void createDeleteButton() {
        panelItem.deleteButton = new JButton(PanelItemNames.deleteString);
        panelItem.deleteButton.setActionCommand(PanelItemNames.deleteString);
        panelItem.deleteButton.addActionListener(new DeleteButtonListener(panelItem));
    }
}
