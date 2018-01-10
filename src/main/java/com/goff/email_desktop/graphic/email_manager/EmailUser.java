package com.goff.email_desktop.graphic.email_manager;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class EmailUser {

    private static String email;
    private static String password;

    public static String getEmail() {
        if (email == null) {
            defineEmail();
        }
        return email;
    }

    public static String getPassword() {
        if (password == null) {
            definePassword();
        }
        return password;
    }

    private static void defineEmail() {
        email = JOptionPane.showInputDialog(null, "Enter the user's e-mail:", "User info",
                JOptionPane.QUESTION_MESSAGE);
    }

    private static void definePassword() {
        final JPasswordField pf = new JPasswordField();
        final int inputPasswordFieldResult = JOptionPane.showConfirmDialog(null, pf, "Enter e-mail's password",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (inputPasswordFieldResult == JOptionPane.OK_OPTION) {
            password = new String(pf.getPassword());
        }
    }
}
