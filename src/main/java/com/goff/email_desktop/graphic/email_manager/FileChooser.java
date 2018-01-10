package com.goff.email_desktop.graphic.email_manager;

import javax.swing.JFileChooser;

public class FileChooser {

    public static String chooseFileAbsolutePath() {
        final JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home"));
        final int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            final java.io.File selectedFile = fileChooser.getSelectedFile();
            return selectedFile.getAbsolutePath();
        }

        return null;
    }
}
