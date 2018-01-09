package com.goff.email_desktop.graphic;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.goff.email_desktop.email.Email;

public class PanelItem {

    public JList<Email> list;

    public DefaultListModel<Email> listModel;

    public JButton addButton;

    public JButton saveBodyButton;

    public JButton deleteButton;

    public JButton sendButton;

    public JButton sendAllButton;

    public JTextField inputField;

    public JTextArea emailBody;

    public JTextArea defaultBodyMessage;

    public JFileChooser fileChooser;
}
