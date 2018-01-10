package com.goff.email_desktop.graphic.email_manager;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JTextArea;

import com.goff.email_desktop.email.Email;

public class PanelItem {

    public JList<Email> list;

    public DefaultListModel<Email> listModel;

    public JButton addButton;

    public JButton editDefaultBodyButton;

    public JButton saveBodyButton;

    public JButton deleteButton;

    public JButton editButton;

    public JButton sendButton;

    public JButton sendAllButton;

    public JTextArea emailBody;

    public JTextArea defaultBodyMessage;

    public JFileChooser fileChooser;
}
